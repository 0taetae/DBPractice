package com.ssafy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/ssafydb?serverTimezone=UTC";
	private final String DB_ID = "ssafy";
	private final String DB_PWD = "ssafy";
	
	public InsertTest() {  // 1. 드라이버 로딩
		try {
			Class.forName(DRIVER);
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		InsertTest it = new InsertTest();
		int cnt = it.registerMember("ssafy", "1234", "김싸피", "ssafy", "ssafy.com");
		System.out.println(cnt + "개 입력 성공");
	}
	
	private int registerMember(String id, String pwd, String name, String eid, String edomain) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
//			conn.setAutoCommit(false);  // transaction 직접 관리
			StringBuilder sql = new StringBuilder("insert into ssafy_members (user_id, user_pwd, user_name, email_id, email_domain) \n");
			sql.append("values (?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());  // 실행하기 위한 statement 만들기 
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, eid);
			pstmt.setString(5, edomain);
			
			cnt = pstmt.executeUpdate();
//			conn.commit();  // 예외가 발생하지 않으면 
		} catch(SQLException e) {
			e.getStackTrace();
//			try {
//				conn.rollback();  // 예외가 발생하면 
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	private Connection getConnection() {  // 2. DB 연결
		try {
			return DriverManager.getConnection(URL, DB_PWD, DB_ID);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 3. SQL 문장 실행 준비 - statement
	// 4. SQL 문장 실행
	// 5. close
}
