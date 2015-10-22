package com.neusoft.clw.sysmanage.datamanage.usermanage.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.common.util.md5.MD5digest;

import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.ST_PR_Info;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserRoleInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.sysmanage.datamanage.rolemanage.domain.Role;
import com.neusoft.clw.sysmanage.datamanage.zonemanage.domain.ZoneXsInfo;
import com.opensymphony.xwork2.ActionContext;

public class useraddAction extends BaseAction {

    private static final String VALIDEFLG_TRUE = "0";

    private static final String VALIDEFLG_FALSE = "1";

    private transient Service service;

    private UserInfo loginuserInfo = new UserInfo();

    /** 性别编码 **/
    private Map < String, String > sexSysMap = new HashMap < String, String >();

    /** 用户类型 **/
    private Map < String, String > userTypeSysMap = new HashMap < String, String >();

    private Map roleMap = new HashMap();

    private Map selectMap = new HashMap();
    
    private UserInfo optuserInfo = new UserInfo();

    /** 国家信息 **/
    private Map < String, String > countryInfosMap = new HashMap < String, String >();

    /** 省/直辖市信息 **/
    private Map < String, String > provinceInfosMap = new HashMap < String, String >();

    /** 市/县信息 **/
    private Map < String, String > cityInfosMap = new HashMap < String, String >();

