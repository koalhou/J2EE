package com.neusoft.clw.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PropertyConfigurator;

public class BootListener implements ServletContextListener {

    // private final Log log = LogFactory.getLog(BootListener.class);

    private final Logger log = Logger.getLogger(getClass());

    /*
     * CLW_M系统销毁
     * @seejavax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
     * ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {

        // 添加处理类型信息至日志
        MDC.put("module", "[MAIN]");
        MDC.put("processType", "[SYSTEM-DESTROY]");

        log.info("-------------------------------");
        log.info("-----通勤车系统退出-----");
        log.info("-------------------------------");

        // 从日志中移除处理类型信息
        MDC.remove("processType");
        MDC.remove("module");

    }

    /*
     * CLW_M系统初始化
     * @see javax.servlet.ServletContextListener#contextInitialized
     * (javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        // 添加处理类型信息至日志
        MDC.put("module", "[MAIN]");
        MDC.put("processType", "[SYSTEM-INIT]");

        log.info("-------------------------------");
        log.info("---通勤车系统启动成功---");
        log.info("-------------------------------");

        // 从日志中移除处理类型信息
        MDC.remove("processType");
        MDC.remove("module");
    }

}

final class LogInitiator {

    private LogInitiator() {
    }

    /**
     * 加载log4j配置文件
     * @param log4j 文件路径
     */
    public static void configure(String configFile) {
        PropertyConfigurator.configure(configFile);
    }

}
