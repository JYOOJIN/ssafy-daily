package com.ssafy.member.model.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.book.model.mapper.BookMapper;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper memberMapper;

	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper=memberMapper;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return memberMapper.idCheck(userId);
	}

	@Override
	@Transactional
	public void joinMember(MemberDto memberDto) throws Exception {
		memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws Exception {
		return memberMapper.loginMember(userId, userPwd);
	}

}
