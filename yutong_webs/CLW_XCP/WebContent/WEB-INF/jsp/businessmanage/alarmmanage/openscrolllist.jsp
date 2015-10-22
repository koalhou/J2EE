<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>

 <!-- 下面是列表不细作,不使用 -->
<html>
	<head>
	<style>
		.flexigrid div.pDiv{
			display:none;
		}
	</style>
	</head>
	<body style="overflow: hidden;">
		<div style="">
			<table id="gala" width="100%">
			</table>
		</div>
		<%@include file="openscrolllist_js.jsp"%>
	</body>
</html>