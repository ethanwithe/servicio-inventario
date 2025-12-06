package com.gimnasio.servicio_inventario.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gimnasio.servicio_inventario.model.Producto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String categoria;
    private BigDecimal precio;
    private Integer stock;
    private String estado;
    private Integer stockMinimo;
    private Integer ventas;
    private String descripcion;
    private String marca;
    private String proveedor;
    private String codigoBarras;
    private LocalDateTime fechaRegistro;
    private BigDecimal valorTotal; // precio * stock

    public static ProductoDTO fromEntity(Producto producto) {
        ProductoDTO dto = ProductoDTO.builder()
            .id(producto.getId())
            .nombre(producto.getNombre())
            .categoria(producto.getCategoria())
            .precio(producto.getPrecio())
            .stock(producto.getStock())
            .estado(producto.getEstado())
            .stockMinimo(producto.getStockMinimo())
            .ventas(producto.getVentas())
            .descripcion(producto.getDescripcion())
            .marca(producto.getMarca())
            .proveedor(producto.getProveedor())
            .codigoBarras(producto.getCodigoBarras())
            .fechaRegistro(producto.getFechaRegistro())
            .build();

        // Calcular valor total
        if (producto.getPrecio() != null && producto.getStock() != null) {
            dto.setValorTotal(producto.getPrecio().multiply(BigDecimal.valueOf(producto.getStock())));
        }

        return dto;
    }
}
