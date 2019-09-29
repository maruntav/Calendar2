package com.osman.sample.calendar.application92.sqlite;

import java.sql.*;

public class SQLiteConnection {
	
	private Connection connection;
	private boolean test;
	
	public SQLiteConnection(boolean test) {
		this.test = test;
		try {
			Class.forName("org.sqlite.JDBC");
			if(test) {
				connection = DriverManager.getConnection("jdbc:sqlite:SQLiteCalEvTest1235.db");
			} else {
				connection = DriverManager.getConnection("jdbc:sqlite:SQLiteCalEv1235.db");
			}
			createTable();
		} catch (ClassNotFoundException e) {
			closeConnection();
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean createTable() {
		try {
			String query = "CREATE TABLE IF NOT EXISTS events (eventid INTEGER PRIMARY KEY, name TEXT, title TEXT, details TEXT, date TEXT, time TEXT)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean closeConnection() {
		try {
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Connection getConnection() {
		return connection;
	}


	public void dropEventsTable() {
		if(test) {
			String query = "DROP TABLE IF EXISTS events";
			PreparedStatement preparedStatement = null;
			try {
				if(connection == null) DriverManager.getConnection("jdbc:sqlite:SQLiteCalEvTest1235.db");
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.execute();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.connection.close();
	}
}
