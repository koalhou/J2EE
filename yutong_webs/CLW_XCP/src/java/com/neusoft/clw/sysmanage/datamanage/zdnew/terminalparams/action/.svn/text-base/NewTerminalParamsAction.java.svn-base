/*******************************************************************************
 * @(#)TerminalParamsAction.java 2012-6-4
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.sysmanage.datamanage.zdnew.terminalparams.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.zdnew.terminalparams.ds.Bit32ValueInfo;
import com.neusoft.clw.sysmanage.datamanage.zdnew.terminalparams.ds.CarNumberInfo;
import com.neusoft.clw.sysmanage.datamanage.zdnew.terminalparams.ds.EnterpriseTreeInfo;
import com.neusoft.clw.sysmanage.datamanage.zdnew.terminalparams.ds.VehicleInfo;
import com.neusoft.clw.sysmanage.datamanage.zdnew.terminalparams.ds.XCTerminalParamsInfo;
import com.opensymphony.xwork2.ActionContext;
/**
 * 终端参数设置action
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-6-4 上午10:16:56
 */
public class NewTerminalParamsAction extends PaginationAction {
    private transient Service service;

    /** 页面提示信息 **/
    private String message = null;

    private List < EnterpriseTreeInfo > enterpriseInfo;

    private EnterpriseTreeInfo enterprise;
    
    private String name="";
   
    /** 车辆信息 **/
    private Map vehicleMap = new HashMap();

    /** 查询企业ID **/
    private String enterpriseId = "";
    
    /** 查询企业名称 **/
    private String enterpriseName = "";
    
    /** 查询车牌 **/
    private String vehicleLn = "";
    
    /** 参数设置信息 **/
    private XCTerminalParamsInfo xcTerminalParamsSet = new XCTerminalParamsInfo();
    
    private String carIdList = "";
    
    private String currentpage = "";
    
    private String currentpageSize = "";
    
    /** 当前排序字段 **/
    private String currentsortname = "";
    
    /** 当前排序方式 **/
    private String currentsortorder = "";
    
    /**
     * 空action
     * @return
     */
    public String blankAction() {
        if( null == xcTerminalParamsSet) {
            xcTerminalParamsSet = new XCTerminalParamsInfo();
        }
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }

    /**
     * 格式化企业信息
     */
    private void formatEnterpriseInfo() {
        try {
            List<CarNumberInfo> carnumbers = (List < CarNumberInfo >) service.getObjects(
                    "NewTerminalParams.getCarnumberByEnterprise", "");
            // 注册车辆信息map
            Map<String, String> enterpriseMap = new HashMap<String, String>();
            
            // 已分配车辆map
            Map<String, String> organizationMap = new HashMap<String, String>();
            
            int totalNumber = 0 ;
            
            for(CarNumberInfo tmp : carnumbers) {
                if("ENTERPRISE".equals(tmp.getFlag())) {
                    // 注册车辆
                    // 车辆总数
                    totalNumber = totalNumber + Integer.parseInt(tmp.getCarnum());
                    enterpriseMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
                } else {
                    // 已分配车辆
                    organizationMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
                }
            }
            
            for(EnterpriseTreeInfo singleInfo : enterpriseInfo) {
                if("111".equals(singleInfo.getId())) {
                    singleInfo.setName(String.format("%s(%s)", singleInfo.getName(),String.valueOf(totalNumber)));
                } else if(enterpriseMap.get(singleInfo.getId()) != null && enterpriseMap.get(singleInfo.getId()).length() > 0) {
                    singleInfo.setName(String.format("%s(%s)", singleInfo.getName(),enterpriseMap.get(singleInfo.getId())));
                } else if(organizationMap.get(singleInfo.getId()) != null && organizationMap.get(singleInfo.getId()).length() > 0) {
                    singleInfo.setName(String.format("%s(%s)", singleInfo.getName(),organizationMap.get(singleInfo.getId())));
                }
            }
            
        } catch (BusinessException e) {
            log.error("Get car numbers error:" + e.getMessage());
        }
    }
    
