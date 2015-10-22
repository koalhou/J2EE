package com.neusoft.parents.cachemanager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.dao.impl.TransactionDao;
import com.neusoft.clw.vncs.service.ActiveCoreService;
import com.neusoft.parents.bean.NoticeBean;
import com.neusoft.parents.dao.IParentsDAO;
import com.neusoft.parents.dao.impl.ParentsDAO;

public class PreLineAlarm {
	
	private Logger log = LoggerFactory.getLogger(PreLineAlarm.class);
	
	private final static String NAME = "<PreLineAlarm>";
	
	private IParentsDAO parentsDAO;
	
	private TransactionDao transactionDao;
	
	public static void main(String[] args) {
		
		PreLineAlarm al = new PreLineAlarm();
		if(al.parentsDAO == null ){
			ParentsDAO da = new ParentsDAO();
			da.setJdbcTemplate(new JdbcTemplate());
			
			al.setParentsDAO(da);
		}
		al.init();
		
	}
	
	/**
	 * 定时任务，将CLW_TQC_EMP_PRELINE_T表内的数据同步到设置闹钟表里
	 */
	public  synchronized void init(){
		
		String reportServer = null; 
		transactionDao = (TransactionDao) SpringBootStrap.getInstance().getBean("transactionDao");
		boolean b = false;
    	try
        {
    		int coreActive = Integer.parseInt(Config.props.getProperty("core.active.time"));
    		reportServer = transactionDao.getReportServer();    		
    		if (reportServer.equals(Constant.CORE_ID)) {// 是指定的报表计算服务器
				b = true;
			} else {
				// 是否为主核心，为了保证主核心业务的正常，核心数量大于1时主核心不参与报表计算。
				ActiveCoreService acs = ActiveCoreService.getInstance();
				b = acs.getMainCore(Constant.CORE_ID, coreActive);
				// 获取活跃核心数量
				int corenum = transactionDao.queryReportServer(coreActive);
				if (corenum == 1) {
					b = true;
				} else {
					int rcore = transactionDao.queryLiveReportS(coreActive,reportServer);
					if (rcore == 0 && !b) {// 非主核心设置自己为报表服务器
						transactionDao.setReportServer(Constant.CORE_ID);
						b = true;
					}
					b = false;
				}
			}
    		
    		log.info(NAME+"判断是本服务器是否为【员工版】消息推送服务器"+b);
			if (!b) {
				log.info(NAME+"本机不是【员工版】消息推送服务器。");
				return;
			}
			log.info("<PreLineAlarm>开始同步数据到闹钟表里");
			List<NoticeBean> list =  parentsDAO.getGroupRouteSite();
			
			
			//先删除所有的数据
			int num = parentsDAO.deletePreLineByRouteAndSite();
			
			for(NoticeBean no : list){
				//通过no.getRoute_id(),no.getSite_id() 先删除表内数据  包括：按距离和按时间
				//int num = parentsDAO.deletePreLineByRouteAndSite(no.getRoute_id(),no.getSite_id());
				
				
				//按距离   添加
				float d=(float) 3.0;
				
				//for(double f = 3.0; f >=0.0  )
				while(d >= 0.0){
					int num2 = parentsDAO.insertPreLineByRouteAndSite(no.getRoute_id(),no.getSite_id(), d);
					d=(float) (d-0.1);
				}
				//按时间  添加
				int t=600;
				//int num3 = parentsDAO.insertPreLineByTime(no.getRoute_id(),no.getSite_id(), t);
				List<NoticeBean> list1 =  parentsDAO.getGroupLineByTime(no.getRoute_id(),no.getSite_id(), t);
				for(NoticeBean to : list1){
					int num3 = parentsDAO.insertPreLineByTime(to);
				}
			}
			
			log.info("<PreLineAlarm>结束同步数据到闹钟表里");
		}catch (Exception e)
	    {   
	        log.error(NAME+"预运行轨迹更新数据异常：", e);
	    }
		

		
		
	}
	

	
	public void setParentsDAO(IParentsDAO parentsDAO)
    {
        this.parentsDAO = parentsDAO;
    }

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}
	
	
	

}
