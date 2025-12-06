package com.gimnasio.servicio_inventario.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gimnasio.servicio_inventario.dto.ProductoDTO;
import com.gimnasio.servicio_inventario.model.Producto;
import com.gimnasio.servicio_inventario.service.InventarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventario/productos")
@RequiredArgsConstructor
@Slf4j
public class ProductoController {

    private final InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody Producto producto) {
        log.info("POST /api/inventario/productos - Creando producto: {}", producto.getNombre());
        ProductoDTO nuevoProducto = inventarioService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable Long id) {
        log.info("GET /api/inventario/productos/{}", id);
        ProductoDTO producto = inventarioService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos() {
        log.info("GET /api/inventario/productos");
        List<ProductoDTO> productos = inventarioService.obtenerTodosLosProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorCategoria(@PathVariable String categoria) {
        log.info("GET /api/inventario/productos/categoria/{}", categoria);
        List<ProductoDTO> productos = inventarioService.obtenerProductosPorCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/stock-bajo")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosStockBajo() {
        log.info("GET /api/inventario/productos/stock-bajo");
        List<ProductoDTO> productos = inventarioService.obtenerProductosStockBajo();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/agotados")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosAgotados() {
        log.info("GET /api/inventario/productos/agotados");
        List<ProductoDTO> productos = inventarioService.obtenerProductosAgotados();
        return ResponseEntity.ok(productos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto producto) {
        log.info("PUT /api/inventario/productos/{}", id);
        ProductoDTO productoActualizado = inventarioService.actualizarProducto(id, producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarProducto(@PathVariable Long id) {
        log.info("DELETE /api/inventario/productos/{}", id);
        inventarioService.eliminarProducto(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Producto eliminado exitosamente");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoDTO> actualizarStock(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> request) {
        log.info("PATCH /api/inventario/productos/{}/stock", id);
        Integer cantidad = request.get("cantidad");
        ProductoDTO producto = inventarioService.actualizarStock(id, cantidad);
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoDTO>> buscarProductos(@RequestParam String keyword) {
        log.info("GET /api/inventario/productos/buscar?keyword={}", keyword);
        List<ProductoDTO> productos = inventarioService.buscarProductos(keyword);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/top")
    public ResponseEntity<List<ProductoDTO>> obtenerTopProductos(
            @RequestParam(defaultValue = "10") int limite) {
        log.info("GET /api/inventario/productos/top?limite={}", limite);
        List<ProductoDTO> productos = inventarioService.obtenerTopProductos(limite);
        return ResponseEntity.ok(productos);
    }
}