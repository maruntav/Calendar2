package com.osman.sample.calendar.application92.fxml;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.junit.Test;

public class ValidationTest {
	@Test
	public void testTimeValidation() {
		AddEventController addEventController = new AddEventController();
		//hour
		IntStream.range(0, 24).forEach(hour -> {
			assertEquals(true, addEventController.timeIsValid(String.valueOf(hour), "00"));
		});
		IntStream.range(0, 10).forEach(hour -> {
			assertEquals(true, addEventController.timeIsValid("0" + hour, "00"));
		});
		IntStream.range(-100, 200)
			.filter(num -> num < 0 && num > 23)
			.forEach(hour -> {
				assertEquals(false, addEventController.timeIsValid(String.valueOf(hour), "00"));
				assertEquals(false, addEventController.timeIsValid("0" + hour, "00"));
			});
		
		//minute
		IntStream.range(0, 60).forEach(minute -> {
			assertEquals(true, addEventController.timeIsValid("00", String.valueOf(minute)));
		});
		IntStream.range(0, 10).forEach(minute -> {
			assertEquals(true, addEventController.timeIsValid("00", "0" + minute));
		});
		IntStream.range(-100, 200)
			.filter(num -> num < 0 && num > 59)
			.forEach(minute -> {
				assertEquals(false, addEventController.timeIsValid("00", String.valueOf(minute)));
			});
	}
}
