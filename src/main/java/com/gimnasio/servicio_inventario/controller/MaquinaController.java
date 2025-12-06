package com.gimnasio.servicio_inventario.controller;

import java.time.LocalDate;
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

import com.gimnasio.servicio_inventario.dto.MaquinaDTO;
import com.gimnasio.servicio_inventario.model.Maquina;
import com.gimnasio.servicio_inventario.service.InventarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventario/maquinas")
@RequiredArgsConstructor
@Slf4j
public class MaquinaController {

    private final InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<MaquinaDTO> crearMaquina(@Valid @RequestBody Maquina maquina) {
        log.info("POST /api/inventario/maquinas - Creando máquina: {}", maquina.getNombre());
        MaquinaDTO nuevaMaquina = inventarioService.crearMaquina(maquina);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMaquina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaquinaDTO> obtenerMaquina(@PathVariable Long id) {
        log.info("GET /api/inventario/maquinas/{}", id);
        MaquinaDTO maquina = inventarioService.obtenerMaquinaPorId(id);
        return ResponseEntity.ok(maquina);
    }

    @GetMapping
    public ResponseEntity<List<MaquinaDTO>> obtenerTodasLasMaquinas() {
        log.info("GET /api/inventario/maquinas");
        List<MaquinaDTO> maquinas = inventarioService.obtenerTodasLasMaquinas();
        return ResponseEntity.ok(maquinas);
    }

    @GetMapping("/zona/{zona}")
    public ResponseEntity<List<MaquinaDTO>> obtenerMaquinasPorZona(@PathVariable String zona) {
        log.info("GET /api/inventario/maquinas/zona/{}", zona);
        List<MaquinaDTO> maquinas = inventarioService.obtenerMaquinasPorZona(zona);
        return ResponseEntity.ok(maquinas);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<MaquinaDTO>> obtenerMaquinasPorEstado(@PathVariable String estado) {
        log.info("GET /api/inventario/maquinas/estado/{}", estado);
        List<MaquinaDTO> maquinas = inventarioService.obtenerMaquinasPorEstado(estado);
        return ResponseEntity.ok(maquinas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaquinaDTO> actualizarMaquina(
            @PathVariable Long id,
            @RequestBody Maquina maquina) {
        log.info("PUT /api/inventario/maquinas/{}", id);
        MaquinaDTO maquinaActualizada = inventarioService.actualizarMaquina(id, maquina);
        return ResponseEntity.ok(maquinaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarMaquina(@PathVariable Long id) {
        log.info("DELETE /api/inventario/maquinas/{}", id);
        inventarioService.eliminarMaquina(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Máquina eliminada exitosamente");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Map<String, String>> cambiarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        log.info("PATCH /api/inventario/maquinas/{}/estado", id);
        String nuevoEstado = request.get("estado");
        inventarioService.cambiarEstadoMaquina(id, nuevoEstado);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Estado actualizado exitosamente");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/mantenimiento")
    public ResponseEntity<Map<String, String>> registrarMantenimiento(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        log.info("PATCH /api/inventario/maquinas/{}/mantenimiento", id);
        LocalDate fecha = LocalDate.parse(request.get("fecha"));
        inventarioService.registrarMantenimiento(id, fecha);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Mantenimiento registrado exitosamente");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MaquinaDTO>> buscarMaquinas(@RequestParam String keyword) {
        log.info("GET /api/inventario/maquinas/buscar?keyword={}", keyword);
        List<MaquinaDTO> maquinas = inventarioService.buscarMaquinas(keyword);
        return ResponseEntity.ok(maquinas);
    }

    @GetMapping("/mantenimiento-proximo")
    public ResponseEntity<List<MaquinaDTO>> obtenerMantenimientoProximo(
            @RequestParam(defaultValue = "30") int dias) {
    	log.info("GET /api/inventario/maquinas/mantenimiento-proximo?dias={}", dias);
    	List<MaquinaDTO> maquinas = inventarioService.obtenerMantenimientoProximo(dias);
    	return ResponseEntity.ok(maquinas);
    }
}
