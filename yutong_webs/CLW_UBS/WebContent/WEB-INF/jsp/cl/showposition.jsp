<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<!-- 中文注释 -->
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<%@ include file="showpositionjs.jsp"%>
</head>
<body  style="width:700px; min-width:700px;" onload="mapInit()">
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<s:hidden id="alarm_start_longitude" name="vehicleRegisterInfo.longitude"/>
<s:hidden id="alarm_start_latitude" name="vehicleRegisterInfo.latitude"/>
<s:hidden id="vehicle_ln" name="vehicleRegisterInfo.vehicleLn"/>
<div id="map" style="width: 700px; height: 370px;"></div>
</body>

</html>

