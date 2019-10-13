package com.osman.sample.calendar.application92.date;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TimeValidation {
	
	public static boolean timeIsValid(String hour, String minute) {
    	int hourVal = Integer.valueOf(hour);
    	int minuteVal = Integer.valueOf(minute);
    	if(hourVal < 0 || hourVal > 23)
    		return false;
    	else if(minuteVal < 0 || minuteVal > 59)
    		return false;
    	return true;
    }
	
	public static boolean hourOrMinuteValid(String newValue) {
    	if(newValue.length() > 2) {
    		return false;
    	} else if(newValue.length() == 0) {
    		return true;
    	}
    	return !Arrays.asList(newValue.split("")).stream()
    			.filter(str -> !Character.isDigit(str.charAt(0))).findFirst().isPresent();
    }
	
	public static boolean timeFormatValid(String time) {
		return Arrays.asList(time.split("")).stream()
			.filter(ch -> ch.equals(":"))
			.collect(Collectors.toList())
			.size() == 1;
	}
}
