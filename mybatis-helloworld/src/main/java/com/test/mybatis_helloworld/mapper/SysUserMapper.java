package com.test.mybatis_helloworld.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.test.mybatis_helloworld.entity.SysUser;

public interface SysUserMapper {
	
	@Select(value={"select  * from sys_user"})
	public List<SysUser> findList(SysUser sysUser,RowBounds rowBounds);
	
	
	@Select(value="select  * from sys_user where id=#{user.id}")
	public List<SysUser> findById(@Param("user")SysUser sysUser);

}
