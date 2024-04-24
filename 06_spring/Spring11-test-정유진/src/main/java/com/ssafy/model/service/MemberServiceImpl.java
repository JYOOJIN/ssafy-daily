package com.ssafy.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Member;
import com.ssafy.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	final MemberMapper memberMapper;

	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public Member login(Map<String, String> map) throws Exception {
		return memberMapper.login(map);
	}

	@Override
	public void join(Member memeber) throws Exception {
		memberMapper.join(memeber);
	}

}
