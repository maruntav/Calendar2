package com.osman.sample.calendar.application92.date;

import java.text.SimpleDateFormat;
import java.util.*;

public class MonthInfo {
	private GregorianCalendar firstDayOfmonth; //0 - 11, from jan to dec
	private int month;
	private int year;
	public MonthInfo(int month, int year) {
		this.month = month;
		this.year = year;
		firstDayOfmonth = new GregorianCalendar(year - 1900, month, 1);
	}
	
	public String getMonthName() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM");
		return simpleDateFormat.format(firstDayOfmonth.getTime());
	}
	
	public int getNumOfDays() {
		GregorianCalendar date = (GregorianCalendar) firstDayOfmonth.clone();
		date.add(Calendar.MONTH, 1);
		date.add(Calendar.DAY_OF_YEAR, -1);
		return date.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getFirstMonday() {
		GregorianCalendar date = (GregorianCalendar) firstDayOfmonth.clone();
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		date.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		return date.get(Calendar.DAY_OF_MONTH);
//		while(date.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
//			date.add(Calendar.DAY_OF_YEAR, 1);
//		}
//		return date.get(Calendar.DAY_OF_MONTH);
		
	}
}