package com.test.mybatis_helloworld.entity;



import java.io.Serializable;
import java.util.Date;

public class SysRole extends BaseEntity {
	private static final long serialVersionUID = 1L;


	private String roleName;


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	

}