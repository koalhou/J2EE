<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>


<html >

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title><s:text name="common.title" /></title>

<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
<%@ include file="/WEB-INF/jsp/common/key2.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/sidelayerMapUitl.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.parser.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.autoresize.js" />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<!-- 中文注释 -->
</head>

<body onload="init()"  onbeforeunload="testspeed()">
<input id="real_vin" name="real_vin" type="hidden" value="<s:property value='vin'/>" /> 
<input type="hidden" id="real_optionUserid" name="real_optionUserid" value="<s:property value='#session.adminProfile.userID'/>"/>
    <!-- 不同内容 -->
<div id="bgdiv" class="popArea" style="display: none">
    <div id="Layer1" class="Layer1" style="display: none"><img align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
    <span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
    </div>
	<div class="warn">
	 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_space" >
	  <tr>
	    <td width="19%" align="center"><label for="select"></label>
	      上报间隔（秒）</td>
	    <td width="22%" align="center" style="padding-right: 8px;"><label for="Intervaltime">
	      <input name="Intervaltime" type="text" class="text90" id="Intervaltime" value="10"/></label></td>
	    <td width="19%" align="right">持续时间（秒）</td>
	    <td  width="22%" align="center"><label for="Intervaltimes">
	      <input name="Intervaltimes" type="text" class="text90" id="Intervaltimes" value="300"/></label>
	    </td>
	    <td width="18%" align="right"><a href="#" class="btn-blue" onfocus="this.blur()" onclick="real_Key_monitoring()">重点监控</a></td>
	  </tr>
	</table>
	</div>
  
	<!--以下为预留位置-->  
	  <div id="map3">
<!--	  	<div id ="realMapbarDivNO" style="position: absolute; left:110px; top: 350px; z-index: 1000;">©2011 MapABC - GS(2010)6011</div>-->
	 	<div id="realiCenter" style="width: 100%; height: 325px;"></div>
	  </div>
  
	  <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_bold">
	  <tr>
	  	<td width="66" align="right">乘载情况：</td>
	    <td width="86" class="text_bg72"><s:label id="real_STU_NUM" name="real_STU_NUM" ></s:label></td>
	    <td>&nbsp;</td>
	    <td width="66" align="right">车&nbsp;&nbsp;&nbsp;&nbsp;速：</td>
	    <td width="86" class="text_bg72"><s:label id="real_SPEEDING" name="real_SPEEDING" ></s:label></td>
	    <td>&nbsp;</td>
	    <td width="66" align="right">行驶方向：</td>
	    <td width="86" class="text_bg72"><s:label id="real_DIRECTION" name="real_DIRECTION" ></s:label></td>
	    
	    
	  </tr>
	  <tr>
	    <td align="right">瞬时油耗：</td>
	    <td class="text_bg72"><s:label id="real_OIL_INSTANT" name="real_OIL_INSTANT" ></s:label></td>
	    <td>&nbsp;</td>
	    <td align="right">转&nbsp;&nbsp;&nbsp;&nbsp;速：</td>
	    <td class="text_bg72"><s:label id="real_ENGINE_ROTATE_SPEED" name="real_ENGINE_ROTATE_SPEED" ></s:label></td>
	    <td>&nbsp;</td>
	    <td width="66" align="right">定位状态：</td>
	    <td width="86"  class="text_bg72"><s:label id="real_DINGWEI_STAT_INFO" name="real_DINGWEI_STAT_INFO" ></s:label></td>
	    
	  </tr>
	  <tr>
	    
	    <td align="right">点火状态：</td>
	    <td class="text_bg72"><s:label id="real_STAT_INFO" name="real_STAT_INFO" ></s:label></td>
	    <td>&nbsp;</td>
	    <td width="66" align="right">时&nbsp;&nbsp;&nbsp;&nbsp;间：</td>
	    <td width="86" class="text_bg72"><s:label id="real_TERMINAL_TIME" name="real_TERMINAL_TIME" ></s:label></td>
	    <td>&nbsp;</td>
	    <td align="right">&nbsp;</td>
	    <td align="right">&nbsp;</td>
	    
	    
	  </tr>
	</table>
</div>
<div id="focusDiv">
 <input type="text" id="focus_txt" />
</div>
<%@ include file="newrealtimelook_js.jsp"%>

</body>
</html>
  
