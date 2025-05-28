package com.veterinaria.petly.repository;

import com.veterinaria.petly.entity.Client;
import com.veterinaria.petly.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findByOwner(Client owner);
}
