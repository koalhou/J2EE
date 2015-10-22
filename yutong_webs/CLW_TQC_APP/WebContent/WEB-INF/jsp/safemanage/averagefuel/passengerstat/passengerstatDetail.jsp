<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
    <%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseroute_css.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<script type='text/javascript' src='../dwr/engine.js'></script>
	<script type='text/javascript' src='../dwr/util.js'></script>
	<script type='text/javascript' src='../dwr/interface/DispatchrouteChartDWR.js'></script>
	<script type='text/javascript' src='../dwr/interface/PassengerStatDwr.js'></script>
	<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
	<style type="text/css">
		body{font-family:'宋体'}	
	</style>
</head>

<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content" class="routemonitorDivArea">
	  <s:form id="export_form" action="exportRanking.shtml" method="post">
		<s:hidden id="user_org_id" name="user_org_id"/>
		<s:hidden id="chooseorgid" name="chooseorgid"/>
		
	    <table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
			<td id='leftdiv'  valign="top" class="leftline">
			<div id="content_leftside">
			  <div class="treeTab">
			  	<s:if test="#session.perUrlList.contains('111_3_5_3_5')">
				<a href="javascript:void(0);" id="treeupid" onclick="tabSwitch('treeupid')" class="tabfocus">早班线路</a>
				</s:if>
				<s:if test="#session.perUrlList.contains('111_3_5_3_6')">
				<a  class="tab" onfocus="this.blur()" id="treedownid" onclick="tabSwitch('treedownid')">晚班线路</a>
				</s:if>
				<s:if test="#session.perUrlList.contains('111_3_5_3_7')">
				<a  class="tab" onfocus="this.blur()" id="treetqcid" onclick="tabSwitch('treetqcid')">厂内通勤</a>
				</s:if>
				 <!-- <a href="javascript:void(0);" class="hideLeft" onfocus="this.blur()" onclick="HideandShowControl()"></a> -->
			  </div>
			  <div class="newsearchPlan">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr>
					<td width="130" align="right"><input id="search_route_name" type="text" class="input120" maxlength="14" onkeypress="if(event.keyCode==13){searchRoute();}"/></td>
					<td align="center"><a onclick="javascript:searchRoute()" class="btnbule">查询</a></td>
				  </tr>
				</table>
			  </div>
			  <div id="treeDemoDiv" class="treeBox">
			  	<ul id="treeDemo" class="ztree"></ul>	  		
			  </div> 
			 </div>
	        </td>
	        <td valign="top" border="0">
				<div id="content_rightside">
				<div class="titleBar">
					<div class="title"><s:text name="客流统计" /></div>
					<a href="passengerStat.shtml" style="margin-right:20px;margin-top:2px;float:right;"><img src="../newimages/statistics/back.png"/></a>
	            </div>
	            <div id="statusManger">
				<div class="list-area">
						<table  border="0" cellpadding="0" cellspacing="0" style="height:42px;">
						<tr>
						<td align="left">
							<div id="last7" style="display:inline-block;width:57px;height:26px;line-height:26px;margin-left:20px;background-color:#5CB0EE;color:white;text-align:center;font-size:12px;cursor:pointer;">最近7天</div>
							<div id="last30" style="display:inline-block;width:57px;height:26px;line-height:26px;margin-left:10px;text-align:center;font-size:12px;cursor:pointer;">最近30天</div>
							<!-- 
							<div id="custom" style="display:inline-block;width:52px;height:26px;line-height:26px;margin-left:20px;text-align:center;font-size:12px;cursor:pointer;">自定义</div>
							 -->
						</td>
						<td width="90" align="right" style="font-size:12px;">日期区间：</td>
							<td width="122">
							<input readonly="readonly" id="begintime" name="begintime" value="${begintime}" class="Wdate" type="text"
	                     	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'endtime\')}',maxDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endtime\')}',isShowClear:false})" />
							</td>
							<td width="40" align="center" style="font-size:12px;">至</td>
							<td width="125">
               					<input readonly="readonly" id="endtime" name="endtime" value="${endtime}" class="Wdate"
	                    				type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begintime\')}',maxDate:'%y-%M-%d',isShowClear:false})" />
							</td>
               				<td align="left"><a href="javascript:void(0);" class="btn-blue"  onclick="checkDateDetail();">查询</a></td>
               				<td><span id="vehicle_codeDesc" style="font-size:15px;color: red" class="noticeMsg"></span></td>
						</tr>
						</table>
						<!-- 
						<div style="width:99.6%;height:134px;border:1px solid #CCCCCC;margin:5px;">
							<div style="width:100%;background-color:#E7E7E7;height:38px;"></div>
							<div></div>
						</div>
						 -->
				</div>
			</div>
			<div id="contentData">
			<!-- 线路div -->
			<div id="passengerStatRouteTitle" style="height:38px;line-height:38px;background-color:#E7E7E7;border:1px solid #CCCCCC;margin-left:10px;margin-right:10px;margin-top:10px;">
				<span style='margin-left:10px;font-size:18px;font-weight:bold;font-family:\"宋体\"'>线路：<span id='routeName'></span></span>
				<span style="float:right;margin-right:25px;margin-top:10px;cursor:pointer;"><img id="closeOrOpen" src="../newimages/statistics/open.png" onclick="hideSiteInfo(this);" /></span>
			</div>
			<div id="siteTitle" style="border-left:1px solid #CCCCCC;border-right:1px solid #CCCCCC;border-bottom:1px solid #CCCCCC;margin-left:10px;margin-right:10px;margin-bottom:10px;">
				<!-- 
				<div id="site" style='margin-left:10px;margin-top:0px;font-size:18px;font-weight:bold;font-family:\"宋体\";'>站点：</div>
				 -->
				<div id='siteName' style='font-size:14px;font-weight:normal;width:100%;padding:10px;'></div>
			</div>
			<div style="margin-top:10px;margin-left:20px;"><input type="checkbox" id="showVacationDetail" style="vertical-align:middle;"/>&nbsp;<label for="showVacationDetail" style="vertical-align:middle;">仅显示工作日</label></div>
			<!-- 图表 -->
			<div id="stackChart" style="border-style:none;margin-left:10px;margin-right:10px;margin-top:10px;height:50%;"></div>
				<div id="statInfo" style="border:1px solid #E1E1E1;margin-left:10px;margin-right:10px;margin-top:10px;height:69px;background-color:#F3F3F3;">
					<div id="statInfoDesc" style="float:left;width:100%;">
						<div style="height:59px;line-height:59px;text-align:center;width:100%;font-size:16px;">
							该时间段内累计发车<span id="banciDetail" style="font-size:25px;">0</span>班次，
							提供座位<span id="zuoweiDetail" style="font-size:25px;">0</span>个，
							实际服务<span id="renciDetail" style="font-size:25px;">0</span>人次。
						</div>
					</div>
				</div>
				</div>
			</div>
	        </td>
	      </tr>
	    </table>
		</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<!--JS区域--> 
<script type="text/javascript" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseroute4.jsp"%>
<script type='text/javascript' src='../scripts/fusioncharts/FusionCharts.js'></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>

<script type="text/javascript" src="../scripts/statistics/passengerstat/passengerstatDetail.js"></script>
</body>

</html>