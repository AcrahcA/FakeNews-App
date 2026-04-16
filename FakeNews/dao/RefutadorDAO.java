package fakeNews.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import fakeNews.modelo.Refutador;

public class RefutadorDAO {
	private final static String URL = "jdbc:mysql://localhost:3306/fakenews_cerverasolischarca";
	private final static String USR = "root";
	private final static String PSS = "admin";

	public boolean guardar(Refutador refutador) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "INSERT INTO `refutadores` ( `nombre`, `apellido`, `medios_presentes`, `legajo` ) "
					+ "VALUES (?, ?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, refutador.getNombre());
			stmt.setString(2, refutador.getApellido());
			stmt.setString(3, refutador.getMedios_presentes());
			stmt.setInt(4,refutador.getLegajo());
			filasAfectadas = stmt.executeUpdate();
			int id_refutador = 0;
			try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
				 if (generatedKeys.next()) {        
		                id_refutador = generatedKeys.getInt(1);
		            }
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			// Esta excepción significa que un usuario con ese legajo ya existe.
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return filasAfectadas == 0 ? false : true;
	}

	public boolean eliminar(int id_refutadores) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "DELETE FROM refutadores WHERE id_refutadores = ?;";
					
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id_refutadores);

			filasAfectadas = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return filasAfectadas == 0 ? false : true;
	}

	public boolean actualizar(Refutador refutadorActualizado, int idRefutadorActual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE refutadores SET  `nombre` = ?, `apellido` = ?, `medios_presentes = ?`,   `legajo` = ? "
					+ "WHERE `id_refutadores` = ?;";
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, refutadorActualizado.getNombre());
			stmt.setString(2, refutadorActualizado.getApellido());
			stmt.setString(3, refutadorActualizado.getMedios_presentes());
			stmt.setInt(4, refutadorActualizado.getLegajo());
			stmt.setInt(5, idRefutadorActual);
			filasAfectadas = stmt.executeUpdate();
			

		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		return filasAfectadas == 0 ? false : true;
	}

	public ArrayList<String> traerTodas() {
		ArrayList<String> todas = new ArrayList<>();
		Connection c = null;
		try {
			c = DriverManager
					.getConnection(URL, USR, PSS);
			String sql = "SELECT nombre FROM refutadores";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				todas.add(rs.getString("nombre"));

			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return todas;
	}
	
	public ArrayList<Refutador> traerTodos() {
		ArrayList<Refutador> refutador = new ArrayList<Refutador>();
		
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			// TODO Cambiar consulta
			String sql = "SELECT id_refutadores, legajo, nombre, apellido, medios_presentes FROM refutadores;";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				
				Refutador unRefutador = new Refutador();

				unRefutador.setNombre(rs.getString("nombre"));
				unRefutador.setLegajo(rs.getInt("legajo"));
				unRefutador.setApellido(rs.getString("apellido"));
				unRefutador.setMedios_presentes(rs.getString("medios_presentes"));

				
				
				refutador.add(unRefutador);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return refutador;
		
	}
	
	
	public boolean modificar(Refutador r, int legajo) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE refutadores SET nombre = ?, apellido = ?, medios_presentes = ? WHERE legajo = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, r.getNombre());
	        stmt.setString(2, r.getApellido());
	        stmt.setString(3, r.getMedios_presentes());
	        stmt.setInt(4, legajo);
	        filasAfectadas = stmt.executeUpdate();


		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		return filasAfectadas == 0 ? false : true;
	}
	public Refutador buscarPorId(int idRefutador) {
		Refutador rf = null;
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT id_refutadores, nombre,legajo, apellido, medios_presentes FROM refutadores WHERE id_refutadores = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, idRefutador);
			ResultSet rs = stmt.executeQuery();

			// Si lo encuentra, dado que el número de legajo es único, solo tendremos una
			// coincidencia. Por eso utilizo el if en vez del while
			if (rs.next()) {

				rf = new Refutador();
				rf.setNombre(rs.getString("nombre"));
				rf.setApellido(rs.getString("apellido"));
				rf.setMedios_presentes(rs.getString("medios_presentes"));
				rf.setLegajo(rs.getInt("legajo"));

		
			

				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return rf;
	}
	public int obtenerIDporLegajo(int legajo) {
	    int idEncontrado = -1;
	    Connection c = null;

	    try {
	        c = DriverManager.getConnection(URL, USR, PSS);
	        String sql = "SELECT id_refutadores FROM refutadores WHERE legajo = ?";
	        PreparedStatement stmt = c.prepareStatement(sql);
	        stmt.setInt(1, legajo);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            idEncontrado = rs.getInt("id_refutadores");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try { 
	            if (c != null) c.close(); 
	        } catch (SQLException e) {}
	    }

	    return idEncontrado;
	}

	public int obtenerIDporNombre(String nombre) {
		int id = 0; Connection c = null;
		try { c = DriverManager.getConnection(URL, USR, PSS); 
		String sql = "select id_refutadores from refutadores where nombre = ?";
		PreparedStatement stmt = c.prepareStatement(sql); stmt.setString(1, nombre);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) { 
			id = rs.getInt("id_refutadores"); 
		   }
		} 
		catch (SQLException e) { e.printStackTrace(); 
		} finally {
			if (c != null) {
				try { c.close();
				} catch (SQLException e) {
					e.printStackTrace(); 
					} 
				}
			} return id;
	
	   }
	}






	
	
	
	
	

