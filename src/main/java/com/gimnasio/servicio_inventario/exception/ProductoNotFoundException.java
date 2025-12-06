package com.gimnasio.servicio_inventario.exception;

public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(String message) {
        super(message);
    }

    public ProductoNotFoundException(Long id) {
        super("Producto no encontrado con ID: " + id);
    }
}
