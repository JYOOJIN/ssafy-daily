package com.ssafy.ws;

public class Reader {
	
	private Book book;
	
	public void setBook(Book book) {
		this.book=book;
	}
	
	public void read() {
		System.out.println(book.getInfo()+"을(를) 열심히 읽습니다.");
	}
}
