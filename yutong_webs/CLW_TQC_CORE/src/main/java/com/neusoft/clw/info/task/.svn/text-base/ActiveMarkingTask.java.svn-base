package com.neusoft.clw.info.task; 

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.neusoft.clw.log.LogFormatter;



/**
 * 负责更新数据库中的服务器运行状态
 * param_name=本机ip地址  param_value=计划启动油耗分析时间 remark=activeTiime
 */
public class ActiveMarkingTask extends TimerTask {
	private static final String NAME = "ActiveMarkingTask";
	private static Logger LOGGER = Logger.getLogger(ActiveMarkingTask.class);
	
	private static ActiveMarkingTask instance = new ActiveMarkingTask();
	private static SimpleDateFormat  dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static String strLocalIP = "";
 
	private  int nCnt = 0;   // 表示连续标记了多少次
	
	private ActiveMarkingTask() {
	}

	public static ActiveMarkingTask getInstance() {
		return instance;
	}
	
	@Override
	public void run() {
		try {
			//LOGGER.info(LogFormatter.formatMsg(NAME, "状态标记", "开始"));
		    strLocalIP  = InetAddress.getLocalHost().getHostAddress();
			String strCurrentTime = dateFormat.format(new Date());

			// 更新标记为:本机IP+当前时间
			@SuppressWarnings("unused")
			String sqlUpdate = "update sys_param_config " 
			   + String.format(" set remark='%s' ", strCurrentTime)
			   + String.format(" where param_name='%s'", strLocalIP);
			
			//DBTool.executeUpdate(sqlUpdate);
			
			++nCnt;
			if (0 == nCnt%30) { //每标记30次,日志输出表示服务器状态正常
			    nCnt = 0;
			    LOGGER.info(LogFormatter.formatMsg(NAME, "状态标记", "完成 "+strLocalIP+" "+strCurrentTime ));
            }
			//LOGGER.debug(LogFormatter.formatMsg(NAME, "状态标记", "完成 " +strLocalIP+" "+strCurrentTime ));
			
		} catch (Exception e) {
			LOGGER.error(LogFormatter.formatMsg(NAME, "状态标记", "错误"), e);
		}
	}
	
}
