package com.vipper.modelo;

import java.io.Serializable;

public class Cine implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id_cine;
	private String nombre, direccion, poblacion, telefono, email;
	@Override
	public String toString() {
		return "Cine [id_cine=" + id_cine + ", nombre=" + nombre + ", direccion=" + direccion + ", poblacion="
				+ poblacion + ", telefono=" + telefono + ", email=" + email + "]";
	}
	
	public Cine() {
		super();
	}
	
	public Cine(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Cine(int id_cine, String nombre, String direccion, String poblacion, String telefono, String email) {
		super();
		this.id_cine = id_cine;
		this.nombre = nombre;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.telefono = telefono;
		this.email = email;
	}

	public int getId_cine() {
		return id_cine;
	}

	public void setId_cine(int id_cine) {
		this.id_cine = id_cine;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
