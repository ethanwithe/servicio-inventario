package com.gimnasio.servicio_inventario.dto;

import java.math.BigDecimal;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadisticasDTO {
    // Estadísticas de productos
    private Long totalProductos;
    private Long productosStockBajo;
    private Long productosAgotados;
    private BigDecimal valorTotal;
    private BigDecimal ventasMes;
    private Map<String, Long> productosPorCategoria;

    // Estadísticas de máquinas
    private Long totalMaquinas;
    private Long maquinasOperativas;
    private Long maquinasMantenimiento;
    private Long maquinasFueraServicio;
    private Map<String, Long> maquinasPorZona;
    private Map<String, Long> maquinasPorEstado;
}
