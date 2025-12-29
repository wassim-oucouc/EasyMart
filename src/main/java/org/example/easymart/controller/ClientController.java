package org.example.easymart.controller;


import jakarta.validation.Valid;
import org.example.easymart.dto.request.ClientDTO;
import org.example.easymart.dto.response.ClientDtoResponse;
import org.example.easymart.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client/create")
    public ResponseEntity<ClientDtoResponse> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok().body(this.clientService.createClient(clientDTO));
    }

    @PutMapping("/client/update/{id}")
    public ResponseEntity<ClientDtoResponse> updateClient(@PathVariable("id") Long id, @Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok().body(this.clientService.updateClientById(id, clientDTO));
    }

    @DeleteMapping("/client/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        this.clientService.deleteClientById(id);
        return ResponseEntity.ok().body("client is deleted");
    }

    @GetMapping("/client/all")
    public ResponseEntity<List<ClientDtoResponse>> getAllClients() {
        return ResponseEntity.ok().body(this.clientService.getAllClients());
    }
}
