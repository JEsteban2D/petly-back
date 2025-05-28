package com.veterinaria.petly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String species;
    private String breed;
    private Integer age;
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false) // Asegura que siempre tenga dueño
    private Client owner;
}
