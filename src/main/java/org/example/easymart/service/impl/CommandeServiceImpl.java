package org.example.easymart.service.impl;

import org.example.easymart.repository.CommandeRepository;
import org.example.easymart.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService {
    private CommandeRepository commandeRepository;


    @Autowired
    public CommandeServiceImpl(CommandeRepository commandeRepository)
    {
        this.commandeRepository = commandeRepository;
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
}
