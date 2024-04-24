package com.ssafy.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.model.dto.Member;

@Mapper
public interface MemberMapper {
	Member login(Map<String, String> map) throws SQLException;
	void join(Member memeber) throws SQLException;
}
