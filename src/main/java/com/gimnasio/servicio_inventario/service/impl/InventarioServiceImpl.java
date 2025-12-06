package com.gimnasio.servicio_inventario.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gimnasio.servicio_inventario.dto.EstadisticasDTO;
import com.gimnasio.servicio_inventario.dto.MaquinaDTO;
import com.gimnasio.servicio_inventario.dto.ProductoDTO;
import com.gimnasio.servicio_inventario.exception.MaquinaNotFoundException;
import com.gimnasio.servicio_inventario.exception.ProductoNotFoundException;
import com.gimnasio.servicio_inventario.model.Maquina;
import com.gimnasio.servicio_inventario.model.Producto;
import com.gimnasio.servicio_inventario.repository.MaquinaRepository;
import com.gimnasio.servicio_inventario.repository.ProductoRepository;
import com.gimnasio.servicio_inventario.service.InventarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class InventarioServiceImpl implements InventarioService {

    private final ProductoRepository productoRepository;
    private final MaquinaRepository maquinaRepository;

    // ============ PRODUCTOS ============

    @Override
    public ProductoDTO crearProducto(Producto producto) {
        log.info("Creando nuevo producto: {}", producto.getNombre());

        if (producto.getCodigoBarras() != null &&
            productoRepository.existsByCodigoBarras(producto.getCodigoBarras())) {
            throw new IllegalArgumentException("El código de barras ya existe");
        }

        Producto nuevoProducto = productoRepository.save(producto);
        log.info("Producto creado exitosamente con ID: {}", nuevoProducto.getId());

        return ProductoDTO.fromEntity(nuevoProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO obtenerProductoPorId(Long id) {
        log.info("Buscando producto con ID: {}", id);
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new ProductoNotFoundException(id));
        return ProductoDTO.fromEntity(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerTodosLosProductos() {
        log.info("Obteniendo todos los productos");
        return productoRepository.findAll().stream()
            .map(ProductoDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerProductosPorCategoria(String categoria) {
        log.info("Obteniendo productos por categoría: {}", categoria);
        return productoRepository.findByCategoria(categoria).stream()
            .map(ProductoDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerProductosStockBajo() {
        log.info("Obteniendo productos con stock bajo");
        return productoRepository.findProductosStockBajo().stream()
            .map(ProductoDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerProductosAgotados() {
        log.info("Obteniendo productos agotados");
        return productoRepository.findProductosAgotados().stream()
            .map(ProductoDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, Producto productoActualizado) {
        log.info("Actualizando producto con ID: {}", id);

        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new ProductoNotFoundException(id));

        if (productoActualizado.getNombre() != null) {
            producto.setNombre(productoActualizado.getNombre());
        }
        if (productoActualizado.getCategoria() != null) {
            producto.setCategoria(productoActualizado.getCategoria());
        }
        if (productoActualizado.getPrecio() != null) {
            producto.setPrecio(productoActualizado.getPrecio());
        }
        if (productoActualizado.getStock() != null) {
            producto.setStock(productoActualizado.getStock());
        }
        if (productoActualizado.getDescripcion() != null) {
            producto.setDescripcion(productoActualizado.getDescripcion());
        }
        if (productoActualizado.getMarca() != null) {
            producto.setMarca(productoActualizado.getMarca());
        }
        if (productoActualizado.getProveedor() != null) {
            producto.setProveedor(productoActualizado.getProveedor());
        }
        if (productoActualizado.getStockMinimo() != null) {
            producto.setStockMinimo(productoActualizado.getStockMinimo());
        }

        Producto productoGuardado = productoRepository.save(producto);
        log.info("Producto actualizado exitosamente");

        return ProductoDTO.fromEntity(productoGuardado);
    }

    @Override
    public void eliminarProducto(Long id) {
        log.info("Eliminando producto con ID: {}", id);
        if (!productoRepository.existsById(id)) {
            throw new ProductoNotFoundException(id);
        }
        productoRepository.deleteById(id);
        log.info("Producto eliminado exitosamente");
    }

    @Override
    public ProductoDTO actualizarStock(Long id, Integer cantidad) {
        log.info("Actualizando stock del producto ID {} con cantidad: {}", id, cantidad);

        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new ProductoNotFoundException(id));

        int nuevoStock = producto.getStock() + cantidad;
        if (nuevoStock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        producto.setStock(nuevoStock);

        // Si la cantidad es negativa, es una venta
        if (cantidad < 0) {
            producto.setVentas(producto.getVentas() + Math.abs(cantidad));
        }

        Producto productoGuardado = productoRepository.save(producto);
        log.info("Stock actualizado exitosamente. Nuevo stock: {}", nuevoStock);

        return ProductoDTO.fromEntity(productoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> buscarProductos(String keyword) {
        log.info("Buscando productos con keyword: {}", keyword);
        return productoRepository.searchByKeyword(keyword).stream()
            .map(ProductoDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerTopProductos(int limite) {
        log.info("Obteniendo top {} productos más vendidos", limite);
        return productoRepository.findTop10ByOrderByVentasDesc().stream()
            .limit(limite)
            .map(ProductoDTO::fromEntity)
            .collect(Collectors.toList());
    }

    // ============ MÁQUINAS ============

    @Override
    public MaquinaDTO crearMaquina(Maquina maquina) {
        log.info("Creando nueva máquina: {}", maquina.getNombre());

        if (maquina.getNumeroSerie() != null &&
            maquinaRepository.existsByNumeroSerie(maquina.getNumeroSerie())) {
            throw new IllegalArgumentException("El número de serie ya existe");
        }

        Maquina nuevaMaquina = maquinaRepository.save(maquina);
        log.info("Máquina creada exitosamente con ID: {}", nuevaMaquina.getId());

        return MaquinaDTO.fromEntity(nuevaMaquina);
    }

    @Override
    @Transactional(readOnly = true)
    public MaquinaDTO obtenerMaquinaPorId(Long id) {
        log.info("Buscando máquina con ID: {}", id);
        Maquina maquina = maquinaRepository.findById(id)
            .orElseThrow(() -> new MaquinaNotFoundException(id));
        return MaquinaDTO.fromEntity(maquina);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaquinaDTO> obtenerTodasLasMaquinas() {
        log.info("Obteniendo todas las máquinas");
        return maquinaRepository.findAll().stream()
            .map(MaquinaDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaquinaDTO> obtenerMaquinasPorZona(String zona) {
        log.info("Obteniendo máquinas por zona: {}", zona);
        return maquinaRepository.findByZona(zona).stream()
            .map(MaquinaDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaquinaDTO> obtenerMaquinasPorEstado(String estado) {
        log.info("Obteniendo máquinas por estado: {}", estado);
        return maquinaRepository.findByEstado(estado).stream()
            .map(MaquinaDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public MaquinaDTO actualizarMaquina(Long id, Maquina maquinaActualizada) {
        log.info("Actualizando máquina con ID: {}", id);

        Maquina maquina = maquinaRepository.findById(id)
            .orElseThrow(() -> new MaquinaNotFoundException(id));

        if (maquinaActualizada.getNombre() != null) {
            maquina.setNombre(maquinaActualizada.getNombre());
        }
        if (maquinaActualizada.getZona() != null) {
            maquina.setZona(maquinaActualizada.getZona());
        }
        if (maquinaActualizada.getMarca() != null) {
            maquina.setMarca(maquinaActualizada.getMarca());
        }
        if (maquinaActualizada.getModelo() != null) {
            maquina.setModelo(maquinaActualizada.getModelo());
        }
        if (maquinaActualizada.getEstado() != null) {
            maquina.setEstado(maquinaActualizada.getEstado());
        }
        if (maquinaActualizada.getDescripcion() != null) {
            maquina.setDescripcion(maquinaActualizada.getDescripcion());
        }
        if (maquinaActualizada.getHorasUso() != null) {
            maquina.setHorasUso(maquinaActualizada.getHorasUso());
        }

        Maquina maquinaGuardada = maquinaRepository.save(maquina);
        log.info("Máquina actualizada exitosamente");

        return MaquinaDTO.fromEntity(maquinaGuardada);
    }

    @Override
    public void eliminarMaquina(Long id) {
        log.info("Eliminando máquina con ID: {}", id);
        if (!maquinaRepository.existsById(id)) {
            throw new MaquinaNotFoundException(id);
        }
        maquinaRepository.deleteById(id);
        log.info("Máquina eliminada exitosamente");
    }

    @Override
    public void cambiarEstadoMaquina(Long id, String nuevoEstado) {
        log.info("Cambiando estado de máquina ID {} a: {}", id, nuevoEstado);

        Maquina maquina = maquinaRepository.findById(id)
            .orElseThrow(() -> new MaquinaNotFoundException(id));

        maquina.setEstado(nuevoEstado);
        maquinaRepository.save(maquina);

        log.info("Estado de máquina cambiado exitosamente");
    }

    @Override
    public void registrarMantenimiento(Long id, LocalDate fecha) {
        log.info("Registrando mantenimiento para máquina ID {} en fecha: {}", id, fecha);

        Maquina maquina = maquinaRepository.findById(id)
            .orElseThrow(() -> new MaquinaNotFoundException(id));

        maquina.setUltimoMantenimiento(fecha);
        // Programar próximo mantenimiento en 3 meses
        maquina.setProximoMantenimiento(fecha.plusMonths(3));

        maquinaRepository.save(maquina);

        log.info("Mantenimiento registrado exitosamente");
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaquinaDTO> buscarMaquinas(String keyword) {
        log.info("Buscando máquinas con keyword: {}", keyword);
        return maquinaRepository.searchByKeyword(keyword).stream()
            .map(MaquinaDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaquinaDTO> obtenerMantenimientoProximo(int dias) {
        log.info("Obteniendo máquinas con mantenimiento próximo en {} días", dias);
        LocalDate fechaLimite = LocalDate.now().plusDays(dias);
        return maquinaRepository.findMantenimientoProximo(fechaLimite).stream()
            .map(MaquinaDTO::fromEntity)
            .collect(Collectors.toList());
    }

    // ============ ESTADÍSTICAS ============

    @Override
    @Transactional(readOnly = true)
    public EstadisticasDTO obtenerEstadisticas() {
        log.info("Obteniendo estadísticas de inventario");

        // Estadísticas de productos
        long totalProductos = productoRepository.count();
        long productosStockBajo = productoRepository.countProductosStockBajo();
        long productosAgotados = productoRepository.findProductosAgotados().size();
        BigDecimal valorTotal = productoRepository.calcularValorTotalInventario();

        // Productos por categoría
        Map<String, Long> productosPorCategoria = new HashMap<>();
        List<Object[]> categorias = productoRepository.countByCategoria();
        for (Object[] row : categorias) {
            productosPorCategoria.put((String) row[0], (Long) row[1]);
        }

        // Estadísticas de máquinas
        long totalMaquinas = maquinaRepository.count();
        long maquinasOperativas = maquinaRepository.countMaquinasOperativas();
        long maquinasMantenimiento = maquinaRepository.countMaquinasMantenimiento();
        long maquinasFueraServicio = maquinaRepository.countMaquinasFueraServicio();

        // Máquinas por zona
        Map<String, Long> maquinasPorZona = new HashMap<>();
        List<Object[]> zonas = maquinaRepository.countByZona();
        for (Object[] row : zonas) {
            maquinasPorZona.put((String) row[0], (Long) row[1]);
        }

        // Máquinas por estado
        Map<String, Long> maquinasPorEstado = new HashMap<>();
        List<Object[]> estados = maquinaRepository.countByEstado();
        for (Object[] row : estados) {
            maquinasPorEstado.put((String) row[0], (Long) row[1]);
        }

        return EstadisticasDTO.builder()
            .totalProductos(totalProductos)
            .productosStockBajo(productosStockBajo)
            .productosAgotados(productosAgotados)
            .valorTotal(valorTotal != null ? valorTotal : BigDecimal.ZERO)
            .ventasMes(BigDecimal.valueOf(42890)) // Simulated
            .productosPorCategoria(productosPorCategoria)
            .totalMaquinas(totalMaquinas)
            .maquinasOperativas(maquinasOperativas)
            .maquinasMantenimiento(maquinasMantenimiento)
            .maquinasFueraServicio(maquinasFueraServicio)
            .maquinasPorZona(maquinasPorZona)
            .maquinasPorEstado(maquinasPorEstado)
            .build();
    }
}