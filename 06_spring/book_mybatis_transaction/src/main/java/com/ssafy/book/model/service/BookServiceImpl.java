package com.ssafy.book.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.book.model.BookDto;
import com.ssafy.book.model.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {
	
	private BookMapper bookMapper;

	public BookServiceImpl(BookMapper bookMapper) {
		super();
		this.bookMapper=bookMapper;
	}
	
	@Override
	public List<BookDto> list() throws SQLException {
		
		return bookMapper.list();
		
	}

	@Override
	public BookDto view(String isbn) throws SQLException{
		
		return bookMapper.view(isbn);
		 
	}

	@Override
	@Transactional
	public void register(BookDto bookDto)throws SQLException {
		
		bookMapper.register(bookDto);
	}

	@Override
	@Transactional
	public void delete(String isbn) throws SQLException{
		bookMapper.delete(isbn);
	}

	@Override
	public void modify(BookDto bookDto) throws SQLException{
		bookMapper.modify(bookDto);
	}

}
