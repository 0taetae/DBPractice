package com.ssafy.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.board.model.BoardDto;
import com.ssafy.util.DBUtil;

public class BoardDaoImpl implements BoardDao {
	
	private static BoardDao boardDao;
	
	private BoardDaoImpl() {}

	public static BoardDao getBoardDao() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}

	@Override
	public int registerArticle(BoardDto boardDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt=0;
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into board(subject, content, user_id) ");
			sql.append(" values(?,?,?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getSubject());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setString(3, boardDto.getUserId());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			
		}finally {
			DBUtil.getInstance().close(conn, pstmt);
		}
		return cnt;
	}

	@Override
	public List<BoardDto> searchListAll() {
		List<BoardDto> list = new ArrayList<BoardDto>();
		try (Connection conn = DBUtil.getInstance().getConnection();){
			StringBuilder sql = new StringBuilder();
			sql.append("select article_no, subject, content, user_id, register_time\n");
			sql.append("from board ");
			sql.append("where subject like \n");
			sql.append("order by register_time desc "); 
			try(PreparedStatement pstmt = conn.prepareStatement(sql.toString()); ){
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					BoardDto boardDto = new BoardDto();
					boardDto.setArticleNo(rs.getInt("article_no"));
					boardDto.setSubject(rs.getString("subject"));
					boardDto.setContent(rs.getString("content"));
					boardDto.setUserId(rs.getString("user_id"));
					boardDto.setRegisterTime(rs.getString("register_time"));

					list.add(boardDto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BoardDto> searchListBySubject(String subject) {
		List<BoardDto> list = new ArrayList<BoardDto>();
		try (Connection conn = DBUtil.getInstance().getConnection();){
			StringBuilder sql = new StringBuilder();
			sql.append("select article_no, subject, content, user_id, ");
			sql.append("register_time ");
			sql.append("from board ");
			sql.append("where subject like '%"+subject+"%' "); 
			sql.append("order by register_time desc "); 
			try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
				 ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					BoardDto boardDto = new BoardDto();
					boardDto.setArticleNo(rs.getInt("article_no"));
					boardDto.setSubject(rs.getString("subject"));
					boardDto.setContent(rs.getString("content"));
					boardDto.setUserId(rs.getString("user_id"));
					boardDto.setRegisterTime(rs.getString("register_time"));

					list.add(boardDto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BoardDto viewArticle(int no) {
		BoardDto boardDto = null;
		try (Connection conn = DBUtil.getInstance().getConnection();){
			StringBuilder sql = new StringBuilder();
			sql.append("select article_no, subject, content, user_id, ");
			sql.append("register_time ");
			sql.append("from board ");
			sql.append("where article_no ="+no+" "); 
			try(PreparedStatement pstmt = conn.prepareStatement(sql.toString()); ){
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					boardDto = new BoardDto();
					boardDto.setArticleNo(rs.getInt("article_no"));  // article_no는 대소문자 상관없음(칼럼명)
					boardDto.setSubject(rs.getString("subject"));  // article_no 대신 index 번호도 가능 
					boardDto.setContent(rs.getString("content"));
					boardDto.setUserId(rs.getString("user_id"));
					boardDto.setRegisterTime(rs.getString("register_time"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardDto;
	}

	@Override
	public void modifyArticle(BoardDto boardDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board set subject=?, content=? ");
			sql.append(" where article_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getSubject());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setInt(3, boardDto.getArticleNo());
			
			int cnt = pstmt.executeUpdate();
			DBUtil.getInstance().close(conn, pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteArticle(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from board ");
			sql.append(" where article_no=?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			int cnt = pstmt.executeUpdate();
			DBUtil.getInstance().close(conn, pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
