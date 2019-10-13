package com.osman.sample.calendar.application92.date;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.assertj.core.util.Arrays;
import org.junit.Test;

public class TimeValidationTest {
	@Test
	public void testTimeValidation() {
		//hour
		IntStream.range(0, 24).forEach(hour -> {
			assertEquals(true, TimeValidation.timeIsValid(String.valueOf(hour), "00"));
		});
		IntStream.range(0, 10).forEach(hour -> {
			assertEquals(true, TimeValidation.timeIsValid("0" + hour, "00"));
		});
		IntStream.range(-100, 200)
			.filter(num -> num < 0 && num > 23)
			.forEach(hour -> {
				assertEquals(false, TimeValidation.timeIsValid(String.valueOf(hour), "00"));
				assertEquals(false, TimeValidation.timeIsValid("0" + hour, "00"));
			});
		
		//minute
		IntStream.range(0, 60).forEach(minute -> {
			assertEquals(true, TimeValidation.timeIsValid("00", String.valueOf(minute)));
		});
		IntStream.range(0, 10).forEach(minute -> {
			assertEquals(true, TimeValidation.timeIsValid("00", "0" + minute));
		});
		IntStream.range(-100, 200)
			.filter(num -> num < 0 && num > 59)
			.forEach(minute -> {
				assertEquals(false, TimeValidation.timeIsValid("00", String.valueOf(minute)));
			});
	}
	
	@Test
	public void testTimeFormatValid() {
		//valid values
		Arrays.asList(new String[] {"02:00", "04:00", "10:00"}).stream()
			.map(val -> (String) val)
			.forEach(val -> {
				assertEquals(true, TimeValidation.timeFormatValid(val));
			});
		//invalid values
		Arrays.asList(new String[] {"02", "49", "12"}).stream()
			.map(val -> (String) val)
			.forEach(val -> {
				assertEquals(false, TimeValidation.timeFormatValid(val));
			});
	}
}
