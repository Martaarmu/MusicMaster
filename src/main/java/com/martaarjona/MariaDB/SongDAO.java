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

public class SongDAO extends Song{

	private static final String SHOWALL = "SELECT  id,nombre,duracion,id_genero,id_disco FROM cancion";
	
	public SongDAO(int id,String name, Time duration, Genero genero, Disk disk) {
		super(id, name, duration, genero, disk);
		// TODO Auto-generated constructor stub
	}
	
	public SongDAO(String name, Time duration, Genero genero, int nReproducciones, Disk disk) {
		super(name, duration, genero, nReproducciones, disk);
		// TODO Auto-generated constructor stub
	}
	
	public SongDAO() {
		// TODO Auto-generated constructor stub
	}

	private static Connection con = null;
	
	public static List<SongDAO> showAll() {
		List<SongDAO> result = new ArrayList<>();
		con = Connect.getConnect();
		if(con!=null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = con.prepareStatement(SHOWALL);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					
					GeneroDAO gd = new GeneroDAO();
					Genero g = gd.getGeneroById(rs.getInt("id_genero"));
					
					DiskDAO dd = new DiskDAO();
					Disk d = dd.getDiskById(rs.getInt("id_disco"));
					
					result.add(new SongDAO(rs.getInt("id"),
							rs.getString("nombre"),
							rs.getTime("duracion"),g,d
							));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}


}
