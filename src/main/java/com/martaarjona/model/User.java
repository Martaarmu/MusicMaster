package com.martaarjona.model;

import java.util.List;

public class User {
	
	protected int id;
	protected String name;
	protected String email;
	protected String photo;
	protected String password;
	protected List<ListReproduction> list;
	protected List<Reproduction> reproductions;
	
	public User(int id, String name, String email, String photo, List<ListReproduction> list,
			List<Reproduction> reproductions) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.photo = photo;
		this.list = list;
		this.reproductions = reproductions;
	}
	public User(String name, String email, String photo, List<ListReproduction> list,
			List<Reproduction> reproductions) {
		super();
		this.name = name;
		this.email = email;
		this.photo = photo;
		this.list = list;
		this.reproductions = reproductions;
	}
	
	
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public User(int id,String name, String email, String password) {
		super();
		this.id=id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public User(String name, String password) {
		super();
		this.name = name;
		
		this.password = password;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int id) {
		// TODO Auto-generated constructor stub
		this.id=id;
		
	}
	
	public User(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public List<ListReproduction> getList() {
		return list;
	}
	public void setList(List<ListReproduction> list) {
		this.list = list;
	}
	public List<Reproduction> getReproductions() {
		return reproductions;
	}
	public void setReproductions(List<Reproduction> reproductions) {
		this.reproductions = reproductions;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
	
}
