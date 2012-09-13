package com.labmojo.demo.multidownload.model;

public class Book {
	public String title;
	public int id;

	public Book(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
