package com.gimnasio.servicio_inventario.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import com.gimnasio.servicio_inventario.model.Maquina;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaquinaDTO {
    private Long id;
    private String nombre;
    private String zona;
    private String marca;
    private String modelo;
    private String estado;
    private LocalDate ultimoMantenimiento;
    private LocalDate proximoMantenimiento;
    private LocalDate fechaAdquisicion;
    private String descripcion;
    private String numeroSerie;
    private Integer horasUso;
    private LocalDateTime fechaRegistro;
    private Integer antiguedad; // En años
    private Integer diasProximoMantenimiento;

    public static MaquinaDTO fromEntity(Maquina maquina) {
        MaquinaDTO dto = MaquinaDTO.builder()
            .id(maquina.getId())
            .nombre(maquina.getNombre())
            .zona(maquina.getZona())
            .marca(maquina.getMarca())
            .modelo(maquina.getModelo())
            .estado(maquina.getEstado())
            .ultimoMantenimiento(maquina.getUltimoMantenimiento())
            .proximoMantenimiento(maquina.getProximoMantenimiento())
            .fechaAdquisicion(maquina.getFechaAdquisicion())
            .descripcion(maquina.getDescripcion())
            .numeroSerie(maquina.getNumeroSerie())
            .horasUso(maquina.getHorasUso())
            .fechaRegistro(maquina.getFechaRegistro())
            .build();

        // Calcular antigüedad
        if (maquina.getFechaAdquisicion() != null) {
            Period period = Period.between(maquina.getFechaAdquisicion(), LocalDate.now());
            dto.setAntiguedad(period.getYears());
        }

        // Calcular días para próximo mantenimiento
        if (maquina.getProximoMantenimiento() != null) {
            Period period = Period.between(LocalDate.now(), maquina.getProximoMantenimiento());
            dto.setDiasProximoMantenimiento(period.getDays());
        }

        return dto;
    }
}