<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" %>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/oiladd.css" />" />

</head>
<body style="width:500px">
   <input type="hidden" id="vehicle_vin" name="vehicle_vin" value="<s:property value='vehicle_vin'/>"/>
   <input type="hidden" id="vehicle_ln" name="vehicle_ln" value="<s:property value='vehicle_ln'/>"/>
   <input type="hidden" id="optionUserid" name="optionUserid" value="<s:property value='#session.adminProfile.userID'/>"/>
   <input type="hidden" id="isMapGroundLogo" name="isMapGroundLogo" value=""/> 
<!-- 不同内容 -->
<div id="poparea" class="popArea">
<table width="100%" border="0" cellspacing="8" cellpadding="0">
			<tr>
			<td>
			<table border="0" cellspacing="4" cellpadding="0">
			<tr>
			<td>时间：</td>
			<td colspan="6">
			 	<input readonly="readonly" 
			 			id="start_time" 
			 			name="start_time"  
				       	class="Wdate" 
				       	type="text" 
				       	size="20"  
				       	onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
				       	
			     至
			    <input readonly="readonly" size="20" id="end_time" name="end_time"
				       class="Wdate" type="text" 
				       onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" />
			</td>
				
		    <td>
		    	<s:hidden id="chooseorgidEngine" name="organizationId"/>
		    	<input class="btn_inquires" type="button" onmouseover="this.style.backgroundPosition='0px -200px'" 
		    	onmouseout="this.style.backgroundPosition='0px -150px'" value="&nbsp;" 
		    	onclick="javascript:newOilControlObj.loadImg();"/>
		    	
		    </td>
			</tr>
			</table>
			</td>
			</tr>
		</table>
<table width="98%" border="0" cellspacing="0" cellpadding="0">
	<!-- 图片显示 -->
	<tr id="imageDiv" class="checkTrueForJudge">
		<td class="">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr class="reportInline">
				    <!-- 油量趋势图 -->
					<td align="center" id="show" class="loadImagesPic3" >
						<div style="position : relative;align-text:center;">
						<div id="leftImgPicChange" class="loading-small" style="width:100%; height:200px;"></div>
						<div id="leftImg" style="position : absolute;float:left; top:10px;left:10px;visibility:hidden;cursor: pointer;"><img src="../images/kcptImages/chart_bar.png" onclick="newOilControlObj.showPicClick('leftImgPicChange')" title="点击看大图"/></div>
						</div>
					</td>
				</tr>
<!-- 				<td width="2%"></td> -->
				<!-- 加油偷油告警位置 -->
				<tr>
					<td align="center" id="map111" class="loadImagesPic3">
						<div style="position : relative;align-text:center;">
						<div id="rightImgPicChange" class="loading-small" style="width:100%; height:230px;"></div>
						<div id="leftImg2" style="position : absolute;float:left; top:0px;right:0px;visibility:visible;cursor: pointer;">
						
						<table id="mapbartab"  border="0" cellspacing="0" cellpadding="0" style="width:100%; ">
		                    <tr id="maptoolbar" >
		                      <td height="20" align="right" style="width: 120px;border-left:1px solid #4573BE;background:url(../images/kcptImages/fudong_bg.png);">
		                      <table width="120" border="0" cellpadding="0" cellspacing="0">
		                        <tr>
		                          <td align="center"><a href="#" title="隐藏" onclick="newOilControlObj.maptoolbarIsshow('up');"><img id="goup" src="../images/kcptImages/up_arrow.png" border="0" /></a></td>
		                          <td align="center"><input type="checkbox" id="addOilCheckbox" onclick="newOilControlObj.addOrLose();" checked="checked" />加油</td>
		                          <td align="center"><input type="checkbox" id="loseOilCheckbox" onclick="newOilControlObj.addOrLose();" checked="checked" />骤减</td>
		                         
		                        </tr>
		                      </table>
		                      </td>
		                    </tr>
	                    <tr id="fudong_down" style="display:none;margin-right: 0px;width: 120px;">
	                      <td align="center" valign="top"><a href="#" ><img src="../images/kcptImages/fudong_down.png" width="60" height="8" onclick="newOilControlObj.maptoolbarIsshow('down')" border="0" /></a></td>
	                    </tr>
	               </table>
	                   </div>
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>
<div id="focusDiv">
 <input type="text" id="focus_txt" />
</div>
<img style="display:none;" id="dms" src=" <s:url value='/scripts/amcharts/amline.swf'/> "/>
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery.wresize.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" src="../scripts/amcharts/swfobject.js"></script>
<%-- <script type="text/javascript" src="http://apis.mapabc.com/webapi/auth.json?t=flashmap&v=2.4.1&key=<s:text name='map.key' />"></script> --%>
<script type="text/javascript" src="http://app.mapabc.com/apis?t=flashmap&v=2.4.1&key=<s:text name='map.key' />"></script>
<script type="text/javascript" src="../scripts/oilmonitor/showpositionjs.js"></script> 
<script type="text/javascript" src="../scripts/oilmonitor/newOilControl.js"></script>
<script>
/* test="#session.adminProfile.en_code == '0000600001' or #session.adminProfile.en_code == '0000600002' or #session.adminProfile.en_code == '0000600003' or #session.adminProfile.en_code == '0000600004' or #session.adminProfile.en_code == '0000105556'" */
<s:if test="#session.adminProfile.en_code == '0000850260'">
	$("#isMapGroundLogo").val("1");
</s:if>
<s:else>
	$("#isMapGroundLogo").val("0");
</s:else>
</script>
</body>
</html>
