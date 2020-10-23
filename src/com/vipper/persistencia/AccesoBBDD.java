package com.vipper.persistencia;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vipper.modelo.*;


public class AccesoBBDD extends Conexion {
	
	//Acceso a tablas en la BBDD
	public List<Cartelera> obtenerCartelera() throws ClassNotFoundException, SQLException{
		//Definir variables
        CallableStatement st;
        ResultSet rs;
        String sql = "call proyectocines.obtenerCartelera();";
        List<Cartelera> result = new ArrayList<Cartelera>();
        
        //Abrir la conexion
        abrirConexion();        
        //Obtener el ResultSet
        st = miConexion.prepareCall(sql);
        //Ejecutar
        rs = st.executeQuery();
        
        //Recorrer el resultSet
        while (rs.next()) {
        	result.add(new Cartelera(
        			rs.getInt("id_cartelera"), 
        			rs.getInt("id_pelicula"), 
					rs.getInt("id_cine"), 
					rs.getInt("id_sala"), 
					rs.getInt("duracion"), 
        			rs.getString("titulo"), 
        			rs.getString("urlimagen"), 
					rs.getString("nombre"), 
					rs.getString("sinopsis"),
					rs.getString("genero"),
					rs.getString("categoria"),
    				rs.getDouble("precio")));
        }
        
        rs.close();
        cerrarConexion();
        
        //No permitir valores nulos si ha habido algún error inesperado que genere null
        result = (result == null) ? new ArrayList<Cartelera>() : result;
        
        return result;
	}

	public List<Cine> obtenerCines() throws ClassNotFoundException, SQLException{
		//Definir variables
        CallableStatement st;
        ResultSet rs;
        String sql = "call proyectocines.mostrarCines();";
        List<Cine> result = new ArrayList<Cine>();
        
        //Abrir la conexion
        abrirConexion();        
        //Obtener el ResultSet
        st = miConexion.prepareCall(sql);
        //Ejecutar
        rs = st.executeQuery();
        
        //Recorrer el resultSet
        while (rs.next()) {
        	result.add(new Cine(
        			rs.getInt("id_cine"), 
        			rs.getString("nombre"), 
        			rs.getString("direccion"), 
					rs.getString("poblacion"),
					rs.getString("telefono"),
					rs.getString("email")));
        }
        
        rs.close();
        cerrarConexion();
        
        result = (result == null) ? new ArrayList<Cine>() : result;
        
        return result;
	}
	
	public List<String> obtenerCategorias() throws SQLException, ClassNotFoundException{
		//Definir variables
        CallableStatement st;
        ResultSet rs;
        String sql = "call proyectocines.mostrarCategorias();";
        List<String> result = new ArrayList<String>();
        
        //Abrir la conexion
        abrirConexion();        
        //Obtener el ResultSet
        st = miConexion.prepareCall(sql);
        //Ejecutar
        rs = st.executeQuery();
        
        //Recorrer el resultSet
        while (rs.next()) {
        	result.add(rs.getString("categoria"));
        }
        
        rs.close();
        cerrarConexion();
        
        result = (result == null) ? new ArrayList<String>() : result;
        
        return result;
	}
	
	public List<String> obtenerGeneros() throws ClassNotFoundException, SQLException{
		//Definir variables
        CallableStatement st;
        ResultSet rs;
        String sql = "call proyectocines.mostrarGeneros();";
        List<String> result = new ArrayList<String>();
        
        //Abrir la conexion
        abrirConexion();        
        //Obtener el ResultSet
        st = miConexion.prepareCall(sql);
        //Ejecutar
        rs = st.executeQuery();
        
        //Recorrer el resultSet
        while (rs.next()) {
        	result.add(rs.getString("genero"));
        }
        
        rs.close();
        cerrarConexion();
        
        result = (result == null) ? new ArrayList<String>() : result;
        
        return result;
	}
	
	public List<Horario> obtenerHorarios(int id_cartelera) throws ClassNotFoundException, SQLException{
		//Definir variables
        CallableStatement st;
        ResultSet rs;
        String sql = "call proyectocines.obtenerHorarios(?);";
        List<Horario> result = new ArrayList<Horario>();
        
        //Abrir la conexion
        abrirConexion();        
        //Obtener el ResultSet
        st = miConexion.prepareCall(sql);
        st.setInt(1,id_cartelera);
        //Ejecutar
        rs = st.executeQuery();
        
        //Recorrer el resultSet
        while (rs.next()) {
        	result.add(new Horario(
    			rs.getInt("id_horario"), 
    			rs.getInt("id_sala"),
    			rs.getInt("id_cine"),
				rs.getString("hora")));
        }
        
        rs.close();
        cerrarConexion();
        
        result = (result == null) ? new ArrayList<Horario>() : result;
        
        return result;
	}

	public List<Horario> obtenerTodosHorarios() throws ClassNotFoundException, SQLException{
		//Definir variables
        CallableStatement st;
        ResultSet rs;
        String sql = "call proyectocines.obtenerTodosHorarios();";
        List<Horario> result = new ArrayList<Horario>();
        
        //Abrir la conexion
        abrirConexion();        
        //Obtener el ResultSet
        st = miConexion.prepareCall(sql);
        //Ejecutar
        rs = st.executeQuery();
        
        //Recorrer el resultSet
        //public Horario(int id_horario, int id_sala, int id_cine, String hora)
        while (rs.next()) {
        	result.add(new Horario(
    			rs.getInt("id_horario"), 
    			rs.getInt("id_sala"),
    			rs.getInt("id_cine"),
				rs.getString("hora")));
        }
        
        rs.close();
        cerrarConexion();
        
        result = (result == null) ? new ArrayList<Horario>() : result;
        
        return result;
	}
	
