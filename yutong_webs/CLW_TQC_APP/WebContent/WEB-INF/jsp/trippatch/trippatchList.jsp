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
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/gpspostion.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />		
<style type="text/css">
	.overDiv{
		z-index: 10;
		position: absolute;
		width: 100%;
		background-color:#878787;
		display: none;
		filter:alpha(opacity=60);
	}
	.msgDiv{
		z-index: 12;
		position: absolute;
		width: 500px;
		height: 210px;
		display: none;
		background-color: #F3F3F3;
	}
	.noticeMsg{
		color:red;
		font-size:12px;
	    font-family:"微软雅黑";
	}
</style>
<!-- 中文注释 -->
</head>
<body>
	<div id="wrapper" style="position: absolute;">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">

			<input type="hidden" id="vehicleLn" name="vehicleLn" />
			
			<div class="popBox">
			  <div id="popArea" class="mk-sidelayer mk-sidelayer-small" style="overflow:hidden;display: none;">
				  <div class="mk-sidelayer-toolbar">        
				        <span class="mk-sidelayer-bar-btn show"></span>
				        <h1 class="mk-sidelayer-bar-title"></h1>
				        <div class="mk-sidelayer-tools" style="overflow:hidden;">
				        </div>
				  </div>
				  <div class="mk-sidelayer-content">
				  </div>
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
								<div class="title">临时派车补录</div>
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
										<td class="reportOnline2">
										<s:form id="conditionselectform" action="exportVehicleCheckList" method="post">
											<s:hidden id="searchVO.sortName" name="searchVO.sortName"/>
											<s:hidden id="searchVO.sortOrder" name="searchVO.sortOrder"/>
												
												<input type="hidden" id="vehicleVin" name="searchVO.vins" />
												<div style="height: 40px;" align="center">
												<!-- 
												<div style="float: left;margin-top: 10px;margin-left: 10px;">
												班车号：<s:textfield id="vehicle_code" name="searchVO.vehicle_code" />
												</div>
												<div style="float: left;margin-top: 10px;margin-left: 10px;">
												车牌号：<s:textfield id="vehicle_ln" name="searchVO.vehicle_ln"/>
												</div>
												 -->
												<div style="float: left;margin-top: 10px;margin-left: 10px;">
												日期：<input readonly="readonly"
															id="beginTime" name="searchVO.start_time" value="${searchVO.start_time}"
															class="Wdate" type="text"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	isShowToday:false,
																	maxDate:'#F{$dp.$D(\'endTime\')}',
																	isShowClear:false})" />
															至 <input readonly="readonly" id="endTime" name="searchVO.end_time" 
															value="${searchVO.end_time}" class="Wdate" type="text"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	isShowToday:false,
																	minDate:'#F{$dp.$D(\'beginTime\')}',
																	maxDate:'#F{$dp.$D(\'curr_date\')}',
																	isShowClear:false})" />
												<s:hidden id="curr_date" name="searchVO.cur_time"/>
												</div>
												<div style="float: left;margin-top: 10px;margin-left: 10px;">
												用车类型：<select id="use_type"
															name="searchVO.type" onchange="searchList();" >
																<option value="" selected="selected">全部</option>
																<option value="1">通勤</option>
																<option value="2">公差</option>
														</select>
												</div>
												<div style="float: left;margin-top: 10px;margin-left: 10px;">
												行驶线路：	<s:textfield id="route_name"
																name="searchVO.route_name" maxlength="20"
																onkeypress="if(event.keyCode==13){searchList();}" />
												</div>
												<div style="float: left;margin-left: 20px;margin-top: 5px;">
												<a href="javascript:void(0);" class="newbtnbule" onclick="searchList();">查询</a>
												</div>
												</div>
											</s:form></td>
									</tr>
									<tr>
										<td valign="top">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" class="reportOnline">
												<tr>
													<td class="titleStyle">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td class="titleStyleZi">派车补录信息</td>
																<td style="float: right">
																	<a href="javascript:void(0)" onclick='addTripPatch();' class="squarLink">
																	<img src="<s:url value='../newimages/btn_add.png'/>" align="bottom"/>
																	新增</a>
																	<a href="javascript:void(0)" onclick='delTripPatch();' class="squarLink">
																	<img src="<s:url value='../newimages/btn_del.png'/>" align="bottom"/>
																	删除</a>
																</td>
															</tr>
														</table></td>
												</tr>
												<tr>
													<td class="reportInline" align="left">
														<div id="tabAreaId">
															<s:form action="tripPatchList" id="userselect"
																method="post">
																<div align="center">
																	<s:actionmessage cssStyle="color:green" />
																	<s:actionerror cssStyle="color:red" />
																	<span id="tipmsg"></span>
																</div>
															</s:form>
															<table id="usertbl" width="100%" cellspacing="0">
															</table>
														</div></td>
												</tr>
											</table></td>
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
	<%@include file="zTree_enterprise_js.jsp"%>
	<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<%@include file="trippatchList_js.jsp"%>
</body>
</html>