<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" %>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/oiladd.css" />" />
<link rel="stylesheet" type="text/css" href="../stylesheets/pop.css" />
<link rel="stylesheet" type="text/css" href="../scripts/flexigrid/flexigrid.css" />

</head>
<body>
	<input type="hidden" id="vehicle_vin" name="vehicle_vin" value="<s:property value='vehicle_vin'/>" />
   	<input type="hidden" id="vehicle_ln" name="vehicle_ln" value="<s:property value='vehicle_ln'/>" />
   <input type="hidden" id="optionUserid" name="optionUserid" value="<s:property value='#session.adminProfile.userID'/>"/>
   <input type="hidden" id="ftlyId" name="ftlyId" value=""/> 
   <input type="hidden" id="isMapGroundLogo" name="isMapGroundLogo" value=""/>
<!-- 不同内容 -->
<div id="poparea" class="popArea">
  		<table class="table" width="470" border="0" cellpadding="0" cellspacing="0">
  			 <tr>
                <th><span style="cursor: pointer;" onclick="alarmControlObj.selectUp()">上一条</span></th>
                <td id="deiOffOil" align="center" style="background: none">
                	<select id="alarmMsg" name="alarmMsg" style="width: 280px;" onchange="alarmControlObj.selectOnChange()">
                		
                	</select>
                </td>
                <th><span style="cursor: pointer;" onclick="alarmControlObj.selectDown();">下一条</span></th>
            </tr>        	
  		</table>
  		<div id="Layer1"  class="alarmLayer" style="display: none;bottom: 415px;width: 265px;"><img align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
		    <span>提示信息</span>——<span id="inforeault" ></span>
		  </div>
        <table id="alarmParam" style="visibility: hidden;" class="table" width="470" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <th id="stateTxt">油量骤减：</th>
                <td id="addOill" align="center">
                </td>
              	<th>驾驶员：</th>
                <td id="driverEmp" align="center">
                </td>
              	<th>电话：</th>
                <td id="drivertel" align="center">
                </td>
            </tr>
        </table>
  <div class="sub-form">
      <form action="#">
          <textarea id="opeerate_desc" onfocus="alarmControlObj.cleartext()" style="height:30px;width: 80%;color:#999999">处理意见</textarea>
          <!--<a id="chiliBtn" href="javascript:void(0)" class="btn-blue" onclick="alarmControlObj.updateFtlyAlarm()" style="margin-top: 5px; color: #fff;">解除</a> -->         
          <input class="btn-blue"  style="marign-bottom:5px;cursor: pointer;" type="button"  value="解&nbsp;除" onclick="alarmControlObj.updateFtlyAlarm();"/>
      </form>
  </div>
  <div id="map2" style="width:100%; height:180px;">
  </div>
  	<div id="gala" style="height: 100%;width: 100%;">
  		<table width="100%" id="ftlyAlarmList" cellspacing="0">
        </table>
	</div>
</div>
<div id="focusDiv">
 <input type="text" id="focus_txt" />
</div>
<script type="text/javascript" src="../scripts/flexigrid/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../scripts/flexigrid/flexigrid.js"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="../scripts/wit/tools.js"></script>
<%-- <script type="text/javascript" src="http://apis.mapabc.com/webapi/auth.json?t=flashmap&v=2.4.1&key=<s:text name='map.key' />"></script> --%>
<script type="text/javascript" src="http://app.mapabc.com/apis?t=flashmap&v=2.4.1&key=<s:text name='map.key' />"></script> 
<script type="text/javascript" src="../scripts/amcharts/swfobject.js"></script>
<script type="text/javascript" src="../scripts/oilmonitor/showpositionjs.js"></script>  
<script type="text/javascript" src="../scripts/oilmonitor/newAlarmControl.js"></script>
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