    /** 显示消息 **/
    private String message = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /** 加载角色信息 **/
    public String userAddInit() {
        loginuserInfo = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
        if (!getGeoInfos()) {
            // 地理信息初始化失败时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }
        // 性别
        if (sexSysMap != null && sexSysMap.size() == 0) {
            sexSysMap = Constants.SEX_SYS_MAP;
        }
        // 用户类型
        if (userTypeSysMap != null && userTypeSysMap.size() == 0) {
            userTypeSysMap = Constants.USERTYPE_SYS_MAP;
        }

        return SUCCESS;
    }

    /** 用户角色窗口加载 **/
    public String userRoleInit() {
        try {
            //初始化角色信息
            getUserRole();
            if (roleMap == null) {
                roleMap = new HashMap();
            }
            if(selectMap == null){
                selectMap = new HashMap();
            }

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 获取地理信息
     * @return
     */
    private boolean getGeoInfos() {
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

    public UserInfo getOptuserInfo() {
        return optuserInfo;
    }

    public void setOptuserInfo(UserInfo optuserInfo) {
        this.optuserInfo = optuserInfo;
    }

    /** 查询系统所有角色 **/
    private void getUserRole() throws BusinessException {
        loginuserInfo = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
        Map maps = new HashMap();
        maps.put("enterprise_id", loginuserInfo.getEntiID());

        List < Role > list = (List < Role >) service.getObjects(
                "Role.getInfos", maps);
        
        String[] roleArray = null;
        if(optuserInfo.getRoleId() != null) {
            roleArray = optuserInfo.getRoleId().split(",");
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

    public String userAdd() {
        UserInfo uinfo = getUserInfo();
        log.info("创建用户,userid:"+uinfo.getUserID()+",username:"+uinfo.getUserName()+",ENTID:"+uinfo.getEntiID());
        //System.out.println("userid:" + uinfo.getUserID());
        //System.out.println("username:" + uinfo.getUserName());
        try {

            if (addUserRole(uinfo) == null) {
                service.insert("User.insertUserInfo", uinfo);
            }
            String st_id = uinfo.getStu_id();
            String st_name = uinfo.getStu_name();
            if(StringUtils.isNotEmpty(st_id)&& StringUtils.isNotEmpty(st_name)){
            	String[]st_id_list = st_id.split(",");
            	String[]st_name_list = st_name.split(",");
            	for(int i=0;i<st_id_list.length;i++){
                	ST_PR_Info info = new ST_PR_Info();
                	info.setPr_userid(uinfo.getUserID());
                	info.setStu_id(st_id_list[i]);
                	info.setStu_name(st_name_list[i]);
                	service.insert("User.insertST_PR_Info", info);
                }	
            }
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            addActionError(getText(e.getMessage()));
            return ERROR;
        } finally {
            // add by jinp start
            // 设置操作描述
            this.addOperationLog("用户创建");
            // 设置操作类型
            this.setOperationType(Constants.INSERT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_USERMANAGE_INSERT_ID);
            // add by jinp stop
        }

        addActionMessage(getText("userinfo.create.success"));
        return SUCCESS;
    }

    private Object addUserRole(UserInfo uinfo) throws BusinessException {
        List list = new ArrayList();

        if (uinfo.getRoleId() != null) {

            String[] id = uinfo.getRoleId().split(",");
            //System.out.println(id.length);
            // String[] name = roleNameNum.split("|");
            for (int i = 0; i < id.length; i++) {
                UserRoleInfo ui = new UserRoleInfo();
                ui.setUSER_ID(uinfo.getUserID());
                String rid = id[i];
                ui.setROLE_ID(rid);
                ui.setENTERPRISE_ID("");
                ui.setCREATER(loginuserInfo.getUserID());
                list.add(ui);
            }
            return service.batchUserAndRoleupdateList(
                    "User.bacthupdateUserAndRoleInfo", list);
        } else {
            return null;
        }
        // else{
        // service.batchUserAndRoleupdateList("", list);
        // }

    }

    private UserInfo getUserInfo() {
        loginuserInfo = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
        //System.out.println("登录用户企业id：" + loginuserInfo.getEntiID());

        optuserInfo.setUserID(UUID.randomUUID().toString());

        System.out.println("PWD:"+optuserInfo.getLoginPwd().trim());
        
        optuserInfo.setLoginPwd(MD5digest.generatePassword(optuserInfo
                .getLoginPwd().trim()));
        optuserInfo.setStudent_pwd(MD5digest.generatePassword(optuserInfo.getStudent_pwd().trim()));
        optuserInfo.setEntiID(loginuserInfo.getEntiID());

        optuserInfo.setCreater(loginuserInfo.getUserID());

        optuserInfo.setModifier(loginuserInfo.getUserID());

        optuserInfo.setValideFlg(VALIDEFLG_TRUE);
        optuserInfo.setvUserID(loginuserInfo.getUserID());

        return optuserInfo;
    }

    public boolean IsUserNameSame(String loginName, String entiID) {
        // loginuserInfo =
        // (UserInfo)ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("entiID", entiID);
        map.put("loginName", loginName);
        log.info(entiID);
        log.info(loginuserInfo.getEntiID());
        try {
            int i = service.getCount("User.getUserNameSameCount", map);
            log.info(i);
            if (i > 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 检查权限中是否具有[学生管理]的系统功能]
     * @param roleids
     * @return 如果包含返回true 否则返回false
     */
    public boolean queryRoleRight(String roleids){
        MDC.put( "modulename", "[queryRoleRight]" );
        log.info("[查询数据库 检查权限中是否具有[学生管理]的系统功能]");
        log.info("[角色ID列表("+roleids+")]");
        //roleids 的 格式 是 A,B,C
        //将roleids转换为 'A','B','C'
        String roleidsstr = SqlStringUtil.getSqlA(roleids);
        log.info("[角色ID字符串列表("+roleidsstr+")]");
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("roleids", roleidsstr);
        map.put("module_id", MouldId.XCP_STUMANAGE_MODULE_ID);
        
        log.info("[学生管理模块ID("+MouldId.XCP_STUMANAGE_MODULE_ID+")]");
        
        try {
            int i = service.getCount("User.getSMMICountFromRole", map);
            log.info(i);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public Map getRoleMap() {
        return roleMap;
    }

    public void setRoleMap(Map roleMap) {
        this.roleMap = roleMap;
    }

    public Map < String, String > getSexSysMap() {
        return sexSysMap;
    }

    public Map < String, String > getUserTypeSysMap() {
        return userTypeSysMap;
    }

    public void setUserTypeSysMap(Map < String, String > userTypeSysMap) {
        this.userTypeSysMap = userTypeSysMap;
    }

    public void setSexSysMap(Map < String, String > sexSysMap) {
        this.sexSysMap = sexSysMap;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
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

    public Map getSelectMap() {
        return selectMap;
    }

    public void setSelectMap(Map selectMap) {
        this.selectMap = selectMap;
    }
}
