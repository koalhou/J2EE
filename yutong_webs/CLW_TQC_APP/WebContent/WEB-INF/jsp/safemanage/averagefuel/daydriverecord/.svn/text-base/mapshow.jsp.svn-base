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
<style>
.noticeMsg{
	color:red;
	font-size:12px;
    font-family:"微软雅黑";
}
</style>
</head>
<body onload="mapInit();" onbeforeunload="closeMap();" onunload="closeWindow();">

<s:hidden id="subVin" name="subVin" value="%{queryObj.VIN}"/>
<s:hidden id="subBeginTime" name="subBeginTime" value="%{queryObj.start_time}"/>
<s:hidden id="subEndTime" name="subEndTime" value="%{queryObj.end_time}"/>

<div id="allDiv"  class="popArea" style="background-color: #F3F3F3;">
<div id="Layer1" class="Layerbitlook" style="display: none">
    <img  src="../newimages/sidelayerimages/right.gif" width="16" height="10" /> 
   	<span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
</div>
<div id="busInfo" style="width: 100%;">
	<table width="100%;">
		<tr>
			<td>
			<p style="font-weight: bold;font-size: 15px;">
			所属组织 ：<s:property value="%{queryObj.organization_name}"/>
			<br />
			回放时段：<s:property value="%{queryObj.start_time}"/>--<s:property value="%{queryObj.end_time}"/>
			</p>
			</td>
		</tr>
	</table>
</div>
<div id="bitiCenter" style="width: 100%; height: 550px;"></div>
<table width="100%" cellpadding="0" cellspacing="0" border="0" >
	<tr>
		<td>
			<div id="slider-range-min" style="height:10px;"></div>
		</td>
	</tr>
</table>
<div class="arr_bar" align="center">
   <div class="arr_bar_l"></div>
    <div class="arr_bar_c">
     <table width="232px" cellpadding="0" cellspacing="0" >
         <tr>
             <td width="38px"><img style="cursor: pointer;" id="tongbu" onclick="replay();" title="重新开始" src="../newimages/arr_first.gif" /></td>
               <td width="38px"><img style="cursor: pointer;" id="kuaitui" onclick="kuaituiOpt();" title="慢速" src="../newimages/arr_pre.gif" /></td>
               <td width="38px"><img style="cursor: pointer;" id="playimg" onclick="clickMove();return false;" title="播放" src="../newimages/arr_play.gif" /></td>
               <td width="38px"><img style="cursor: pointer;" id="jieshu" onclick="stopMove();" title="结束" src="../newimages/arr_stop2.gif" /></td>
               <td width="38px"><img style="cursor: pointer;" id="kuaijin" onclick="kuaijinOpt()" title="快速" src="../newimages/arr_next.gif" /></td>
               <td ><img style="cursor: pointer;" id="weibu" onclick="completeMove();" title="完成播放" src="../newimages/arr_last.gif" /></td>
           </tr>
       </table>
   </div>
   <div class="arr_bar_r"><strong><span  id="bofangbeishu">X2</span></strong></div>
</div>

<div id="arr_bar_mm" style="width: 100%; height: 30px;">
<table id="notalarmtable" width="96%" >
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
	</table>
	</div>
