package com.ssafy.book.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.book.model.BookDto;

public interface BookDao {
	List<BookDto> list() throws SQLException;
	BookDto view(String isbn) throws SQLException;
	void register(BookDto bookDto) throws SQLException;
	void delete(String isbn) throws SQLException;
	void modify(BookDto bookDto) throws SQLException;
}
