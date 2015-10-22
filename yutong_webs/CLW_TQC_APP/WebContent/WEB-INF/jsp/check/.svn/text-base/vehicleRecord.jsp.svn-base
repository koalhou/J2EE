<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterprise_css.jsp"%>
<title><s:text name="common.title" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stylesheets/gpspostion.css" />" />
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stylesheets/pop.css" />" />
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stylesheets/alarm.css" />" />
<style type="text/css">
.overDiv {
	z-index: 10;
	position: absolute;
	width: 100%;
	background-color: #878787;
	display: none;
	filter: alpha(opacity = 60);
}

.msgDiv {
	z-index: 12;
	position: absolute;
	width: 500px;
	height: 210px;
	display: none;
	background-color: #F3F3F3;
}

.noticeMsg {
	color: red;
	font-size: 12px;
	font-family: "微软雅黑";
}
</style>
<!-- 中文注释 -->
</head>
<body>
	<div id="wrapper" style="position: absolute;">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">

			<input type="hidden" id="vehicleLn" name="vehicleLn" />
			<s:hidden id="curr_date" name="searchVO.curr_date"/>
			
			<div class="popBox">
				<div id="popArea" class="mk-sidelayer mk-sidelayer-small"
					style="overflow: hidden; display: none;">
					<div class="mk-sidelayer-toolbar">
						<span class="mk-sidelayer-bar-btn show"></span>
						<h1 class="mk-sidelayer-bar-title"></h1>
						<div class="mk-sidelayer-tools" style="overflow: hidden;"></div>
					</div>
					<div class="mk-sidelayer-content"></div>
				</div>
			</div>

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
							<div id="treeDemoDiv" class="treeBox" style="float:left">
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
								<div class="title">公车私用巡检</div>
								<div class="workLink">&nbsp;</div>
							</div>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td id="centerContent">
										<div class="alarm_tab" style="margin-top: 5px;">
											<a class="alarm_tabs" flag="1" href="javascript:void(0)"
												id="deal0">异常用车</a> <a flag="2" href="javascript:void(0)"
												id="deal2">处理记录</a>
										</div>
										<table id="changeTable" width="100%" border="0"
											cellspacing="0" cellpadding="0">
											<tr>
												<td class="reportOnline2"><s:form id="illform"
														action="exportIllListAction" method="post">

														<input type="hidden" id="vehicleVin" name="searchVO.vins" />
														<table border="0" cellspacing="4" cellpadding="0" width="100%">
															<tr>
																<td>
																<div style="float: left;margin-top: 5px;">
																<!-- 
																<label>班车号：</label>
																<s:textfield id="vehicle_code"
																		name="searchVO.vehicle_code"
																		onkeypress="if(event.keyCode==13){illSearchList();}" />
															     -->
																<label>发车日期：</label>
																<input readonly="readonly"
																	id="beginTime" name="searchVO.beginTime"
																	value="${searchVO.beginTime}" class="Wdate" type="text"
																	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	isShowToday:false,
																	maxDate:'#F{$dp.$D(\'endTime\')}',
																	isShowClear:false})" />
																	至 <input readonly="readonly" id="endTime"
																	name="searchVO.endTime" value="${searchVO.endTime}"
																	class="Wdate" type="text"
																	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	isShowToday:false,
																	minDate:'#F{$dp.$D(\'beginTime\')}',
																	maxDate:'#F{$dp.$D(\'curr_date\')}',
																	isShowClear:false})" />
																	</div>
																	<div style="float: left;margin-left: 20px;">
																	<a href="javascript:void(0);"
																	class="newbtnbule" onclick="illSearchList();">查询</a>
																	</div>
																</td>
																<td style="float: right">
																<a href="javascript:void(0);"
																	class="squarLink" onclick='batchOperateShow();'>
																	<img src="<s:url value='../images/btn_tool/btn_operate.png'/>" align="bottom" />
																	批量处理</a>
																	<a href="javascript:void(0);"
																	onclick='exportIllList();' class="squarLink">
																	<img src="<s:url value='../images/btn_tool/btn_export.png'/>" align="bottom"/>
																	导出</a></td>
															</tr>
														</table>

													</s:form>
												</td>
											</tr>
											<tr>
												<td valign="top">
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td class="">
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr>
																		<td class="">
																			<div>
																				<table id="illTabl" width="100%" cellspacing="0"
																					style="display: none">
																				</table>
																			</div>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>

										</table>
										<table id="wearTable" style="display: none" width="100%"
											border="0" cellspacing="0" cellpadding="0">

											<tr>
												<td class="reportOnline2"><s:form id="operatedform"
														action="operatedSearchListAction" method="post">

														<input type="hidden" id="vehicleVin" name="searchVO.vins" />

														<table border="0" cellspacing="4" cellpadding="0" width="100%">
															<tr>
																<td>
																<div style="float: left;margin-top: 5px;">
																<!-- 
																<s:label>班车号：</s:label>
																<s:textfield id="vehicle_code"
																		name="searchVO.vehicle_code"
																		onkeypress="if(event.keyCode==13){operatedSearchList();}" />
																 -->
																<s:label>发车日期：</s:label>
																<input readonly="readonly"
																	id="beginTime1" name="searchVO.beginTime"
																	value="${searchVO.beginTime}" class="Wdate" type="text"
																	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	isShowToday:false,
																	maxDate:'#F{$dp.$D(\'endTime1\')}',
																	isShowClear:false})" />
																	至 <input readonly="readonly" id="endTime1"
																	name="searchVO.endTime" value="${searchVO.endTime}"
																	class="Wdate" type="text"
																	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	isShowToday:false,
																	minDate:'#F{$dp.$D(\'beginTime1\')}',
																	maxDate:'#F{$dp.$D(\'curr_date\')}',
																	isShowClear:false})" />
																<s:label>类别：</s:label>
																<select id="operate_type"
																	name="searchVO.operate_type" onchange="operatedSearchList()">
																		<option value="" selected="selected">全部</option>
																		<option value="0">公车私用</option>
																		<option value="1">正常用车</option>
																</select>
																</div>
																<div style="float: left;margin-left: 20px;">
																<a href="javascript:void(0);"
																	class="newbtnbule" onclick="operatedSearchList();">查询</a>
																</div>
																</td>
																<td style="float: right"><a href="javascript:void(0);"
																	onclick='exportOperatedList();' class="squarLink">
																	<img src="<s:url value='../images/btn_tool/btn_export.png'/>" align="bottom" />
																	导出</a>
																</td>
															</tr>
														</table>
													</s:form>
												</td>
											</tr>
											<tr>
												<td valign="top">
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td class="">
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr>
																		<td class="">
																			<div>
																				<table id="operatedTabl" width="100%"
																					cellspacing="0" style="display: none">
																				</table>
																			</div>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>

										</table>
									</td>
								</tr>
							</table>
						</div></td>
				</tr>
			</table>
		</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<div id="overDiv" class="overDiv"></div>
	<div id="msgDiv" class="msgDiv">
		<div style="background-color: #E1E1E1; height: 40px; width: 100%;">
			<span
				style="float: left; font-size: 15px; font-weight: bold; margin-top: 10px; margin-left: 15px;">批量意见处理</span>
			<img src="<s:url value='../newimages/dialog_closebtn.png'/>"
				style="margin-right: 10px; margin-top: 10px; cursor: pointer; float: right;"
				onclick="closeDialog();" align="right" alt="关闭" />
		</div>
		<div style="height: 10px; margin-left: 50px;">
			<span id="errormsg" class="noticeMsg"></span>
		</div>
		<table style="margin-left: 50px; width: 440px;">
			<tr>
				<td height="30px;"><span class="noticeMsg">*</span>是否为公车私用:</td>
				<td><input type="radio" name="rtype" id="rtype" value="0"/>公车私用</td>
				<td><input type="radio" name="rtype" id="rtype" value="1" />正常用车</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" height="80px;">
				<textarea id="desc" rows="4"
						cols="47"
						onfocus="javascript:if(this.value=='处理意见(必填)')this.value='';this.style.color='#323232';"
						onblur="javascript:if(this.value=='') this.value='处理意见(必填)';this.style.color='#D2D2D2';" style="color: #D2D2D2;">处理意见(必填)</textarea>
				</td>
			</tr>
			<tr>
				<td><span style="color: #D2D2D2;">不超过<font size="3">50</font>个字</span></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td align="center"><a class="btnbule" onfocus="this.blur()"
					href="javascript:void(0)" onclick="batchOperateOk();">处理</a></td>
			</tr>
		</table>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%-- 	<%@include file="/WEB-INF/jsp/treemanage/zTree_enterprise_js.jsp"%> --%>
	
	<%@include file="check_zTree_enterprise_js.jsp"%>
	
	<script type="text/javascript"
		src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"
		src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<%@include file="vehicleRecord_js.jsp"%>
</body>
</html>