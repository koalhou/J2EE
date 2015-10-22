<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<title><s:property value="getText('service.management.title')"/>&nbsp;|&nbsp;<s:property value="getText('service.management.maintenance.set')"/></title>
</head>
<body>
<%@include file="../common/menu.jsp"%>
<%@include file="luxurycarset_add_validate.jsp"%>

<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<s:form id="luxurycarsetForm" name="luxurycarsetForm"
			action="luxuryCarSetDoAdd" method="post">
			<s:hidden id="page" name="page" />
			<s:hidden id="pageSize" name="pageSize" />
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
					<td height="32" class="msgtr" align="left">
					  <span	class="msgTitle">
					    &nbsp;&nbsp;<s:property value="getText('service.management.luxury.car.set')"/>
					  </span>
					</td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="30%" height="28" align="right">&nbsp;&nbsp;车工号：</td>
			              	<td width="70%" class="fsBlack" align="left">
			                	<s:textfield id="vehicle_number" name="luxuryCar.vehicle_number" maxlength="12"/>
			              	</td>
			            </tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td class="btnBar"><a href="<s:url value='/sm/luxuryCarSetInit.shtml' />" class="buttontwo"><s:property value="getText('btn.back')"/></a> 
					<a href="#" class="buttontwo" onclick="submitPostFrom();"><s:property value="getText('btn.sure')"/></a></td>
				</tr>
			</table>
		</s:form></td>
	</tr>
</table>

<%@include file="../common/copyright.jsp"%>

</body>
</html>