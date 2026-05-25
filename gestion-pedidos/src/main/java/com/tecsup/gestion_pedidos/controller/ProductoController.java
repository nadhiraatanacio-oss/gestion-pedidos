package com.tecsup.gestion_pedidos.controller;

import com.tecsup.gestion_pedidos.entity.Producto;
import com.tecsup.gestion_pedidos.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/productos") @RequiredArgsConstructor
public class ProductoController {
    private final ProductoService service;

    @GetMapping public List<Producto> listar() { return service.listar(); }
    @GetMapping("/{id}") public Producto obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping public ResponseEntity<Producto> guardar(@Valid @RequestBody Producto p) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(p));
    }
    @PutMapping("/{id}") public Producto actualizar(@PathVariable Long id, @Valid @RequestBody Producto p) {
        return service.actualizar(id, p);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id); return ResponseEntity.noContent().build();
    }
}