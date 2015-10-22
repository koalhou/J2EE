<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	    <%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
		<title>
			<s:text name="common.title" />
		</title>
		<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
		<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
		<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseonly_css.jsp"%>
		<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/alarm.css" />" />
    </head>
	<body>
	    <div id="wrapper">
	    <%@include file="/WEB-INF/jsp/common/header.jsp"%>
	    <div id="content">
	    <s:hidden id="changeflag" name="changeflag" value="1"/>
		<s:form id="drivershuaka_form" action="" method="post" onsubmit="return false;">
			<s:hidden id="choosedriverid" name="choosedriverid" />
			<table  width="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td id='leftdiv' valign="top" class="leftline">
					<!-- 组织树 组织  -->
					<div id="content_leftside">
						<div id="leftInfoDiv" class="treeTab">
							<a href="#" class="tabfocus" onfocus="this.blur()">组织</a> <a
								href="#" class="hideLeft" onfocus="this.blur()"
								onclick="HideandShowControl()"></a>
						</div>
						<!-- 组织树 查询  -->
						<div id="searchPlanDiv" class="searchPlan">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="130" align="right"><input id="driverName"
										type="text" class="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchTree();}"/></td>
									<td align="center"><a href="#" class="btnbule"
										onfocus="this.blur()" onClick="searchTree()">查询</a></td>
								</tr>
							</table>
						</div>
						<!-- 组织车辆树  -->
						<div id="lefttree" class="treeBox">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
					</div>
				</td>

					<td  valign="top" class="sleftline" id="middeldiv" style="display: none;">
	                   <div id="content_middelside">
		                <div>
	            	     <a href="#" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
	          	       </div>
	                  </div>
                   </td>
					
					<td valign="top"  class="position_r" id="rightdiv">
					 <div id="content_rightside">
					     
                     </div>
					</td>
				</tr>
			</table>
			</s:form>				
</div>
   <%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterprise_driver_js.jsp"%>
<%@include file="/WEB-INF/jsp/businessmanage/alarmmanage/alarmtime_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js' />"></script>
<%@include file="/WEB-INF/jsp/yunxing/drivershuaka/drivershuaka_list_js.jsp"%>
</body>
</html>