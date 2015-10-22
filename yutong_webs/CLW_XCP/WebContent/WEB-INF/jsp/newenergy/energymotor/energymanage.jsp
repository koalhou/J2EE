<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/alarm.css" />" />
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
		        <div class="title">新能源信息管理</div>
		            <div class="workLink">
		              <div class="export">
		              <a href="#" class="export_icon" onfocus="this.blur()" onclick="javascript:exportData();"><s:text name="button.daochu" /></a>
		              </div>
		            </div>   
		        </div>
		        <div id="right_main">
					<div class="car-info">
					<table border="0" cellpadding="0" cellspacing="0" style="margin:0px 10px;">
						<tr>
							<th align="right">车牌号：</th>
							<td align="left" style="padding: 0 5px;">
								<input id="vehicleln" name="vehicleln" value="" onkeydown="keyEnter(event)"/>
							</td>	
							<th align="right" style="width:75px;">所属线路：</th>
							<td align="left" style="padding: 0 5px;">
								<input id="routeName" name="routeName" value=""/>
							</td>							
	                    	<th align="right" style="width:75px;">时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;段：</th>
	                    	<td align="left" style="padding: 0 5px;">
									<input class="Wdate" readonly="readonly" type="text"  size="24" id="startTime" name="startTime" 
	                         onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',autoPickDate:true,maxDate:'%y-%M-%d %H:%m',
															  isShowClear:false,onpicked:pickedstarttime})"
				  								value="<s:property value='startTime'/>"/>
									  至
								<input class="Wdate" readonly="readonly" type="text"  size="24" id="endTime" name="endTime" 
	                         onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',autoPickDate:true,maxDate:'%y-%M-%d %H:%m',
															  isShowClear:false,onpicked:pickedendtime})"
				 							value="<s:property value='endTime'/>"/>
	                    	</td>
	                    	<td><a href="javascript:void(0);" onclick="javascript:searchMotorList();" class="btn-blue">查询</a></td>
	                	</tr>
					</table>
					</div>
					<div class="alarm_tab">
                                <a id="deal1" href="#" onclick="changetab(this,1)" class="alarm_tabs">主电机信息</a>
                                <a id="deal2" href="#" onclick="changetab(this,2)">ISG电机信息</a>
                                <a id="deal3" href="#" onclick="changetab(this,3)">电池电容绝缘</a>
                                <a id="deal4" href="#" onclick="changetab(this,4)">电容管理系统</a>
                                <a id="deal5" href="#" onclick="changetab(this,5)">电池管理系统</a>
                                <a id="deal6" href="#" onclick="changetab(this,6)">其它信息</a>
                    </div>
					<div class="table_list">
						<table cellspacing="4" width="100%">
		   					<tr>
		    					<td>
		    						<div id="grid1">
			    						<table id="galaList1" width="100%" cellspacing="0" ></table>
		    						</div>
		    						<div id="grid2">
			    						<table id="galaList2" width="100%" cellspacing="0" style="display:none;"></table>
		    						</div>
		    						<div id="grid3">
			    						<table id="galaList3" width="100%" cellspacing="0" style="display:none;"></table>
		    						</div>
		    						<div id="grid4">
			    						<table id="galaList4" width="100%" cellspacing="0" style="display:none;"></table>
		    						</div>
		    						<div id="grid5">
			    						<table id="galaList5" width="100%" cellspacing="0" style="display:none;"></table>
		    						</div>
		    						<div id="grid6">
			    						<table id="galaList6" width="100%" cellspacing="0" style="display:none;"></table>
		    						</div>
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
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<s:form id="export_form" action="" method="post">
	
	<s:hidden id="vehicleln" name="vehicleln"/>
	<s:hidden id="startTime" name="startTime"/>
	<s:hidden id="endTime" name="endTime"/>
	<s:hidden id="alarmMsg" name="alarmMsg"/>
</s:form>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/md5/base64.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/selectObj.js' />"></script>
<%@include file="/WEB-INF/jsp/treemanage/zTree_newenergy_js.jsp"%>
<%@include file="energymotor_js.jsp"%>
</body>
</html>