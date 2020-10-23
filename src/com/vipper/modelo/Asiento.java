package com.vipper.modelo;

import java.io.Serializable;

public class Asiento implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id_asiento, asiento, fila, id_sala, id_horario;
	private boolean disponible;
	private String urlimagen;
	private boolean reservado;
	
	
	@Override
	public String toString() {
		return "Asiento [id_asiento=" + id_asiento + ", asiento=" + asiento + ", fila=" + fila + ", id_sala=" + id_sala
				+ ", id_horario=" + id_horario + ", disponible=" + disponible + ", getId_asiento()=" + getId_asiento()
				+ ", getAsiento()=" + getAsiento() + ", getFila()=" + getFila() + ", getId_sala()=" + getId_sala()
				+ ", isDisponible()=" + isDisponible() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public Asiento() {
		super();
	}

	public Asiento(int id_asiento, int asiento, int fila, int id_sala, boolean disponible, int id_horario) {
		super();
		this.id_asiento = id_asiento;
		this.asiento = asiento;
		this.fila = fila;
		this.id_sala = id_sala;
		this.disponible = disponible;
		this.id_horario = id_horario;
	}


	public int getId_asiento() {
		return id_asiento;
	}

	public void setId_asiento(int id_asiento) {
		this.id_asiento = id_asiento;
	}

	public int getAsiento() {
		return asiento;
	}

	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	public int getId_horario() {
		return id_horario;
	}

	public void setId_horario(int id_horario) {
		this.id_horario = id_horario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUrlimagen() {
		return urlimagen;
	}

	public void setUrlimagen(String urlimagen) {
		this.urlimagen = urlimagen;
	}

	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}
}
