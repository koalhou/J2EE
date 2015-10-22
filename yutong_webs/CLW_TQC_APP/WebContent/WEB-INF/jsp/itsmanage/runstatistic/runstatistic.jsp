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
<style type="text/css">
a.buleLink {
	background-color: #5CB0EE;
	color: #FFF;
	display: block;
	height: 25px;
	line-height: 25px;
	text-align: center;
	width: 40px;
}

a.blackLink {
	color: black;
	display: block;
	height: 25px;
	line-height: 25px;
	text-align: center;
	width: 40px;
}
.th1{
	border:1px;
	background-image:url("../scripts/flexigrid/images/fhbg1.gif");
	border-left-color: #fff;
	border-left-style:solid;
	border-right-color: #ddd;
	border-right-style: solid;
}
.th2{
	border:1px;
	background-image:url("../scripts/flexigrid/images/fhbg.gif");
	border-left-color: #fff;
	border-left-style:solid;
	border-right-color: #ddd;
	border-right-style: solid;
	border-top-color: #fff;
	border-top-style: solid;
	border-bottom-color: #ddd;
	border-bottom-style: solid;
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
								<div class="title">运营统计</div>
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
												id="conditionselectform" action="exportList"
												method="post">

												<s:hidden id="dateFlag" name="searchVO.dateFlag"
													value="month"></s:hidden>
												<table border="0" cellspacing="4" cellpadding="0">
													<tr>
														<td><a id="amonth" href="javascript:void(0)"
															onclick="changeAcolor(this);" class="buleLink">本月</a>
														</td>
														<td><a id="ayear" href="javascript:void(0)"
															onclick="changeAcolor(this);" class="blackLink">本年</a></td>
														<td><a id="afree" href="javascript:void(0)"
															onclick="changeAcolor(this);" class="blackLink">自定义</a>
														</td>
														<td><s:label>日期：</s:label>
														</td>
														<td align="left"><input readonly="readonly"
															id="beginTime" disabled="disabled" class="Wdate"
															type="text" value="${searchVO.beginTime}"
															name="searchVO.beginTime"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	isShowToday:false,
																	maxDate:'#F{$dp.$D(\'endTime\')}',
																	isShowClear:false})" />
															至 <input readonly="readonly" id="endTime"
															disabled="disabled" class="Wdate" type="text"
															value="${searchVO.endTime}" name="searchVO.endTime"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	isShowToday:false,
																	minDate:'#F{$dp.$D(\'beginTime\')}',
																	maxDate:'#F{$dp.$D(\'yesterday\')}',
																	isShowClear:false})" />
														<s:hidden id="yesterday" name="searchVO.yesterday"/>
														</td>
														<td><a href="javascript:void(0);" class="newbtnbule" style="float:left"
															onclick="searchList();">查询</a>
															<span style="float: left;margin-top: 5px;margin-left: 10px;">(不能查询当日数据)</span>
														</td>
													</tr>
												</table>
												
												<s:hidden id="searchVO.vins" name="searchVO.vins"/>

											</s:form></td>
									</tr>
									<tr>
										<td valign="top">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" class="reportOnline">
												<tr>
													<td class="reportInline" align="left">
														<div id="tabAreaId">
															<s:form action="tripPatchList" id="userselect"
																method="post">
																<div align="center">
																	<s:actionmessage cssStyle="color:green" />
																	<s:actionerror cssStyle="color:red" />
																</div>
															</s:form>
															<div id="myHDivBox" style="border: 1px;border-color: #ccc;border-style: solid;">
																<TABLE cellSpacing=0 cellPadding=0 class="myTitle">
																<THEAD>
																<TR>
																<TH align="center" rowspan="2" class="th1">
																<DIV style="TEXT-ALIGN: center; WIDTH: 91px">厂区</DIV></TH>
																<TH  align=center rowspan="2" class="th1">
																<DIV style="TEXT-ALIGN: center; WIDTH: 60px">班车号</DIV></TH>
																<TH align=center rowspan="2" class="th1">
																<DIV style="TEXT-ALIGN: center; WIDTH: 70px">车牌号</DIV></TH>
																<TH align="center" rowspan="2" class="th1">
																<DIV style="TEXT-ALIGN: center; WIDTH: 60px">驾驶员</DIV></TH>
																<TH align="center" colspan="3" class="th2">
																<DIV style="TEXT-ALIGN: center; WIDTH: 180px">行驶里程(km)</DIV></TH>
																<TH align="center" rowspan="2" class="th1">
																<DIV style="TEXT-ALIGN: center; WIDTH: 60px">耗油量(L)</DIV></TH>
																<TH align="center" rowspan="2" class="th1">
																<DIV style="TEXT-ALIGN: center; WIDTH: 90px">百公里耗油量(L)</DIV></TH>
																<TH align="center" rowspan="2" class="th1">
																<DIV style="TEXT-ALIGN: center; WIDTH: 60px">服务人次</DIV></TH>
																<TH align="center" colspan="3" class="th2">
																<DIV style="TEXT-ALIGN: center; WIDTH: 180px">出勤次数</DIV></TH>
																<TH align="center" colspan="2" class="th2">
																<DIV style="TEXT-ALIGN: center; WIDTH: 120px">维修</DIV></TH>
																<TH align="center" colspan="2" class="th2">
																<DIV style="TEXT-ALIGN: center; WIDTH: 160px">公差</DIV></TH>
																
																<TR>
																<TH align="center" rowspan="2" class="th1">
																<DIV style="TEXT-ALIGN: center; WIDTH: 60px">载重里程</DIV></TH>
																<TH align="center" rowspan="2" class="th1">
																<DIV style="TEXT-ALIGN: center; WIDTH: 60px">空驶里程</DIV></TH>
																<TH align="center" rowspan="2" class="th1">
																<DIV style="TEXT-ALIGN: center; WIDTH: 60px">合计</DIV></TH>
																<TH align="center" class="th2">
																<DIV style="TEXT-ALIGN: center; WIDTH: 60px">早班</DIV></TH>
																<TH align="center" class="th2">
																<DIV style="TEXT-ALIGN: center; WIDTH: 60px">晚班</DIV></TH>
																<TH align="center" class="th2">
																<DIV style="TEXT-ALIGN: center; WIDTH: 60px">合计</DIV></TH>
																<TH align="center" class="th2">
																<DIV style="TEXT-ALIGN: center; WIDTH: 60px">维修次数</DIV></TH>
																<TH align="center" class="th2">
																<DIV style="TEXT-ALIGN: center; WIDTH: 60px">维修费用</DIV></TH>
																<TH align="center" class="th2">
																<DIV style="TEXT-ALIGN: center; WIDTH: 80px">公差次数</DIV></TH>
																<TH align="center" class="th2">
																<DIV style="TEXT-ALIGN: center; WIDTH: 80px">公差里程(km)</DIV></TH></TR>
																</THEAD></TABLE>
															</div>
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
<%-- 	<%@include file="/WEB-INF/jsp/treemanage/zTree_enterprise_js.jsp"%> --%>
	<%@include file="runstatistic_zTree_enterprise_js.jsp"%>
	<script type="text/javascript"
		src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"
		src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<%@include file="runstatistic_js.jsp"%>
</body>
</html>