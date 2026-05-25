package com.tecsup.gestion_pedidos.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "auditoria_log")
@Data @NoArgsConstructor @AllArgsConstructor
public class AuditoriaLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accion;
    private String metodo;
    private Timestamp fecha;
    private String usuario;
}