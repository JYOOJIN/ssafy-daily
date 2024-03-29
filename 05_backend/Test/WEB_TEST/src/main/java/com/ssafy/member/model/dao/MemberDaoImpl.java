package com.ssafy.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.util.DBUtil;

public class MemberDaoImpl implements MemberDao{


	//singleton pattern
	private static MemberDao instance=new MemberDaoImpl();
	private MemberDaoImpl() {}
	public static MemberDao getInstance() {
		return instance;
	}
	
	@Override
	public MemberDto login(String userId, String userPassword) throws SQLException {
		
		//상세조회랑 똑같다. 아이디와 비밀번호가 맞으면 회원정보를 MemberDto에 담고 return
		
		MemberDto memberDto=new MemberDto();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {

			conn=DBUtil.getConnection();
			
			StringBuilder sql=new StringBuilder();
			sql.append("select * from members where user_id=? and user_password=?");
			
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, userPassword);
			
			rs=pstmt.executeQuery(); //select의 결과
			
			if(rs.next()) {
				
				memberDto=new MemberDto();
				
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserPassword(rs.getString("user_password"));
				memberDto.setEmailId(rs.getString("email_id"));
				memberDto.setEmailDomain(rs.getString("email_domain"));
				memberDto.setJoinDate(rs.getString("join_date"));
				
				return memberDto;
			
			}
			
		}finally {
			DBUtil.close(conn,pstmt,rs);
		}

		return null;
	}

}
