package org.example.easymart.controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.example.easymart.aop.annotations.Secured;
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

    @Secured(role = "ADMIN")
    @PostMapping("/create/commande")
    public ResponseEntity<CommandeDtoResponse> createCommande(@Valid @RequestBody CommandeDTO commandeDTO) {
        return ResponseEntity.ok().body(this.commandeService.createCommande(commandeDTO));
    }

    @PutMapping("/confirm/commande")
    public ResponseEntity<CommandeDtoResponse> confirmOrder(@Valid @RequestParam("orderId") Long orderId)
    {
        return ResponseEntity.ok().body(this.commandeService.confirmCommande(orderId));
    }

    @PutMapping("/reject/commande")
    public ResponseEntity<CommandeDtoResponse> rejectCommande(@RequestParam("orderId") Long orderId)
    {
        return ResponseEntity.ok().body(this.commandeService.rejectCommandeById(orderId));
    }
}
