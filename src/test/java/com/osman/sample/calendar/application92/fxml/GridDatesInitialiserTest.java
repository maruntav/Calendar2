package com.osman.sample.calendar.application92.fxml;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.osman.sample.calendar.application92.date.MonthInfo;

import org.junit.Test;

public class GridDatesInitialiserTest {
	@Test
	public void testAddSixthRow() {
		List<Integer> expected = Arrays.asList(new Integer[] {4, 4, 1, 6, 3, 1, 5, 2, 30, 4, 2, 30});
		IntStream.range(1, 13).boxed().collect(Collectors.toList()).stream()
			.forEach(input -> {
				int actual = new MonthInfo(input, 2019).getFirstMondayForMonthCalendar().plusDays(35).getDayOfMonth();
				assertEquals(expected.get(input - 1).intValue(), actual);
			});
	}
}
