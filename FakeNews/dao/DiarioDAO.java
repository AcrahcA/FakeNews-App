package fakeNews.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DiarioDAO {
	private final static String URL = "jdbc:mysql://localhost:3306/fakenews_cerverasolischarca";
	private final static String USR = "root";
	private final static String PSS = "admin";
	public ArrayList<String> traerTodas() {
		ArrayList<String> todas = new ArrayList<>();
		Connection c = null;
		try {
			c = DriverManager
					.getConnection(URL, USR, PSS);
			String sql = "SELECT diario FROM diario";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				todas.add(rs.getString("diario"));
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
	public int obtenerIDDeDiario(String nombre) {
		int id = 0;
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "select iddiario from diario where diario = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, nombre);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt("iddiario");
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

		return id;

	
	}

}
