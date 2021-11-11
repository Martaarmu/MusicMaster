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

public class DiskDAO extends Disk implements IDAO<Disk>{

	private static final String GETDISKBYID ="SELECT id,nombre,fecha,id_artista FROM disco";
	public DiskDAO(int id, String name, Date date, Artist artist) {
		super(id,name,date,artist);
		
	}
	public DiskDAO() {}
	private Connection con = null;
	
	public Disk getDiskById(int id) {
		Disk d = new DiskDAO();
		con=Connect.getConnect();
		
		if(con!=null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = con.prepareStatement(GETDISKBYID);
			//	ps.setInt(1, id);
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
				e.printStackTrace();
			}
	
		}
		return d;
	}
	public List<Disk> showAll(){
		List<Disk> result = new ArrayList<>();
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
