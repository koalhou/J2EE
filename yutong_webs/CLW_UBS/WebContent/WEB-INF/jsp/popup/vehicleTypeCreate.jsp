<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<title><s:property value="getText('common.title')" /></title>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<style type="text/css">
body {
	background: #f8f8f8;
	color: #555;
	font: 12px/ 180% Tahoma, Arial, "宋体", "微软雅黑";
	margin: 0;
	width: 350px;
	min-width: 350px;
}

.msgBoxPass {
	margin: 10px auto;
	background: #eee url(../images/msg_title_bg.gif) repeat-x top left;
	border: 1px solid #b8b8b8;
	padding: 0 8px;
	width: 350px;
}

.msgTitlePwd{
	color: #000;
	font-weight: bold;
	line-height: 30px;
	height: 40px;
}
</style>
</head>

<body>
<%@include file="vehicleTypeCreate_validate.jsp"%>
<s:form id="vehicleTypeCreate_form" action="createVehicleType" method="post">
	<table id="contenttable" width="400" border="0" cellspacing="0"	cellpadding="0" align="center">
		<tr>
			<td valign="top" align="center">
				<s:label id="errorLbl" cssStyle="font-size:12px;color:#CC0000"/>
				<s:label id="successLbl" cssStyle="font-size:12px;color:#009900"/>
			</td>
		</tr>
		<tr>
			<td align="center">
			<table class="msgBoxPass" border="0" align="center" cellpadding="0"	cellspacing="0">
				<tr>
					<td height="32" background="<s:url value='/images/msg_title_bg.gif'/>">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="32" align="left">
								<span class="msgTitlePwd">车型信息</span>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					    <tr>
			              <td width="30%" height="28" align="right">车型品牌：
			              </td>
			              <td width="60%" align="left">
			                <s:select id="parentId" 
			                          name="parentId" 
			                          list="brandMap"  
			                          headerKey="" 
			                          headerValue="%{getText('please.select')}" 
			                          onchange="onBrandChange()"
			                          cssStyle="width:130px;">
			                </s:select>
			              </td>
			              <td>&nbsp;</td>
			            </tr>
			            
						<tr>
			              <td width="30%" height="28" align="right">
			                <s:property value="getText('base.info.code')" />：
			              </td>
			              <td width="60%" align="left">
			                <s:textfield id="codeId" name="codeId" maxlength="20"/>
			              </td>
			              <td width="1%">&nbsp;</td>
			            </tr>
			            <tr>
			              <td width="30%" height="28" align="right">
			                <s:property value="getText('base.info.codename')"/>：
			              </td>
			              <td width="60%" align="left">
			                <s:textfield id="codeName" name="codeName" maxlength="20"/>
			              </td>
			              <td width="1%">&nbsp;</td>
			            </tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class="btnBar">
						<a href="javascript:art.dialog.close();" class="buttontwo">取消</a>
						<a href="#" class="buttontwo"	onclick="createEnterprise()">确定</a>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>

