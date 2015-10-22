<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8" deferredSyntaxAllowedAsLiteral="true"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/gpspostion.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/badDrive.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value='/stylesheets/routemonitor.css' />"/>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/alarm.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/mooko-ui/themes/default/mk.routemonitor.css" />" />
	
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseroute_css.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body>
<input type="hidden" id="newDayer" value=""/>
<input type="hidden" id="end_time_hidden" value="<s:property value='vehicleInfo.endDate'/>"/>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content" class="routemonitorDivArea">


	    <table width="100%" border="0"  cellpadding="0" cellspacing="0">
	      <tr>
	        <td id='leftdiv'  valign="top" class="leftline">
	         <div id="content_leftside">
				<div id="leftInfoDiv" class="treeTab">
					<a href="javascript:void(0)" class="tabfocus">组织</a>
					<a href="javascript:void(0)" onclick="javascript:HideandShowControl()" class="hideLeft"></a>
				</div>
	          	<div class="newsearchPlan" id="newsearchPlan">
		            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		                <td width="130" align="right"><input id="vehicleLn" type="text" class="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchTree('1');}"/></td>
		                <td align="center"><a onclick="javascript:searchTree('1')" class="btnbule">查询</a></td>
		              </tr>    
		              <tr align="left" >
		              	<td align="left" colspan="2">
			              	<table width="80%" border="0" align="left" cellpadding="0" cellspacing="0">
			              		<tr>
				              		<td width="10" align="right" colspan="2">
				              			<input type="checkbox" name="isTerminal" id="isTerminal" onclick="javascript:searchTree('1','1');"/>
				              		</td>
	 		            			<td align="left" >
	 		            				<span style="height: 25px;">&nbsp;仅显示有设备车辆</span>
	 		            			</td>
			              		</tr>
							</table>
		              	</td>
		              </tr>          
		            </table>
	          	</div>
				<div id="lefttree" class="treeBox" style="float: left;width: 260px;">
					<ul id="treeDemo" class="ztree"></ul>
			    </div>	
			</div>
	        </td>
			<td class="sleftline" valign="top" id="middeldiv" style="display: none;width: 23px;">
		       <div id="content_middelside">
		       <div>
		  	     <a href="javascript:void(0);" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
			   </div>
		       </div>
		     </td>
	        <td valign="top" border="0">
	        <div id="content_rightside">
	          <div class="titleBar" id="titleBarMap">
	            <div class="title">油量统计</div>
	          </div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td id="centerContent">
					<div class="alarm_tab" style="margin-top: 5px;">
                                  <a  class="alarm_tabs" flag="1"  href="javascript:void(0)" id="deal0">变动记录</a>
                                  <a  flag="2"  href="javascript:void(0)" id="deal2">油耗统计</a>
                      </div>
                     <table  id="changeTable" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="reportOnline2">
							<table width="100%" border="0" cellspacing="8" cellpadding="0">
								<tr>
									<td>
									<form  id="ftlyInfoDetails_form" method="post" target="sfsadfas" action="exportAddExcel.shtml">
									<input type="hidden" id="vehicle_vin" name="vehicle_vin"/>
									<table border="0" cellspacing="4" cellpadding="0">
										<tr>
										<td>油量变动形式：</td>
											<td colspan="1"><select name="oilbox_state" id="oilboxState" onchange="javaScript:ftlyOilRecordsObj.searchOilRecordsDetailList();"> 
											<option value="010,001">全部</option> 
											<option value="010">加油</option> 
											<option value="001">骤减</option> 
											</select> </td>
										<td>时   段：</td>
											<td colspan="6">
											 	<input  id="getVehicleStartDate" name="start_time"
											 			value="<s:property value="vehicleInfo.startDate"/>"  	
											 			class="Wdate" 
												       	type="text" 
												       	size="23"
												       	onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'getVehicleEndDate\')}',dateFmt:'yyyy-MM-dd'})"/>
						                        	至
