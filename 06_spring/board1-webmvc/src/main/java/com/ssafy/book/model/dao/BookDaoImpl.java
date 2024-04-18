package com.ssafy.book.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.ssafy.board.model.BoardDto;
import com.ssafy.util.DBUtil;

public class BookDaoImpl implements BookDao{
	private DataSource dataSource;
	private DBUtil dbUtil;
	
	public BookDaoImpl(DataSource dataSource, DBUtil dbUtil) {
		super();
		this.dataSource = dataSource;
		this.dbUtil = dbUtil;
	}

	@Override
	public void writeArticle(BoardDto boardDto) throws SQLException {
		
	}

	@Override
	public List<BoardDto> listArticle(Map<String, Object> param) throws SQLException {
		return null;
	}

	@Override
	public BoardDto getArticle(int articleNo) throws SQLException {
		return null;
	}
}
