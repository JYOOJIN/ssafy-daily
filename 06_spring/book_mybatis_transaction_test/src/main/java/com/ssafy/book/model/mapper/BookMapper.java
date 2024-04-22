package com.ssafy.book.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.book.model.BookDto;

@Mapper
public interface BookMapper {
	List<BookDto> list() throws SQLException;
	
	BookDto view(String isbn) throws SQLException;
	
	void register(BookDto bookDto) throws SQLException;
	
	void delete(String isbn) throws SQLException;
	
	void modify(BookDto bookDto) throws SQLException;
	
}
