<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/flexigrid/flexigrid.css'/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/badDrive.css" />" />
	<style>
		.flexigrid div.bDiv td div .downBtn{ position:absolute; width:16px; height:16px; display:block; padding-left:5px;}
		.flexigrid div.bDiv td div .downBtn .downpress{ display:block; width:16px; height:16px; background:url(../images/replayVideo/icon_download.gif) no-repeat; padding-left:5px;cursor:pointer;}
		.flexigrid div.bDiv td div .downBtn .progress{ position:absolute; left:23px; padding:2px; font-size:10px; color:#333; background-color:#F9F4D0; border:1px #E7BF56 solid; top:-2px; font-family:Arial, Helvetica, sans-serif;display: none;}
		.flexigrid div.bDiv td div .downingBtn{ position:absolute; width:16px; height:16px; display:block; padding-left:5px;}
		.flexigrid div.bDiv td div .downingBtn .downpress{ display:block; width:16px; height:16px; background:url(../images/replayVideo/icon_download_anim.gif) no-repeat;  padding-left:5px;}
		.flexigrid div.bDiv td div .downingBtn .progress{ position:absolute; left:23px; padding:2px; font-size:10px; color:#333; background-color:#F9F4D0; border:1px #E7BF56 solid; top:-2px; font-family:Arial, Helvetica, sans-serif;display: none;}
		.position1{display:block; width:22px; height:22px; float:left; padding-top: 8px;}
		.position2{display:block; width:26px; line-height:23px; float:left;padding-top: 4px;}
		.setpath_iframe{ 
			position:absolute; 
			visibility:inherit; 
			top:0; left:0;
			width:500px;
			height:484px; 
			z-index:-1; 
			filter: Alpha(Opacity=0); 
			border-style: none;
		}
		.setBackgroundModel{ 
			position:absolute;
			background:#000;
			opacity:0.7;
			width:100%;
			height:100%;
			background:url(../newimages/shadow.png);
		}
	</style>
	<script language="javascript" for="ocx" event="OnMessage(lType,lResult,szDescription)">
			switch(lType){
			case 0: //登陆回放结果处理
				//add by suyingtao 20120615 修正现网视频无法连接 
				if(lResult == 0){
				}else{
					alert("0."+szDescription);
				}			
				break;
			case 1:
				tishiShow(szDescription);
				break;	
			case 2: // 视频回放查询
				if(lResult==2){
					var xmlresultDA = szDescription;
					if(xmlresultDA != "" && xmlresultDA != "undefined"){
						var xmldocDA = loadXML(xmlresultDA);
						var pid = xmldocDA.getElementsByTagName("Recordsnfo");
						var videoid = pid[0].getAttribute("cameraID");
						var way = videoid.substring(videoid.length-1);
						var elementsDA = xmldocDA.getElementsByTagName("Record");
						for (var l = 0; l < elementsDA.length; l++) {
							var id = elementsDA[l].getAttribute("id");
							var name = elementsDA[l].getAttribute("name");
							var beginTime = elementsDA[l].getAttribute("beginTime");
							var endTime = elementsDA[l].getAttribute("endTime");
							var sourceType = elementsDA[l].getAttribute("sourceType");
							var recordType = elementsDA[l].getAttribute("recordType");
							var recordLen = elementsDA[l].getAttribute("recordLen");
							var planId = elementsDA[l].getAttribute("planId");
							var ssId = elementsDA[l].getAttribute("ssId");
							var diskId = elementsDA[l].getAttribute("diskId");
							var fileHandle = elementsDA[l].getAttribute("fileHandle");
							DAvideoresultstrs = DAvideoresultstrs+name+","+beginTime+","+endTime+","+sourceType+","+recordType+","+recordLen+","+planId+","+ssId+","+diskId+","+fileHandle+","+ way+"~";
						}
						reloadvideofile(DAvideoresultstrs,"DA");
					}
				}else if(lResult==1){
					tishiShow("视频查询失败！");
				}
				break;
			}
	</script>
</head>
<body onload="photoOnline();showtextshow(); showFactoryDiv(); StartPlayAllBAWindows();" onunload="StopPlayAllWindows()">
<s:hidden id="VIDEO_ID" name="terminalViBeanV.VIDEO_ID" />
<s:hidden id="VIDEO_FACTORY" name="terminalViBeanV.VIDEO_FACTORY" />
<s:hidden id="VIDEO_SERVER_IP" name="terminalViBeanV.VIDEO_SERVER_IP" />
<s:hidden id="VIDEO_USER" name="terminalViBeanV.VIDEO_USER" />
<s:hidden id="VIDEO_PASSWORD" name="terminalViBeanV.VIDEO_PASSWORD" />
<s:hidden id="VEHICLE_LN" name="terminalViBeanV.VEHICLE_LN" />
<input type="hidden" id="m_vidiconId"
	value="<s:property value='videoID'/>" />
<input id="vin" name="vin" type="hidden" value="<s:property value='vin'/>" />
<!-- 不同内容 -->
<div class="popArea" style="width: 100%; height: 100%">
	<s:if test="terminalViBeanV.VIDEO_FACTORY!='YW'">
	<div id="Layer1" class="LayerVideoReplay" style="display: none"><img id="tishitu" align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
	   	<span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
	</div>
	<div class="warn">
	<div class="alert alert-success" style="display: none;">
		<!—关闭按钮-->
	    <a href="javascript:void(0)" class="close-btn" onclick="closedialog()"><span></span></a>
		<!—提示信息-->
	    <span class="text"></span>
	    <!—路径部分-->
		<div class="info">
			<!—路径-->
	        <span class="path-uri"></span>
	           <!—拷贝链接-->
	        <span class="path-copy"><a href="javascript:void(0)" onclick="copePath()">打开路径</a></span>
	    </div>
	</div>
	<h2 style="padding-top: 5px">时间：</h2>
	<table border="0" cellspacing="0" cellpadding="0" height="22">
		<tr>
			<td width="22" align="left" valign="top">
			<div>
				<img onclick="pickedendtime();" src="../newimages/sidelayerimages/arrow_left.png" width="19" height="22" />
			</div>
			</td>
			<td valign="top"> 
			<div>
				<input readonly="readonly" class="date_img" style="height:22px;"
					type="text" size="10" id="line_end_time" name="line_end_time"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
														  autoPickDate:true,
														  maxDate:'%y-%M-%d',
														  isShowClear:false})"
					 value="<s:property value='line_end_time'/>" /> 
			</div>
			</td>
			<td width="22" align="right"  valign="top">
			<div>
				<img onclick="pickedstarttime();" src="../newimages/sidelayerimages/arrow_right.png" width="19" height="22" />
			</div>
			</td>
		</tr>
	</table>
	<div class="line_spacing">
		<h2>通&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;道：</h2>
		<div class="check_box">
			<div><span class="position1"><input name="routeway2" type="checkbox" value="1"
				checked="checked" /></span><span class="position2">整车</span></div>
			<div><span class="position1"><input name="routeway2" type="checkbox" value="2"
				checked="checked" /></span><span class="position2">路况</span></div>
			<div><span class="position1"><input name="routeway2" type="checkbox" value="3" /></span><span class="position2">车门</span></div>
			<div><span class="position1"><input name="routeway2" type="checkbox" value="4" /></span><span class="position2">司机</span></div>
		</div>
		<div class="list-sech2" style="width:80px;float:left">
			<a href="#" class="btn-blue" onfocus="this.blur()" onclick="checkDvrState();" style="margin-right: 5px;">查询</a>
		</div>
		<div class="newlist-sech2" style="width:80px; height:25px; float:right" ><a href="#" onclick="showDownPage()" style="margin-right: 15px;">不能播放？</a></div>
	</div>
