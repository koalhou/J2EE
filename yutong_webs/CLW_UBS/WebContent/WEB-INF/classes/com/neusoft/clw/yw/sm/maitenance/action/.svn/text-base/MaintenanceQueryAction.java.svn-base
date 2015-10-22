package com.neusoft.clw.yw.sm.maitenance.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.yw.qx.entimanage.ds.EnterpriseResInfo;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.sm.maitenance.ds.Maintenance;
import com.opensymphony.xwork2.ActionContext;

/**
 * 维保查询action
 * @author Huangmb
 */
public class MaintenanceQueryAction extends PaginationAction {
    /** 共同service **/
    private transient Service service;

    /** 提示信息 **/
    private String message = null;

    /** 维保信息 **/
    private Maintenance maintenance;

    /** 维保项目 **/
    private static final Map < String, String > MAINT_ITEM = new TreeMap < String, String >();

    /** 查询列表 **/
    private Map < String, Object > map = new HashMap < String, Object >();
    static {
        MAINT_ITEM.put("1", "新车报到");
        MAINT_ITEM.put("2", "走保");
        MAINT_ITEM.put("3", "强保");
        MAINT_ITEM.put("4", "高档车强保");
    }

    /**
     * 伪造高档车相关数据,不用时删除
     * @param pageIndex 页号
     * @param rp 每页显示条数
     * @return
     */
    private List < Maintenance > maitenanceList(int pageIndex, int rp) {
        List < Maintenance > pageList = new ArrayList < Maintenance >();
        Maintenance maitenance = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int start = (pageIndex - 1) * rp + 10;
        int end = start + rp;
        for (int i = start; i < end; i++) {
            maitenance = new Maintenance();
            maitenance.setCompany("安捷通" + i);
            maitenance.setBranch("安捷通" + i + "分公司");
            maitenance.setCondition_luxury("4");
            maitenance.setConditionCompulsory("2500");
            maitenance.setConditionGo("");
            maitenance.setConditionNewCar("");
            maitenance.setConditionRemindCompulsory("");
            maitenance.setConditionRemindCompulsoryLuxury("");
            maitenance.setConditionRemindGo("");
            maitenance.setConditionRemindNewCar("");
            maitenance.setItem_id("");
            maitenance.setRemind_flag("0");
            maitenance.setType_id("");
            maitenance.setValidate_flag("");
            maitenance.setVehicle_fleet("");
            maitenance.setVehicle_ln("川A 88" + i);
            maitenance.setVehicle_number("11A333F-12" + i);
            maitenance.setCreate_time(sdf.format(new Date()));
            maitenance.setCreater_id("管理员");
            maitenance.setModifier_id("修改员");
            maitenance.setModify_time(sdf
                    .format(new Date().getTime() + 1000 * 60 * 15));
            maitenance.setVehicle_vin("川A 80" + i);
            pageList.add(maitenance);
        }
        return pageList;
    }

    /**
     * 产生一条伪数据
     * @return
     */
    private Maintenance createMaitenance() {
        int i = 88;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Maintenance maitenance = new Maintenance();
        maitenance = new Maintenance();
        maitenance.setCompany("安捷通" + i);
        maitenance.setBranch("安捷通" + i + "分公司");
        maitenance.setCondition_luxury("4");
        maitenance.setConditionCompulsory("2500");
        maitenance.setConditionGo("");
        maitenance.setConditionNewCar("");
        maitenance.setConditionRemindCompulsory("");
        maitenance.setConditionRemindCompulsoryLuxury("");
        maitenance.setConditionRemindGo("");
        maitenance.setConditionRemindNewCar("");
        maitenance.setItem_id("");
        maitenance.setRemind_flag("0");
        maitenance.setType_id("");
        maitenance.setValidate_flag("");
        maitenance.setVehicle_fleet("");
        maitenance.setVehicle_ln("川A 88" + i);
        maitenance.setVehicle_number("11A333F-12" + i);
        maitenance.setCreate_time(sdf.format(new Date()));
        maitenance.setCreater_id("管理员");
        maitenance.setModifier_id("修改员");
        maitenance.setModify_time(sdf
                .format(new Date().getTime() + 1000 * 60 * 15));
        maitenance.setVehicle_vin("川A 80" + i);
        return maitenance;
    }

