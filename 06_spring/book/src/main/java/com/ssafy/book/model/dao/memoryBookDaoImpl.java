package com.ssafy.book.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.book.model.BookDto;

@Repository
public class memoryBookDaoImpl implements BookDao {
	private List<BookDto> books;

	public memoryBookDaoImpl() {
		this.books = new ArrayList<>();
		books.add(new BookDto("111-222-3333", "홍길동", "책제목1", 10000, "좋은 책 1", "abc1.png"));
		books.add(new BookDto("111-222-4444", "임꺽정", "책제목2", 20000, "좋은 책 2", "abc2.png"));
		books.add(new BookDto("111-333-4444", "장길산", "책제목3", 30000, "좋은 책 3", "abc3.png"));
	}

	@Override
	public List<BookDto> list() {
		return books;
	}

	@Override
	public BookDto view(String isbn) {
		for(BookDto bd : books) {
			if(bd.getIsbn().equals(isbn)) {
				return bd;
			}
		}
		return null;
	}

	@Override
	public void register(BookDto bookDto) {
		books.add(bookDto);
	}

	@Override
	public void delete(String isbn) {
		for(BookDto bd : books) {
			if(bd.getIsbn().equals(isbn)) {
				books.remove(bd);
				return;
			}
		}
	}

	@Override
	public void modify(BookDto bookDto) {
		for(BookDto bd : books) {
			if(bd.getIsbn().equals(bookDto.getIsbn())) {
				bd = bookDto;
				return;
			}
		}
	}
}
