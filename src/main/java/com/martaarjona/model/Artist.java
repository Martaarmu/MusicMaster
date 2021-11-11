package com.martaarjona.model;

public class Artist {
	
	protected int id;
	protected String name;
	protected String nacionality;
	protected String photo;
	
	
	
	public Artist(int id, String name, String nacionality, String photo) {
		super();
		this.id = id;
		this.name = name;
		this.nacionality = nacionality;
		this.photo = photo;
	}

	public Artist(String name, String nacionality, String photo) {
		this.name = name;
		this.nacionality = nacionality;
		this.photo = photo;
	}
	
	public Artist() {}

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

	public String getNacionality() {
		return nacionality;
	}

	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
		Artist other = (Artist) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", nacionality=" + nacionality + ", photo=" + photo + "]";
	}
	
	
	
}
