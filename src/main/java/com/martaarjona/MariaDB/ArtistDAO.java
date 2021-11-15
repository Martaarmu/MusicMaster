package com.martaarjona.MariaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.martaarjona.dao.IDAO;
import com.martaarjona.model.Artist;
import com.martaarjona.model.Genero;
import com.martaarjona.utils.Connect;

public class ArtistDAO extends Artist implements IDAO<Artist>{

	private static String GETARTISTBYID="SELECT id,nombre,nacionalidad,foto FROM artista";
	private static String INSERT = "INSERT INTO artista (nombre,nacionalidad,foto)"
									+ "VALUES (?,?,?)";
	private static String UPDATE = "UPDATE disco SET nombre=?, nacionalidad=?, foto=?"
									+ "WHERE id=?";
	private static String DELETE = "DELETE FROM disco WHERE id=?";
	
	public ArtistDAO(int id, String name, String nacionality, String photo) {
		super(id,name,nacionality,photo);	
	}
	public ArtistDAO() {}
	
	private Connection con = null;
	
	/**
	 * Método que trae un artista de la BD por su id
	 * @param id
	 * @return
	 * @throws DAOExcepcion 
	 */
	
	public Artist getArtistById(int id) throws DAOExcepcion {
		Artist a = new ArtistDAO();
		con=Connect.getConnect();
		
		if(con!=null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = con.prepareStatement(GETARTISTBYID);
				rs=ps.executeQuery();
				if(rs.next()) {
					a=new ArtistDAO(rs.getInt("id"),
							rs.getString("nombre"),
							rs.getString("nacionalidad"),
							rs.getString("foto"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DAOExcepcion(e);
				//e.printStackTrace();
			}
	
		}
		return a;
	}
	
	public List<Artist> showAll(){
		List<Artist> result = new ArrayList<>();
		return result;
	}
	
	/**
	 * Método que permite insertar un artista en la BD
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
				ps.setString(1,this.name);
				ps.setString(2, this.nacionality);
				ps.setString(3, this.photo);
				rs=ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DAOExcepcion(e);
				//e.printStackTrace();
			}
			
			
		}
		return rs;
	}
	
	/**
	 * Método que permite editar un artista de la BD
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
				ps.setString(1,this.name);
				ps.setString(2, this.nacionality);
				ps.setString(3, this.photo);
				ps.setInt(4, this.id);
				rs=ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DAOExcepcion(e);
				//e.printStackTrace();
			}
			
			
		}
		return rs;
	}
	
	/**
	 * Método que permite borrar un artista de la BD
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
				this.nacionality = "";
				this.photo="";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new DAOExcepcion(e);
			}
		}
		return rs;
	}
	

}
