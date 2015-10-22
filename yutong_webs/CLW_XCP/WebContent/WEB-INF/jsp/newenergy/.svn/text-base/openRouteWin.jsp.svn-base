<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title>选择线路</title>
<%-- 	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/gpspostion.css" />" /> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/badDrive.css" />" /> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<s:url value='/stylesheets/routemonitor.css' />"/> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" /> --%>
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseroute_css.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body>
<div id="wrapper">
	<div id="content">
		<div id="content_leftside">
	          <div class="treeTab">
	            <a href="#" class="tabfocus">线路</a>
	             <a href="#" class="hideLeft" onfocus="this.blur()" onclick="HideandShowControl()"></a>
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
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriserouteWin.jsp"%>
</body>

</html>