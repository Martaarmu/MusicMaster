package com.martaarjona.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Timer;

public class Song {
	protected int id;
	protected String name;
	protected Time duration;
	protected Genero genero;
	protected Disk disk;
	
	
	
	public Song(int id, String name, Time time, Genero genero, Disk disk) {
		super();
		this.id = id;
		this.name = name;
		this.duration = time;
		this.genero = genero;
		this.disk = disk;
	}
	
	public Song() {
		// TODO Auto-generated constructor stub
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
	public Time getDuration() {
		return duration;
	}
	public void setDuration(Time duration) {
		this.duration = duration;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Disk getDisk() {
		return disk;
	}
	public void setDisk(Disk disk) {
		this.disk = disk;
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
		Song other = (Song) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", duration=" + duration + ", genero=" + genero
				+  ", disk=" + disk + "]";
	}
	
	
	
	

}
