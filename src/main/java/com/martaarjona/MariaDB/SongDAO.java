package com.martaarjona.MariaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import com.martaarjona.dao.IDAO;
import com.martaarjona.model.Disk;
import com.martaarjona.model.Genero;
import com.martaarjona.model.Song;
import com.martaarjona.utils.Connect;

import javafx.css.PseudoClass;

public class SongDAO extends Song implements IDAO<Song> {

	private static final String SHOWALL = "SELECT  id,nombre,duracion,id_genero,id_disco FROM cancion";
	private static final String INSERT = "INSERT INTO cancion (nombre,duracion,id_genero,id_disco)" + "VALUES(?,?,?,?)";
	private static final String UPDATE = "UPDATE cancion SET nombre=?, duracion=?, id_genero=?,"
			+ " id_disco=? WHERE id=?";
	private static final String DELETE = "DELETE FROM cancion WHERE id=?";

	public SongDAO(int id, String name, Time duration, Genero genero, Disk disk) {
		super(id, name, duration, genero, disk);
		// TODO Auto-generated constructor stub
	}

	public SongDAO() {
		// TODO Auto-generated constructor stub
	}

	private static Connection con = null;

	/**
	 * Método que muestra todas las canciones de una lista
	 * 
	 * @return
	 * @throws DAOExcepcion 
	 */
	public static List<SongDAO> showAll() throws DAOExcepcion {
		List<SongDAO> result = new ArrayList<>();
		con = Connect.getConnect();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.prepareStatement(SHOWALL);
				rs = ps.executeQuery();

				while (rs.next()) {

					GeneroDAO gd = new GeneroDAO();
					Genero g = gd.getGeneroById(rs.getInt("id_genero"));

					DiskDAO dd = new DiskDAO();
					Disk d = dd.getDiskById(rs.getInt("id_disco"));

					result.add(new SongDAO(rs.getInt("id"), rs.getString("nombre"), rs.getTime("duracion"), g, d));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DAOExcepcion(e);
				//e.printStackTrace();
			}finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
				}
				
			}

		}
		return result;
	}

	/**
	 * Método que guarda una canción en la BD
	 * @throws DAOExcepcion 
	 */
	@Override
	public int save() throws DAOExcepcion {
		// TODO Auto-generated method stub
		int rs = 0;
		con = Connect.getConnect();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				//PreparedStatement ps = con.prepareStatement(INSERT);
				ps=con.prepareStatement(INSERT);
				ps.setString(1, this.name);
				ps.setTime(2, this.duration);
				ps.setInt(3, this.genero.getId());
				ps.setInt(4, this.disk.getId());
				rs = ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new DAOExcepcion(e);
			}finally {
				try {
					ps.close();
					//rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
				}
				
			}

		}
		return rs;
	}

	/**
	 * Método que edita una canción de la BD
	 * @throws DAOExcepcion 
	 */

	@Override
	public int edit() throws DAOExcepcion {
		// TODO Auto-generated method stub
		int rs = 0;
		con = Connect.getConnect();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				 ps = con.prepareStatement(UPDATE);
				ps.setString(1, this.name);
				ps.setTime(2, this.duration);
				ps.setInt(3, this.genero.getId());
				ps.setInt(4, this.disk.getId());
				ps.setInt(4, this.id);
				rs = ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new DAOExcepcion(e);
			}finally {
				try {
					ps.close();
					//rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
				}
				
			}

		}
		return rs;

	}

	/**
	 * Método que borra una canción de la BD
	 * @throws DAOExcepcion 
	 */
	@Override
	public int delete() throws DAOExcepcion {
		// TODO Auto-generated method stub
		int rs = 0;
		con = Connect.getConnect();
		if (con != null) {
			PreparedStatement ps= null;
			try {
				 ps = con.prepareStatement(DELETE);
				ps.setInt(1, this.id);
				rs = ps.executeUpdate();
				this.id = -1;
				this.name = "";
				this.duration = null;
				this.genero = null;
				this.disk = null;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new DAOExcepcion(e);
			}finally {
				try {
					ps.close();
					//rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
				}
				
			}

		}
		return rs;

	}

}
