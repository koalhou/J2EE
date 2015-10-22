<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
<%-- 	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" /> --%>
<%-- 	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%> --%>
<%-- <link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>"> --%>
<%-- <link rel="stylesheet" type="text/css" href="<s:url value="/mooko-ui/themes/icon.css" />"> --%>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
	
	<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
	<style>
		.alarmLayer {
			position:absolute;
			left:50px;
			top: 30px;
			width:260px;
			line-height:15px;
			z-index:1;
			background:#FFFFE1;
			border:1px solid #ccc;
			color:#147800;
			padding-top:3px;
			padding-left:10px;
			padding-bottom:3px;
		}
	</style>
</head>

<body>
	<input type="hidden" name="keyId" id="keyId" value="<s:property value='keyId'/>"/>
<%-- 	<s:hidden name="keyId" id="keyId" value=""></s:hidden> --%>
	<div id="Layer1"  class="alarmLayer" style="display: none"><img align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
    <span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
  	</div>
	<div style="padding-left: 25px;"><span style="font-weight: bold;">运行时间段信息</span>(<span id="addRange" style="color: red;">*</span>为必填项，设置完成5分钟后生效)</div>
	<div id="stationInfoDiv" style="width:350px;height:150px;margin-top: 10px;padding-left: 15px;">
			<div id="point" style="height: 80px;border: 1px solid gray;">
				<table id="photoSetPointTable" style="margin-top: 15px;" width="330px" border="0" align="center" cellpadding="0" cellspacing="0">
					
					<tr>
						<td style="width: 120px;" align="right">上学时间段：</td>
		 				<td style="">
		 					<input type="text" id="goStartTime" name="goStartTime" value="" style="width: 70px;" readonly="readonly" onfocus="WdatePicker({dateFmt:'HH:mm'})"/><span style="color:red;">*</span>
<!-- 		 					<select id="goStartTime" name="goStartTime" > -->
<!-- 		 						<option value=''>---请选择---</option> -->
<!-- 		 					</select> -->
		 					--
		 					<input type="text" id="goEndTime" name="goEndTime" value="" style="width: 70px;" readonly="readonly" onfocus="WdatePicker({dateFmt:'HH:mm'})"/><span style="color:red;">*</span>
<!-- 		 					<select id="goEndTime" name="goEndTime" > -->
<!-- 		 						<option value=''>---请选择---</option> -->
<!-- 		 					</select> -->
						</td>
					</tr>
					<tr>
						<td style="width: 120px;" align="right">放学时间段：</td>
						<td style="">
							<input type="text" id="backStartTime" name="backStartTime" value="" style="width: 70px;" readonly="readonly" onfocus="WdatePicker({dateFmt:'HH:mm'})"/><span style="color:red;">*</span>
<!-- 							<select id="backStartTime" name="backStartTime" > -->
<!-- 								<option value=''>---请选择---</option> -->
<!-- 		 					</select> -->
		 					--
		 					<input type="text" id="backEndTime" name="backEndTime" value="" style="width: 70px;" readonly="readonly" onfocus="WdatePicker({dateFmt:'HH:mm'})"/><span style="color:red;">*</span>
<!-- 		 					<select id="backEndTime" name="backEndTime" > -->
<!-- 		 						<option value=''>---请选择---</option> -->
<!-- 		 					</select> -->
						</td>
					</tr>
				</table>
			</div>
			<table id="dataTable" style="width: 330px;" width="330px" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr valign="middle"  height="35px">
					<td  colspan="4">
						<a href="javascript:void(0)" class="buttonMap" id="cancelButton" onclick="cancelArt()" >取消</a>
	                    <a href="javascript:void(0)" class="buttonMap" id="ysButton" onclick="addSet();">确定</a>
	                    <a href="javascript:void(0)" class="buttonMap" id="delButton" onclick="deleteSet();">删除</a>
					</td>
				</tr>
			</table>
			</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/md5/base64.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/js_Map.js' />"></script>
<%@include file="winTimeSet_js.jsp"%>
</body>
</html>