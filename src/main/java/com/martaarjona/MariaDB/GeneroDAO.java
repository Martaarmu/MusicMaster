package com.martaarjona.MariaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.martaarjona.dao.IDAO;
import com.martaarjona.model.Genero;
import com.martaarjona.utils.Connect;

public class GeneroDAO extends Genero implements IDAO<Genero>{
	
	private static final String GETGENEROBYID = "SELECT id,nombre FROM genero WHERE id=?";
	private static final String INSERT =  "INSERT INTO genero (nombre) VALUES (?)";
	private static final String UPDATE = "UPDATE genero SET nombre=? WHERE id=?";
	private static final String DELETE = "DELETE FROM genero WHERE id=?";
	
	public GeneroDAO (int id, String name) {
		super(id,name);
	}
	public GeneroDAO() {}
	private Connection con = null;
	
	/**
	 * Método que trae un genero por su id
	 * @param id
	 * @return
	 */
	public Genero getGeneroById(int id) {
		Genero g = new GeneroDAO();
		con=Connect.getConnect();
		
		if(con!=null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = con.prepareStatement(GETGENEROBYID);
				ps.setInt(1, id);
				rs=ps.executeQuery();
				if(rs.next()) {
					g=new GeneroDAO(rs.getInt("id"),rs.getString("nombre"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		return g;
	}
	
	public List<Genero> showAll(){
		List<Genero> result = new ArrayList<>();
		return result;
	}
	
	/**
	 * Método que inserta un genero en la BD
	 */
	@Override
	public int save() {
		// TODO Auto-generated method stub
		int rs=0;
		if(con!=null) {
			try {
				PreparedStatement ps = con.prepareStatement(INSERT);
				ps.setString(1,this.name);
				rs=ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return rs;
	}
	
	/**
	 * Método que edita un genero de la BD
	 */
	@Override
	public int edit() {
		// TODO Auto-generated method stub
		int rs=0;
		if(con!=null) {
			try {
				PreparedStatement ps = con.prepareStatement(UPDATE);
				ps.setString(1,this.name);
				ps.setInt(2, this.id);
				rs=ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return rs;
	}
	
	/**
	 * Método que borra un genero de la BD
	 */
	@Override
	public int delete() {
		// TODO Auto-generated method stub
		int rs=0;
		if(con!=null) {
			try {
				PreparedStatement ps = con.prepareStatement(DELETE);
				ps.setInt(1,this.id);
				rs=ps.executeUpdate();
				this.id=-1;
				this.name="";
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return rs;
		
	}

}
