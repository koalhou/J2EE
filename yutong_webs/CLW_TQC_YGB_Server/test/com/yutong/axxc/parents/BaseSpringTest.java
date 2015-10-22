package com.yutong.axxc.parents;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yutong.axxc.tqc.common.CachedCommon;
import com.yutong.axxc.tqc.mapper.MybatisDAO;
import com.yutong.axxc.tqc.service.EtagService;

/**
 * 
 */
public class BaseSpringTest
{
    protected static Logger logger = LoggerFactory.getLogger(BaseSpringTest.class);

    protected static ApplicationContext ac;
	protected static MybatisDAO dao;

    @BeforeClass
    public static void before() {

        ac = new ClassPathXmlApplicationContext(new String[] {
                "applicationContext-datasource.xml",
                "applicationContext-memcached.xml",
                "applicationContext-rest-services.xml" });
        dao=(MybatisDAO) ac.getBean("mybatisDAO");
    }
   
    @Test
	public void testSpring() {
    	logger.info("{}",dao);
    	EtagService etagService=(EtagService)ac.getBean("etagService");
    	String key=CachedCommon.CACHED_VERSION+"138";
		boolean b=etagService.put(key, 30, "test");
    	Object obj=etagService.get("version_138");
    	logger.info("{}",obj);
	}
    
    protected void printResponse(Response rs) {
    	int status = rs.getStatus();
		Object ent=rs.getEntity();
		logger.info("{},{}",status,ent);
	}
  
}
