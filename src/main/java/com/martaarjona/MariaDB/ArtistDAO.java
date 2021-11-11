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
	
	public ArtistDAO(int id, String name, String nacionality, String photo) {
		super(id,name,nacionality,photo);	
	}
	public ArtistDAO() {}
	private Connection con = null;
	
	public Artist getArtistById(int id) {
		Artist a = new ArtistDAO();
		con=Connect.getConnect();
		
		if(con!=null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = con.prepareStatement(GETARTISTBYID);
			//	ps.setInt(1, id);
				rs=ps.executeQuery();
				if(rs.next()) {
					a=new ArtistDAO(rs.getInt("id"),
							rs.getString("nombre"),
							rs.getString("nacionalidad"),
							rs.getString("foto"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		return a;
	}
	public List<Artist> showAll(){
		List<Artist> result = new ArrayList<>();
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
