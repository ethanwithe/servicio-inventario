package com.gimnasio.servicio_inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServicioInventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioInventarioApplication.class, args);
	}

}
