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

	        <td valign="top" border="0">
				<div id="content_rightside">
				<div class="titleBar">
					<div class="title"><s:text name="客流统计" /></div>
		         	<s:if test="#session.perUrlList.contains('111_3_4_2_2')">
		            	<div class="workLink">
						<span style='margin-top:2px;margin-right:20px;float:right; width:82px; height:25px; background:url(../newimages/alarm_btn_bg.gif) no-repeat left top; line-height:25px; text-align:left;'><a href='javascript:void(0);' onclick='viewDetail();' style='width:82px; height:25px; display:block; color:#1e1e1e;background:url(../newimages/statistics/viewDetail.png) no-repeat 4px 3px;text-indent:26px;'>线路详情</a></span>
						</div>
					</s:if>
	            </div>
	            <div id="statusManger">
				<div class="list-area">
						<table  border="0" cellpadding="0" cellspacing="0">
						<tr style="height:42px;">
						<td><div style="margin-left:25px;font-size:12px;">班次：</div></td>
						<td>
							<div id="all" style="display:inline-block;width:57px;height:26px;line-height:26px;margin-left:10px;background-color:#5CB0EE;color:white;text-align:center;font-size:12px;cursor:pointer;">全部</div>
							<div id="morning" style="display:inline-block;width:57px;height:26px;line-height:26px;margin-left:10px;text-align:center;font-size:12px;cursor:pointer;">早班</div>
							<div id="night" style="display:inline-block;width:57px;height:26px;line-height:26px;margin-left:10px;text-align:center;font-size:12px;cursor:pointer;">晚班</div>
						</td>
						<td></td>
						<td></td>
						</tr>
						<tr style="height:42px;">
						<td><div style="margin-left:25px;font-size:12px;">时间：</div></td>
						<td align="left">
							<div id="last7" style="display:inline-block;width:57px;height:26px;line-height:26px;margin-left:10px;background-color:#5CB0EE;color:white;text-align:center;font-size:12px;cursor:pointer;">最近7天</div>
							<div id="last30" style="display:inline-block;width:57px;height:26px;line-height:26px;margin-left:10px;text-align:center;font-size:12px;cursor:pointer;">最近30天</div>
							<!-- 
							<div id="custom" style="display:inline-block;width:52px;height:22px;line-height:22px;margin-left:20px;text-align:center;font-family:'宋体';font-size:12px;cursor:pointer;">自定义</div>
							 -->
						</td>
						<td width="80" align="right" style="font-family:'宋体';font-size:12px;">日期区间：</td>
							<td width="122">
							<input readonly="readonly" id="begintime" name="begintime" value="${begintime}" class="Wdate" type="text"
	                     	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'endtime\')}',maxDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endtime\')}',isShowClear:false})" />
							</td>
							<td width="40" align="center" style="font-family:'宋体';font-size:12px;">至</td>
							<td width="125">
               					<input readonly="readonly" id="endtime" name="endtime" value="${endtime}" class="Wdate"
	                    				type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begintime\')}',maxDate:'%y-%M-%d',isShowClear:false})" />
							</td>
               				<td align="left"><a href="javascript:void(0);" class="btn-blue"  onclick="checkDate();">查询</a></td>
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
			
				<div class='showVacation' style="margin-top:10px;">
				<div style="margin-left:20px;">
				<input type="checkbox" id="showVacation" style="vertical-align:middle;"/>&nbsp;<label for="showVacation" style="vertical-align:middle;">仅显示工作日</label>
				<img src="<s:url value='../images/linechartexplain.jpg'/>" align="right"/>
				</div>
				</div>
				
				
				
				<div id="lineChart" style="border:1px solid #CCCCCC;margin-left:10px;margin-right:10px;margin-top:10px;height:50%;"></div>
				
				<div id="statInfo" style="border:1px solid #E1E1E1;margin-left:10px;margin-right:10px;margin-top:10px;height:117px;background-color:#F3F3F3;">
					<div id="statInfoDesc" style="float:left;width:90%;">
						<div style="height:59px;line-height:59px;text-align:center;width:100%;font-size:16px;">
							宇通客车累计发车<span id="banci1" style="font-size:25px;">0</span>班次，
							提供座位<span id="zuowei1" style="font-size:25px;">0</span>个，
							实际服务<span id="renci1" style="font-size:25px;">0</span>人次。
						</div>
						<div style="height:59px;line-height:30px;text-align:center;width:100%;font-size:16px;">
							一厂累计发车<span id="banci2" style="font-size:25px;">0</span>班次，
							提供座位<span id="zuowei2" style="font-size:25px;">0</span>个，
							实际服务<span id="renci2" style="font-size:25px;">0</span>人次。
							二厂累计发车<span id="banci3" style="font-size:25px;">0</span>班次，
							提供座位<span id="zuowei3" style="font-size:25px;">0</span>个，
							实际服务<span id="renci3" style="font-size:25px;">0</span>人次。</div>
					</div>
					<div style="width:118px;height:118px;float:right;text-align:center;background-color:EBEBEB;border-left:1px solid #E1E1E1;cursor:pointer;" onclick="exportData();">
						<img style="margin-top:18px;" src="../newimages/statistics/export.png"></img>
						<div style="margin-top:23px;">导出详细数据</div>
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
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseroute3.jsp"%>
<script type='text/javascript' src='../scripts/fusioncharts/FusionCharts.js'></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>

<script type="text/javascript" src="../scripts/statistics/passengerstat/passengerstat1.js"></script>
</body>

</html>