</div>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.parser.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.autoresize.js" />"></script>
<script type="text/javascript" src="<s:url value='/scripts/JQuerySlider/js/jquery-ui-1.7.3.custom.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/JQuerySlider/js/ui.core.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/JQuerySlider/js/ui.slider.js'/>"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<script type="text/javascript">
var mapObj=null;
//加载地图
function mapInit() { 
	
	//var lon = document.getElementById("lon").value;
	//var lat = document.getElementById("lat").value;
	var mapOption = new MMapOptions();   
	mapOption.zoom = 4;//要加载的地图的缩放级别   
	//mapOption.center = new MLngLat(lon,lat);//要加载的地图的中心点经纬度坐标   
	mapOption.center =new MLngLat(113.686, 34.693);
	mapOption.hasDefaultMenu = false;
	mapOption.toolbar = MConstants.SMALL; //设置地图初始化工具条，ROUND:新版圆工具条   *
	mapOption.toolbarPos=new MPoint(5,5); //设置工具条在地图上的显示位置   
	mapOption.overviewMap = MConstants.MINIMIZE; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）   *
	mapOption.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。   *
	mapOption.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标  *
	mapOption.maxZoomLevel=17;
	mapOption.logoUrl = "../newimages/sidelayerimages/mask.png";
	mapOption.groundLogo = "../newimages/sidelayerimages/mapbackgroud.jpg";
	mapOption.language = MConstants.MAP_CN;//设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图   **
	mapOption.fullScreenButton = MConstants.HIDE;//设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏   **
	mapOption.centerCross = MConstants.HIDE;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏   **
	mapOption.requestNum=100;//设置地图切片请求并发数。默认100。   **
	mapOption.isQuickInit=true;//设置是否快速显示地图，true显示，false不显示。   **
	mapObj = new MMap('bitiCenter', mapOption); //地图初始化
 
   	mapObj.setKeyboardEnabled(false);
    //注册地图加载完成事件
    mapObj.addEventListener(mapObj,MConstants.MAP_READY,map_ready);
    
}   
//地图加载完成回调函数
function map_ready(param){  
	var vin=jQuery("#subVin").val();
	var beginTime=jQuery("#subBeginTime").val();
	var endTime=jQuery("#subEndTime").val();
	GPSDwr.getVehcileLineList(vin,beginTime,endTime,drawLine_CallBack);
	//GPSDwr.getSimpleRecord1(vin,beginTime,endTime,drawLine_CallBack);
}

/**
 * 全局坐标点集合，对象集合，对象
 */
