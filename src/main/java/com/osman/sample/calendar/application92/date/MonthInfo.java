package com.osman.sample.calendar.application92.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class MonthInfo {
//	private int month; //1 - 12, from Jan to Dec
//	private int year;
	LocalDate date;
	public MonthInfo(int month, int year) {
//		this.month = month;
//		this.year = year;
		date = LocalDate.of(year, month, 1);
	}
	
	public MonthInfo() {
//		this.month = now.getMonthValue();
//		this.year = now.getYear();
		date = LocalDate.now();
	}
	
	public String getMonthName() {
//		LocalDate date = LocalDate.of(year, month, 1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM");
		return date.format(dateTimeFormatter);
	}
	
	public int getNumOfDays() {
//		LocalDate date = LocalDate.of(year, month, 1);
		return date.lengthOfMonth();
	}
	
	public int getMonth() {
		return date.getMonthValue();
	}
	
	public int getYear() {
		return date.getYear();
	}
	
	public int getDayOfMonth() {
		return date.getDayOfMonth();
	}
	
	public int getFirstMonday() {
		LocalDate date = LocalDate.of(getYear(), getMonth(), 1);
		while(date.getDayOfWeek() != DayOfWeek.MONDAY) {
			date = date.plusDays(1);
		}
		return date.getDayOfMonth();
	}
	
	public LocalDate getFirstMondayForMonthCalendar() {
		LocalDate date = LocalDate.of(getYear(), getMonth(), 1);
		while(date.getDayOfWeek() != DayOfWeek.MONDAY) {
			date = date.minusDays(1);
		}
		return date;
	}
}