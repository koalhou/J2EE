<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<title><s:property value="getText('menu.ub.holiday')" /></title>
</head>
<body>

<%@include file="../../common/menu.jsp"%>
<%@include file="workdayManage_add_validate.jsp"%>

<s:form id="workdayinfo_form" action="workdaymanage_do_add"
	method="post">
	<s:hidden id="page" name="page" />
	<s:hidden id="pageSize" name="pageSize" />
	<s:hidden id="dayflag" name="staitcsObject.dayflag" value="1" />

	<table height="628px" width="100%" align="center" border="0"
		cellpadding="0" cellspacing="0">
		<tr>
			<td align="center" valign="top">

			<div id="message"><s:actionerror theme="mat" /> <s:fielderror
				theme="mat" /> <s:actionmessage theme="mat" /></div>

			<table class="msgBox2" width="650" border="0" align="center"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="32"
						background="<s:url value='/images/msg_title_bg.gif'/>"
						align="left"><span class="msgTitle">&nbsp;&nbsp; <s:property
						value="getText('ub.workday.holidayinfo')" /> (<span
						class="noticeMsg">*</span><s:property
						value="getText('input.required')" />) </span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="20%" height="28" align="right"><s:property
								value="getText('ub.workday.day_name')" />：</td>
							<td width="100%" class="fsBlack" align="left"><s:textfield
								id="workdayname" name="staitcsObject.day_name" maxlength="60" /><span
								class="noticeMsg">*</span></td>
						</tr>
						<tr>
							<td width="20%" height="28" align="right"><s:property
								value="getText('ub.workday.duration')" />：</td>
							<td width="100%" class="fsBlack" align="left"><s:textfield
								id="startTime" name="staitcsObject.startDate"
								onfocus="WdatePicker({maxDate:'#F{$dp.$D(\\\'endTime\\\');}',dateFmt:'yyyy-MM-dd',autoPickDate:true})"
								cssClass="Wdate">
							</s:textfield>-<s:textfield id="endTime" name="staitcsObject.endDate"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\\\'startTime\\\');}',dateFmt:'yyyy-MM-dd',autoPickDate:true})"
								cssClass="Wdate">
							</s:textfield><span class="noticeMsg">*工作日区间必须为周末</span></td>
						</tr>


					</table>
					</td>
				</tr>
				<tr>
					<td class="btnBar">
					<s:if test="#session.perUrlList.contains('111_4_8_3_2_2')">
					<a href="#" class="buttontwo"
						onclick="goBack('workdaymanage.shtml')"> <s:property
						value="getText('btn.back')" /> </a> <a href="#" class="buttontwo"
						onclick="submitForm()"> <s:property
						value="getText('btn.sure')" /> </a></s:if></td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
</s:form>
<%@include file="../../common/copyright.jsp"%>

</body>
</html>