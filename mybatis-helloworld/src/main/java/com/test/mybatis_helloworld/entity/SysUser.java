package com.test.mybatis_helloworld.entity;



import java.io.Serializable;

import javax.annotation.Generated;

import java.util.Date;

/**
 * The persistent class for the sys_user database table.
 * 
 */
public class SysUser extends BaseEntity{
	private static final long serialVersionUID = 1L;



	private String loginName;

	private String password;

	private String phone;

	private String realName;


	private String userName;

	public SysUser() {
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}