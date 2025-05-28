package com.veterinaria.petly.controller;

import com.veterinaria.petly.entity.Client;
import com.veterinaria.petly.entity.Pet;
import com.veterinaria.petly.repository.ClientRepository;
import com.veterinaria.petly.repository.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*")
public class PetController {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/client/{clientId}")
    @Transactional
    public ResponseEntity<Pet> createPetForClient(
            @PathVariable Integer clientId,
            @RequestBody Pet pet,
            UriComponentsBuilder uriComponentsBuilder) {

        return clientRepository.findById(clientId)
                .map(client -> {
                    pet.setOwner(client);
                    Pet savedPet = petRepository.save(pet);

                    // Actualizar la relación bidireccional
                    client.getPets().add(savedPet);
                    clientRepository.save(client);

                    URI location = uriComponentsBuilder.path("/api/pets/{id}")
                            .buildAndExpand(savedPet.getId())
                            .toUri();

                    return ResponseEntity.created(location).body(savedPet);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Integer id) {
        return petRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Integer id) {
        petRepository.deleteById(id);
    }
}
