package com.hdilhara.clientservice.model;

public class User {
	String user_name;
	String[] authorities;
	long exp;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String[] getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}
	public long getExp() {
		return exp;
	}
	public void setExp(long exp) {
		this.exp = exp;
	}
	
	
}
