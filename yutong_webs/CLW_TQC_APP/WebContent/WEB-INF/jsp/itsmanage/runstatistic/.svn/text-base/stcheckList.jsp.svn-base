<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterprise_css.jsp"%>
<title><s:property value="getText('common.title')" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stylesheets/gpspostion.css" />" />
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stylesheets/pop.css" />" />
<!-- 中文注释 -->
</head>
<body>
	<div id="wrapper" style="position: absolute;">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">

			<input type="hidden" id="vehicleLn" name="vehicleLn" />

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top" class="leftline" id="leftdiv">
						<div id="content_leftside">
							<div class="treeTab">
								<a class="tabfocus" onfocus="this.blur()" id="enttabid"
									onclick="tabSwitch('enttabid')">组织</a> <a class="hideLeft"
									onfocus="this.blur()" id="hideleftbutton"
									onclick="HideandShowControl()"></a>
							</div>

							<div class="newsearchPlan">
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="130px" align="right"><input maxlength="60"
											id="vehicleLntext" name="vehicleLntext" type="text"
											class="input120"
											onkeypress="if(event.keyCode==13){searchTreeClick();}" />
										</td>
										<td align="center"><a class="btnbule"
											onfocus="this.blur()" onclick="searchTreeClick()">查询</a>
										</td>
									</tr>
								</table>
							</div>
							<div id="treeDemoDiv" class="treeBox">
								<ul id="treeDemo" class="ztree"></ul>
							</div>
						</div></td>
					<td valign="top" class="sleftline" id="middeldiv"
						style="display: none;">
						<div id="content_middelside">
							<div>
								<a class="showLeft" onfocus="this.blur()" id="showleftbutton"
									onclick="HideandShowControl()"></a>
							</div>
						</div></td>
					<td valign="top" id="rightdiv">
						<div id="content_rightside">
							<div class="titleBar" id="titleBarMap">
								<div class="title">员工刷卡记录</div>
								<div class="workLink">
									 <a href="javascript:void(0)" onclick='listExport()'
										class="squarLink">
										<img src="<s:url value='../images/btn_tool/btn_export.png'/>" align="bottom" />
										导出</a> 
								</div>
							</div>
							<div id="iCenter">
								<table width="100%" border="0" cellspacing="5" cellpadding="0">
									<tr>
										<td class="reportOnline2"><s:form
												id="conditionselectform" action="exportStCheckList" method="post">
												<table border="0" cellspacing="4" cellpadding="0">
													<tr>
														<td><s:label>姓名：</s:label>
														</td>
														
														
														<td width="130px" align="left"><input maxlength="12"
															id="emp_name" name="stcheck.emp_name" type="text"
															class="input120"
															onkeypress="if(event.keyCode==13){searchList();}" />
														</td>
														
<%-- 														<td align="left"><s:textfield id="emp_name" --%>
<!-- 																name="stcheck.emp_name" onkeypress="if(event.keyCode==13){searchList();} /> -->
<!-- 														</td> -->
														
														
														<td><s:label>员工号：</s:label>
														</td>
														
														
														<td width="130px" align="left"><input maxlength="8"
															id="emp_code" name="stcheck.emp_code" type="text"
															class="input120" 
															onkeypress="if(event.keyCode==13){searchList();}"
													        onkeyup="value=value.replace(/[^\d]/g,'')"
															onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/> 
															
														</td>
														
<%-- 														<td align="left"><s:textfield id="vehicle_code" --%>
<!-- 																name="stcheck.vehicle_code"   /> -->
<!-- 														</td> -->
																
																
														<td><s:label>刷卡时间：</s:label>
														</td>
														<td align="left"><input readonly="readonly"
															id="start_time" class="Wdate"
															type="text" value="${stcheck.start_time}"
															name="stcheck.start_time"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	isShowToday:false,
																	maxDate:'#F{$dp.$D(\'end_time\')}',
																	isShowClear:false})" />
															至 <input readonly="readonly" id="end_time"
															 class="Wdate" type="text"
															value="${stcheck.end_time}" name="stcheck.end_time"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	isShowToday:false,
																	minDate:'#F{$dp.$D(\'start_time\')}',
																	maxDate:'%y-%M-%d',
																	isShowClear:false})" />

														</td>
														<td><a href="javascript:void(0);" class="newbtnbule"
															onclick="searchList();">查询</a>
														</td>
													</tr>
												</table>

											</s:form></td>
									</tr>
									<tr>
										<td valign="top">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" class="reportOnline">
												<tr>
													<td align="left">
														<div id="tabAreaId">
															<s:form action="stcheckList" id="userselect"
																method="post">
																<div id="Below_new" align="center">
																	<s:actionmessage cssStyle="color:green" />
																	<s:actionerror cssStyle="color:red" />
																</div>
															</s:form>
															<table id="usertbl" width="100%" cellspacing="0">
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</div></td>
				</tr>
			</table>
		</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<%@include file="runstatistic_zTree_enterprise_js.jsp"%>
	<script type="text/javascript"
		src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"
		src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<%@include file="stcheckList_js.jsp"%>
</body>
</html>