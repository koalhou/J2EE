<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/alarm.css" />" />
	
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style>
		#weeksli {
			margin-left: 20px;
		}
		#weeksli > li {
			display:inline;
			font-size: 14px;
			margin-left: 10px;
		}
	</style>
</head>

<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">		
	<s:form id="statusdetail_form" action="editbefore" method="post">
		<s:hidden id="detailstarttime" name="vehiclestatus.start_time"></s:hidden>
		<s:hidden id="detailendtime" name="vehiclestatus.end_time"></s:hidden>
		<s:hidden id="infoorgid" name="vehiclestatus.infoorgid"></s:hidden>
		<s:hidden id="detailmonth" name="vehiclestatus.month"></s:hidden>
		<s:hidden id="detailquarter" name="vehiclestatus.quarter"></s:hidden>
		<s:hidden id="detailyear" name="vehiclestatus.year"></s:hidden>
		<s:hidden id="vehd_id" name="vehiclestatus.vehd_id"></s:hidden>
		<s:hidden id="vehd_id" name="vehiclestatus.vehicle_code"></s:hidden>
		<s:hidden id="yuanpage" name="page"></s:hidden>
		<s:hidden id="yuanpageSize" name="pageSize"></s:hidden>
		<s:hidden id="mypgnum" name="vehiclestatus.mypgnum"></s:hidden>
		<s:hidden id="myoldpagesize" name="vehiclestatus.myoldpagesize"></s:hidden>
		<input type="hidden" id="ChooseEnterID_tree" name="ChooseEnterID_tree"
			value="<%=session.getValue("ChooseEnterID_tree")%>" />
		
		<input type="hidden" id="newDayer" value=""/>
	</s:form>
	<s:form id="vehiclestatus_form" action="vehiclestatus" method="post">
		<s:hidden id="chooseorgid" name="chooseorgid" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td id='leftdiv' valign="top" class="leftline">
				<div id="content_leftside" style="float:left;">
				<div id="leftInfoDiv" class="treeTab">
								<a href="#" class="tabfocus">组织</a>
								<a href="#" onclick="HideandShowControl()" class="hideLeft"></a>
				</div>
	          <div class="newsearchPlan" style="height: 40px;">
	            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	              <tr>
	              	<td width="10px"></td>
	                <td width="130px" align="left"><input id="vehicleLn" type="text" class="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchTree();}"/></td>
	                <td align="center"><a href="javascript:searchTree()" class="btnbule">查询</a></td>
	              </tr> 
	              <tr>
	              	<td width="10px"></td>
	              	<td width="130px" align="left">
<!-- 	              		<table border="0" align="left" cellpadding="0" cellspacing="0"> -->
<!-- 	              			<tr> -->
<!-- 	              			  <td> -->
<!-- 	              			    <span> -->
<%-- 	              			    	<s:checkbox id="filterFlag" name="filterFlag" onclick="fliterCars();" value="true"/> --%>
<!-- 	              			    </span> -->
<!-- 	              			  </td> -->
<!-- 	              			  <td valign="top"> -->
<!-- 	              			    <span>&nbsp;未配置线路车辆过滤</span> -->
<!-- 	              			  </td> -->
<!-- 	              			</tr> -->
<!-- 	              		</table> -->
	              	</td>
	              	<td align="center">
	                </td>
	              </tr>           
	            </table>
	          </div>			
				<div id="lefttree" class="treeBox">
							     <ul id="treeDemo" class="ztree"></ul>
			    </div>	
				</div>	
				</td>
				<td valign="top" class="sleftline" id="middeldiv" style="display: none;">
		                   <div id="content_middelside">
			                <div>
		            	     <a href="#" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
		          	       </div>
		                  </div>
	            </td>
	            
	            <td valign="top">
	            <div id="content_rightside">
	            <div class="titleBar">
		        <div class="title">定时拍照设置</div>
			            <div class="workLink">
			              <div class="export_alarm m_r_10" id="piliangchuli">
			              <a href="#" class="export_alarm_c1" onfocus="this.blur()" onclick="javascript:openSetVins('1');">批量设置</a>
			              </div>
			            </div>   
		        </div>
		        <div id="right_main">
					<div class="car-info" style="display:none;">
					<table border="0" cellpadding="0" cellspacing="0" style="margin:0px 10px;">
						<tr>
	                    	<th align="right">时间：</th>
	                    	<td align="left" style="padding: 0 5px;">
								<input readonly="readonly" id="queryTime" name="queryTime" value="${queryObj.queryTime}" 
									class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
									  autoPickDate:true,
									  maxDate:'%y-%M-%d',
									  isShowClear:false,
									  onpicked:function(){queryWeekList();}})"/>                          
	                    	</td>
	                    	<td><a href="javascript:void(0);" onclick="javascript:queryWeekList();" class="btn-blue">查询</a></td>
	                	</tr>
					</table>
					</div>
<!-- 					<div class="week_list"> -->
<!-- 		           		 <div style=" float:left; margin-right:5px;"><img src="../newimages/week_arrow_left.png" width="16" height="50" onclick="previousWeek()"/></div> -->
<!-- 		              	<ul> -->
<!-- 		                	<li id="li1" vat="1" onclick="changeChoose('1')" style="cursor: hand;"><span id="week0" class="week_bold">周一</span><span id="day0"></span></li> -->
<!-- 		                    <li id="li2" vat="2" onclick="changeChoose('2')" style="cursor: hand;"><span id="week1" class="week_bold">周二</span><span id="day1"></span></li> -->
<!-- 		                    <li id="li3" vat="3" onclick="changeChoose('3')" style="cursor: hand;"><span id="week2" class="week_bold">周三</span><span id="day2"></span></li> -->
<!-- 		                    <li id="li4" vat="4" onclick="changeChoose('4')" style="cursor: hand;"><span id="week3" class="week_bold">周四</span><span id="day3"></span></li> -->
<!-- 		                    <li id="li5" vat="5" onclick="changeChoose('5')" style="cursor: hand;"><span id="week4" class="week_bold">周五</span><span id="day4"></span></li> -->
<!-- 		                    <li id="li6" vat="6" onclick="changeChoose('6')" style="cursor: hand;"><span id="week5" class="week_bold">周六</span><span id="day5"></span></li> -->
<!-- 		                    <li id="li7" vat="7" onclick="changeChoose('7')" style="cursor: hand;"><span id="week6" class="week_bold">周日</span><span id="day6"></span></li> -->
<!-- 		                </ul> -->
<!-- 		                <div style=" float:left; margin-left:5px;"><img src="../newimages/week_arrow_right.png" width="16" height="50" onclick="nextWeek()"/></div> -->
<!-- 		            </div> -->
					<div class="table_list">
						<table cellspacing="4" width="100%">
		   					<tr>
		    					<td><table id="galaList" width="100%" cellspacing="0"></table></td>
	              				</tr>
	              			</table>
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
<s:form id="export_form" action="" method="post">
	<s:hidden id="exportObj.VIN" name="exportObj.VIN"/>
	<s:hidden id="exportObj.queryTime" name="exportObj.queryTime"/>
	<s:hidden id="exportObj.vehicle_ln" name="exportObj.vehicle_ln"/>
</s:form>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/md5/base64.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="/WEB-INF/jsp/treemanage/zTree_withoutphotoSet.jsp"%>
<%@include file="vehicleBrows_js.jsp"%>
</body>
</html>