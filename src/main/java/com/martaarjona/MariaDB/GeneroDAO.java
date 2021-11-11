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
	
	private static final String GETGENEROBYID="SELECT id,nombre FROM genero WHERE id=?";
	
	public GeneroDAO (int id, String name) {
		super(id,name);
	}
	public GeneroDAO() {}
	private Connection con = null;
	
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
	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
}
