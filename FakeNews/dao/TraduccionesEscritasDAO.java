package fakeNews.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import fakeNews.modelo.TraduccionesEscritas;

public class TraduccionesEscritasDAO {
	private final static String URL = "jdbc:mysql://localhost:3306/fakenews_cerverasolischarca";
	private final static String USR = "root";
	private final static String PSS = "admin";

	public boolean guardar(TraduccionesEscritas traduccionesEscritas) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "INSERT INTO `traduccionesescritas` ( `nombreDiario`, `nombreNota`, `cantidadDeVisualizaciones` ) "
					+ "VALUES (?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, traduccionesEscritas.getNombreDiario());
			stmt.setString(2, traduccionesEscritas.getNombreNota());
			stmt.setString(3, traduccionesEscritas.getCantidadDeVisualizaciones());

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

	public boolean eliminar(int idTraduccionesEscritas) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "DELETE FROM traduccionesescritas WHERE idTE = ?;";
					
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, idTraduccionesEscritas);

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

	public boolean actualizar(TraduccionesEscritas traduccionesEscritasActualizada, int idTAActual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE traduccionaudiovisual SET  `nombreDiario` = ?, `nombreNota` = ?, `cantidadDeVisualizaciones` = ?, "
					+ "WHERE `idTE` = ?;";
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, traduccionesEscritasActualizada.getNombreDiario());
			stmt.setString(2, traduccionesEscritasActualizada.getNombreNota());
			stmt.setString(3, traduccionesEscritasActualizada.getCantidadDeVisualizaciones());
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

	public ArrayList<TraduccionesEscritas> traerTodos() {
		ArrayList<TraduccionesEscritas> traduccionesEscritas = new ArrayList<TraduccionesEscritas>();
		
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT * FROM traduccionesescritas";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				
				TraduccionesEscritas unaTraduccionesEscritas = new TraduccionesEscritas();
				
				unaTraduccionesEscritas.setNombreDiario(rs.getString("nombreDiario"));
				unaTraduccionesEscritas.setNombreNota(rs.getString("nombreNota"));
				unaTraduccionesEscritas.setCantidadDevisualizaciones(rs.getString("cantidadDeVisualizaciones"));
				
				
				traduccionesEscritas.add(unaTraduccionesEscritas);
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
		
		return traduccionesEscritas;
		
	}
	
	
	
	
	
	
}
