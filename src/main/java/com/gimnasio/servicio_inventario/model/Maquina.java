package com.gimnasio.servicio_inventario.model;

import java.time.LocalDate;
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
@Table(name = "maquinas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String zona; // Cardio, Pesas, Funcional

    @Column(length = 50)
    private String marca;

    @Column(length = 50)
    private String modelo;

    @Column(nullable = false, length = 30)
    private String estado; // Operativa, Mantenimiento, Fuera de Servicio

    @Column(name = "ultimo_mantenimiento")
    private LocalDate ultimoMantenimiento;

    @Column(name = "proximo_mantenimiento")
    private LocalDate proximoMantenimiento;

    @Column(name = "fecha_adquisicion")
    private LocalDate fechaAdquisicion;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "numero_serie", length = 100)
    private String numeroSerie;

    @Column(name = "horas_uso")
    private Integer horasUso;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (estado == null) {
            estado = "Operativa";
        }
        if (horasUso == null) {
            horasUso = 0;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}
