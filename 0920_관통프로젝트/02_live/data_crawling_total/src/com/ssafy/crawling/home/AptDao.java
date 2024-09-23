package com.ssafy.crawling.home;

import java.util.List;
import java.util.Map;

public interface AptDao {

	List<String> getDongCodeList();
	List<String> getAptSeqList(String lawdCd);
	List<Integer> getAptNoList(Map<String, String> map);
	
	void registAptInfo(List<AptInfoDto> aptInfoList);
	void registAptDeal(List<AptDealDto> aptDeakList);
	
}
