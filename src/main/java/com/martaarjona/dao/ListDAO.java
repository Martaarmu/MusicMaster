package com.martaarjona.dao;

import java.util.List;

import com.martaarjona.MariaDB.ListReproductionDAO;
import com.martaarjona.MariaDB.SongDAO;
import com.martaarjona.MariaDB.UserDAO;
import com.martaarjona.model.ListReproduction;
import com.martaarjona.model.Song;

public interface ListDAO extends IDAO<ListReproduction>{
	List<ListReproduction> showbyname(String name);
	int addSong(SongDAO s, ListReproductionDAO l);
	int removeSong (SongDAO s, ListReproductionDAO l);
}
