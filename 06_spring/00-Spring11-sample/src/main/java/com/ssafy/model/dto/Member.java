package com.ssafy.model.dto;

public class Member {
	private String id;
	private String name;
	private String password;
	private String joinDate;
	
	public Member(String id, String name, String password, String joinDate) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.joinDate = joinDate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	
}
