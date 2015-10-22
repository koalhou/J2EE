package com.neusoft.clw.sysmanage.datamanage.usermanage.action;

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
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.md5.MD5digest;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.sysmanage.datamanage.entimanage.domain.EnterpriseResInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.opensymphony.xwork2.ActionContext;

public class usermanageAction extends PaginationAction {

    private transient Service service;

    private List < UserInfo > userbeanList;

    private String userid;

    private String organizidtreeid;

    private String loginName;

    private String validFlag;
    
    private String flag;
    
    /** 用户ID **/
    private String userId = "";
    
    /** 用户密码**/
    private String password;
    
    /** 用户确认密码**/
    private String confirmPassword;
           
    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/** 页面消息 **/
    private String message = null;

    // add by jinp start
    private Map map = new HashMap();

    private static final Map < String, String > USER_STATUS = new HashMap < String, String >();

    static {
        USER_STATUS.put("3", "全部");
        USER_STATUS.put("0", "可用");
        USER_STATUS.put("2", "禁用");
    }

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    // add by jinp stop

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getOrganizidtreeid() {
        return organizidtreeid;
    }

    public void setOrganizidtreeid(String organizidtreeid) {
        this.organizidtreeid = organizidtreeid;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    private List < UserInfo > oneuserList;

    /** 左侧list **/
    private List < VehcileInfo > leftList = null;

    // add by jinp start
    /**
     * 页面初始化
     * @return
     */
    public String init() {
    	if(getMessage()!=null){
        	addActionMessage(getText(getMessage()));
        }     
        return SUCCESS;
    }

    // add by jinp stop

    // add by jinp start
    public Map getPagination(List list, int totalCountDay, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {

            UserInfo s = (UserInfo) list.get(i);

            Map cellMap = new LinkedHashMap();
            String userType = s.getUserType();
            s.setUserType(Constants.USERTYPE_SYS_MAP.get(s.getUserType()));
            s.setSex(Constants.SEX_SYS_MAP.get(s.getSex()));
            s.setValideFlg(USER_STATUS.get(s.getValideFlg()));

            cellMap.put("id", s.getUserID());

            cellMap.put("cell", new Object[] {s.getLoginName(),
                    s.getUserType(), s.getUserName(), s.getSex(),
                    s.getMoblie(), s.getEmail(), s.getOrganizationName(),
                    s.getValideFlg(),s.getStudent_flag(),userType});

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    // add by jinp stop

    /** 加载用户信息表 **/
    public String userInit() {
        HttpServletRequest request = ServletActionContext.getRequest();

        int totalCount = 0;
        try {

            Map < String, Object > map = new HashMap < String, Object >();
            // map.put("entiID", getCurrentUser().getEntiID());
            UserInfo user = getCurrentUser();
            if (null != organizidtreeid && !"".equals(organizidtreeid)) {
                map.put("organizidtreeID", organizidtreeid);
            }

            if (null != loginName && !"".equals(loginName)) {
                map.put("loginName", loginName);
            }
            if ("yutongsuperadmin".equals(user.getUserID())) {
                map.put("creater", null);
            } else {
                map.put("creater", user.getUserID());
            }
            map.put("organization_id", user.getOrganizationID());
            // add by:huangmb start
            if(validFlag == null && flag!=null && !"".equals(flag)){
            	validFlag= flag;
            }
            if (!"3".equals(validFlag)) {
                map.put("validFlag", validFlag);
            }
           
            // add by:huangmb end
            totalCount = service.getCount("User.getConditionselectCount", map);
            //System.out.println("页数：" + totalCount);

            // add by jinp start
            // 每页显示条数
            String rpNum = request.getParameter("rp");
            // 当前页码
            String pageIndex = request.getParameter("page");
            // 排序字段名
            String sortName = request.getParameter("sortname");
            // 升序OR降序
            String sortOrder = request.getParameter("sortorder");

            map.put("sortname", sortName);
            map.put("sortorder", sortOrder);

            userbeanList = service.getObjectsByPage("User.getConditionselect",
                    map, (Integer.parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
            setMap(getPagination(userbeanList, totalCount, pageIndex));
            // modify by jinp end

            // if(userbeanList != null && userbeanList.size()>0){
            // System.out.println("用户数量"+userbeanList.size());
            // return SUCCESS;
            // }
            // else{
            // return SUCCESS;
            // }
            if(getMessage()!=null){
            	addActionMessage(getMessage());
            }           
            return SUCCESS;
        } catch (BusinessException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
            return ERROR;
        } finally {
            // add by jinp start
            // 设置操作描述
            this.addOperationLog("用户查询");
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_USERMANAGE_QUERY_ID);
            // add by jinp stop
        }

    }

    /** 获取单一用户信息 **/
    public String getUserInfo() {
        //System.out.println("userid:" + userid);
        UserInfo user = getCurrentUser();
        Map < String, String > map = new HashMap < String, String >();
        map.put("userID", userid);
        map.put("organization_id", user.getOrganizationID());

        try {
            oneuserList = service.getObjects("User.getConditionselect", map);
            if (oneuserList != null && oneuserList.size() > 0) {
                //System.out.println("用户数量" + oneuserList.size());

                return SUCCESS;
            } else {
                return ERROR;
            }

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ERROR;
        }

    }

    public String getconditionselect() {
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        Map < String, String > map = new HashMap < String, String >();
        // map.put("loginName", loginName);
        map.put("organizidtreeID", organizidtreeid);
        map.put("organization_id", user.getOrganizationID());

        try {
            totalCount = service.getCount("User.getConditionselectCount", map);
            //System.out.println("页数：" + totalCount);

            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            // userbeanList = service.getObjects("User.getConditionselect",
            // map);
            userbeanList = service.getObjectsByPage("User.getConditionselect",
                    map, pageObj.getStartOfPage(), pageSize);

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return SUCCESS;
    }

    /** 加载树 **/
    public String getTreeInit() {
        final String addBefTitle = getText("navinfo.add.title");
        log.info(addBefTitle);
        UserInfo user = getCurrentUser();
        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {
            map.put("in_enterprise_id", user.getOrganizationID());
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("VehicleManage.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            }
            leftList = service.getObjects("VehicleManage.getVehicledesc", user
                    .getEntiID());
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
     * 重置密码初始化
     * @return
     */
    public String resetPwdBefore() {
        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }
    
    /**
     * 重置模块密码
     * @return
     */
    public String reset() {
        // 判断用户ID是否为空
        if (userId == "" || userId == null) {
            setMessage("请选择用户！");
            return ERROR;
        }

        Map < String, String > map = new HashMap < String, String >();
        map.put("password", MD5digest
                .generatePassword(password.trim()));
        map.put("userId", userId);

        try {
            // 重置密码
            service.update("User.resetUserPwd", map);
            setMessage("user.reset.success");
        } catch (BusinessException e) {
        	setMessage("info.db.error");
            log.error(e.getMessage());
            setUserId(userId);
            return ERROR;
        } catch (Exception e) {
        	setMessage("info.db.error");
            log.error(e.getMessage());
            setUserId(userId);
            return ERROR;
        } finally {
            addOperationLog("重置管理密码");
        }

        return SUCCESS;
    }
    
    /**
     * 重置用户模块密码
     * @return
     */
    public String resetP() {
        // 判断用户ID是否为空
        if (userId == "" || userId == null) {
            setMessage("请选择用户！");
            return ERROR;
        }

        Map < String, String > map = new HashMap < String, String >();
        map.put("password", MD5digest
                .generatePassword(password.trim()));
        map.put("userId", userId);

        try {
            // 重置密码
            service.update("User.resetUserPwdP", map);
            setMessage("user.resetP.success");
        } catch (BusinessException e) {
        	setMessage("info.db.error");
            log.error(e.getMessage());
            setUserId(userId);
            return ERROR;
        } catch (Exception e) {
        	setMessage("info.db.error");
            log.error(e.getMessage());
            setUserId(userId);
            return ERROR;
        } finally {
            addOperationLog("重置用户密码");
        }

        return SUCCESS;
    }
    
    public List < VehcileInfo > getLeftList() {
        return leftList;
    }

    public void setLeftList(List < VehcileInfo > leftList) {
        this.leftList = leftList;
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    public List < UserInfo > getUserbeanList() {
        return userbeanList;
    }

    public void setUserbeanList(List < UserInfo > userbeanList) {
        this.userbeanList = userbeanList;
    }

    public List < UserInfo > getOneuserList() {
        return oneuserList;
    }

    public void setOneuserList(List < UserInfo > oneuserList) {
        this.oneuserList = oneuserList;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}
