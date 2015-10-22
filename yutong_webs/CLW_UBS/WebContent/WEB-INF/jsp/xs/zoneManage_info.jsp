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
<%@include file="zoneManage_info_validate.jsp"%>

<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<s:form id="clwForm" name="clwForm"
			action="zoneManage" method="post" onsubmit="return false;">
			<s:hidden id="page" name="page" />
			<s:hidden id="pageSize" name="pageSize" />
			<s:hidden id="zone_id" name="zoneXsInfo.zone_id" ></s:hidden>
			<s:hidden id="zone_level" name="zoneXsInfo.zone_level" ></s:hidden>
			<s:hidden id="zone_parent_id" name="zoneXsInfo.zone_parent_id" ></s:hidden>
			<input type="hidden" id="zone_name_input_old" value="<s:property value='zoneXsInfo.zone_name'/>"/>
			<input type="hidden" id="zone_code_old" value="<s:property value='zoneXsInfo.zone_code'/>"/>
			<!--回退上级/回退到最高级 注释后：回退到最高级 
			<s:hidden name="zone_id" ></s:hidden>
			<s:hidden name="zone_level" ></s:hidden>
			<s:hidden name="zone_parent_id" ></s:hidden>
			 -->
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
					<td height="32"  class="msgtr"><span
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
									<s:property value="zoneXsInfo.zone_parent_name"/>
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
									<s:property value="zoneXsInfo.zone_parent_name"/>
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
							<td width="60%" class="fsBlack" align="left"> 
								<label>
								<s:textfield id="zone_name_input" name="zoneXsInfo.zone_name" maxlength="30"></s:textfield>
								</label> 
							</td>
							<td></td>
						</tr>
						<%--
						<tr>
							<td width="30%" height="28" align="right">
								创建人：
							</td>
							<td width="60%" class="fsBlack"> 
								<label>
								<s:property value="zoneXsInfo.creater"/>
								</label> 
							</td>
							<td></td>
						</tr>
						<tr>
							<td width="30%" height="28" align="right">
								创建时间：
							</td>
							<td width="60%" class="fsBlack"> 
								<label>
								<s:property value="zoneXsInfo.create_time"/>
								</label> 
							</td>
							<td></td>
						</tr>
						<tr>
							<td width="30%" height="28" align="right">
								最后修改人：
							</td>
							<td width="60%" class="fsBlack"> 
								<label>
								<s:property value="zoneXsInfo.modifier"/>
								</label> 
							</td>
							<td></td>
						</tr>
						<tr>
							<td width="30%" height="28" align="right">
								最后修改时间：
							</td>
							<td width="60%" class="fsBlack"> 
								<label>
								<s:property value="zoneXsInfo.modify_time"/>
								</label> 
							</td>
							<td></td>
						</tr>
						--%>
					</table>
					</td>
				</tr>
				
				<tr>
					<td class="btnBar">
					<a href="#" class="buttontwo" onclick="submitRollBack();"><s:property value="getText('btn.back')"/></a> 
					<s:if test="#session.perUrlList.contains('111_0_6_1_1_4')">
					<a href="#" class="buttontwo" onclick="submitPostDelFrom();"><s:property value="getText('btn.delete')"/></a>
					</s:if>
					<s:if test="#session.perUrlList.contains('111_0_6_1_1_3')">
					<a href="#" class="buttontwo" onclick="submitPostEditFrom();"><s:property value="getText('btn.update')"/></a>
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