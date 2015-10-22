<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>


<html >

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title></title>



<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>


<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>

<style  type="text/css"> 
html, body{ margin:0; padding:0; border:none; font:12px/18px  'Microsoft Yahei','微软雅黑',Arial,Verdana;}
</style>

<style media="print" type="text/css"> 
.Noprint{display:none;} 
.PageNext{page-break-after: always;} 
@media print{
.titleBg{background:#666;}
}
</style>

</head>

<body onLoad="initInfo();mapInit('map')" onunload="" onbeforeunload="" style="margin:0; padding:0; border:none; font:12px/18px  'Microsoft Yahei','微软雅黑',Arial,Verdana;">
<input id="lon" name="lon" type="hidden" value="<s:property value='terminalViBean.LONGITUDE'/>" />
<input id="lat" name="lat" type="hidden" value="<s:property value='terminalViBean.LATITUDE'/>" />

<input id="VEHICLE_VIN" name="VEHICLE_VIN" type="hidden" value="<s:property value='terminalViBean.VEHICLE_VIN'/>" />
<input id="VEHICLE_LN" name="VEHICLE_LN" type="hidden" value="<s:property value='terminalViBean.VEHICLE_LN'/>" />
<input id="ROUTE_ID" name="ROUTE_ID" type="hidden" value="<s:property value='terminalViBean.ROUTE_ID'/>" />
<input id="ROUTE_NAME" name="ROUTE_NAME" type="hidden" value="<s:property value='terminalViBean.ROUTE_NAME'/>" />
<input id="DRIVER_ID" name="DRIVER_ID" type="hidden" value="<s:property value='terminalViBean.DRIVER_ID'/>" />
<input id="DRIVER_NAME" name="DRIVER_NAME" type="hidden" value="<s:property value='terminalViBean.DRIVER_NAME'/>" />
<input id="load_alarm_event" name="load_alarm_event" type="hidden" value="<s:property value='terminalViBean.load_alarm_event'/>" />
<input id="selectModel" name="selectModel" type="hidden" value="<s:property value='terminalViBean.selectModel'/>" />
<input id="TRIP_ID" name="TRIP_ID" type="hidden" value="<s:property value='terminalViBean.TRIP_ID'/>" />
<input id="START_TIME" name="START_TIME" type="hidden" value="<s:property value='terminalViBean.START_TIME'/>" />
<input id="END_TIME" name="END_TIME" type="hidden" value="<s:property value='terminalViBean.END_TIME'/>" />
<input id="pointIndex" name="pointIndex" type="hidden" value="<s:property value='terminalViBean.pointIndex'/>" />
<object id="WebBrowser" classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height="0" width="0"> 
</object>

<div style="background-color: white; width: 98%; height: 710;" >
	<div style="width: 100%; height: 100px; float: left;">
	<div style="float: left; width: 250px;height: 100px; background-color: white;">
		<s:if test="''==#session.adminProfile.img_path || null==#session.adminProfile.img_path">
		    <img src="../newimages/printlogo.png" alt="" />
	    </s:if> 
	    <s:else>
		    <img src="..<s:property value="#session.adminProfile.img_path" />" style="filter: Alpha(opacity = 100, finishOpacity = 0, style = 3)" />
	    </s:else>
	</div>
	<div id="printdiv1" class="Noprint" style="float: right;margin-top: 15px;margin-right: 10px;"> 
		<a   onfocus="this.blur()"  style="width: 69px;height: 29px;float: right;margin-right: 10px;cursor: pointer;" onclick="window.close();" >
		<img src="../newimages/printClose.png" alt="" />
		</a>
		<a   onfocus="this.blur()" style="width: 69px;height: 29px;float: right;margin-right: 10px;cursor: pointer;" onclick="printpage();" >
		<img src="../newimages/printbutton.png" alt="" />
		</a>
		
	</div>
	</div>
	<div style="float: left;width: 100%;height: 580px;border: 1px solid #b4b4b4;margin: 0 5px 0 5px;">
		<div id="infodiv" style="float: left; width: 100%;height: 65px;background-color: #f1f1f1;border-bottom: 1px solid #b4b4b4;" class="titleBg" >
			<table width="100%">
				<tr>
					<td align="center" width="100%">
						<table style="font:12px  'Microsoft Yahei','微软雅黑',Arial,Verdana;font-weight: bold;" cellpadding="5px">
							<tr>
								<td align="left" width="150px;" >
									<span>车牌号：</span> <label id="vln" ></label>
								</td>
								<td id="driverTd" align="left" width="150px;" style="display: none;">
									<span>司机：</span> <label id = "vdrver"></label>
								</td>
								<td id="routeTd" align="left" width="250px;" style="display: none;text-align: center" >
									<span>行驶线路：</span> <label id ="vroundname"></label>
								</td>
							</tr>
							<tr>
								<td colspan="3" align="left" width="450px;" >
									<span>起止时间：</span> <label id="vsetime"></label>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<div id = "map" style="float: left; width: 100%;height: 515px;">
		</div>
	</div>
	<div id="printdiv2" class="Noprint" style="float: right;margin-top: 9px;margin-right: 10px;">
		<a   onfocus="this.blur()"  style="width: 69px;height: 29px;float: right;margin-right: 10px;cursor: pointer;" onclick="printpage();" >
		<img src="../newimages/printbutton.png" alt="" />
		</a>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/common/key2.jsp"%>
<script type="text/javascript">
//加载地图对象
var mapObj = null; 
//自适应zoom数组
var arrForFitView=new Array();
//加载地图
function mapInit(divid) { 
	
	var lon = document.getElementById("lon").value;
	var lat = document.getElementById("lat").value;
	
	var mapoption = new MMapOptions();   
	mapoption.zoom = 4;//要加载的地图的缩放级别   
	mapoption.center = new MLngLat(lon,lat);//要加载的地图的中心点经纬度坐标   
	mapoption.hasDefaultMenu = false;
	mapoption.toolbar = MConstants.SMALL; //设置地图初始化工具条，ROUND:新版圆工具条   *
	mapoption.toolbarPos=new MPoint(5,5); //设置工具条在地图上的显示位置   
	mapoption.overviewMap = MConstants.MINIMIZE; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）   *
	mapoption.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。   *
	mapoption.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标  *
	mapoption.maxZoomLevel=17;
	mapoption.logoUrl = "../newimages/sidelayerimages/mask.png";
	mapoption.groundLogo = "../newimages/sidelayerimages/mapbackgroud.jpg";
	mapoption.language = MConstants.MAP_CN;//设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图   **
	mapoption.fullScreenButton = MConstants.HIDE;//设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏   **
	mapoption.centerCross = MConstants.HIDE;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏   **
	mapoption.requestNum=100;//设置地图切片请求并发数。默认100。   **
	mapoption.isQuickInit=true;//设置是否快速显示地图，true显示，false不显示。   **
	mapObj = new MMap(divid, mapoption); //地图初始化
 
   	mapObj.setKeyboardEnabled(false);
    
    mapObj.addEventListener(mapObj,MConstants.MAP_READY,map_ready);
    
}   
function map_ready(param){  
	initline();
	
}

function printpage(){
	//factory.printing.header = "This is MeadCo" ;
	//factory.printing.footer = "Advanced Printing by ScriptX" ;
			
	//document.getElementById("printdiv1").style.display = "none";
	//document.getElementById("printdiv2").style.display = "none";
	//document.all.WebBrowser.ExecWB(6,6);
	document.all.WebBrowser.ExecWB(7,1);
}

function initInfo(){
	if(document.getElementById("VEHICLE_LN").value != null && document.getElementById("VEHICLE_LN").value != ""){
		document.getElementById("vln").innerHTML = document.getElementById("VEHICLE_LN").value;
	}
	if(document.getElementById("DRIVER_NAME").value != null && document.getElementById("DRIVER_NAME").value != "" && document.getElementById("DRIVER_NAME").value != "null"){
		jQuery("#driverTd").css("display","block");
		jQuery("#driverTd").width("150px");
		document.getElementById("vdrver").innerHTML = document.getElementById("DRIVER_NAME").value;
	} else {
		jQuery("#driverTd").css("display","none");
		jQuery("#driverTd").width("0px");
	}
	if(document.getElementById("ROUTE_NAME").value != null && document.getElementById("ROUTE_NAME").value != "" && document.getElementById("ROUTE_NAME").value != "null"){
		jQuery("#routeTd").css("display","block");
		jQuery("#routeTd").width("250px");
		document.getElementById("vroundname").innerHTML = document.getElementById("ROUTE_NAME").value;
	} else {
		jQuery("#routeTd").css("display","none");
		jQuery("#routeTd").width("0px");
	}
	if(document.getElementById("START_TIME").value != null && document.getElementById("START_TIME").value != ""){
		document.getElementById("vsetime").innerHTML = document.getElementById("START_TIME").value + "至" + document.getElementById("END_TIME").value;
	}
}
var terminalinfolist = null;
var Errorterminalinfolist = null;

/**
 * 加载路线
 */
function initline(){
	var vin = document.getElementById("VEHICLE_VIN").value;
	var startt = document.getElementById("START_TIME").value;
	var endt = document.getElementById("END_TIME").value;
	GPSDwr.getVehcileLineList(vin,startt,endt,getpostselectline);
	
}
function getpostselectline(array){
	//alert("fff");
	terminalinfolist = array.data; 
	Errorterminalinfolist = array.errdata;
	//mapObj.removeAllOverlays();
	var pointIndex = document.getElementById("pointIndex").value;

	if(terminalinfolist != null && terminalinfolist.length>0){
		drawLine(terminalinfolist,pointIndex);

		var selectModel = document.getElementById("selectModel").value;
		if(selectModel=="3"){
			//idinfo 格式 TRIP_ID + "-" + VIN + "-" + START_TIME+"-" + END_TIME;
			var VEHICLE_VIN = document.getElementById("VEHICLE_VIN").value;
			var TRIP_ID = document.getElementById("TRIP_ID").value;
			var START_TIME = document.getElementById("START_TIME").value;
			var END_TIME = document.getElementById("END_TIME").value;
			var day = START_TIME.substring(0,10);
			var st1 = START_TIME.substring(11);
			var en1 = END_TIME.substring(11);
			//alert(TRIP_ID+"-"+VEHICLE_VIN+"-"+st1+"-"+en1);
			GPSDwr.getRunLineSiteList(TRIP_ID+"-"+VEHICLE_VIN+"-"+st1+"-"+en1,day,getRunLineSiteList_callback);
		}

		

		//if(load_alarm_event.value=="true"){
		if(load_alarm_event.value.length > 0){
			if(Errorterminalinfolist != null && Errorterminalinfolist.length >0){
				drawErrorPoint(Errorterminalinfolist);
			}
		}
	}
}
function getRunLineSiteList_callback(data){
	//mapObj.removeAllOverlays();
	if(data != null || data.length > 0 ){
		
		showSiteListOnMap(data);
	}
	/*
	else{
		tishiShow("无站点信息！");
		tishiHide();
	}
	*/
}
/**
 * 加载告警点
 */
function initalarm(){
	
}

/**
 * 加载站点
 */
function initsite(){
	
}


//显示全部轨迹
function drawLine(terminalinfolist,pointIndex){
	//alert("llllllllllllls"+terminalinfolist.length);
	//mapObj.removeAllOverlays();
	var terminalinfo = null;
	if(terminalinfolist !=null && terminalinfolist.length > 1){
		var arr = new Array();
		var len = terminalinfolist.length;
		if(terminalinfolist[0].gpsIsExc){
			
				for(var i = 0; i < terminalinfolist.length; i++){
		
					 var lon = terminalinfolist[i].LONGITUDE;
					 var lat = terminalinfolist[i].LATITUDE;
					 //alert(lon+"-"+lat);
					 //alert(lat);
					if(lon!= null && lon!=""&& lat!=null && lat!=""&& lon!="FFFF" && lat!="FFFF"&& lon>0&& lat>0&&lon<180 && lat <90){
						
						/*if(terminalinfolist[i].color=="r"){
							errorArr.push(i);
						}*/
						arr.push(new MLngLat(lon,lat));
						
					}
					
					if(i == 0){
						var markerOption = new MMarkerOptions();
						markerOption.imageUrl="../images/start_route.png";
						markerOption.imageSize = new MSize(16,16);
					     markerOption.imageAlign=MConstants.MIDDLE_CENTER;
					     markerOption.rotation = "0";

					     markerOption.picAgent = false;
						 markerOption.isEditable=false; //设置点是否可编辑。
						 markerOption.hasShadow=false;  //是否显示阴影。	
						 markerOption.zoomLevels=[]; //设置点的缩放级别范围。
						 markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
						 markerOption.dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
						 
					     var marker = new MMarker(new MLngLat(lon,lat),markerOption);
						 marker.id="start_0";
						 mapObj.addOverlay(marker,false);
					} else if(len-i == 1){
						var markerOption = new MMarkerOptions();
						markerOption.imageUrl="../images/end_route.png";
						markerOption.imageSize = new MSize(16,16);
					     markerOption.imageAlign=MConstants.MIDDLE_CENTER;
					     markerOption.rotation = "0";

					     markerOption.picAgent = false;
						 markerOption.isEditable=false; //设置点是否可编辑。
						 markerOption.hasShadow=false;  //是否显示阴影。	
						 markerOption.zoomLevels=[]; //设置点的缩放级别范围。
						 markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
						 markerOption.dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
						 
					     var marker = new MMarker(new MLngLat(lon,lat),markerOption);
						 marker.id="end_0";
						 mapObj.addOverlay(marker,false);
					}
					    
				}

				if(arr.length >0){
					var polyline=new MPolyline(arr);
					
					polyline.id="LINE";
				    mapObj.addOverlay(polyline,false);
				    setFitV(polyline.id,arrForFitView);
				    
			    	//var arrayterminalinfo = new Array();
				    //arrayterminalinfo.push(terminalinfolist[pointIndex]);
				    //addmarkerObjNew(arrayterminalinfo,0);
					
				   
				}
		}
	}
	else if(terminalinfolist !=null && terminalinfolist.length == 1){
		if(terminalinfolist[0].gpsIsExc){
			var lon = terminalinfolist[0].LONGITUDE;
			 var lat = terminalinfolist[0].LATITUDE;
			if(lon!= null && lon!=""&& lat!=null && lat!="" && lon!="FFFF"&& lat!="FFFF" && lon>0&& lat>0&&lon<180 && lat <90){
				var arrayterminalinfo = new Array();
			    arrayterminalinfo.push(terminalinfo);
			    addmarkerObj(arrayterminalinfo,true);
			    
			    document.getElementById("bofangtime").innerHTML=timeoptval(terminalinfo.TERMINAL_TIME);
			}
			else{
				//alert("GPS数据异常");
			}
		}	
	}
}


function addmarkerObjNew(array,index){
	  //mapObj.removeOverlaysByType(MOverlay.TYPE_MARKER);
	  var arr = new Array();
	   if(array != null && array.length > 0){
		   var ok = 0;
		   
		   //if(array[0].gpsIsExc){
			   for(var i=0; i < array.length;i++){
	
				   var lon = array[i].LONGITUDE;
				   var lat = array[i].LATITUDE;

			     var markerOption = new MMarkerOptions();
			     markerOption.imageUrl="../images/arrow_blue.gif";
			     markerOption.imageSize = new MSize(14,32);
			     markerOption.imageAlign=MConstants.MIDDLE_CENTER;
			     if(array[i].DIRECTION!="FFFF"){
					   hudu = array[i].DIRECTION;
				 }
			     markerOption.rotation = hudu;
			     
			     markerOption.picAgent = false;
				 markerOption.isEditable=false; //设置点是否可编辑。
				 markerOption.hasShadow=false;  //是否显示阴影。	
				 markerOption.zoomLevels=[]; //设置点的缩放级别范围。
				 markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
				 markerOption. dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
			     
			     var marker = new MMarker(new MLngLat(lon,lat),markerOption);
				 marker.id="marker";
				 
				 mapObj.addOverlay(marker,false);
				 //MapMoveToPoint(lon,lat);
				   	
			}

	   }
	  
	   

}

function addmarkerObj(array,flagg){
	  //mapObj.removeOverlaysByType(MOverlay.TYPE_MARKER);
	  var arr = new Array();
	   if(array != null && array.length > 0){
		   var ok = 0;
		   
		   //if(array[0].gpsIsExc){
			   for(var i=0; i < array.length;i++){
	
				   var lon = array[i].LONGITUDE;
				   var lat = array[i].LATITUDE;
	
				   //alert(lon +"="+lat);
	
				  
				   
					// 创建点
					if(lon!= null && lon!=""&& lat!=null && lat!=""&& lon!="FFFF" && lat!="FFFF" && lon>0&& lat>0&&lon<180 && lat <90){
						 //点的属性设置
						   var markerOption = new MMarkerOptions(); 
						   
						   markerOption.imageUrl="../images/arrow_blue.gif"; //lan_1.png";
						   markerOption.imageSize = new MSize(14,32);
						   markerOption.imageAlign=MConstants.MIDDLE_CENTER;

						   
						   
							if(array[i].DIRECTION!="FFFF"){
								   hudu = array[i].DIRECTION;
							}
						   
						   markerOption.rotation = hudu;

						   markerOption.picAgent=false;
							markerOption.isEditable=false; //设置点是否可编辑。
							markerOption.hasShadow=false;  //是否显示阴影。	
							markerOption.zoomLevels=[]; //设置点的缩放级别范围。
							markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
							markerOption. dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
						   
						   var marker = new MMarker(new MLngLat(lon,lat),markerOption);
						   marker.id=array[i].VEHICLE_VIN;
	
						  
						   mapObj.addOverlay(marker,false);
						   
						   if(flagg){
							   //mapObj.addEventListener(marker,MOUSE_CLICK,clickMouse);
							   }
						   //setFitV(marker.id,arrForFitView);
						   ok = ok+1;
					}	   
			   }
	   }  
}
function MapMoveToPoint(lon,lat){
	var bounds=mapObj.getLngLatBounds();
	//alert(bounds.southWest.lngX+","+bounds.southWest.latY+";"+bounds.northEast.lngX+","+bounds.northEast.latY);

	if(lon < bounds.southWest.lngX || lat < bounds.southWest.latY || lon > bounds.northEast.lngX || lat > bounds.northEast.latY){
		mapObj.panTo(new MLngLat(lon,lat));
	}
}

//异常点标记
function drawErrorPoint(Errorterminalinfolist){

	if(Errorterminalinfolist != null && Errorterminalinfolist.length > 0){
		//alert(Errorterminalinfolist.length);
		for(var i = 0; i < Errorterminalinfolist.length; i++){
			var arrayterminalinfo = new Array();
		    arrayterminalinfo.push(Errorterminalinfolist[i]);
		    //alert("111");
		    addmarkerErrObj(arrayterminalinfo,i);
		}
	}
}

function addmarkerErrObj(array,idx){
	  //mapObj.removeOverlaysByType(MOverlay.TYPE_MARKER);
	  var arr = new Array();
	   if(array != null && array.length > 0){
		   var ok = 0;
		   	   var boxStr = jQuery("#load_alarm_event").val();
			   for(var i=0; i < array.length;i++){
	
				 var lon = array[i].LONGITUDE;
				 var lat = array[i].LATITUDE;

				 mapObj.removeOverlayById(array[i].VEHICLE_VIN);
			  	 var markerOption = new MMarkerOptions();
			  	 
// 				 if((array[i].ALARM_TYPE_ID=="40") || array[i].ALARM_TYPE_ID=="72"){//紧急告警
// 					 markerOption.imageUrl="../newimages/sidelayerimages/alarm01.gif";
// 				 }else if((array[i].ALARM_TYPE_ID=="32") ||  (array[i].ALARM_TYPE_ID=="49") || (array[i].ALARM_TYPE_ID=="54")){//超速告警
// 					 markerOption.imageUrl="../newimages/sidelayerimages/alarm02.gif";
// 				 }else if(array[i].ALARM_TYPE_ID=="75"){//开门
// 					 markerOption.imageUrl="../newimages/sidelayerimages/alarm05.gif";
// 				 }else{//车辆故障
						
// 					 markerOption.imageUrl="../newimages/sidelayerimages/alarm03.gif";
// 				 }

			  	if((array[i].ALARM_TYPE_ID=="40") ){//紧急告警
					if(boxStr.indexOf("3") > -1){
					 markerOption.imageUrl="../newimages/sidelayerimages/alarm01.gif";
					} else {
						continue;
					}
				 } else if((array[i].ALARM_TYPE_ID=="32")){//超速告警
					if(boxStr.indexOf("1") > -1){
					 markerOption.imageUrl="../newimages/sidelayerimages/alarm02.gif";
					} else {
						continue;
					}
				 } else if(array[i].ALARM_TYPE_ID=="49"){ //超转告警
					 continue;
				 } else if(array[i].ALARM_TYPE_ID=="75"){//开门告警
					 if(boxStr.indexOf("4") > -1){
					 	markerOption.imageUrl="../newimages/sidelayerimages/alarm05.gif";
					 } else {
						 continue;
					 }
				 } else if(array[i].ALARM_TYPE_ID=="72"){//超载
					 continue;
				 } else if(array[i].ALARM_TYPE_ID=="54"){//未鉴权驾驶
					 continue;
				 } else if((array[i].ALARM_TYPE_ID=="73" || array[i].ALARM_TYPE_ID=="74" ||
						 array[i].ALARM_TYPE_ID=="79" || array[i].ALARM_TYPE_ID=="80")){//乘车异常
					if(boxStr.indexOf("2") > -1){
					 	markerOption.imageUrl="../newimages/sidelayerimages/alarm06.gif";
					} else {
						continue;
					}
				 } else if(boxStr.indexOf("5") > -1){
//				 } else if("09,10,13,25,26,64,65,66,67,68,69,70,71".indexOf(array[i].ALARM_TYPE_ID) > -1 && boxStr.indexOf("5") > -1){//车辆故障
					 markerOption.imageUrl="../newimages/sidelayerimages/alarm03.gif";
//	 				 continue;
				 } else {continue;}
			     
			     markerOption.imageSize = new MSize(16,16);
			     markerOption.imageAlign=MConstants.MIDDLE_CENTER;
			     if(array[i].DIRECTION!="FFFF"){
					   hudu = array[i].DIRECTION;
				 }
			     markerOption.rotation = "0";

			     markerOption.picAgent = false;
				 markerOption.isEditable=false; //设置点是否可编辑。
				 markerOption.hasShadow=false;  //是否显示阴影。	
				 markerOption.zoomLevels=[]; //设置点的缩放级别范围。
				 markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
				 markerOption. dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
			     
			     var marker = new MMarker(new MLngLat(lon,lat),markerOption);
				 marker.id="error_"+idx;
				 mapObj.addOverlay(marker,false);

				 //mapObj.addEventListener(marker,MConstants.MOUSE_CLICK);

			}
	   }
	   

}
/**
* 在地图绘制站点
*/
function showSiteListOnMap(obj){
	
	if(obj != null && obj.length > 0){
		var sitelist = new Array();
		for(var i = 0; i < obj.length; i++){

			var tv = obj[i];
			var sitename = tv.SITE_NAME;
			var siteid = tv.SITE_ID;
			var sitelon = tv.LONGITUDE;
			var sitelat = tv.LATITUDE;

			

			var markerOption = new MMarkerOptions();
			markerOption.imageUrl="../images/map/station1.png";
		    markerOption.imageSize = new MSize(14,32);
		    markerOption.imageAlign=MConstants.MIDDLE_CENTER;

		    var fontstyle=new MFontStyle();//创建字体风格对象   
		    fontstyle.name="";//设置字体名称，默认为宋体   
		    fontstyle.size=14;//设置字体大小，默认为12   
		    fontstyle.color=0xFFFFFF;//设置字体的颜色，默认为0x000d46(黑色)   
		    fontstyle.bold=false;//设置字体是否为粗体，true，是，fasle，否（默认）   
		    var labeloption=new MLabelOptions();//添加标注   
		    labeloption.fontStyle=fontstyle;//设置标注的字体样式   
		    labeloption.alpha=0.8;//设置标背景及边框的透明度，默认为1，及不透明   
		    labeloption.hasBackground=true;//设置标注是否有背景，默认为false，即没有背景   
		    labeloption.hasBorder=true;//设置标注背景是否有边框，默认为false，即没有边框   
		    labeloption.backgroundColor=0x145697;//设置标注的背景颜色   
		    labeloption.borderColor=0x000088;//设置标注的边框颜色   
		    labeloption.content= sitename ;//标注的显示内容   
		    //设置标注左上角相对于面对象中心的锚点。标注左上角与面对象中心重合时，像素坐标原点(0,0)   
		    //labeloption.labelPosition=new MPoint(0,25);   
		    //设置对准点正下方的文字标签锚点   
		    labeloption.labelAlign=MConstants.BOTTOM_CENTER;

		    markerOption.labelOption = labeloption;
		    markerOption.picAgent = false;
			markerOption.isEditable=false; //设置点是否可编辑。
			markerOption.hasShadow=false;  //是否显示阴影。	
			markerOption.zoomLevels=[]; //设置点的缩放级别范围。
			markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
			markerOption. dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
		     
		    var marker = new MMarker(new MLngLat(sitelon,sitelat),markerOption);
			marker.id="site_" + siteid;
			sitelist.push(marker);
			
		}
		mapObj.addOverlays(sitelist,false);
	}
	
}

//自适应room值
function setFitV(overlayid,arrForFitView){   
	       
	    arrForFitView.push(overlayid);   
	    mapObj.setFitview(arrForFitView);   
} 
</script>
</body>
</html>