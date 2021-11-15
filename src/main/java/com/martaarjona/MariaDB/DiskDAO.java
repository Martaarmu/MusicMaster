package com.martaarjona.MariaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.martaarjona.dao.IDAO;
import com.martaarjona.model.Artist;
import com.martaarjona.model.Disk;
import com.martaarjona.model.Genero;
import com.martaarjona.model.Song;
import com.martaarjona.utils.Connect;

import javafx.css.PseudoClass;

public class DiskDAO extends Disk implements IDAO<Disk>{

	private static final String GETDISKBYID ="SELECT id,nombre,fecha,id_artista FROM disco";
	private static final String INSERT = "INSERT INTO disco (nombre,fecha,id_artista)"
										+ "VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE disco SET nombre=?, fecha=?,"
										+ "id_artista=? WHERE id=?";
	private static final String DELETE = "DELETE FROM disco WHERE id=?";
	
	public DiskDAO(int id, String name, Date date, Artist artist) {
		super(id,name,date,artist);
		
	}
	public DiskDAO() {}
	
	private Connection con = null;
	
	/**
	 * Método que trae un disco por su id
	 * @param id
	 * @return
	 * @throws DAOExcepcion 
	 */
	public Disk getDiskById(int id) throws DAOExcepcion {
		Disk d = new DiskDAO();
		con=Connect.getConnect();
		
		if(con!=null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = con.prepareStatement(GETDISKBYID);
				rs=ps.executeQuery();
				if(rs.next()) {
					
					ArtistDAO ad = new ArtistDAO();
					Artist a = ad.getArtistById(rs.getInt("id_artista"));
					d=new DiskDAO(rs.getInt("id"),
							rs.getString("nombre"),
							rs.getDate("fecha"),a
							);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new DAOExcepcion(e);
			}
		}
		return d;
	}
	
	
	public List<Disk> showAll(){
		List<Disk> result = new ArrayList<>();
		return result;
	}
	
	/**
	 * Método que permite guardar un disco en la BD
	 * @throws DAOExcepcion 
	 */
	@Override
	public int save() throws DAOExcepcion {
		// TODO Auto-generated method stub
		int rs=0;
		con = Connect.getConnect();
		if(con!=null) {
			try {
				PreparedStatement ps = con.prepareStatement(INSERT);
				ps.setString(1, this.name);
				//ps.setDate(2, this.date);
				ps.setInt(3, this.artist.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new DAOExcepcion(e);
			}
			
		}
		return rs;
	}
	
	/**
	 * Método que permite editar un disco de la BD
	 * @throws DAOExcepcion 
	 */
	@Override
	public int edit() throws DAOExcepcion {
		// TODO Auto-generated method stub
		int rs=0;
		con = Connect.getConnect();
		if(con!=null) {
			try {
				PreparedStatement ps = con.prepareStatement(UPDATE);
				ps.setString(1, this.name);
				//ps.setDate(2, this.date);
				ps.setInt(3, this.artist.getId());
				ps.setInt(4, this.id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new DAOExcepcion(e);
			}
			
		}
		return rs;
	}
	
	/**
	 * Método que permite borrar un disco de la BD
	 * @throws DAOExcepcion 
	 */
	@Override
	public int delete() throws DAOExcepcion {
		// TODO Auto-generated method stub
		int rs = 0;
		con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETE);
				q.setInt(1, this.id);
				rs = q.executeUpdate();
				this.id = -1;
				this.name = "";
				this.date=null;
				this.artist = null;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DAOExcepcion(e);
				//e.printStackTrace();
			}
		}
		return rs;
	}


}