<!-- 						                        	({minDate: '%y-%M-#{%d+1}' } -->
											    <input id="getVehicleEndDate" name="end_time"
											    	   value="${vehicleInfo.endDate }"
											    	   size="23" 
											    	   class="Wdate" type="text"
												       onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end_time_hidden\')}',minDate:'#F{$dp.$D(\'getVehicleStartDate\')}'})" />
											</td>
											<td align="center" width="150" style="color:#797979;"></td>
										    <td>
										    <s:if test="#session.perUrlList.contains('111_3_2_2_2_1')">
										    	&nbsp;&nbsp;&nbsp;
										    	<input class="btn-blue"  style="float: none;" type="button" id="searchOil"  value="查  询"
										    	onclick="javaScript:ftlyOilRecordsObj.searchOilRecordsDetailList();"/>
										    </s:if>
										    <s:if test="#session.perUrlList.contains('111_3_2_2_2_2')">	
										    	&nbsp;&nbsp;&nbsp;
										    	<input class="btn-blue"  style="float: none;" type="button"  value="导  出" 
										    	onclick="javaScript:ftlyOilRecordsObj.exportAddExcel()"/>
										    </s:if>	
										    </td>
										</tr>
									</table>
									</form>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="">
												<div>
												  <table id="addOil" width="100%"  cellspacing="0" style="display: none">
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
					<table id="wearTable" style="display: none" width="100%" border="0" cellspacing="0" cellpadding="0">
					
					<tr>
						<td class="reportOnline2">
						
							<table width="100%" border="0" cellspacing="8" cellpadding="0">
								<tr>
									<td>
									<form id="oilWear_form" method="post" target="sfsadfas" action="exportOilWearExcel.shtml">
									<input type="hidden" id="vehicle_vin_wear" name="vehicle_vin"/>
									<table border="0" cellspacing="4" cellpadding="0">
										<tr>
										<td>时   段：</td>
											<td colspan="6">
											 	<input  id="getVehicleStartTime" name="start_time"
											 			value="<s:property value="vehicleInfo.startDate"/>"  	
											 			class="Wdate" 
												       	type="text" 
												       	size="23"
												       	onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'getVehicleEndTime\')}'})"/>
						                        	至
											    <input id="getVehicleEndTime" name="end_time"
											    	   value="${vehicleInfo.endDate }"
											    	   size="23" 
											    	   class="Wdate" type="text"
												       onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end_time_hidden\')}',minDate:'#F{$dp.$D(\'getVehicleStartTime\')}'})" />
											</td>
											<td align="center" width="150" style="color:#797979;">( 不能查询当日数据 )</td>
										    <td>
										     <s:if test="#session.perUrlList.contains('111_3_2_2_3_1')">
										    	&nbsp;&nbsp;&nbsp;
										    	<input class="btn-blue" style="float: none;" type="button" id="searchOilAna"  value="查  询"
										    	onclick="javaScript:ftlyOilRecordsObj.searchOilWearDetailList();"/>
										    	</s:if>
										    	 <s:if test="#session.perUrlList.contains('111_3_2_2_3_2')">
										    	&nbsp;&nbsp;&nbsp;
										    	<input class="btn-blue" style="float: none;" type="button"  value="导  出" 
										    	onclick="javaScript:ftlyOilRecordsObj.exportOilWearExcel()"/>
										    	</s:if>
										    	
										    </td>
										</tr>
									</table>
									</form>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="">
												<div>
												  <table id="wearGrid" width="100%"  cellspacing="0" style="display: none">
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

		               
	          </div>
	        </td>
	      </tr>
	    </table>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>



<!--JS区域--> 
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='../scripts/flexigrid/flexigrid.js' />"></script>
<!-- 
 -->
<%@include file="zTree_withoutonline.jsp"%>
<script type="text/javascript" src="../scripts/oilrecords/oilWearList.js"></script>

</body>

</html>