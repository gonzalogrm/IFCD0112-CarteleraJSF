package com.vipper.modelo;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.vipper.persistencia.AccesoBBDD;

@ManagedBean(name = "filtro")
@SessionScoped
public class Filtro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Formulario Bean
	private int id_cine;
	private String categoria, genero;
	
	//Obtener Carteleras
	private List<Cartelera> carteleras, todasCarteleras;
	private List<Cine> todosCines;
	private List<String> todasCategorias;
	private List<String> todosGeneros;
	
	//Detalle
	private Cartelera carteleraDetalle;
	private List<Horario> horarios, todosHorarios;
	
	//Asientos
	private int idHorario;
	private Asiento[][] asientosArray;
	
	//Reserva
	private List<Asiento> reservaAsientos;
	private double precioReserva;
	private String mensajeError;
	
	
	//Métodos de acción
	public void cargarTodos(){
		AccesoBBDD acceso = new AccesoBBDD();
		try {
			todasCarteleras = acceso.obtenerCartelera();
			todosCines = acceso.obtenerCines();
			todasCategorias = acceso.obtenerCategorias();
			todosGeneros = acceso.obtenerGeneros();
			todosHorarios = acceso.obtenerTodosHorarios();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    	finally {
    		if(todasCarteleras == null || todasCarteleras.size()==0) {
    			todasCarteleras = 
    					new ArrayList<Cartelera>(Arrays.asList(new Cartelera("No hay cartelera")));
			}			

			if(todosCines == null || todosCines.size()==0) {
				todosCines = 
    					new ArrayList<Cine>(Arrays.asList(new Cine("No hay cines")));
			}
			
			if(todasCategorias == null || todasCategorias.size()==0) {
				todasCategorias = 
    					new ArrayList<String>(Arrays.asList("No hay Categorías"));
			}
			
			if(todosGeneros == null || todosGeneros.size()==0) {
				todosGeneros = 
    					new ArrayList<String>(Arrays.asList("No hay Géneros"));
			}		
			
			//Doble lista para no perder información después de filtrar
			carteleras = todasCarteleras;
			horarios = todosHorarios;
    	}
	}
	
	public String filtrarCarteleras() {
		AccesoBBDD acceso = new AccesoBBDD();
		carteleras = acceso.filtrarCarteleras(todasCarteleras, this);
		return "index";
	}
	
	public String resetList() {
		carteleras = todasCarteleras;
		return "index";
	}
	
	public String detalleCartelera(int idCartelera) {
		AccesoBBDD acceso = new AccesoBBDD();
		carteleraDetalle = acceso.filtrarCarteleraID(idCartelera, todasCarteleras);
		horarios = acceso.filtrarHorarios(todosHorarios, carteleraDetalle);
		
		return "detalle";
	}
	
	public String mostrarAsientos(int idHorario) {
		AccesoBBDD acceso = new AccesoBBDD();
  		//Asiento[][] asientosArray=null;
  		
  		try {
			asientosArray = acceso.obtenerAsientos(idHorario);
		} catch (ClassNotFoundException | SQLException e) {
			asientosArray = new Asiento[1][1];
		}
  		finally{
  			this.idHorario = idHorario;
  			//this.asientosArray = asientosArray;
  		}
  		
  		System.out.println(Arrays.deepToString(this.asientosArray));
  		
		return "mostrarasientos";
	}
	
	public String realizarReserva() {
		reservaAsientos = new ArrayList<Asiento>();
		precioReserva = 0;
		mensajeError = null;
		boolean exito = false;
		for (int i = 0; i < asientosArray.length; i++) {
			for (int j = 0; j < asientosArray[i].length; j++) {
				if (asientosArray[i][j].isReservado()) {
					AccesoBBDD acceso = new AccesoBBDD();		
					try {
						exito = acceso.hacerReserva(
								carteleraDetalle, 
								asientosArray[i][j].getId_horario(), 
								asientosArray[i][j].getId_asiento());
						
						if(exito) {
							reservaAsientos.add(asientosArray[i][j]);
							precioReserva+=carteleraDetalle.getPrecio();
							asientosArray[i][j].setReservado(false);
						}else {
							mensajeError = "Error en la reserva";
							return null;
						}
					} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						exito=false;
						mensajeError = "Error en la reserva";
						return null;
					}
				}
			}
		}
	
		return "mostrarreserva";
	}
	
	
	/*
	public String realizarReserva() {
		AccesoBBDD acceso = new AccesoBBDD();		
		boolean exito = false;

		for (String string : reservaHorarioAsiento) {
			List<String> datos = Arrays.asList(string.split(","));
			//0: id_horario, 1: id_asiento
			System.out.println("DATOS: " + datos.get(0) + " | " +  datos.get(1));
			
			try {
				System.out.println(
						exito = acceso.hacerReserva(
								carteleraDetalle, Integer.parseInt(datos.get(0)), Integer.parseInt(datos.get(1))));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				exito=false;
				break;
			}
		}
		return null;
	}
	*/
	
	
	//Métodos
	@Override
	public String toString() {
		return "Filtro [cine=" + id_cine + ", categoria=" + categoria + ", genero=" + genero + "]";
	}


	public Filtro() {
		super();
		cargarTodos();
	}

	public Filtro(int id_cine, String categoria, String genero) {
		super();
		cargarTodos();
		this.id_cine = id_cine;
		this.categoria = categoria;
		this.genero = genero;
	}

	public int getId_cine() {
		return id_cine;
	}

	public void setId_cine(int id_cine) {
		this.id_cine = id_cine;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Cartelera> getCarteleras() {
		return carteleras;
	}

	public void setCarteleras(List<Cartelera> carteleras) {
		this.carteleras = carteleras;
	}

	public List<Cartelera> getTodasCarteleras() {
		return todasCarteleras;
	}

	public void setTodasCarteleras(List<Cartelera> todasCarteleras) {
		this.todasCarteleras = todasCarteleras;
	}

	public List<Cine> getTodosCines() {
		return todosCines;
	}

	public void setTodosCines(List<Cine> todosCines) {
		this.todosCines = todosCines;
	}

	public List<String> getTodasCategorias() {
		return todasCategorias;
	}

	public void setTodasCategorias(List<String> todasCategorias) {
		this.todasCategorias = todasCategorias;
	}

	public List<String> getTodosGeneros() {
		return todosGeneros;
	}

	public void setTodosGeneros(List<String> todosGeneros) {
		this.todosGeneros = todosGeneros;
	}
	
	public Cartelera getCarteleraDetalle() {
		return carteleraDetalle;
	}

	public void setCarteleraDetalle(Cartelera carteleraDetalle) {
		this.carteleraDetalle = carteleraDetalle;
	}
	
	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public List<Horario> getTodosHorarios() {
		return todosHorarios;
	}

	public void setTodosHorarios(List<Horario> todosHorarios) {
		this.todosHorarios = todosHorarios;
	}

	public int getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public Asiento[][] getAsientosArray() {
		return asientosArray;
	}

	public void setAsientosArray(Asiento[][] asientosArray) {
		this.asientosArray = asientosArray;
	}

	public List<Asiento> getReservaAsientos() {
		return reservaAsientos;
	}

	public void setReservaAsientos(List<Asiento> reservaAsientos) {
		this.reservaAsientos = reservaAsientos;
	}

	public double getPrecioReserva() {
		return precioReserva;
	}

	public void setPrecioReserva(double precioReserva) {
		this.precioReserva = precioReserva;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	
}
