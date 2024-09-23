package com.ssafy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest {
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/ssafydb?serverTimezone=UTC";
	private final String DB_ID = "ssafy";
	private final String DB_PWD = "ssafy";
	
	public UpdateTest() {
		try {
			Class.forName(DRIVER);
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		UpdateTest it = new UpdateTest();
		int cnt = it.updateMember("ssafy", "1111");
		System.out.println(cnt + "개 수정 성공");
	}
	
	private int updateMember(String id, String pwd) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
//			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder("update ssafy_members \n");
			sql.append("set user_pwd = ? \n");
			sql.append("where user_id = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, pwd);
			pstmt.setString(2, id);
			
			cnt = pstmt.executeUpdate();
//			conn.commit();
		} catch(SQLException e) {
			e.getStackTrace();
//			try {
//				conn.rollback();
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

	private int registerMember(String id, String pwd, String name, String eid, String edomain) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
//			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder("insert into ssafy_members (user_id, user_pwd, user_name, email_id, email_domain) \n");
			sql.append("values (?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, eid);
			pstmt.setString(5, edomain);
			
			cnt = pstmt.executeUpdate();
//			conn.commit();
		} catch(SQLException e) {
			e.getStackTrace();
//			try {
//				conn.rollback();
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

	private Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, DB_PWD, DB_ID);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
