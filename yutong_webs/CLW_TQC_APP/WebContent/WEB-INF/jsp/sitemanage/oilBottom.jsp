<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<script type="text/javascript" src="../scripts/flexigrid/jquery-1.5.2.min.js"></script>
		<script type="text/javascript" src="../scripts/sitemanage/siteManagerDialog.js"></script>
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body background:#FBFBFB;>
	<div id="wrapper">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		
		
		
		<div id="content">
		
		<div class="titleBar">
			<div class="title"><s:text name="加油设置" /></div>
		</div>
		
	<table id="bugAddHeihgt" width="550px" border="2" cellspacing="0" cellpadding="0" align="center">
	<tr>		
		<td valign="top">
			<table class="" border="0" align="center" cellpadding="0" cellspacing="0" id="show_info">			
			<tr>
				<td align="center">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				   
				   
				   
				   
					<tr height="33px">
					  <td colspan="7" style="padding-left: 0px;"><span style="line-height: 22px"><b>低油量提醒阀值</b></span></td>
					</tr>
				   
				   <tr><td height="10" /></tr>	
				   
				   
				   
				   <tr>
					     		<td><input type="checkbox" name="oilValueCheck" id="oilValueCheck" value="1"/>启用低油量阀值提醒</td>
					     		<td>提醒阀值：</td>
					     		<td>
									<select name="" id="lowerOilValue" style="width: 80px;" >
										<option value="0.05">5%</option>
										<option value="0.1">10%</option>
										<option value="0.15">15%</option>
										<option value="0.2">20%</option>
										<option value="0.25">25%</option>
										<option value="0.3">30%</option>
										<option value="0.35">35%</option>
										<option value="0.40">40%</option>
										<option value="0.45">45%</option>
										<option value="0.50">50%</option>
									</select><span class="noticeMsg">*</span>
								</td>
					     	</tr>
				   
				   
				   
				   
					
					
					
					<tr><td height="10" /></tr>		
					
					
					
					
					 <tr>
					     		<td></td>
					     		<td>油价（元/升）：</td>
					     		<td >
					     			
									<input type="text" name="oilPrice" id="oilPrice" value="" maxlength="14" style="width: 80px;"/><span class="noticeMsg">*</span>
									
								</td>
					     	</tr>
				   
					<tr><td height="10" /></tr>
					
				</table>
				</td>
			</tr>
			
			
			 <tr valign="middle"  height="50px">
								<td  colspan="4">
								<s:if test="#session.perUrlList.contains('111_3_5_13_1')">
					               <a href="javascript:void(0)" class="buttonMap" onclick="sitManagerDialogObj.addLowerVal()">确定</a>
					               </s:if>
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
		<span style="line-height: 22px" >油量监控模块支持对于低油量车辆进行标记，以便识别需要加油的车辆！</span>
		</td>
		</tr>
		</table>
		</center>
	
	
	
	
	
	
	
	
	
		
	
		</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
</body>
</html>
