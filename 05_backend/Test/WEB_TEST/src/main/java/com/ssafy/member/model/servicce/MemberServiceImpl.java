package com.ssafy.member.model.servicce;

import com.ssafy.member.model.dao.MemberDao;
import com.ssafy.member.model.dao.MemberDaoImpl;
import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.product.model.dao.ProductDao;
import com.ssafy.product.model.dao.ProductDaoImpl;

public class MemberServiceImpl implements MemberService{

	//singleton Pattern
	private static MemberService instance = new MemberServiceImpl();
	private MemberServiceImpl() {}
	public static MemberService getInstance() {
		return instance;
	}
	
	//서비스에서 Dao(singleton pattern) 사용하기 위한 객체 생성: memberDao
	private MemberDao memberDao=MemberDaoImpl.getInstance();
	
	@Override
	public MemberDto login(String userId, String userPassword) throws Exception {
		return memberDao.login(userId, userPassword);
	}

}
