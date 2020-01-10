package com.test.mybatis_helloworld.entity;



import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the sys_menu database table.
 * 
 */
public class SysMenu extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	private String column_11;


	private String isShow;

	private String menuName;

	private String menuPath;

	private int parentId;

	private String permission;


	public SysMenu() {
	}


	public String getColumn_11() {
		return column_11;
	}


	public void setColumn_11(String column_11) {
		this.column_11 = column_11;
	}


	public String getIsShow() {
		return isShow;
	}


	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}


	public String getMenuName() {
		return menuName;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public String getMenuPath() {
		return menuPath;
	}


	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}


	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public String getPermission() {
		return permission;
	}


	public void setPermission(String permission) {
		this.permission = permission;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}