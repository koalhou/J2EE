<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/alarm.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/badDrive.css" />" />
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseonly_css.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style type="text/css">
	.car-status3{
		height:195px;
		min-width:1070px;
		margin:0 auto;
	}
	</style>
</head>
<body>    
	<div id="wrapper">		
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">
			<s:form id="oil_form" action="" method="post">
			<s:hidden id="chooseorgid" name="chooseorgid" />
			<s:hidden id="orgname" name="orgname"/>
			<s:hidden id="vehicle_vin" name="oilused.vehicle_vin"/>
			
			<s:hidden id="oilused.sortname" name="oilused.sortname"/>
			<s:hidden id="oilused.sortorder" name="oilused.sortorder"/>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td id='leftdiv' valign="top" class="leftline">
						<div id="content_leftside">
							<div id="leftInfoDiv" class="treeTab">
								<a href="javascript:void(0);" class="tabfocus">组织</a>
								<a href="javascript:void(0);" onclick="HideandShowControl()" class="hideLeft"></a>
							</div>
							<div id="lefttree" class="treeBox">
								<ul id="treeDemo" class="ztree"></ul>
							</div>	
						</div>
					</td>
					<td bgcolor="#E9E9E9" valign="top" class="leftline" id="middeldiv" style="display: none;">
						<div id="content_middelside">
							<div>
								<a href="javascript:void(0);" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
							</div>
						</div>
					</td>
					<td valign="top" id="rightdiv">
						<div id="content_rightside">
							<div class="titleBar">
								<div class="title"><s:text name="油耗与里程统计" /></div>
								<!--  <div class="title"><s:text name="menu3.youhaobaogao" /></div>-->
<%-- 								<s:if test="#session.perUrlList.contains('111_3_4_6_2')"> --%>
								<div class="workLink" style="float:right;">
									<div>
										<a href="javascript:void(0);"  class="squarLink" onfocus="this.blur()" onclick="exportcar();return false;">
										<img src="<s:url value='../images/btn_tool/btn_export.png'/>" align="bottom"/>导出
										</a>
									</div>
								</div>
<%-- 								</s:if> --%>
						</div>

						
						<div id="statusManger">
							<div class="car-info">
								<h1 id="carln">未选车</h1>
								<span id="messagetime" class="times" style=display:none><s:property value="oilused.check_time_code"/>
								</span>
<%-- 								<span id="totalmessageandoil" class="times" style=display:none><s:property value="oilused.check_time_code"/> --%>
<!-- 								</span> -->
							</div>
							<div class="car-status3">
								<div id="lineChart" class="car-badDrive-alarm" style="display: none;">
								</div>
								<div id="noDataDiv" style="text-align:center;position: relative; top: 20px;">
									<img src="<s:url value='/newimages/pic_nodata.png'/>"/>
								</div>
								<div id="zoomPic" style="float:left; position: relative; top: -170px; left: 50px; z-index: 99;display: none;" class="magnifier_a" onclick="showOilDetail();" title="点击查看大图">						
								</div>
							</div>
							<div class="list-area">
								<div class="list-sech">
									<div class="div_float">
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<th align="right">班车号：</th>
												<td align="left" style="padding: 0 5px;">
													<s:textfield id="vehicle_code" name="vehicle_code" maxlength="32" onkeypress="if(event.keyCode==13){searchOilused();}"/>
												</td>
												<th align="right">&nbsp;&nbsp;<s:text name="common.vehcileln" />：</th>
												<td align="left" style="padding: 0 5px;">
													<s:textfield id="vehicle_ln" name="vehicle_ln" maxlength="32" onkeypress="if(event.keyCode==13){searchOilused();}"/>
												</td>												
												<td>&nbsp;&nbsp;</td>
												<th align="right">考核月度：</th>
												<td>
													<select id="check_time_code" name="oilused.check_time_code" style="width:120px" onchange="searchOilused()"/>
												</td>
												<td>&nbsp;&nbsp;</td>
												<td>
													<s:if test="#session.perUrlList.contains('111_3_4_6_1')">
													<a href="javascript:void(0);" class="btn-blue" onclick="searchOilused()">查询</a>
													</s:if>
												</td>
												<td align="center" width="150" style="color:#797979;">( 不能查询当日数据 )</td>
											</tr>
										</table>
									</div>
								</div>
								<div id="parentSpan1" style="padding: 4px;">
									<table id="gala" ></table>
								</div>
								<table border="0" cellpadding="0" cellspacing="3px">
									<tr>
										<th align="right">&nbsp;合计油耗：</th>
										<td align="left" style="padding: 0 5px; width: 110px;">
											<span id="totaloil"/>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<th align="right">合计里程：</th>
										<td align="left" style="padding: 0 5px; width: 110px;">
											<span id="countmileage"/>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<th align="right">平均百公里油耗：</th>
										<td align="left">
											<span id="avgOilPerMile"/>
										</td>
										
									</tr>
								</table>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</s:form>
	<div id="oilDetail" class="chartDetail_c_oil" style="display:none; width: 1150px;">
		<div class="chartDetail_title_oil" id="divTitle">
			<%-- <img src="<s:url value='/newimages/chartDetail_t_l.gif'/>" class="lt"/>
			<img src="<s:url value='/newimages/chartDetail_t_r.gif'/>" class="rt"/> --%> 
			油耗趋势
			<img src="<s:url value='/newimages/chartDetail_t_close.png'/>" class="close" onclick="closeOilDetail();"/>
		</div>
		<div class="chartDetail_cent_oil001" style="width: 1150px; height: 280px;">
		<div class="chartDetail_cent_oil" style="width: 1130px; height:280px;">
			<div style="height:30px; text-align:center;">
				<table>
					<tr>
						<td>&nbsp;&nbsp;</td>
						<td align="right"><strong>班车号：</strong>
						</td>
						<td>
							<input id="vehicle_code_vs" type="text" style="background:url(../newimages/input_bg80.png) no-repeat left top; border:none; width:75px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
						</td>
						<td>&nbsp;&nbsp;</td>
						<td align="right"><strong>车牌号：</strong>
						</td>
						<td>
							<input id="vehicle_ln_vs" type="text" style="background:url(../newimages/input_bg80.png) no-repeat left top; border:none; width:75px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="right"><strong>部门：</strong>
						</td>
						<td>
							<input id="org_name_vs" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="right"><strong>考核月度：</strong>
						</td>
						<td>
							<input id="time_name_vs" type="text" style="background:url(../newimages/input_bg80.png) no-repeat left top; border:none; width:75px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
						</td>
						<td align="right">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
			<div id="oilChartDetail" class="chartDetail_c_c"></div>
		</div>
		</div>
		<div class="chartDetail_bottom_oil">
	</div>
	</div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseonly_js.jsp"%>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/OilLine.js'></script>
<script type='text/javascript' src='../scripts/fusioncharts/FusionCharts.js'></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/md5/base64.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mf.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/form.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
<%@include file="oilusedTree_js.jsp"%>
</body>
</html>