    /**
     * 转到维保查询页面
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("service.management.query.location"));
        UserInfo user = sessionUser();
        Map < String, Object > map = new HashMap < String, Object >(4);
        // 一进页面进入自己最顶级企业
        String ChooseEnterID_tree = user.getOrganizationID();

        String tree_script = "";
        try {
            if (!"3".equals(user.getUserType())) {
                map.put("in_enterprise_id", user.getOrganizationID());
                map.put("out_flag", null);
                map.put("out_message", null);
                map.put("out_ref", null);
                service.getObject("EntiManage.show_enterprise_tree", map);
                if ("0".equals(map.get("out_flag"))) {
                    ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                            .get("out_ref");
                    tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
                }
                ChooseEnterID_tree = user.getOrganizationID();
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
            if (!"".equals(ChooseEnterID_tree)) {
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree", ChooseEnterID_tree);
            }
        }
        return SUCCESS;
    }

    /**
     * 树获取页面
     * @return
     */
    public String getTreeInit() {
        final String addBefTitle = getText("oilinfo.gettree.title");
        log.info(addBefTitle);
        UserInfo user = sessionUser();
        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {
            map.put("in_enterprise_id", user.getOrganizationID());
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("GPS.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addBefTitle, e);
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
        }
        return SUCCESS;
    }

    /**
     * 维保查询页面
     * @return
     */
    public String maintenanceQuery() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String enterprise_id = request.getParameter("ChooseEnterID_tree");
        ActionContext.getContext().getSession().put("ChooseEnterID_tree",
                enterprise_id);
        try {
            String pageIndex = request.getParameter("page");
            String rpNum = request.getParameter("rp");

            // 为null,非数字,则初始化,保证转换成数字是不出异常
            pageIndex = defaultNumber(pageIndex, "1");
            rpNum = defaultNumber(rpNum, "10");

            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            if (this.maintenance == null) {
                this.maintenance = new Maintenance();
            }
            this.maintenance.setSortname(sortName);
            this.maintenance.setSortorder(sortOrder);
            this.maintenance.setEnterprise_id(enterprise_id);
            int totalSize = service.getCount(
                    "LuxuryCarSet.getMaintenanceQueryCount", this.maintenance);
            if (totalSize <= 0) {
                super.addActionError(getText("common.no.data"));
            }
            List < Maintenance > pageList = (List < Maintenance >) service
                    .getObjectsByPage("LuxuryCarSet.getMaintenanceQueryList",
                            this.maintenance, (Integer.parseInt(pageIndex) - 1)
                                    * Integer.parseInt(rpNum), Integer
                                    .parseInt(rpNum));
            if (message != null) {
                super.addActionMessage(getText(message));
            }
            this.map = getPagination(pageList, totalSize, pageIndex);
        } catch (BusinessException be) {
            log.error("三包维保列表", be);
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_MAITENANCEQUERY_QUERY_MID);
            addOperationLog("三包维保列表");
        }
        return SUCCESS;
    }

    /**
     * 维保查询更新提醒状态
     * @return
     */
    public String modifyRemindState() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String checkItemIds = request.getParameter("checkItemIds");
        String unCheckItemIds = request.getParameter("unCheckItemIds");
        if(this.maintenance == null){
            this.maintenance = new Maintenance();
        }
        //更新客户设置不再提醒状态
        if(checkItemIds != null && !"".equals(checkItemIds)){
            String[] cids = checkItemIds.split(",");
            for(int i=0;i<cids.length;i++){
                this.maintenance.setItem_id(cids[i]);
                //不再提醒,设值为1
                this.maintenance.setRemind_flag("1");
                try {
                    this.service.update("LuxuryCarSet.updateMaintenanceRemindState", this.maintenance);
                } catch (BusinessException e) {
                    log.error("",e);
                }
            }
        }
        //更新客户设置提醒状态
        if(unCheckItemIds != null && !"".equals(unCheckItemIds)){
            String[] ucids = unCheckItemIds.split(",");
            for(int i=0;i<ucids.length;i++){
                this.maintenance.setItem_id(ucids[i]);
                //提醒,设值为0
                this.maintenance.setRemind_flag("0");
                this.maintenance.setValidate_flag("0");
                try {
                    this.service.update("LuxuryCarSet.updateMaintenanceRemindState", this.maintenance);
                } catch (BusinessException e) {
                    log.error("",e);
                }
            }
        }
        setOperationType(Constants.UPDATE,
                ModuleId.CLW_M_MAITENANCEQUERY_REMIND_MID);
        addOperationLog("三包维保列表-不再提醒");
        return SUCCESS;
    }

    /**
     * 转换为Map对象
     * @param luxuryCarList
     * @param totalCount
     * @param pageIndex
     * @return
     */
    public Map < String, Object > getPagination(
            List < Maintenance > maitenanceList, int totalCount,
            String pageIndex) {
        List < Map > mapList = new ArrayList < Map >();
        Map < String, Object > mapData = new LinkedHashMap < String, Object >();
        Map < String, Object > cellMap = null;
        String flag = null;
        String inputBox = null;
        if (maitenanceList != null && maitenanceList.size() > 0)
            for (Maintenance m : maitenanceList) {
                flag = m.getRemind_flag();
                if(flag != null && "1".equals(flag.trim())){
                    inputBox = "<input id='" + m.getItem_id() + "' type='checkbox' name='noremind' lang='" + m.getItem_id() + "' checked='checked' />";
                }else{
                    inputBox = "<input id='" + m.getItem_id() + "' type='checkbox' name='noremind' lang='" + m.getItem_id() + "'/>";
                }
                cellMap = new LinkedHashMap < String, Object >();
                cellMap.put("id", m.getVehicle_number());
                cellMap.put("cell", new Object[] {m.getVehicle_ln(),
                        m.getType_name(),m.getVehicle_number(),
                        m.getVehicle_vin(), m.getConfig_id(),
                        m.getConditionNewCar(), m.getConditionRemindNewCar(), 
                        m.getCondition_luxury(),inputBox});
                mapList.add(cellMap);
            }
        // 从前台获取当前第page页
        mapData.put("page", pageIndex);
        // 从数据库获取总记录数
        mapData.put("total", totalCount);
        mapData.put("rows", mapList);
        return mapData;
    }

    /**
     * num不是数字,则返回dn,要求dn必须是数字
     * @param num 任一字符串
     * @param dn 必须是数字
     * @return
     */
    private String defaultNumber(String num, String dn) {
        // 为null,非数字,则初始化
        if (num == null || !num.matches("^[0-9]*$")) {
            num = dn;
        }
        return num;
    }

    /**
     * 统一获取取用户信息
     * @return
     */
    private UserInfo sessionUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    public Map < String, Object > getMap() {
        return map;
    }

    public void setMap(Map < String, Object > map) {
        this.map = map;
    }

}
