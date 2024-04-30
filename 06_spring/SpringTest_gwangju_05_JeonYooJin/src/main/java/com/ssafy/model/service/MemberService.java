package com.ssafy.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.model.dto.LoginHistoryDto;
import com.ssafy.model.dto.Member;
import com.ssafy.model.dto.Product;

public interface MemberService {
	Member login(Map<String, String> map) throws Exception;
	void join(Member memeber) throws Exception;
	Member getMember(String memberId) throws Exception;
	void updateProduct(Member memberDto) throws Exception;
	List<LoginHistoryDto> listMember() throws Exception;
}
