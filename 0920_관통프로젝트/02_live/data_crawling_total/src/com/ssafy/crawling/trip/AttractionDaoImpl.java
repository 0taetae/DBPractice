package com.ssafy.crawling.trip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.crawling.home.AptInfoDto;

public class AttractionDaoImpl implements AttractionDao {
	
	private final String driverName = "com.mysql.cj.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost:3306/ssafytrip?serverTimezone=UTC";
//	private final String url = "jdbc:mysql://70.12.60.80:3306/ssafytrip?serverTimezone=UTC";
	private final String user = "ssafy";
	private final String pass = "ssafy";

	private static AttractionDao attractionDao = new AttractionDaoImpl();

	private AttractionDaoImpl() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static AttractionDao getAttractionDao() {
		return attractionDao;
	}

	@Override
	public void registSido(List<SidoGugunDto> sidoGugunList) {
		System.out.println("dao sido list: " + sidoGugunList.toString());
		StringBuilder sql = new StringBuilder(
				"insert into sidos (sido_code, sido_name) \n");
		sql.append("values ");
		for (int i = 0; i < sidoGugunList.size(); i++) {
			if(i != sidoGugunList.size() - 1)
				sql.append("(?, ?), \n");
			else
				sql.append("(?, ?)");
		}

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			int idx = 0;
			for(SidoGugunDto sidoGugunDto: sidoGugunList) {
				pstmt.setInt(++idx, sidoGugunDto.getSidoCode());
				pstmt.setString(++idx, sidoGugunDto.getSidoName());
			}
			int cnt = pstmt.executeUpdate();
			System.out.println(">>>>>>>>>>>>>>> sido insert count : " + cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registGugun(List<SidoGugunDto> sidoGugunList) {
		StringBuilder sql = new StringBuilder(
				"insert into guguns (gugun_code, gugun_name, sido_code) \n");
		sql.append("values ");
		for (int i = 0; i < sidoGugunList.size(); i++) {
			if(i != sidoGugunList.size() - 1)
				sql.append("(?, ?, ?), \n");
			else
				sql.append("(?, ?, ?)");
		}

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			int idx = 0;
			for(SidoGugunDto sidoGugunDto: sidoGugunList) {
				pstmt.setInt(++idx, sidoGugunDto.getGugunCode());
				pstmt.setString(++idx, sidoGugunDto.getGugunName());
				pstmt.setInt(++idx, sidoGugunDto.getSidoCode());
			}
			int cnt = pstmt.executeUpdate();
			System.out.println(">>>>>>>>>>>>>>> gugun insert count : " + cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registAttraction(List<AttractionDto> attractionList) {
		StringBuilder sql = new StringBuilder(
				"insert into attractions (content_id, title, content_type_id, area_code, si_gun_gu_code, first_image1, first_image2, map_level, latitude, longitude, tel, addr1, addr2, homepage, overview) \n");
		sql.append("values ");
		for (int i = 0; i < attractionList.size(); i++) {
			if(i != attractionList.size() - 1)
				sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?), \n");
			else
				sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		}

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			int idx = 0;
			for(AttractionDto attractionDto: attractionList) {
				pstmt.setString(++idx, attractionDto.getContentId());
				pstmt.setString(++idx, attractionDto.getTitle());
				pstmt.setString(++idx, attractionDto.getContentTypeId());
				pstmt.setString(++idx, attractionDto.getAreaCode());
				pstmt.setString(++idx, attractionDto.getSiGunGuCode());
				pstmt.setString(++idx, attractionDto.getFirstImage1());
				pstmt.setString(++idx, attractionDto.getFirstImage2());
				pstmt.setInt(++idx, attractionDto.getMapLevel());
				pstmt.setString(++idx, attractionDto.getLatitude());
				pstmt.setString(++idx, attractionDto.getLongitude());
				pstmt.setString(++idx, attractionDto.getTel());
				pstmt.setString(++idx, attractionDto.getAddr1());
				pstmt.setString(++idx, attractionDto.getAddr2());
				pstmt.setString(++idx, attractionDto.getHomePage());
				pstmt.setString(++idx, attractionDto.getOverView());
			}
			int cnt = pstmt.executeUpdate();
			System.out.println(">>>>>>>>>>>>>>> attraction insert count : " + cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
