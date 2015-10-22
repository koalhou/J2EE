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
		         	<s:if test="#session.perUrlList.contains('111_3_4_2_2')">
		            	<div class="workLink">
						<div class="export">
						<a href="javascript:void(0);" class="export_icon" onfocus="this.blur()" onclick="javascript:exportData();"><s:text name="button.daochu" /></a>
						</div>
						</div>
					</s:if>
	            </div>
	            <div id="statusManger">
				<div class="list-area">
						<table  border="0" cellpadding="0" cellspacing="0" style="height:42px;">
						<tr>
						<td align="left">
							<div id="today" style="display:inline-block;width:57px;height:22px;line-height:22px;margin-left:10px;background-color:#5CB0EE;color:white;text-align:center;font-weight:bold;font-family:'宋体';font-size:12px;cursor:pointer;">当日</div>
							<div id="last7" style="display:inline-block;width:57px;height:22px;line-height:22px;margin-left:10px;text-align:center;font-family:'宋体';font-size:12px;cursor:pointer;">最近七天</div>
							<div id="last30" style="display:inline-block;width:57px;height:22px;line-height:22px;margin-left:10px;text-align:center;font-family:'宋体';font-size:12px;cursor:pointer;">最近30天</div>
							<!-- 
							<div id="custom" style="display:inline-block;width:52px;height:22px;line-height:22px;margin-left:20px;text-align:center;font-family:'宋体';font-size:12px;cursor:pointer;">自定义</div>
							 -->
						</td>
						<td width="60" align="right" style="font-family:'宋体';font-size:12px;">日期：</td>
							<td width="122">
							<input readonly="readonly" id="begintime" name="begintime" value="${endtime}" class="Wdate" type="text"
	                     	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'endtime\')}',maxDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endtime\')}',isShowClear:false})" />
							</td>
							<td width="40" align="center" style="font-family:'宋体';font-size:12px;">至</td>
							<td width="125">
               					<input readonly="readonly" id="endtime" name="endtime" value="${endtime}" class="Wdate"
	                    				type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begintime\')}',maxDate:'%y-%M-%d',isShowClear:false})" />
							</td>
               				<td align="left"><a href="javascript:void(0);" class="btn-blue"  onclick="makeChart()">查询</a></td>
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
			<!-- 线路div -->
			<div id="passengerStatRoute"></div>
				<!-- 分页 -->
				<div id="routempageslipe" style="text-align:center;display:block;">
					<img id="routempagefirst" style="cursor:hand;" src="../images/pageslipe/first.gif" alt="" onclick="routempagec(this);"/>
					<img id="routempageprev" style="cursor:hand;" src="../images/pageslipe/prev.gif" alt="" onclick="routempagec(this);"/>
					<input id="routempageno" value="1" style="width: 20px;" onchange="routempagechange(this);"/>
					<span id="routempageall"></span>
					<img id="routempagenext" style="cursor:hand;" src="../images/pageslipe/next.gif" alt="" onclick="routempagec(this);"/>
					<img id="routempagelast" style="cursor:hand;" src="../images/pageslipe/last.gif" alt="" onclick="routempagec(this);"/>
				</div>
				<div id="lineChart" style="display:none;border:1px solid #CCCCCC;margin-left:10px;margin-right:10px;margin-top:10px;height:346px;">fdafda</div>
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
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseroute3.jsp"%>
<script type='text/javascript' src='../scripts/fusioncharts/FusionCharts.js'></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>

<script type="text/javascript" src="../scripts/statistics/passengerstat/passengerstat.js"></script>
</body>

</html>