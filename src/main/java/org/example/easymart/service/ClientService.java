package org.example.easymart.service;

import org.example.easymart.dto.request.ClientDTO;
import org.example.easymart.dto.response.ClientDtoResponse;

import java.util.List;

public interface ClientService {

    public ClientDtoResponse createClient(ClientDTO clientDTO);
    public ClientDtoResponse updateClientById(Long id,ClientDTO clientDTO);
    public void deleteClientById(Long id);
    public List<ClientDtoResponse> getAllClients();
}