    /**
     * 组织机构树
     */
    public String getOrganizationTree() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        try {
            enterpriseInfo = (List < EnterpriseTreeInfo >) service.getObjects(
                    "NewTerminalParams.getOrganizationTreeData", currentUser.getOrganizationID());
            //formatEnterpriseInfo();
            
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;

    }
    
    /**
     * 查询组织机构树
     */
    public String searchOrganizationTreeData() {
    	if(null==enterprise){
    		enterprise = new EnterpriseTreeInfo();
    	}
    	try {
			enterprise.setName(java.net.URLDecoder.decode(name, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			log.error("decode error:" + e1.getMessage());
		}
        try {
            enterpriseInfo = (List < EnterpriseTreeInfo >) service.getObjects(
                    "NewTerminalParams.searchOrganizationTreeData", enterprise);
            formatEnterpriseInfo();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    

    /**
     * 组装JSON数据
     * @param vehicleList
     * @param totalCount
     * @param pageIndex
     * @param rpNum
     * @return
     */
    public Map getPagination(List vehicleList, int totalCount, String pageIndex,String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        
        // 已选的车辆放到列表前
        if ( null != carIdList && carIdList.length() > 0 ) {
            String[] carList = carIdList.split(",");
            
            for(int i = 0 ; i < carList.length; i++ ) {
                for(int j = 0; j < vehicleList.size(); j++ ) {
                    VehicleInfo vehicleTmp = (VehicleInfo) vehicleList.get(j);
                    if(null != carList[i] && carList[i].equals(vehicleTmp.getVehicleId())) {
                        
                        if(null == vehicleTmp.getVehicleLn() || vehicleTmp.getVehicleLn().length() == 0) {
                            vehicleTmp.setVehicleLn("暂无车牌");
                        }
                        
                        Map cellMap = new LinkedHashMap();
                        cellMap.put("id", vehicleTmp.getVehicleId());

                        cellMap.put("cell", new Object[] {
                                vehicleTmp.getVehicleId(),
                                vehicleTmp.getVehicleLn(),
                                "1"
                                 });
                        mapList.add(cellMap);
                        vehicleList.remove(j);
                        break;
                    }
                }
            }
            
        }
        
        for (int i = 0; i < vehicleList.size(); i++) {
            VehicleInfo vehicleInfo = (VehicleInfo) vehicleList.get(i);

            if(null == vehicleInfo.getVehicleLn() || vehicleInfo.getVehicleLn().length() == 0) {
                vehicleInfo.setVehicleLn("暂无车牌");
            }
            
            Map cellMap = new LinkedHashMap();

            cellMap.put("id", vehicleInfo.getVehicleId());

            cellMap.put("cell", new Object[] {
                    vehicleInfo.getVehicleId(),
                    vehicleInfo.getVehicleLn(),
                    "0"
                     });

            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 获取车辆信息
     * @return
     */
    public String getVehicleListByEnterpriseId() {
        HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        
        String rpNum = "";
        String pageIndex = "";

        pageIndex = request.getParameter("page");
        rpNum = request.getParameter("rp");
        
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        
        try {

            Map < String, String > map = new HashMap < String, String >();
            if(null == enterpriseId || enterpriseId.length() == 0) {
                UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                        Constants.USER_SESSION_KEY);
                map.put("enterpriseId", currentUser.getOrganizationID());
            } else {
                map.put("enterpriseId", enterpriseId);
            }
            map.put("sortname", sortName);
            map.put("sortorder", sortOrder);
            map.put("vehicleLn", vehicleLn);
            
            int totalCount = 0;
            totalCount = service.getCount("NewTerminalParams.getVehicleCount", map);

            List<VehicleInfo> vehicleList = service.getObjectsByPage(
                    "NewTerminalParams.getVehicleListById", map, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.vehicleMap = getPagination(vehicleList, totalCount, pageIndex,rpNum);
            
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query registered vehicles error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query registered vehicles error:" + e.getMessage());
            return ERROR;
        } finally {
            addOperationLog("查询已注册车辆");
        }
        
        return SUCCESS;
    }
    
    /**
     * 格式化车辆查询条件
     * @return
     */
    private String formateVehicleList() {
        String ret = "";
        if (null != carIdList && carIdList.length() > 0) {
            String[] carList = carIdList.split(",");
            for (int i = 0; i < carList.length; i++) {
                if (null != carList[i] && carList[i].length() > 0) {
                    if (ret == "") {
                        ret = "'" + carList[i] + "'";
                    } else {
                        ret = ret + "," + "'" + carList[i] + "'";
                    }
                }
            }
        }
        return ret;
    }
    
    /**
     * 设置当前终端参数信息
     * @return
     */
    public String setTerminalParams() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
        
        int failCnt = 0;
        try {
            List<XCTerminalParamsInfo> vehicleInfos = service.getObjects("NewTerminalParams.getVehicleInfos", formateVehicleList()); 
            // 设置操作人
            xcTerminalParamsSet.setUserId(currentUser.getUserID());
            for(XCTerminalParamsInfo tmp : vehicleInfos) {
                if(null != tmp.getVehicleVin() && tmp.getVehicleVin().length() > 0) {
                    // 设置VIN号
                    xcTerminalParamsSet.setVehicleVin(tmp.getVehicleVin());
                    // 设置SIM卡号
                    xcTerminalParamsSet.setSimCardNumber(tmp.getSimCardNumber());
                }
                
                SendCommandUtils sendCommandUtils = new SendCommandUtils();
                // 查询终端参数
                String ret = sendCommandUtils.setTerminalParams(xcTerminalParamsSet);
                if(!"0".equals(ret)) {
                    failCnt ++ ;
                }
            }
            if(failCnt > 0) {
                addActionError(String.valueOf(failCnt) + "台车下发失败，请重新发送！");
            } else {
                addActionMessage("参数设置命令已下发！");
            }
           
        } catch (Exception e) {
            log.error("Query terminal params error:" + e.getMessage());
            addActionError("终端参数设置命令下发失败，请重新下发！");
            return ERROR;
        } finally {
            this.addOperationLog("设置终端参数信息");
            // 设置操作类型
            this.setOperationType(Constants.INSERT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_TERPARMS_SET);
        }

        
        return SUCCESS;
    }
    
    /**
     * 32比特数据分析【报警屏蔽字专用】
     * @return
     */
    private Bit32ValueInfo formatStr2Bit32(String strVal) {
        Bit32ValueInfo bit32ValueInfo = new Bit32ValueInfo();

        if (null != strVal && 32 == strVal.length()) {
            char[] bitValue = strVal.toCharArray();
            if ('0' == bitValue[31]) {
                bit32ValueInfo.setBit1(true);
            }
            if ('0' == bitValue[30]) {
                bit32ValueInfo.setBit2(true);
            }
            if ('0' == bitValue[29]) {
                bit32ValueInfo.setBit3(true);
            }
            if ('0' == bitValue[28]) {
                bit32ValueInfo.setBit4(true);
            }
            if ('0' == bitValue[27]) {
                bit32ValueInfo.setBit5(true);
            }
            if ('0' == bitValue[26]) {
                bit32ValueInfo.setBit6(true);
            }
            if ('0' == bitValue[25]) {
                bit32ValueInfo.setBit7(true);
            }
            if ('0' == bitValue[24]) {
                bit32ValueInfo.setBit8(true);
            }
            if ('0' == bitValue[23]) {
                bit32ValueInfo.setBit9(true);
            }
            if ('0' == bitValue[22]) {
                bit32ValueInfo.setBit10(true);
            }
            if ('0' == bitValue[21]) {
                bit32ValueInfo.setBit11(true);
            }
            if ('0' == bitValue[20]) {
                bit32ValueInfo.setBit12(true);
            }
            if ('0' == bitValue[19]) {
                bit32ValueInfo.setBit13(true);
            }
            if ('0' == bitValue[18]) {
                bit32ValueInfo.setBit14(true);
            }
            if ('0' == bitValue[17]) {
                bit32ValueInfo.setBit15(true);
            }
            if ('0' == bitValue[16]) {
                bit32ValueInfo.setBit16(true);
            }
            if ('0' == bitValue[15]) {
                bit32ValueInfo.setBit17(true);
            }
            if ('0' == bitValue[14]) {
                bit32ValueInfo.setBit18(true);
            }
            if ('0' == bitValue[13]) {
                bit32ValueInfo.setBit19(true);
            }
            if ('0' == bitValue[12]) {
                bit32ValueInfo.setBit20(true);
            }
            if ('0' == bitValue[11]) {
                bit32ValueInfo.setBit21(true);
            }
            if ('0' == bitValue[10]) {
                bit32ValueInfo.setBit22(true);
            }
            if ('0' == bitValue[9]) {
                bit32ValueInfo.setBit23(true);
            }
            if ('0' == bitValue[8]) {
                bit32ValueInfo.setBit24(true);
            }
            if ('0' == bitValue[7]) {
                bit32ValueInfo.setBit25(true);
            }
            if ('0' == bitValue[6]) {
                bit32ValueInfo.setBit26(true);
            }
            if ('0' == bitValue[5]) {
                bit32ValueInfo.setBit27(true);
            }
            if ('0' == bitValue[4]) {
                bit32ValueInfo.setBit28(true);
            }
            if ('0' == bitValue[3]) {
                bit32ValueInfo.setBit29(true);
            }
            if ('0' == bitValue[2]) {
                bit32ValueInfo.setBit30(true);
            }
            if ('0' == bitValue[1]) {
                bit32ValueInfo.setBit31(true);
            }
            if ('0' == bitValue[0]) {
                bit32ValueInfo.setBit32(true);
            }
        }
        
        return bit32ValueInfo;
    }
    
    /**
     * 32比特数据分析
     * @return
     */
    private Bit32ValueInfo formatStr2Bit32Normal(String strVal) {
        Bit32ValueInfo bit32ValueInfo = new Bit32ValueInfo();

        if (null != strVal && 32 == strVal.length()) {
            char[] bitValue = strVal.toCharArray();
            if ('1' == bitValue[31]) {
                bit32ValueInfo.setBit1(true);
            }
            if ('1' == bitValue[30]) {
                bit32ValueInfo.setBit2(true);
            }
            if ('1' == bitValue[29]) {
                bit32ValueInfo.setBit3(true);
            }
            if ('1' == bitValue[28]) {
                bit32ValueInfo.setBit4(true);
            }
            if ('1' == bitValue[27]) {
                bit32ValueInfo.setBit5(true);
            }
            if ('1' == bitValue[26]) {
                bit32ValueInfo.setBit6(true);
            }
            if ('1' == bitValue[25]) {
                bit32ValueInfo.setBit7(true);
            }
            if ('1' == bitValue[24]) {
                bit32ValueInfo.setBit8(true);
            }
            if ('1' == bitValue[23]) {
                bit32ValueInfo.setBit9(true);
            }
            if ('1' == bitValue[22]) {
                bit32ValueInfo.setBit10(true);
            }
            if ('1' == bitValue[21]) {
                bit32ValueInfo.setBit11(true);
            }
            if ('1' == bitValue[20]) {
                bit32ValueInfo.setBit12(true);
            }
            if ('1' == bitValue[19]) {
                bit32ValueInfo.setBit13(true);
            }
            if ('1' == bitValue[18]) {
                bit32ValueInfo.setBit14(true);
            }
            if ('1' == bitValue[17]) {
                bit32ValueInfo.setBit15(true);
            }
            if ('1' == bitValue[16]) {
                bit32ValueInfo.setBit16(true);
            }
            if ('1' == bitValue[15]) {
                bit32ValueInfo.setBit17(true);
            }
            if ('1' == bitValue[14]) {
                bit32ValueInfo.setBit18(true);
            }
            if ('1' == bitValue[13]) {
                bit32ValueInfo.setBit19(true);
            }
            if ('1' == bitValue[12]) {
                bit32ValueInfo.setBit20(true);
            }
            if ('1' == bitValue[11]) {
                bit32ValueInfo.setBit21(true);
            }
            if ('1' == bitValue[10]) {
                bit32ValueInfo.setBit22(true);
            }
            if ('1' == bitValue[9]) {
                bit32ValueInfo.setBit23(true);
            }
            if ('1' == bitValue[8]) {
                bit32ValueInfo.setBit24(true);
            }
            if ('1' == bitValue[7]) {
                bit32ValueInfo.setBit25(true);
            }
            if ('1' == bitValue[6]) {
                bit32ValueInfo.setBit26(true);
            }
            if ('1' == bitValue[5]) {
                bit32ValueInfo.setBit27(true);
            }
            if ('1' == bitValue[4]) {
                bit32ValueInfo.setBit28(true);
            }
            if ('1' == bitValue[3]) {
                bit32ValueInfo.setBit29(true);
            }
            if ('1' == bitValue[2]) {
                bit32ValueInfo.setBit30(true);
            }
            if ('1' == bitValue[1]) {
                bit32ValueInfo.setBit31(true);
            }
            if ('1' == bitValue[0]) {
                bit32ValueInfo.setBit32(true);
            }
        }
        
        return bit32ValueInfo;
    }
    
    /**
     * 分析语音输出通道
     */
    private void analysisVocieOutputCtrl() {
        if(null != xcTerminalParamsSet.getVoiceOutputControlType() && 
           8 == xcTerminalParamsSet.getVoiceOutputControlType().length()) {
            // 语音输出通道有设置值时
            char[] bitValue = xcTerminalParamsSet.getVoiceOutputControlType().toCharArray();
            xcTerminalParamsSet.setVoiceOutputControlType0(String.valueOf(bitValue[7]));
            xcTerminalParamsSet.setVoiceOutputControlType1(String.valueOf(bitValue[6]));
            xcTerminalParamsSet.setVoiceOutputControlType2(String.valueOf(bitValue[5]));
            xcTerminalParamsSet.setVoiceOutputControlType3(String.valueOf(bitValue[4]));
            xcTerminalParamsSet.setVoiceOutputControlType4(String.valueOf(bitValue[3]));
            xcTerminalParamsSet.setVoiceOutputControlType5(String.valueOf(bitValue[2]));
        }
    }
    
    /**
     * 分析定时、定距信息
     * @param type
     */
    private void analysisSchedule(String type) {
        Bit32ValueInfo bit32ValueInfo = new Bit32ValueInfo();
        if("regularCameraControl".equals(type)) {
            // 定时拍照控制
            if(null != xcTerminalParamsSet.getRegularCameraControlStr() && 
                    32 == xcTerminalParamsSet.getRegularCameraControlStr().length()) {
                char[] bitValue = xcTerminalParamsSet.getRegularCameraControlStr().toCharArray();
                if ('1' == bitValue[31]) {
                    bit32ValueInfo.setBit1(true);
                }
                if ('1' == bitValue[30]) {
                    bit32ValueInfo.setBit2(true);
                }
                if ('1' == bitValue[29]) {
                    bit32ValueInfo.setBit3(true);
                }
                if ('1' == bitValue[28]) {
                    bit32ValueInfo.setBit4(true);
                }
                if ('1' == bitValue[27]) {
                    bit32ValueInfo.setBit5(true);
                }
                if ('1' == bitValue[26]) {
                    bit32ValueInfo.setBit6(true);
                }
                if ('1' == bitValue[25]) {
                    bit32ValueInfo.setBit7(true);
                }
                if ('1' == bitValue[24]) {
                    bit32ValueInfo.setBit8(true);
                }
                if ('1' == bitValue[23]) {
                    bit32ValueInfo.setBit9(true);
                }
                if ('1' == bitValue[22]) {
                    bit32ValueInfo.setBit10(true);
                }
                if ('1' == bitValue[21]) {
                    bit32ValueInfo.setBit11(true);
                }
                if ('1' == bitValue[20]) {
                    bit32ValueInfo.setBit12(true);
                }
                if ('1' == bitValue[19]) {
                    bit32ValueInfo.setBit13(true);
                }
                xcTerminalParamsSet.setRegularCameraControl(bit32ValueInfo);
                xcTerminalParamsSet.setRegularTime(
                        String.valueOf(
                                Integer.parseInt(
                                        xcTerminalParamsSet.getRegularCameraControlStr().substring(0, 16), 
                                        2)/60
                                      ));
            }
        } else {
            // 定距拍照控制
            if(null != xcTerminalParamsSet.getFixDistanceCameraControlStr() && 
                    32 == xcTerminalParamsSet.getFixDistanceCameraControlStr().length()) {
                char[] bitValue = xcTerminalParamsSet.getFixDistanceCameraControlStr().toCharArray();
                if ('1' == bitValue[31]) {
                    bit32ValueInfo.setBit1(true);
                }
                if ('1' == bitValue[30]) {
                    bit32ValueInfo.setBit2(true);
                }
                if ('1' == bitValue[29]) {
                    bit32ValueInfo.setBit3(true);
                }
                if ('1' == bitValue[28]) {
                    bit32ValueInfo.setBit4(true);
                }
                if ('1' == bitValue[27]) {
                    bit32ValueInfo.setBit5(true);
                }
                if ('1' == bitValue[26]) {
                    bit32ValueInfo.setBit6(true);
                }
                if ('1' == bitValue[25]) {
                    bit32ValueInfo.setBit7(true);
                }
                if ('1' == bitValue[24]) {
                    bit32ValueInfo.setBit8(true);
                }
                if ('1' == bitValue[23]) {
                    bit32ValueInfo.setBit9(true);
                }
                if ('1' == bitValue[22]) {
                    bit32ValueInfo.setBit10(true);
                }
                if ('1' == bitValue[21]) {
                    bit32ValueInfo.setBit11(true);
                }
                if ('1' == bitValue[20]) {
                    bit32ValueInfo.setBit12(true);
                }
                if ('1' == bitValue[19]) {
                    bit32ValueInfo.setBit13(true);
                }
                xcTerminalParamsSet.setFixDistanceCameraControl(bit32ValueInfo);
                xcTerminalParamsSet.setFixDistance(
                        String.valueOf(
                                Integer.parseInt(
                                        xcTerminalParamsSet.getFixDistanceCameraControlStr().substring(0, 16), 
                                        2)/1000
                                      ));
            }
        }
    }
    
    /**
     * 解析终端参数信息值
     */
    private void analysisXcTerminalParams() {
        if(null == xcTerminalParamsSet) {
            xcTerminalParamsSet = new XCTerminalParamsInfo();
        } else {
            // 分析报警屏蔽字
            xcTerminalParamsSet.setAlarmShield(formatStr2Bit32(xcTerminalParamsSet.getAlarmShieldStr()));
            // 报警拍摄开关
            xcTerminalParamsSet.setAlarmShootSwitch(formatStr2Bit32Normal(xcTerminalParamsSet.getAlarmShootSwitchStr()));
            // 报警存储标志
            xcTerminalParamsSet.setAlarmShootSaveFlag(formatStr2Bit32Normal(xcTerminalParamsSet.getAlarmShootSaveFlagStr()));
            // 语音输出通道控制
            analysisVocieOutputCtrl();
            // 门开关控制
            xcTerminalParamsSet.setCarDoorControl(formatStr2Bit32Normal(xcTerminalParamsSet.getCarDoorControlStr()));
            // 定时拍照控制
            analysisSchedule("regularCameraControl");
            // 定距拍照控制
            analysisSchedule("fixDistanceCameraControl");
        }
    }
    
    /**
     * 单车查询终端参数
     * @return
     */
    public String queryTerminalParams() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        try {
            // 下发终端参数查询命令
            XCTerminalParamsInfo queryParamsBean = new XCTerminalParamsInfo();
            List<XCTerminalParamsInfo> vehicleInfos = service.getObjects("NewTerminalParams.getVehicleInfos", formateVehicleList()); 
            // 设置操作人
            queryParamsBean.setUserId(currentUser.getUserID());
            for(XCTerminalParamsInfo tmp : vehicleInfos) {
                if(null != tmp.getVehicleVin() && tmp.getVehicleVin().length() > 0) {
                    // 设置VIN号
                    queryParamsBean.setVehicleVin(tmp.getVehicleVin());
                    // 设置SIM卡号
                    queryParamsBean.setSimCardNumber(tmp.getSimCardNumber());
                }
            }
            SendCommandUtils sendCommandUtils = new SendCommandUtils();
            
            // 删除旧数据
            service.delete("NewTerminalParams.deleteParamsByVehicleId", carIdList);
            
            // 查询终端参数
            String ret = sendCommandUtils.queryTerminalParams(queryParamsBean);
            if ("0".equals(ret)) {
                addActionMessage("参数查询命令已下发！请点击刷新按钮以获得最新数据。");
            } else {
                addActionError("参数查询命令下发失败！");
            }
            
            // 查询当前终端参数信息
//            xcTerminalParamsSet = (XCTerminalParamsInfo)service.getObject("NewTerminalParams.getParamsByVehicleId", carIdList);
//            analysisXcTerminalParams();
            xcTerminalParamsSet = new XCTerminalParamsInfo();
        } catch (Exception e) {
            log.error("Query terminal params error:" + e.getMessage());
            addActionError("终端参数查询命令下发失败，请重新下发！");
            return ERROR;
        } finally {
            this.addOperationLog("查询最新终端参数信息");
            // 设置操作类型
            this.setOperationType(Constants.INSERT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_TERPARMS_QUERY);
        }
        return SUCCESS;
    }
    
    /**
     * 单车刷新终端参数
     * @return
     */
    public String refreshTerminalParams() {
        try {
            // 查询当前终端参数信息
            xcTerminalParamsSet = (XCTerminalParamsInfo) service.getObject(
                    "NewTerminalParams.getParamsByVehicleId", carIdList);
            if(null == xcTerminalParamsSet) {
                addActionMessage("尚未获取到该终端参数信息！");
            } else {
                addActionMessage("终端参数信息已刷新！");
            }
            analysisXcTerminalParams();
            
        } catch (Exception e) {
            log.error("Query terminal params error:" + e.getMessage());
            addActionError("终端参数查询命令下发失败，请重新下发！");
            return ERROR;
        } finally {
            this.addOperationLog("查询最新终端参数信息");
            // 设置操作类型
            this.setOperationType(Constants.INSERT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_TERPARMS_QUERY);
        }
        return SUCCESS;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List < EnterpriseTreeInfo > getEnterpriseInfo() {
        return enterpriseInfo;
    }

    public void setEnterpriseInfo(List < EnterpriseTreeInfo > enterpriseInfo) {
        this.enterpriseInfo = enterpriseInfo;
    }
	public EnterpriseTreeInfo getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(EnterpriseTreeInfo enterprise) {
		this.enterprise = enterprise;
	}

    public Map getVehicleMap() {
        return vehicleMap;
    }

    public void setVehicleMap(Map vehicleMap) {
        this.vehicleMap = vehicleMap;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public XCTerminalParamsInfo getXcTerminalParamsSet() {
        return xcTerminalParamsSet;
    }

    public void setXcTerminalParamsSet(XCTerminalParamsInfo xcTerminalParamsSet) {
        this.xcTerminalParamsSet = xcTerminalParamsSet;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getVehicleLn() {
        return vehicleLn;
    }

    public void setVehicleLn(String vehicleLn) {
        this.vehicleLn = vehicleLn;
    }

    public String getCarIdList() {
        return carIdList;
    }

    public void setCarIdList(String carIdList) {
        this.carIdList = carIdList;
    }

    public String getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(String currentpage) {
        this.currentpage = currentpage;
    }

    public String getCurrentpageSize() {
        return currentpageSize;
    }

    public void setCurrentpageSize(String currentpageSize) {
        this.currentpageSize = currentpageSize;
    }

    public String getCurrentsortname() {
        return currentsortname;
    }

    public void setCurrentsortname(String currentsortname) {
        this.currentsortname = currentsortname;
    }

    public String getCurrentsortorder() {
        return currentsortorder;
    }

    public void setCurrentsortorder(String currentsortorder) {
        this.currentsortorder = currentsortorder;
    }
}
