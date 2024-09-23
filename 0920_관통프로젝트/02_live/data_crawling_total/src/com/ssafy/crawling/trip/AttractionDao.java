package com.ssafy.crawling.trip;

import java.util.List;

public interface AttractionDao {

	void registSido(List<SidoGugunDto> sidoGugunList);
	void registGugun(List<SidoGugunDto> sidoGugunList);
	void registAttraction(List<AttractionDto> attractionList);
	
}
