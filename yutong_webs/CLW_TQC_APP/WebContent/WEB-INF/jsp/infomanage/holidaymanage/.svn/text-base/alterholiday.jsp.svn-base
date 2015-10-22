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
		<s:form id="alter_holidayform" action="holiday_edit" method="post">
			<s:hidden name="page" />
			<s:hidden name="pageSize" />
			<s:hidden id="holiday_id" name="holidayInfo.holiday_id" />
			<s:hidden id="student_id" name="holidayInfo.student_id" />
			<table id="holidayTable" width="100%" border="0" cellspacing="0"	cellpadding="0">
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
								<s:fielderror theme="mat" />
								<s:actionmessage theme="mat" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
					<table class="msgBox2" width="720" border="0" align="center"
						cellpadding="0" cellspacing="0">
						<tr>
							<td height="32"><span class="msgTitle">修改请假信息</span></td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="15%" height="28" align="right">
											学号：
										</td>
										<td width="85%" class="fsBlack">
											<s:property value="holidayInfo.student_code" />
										</td>
									</tr>
									<tr>
										<td width="15%" height="28" align="right">
											姓名：
										</td>
										<td width="85%" class="fsBlack">
											<s:property value="holidayInfo.student_name" />
										</td>
									</tr>
									<tr>
										<td height="28" align="right">
											请假开始时间：
										</td>
										<td>
											<s:if test="0==holidayInfo.holiday_flag">
												<input id="holiday_start_time" 
												             name="holidayInfo.holiday_start_time"
												             value="${holidayInfo.holiday_start_time}"
												             type="text"
												             class="Wdate"
												             readonly="readonly"
												             onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,maxDate:'#F{$dp.$D(\'holiday_end_time\')}'})"
												             >
												</input>
											</s:if>
											<s:else>
												<s:property value="holidayInfo.holiday_start_time" />
											</s:else>
										</td>
									</tr>
									<tr>
										<td align="right">
											请假结束时间：
										</td>
										<td>
											<s:if test="0==holidayInfo.holiday_flag">
												<input id="holiday_end_time" 
												             name="holidayInfo.holiday_end_time"
												             value="${holidayInfo.holiday_end_time}"
												             type="text"
												             class="Wdate"
												             readonly="readonly"
												             onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,minDate:'#F{$dp.$D(\'holiday_start_time\')}'})"
												             >
												</input>
											</s:if>
											<s:else>
												<s:property value="holidayInfo.holiday_end_time" />
											</s:else>
										</td>
									</tr>
									<tr>
										<td height="28" align="right">
											请假原因：
										</td>
										<td colspan="3">
											<s:if test="0==holidayInfo.holiday_flag">
												<s:textfield id="holiday_reason" name="holidayInfo.holiday_reason" size="83" maxlength="40" />
											</s:if>
											<s:else>
												<s:property value="holidayInfo.holiday_reason" />
											</s:else>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="btnBar">
								<a href="#" onclick="goBack('holidaymanage.shtml');" class="buttontwo">
									<s:text name="button.cancle" />
								</a>
								<s:if test="0==holidayInfo.holiday_flag">
									<s:if test="#session.perUrlList.contains('111_3_3_8_4')">
										<s:url id="cancel" action="holiday_cancel">
											<s:param name="holidayInfo.holiday_id" value="holidayInfo.holiday_id" />
											<s:param name="holidayInfo.student_id" value="holidayInfo.student_id" />
											<s:param name="holidayInfo.holiday_start_time" value="holidayInfo.holiday_start_time" />
											<s:param name="holidayInfo.holiday_end_time" value="holidayInfo.holiday_end_time" />
											<s:param name="page" value="page" />
											<s:param name="pageSize" value="pageSize" />
										</s:url>
										<a href="#"	onclick="Wit.delConfirm('${cancel}', '确定要进行销假处理吗？')" class="buttontwo">
											销假
										</a>
								    </s:if> 
								    <s:if test="#session.perUrlList.contains('111_3_3_8_3')">
										<a href="#" class="buttontwo" onClick="submitalterForm()">
											<s:text name="button.edit" />
										</a>
									</s:if>
								</s:if>
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
<%@include file="alterholiday_validate.jsp"%>
</body>
</html>