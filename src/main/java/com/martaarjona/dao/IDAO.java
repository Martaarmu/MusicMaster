package com.martaarjona.dao;

import java.util.List;

import com.martaarjona.MariaDB.DAOExcepcion;

public interface IDAO<T> {
	int save() throws DAOExcepcion;
	int edit() throws DAOExcepcion;
	int delete() throws DAOExcepcion;
	
	
}
