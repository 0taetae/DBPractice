package com.ssafy.crawling.home;

public class AptDealDto extends AptInfoDto {

	private String aptDong; 	// 아파트 동
	private String floor; 		// 층
	private int dealYear; 		// 거래년도
	private int dealMonth; 		// 거래월
	private int dealDay; 		// 거래일
	private double excluUseAr; 	// 전용면적
	private String dealAmount; 	// 거래금액

	public String getAptDong() {
		return aptDong;
	}

	public void setAptDong(String aptDong) {
		this.aptDong = aptDong;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public double getExcluUseAr() {
		return excluUseAr;
	}

	public void setExcluUseAr(double excluUseAr) {
		this.excluUseAr = excluUseAr;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

//	@Override
//	public String toString() {
//		return "AptDealDto [" + super.toString() + ", aptDong=" + aptDong + ", floor=" + floor + ", dealYear=" + dealYear + ", dealMonth="
//				+ dealMonth + ", dealDay=" + dealDay + ", excluUseAr=" + excluUseAr + ", dealAmount=" + dealAmount
//				+ "]";
//	}

}
