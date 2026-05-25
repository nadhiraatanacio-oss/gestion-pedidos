package com.tecsup.gestion_pedidos.service;

import com.tecsup.gestion_pedidos.entity.Producto;
import com.tecsup.gestion_pedidos.exception.ResourceNotFoundException;
import com.tecsup.gestion_pedidos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository repo;

    public List<Producto> listar() { return repo.findAll(); }

    public Producto obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    public Producto guardar(Producto p) { return repo.save(p); }

    public Producto actualizar(Long id, Producto p) {
        Producto existing = obtener(id);
        existing.setNombre(p.getNombre());
        existing.setDescripcion(p.getDescripcion());
        existing.setPrecio(p.getPrecio());
        existing.setStock(p.getStock());
        existing.setCategoria(p.getCategoria());
        return repo.save(existing);
    }

    public void eliminar(Long id) { obtener(id); repo.deleteById(id); }
}