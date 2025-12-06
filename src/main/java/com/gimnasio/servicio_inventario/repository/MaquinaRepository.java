package com.gimnasio.servicio_inventario.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gimnasio.servicio_inventario.model.Maquina;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long> {

    List<Maquina> findByZona(String zona);

    List<Maquina> findByEstado(String estado);

    Optional<Maquina> findByNumeroSerie(String numeroSerie);

    boolean existsByNumeroSerie(String numeroSerie);

    @Query("SELECT m FROM Maquina m WHERE m.nombre LIKE %:keyword% OR m.marca LIKE %:keyword% OR m.zona LIKE %:keyword%")
    List<Maquina> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT m FROM Maquina m WHERE m.proximoMantenimiento <= :fecha")
    List<Maquina> findMantenimientoProximo(@Param("fecha") LocalDate fecha);

    @Query("SELECT m.zona, COUNT(m) FROM Maquina m GROUP BY m.zona")
    List<Object[]> countByZona();

    @Query("SELECT m.estado, COUNT(m) FROM Maquina m GROUP BY m.estado")
    List<Object[]> countByEstado();

    @Query("SELECT COUNT(m) FROM Maquina m WHERE m.estado = 'Operativa'")
    long countMaquinasOperativas();

    @Query("SELECT COUNT(m) FROM Maquina m WHERE m.estado = 'Mantenimiento'")
    long countMaquinasMantenimiento();

    @Query("SELECT COUNT(m) FROM Maquina m WHERE m.estado = 'Fuera de Servicio'")
    long countMaquinasFueraServicio();
}