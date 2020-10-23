package com.vipper.modelo;

import java.io.Serializable;

public class Horario implements Serializable {
	
	private int id_horario, id_sala, id_cine;
	private String hora;
	
	@Override
	public String toString() {
		return "Horario [id_horario=" + id_horario + ", id_sala=" + id_sala + ", id_cine=" + id_cine + ", hora=" + hora
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public Horario() {
		super();
	}
	
	public Horario(String hora) {
		super();
		this.hora = hora;
	}

	public Horario(int id_horario, int id_sala, int id_cine, String hora) {
		super();
		this.id_horario = id_horario;
		this.id_sala = id_sala;
		this.id_cine = id_cine;
		this.hora = hora;
	}

	public int getId_horario() {
		return id_horario;
	}

	public void setId_horario(int id_horario) {
		this.id_horario = id_horario;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	public int getId_cine() {
		return id_cine;
	}

	public void setId_cine(int id_cine) {
		this.id_cine = id_cine;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	
}
