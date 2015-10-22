package com.neusoft.clw.common.util.servlet;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.AppContextHelper;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.servlet.ds.Appcfg;
import com.neusoft.clw.common.util.servlet.ds.MemData;

/**
 * web初始化servlet
 * @author JinPeng
 */
public class WebInitServlet extends HttpServlet {
    /** serialVersionUID **/
    private static final long serialVersionUID = -5958651158958275940L;

    protected final Logger log = Logger.getLogger(getClass());

    /**
     * 初始化
     */
    public void init() throws ServletException {
        // 初始配置参数
        initParameter();
    }

    /**
     * 初始化参数
     */
    public void initParameter() {
        log.info("initParameter start");

        Service service = (Service) AppContextHelper.getBean("service");

        try {
            // 初始化参数表
            List < Appcfg > appcfglist = service.getObjects(
                    "AppConfig.getAppConfigInfos", Constants.CLW_M_CODE);
            MemData.setAppcfgList(appcfglist);

            // 初始化数据同步参数表
            List < Appcfg > realappcfglist = service.getObjects(
                    "AppConfig.getAppConfigInfos", Constants.CLW_M_REAL_CODE);
            MemData.setRealNoticList(realappcfglist);
            
        } catch (BusinessException e) {
            log.error("initParameter error:", e);
        } catch (Exception e2) {
            log.error("initParameter error:", e2);
        } finally {
            log.info("initParameter end");
        }

    }

    /**
     * 销毁
     */
    public void destroy() {
        log.info("destroy start");

        if (null != MemData.getAppcfgList()) {
            MemData.setAppcfgList(null);
        }

        log.info("destroy end");
    }
}
