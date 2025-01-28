package com.framework.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ReadDB {

	public static ResultSet readDb(String query) {
		ResultSet dataSet = null;
		Properties prop = ReadProp.readData("Config.properties");
		String url = prop.getProperty("db_url");
		String username = prop.getProperty("db_user");
		String password = prop.getProperty("db_pass");

		Connection connection;
		try {
			connection = DriverManager.getConnection(url, username, password);
			dataSet = connection.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataSet;
	}	

}
