package com.neusoft.clw.sysmanage.sysset.ocktimeset.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.sysmanage.datamanage.rolemanage.domain.Role;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.sysmanage.sysset.ocktimeset.domain.OckTimeSet;
import com.neusoft.clw.sysmanage.sysset.oilset.domain.OilSet;
import com.opensymphony.xwork2.ActionContext;

public class OckTimeSetAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    /** 显示数据list **/
    private List < OckTimeSet > ocktimesetList;

    private UserInfo userinfo;

    /** 修改数据用 **/
    private OckTimeSet ocktimeset;

    /** 显示消息 **/
    private String message = null;

    /**
     * 错误提示信息
     */
    private String errorMessage;

    private final String FORBID = "forbid";

    // add by jinp start
    private Map map = new HashMap();

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    private String check_time_code = "";

    public String getCheck_time_code() {
        return check_time_code;
    }

    public void setCheck_time_code(String check_time_code) {
        this.check_time_code = check_time_code;
    }

    // add by jinp stop

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 修改数据时判断重复使用
     */
    private String oldcode;

    public String getOldcode() {
        return oldcode;
    }

    public void setOldcode(String oldcode) {
        this.oldcode = oldcode;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List < OckTimeSet > getOcktimesetList() {
        return ocktimesetList;
    }

    public void setOcktimesetList(List < OckTimeSet > ocktimesetList) {
        this.ocktimesetList = ocktimesetList;
    }

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public OckTimeSet getOcktimeset() {
        return ocktimeset;
    }

    public void setOcktimeset(OckTimeSet ocktimeset) {
        this.ocktimeset = ocktimeset;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map < String, String > getMonthSysMap() {
        return monthSysMap;
    }

    public void setMonthSysMap(Map < String, String > monthSysMap) {
        this.monthSysMap = monthSysMap;
    }

    /** 是否编码 **/
    private Map < String, String > monthSysMap = new HashMap < String, String >();

    // add by jinp start
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

    // add by jinp stop

    // add by jinp start
    public Map getPagination(List list, int totalCountDay, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {

            OckTimeSet s = (OckTimeSet) list.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getCheck_time_id());

            cellMap.put("cell",
                    new Object[] {s.getCheck_time_code(), s.getStart_time(),
                            s.getEnd_time(), s.getCheck_time_desc() });

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    // add by jinp stop

    /**
     * 浏览消息配置信息
     * @return
     */
    public String ockTimeSetBrowse() {
        HttpServletRequest request = ServletActionContext.getRequest();
        final String browseTitle = getText("ocktime.browse.title");
        log.info(browseTitle);
        try {
            if (null == ocktimeset) {
                ocktimeset = new OckTimeSet();
            }
            UserInfo user = getCurrentUser();
            int totalCount = 0;
            ocktimeset.setEnterprise_id(user.getEntiID());
            

            // add by jinp start
            // 每页显示条数
            String rpNum = request.getParameter("rp");
            // 当前页码
            String pageIndex = request.getParameter("page");
            // 排序字段名
            String sortName = request.getParameter("sortname");
            // 升序OR降序
            String sortOrder = request.getParameter("sortorder");
            ocktimeset.setSortname(sortName);
            ocktimeset.setSortorder(sortOrder);

            // 查询条件
            if (null != check_time_code && !"".equals(check_time_code)) {
                ocktimeset.setCheck_time_code(check_time_code);
            }

            // add by jinp stop

            totalCount = service.getCount("OckTimeSet.getCount", ocktimeset);
            // modify by jinp start
            // Page pageObj = new Page(page, totalCount, pageSize, url, param);
            // this.pageBar = PageHelper.getPageBar(pageObj);
            ocktimesetList = service.getObjectsByPage("OckTimeSet.getInfos",
                    ocktimeset, (Integer.parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            setMap(getPagination(ocktimesetList, totalCount, pageIndex));
            // modify by jinp end

            if (ocktimesetList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 用于添加或者删除时显示消息
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
            this.setModuleId(MouldId.YTP_OCKTIMESET_QUERY_ID);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String addBefore() {

        // final String addTitle = getText("dirver.browse.title");
        // if (monthSysMap != null && monthSysMap.size() == 0) {
        // monthSysMap = Constants.MONTH_SYS_MAP;
        // }
        if (null == ocktimeset) {
            ocktimeset = new OckTimeSet();
        }
        ocktimeset.setCheck_time_code(DateUtil.getMonth());
        return SUCCESS;
    }

    /**
     * 添加考核油耗月度
     */
    public String addTimeSet() {
        if (null == ocktimeset) {
            return addBefore();
        }
        final String addTitle = getText("ocktime.add.title");
        try {
            UserInfo user = getCurrentUser();
            ocktimeset.setCreater(user.getUserID());
            ocktimeset.setCheck_time_id(UUIDGenerator.getUUID());
            ocktimeset.setEnterprise_id(user.getEntiID());
            service.insert("OckTimeSet.insertOckTimeSetInfo", ocktimeset);
        } catch (BusinessException e) {
            log.error(addTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("ocktime.add.success");
        // 设置操作描述
        this.addOperationLog(formatLog(addTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.INSERT);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_OCKTIMESET_ADD_ID);
        return SUCCESS;
    }

    /**
     * 修改考核油耗月度设置页面
     */
    public String editBefore() {
        final String editBefTitle = getText("ocktime.editbefore.title");
        log.info(editBefTitle);

        // add by jinp start
        HttpServletRequest request = ServletActionContext.getRequest();
        // add by jinp stop

        try {
            if (null != ocktimeset) {
                // if (monthSysMap != null && monthSysMap.size() == 0) {
                // monthSysMap = Constants.MONTH_SYS_MAP;
                // }
                // modify by jinp start
                String check_time_id = request.getParameter("check_time_id");

                if (null == check_time_id) {
                    check_time_id = ocktimeset.getCheck_time_id();
                }

                ocktimeset = (OckTimeSet) service.getObject(
                        "OckTimeSet.getOckTimeSetInfo", check_time_id);

                if (null != ocktimeset) {
                    setOldcode(ocktimeset.getCheck_time_code());
                }
                // modify by jinp end
            } else {
                // setMessage("info.data.notexsist");
                return ERROR;
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(editBefTitle, e);
            return ERROR;
        }
        if (null != errorMessage) {
            addActionError(getText(errorMessage));
        }
        return SUCCESS;
    }

    /**
     * 修改考核油耗月度配置
     */
    public String updateTimeSet() {
        if (null == ocktimeset) {
            return editBefore();
        }
        final String editTitle = getText("ocktime.update.title");
        try {
            UserInfo user = getCurrentUser();
            ocktimeset.setModifier(user.getUserID());

            service.update("OckTimeSet.updatebyOckTimeSetid", ocktimeset);
        } catch (BusinessException e) {
            log.error(editTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("ocktime.update.success");
        // 设置操作描述
        this.addOperationLog(formatLog(editTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_OCKTIMESET_UPDATE_ID);
        return SUCCESS;
    }

    /**
     * 删除考核油耗月度配置
     */
    public String deleteTimeSet() {
        if (null == ocktimeset) {
            return editBefore();
        }
        final String cancleTitle = getText("ocktime.delete.title");
        try {
            UserInfo user = getCurrentUser();
            ocktimeset.setVaset_user_id(user.getUserID());

            service.update("OckTimeSet.deletebyOckTimeSetid", ocktimeset);

        } catch (BusinessException e) {
            log.error(cancleTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("ocktime.del.success");
        // 设置操作描述
        this.addOperationLog(formatLog(cancleTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.DELETE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_OCKTIMESET_DEL_ID);

        return SUCCESS;

    }

    /**
     * 验证考核月度是否唯一
     */
    public boolean checkCodeIdUnique(String code, String enid) {
        boolean ret = false;
        try {
            OckTimeSet ocktimesetdwr = new OckTimeSet();
            ocktimesetdwr.setCheck_time_code(code);
            ocktimesetdwr.setEnterprise_id(enid);
            int cnt = service.getCount("OckTimeSet.getCountforcode",
                    ocktimesetdwr);
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("考核月度唯一查询DWR异常发生，异常原因：" + e.getMessage());
        }
        return ret;
    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, OckTimeSet ocktimeset) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != ocktimeset) {
            if (null != ocktimeset.getCheck_time_id()) {
                OperateLogFormator.format(sb, "Check_time_id", ocktimeset
                        .getCheck_time_id());
            }
        }
        return sb.toString();
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

}
