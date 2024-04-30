package com.ssafy.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.MemberDto;
import com.ssafy.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	final MemberMapper memberMapper;
	
	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public MemberDto login(Map<String, String> map) throws Exception {
		return memberMapper.login(map);
	}
	
	
}
