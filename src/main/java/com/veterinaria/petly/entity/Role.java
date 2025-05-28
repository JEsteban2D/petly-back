package com.veterinaria.petly.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Constructor sin argumentos (requerido por JPA)
    public Role() {
    }

    // Constructor con parámetro ERole
    public Role(ERole name) {
        this.name = name;
    }
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    @Override
    public String getAuthority() {
        return name.name();  // Devuelve el nombre del enum como autoridad
    }
}
