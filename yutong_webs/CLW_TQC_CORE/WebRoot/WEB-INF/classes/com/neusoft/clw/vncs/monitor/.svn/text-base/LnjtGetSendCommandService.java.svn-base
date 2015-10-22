package com.neusoft.clw.vncs.monitor;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.vncs.inside.msg.InsideMsgFactory;
import com.neusoft.clw.vncs.inside.msg.req.SendCmdReq;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;

/**
 * @author <a href="mailto:zhangmq@neusoft.com">tianmc </a>
 * @version $Revision 1.0 $ 2010-5-25 上午11:07:35
 */
public class LnjtGetSendCommandService {
	private Logger log = LoggerFactory.getLogger(LnjtGetSendCommandService.class);
	private SendInfoList sendInfo = SendInfoList.getInstance();
    private String name = "SMFDao";
    private Timer timer = new Timer("getDBTimer");
    private DbTask task;
    private JdbcTemplate jdbcTemplate;
    private static final String lnjt_cr_send_command = "select ID,TERMINAL_ID,REQUESTTYPE,QUERYDATE,CMDID,OEMCODE,COMMADDR,IF_SEND,CMDSEQ,CMDARGUS,OHTER_INFO,VERSION from lnjt_cr_send_command where if_send='1001'";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getName() {
        return name;
    }
    
    public void init() {
         try {       
               start();
        } catch (Throwable t) {
            log.error(LogFormatter.formatMsg("LnjtSendCommandDao",
                    "Lnjt LnjtSendCommandDao Module start failed."), t);
        }
    }
    public void destroy() throws Exception {
     }
    public void start() throws Exception {
    	task = new DbTask();
	    timer.schedule(task, 0, 1000);
	    log.info(LogFormatter.formatMsg("LnjtSendCommandDao", "start the  timer task."));
    }
    class DbTask extends TimerTask {

        @SuppressWarnings("unchecked")
		@Override
        public void run() {
            try {
                synchronized (this) {
                	List list=jdbcTemplate.queryForList(lnjt_cr_send_command);
                	Iterator it = list.iterator();
                	Map map;
            		while(it.hasNext()){
            			CmdBean cmdbean=new CmdBean();
            			map = (Map)it.next();           			
            			String ss=(String)map.get("ID");
            			log.info("dao5555555555:"+ss);        			
            			cmdbean.setID((String)map.get("ID"));
						cmdbean.setTERMINAL_ID(	(String)map.get("TERMINAL_ID"));
						cmdbean.setREQUESTTYPE((String)map.get("REQUESTTYPE"));
						cmdbean.setCMDID((String)map.get("CMDID"));
						cmdbean.setOEMCODE((String)map.get("OEMCODE"));
						cmdbean.setCOMMADDR((String)map.get("COMMADDR"));
						cmdbean.setCMDARGUS((String)map.get("CMDARGUS"));
						cmdbean.setCMDSEQ(InsideMsgUtils.getTerminalSeq());
						cmdbean.setVGSEQ(InsideMsgUtils.getSeq());
						SendCmdReq send=  (SendCmdReq) (InsideMsgFactory.createSendCmd(cmdbean)) ;
						byte[] sendDate=send.getBytes();
						SendBean sendBean=new SendBean(cmdbean,sendDate,"admin");
						sendInfo.add(sendBean,"SENDCMD");							
            		}  
					 
            		log.info(LogFormatter.formatMsg("LnjtGetSendCommandService", "start the  timer task."));
                }
            } catch (Throwable t) {
                cancel();
                log.info(LogFormatter.formatMsg("LnjtGetSendCommandService", "timer task."+t.getMessage()));
            }
        }
    }
}
