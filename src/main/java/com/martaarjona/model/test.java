package com.martaarjona.model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import com.martaarjona.MariaDB.ListReproductionDAO;
import com.martaarjona.MariaDB.UserDAO;
import com.martaarjona.utils.Connect;



public class test {

	public static void main(String[] args) {
		
		//Timestamp t = new Timestamp();
		//Genero g = new Genero(1,"genero1");
		//Song s = new Song("cancion",t,g);
		//Song s1 = new Song("cancion1",t,g);
		
		//User u = new User ("juan","email","foto");
		//List<Song> songs = new ArrayList<>();
		//songs.add(s);
		
		ListReproductionDAO lr = new ListReproductionDAO();
		ListReproduction l = lr.showbyid(1);
		
		
		
		//List<ListReproduction> l = lr.showAll();

		System.out.println(l);
	
	
		
	
	}
}
