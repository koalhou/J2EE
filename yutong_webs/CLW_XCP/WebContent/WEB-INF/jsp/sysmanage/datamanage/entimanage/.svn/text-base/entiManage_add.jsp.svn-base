<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body onload="show_enterprise_country();newload();">	
<div id="wrapper">
  <%@include file="/WEB-INF/jsp/common/header.jsp"%>
	    <div id="content">
<div id="main" style="overflow:auto">
<s:form id="clwForm" name="clwForm"
			action="entimanage_do_add" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
     <td valign="top" colspan="2">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="36" valign="top" background="../images/tree_tabBg.gif">
				<div class="toolbar">
				<div class="contentTil">组织结构</div>
				
				</div>
				</td>
			</tr>
		</table>
     </td>
    </tr>
	<tr>
		<td valign="top">
			<input type="hidden" id="ChooseEnterID_tree" name="ChooseEnterID_tree" value="<%=session.getValue("ChooseEnterID_tree") %>">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <td align="right">
                   <div id="message">
                     <s:actionerror theme="mat" />
                     <s:fielderror theme="mat"/>
                     <s:actionmessage theme="mat"/>
                   </div>
                 </td>
               </tr>
             </table>
			<table class="msgBox2" width="650" border="0" align="center"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="32"><span
						class="msgTitle">&nbsp;&nbsp;新建组织结构</span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
						  <td align="right">&nbsp;</td>
						  <td height="28" align="right"><s:text name="enterprise.info.PARENT_NAME" />:</td>
						  <td colspan="4" align="left"><label id="PARENT_NAME"><s:property value="enterpriseDataInfo.short_name"/>  </label><input type="hidden" id="PARENT_ID" name ="PARENT_ID" value="<s:property value="enterpriseDataInfo.enterprise_id"/>"></td>
						</tr>
						<tr>
						  <td align="right">&nbsp;</td>
						  <td width="100"  height="28" align="right"><s:text name="enterprise.info.FULL_NAME" />:</td>
						  <td colspan="4" align="left"><label><input type="text"
								id="FULL_NAME" name="FULL_NAME" size="58"  maxlength="60"></label></td>
						</tr>
						<tr>
						  <td align="right">&nbsp;</td>
						  <td height="28" align="right"><s:text name="enterprise.info.ADDRESS" />:</td>
						  <td colspan="4" align="left"><label><input type="text"
								id="ADDRESS" name="ADDRESS" size="58" maxlength="60"></label></td>
						</tr>
						<tr>
						  <td align="right">&nbsp;</td>
						  <td height="28" align="right"><s:text name="enterprise.info.ENTERPRISE_COUNTRY" />:</td>
							<td align="left">
							<label> 
							<s:select id="ENTERPRISE_COUNTRY" name="ENTERPRISE_COUNTRY" list="countryInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_province()">							
							</s:select></label>
						  </td>
							<td width="6">&nbsp;</td>
							<td align="right"><s:text name="enterprise.info.ENTERPRISE_PROVINCE" />:</td>
							<td width="250" align="left"><label> 
						  <s:select id="ENTERPRISE_PROVINCE" name="ENTERPRISE_PROVINCE" list="provinceInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_city()">						  </s:select></label></td>
						</tr>
						<tr>
						  <td align="right">&nbsp;</td>
						  <td height="28" align="right"><s:text name="enterprise.info.ENTERPRISE_CITY" />:</td>
							<td align="left"><label> 
						  <s:select id="ENTERPRISE_CITY" name="ENTERPRISE_CITY" list="cityInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">						  </s:select></label></td>
							<td>&nbsp;</td>
							<td align="right" width="142">
						  <s:text name="enterprise.info.SHORT_NAME" />:</td>
						  <td align="left"><label><input type="text"  size="20" id="SHORT_NAME"
								name="SHORT_NAME"  maxlength="10"></label></td>
						</tr>
						<tr>
						  <td width="17" align="right">&nbsp;</td>
						  <td width="76" height="28" align="right"><s:text name="enterprise.info.EMAIL" />:</td>
							<td width="168" align="left" class="fsBlack"> <label>
						  <input type="text" id="EMAIL" name="EMAIL"  maxlength="30"> </label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:text name="enterprise.info.POSTCODE" />:</td>
						    <td align="left"><label><input type="text" id="POSTCODE"
								name="POSTCODE"  maxlength="10"></label></td>
						</tr>
						<tr>
						  <td width="17" align="right">&nbsp;</td>
						  <td width="76" height="28" align="right"><s:text name="enterprise.info.CONTACT_P" />:</td>
							<td width="168" align="left" class="fsBlack"> <label>
						    <input type="text" id="CONTACT_P" name="CONTACT_P"  maxlength="30"> </label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:text name="enterprise.info.CONTACT_TEL" />:</td>
						   <td align="left"><label> <input type="text" id="CONTACT_TEL"
								name="CONTACT_TEL" maxlength="11"> </label></td>
						</tr>
						<tr>
						  <td height="5" colspan="7" align="right"></td>
					  </tr>
						<tr>
						  <td align="right">&nbsp;</td>
						  <td height="28" align="right" valign="top"><s:text name="enterprise.info.ENTERPRISE_DESC" />:</td>
							<td colspan="5" align="left"><label> <textarea
								id="ENTERPRISE_DESC" name="ENTERPRISE_DESC"  cols="67" rows="6" style="font-family:'Microsoft Yahei', 微软雅黑, 宋体,Tahoma, Arial, Verdana;"></textarea>
								<div id="ENTERPRISE_DESC_show" class="error"></div>
						  </label></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class="btnBar"><a href="<s:url value='/enti/entimanage.shtml' />" class="buttontwo"><s:text name="button.cancle" /></a> <a
						href="#" class="buttontwo" onclick="submitPostFrom();"><s:text name="button.queding" /></a></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</s:form>
</div>
</div>
 <%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="entiManageadd_js.jsp"%>
</body>
</html>