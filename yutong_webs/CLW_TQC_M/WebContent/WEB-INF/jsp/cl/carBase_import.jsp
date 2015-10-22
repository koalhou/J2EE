<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title><s:property value="getText('menu.cl')"/>&nbsp;|&nbsp;<s:property value="getText('menu.cl.vehicle')"/></title>
<%@include file="../common/meta.jsp" %>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="carBase_import_validate.jsp"%>
  <s:form id="clwForm" action="carbase_do_import" method="post" enctype="multipart/form-data">
    <s:hidden id="page" name="page" />
    <s:hidden id="pageSize" name="pageSize" />
    
    <table height="628px" width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td align="center" valign="top" >
    
    <div id="message">
      <s:actionerror theme="mat" />
      <s:fielderror theme="mat"/>
      <s:actionmessage theme="mat"/>
    </div>
    
    <table class="msgBox2" width="650" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>" align="left">
          <span class="msgTitle">&nbsp;&nbsp;
            <s:property value="getText('carbase.manage.import')" />
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <tr>
		      <td width="15%" height="28" align="right" ><s:property value="getText('carbase.info.brand')"/>：</td>
			  <td width="25%" align="left">
			    <s:select cssStyle="width:130px;" id="brand" name="carBaseInfo.brand" list="carEngineBrandMap" headerKey="" headerValue="%{getText('please.select')}" onchange="clearInfo2()">
				</s:select>
			  </td>
              <td>&nbsp;</td>
              <td width="20%" height="28" align="right">
              <s:property value="getText('carbase.info.engine_type')"/>：
              </td>
              <td width="25%" class="fsBlack" align="left">
              <s:textfield id="engine_type_info" maxlength="30" onclick="show_engine_type_list()"/>
			  <s:hidden id="engine_type" name="carBaseInfo.engine_type"></s:hidden>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="15%" height="28" align="right"><s:property value="getText('carbase.info.car_type_brand')"/>：</td>
              <td width="25%" class="fsBlack" align="left">
				<s:select cssStyle="width:130px;" id="car_type_brand" name="carBaseInfo.car_type_brand" list="carTypeBrandMap" headerKey="" headerValue="%{getText('please.select')}"  onchange="clearInfo()">
				</s:select>
              </td>
              <td>&nbsp;</td>
              <td width="20%" align="right"><s:property value="getText('carbase.info.vehicle_type_id')"/>：</td>
              <td width="25%" align="left">
                <s:textfield id="vehicle_type_id_info" maxlength="30" onclick="show_vehicle_type_list()"/>
				<s:hidden id="vehicle_type_id" name="carBaseInfo.vehicle_type_id"></s:hidden>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="15%" height="28" align="right">
              <s:property value="getText('carbase.info.cr_config_id')"/>：
              </td>
              <td width="25%" class="fsBlack" align="left">
              <s:select cssStyle="width:130px;" id="cr_config_id" name="carBaseInfo.cr_config_id" list="illDriveMap" headerKey="" headerValue="%{getText('please.select')}" >
			</s:select>
              </td>
              <td>&nbsp;</td>
              <td width="20%" align="right"><s:property value="getText('carbase.info.vehicle_type_info')"/>：</td>
              <td width="25%" align="left">
                <input type="text" disabled="disabled" id="vehicle_type_info">
              </td>
			  <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="15%" height="28" align="right">
                <s:property value="getText('file.choice.filepath')" />：
              </td>
              <td width="25%" class="fsBlack" colspan="5" align="left">
                <s:file label="choicefile" 
                        theme="simple" 
                        name="file" 
                        size="60"
                        onkeydown="return false;"
                        cssStyle="height: 17px;line-height: 17px;font-size: 12px;border: #006699 1px solid;padding-left: 2px;padding-top: 1px;"/>
                <a href="<s:url value='/template/CarBaseinformation.xls' />"><s:property value="getText('file.template.download')" /></a>
              </td>
            </tr>
            <tr>
              <td height="40" align="right">
                <b>
                  <span class="noticeMsg"><s:property value="getText('import.notice')" />：</span>
                </b>
              </td>
              <td height="40" align="left" colspan="5">
                <b>
                  <span class="noticeMsg"><s:property value="getText('import.tip.info')" /></span>
                </b>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar" > 
          <a href="<s:url value='/cl/carbase.shtml' />" class="buttontwo"><s:property value="getText('btn.back')"/></a>
          <a href="#" class="buttontwo" onclick="submitPostFrom();"><s:property value="getText('btn.sure')"/></a>
        </td>
      </tr>
    </table>
    
    </td>
    </tr>
    </table>
  </s:form>
  <%@include file="../common/copyright.jsp"%>
</body>
</html>
