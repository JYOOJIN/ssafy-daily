package com.ssafy.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.model.dto.MemberDto;
import com.ssafy.model.dto.MemberDto;

@Mapper
public interface MemberMapper {
	MemberDto login(Map<String, String> map) throws SQLException;
	void join(MemberDto memeber) throws SQLException;
}
