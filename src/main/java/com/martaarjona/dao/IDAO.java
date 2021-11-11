package com.martaarjona.dao;

import java.util.List;

public interface IDAO<T> {
	void save();
	int edit();
	int delete();
	//List<T> showAll();
	
}
