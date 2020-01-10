package org.mybatis.plugin;

import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts(
	    {
	        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
	        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
	    }
	)
public class ExamplePlugin  implements  Interceptor {

	private static Logger logger=LoggerFactory.getLogger(ExamplePlugin.class);
	private Properties properties = new Properties();
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		logger.warn("before================");
		
		Object resultObject=invocation.proceed();
		logger.warn("after================");
		return resultObject;
	}

	
	/**
	 * 返回target的动态代理对象，代理方法 
	 * @see org.apache.ibatis.plugin.invoke
	 * 
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties=properties;
	}

}
