package com.ssafy.crawling.home;

public class AptInfoDto {

	private int no; 				// 일련번호
	private String aptSeq; 			// 아파트 단지 일련번호
	private String sggCd; 			// 법정동 시구군 코드
	private String umdCd; 			// 법정동 읍면동 코드
	private String umdNm; 			// 법정동 읍면동
	private String jibun; 			// 지번
	private String roadNmSggCd; 	// 도로명 시구군 코드
	private String roadNm; 			// 도로명 주소
	private int roadNmBonbun; 		// 도로명 길 본번호
	private int roadNmBubun;		// 도로명 길 부번호
	private String aptNm; 			// 아파트 단지명
	private int buildYear; 			// 건축년도
	private String latitude; 		// 위도
	private String longitude; 		// 경도

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getAptSeq() {
		return aptSeq;
	}

	public void setAptSeq(String aptSeq) {
		this.aptSeq = aptSeq;
	}

	public String getSggCd() {
		return sggCd;
	}

	public void setSggCd(String sggCd) {
		this.sggCd = sggCd;
	}

	public String getUmdCd() {
		return umdCd;
	}

	public void setUmdCd(String umdCd) {
		this.umdCd = umdCd;
	}

	public String getUmdNm() {
		return umdNm;
	}

	public void setUmdNm(String umdNm) {
		this.umdNm = umdNm;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getRoadNmSggCd() {
		return roadNmSggCd;
	}

	public void setRoadNmSggCd(String roadNmSggCd) {
		this.roadNmSggCd = roadNmSggCd;
	}

	public String getRoadNm() {
		return roadNm;
	}

	public void setRoadNm(String roadNm) {
		this.roadNm = roadNm;
	}

	public int getRoadNmBonbun() {
		return roadNmBonbun;
	}

	public void setRoadNmBonbun(int roadNmBonbun) {
		this.roadNmBonbun = roadNmBonbun;
	}

	public int getRoadNmBubun() {
		return roadNmBubun;
	}

	public void setRoadNmBubun(int roadNmBubun) {
		this.roadNmBubun = roadNmBubun;
	}

	public String getAptNm() {
		return aptNm;
	}

	public void setAptNm(String aptNm) {
		this.aptNm = aptNm;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "AptInfoDto [no=" + no + ", aptSeq=" + aptSeq + ", sggCd=" + sggCd + ", umdCd=" + umdCd + ", umdNm="
				+ umdNm + ", jibun=" + jibun + ", roadNmSggCd=" + roadNmSggCd + ", roadNm=" + roadNm + ", roadNmBonbun="
				+ roadNmBonbun + ", aptNm=" + aptNm + ", buildYear=" + buildYear + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
