package com.yutong.clw.dao.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.yutong.clw.beans.cl.CoreCfgBean;
import com.yutong.clw.sysboot.SpringBootStrap;
import com.yutong.clw.utils.DateUtil;
import com.yutong.clw.utils.LogFormatter;

public class TransactionDaoServers {
	
private Logger log = LoggerFactory.getLogger(TransactionDaoServers.class);
	
	private final static String NAME = "TransactionDaoServers";
	
	private TransactionDao transactionDao;
	
	private TerminalDAO terminalDAO;
	
	public void setTerminalDAO(TerminalDAO terminalDAO) {
		this.terminalDAO = terminalDAO;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}
	
	@SuppressWarnings("unchecked")
	public boolean selectRunState(String core_id,int sendvalid){
		try{
			ActiveCoreDAO activeCoreDAO = (ActiveCoreDAO) SpringBootStrap.getInstance().getBean("activeCoreDAO");
			List list = activeCoreDAO.selectRunState(core_id);
			if(list!=null&&list.size()>0){
				return true;
			}else{
				List mainList = transactionDao.selectAllRunState();
				for(int j=0;j<mainList.size();j++){
					CoreCfgBean core = (CoreCfgBean) mainList.get(j);
					if(core.getMain_flag().equals("0")){
						continue;
					}else{
						String active_time = DateUtil.getDateByDiscreMinuteWithNow(core.getActive_time(), sendvalid);
						String sys_time = terminalDAO.getSysTime();
						if(DateUtil.changeStringTo12Date(active_time).before(DateUtil.changeStringTo12Date(sys_time))){
							transactionDao.updateRunState("0", core.getCore_id());
						}else{
							return false;
						}
					}
				}
				transactionDao.updateRunState("1",core_id);
				return true;
			}
			
		}catch(SQLException e){
			log.error(LogFormatter.formatMsg(NAME, "selerror", e.getMessage()));
			return false;
		}catch(DataAccessException e){	
			log.error(LogFormatter.formatMsg(NAME, "selerror", "执行操作时出现 WAIT 超时"));
			return false;
		} catch (ParseException e) {
			log.error(LogFormatter.formatMsg(NAME, "转换Date异常："+e.getMessage()));
			return false;
		}
	}
	
	/**
	 * @param state
	 * @param loginname
	 * @param serverid
	 */
	public void updateRunState(String main_flag, String core_id){
		transactionDao.updateRunState(main_flag,core_id);
	}
	
}
