package com.tecsup.gestion_pedidos.repository;

import com.tecsup.gestion_pedidos.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}