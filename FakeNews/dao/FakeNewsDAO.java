package fakeNews.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import fakeNews.modelo.FakeNews;
import fakeNews.modelo.Refutador;

public class FakeNewsDAO {
	private final static String URL = "jdbc:mysql://localhost:3306/fakenews_cerverasolischarca";
	private final static String USR = "root";
	private final static String PSS = "admin";

	public boolean guardar(FakeNews fakeNews) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "INSERT INTO `fakenews` ( `titulo`, `descripcion`, `creador`, `categoria`, `fecha`,`medios`,`id_refutacion`, `legajo` ) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);" ;
			PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, fakeNews.getTitulo());
			stmt.setString(2, fakeNews.getDescripcion());
			stmt.setString(3, fakeNews.getCreador());
			CategoriaDAO cDao = new CategoriaDAO();
			 
			int idCategoria = cDao.obtenerIDDeCategoria(fakeNews.getCategoria());
			stmt.setInt(4, idCategoria);
			stmt.setString(5, fakeNews.getFecha());
			
			// TODO Con el nombre del medio buscar el id en la tabla
			tipoMedioDAO mDao = new tipoMedioDAO();
			int idMedios = mDao.obtenerIDDeMedios(fakeNews.getMedios());
			stmt.setInt(6, idMedios);
			RefutacionDAO rDao = new RefutacionDAO();
				int idRefutacion = rDao.obtenerIDDeRefutacion(fakeNews.getRefutacion());
				stmt.setInt(7,idRefutacion);
			stmt.setInt(8, fakeNews.getLegajo());
			int id_fakenews = 0;
				filasAfectadas = stmt.executeUpdate();

			try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
				 if (generatedKeys.next()) {        
		                id_fakenews = generatedKeys.getInt(1);
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
			String sql = "DELETE FROM fakenews WHERE id_fakenews = ?;";
					
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

	public boolean actualizar(FakeNews fakeNewsActualizada, int idFakeNewsActual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE fakenews SET `titulo` = ?, `descripcion` = ?, `creador` = ?, `categoria` = ?, `fecha` = ?, `medios` = ?, `id_refutacion` = ? , `legajo` = ?"
					+ "WHERE `id_fakenews` = ?;";

			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, fakeNewsActualizada.getTitulo());
			stmt.setString(2, fakeNewsActualizada.getDescripcion());
			stmt.setString(3, fakeNewsActualizada.getCreador());
			stmt.setString(4, fakeNewsActualizada.getCategoria());
			stmt.setString(5, fakeNewsActualizada.getFecha());
			stmt.setString(6, fakeNewsActualizada.getMedios());
			stmt.setInt(7, fakeNewsActualizada.getRefutacion());
			stmt.setInt(8, fakeNewsActualizada.getLegajo());
			stmt.setInt(9, idFakeNewsActual);
			

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

	public ArrayList<FakeNews> traerTodos() {
		ArrayList<FakeNews> fakeNews = new ArrayList<FakeNews>();
		
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			// TODO Cambiar consulta
			String sql = "SELECT fk.legajo, fk.titulo, fk.descripcion, fk.creador, fk.fecha, c.tipo AS categoria, m.tipo AS medios FROM fakenews fk, categorias c, tipomedios m WHERE fk.categoria = c.idCategorias AND fk.medios = m.idtipomedios;";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				
				FakeNews unaFake = new FakeNews();

				unaFake.setLegajo(rs.getInt("legajo"));
				unaFake.setCreador(rs.getString("creador"));
				unaFake.setDescripcion(rs.getString("descripcion"));
				unaFake.setCategoria(rs.getString("categoria"));
				unaFake.setFecha(rs.getString("fecha"));
				unaFake.setMedios(rs.getString("medios"));
				unaFake.setTitulo(rs.getString("titulo"));
				
				
				fakeNews.add(unaFake);
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
		
		return fakeNews;
		
	}
	
	public FakeNews buscarPorId(int idFakeNews) {
		FakeNews fk = null;
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT id_fakenews, legajo, titulo, descripcion, creador, fecha, medios, categoria FROM fakenews WHERE id_fakenews = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, idFakeNews);
			ResultSet rs = stmt.executeQuery();

			// Si lo encuentra, dado que el número de legajo es único, solo tendremos una
			// coincidencia. Por eso utilizo el if en vez del while
			if (rs.next()) {

				fk = new FakeNews();
				fk.setLegajo(rs.getInt("legajo"));
				fk.setDescripcion(rs.getString("descripcion"));
				fk.setCreador(rs.getString("creador"));
				fk.setFecha(rs.getString("fecha"));
				fk.setMedios(rs.getString("medios"));
				fk.setCategoria(rs.getString("categoria"));
				fk.setTitulo(rs.getString("titulo"));
                

				
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

		return fk;
	}
	public FakeNews buscarPorTitulo(String tituloBuscado) {
	    FakeNews fk = null;
	    Connection c = null;

	    try {
	        c = DriverManager.getConnection(URL, USR, PSS);
	        String sql = "SELECT  legajo, titulo, descripcion, creador, fecha, medios, categoria FROM fakenews WHERE titulo = ?";
	        PreparedStatement stmt = c.prepareStatement(sql);
	        stmt.setString(1, tituloBuscado);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            fk = new FakeNews();
	     
	            fk.setLegajo(rs.getInt("legajo"));
	            fk.setTitulo(rs.getString("titulo"));
	            fk.setDescripcion(rs.getString("descripcion"));
	            fk.setCreador(rs.getString("creador"));
	            fk.setFecha(rs.getString("fecha"));
	            fk.setMedios(rs.getString("medios"));
	            fk.setCategoria(rs.getString("categoria"));
	         }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return fk;
	}
	public int obtenerIDporLegajo(int legajo) {
	    int idEncontrado = -1;
	    Connection c = null;

	    try {
	        c = DriverManager.getConnection(URL, USR, PSS);
	        String sql = "SELECT id_fakeNews FROM fakenews WHERE legajo = ?";
	        PreparedStatement stmt = c.prepareStatement(sql);
	        stmt.setInt(1, legajo);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            idEncontrado = rs.getInt("id_fakenews");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try { 
	        	if (c != null) c.close(); }
	        catch (SQLException e) {}
	    }

	    return idEncontrado;
	}
	public boolean modificar(FakeNews r, int legajo) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE fakenews SET titulo = ?, categoria = ?, medios = ?, creador = ?, fecha = ?, descripcion = ? WHERE legajo = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			
	        stmt.setString(1, r.getDescripcion());
	        stmt.setString(2, r.getMedios());
	        stmt.setString(3,r.getDescripcion());
	        stmt.setString(4,r.getCreador());
	        stmt.setString(5, r.getCategoria());
	        stmt.setString(6, r.getFecha());
	        stmt.setInt(7, legajo);
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
	
	public int obtenerIdPorTitulo(String titulo) {
	    int id = -1;
	    Connection c = null;

	    try {
	        c = DriverManager.getConnection(URL, USR, PSS);

	        String sql = "SELECT id_fakenews FROM fakenews WHERE titulo = ?;";
	        PreparedStatement stmt = c.prepareStatement(sql);
	        stmt.setString(1, titulo);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            id = rs.getInt("id_fakenews");
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

	    return id;
	}

	public boolean actualizarIdRefutacionPorLegajo(int legajoFake, int nuevoIdRefutacion) {
	    Connection c = null;
	    int filasAfectadas = 0;

	    try {
	        c = DriverManager.getConnection(URL, USR, PSS);

	        String sql = "UPDATE fakenews SET id_refutacion = ? WHERE legajo = ?;";
	        PreparedStatement stmt = c.prepareStatement(sql);

	        stmt.setInt(1, nuevoIdRefutacion);
	        stmt.setInt(2, legajoFake);

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

	    return filasAfectadas > 0;
	}
	public FakeNews obtenerPorLegajo(int legajo) {
	    FakeNews f = null;

	    try (Connection con = DriverManager.getConnection(URL, USR, PSS)) {

	        PreparedStatement ps = con.prepareStatement(
	            "SELECT * FROM fakenews WHERE legajo = ?"
	        );
	        ps.setInt(1, legajo);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            f = new FakeNews();

	            // ——— CAMPOS DIRECTOS DE LA TABLA ———
	            f.setTitulo(rs.getString("titulo"));
	            f.setDescripcion(rs.getString("descripcion"));
	            f.setCreador(rs.getString("creador"));
	            f.setFecha(rs.getString("fecha"));
	            f.setLegajo(rs.getInt("legajo"));
	            f.setRefutacion(rs.getInt("id_refutacion"));
	            f.setCategoria(rs.getString("categoria"));
	            f.setMedios(rs.getString(rs.getString("medios")));
	            f.setRefutacion(rs.getInt("id_refutacion"));
	            
	           
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return f;
	}

	



	



	
	
	
	

}
