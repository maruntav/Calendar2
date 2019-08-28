package com.osman.sample.calendar.application92.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM");
		return date.format(dateTimeFormatter);
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
	
}