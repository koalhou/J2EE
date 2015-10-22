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
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
	            <td valign="top">
	            <div id="content_rightside">
	            <div class="titleBar">
		        <div class="title">运行时段设置</div>
<%-- 			        <s:if test="#session.perUrlList.contains('111_3_2_5_2')"> --%>
			            <div class="workLink">
<!-- 			              <div class="export"> -->
<%-- 			              <a href="#" class="export_icon" onfocus="this.blur()" onclick="javascript:exportData();"><s:text name="button.daochu" /></a> --%>
<!-- 			              </div> -->
							<a href="javascript:void(0);" class="btnAdd" onfocus="this.blur()" onclick="javascript:openWinSign('','');"></a>
			            </div>   
<%-- 			        </s:if>       --%>
		        </div>
		        <div id="right_main">
<!-- 					<div class="car-info"> -->
<!-- 					<table border="0" cellpadding="0" cellspacing="0" style="margin:0px 10px;"> -->
<!-- 						<tr> -->
<!-- 							<th align="right">车牌号：</th> -->
<!-- 							<td align="left" style="padding: 0 5px;"> -->
<!-- 								<input id="vehicleln" name="vehicleln" value="" onkeydown="keyEnter(event)"/> -->
<!-- 							</td> -->
<!-- 							<th align="right" style="width:75px;">所属组织：</th> -->
<!-- 							<td align="left" style="padding: 0 5px;"> -->
<!-- 								<input type="hidden" id="organization_id" name="organization_id" /> -->
<!-- 								<input type="text" id="organizname" name="organizname" onclick="openorganizidtree();" readonly="true"/>  -->
<!-- 							</td>		 -->
<!-- 							<th align="right" style="width:75px;">所属线路：</th> -->
<!-- 							<td align="left" style="padding: 0 5px;"> -->
<!-- 								<input id="routeName" name="routeName" value=""/> -->
<!-- 							</td>							 -->
<!-- 	                    	<td><a href="javascript:void(0);" onclick="javascript:searchsmsList();" class="btn-blue">查询</a></td> -->
<!-- 	                	</tr> -->
<!-- 					</table> -->
<!-- 					</div> -->
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
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/md5/base64.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%-- <script type="text/javascript" src="<s:url value='/scripts/selectObj.js' />"></script> --%>
<%-- <%@include file="/WEB-INF/jsp/treemanage/zTree_newenergy_js.jsp"%> --%>
<%@include file="runtimeset_js.jsp"%>
</body>
</html>