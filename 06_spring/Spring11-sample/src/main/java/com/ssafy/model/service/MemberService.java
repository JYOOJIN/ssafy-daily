package com.ssafy.model.service;

import java.util.Map;

import com.ssafy.model.dto.Member;

public interface MemberService {
	Member login(Map<String, String> map) throws Exception;
	void join(Member memeber) throws Exception;
}
