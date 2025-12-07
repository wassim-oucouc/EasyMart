package org.example.easymart;

import org.example.easymart.dto.request.ClientDTO;
import org.example.easymart.dto.request.CommandeDTO;
import org.example.easymart.dto.request.OrderItemDTO;
import org.example.easymart.dto.response.*;
import org.example.easymart.entity.Client;
import org.example.easymart.entity.Commande;
import org.example.easymart.entity.OrderItem;
import org.example.easymart.entity.Produit;
import org.example.easymart.enumeration.CustomerTier;
import org.example.easymart.enumeration.OrderStatus;
import org.example.easymart.mapper.CommandeMapper;
import org.example.easymart.repository.CommandeRepository;
import org.example.easymart.repository.ProduitRepository;
import org.example.easymart.service.ClientService;
import org.example.easymart.service.CommandeService;
import org.example.easymart.service.DiscountService;
import org.example.easymart.service.PromoCodeService;
import org.example.easymart.service.impl.CommandeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommandeServiceTest {

    @Mock
    private  CommandeRepository commandeRepository;
    @Mock
    private  CommandeMapper commandeMapper;
    @Mock
    private  ProduitRepository produitRepository;
    @Mock
    private  ClientService clientService;
    @Mock
    private  DiscountService discountService;
    @Mock
    private  PromoCodeService promoCodeService;
    @InjectMocks
    private CommandeServiceImpl commandeService;

    @Test
    public void createCommandeTest()
    {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(1L);
        dto.setClientId(5L);
        dto.setDate(new Date());
        dto.setCode_promo("PROMO10");
        dto.setOrderStatus(OrderStatus.PENDING);
        dto.setSous_total(BigDecimal.valueOf(520));
        dto.setRemise(BigDecimal.ZERO);
        dto.setTva(BigDecimal.valueOf(40));
        dto.setTotal(BigDecimal.valueOf(240));
        dto.setMontant_restant(BigDecimal.valueOf(240));
        dto.setQuantity(2);
        OrderItemDTO item = new OrderItemDTO();
        item.setId(1L);
        item.setProduitId(10L);
        item.setQuantite(2);
        item.setTotalLigne(BigDecimal.valueOf(200));
        dto.setOrderItemDTOList(List.of(item));

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(5L);
        clientDTO.setNom("John Doe");
        clientDTO.setEmail("john.doe@gmail.com");
        clientDTO.setUserId(100L);
        clientDTO.setCustomerTier(CustomerTier.SILVER);

        UserDtoResponse user = new UserDtoResponse();
        user.setId(100L);
        user.setUsername("john_doe");

        ClientDtoResponse clientResponse = new ClientDtoResponse();
        clientResponse.setId(5L);
        clientResponse.setNom("John Doe");
        clientResponse.setEmail("john.doe@example.com");
        clientResponse.setCustomerTier(CustomerTier.SILVER);
        clientResponse.setUserDtoResponse(user);

        Produit produit = Produit.builder()
                .id(10L)
                .nom("Laptop")
                .prix(1000)
                .stock(50L)
                .disabled(false)
                .build();

        Client client = new Client();
        client.setId(5L);
        client.setNom("John Doe");
        client.setEmail("john.doe@example.com");

        OrderItem itemEntity = new OrderItem();
        itemEntity.setId(1L);
        itemEntity.setProduit(produit);
        itemEntity.setQuantite(2);
        itemEntity.setTotalLigne(BigDecimal.valueOf(200));

        Commande commande = new Commande();
        commande.setId(1L);
        commande.setClient(client);
        commande.setOrderItems(List.of(itemEntity));
        commande.setDate(new Date());
        commande.setQuantity(2);
        commande.setSous_total(BigDecimal.valueOf(520));
        commande.setRemise(BigDecimal.ZERO);
        commande.setTva(BigDecimal.valueOf(40));
        commande.setTotal(BigDecimal.valueOf(240));
        commande.setCode_promo("PROMO10");
        commande.setMontant_restant(BigDecimal.valueOf(240));
        commande.setOrderStatus(OrderStatus.PENDING);

                ProduitDtoResponse produitDtoResponse = new ProduitDtoResponse();
        produitDtoResponse.setId(10L);
        produitDtoResponse.setNom("Laptop");
        produitDtoResponse.setPrix(1000);
        produitDtoResponse.setStock(50L);

        OrderItemDtoResponse item1 = new OrderItemDtoResponse();
        item1.setId(1L);
        item1.setProduitDtoResponse(produitDtoResponse);
        item1.setQuantite(2);
        item1.setTotalLigne(BigDecimal.valueOf(2000));

        CommandeDtoResponse commandeDtoResponse = new CommandeDtoResponse();
        commandeDtoResponse.setId(1L);
        commandeDtoResponse.setClientDtoResponse(clientResponse);
        commandeDtoResponse.setOrderItemDtoResponseList(List.of(item1));
        commandeDtoResponse.setDate(new Date());
        commandeDtoResponse.setQuantity(2);
        commandeDtoResponse.setSous_total(BigDecimal.valueOf(2000));
        commandeDtoResponse.setRemise(BigDecimal.valueOf(100)); // exemple de remise
        commandeDtoResponse.setTva(BigDecimal.valueOf(200));
        commandeDtoResponse.setTotal(BigDecimal.valueOf(2100));
        commandeDtoResponse.setCode_promo("PROMO10");
        commandeDtoResponse.setMontant_restant(BigDecimal.valueOf(2100));
        commandeDtoResponse.setOrderStatus(OrderStatus.PENDING);

        PromoCodeDtoResponse promo = new PromoCodeDtoResponse();
        promo.setId(1L);
        promo.setCode("PROMO10");
        promo.setPourcentage(BigDecimal.valueOf(10));

        when(clientService.getClientById(5L)).thenReturn(clientResponse);
        when(promoCodeService.getPromoCodeByCode("PROMO10")).thenReturn(promo);
        when(produitRepository.findById(10L)).thenReturn(Optional.of(produit));
        when(produitRepository.save(produit)).thenReturn(produit);
        when(commandeMapper.toEntity(dto)).thenReturn(commande);
        doReturn(BigDecimal.valueOf(0.05))
                .when(discountService)
                .getRemiseByStatus(eq(CustomerTier.SILVER), eq(BigDecimal.valueOf(2000)));
        when(commandeMapper.toDtoResponse(commande)).thenReturn(commandeDtoResponse);
        when(commandeRepository.save(commande)).thenReturn(commande);

        CommandeDtoResponse commandeDtoResponseCreated = this.commandeService.createCommande(dto);
        assertNotNull(commandeDtoResponseCreated);
        assertEquals(commandeDtoResponse.getCode_promo(),dto.getCode_promo());

    }
}
