package com.neusoft.clw.sysmanage.datamanage.vehiclemanage.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.vehiclemanageservice.VehicleManageService;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.UnicodeConverter;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.sysmanage.datamanage.drivermanage.domain.DriverInfo;
import com.neusoft.clw.sysmanage.datamanage.entimanage.domain.EnterpriseResInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.opensymphony.xwork2.ActionContext;
import com.sun.net.ssl.internal.www.protocol.https.BASE64Encoder;

public class VehiclemanageAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    /** 显示数据list **/
    private List < VehcileInfo > vehcList;

    /** 左侧list **/
    private List < VehcileInfo > leftList = null;

    /** 批量更新orgid */
    private transient VehicleManageService vehmanageService;

    /** 驾驶员列表 **/
    private List < DriverInfo > driverList;

    /** 车主用户列表 **/
    private List < UserInfo > userList;

    /**
     * 车辆批量去除分配VIN字符串组合
     */
    private String carsVinInfos;

    public String getCarsVinInfos() {
        return carsVinInfos;
    }

    public void setCarsVinInfos(String carsVinInfos) {
        this.carsVinInfos = carsVinInfos;
    }

    public List < UserInfo > getUserList() {
        return userList;
    }

    public void setUserList(List < UserInfo > userList) {
        this.userList = userList;
    }

    private DriverInfo driverInfo;

    private String message = null;
    
    private String messageFlag = null;

    private VehcileInfo vehcileInfo;

    private String vehcileVin;
    
    private String vehcileCode;
    
    private String vehcileLn;

    private String organization_id;
    
    /** 选择的文件 */
    private File file;

    /** 选择的文件类型 */
    private String fileContentType;

    /** 文件名 */
    private String fileFileName;
    
    private ByteArrayInputStream inputStream;
    
    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    private String organization_idold;

    /** 修改时用车牌号验证唯一 **/
    private String oldvehcileLn;

    /** 修改时用车牌内部编码验证唯一 **/
    private String oldvehcileCode;
    
    /** 修改时用车牌颜色与核心同步 **/
    private String oldVehcileColor;

    private Map map = new HashMap();
    
    public String getVehcileLn() {
		return vehcileLn;
	}

	public void setVehcileLn(String vehcileLn) {
		this.vehcileLn = vehcileLn;
	}

	public String getVehcileCode() {
		return vehcileCode;
	}

	public void setVehcileCode(String vehcileCode) {
		this.vehcileCode = vehcileCode;
	}

	public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getOldvehcileLn() {
        return oldvehcileLn;
    }

	public String getOldVehcileColor() {
		return oldVehcileColor;
	}

	public void setOldVehcileColor(String oldVehcileColor) {
		this.oldVehcileColor = oldVehcileColor;
	}

	public void setOldvehcileLn(String oldvehcileLn) {
        this.oldvehcileLn = oldvehcileLn;
    }

    public String getOldvehcileCode() {
        return oldvehcileCode;
    }

    public void setOldvehcileCode(String oldvehcileCode) {
        this.oldvehcileCode = oldvehcileCode;
    }

    public DriverInfo getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(DriverInfo driverInfo) {
        this.driverInfo = driverInfo;
    }

    public List < DriverInfo > getDriverList() {
        return driverList;
    }

    public void setDriverList(List < DriverInfo > driverList) {
        this.driverList = driverList;
    }

    public String getOrganization_idold() {
        return organization_idold;
    }

    public void setOrganization_idold(String organization_idold) {
        this.organization_idold = organization_idold;
    }

    public String getVehcileVin() {
        return vehcileVin;
    }

    public void setVehcileVin(String vehcileVin) {
        this.vehcileVin = vehcileVin;
    }

    public VehcileInfo getVehcileInfo() {
        return vehcileInfo;
    }

    public void setVehcileInfo(VehcileInfo vehcileInfo) {
        this.vehcileInfo = vehcileInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    public String getMessageFlag() {
		return messageFlag;
	}

	public void setMessageFlag(String messageFlag) {
		this.messageFlag = messageFlag;
	}

	public List < VehcileInfo > getLeftList() {
        return leftList;
    }

    public void setLeftList(List < VehcileInfo > leftList) {
        this.leftList = leftList;
    }

    public List < VehcileInfo > getVehcList() {
        return vehcList;
    }

    public void setVehcList(List < VehcileInfo > vehcList) {
        this.vehcList = vehcList;
    }

    /**
     * @return Returns the service.
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service The service to set.
     */
    public void setService(Service service) {
        this.service = service;
    }

    public VehicleManageService getVehmanageService() {
        return vehmanageService;
    }

    public void setVehmanageService(VehicleManageService vehmanageService) {
        this.vehmanageService = vehmanageService;
    }

    public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	// add by jinp start
    /**
     * 页面初始化
     * @return
     */
    public String init() {
        if (null != message) {
        	if("1".equals(messageFlag)){
        		addActionMessage(UnicodeConverter.fromEncodedUnicode(message.toCharArray(),0,message.length()));
        		setMessageFlag("0");//重新设置为"0"
        	}
        	else{
        		addActionMessage(getText(message));
        	}
        }
        return SUCCESS;
    }
    // add by jinp stop

    // add by jinp start
    public Map getPagination(List list, int totalCountDay, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {

            VehcileInfo s = (VehcileInfo) list.get(i);

            Map cellMap = new LinkedHashMap();

            if (null == s.getShort_allname()
                    || s.getShort_allname().length() == 0) {
                s.setShort_allname(getText("vehcileinfo.status.one"));
            }

            if (null == s.getUser_name() || s.getUser_name().length() == 0) {
                s.setUser_name("无车主");
            }
            int flag=0;    //标识位，为1表示没有分配组织   
            if("未分配".equals(s.getShort_allname()))
            {
            	flag=1;
            }

            cellMap.put("id", s.getVehicle_id());

            cellMap.put("cell", new Object[] {
            		null,                 //复选框位置，空出来
            		s.getVehicle_code(),
            		s.getVehicle_ln(),    
            		s.getVehicle_vin(),
            		s.getLimite_number(),//座位数
            		s.getUser_name(), //车主
            		s.getOilbox_capacity(), //最大油箱油量
                    s.getShort_allname(),//所属部门
                    flag
                    });  

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    public Map getPaginationList(List list, int totalCountDay, String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {

            VehcileInfo s = (VehcileInfo) list.get(i);

            Map cellMap = new LinkedHashMap();

            if (null == s.getShort_allname()
                    || s.getShort_allname().length() == 0) {
                s.setShort_allname(getText("vehcileinfo.status.one"));
            }

            if (null == s.getUser_name() || s.getUser_name().length() == 0) {
                s.setUser_name("无车主");
            }

            cellMap.put("id", s.getVehicle_id());

            cellMap.put("cell", new Object[] {(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
            		s.getVehicle_vin(),
                    s.getVehicle_ln(),
                    s.getVehicle_code(),
                    s.getShort_allname(), s.getUser_name() });

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    // add by jinp stop

    /**
     * 车辆管理页面浏览车辆
     * @return
     */
    public String vehiclebrowse() {
        HttpServletRequest request = ServletActionContext.getRequest();
        final String browseTitle = getText("vehcileinfo.browse.title");
        String logid = getlogid();
        log.info("logid:" + logid + "," + browseTitle);
        int totalCount = 0;
        UserInfo user = getCurrentUser();

        try {
            VehcileInfo vehinfo = new VehcileInfo();

            // 条件查询设置组织ID-如果没有输入条件查询则默认查询所有当前用户组织下的车辆
            if (organization_id != "" && organization_id != null
                    && !organization_id.equals("")) {
                vehinfo.setOrganization_id(organization_id);
            } else {
                vehinfo.setOrganization_id(user.getOrganizationID());
            }
            // 设置企业ID
            vehinfo.setEnterprise_id(user.getEntiID());
            // 设置车辆Vin
            if (vehcileVin != "" && vehcileVin != null
                    && !vehcileVin.equals("")) {
                vehinfo.setVehicle_vin(vehcileVin);
            } else {
                vehinfo.setVehicle_vin(null);
            }
            
            
            
            if (vehcileCode != "" && vehcileCode != null
                    && !vehcileCode.equals("")) {
                vehinfo.setVehicle_code(vehcileCode);
            } else {
                vehinfo.setVehicle_code(null);
            }
            
            
            
            
            // 设置车牌
            if (vehcileLn != "" && vehcileLn != null
                    && !vehcileLn.equals("")) {
                vehinfo.setVehicle_ln(vehcileLn);
            } else {
                vehinfo.setVehicle_ln(null);
            }

            if (user.getEntiID().equals(user.getOrganizationID())
            // add by jinp start
                    && !("3".equals(user.getUserType()))
            // add by jinp stop
            ) {
                // 管理员查询
                if (organization_id != "" && organization_id != null
                        && !organization_id.equals("")) {
                    vehinfo.setOrganization_id(organization_id);
                } else {
                    vehinfo.setOrganization_id(null);
                }
                totalCount = service.getCount("VehicleManage.getCountbyEnid",
                        vehinfo);
                // add by jinp start
                // 每页显示条数
                String rpNum = request.getParameter("rp");
                // 当前页码
                String pageIndex = request.getParameter("page");
                // 排序字段名
                String sortName = request.getParameter("sortname");
                // 升序OR降序
                String sortOrder = request.getParameter("sortorder");
                vehinfo.setSortname(sortName);
                vehinfo.setSortorder(sortOrder);
                // add by jinp stop
                // modify by jinp start
                // Page pageObj = new Page(page, totalCount, pageSize, url,
                // param);
                // this.pageBar = PageHelper.getPageBar(pageObj);

                vehcList = (List < VehcileInfo >) service.getObjectsByPage(
                        "VehicleManage.getInfosbyEnid", vehinfo, (Integer
                                .parseInt(pageIndex) - 1)
                                * Integer.parseInt(rpNum), Integer
                                .parseInt(rpNum));
                setMap(getPagination(vehcList, totalCount, pageIndex));
                // modify by jinp stop
                // add by jinp start
            } else if ("3".equals(user.getUserType())) {
                vehinfo.setUser_id(user.getUserID());

                // 车主用户
                totalCount = service.getCount("VehicleManage.getCountByUserId",
                        vehinfo);
                // 每页显示条数
                String rpNum = request.getParameter("rp");
                // 当前页码
                String pageIndex = request.getParameter("page");
                // 排序字段名
                String sortName = request.getParameter("sortname");
                // 升序OR降序
                String sortOrder = request.getParameter("sortorder");
                vehinfo.setSortname(sortName);
                vehinfo.setSortorder(sortOrder);

                vehcList = (List < VehcileInfo >) service.getObjectsByPage(
                        "VehicleManage.getUserVehicleInfos", vehinfo, (Integer
                                .parseInt(pageIndex) - 1)
                                * Integer.parseInt(rpNum), Integer
                                .parseInt(rpNum));
                setMap(getPagination(vehcList, totalCount, pageIndex));
                // add by jinp stop
            } else {
                totalCount = service
                        .getCount("VehicleManage.getCount", vehinfo);
                // 每页显示条数
                String rpNum = request.getParameter("rp");
                // 当前页码
                String pageIndex = request.getParameter("page");
                // 排序字段名
                String sortName = request.getParameter("sortname");
                // 升序OR降序
                String sortOrder = request.getParameter("sortorder");
                vehinfo.setSortname(sortName);
                vehinfo.setSortorder(sortOrder);
                // add by jinp stop
                // modify by jinp start

                // Page pageObj = new Page(page, totalCount, pageSize, url,
                // param);
                // this.pageBar = PageHelper.getPageBar(pageObj);
                vehcList = (List < VehcileInfo >) service.getObjectsByPage(
                        "VehicleManage.getInfos", vehinfo, (Integer
                                .parseInt(pageIndex) - 1)
                                * Integer.parseInt(rpNum), Integer
                                .parseInt(rpNum));
                setMap(getPagination(vehcList, totalCount, pageIndex));
                // modify by jinp stop
            }
            if (0 == vehcList.size()) {
                this.addActionMessage(getText("nodata.list"));
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
            this.setModuleId(MouldId.YTP_VEHMANAGE_QUREY_ID);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error("logid:" + logid + "," + browseTitle, e);
            return ERROR;
        }

        return SUCCESS;
    }
    
    /**
     * 车辆页面
     * @return
     */
    public String vehicleList() {
        HttpServletRequest request = ServletActionContext.getRequest();
        final String browseTitle = getText("vehcileinfo.browse.title");
        String logid = getlogid();
        log.info("logid:" + logid + "," + browseTitle);
        int totalCount = 0;
        UserInfo user = getCurrentUser();

        try {
            VehcileInfo vehinfo = new VehcileInfo();

            // 条件查询设置组织ID-如果没有输入条件查询则默认查询所有当前用户组织下的车辆
            if (organization_id != "" && organization_id != null
                    && !organization_id.equals("")) {
                vehinfo.setOrganization_id(organization_id);
            } else {
                vehinfo.setOrganization_id(user.getOrganizationID());
            }
            // 设置企业ID
            vehinfo.setEnterprise_id(user.getEntiID());
            // 设置车辆Vin
            if (vehcileVin != "" && vehcileVin != null
                    && !vehcileVin.equals("")) {
                vehinfo.setVehicle_vin(vehcileVin);
            } else {
                vehinfo.setVehicle_vin(null);
            }
            
            // 设置车牌
            if (vehcileLn != "" && vehcileLn != null
                    && !vehcileLn.equals("")) {
                vehinfo.setVehicle_ln(vehcileLn);
            } else {
                vehinfo.setVehicle_ln(null);
            }

            if (user.getEntiID().equals(user.getOrganizationID())
            // add by jinp start
                    && !("3".equals(user.getUserType()))
            // add by jinp stop
            ) {
                // 管理员查询
                if (organization_id != "" && organization_id != null
                        && !organization_id.equals("")) {
                    vehinfo.setOrganization_id(organization_id);
                } else {
                    vehinfo.setOrganization_id(null);
                }
                totalCount = service.getCount("VehicleManage.getCountbyEnid",
                        vehinfo);
                // add by jinp start
                // 每页显示条数
                String rpNum = request.getParameter("rp");
                // 当前页码
                String pageIndex = request.getParameter("page");
                // 排序字段名
                String sortName = request.getParameter("sortname");
                // 升序OR降序
                String sortOrder = request.getParameter("sortorder");
                vehinfo.setSortname(sortName);
                vehinfo.setSortorder(sortOrder);
                // add by jinp stop
                // modify by jinp start
                // Page pageObj = new Page(page, totalCount, pageSize, url,
                // param);
                // this.pageBar = PageHelper.getPageBar(pageObj);

                vehcList = (List < VehcileInfo >) service.getObjectsByPage(
                        "VehicleManage.getInfosbyEnid", vehinfo, (Integer
                                .parseInt(pageIndex) - 1)
                                * Integer.parseInt(rpNum), Integer
                                .parseInt(rpNum));
                setMap(getPaginationList(vehcList, totalCount, pageIndex,rpNum));
                // modify by jinp stop
                // add by jinp start
            } else if ("3".equals(user.getUserType())) {
                vehinfo.setUser_id(user.getUserID());

                // 车主用户
                totalCount = service.getCount("VehicleManage.getCountByUserId",
                        vehinfo);
                // 每页显示条数
                String rpNum = request.getParameter("rp");
                // 当前页码
                String pageIndex = request.getParameter("page");
                // 排序字段名
                String sortName = request.getParameter("sortname");
                // 升序OR降序
                String sortOrder = request.getParameter("sortorder");
                vehinfo.setSortname(sortName);
                vehinfo.setSortorder(sortOrder);

                vehcList = (List < VehcileInfo >) service.getObjectsByPage(
                        "VehicleManage.getUserVehicleInfos", vehinfo, (Integer
                                .parseInt(pageIndex) - 1)
                                * Integer.parseInt(rpNum), Integer
                                .parseInt(rpNum));
                setMap(getPaginationList(vehcList, totalCount, pageIndex,rpNum));
                // add by jinp stop
            } else {
                totalCount = service
                        .getCount("VehicleManage.getCount", vehinfo);
                // 每页显示条数
                String rpNum = request.getParameter("rp");
                // 当前页码
                String pageIndex = request.getParameter("page");
                // 排序字段名
                String sortName = request.getParameter("sortname");
                // 升序OR降序
                String sortOrder = request.getParameter("sortorder");
                vehinfo.setSortname(sortName);
                vehinfo.setSortorder(sortOrder);
                // add by jinp stop
                // modify by jinp start

                // Page pageObj = new Page(page, totalCount, pageSize, url,
                // param);
                // this.pageBar = PageHelper.getPageBar(pageObj);
                vehcList = (List < VehcileInfo >) service.getObjectsByPage(
                        "VehicleManage.getInfos", vehinfo, (Integer
                                .parseInt(pageIndex) - 1)
                                * Integer.parseInt(rpNum), Integer
                                .parseInt(rpNum));
                setMap(getPaginationList(vehcList, totalCount, pageIndex,rpNum));
                // modify by jinp stop
            }
            if (0 == vehcList.size()) {
                this.addActionMessage(getText("nodata.list"));
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
            this.setModuleId(MouldId.YTP_VEHMANAGE_QUREY_ID);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error("logid:" + logid + "," + browseTitle, e);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 车辆分配页面
     * @return
     */

    public String addvehiclebefore() {
        final String addBefTitle = getText("vehcileinfo.add.title");
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
                tree_script = TreeHtmlShow.getEnterpriseLastChildClick(res);
            }
            leftList = service.getObjects("VehicleManage.getVehicledesc", user
                    .getOrganizationID());

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
                tree_script = TreeHtmlShow.getEnterpriseLastChildClick(res);
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
     * 分配车辆
     * @return
     */
    public String add() {
        if (null == vehcileInfo) {
            return addvehiclebefore();
        }
        final String addTitle = getText("vehcileinfo.add.info");
        log.info(addTitle);
        try {
            String[] selectveh = vehcileInfo.getSelectveh();
            List < VehcileInfo > updateorgidList = new ArrayList < VehcileInfo >();
            if (null != selectveh) {
                UserInfo user = getCurrentUser();
                /** 批量更新list **/
                for (int i = 0; i < selectveh.length; i++) {
                    VehcileInfo vecinfo = new VehcileInfo();
                    vecinfo
                            .setOrganization_id(vehcileInfo
                                    .getOrganization_id());
                    vecinfo.setVehicle_id(selectveh[i]);
                    vecinfo.setModifier(user.getUserID());
                    vecinfo.setUser_id(vehcileInfo.getUser_id());
                    updateorgidList.add(vecinfo);
                }
            }

            if (null != updateorgidList && updateorgidList.size() == 0) {
                addActionError(getText("nodata.vehcileinfo.list"));
                return ERROR;
            } else {
                // 分配车辆时批量更新ORGID
                vehmanageService.updateList(updateorgidList, vehcileInfo
                        .getOrganization_id());
            }

        } catch (BusinessException e) {
            log.error(addTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("vehcileinfo.addsuccess.message");
        // 设置操作描述
        this.addOperationLog(formatLog(addTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.INSERT);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_VEHMANAGE_INSERT_ID);
        return SUCCESS;
    }

    public String editBefore() {
        final String editBefTitle = getText("vehcileinfo.editbefore.title");
        log.info(editBefTitle);
        String tree_script = "";
        String ChooseEnterID_tree = "";

        try {
            if (null != vehcileInfo) {
                vehcileInfo = (VehcileInfo) service.getObject(
                        "VehicleManage.getVehicleinfo", vehcileInfo);
                if (null == vehcileInfo) {
                    setMessage("info.data.notexsist");
                    return ERROR;
                }
                oldvehcileLn = vehcileInfo.getVehicle_ln();
                oldvehcileCode = vehcileInfo.getVehicle_code();
                oldVehcileColor=vehcileInfo.getVeh_pai_color();
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
                    tree_script = TreeHtmlShow.getEnterpriseLastChildClick(res);
                }

                if (null != vehcileInfo.getOrganization_id()) {
                    Map < String, Object > enmap = new HashMap < String, Object >(
                            5);
                    enmap.put("in_enterprise_id", user.getOrganizationID());
                    enmap.put("in_org_id", vehcileInfo.getOrganization_id());
                    enmap.put("out_flag", null);
                    enmap.put("out_message", null);
                    enmap.put("out_ref", null);
                    service
                            .getObject("VehicleManage.show_enterprise_id",
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
                setOrganization_idold(vehcileInfo.getOrganization_id());
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(editBefTitle, e);
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
     * 显示驾驶员相片
     * @return
     */
    public String showVehiclePhoto() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String vehicle_id = request.getParameter("vehicle_id");
            
            VehcileInfo tmp = new VehcileInfo();
            tmp.setVehicle_id(vehicle_id);
            
            tmp = (VehcileInfo) service.getObject(
                    "VehicleManage.getVehicleinfo", tmp);
            
            ByteArrayInputStream input = null;
            try {
                input = new ByteArrayInputStream(tmp.getVeh_photo());
            } catch (Exception e) {
                log.debug("显示车辆相片出现错误：" + e.toString());
                return ERROR;
            }
            this.setInputStream(input);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
        return SUCCESS;
    }

    /*
     * 更新车辆信息
     */
    public String updateVehicle() {
    	if (null == vehcileInfo) {
            return editBefore();
        }
        final String editTitle = getText("vehcileinfo.update");
        log.info(editTitle);
        try {
            UserInfo user = getCurrentUser();
            vehcileInfo.setModifier(user.getUserID());
            //vehcileInfo.setVehicle_vin(vehcileInfo.getVehicle_vin());
            int i = service.getCount("VehicleManage.getCountforenCl",
                    organization_idold);
            
            String updateStr = "";

            byte[] data = null;
            
            if (null != file) {
                // 判断是否包含相片文件
            	try {
                    FileInputStream fis = new FileInputStream(file);
                    
                    data = new byte[fis.available()];
                    fis.read(data);
                    fis.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// 对字节数组Base64编码 
                BASE64Encoder encoder = new BASE64Encoder();   
                String str = encoder.encode(data);// 返回Base64编码过的字节数组字符串 
                
                if (str == null){ // 图像数据为空   
                	return null;
                }   
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] bytes = null;
                try {   
                	// Base64解码   
                	bytes = decoder.decodeBuffer(str);   
                	for (int i1 = 0; i1 < bytes.length; ++i1) {   
	                	if (bytes[i1] < 0) {// 调整异常数据   
	                		bytes[i1] += 256;   
	                	}   
                	}   
                }catch (Exception e){
                	;
                }  

                // 创建附件信息bean
                vehcileInfo.setVeh_photo(bytes);
                vehcileInfo.setPhot_name(fileFileName);
                
                updateStr = "1";
                                
            } else if("del".equals(vehcileInfo.getPhotoDelFlag())) {
                // 删除相片并更新信息
                updateStr = "2";
               
            } else {
                updateStr = "3";
                
            }
            if (i > 1) {
                vehmanageService.updateaddMulti(vehcileInfo,updateStr);
            } else {
                vehmanageService
                        .updatesubMulti(vehcileInfo, organization_idold,updateStr);
            }

        } catch (BusinessException e) {
            log.error(editTitle, e);
            addActionError("info.integrity.violation.error".equals(e.getMessage())?"驾驶员为空，无法修改":e.getMessage());
            return ERROR;
        }
        setMessage("vehcileinfo.editsuccess.message");
        // 设置操作描述
        this.addOperationLog(formatLog(editTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_VEHMANAGE_UPDATE_ID);
        return SUCCESS;
    }
    public String cancleVehicle() {
        if (null == vehcileInfo) {
            return editBefore();
        }
        final String cancleTitle = getText("vehcileinfo.cancle");
        log.info(cancleTitle);
        try {
            UserInfo user = getCurrentUser();
            vehcileInfo.setModifier(user.getUserID());
            vehcileInfo.setOrganization_id(user.getOrganizationID());
            vehcileInfo.setCancleids(vehcileInfo.getVehicle_vin());
            int length = service.getCount("VehicleManage.getvehicleridingexeplan_count", vehcileInfo);
            if(length == 0) {
            	try {
	                String returnValue = (String) service.getObject("VehicleManage.getvehicleridingplan", vehcileInfo);
	                if(null!=returnValue&&!("").equals(returnValue)&& ""!=returnValue){
	                    setMessage(getText("common.vehcileln")+"：“"+returnValue+"” "+getText("ridingplan.canle.cars.exist"));
	                    return ERROR;
	                }
	                setMessage("vehcileinfo.canclesuccess.message");
	            } catch (BusinessException e1) {
	                e1.printStackTrace();
	            }
	            int i = service.getCount("VehicleManage.getCountforenCl", organization_idold);
	            if (i > 1) {
	                service.update("VehicleManage.canclebyVehicleid", vehcileInfo);
	            } else {
	                vehmanageService.cancleMulti(vehcileInfo, organization_idold);
	            }
            } else {
        		setMessage("vehcileinfo.canclealreadysend.message");
        	}

        } catch (BusinessException e) {
            log.error(cancleTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        // 设置操作描述
        this.addOperationLog(formatLog(cancleTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.DELETE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_VEHMANAGE_CANCLE_ID);

        return SUCCESS;
    }

    public String selectDriver() {
        final String browseTitle = getText("vehdriver.browse.title");
        log.info(browseTitle);
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        try {
            if (null == driverInfo) {
                driverInfo = new DriverInfo();
            }
            driverInfo.setEnterprise_id(user.getEntiID());
            // totalCount = service.getCount("DriverManage.getCount",
            // driverinfo);
            // Page pageObj = new Page(page, totalCount, pageSize, url, param);
            // this.pageBar = PageHelper.getPageBar(pageObj);
            driverList = (List < DriverInfo >) service.getObjects(
                    "DriverManage.getInfos", driverInfo);
            if (driverList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 用于添加或者删除时显示消息
            if (null != message) {
                addActionMessage(getText(message));
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 驾驶员新列表信息页面
     * @return
     */
    public String readyselDriver() {
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }

    /**
     * 车辆批量去除分配
     * @return
     */
    public String batchCancleVehicle() {

        final String cancleTitle = getText("vehcileinfo.cancle");
        // 获取当前操作用户信息
        UserInfo user = getCurrentUser();
        String returnValue = "";
        VehcileInfo vehcileinfo = new VehcileInfo();
        String batchS = "";
        
        // 所选去除分配车辆VIN确认
        if (carsVinInfos != null && carsVinInfos != ""
                && !carsVinInfos.equals("")) {
        	
    		vehcileinfo.setOrganization_id(user.getOrganizationID());
    		vehcileinfo.setCancleids(carsVinInfos);
    		
			
            // 拆分车辆VIN
            String carsVin[] = formatIds(carsVinInfos).split(",");

            List < VehcileInfo > vInfos = new ArrayList < VehcileInfo >();

            // 去除分配车辆VIN进行集合统计
            for (int i = 0; i < carsVin.length; i++) {

                VehcileInfo vi = new VehcileInfo();

                vi.setVehicle_vin(carsVin[i]);
                // 设置修改人-当前操作者
                vi.setModifier(user.getUserID());

                // vi.setOrganization_id(organization_id);

                vInfos.add(vi);
            }

            try {
                // 批量去除车辆分配
                batchS = service.batchCanelCars(vInfos);
            } catch (SQLException e) {
                log.error(cancleTitle, e);
                setMessage("canle.cars.error");
                return ERROR;
            }

        } else {
            // 没有选择车辆返回提示，重新操作
            setMessage("choose.canle.car");
            return ERROR;
        }
        //setMessage(UnicodeConverter.toEncodedUnicode(batchS+"已添加发车安排！",true));
        if("".equals(batchS)){
        	setMessage("vehcileinfo.canclesuccess.message");
        	//setMessageFlag("0");//批量去除分配中，所选车辆中没有有发车安排的车辆   标识  设置为“0”
        }
        else{
        	//setMessage("vehcileinfo.canclealreadysend.message");
        	String mes=UnicodeConverter.toEncodedUnicode(batchS+"已添加发车安排，无法去除分配！",true);
        	setMessage(mes);
        	setMessageFlag("1");//批量去除分配中，所选车辆中有发车安排的车辆   标识  设置为“1”
        }
        //setMessage("vehcileinfo.canclesuccess.message");
        // 设置操作描述
        this.addOperationLog(formatLog(cancleTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.DELETE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_VEHMANAGE_CANCLE_ID);

        return SUCCESS;
    }

    private String formatIds(String regions) {
        String ret = "";
        String[] strs = regions.split(",");
        for (int i = 0; i < strs.length; i++) {
            String tmp = strs[i];
            if (null != tmp && tmp.length() > 0) {
                tmp = tmp.substring(1, tmp.length() - 1);
                strs[i] = tmp;
            }
        }
        for (int i = 0; i < strs.length; i++) {
            if (ret == "") {
                ret = ret.concat(strs[i]);
            } else {
                ret = ret.concat(",").concat(strs[i]);
            }
        }
        return ret;
    }

    public String selectDriver2() {
        final String browseTitle = getText("vehdriver.browse.title");
        log.info(browseTitle);
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == driverInfo) {
                driverInfo = new DriverInfo();
            }
            UserInfo user = getCurrentUser();
            driverInfo.setEnterprise_id(user.getEntiID());
            driverInfo.setOrganization_id(user.getOrganizationID());

            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            driverInfo.setSortname(sortName);
            driverInfo.setSortorder(sortOrder);
            //String dong=request.getParameter("dong");//是否要根据组织查询，如果需要的话就加上，否则去掉
            //driverInfo.setOrganization_id(dong);
            //driverInfo.setOrganization_id(vehcileInfo.getOrganization_id());

            int totalCount = 0;
            totalCount = service.getCount("DriverManage.getCount", driverInfo);
            // Page pageObj = new Page(page, totalCount, pageSize, url, param);
            // this.pageBar = PageHelper.getPageBar(pageObj);
            driverList = (List < DriverInfo >) service.getObjectsByPage(
            		"DriverManage.getInfos", driverInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getdriverPagination(driverList, totalCount, pageIndex);// 转换map

            if (driverList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 用于添加或者删除时显示消息
            if (null != message) {
                addActionMessage(getText(message));
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 车主新列表信息页面
     * @return
     */
    public String readyselUser() {
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }

    public String selectUser2() {
        final String browseTitle = getText("vehuser.browse.title");
        log.info(browseTitle);
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

        UserInfo user = getCurrentUser();
        try {
            if (null == vehcileInfo) {
                vehcileInfo = new VehcileInfo();
            }
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            vehcileInfo.setSortname(sortName);
            vehcileInfo.setSortorder(sortOrder);
            vehcileInfo.setEnterprise_id(user.getEntiID());

            int totalCount = 0;

            totalCount = service.getCount("VehicleManage.getCountforvehuser",
                    vehcileInfo);

            userList = (List < UserInfo >) service.getObjectsByPage(
                    "VehicleManage.getforvehusers", vehcileInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
            this.map = getuserPagination(userList, totalCount, pageIndex);// 转换map
            // Page pageObj = new Page(page, totalCount, pageSize, url, param);
            // this.pageBar = PageHelper.getPageBar(pageObj);
            // userList = (List<UserInfo>) service.getObjectsByPage(
            // "VehicleManage.getforvehusers", vehcileInfo,pageObj
            // .getStartOfPage(), pageSize);
            if (userList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 用于添加或者删除时显示消息
            if (null != message) {
                addActionMessage(getText(message));
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    public Map getuserPagination(List userlist, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < userlist.size(); i++) {
            UserInfo s = (UserInfo) userlist.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getUserID());
            cellMap.put("cell", new Object[] {s.getUserName(),
                    s.getLoginName(), s.getSex(), s.getBirthday(),
                    s.getIdCard() });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    public Map getdriverPagination(List driverList, int totalCount,
            String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < driverList.size(); i++) {
            DriverInfo s = (DriverInfo) driverList.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getDriver_id());
            cellMap.put("cell", new Object[] {s.getDriver_name(),
                    s.getDriver_license(), s.getDriver_sex(),
                    s.getDriver_birth(), s.getShort_allname() });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    public String selectUser() {
        final String browseTitle = getText("vehuser.browse.title");
        log.info(browseTitle);
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        try {
            if (null == vehcileInfo) {
                vehcileInfo = new VehcileInfo();
            }
            vehcileInfo.setEnterprise_id(user.getEntiID());
            totalCount = service.getCount("VehicleManage.getCountforvehuser",
                    vehcileInfo);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            userList = (List < UserInfo >) service.getObjectsByPage(
                    "VehicleManage.getforvehusers", vehcileInfo, pageObj
                            .getStartOfPage(), pageSize);
            if (userList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 用于添加或者删除时显示消息
            if (null != message) {
                addActionMessage(getText(message));
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
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
     * 获得当前操作LOGID
     * @return
     */
    private String getlogid() {
        return (String) ActionContext.getContext().getSession().get(
                Constants.LOG_USE_ID);
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
                OperateLogFormator.format(sb, "vehicleid", vehObj
                        .getVehicle_id());
            }
        }
        return sb.toString();
    }

}
