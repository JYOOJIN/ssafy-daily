package com.ssafy.model.dto;

public class LoginHistoryDto {
	private int logId;
	private String memberId;
	private String loginTime;
	private String logoutTime;
	
	
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	
	
	@Override
	public String toString() {
		return "LoginHistoryDto [logId=" + logId + ", memberId=" + memberId + ", loginTime=" + loginTime
				+ ", logoutTime=" + logoutTime + "]";
	}
	
	
	
	
}
