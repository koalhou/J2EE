<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<title><s:property value="getText('xs.menu')"/>&nbsp;|&nbsp;<s:property value="getText('xs.bm.menu')"/>&nbsp;|&nbsp;<s:property value="getText('zonemanage.menu')"/></title>
</head>
<body>
<%@include file="../common/menu.jsp"%>
<%@include file="zoneManage_add_validate.jsp"%>

<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<s:form id="clwForm" name="clwForm"
			action="zonemanage" method="post" onsubmit="return false;">
			<s:hidden id="page" name="page" />
			<s:hidden id="pageSize" name="pageSize" />
			<s:hidden id="zone_level" name="zone_level"></s:hidden>
		    <s:hidden id="zone_parent_id" name="zone_parent_id"></s:hidden>
		    <s:hidden id="zone_name" name="zone_name"></s:hidden>
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <td align="right" bgcolor="#F6F6F6">
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
					<td height="32" class="msgtr"><span
						class="msgTitle">&nbsp;&nbsp;
					<s:if test="zone_level==\"1\""><s:property value="getText('zonemanage.info.msgtr.leve1')"/></s:if>
					<s:elseif test="zone_level==\"2\""><s:property value="getText('zonemanage.info.msgtr.leve2')"/></s:elseif>
					<s:elseif test="zone_level==\"3\""><s:property value="getText('zonemanage.info.msgtr.leve3')"/></s:elseif>
					(<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
					</span>
					</td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<s:if test="zone_level==\"2\"">
						<tr>
							<td width="30%" height="28" align="right">
								<s:property value="getText('zonemanage.info.before.leve1')"/>：
							</td>
							<td width="60%" class="fsBlack" align="left"> 
								<label>
									<s:property value="zone_parent_name"/>
								</label> 
							</td>
							<td></td>
						</tr>
						</s:if>
						<s:elseif test="zone_level==\"3\"">
						<tr>
							<td width="30%" height="28" align="right">
								<s:property value="getText('zonemanage.info.before.leve2')"/>：
							</td>
							<td width="60%" class="fsBlack" align="left"> 
								<label>
									<s:property value="zone_parent_name"/>
								</label> 
							</td>
							<td></td>
						</tr>
						</s:elseif>
						<tr>
							<td width="30%" height="28" align="right">
							<s:property value="getText('zonemanage.info.zone_code')"/>：
							</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:textfield id="zone_code" name="zoneXsInfo.zone_code" maxlength="60"/>
							</label> </td>
							<td></td>
						</tr>
						<tr>
							<td width="30%" height="28" align="right">
							<s:if test="zone_level==\"1\""><s:property value="getText('zonemanage.info.leve1')"/>：</s:if>
							<s:elseif test="zone_level==\"2\""><s:property value="getText('zonemanage.info.leve2')"/>：</s:elseif>
							<s:elseif test="zone_level==\"3\""><s:property value="getText('zonemanage.info.leve3')"/>：</s:elseif>
							<s:else><s:property value="getText('zonemanage.info.leve')"/></s:else>
							</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:textfield id="zone_name_input" name="zoneXsInfo.zone_name" maxlength="30"/>
							
							</label> </td>
							<td></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class="btnBar">
					<a href="#" class="buttontwo" onclick="submitRollBack();"><s:property value="getText('btn.back')"/></a> 
					<a href="#" class="buttontwo" onclick="submitPostFrom();"><s:property value="getText('btn.sure')"/></a></td>
				</tr>
			</table>
		</s:form></td>
	</tr>
</table>

<%@include file="../common/copyright.jsp"%>

</body>
</html>