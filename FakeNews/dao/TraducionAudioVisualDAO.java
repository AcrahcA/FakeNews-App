package fakeNews.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import fakeNews.modelo.TraducionAudioVisual;

public class TraducionAudioVisualDAO {
	private final static String URL = "jdbc:mysql://localhost:3306/fakenews_cerverasolischarca";
	private final static String USR = "root";
	private final static String PSS = "admin";

	public boolean guardar(TraducionAudioVisual traducionAudioVisual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "INSERT INTO `traduccionaudiovisual` ( `nombreDelPrograma`, `minutosDedicados`, `rating` ) "
					+ "VALUES (?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sql);
		
			stmt.setString(1, traducionAudioVisual.getNombreDelPrograna());
			stmt.setString(2, traducionAudioVisual.getMinutosDedicados());
			stmt.setString(3, traducionAudioVisual.getRating());

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

	public boolean eliminar(int idTraduccionAudioVisual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "DELETE FROM traduccionaudiovisual WHERE idTA = ?;";
					
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, idTraduccionAudioVisual);

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

	public boolean actualizar(TraducionAudioVisual traducionAudioVisualActualizada, int idTAActual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE traduccionaudiovisual SET  `nombreDelPrograma` = ?, `minutosDedicados` = ?, `rating` = ?, "
					+ "WHERE `idTA` = ?;";
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, traducionAudioVisualActualizada.getNombreDelPrograna());
			stmt.setString(2, traducionAudioVisualActualizada.getMinutosDedicados());
			stmt.setString(3, traducionAudioVisualActualizada.getRating());
			stmt.setInt(4, idTAActual);

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

	public ArrayList<TraducionAudioVisual> traerTodos() {
		ArrayList<TraducionAudioVisual> traducionAudioVisual = new ArrayList<TraducionAudioVisual>();
		
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT * FROM traduccionaudiovisual";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				
				TraducionAudioVisual unaTraducionAudioVisual = new TraducionAudioVisual();
				
				unaTraducionAudioVisual.setNombreDelPrograna(rs.getString("nombreDelPrograma"));
				unaTraducionAudioVisual.setMinutosDedicados(rs.getString("minutosDedicados"));
				unaTraducionAudioVisual.setRating(rs.getString("rating"));;
				
				
				traducionAudioVisual.add(unaTraducionAudioVisual);
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
		
		return traducionAudioVisual;
		
	}
	
	
	
	
	
}
