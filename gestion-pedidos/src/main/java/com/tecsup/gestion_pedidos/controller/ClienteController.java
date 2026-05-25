package com.tecsup.gestion_pedidos.controller;

import com.tecsup.gestion_pedidos.entity.Cliente;
import com.tecsup.gestion_pedidos.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/clientes") @RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @GetMapping public List<Cliente> listar() { return service.listar(); }
    @GetMapping("/{id}") public Cliente obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping public ResponseEntity<Cliente> guardar(@Valid @RequestBody Cliente c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(c));
    }
    @PutMapping("/{id}") public Cliente actualizar(@PathVariable Long id, @Valid @RequestBody Cliente c) {
        return service.actualizar(id, c);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id); return ResponseEntity.noContent().build();
    }
}