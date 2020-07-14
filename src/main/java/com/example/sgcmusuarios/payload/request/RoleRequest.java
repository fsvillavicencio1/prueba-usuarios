package com.example.sgcmusuarios.payload.request;

import javax.validation.constraints.*;

public class RoleRequest {
	
	@NotBlank
    @Size(max = 30)
    private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
