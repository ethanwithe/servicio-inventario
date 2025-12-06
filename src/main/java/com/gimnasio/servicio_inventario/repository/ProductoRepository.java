package com.gimnasio.servicio_inventario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gimnasio.servicio_inventario.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria(String categoria);

    List<Producto> findByEstado(String estado);

    @Query("SELECT p FROM Producto p WHERE p.stock < p.stockMinimo")
    List<Producto> findProductosStockBajo();

    @Query("SELECT p FROM Producto p WHERE p.stock = 0")
    List<Producto> findProductosAgotados();

    Optional<Producto> findByCodigoBarras(String codigoBarras);

    boolean existsByCodigoBarras(String codigoBarras);

    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:keyword% OR p.categoria LIKE %:keyword% OR p.marca LIKE %:keyword%")
    List<Producto> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT p.categoria, COUNT(p) FROM Producto p GROUP BY p.categoria")
    List<Object[]> countByCategoria();

    @Query("SELECT COUNT(p) FROM Producto p WHERE p.stock < p.stockMinimo")
    long countProductosStockBajo();

    @Query("SELECT SUM(p.precio * p.stock) FROM Producto p")
    java.math.BigDecimal calcularValorTotalInventario();

    List<Producto> findTop10ByOrderByVentasDesc();
}