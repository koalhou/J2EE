package com.neusoft.parents.sitenotemanager;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.parents.dao.impl.ParentsDAO;

public class StuStateClearManager
{

    private Logger log = LoggerFactory.getLogger(StuStateClearManager.class);

    private static final String NAME = "<SendCommandManager>";

    private static final StuStateClearManager stuStateClearManager = new StuStateClearManager();

    private ParentsDAO parentsDAO;

    public ParentsDAO getParentsDAO()
    {
        return parentsDAO;
    }

    public void setParentsDAO(ParentsDAO parentsDAO)
    {
        this.parentsDAO = parentsDAO;
    }

    private StuStateClearManager()
    {

    }

    public static StuStateClearManager getInstance()
    {
        return stuStateClearManager;
    }

    @SuppressWarnings({ "unchecked", "static-access" })
    public void init()
    {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[StuStateClearInit]");
        try
        {
            log.info("开始更新学生的状态");
            int num=parentsDAO.updateAllStuState();
            log.info("共更新"+num+"条学生状态信息");
        }
        catch (Exception e)
        {

            log.error(NAME + "StuStateClearManager错误：" + e.getMessage());
            e.printStackTrace();
        }

    }

}
