package com.ssafy.crawling.home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AptDaoImpl implements AptDao {

	private final String driverName = "com.mysql.cj.jdbc.Driver";
	private final String url = "jdbc:mysql://70.12.60.80:3306/ssafyhome?serverTimezone=UTC";
	private final String user = "ssafy";
	private final String pass = "ssafy";

	private static AptDao aptDao = new AptDaoImpl();

	private AptDaoImpl() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static AptDao getAptDao() {
		return aptDao;
	}
	
	@Override
	public List<String> getDongCodeList() {
		List<String> dongCodeList = new ArrayList<>();
		String sql = "select substr(dong_code, 1, 5) dongcode\n"
				+ "from dongcodes\n"
				+ "where gugun_name is not null \n"
				+ "and dong_name is null";
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				dongCodeList.add(rs.getString("dongcode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("dongcode count : " + dongCodeList.size());
		return dongCodeList;
	}

	@Override
	public List<String> getAptSeqList(String lawdCd) {
		List<String> aptSeqList = new ArrayList<>();
		String sql = "select apt_seq from houseinfos where sgg_cd = " + lawdCd;
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				aptSeqList.add(rs.getString("apt_seq"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aptSeqList;
	}
	
	@Override
	public List<Integer> getAptNoList(Map<String, String> map) {
		List<Integer> aptNoList = new ArrayList<>();
		String sql = "select no from housedeals";
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				aptNoList.add(rs.getInt("no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aptNoList;
	}

	@Override
	public void registAptInfo(List<AptInfoDto> aptInfoList) {
		StringBuilder sql = new StringBuilder(
				"insert into houseinfos (apt_seq, sgg_cd, umd_cd, umd_nm, jibun, road_nm_sgg_cd, road_nm, road_nm_bonbun, road_nm_bubun, apt_nm, build_year, latitude, longitude) \n");
		sql.append("values ");
		for (int i = 0; i < aptInfoList.size(); i++) {
			if(i != aptInfoList.size() - 1)
				sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?), \n");
			else
				sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		}

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			int idx = 0;
			for(AptInfoDto aptInfoDto: aptInfoList) {
				pstmt.setString(++idx, aptInfoDto.getAptSeq());
				pstmt.setString(++idx, aptInfoDto.getSggCd());
				pstmt.setString(++idx, aptInfoDto.getUmdCd());
				pstmt.setString(++idx, aptInfoDto.getUmdNm());
				pstmt.setString(++idx, aptInfoDto.getJibun());
				pstmt.setString(++idx, aptInfoDto.getRoadNmSggCd());
				pstmt.setString(++idx, aptInfoDto.getRoadNm());
				pstmt.setInt(++idx, aptInfoDto.getRoadNmBonbun());
				pstmt.setInt(++idx, aptInfoDto.getRoadNmBubun());
				pstmt.setString(++idx, aptInfoDto.getAptNm());
				pstmt.setInt(++idx, aptInfoDto.getBuildYear());
				pstmt.setString(++idx, aptInfoDto.getLatitude());
				pstmt.setString(++idx, aptInfoDto.getLongitude());
			}
			int cnt = pstmt.executeUpdate();
			System.out.println(">>>>>>>>>>>>>>> Apt Info insert count : " + cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registAptDeal(List<AptDealDto> aptDeakList) {
		StringBuilder sql = new StringBuilder(
				"insert into housedeals (apt_seq, apt_dong, floor, deal_year, deal_month, deal_day, exclu_use_ar, deal_amount) \n");
		sql.append("values ");
		for (int i = 0; i < aptDeakList.size(); i++) {
			if(i != aptDeakList.size() - 1)
				sql.append("(?, ?, ?, ?, ?, ?, ?, ?), \n");
			else
				sql.append("(?, ?, ?, ?, ?, ?, ?, ?)");
		}

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			int idx = 0;
			for(AptDealDto aptDealDto: aptDeakList) {
				pstmt.setString(++idx, aptDealDto.getAptSeq());
				pstmt.setString(++idx, aptDealDto.getAptDong());
				pstmt.setString(++idx, aptDealDto.getFloor());
				pstmt.setInt(++idx, aptDealDto.getDealYear());
				pstmt.setInt(++idx, aptDealDto.getDealMonth());
				pstmt.setInt(++idx, aptDealDto.getDealDay());
				pstmt.setDouble(++idx, aptDealDto.getExcluUseAr());
				pstmt.setString(++idx, aptDealDto.getDealAmount());
				
			}
			int cnt = pstmt.executeUpdate();
			System.out.println(">>>>>>>>>>>>>>> Apt Deal insert count : " + cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
