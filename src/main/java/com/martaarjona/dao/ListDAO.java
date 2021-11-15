package com.martaarjona.dao;

import java.util.List;

import com.martaarjona.MariaDB.DAOExcepcion;
import com.martaarjona.MariaDB.ListReproductionDAO;
import com.martaarjona.MariaDB.SongDAO;
import com.martaarjona.MariaDB.UserDAO;
import com.martaarjona.model.ListReproduction;
import com.martaarjona.model.Song;

public interface ListDAO extends IDAO<ListReproduction>{
	
	int addSong(SongDAO s, ListReproductionDAO l) throws DAOExcepcion;
	int removeSong (SongDAO s, ListReproductionDAO l) throws DAOExcepcion;
}
