package com.tecsup.gestion_pedidos.dto;

import lombok.*;
import java.util.List;

@Data
public class PedidoRequestDTO {
    private Long clienteId;
    private List<DetalleDTO> detalles;

    @Data
    public static class DetalleDTO {
        private Long productoId;
        private Integer cantidad;
    }
}