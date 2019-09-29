package com.osman.sample.calendar.application92.sqlite;

import static org.junit.Assert.assertEquals;

import java.time.*;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.osman.sample.calendar.application92.dto.Event;

public class EditEventTest {
	public SQLiteConnection sqlConn;
	
	@Before
	public void setUp() {
		sqlConn = new SQLiteConnection(true);
	}
	
	@After
	public void tearDown() {
		sqlConn.dropEventsTable();
	}
	
	@Test
	public void testStatements() {
		LocalDate date = LocalDate.of(2019, 9, 20);
		LocalTime time = LocalTime.of(7, 0);
		int id = addEvent(date, time);
		selectEvent(id, date, true);
		deleteEvent(id);
		selectEvent(id, date, false);
	}
	
	public void selectEvent(int id, LocalDate date, boolean idExistsExpected) {
		EditEvent editEvent = new EditEvent(sqlConn.getConnection());
		ArrayList<Event> events = editEvent.getEventsForDay(date);
		boolean idExists = events.stream()
				.filter(event -> event.getId() == id)
				.findAny().isPresent();
		assertEquals(idExistsExpected, idExists);
	}
	
	public void deleteEvent(int id) {
		EditEvent editEvent = new EditEvent(sqlConn.getConnection());
		editEvent.deleteEvent(id);
	}
	
	public int addEvent(LocalDate date, LocalTime time) {
		EditEvent editEvent = new EditEvent(sqlConn.getConnection());
		return editEvent.addEvent("Wake Up", "Go to work", LocalDate.of(2019, 9, 20), LocalTime.of(7, 0));
	}
}
