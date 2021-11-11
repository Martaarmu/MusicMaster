package com.martaarjona.model;

import java.util.Date;

public class Reproduction {
	
	protected int id;
	protected User user;
	protected Song song;
	protected Date date;
	
	public Reproduction(int id, User user, Song song, Date date) {
		super();
		this.id = id;
		this.user = user;
		this.song = song;
		this.date = date;
	}


	public Reproduction(User user, Song song, Date date) {
		super();
		this.user = user;
		this.song = song;
		this.date = date;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
