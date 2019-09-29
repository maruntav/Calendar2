package com.osman.sample.calendar.application92.dto;

public class Event {
	private int id;
	private String date;
	private String time;
	private String title;
	private String details;
	
	public Event(int id, String date, String time, String title, String details) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.title = title;
		this.details = details;
	}

	public int getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}
	
	public String getTitle() {
		return title;
	}

	public String getDetails() {
		return details;
	}
}