	public List<Cartelera> filtrarCarteleras(List<Cartelera> entrada, Filtro filtro){
		Stream<Cartelera> filtrado = 
			entrada.stream().filter(i -> i.getId_cine() == filtro.getId_cine() 
					&& i.getGenero().equals(filtro.getGenero())
					&& i.getCategoria().equals(filtro.getCategoria()));
		return filtrado.collect(Collectors.toList());
	}
	
	public Cartelera filtrarCarteleraID(int id, List<Cartelera> cartelerasList) {
		return cartelerasList.stream().
			filter(i -> i.getId_cartelera()==id).findFirst().orElse(null);
	}
	
	public List<Horario> filtrarHorarios(List<Horario> horariosList, Cartelera cartelera){
		return horariosList.stream().filter(
			h -> h.getId_sala()==cartelera.getId_sala()
			&& h.getId_cine()==cartelera.getId_cine())
			.collect(Collectors.toList());
	}
	
	public Asiento[][] obtenerAsientos(int pid_horario) throws SQLException, ClassNotFoundException{
		//Definir variables
        CallableStatement st;
        ResultSet rs;
        String sql = "call proyectocines.obtenerAsientos(?,?,?);";
        Asiento[][] result = null;
        
        //Abrir la conexion
        abrirConexion();        
        //Obtener el ResultSet
        st = miConexion.prepareCall(sql);
        st.setInt(1,pid_horario);
        st.registerOutParameter(2, Types.INTEGER); //filas
        st.registerOutParameter(3, Types.INTEGER); //asientos (columnas)
        //Ejecutar
        rs = st.executeQuery();
        
        //Crear Matriz
        result = new Asiento[st.getInt(2)][st.getInt(3)];
        
        //Recorrer el resultSet
        while (rs.next()) {
        	Asiento a = new Asiento(
        			rs.getInt("id_asiento"), 
        			rs.getInt("asiento"),
        			rs.getInt("fila"),
        			rs.getInt("id_sala"),
        			rs.getBoolean("disponible"),
        			rs.getInt("id_horario"));
        	
        	//Asociar imagen
        	if(a.isDisponible()) {
        		a.setUrlimagen("asientodisponible.png");
        	}else {
        		a.setUrlimagen("asientoocupado.png");
        	}
        	
        	//Añadir al array2D
        	result[a.getFila()-1][a.getAsiento()-1] = a;
        }
        
        rs.close();
        cerrarConexion();
        
        return result;
	}

	public boolean hacerReserva(Cartelera cartelera, int pid_horario, int pid_asiento) 
			throws ClassNotFoundException, SQLException {
		
		//Definir variables
        CallableStatement st;
        String sql = "call proyectocines.hacerReserva(?,?,?,?,?,?);";
        
        //Abrir la conexion
        abrirConexion();        
        //Obtener el ResultSet
        st = miConexion.prepareCall(sql);
        st.setInt(1,cartelera.getId_pelicula());
        st.setInt(2,cartelera.getId_cine());
        st.setInt(3,cartelera.getId_sala());
        st.setInt(4,pid_horario);
        st.setInt(5,pid_asiento);
        st.setDouble(6,cartelera.getPrecio());
        
        //Ejecutar
        int registros = st.executeUpdate();
		
        cerrarConexion();
        
        return registros >= 1;
	}
	
	
	/*
	public Inmueble selectInmuebles(String referencia) throws ClassNotFoundException, SQLException{
		//Definir variables
        CallableStatement st;
        ResultSet rs;
        String sql = "call inmuebles.selectInmuebles(?);";
        Inmueble result = null;
        
        //Abrir la conexion
        abrirConexion();        
        //Obtener el ResultSet
        st = miConexion.prepareCall(sql);
        st.setString(1,referencia);
      //Ejecutar
        rs = st.executeQuery();
        
        //Recorrer el resultSet

        if (rs.next()) {
        	result = new Inmueble(
                    rs.getString("referencia"),
                    rs.getString("direccion"),
                    rs.getString("cp"),
                    rs.getString("localidad"),
                    rs.getString("descripcion"),
                    rs.getString("tipo"),
                    rs.getString("urlimagen"),
                    rs.getDouble("metros"),
                    rs.getDouble("precio"),
                    rs.getInt("banos"),
                    rs.getInt("dormitorios"),
                    rs.getBoolean("disponible"),
                    rs.getDate("fbaja").toLocalDate()
        			);
        }
        
        System.out.println("RESULT:" + result);
        
        cerrarConexion();
        
        return result;
	}

	public List<Inmueble> filtrarPrecio(List<Inmueble> entrada, Filtro filtro){
		Stream<Inmueble> filtrado = 
				entrada.stream().filter(
						i -> (i.getPrecio() >= filtro.getPrecioDesde() && i.getPrecio() <= filtro.getPrecioHasta()));
		return filtrado.collect(Collectors.toList());
	}
	
	public List<Inmueble> filtrarDormitorios(List<Inmueble> entrada, Filtro filtro){
		Stream<Inmueble> filtrado = 
				entrada.stream().filter(i -> i.getDormitorios()==filtro.getDormitorios());
		return filtrado.collect(Collectors.toList());
	}
	
	public List<Inmueble> filtrarBanos(List<Inmueble> entrada, Filtro filtro){
		Stream<Inmueble> filtrado = 
				entrada.stream().filter(i -> i.getBanos()==filtro.getBanos());
		return filtrado.collect(Collectors.toList());
	}
	
	*/

}
