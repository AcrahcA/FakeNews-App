package fakeNews.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import fakeNews.modelo.Medios;

public class MediosDAO {
	private final static String URL = "jdbc:mysql://localhost:3306/fakenews_cerverasolischarca";
	private final static String USR = "root";
	private final static String PSS = "admin";

	public boolean guardar(Medios medios) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "INSERT INTO `medios` ( `usuario`, `fecha`, `retractor`, `nombre` ) "
					+ "VALUES (?, ?, ?, ?;";
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, medios.getUsuario());
			stmt.setString(2, medios.getFecha());
			stmt.setString(3, medios.getRetractador());
			stmt.setString(4, medios.getNombre());
			

			filasAfectadas = stmt.executeUpdate();

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

	public boolean eliminar(int idMedios) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "DELETE FROM medios WHERE id_medios = ?;";
					
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, idMedios);

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

	public boolean actualizar(Medios mediosActualizada, int idMediosActual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE medios SET  `usuario` = ?, `fecha` = ?, `retractor` = ?, `nombre` = ?"
					+ "WHERE `id_medios` = ?;";
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, mediosActualizada.getUsuario());
			stmt.setString(2, mediosActualizada.getFecha());
			stmt.setString(3, mediosActualizada.getRetractador());
			stmt.setString(4, mediosActualizada.getNombre());
			stmt.setInt(5, idMediosActual);

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

	public ArrayList<Medios> traerTodos() {
		ArrayList<Medios> medios = new ArrayList<Medios>();
		
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT * FROM medios";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				
				Medios unMedio = new Medios();
				
				unMedio.setUsuario(rs.getString("usuario"));
				
				
				
				medios.add(unMedio);
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
		
		return medios;
		
	}
	
	
	
	
	
}
