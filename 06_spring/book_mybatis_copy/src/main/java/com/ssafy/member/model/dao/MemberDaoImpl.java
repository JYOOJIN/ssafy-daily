package com.ssafy.member.model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssafy.member.model.MemberDto;
import com.ssafy.util.SqlMapConfig;

public class MemberDaoImpl implements MemberDao{
	
	private final String NAMESPACE = "com.ssafy.member.model.dao.MemberDao.";

	@Override
	public int idCheck(String userId) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			return sqlSession.selectOne(NAMESPACE + "idCheck", userId);
		}
	}

	@Override
	public void joinMember(MemberDto memberDto) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.insert(NAMESPACE + "joinMember", memberDto);
			sqlSession.commit();
		}
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
	        Map<String, String> params = new HashMap<>();
	        params.put("userId", userId);
	        params.put("userPwd", userPwd);
	        return sqlSession.selectOne(NAMESPACE + "loginMember", params);
	    }
	}
	
	

}
