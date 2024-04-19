package com.ssafy.book.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ssafy.book.model.BookDto;
import com.ssafy.util.DBUtil;

@Repository
@Primary
public class BookDaoImpl implements BookDao {

	private DataSource dataSource;
	private DBUtil dbUtil;
	
	public BookDaoImpl(DataSource dataSource, DBUtil dbUtil) {
		this.dataSource = dataSource;
		this.dbUtil = dbUtil;
	}
	
	@Override
	public List<BookDto> list() throws SQLException {
		List<BookDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM book \n");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookDto bookDto = new BookDto();
				bookDto.setIsbn(rs.getString("isbn"));
				bookDto.setTitle(rs.getString("title"));
				bookDto.setAuthor(rs.getString("author"));
				bookDto.setPrice(rs.getInt("price"));
				bookDto.setDescription(rs.getString("description"));
				bookDto.setImg(rs.getString("img"));
				
				list.add(bookDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public BookDto view(String isbn) throws SQLException {
		BookDto bookDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM book\n");
			sql.append("WHERE isbn = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bookDto = new BookDto();
				bookDto.setIsbn(rs.getString("isbn"));
				bookDto.setTitle(rs.getString("title"));
				bookDto.setAuthor(rs.getString("author"));
				bookDto.setPrice(rs.getInt("price"));
				bookDto.setDescription(rs.getString("description"));
				bookDto.setImg(rs.getString("img"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		System.out.println(bookDto);
		return bookDto;
	}

	@Override
	public void register(BookDto bookDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO BOOK(isbn, title, author,price, description, img) \n");
			sql.append("VALUES (?,?,?,?,?,?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bookDto.getIsbn());
			pstmt.setString(2, bookDto.getTitle());
			pstmt.setString(3, bookDto.getAuthor());
			pstmt.setInt(4, (int) bookDto.getPrice());
			pstmt.setString(5, bookDto.getDescription());
			pstmt.setString(6, bookDto.getImg());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void delete(String isbn) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM BOOK \n");
			sql.append("WHERE isbn = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, isbn);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void modify(BookDto bookDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE BOOK \n");
			sql.append("SET title = ?, author = ?, price = ?, description=?,img =?\n");
			sql.append("WHERE isbn = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bookDto.getTitle());
			pstmt.setString(2, bookDto.getAuthor());
			pstmt.setInt(3, (int) bookDto.getPrice());
			pstmt.setString(4, bookDto.getDescription());
			pstmt.setString(5, bookDto.getImg());
			pstmt.setString(6, bookDto.getIsbn());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

}
