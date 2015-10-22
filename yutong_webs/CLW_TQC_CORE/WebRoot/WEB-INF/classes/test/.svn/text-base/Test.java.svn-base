package test;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.parents.dao.impl.ParentsDAO;
import com.neusoft.tqcpt.dao.TqcOilWearDao;
import com.neusoft.tqcpt.service.OilWearSQL;
import com.neusoft.tqcpt.util.CDate;

public class Test extends TestCase{

	public static void main(String[] args) throws Exception {
	
		/*List listData=new ArrayList();
		Map mapData1=new HashMap(),mapData2=new HashMap(),
		    mapData3=new HashMap(),mapResult=new HashMap();
		mapData1.put("CLW_TQC", "习近平");	
		mapData2.put("CLW_TQC", "李克强");
		mapData3.put("CLW_TQC", "张德江");
		
		listData.add(mapData1);
		listData.add(mapData2);
		listData.add(mapData3);				
		
		for(int i=0;i<listData.size();i++){
			  System.out.println("------(内)-------->>:"+listData);
              mapResult = (Map) listData.get(i);
              mapResult.put("CLW_TQC", "汤玉祥_"+i);        
		}*/
		
		System.out.println(CDate.getCurrentDateNoSplit());
	
	}
	
	public void test1() {	
		//String[] classXmlContexts = {"main.xml","config.xml","service.xml","dao.xml","clwapplication.xml"};
		String[] classXmlContexts = {"config.xml","service.xml","dao.xml"};
        ApplicationContext ac = new ClassPathXmlApplicationContext(classXmlContexts);		
		
		//------------------------------行程生成
		/*QuartzTripDAO qtdao = (QuartzTripDAO) ac.getBean("qtdao");
             try{
        	List<String> vehicleList = qtdao.getVehicleList();
    		if (vehicleList!= null&& vehicleList.size() > 0) {
    			for(String vin:vehicleList){
    				if(qtdao.gettrip(vin,sDate)>0){
    					System.out.println("------------>>:"+vin);
    					//QuartzTripThread newthread = new QuartzTripThread(sDate,vin,ac);
    					//newthread.getVinToTrip(vin, sDate);
    			    }	
    			}	
    		}
		}catch(Exception e) {
				e.printStackTrace();
			}*/	
		
		//-----------------------------油耗统计
	    /*TqcOilWearDao tqcOilDao =(TqcOilWearDao)ac.getBean("tqcOilWearDao");
		OilWearSQL oilWearSQL =(OilWearSQL)ac.getBean("oilWearSQL");
		//String sDate="2013-08-06";
		tqcOilDao.selectAllVehicleOilWear("2013-12-01","20131201");
		tqcOilDao.selectAllVehicleOilWear("2013-12-02","20131202");
		tqcOilDao.selectAllVehicleOilWear("2013-12-03","20131203");
		tqcOilDao.selectAllVehicleOilWear("2013-12-04","20131204");
		tqcOilDao.selectAllVehicleOilWear("2013-12-05","20131205");
		tqcOilDao.selectAllVehicleOilWear("2013-12-06","20131206");
		tqcOilDao.selectAllVehicleOilWear("2013-12-07","20131207");
		tqcOilDao.selectAllVehicleOilWear("2013-12-08","20131208");
		tqcOilDao.selectAllVehicleOilWear("2013-12-09","20131209");
		tqcOilDao.selectAllVehicleOilWear("2013-12-10","20131210");
		tqcOilDao.selectAllVehicleOilWear("2013-12-11","20131211");
		tqcOilDao.selectAllVehicleOilWear("2013-12-12","20131212");
		tqcOilDao.selectAllVehicleOilWear("2013-12-13","20131213");
		tqcOilDao.selectAllVehicleOilWear("2013-12-14","20131214");
		tqcOilDao.selectAllVehicleOilWear("2013-12-15","20131215");
		tqcOilDao.selectAllVehicleOilWear("2013-12-16","20131216");
		tqcOilDao.selectAllVehicleOilWear("2013-12-17","20131217");
		tqcOilDao.selectAllVehicleOilWear("2013-12-18","20131218");
		tqcOilDao.selectAllVehicleOilWear("2013-12-19","20131219");
		tqcOilDao.selectAllVehicleOilWear("2013-12-20","20131220");
		tqcOilDao.selectAllVehicleOilWear("2013-12-21","20131221");
		tqcOilDao.selectAllVehicleOilWear("2013-12-22","20131222");
		tqcOilDao.selectAllVehicleOilWear("2013-12-23","20131223");
		tqcOilDao.selectAllVehicleOilWear("2013-12-25","20131225");
		tqcOilDao.selectAllVehicleOilWear("2013-12-26","20131226");
		tqcOilDao.selectAllVehicleOilWear("2013-12-27","20131227");
		tqcOilDao.selectAllVehicleOilWear("2013-12-28","20131228");
		tqcOilDao.selectAllVehicleOilWear("2013-12-29","20131229");
		tqcOilDao.selectAllVehicleOilWear("2013-12-30","20131230");
		tqcOilDao.selectAllVehicleOilWear("2013-12-31","20131231");
		
		tqcOilDao.selectAllVehicleOilWear("2014-01-01","20140101");
		tqcOilDao.selectAllVehicleOilWear("2014-01-02","20140102");
		tqcOilDao.selectAllVehicleOilWear("2014-01-03","20140103");
		tqcOilDao.selectAllVehicleOilWear("2014-01-04","20140104");
		tqcOilDao.selectAllVehicleOilWear("2014-01-05","20140105");*/
		
        System.out.println("-------------------------------->>:end");
		
		//-----------------发短信测试-----------------		
		/*Up_InfoContent uhc=new Up_InfoContent();
		uhc.setTerminal_id("LZYTBTD6261002878");		
		ZsptFtlyInfo zfi=new ZsptFtlyInfo();
		zfi.setAddOill("100");
		zfi.setVinCode("LZYTBTD6261002878");		
		uhc.setZsptFtlyInfo(zfi);*/
		
		//-----------------------------发车与可客流统计 
		/*TqcStatisticDao tqcStatisticDao =(TqcStatisticDao)ac.getBean("tqcStatisticDao");
		TqcStatisticSQL tqcStatisticSQL =(TqcStatisticSQL)ac.getBean("tqcStatisticSQL");
		
		String sDate="2013-12-06";
		//tqcStatisticSQL.delSendPassengerStatByDay(sDate);		
		tqcStatisticDao.sendPassengerStatistic("LZYTCTD6391040122", sDate);
		//tqcStatisticDao.selectAllVehicleStatistic();*/
	   
		//-----------------------------查询
        /*OilWearSQL oilWearSQL =(OilWearSQL)ac.getBean("oilWearSQL");
		String sDate="2013-12-12";        
        oilWearSQL.synFromInfoToAlarm(sDate);*/
        
        
        ParentsDAO parentsDAO = (ParentsDAO) ac.getBean("parentsDAO"); 
	   
        System.out.println(parentsDAO.getVehicleCode("LZYTAGDS3A1000010"));
	}
}
