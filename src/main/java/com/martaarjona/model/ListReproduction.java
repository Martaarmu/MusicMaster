package com.martaarjona.model;

import java.util.List;

public class ListReproduction {
	
	protected int id;
	protected String name;
	protected User creator;
	protected List<Song> songs;
	protected List<User> users;
	
	
	
	public ListReproduction() {
		super();
	}
	public ListReproduction(String name,User u) {
		this.name=name;
		this.creator=u;
	}
	public ListReproduction(int id, String name, User creator, List<Song> songs, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.creator = creator;
		this.songs = songs;
		this.users = users;
	}
	
	public ListReproduction(String name, User creator, List<Song> songs) {
		super();
		this.name = name;
		this.creator = creator;
		this.songs = songs;
	}
	public ListReproduction(String name, User creator, List<Song> songs, List<User> users) {
		super();
		this.name = name;
		this.creator = creator;
		this.songs = songs;
		this.users = users;
	}
	public ListReproduction(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}
	public ListReproduction(int id, String name,User u) {
		super();
		this.id = id;
		this.name = name;
		this.creator=u;
	}
	
	
	public ListReproduction(String nombre, String id_usuario) {
		// TODO Auto-generated constructor stub
		this.name=nombre;
		this.creator.setName(id_usuario);
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
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
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
		ListReproduction other = (ListReproduction) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ListReproduction [id=" + id + ", name=" + name + ", creator=" + creator.getName() + ", songs=" + songs + "]";
	}
	
	
	
}
