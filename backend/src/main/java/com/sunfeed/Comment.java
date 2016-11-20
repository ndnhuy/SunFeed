package com.sunfeed;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Comment {
	@DateTimeFormat
	private Date id;
	private String author;
	private String text;
	public Date getId() {
		return id;
	}
	public void setId(Date id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
