<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style>
		.routeSel {
/* 			width: 149px; */
/* 			height: 120px; 
			background: #ECE9D8;*/
			background: #ffffff;
			border: 1px solid #7F9DB9;
			overflow: auto;
			position: absolute;
			z-index: 10000;
		}
		.option {
			width: 100%;
			height: 15px;
			padding-left: 3px;
			padding-top: 3px;
			line-height: 15px;
		}
		.optionOver {
			background: #316AC5;
		}
		.optionOut {
			background: #ffffff;
		}
		.selNone {
			display:none;
		}
	</style>
</head>

<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	
	<div id="content">		
	<s:form id="statusdetail_form" action="editbefore" method="post">
		
		<input type="hidden" id="ChooseEnterID_tree" name="ChooseEnterID_tree"
			value="<%=session.getValue("ChooseEnterID_tree")%>" />
		
	</s:form>
	<s:form id="vehiclestatus_form" action="vehiclestatus" method="post">
<%-- 		<s:hidden id="chooseorgid" name="chooseorgid" /> --%>
		<s:hidden id="organization_id" name="organization_id"/>
		<s:hidden id="vehicleVin" name="vehicleVin"/>
		<s:hidden id="alarmKey" name="alarmKey"/>
		<s:hidden id="isSearch" name="isSearch"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td id='leftdiv' valign="top" class="leftline">
				<div id="content_leftside">
				<div id="leftInfoDiv" class="treeTab">
								<a href="#" class="tabfocus">组织</a>
								<a href="#" onclick="HideandShowControl()" class="hideLeft"></a>
				</div>
	          <div class="newsearchPlan" style="height: 60px;">
	            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	              <tr>
	              	<td width="10px"></td>
	                <td width="130px" align="left"><input id="vehicleLn" type="text" class="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchTree();}"/></td>
	                <td align="center"><a href="javascript:searchTree()" class="btnbule">查询</a></td>
	              </tr> 
	              <tr>
	              	<td width="10px"></td>
	              	<td width="130px" align="left">
	              		<table border="0" align="left" cellpadding="0" cellspacing="0">
	              			<tr>
	              			  <td>
	              			    <span>
<%-- 	              			    	<s:checkbox id="filterFlag" name="filterFlag" onclick="fliterCars();" value="false"/> --%>
	              			    	<input type="checkbox" id="filterFlag" name="filterFlag" onclick="fliterCars();" value="false" checked="checked"/>
	              			    </span>
	              			  </td>
	              			  <td valign="top">
	              			    <span>&nbsp;未配置线路车辆过滤</span>
	              			  </td>
	              			</tr>
	              		</table>
	              	</td>
	              	<td align="center">
	                </td>
	              </tr>           
	            </table>
	          </div>			
				<div id="lefttree" class="treeBox" style="float:left;">
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
		        <div class="title">报警明细</div>
<%-- 			        <s:if test="#session.perUrlList.contains('111_3_2_5_2')"> --%>
			            <div class="workLink">
			              <div class="export">
			              <a href="#" class="export_icon" onfocus="this.blur()" onclick="javascript:exportData();"><s:text name="button.daochu" /></a>
			              </div>
			            </div>   
<%-- 			        </s:if>       --%>
		        </div>
		        <div id="right_main">
					<div class="car-info">
					<table border="0" cellpadding="0" cellspacing="0" style="margin:0px 10px;">
						<tr>
							<th align="right">车牌号：</th>
							<td align="left" style="padding: 0 5px;">
								<input id="vehicleln" name="vehicleln" value="" onkeydown="keyEnter(event)"/>
							</td>
							<th align="right" style="width:75px;">报警信息：</th>
							<td align="left" style="padding: 0 5px;">
								<select id="alarmMsg" name="alarmMsg" onchange="searchAlarmList()">
									<option value="">---请选择---</option>
									<option value="19">19</option>
									<option value="33">33</option>
									<option value="36">36</option>
									<option value="38">38</option>
									<option value="50">50</option>
									<option value="130">130</option>
									<option value="131">131</option>
									<option value="133">133</option>
									<option value="134">134</option>
									<option value="243">243</option>
									<option value="244">244</option>
									<option value="245">245</option>
								</select>
							</td>	
							<th align="right" style="width:75px;">所属线路：</th>
							<td align="left" style="padding: 0 5px;">
								<input id="routeName" name="routeName" value=""/>
