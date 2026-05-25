package com.tecsup.gestion_pedidos.config;

import com.tecsup.gestion_pedidos.entity.Usuario;
import com.tecsup.gestion_pedidos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (repo.findByUsername("admin").isEmpty()) {
            repo.save(new Usuario(null, "admin", encoder.encode("admin123"), "ROLE_ADMIN"));
            repo.save(new Usuario(null, "user", encoder.encode("user123"), "ROLE_USER"));
        }
    }
}