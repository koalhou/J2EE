<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
<style type="text/css">
.videoborder {
display: block; 
float:left; 
}
</style>
<style type="text/css">
#livetip {
position: absolute;
border-radius: 4px;
-webkit-border-radius: 4px;
-moz-border-radius: 4px;
z-index: 3000;
width:200px;
overflow:hidden;
}

#livetip label{border: 1px solid #d0d0d0; display:block;background-color: #FCFCFC;padding: 4px;}
</style>

</head>

<body onload="photoOnline();showtextshow();showFactoryDiv();StartPlayAllBAWindows();"  onbeforeunload="closeMapDiv();">

<s:hidden id="VIDEO_ID" name="terminalViBean.VIDEO_ID"/>
<s:hidden id="VIDEO_FACTORY" name="terminalViBean.VIDEO_FACTORY"/>
<s:hidden id="VIDEO_SERVER_IP" name="terminalViBean.VIDEO_SERVER_IP"/>
<s:hidden id="STREAM_SERVER_IP" name="terminalViBean.STREAM_SERVER_IP"/>
<s:hidden id="VIDEO_USER" name="terminalViBean.VIDEO_USER"/>
<s:hidden id="VIDEO_PASSWORD" name="terminalViBean.VIDEO_PASSWORD"/>
<input type="hidden" id="m_vidiconId" value="<s:property value='videoID'/>"/>

<input id="vin" name="vin" type="hidden" value="<s:property value='vin'/>" /> 
<s:hidden  id="vehln" name="terminalViBean.VEHICLE_LN"/>
<input type="hidden" id="optionUserid" name="optionUserid" value="<s:property value='#session.adminProfile.userID'/>"/>
<!-- 不同内容 -->

<div id="videoframearea" ><!-- 不同内容 --> <!--<div class="space_height"></div>-->
<s:if test="terminalViBean.VIDEO_FACTORY=='HI'">
	<div id="ocxHI">
	<table cellpadding="0" cellspacing="4" border="0" style="margin-left:4px;">
		<tr>
		    <td  valign="top">
			    <div id="videoCenter" style="width:490px; height:360px; position:absolute;" >
			    
					<div class="videoborder" id="divHI1">
						<table width="242px" height="180px"  border="0" cellpadding="0"
									cellspacing="0" bordercolor="#171C20" id="tableHI1"
									style="margin:0px 0 0px 0;">
									
							<tr>
								<td>
									<object
											classid="clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778"
											codebase="<s:url value='/codebase/ppvsguard.cab' />#version=2,2,0,21460"
											standby="Waiting..." id="PPVSHI1" width="0px" height="0px";
											name="ocx1" title="1号窗口"> 
										</object>
								</td>
							</tr>		
						</table>
					</div>
		
					<div class="videoborder" id="divHI2">
						<table width="242px" height="180px"   border="0" cellpadding="0"
													cellspacing="0" bordercolor="#171C20" id="tableHI2"
													style="margin:0px 0 0 0px;">
								<tr>
									<td>
										<object
											classid="clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778"
											codebase=""
											standby="Waiting..." id="PPVSHI2" width="0px" height="0px"
											name="ocx2" title="2号窗口"> 
										</object>
									</td>
								</tr>
						</table>
					</div>
		
					<div class="videoborder" id="divHI3">
						<table width="242px" height="180px"  border="0" cellpadding="0"
													cellspacing="0" bordercolor="#171C20" id="tableHI3"
													style="margin:0px 0 0 0; ">
								<tr>
									<td>
										<object
											classid="clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778"
											codebase=""
											standby="Waiting..." id="PPVSHI3" width="0px" height="0px"
											name="ocx3" title="3号窗口"> 
										</object>
									</td>
								</tr>
						</table>
					</div>
		
					<div class="videoborder" id="divHI4">
						<table width="242px" height="180px"  border="0" cellpadding="0"
													cellspacing="0" bordercolor="#171C20" id="tableHI4"
													style="margin:0px 0 0 0px; padding:0px 0 0 0;">
								<tr>
									<td>
										<object
											classid="clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778"
											codebase=""
											standby="Waiting..." id="PPVSHI4" width="0px" height="0px"
											name="ocx4" title="4号窗口"> 
										</object>
									</td>
								</tr>
						</table>
					</div>
			</div>
				
			</td>
	    </tr>
	</table>
	</div>
	
