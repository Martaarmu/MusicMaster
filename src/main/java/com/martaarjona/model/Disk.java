package com.martaarjona.model;

import java.util.Date;
import java.util.List;

public class Disk {
	protected int id;
	protected String name;
	protected Date date;
	protected int nReproduccion;
	protected Artist artist;
	protected List<Song>songs;
	
	
	
	public Disk(int id, String name, Date date, Artist artist) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.artist = artist;
		
	}
	public Disk(String name, Date date, int nReproduccion, Artist artist) {
		super();
		this.name = name;
		this.date = date;
		this.nReproduccion = nReproduccion;
		this.artist = artist;
	}
	public Disk () {}
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getnReproduccion() {
		return nReproduccion;
	}
	public void setnReproduccion(int nReproduccion) {
		this.nReproduccion = nReproduccion;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
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
		Disk other = (Disk) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "id: " + id + "\nNombre: " + name + "\nFecha: " + date + "\nArtista: "
				+ artist.getName();
	}
	
	
	
}
