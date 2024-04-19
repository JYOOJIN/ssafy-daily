package com.ssafy.book.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssafy.book.model.BookDto;
import com.ssafy.util.SqlMapConfig;

public class BookDaoImpl implements BookDao{

	private final String NAMESPACE = "com.ssafy.book.model.dao.BookDao.";

	//전체 목록 조회
	@Override
	public List<BookDto> list() throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			return sqlSession.selectList(NAMESPACE + "list");
		}
	}

	//상세 도서 조회
	@Override
	public BookDto view(String isbn) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			return sqlSession.selectOne(NAMESPACE + "view", isbn);
		}
	}

	//도서 등록
	@Override
	public void register(BookDto bookDto) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.insert(NAMESPACE + "register", bookDto);
			sqlSession.commit();
		}
	}

	//도서 삭제
	@Override
	public void delete(String isbn) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.delete(NAMESPACE + "delete", isbn);
			sqlSession.commit();
		}
	}

	//도서 수정
	@Override
	public void modify(BookDto bookDto) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.update(NAMESPACE + "modify", bookDto);
			sqlSession.commit();
		}
		
	}
	
	
	
	

}
