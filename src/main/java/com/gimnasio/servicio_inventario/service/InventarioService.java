package com.gimnasio.servicio_inventario.service;

import java.time.LocalDate;
import java.util.List;

import com.gimnasio.servicio_inventario.dto.EstadisticasDTO;
import com.gimnasio.servicio_inventario.dto.MaquinaDTO;
import com.gimnasio.servicio_inventario.dto.ProductoDTO;
import com.gimnasio.servicio_inventario.model.Maquina;
import com.gimnasio.servicio_inventario.model.Producto;

public interface InventarioService {

    // ============ PRODUCTOS ============
    ProductoDTO crearProducto(Producto producto);
    ProductoDTO obtenerProductoPorId(Long id);
    List<ProductoDTO> obtenerTodosLosProductos();
    List<ProductoDTO> obtenerProductosPorCategoria(String categoria);
    List<ProductoDTO> obtenerProductosStockBajo();
    List<ProductoDTO> obtenerProductosAgotados();
    ProductoDTO actualizarProducto(Long id, Producto producto);
    void eliminarProducto(Long id);
    ProductoDTO actualizarStock(Long id, Integer cantidad);
    List<ProductoDTO> buscarProductos(String keyword);
    List<ProductoDTO> obtenerTopProductos(int limite);

    // ============ MÁQUINAS ============
    MaquinaDTO crearMaquina(Maquina maquina);
    MaquinaDTO obtenerMaquinaPorId(Long id);
    List<MaquinaDTO> obtenerTodasLasMaquinas();
    List<MaquinaDTO> obtenerMaquinasPorZona(String zona);
    List<MaquinaDTO> obtenerMaquinasPorEstado(String estado);
    MaquinaDTO actualizarMaquina(Long id, Maquina maquina);
    void eliminarMaquina(Long id);
    void cambiarEstadoMaquina(Long id, String nuevoEstado);
    void registrarMantenimiento(Long id, LocalDate fecha);
    List<MaquinaDTO> buscarMaquinas(String keyword);
    List<MaquinaDTO> obtenerMantenimientoProximo(int dias);

    // ============ ESTADÍSTICAS ============
    EstadisticasDTO obtenerEstadisticas();
}