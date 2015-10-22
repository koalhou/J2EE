package com.neusoft.clw.sysmanage.datamanage.usermanage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.usermanageservice.UserManageService;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.md5.MD5digest;
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.sysmanage.datamanage.entimanage.domain.EnterpriseResInfo;
import com.neusoft.clw.sysmanage.datamanage.rolemanage.domain.Role;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.ST_PR_Info;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserRoleInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.sysmanage.datamanage.zonemanage.domain.ZoneXsInfo;
import com.opensymphony.xwork2.ActionContext;

public class userupdateAction extends BaseAction {

    private static final String VALIDEFLG_TRUE = "0";

    private static final String VALIDEFLG_FALSE = "1";

    private transient Service service;

    private transient UserManageService usermanageservice;
    
    /** 系统所有角色信息 **/
    private Map roleMap = new HashMap();
    
    /** 用户选中的角色信息 **/
    private Map selectMap = new HashMap();
    
    /** 显示消息 * */
    private String message;

    private String userID = "";

    private boolean resetPwd = false;

    /** 性别编码 * */
    private Map < String, String > sexSysMap = new HashMap < String, String >();

    /** 用户类型 * */
    private Map < String, String > userTypeSysMap = new HashMap < String, String >();

    private UserInfo userinfo;

    /** 国家信息 * */
    private Map < String, String > countryInfosMap = new HashMap < String, String >();

    /** 省/直辖市信息 * */
    private Map < String, String > provinceInfosMap = new HashMap < String, String >();

    /** 市/县信息 * */
    private Map < String, String > cityInfosMap = new HashMap < String, String >();

