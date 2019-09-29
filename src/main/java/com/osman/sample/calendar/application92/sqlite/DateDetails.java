package com.osman.sample.calendar.application92.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DateDetails {
	public static boolean dateHasEvents(Connection connection, LocalDate date) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM events WHERE date='?'");
		statement.setString(1, date.toString());
		ResultSet resultSet = statement.executeQuery();
		return resultSet.first();
	}
}