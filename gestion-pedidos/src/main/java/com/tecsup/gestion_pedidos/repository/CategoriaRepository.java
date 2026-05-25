package com.tecsup.gestion_pedidos.repository;

import com.tecsup.gestion_pedidos.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}