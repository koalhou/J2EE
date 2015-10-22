package com.neusoft.parents.cachemanager;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.neusoft.clw.constant.Constant;
import com.neusoft.parents.bean.CacheItem;

public class StuSiteNoteCacheManager
{
    private Logger log = LoggerFactory.getLogger(StuSiteNoteCacheManager.class);

    private final static String NAME = "<StuSiteNoteCacheManager>";

    private static final StuSiteNoteCacheManager stuSiteNoteCacheManager = new StuSiteNoteCacheManager();

    public static Map<String, CacheItem> infoMap;

    private StuSiteNoteCacheManager()
    {
        infoMap = new HashMap<String, CacheItem>();
    }

    public static StuSiteNoteCacheManager getInstance()
    {
        return stuSiteNoteCacheManager;
    }

    // public synchronized void init()
    // {
    // MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
    // MDC.put("modulename", "[stuSiteNoteCacheManager]");
    // log.info("<stuSiteNoteCacheManager>开始初始化学生站点提醒信息缓存。。");
    //
    // List<StuSiteNote> list = parentsDAO.getStuSiteNote();
    //
    // CacheItem ci = new CacheItem();
    // ci.setDate(new Date());
    // ci.setStuSiteNoteList(list);
    //
    // if (list == null || list.size() <= 0)
    // {
    // log.debug(NAME + "从数据库未查出车辆实时信息。。");
    // return;
    // }
    // if (Constant.isfirststuparentsload)
    // {
    // String key = Constant.STUSITENOTE;
    // addSiteNoteIntoCache(key, ci);
    // Constant.isfirststuparentsload = false;
    // }
    // else
    // {
    // String key = Constant.STUSITENOTE;
    // SyncSiteNoteInfoValue(Constant.OFF, key, ci);
    // }
    // list = null;
    // log.info(NAME + "学生家长信息缓存初始化完毕。");
    //
    // }

    /**
     * 将学生家长信息加入缓存
     * 
     * @param cidList
     */
    public synchronized void addSiteNoteIntoCache(String key, CacheItem stuSiteNote)
    {
        if (Constant.isstartMemcache.equals("0") && Constant.getMemcachedClient().connectState())
        {
            Constant.getMemcachedClient().insert(key, stuSiteNote);
        }
        infoMap.put(key, stuSiteNote);
    }

    @SuppressWarnings("unchecked")
    public synchronized CacheItem getValue(String key)
    {
        if (Constant.isstartMemcache.equals("0") && Constant.getMemcachedClient().connectState())
        {
            Object o = Constant.getMemcachedClient().getObject(key);
            if (o != null && !o.equals(""))
            {
                return (CacheItem) o;
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

    public synchronized CacheItem SyncSiteNoteInfoValue(String type, String key, CacheItem stuSiteNote)
    {
        if (type.equals(Constant.OFF))
        {
            deleteSiteNoteInfo(key);
            addSiteNoteIntoCache(key, stuSiteNote);
            return null;
        }
        else if (type.equals(Constant.ON))
        {
            return getValue(key);
        }
        else
        {
            log.error(NAME + "SyncVehicleRealInfoValue传入的类型错误");
            return null;
        }
    }

    public void deleteSiteNoteInfo(String key)
    {
        if (Constant.isstartMemcache.equals("0") && Constant.getMemcachedClient().connectState())
        {
            Constant.getMemcachedClient().delete(key);
        }
        infoMap.remove(key);
    }

}
