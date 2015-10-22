package com.neusoft.clw.info.business;

import java.util.List;

import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.MapDocument.Map;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.core.xmlbean.ResultDocument.Result;
import com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value;
import com.neusoft.clw.info.bean.ResponseBean;
import com.neusoft.clw.info.bean.SummaryRecord;
import com.neusoft.clw.info.bean.TerminalBizRecord;
import com.neusoft.clw.info.bean.TerminalRecord;
import com.neusoft.clw.info.bean.VehicRecord;
import com.neusoft.clw.info.global.FunctionName;
import com.neusoft.clw.info.util.tool.Base64;

public class GetSendInfo {
    private static final String TYPE_NAME_OF_STRING = "str";

    private static final String TYPE_NAME_OF_STRING_BASE64 = "bstr";

    // private static final Logger LOGGER = Logger.getLogger(GetSendInfo.class);
    private GetSendInfo() { 

    }

    /**
     * 请求成功处理的情况下,将ResponseBean转换为与请求功能对应的xml字符串
     * @param responseBean
     * @return String
     */
    public static String getInfoNormally(ResponseBean responseBean) throws Exception {

        OlxDocument xmlDoc = OlxDocument.Factory.newInstance();

        try {
            // olx节点
            Olx olx = xmlDoc.addNewOlx();
            olx.setDir("down");
            olx.setVersion(responseBean.getOlxVersion());
            // olx.setCompress(responseBean.getOlxCompress());
           // olx.setC(responseBean.getOlxCompress()); // 应答中c表示压缩属性
            //
            // function节点
            Function function = olx.addNewFunction();
            function.setName(responseBean.getFunctionName());
            function.setSeq(responseBean.getFunctionSeq());
            //
            // result节点
            Result result = function.addNewResult();
            //
            // tree-object节点
            TreeObject treeObject = result.addNewTreeObject();
            treeObject.setVersion(responseBean.getTreeObjectVersion());
            // 状态描述,  
            setNewValue(treeObject.addNewValue(), "c", "int", "0"); // 状态码, 成功为0
            // setNewValue(treeObject.addNewValue(), "m", TYPE_NAME_OF_STRING_BASE64,
            // Base64.encode("请求成功", "UTF-8")); // 状态描述, 原文UTF-8,base64加密
            String desc = it.sauronsoftware.base64.Base64.encode("请求成功", "UTF-8");
            setNewValue(treeObject.addNewValue(), "m", TYPE_NAME_OF_STRING_BASE64, desc);
            
            if (!responseBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDCOMMAND)&&!responseBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDUPDATE)) {
                   
	            // treeObject.values
	            setNewValue(treeObject.addNewValue(), "beginTime", TYPE_NAME_OF_STRING, responseBean
	                    .getBeginTime());
	            setNewValue(treeObject.addNewValue(), "endTime", TYPE_NAME_OF_STRING, responseBean
	                    .getEndTime());
	           	           
	            setNewValue(treeObject.addNewValue(), "recordNum", "int", responseBean.getRecordNum());
	            setNewValue(treeObject.addNewValue(), "pageNo", "int", responseBean.getPageNo());
	            setNewValue(treeObject.addNewValue(), "pageCount", "int", responseBean.getPageCount());
	
	            setNewValue(treeObject.addNewValue(), "totalCount", "int", responseBean.getTotalCount());
	            // 详细列表,响应消息的主要内容
	            com.neusoft.clw.core.xmlbean.ListDocument.List list = treeObject.addNewList();
	             
	            /* 车辆信息列表 */
	            if (responseBean.getFunctionName().equals(FunctionName.VNCS_APP_VEHICLEINFO)) {
	            	setVehicList(responseBean.getVehicRecord(), list);
	                return xmlDoc.xmlText();
	            }
	            /* 终端信息列表 */
	            if (responseBean.getFunctionName().equals(FunctionName.VNCS_APP_TERMINALINFO)) {
	            	setTerminalList(responseBean.getTerminalRecord(), list);
	                return xmlDoc.xmlText();
	            }
	            /* 业务信息列表 */
	            if (responseBean.getFunctionName().equals(FunctionName.VNCS_APP_TERMINALBIZ)) {
	            	setTerminalBizList(responseBean.getTerminalBizRecord(), list);
	                return xmlDoc.xmlText();
	            }
            }
        } catch (Exception e) {
            throw new Exception("将responseBean转换为xml字符串过程出错:" + e.getMessage());
        }

        return xmlDoc.xmlText(); // xml字符串一行
    }

    /**
     * 设置分组油耗汇总应答列表
     * @param sumList
     * @param list
     */
    @SuppressWarnings("unused")
	private static void setGroupOilSumList(List<SummaryRecord> sumList,
            com.neusoft.clw.core.xmlbean.ListDocument.List list) {
        list.setKey("summaryList");
        if (null != sumList && sumList.size() > 0) {
            for (SummaryRecord record : sumList) {
                Map newMap = list.addNewMap();
                setNewValue(newMap.addNewValue(), "groupId", TYPE_NAME_OF_STRING, record
                        .getGroupId());
                setNewValue(newMap.addNewValue(), "ao", TYPE_NAME_OF_STRING, record.getAo());
                setNewValue(newMap.addNewValue(), "uo", TYPE_NAME_OF_STRING, record.getUo());
                setNewValue(newMap.addNewValue(), "eo", TYPE_NAME_OF_STRING, record.getEo());
            }
        }
    }

    /**
     * 设置车辆油耗日汇总应答中的列表
     * @param sumList
     * @param list
     */
    @SuppressWarnings("unused")
	private static void setDailyOilSumListOfVehic(List<SummaryRecord> sumList,
            com.neusoft.clw.core.xmlbean.ListDocument.List list) {
        list.setKey("summaryList");
        if (null != sumList && sumList.size() > 0) {
            for (SummaryRecord record : sumList) {
                Map newMap = list.addNewMap();
                setNewValue(newMap.addNewValue(), "date", TYPE_NAME_OF_STRING, record.getStatTime());
                setNewValue(newMap.addNewValue(), "ao", TYPE_NAME_OF_STRING, record.getAo());
                setNewValue(newMap.addNewValue(), "uo", TYPE_NAME_OF_STRING, record.getUo());
                setNewValue(newMap.addNewValue(), "eo", TYPE_NAME_OF_STRING, record.getEo());
            }
        }
    }
    /**
     * 终端业务信息列表
     * @param sumList
     * @param list
     */
    private static void setTerminalBizList(List<TerminalBizRecord> terminalBizRecord,
            com.neusoft.clw.core.xmlbean.ListDocument.List list) {
        list.setKey("terminalList");
//        System.out.println(terminalBizRecord.toString());
        if (null != terminalBizRecord && terminalBizRecord.size() > 0) {
            for (TerminalBizRecord record : terminalBizRecord) {
                Map newMap = list.addNewMap();
                setNewValue(newMap.addNewValue(), "terminal_id", TYPE_NAME_OF_STRING, record.getTerminal_id());
                setNewValue(newMap.addNewValue(), "biz_name", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getBiz_name(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "modify_time", TYPE_NAME_OF_STRING, record.getModify_time());
                setNewValue(newMap.addNewValue(), "biz_id", TYPE_NAME_OF_STRING, record.getBiz_id());
            }
        }
    }
    
    
    /**
     * 终端信息列表
     * @param sumList
     * @param list
     */
    private static void setTerminalList(List<TerminalRecord> terminalList,
            com.neusoft.clw.core.xmlbean.ListDocument.List list) {
        list.setKey("terminalList");
//        System.out.println(terminalList.toString());
        if (null != terminalList && terminalList.size() > 0) {
            for (TerminalRecord record : terminalList) {
                Map newMap = list.addNewMap();
                setNewValue(newMap.addNewValue(),"id", TYPE_NAME_OF_STRING, record.getId());
                setNewValue(newMap.addNewValue(), "vehicle_vin", TYPE_NAME_OF_STRING, record.getVehicle_vin());
                setNewValue(newMap.addNewValue(), "terminal_id", TYPE_NAME_OF_STRING, record.getTerminal_id());
                setNewValue(newMap.addNewValue(), "terminal_oem_id", TYPE_NAME_OF_STRING, record.getTerminal_oem_id());
                setNewValue(newMap.addNewValue(), "terminal_oem_name", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getTerminal_oem_name(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "enterprise_id", TYPE_NAME_OF_STRING, record.getEnterprise_id());
                setNewValue(newMap.addNewValue(), "enterprise_name", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getEnterprise_name(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "terminal_type_name", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getTerminal_type_name(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "terminal_protocol_name", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getTerminal_protocol_name(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "valid_flag", TYPE_NAME_OF_STRING,  record.getValid_flag());
                setNewValue(newMap.addNewValue(), "sim_card_number", TYPE_NAME_OF_STRING, record.getSim_card_number());
                setNewValue(newMap.addNewValue(), "video_id", TYPE_NAME_OF_STRING, record.getVideo_id());
                setNewValue(newMap.addNewValue(), "modify_time", TYPE_NAME_OF_STRING, record.getModify_time());
                setNewValue(newMap.addNewValue(), "communicate_id", TYPE_NAME_OF_STRING, record.getCommunicate_id());
                setNewValue(newMap.addNewValue(), "vaset_time", TYPE_NAME_OF_STRING, record.getVaset_time());
                setNewValue(newMap.addNewValue(), "modify_time", TYPE_NAME_OF_STRING, record.getModify_time());
                setNewValue(newMap.addNewValue(), "valid_flag", TYPE_NAME_OF_STRING, record.getValid_flag());
                setNewValue(newMap.addNewValue(), "vaset_time", TYPE_NAME_OF_STRING, record.getVaset_time());
                setNewValue(newMap.addNewValue(), "cellphone", TYPE_NAME_OF_STRING, record.getCellphone());
                
            }
        }
    }
    
    
    /**
     * 车辆信息列表
     * @param sumList
     * @param list
     */
    private static void setVehicList(List<VehicRecord> vehicList,
            com.neusoft.clw.core.xmlbean.ListDocument.List list) {
        list.setKey("vehicleList");
        if (null != vehicList && vehicList.size() > 0) {
            for (VehicRecord record : vehicList) {
                Map newMap = list.addNewMap();
                setNewValue(newMap.addNewValue(), "vehicle_vin", TYPE_NAME_OF_STRING, record.getVehicle_vin());
                setNewValue(newMap.addNewValue(), "vehicle_id", TYPE_NAME_OF_STRING, record.getVehicle_id());
                setNewValue(newMap.addNewValue(), "vehicle_code", TYPE_NAME_OF_STRING, record.getVehicle_code());
                setNewValue(newMap.addNewValue(), "vehicle_ln", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getVehicle_ln(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "enterprise_id", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getEnterprise_id(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "enterprise_name", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getShort_name(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "engine_type", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getEngine_type(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "brand", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getBrand(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "vehicle_type_id", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getVehicle_type_id(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "vehicle_color", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getVehicle_color(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "limite_number", TYPE_NAME_OF_STRING, record.getLimite_number());
                setNewValue(newMap.addNewValue(), "standard_oil", TYPE_NAME_OF_STRING, record.getStandard_oil());
                setNewValue(newMap.addNewValue(), "dead_load", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getDead_load(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "sale_price", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getSale_price(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "out_number", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getOut_number(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "business_type", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getBusiness_type(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "tyre_r", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getTyre_r(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "rear_axle_rate", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getRear_axle_rate(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "create_time", TYPE_NAME_OF_STRING, record.getCreate_time());
                setNewValue(newMap.addNewValue(), "modifier", TYPE_NAME_OF_STRING_BASE64, Base64.encode(record.getModifier(), "UTF-8"));
                setNewValue(newMap.addNewValue(), "modify_time", TYPE_NAME_OF_STRING, record.getModify_time());
                setNewValue(newMap.addNewValue(), "valid_flag", TYPE_NAME_OF_STRING, record.getValid_flag());
                setNewValue(newMap.addNewValue(), "vaset_time", TYPE_NAME_OF_STRING, record.getVaset_time());
                setNewValue(newMap.addNewValue(), "VEH_PAI_COLOR", TYPE_NAME_OF_STRING, record.getVeh_pai_color());
                setNewValue(newMap.addNewValue(), "VEH_SHENGID", TYPE_NAME_OF_STRING, record.getVeh_shengid());
                setNewValue(newMap.addNewValue(), "VEH_SHIID", TYPE_NAME_OF_STRING, record.getVeh_shiid());
            }
        }
    }

    /**
     * 将ResponseBean转换为与请求功能对应的xml字符串
     * @param responseBean
     * @return String
     */
    public static String getInfoWhenExceptionOccurs(ResponseBean responseBean, int nErrorCode,
            String strErrorDesc) throws Exception {

        OlxDocument xmlDoc = OlxDocument.Factory.newInstance();

        // 出现异常时, 响应消息包括两部分: 物理协议+状态描述
        // 1. 物理协议部分
        // olx节点
        Olx olx = xmlDoc.addNewOlx();
        olx.setDir(responseBean.getOlxDir());
        olx.setVersion(responseBean.getOlxVersion());
        olx.setCompress(responseBean.getOlxCompress()); // compress属性与请求一致
        // function节点
        Function function = olx.addNewFunction();
        function.setName(responseBean.getFunctionName()); // 请求方解析响应消息的依据,不能更改
        function.setSeq(responseBean.getFunctionSeq()); // 同上
        // result节点
        Result result = function.addNewResult();

        // 2. 状态描述部分
        // tree-object节点
        TreeObject treeObject = result.addNewTreeObject();
        treeObject.setVersion(responseBean.getTreeObjectVersion()); // 在响应消息中,version属性固定不变的!
        // 状态码和状态描述字段
        setNewValue(treeObject.addNewValue(), "c", "int", nErrorCode); // 状态码
        String base64ErrorDesc = Base64.encode(strErrorDesc, "UTF-8");
        setNewValue(treeObject.addNewValue(), "m", TYPE_NAME_OF_STRING_BASE64, base64ErrorDesc); // 状态描述,
        // 原文UTF-8,base64加密

        return xmlDoc.xmlText(); // xml字符串一行
        // return xmlDoc.toString();//已经排版缩进的xml

    }

    // 设置一个value标签的三个属性值
    private static void setNewValue(Value value, String strKey, String strClas, Object objValue) {
        value.setKey(strKey);
        value.setClass1(Value.Class.Enum.forString(strClas));
        String strValue = String.valueOf(objValue);
        value.setValue(strValue);
    }

}
