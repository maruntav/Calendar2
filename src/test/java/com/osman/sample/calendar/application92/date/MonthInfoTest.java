package com.osman.sample.calendar.application92.date;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.*;

public class MonthInfoTest {
	
	@Test
	public void testMonthNumber() {
		List<String> expected = Arrays.asList(
				new String[] {"January", "February", "March", "April", "May", "June", "July",
						"August", "September", "October", "November", "December"});
		Arrays.asList(new Integer[] {0, 1, 2, 3, 4, 5, 6 ,7, 8, 9, 10}).stream()
			.forEach(input -> {
				MonthInfo info = new MonthInfo(input, 2019);
				assertEquals(expected.get(input), info.getMonthName());
			});
	}
	
	@Test
	public void testMonthLength() {
		List<Integer> expected = Arrays.asList(new Integer[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31});
		Arrays.asList(new Integer[] {0, 1, 2, 3, 4, 5, 6 ,7, 8, 9, 10, 11}).stream()
			.forEach(input -> {
				MonthInfo info = new MonthInfo(input, 2019);
				assertEquals(expected.get(input).intValue(), info.getNumOfDays());
			});
	}
	
	@Test
	public void getFirstMonday() {
		List<Integer> expected = Arrays.asList(new Integer[] {7, 4, 4, 1, 6, 3, 1, 5, 2, 7, 4, 2});
		Arrays.asList(new Integer[] {0, 1, 2, 3, 4, 5, 6 ,7, 8, 9, 10, 11}).stream()
		.forEach(input -> {
			MonthInfo info = new MonthInfo(input, 2019);
			assertEquals(expected.get(input).intValue(), info.getFirstMonday());
		});
	}
}
