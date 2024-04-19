package com.ssafy.book.model;

public class BookDto {
	private String isbn;
	private String title;
	private String author;
	private long price;
	private String description;
	private String img;

	public BookDto(String isbn, String author, String title, long price, String description, String img) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.description = description;
		this.img = img;
	}

	public BookDto() {
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "BookDto [ISBN=" + isbn + ", title=" + title + ", author=" + author + ", price=" + price
				+ ", description=" + description + ", img=" + img + "]";
	}

}
