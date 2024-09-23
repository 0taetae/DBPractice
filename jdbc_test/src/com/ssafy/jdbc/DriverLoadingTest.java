package com.ssafy.jdbc;

public class DriverLoadingTest {
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/ssafydb?serverTimezone=UTC";
	private final String DB_ID = "ssafy";
	private final String DB_PWD = "ssafy";
	
	public DriverLoadingTest() {
		try {
			Class.forName(DRIVER);
			System.out.println("로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new DriverLoadingTest();
	}
}