    /** 加载用户信息 * */
    public String initUserinfo() {
        MDC.put("modulename", "[userupdate]");
        
        log.info("[进入用户修改页面]");

        // 性别
        if (sexSysMap != null && sexSysMap.size() == 0) {
            sexSysMap = Constants.SEX_SYS_MAP;
        } else {
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        if (userTypeSysMap != null && userTypeSysMap.size() == 0) {
            userTypeSysMap = Constants.USERTYPE_SYS_MAP;
        } else {
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        try {
            // 加载用户信息
            userinfo = getUser();
            if (userinfo == null) {
                return ERROR;
            }

            if(null == userinfo.getStudent_flag() || "".equals(userinfo.getStudent_flag()) ){
                userinfo.setStudent_flag("1");
            }
            
            //初始化不重置密码
            userinfo.setPwd_update_flag("1");
            
            log.info("[当前用户-学生管理标识："+userinfo.getStudent_flag()+"]");
            log.info("[当前用户-学生管理密码："+userinfo.getStudent_pwd()+"]");
            
            if (!getGeoInfos(userinfo)) {
                // 地理信息初始化失败时
                super.addActionError(getText("info.db.error"));
                return ERROR;
            }

            // 加载用户角色信息
            initUserRole();
           
            // 加载学生信息
            initUserStudent();

            // /setuserInfo(userinfo);

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    /** 加载用户信息 -个人信息 * */
    public String initModifyPersonalInfo() {

        log.debug(getText(message));
        // 性别
        if (sexSysMap != null && sexSysMap.size() == 0) {
            sexSysMap = Constants.SEX_SYS_MAP;
        } else {
            super.addActionError(getText("info.db.error"));

            // addActionMessage(message);
            return ERROR;
        }
        log.debug("加载用户信息");
        try {
            // 加载用户信息
            userinfo = getPersonal();
            if (userinfo == null) {
                return ERROR;
            }
                   
            log.debug("地理信息初始化");
            if (!getGeoInfos(userinfo)) {
                // 地理信息初始化失败时
                super.addActionError(getText("info.db.error"));
                return ERROR;
            }
            if (message != null) {
                addActionMessage(getText(message));
            }

        } catch (BusinessException e) {
            log.error("初始修改个人信息异常", e);
            return ERROR;
        }
        log.debug("初始成功");
        return SUCCESS;
    }

    public Map < String, String > getUserTypeSysMap() {
        return userTypeSysMap;
    }

    public void setUserTypeSysMap(Map < String, String > userTypeSysMap) {
        this.userTypeSysMap = userTypeSysMap;
    }

    private void initUserRole() throws BusinessException {
        String roleid = "";
        String rolename = "";

        List < UserRoleInfo > list = service.getObjects("User.getUserRoleinfo",
                userinfo.getUserID());
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {

                if (i == list.size() - 1) {
                    roleid += list.get(i).getROLE_ID();
                    rolename += list.get(i).getROLE_NAME();
                } else {
                    roleid += list.get(i).getROLE_ID() + ",";
                    rolename += list.get(i).getROLE_NAME() + ",";
                }

            }
        }
        userinfo.setRoleId(roleid);
        userinfo.setRoleName(rolename);

    }
    
    /**
     * 加载学生信息
     **/
    private void initUserStudent() throws BusinessException {
        String stu_card_id = "";
        String stu_name = "";

        List < ST_PR_Info > list = service.getObjects("User.getST_PRinfo",
                userinfo.getUserID());
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {

                if (i == list.size() - 1) {
                	stu_card_id += list.get(i).getStu_id();
                	stu_name += list.get(i).getStu_name();
                } else {
                	stu_card_id += list.get(i).getStu_id() + ",";
                	stu_name += list.get(i).getStu_name() + ",";
                }

            }
        }
        userinfo.setStu_id(stu_card_id);
        userinfo.setStu_name(stu_name);

    }

    /** 更新用户信息 * */
    public String updateuserinfo() {

        if (userinfo != null) {
            userinfo.setModifier(getCurrentUser().getUserID());

            try {      
                log.info("[是否重置密码："+userinfo.getPwd_update_flag()+"]");
                //重新设置密码
                if("0".equals(userinfo.getPwd_update_flag())){
                    log.info("[重置的密码："+userinfo.getStudent_pwd().trim()+"]");
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("userId", userinfo.getUserID());
                    map.put("student_flag", userinfo.getStudent_flag());
                    map.put("student_pwd", MD5digest.generatePassword(userinfo.getStudent_pwd().trim()));
                    service.update("User.resetUserFAP", map);
                }
                
                
                //修改用户信息
                usermanageservice.batchUserAndRoleupdateList(userinfo);
                service.delete("User.deleteST_PR_Info", userinfo);
                String st_id = userinfo.getStu_id();
                String st_name = userinfo.getStu_name();
                if(!"5".equals(userinfo.getUserType())){
                	st_id =null;	
                	st_name=null;
                }              
                if(StringUtils.isNotEmpty(st_id)&& StringUtils.isNotEmpty(st_name)){
                	String[]st_id_list = st_id.split(",");
                	String[]st_name_list = st_name.split(",");
                	for(int i=0;i<st_id_list.length;i++){
                    	ST_PR_Info info = new ST_PR_Info();
                    	info.setPr_userid(userinfo.getUserID());
                    	info.setStu_id(st_id_list[i]);
                    	info.setStu_name(st_name_list[i]);
                    	service.insert("User.insertST_PR_Info", info);
                    }	
                }
                addActionMessage(getText("userinfo.update.success"));
                return SUCCESS;

            } catch (BusinessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                addActionError(getText(e.getMessage()));
                return ERROR;
            } catch (DataAccessIntegrityViolationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                addActionError(getText(e.getMessage()));
                return ERROR;
            } catch (DataAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                addActionError(getText(e.getMessage()));
                return ERROR;
            } finally {
                // add by jinp start
                // 设置操作描述
                this.addOperationLog("用户修改");
                // 设置操作类型
                this.setOperationType(Constants.UPDATE);
                // 设置所属应用系统
                this.setApplyId(Constants.XC_P_CODE);
                // 设置所属模块
                this.setModuleId(MouldId.XCP_USERMANAGE_UPDATE_ID);
                // add by jinp stop
            }
        } else {
            return ERROR;
        }

    }

    /** 更新个人用户信息 * */
    public String modifyPersonalInfo() {

        if (userinfo != null) {
            userinfo.setModifier(getCurrentUser().getUserID());

            try {
                usermanageservice.modifyPersonalInfo(userinfo);

                setMessage("userinfo.update.success");
                addActionMessage(getText(message));

                log.info(getText(message));

                return SUCCESS;

            } catch (BusinessException e) {
                e.printStackTrace();
                addActionError(getText(e.getMessage()));
                return ERROR;
            } catch (DataAccessIntegrityViolationException e) {
                e.printStackTrace();
                addActionError(getText(e.getMessage()));
                return ERROR;
            } catch (DataAccessException e) {
                e.printStackTrace();
                addActionError(getText(e.getMessage()));
                return ERROR;
            } finally {
                // add by jinp start
                // 设置操作描述
                this.addOperationLog("用户个人信息修改");
                // 设置操作类型
                this.setOperationType(Constants.UPDATE);
                // 设置所属应用系统
                this.setApplyId(Constants.CLW_P_CODE);
                // 设置所属模块
                this.setModuleId(MouldId.YTP_USERMANAGE_UPDATE_ID);
                // add by jinp stop
            }
        } else {
            return ERROR;
        }

    }

    /**
     * 树获取页面
     * @return
     */
    public String getTreeInit() {
        final String addBefTitle = getText("route.gettree.title");
        log.info(addBefTitle);
        String tree_script = "";
        String ChooseEnterID_tree = "";
        try {

            Map < String, Object > map = new HashMap < String, Object >(4);
            /* 显示树 */
            UserInfo user = getCurrentUser();
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
            userinfo = getUser();
            if (null != userinfo) {
                if (null != userinfo.getOrganizationID()) {
                    Map < String, Object > enmap = new HashMap < String, Object >(
                            5);
                    enmap.put("in_enterprise_id", user.getOrganizationID());
                    enmap.put("in_org_id", userinfo.getOrganizationID());
                    enmap.put("out_flag", null);
                    enmap.put("out_message", null);
                    enmap.put("out_ref", null);
                    service.getObject("VehicleManage.show_enterprise_id",
                                    enmap);
                    if ("0".equals(enmap.get("out_flag"))) {
                        ArrayList < VehcileInfo > enallid = (ArrayList < VehcileInfo >) enmap
                                .get("out_ref");
                        StringBuffer enid = new StringBuffer("");
                        for (int i = 0; i < enallid.size(); i++) {
                            VehcileInfo veinfo = enallid.get(i);
                            enid.append(veinfo.getEnterprise_id());
                            if (i < (enallid.size() - 1)) {
                                enid.append("|");
                            }
                        }
                        ChooseEnterID_tree = enid.toString();
                    }
                }
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addBefTitle, e);
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
            ActionContext.getContext().getSession().put("ChooseEnterID_tree",
                    ChooseEnterID_tree);
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

    private int updateueser(UserInfo userinfo) throws BusinessException {

        int i = service.update("User.updateUserInfo", userinfo);
        //System.out.println(i);
        return i;

    }

    /** 查询用户信息 * */
    private UserInfo getUser() throws BusinessException {
        UserInfo user = getCurrentUser();
        Map < String, String > map = new HashMap < String, String >();
        map.put("userID", userID);
        map.put("organization_id", user.getOrganizationID());

        UserInfo uinfo = (UserInfo) service.getObject(
                "User.getConditionselect", map);

        return uinfo;
    }

    /** 查询个人用户信息 * */
    private UserInfo getPersonal() throws BusinessException {
        UserInfo user = getCurrentUser();
        Map < String, String > map = new HashMap < String, String >();
        map.put("userID", user.getUserID());

        UserInfo uinfo = (UserInfo) service.getObject("User.getPersonal", map);

        return uinfo;
    }

    /**
     * 获取地理信息
     * @return
     */
    private boolean getGeoInfos(UserInfo loginuserInfo) {

        // 地理信息列表
        List < ZoneXsInfo > list = new ArrayList < ZoneXsInfo >();
        Map < String, Object > mapPar = new HashMap < String, Object >(1);

        try {
            // 国家
            mapPar.put("zone_parent_id", null);
            list = service.getObjects("ZoneManage.getSelectList", mapPar);
            for (ZoneXsInfo zoneXsInfo : list) {
                countryInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                        .getZone_name());
            }

            // 省/直辖市

            if (loginuserInfo != null && loginuserInfo.getCountyID() != ""
                    && loginuserInfo.getCountyID() != null) {
                mapPar.put("zone_parent_id", loginuserInfo.getCountyID());

                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    provinceInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
            // 市/县
            if (loginuserInfo != null && loginuserInfo.getProvinceID() != ""
                    && loginuserInfo.getProvinceID() != null) {
                mapPar.put("zone_parent_id", loginuserInfo.getProvinceID());
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    cityInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return false;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return false;
        }

        return true;
    }

    /** 修改用户是 单击角色窗口加载 **/
    public String userUpdateRoleInit() {
        
        try {
            //加载角色信息
            getUserRole();
            if (roleMap == null) {
                roleMap = new HashMap();
            }
          
            
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }
    
    /** 删除重复的角色信息 **/
    @SuppressWarnings("rawtypes")
    private void removeRepeatInfo(Map roleMap,Map roleInitMap) throws BusinessException{
        
        Iterator itRole = roleInitMap.keySet().iterator();
        
        while(itRole.hasNext()){
            roleMap.remove(itRole.next().toString());
        } 
    }
    
    /** 查询系统所有角色 **/
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void getUserRole() throws BusinessException {
        UserInfo user = getCurrentUser();
        
        Map maps = new HashMap();
        maps.put("enterprise_id", user.getEntiID());

        List <Role> list = (List < Role >) service.getObjects(
                "Role.getInfos", maps);
        
        String[] roleArray = null;
        if(userinfo.getRoleId() != null) {
            roleArray = userinfo.getRoleId().split(",");
        }
         
        for(Role role : list) {
            //已经选中的角色信息获取
            for(int i = 0; i < roleArray.length; i++) {
                if(roleArray[i] != null && roleArray[i].equals(role.getRole_id())) {
                    selectMap.put(role.getRole_id(), role.getRole_name());
                    break;
                }
            }
            if(selectMap.containsKey(role.getRole_id())) {
                continue;
            } else {
                roleMap.put(role.getRole_id(), role.getRole_name());
            }
        }

    }
    
    
    /** 用户具有的角色信息 **/
    @SuppressWarnings("unchecked")
    private Map getUserInitRole() throws BusinessException {
        
        log.info("[修改用户的用户ID("+userID+")]");
        
        Map maps = new HashMap();
        maps.put("userID",userID);
        
        List < Role > list = (List < Role >) service.getObjects(
                "Role.getUserRoleinfo", maps);
        
        log.info("[用户("+userID+") 具有的角色个数("+list.size()+")]");
        
        Map map = new HashMap();
        if (list != null && list.size() > 0) {
            for (Role role : list) {
                map.put(role.getRole_id(), role.getRole_name());
            }
            return map;
        } else {
            return null;
        }
    }
    
    // private void setuserInfo(UserInfo userinfo){
    // userID = userinfo.getUserID();
    // loginName = userinfo.getLoginName();
    // loginPwd = userinfo.getLoginPwd();
    // userType = userinfo.getUserType();
    // userName = userinfo.getUserName();
    // sex = userinfo.getSex();
    // birth = userinfo.getBirth();
    // countyID = userinfo.getCountyID();
    // provinceID = userinfo.getProvinceID();
    // cityID = userinfo.getCityID();
    // moblie = userinfo.getMoblie();
    // tel = userinfo.getTel();
    // email = userinfo.getEmail();
    // idCard = userinfo.getIdCard();
    // duty = userinfo.getDuty();
    // entiID = userinfo.getEntiID();
    // organizationID = userinfo.getOrganizationID();
    // creater = userinfo.getCreater();
    // createTime = userinfo.getCreateTime();
    // modifier = userinfo.getModifier();
    // modifyTime = userinfo.getModifyTime();
    // valideFlg = userinfo.getValideFlg();
    // vUserID = userinfo.getvUserID();
    // vTime = userinfo.getvTime();
    //
    // }

    // 获取页面信息
    // private UserInfo getUserInfo(){
    // UserInfo userinfo = new UserInfo();
    // userinfo.setUserID(userID);
    // userinfo.setLoginName(loginName);
    // userinfo.setLoginPwd(loginPwd);
    // userinfo.setUserType(userType);
    // userinfo.setUserName(userName);
    // userinfo.setSex(sex);
    // userinfo.setBirth(birth);
    // userinfo.setCountyID(countyID);
    // userinfo.setProvinceID(provinceID);
    // userinfo.setCityID(cityID);
    // userinfo.setMoblie(moblie);
    // userinfo.setTel(tel);
    // userinfo.setEmail(email);
    // userinfo.setIdCard(idCard);
    // userinfo.setDuty(duty);
    // userinfo.setEntiID(entiID);
    // userinfo.setOrganizationID(organizationID);
    // userinfo.setCreater(creater);
    // userinfo.setCreateTime(DateUtil.convertLongToDate(System.currentTimeMillis()));
    // userinfo.setModifier(modifier);
    // userinfo.setModifyTime(DateUtil.convertLongToDate(System.currentTimeMillis()));
    // userinfo.setValideFlg(VALIDEFLG_TRUE);
    // userinfo.setvUserID(vUserID);
    // userinfo.setvTime(DateUtil.convertLongToDate(System.currentTimeMillis()));
    // return userinfo;
    // }

    
    
    public Map < String, String > getSexSysMap() {
        return sexSysMap;
    }

    public Map getRoleMap() {
        return roleMap;
    }

    public void setRoleMap(Map roleMap) {
        this.roleMap = roleMap;
    }
    
    public void setSexSysMap(Map < String, String > sexSysMap) {
        this.sexSysMap = sexSysMap;
    }

    public Map < String, String > getCountryInfosMap() {
        return countryInfosMap;
    }

    public void setCountryInfosMap(Map < String, String > countryInfosMap) {
        this.countryInfosMap = countryInfosMap;
    }

    public Map < String, String > getProvinceInfosMap() {
        return provinceInfosMap;
    }

    public void setProvinceInfosMap(Map < String, String > provinceInfosMap) {
        this.provinceInfosMap = provinceInfosMap;
    }

    public Map < String, String > getCityInfosMap() {
        return cityInfosMap;
    }

    public void setCityInfosMap(Map < String, String > cityInfosMap) {
        this.cityInfosMap = cityInfosMap;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public UserManageService getUsermanageservice() {
        return usermanageservice;
    }

    public void setUsermanageservice(UserManageService usermanageservice) {
        this.usermanageservice = usermanageservice;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public boolean isResetPwd() {
        return resetPwd;
    }

    public void setResetPwd(boolean resetPwd) {
        this.resetPwd = resetPwd;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Map getSelectMap() {
        return selectMap;
    }

    public void setSelectMap(Map selectMap) {
        this.selectMap = selectMap;
    }
}
