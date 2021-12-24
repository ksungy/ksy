package com.qtqt.mvc.common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	public static Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "QT", "QT");

			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static void commit(Connection connection) {
		try {
			if(connection != null && !connection.isClosed()) {				
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection connection) {
		try {
			if(connection != null && !connection.isClosed()) {				
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection connection) {
		try {
			if(connection != null && !connection.isClosed()) {
				connection.close();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement statement) {
		try {
			if(statement != null && !statement.isClosed()) {				
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet resultSet) {
		try {
			if(resultSet != null && !resultSet.isClosed()) {				
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
} 