package com.framework.commons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.utils.ReadDB;

public class DBCommons{
	
	// This class will have common functions related to DB

	public static List<Map<String, String>> readData(String query) {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		ResultSet dataSet = ReadDB.readDb(query);

		try {
			while (dataSet.next()) {
				Map<String, String> record = new HashMap<String, String>();
				for (int col = 1; col <= dataSet.getMetaData().getColumnCount(); col++) {
					String columnName = dataSet.getMetaData().getColumnName(col);
					String columnValue = dataSet.getString(col);
					record.put(columnName, columnValue);
				}
				data.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

}
