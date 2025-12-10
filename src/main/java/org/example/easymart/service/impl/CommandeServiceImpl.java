package org.example.easymart.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.easymart.aop.annotations.Secured;
import org.example.easymart.dto.request.CommandeDTO;
import org.example.easymart.dto.request.OrderItemDTO;
import org.example.easymart.dto.response.ClientDtoResponse;
import org.example.easymart.dto.response.CommandeDtoResponse;
import org.example.easymart.dto.response.ProduitDtoResponse;
import org.example.easymart.dto.response.PromoCodeDtoResponse;
import org.example.easymart.entity.Commande;
import org.example.easymart.entity.OrderItem;
import org.example.easymart.entity.Produit;
import org.example.easymart.enumeration.OrderStatus;
import org.example.easymart.exception.CommandeNotFoundException;
import org.example.easymart.exception.ConfirmationCommandeException;
import org.example.easymart.exception.InsufficientQuantityException;
import org.example.easymart.exception.ProduitNotFoundException;
import org.example.easymart.mapper.CommandeMapper;
import org.example.easymart.repository.CommandeRepository;
import org.example.easymart.repository.ProduitRepository;
import org.example.easymart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private final ClientService clientService;
    private final DiscountService discountService;
    private final ProduitRepository produitRepository;
    private final PromoCodeService promoCodeService;


    @Autowired
    public CommandeServiceImpl(CommandeRepository commandeRepository, CommandeMapper commandeMapper, ClientService clientService, DiscountService discountService, ProduitRepository produitRepository, PromoCodeService promoCodeService)
    {
        this.commandeRepository = commandeRepository;
        this.commandeMapper = commandeMapper;
        this.clientService = clientService;
        this.discountService = discountService;
        this.produitRepository = produitRepository;
        this.promoCodeService = promoCodeService;
    }

    public Boolean checkCommandeExitsByProductId(Long id)
    {
        List<Object[]> rows = this.commandeRepository.checkCommandesProductByProductId(id);
        if(rows.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    @Secured(role = {"ADMIN"})
    public CommandeDtoResponse getCommandeById(Long commandeId)
    {
       Commande commande = this.commandeRepository.findById(commandeId).orElseThrow(() -> new CommandeNotFoundException("commande not exists id : " + commandeId));
       return this.commandeMapper.toDtoResponse(commande);
    }

    public void DeductMotantFromMotantRestant(BigDecimal montant,Long commandeId)
    {
        this.commandeRepository.DeductMomtantFromMontant_restant(montant,commandeId);
    }

    @Transactional
    @Secured(role = {"ADMIN"})
    public CommandeDtoResponse createCommande(CommandeDTO commandeDTO)
    {
        ClientDtoResponse clientDtoResponse =  this.clientService.getClientById(commandeDTO.getClientId());

        if (commandeDTO.getSous_total() == null) {
            commandeDTO.setSous_total(BigDecimal.ZERO);
        }

        this.checkProduitsQuantity(commandeDTO.getOrderItemDTOList());
        BigDecimal sousTotal = BigDecimal.ZERO;
        PromoCodeDtoResponse promoCodeDtoResponse = this.promoCodeService.getPromoCodeByCode(commandeDTO.getCode_promo());


        for(OrderItemDTO orderItemDTO : commandeDTO.getOrderItemDTOList())
        {
          Produit produit =   produitRepository.findById(orderItemDTO.getProduitId()).orElseThrow(() -> new ProduitNotFoundException("Produit non exists id :" + orderItemDTO.getProduitId()));
          produit.setStock(produit.getStock() - orderItemDTO.getQuantite());
            sousTotal = sousTotal.add(BigDecimal.valueOf(produit.getPrix()).multiply(BigDecimal.valueOf(orderItemDTO.getQuantite())));
            this.produitRepository.save(produit);
        }
        commandeDTO.setSous_total(sousTotal);

        BigDecimal remiseTier =  this.discountService.getRemiseByStatus(clientDtoResponse.getCustomerTier(),commandeDTO.getSous_total());
        BigDecimal remise = remiseTier != null ? remiseTier : BigDecimal.ZERO;
        BigDecimal couponCodePourcentage = promoCodeDtoResponse.getPourcentage() != null ? promoCodeDtoResponse.getPourcentage() : BigDecimal.ZERO;
        BigDecimal  remiseFinal = remise.add(couponCodePourcentage);
            commandeDTO.setRemise(remiseFinal);
            BigDecimal motantRemise = commandeDTO.getSous_total().multiply(remiseFinal);
        BigDecimal montantHT = commandeDTO.getSous_total().subtract(motantRemise);
        commandeDTO.setTva(montantHT.multiply(BigDecimal.valueOf(0.20)));
        commandeDTO.setTotal(montantHT.add(commandeDTO.getTva()));
        Commande commande = this.commandeMapper.toEntity(commandeDTO);
        commande.setOrderStatus(OrderStatus.PENDING);
        commande.setMontant_restant(commande.getTotal());
        this.commandeRepository.save(commande);
       return  this.commandeMapper.toDtoResponse(commande);

    }

    @Secured(role = {"ADMIN"})
    public CommandeDtoResponse confirmCommande(Long id)
    {
        Commande commande = this.commandeRepository.findById(id).orElseThrow(() -> new CommandeNotFoundException("Commande not exists with id " + id));

        if(commande.getMontant_restant().compareTo(BigDecimal.ZERO) != 0)
        {
            throw new ConfirmationCommandeException("Please Complete Your Payments Before Confirm Order!");
        }
        this.clientService.recalculateNiveauFidilitéByTotal(commande.getClient().getId(),commande.getTotal());
        commande.setOrderStatus(OrderStatus.CONFIRMED);
        this.commandeRepository.save(commande);
        return this.commandeMapper.toDtoResponse(commande);


    }

    @Transactional
    @Secured(role = {"ADMIN"})
    public CommandeDtoResponse rejectCommandeById(Long id)
    {
        Commande commande = this.commandeRepository.findById(id).orElseThrow(() -> new CommandeNotFoundException("Commande not exists with id " + id));
        List<OrderItem> orderItems = commande.getOrderItems();
        for(OrderItem orderItem : orderItems)
        {
            this.produitRepository.addQuantity(orderItem.getProduit().getId(),orderItem.getQuantite());
        }
        commande.setOrderStatus(OrderStatus.REJECTED);
        this.commandeRepository.save(commande);
       return  this.commandeMapper.toDtoResponse(commande);
    }


    public void checkProduitsQuantity(List<OrderItemDTO> orderItemDTO)
    {
        for(OrderItemDTO  orderItemDTOL : orderItemDTO)
        {
            Produit produit =  this.produitRepository.findById(orderItemDTOL.getProduitId()).orElseThrow(() -> new ProduitNotFoundException("produit not exists id :" + orderItemDTOL.getProduitId()));
            int quantityCalculated = Math.toIntExact(produit.getStock() - orderItemDTOL.getQuantite());
            if(quantityCalculated < 0)
            {
                throw new InsufficientQuantityException("Quantity Pas disponible Pour Produit :" + produit.getNom());
            }
        }
    }
}
