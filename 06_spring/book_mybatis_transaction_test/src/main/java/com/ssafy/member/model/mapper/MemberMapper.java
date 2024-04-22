package com.ssafy.member.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {
	int idCheck(String userId) throws SQLException;
	void joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(@Param("userId") String userId,@Param("userPwd") String userPwd) throws SQLException;
}
