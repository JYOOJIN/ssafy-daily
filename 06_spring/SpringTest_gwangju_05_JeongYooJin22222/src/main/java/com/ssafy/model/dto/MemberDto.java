package com.ssafy.model.dto;

public class MemberDto {

	private String no;
	private String memberId;
	private String memberName;
	private String password;
	private String email;
	private String mobile;
	private boolean isAdmin;
	
	
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	@Override
	public String toString() {
		return "MemberDto [no=" + no + ", memberId=" + memberId + ", memberName=" + memberName + ", password="
				+ password + ", email=" + email + ", mobile=" + mobile + ", isAdmin=" + isAdmin + "]";
	}
	
	
	
	
}
