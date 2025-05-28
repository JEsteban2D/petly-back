package com.veterinaria.petly;

import com.veterinaria.petly.entity.ERole;
import com.veterinaria.petly.entity.Role;
import com.veterinaria.petly.entity.User;
import com.veterinaria.petly.repository.RoleRepository;
import com.veterinaria.petly.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PetlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetlyApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository,
						   UserRepository userRepository,
						   PasswordEncoder passwordEncoder) {
		return args -> {
			// Crear roles si no existen
			if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
				roleRepository.save(new Role(ERole.ROLE_ADMIN));
			}
			if (roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
				roleRepository.save(new Role(ERole.ROLE_USER));
			}

			// Crear usuario admin si no existe
			if (userRepository.findByUsername("admin").isEmpty()) {
				Set<Role> adminRoles = new HashSet<>();
				adminRoles.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());

				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setRoles(adminRoles);

				userRepository.save(admin);
			}

			// Crear usuario normal si no existe
			if (userRepository.findByUsername("user").isEmpty()) {
				Set<Role> userRoles = new HashSet<>();
				userRoles.add(roleRepository.findByName(ERole.ROLE_USER).get());

				User user = new User();
				user.setUsername("user");
				user.setPassword(passwordEncoder.encode("user123"));
				user.setRoles(userRoles);

				userRepository.save(user);
			}
		};
	}
}
