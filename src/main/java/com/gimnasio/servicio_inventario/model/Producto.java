package com.gimnasio.servicio_inventario.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 20)
    private String estado; // Disponible, Stock Bajo, Agotado

    @Column(name = "stock_minimo")
    private Integer stockMinimo;

    private Integer ventas;

    @Column(length = 500)
    private String descripcion;

    @Column(length = 50)
    private String marca;

    @Column(length = 50)
    private String proveedor;

    @Column(name = "codigo_barras", length = 50)
    private String codigoBarras;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (estado == null) {
            actualizarEstado();
        }
        if (ventas == null) {
            ventas = 0;
        }
        if (stockMinimo == null) {
            stockMinimo = 10;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
        actualizarEstado();
    }

    private void actualizarEstado() {
        if (stock <= 0) {
            estado = "Agotado";
        } else if (stock < stockMinimo) {
            estado = "Stock Bajo";
        } else {
            estado = "Disponible";
        }
    }
}