</div>
<div id="pathSetting" class="path_box_replay" style="display:none">
  <div><label style="margin: 0px 10px 0px 0px;"><strong>指定目录:</strong></label><input id="orderpath" type="text" style="width: 400px;"/></div>
  <div><span>示例:C:\安芯视频\视频回放\</span><span><a href="#" class="btn-blue2" style="margin-left: 5px;" onfocus="this.blur()" onclick="closedown()" >取消</a><a href="#" class="btn-blue2" onfocus="this.blur()" onclick="savepath()" >确定</a></span></div>
</div>
</s:if>
<!--以下为预留位置-->
<div id="map" style="height:100%;">
	<s:if test="terminalViBeanV.VIDEO_FACTORY=='HI'">
		<object
			classid="clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778"
			codebase="<s:url value='/codebase/ppvsguard.cab' />#version=2,2,0,41683"
			standby="Waiting..." id="PPVSHI1" width="100%" height=<s:property value='selfheight'/>
			name="ocx1" title="1号窗口"> 
		</object>
	</s:if>
	<s:if test="terminalViBeanV.VIDEO_FACTORY=='DA'">
	   	<object id="ocx" width="100%" height=<s:property value='selfheight'/> classid='CLSID:C2F93B39-9404-47A9-B254-2F64753B06DC' 
                                     codebase="<s:url value='/codebase/dssPlay.CAB' />#version=2,1,0,5" >
                                  <param name="nWndNum"  value="1">					
        </object>        
	</s:if>
	<s:if test="selfheight=='400px' && terminalViBeanV.VIDEO_FACTORY=='YW' ">
		<object id="ocxyw"
			width="100%" height="660px;"
			classid="clsid:6A4D7CA5-03B1-4A5B-97C2-A24AE5A87EA9"
			codebase="<s:url value='/codebase/mediacontrol.cab' />#version=8.1.0.8888">
		</object>
	</s:if>
	<s:if test="selfheight=='280px' &&  terminalViBeanV.VIDEO_FACTORY=='YW' ">
		<object id="ocxyw"
			width="100%" height="483px;"
			classid="clsid:6A4D7CA5-03B1-4A5B-97C2-A24AE5A87EA9"
			codebase="<s:url value='/codebase/mediacontrol.cab' />#version=8.1.0.8888">
		</object>
	</s:if>

</div>
<s:if test="terminalViBeanV.VIDEO_FACTORY!='YW'">
<div style="border-top: 1px solid #999999;">
<table id="videoFileList"></table>
</div>
</s:if>
</div>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery.wresize.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/jquery-cookie.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.parser.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.autoresize.js" />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<%@include file="replayvideo/replayvideo_js.jsp"%>
</body>
</html>
<script for=PPVSHI1 event="PopMenuSnapShot">
	SnapShotT();
</script>
<script type='text/javascript'>
function showDownPage(){
	art.dialog.open("<s:url value='/ytstatic/videoplug/videoplugDown.jsp' />",{
		title:"温馨提醒",
		lock:true,
		width:450,
		height:200
	});
}
</script>