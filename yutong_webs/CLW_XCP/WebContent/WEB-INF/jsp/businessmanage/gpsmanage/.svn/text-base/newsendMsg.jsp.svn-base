<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>

<html >

<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>

<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />

</head>

<body onload="mapInit()" onunload="closeThread()">
<!-- 不同内容 -->
<div id="bgdiv" class="popArea" style="display: none">
<input type="hidden" id="optionUserid" name="optionUserid" value="<s:property value='#session.adminProfile.userID'/>"/>
<input type="hidden"  id="enterprise_id" name="enterprise_id" value="<s:property value='#session.adminProfile.entiID'/>" />
<input type="hidden"  id="organizationid" name="organizationid" value="<s:property value='#session.adminProfile.organizationID'/>" />
<input type="hidden" id="optionUserid" name="optionUserid" value="<s:property value='#session.adminProfile.userID'/>" />
<s:hidden  id="vin" name="terminalViBean.VEHICLE_VIN"/>


    <!-- 不同内容 -->
    <div id="Layer1" class="LayerMsg" style="display: none"><img align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
	   	<span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
	</div>
  <div id="infoShowDiv" class="warn2" style="overflow-x: hidden; overflow-y: auto;">
	
   
    
    
    
  </div>
  
  <div id="showArea1" class="line_spacing2">
    <h2>消息类型：</h2>
    <div class="check_box">
      <div><span class="position0"><input name="InfoType" type="checkbox" id="3"  value="3" /></span><span class="position2">紧急</span> </div>
      <div><span class="position0"><input name="InfoType" type="checkbox" id="1" checked="checked"  value="1" /></span><span class="position2">语音播放</span></div>
      <div><span class="position0"><input name="InfoType" type="checkbox" id="2"  value="0" /></span><span class="position2">车载屏</span></div>
      <div><span class="position0"><input name="InfoType" type="checkbox" id="4" checked="checked"  value="2"/></span><span class="position2">终端显示器</span></div>
    </div>
   	 
  </div>
    <div id="showArea2" class="line_spacing2">
	    <h2>预设消息：</h2>
	    <div>
	      
	      <select class="select400" style="float: left;" id="yusheInfo" name="yusheInfo" size="1"  onchange="onchangeOption();"></select>
	      <a id="deleteInfoTextBtn" class ="sbutton" style="display: inline-block;float: left;margin-top:10px;height: 17px;line-height: 17px" onclick="deleteAndreturn()" >删除预设</a>
	    </div>
    </div>
    <div id="showArea3" class="line_spacing2">
	    <div>
			<textarea id="msg" name="msg" rows="4"  class="sub-form2" onkeyup="gbcount(this)"  onmouseout="gbcount(this)"  ></textarea> 
		</div>  	 
    </div>
    
  <div id="showArea4" class="line_spacing3">
      <table width="100%">
	      <tr>
	      	<td width="30%"><span class="position0"><input name="saveinfo" type="checkbox" value="" id="saveinfo"/></span><span class="position2">保存为预设信息</span></td>
	      	<td width="30%" ><p style="line-height: 15px">您还可以输入</p>
	      					<h3 style="width: 20px;float: left;line-height:15px;font-size: 16px"><label id = "infocount">64</label></h3>
	      					<p style="line-height: 15px">个字</p></td>
	      	<td  >
	      		<a id="buttontype"  class="btn-blue" onfocus="this.blur()" style=" color:#FFF;" onclick="reset()" >重置</a>
          		<a  class="btn-blue" onfocus="this.blur()" style=" color:#FFF;" onclick="postMsg()">发送</a>
          	</td>
	      </tr>
      </table>
          
          
	  
  </div>
</div>
  

<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery.wresize.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.parser.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.autoresize.js" />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<%@ include file="newsendMsg_js.jsp"%>

</body>
</html>