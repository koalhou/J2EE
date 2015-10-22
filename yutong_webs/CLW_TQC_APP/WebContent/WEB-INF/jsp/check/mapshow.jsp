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
<body onload="mapInit();" onunload="closeMap();">

<s:hidden id="subVin" name="subVin" value="%{alarmShow.vehicle_vin}"/>
<s:hidden id="subAlarmId" name="subAlarmId" value="%{alarmShow.alarm_id}"/>
<s:hidden id="subBeginTime" name="subBeginTime" value="%{alarmShow.alarm_time}"/>
<s:hidden id="subEndTime" name="subEndTime" value="%{alarmShow.alarm_end_time}"/>

<div id="allDiv" style="background-color: #F3F3F3;">
<div id="Layer1" class="Layerbitlook" style="display: none">
    <img  src="../newimages/sidelayerimages/right.gif" width="16" height="10" /> 
   	<span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
</div>
<div id="busInfo" style="width: 100%; height: 50px;">
	<table width="100%">
		<tr>
			<td height="25px;">
			时间：<s:property value="%{alarmShow.alarm_time}"/>至<s:property value="%{alarmShow.alarm_end_time}"/>
			时长：<s:property value="%{alarmShow.use_time}"/> (min)
			里程：<s:property value="%{alarmShow.mileage}"/> (km)
			</td>
		</tr>
		<tr>
			<td>
			上报时间：<input id='uptime' type='text' readonly="readonly" style="background:#F3F3F3;"/>
			定位状态：<input id='state' type='text' readonly="readonly" size='10' style="background:#F3F3F3;"/>
			行驶速度：<input id='speeding' type='text' readonly="readonly" size='10' style="background:#F3F3F3;"/>
			</td>
		</tr>
	</table>
</div>
<div id="bitiCenter" style="width: 100%; height: 290px;"></div>
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
<div id="subMsgDiv" style="height:130px;">
	<table width="500px;"  align="center">
		<tr>
		<td>
		<span class="noticeMsg">*</span>是否为公车私用:
		<s:if test="%{alarmShow.operate_type != null && alarmShow.operate_type!=''}">
			<input type="radio" name="rtype" id="rtype" value="0" <s:if test="%{alarmShow.operate_type==0}">checked="checked"</s:if> />公车私用
			<input type="radio" name="rtype" id="rtype" value="1" <s:if test="%{alarmShow.operate_type==1}">checked="checked"</s:if>/>正常用车
		</s:if>
		<s:else>
			<input type="radio" name="rtype" id="rtype" value="0" checked="checked" />公车私用
			<input type="radio" name="rtype" id="rtype" value="1" />正常用车
		</s:else>
		</td>
		</tr>
		<tr>
		<td>
		<s:if test="%{alarmShow.deal_flag==0}">
		<textarea id="desc" rows="3" cols="47" onfocus="javascript:if(this.value=='处理意见(必填)')this.value='';this.style.color='#323232';" onblur="javascript:if(this.value=='') this.value='处理意见(必填)';this.style.color='#D2D2D2';" style="color: #D2D2D2;">处理意见(必填)</textarea>
		</s:if>
		<s:else>
		<textarea id="desc" rows="3" cols="47" disabled="disabled"><s:property value="%{alarmShow.opeerate_desc}"/></textarea>
		</s:else>
		</td>
		</tr>
		<tr>
		<td>
		<span style="color: #D2D2D2;float:left">不超过<font size="3">50</font>个字</span>
		<s:if test="%{alarmShow.deal_flag==0}">
		<a class="btnbule" onfocus="this.blur()" href="javascript:void(0)" onclick="subBatchOperateOk();" style="float: right;">处理</a>
		</s:if>
		</td>
		</tr>
	</table>
