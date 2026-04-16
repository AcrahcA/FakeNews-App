package fakeNews.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import fakeNews.modelo.Refutacion;

public class RefutacionDAO {
	private final static String URL = "jdbc:mysql://localhost:3306/fakenews_cerverasolischarca";
	private final static String USR = "root";
	private final static String PSS = "admin";

	public int guardar(Refutacion refutacion) {
	    Connection c = null;
	    int id_refutacion = 0;

	    try {
	        c = DriverManager.getConnection(URL, USR, PSS);

	        String sql = "INSERT INTO `refutacion` (`fecha`, `fuentes`, `organismo_oficial`, `id_refutadores`) "
	                + "VALUES (?, ?, ?, ?);";

	        PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	        stmt.setString(1, refutacion.getFecha());
	        stmt.setString(2, refutacion.getFuentes());
	        stmt.setString(3, refutacion.getOrganismo_oficial());

	        RefutadorDAO rdao = new RefutadorDAO();
	        int idRefutador = rdao.obtenerIDporNombre(refutacion.getRefutador());
	        stmt.setInt(4, idRefutador);

	        stmt.executeUpdate();

	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                id_refutacion = generatedKeys.getInt(1);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (c != null) c.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return id_refutacion;
	}


	public boolean eliminar(int idRefutacion) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "DELETE FROM refutacion WHERE id_refutacion = ?;";
					
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, idRefutacion);

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

	public boolean actualizar(Refutacion refutacionActualizada, int idRefutacionActual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql ="UPDATE refutacion SET `fecha` = ?, `fuentes` = ?, `organismo_oficial` = ?, `id_refutadores` = ?"
	                + "WHERE `id_refutacion` = ?;";

			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, refutacionActualizada.getFecha());
			stmt.setString(2, refutacionActualizada.getFuentes());
			stmt.setString(3, refutacionActualizada.getOrganismo_oficial());
			RefutadorDAO rdao = new RefutadorDAO();
			int idRefutador = rdao.obtenerIDporNombre(refutacionActualizada.getRefutador());
			stmt.setInt(4, idRefutador);
			stmt.setInt(5, idRefutacionActual);
			
			

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

	public ArrayList<Refutacion> traerTodos() {
		ArrayList<Refutacion> refutacion = new ArrayList<Refutacion>();
		
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT fecha, fuentes, organismo_oficial, id_refutadores FROM refutacion;";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				
				Refutacion unaRefutacion = new Refutacion();
				
				unaRefutacion.setFecha(rs.getString("fecha"));
				unaRefutacion.setFuentes(rs.getString("fuentes"));
				unaRefutacion.setOrganismo_oficial(rs.getString("organismo_oficial"));
				unaRefutacion.setRefutador(rs.getString("id_refutadores"));
                
				refutacion.add(unaRefutacion);
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
		
		return refutacion;
		
	}
	public int obtenerIDDeRefutacion(int id) {
	    int idEncontrado = 0;
	    Connection c = null;

	    try {
	        c = DriverManager.getConnection(URL, USR, PSS);

	        String sql = "SELECT id_refutacion FROM refutacion WHERE id_refutacion = ?";
	        PreparedStatement stmt = c.prepareStatement(sql);
	        stmt.setInt(1, id);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            idEncontrado = rs.getInt("id_refutacion");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return idEncontrado;
	}

	
	
	
}
