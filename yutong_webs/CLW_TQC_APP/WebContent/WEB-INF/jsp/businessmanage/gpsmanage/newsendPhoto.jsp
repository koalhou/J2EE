<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>



<%@page import="org.apache.velocity.runtime.directive.Include"%>
<html >

<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<title><s:text name="common.title" /></title>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />

</head>

<body onload="photoOnline();iframefoucus();init();">
<s:hidden  id="vin" name="terminalViBean.VEHICLE_VIN"/>
<s:hidden  id="vehln" name="terminalViBean.VEHICLE_LN"/>
<input type="hidden" id="optionUserid" name="optionUserid" value="<s:property value='#session.adminProfile.userID'/>"/>
<!-- 不同内容 -->
<div id="bgdiv" class="popArea">
	<div id="Layer1" class="Layer1" style="display: none"><img align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
	   	<span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
	</div>
    <!-- 不同内容 -->
<div class="camera_box2">
    <h2>摄像头：</h2>
    <div class="check_box">
      <!-- <form id="photo_form" action="SendPhtotAction" method="post">
      <div>
      <s:checkboxlist  id="routeway2" name="routeway2"  list="photoSysMap" listKey="ROUTEWAY_NO" listValue="ROUTEWAY_NAME" value="1"></s:checkboxlist></div>
      </form>-->
      <div><span class="position0"><input name="routeway2" type="checkbox" value="1" checked="checked" /></span><span class="position2">整车</span></div>
      <div><span class="position0"><input name="routeway2" type="checkbox" value="2" checked="checked" /></span><span class="position2">路况</span></div>
      <div><span class="position0"><input name="routeway2" type="checkbox" value="3" /></span><span class="position2">车门</span></div>
      <div><span class="position0"><input name="routeway2" type="checkbox" value="4" /></span><span class="position2">驾驶员</span></div>
    </div>
	<div class="list-sech2">
	  <a  class="btn-blue3" onfocus="this.blur()" onclick="postPhoto()">拍照</a>
	</div>
</div>
  
<!--弹出层--> 
  
	

  <div id="photoAreaDiv" class="photograph_box">
	  <div id="photo_leftAreaDiv" class="photograph_left">
	  	<div class="photograph_content_title">
	  		<table width="100%" height="20px">
	  			<tr>
	  				<td style="font-size:12px; font-weight:bold; width: 50%;height: 22px">
	  				<strong><s:label id="PHOTO_EVENT" name="PHOTO_EVENT" ></s:label></strong>
	  				</td>
	  				<td width="50%"><span><s:label id="PHOTO_TIME" name="PHOTO_TIME" ></s:label></span>
	  				</td>
	  			</tr>
	  		</table>
	  	</div>
	    <div class="photograph_content">
	    <img id="PHOTO" src="../newimages/backgroup.gif" width="320px" height="230px" onerror="onerror_event(this)"/></div>
	  </div>
	  
	  <div id="photo_rightAreaDiv" class="photograph_right">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td height="30" align="center" valign="middle">
			    <img id="upImage" title="上一张" onclick="downContrl()" src="../newimages/sidelayerimages/btn_arrowTop_Disable.png" width="102" height="19" /></td>
			  </tr>
			  <tr>
			    <td height="240" align="center" valign="top">
			    <div><img id="PHOTO0" style="border:2px solid #fc0;float:left " onclick="updateid=false;photoOrtherInfo(photoindex);pointOfIntersection(0);" src="../newimages/backgroup.gif" width="102" height="75" onerror="onerror_event(this)"/></div>
			    <div><img id="PHOTO1" style="float: left;" onclick="updateid=false;photoOrtherInfo(photoindex+1);pointOfIntersection(1);" src="../newimages/backgroup.gif" width="102" height="75" onerror="onerror_event(this)"/></div>
			    <div><img id="PHOTO2" style="float: left;" onclick="updateid=false;photoOrtherInfo(photoindex+2);pointOfIntersection(2);" src="../newimages/backgroup.gif" width="102" height="75" onerror="onerror_event(this)"/></div>
			    </td>
			  </tr>
			  <tr>
			    <td height="30" align="center" valign="middle">
			    <img id="downImage" title ="下一张" onclick="upControl()" src="../newimages/sidelayerimages/btn_arrowBottom_Disable.png" width="102" height="19" /></td>
			  </tr>
		  </table>
	  </div>
  </div>
  
  <div class="sub-form">
	  <div class="div_space2" style="display:none;">
	  <span class="position0"><input type="checkbox" name="PHOTO_FALG" id="PHOTO_FALG" onclick="updateid=true;"/></span>
	  <span class="position2"> 标记为超载</span>
	  </div>
	      <form action="#">
	        <table width="100%">
	        	<tr>
	        		<td colspan="2"><textarea id="remark" name="remark" rows="3" class="sub-form2" style="color:#999999;" onmousedown="cleartext()" onkeydown="updateid=true;" onpaste="updateid=true;">填写备注</textarea>
	        		<input type="hidden" id="PHOTO_ID" name="PHOTO_ID"/>
	        		</td>
	        	</tr>
	        	<tr>
	        		<td width="50%">
	        		<s:if test="#session.perUrlList.contains('111_3_1_2_1')">
	        		<span class="warn-history2">
	        		<a id="lookphoto" href="<s:url value='/photomonitor/initphoto.shtml' />" target="_parent" >查看历史照片</a>
	        		</span>
	        		</s:if>
	        		</td>
	        		<br>
	        		<td width="50%">
	        		<a  class="btn-blue" onfocus="this.blur()" style=" color:#FFF;" onclick="savePhotoInfo()">保存</a>
	        		</td>
	        	</tr>
	        </table>
	                  
	        
	      </form>
	  </div>
  </div>
<div id="focusDiv">
 <input type="text" id="focus_txt" />
</div>

<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<%@ include file="newsendPhoto_js.jsp"%>

</body>
</html>