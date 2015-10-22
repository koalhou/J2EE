<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>


<html >

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title><s:text name="common.title" /></title>

<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
<link type="text/css" href="<s:url value='/scripts/JQuerySlider/css/jquery-ui-1.7.3.custom.css'/>" rel="stylesheet" />
<%@ include file="/WEB-INF/jsp/common/key2.jsp"%>
</head>

<body onLoad="mapInit('bitiCenter')" onunload="setonunload()" onbeforeunload="testspeed()">

<input id="vin" name="vin" type="hidden" value="<s:property value='vin'/>" />
<input id="lookflag" name="lookflag" type="hidden"  value="<s:property value='lookflag'/>"/>
<input id="lon" name="lon" type="hidden" value="<s:property value='lon'/>" />
<input id="lat" name="lat" type="hidden" value="<s:property value='lat'/>" />
<input id="route_id" name="route_id" type="hidden" value="<s:property value='route_id'/>" />	 
<input type="hidden"  id="organizationid" name="organizationid" value="<s:property value='#session.adminProfile.organizationID'/>" />


<div id="loading" class="popArea" style="display:none; position:absolute; z-index:1; right:0; top:0px; background:url(../newimages/loading.gif) no-repeat center center;">
</div>
<!-- 不同内容 -->
	<div id="bgdiv" class="popArea">
	<div id="Layer1" class="Layerbitlook" style="display: none"><img align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
    	<span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
    </div>
	<!-- 不同内容 -->
	<div class="warn">
	 <table width="480px" border="0" cellpadding="0" cellspacing="0" class="table_space">
	  <tr>
	    <td align="left" ><label for="select"></label>
	      <select name="select" class="select84" id="selectModel" onchange="selectModel(this)">
	        <option value="1">行车记录</option>
	        <option value="2">任意时段</option>
	      </select>
	    </td>
	    <td >
	    	<div id="everyTime" style="display: none;">
	    		<table>
	    			<tr>
	    				<td align="center">
						      <label for="textfield">
						      <input class="Wdate" readonly="readonly" type="text"  size="24" id="line_start_time" name="line_start_time" 
	                         onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,maxDate:'%y-%M-%d %H:%m:%s',
															  isShowClear:false,onpicked:pickedstarttime})"
				  value="<s:property value='line_start_time'/>"/>
						      </label>
					    </td>
					    <td width="16" align="center">至</td>
					    <td align="center">
						    <label for="select2">
						      <input class="Wdate" readonly="readonly" type="text"  size="24" id="line_end_time" name="line_end_time" 
	                         onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,maxDate:'%y-%M-%d %H:%m:%s',
															  isShowClear:false,onpicked:pickedendtime})"
				 value="<s:property value='line_end_time'/>"/>
						    </label>
					    </td>
	    			</tr>
	    		</table>
	    	</div>
	    	<div id="diveringNode" style="display: block">
	    		<table>
	    			<tr>
	    				<td align="center">
						      <input class="Wdate" readonly="readonly" type="text"  size="13" id="timetab_time" name="timetab_time"  style="width: 90px;"
	                         onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
															  autoPickDate:true,
															  maxDate:'%y-%M-%d',
															  isShowClear:false})"  value="<s:property value='timetab_time'/>"/>
					    </td>
					    <td width="2px" align="center"></td>
					    <td align="center">
						    <select name="select" style="width: 200px;" id="selectDriveingNode"  onchange="selectbutton()">
						        <option value="0">请点击查询</option>
						    </select>
					    </td>
	    			</tr>
	    		</table>
	    	</div>
	    </td>
	    
	    <td align="left" ><a href="#" class="btn-blue" onfocus="this.blur()" onclick="selectControl()">查询</a></td>
	  </tr>
	</table>
	
	</div>
	  
	<!--以下为预留位置-->  
	  <div id="bitmap" style="height:325px;">
	<!--  <div id ="MapbarDivNO" style="position: absolute; left:160px; top: 300px; z-index: 1000;">©2011 MapABC - GS(2010)6011</div>-->
		  <div id ="MapbarDiv" valign="right" style="width:110px; height:41px; position:absolute; z-index:1; right:0; top:50px;display:none">
			  <table  width="100%" border="0" cellspacing="0" cellpadding="0">
			
			      <tr id="maptoolbar" valign="right">
			        
			       <td height="33" valign="right">
			       
			      <table width="110px" border="0" cellpadding="0" cellspacing="0" style=" border:1px solid #186883;">
				      <tr id="tr01">
				         <td align="center" width="100px">
				   			<s:label id="errorinfo" name="errorinfo" ></s:label>
				         </td>
				       </tr>
			      
			       </table>
			       </td>
			      </tr>
			  </table>	    	
	   </div>
	   <div id="bitiCenter" style="width: 100%; height: 280px;"></div>
	   <div>
	 	<table width="100%" height="5" cellpadding="0" cellspacing="0" border="0" >
			<tr>
				<td>
						<div id="slider-range-min"></div>
				</td>
			</tr>
		</table>		
			<div class="arr_bar">
			   <div class="arr_bar_l"></div>
			    <div class="arr_bar_c">
			     <table width="232px" cellpadding="0" cellspacing="0" >
			         <tr>
			             <td width="38px"><img style="cursor: pointer;" id="tongbu" onclick="tongbuOpt()" title="重新开始" src="../newimages/arr_first.gif" /></td>
			               <td width="38px"><img style="cursor: pointer;" id="kuaitui" onclick="kuaituiOpt()" title="慢速" src="../newimages/arr_pre.gif" /></td>
			<!--                <td><img src="../newimages/arr_stop.gif" /></td>-->
			               <td width="38px"><img style="cursor: pointer;" id="playimg" onclick="baofangOpt();return false;" title="播放" src="../newimages/arr_play.gif" /></td>
			               <td width="38px"><img style="cursor: pointer;" id="jieshu" onclick="jieshuOpt()" title="结束" src="../newimages/arr_stop2.gif" /></td>
			               <td width="38px"><img style="cursor: pointer;" id="kuaijin" onclick="kuaijinOpt()" title="快速" src="../newimages/arr_next.gif" /></td>
			               <td ><img style="cursor: pointer;" id="weibu" onclick="weibuOpt()" title="完成播放" src="../newimages/arr_last.gif" /></td>
			           </tr>
			       </table>
			   </div>
			   <div class="arr_bar_r"><strong><s:label id="bofangbeishu" name="bofangbeishu" ></s:label></strong></div>
			</div>
		
	 </div>
	
	  </div>
	  
	 <table id="notalarmtable" style="display: block;" width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_bold">
	  <tr>
	    <td width="66" align="right">上报时间：</td>
	    <td width="86" class="text_bg72"><s:label id="bofangtime" name="bofangtime" ></s:label></td>
	    <td>&nbsp;</td>
	    <td width="66" align="right">定位状态：</td>
	    <td width="86"  class="text_bg72"><s:label id="DINGWEI_STAT" name="DINGWEI_STAT" ></s:label></td>
	    <td>&nbsp;</td>
	    <td width="66" align="right">行驶速度：</td>
	    <td width="86" class="text_bg72"><s:label id="SPEEDING" name="SPEEDING" ></s:label></td>
	  </tr>
	  <tr>
	    <td align="right">行驶方向：</td>
	    <td class="text_bg72"><s:label id="DIRECTION" name="DIRECTION" ></s:label></td>
	    <td>&nbsp;</td>
	    <%-- <td align="right">引擎转速：</td>
	    <td class="text_bg72"><s:label id="ENGINE_ROTATE_SPEED" name="ENGINE_ROTATE_SPEED" ></s:label></td>--%>
	    <td align="right">核载人数：</td>
	    <td class="text_bg72"><s:label id="LIMITE_NUMBER" name="LIMITE_NUMBER" ></s:label></td>
	    <td>&nbsp;</td>
	    <td align="right">点火状态：</td>
	    <td class="text_bg72"><s:label id="FIRE_UP_STATE" name="FIRE_UP_STATE" ></s:label></td>
	  </tr>
	  <tr>
	    <%--<td align="right">乘载情况：</td>
	    <td class="text_bg72"><s:label id="LIMITE_NUMBER" name="LIMITE_NUMBER" ></s:label></td>
	    <td>&nbsp;</td>--%>
	    <td align="right">油箱油量：</td>
	    <td class="text_bg72"><s:label id="oil_instant" name="oil_instant" ></s:label></td>
	    <td>&nbsp;</td>	  
	  </tr>
	</table>
	
	<table id="alarmtable" style="display: none;" width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_bold">
	  <tr>
	    <td width="66" align="right">上报时间：</td>
	    <td width="86" class="text_bg72"><s:label id="alarmbofangtime" name="alarmbofangtime" ></s:label></td>
	    <td>&nbsp;</td>
	    <td width="66" align="right">定位状态：</td>
	    <td width="86"  class="text_bg72"><s:label id="alarmDINGWEI_STAT" name="alarmDINGWEI_STAT" ></s:label></td>
	    <td>&nbsp;</td>
	    <td width="66" align="right">行驶速度：</td>
	    <td width="86" class="text_bg72"><s:label id="alarmSPEEDING" name="alarmSPEEDING" ></s:label></td>
	  </tr>
	  <tr>
	    <td align="right">行驶方向：</td>
	    <td class="text_bg72"><s:label id="alarmDIRECTION" name="alarmDIRECTION" ></s:label></td>
	    <td>&nbsp;</td>
	    <%--<td align="right">引擎转速：</td>
	    <td class="text_bg72"><s:label id="alarmENGINE_ROTATE_SPEED" name="alarmENGINE_ROTATE_SPEED" ></s:label></td>--%>
	     <td align="right">乘载情况：</td>
	    <td class="text_bg72"><s:label id="alarmLIMITE_NUMBER" name="alarmLIMITE_NUMBER" ></s:label></td>
	    <td>&nbsp;</td>
	    <td align="right">点火状态：</td>
	    <td class="text_bg72"><s:label id="alarmFIRE_UP_STATE" name="alarmFIRE_UP_STATE" ></s:label></td>
	  </tr>
	  <tr>
	    <%--<td align="right">乘载情况：</td>
	    <td class="text_bg72"><s:label id="alarmLIMITE_NUMBER" name="alarmLIMITE_NUMBER" ></s:label></td>
	    <td>&nbsp;</td>--%>
	    <td align="right">油箱油量：</td>
	    <td class="text_bg72"><s:label id="alarmoil_instant" name="alarmoil_instant" ></s:label></td>
	    <td>&nbsp;</td>
	    <td align="right">事件类型：</td>
	    <td style="text-align:center;">
	    <input id="alarm_type_name" type="text" style="background:url(../newimages/info_bg72.png) no-repeat left top; border:none; width:67px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;text-align:center;font-weight:bold;color: #555" readonly="readonly"/>
	    </td>
	  </tr>
	</table>		
</div>
    
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.parser.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.autoresize.js" />"></script>
<script type="text/javascript" src="<s:url value='/scripts/JQuerySlider/js/jquery-ui-1.7.3.custom.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/JQuerySlider/js/ui.core.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/JQuerySlider/js/ui.slider.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<%@ include file="newbitlooktest_js.jsp"%>

</body>
</html>