<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<s:form id="add_holidayform" action="holiday_add" method="post">
			<s:hidden name="page" />
			<s:hidden name="pageSize" />
			<table id="holidayTable" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top">
						<div class="toolbar">
							<div class="contentTil">请销假信息</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="center">
									<s:actionerror theme="mat" />
			      					<s:fielderror theme="mat"/>
			      					<s:actionmessage theme="mat"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<table class="msgBox2" width="720" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td height="32">
									<span class="msgTitle">新建请假信息</span>
								</td>
							</tr>
							<tr>
								<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="15%" height="28" align="right">
											姓名：
										</td>
										<td width="85%" class="fsBlack">
											<s:hidden id="student_id" name="holidayInfo.student_id" />
											<s:textfield id="student_name" name="holidayInfo.student_name" maxlength="10" readonly="true" onclick="choiceStu();"/>
										</td>
									</tr>
									<tr>
										<td height="28" align="right">
											请假开始时间：
										</td>
										<td>
											<input id="holiday_start_time" 
											             name="holidayInfo.holiday_start_time"
											             value="${holidayInfo.holiday_start_time}"
											             type="text"
											             class="Wdate"
											             readonly="readonly"
											             onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,maxDate:'#F{$dp.$D(\'holiday_end_time\')}'})"
											             >
											</input>
										</td>
									</tr>
									<tr>
										<td align="right">
											请假结束时间：
										</td>
										<td>
											<input id="holiday_end_time" 
											             name="holidayInfo.holiday_end_time"
											             value="${holidayInfo.holiday_end_time}"
											             type="text"
											             class="Wdate"
											             readonly="readonly"
											             onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,minDate:'#F{$dp.$D(\'holiday_start_time\')}'})"
											             >
											</input>
										</td>
									</tr>
									<tr>
										<td height="28" align="right">
											请假原因：
										</td>
										<td colspan="3">
											<s:textfield id="holiday_reason" name="holidayInfo.holiday_reason" size="83" maxlength="40" />
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td class="btnBar">
									<a href="#" class="buttontwo" onclick="goBack('holidaymanage.shtml');"><s:text name="button.cancle" /></a> 
									<a href="#" class="buttontwo" onclick="submitForm()"><s:text name="button.queding" /></a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form> 
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="addholiday_validate.jsp"%>
</body>
</html>
