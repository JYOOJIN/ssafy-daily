package com.ssafy.book.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;

public interface BookDao {
	void writeArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
}
