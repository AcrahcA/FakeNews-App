package fakeNews.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import fakeNews.modelo.RedSocial;

public class RedSocialDAO {
	private final static String URL = "jdbc:mysql://localhost:3306/fakenews_cerverasolischarca";
	private final static String USR = "root";
	private final static String PSS = "admin";

	public boolean guardar(RedSocial redSocial) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "INSERT INTO `redsocial` ( `plataforma`, `usuario`, `numeroDeInteracciones` ) "
					+ "VALUES (?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sql);
		
			stmt.setString(1, redSocial.getPlataforma());
			stmt.setString(2, redSocial.getUsuario());
			stmt.setString(3, redSocial.getNumeroDeInteracciones());

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

	public boolean eliminar(int idRedSocial) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "DELETE FROM redsocial WHERE idRedSocial = ?;";
					
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, idRedSocial);

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

	public boolean actualizar(RedSocial redSocialActualizada, int idRedSocialActual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE redsocial SET  `plataforma` = ?, `usuario` = ?, `numeroDeInteracciones` = ?, "
					+ "WHERE `idRedSocial` = ?;";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, redSocialActualizada.getPlataforma());
			stmt.setString(2, redSocialActualizada.getUsuario());
			stmt.setString(3, redSocialActualizada.getNumeroDeInteracciones());
			stmt.setInt(4, idRedSocialActual);

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

	public ArrayList<RedSocial> traerTodos() {
		ArrayList<RedSocial> redSocial = new ArrayList<RedSocial>();
		
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT * FROM redsocial";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				
				RedSocial unaRedSocial = new RedSocial();
				
				unaRedSocial.setPlataforma(rs.getString("plataforna"));
				unaRedSocial.setUsuario(rs.getString("usuario"));
				unaRedSocial.setNumeroDeInteracciones(rs.getString("numeroDeInteracciones"));;
				
				
				redSocial.add(unaRedSocial);
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
		
		return redSocial;
		
	}
	
	
	
	
	
}