</div>
</div>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.parser.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.autoresize.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.sidelayer.js" />"></script>
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
	//GPSDwr.getVehcileLineList(vin,beginTime,endTime,drawLine_CallBack);
	GPSDwr.getSimpleRecord(vin,beginTime,endTime,drawLine_CallBack);
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
				var lon = list[i].longitude;
				var lat = list[i].latitude;
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
		   hudu = obj.direction;
	}
	markerOption.rotation = hudu;
	markerOption.picAgent=false;
	markerOption.isEditable=false; //设置点是否可编辑。
	markerOption.hasShadow=false;  //是否显示阴影。	
	//markerOption.zoomLevels=[]; //设置点的缩放级别范围。
	markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
	markerOption.dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
	
	var marker = new MMarker(new MLngLat(obj.longitude,obj.latitude),markerOption);
	marker.id=obj.vehicle_vin;
	mapObj.addOverlay(marker,true);
	//赋予全局变量
	gMarkerId=obj.vehicle_vin;
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
	mapObj.markerMoveTo(gMarkerId,gLnglatArr[pointIndex], genDirection(gInfoArr[pointIndex].direction), 0);
	//更新状态值
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
	mapObj.markerMoveTo(gMarkerId,gLnglatArr[pointIndex], genDirection(gInfoArr[pointIndex].direction), 0);
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
	mapObj.markerMoveTo(gMarkerId,gLnglatArr[pointIndex], genDirection(gInfoArr[pointIndex].direction), 0);
	document.getElementById("playimg").src="../newimages/arr_play.gif";
	document.getElementById("playimg").title="播放";
	
	//更新状态值
	refreshValue(gInfoArr[pointIndex]);
	
	tipsShow('完成播放');
	tipsHide();
	
	//结束播放,为了保持和以前播放一致性,后来新增
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
	jQuery('#uptime').val(terminalInfo.terminal_time);
	jQuery('#state').val(terminalInfo.dingwei_stat !=0 ? '有效':'无效');
	jQuery('#speeding').val(terminalInfo.speeding);
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
//指向最新列表的第一行数据
function pointNextrow(){
	//刷新列表页面
	window.parent.window.illSearchList();
	var nextrow="";
	//查询一条未处理的记录
	var vins=window.parent.window.document.getElementById('vehicleVin').value;
	var beginTime=window.parent.window.document.getElementById('beginTime').value;
	var endTime=window.parent.window.document.getElementById('endTime').value;
	jQuery.ajax({
		type:'post',
		url:'<s:url value="../checking/getNextRow.shtml" />',
		data:{'searchVO.vins':vins,'searchVO.beginTime':beginTime,'searchVO.endTime':endTime,'searchVO.operate_state':'0'},
		async:false,
		success:function(data){
			nextrow=data;
		}
	});
	//如果存在下一条未处理的异常记录显示，否则刷新整个页面并关闭弹出窗口
	if(nextrow.length>0){
		var cols=nextrow.split(',');
		window.parent.showMap(cols[0],cols[1],cols[2]);
	}else{
		window.parent.location.href="../checking/vehicleCheckManage.shtml";
	}
}
//意见处理提交
function subBatchOperateOk(){
	
	var ids=jQuery("#subAlarmId").val();
	var type=jQuery("input:radio[name='rtype']:checked").val();
	var desc=jQuery.trim(jQuery('#desc').val());
	if(type==null){
		tipsShow('请选择是否为公车私用!');
		tipsHide();
		return false;
	}
	if(desc=='' || desc==null || desc=='处理意见(必填)'){
		tipsShow('请填写处理意见!');
		tipsHide();
		return false;
	}
	if(desc.length>50){
		tipsShow('处理意见不能超过50字!');
		tipsHide();
		return false;
	}
// 	jQuery.post("../checking/batchOperate.shtml",{'ids':ids,'type':type,'desc':desc},function(data){
// 		tipsShow(data);
// 		tipsHide();
// 	});	
	jQuery.ajax({
		type:'post',
		url:'<s:url value="../checking/batchOperate.shtml" />',
		data:{'ids':ids,'type':type,'desc':desc},
		async:false,
		success:function(data){
			tipsShow(data);
			tipsHide();
		}
	});
	//window.parent.location.href="../checking/vehicleCheckManage.shtml";
	//指向最新的列表的第一行数据
	pointNextrow();
}
function styleControl(){
	 var popWidth ="500px";
	 var popMaxWidth ="655px";
	 var popHeight = "484px";
	 var popMaxHeiht = "660px";
	if(jQuery(window.parent.iframeshowArea).width() > 500){
		//页面最外层div大小
		jQuery('#allDiv').width(popMaxWidth);
		jQuery('#allDiv').height(popMaxHeiht);
		jQuery('#bitiCenter').height("460px");
	}else{
		jQuery('#allDiv').width(popWidth);
		jQuery('#allDiv').height(popHeight);
		jQuery('#bitiCenter').height("285px");
	}
}
jQuery(window.parent.iframeshowArea).resize(function(){
	styleControl();
});
/**
 * 修改
 */
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