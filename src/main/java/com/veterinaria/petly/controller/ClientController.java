package com.veterinaria.petly.controller;

import com.veterinaria.petly.entity.Client;
import com.veterinaria.petly.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Integer id, @RequestBody Client clientDetails) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            client.setFullName(clientDetails.getFullName());
            client.setPhone(clientDetails.getPhone());
            client.setEmail(clientDetails.getEmail());
            client.setAddress(clientDetails.getAddress());
            return clientRepository.save(client);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id) {
        clientRepository.deleteById(id);
    }

}
