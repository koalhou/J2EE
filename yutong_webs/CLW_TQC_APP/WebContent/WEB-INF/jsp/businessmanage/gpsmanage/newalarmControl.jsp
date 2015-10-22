<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" %>
<html >

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
<%@ include file="/WEB-INF/jsp/common/key2.jsp"%>
</head>
<body onload="mapInit()" onbeforeunload="testspeed()">
   <s:hidden id="alarm_start_longitude" name="alarmmanage.longitude"/>
   <s:hidden id="alarm_start_latitude" name="alarmmanage.latitude"/>
   <s:hidden id="vehicle_ln" name="alarmmanage.vehicle_ln"/>
   <s:hidden id="alarm_id"   name="alarmmanage.alarm_id"/>  
   <s:hidden id="alarmtypeid"   name="alarmmanage.alarm_type_id"/> 
   <s:hidden id="vehicle_vin"   name="alarmmanage.vehicle_vin"/> 
   <s:hidden id="direction"   name="alarmmanage.direction"/> 
   <s:hidden id="sourceid" name="sourceid"/>
   <s:hidden id="alarm_time" name="alarmmanage.alarm_time"/>
   <s:hidden id="chooseorgid" name="alarmmanage.enterprise_id"/>
   <input type="hidden" id="optionUserid" name="optionUserid" value="<s:property value='#session.adminProfile.userID'/>"/>
   <!-- <input type="hidden" id="isMapGroundLogo" name="isMapGroundLogo" value=""/>  -->
<!-- 不同内容 -->
<div id="poparea" class="popArea">
  <div id="Layer1"  class="alarmLayer" style="display: none"><img align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
    <span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
  </div>
    <!-- 不同内容 -->
  <div class="warn">
    <div class="alarmh2times">
	    <h2 id="alarmtypename" style="margin-top:2px; "><s:property value="alarmmanage.alarm_type_name"/></h2>
	     <span id="alarmtime" class="times" style="margin-left:7px; "></span>
    </div>
    <div class="wait"><strong id="alarmcount"><s:property value="alarmmanage.alarm_count"/></strong><span>条未处理告警（3天内）</span></div>
        <table class="table" width="470" border="0" cellpadding="0" cellspacing="0">
            <tr id="ala_details"></tr>
        </table>
  </div>
  <div id="map2">
  </div>
  <div class="sub-form">
      <form action="#">
          <textarea id="opeerate_desc" onfocus="cleartext();" onpropertychange="if(value.length>100) value=value.substr(0,100)" onchange="getTxtLength(this);"  onblur="getTxtLength(this);" onkeydown="getTxtLength(this)" onkeyup="getTxtLength(this);" style="height:58px;color:#999999">处理意见</textarea>
          <div style="margin-top: 0px;font-size: 12px;font-weight: bold;color: #3b97d4;margin-left: 5px;">共<span id="curLength">0</span>/100个字</div>
          <span class="warn-history">
          <a id="morealarm" href="#" onclick="morealarm()" target="_parent">查看历史告警</a>
          </span>
          <s:if test="#session.perUrlList.contains('111_3_1_9_2')">
          <a href="#" class="btn-blue" onclick="posttabOffCommand()" style="margin-top: 5px; color: #fff;">解除</a>
          </s:if>
      </form>
  </div>
</div>
<div id="focusDiv">
 <input type="text" id="focus_txt" />
</div>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.parser.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.autoresize.js" />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/AlarmDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<%@include file="/WEB-INF/jsp/businessmanage/alarmmanage/newtabalarmdeal_js.jsp"%>
 <!-- test="#session.adminProfile.en_code == '0000600001' or #session.adminProfile.en_code == '0000600002' or #session.adminProfile.en_code == '0000600003' or #session.adminProfile.en_code == '0000600004' or #session.adminProfile.en_code == '0000105556'" -->
 <!-- <script>
	<s:if test="#session.adminProfile.en_code == '0000850260'">
		jQuery("#isMapGroundLogo").val("1");
	</s:if>
	<s:else>
		jQuery("#isMapGroundLogo").val("0");
	</s:else>
	</script> -->
</body>
</html>