package com.ssafy.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.model.dto.LoginHistoryDto;
import com.ssafy.model.dto.Member;
import com.ssafy.model.dto.Product;

@Mapper
public interface MemberMapper {
	Member login(Map<String, String> map) throws SQLException;
	void join(Member memeber) throws SQLException;
	Member getMember(String memberId) throws SQLException;
	void updateMember(Member member) throws SQLException;
	List<LoginHistoryDto> listMember() throws SQLException;
	
}
