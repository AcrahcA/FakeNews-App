package fakeNews.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ProgramaDAO {
	private final static String URL = "jdbc:mysql://localhost:3306/fakenews_cerverasolischarca";
	private final static String USR = "root";
	private final static String PSS = "admin";
	public ArrayList<String> traerTodas() {
		ArrayList<String> todas = new ArrayList<>();
		Connection c = null;
		try {
			c = DriverManager
					.getConnection(URL, USR, PSS);
			String sql = "SELECT programa FROM programa";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				todas.add(rs.getString("programa"));
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
	public int obtenerIDDePrograma(String nombre) {
		int id = 0;
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "select idprograma from programa where programa = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, nombre);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt("idprograma");
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
