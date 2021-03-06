package com.osman.sample.calendar.application92.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class MonthInfo {
	LocalDate date;
	public MonthInfo(int month, int year) {
		date = LocalDate.of(year, month, 1);
	}
	
	public MonthInfo(String month, int year) {
		this(Month.valueOf(month.toUpperCase()).getValue(), year);
	}
	
	public MonthInfo() {
		date = LocalDate.now();
	}
	
	public String getMonthName() {
		return date.format(DateTimeFormatter.ofPattern("MMMM"));
	}
	
	public int getNumOfDays() {
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
	
	public LocalDate getPreviousMonth() {
		return date.minusMonths(1);
	}
	
	public LocalDate getNextMonth() {
		return date.plusMonths(1);
	}
}