</s:if> 

<s:if test="terminalViBean.VIDEO_FACTORY=='DA'">
	<div  id="ocxDA" >
	<table  border="0" align="center" cellpadding="0"
		cellspacing="4" >
		<tr>
			<td align="center" valign="middle"><object id="ocx"
				width="490px" height="380px"
				classid='clsid:902BD8F8-AB6F-4102-AA72-CFBB012F71B2'
				codebase="<s:url value='/codebase/dssPlay.cab' />#version=2,1,0,5">
				<param name="nWndNum" value="4">
			</object></td>
		</tr>
	</table>
	</div>
</s:if>
<div id="Layer1" class="LayerVideo" style="display: none"><img id="tishitu" align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
	   	<span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
</div>
<s:if test="terminalViBean.VIDEO_FACTORY=='HI'">
<div id="picshot" class="alert alert-success" style="display: none;">
   <!--关闭按钮-->
   <a href="#" class="close-btn" onclick="closepicshot()"><span></span></a>
   <!--提示信息-->
   <span class="text">截屏成功！</span>
    <!--路径部分-->
   <div class="info">
      <!--路径-->
        <span id="picpath" class="path-uri"></span>
           <!--拷贝链接-->
        <span class="path-copy">&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="copePath()">打开路径</a></span>
    </div>
</div>

<div id="carmera1" class="camera_box" style="display:none">
	<h2 style=" padding-top:1px;margin: 0px 17px 0px 0px;">摄像头：</h2>
	<div class="check_box">
	  <div style="margin: 3px 5px 3px 5px"><span class="position3"><input name="routeway2" type="checkbox" value="1" checked="checked" /></span><span class="position4">整车</span></div>
      <div style="margin: 3px 5px 3px 5px"><span class="position3"><input name="routeway2" type="checkbox" value="2" checked="checked" /></span><span class="position4">路况</span></div>
      <div style="margin: 3px 5px 3px 5px"><span class="position3"><input name="routeway2" type="checkbox" value="3" /></span><span class="position4">车门</span></div>
      <div style="margin: 3px 5px 3px 5px"><span class="position3"><input name="routeway2" type="checkbox" value="4" /></span><span class="position4">驾驶员</span></div>
	</div>
	<div class="newlist-sech2" style="width:160px;">
	<a href="#" class="btn-blue2" onfocus="this.blur()" onclick="postPhoto('HI')" style="margin-right: 15px;">拍照</a></div>
</div>
<!-- 原有存储路径设置部分
<div id="carmera2" class="path_box" style="display:none">
  <div><label style="margin: 0px 10px 0px 0px;"><strong>指定目录:</strong></label><input id="orderpath" type="text" style="width: 400px;"/></div>
  <div><span>示例:C:\安芯视频\视频监控\</span><span><a href="#" class="btn-blue2" style="margin-left: 5px;" onfocus="this.blur()" onclick="closedown()" >取消</a><a href="#" class="btn-blue2" onfocus="this.blur()" onclick="savepath()">确定</a></span></div>
</div>
-->
</s:if>
<s:if test="terminalViBean.VIDEO_FACTORY=='DA'">
<div id="camera_boxda" class="camera_box" style="display:none">
	<h2 style=" padding-top:1px;">摄像头：</h2>
	<div class="check_box">
	  <div><span class="position3"><input name="routeway2" type="checkbox" value="1" checked="checked" /></span><span class="position4">整车</span></div>
      <div><span class="position3"><input name="routeway2" type="checkbox" value="2" checked="checked" /></span><span class="position4">路况</span></div>
      <div><span class="position3"><input name="routeway2" type="checkbox" value="3" /></span><span class="position4">车门</span></div>
      <div><span class="position3"><input name="routeway2" type="checkbox" value="4" /></span><span class="position4">驾驶员</span></div>
	</div>
	<div class="list-sech2"><a href="#" class="btn-blue2" onfocus="this.blur()" onclick="postPhoto('DA')">拍照</a></div>
</div>
</s:if>
</div>
<%@include file="newvideoCookie.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.parser.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.autoresize.js" />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<%@ include file="newvideo.jsp"%>

</body>
</html>