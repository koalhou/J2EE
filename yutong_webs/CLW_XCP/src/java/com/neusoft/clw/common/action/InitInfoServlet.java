package com.neusoft.clw.common.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.AppContextHelper;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MemData;
import com.neusoft.clw.sysmanage.datamanage.appconfig.domain.Appcfg;

public class InitInfoServlet extends HttpServlet {

    // protected static final Log LOGGER = LogFactory
    // .getLog(InitInfoServlet.class);
    protected static final Logger LOGGER = Logger
            .getLogger(InitInfoServlet.class);

    public void init() throws ServletException {
        // 初始配置参数
        initParameter();
        initsynParameter();
    }

    public void initParameter() {
        Service service = (Service) AppContextHelper.getBean("service");
        LOGGER.info("start init parameter begin");
        try {
            // 初始化参数表
            List < Appcfg > appcfglist = service.getObjects("Appcfg.getInfos",
                    Constants.CLW_P_CODE);
            MemData.setAppcfgList(appcfglist);
            LOGGER.info("start init parameter end");
        } catch (BusinessException e) {
            LOGGER.error("参数初始化出错:", e);
        } catch (Exception e2) {
            LOGGER.error("参数初始化出错:", e2);
        }
        service = null;
    }
    
    public void initsynParameter(){
        Service service = (Service) AppContextHelper.getBean("service");
        LOGGER.info("start init syn parameter begin");
        try {
            // 初始化参数表
            List < Appcfg > syncfglist = service.getObjects("Appcfg.getInfos",
                    Constants.CLW_SYN_CODE);
            MemData.setSyncfgList(syncfglist);
            LOGGER.info("start init syn parameter end");
        } catch (BusinessException e) {
            LOGGER.error("数据同步参数初始化出错:", e);
        } catch (Exception e2) {
            LOGGER.error("数据同步参数初始化出错:", e2);
        }
        service = null;
    }

    public void destroy() {
        LOGGER.info("xiaohui");
        if (null != MemData.getAppcfgList()) {
            MemData.setAppcfgList(null);
        }

        LOGGER.info("xiaohui end");
    }
}
