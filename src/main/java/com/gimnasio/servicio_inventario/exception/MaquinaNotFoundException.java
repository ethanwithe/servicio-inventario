package com.gimnasio.servicio_inventario.exception;

public class MaquinaNotFoundException extends RuntimeException {
    public MaquinaNotFoundException(String message) {
        super(message);
    }

    public MaquinaNotFoundException(Long id) {
        super("Máquina no encontrada con ID: " + id);
    }
}