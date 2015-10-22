package com.neusoft.parents.newsmanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.dao.impl.TransactionDao;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.clw.vncs.service.ActiveCoreService;
import com.neusoft.parents.bean.Rules;
import com.neusoft.parents.bean.News;
import com.neusoft.parents.bean.StuSiteNote;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.dao.IParentsDAO;
import com.neusoft.parents.dao.impl.ParentsDAO;
import com.neusoft.parents.pushmessage.domain.BaseNotification;
import com.neusoft.parents.pushmessage.domain.PushMessageCls;
import com.neusoft.parents.pushmessage.manager.PushMsgBuffer;
import com.neusoft.parents.service.ParentsBuildSQL;
import com.neusoft.parents.utils.JacksonUtils;
import com.neusoft.tqcpt.dao.TqcStatisticDao;
import com.neusoft.tqcpt.service.TqcStatisticSQL;

public class NewsManager {
	private Logger log = LoggerFactory.getLogger(NewsManager.class);
	private static final String NAME = "<NewsManager>";
	public static boolean runflag = true;
	private IParentsDAO parentsDAO;
	private TransactionDao transactionDao;

	public static void main(String[] args){
		 System.out.println("111111111111111111");
		 
		 new NewsManager().init();
	     System.out.println("222222222222222");
	}

	public void init(){
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
    		log.info(NAME+"判断是本服务器是否为【员工版】公告推送服务器"+b);
			if (!b) {
				log.info(NAME+"本机不是【员工版】公告推送服务器。");
				return;
			}
			
			log.info(NAME+"本次公告推送开始！runflag:" + runflag);
			if (runflag) {			
				runflag = false;
				this.log.info("公告推送开始");
				List<News> newsList = getNewsNotes();
				if(newsList != null && newsList.size() > 0){
					for (News eachnewsList : newsList)
					 {
						//先将标志位设置为1
						DBBuffer.getInstance().add(ParentsBuildSQL.getInstance().buildUpdateNewsLog(eachnewsList));
						List<News> str = parentsDAO.getEmpCode();
						for(News str1:str)
						{
							eachnewsList.setEmp_code(str1.getEmp_code());
							pushMsg(eachnewsList);
						} 
					 }
				}
				else{
					log.info("没有要推送的公告");
				}
				this.log.info("公告推送结束 runflag:" + runflag);
				runflag = true;
			}
        }
		  	
	    catch (Exception e)
	    {   
	    	runflag = true;
	        log.error(NAME+"公告推送异常：", e);
	    }

	}
	
	public List<News> getNewsNotes(){
		List<News> ret = null;
		List<News> list = parentsDAO.getNewsList();		       
		if(list != null && list.size()>0){
			ret = list;
		}
		return ret;
	}
	/**
	 * 新闻推送
	 */
	public void pushMsg(News eachnewsList){       
		PushMessageCls pushMessageBean = new PushMessageCls();
		int id = parentsDAO.getPushCheckRecordID();    
		pushMessageBean.setId(id);
		pushMessageBean.setRule_id("01");    //02厂内推送 03厂外推送 01新闻推送
		pushMessageBean.setUser_code(eachnewsList.getEmp_code());
		
		BaseNotification content = new BaseNotification();
		content.setNews_type(eachnewsList.getNews_type());
		content.setNews_id(eachnewsList.getNews_id());
		content.setNews_title(eachnewsList.getNews_title());
		content.setNews_summary(eachnewsList.getNews_summary());
		content.setNews_time(DateUtil.date2string(eachnewsList.getNews_date(),"yyyyMMddHHmmss"));
		/*try
		{
			String msgJson = JacksonUtils.toJson(content);
			pushMessageBean.setContent(msgJson);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}*/
		pushMessageBean.setContent(content);
		// 将推送内容放入到推送缓冲池中
		PushMsgBuffer.getInstance().add(pushMessageBean);
		// 将推送信息存储到数据库中
		//DBBuffer.getInstance().add(ParentsBuildSQL.getInstance().buildInsertPushInfo(pushMessageBean));//liuja先删除掉，之后要加上。推送记录
		/*try
		{
			log.info("上行推送的json...."+JacksonUtils.toJson(pushMessageBean));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}*/
	}

	public IParentsDAO getParentsDAO() {
		return parentsDAO;
	}

	public void setParentsDAO(IParentsDAO parentsDAO) {
		this.parentsDAO = parentsDAO;
	}
	
	
}
