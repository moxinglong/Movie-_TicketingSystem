package com.moxl.movie.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	public static Connection GetConnection() throws Exception {
		//mysql:
		String driver ="com.mysql.jdbc.Driver";
		/*
		 * oracle:
		 * oracle.jdbc.driver.OracleDriver
		 * */
		Connection conn =null;
		Class.forName(driver);
		conn=DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/movie", 
				"root", "");
		/*
		 * oracle:
		 * jdbc:oracle:thin:@localhost:1521:orcl
		 * */
		return conn;
	}

}
