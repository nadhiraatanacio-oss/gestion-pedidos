package com.tecsup.gestion_pedidos.service;

import com.tecsup.gestion_pedidos.entity.Cliente;
import com.tecsup.gestion_pedidos.exception.ResourceNotFoundException;
import com.tecsup.gestion_pedidos.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repo;

    public List<Cliente> listar() { return repo.findAll(); }

    public Cliente obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }

    public Cliente guardar(Cliente c) { return repo.save(c); }

    public Cliente actualizar(Long id, Cliente c) {
        Cliente existing = obtener(id);
        existing.setNombres(c.getNombres());
        existing.setApellidos(c.getApellidos());
        existing.setCorreo(c.getCorreo());
        existing.setTelefono(c.getTelefono());
        existing.setDireccion(c.getDireccion());
        return repo.save(existing);
    }

    public void eliminar(Long id) { obtener(id); repo.deleteById(id); }
}