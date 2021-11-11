package com.martaarjona.dao;

import java.util.List;

public interface IDAO<T> {
	void save();
	void edit();
	void delete();
	//List<T> showAll();
	
}
