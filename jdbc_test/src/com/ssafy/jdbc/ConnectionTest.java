package com.ssafy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/ssafydb?serverTimezone=UTC";
	private final String DB_ID = "ssafy";
	private final String DB_PWD = "ssafy";
	
	public ConnectionTest() {
		try {
			Class.forName(DRIVER);
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ConnectionTest ct = new ConnectionTest();
		Connection conn = ct.getConnection();
		if(conn != null) {
			System.out.println("DB 연결 성공");
		}
	}
	
	private Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, DB_PWD, DB_ID);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
