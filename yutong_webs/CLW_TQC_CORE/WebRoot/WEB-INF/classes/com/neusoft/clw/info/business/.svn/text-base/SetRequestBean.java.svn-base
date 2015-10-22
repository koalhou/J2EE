package com.neusoft.clw.info.business;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.MapDocument.Map;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.core.xmlbean.ParamDocument.Param;
import com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value;
import com.neusoft.clw.info.bean.RequestBean;
import com.neusoft.clw.info.exception.InvalidXmlFormatException;
import com.neusoft.clw.info.global.FunctionName;
import com.neusoft.clw.info.util.tool.Base64;
import com.neusoft.clw.info.util.tool.TimeHelpers;

public class SetRequestBean {
    static final String VERSION_OLX = "0.0.1";

    private static final String TYPE_NAME_OF_STRING = "str";
    private static final String TYPE_NAME_OF_BSTRING = "bstr";

    /**
     * 请求xml解析为RequestBean 
     */
    public static boolean setBean(final RequestBean reqBean, final OlxDocument xmlDoc)
            throws InvalidXmlFormatException, Exception {

        // 1. olx节点, Dir和Version是必选属性
        Olx olx = xmlDoc.getOlx();
        if (null == olx || null == olx.getDir() || null == olx.getVersion()) {
            throw new InvalidXmlFormatException("olx节点为空或其属性值错误");
        } else {
            reqBean.setOlxDir(olx.getDir().trim());
            reqBean.setOlxVersion(olx.getVersion().trim());
        }

        // 2. function节点, name和seq是必选属性
        Function function = olx.getFunction();
        // 在响应消息里的function及其属性name,seq必须原样返回,不做任何更改
        if (null == function || null == function.getName() || null == function.getSeq()) {
            reqBean.setFunctionName(function.getName());
            reqBean.setFunctionSeq(function.getSeq());
            throw new InvalidXmlFormatException("function节点为空或name,或seq属性为空");
        } else {
            reqBean.setFunctionName(function.getName().trim());
            reqBean.setFunctionSeq(function.getSeq().trim());
            if (null != function.getTimeout()) { // timeout为可选属性, 特殊处理
                reqBean.setFunctionTimeOut(function.getTimeout().trim());
            }
        }

        // 3. param节点
        Param param = function.getParam();
        if (null == function) {
            throw new InvalidXmlFormatException("param节点为空");
        }

        // 4. tree-object节点
        TreeObject treeObject = param.getTreeObject();
        if (null == treeObject) {
            throw new InvalidXmlFormatException("tree-Object节点为空");
        } else {
            if (null != treeObject.getVersion()) { // version为可选属性, 特殊处理
                reqBean.setTreeObjectVersion(treeObject.getVersion().trim());

            }
        }

        // 5. 分页参数 和 排序参数, 均为可选
        Map[] mapsInTreeObject = treeObject.getMapArray();
        if (null != mapsInTreeObject) {
            for (int i = 0; i < mapsInTreeObject.length; i++) {
                String strMapKey = mapsInTreeObject[i].getKey();
                if (null == strMapKey) {
                    throw new InvalidXmlFormatException("<map>节点缺少key属性");
                }

                if (mapsInTreeObject[i].getKey().trim().equalsIgnoreCase("pageParam")) {
                    // 5.1 用了分页参数
                    reqBean.setUsePageParam(true);

                    if (4 != mapsInTreeObject[i].getValueArray().length) {
                        throw new InvalidXmlFormatException("pageParam中参数个数错误");
                    }

                    for (Value value : mapsInTreeObject[i].getValueArray()) {
                        String strKey = value.getKey();
                        String strClass = value.getClass1().toString().toLowerCase();
                        String strValue = value.getValue();

                        if (strKey.equalsIgnoreCase("isPage") && strClass.equals("bool")
                                && isBoolean(strValue))
                            reqBean.setIsPage(strValue);
                        else if (strKey.equalsIgnoreCase("pageNo") && strClass.equals("int"))
                            reqBean.setPageNo(strValue);
                        else if (strKey.equalsIgnoreCase("pageSize") && strClass.equals("int"))
                            reqBean.setPageSize(strValue);
                        else if (strKey.equalsIgnoreCase("maxPageSize") && strClass.equals("int"))
                            reqBean.setMaxPageSize(strValue);
                        else
                            throw new InvalidXmlFormatException("无效的请求参数:"
                                    + String.format(
                                            "<value key=\"%s\" class=\"%s\" value=\"%s\"/>",
                                            strKey, strClass, strValue));
                    }
                    // 是否使用分页参数
                    if (reqBean.getIsPage().equalsIgnoreCase("true") // 为true时,按照pageNo和pageSize分页
                            && (null == reqBean.getPageNo() || null == reqBean.getPageSize()
                                    || !isInteger(reqBean.getPageNo()) || !isInteger(reqBean
                                    .getPageSize()))) {
                        throw new InvalidXmlFormatException(
                                "分页参数错误:isPage=true时,必需有pageNo和pageSize参数, 且均为整数");
                    } else if (reqBean.getIsPage().equalsIgnoreCase("false") // 为false时,按照maxPageSize分页
                            && (null == reqBean.getMaxPageSize() || !isPosInteger(reqBean
                                    .getMaxPageSize()))) {
                        throw new InvalidXmlFormatException(
                                "分页参数错误:isPage=false时,必需有合法的maxPageSize参数来限定页面大小");
                    }
                    // isPage=false时, 按照maxPageSize分页,返回第一页
                    if (reqBean.getIsPage().equalsIgnoreCase("false")) {
                        reqBean.setPageNo("1");
                        reqBean.setPageSize(reqBean.getMaxPageSize());
                    }

                } else {
                    throw new InvalidXmlFormatException("key=\"" + strMapKey + "\"的map节点为非法参数!");
                }
            }
        }

        if (false == reqBean.isUsePageParam()) { // 如果没有使用参数,则默认返回第一页,系统指定页面大小
            reqBean.setPageNo("1");
            reqBean.setPageSize("" + "500");
        }

        // 6. 获取参数
        for (Value value : treeObject.getValueArray()) {
            String strKey = value.getKey().trim();
            String strClass = value.getClass1().toString();
            String strValue = value.getValue().toString();
            if (strKey.equalsIgnoreCase("beginTime") && strClass.equals(TYPE_NAME_OF_STRING)
                    && isDateYYYYMMDD(strValue)) {
                reqBean.setBeginTime(strValue);
            } else if (strKey.equalsIgnoreCase("endTime") && strClass.equals(TYPE_NAME_OF_STRING)
                    && isDateYYYYMMDD(strValue)) {
                reqBean.setEndTime(strValue);
            }  else if (strKey.equalsIgnoreCase("appid") && strClass.equals(TYPE_NAME_OF_STRING)) {
                reqBean.setAppid(strValue);
            } else if (strKey.equalsIgnoreCase("pass") && strClass.equals(TYPE_NAME_OF_BSTRING)) {
                reqBean.setPass(strValue);
            }else if (strKey.equalsIgnoreCase("send_command") && strClass.equals(TYPE_NAME_OF_STRING)) {
                reqBean.setSend_command(strValue);
            }else if (strKey.equalsIgnoreCase("send_type") && strClass.equals(TYPE_NAME_OF_STRING)) {
                reqBean.setSend_type(strValue);
            }else if (strKey.equalsIgnoreCase("terminal_id") && strClass.equals(TYPE_NAME_OF_STRING)) {
                reqBean.setTerminal_id(strValue);
            }else if (strKey.equalsIgnoreCase("sim_card_number") && strClass.equals(TYPE_NAME_OF_STRING)) {
                reqBean.setSim_card_number(strValue);
            }else if (strKey.equalsIgnoreCase("msg_id") && strClass.equals(TYPE_NAME_OF_STRING)) {
                reqBean.setMsg_id(strValue);
            }else if (strKey.equalsIgnoreCase("operate_user_id") && strClass.equals(TYPE_NAME_OF_STRING)) {
                reqBean.setOperate_user_id(strValue);
            }else if (strKey.equalsIgnoreCase("remark") && strClass.equals(TYPE_NAME_OF_BSTRING)) {
                reqBean.setRemark(Base64.decode(strValue,"UTF-8"));
            }else if (strKey.equalsIgnoreCase("packet_content") && strClass.equals(TYPE_NAME_OF_BSTRING)) {
                reqBean.setPacket_content(Base64.decode(strValue,"UTF-8"));
            }else if(strKey.equalsIgnoreCase("reggrpid")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setReggrpid(strValue.equals("null")?"":strValue);
            }else if(strKey.equalsIgnoreCase("chanle_code")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setChanel_code(strValue.equals("null")?"":strValue);
            }else if(strKey.equalsIgnoreCase("terminal")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setTerminalAndVehicle("terminal",Integer.parseInt(strValue));
            }else if(strKey.equalsIgnoreCase("vehicle")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setTerminalAndVehicle("vehicle",Integer.parseInt(strValue));
            }else if(strKey.equalsIgnoreCase("scope")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setScope(strValue);
            }else if(strKey.equalsIgnoreCase("route_id")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setRoute_id(strValue);
            }else if(strKey.equalsIgnoreCase("stu_id")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setStu_id(strValue);
            }else if(strKey.equalsIgnoreCase("batch_id")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setBatch_id(strValue);
            }else if(strKey.equalsIgnoreCase("iplength")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setIplength(strValue);
            }else if(strKey.equalsIgnoreCase("ip")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setIp(strValue);
            }else if(strKey.equalsIgnoreCase("port")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setPort(strValue);
            }else if(strKey.equalsIgnoreCase("userlength")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setUserlength(strValue);
            }else if(strKey.equalsIgnoreCase("username")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setUsername(strValue);
            }else if(strKey.equalsIgnoreCase("passlength")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setPasslength(strValue);
            }else if(strKey.equalsIgnoreCase("userpass")&&strClass.equals(TYPE_NAME_OF_BSTRING)){
            	reqBean.setUserpass(Base64.decode(strValue,"UTF-8"));
            }else if(strKey.equalsIgnoreCase("filelength")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setFilelength(strValue);
            }else if(strKey.equalsIgnoreCase("filename")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setFilename(strValue);
            }else if(strKey.equalsIgnoreCase("crc")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setCrc(strValue);
            }else if(strKey.equalsIgnoreCase("exception_event_switch")&&strClass.equals(TYPE_NAME_OF_STRING)){
            	reqBean.setException_event_switch(strValue);
            }else {
                throw new InvalidXmlFormatException("无效的请求参数:"
                        + String.format("<value key=\"%s\" class=\"%s\" value=\"%s\"/>", strKey,
                                strClass, strValue));
            }
        }
        
        if (!reqBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDCOMMAND)&&!reqBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDUPDATE)&&!reqBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDLINE)&&!reqBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDLOCK)&&!reqBean.getFunctionName().equals(FunctionName.VNCS_APP_EXCEPTIONSETTING)) {
	        if (null == reqBean.getBeginTime() || null == reqBean.getEndTime()) {
	            throw new InvalidXmlFormatException("请求中必需包含起止时间beginTime和endTime,格式'yyyy-mm-dd'");
	        }
        }
       
        // 8. 详细检查各个参数的值
        checkFeilds(reqBean);

        return true;
    }

  

    /**
     * 检查请求xml的节点属性值各项参数值 --只对0.0.1版本有效
     */
    private static boolean checkFeilds(RequestBean reqBean) throws InvalidXmlFormatException {
        // String strVersion = "0.0.1";

        // 1.olx节点的属性
        //
        // dir属性, 必需
        if (!reqBean.getOlxDir().equalsIgnoreCase("up")) {
            throw new InvalidXmlFormatException("olx.Dir属性值错误:请求xml中dir必须是\"up\"");
        }
        // version属性,必需
        if (!reqBean.getOlxVersion().equals(VERSION_OLX)) {
            throw new InvalidXmlFormatException("olx.Version属性值错误, 系统仅支持的版本号" + VERSION_OLX);
        }

        // 2.function节点的属性
        //
        // name属性, 必需 --只提供四种功能,功能名称在com.neusoft.clw.info.global.FunctionName.java中定义
        if (!isValidFunctionName(reqBean.getFunctionName())) {
            throw new InvalidXmlFormatException("function.name属性值错误:不提供此功能请求"
                    + reqBean.getFunctionName());
        }
        // seq属性, 必需
        if (!isInteger(reqBean.getFunctionSeq())) {
            throw new InvalidXmlFormatException("function.seq属性值错误:seq=" + reqBean.getFunctionSeq()
                    + "不是整数");
        }
        // timout属性, 可选
        if (null != reqBean.getFunctionTimeOut() && !isInteger(reqBean.getFunctionTimeOut())) {
            throw new InvalidXmlFormatException("function.timout属性值错误:timout="
                    + reqBean.getFunctionTimeOut() + "不是整数");
        }

        // 3.tree-object节点的属性
        // version属性 --可选, 且和olx.version保持一致
        /* tree-object中的version不再验证，modified by zhanglzh, 14th Dec 2010 */
        // if (null!=reqBean.getTreeObjectVersion() &&
        // !reqBean.getTreeObjectVersion().equalsIgnoreCase(VERSION_OLX)) {
        // throw new InvalidXmlFormatException("tree-object.Version属性值错误,必需和olx.version保持一致!
        // 系统当前支持的版本号仅为"+VERSION_OLX);
        // }
        // 4. 分页参数
        if (reqBean.isUsePageParam()) {
            if (reqBean.getIsPage().equalsIgnoreCase("true")) {
                if (!isInteger(reqBean.getPageNo()) || !isInteger(reqBean.getPageSize())) {
                    throw new InvalidXmlFormatException("分页参数中pageNo和pageSize参数的value属性值必须是正整数");
                }
            } else if (reqBean.getIsPage().equalsIgnoreCase("false")) {
                if (!isInteger(reqBean.getMaxPageSize())) {
                    throw new InvalidXmlFormatException("分页参数中maxPageSize参数的value属性值必须是正整数");
                }
            } else {
                throw new InvalidXmlFormatException("分页参数中isPage参数的value属性错误:isPage="
                        + reqBean.getIsPage());
            }
        }

        // 6. 开始时间和结束时间
        if (!reqBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDCOMMAND)&&!reqBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDUPDATE)&&!reqBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDLINE)&&!reqBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDLOCK)) {
	        if (!isDateYYYYMMDD(reqBean.getBeginTime()) || !isDateYYYYMMDD(reqBean.getEndTime())) {
	            throw new InvalidXmlFormatException("起止时间格式错误,beginTime,endTime必需满足格式'yyyy-mm-dd'");
	        }
       
	        // 开始时间不能大于结束时间
	        Date beginTime = TimeHelpers.string2date("yyyy-MM-dd HH:mm:ss", reqBean.getBeginTime());
	        Date endTime = TimeHelpers.string2date("yyyy-MM-dd HH:mm:ss", reqBean.getEndTime());
	        if (TimeHelpers.EXCEED_FLAG_OF_DATE == TimeHelpers.compareDate(beginTime, endTime)) {
	            throw new InvalidXmlFormatException("开始时间大于结束时间");
	        }
        }
        if(reqBean.getScope()!= null){
        	if(!reqBean.getScope().equals("0")&&!reqBean.getScope().equals("1")){
        		throw new InvalidXmlFormatException("scope不满足协议要求，必须为1或0");
        	}
        }
        return true;
    }

    /**
     * 判断字符串是否是正整数
     */
    private static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是正整数
     */
    private static boolean isPosInteger(String value) {
        try {
            int intvalue = Integer.parseInt(value);
            if (intvalue > 0) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是布尔型
     */
    private static boolean isBoolean(String value) {
        if (value.equals("true") || value.equals("false")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否符合'yyyy-mm-dd hh:mm:ss'格式
     */
    private static boolean isDateYYYYMMDD(String strDate) {
        try {
            // System.out.println(strDate);
            // System.out.println(new SimpleDateFormat("yyyy-mm-dd").parse(strDate));
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isValidFunctionName(String str) {
        if (str.equals(FunctionName.VNCS_APP_SENDCOMMAND)
                || str.equals(FunctionName.VNCS_APP_TERMINALBIZ)
                || str.equals(FunctionName.VNCS_APP_TERMINALINFO)
                || str.equals(FunctionName.VNCS_APP_VEHICLEINFO)||str.equals(FunctionName.VNCS_APP_SENDUPDATE)
                || str.equals(FunctionName.VNCS_APP_SENDHOLS)||str.equals(FunctionName.VNCS_APP_SENDLINE)
                ||str.equals(FunctionName.VNCS_APP_SENDLOCK)
               ) {
            return true;
        } else {
            return false;
        }
    }
}
