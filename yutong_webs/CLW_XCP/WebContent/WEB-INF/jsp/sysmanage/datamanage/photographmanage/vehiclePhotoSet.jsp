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
		.weeksli {
			margin-left: 50px;
		}
		.weeksli > li {
			display:inline;
			font-size: 14px;
			margin-left: 10px;
		}
		.redBorder {
			border: 1px solid red;
		}
		.maxRedBorder {
			border: 1px solid red;
		}
		.crossRedBorder {
			border: 1px solid red;
		}
		.intervalRedBorder {
			border: 1px solid red;
		}
		.intervalFormatRedBorder {
			border: 1px solid red;
		}
		.alarmLayer {
			position:absolute;
			left:70px;
			top: 20px;
			width:450px;
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

<body style="overflow: hidden;">
	<input type="hidden" name="vehicleVin" id="vehicleVin" value="<s:property value='vehicleVin'/>"/>
	<input type="hidden" name="isOperator" id="isOperator" value="<s:property value='isOperator'/>"/>
	<div id="Layer1"  class="alarmLayer" style="display: none"><img align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
    <span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
  	</div>
	<div style="color: red;">提示：时间格式--08：02；拍照间隔>=5分钟，为整正数，单位：分钟；</div>
	<div id="stationInfoDiv" style="width:549px;height:450px;margin-top: 10px;">
			<div><span>时间段拍照：</span><span id="addRange"><a href="javascript:void(0)" onclick="addRangeTable('1','')">新增</a></span></div>
			<div id="range" style="height: 190px;border: 1px solid gray;overflow-y: scroll;">
				<table id="photoSetTable"  width="525px" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr style="height: 25px;">
						<td style="color: black;" colspan="3">
							<ul id="weeksli">
								<li><input type="checkbox" atr='1' name="week1" onclick="checkBoxChange('photoSetTable','0')"/>周一</li>
								<li><input type="checkbox" atr='2' name="week2" onclick="checkBoxChange('photoSetTable','0')"/>周二</li>
								<li><input type="checkbox" atr='3' name="week3" onclick="checkBoxChange('photoSetTable','0')"/>周三</li>
								<li><input type="checkbox" atr='4' name="week4" onclick="checkBoxChange('photoSetTable','0')"/>周四</li>
								<li><input type="checkbox" atr='5' name="week5" onclick="checkBoxChange('photoSetTable','0')"/>周五</li>
								<li><input type="checkbox" atr='6' name="week6" onclick="checkBoxChange('photoSetTable','0')"/>周六</li>
								<li><input type="checkbox" atr='7' name="week7" onclick="checkBoxChange('photoSetTable','0')"/>周日</li>
							</ul>
						</td>
					</tr>
					<tr>
		 				<td style="padding-left: 60px;width: 290px;">
		 					拍照时间范围：<input type="text" name="startTime" value="" style="width: 70px;" readonly="readonly" onfocus="WdatePicker({dateFmt:'HH:mm'})" onchange="inputChange('range','photoSetTable',this,'0')"/>
		 					------
		 					<input type="text" name="endTime" value="" style="width: 70px;" readonly="readonly" onfocus="WdatePicker({dateFmt:'HH:mm'})" onchange="inputChange('range','photoSetTable',this,'1')"/>
						</td>
						<td>
							拍照间隔：<input type="text" name="interval" value="" style="width: 30px;" onchange="inputInterval('photoSetTable',this)"/>
						</td>
						<td style="width: 40px;">
<!-- 							<a href="javascript:void(0);" onclick="deleteSetTable('photoSetTable')" style="margin-left: 10px;">删除</a> -->
								&nbsp;
						</td>
					</tr>
					<tr  name="hrs"  valign="middle"><td valign="middle" colspan="4">&nbsp;<hr style="height: 2px solid gray;"/></td></tr>
				</table>
			</div>
			<div style="margin-top: 8px;"><span>时间点拍照：</span><span id="addPoint"><a href="javascript:void(0)" onclick="addPointTable('1','')">新增</a></span></div>
			<div id="point" style="height: 190px;border: 1px solid gray;overflow-y: scroll;">
				<table id="photoSetPointTable"  width="525px" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr style="height: 25px;">
						<td style="color: black;" colspan="3">
							<ul id="weeksli">
								<li><input type="checkbox" atr='1' name="week1" onclick="checkBoxChange('photoSetPointTable','1')"/>周一</li>
								<li><input type="checkbox" atr='2' name="week2" onclick="checkBoxChange('photoSetPointTable','1')"/>周二</li>
								<li><input type="checkbox" atr='3' name="week3" onclick="checkBoxChange('photoSetPointTable','1')"/>周三</li>
								<li><input type="checkbox" atr='4' name="week4" onclick="checkBoxChange('photoSetPointTable','1')"/>周四</li>
								<li><input type="checkbox" atr='5' name="week5" onclick="checkBoxChange('photoSetPointTable','1')"/>周五</li>
								<li><input type="checkbox" atr='6' name="week6" onclick="checkBoxChange('photoSetPointTable','1')"/>周六</li>
								<li><input type="checkbox" atr='7' name="week7" onclick="checkBoxChange('photoSetPointTable','1')"/>周日</li>
							</ul>
						</td>
					</tr>
					<tr>
		 				<td style="padding-left: 60px;width: 290px;">
		 					拍照时间：<input type="text" name="startTime" value="" style="width: 70px;" readonly="readonly" onfocus="WdatePicker({dateFmt:'HH:mm'})" onchange="inputChange('point','photoSetPointTable',this,'0')"/>
<!-- 		 					------  -->
<!-- 		 					<input type="input" name="endTime" value="" style="width: 70px;"/> -->
						</td>
						<td>
<!-- 							拍照间隔：<input type="input" name="interval" value="" style="width: 30px;"/> -->&nbsp;
						</td>
						<td style="width: 40px;">
<!-- 							<a href="javascript:void(0);" onclick="deleteSetTable('photoSetTable1')" style="margin-left: 10px;">删除</a> -->
								&nbsp;
						</td>
					</tr>
					<tr name="hrs" valign="middle"><td valign="middle" colspan="3">&nbsp;<hr style="height: 2px solid gray;"/></td></tr>
				</table>
			</div>
			<table id="dataTable" style="width: 549px;" width="549px" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr valign="middle"  height="35px">
					<td  colspan="4">
						<a href="javascript:void(0)" class="buttonMap" id="cancelButton" onclick="cancelArt()" >取消</a>
	                    <a href="javascript:void(0)" class="buttonMap" id="ysButton" onclick="addPhotoSet();">确定</a>
						<a href="javascript:void(0)" class="buttonMap" id="delButton" onclick="deleteSet();">删除</a>
					</td>
				</tr>
			</table>
			</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/md5/base64.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/js_Map.js' />"></script>
<%@include file="vehiclePhotoSet_js.jsp"%>
</body>
</html>