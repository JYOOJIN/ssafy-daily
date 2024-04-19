package com.ssafy.book.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.book.model.BookDto;
import com.ssafy.book.model.dao.BookDao;

@Service
public class BookServiceImpl implements BookService {
	private BookDao bookDao;
	
	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	@Override
	public List<BookDto> list() throws SQLException {
		return bookDao.list();
	}

	@Override
	public BookDto view(String isbn) throws SQLException{
		return bookDao.view(isbn);
	}

	@Override
	public void register(BookDto bookDto)throws SQLException {
		bookDao.register(bookDto);
	}

	@Override
	public void delete(String isbn) throws SQLException{
		bookDao.delete(isbn);
	}

	@Override
	public void modify(BookDto bookDto) throws SQLException{
		bookDao.modify(bookDto);
	}

}