var gLnglatArr,gInfoArr,gMarkerId;
//显示全部轨迹
function drawLine_CallBack(array){
	var list=array.data;
	if(list !=null && list.length > 0){
		//有效坐标点集合
		var lnglatArr = new Array();
		//数据对象集合
		var infoArr = new Array();
		if(list[0].gpsIsExc){
			for(var i = 0; i < list.length; i++){
				var lon = list[i].LONGITUDE;
				var lat = list[i].LATITUDE;
				if(lon!= null && lon!=""&& lat!=null && lat!=""&& lon!="FFFF" && lat!="FFFF"&& lon>0&& lat>0&&lon<180 && lat <90){
					infoArr.push(list[i]);
					lnglatArr.push(new MLngLat(lon,lat));
				}
			}
			var effectSize=lnglatArr.length;
			//添加起点覆盖物
			if(effectSize>0){
				addMarker(infoArr[0]);
			}
			//添加轨迹线
			if(effectSize>1){
				var polyline=new MPolyline(lnglatArr);
				polyline.id="LINE";
			    mapObj.addOverlay(polyline,true);
			}
			
		}else{
      		tipsShow("GPS数据偏移异常，请重新尝试或联系地图厂商！");
			tipsHide();
   		}
		//赋给全局变量
		gLnglatArr=lnglatArr;
		gInfoArr=infoArr;
	}else{
		tipsShow("没有轨迹数据！");
		tipsHide();
	}
}
//添加点
function addMarker(obj){
	var markerOption = new MMarkerOptions();
	markerOption.imageUrl="../images/arrow_blue.png"; //lan_1.png";
	markerOption.imageSize = new MSize(14,32);
	markerOption.imageAlign=MConstants.MIDDLE_CENTER;
	var hudu=0;
	if(obj.direction!="FFFF"){
		   hudu = obj.DIRECTION;
	}
	markerOption.rotation = hudu;
	markerOption.picAgent=false;
	markerOption.isEditable=false; //设置点是否可编辑。
	markerOption.hasShadow=false;  //是否显示阴影。	
	//markerOption.zoomLevels=[]; //设置点的缩放级别范围。
	markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
	markerOption.dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
	var marker = new MMarker(new MLngLat(obj.LONGITUDE,obj.LATITUDE),markerOption);
	marker.id=obj.VEHICLE_VIN;
	mapObj.addOverlay(marker,true);
	//赋予全局变量
	gMarkerId=obj.VEHICLE_VIN;
}
//move along
//播放
//刷新覆盖物
var pointIndex=0;
//播放状态,0：播放,1：暂停
var playstate=1;
//点击播放按钮
function clickMove(){
	if(playstate==0){
		playstate=1;
	}else{
		playstate=0;
	}
	//开始播放
	startMove();
}
function refreshBus(){
	if(pointIndex>=gLnglatArr.length){//完成播放
		completeMove();
	}else if(playstate==1){//暂停
		pauseMove();
	}else if(playstate==0){//播放
		startMove();
	}
}
//开始播放
var timer;
var maxTimer=2000;
var multiple=2;
function startMove(){
	
	document.getElementById("playimg").src="../newimages/arr_stop.gif";
	document.getElementById("playimg").title="暂停";
	
	var delay=maxTimer/Math.pow(2,multiple);
	jQuery("#slider-range-min").slider( "option", "max", gLnglatArr.length-1 );
	jQuery("#slider-range-min").slider( "option", "value", pointIndex );
	mapObj.markerMoveTo(gMarkerId,gLnglatArr[pointIndex], genDirection(gInfoArr[pointIndex].DIRECTION), 0);
	//alert(gInfoArr[pointIndex].TERMINAL_TIME);
	//terminalInfo.dingwei_stat);
	
	//terminalInfo.speeding);
	//terminalInfo.direction);
	//转速
	//document.getElementById("alarmENGINE_ROTATE_SPEED").innerHTML=nullToZore(tt.ENGINE_ROTATE_SPEED)+"&nbsp;rpm";
	//terminalInfo.STAT_INFO==0);
	//承载情况
	//terminalInfo.limite_number);//nullToZore(tt.STU_NUM)+"/"+
	//瞬时油耗
	//terminalInfo.OIL_INSTANT);
	refreshValue(gInfoArr[pointIndex]);
	
	pointIndex++;
	timer=window.setTimeout("refreshBus()",delay);
}
//获取角度
function genDirection(direction){
    var hudu = 0;
	if (direction != "FFFF") {
		hudu = direction;
	}
	return hudu;
}
//暂停
function pauseMove(){
	window.clearInterval(timer);
	document.getElementById("playimg").src="../newimages/arr_play.gif";
	document.getElementById("playimg").title="播放";
}
//结束
function stopMove(){  
	window.clearInterval(timer);
	//播放结束
	playstate=1;
	pointIndex = 0;
	jQuery("#slider-range-min").slider( "option", "value", pointIndex );
	mapObj.markerMoveTo(gMarkerId,gLnglatArr[pointIndex], genDirection(gInfoArr[pointIndex].DIRECTION), 0);
	document.getElementById("playimg").src="../newimages/arr_play.gif";
	document.getElementById("playimg").title="播放";
	
	//更新状态值
	refreshValue(gInfoArr[pointIndex]);
}
//完成播放
function completeMove(){
	//playstate=1;
	window.clearInterval(timer);
	pointIndex=gLnglatArr.length-1;
	jQuery("#slider-range-min").slider( "option", "max", gLnglatArr.length-1 );
	jQuery("#slider-range-min").slider( "option", "value", pointIndex );
	mapObj.markerMoveTo(gMarkerId,gLnglatArr[pointIndex], genDirection(gInfoArr[pointIndex].DIRECTION), 0);
	document.getElementById("playimg").src="../newimages/arr_play.gif";
	document.getElementById("playimg").title="播放";
	
	//更新状态值
	refreshValue(gInfoArr[pointIndex]);
	
	tipsShow('完成播放');
	tipsHide();
	
	//播放器完成播放后回到起点,结束播放,为了保持和以前播放一致性,后来新增v20140624
	window.setTimeout("stopMove()",300);
}
//重新播放
function replay(){
	pointIndex=0;
	playstate=0;
	startMove();
}
//更新显示的值
function refreshValue(terminalInfo){

	//播放时间
	//document.getElementById("bofangtime").innerHTML=timeoptval(terminalInfo.terminal_time);
	document.getElementById("bofangtime").innerHTML=timeoptval(terminalInfo.TERMINAL_TIME);
	//瞬时油耗
	document.getElementById("DINGWEI_STAT").innerHTML=(terminalInfo.dingwei_stat!=0?'有效':'无效');
	//车速
	document.getElementById("SPEEDING").innerHTML=nullToZore(terminalInfo.SPEEDING)+"&nbsp;km/h";
	//方向
	document.getElementById("DIRECTION").innerHTML=diretionToStr(terminalInfo.DIRECTION);
	//转速
	//document.getElementById("alarmENGINE_ROTATE_SPEED").innerHTML=nullToZore(tt.ENGINE_ROTATE_SPEED)+"&nbsp;rpm";
	//点火状态
	document.getElementById("FIRE_UP_STATE").innerHTML=(terminalInfo.STAT_INFO==0?'关':'开');
	//承载情况
	document.getElementById("LIMITE_NUMBER").innerHTML=nullToZore(terminalInfo.LIMITE_NUMBER)+"人";//nullToZore(tt.STU_NUM)+"/"+
	//瞬时油耗
	//document.getElementById("oil_instant").innerHTML=nullToZore(terminalInfo.OIL_INSTANT)+"&nbsp;L";
}
function trim(v){
	return v.replace(/^\s+|\s+$/g, '');
}
function nullToZore(str){
	if(str == null || str == "" ||  str == "undefined" || str == " " ||str =="FFFF"){
		return 0;
	}
	else{
		return str;
	}
}
function timeoptval(time){
	if(time != null && trim(time) != "" && trim(time) != "undefined"){
		return time.substring(10, time.length-2);
	}
	else{
		return "0";
	}
}
function diretionToStr(str){
	//alert("方向："+str);
	if(str == null || str == "" || str == "undefined" || str == " "){
		return "无";
	}
	else if((str>=0 && str <10) || (str >=350 && str<=360)){
		return "北";
	}
	else if(str>=10 && str <80){
		return "东北";
	}
	else if(str>=80 && str<100){
		return "东";
	}
	else if(str>=100 && str < 170){
		return "东南";
	}
	else if(str>=170 && str < 190){
		return "南";
	}
	else if(str>=190 && str < 260){
		return "西南";
	}
	else if(str>=260 && str < 280){
		return "西";
	}
	else if(str>=280 && str < 350){
		return "西北";
	}else{
		return "北";
	}
}
//快进
function kuaijinOpt(){
	if(multiple==16){
		return ;
	}
	multiple=multiple*2;
	jQuery('#bofangbeishu').text('X'+multiple);
}
//快退
function kuaituiOpt(){
	if(multiple==2){
		return ;
	}
	multiple=multiple/2;
	jQuery('#bofangbeishu').text('X'+multiple);
}
function tipsShow(info){
	hideTipsShow(0);
	document.getElementById("inforeault").innerHTML=info;
}
function tipsHide(){
	window.setTimeout("hideTipsShow(1)",2000);
}
function hideTipsShow(flag){
	if(flag==1){
		jQuery('#Layer1').css('display','none');
	}
	else if(flag==0){
		jQuery('#Layer1').css('display','block');
	}
}
//关闭地图
function closeMap(){
	mapObj=null;
	document.getElementById("bitiCenter").innerHTML = "";
	gLnglatArr=null;
	gInfoArr=null;
}
function closeWindow(){
	window.clearInterval(timer);
}
function styleControl(){
	 var popWidth ="500px";
	 var popMaxWidth ="655px";
	 var popHeight = "490px";
	 var popMaxHeiht = "655px";
	if(jQuery(window.parent.iframeshowArea).width() > 500){
		//页面最外层div大小
		jQuery('#allDiv').width(popMaxWidth);
		jQuery('#allDiv').height(popMaxHeiht);
		jQuery('#bitiCenter').height("460px");
	}else{
		jQuery('#allDiv').width(popWidth);
		jQuery('#allDiv').height(popHeight);
		jQuery('#bitiCenter').height("300px");
	}
}
jQuery(window.parent.iframeshowArea).resize(function(){
	styleControl();
});
/**
 * js入口
 */
jQuery(document).ready(function(){
	//mapInit();
	//播放快进条
	pointIndex = 0;
	jQuery("#slider-range-min").slider({
		animate: true ,
		range: "min",
		value: 0,
		min: 0,
		max: 100,
		slide: function(event, ui) {
			pointIndex = ui.value;
		},
		start: function(event, ui){
			
		}
	});
	jQuery("#slider-range-min").slider( "option", "value", pointIndex );
	jQuery("#slider-range-min").slider( "option", "disabled", false);
	
	styleControl();
});
</script>
</body>
</html>