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
<%-- 	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseroute_css.jsp"%> --%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style>
		.contentWin {
			width: 500px;
		}
		.inputRed {
			border: 1px solid red;
		}
		.alarmLayer {
			position:absolute;
			left:130px;
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

<body style="width: 500px;">
<div id="Layer1"  class="alarmLayer" style="display: none"><img align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
<span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
</div>
<s:hidden id="vins" name="vins" />
<s:hidden id="flag" name="flag" />
<div id="wrapper" style="width: 590px;">
	<div id="contentWin">
		<div style="margin-top: 15px;font-weight: bold;"><span style="float:left;"></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<table style="float:left;margin-left: 20px;width: 180px;">
				<tr style="height: 10px;">
					<td style="width: 80px;">短信提醒</td>
					<td style="width: 15px;"><input type="checkbox" id="allSels" name="allSels" value=""/></td>
					<td>全选</td>
				</tr>
			</table>
		</div>
		<div style="height: 120px;width: 590px;overflow-y: auto; ">
			<table id="alarmTypeTable" style="width: 95%;margin-top: 8px;margin-left: 20px;" border="0" cellpadding="8" cellspacing="3">
<!-- 				<tr> -->
<!-- 					<td align="center"><input type="checkbox" /></td> -->
<!-- 					<td>充电完毕</td> -->
<!-- 					<td><span style="font-weight: bold;">提示信息：</span><span>请注意，车辆已充满电！</span></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td align="center"><input type="checkbox" /></td> -->
<!-- 					<td>充电完毕</td> -->
<!-- 					<td><span style="font-weight: bold;">提示信息：</span><span>请注意，车辆已充满电！</span></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td align="center"><input type="checkbox" /></td> -->
<!-- 					<td>充电完毕</td> -->
<!-- 					<td><span style="font-weight: bold;">提示信息：</span><span>请注意，车辆已充满电！</span></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td align="center"><input type="checkbox" /></td> -->
<!-- 					<td>充电完毕</td> -->
<!-- 					<td><span style="font-weight: bold;">提示信息：</span><span>请注意，车辆已充满电！</span></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td align="center"><input type="checkbox" /></td> -->
<!-- 					<td>充电完毕</td> -->
<!-- 					<td><span style="font-weight: bold;">提示信息：</span><span>请注意，车辆已充满电！</span></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td align="center"><input type="checkbox" /></td> -->
<!-- 					<td>充电完毕</td> -->
<!-- 					<td><span style="font-weight: bold;">提示信息：</span><span>请注意，车辆已充满电！</span></td> -->
<!-- 				</tr> -->
			</table>
			
			<div></div>
		</div>
		<div class="titleBar" style="width:590px;">
        	<div class="title">联系人信息</div>
        	
            <div class="workLink">
<!--               <div class="export"> -->
              	<a id="addUserBtn" href="javascript:void(0);" class="btnAdd" onfocus="this.blur()" onclick="javascript:openWinSign();"></a>
<!--               </div> -->
            </div>   
        </div>
        <div id="right_main" style="height: 100px;overflow-y: auto;">
        	<table id="userMsg" style="width: 95%;margin-top: 8px;margin-left: 20px;" border="0" cellpadding="10" cellspacing="3">
<!--         		<tr> -->
<!-- 					<td align="right">姓名：</td> -->
<!-- 					<td><input type="text" name="userName"/><span style="color: red;">*</span></td> -->
<!-- 					<td align="right">手机号：</td> -->
<!-- 					<td><input type="text" name="telephone"/><span style="color: red;">*</span></td> -->
<!-- 					<td><a href="javascript:void(0)" onclick="removeRow(this)">删除</a></td> -->
<!-- 				</tr> -->
<!--         		<tr> -->
<!-- 					<td align="right">姓名：</td> -->
<!-- 					<td><input type="text" name="userName"/><span style="color: red;">*</span></td> -->
<!-- 					<td align="right">手机号：</td> -->
<!-- 					<td><input type="text" name="telephone"/><span style="color: red;">*</span></td> -->
<!-- 				</tr> -->
<!--         		<tr> -->
<!-- 					<td align="right">姓名：</td> -->
<!-- 					<td><input type="text" name="userName"/><span style="color: red;">*</span></td> -->
<!-- 					<td align="right">手机号：</td> -->
<!-- 					<td><input type="text" name="telephone"/><span style="color: red;">*</span></td> -->
<!-- 				</tr> -->
<!--         		<tr> -->
<!-- 					<td align="right">姓名：</td> -->
<!-- 					<td><input type="text" name="userName"/><span style="color: red;">*</span></td> -->
<!-- 					<td align="right">手机号：</td> -->
<!-- 					<td><input type="text" name="telephone"/><span style="color: red;">*</span></td> -->
<!-- 				</tr> -->
<!--         		<tr> -->
<!-- 					<td align="right">姓名：</td> -->
<!-- 					<td><input type="text" name="userName"/><span style="color: red;">*</span></td> -->
<!-- 					<td align="right">手机号：</td> -->
<!-- 					<td><input type="text" name="telephone"/><span style="color: red;">*</span></td> -->
<!-- 				</tr> -->
<!--         		<tr> -->
<!-- 					<td align="right">姓名：</td> -->
<!-- 					<td><input type="text" name="userName"/><span style="color: red;">*</span></td> -->
<!-- 					<td align="right">手机号：</td> -->
<!-- 					<td><input type="text" name="telephone"/><span style="color: red;">*</span></td> -->
<!-- 				</tr> -->
<!--         		<tr> -->
<!-- 					<td align="right">姓名：</td> -->
<!-- 					<td><input type="text" name="userName"/><span style="color: red;">*</span></td> -->
<!-- 					<td align="right">手机号：</td> -->
<!-- 					<td><input type="text" name="telephone"/><span style="color: red;">*</span></td> -->
<!-- 				</tr> -->
<!--         		<tr> -->
<!-- 					<td align="right">姓名：</td> -->
<!-- 					<td><input type="text" name="userName"/><span style="color: red;">*</span></td> -->
<!-- 					<td align="right">手机号：</td> -->
<!-- 					<td><input type="text" name="telephone"/><span style="color: red;">*</span></td> -->
<!-- 				</tr> -->
<!--         		<tr> -->
<!-- 					<td align="right">姓名：</td> -->
<!-- 					<td><input type="text" name="userName"/><span style="color: red;">*</span></td> -->
<!-- 					<td align="right">手机号：</td> -->
<!-- 					<td><input type="text" name="telephone"/><span style="color: red;">*</span></td> -->
<!-- 				</tr> -->
<!--         		<tr> -->
<!-- 					<td align="right">姓名：</td> -->
<!-- 					<td><input type="text" name="userName"/><span style="color: red;">*</span></td> -->
<!-- 					<td align="right">手机号：</td> -->
<!-- 					<td><input type="text" name="telephone"/><span style="color: red;">*</span></td> -->
<!-- 				</tr> -->
        	</table>
        </div>
        <table id="dataTable" style="width: 550px;margin-top: 10px;margin-right: 30px;" border="0" 
        		align="center" cellpadding="0" cellspacing="0">
			<tr valign="middle"  height="35px">
				<td  colspan="4">
					<a href="javascript:void(0)" class="buttonMap" id="cancelButton" onclick="cancelArt()" >取消</a>
                    <a href="javascript:void(0)" class="buttonMap" id="ysButton" onclick="addSmsSet();">确定</a>
				</td>
			</tr>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="/WEB-INF/jsp/newenergy/energysms/openSmsSetWin_js.jsp"%>
</body>
<style>
	body {
		min-width: 500px;
	}
</style>
</html>