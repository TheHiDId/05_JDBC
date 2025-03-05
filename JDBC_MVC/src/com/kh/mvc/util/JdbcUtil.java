package com.kh.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/* JDBC API 사용 중 중복코드가 너무 많아서
 * 중복된 코드를 메서드로 분리하여 필요할 때 마다 재사용하도록 하기위한 클래스
 */
public class JdbcUtil {

	public static Connection getConnection() {
		
		final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
		final String USERNAME = "KH13_BJY";
		final String PASSWORD = "KH1234";
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		} catch (SQLException e) {
				e.printStackTrace();
		} 
		
		return conn;
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) stmt.close();
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
}
