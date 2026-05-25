package com.tecsup.gestion_pedidos.service;

import com.tecsup.gestion_pedidos.entity.Categoria;
import com.tecsup.gestion_pedidos.exception.ResourceNotFoundException;
import com.tecsup.gestion_pedidos.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repo;

    public List<Categoria> listar() { return repo.findAll(); }

    public Categoria guardar(Categoria c) { return repo.save(c); }

    public Categoria actualizar(Long id, Categoria c) {
        Categoria existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        existing.setNombre(c.getNombre());
        return repo.save(existing);
    }

    public void eliminar(Long id) {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        repo.deleteById(id);
    }
}