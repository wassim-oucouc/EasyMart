package org.example.easymart.controller;

import org.example.easymart.dto.request.CommandeDTO;
import org.example.easymart.dto.response.CommandeDtoResponse;
import org.example.easymart.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandeController {

    private final CommandeService commandeService;

    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @PostMapping("/create/commande")
    public ResponseEntity<CommandeDtoResponse> createCommande(@RequestBody CommandeDTO commandeDTO) {
        return ResponseEntity.ok().body(this.commandeService.createCommande(commandeDTO));
    }

    @PutMapping("/confirm/commande")
    public ResponseEntity<CommandeDtoResponse> confirmOrder(@RequestParam("orderId") Long orderId)
    {
        return ResponseEntity.ok().body(this.commandeService.confirmCommande(orderId));
    }
}
