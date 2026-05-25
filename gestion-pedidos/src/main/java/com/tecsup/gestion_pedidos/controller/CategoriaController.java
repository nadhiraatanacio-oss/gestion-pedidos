package com.tecsup.gestion_pedidos.controller;

import com.tecsup.gestion_pedidos.entity.Categoria;
import com.tecsup.gestion_pedidos.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/categorias") @RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService service;

    @GetMapping public List<Categoria> listar() { return service.listar(); }
    @PostMapping public ResponseEntity<Categoria> guardar(@Valid @RequestBody Categoria c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(c));
    }
    @PutMapping("/{id}") public Categoria actualizar(@PathVariable Long id, @Valid @RequestBody Categoria c) {
        return service.actualizar(id, c);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id); return ResponseEntity.noContent().build();
    }
}