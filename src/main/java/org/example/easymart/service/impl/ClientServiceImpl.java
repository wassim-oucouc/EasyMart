package org.example.easymart.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.easymart.dto.request.ClientDTO;
import org.example.easymart.dto.request.CommandeDTO;
import org.example.easymart.dto.response.ClientDtoResponse;
import org.example.easymart.entity.Client;
import org.example.easymart.enumeration.CustomerTier;
import org.example.easymart.exception.ClientNotFoundException;
import org.example.easymart.mapper.ClientMapper;
import org.example.easymart.repository.ClientRepository;
import org.example.easymart.repository.CommandeRepository;
import org.example.easymart.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final CommandeRepository commandeRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper, CommandeRepository commandeRepository)
    {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.commandeRepository = commandeRepository;
    }

    public ClientDtoResponse createClient(ClientDTO clientDTO)
    {
        Client client = this.clientMapper.toEntity(clientDTO);
        Client clientCreated = this.clientRepository.save(client);
       return  this.clientMapper.toDtoResponse(clientCreated);
    }
    public ClientDtoResponse updateClientById(Long id,ClientDTO clientDTO)
    {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("client not exists id : " + id));
        Client clientMapped = this.clientMapper.toEntity(clientDTO);
        client.setUser(clientMapped.getUser());
        client.setNom(clientMapped.getNom());
        client.setEmail(clientMapped.getEmail());
        client.setCustomerTier(clientMapped.getCustomerTier());
        return this.clientMapper.toDtoResponse(this.clientRepository.save(client));

    }
    public void deleteClientById(Long id)
    {
        this.clientRepository.deleteById(id);
    }
    public List<ClientDtoResponse> getAllClients()
    {
        return this.clientRepository.findAll().stream().map(clientMapper::toDtoResponse).toList();
    }

    public ClientDtoResponse getClientById(Long id)
    {
       Client client =  this.clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("client not exists id : " + id));
       return this.clientMapper.toDtoResponse(client);
    }

    public void recalculateNiveauFidilitéByTotal(Long clientId, BigDecimal total)
    {
        Client client =  this.clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("client not exists id : " + clientId));
        int count = this.commandeRepository.countCommandeByClient_Id(clientId);
        log.info("total de commande du client {}",count);

        if((count >= 3 && count < 10) || total.compareTo(BigDecimal.valueOf(1050)) >= 0)
        {
            client.setCustomerTier(CustomerTier.SILVER);
        }
        else if((count >= 10 && count < 20) || total.compareTo(BigDecimal.valueOf(5150)) >= 0 )
        {
            client.setCustomerTier(CustomerTier.GOLD);
        }
        else if((count >= 20) || total.compareTo(BigDecimal.valueOf(15000)) >= 0)
        {
            client.setCustomerTier(CustomerTier.PLATINUM);
        }

        clientRepository.save(client);
    }


}
