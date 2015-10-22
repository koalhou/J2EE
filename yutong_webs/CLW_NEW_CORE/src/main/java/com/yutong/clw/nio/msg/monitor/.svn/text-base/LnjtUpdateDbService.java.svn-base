package com.yutong.clw.nio.msg.monitor;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yutong.clw.utils.LogFormatter;

/**
 * @author <a href="mailto:tianmc@neusoft.com">tianmc </a>
 * @version $Revision 1.0 $ 2010-5-25 上午11:07:35
 */
public class LnjtUpdateDbService {
	private Logger log = LoggerFactory.getLogger(LnjtUpdateDbService.class);
	private Timer timer = new Timer("getDBTimer");
	private DbTask task;
	private JdbcTemplate jdbcTemplate;
	private UtilInfoList utilInfoList = UtilInfoList.getInstance();

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
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
		log.info("LnjtUpdateDbService，新建一个LnjtUpdateDbService");
		task = new DbTask();
		timer.schedule(task, 0, 1000);
		log.info(LogFormatter.formatMsg("LnjtUpdateDbService",
				"start the  timer task."));
	}

	class DbTask extends TimerTask {

		@Override
		public void run() {
			String sql;
			try {
				synchronized (this) {
					boolean sflag = true;
					while (sflag) {
//						log.info("---------取数据---------");
						sql = utilInfoList.remove("UPDATESQL");// 取得一条数据
						if (sql == null) {
							sflag = false;
							break;
						}
						jdbcTemplate.update(sql);
					}
				}
			} catch (Throwable t) {
				cancel();
				log.error(LogFormatter.formatMsg("LnjtUpdateDbService dbTask",
						"dbtask has some problem."), t);
			}
		}
	}
}