<!-- 								<div id="routeSel" name="routeSel" class="routeSel" > -->
<!-- 									<div class="option">北京</div> -->
<!-- 									<div class="option">上海</div> -->
<!-- 									<div class="option">赌咒</div> -->
<!-- 									<div class="option">重庆</div> -->
<!-- 									<div class="option">天津</div> -->
<!-- 									<div class="option">河北</div> -->
<!-- 								  </div> -->
							</td>							
	                    	<th align="right" style="width:75px;">时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;段：</th>
	                    	<td align="left" style="padding: 0 5px;">
<%-- 								<input readonly="readonly" id="startTime" name="startTime" value="${queryObj.queryTime}"  --%>
<!-- 									class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm', -->
<!-- 									  autoPickDate:true, -->
<!-- 									  maxDate:'%y-%M-%d', -->
<!-- 									  isShowClear:false})"/> -->
									<input class="Wdate" readonly="readonly" type="text"  size="24" id="startTime" name="startTime" 
	                         onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',autoPickDate:true,maxDate:'%y-%M-%d %H:%m',
															  isShowClear:false,onpicked:pickedstarttime})"
				  								value="<s:property value='startTime'/>"/>
									  至
<%-- 								<input readonly="readonly" id="endTime" name="endTime" value="${queryObj.queryTime}"  --%>
<!-- 									class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm', -->
<!-- 									  autoPickDate:true, -->
<!-- 									  maxDate:'%y-%M-%d', -->
<!-- 									  minDate:'#F{$dp.$D(\'startTime\')}', -->
<!-- 									  isShowClear:false})"/>                               -->
								<input class="Wdate" readonly="readonly" type="text"  size="24" id="endTime" name="endTime" 
	                         onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',autoPickDate:true,maxDate:'%y-%M-%d %H:%m',
															  isShowClear:false,onpicked:pickedendtime})"
				 							value="<s:property value='endTime'/>"/>
	                    	</td>
	                    	<td><a href="javascript:void(0);" onclick="javascript:searchAlarmList();" class="btn-blue">查询</a></td>
	                	</tr>
					</table>
					</div>
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
	
	<s:hidden id="vehicleln" name="vehicleln"/>
	<s:hidden id="startTime" name="startTime"/>
	<s:hidden id="endTime" name="endTime"/>
	<s:hidden id="alarmMsg" name="alarmMsg"/>
</s:form>
<div id="stationInfoDiv" style="width: 100%;margin-top: 20px;display:none;">
<!-- 			<div id="range" style="height: 190px;"> -->
			<div id="Layer1"  class="alarmLayer" style="display: none;"><img align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" />
			<input type="hidden" id="alarmId" name="alarmId" value=""/> 
		    <span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
		  	</div>
		  	<div style="color: red;">处理意见最多100个字！</div>
			<table id="dataTable" style="width: 100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
	 				<td>
	 					<textarea id="recordName" name="record" rows="11" cols="61" style="background: #FFFFFF;width: 450px;height: 170px;"></textarea>
					</td>
				</tr>
			</table>
<!-- 			</div> -->
		<table id="dataTable" style="width: 100%;" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr valign="middle"  height="35px">
				<td  colspan="4">
					<a href="javascript:void(0)" class="buttonMap" id="cancelButton" onclick="cancelArt()" >取消</a>
                    <a href="javascript:void(0)" class="buttonMap" id="ysButton" onclick="addRecordSet()">确定</a>
				</td>
			</tr>
		</table>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/md5/base64.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/selectObj.js' />"></script>
<%@include file="/WEB-INF/jsp/treemanage/zTree_newenergy_js.jsp"%>
<%@include file="energyalarm_js.jsp"%>
</body>
</html>