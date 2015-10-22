package test;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.tqcpt.dao.TqcOilWearDao;
import com.neusoft.tqcpt.service.OilWearSQL;


public class TestHistory extends TestCase{
	public void test1() {	
		//String[] classXmlContexts = {"main.xml","config.xml","service.xml","dao.xml","clwapplication.xml"};
		String[] classXmlContexts = {"config.xml","service.xml","dao.xml"};
        ApplicationContext ac = new ClassPathXmlApplicationContext(classXmlContexts);	
		
		//-----------------------------油耗统计
	    TqcOilWearDao tqcOilDao =(TqcOilWearDao)ac.getBean("tqcOilWearDao");
		OilWearSQL oilWearSQL =(OilWearSQL)ac.getBean("oilWearSQL");
		//String sDate="2013-08-06";
		tqcOilDao.selectAllVehicleOilWear("2013-11-25","20131125");
		//tqcOilDao.selectAllVehicleOilWear("2013-12-07","20131207");	
        System.out.println("-------------------------------->>:end");

		
	
        
	
	}
}
