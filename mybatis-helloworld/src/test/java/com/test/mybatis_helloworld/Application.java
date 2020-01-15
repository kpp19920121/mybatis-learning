package com.test.mybatis_helloworld;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.LocalCacheScope;
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
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

		
		//1.分页参数设置
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties properties=new Properties();
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("params", "pageNum=pageNumKey;pageSize=pageSizeKey;");
		pageInterceptor.setProperties(properties);
		configuration.addInterceptor(pageInterceptor);
		//configuration.addMapper(SysUserMapper.class);
		configuration.addMappers("com.test");
		
		//开启一级缓存，默认以及缓存无法关闭，可以选择statment级别或者session级别
		configuration.setLocalCacheScope(LocalCacheScope.SESSION);
		//开启二级缓存
		configuration.setCacheEnabled(Boolean.TRUE);

		sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

		sqlSession = sqlSessionFactory.openSession();
	}

	@Test
	public void helloworld() {

		SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
		SysUser sysUser=new SysUser();
		sysUser.setId(1);
		sysUserMapper.findById(sysUser);
		sysUserMapper.findById(sysUser);
		sqlSession.close();
		sqlSession=sqlSessionFactory.openSession();
		sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
		sysUser=new SysUser();
		sysUser.setId(1);
		sysUserMapper.findById(sysUser);
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
	
	
	@Test
	public void rowBoundsTest(){
		SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
		
		//1.获取指定结果集
//		List<SysUser> userList= sysUserMapper.findList(new SysUser(), new RowBounds(1, 10));
//		System.out.println(userList);
		//2.获取分页总页数
		PageRowBounds rowBounds = new PageRowBounds(1, 10);
		List<SysUser> totalUserList= sysUserMapper.findList(new SysUser(), rowBounds);
		long total = rowBounds.getTotal();
		System.out.println(total);
		System.out.println(totalUserList.size());
	}

	@Test
	public void pageHelperTest(){
		Page<SysUser> page=PageHelper.<SysUser>startPage(1, 10);
		SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
		List<SysUser> totalUserList= sysUserMapper.findList2(new SysUser());
		System.out.println(totalUserList);
		System.out.println(page);
	}
	
	
	@Test
	public void offsetPage(){
		Page<SysUser> page=PageHelper.<SysUser>offsetPage(0, 10);
		SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
		List<SysUser> totalUserList= sysUserMapper.findList2(new SysUser());
		System.out.println(totalUserList.getClass());
		System.out.println(totalUserList);
		System.out.println(page.getResult());
	}
	
	@Test
	public void findByPageCondition(){
		SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
		List<SysUser> totalUserList= sysUserMapper.findByPageCondition(new SysUser(), 1, 10);
		System.out.println(totalUserList.getClass());
		System.out.println(totalUserList);
		//System.out.println(page.getResult());
	}
	
	
	
	@Test
	public void test1() throws FileNotFoundException{
		//InputStream is =new FileInputStream(new File(""));
		BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream(new File("")));
		InputStreamReader InputStreamReader=new InputStreamReader(new BufferedInputStream(new FileInputStream(new File(""))));
}
}

