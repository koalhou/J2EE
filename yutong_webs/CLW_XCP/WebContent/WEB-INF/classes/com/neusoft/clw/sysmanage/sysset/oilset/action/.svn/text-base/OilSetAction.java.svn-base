package com.neusoft.clw.sysmanage.sysset.oilset.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.oilsetservice.OilSetService;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.sysmanage.sysset.ocktimeset.domain.OckTimeSet;
import com.neusoft.clw.sysmanage.sysset.oilset.domain.OilSet;
import com.opensymphony.xwork2.ActionContext;

public class OilSetAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    /** 显示数据list * */
    private List < OilSet > oilsetList;

    private List < OckTimeSet > ocktimesetList;

    private OckTimeSet ocktimeset;

    private UserInfo userinfo;

    private List < VehcileInfo > vehcileLeftList;

    private List < VehcileInfo > vehcileRightList;

    private VehcileInfo vehcileinfo;

    /**
     * 车辆品牌下拉框列表信息
     */
    private List < String > carPinPaiList = new ArrayList < String >();

    private HashMap carTypeMap = new HashMap();

    public HashMap getCarTypeMap() {
        return carTypeMap;
    }

    public void setCarTypeMap(HashMap carTypeMap) {
        this.carTypeMap = carTypeMap;
    }

    /**
     * 车型下拉框列表信息
     */
    private List < String > carTypeList = new ArrayList < String >();

    public List < String > getCarTypeList() {
        return carTypeList;
    }

    public void setCarTypeList(List < String > carTypeList) {
        this.carTypeList = carTypeList;
    }

    public List < String > getCarPinPaiList() {
        return carPinPaiList;
    }

    public void setCarPinPaiList(List < String > carPinPaiList) {
        this.carPinPaiList = carPinPaiList;
    }

    private Map map = new HashMap();

    private String sstart_time;

    private String send_time;

    public VehcileInfo getVehcileinfo() {
        return vehcileinfo;
    }

    public void setVehcileinfo(VehcileInfo vehcileinfo) {
        this.vehcileinfo = vehcileinfo;
    }

    /** 修改数据用 * */
    private OilSet oilset;

    /** 显示消息 * */
    private String message = null;

    private transient OilSetService oilsetservice;

    public OckTimeSet getOcktimeset() {
        return ocktimeset;
    }

    public void setOcktimeset(OckTimeSet ocktimeset) {
        this.ocktimeset = ocktimeset;
    }

    public List < VehcileInfo > getVehcileLeftList() {
        return vehcileLeftList;
    }

    public void setVehcileLeftList(List < VehcileInfo > vehcileLeftList) {
        this.vehcileLeftList = vehcileLeftList;
    }

    public List < VehcileInfo > getVehcileRightList() {
        return vehcileRightList;
    }

    public void setVehcileRightList(List < VehcileInfo > vehcileRightList) {
        this.vehcileRightList = vehcileRightList;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List < OilSet > getOilsetList() {
        return oilsetList;
    }

    public void setOilsetList(List < OilSet > oilsetList) {
        this.oilsetList = oilsetList;
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

    public OilSet getOilset() {
        return oilset;
    }

    public void setOilset(OilSet oilset) {
        this.oilset = oilset;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OilSetService getOilsetservice() {
        return oilsetservice;
    }

    public void setOilsetservice(OilSetService oilsetservice) {
        this.oilsetservice = oilsetservice;
    }

    /**
     * 效验一台车考核油耗设置的月份唯一
     * @return 是否唯一 true or false
     */
    public boolean checkMonthUnique(String month, String ln, String checkId) {

        OilSet os = new OilSet();
        os.setCheck_time_code(month);
        os.setCheck_id(checkId);
        // os.setVehicle_ln(ln);
        os.setVehicle_vin(ln);
        try {
            int resNum = service
                    .getCount("OilSet.getCheckMonthUniqueCount", os);

            if (resNum == 0) {
                return true;
            } else {
                return false;
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 考核油耗查询-获取车辆品牌下的车型
     * @return 车型下拉框列表信息
     */
    public HashMap getCarTypeLists(String enId, String brand) {

        // 创建参数对象
        OilSet para = new OilSet();
        // 设置参数对象的所属组织结构ID
        // para.setOrganization_id(orgId);
        para.setEnterprise_id(enId);
        // 设置参数对象的车辆品牌
        para.setCarBrand(brand);
        // 创建接收结果容器
        List < OilSet > carTypeList = new ArrayList < OilSet >();

        // 查询当前品牌下当前组织下车辆类型
        try {

            carTypeList = service.getObjects("OilSet.getCarTypeList", para);
            HashMap hm = new HashMap();
            if (!carTypeList.isEmpty()) {
                for (OilSet os : carTypeList) {
                    hm.put(os.getCarType(), os.getCarTypeDesc());
                }
            }
            return hm;
        } catch (BusinessException e) {
            LOG.error("get car type list error - select ex:", e);
            return null;
        }

    }

    /**
     * 浏览消息配置信息
     * @return
     */
    public String oilSetBrowse() {
        final String browseTitle = getText("oil.show");
        log.info(browseTitle);

        // 用于添加或者删除时显示消息
        if (null != message) {
            addActionMessage(getText(message));
        }

        try {
            if (null == oilset) {
                oilset = new OilSet();
            } else {
                if (oilset.getCarBrand() == ""
                        || oilset.getCarBrand().equals("")
                        || oilset.getCarBrand().equals("请选择")
                        || oilset.getCarBrand() == ("请选择")) {
                    oilset.setCarBrand(null);
                }
                if (oilset.getCarType() == "" || oilset.getCarType().equals("")
                        || oilset.getCarType().equals("请选择")
                        || oilset.getCarType() == "请选择") {
                    oilset.setCarType(null);
                }
                if (oilset.getCheck_time_code() == ""
                        || oilset.getCheck_time_code().equals("")) {
                    oilset.setCheck_time_code(null);
                }

            }
            UserInfo user = getCurrentUser();
            int totalCount = 0;
            oilset.setEnterprise_id(user.getEntiID());
            // 这只油耗设置对象所属组织机构-应用于车辆品牌列表获取-作用传递参数
            // oilset.setOrganization_id(user.getOrganizationID());

            // 获取车辆品牌信息列表
            carPinPaiList = service.getObjects("OilSet.getCarPinParList",
                    oilset);

            /*
             * totalCount = service.getCount("OilSet.getCount", oilset); Page
             * pageObj = new Page(page, totalCount, pageSize, url, param);
             * this.pageBar = PageHelper.getPageBar(pageObj); oilsetList =
             * service.getObjectsByPage("OilSet.getInfos", oilset, pageObj
             * .getStartOfPage(), pageSize);
             */

            HttpServletRequest request = (HttpServletRequest) ActionContext
                    .getContext().get(
                            org.apache.struts2.StrutsStatics.HTTP_REQUEST);

            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            oilset.setSortname(sortName);
            oilset.setSortorder(sortOrder);

            if (StringUtils.isEmpty(pageIndex)) {
                pageIndex = "1";
            }
            if (StringUtils.isEmpty(rpNum)) {
                rpNum = String.valueOf(pageSize);
            }

            page = Integer.parseInt(pageIndex);
            pageSize = Integer.parseInt(rpNum);

            totalCount = service.getCount("OilSet.getCount", oilset);

            oilsetList = service
                    .getObjectsByPage("OilSet.getInfos", oilset, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            /*
            if (oilsetList.isEmpty()) {
                addActionMessage(getText("nodata.list"));
                return SUCCESS;
            }*/

            this.map = getPagination(oilsetList, totalCount, pageIndex);

            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.YTP_OILSET_QUERY_ID);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String addBefore() {
        final String addTitle = getText("dirver.browse.title");
        UserInfo user = getCurrentUser();

        if (message != null) {
            addActionError(getText(message));
        }

        if (null == oilset) {
            oilset = new OilSet();
        }
        oilset.setEnterprise_id(user.getEntiID());
        // 这只油耗设置对象所属组织机构-应用于车辆品牌列表获取-作用传递参数
        oilset.setOrganization_id(user.getOrganizationID());

        if (null == ocktimeset) {
            ocktimeset = new OckTimeSet();
        }

        if (null == vehcileinfo) {
            vehcileinfo = new VehcileInfo();
        }
        vehcileinfo.setOrganization_id(user.getOrganizationID());
        ocktimeset.setEnterprise_id(user.getEntiID());
        vehcileinfo.setEnterprise_id(user.getEntiID());

        try {
            // 获取车辆品牌信息列表
            carPinPaiList = service.getObjects("OilSet.getCarPinParList",
                    oilset);

            ocktimesetList = service.getObjects("OilSet.getOckTimeSet",
                    ocktimeset);
            // vehcileLeftList=
            // service.getObjects("OilSet.getVehicleLeft",vehcileinfo);
            vehcileRightList = service.getObjects("OilSet.getVehicleRight",
                    vehcileinfo);
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    /**
     * 添加消息配置
     */
    public String addOilSet() {
        if (null == oilset) {
            return addBefore();
        }
        final String addTitle = getText("oil.create");
        try {
            UserInfo user = getCurrentUser();
            oilset.setCreater(user.getUserID());
            oilset.setEnterprise_id(user.getEntiID());

            // 效验当前车型当前月份是否已经设置考核油耗设置
            int resNum = service
                    .getCount("OilSet.checkTypeMonthUnique", oilset);

            if (resNum > 0) {
                // addActionError(getText("check.car.type.month.unique"));
                this.message = "check.car.type.month.unique";
                return ERROR;
            }
            // 查询选定品牌-车型-所属本企业下-有效的车辆VIN
            List < String > carVinList = service.getObjects(
                    "OilSet.getCarVinList", oilset);

            // 设置当前品牌车型系列车辆对象
            List < OilSet > oilSetBatchList = new ArrayList < OilSet >();

            if (!carVinList.isEmpty()) {
                oilset.setVelTypeId(UUID.randomUUID().toString());
                for (String paraString : carVinList) {
                    OilSet oilPara = new OilSet();
                    // 设置车辆VIN
                    oilPara.setVehicle_vin(paraString);
                    oilPara.setCreater(oilset.getCreater());
                    oilPara.setEnterprise_id(oilset.getEnterprise_id());
                    oilPara.setCheck_value(oilset.getCheck_value());
                    oilPara.setCheck_time_code(oilset.getCheck_time_code());
                    oilPara.setValid_flag("0");
                    oilPara.setCheck_id(UUID.randomUUID().toString());
                    oilPara.setVelTypeId(oilset.getVelTypeId());
                    oilSetBatchList.add(oilPara);
                }

                // 获取车辆品牌ID
                String desc = (String) service.getObject(
                        "OilSet.getCarTypeDesc", oilset);

                oilset.setCarTypeDesc(desc);

                // 批量插入数据
                try {
                    service.batchAddOilSet(oilSetBatchList);
                } catch (SQLException e) {

                    log.error("batch set oil error", e);
                    // addActionError(getText("batch.set.oil.faile"));
                    this.message = "batch.set.oil.faile";
                    return ERROR;
                }

                // 车辆类型品牌插入
                service.insert("OilSet.insertVelInfo", oilset);

            } else {
                // addActionError(getText("check.car.type.month.car.error"));
                this.message = "check.car.type.month.unique";
                return ERROR;
            }

        } catch (BusinessException e) {
            log.error(e.getMessage());
            this.message = "batch.set.oil.faile";
            return ERROR;
        }
        setMessage("oil.addsuccess");
        // 设置操作描述
        this.addOperationLog(formatLog(addTitle, oilset));
        // 设置操作类型
        this.setOperationType(Constants.INSERT);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_OILSET_ADD_ID);
        return SUCCESS;
    }

    /**
     * 修改消息配置页面
     */
    public String editBefore() {
        final String editBefTitle = getText("driver.editbefore.title");
        log.info(editBefTitle);

        try {

            // 修改信息提示
            if (message != null) {
                addActionError(getText(message));
            }

            BASE64Decoder decoder = new BASE64Decoder();
            oilset.setVehicle_ln(new String(decoder.decodeBuffer(oilset
                    .getVehicle_ln()), "UTF-8"));
            oilset.setCarBrand(new String(decoder.decodeBuffer(oilset
                    .getCarBrand()), "UTF-8"));
            oilset.setCarType(new String(decoder.decodeBuffer(oilset
                    .getCarType()), "UTF-8"));

            oilset.setCarTypeDesc(new String(decoder.decodeBuffer(oilset
                    .getCarTypeDesc()), "UTF-8"));

            UserInfo user = getCurrentUser();

            // 获取车辆类型表主键
            String velTypePermikey = (String) service.getObject(
                    "OilSet.getCarTypeId", oilset);

            oilset.setVelTypeId(velTypePermikey);
            oilset.setEnterprise_id(user.getEntiID());
            // 这只油耗设置对象所属组织机构-应用于车辆品牌列表获取-作用传递参数
            oilset.setOrganization_id(user.getOrganizationID());

            // 获取车辆品牌信息列表
            carPinPaiList = service.getObjects("OilSet.getCarPinParList",
                    oilset);

            carTypeMap.put(oilset.getCarType(), oilset.getCarTypeDesc());
            // 车型列表初值设置
            // carTypeList.add(oilset.getCarType());
        } catch (Exception e) {
            addActionError(getText(e.getMessage()));
            log.error(editBefTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 修改消息配置
     */
    public String updateOilSet() {
        if (null == oilset) {
            return editBefore();
        }
        final String editTitle = getText("oil.update");
        try {

            UserInfo userInfo = getCurrentUser();

            oilset.setEnterprise_id(userInfo.getEntiID());

            UserInfo user = getCurrentUser();

            oilset.setModifier(user.getUserID());

            service.update("OilSet.updatebyOilSetid", oilset);

            // // 修改油耗设置唯一验证
            // // int uniqueRes = service
            // // .getCount("OilSet.checkUpdateUnique", oilset);
            // //if (uniqueRes != 0) {
            // this.setMessage("update.oil.set.same");
            // BASE64Encoder encoder = new BASE64Encoder();
            //
            // if (oilset.getCarTypeDesc() == null) {
            // String ospara = (String) service.getObject(
            // "OilSet.getCarTypeDesc", oilset);
            // oilset.setCarTypeDesc(ospara);
            // }
            //
            // oilset.setCarBrand(encoder.encode(oilset.getCarBrand()
            // .getBytes("UTF-8")));
            // oilset.setCarType(encoder.encode(oilset.getCarType().getBytes(
            // "UTF-8")));
            //
            // oilset.setCarTypeDesc(encoder.encode(oilset.getCarTypeDesc()
            // .getBytes("UTF-8")));
            //
            // return ERROR;
            // } else {
            //
            // UserInfo user = getCurrentUser();
            // oilset.setModifier(user.getUserID());
            //
            // String desc = (String) service.getObject(
            // "OilSet.getCarTypeDesc", oilset);
            //
            // oilset.setCarTypeDesc(desc);
            // service.updateBatchOilSet(oilset);
            // }
            // service.update("OilSet.updatebyOilSetid", oilset);
        } catch (Exception e) {
            log.error(e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("oil.updatesuccess");
        // 设置操作描述
        this.addOperationLog(formatLog(editTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_OILSET_UPDATE_ID);
        return SUCCESS;
    }

    /**
     * 删除消息配置
     */
    public String deleteOilSet() {
        if (null == oilset) {
            return editBefore();
        }
        final String cancleTitle = getText("oil.delete");
        try {
            UserInfo user = getCurrentUser();
            oilset.setVaset_user_id(user.getUserID());

            service.deleteBatchOilSet(oilset);

            int res = service.getCount("OilSet.getVonum", oilset);

            if (res == 0) {
                service.update("OilSet.deleteTypebyid", oilset);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("delete.success");
        // 设置操作描述
        this.addOperationLog(formatLog(cancleTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.DELETE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_OILSET_DELETE_ID);

        return SUCCESS;

    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, OilSet oilset) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != oilset) {
            if (null != oilset.getCheck_id()) {
                OperateLogFormator.format(sb, "Check_id", oilset.getCheck_id());
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

    @SuppressWarnings("unchecked")
    public Map getPagination(List dayList, int totalCountDay, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < dayList.size(); i++) {

            OilSet s = (OilSet) dayList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getCheck_id());

            cellMap.put("cell", new Object[] {
                    (s.getVehicle_ln() == null || s.getVehicle_ln().length() == 0) ? "暂无":s.getVehicle_ln(),
                    s.getVehicle_vin(),
                    s.getCarBrand(), s.getCarTypeDesc(),
                    s.getCheck_time_code(), s.getCheck_value(), s.getCreater(),
                    s.getCreate_time(), s.getModifier(), s.getModify_time(),
                    s.getVelTypeId(), s.getCarType(), s.getCheck_id() });

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        if (null == oilset) {
            oilset = new OilSet();
        }

        return mapData;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getSstart_time() {
        return sstart_time;
    }

    public void setSstart_time(String sstart_time) {
        this.sstart_time = sstart_time;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

}
