package org.example.easymart.service.impl;

import org.example.easymart.dto.request.ClientDTO;
import org.example.easymart.dto.response.ClientDtoResponse;
import org.example.easymart.entity.Client;
import org.example.easymart.enumeration.CustomerTier;
import org.example.easymart.exception.ClientNotFoundException;
import org.example.easymart.mapper.ClientMapper;
import org.example.easymart.repository.ClientRepository;
import org.example.easymart.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper)
    {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
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


}
