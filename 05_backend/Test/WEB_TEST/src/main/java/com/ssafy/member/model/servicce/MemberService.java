package com.ssafy.member.model.servicce;

import com.ssafy.member.model.dto.MemberDto;

public interface MemberService {
	public MemberDto login(String userId,String userPassword) throws Exception; //로그인

}
