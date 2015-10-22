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
 * 维保设置action
 * @author Huangmb
 */
public class MaintenanceSetAction extends PaginationAction {
    /** 共同service **/
    private transient Service service;

    /** 提示信息 **/
    private String message = null;

    /** 维保信息 **/
    private Maintenance maintenance;

    /** 查询列表 **/
    private Map < String, Object > map = new HashMap < String, Object >();

    /** 维保项目 **/
    private static final Map < String, String > MAINT_ITEM = new TreeMap < String, String >();
    static {
        MAINT_ITEM.put("1", "新车报到");
        MAINT_ITEM.put("2", "走保");
        MAINT_ITEM.put("3", "强保");
        MAINT_ITEM.put("4", "高档车强保");
    }

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("service.management.location"));
        HttpServletRequest request = ServletActionContext.getRequest();
        // 为页面select设置数据
        request.setAttribute("maintItem", MAINT_ITEM);
        return SUCCESS;
    }

    /**
     * 页面初始化/查询
     * @return
     */
    public String queryList() {
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            String pageIndex = request.getParameter("page");
            String rpNum = request.getParameter("rp");

            // 为null,非数字,则初始化,保证转换成数字是不出异常
            pageIndex = defaultNumber(pageIndex, "1");
            rpNum = defaultNumber(rpNum, "10");

            String typeId = null;
            if (this.maintenance != null) {
                // 为0时表示查询全部
                if (this.maintenance.getType_id() != null
                        && "0".equals(this.maintenance.getType_id())) {
                    typeId = null;
                } else {
                    typeId = this.maintenance.getType_id();
                }
            }

            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            if (this.maintenance == null) {
                this.maintenance = new Maintenance();
            }
            this.maintenance.setSortname(sortName);
            this.maintenance.setSortorder(sortOrder);
            this.maintenance.setType_id(typeId);
            int totalSize = service.getCount("Maintenance.getMaintenanceCount",
                    this.maintenance);
            List < Maintenance > pageList = (List < Maintenance >) service
                    .getObjectsByPage("Maintenance.getMaintenanceList",
                            this.maintenance, (Integer.parseInt(pageIndex) - 1)
                                    * Integer.parseInt(rpNum), Integer
                                    .parseInt(rpNum));
            if (message != null) {
                super.addActionMessage(getText(message));
            }
            this.map = getPagination(pageList, totalSize, Integer
                    .valueOf(pageIndex), Integer.valueOf(rpNum));
        } catch (Exception be) {
            // TODO 改为BusinessException
            log.error("三包维保设置列表", be);
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_MAITENANCESET_QUERY_MID);
            addOperationLog("三包维保设置列表");
        }
        return SUCCESS;
    }

    /**
     * 转到维保设置添加页面
     * @return
     */
    public String toAdd() {
        log.info("");
        // TODO
        return SUCCESS;
    }

    /**
     * 维保设置添加
     * @return
     */
    public String doAdd() {
        // TODO
        return SUCCESS;
    }

    /**
     * 转到维保设置修改页面
     * @return
     */
    public String toModify() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("service.management.location"));
        HttpServletRequest request = ServletActionContext.getRequest();
        // 查询
        try {
            if (this.maintenance == null) {
                this.maintenance = new Maintenance();
            }
            List < Maintenance > pageList = (List < Maintenance >) service
                    .getObjects("LuxuryCarSet.getMaintenanceList",
                            this.maintenance);
            String[] itemIds = new String[4];
            // 将9条记录合并为一条记录
            if (pageList != null && pageList.size() > 0) {
                for (Maintenance m : pageList) {
                    String type = m.getType_id();
                    if ("1".equals(type)) {
                        // 新车报到
                        this.maintenance.setConditionNewCar(m
                                .getConditionNewCar());
                        this.maintenance.setConditionRemindNewCar(m
                                .getConditionRemindNewCar());
                        itemIds[0] = m.getItem_id();
                    } else if ("2".equals(type)) {
                        // 走保
                        this.maintenance.setConditionGo(m.getConditionNewCar());
                        this.maintenance.setConditionRemindGo(m
                                .getConditionRemindNewCar());
                        itemIds[1] = m.getItem_id();
                    } else if ("3".equals(type)) {
                        // 强保
                        this.maintenance.setConditionCompulsory(m
                                .getConditionNewCar());
                        this.maintenance.setConditionRemindCompulsory(m
                                .getConditionRemindNewCar());
                        itemIds[2] = m.getItem_id();
                    } else if ("4".equals(type)) {
                        // 高档车强保
                        this.maintenance.setConditionCompulsoryLuxury(m
                                .getConditionNewCar());
                        this.maintenance.setConditionRemindCompulsoryLuxury(m
                                .getConditionRemindNewCar());
                        this.maintenance.setCondition_luxury(m
                                .getCondition_luxury());
                        itemIds[3] = m.getItem_id();
                    }
                    this.maintenance.setItem_id(m.getItem_id());
                }
            }
            request.setAttribute("itemIds", itemIds);
            // 转修改页面
        } catch (Exception e) {
            log.error("三包维保设置详情信息出错!", e);
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_MAITENANCESET_QUERY_MID);
            addOperationLog("三包维保设置详情信息");
        }
        return SUCCESS;
    }

    /**
     * 维保设置修改
     * @return
     */
    public String doModify() {
        UserInfo user = sessionUser();
        if (user == null) {
            addActionError("获取当前用户信息失败!");
            return ERROR;
        }
        if(this.maintenance == null){
            this.maintenance = new Maintenance();
        }
        this.maintenance.setModifier_id(user.getUserID());
        String[] itemIds = null;
        if(this.maintenance.getItem_id() != null || "".equals(this.maintenance.getItem_id().trim())){
            itemIds = this.maintenance.getItem_id().trim().split(",");
        }
        if(itemIds == null || itemIds.length < 1){
            return ERROR;
        }
        // TODO service方法中的空串要改正为实际的
        try {
            // 直接根据ID修改
            for(int i=0;i<itemIds.length;i++){
                switch(i){
                case 0:
                    this.maintenance.setItem_id(itemIds[i].trim());
                    this.service.update("LuxuryCarSet.updateMaintenance",
                            this.maintenance);
                    break;
                case 1:
                    this.maintenance.setItem_id(itemIds[i].trim());
                    this.maintenance.setConditionNewCar(this.maintenance.getConditionGo());
                    this.maintenance.setConditionRemindNewCar(this.maintenance.getConditionRemindGo());
                    this.service.update("LuxuryCarSet.updateMaintenance",
                            this.maintenance);
                    break;
                    case 2:
                        this.maintenance.setItem_id(itemIds[i].trim());
                        this.maintenance.setConditionNewCar(this.maintenance.getConditionCompulsory());
                        this.maintenance.setConditionRemindNewCar(this.maintenance.getConditionRemindCompulsory());
                        this.service.update("LuxuryCarSet.updateMaintenance",
                                this.maintenance);
                        break;   
                    case 3:
                        this.maintenance.setItem_id(itemIds[i].trim());
                        this.maintenance.setConditionNewCar(this.maintenance.getConditionCompulsoryLuxury());
                        this.maintenance.setConditionRemindNewCar(this.maintenance.getConditionRemindCompulsoryLuxury());
                        this.service.update("LuxuryCarSet.updateMaintenance",
                                this.maintenance);
                        break;                        
                }
            }
            addActionMessage("修改数据成功!");
        } catch (BusinessException e) {
            log.error("修改三包维保设置信息出错!", e);
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_MAITENANCESET_MODIFY_MID);
            addOperationLog("修改三包维保设置信息");
        }
        return SUCCESS;
    }

    /**
     * 维保设置删除
     * @return
     */
    public String doDelete() {
        // TODO
        return SUCCESS;
    }

    /**
     * 转换为Map对象
     * @param maitenanceList
     * @param totalCount 数据总条数
     * @param pageIndex 页号
     * @param rp 每页显示条数
     * @return
     */
    public Map < String, Object > getPagination(
            List < Maintenance > maitenanceList, int totalCount, int pageIndex,
            int rp) {
        List < Map > mapList = new ArrayList < Map >();
        Map < String, Object > mapData = new LinkedHashMap < String, Object >();
        Map < String, Object > cellMap = null;
        int index = 0;
        // 序号
        int curIndex = 0;
        if (maitenanceList != null && maitenanceList.size() > 0)
            for (Maintenance m : maitenanceList) {
                index++;
                curIndex = (pageIndex - 1) * rp + index;
                cellMap = new LinkedHashMap < String, Object >();
                cellMap.put("id", m.getConfig_id());
                cellMap.put("cell", new Object[] {
                        curIndex,
                        MAINT_ITEM.get(m.getType_id()),
                        m.getModify_time(),
                        m.getModifier_id(),
                        "<a href='javascript:modify(\"" + m.getConfig_id()
                                + "\")'>修改 </a>" });
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
     * 统一获取取用户信息
     * @return
     */
    private UserInfo sessionUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }
    
    /**
     * 验证是否整数
     * @param number
     * @return
     */
    private boolean isNumber(String number){
        if(number.matches("^[0-9]*$")){
            return true;
        }
        return false;
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
