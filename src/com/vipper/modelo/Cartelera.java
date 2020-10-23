package com.vipper.modelo;

import java.io.Serializable;

public class Cartelera implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id_cartelera, id_pelicula, id_cine, id_sala, duracion;
	private String titulo, urlimagen, nombre, descripcion, sinopsis, genero, categoria;
	private double precio;
	
	@Override
	public String toString() {
		return "Cartelera [id_cartelera=" + id_cartelera + ", id_pelicula=" + id_pelicula + ", id_cine=" + id_cine
				+ ", id_sala=" + id_sala + ", duracion=" + duracion + ", titulo=" + titulo + ", urlimagen=" + urlimagen
				+ ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + "]";
	}
	
	public Cartelera() {
		super();
	}

	
	public Cartelera(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Cartelera(int id_cartelera, int id_pelicula, int id_cine, int id_sala, int duracion, String titulo,
			String urlimagen, String nombre, String sinopsis, String genero, String categoria, double precio) {
		super();
		this.id_cartelera = id_cartelera;
		this.id_pelicula = id_pelicula;
		this.id_cine = id_cine;
		this.id_sala = id_sala;
		this.duracion = duracion;
		this.titulo = titulo;
		this.urlimagen = urlimagen;
		this.nombre = nombre;
		this.sinopsis = sinopsis;
		this.genero = genero;
		this.categoria = categoria;
		this.precio = precio;
	}

	public int getId_cartelera() {
		return id_cartelera;
	}

	public void setId_cartelera(int id_cartelera) {
		this.id_cartelera = id_cartelera;
	}

	public int getId_pelicula() {
		return id_pelicula;
	}

	public void setId_pelicula(int id_pelicula) {
		this.id_pelicula = id_pelicula;
	}

	public int getId_cine() {
		return id_cine;
	}

	public void setId_cine(int id_cine) {
		this.id_cine = id_cine;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUrlimagen() {
		return urlimagen;
	}

	public void setUrlimagen(String urlimagen) {
		this.urlimagen = urlimagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
