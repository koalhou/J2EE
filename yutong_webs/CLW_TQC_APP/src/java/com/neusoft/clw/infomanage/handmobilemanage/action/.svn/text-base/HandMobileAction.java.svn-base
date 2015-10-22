package com.neusoft.clw.infomanage.handmobilemanage.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.handmobileservice.HandMobileService;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.infomanage.handmobilemanage.domain.HandMobileInfo;
import com.neusoft.clw.sysmanage.datamanage.entimanage.domain.EnterpriseResInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.opensymphony.xwork2.ActionContext;

public class HandMobileAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    /** 批量更新orgid */
    private transient HandMobileService handmobileService;

    /**
     * 车辆批量去除分配IMEI字符串组合
     */
    private String carsVinInfos;

    public String getCarsVinInfos() {
        return carsVinInfos;
    }

    public void setCarsVinInfos(String carsVinInfos) {
        this.carsVinInfos = carsVinInfos;
    }

    public HandMobileService getHandmobileService() {
        return handmobileService;
    }

    public void setHandmobileService(HandMobileService handmobileService) {
        this.handmobileService = handmobileService;
    }

    private String message = null;

    // 选择条件处的组织ID
    private String organization_id;

    private String terminal_id;

    /** 左侧list **/
    private List < HandMobileInfo > leftList = null;

    private HandMobileInfo handmobileinfo;

    public List < HandMobileInfo > getLeftList() {
        return leftList;
    }

    public void setLeftList(List < HandMobileInfo > leftList) {
        this.leftList = leftList;
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public HandMobileInfo getHandmobileinfo() {
        return handmobileinfo;
    }

    public void setHandmobileinfo(HandMobileInfo handmobileinfo) {
        this.handmobileinfo = handmobileinfo;
    }

    private Map map = new HashMap();

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
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

    /**
     * 页面初始化
     * @return
     */
    public String init() {
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }

    /**
     * 车辆管理页面浏览车辆
     * @return
     */
    public String handmobileBrowse() {
        HttpServletRequest request = ServletActionContext.getRequest();
        final String browseTitle = getText("handmobile.browse.title");
        log.info(browseTitle);
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        try {
            HandMobileInfo handmobinfo = new HandMobileInfo();

            // 条件查询设置组织ID-如果没有输入条件查询则默认查询所有当前用户组织下的车辆
            if (null != organization_id && !organization_id.equals("")) {
                handmobinfo.setOrganization_id(organization_id);
            } else {
                handmobinfo.setOrganization_id(user.getOrganizationID());
            }
            // 设置企业ID
            handmobinfo.setEnterprise_id(user.getEntiID());
            // 设置车辆Vin

            if (null != terminal_id && !terminal_id.equals("")) {
                handmobinfo.setTerminal_id(SearchUtil.formatSpecialChar(terminal_id));
            } else {
                handmobinfo.setTerminal_id(null);
            }

            if (user.getEntiID().equals(user.getOrganizationID())) {
                // 管理员查询
                if (null != organization_id && !organization_id.equals("")) {
                    handmobinfo.setOrganization_id(organization_id);
                } else {
                    handmobinfo.setOrganization_id(null);
                }
                totalCount = service.getCount(
                        "HandMobileManage.getCountbyEnid", handmobinfo);
                // add by jinp start
                // 每页显示条数
                String rpNum = request.getParameter("rp");
                // 当前页码
                String pageIndex = request.getParameter("page");
                // 排序字段名
                String sortName = request.getParameter("sortname");
                // 升序OR降序
                String sortOrder = request.getParameter("sortorder");
                handmobinfo.setSortname(sortName);
                handmobinfo.setSortorder(sortOrder);

                List < HandMobileInfo > handList = (List < HandMobileInfo >) service
                        .getObjectsByPage("HandMobileManage.getInfosbyEnid",
                                handmobinfo, (Integer.parseInt(pageIndex) - 1)
                                        * Integer.parseInt(rpNum), Integer
                                        .parseInt(rpNum));
                this.map = getPagination(handList, totalCount, pageIndex);
                // modify by jinp stop
                // add by jinp start
            } else {
                // 分公司用户
                totalCount = service.getCount("HandMobileManage.getCount",
                        handmobinfo);
                // 每页显示条数
                String rpNum = request.getParameter("rp");
                // 当前页码
                String pageIndex = request.getParameter("page");
                // 排序字段名
                String sortName = request.getParameter("sortname");
                // 升序OR降序
                String sortOrder = request.getParameter("sortorder");
                handmobinfo.setSortname(sortName);
                handmobinfo.setSortorder(sortOrder);
                List < HandMobileInfo > handList = (List < HandMobileInfo >) service
                        .getObjectsByPage(
                                "HandMobileManage.gethandmoblieInfos",
                                handmobinfo, (Integer.parseInt(pageIndex) - 1)
                                        * Integer.parseInt(rpNum), Integer
                                        .parseInt(rpNum));
                this.map = getPagination(handList, totalCount, pageIndex);
            }

            if (null != message) {
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_HANDMOBILE_QUREY_ID);
        } catch (BusinessException e) {
            log.error("查询手持设备信息出错:", e);
            addActionError(getText(e.getMessage()));
            return ERROR;
        }

        return SUCCESS;
    }

    public Map getPagination(List list, int totalCountDay, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {

            HandMobileInfo s = (HandMobileInfo) list.get(i);

            Map cellMap = new LinkedHashMap();
            if (null == s.getShort_allname()
                    || s.getShort_allname().length() == 0) {
                s.setShort_allname(getText("vehcileinfo.status.one"));
            }
            cellMap.put("id", s.getVehicle_id());

            cellMap.put("cell", new Object[] {null, s.getTerminal_id(),
                    s.getVehicle_vin(), s.getCellphone(), s.getShort_allname(),
                    s.getUser_name(), s.getUser_contact() });

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    /**
     * 手持设备修改页面
     * @return
     */
    public String editBefore() {
        final String editBefTitle = getText("handmobile.editbefore.title");
        log.info(editBefTitle);
        String tree_script = "";
        String ChooseEnterID_tree = "";

        try {
            if (null != handmobileinfo) {
                handmobileinfo = (HandMobileInfo) service.getObject(
                        "HandMobileManage.getHandmobileByid", handmobileinfo);
                if (null == handmobileinfo) {
                    setMessage("info.data.notexsist");
                    return ERROR;
                }
                Map < String, Object > map = new HashMap < String, Object >(4);
                /* 显示树 */
                UserInfo user = getCurrentUser();
                /*
                 * map.put("in_enterprise_id", user.getOrganizationID());
                 * map.put("out_flag", null); map.put("out_message", null);
                 * map.put("out_ref", null);
                 * service.getObject("HandMobileManage.show_enterprise_tree",
                 * map); if ("0".equals(map.get("out_flag"))) { ArrayList <
                 * EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >)
                 * map .get("out_ref"); tree_script =
                 * TreeHtmlShow.getEnterpriseAllClick(res); }
                 */

                if (null != handmobileinfo.getOrganization_id()) {
                    Map < String, Object > enmap = new HashMap < String, Object >(
                            5);
                    enmap.put("in_enterprise_id", user.getOrganizationID());
                    enmap.put("in_org_id", handmobileinfo.getOrganization_id());
                    enmap.put("out_flag", null);
                    enmap.put("out_message", null);
                    enmap.put("out_ref", null);
                    service.getObject("HandMobileManage.show_enterprise_id",
                            enmap);
                    if ("0".equals(enmap.get("out_flag"))) {
                        ArrayList < HandMobileInfo > enallid = (ArrayList < HandMobileInfo >) enmap
                                .get("out_ref");
                        StringBuffer enid = new StringBuffer("");
                        for (int i = 0; i < enallid.size(); i++) {
                            HandMobileInfo veinfo = enallid.get(i);
                            enid.append(veinfo.getEnterprise_id());
                            if (i < (enallid.size() - 1)) {
                                enid.append("|");
                            }
                        }
                        ChooseEnterID_tree = enid.toString();
                    }
                }
                // setOrganization_idold(HandMobileManage.getOrganization_id());
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(editBefTitle, e);
            return ERROR;
        }

        finally {
            // ActionContext.getContext().getSession().put("tree_script",
            // tree_script);
            ActionContext.getContext().getSession().put("ChooseEnterID_tree",
                    ChooseEnterID_tree);
        }
        return SUCCESS;
    }

    /**
     * 修改手持设备
     */
    public String updateHandmobile() {
        if (null == handmobileinfo) {
            return editBefore();
        }
        final String editTitle = getText("handmobile.update.title");
        log.info(editTitle);
        try {
            UserInfo user = getCurrentUser();
            handmobileinfo.setModifier(user.getUserID());
            service
                    .update("HandMobileManage.updatebyVehicleid",
                            handmobileinfo);
        } catch (BusinessException e) {
            log.error(editTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("handmobile.editsuccess.message");
        // 设置操作描述
        this.addOperationLog(formatLog(editTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_HANDMOBILE_UPDATE_ID);
        return SUCCESS;
    }

    /**
     * 去除分配
     * @return
     */
    public String cancleHandmobile() {
        if (null == handmobileinfo) {
            return editBefore();
        }
        final String cancleTitle = getText("handmobile.cancle");
        log.info(cancleTitle);
        try {
            UserInfo user = getCurrentUser();
            handmobileinfo.setModifier(user.getUserID());
            service
                    .update("HandMobileManage.canclebyVehicleid",
                            handmobileinfo);
        } catch (BusinessException e) {
            log.error(cancleTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("handmobile.canclesuccess.message");
        // 设置操作描述
        this.addOperationLog(formatLog(cancleTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_HANDMOBILE_CANCLE_ID);
        return SUCCESS;
    }

    public String addHandmobilebefore() {
        final String addBefTitle = getText("handmobile.add.title");
        log.info(addBefTitle);
        UserInfo user = getCurrentUser();
        // Map < String, Object > map = new HashMap < String, Object >(4);
        // String tree_script = "";
        try {
            /*
             * map.put("in_enterprise_id", user.getOrganizationID());
             * map.put("out_flag", null); map.put("out_message", null);
             * map.put("out_ref", null);
             * service.getObject("VehicleManage.show_enterprise_tree", map); if
             * ("0".equals(map.get("out_flag"))) { ArrayList < EnterpriseResInfo
             * > res = (ArrayList < EnterpriseResInfo >) map .get("out_ref");
             * tree_script = TreeHtmlShow.getEnterpriseLastChildClick(res); }
             */
            leftList = (List < HandMobileInfo >) service.getObjects(
                    "HandMobileManage.getHandmobiledesc", user
                            .getOrganizationID());

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addBefTitle, e);
            return ERROR;
        }
        /*
         * finally { ActionContext.getContext().getSession().put("tree_script",
         * tree_script); }
         */
        return SUCCESS;
    }

    /**
     * 分配手持设备
     * @return
     */
    public String add() {

        if (null == handmobileinfo) {
            return addHandmobilebefore();
        }
        final String addTitle = getText("handmobile.add.info");
        log.info(addTitle);
        try {
            String[] selectveh = handmobileinfo.getSelectveh();
            List < HandMobileInfo > updateorgidList = new ArrayList < HandMobileInfo >();
            if (null != selectveh) {
                UserInfo user = getCurrentUser();
                /** 批量更新list **/
                for (int i = 0; i < selectveh.length; i++) {
                    HandMobileInfo handinfo = new HandMobileInfo();
                    handinfo.setOrganization_id(handmobileinfo
                            .getOrganization_id());
                    handinfo.setVehicle_id(selectveh[i]);
                    handinfo.setModifier(user.getUserID());
                    updateorgidList.add(handinfo);
                }
            }
            if (null != updateorgidList && updateorgidList.size() == 0) {
                addActionError(getText("nodata.vehcileinfo.list"));
                return ERROR;
            } else {
                // 分配车辆时批量更新ORGID
                handmobileService.updateList(updateorgidList, handmobileinfo
                        .getOrganization_id());
            }

        } catch (BusinessException e) {
            log.error(addTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("handmobile.addsuccess.message");
        // 设置操作描述
        this.addOperationLog(formatLog(addTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_HANDMOBILE_INSERT_ID);
        return SUCCESS;
    }

    /**
     * 车辆批量去除分配
     * @return
     */
    public String batchCancleHandmobile() {
        final String cancleTitle = getText("handmobile.batch.cancle");
        // 获取当前操作用户信息
        UserInfo user = getCurrentUser();

        // 所选去除分配车辆VIN确认
        if (carsVinInfos != null && carsVinInfos != ""
                && !carsVinInfos.equals("")) {

            // 拆分车辆VIN
            String carsVin[] = carsVinInfos.split(",");
            List < HandMobileInfo > vInfos = new ArrayList < HandMobileInfo >();
            // 去除分配车辆VIN进行集合统计
            for (int i = 0; i < carsVin.length; i++) {

                HandMobileInfo vi = new HandMobileInfo();
                vi.setVehicle_vin(carsVin[i]);
                // 设置修改人-当前操作者
                vi.setModifier(user.getUserID());
                vInfos.add(vi);
            }

            try {
                // 批量去除车辆分配
                handmobileService.batchCancle(vInfos);
            } catch (BusinessException e) {
                log.error(cancleTitle, e);
                setMessage("canle.handmobile.error");
                return ERROR;
            }

        } else {
            // 没有选择车辆返回提示，重新操作
            setMessage("choose.canle.handmobile");
            return ERROR;
        }
        setMessage("handmobile.canclesuccess.message");
        // 设置操作描述
        this.addOperationLog(formatLog(cancleTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_HANDMOBILE_BATCHCANCLE_ID);

        return SUCCESS;

    }

    /** 加载树 **/
    public String getTreeInit() {
        final String addBefTitle = getText("handmobile.load.tree");
        log.info(addBefTitle);
        UserInfo user = getCurrentUser();
        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {
            map.put("in_enterprise_id", user.getOrganizationID());
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("HandMobileManage.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            }
            // leftList = service.getObjects("VehicleManage.getVehicledesc",
            // user
            // .getEntiID());
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
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, HandMobileInfo handmobileObj) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != handmobileObj) {
            if (null != handmobileObj.getVehicle_id()) {
                OperateLogFormator.format(sb, "vehicleid", handmobileObj
                        .getVehicle_id());
            }
        }
        return sb.toString();
    }

}
