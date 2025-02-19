package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.BoardDto;

public interface BoardDao {

	int registerArticle(BoardDto boardDto);
	List<BoardDto> searchListAll();
	List<BoardDto> searchListBySubject(String subject);
	BoardDto viewArticle(int no);
	void modifyArticle(BoardDto boardDto);
	void deleteArticle(int no);
	
}
