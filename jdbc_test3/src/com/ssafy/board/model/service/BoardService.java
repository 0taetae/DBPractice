package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.BoardDto;

public interface BoardService {

	void registerArticle(BoardDto boardDto);  // 추가하기
	List<BoardDto> searchListAll();  // 전체보기
	List<BoardDto> searchListBySubject(String subject);  // 조건으로 검색하기
	BoardDto viewArticle(int no); // 상세보기
	void modifyArticle(BoardDto boardDto);  // 수정하기
	void deleteArticle(int no);  // 삭제하기 
	
}
