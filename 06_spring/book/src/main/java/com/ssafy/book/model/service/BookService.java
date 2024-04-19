package com.ssafy.book.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.book.model.BookDto;

public interface BookService {
	List<BookDto> list () throws SQLException;
	BookDto view(String isbn) throws SQLException;
	void register(BookDto bookDto) throws SQLException;
	void delete(String isbn) throws SQLException;
	void modify(BookDto bookDto) throws SQLException;
}
