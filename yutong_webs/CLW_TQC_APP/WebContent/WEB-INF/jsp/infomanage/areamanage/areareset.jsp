<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>

<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body style="background:#FBFBFB;">
	<div id="wrapper">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">
		<div id="center">
		<s:form id='update_form' method="post" action="area_update">
		<div class="titleBar">
			<div class="title">区域设置</div>
		</div>
		<s:hidden id="area_idhidden" name="areaInfo.areaId"></s:hidden>
	<table id="bugAddHeihgt" width="950px" border="2" cellspacing="0" cellpadding="0" align="center">
	<tr>		
		<td valign="top">
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" id="show_info">			
			<tr>
				<td align="center">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr><td height="10" /></tr>	
					<tr>
					<td colspan="7" style="padding-left: 0px;">
						<div id="content_rightside" style="border: 1px solid #999;">
						<div id="iCenter" style="width: 100%; height: 350px;"></div>
						</div>
					</td>
					</tr>
				   <tr><td height="10" /></tr>	
				   <tr>
			     		<td>区域名称：</td>
			     		<td align="left">
			     			<input type="text" name="areaInfo.areaName" id="areaName" value="${areaInfo.areaName}" maxlength="14" style="width:160px;"/>
			     			<span class="noticeMsg">*</span>
						</td>
						<td align="left"><a href="javascript:void(0)" class="buttonMapR" onclick="drawrect();">规划区域</a></td>
						<td align="left">左下&nbsp;&nbsp;经:</td>
						<td align="left">
						<input id="upleftlat" name="areaInfo.min_longitude" value="${areaInfo.min_latitude}" readonly="readonly"/>
						<span class="noticeMsg">*</span>
						</td>
						<td align="left">纬:</td>
						<td align="left">
						<input id="upleftlon" name="areaInfo.min_latitude" value="${areaInfo.min_longitude}" readonly="readonly" />
						<span class="noticeMsg">*</span>
						</td>
			     	</tr>
					<tr><td height="10" /></tr>
					 <tr>
			     		<td>备注：</td>
			     		<td align="left">
						<input type="text" name="areaInfo.remark" id="remark" value="" maxlength="14" style="width:160px;"/>
						</td>
						<td align="left"><a href="javascript:void(0)" class="buttonMapR" onclick="removeOverlay();">清除</a></td>
						<td align="left">右上&nbsp;&nbsp;经:</td>
						<td align="left">
						<input id="downrightlat" name="areaInfo.max_longitude" value="${areaInfo.max_latitude}" readonly="readonly"/>
						<span class="noticeMsg">*</span>
						</td>
						<td align="left">纬:</td>
						<td align="left">
						<input id="downrightlon" name="areaInfo.max_latitude" value="${areaInfo.max_longitude}" readonly="readonly"/>
						<span class="noticeMsg">*</span>
						</td>
			     	</tr>
					<tr><td height="10" /></tr>
					
				</table>
				</td>
			</tr>
			<tr valign="middle"  height="50px">
				<td>
	               <a href="#" onclick="goBack('areaReady.shtml')" class="buttonMap"><s:text name="button.cancle" /></a>
	               <a href="javascript:void(0)" class="buttonMap" id="addbutton">确定</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
	<center>
		<table width="550px">
		<tr>
        <td align="right">
		<span style="line-height: 22px" >将站点进行标记，以便更清楚分辨站点分布！</span>
		</td>
		</tr>
		</table>
		</center>
		</s:form>
		</div>
		</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
	<%@include file="areareset_validate.jsp"%>
</body>
</html>
