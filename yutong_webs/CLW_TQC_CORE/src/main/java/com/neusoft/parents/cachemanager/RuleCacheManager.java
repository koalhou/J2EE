package com.neusoft.parents.cachemanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.parents.bean.Rules;
import com.neusoft.parents.dao.IParentsDAO;

public class RuleCacheManager
{
    private Logger log = LoggerFactory.getLogger(RuleCacheManager.class);

    private final static String NAME = "<RuleCacheManager>";

    private static final RuleCacheManager ruleCacheManager = new RuleCacheManager();

    private IParentsDAO parentsDAO;

    public static Map<String, List<Rules>> infoMap;

    private RuleCacheManager()
    {
        infoMap = new HashMap<String, List<Rules>>();
    }

    public static RuleCacheManager getInstance()
    {
        return ruleCacheManager;
    }

    public synchronized void init()
    {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[ruleCacheManager]");

        log.info("<ruleCacheManager>开始初始化家长规则信息缓存。。");

        List<Rules> listAll = parentsDAO.getRulesInfo();
        if (listAll == null || listAll.size() <= 0)
        {
            log.debug(NAME + "从数据库未查出推送规则信息。。");
            return;
        }
        if (Constant.isfirstruleload)
        {
            for (Rules rules : listAll)
            {
                List<Rules> listUser = parentsDAO.getUserPushRule(rules.getUser_id(),rules.getStu_id());
                String key = Constant.PUSHRULE + rules.getUser_id()+rules.getStu_id();
                addRulesIntoCache(key, listUser);
            }
            Constant.isfirstruleload = false;
        }
        else
        {
            for (Rules rules : listAll)
            {
                List<Rules> listUser = parentsDAO.getUserPushRule(rules.getUser_id(),rules.getStu_id());
                String key = Constant.PUSHRULE + rules.getUser_id()+rules.getStu_id();
                SyncRulesInfoValue(Constant.OFF, key, listUser);
            }
        }
        log.info(NAME + "本次启动共加载" + listAll.size() + "家长规则信息缓存");
        listAll.clear();
        listAll=null;
    }

    /**
     * 将学生家长信息加入缓存
     * 
     * @param cidList
     */
    public synchronized void addRulesIntoCache(String key, List<Rules> rules)
    {
        if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState())
        {
            Constant.getMemcachedClient().insert(key, rules);
        }
        infoMap.put(key, rules);
    }

    @SuppressWarnings("unchecked")
    public synchronized List<Rules> getValue(String key)
    {
        if (Constant.isstartMemcache.equals("0") && Constant.getMemcachedClient().connectState())
        {
            Object o = Constant.getMemcachedClient().getObject(key);
            if (o != null && !o.equals(""))
            {
                return (List<Rules>) o;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return infoMap.get(key);
        }
    }

    public synchronized List<Rules> SyncRulesInfoValue(String type, String key, List<Rules> rules)
    {
        if (type.equals(Constant.OFF))
        {
            deleteRulesInfo(key);
            addRulesIntoCache(key, rules);
            return null;
        }
        else if (type.equals(Constant.ON))
        {
            return getValue(key);
        }
        else
        {
            log.error(NAME + "SyncStuParentsInfoValue传入的类型错误");
            return null;
        }
    }

    public void deleteRulesInfo(String key)
    {
        if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState())
        {
            Constant.getMemcachedClient().delete(key);
        }
        infoMap.remove(key);
    }

    public void setParentsDAO(IParentsDAO parentsDAO)
    {
        this.parentsDAO = parentsDAO;
    }

}
