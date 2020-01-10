package com.test.mybatis_helloworld;

import java.util.List;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.PageRowBounds;
import com.test.mybatis_helloworld.entity.SysUser;
import com.test.mybatis_helloworld.mapper.SysUserMapper;

public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);

	private SqlSessionFactory sqlSessionFactory;

	private SqlSession sqlSession;

	@Before
	public void init() {
		DruidDataSource dataSource = new DruidDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://192.168.233.133:3306/develop");

		dataSource.setUsername("root");
		dataSource.setPassword("root");

		TransactionFactory transactionFactory = new JdbcTransactionFactory();

		Environment environment = new Environment("development",
				transactionFactory, dataSource);

		Configuration configuration = new Configuration(environment);

		PageInterceptor pageInterceptor = new PageInterceptor();
		configuration.addInterceptor(pageInterceptor);
		configuration.addMapper(SysUserMapper.class);

		sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

		sqlSession = sqlSessionFactory.openSession();
	}

	@Test
	public void helloworld() {

		SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
		SysUser sysUser=new SysUser();
		sysUser.setId(1);
		sysUserMapper.findById(sysUser);
		
	}

	@Test
	public void pluginsTest() {
		SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
		SysUser sysUser=new SysUser();
		sysUser.setId(1);
		
		PageRowBounds pageRowBounds=new PageRowBounds(2,10);
		
		List<SysUser> userList= sysUserMapper.findList(sysUser, pageRowBounds);
		logger.warn("total data:"+sysUser);
		logger.warn("total records:"+pageRowBounds.getTotal());

	}

}
