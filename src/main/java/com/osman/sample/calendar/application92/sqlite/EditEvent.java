package com.osman.sample.calendar.application92.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;

import com.osman.sample.calendar.application92.dto.Event;

public class EditEvent {
	Connection connection;
	public EditEvent(Connection connection) {
		this.connection = connection;
	}
	
	public int addEvent(String title, String details, LocalDate date, LocalTime time) {
		PreparedStatement statement = null;
		int newId = -1;
		try {
			String query = "INSERT INTO events (date, time, title, details) VALUES (?, ?, ?, ?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, date.toString());
			statement.setString(2, time.toString());
			statement.setString(3, title);
			statement.setString(4, details);
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();
			while(rs.next()) {
				newId = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			doCleanUp(null, statement, true);
		}
		return newId;
	}
	
	public ArrayList<Event> getEventsForDay(LocalDate date) {
		ArrayList<Event> events = new ArrayList<>();
		String query = "SELECT * FROM events WHERE date=?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, date.toString());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				events.add(new Event(resultSet.getInt(1), resultSet.getString("date"), resultSet.getString("time"), resultSet.getString("title"), resultSet.getString("details")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			doCleanUp(resultSet, preparedStatement, true);
		}
		return events;
	}
	
	public void editEvent(Event event) {
		String query = "UPDATE events SET title=?, details=?, date=?, time=? WHERE eventid=?";
		PreparedStatement preparedStatement = null;
		try {
			System.out.println(" the id is: " + event.getId());
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, event.getTitle());
			preparedStatement.setString(2, event.getDetails());
			preparedStatement.setString(3, event.getDate());
			preparedStatement.setString(4, event.getTime());
			preparedStatement.setInt(5, event.getId());
			preparedStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			doCleanUp(null, preparedStatement, true);
		}
	}
	
	public boolean deleteEvent(int id) {
		String query = "DELETE from events WHERE eventid=?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			doCleanUp(null, preparedStatement, false);
		}
		return false;
	}
	
	public void doCleanUp(ResultSet resultSet, PreparedStatement preparedStatement, boolean closeConnection) {
		try {
			if(resultSet != null) resultSet.close();
			if(preparedStatement != null) preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		//never called
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
