<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<title><s:property value="getText('xs.menu')"/>&nbsp;|&nbsp;<s:property value="getText('versionmanage.menu')"/></title>
</head>
<body>
<%@include file="../common/menu.jsp"%>
<%@include file="versionManage_info_validate.jsp"%>

<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<s:form id="clwForm" name="clwForm"
			action="versionmanage_do_edit" method="post">
			<s:hidden id="page" name="page" />
			<s:hidden id="pageSize" name="pageSize" />
			<s:hidden id="version_id" name="sysVersionInfo.version_id" ></s:hidden>
			<input type="hidden" id="temp_version_id" name="version_id" value="<s:property value='sysVersionInfo.version_id'/>"/>
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
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('versionmanage.info.list2')"/>(<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)</span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
			              <td align="center">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="30%" height="28" align="right"><s:property value="getText('versionmanage.info.apply_id')"/>：</td>
									<td width="60%" class="fsBlack" align="left"> <label>
										<select name="sysVersionInfo.apply_id" id="apply_id">
				                              	<option value="0" <s:if test="sysVersionInfo.apply_id==\"0\"">selected</s:if>><s:property value="getText('apply_id_0')"/></option>
				                              	<option value="1" <s:if test="sysVersionInfo.apply_id==\"1\"">selected</s:if>><s:property value="getText('apply_id_1')"/></option>
			                            </select>
									</label> </td>
									<td></td>
								</tr>
							</table>
							</td>
			            </tr>
			            <tr>
			              <td align="center">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="30%" height="28" align="right"><s:property value="getText('versionmanage.info.version_name2')"/>：</td>
									<td width="60%" class="fsBlack" align="left"> <label>
										<s:textfield id="version_name" name="sysVersionInfo.version_name" maxlength="30"/>
									</label> </td>
									<td></td>
								</tr>
							</table>
							</td>
			            </tr>
			            <tr>
			              <td align="center">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="30%" height="28" align="right"><s:property value="getText('versionmanage.info.version_time')"/>：</td>
									<td width="60%" class="fsBlack" align="left"> <label>
										<s:textfield id="version_time" name="sysVersionInfo.version_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true})" cssClass="Wdate"/>
									</label> </td>
									<td></td>
								</tr>
							</table>
							</td>
			            </tr>
			            <tr>
			              <td align="center">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="30%" height="28" align="right"><s:property value="getText('versionmanage.info.version_desc')"/>：</td>
									<td width="60%" class="fsBlack" align="left"> <label>
									<s:textarea id="version_desc" name="sysVersionInfo.version_desc" cols="60" rows="6"></s:textarea>
									<div id="version_desc_show" class="error">
									</label> </td>
									<td></td>
								</tr>
							</table>
							</td>
			            </tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td class="btnBar"><a href="<s:url value='/xs/versionmanage.shtml' />" class="buttontwo"><s:property value="getText('btn.back')"/></a>
					<s:if test="#session.perUrlList.contains('111_0_6_3_4')">
					<a href="#" onclick="submitPostDelFrom();" class="buttontwo" ><s:property value="getText('btn.delete')"/></a>
					</s:if>
					<s:if test="#session.perUrlList.contains('111_0_6_3_3')">
					<a href="#" class="buttontwo" onclick="submitPostFrom();"><s:property value="getText('btn.update')"/></a>
					</s:if>
					</td>
				</tr>
			</table>
		</s:form></td>
	</tr>
</table>

<%@include file="../common/copyright.jsp"%>

</body>
</html>