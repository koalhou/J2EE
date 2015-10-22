package com.neusoft.clw.safemanage.personsafe.photomonitor.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.safemanage.personsafe.photomonitor.domain.PhotoMonitorInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 人员安全==拍照监控
 * @author <a href="mailto:song-dh@neusoft.com">宋德华 </a>
 * @author <a href="mailto:fanxiaoyu@neusoft.com">范晓宇 </a>
 * @version $Revision 1.1 $ Apr 1, 2011 11:07:01 AM
 */
public class PhotoMonitorAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    private final String message = null;

    private String vehicle_ln;

    private String vehicle_vins;
    
    private String start_time;

    private String end_time;

    private String photo_event;
    
    // 分页相关
    private int pageindex; // 页数

    private int pagenumber; // 每页显示条数

    private int pagetotal; // 总条数

    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }

    public int getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(int pagenumber) {
        this.pagenumber = pagenumber;
    }

    public int getPagetotal() {
        return pagetotal;
    }

    public void setPagetotal(int pagetotal) {
        this.pagetotal = pagetotal;
    }

    public String getPhoto_event() {
        return photo_event;
    }

    public void setPhoto_event(String photo_event) {
        this.photo_event = photo_event;
    }

    private String photo_file;

    private String vehicle_code;

    /** 操作時間 */
    private String operate_time;

    private String chanle_code;

    /** 操作人 */
    private String user_name;

    private List<PhotoMonitorInfo> photoList;

    List<PhotoMonitorInfo> photoListView;

    private String chooseorgid;

    private Map map = new HashMap();

    // PhotoMonitorInfo photoMonitorInfo;

    // 列表页 点击查看照片详细信息时 选中的照片信息
    PhotoMonitorInfo photoView;

    // 相册内 获取照片详细信息
    PhotoMonitorInfo photoInfo;

    // PhotoMonitorInfo photoInfo;
    private Map photoInfoMap = new HashMap();

    /*
     * public void setPhotoInfo(PhotoMonitorInfo photoInfo) { this.photoInfo = photoInfo; }
     */

    public Map getPhotoInfoMap() {
        return photoInfoMap;
    }

    public void setPhotoInfoMap(Map photoInfoMap) {
        this.photoInfoMap = photoInfoMap;
    }

    public void setPhotoInfo(PhotoMonitorInfo photoInfo) {
        this.photoInfo = photoInfo;
    }

    public PhotoMonitorInfo getPhotoView() {
        return photoView;
    }

    public void setPhotoView(PhotoMonitorInfo photoView) {
        this.photoView = photoView;
    }

    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public List<PhotoMonitorInfo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<PhotoMonitorInfo> photoList) {
        this.photoList = photoList;
    }

    /**
     * 进入历史照片页面前处理 初始化开始时间和结束时间
     * @return
     */
    /**
    public String photoReady() {
        if (begintime == null) {
            begintime = DateUtil.getCurrentBeforeWeek();
        }
        if (StringUtils.isEmpty(endime)) {
            endime = DateUtil.getCurrentDay();
        }

        return SUCCESS;
    }
    **/
    /**
     * 进入历史照片页面前处理 初始化开始时间和结束时间
     * @return
     */
    public String init() {
        //MDC. put( "loginid" , getloginuuid());
        MDC. put( "modulename", "[pm]" );
        log.info("[跳转到照片管理页面]");
        if (start_time == null) {
            start_time = DateUtil.getCurrentBeforeWeek();
        }
        if (StringUtils.isEmpty(end_time)) {
            end_time = DateUtil.getCurrentDay();
        }
        return SUCCESS;
    }
    

    /**
     * 历史照片列表查询方法 页面初始化和主动查询时进入
     * @return
     */
    @SuppressWarnings("unchecked")
    public String photoManage() {

        //MDC. put( "loginid" , getloginuuid());
        MDC. put( "modulename", "[pm]" );
        final String browseTitle = getText("photomonitor.browse.title");
        log.info("[历史照片列表查询方法开始]");
        // int totalCount = 0;
        //UserInfo user = getCurrentUser();
        // Map < String, Object > map = new HashMap < String, Object >(4);
        // String tree_script = "";
        // String ChooseEnterID_tree = "";

        // pageindex:0,vehicle_ln:vehicle_ln,photo_event:photo_event,begintime:begintime,endime:endime
        log.info("pageindex:" + pageindex + ",[vehicle_vins:" + vehicle_vins + "],start_time:" + start_time
                + ",end_time:" + end_time + ",photo_event:" + photo_event);
        // log.info("chooseorgid:" + chooseorgid);
        // HttpServletRequest request = (HttpServletRequest)
        // ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {

            // 开始时间和结束时间初始化处理
            if (start_time == null) {
                // 获取当前日期的前6天 时间点
                start_time = DateUtil.getCurrentBeforeWeek();
            }

            log.debug("[start_time:" + start_time + "]");

            if (StringUtils.isEmpty(end_time)) {
                end_time = DateUtil.getCurrentDay();
            }
            if ((Integer.parseInt(end_time.replace("-", "")) - Integer.parseInt((DateUtil
                    .getCurrentDay()).replace("-", ""))) > 0) {
                end_time = DateUtil.getCurrentDay();
            }

            // 数据库查询条件设置
            Map<String,Object> paramap = new HashMap<String,Object>();
            if (!"".equals(start_time.trim())) {
                paramap.put("start_time", start_time + " 00:00:00");
            }
            paramap.put("end_time", end_time + " 23:59:59");
            //update by fxy 取消组织限制条件
            //paramap.put("organization_id", user.getOrganizationID());
            paramap.put("vehicle_vins", vehicle_vins);                     
            paramap.put("photo_event", photo_event);

            /*
             * if (null != chooseorgid && !"".equals(chooseorgid)) { paramap.put("organization_id",
             * chooseorgid); }
             */

            
            /*if ("yutongsuperadmin".equals(user.getUserID())) {
                paramap.put("operate_user_id", null);
            } else {
                paramap.put("operate_user_id", user.getUserID());
            }*/

            // 数据列表总数
            /*
             * totalCount = service .getCount("photomonitor.getPhotoCount", paramap);
             */
            log.info("(数据列表总数)数据库查询条件:" + "[vehicle_vins:" + paramap.get("vehicle_vins")
                    + "],start_time:" + paramap.get("start_time") + ",end_time:" + paramap.get("end_time")
                    + ",photo_event:" + paramap.get("photo_event"));

            pagetotal = service.getCount("photomonitor.getPhotoListNoOrgCount", paramap);

            log.info("(数据列表总数)pagetotal-->" + pagetotal);
            // 页数
            // pagenumber = Integer.toString(i)request.getParameter("pagenumber");
            // log.debug("pagenumber-->"+pagenumber);
            // 每页显示的数目
            // pageindex = request.getParameter("pageindex");
            //HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(
              //      org.apache.struts2.StrutsStatics.HTTP_REQUEST);

            // log.debug("pageindex-->"+pageindex);
            // String sortName = request.getParameter("sortname");
            // String sortName = "order by PHOTO_TIME";
            // String sortOrder = request.getParameter("sortorder");
            // String sortOrder = "DESC";
            paramap.put("sortname", "b.PHOTO_TIME");
            paramap.put("sortorder", "DESC");

            // 该分页从0页开始计算
            // int begin=pageindex*pagenumber;
            // 每页显示的照片个数
            int items_per_page = 15;
            int begin = pageindex * items_per_page;
            // int end = begin+pagenumber;
            log.info("(分页开始的位置)begin=" + begin);
            log.info("(每页显示的照片个数)items_per_page=" + items_per_page);

            log.info("(数据列表)数据库查询条件:" + "[vehicle_vins:" + paramap.get("vehicle_vins") + "],start_time:"
                    + paramap.get("start_time") + ",end_time:" + paramap.get("end_time")
                    + ",photo_event:" + paramap.get("photo_event") + ",sortname:"
                    + paramap.get("sortname") + ",sortorder:" + paramap.get("sortorder"));

            photoList = service.getObjectsByPage("photomonitor.getPhotoListNoOrg", paramap, begin, items_per_page);

            log.info("(数据列表总数)-->" + photoList.size());

            // this.setPagetotal(photoList.size());

            // 组装返回的json数据
            this.map = getPagination(photoList, pagetotal);

            if (photoList == null || 0 == photoList.size()) {
                this.addActionMessage(getText("photomonitor.nodata.list"));
            }

            // this.setPhotoList(photoList);
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_PHOTOMONITOR_QUERY_ID);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error("[历史照片列表查询,出现BusinessException]");
            log.error(browseTitle, e);
            return ERROR;
        }
        log.info("[历史照片列表查询方法结束]");
        return SUCCESS;

    }

    /**
     * 查看相片
     * @return
     */
    /*
     * public String photoView() { final String browseTitle = getText("photomonitor.browse.title");
     * Map < String, Object > paramap = new HashMap < String, Object >(); paramap.put("photo_id",
     * photoMonitorInfo.getPhoto_id()); try { photoListView = service.getObjectsByPage(
     * "photomonitor.getPhotoView", paramap, 0, 10); log.info("photoListView.size():" +
     * photoListView.size()); if (photoListView == null || 0 == photoListView.size()) {
     * this.addActionMessage(getText("photomonitor.nodata.list")); } } catch (BusinessException e) {
     * addActionError(getText(e.getMessage())); log.error(browseTitle, e); return ERROR; } return
     * SUCCESS; }
     */
    /**
     * 在列表页面 点击进入相册
     * @return
     */
    @SuppressWarnings("unchecked")
    public String photoGalleriaView() {
        final String browseTitle = getText("photomonitor.browse.title");
        //MDC.put( "loginid" , getloginuuid());
        MDC.put( "modulename", "[pm]" );
        // Map < String, Object > paramap = new HashMap < String, Object >();
        log.info("[照片浏览页面开始]");

        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(
                org.apache.struts2.StrutsStatics.HTTP_REQUEST);

        String photoIdStr = request.getParameter("photoIdStr");
        // checkedId
        String checkedId = request.getParameter("checkedId");

        log.info("[照片ID列表photoIdStr=" + photoIdStr+"]");
        log.info("[选中的照片ID checkedId=" + checkedId+"]");

        // 解析为photoID数组
        String[] photoId = photoIdStr.split(",");

        // 将ID数组 转换为 List参数列表
        List<String> photoIdList = new ArrayList<String>();
        photoIdList = Arrays.asList(photoId);

        try {

            // 获取的照片信息列表
            photoListView = service.getObjects("photomonitor.getPhotoInfoListByList", photoIdList);

            // 获取选中照片的信息bean
            photoView = (PhotoMonitorInfo) service
                    .getObject("photomonitor.getPhotoInfo", checkedId);

            log.debug("[点击照片对应的描述" + photoView.getPhoto_desc() + "]");

            // if (photoListView == null || 0 == photoListView.size()) {
            // this.addActionMessage(getText("photomonitor.nodata.list"));
            // }

            // 照片描述空值处理 null 替换为 “” ibatis转换时将空值转换为 "null"字符串
            if (null == photoView.getPhoto_desc() || "".equals(photoView.getPhoto_desc())
                    || "null".equals(photoView.getPhoto_desc())) {
                photoView.setPhoto_desc("");
            }
            
            // 转义photo_event
            if ("0".equals(photoView.getPhoto_event())) {
                photoView.setPhoto_event("手动拍照");
            } else if ("1".equals(photoView.getPhoto_event())) {
                photoView.setPhoto_event("定时拍照");
            } else if ("2".equals(photoView.getPhoto_event())) {
                photoView.setPhoto_event("SOS拍照");
            } else if ("3".equals(photoView.getPhoto_event())) {
                photoView.setPhoto_event("碰撞侧翻拍照");
            } else if ("4".equals(photoView.getPhoto_event())) {
                photoView.setPhoto_event("开门拍照");
            } else if ("5".equals(photoView.getPhoto_event())) {
                photoView.setPhoto_event("关门拍照");
            } else if ("6".equals(photoView.getPhoto_event())) {
                photoView.setPhoto_event("行驶开关门拍照");
            } else if ("7".equals(photoView.getPhoto_event())) {
                photoView.setPhoto_event("定距拍照");
            } else {
                photoView.setPhoto_event("其他拍照");
            }
            
            

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error("[照片浏览页面,出现BusinessException]");
            log.error(browseTitle, e);
            return ERROR;
        }

        log.info("[照片浏览页面结束]");
        return SUCCESS;

    }

    /**
     * 相册内点击缩略图 获取单个照片的信息
     * @return
     */
    public String getPhotoInfo() {

        final String browseTitle = getText("photomonitor.browse.title");
        //MDC.put( "loginid" , getloginuuid());
        MDC.put( "modulename", "[pm]" );

        log.info("[点击缩略图 获取单个照片的信息开始]");

        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(
                org.apache.struts2.StrutsStatics.HTTP_REQUEST);

        String phId = request.getParameter("phId");

        log.info("phId=" + phId);

        try {

            // 获取选中照片的信息bean
            photoInfo = (PhotoMonitorInfo) service.getObject("photomonitor.getPhotoInfo", phId);

            // 照片描述空值处理 null 替换为 “” ibatis转换时将空值转换为 "null"字符串
            log.info("getPhoto_desc=" + photoInfo.getPhoto_desc());

            if (null == photoInfo.getPhoto_desc() || "".equals(photoInfo.getPhoto_desc())
                    || "null".equals(photoInfo.getPhoto_desc())) {
                photoInfo.setPhoto_desc("");
            }
            
         // 转义photo_event
            if ("0".equals(photoInfo.getPhoto_event())) {
                photoInfo.setPhoto_event("手动拍照");
            } else if ("1".equals(photoInfo.getPhoto_event())) {
                photoInfo.setPhoto_event("定时拍照");
            } else if ("2".equals(photoInfo.getPhoto_event())) {
                photoInfo.setPhoto_event("SOS拍照");
            } else if ("3".equals(photoInfo.getPhoto_event())) {
                photoInfo.setPhoto_event("碰撞侧翻拍照");
            } else if ("4".equals(photoInfo.getPhoto_event())) {
                photoInfo.setPhoto_event("开门拍照");
            } else if ("5".equals(photoInfo.getPhoto_event())) {
                photoInfo.setPhoto_event("关门拍照");
            } else if ("6".equals(photoInfo.getPhoto_event())) {
                photoInfo.setPhoto_event("行驶开关门拍照");
            } else if ("7".equals(photoInfo.getPhoto_event())) {
                photoInfo.setPhoto_event("定距拍照");
            } else {
                photoInfo.setPhoto_event("其他拍照");
            }

            this.photoInfoMap.put("photoInfo", photoInfo);

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error("[点击缩略图 获取单个照片,出现BusinessException]");
            log.error(browseTitle, e);
            return ERROR;
        }

        log.info("[点击缩略图 获取单个照片的信息结束]");
        return SUCCESS;
    }

    /**
     * 更新单张照片信息
     * @return
     */
    @SuppressWarnings("unchecked")
    public String updatePhotoInfo() {

        final String browseTitle = getText("photomonitor.browse.title");
        //MDC.put( "loginid" , getloginuuid());
        MDC.put( "modulename", "[pm]" );
        log.info("[更新单张照片信息开始]");

        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(
                org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        // photo_id:photo_id,photo_falg:photo_falg,photo_desc:photo_desc
        String photo_id = request.getParameter("photo_id");
        String photo_falg = request.getParameter("photo_falg");
        String photo_desc = request.getParameter("photo_desc");

        // FLAG_USER 标识超载的用户ID Action中通过session获取
        String flag_user = this.getCurrentUser().getUserID();
        // FLAG_TIME 标识超载的时间 利用数据库SQL生成

        // 更新信息
        Map<String, String> paramap = new HashMap<String, String>();
        paramap.put("photo_id", photo_id);
        paramap.put("photo_falg", photo_falg);
        paramap.put("photo_desc", photo_desc);
        paramap.put("flag_user", flag_user);
        log.info("[更新的照片信息(photo_id=" + photo_id+")(photo_falg="+photo_falg+")(photo_desc="+photo_desc+")(flag_user="+flag_user+")");

        try {

            // 更新数据
            service.update("photomonitor.updatePhotoInfo", paramap);

            // 查询更新后的值
            photoInfo = (PhotoMonitorInfo) service.getObject("photomonitor.getPhotoInfo", photo_id);

            // 照片描述空值处理 null 替换为 “” ibatis转换时将空值转换为 "null"字符串
            log.info("更新后photo_falg=" + photoInfo.getPhoto_falg());
            log.info("更新后getPhoto_desc=" + photoInfo.getPhoto_desc());

            if (null == photoInfo.getPhoto_desc() || "".equals(photoInfo.getPhoto_desc())
                    || "null".equals(photoInfo.getPhoto_desc())) {
                photoInfo.setPhoto_desc("");
            }

            this.photoInfoMap.put("photoInfo", photoInfo);

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error("[更新单张照片信息,出现BusinessException]");
            log.error(browseTitle, e);
            return ERROR;
        }

        log.info("[更新单张照片信息结束]");
        return SUCCESS;
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, VehcileInfo vehObj) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != vehObj) {
            if (null != vehObj.getVehicle_id()) {
                OperateLogFormator.format(sb, "vehicleid", vehObj.getVehicle_id());
            }
        }
        return sb.toString();
    }

    public Map getPagination(List photoList, int pagetotal) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();

        // 部分字段转义处理
        for (int i = 0; i < photoList.size(); i++) {

            PhotoMonitorInfo s = (PhotoMonitorInfo) photoList.get(i);

            // 处理操作人为空的情况 操作人员设置
            if (!"0".equals(s.getPhoto_event())) {
                if (null == s.getUser_name() || "".equals(s.getUser_name())) {
                    s.setUser_name("终端触发");
                }
            }

            // 转义photo_event
            if ("0".equals(s.getPhoto_event())) {
                s.setPhoto_event("手动拍照");
            } else if ("1".equals(s.getPhoto_event())) {
                s.setPhoto_event("定时拍照");
            } else if ("2".equals(s.getPhoto_event())) {
                s.setPhoto_event("SOS拍照");
            } else if ("3".equals(s.getPhoto_event())) {
                s.setPhoto_event("碰撞侧翻拍照");
            } else if ("4".equals(s.getPhoto_event())) {
                s.setPhoto_event("开门拍照");
            } else if ("5".equals(s.getPhoto_event())) {
                s.setPhoto_event("关门拍照");
            } else if ("6".equals(s.getPhoto_event())) {
                s.setPhoto_event("行驶开关门拍照");
            } else if ("7".equals(s.getPhoto_event())) {
                s.setPhoto_event("定距拍照");
            } else {
                s.setPhoto_event("其他拍照");
            }

            /*
             * cellMap.put("cell", new Object[] {s.getVehicle_ln(), s.getVehicle_vin(),
             * s.getVehicle_code(), s.getChanle_code(), s.getPhoto_event(), s.getPhoto_time(),
             * s.getUser_name(), s.getPhoto_file() }); mapList.add(cellMap);
             */
            mapList.add(s);

        }
        // mapData.put("pageindex", pageindex);// 从前台获取当前第page页
        mapData.put("pagetotal", pagetotal);// 从数据库获取总记录数
        mapData.put("list", mapList);

        /*
         * if (null == photoMonitorInfo) { photoMonitorInfo = new PhotoMonitorInfo(); }
         */

        return mapData;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getPhoto_file() {
        return photo_file;
    }

    public void setPhoto_file(String photo_file) {
        this.photo_file = photo_file;
    }

    public String getChooseorgid() {
        return chooseorgid;
    }

    public void setChooseorgid(String chooseorgid) {
        this.chooseorgid = chooseorgid;
    }

    /*
     * public PhotoMonitorInfo getPhotoMonitorInfo() { return photoMonitorInfo; } public void
     * setPhotoMonitorInfo(PhotoMonitorInfo photoMonitorInfo) { this.photoMonitorInfo =
     * photoMonitorInfo; }
     */

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getVehicle_code() {
        return vehicle_code;
    }

    public void setVehicle_code(String vehicle_code) {
        this.vehicle_code = vehicle_code;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getChanle_code() {
        return chanle_code;
    }

    public void setChanle_code(String chanle_code) {
        this.chanle_code = chanle_code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getVehicle_vins() {
        return vehicle_vins;
    }

    public void setVehicle_vins(String vehicle_vins) {
        this.vehicle_vins = vehicle_vins;
    }

    public List<PhotoMonitorInfo> getPhotoListView() {
        return photoListView;
    }

    public void setPhotoListView(List<PhotoMonitorInfo> photoListView) {
        this.photoListView = photoListView;
    }
    
    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
    
}
