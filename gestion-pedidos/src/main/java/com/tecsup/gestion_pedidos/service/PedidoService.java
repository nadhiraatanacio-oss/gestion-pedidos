package com.tecsup.gestion_pedidos.service;

import com.tecsup.gestion_pedidos.dto.PedidoRequestDTO;
import com.tecsup.gestion_pedidos.entity.*;
import com.tecsup.gestion_pedidos.exception.*;
import com.tecsup.gestion_pedidos.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service @RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepo;
    private final ClienteRepository clienteRepo;
    private final ProductoRepository productoRepo;

    public List<Pedido> listar() { return pedidoRepo.findAll(); }

    public Pedido obtener(Long id) {
        return pedidoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));
    }

    public Pedido registrar(PedidoRequestDTO dto) {
        Cliente cliente = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        if (dto.getDetalles() == null || dto.getDetalles().isEmpty())
            throw new ResourceNotFoundException("El pedido debe tener al menos un producto");

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFecha(LocalDate.now());

        List<DetallePedido> detalles = new ArrayList<>();
        double total = 0;

        for (PedidoRequestDTO.DetalleDTO d : dto.getDetalles()) {
            Producto producto = productoRepo.findById(d.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

            if (producto.getStock() < d.getCantidad())
                throw new StockInsuficienteException("Stock insuficiente para: " + producto.getNombre());

            producto.setStock(producto.getStock() - d.getCantidad());
            productoRepo.save(producto);

            double subtotal = producto.getPrecio() * d.getCantidad();
            total += subtotal;

            DetallePedido detalle = new DetallePedido();
            detalle.setCantidad(d.getCantidad());
            detalle.setSubtotal(subtotal);
            detalle.setProducto(producto);
            detalle.setPedido(pedido);
            detalles.add(detalle);
        }

        pedido.setTotal(total);
        pedido.setDetalles(detalles);
        return pedidoRepo.save(pedido);
    }

    public void eliminar(Long id) { obtener(id); pedidoRepo.deleteById(id); }
}