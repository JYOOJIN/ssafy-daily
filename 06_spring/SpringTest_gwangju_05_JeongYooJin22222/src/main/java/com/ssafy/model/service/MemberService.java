package com.ssafy.model.service;

import java.util.Map;

import com.ssafy.model.dto.MemberDto;

public interface MemberService {
	
	MemberDto login(Map<String, String> map) throws Exception;
}
