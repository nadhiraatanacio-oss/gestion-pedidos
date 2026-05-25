package com.tecsup.gestion_pedidos.controller;

import com.tecsup.gestion_pedidos.dto.PedidoRequestDTO;
import com.tecsup.gestion_pedidos.entity.Pedido;
import com.tecsup.gestion_pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/pedidos") @RequiredArgsConstructor
public class PedidoController {
    private final PedidoService service;

    @GetMapping public List<Pedido> listar() { return service.listar(); }
    @GetMapping("/{id}") public Pedido obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping public ResponseEntity<Pedido> registrar(@RequestBody PedidoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id); return ResponseEntity.noContent().build();
    }
}