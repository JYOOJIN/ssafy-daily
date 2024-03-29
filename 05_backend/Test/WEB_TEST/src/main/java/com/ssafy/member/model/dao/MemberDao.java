package com.ssafy.member.model.dao;

import java.sql.SQLException;

import com.ssafy.member.model.dto.MemberDto;

public interface MemberDao {
	public MemberDto login(String userId,String userPassword) throws SQLException; //로그인
}
