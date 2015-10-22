<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 中文注释 -->
<link rel="stylesheet" href="<s:url value='/styles/nyroModal.css' />" type="text/css" media="screen" />
<link href="/styles/global.css" rel="stylesheet" type="text/css" />
<link href="/styles/list.css" rel="stylesheet" type="text/css" />
<%@ include file="/WEB-INF/jsp/common/key.jsp"%>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<title>学生轨迹回放</title>
<style type="text/css">
*{ margin: 0; padding: 0}

body,td,th {
	font-size: 12px;
	color: #000000;
}
body {
	background: #f8f8f8;
	color: #555; 
	font: 12px/180% Tahoma,Arial,"宋体","微软雅黑";
	margin: 0;
}
a {
	font-size: 12px;
	color: #000000;
}
a:link {
	color: #008CDC;
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #008CDC;
}
a:hover {
	text-decoration: none;
	color: #000000;
}
a:active {
	text-decoration: none;
}
a.sbutton{
	background: url(../images/sbutton_bg.gif) repeat-x top left;
	border: 1px solid #b4b4b4;
	color: #2a2a2a;
	display: block;
	margin: 0 2px;
	padding: 1px 0 0 0;
	line-height: 20px;
	text-align: center;
	width: 60px;
	float:left;
}
.fudongBar{ width:180px; height:41px; position:absolute; z-index:1; left:0; top:0;}
.reportOnline2{ background: #eee; border: 1px solid #c3c3c3;}
.reportOnline3{ background: #fff; border: 1px solid #c3c3c3; width:100%; margin:3px auto 0;}
.biaotitiao{ background: #ccc url(../images/listTitle_bg.gif) repeat-x left top; line-height: 22px; font-size:12px; font-weight:bold; color:#333333; text-indent:4px;}

.play_stop{ position:relative; z-index:1; top:-8px; left:72px;}

</style>
<script type="text/javascript">
//加载地图对象
var mapObj = null; 
// 自适应zoom数组
var arrForFitView=new Array();

var intreal = 0;

function setonunload(){
	window.clearInterval(intreal);
}

// 加载地图
function mapInit(divid) {   
	var lon = document.getElementById("lon").value;
	var lat = document.getElementById("lat").value;
	//alert("lon:"+lon+",lat:"+lat);
    var mapOptions = new MMapOptions();//构建地图辅助类   
    mapOptions.zoom=9;//设置地图zoom级别   
    mapOptions.center=new MLngLat(lon,lat);   //设置地图中心点   
    mapOptions.hasDefaultMenu=false;
    mapOptions.toolbar = DEFAULT;//工具条   
    mapOptions.toolbarPos = new MPoint(5,5);  //工具条   
    mapOptions.overviewMap = MINIMIZE;//是否显示鹰眼  
    mapOptions.logoUrl = " "; 
    mapOptions.scale = SHOW;//是否显示比例尺   
    mapOptions.returnCoordType = COORD_TYPE_OFFSET;//返回数字坐标   
    mapOptions.zoomBox = true;//鼠标滚轮缩放和双击放大时是否有红框动画效果。   
    mapObj=new MMap(divid,mapOptions); //地图初始   
    mapObj.setKeyboardEnabled(false);

   // document.getElementById("submit"); 
    document.getElementById("bofangbeishu").innerHTML ="X 1";
    //document.getElementById("bofangtime").innerHTML="";
   //document.getElementById("OIL_INSTANT").innerHTML="";
    //document.getElementById("SPEEDING").innerHTML="";
    //document.getElementById("DIRECTION").innerHTML="";
    //document.getElementById("ENGINE_ROTATE_SPEED").innerHTML="";
    //document.getElementById("FIRE_UP_STATE").innerHTML="";
    //document.getElementById("alllength").innerHTML="";
    //var div = draw();
   // mapObj.addControl(div);
    var div2 = draw2();
    mapObj.addControl(div2);
        
    initSiteInfo();


   
        
}   

//加载站点信息
function initSiteInfo(){
	var routeid = document.getElementById("route_id").value;
	GPSDwr.initBitSite(routeid,initBitSite_callback);

	function initBitSite_callback(array){
		//alert(array.length);

		if(array != null && array.length >0){
			for(var i = 0; i < array.length; i++){
				var lon = array[i].SITE_LONGITUDE;
				var lat = array[i].SITE_LATITUDE;

				//点的属性设置
				var markerOption = new MMarkerOptions();
				markerOption.imageUrl="../images/icon_bar.gif";
				markerOption.imageAlign=MIDDLE_CENTER;
				var marker = new MMarker(new MLngLat(lon,lat),markerOption);
				markerOption.labelOption=addMarkerLabel2(array[i].SITE_NAME,TOP_CENTER);
				marker.id="site"+array[i].SITE_ID
				mapObj.addOverlay(marker,true);
				setFitV(marker.id,arrForFitView);

			}	 
		}		
	}
}
function addMarkerLabel2(pointname,direction){   
    
    var fontstyle=new MFontStyle();//创建字体风格对象   
    fontstyle.name="";//设置字体名称，默认为宋体   
    fontstyle.size=14;//设置字体大小，默认为12   
    fontstyle.color=0x000d46;//设置字体的颜色，默认为0x000d46(黑色)   
    fontstyle.bold=false;//设置字体是否为粗体，true，是，fasle，否（默认）   
    var labeloption=new MLabelOptions();//添加标注   
    labeloption.fontStyle=fontstyle;//设置标注的字体样式   
    labeloption.alpha=0.8;//设置标背景及边框的透明度，默认为1，及不透明   
    labeloption.hasBackground=true;//设置标注是否有背景，默认为false，即没有背景   
    labeloption.hasBorder=true;//设置标注背景是否有边框，默认为false，即没有边框   
    labeloption.backgroundColor=0x0000;//设置标注的背景颜色   
    labeloption.borderColor=0x00FFCC;//设置标注的边框颜色   
    labeloption.content=pointname;//标注的显示内容   
   //设置标注左上角相对于面对象中心的锚点。标注左上角与面对象中心重合时，像素坐标原点(0,0)   
    labeloption.labelPosition=new MPoint(0,18);   
    //设置对准点正下方的文字标签锚点   
    labeloption.labelAlign=TOP_LEFT;   
  
   return  labeloption;
}



// 添加点 参数为RaceTerminalInfo对象list
var hudu = 0;
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
					if(lon!= null && lon!="" && lat!=null && lat!=""&& lon!="FFFF" && lat!="FFFF" && lon>0&& lat>0&&lon<180 && lat <90){
						 //点的属性设置
						   var markerOption = new MMarkerOptions(); 
						   
						   markerOption.imageUrl="../images/arrow_blue.png"; //lan_1.png";
						   markerOption.imageAlign=MIDDLE_CENTER;

						   
						   
							if(array[i].DIRECTION!="FFFF"){
								   hudu = array[i].DIRECTION;
							}
						   
						   markerOption.rotation = hudu;
						   //markerOption.labelOption=addMarkerLabel(array[i].VEHICLE_LN,TOP_CENTER);
						   
						   //markerOption.tipOption = addFlashTip(array[i]); 
						   markerOption.canShowTip= true;
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
			   

		   //}
		   

	   }
	   else{
			alert("没有坐标数据,无法标点!");
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
	
				   
						 //点的属性设置
						   //alert(array[i].color);
				   //if(array[i].color=="b"){
					   //alert(array[i].color);
						  
						     var markerOption = new MMarkerOptions();
						     markerOption.imageUrl="../images/arrow_blue.png";
						     markerOption.imageAlign=MIDDLE_CENTER;
						     if(array[i].DIRECTION!="FFFF"){
								   hudu = array[i].DIRECTION;
							 }
							 
						     markerOption.rotation = hudu;
						     var marker = new MMarker(new MLngLat(lon,lat),markerOption);
							 marker.id="marker";
							 
							 mapObj.addOverlay(marker,false);
							 MapMoveToPoint(lon,lat);
				   //}
				   /*else if(array[i].color=="r"){
						  //markerOption.imageUrl="../images/arrow_red.gif";
							 mapObj.removeOverlayById(array[i].VEHICLE_VIN);
						  	 var markerOption = new MMarkerOptions();
						     markerOption.imageUrl="../images/arrow_red.gif";
						     markerOption.imageAlign=MIDDLE_CENTER;
						     if(array[i].DIRECTION!="FFFF"){
								   hudu = array[i].DIRECTION;
							 }
						     markerOption.rotation = hudu;
						     var marker = new MMarker(new MLngLat(lon,lat),markerOption);
							 
							 marker.id=index;
							 mapObj.addOverlay(marker,false);

							 mapObj.addEventListener(marker,MOUSE_CLICK,clickMouse);
							 MapMoveToPoint(lon,lat);
				   }*/
  
			}

	   }
	   else{
			alert("没有坐标数据,无法标点!");
	   }
	   

}

var isshow = true;
var showNo = "";

function clickMouse(event){
	//alert(event.overlayId);
	
	var t = terminalinfolist[event.overlayId];
	
	if(t.OVERSPEED_ALERT!=null && t.OVERSPEED_ALERT=="2"){
		document.getElementById("OVERSPEED_ALERT").innerHTML="有";
		document.getElementById("tr01").style.display = "";
		}
	else{
		document.getElementById("OVERSPEED_ALERT").innerHTML="无";
		document.getElementById("tr01").style.display = "none";
		}
	if(t.FATIGUE_ALERT!=null && t.FATIGUE_ALERT=="2"){
		document.getElementById("FATIGUE_ALERT").innerHTML = "有";
		document.getElementById("tr02").style.display ="";
		}
	else{
		document.getElementById("FATIGUE_ALERT").innerHTML = "无";
		document.getElementById("tr02").style.display ="none";
		}
	if(t.GPS_ALERT!=null && t.GPS_ALERT=="2"){
		document.getElementById("GPS_ALERT").innerHTML = "有";
		document.getElementById("tr03").style.display = "";
		}
	else{
		document.getElementById("GPS_ALERT").innerHTML = "无";
		document.getElementById("tr03").style.display = "none";
		}
	if(t.sos != null && t.sos=="2"){
		document.getElementById("SOS").innerHTML = "有";
		document.getElementById("tr04").style.display = "";
		}
	else{
		document.getElementById("SOS").innerHTML = "无";
		document.getElementById("tr04").style.display = "none";
		}
	if(t.rapid_alert !=null && t.rapid_alert=="2"){
		document.getElementById("RAPID_ALERT").innerHTML = "有";
		document.getElementById("tr05").style.display = "";
		}
	else{
		document.getElementById("RAPID_ALERT").innerHTML = "无";
		document.getElementById("tr05").style.display = "none";
		}
	if(t.region_overspeed_alert !=null && t.region_overspeed_alert=="2"){
		document.getElementById("Region_overspeed_alert").innerHTML = "有";
		document.getElementById("tr06").style.display = "";
		}
	else{
		document.getElementById("Region_overspeed_alert").innerHTML = "无";
		document.getElementById("tr06").style.display = "none";
		}
	if(t.region_in_alert != null && t.region_in_alert=="2"){
		document.getElementById("Region_in_alert").innerHTML = "有";
		document.getElementById("tr07").style.display = "";
		}
	else{
		document.getElementById("Region_in_alert").innerHTML = "无";
		document.getElementById("tr07").style.display = "none";
		}
	if(t.region_out_alert != null && t.region_out_alert=="2"){
		document.getElementById("Region_out_alert").innerHTML = "有";
		document.getElementById("tr08").style.display = "";
		}
	else{
		document.getElementById("Region_out_alert").innerHTML = "无";
		document.getElementById("tr08").style.display = "none";
		}
	if(t.region_openclosedoor_alert !=null && t.region_openclosedoor_alert=="2"){
		document.getElementById("Region_openclosedoor_alert").innerHTML = "有";
		document.getElementById("tr09").style.display = "";
		}
	else{
		document.getElementById("Region_openclosedoor_alert").innerHTML = "无";
		document.getElementById("tr09").style.display = "none";
		}

	if(showNo == event.overlayId){
		if(isshow){
			document.getElementById("MapbarDiv").style.display = "none";
			isshow=false;
		}
		else{
			document.getElementById("MapbarDiv").style.display = "";
			showNo = event.overlayId;
			isshow=true;
		}
	}
	else{
		document.getElementById("MapbarDiv").style.display = "";
		showNo = event.overlayId;
		isshow=true;
	}
	
	
}

function MapMoveToPoint(lon,lat){
	var bounds=mapObj.getLngLatBounds();
	//alert(bounds.southWest.lngX+","+bounds.southWest.latY+";"+bounds.northEast.lngX+","+bounds.northEast.latY);

	if(lon < bounds.southWest.lngX || lat < bounds.southWest.latY || lon > bounds.northEast.lngX || lat > bounds.northEast.latY){
		mapObj.panTo(new MLngLat(lon,lat));
	}
}

//添加点的文字样式
function addMarkerLabel(pointname){   
    
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
    labeloption.backgroundColor=0x000000;//设置标注的背景颜色   
    labeloption.borderColor=0x000088;//设置标注的边框颜色   
    labeloption.content=pointname;//标注的显示内容   
   //设置标注左上角相对于面对象中心的锚点。标注左上角与面对象中心重合时，像素坐标原点(0,0)   
    labeloption.labelPosition=new MPoint(1,2);   
    //设置对准点正下方的文字标签锚点   
    labeloption.labelAlign=TOP_LEFT;   
  
   return  labeloption;
}

//数据加载中0未加载，1加载中
var loaddate = 0;

// 点击播放按钮
function postselectline(){
	
	
	
	if(terminalinfolist != null&&terminalinfolist.length>0){
		mapObj.removeOverlayById("marker");
		AscUpdateLine();
		playstate=0;
		
	}
	else{
		playstate=2;
		document.getElementById("playimg").src="../images/btn_play.gif";
		document.getElementById("playimg").title="播放";
		alert("没有可播放数据!");
	}
	
}


// 自适应room值
function setFitV(overlayid,arrForFitView){   
	       
	    arrForFitView.push(overlayid);   
	    mapObj.setFitview(arrForFitView);   
} 

//显示全部轨迹
function drawLine(terminalinfolist){
	//alert("llllllllllllls"+terminalinfolist.length);
	//mapObj.removeAllOverlays();
	var terminalinfo = null;
	if(terminalinfolist !=null && terminalinfolist.length > 1){
		var arr = new Array();
		if(terminalinfolist[0].gpsIsExc){
			//var errorArr = new Array();
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
						terminalinfo = terminalinfolist[i];
					}
					    
				}

					if(arr.length >0){
						var polyline=new MPolyline(arr);
						
						polyline.id="LINE";
					    mapObj.addOverlay(polyline,false);
					    setFitV(polyline.id,arrForFitView);

					    var arrayterminalinfo = new Array();
					    arrayterminalinfo.push(terminalinfo);
					    addmarkerObjNew(arrayterminalinfo,terminalinfolist.length-1);

					    /*if(errorArr.length>0){
							for(var z = 0; z < errorArr.length; z++){
								var ei = errorArr[z];
								var obj = terminalinfolist[ei];
								var arrayterminalinfo = new Array();
							    arrayterminalinfo.push(obj);
							    addmarkerObjNew(arrayterminalinfo,ei);
							}
						}*/

					    /*if(terminalinfo.color!="r"){
					    	var arrayterminalinfo = new Array();
						    arrayterminalinfo.push(terminalinfo);
						    addmarkerObjNew(arrayterminalinfo,terminalinfolist.length-1);
						}*/
					   
					}
		}
		else{
            alert("GPS数据偏移异常，请重新尝试或联系地图厂商");
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
			    
			    //document.getElementById("bofangtime").innerHTML=timeoptval(terminalinfo.TERMINAL_TIME);
			}
			else{
				//alert("GPS数据异常");
			}
		}
		else{
            alert("GPS数据偏移异常，请重新尝试或联系地图厂商");
        }
		
	}
	else{
		mapObj.removeAllOverlays();
		alert("没有轨迹数据!");
	}

}

// 实时更新轨迹
var terminalinfolist = new Array();
// 播放位置计数
var pointIndex = 0;
// 跟新轨迹
function AscUpdateLine(){
	//var terminalinfo ;
	var settime = 2000/playShudu;//document.getElementById('huifangspeed').value;
	//alert(settime);
	if(terminalinfolist != null && terminalinfolist.length>0){

		if(terminalinfolist[0].gpsIsExc){
				if(pointIndex < terminalinfolist.length){
					
					//var arrsMarker = mapObj.getOverlayById(terminalinfolist[pointIndex].VEHICLE_VIN);
					var lon = terminalinfolist[pointIndex].LONGITUDE;
					var lat = terminalinfolist[pointIndex].LATITUDE;
					if(lon!= null && lon!=""&& lat!=null && lat!=""&& lon!="FFFF" && lat!="FFFF"&& lon>0&& lat>0&&lon<180 && lat <90){
								
						var arrayterminalinfo = new Array();
						arrayterminalinfo.push(terminalinfolist[pointIndex]);
						addmarkerObjNew(arrayterminalinfo,pointIndex);
						//播放时间
						//document.getElementById("bofangtime").innerHTML=timeoptval(terminalinfolist[pointIndex].TERMINAL_TIME);
					    //里程计算
						//mousePolylineDistance();
						//瞬时油耗
						//document.getElementById("OIL_INSTANT").innerHTML=nullToZore(terminalinfolist[pointIndex].OIL_INSTANT)+"&nbsp;L/h";
						//车速
						//document.getElementById("SPEEDING").innerHTML=nullToZore(terminalinfolist[pointIndex].SPEEDING)+"&nbsp;km/h";
						//方向
						//document.getElementById("DIRECTION").innerHTML=diretionToStr(terminalinfolist[pointIndex].DIRECTION);
						//转速
						//document.getElementById("ENGINE_ROTATE_SPEED").innerHTML=nullToZore(terminalinfolist[pointIndex].ENGINE_ROTATE_SPEED)+"&nbsp;rpm";
						//点火状态
						//document.getElementById("FIRE_UP_STATE").innerHTML=(terminalinfolist[pointIndex].STAT_INFO==0?'关':'开');
					}
					pointIndex++;
					intreal = window.setTimeout("AscUpdateLine()",settime);
					
				}
				else{
					//alert(pointIndex);
					pointIndex = 0;
					document.getElementById("playimg").src="../images/btn_play.gif";
					document.getElementById("playimg").title="播放";
					playstate = 2;
					alert("回放结束!");
				}
		}
		else{
            alert("GPS数据偏移异常，请重新尝试或联系地图厂商");
        }
	}
}

function timeoptval(time){
	if(time != null && time.trim() != "" && time.trim() != "undefined"){
		return time.substring(0, time.length-2);
	}
	else{
		return "0";
	}
}

function nullToZore(str){
	if(str == null || str == "" ||  str == "undefined" || str == " " ||str =="FFFF"){
		return 0;
	}
	else{
		return str;
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

//判断时间范围
function timeBound(){
	var startt = document.getElementById('line_start_time').value;
	var date1 = Date.parse(new Date(startt.replace(/-/g, "/"))); 
	//alert(date1);//
	var endt = document.getElementById('line_end_time').value;
	var date2 = Date.parse(new Date(endt.replace(/-/g, "/"))); 
	//alert(date2);

	if(((date2-date1) >=0)&&((date2-date1)/1000<=7200)){
		return true;
	}
	else if((date2-date1) < 0){
		alert("开始时间要小于结束时间!");
		return false;
	}
	else{
		alert("时间范围不能大于2小时!");
		return false;
	}
}


// 计算距离
function distanceObj(){   
    this.lngX;this.latY;this.lngX1;this.latY1;this.polylineCoor;   
} 
var disObj=new distanceObj(); 

function mousePolylineDistance(){   
    var arr=mapObj.getOverlaysByType(MOverlay.TYPE_POLYLINE);   
    if(arr.length==0){   
        return ;   
    }   
    //if(arr.length==1){mapObj.setCurrentMouseTool(PAN_WHEELZOOM);}   
    disObj.polylineCoor=new Array();   
    for(var i=0;i<arr.length;i++){   
        for(var j=0;j<arr[i].lnglatArr.length;j++){   
            disObj.polylineCoor.push(new MLngLat(arr[i].lnglatArr[j].lngX,arr[i].lnglatArr[j].latY));   
        }   
    }   
    //mapObj.addEventListener(mapObj,ADD_OVERLAY,mousePolylineDistance);  
    distancePolyline(); 
}  
function distancePolyline(){   
    var mrs = new MSpatialSearch();   
    var opt = new MSpatialSearchOptions();   
    mrs.setCallbackFunction(distancePolyline_CallBack);   
    var regionXY2=new MLngLats(disObj.polylineCoor);   
    mrs.calculaDistance(null,null,regionXY2,opt);   
}   
function distancePolyline_CallBack(data){   
    if(data.error_message != null){   
        document.getElementById("alllength").innerHTML = "查询异常！"+data.error_message;   
    }else{   
        /*var result="";   
        for(var e=0;e<data.list.length;e++){   
            result+="第一段距离："+data.list[e].length+"<br />";   
        }  */ 
        document.getElementById("alllength").innerHTML=data.length;   
    }   
}



//播放倍数
var playShudu = 1;
//播放状态 0播放 1暂停2未播放
var playstate= 2;
// 返回到头
function tongbuOpt(){

	if(loaddate==1){
		return;
	}
	pointIndex=0;
	setonunload();
	mapObj.removeAllOverlays();
	postselectline();
	playstate= 0;
	
}

// 播放速度减少
function kuaituiOpt(){
	var bofangbeishuObj = document.getElementById("bofangbeishu");
	if(playShudu > 1){
		playShudu = playShudu-1;
	}
	//alert(playShudu);
	bofangbeishuObj.innerHTML = "X "+playShudu;
}

// 暂停
function zhantingOpt(){
	if(loaddate==1){
		return;
	}
	document.getElementById("playimg").src="../images/btn_play.gif";
	document.getElementById("playimg").title="播放";
	playstate= 1;
	setonunload();
}

//轨迹信息查询
function selectgj(){
	if(!timeBound()){
		return ;
	}
	
	var vin = document.getElementById("vin").value;
	//alert(vin);
	var flag = document.getElementById('lookflag').value;
	//alert(flag);
	var startt = document.getElementById('line_start_time').value;
	//alert(startt);
	var endt = document.getElementById('line_end_time').value;

	var stuid = document.getElementById("stu_id").value;
	//alert(stuid);
	loaddate=1;
	document.getElementById("loading").innerHTML="数据加载...";
	
	GPSDwr.getXS_Check_record(vin,stuid, startt,endt,getXS_Check_record_callback);
	

	function getXS_Check_record_callback(array){
		//alert("123123--"+array.length);
		//学生打卡记录
		
		//清空孩子打卡记录点
		var markerlist = mapObj.getOverlaysByType(MOverlay.TYPE_MARKER);
		var lineid = mapObj.getOverlayById("LINE");
		if(markerlist != null && markerlist.length>0){
			for(var i = 0; i < markerlist.length; i++){
				var markerid = markerlist[i].id;
				 if(markerid.indexOf("childerror") != -1){
					 mapObj.removeOverlayById(markerid);
				 }
				  
			}
		}
		if(lineid != null && lineid != "" && lineid != "undefined"){
			mapObj.removeOverlayById(lineid);
			mapObj.removeOverlayById("marker");
		}
		
		
		if(array != null && array.length>0){
			// 标记孩子打卡记录点
			for(var i = 0; i < array.length; i++){
				var lon = array[i].LONGITUDE;
				var lat = array[i].LATITUDE;

				//点的属性设置
				var markerOption = new MMarkerOptions();
				markerOption.imageUrl="../images/arrow_red.gif";
				markerOption.imageAlign=MIDDLE_CENTER;
				var marker = new MMarker(new MLngLat(lon,lat),markerOption);
				marker.id="childerror"+array[i].SITE_ID;
				mapObj.addOverlay(marker,true);
				setFitV(marker.id,arrForFitView);

			}
			
			//车辆行驶线路
			GPSDwr.getVehcileLineList(vin,startt,endt,getpostselectline);

			function getpostselectline(array){
				    loaddate=0;
				    document.getElementById("loading").innerHTML="";
					//alert("fff");
					terminalinfolist = array.data;
					//mapObj.removeAllOverlays();

					drawLine(terminalinfolist);
			}	 
		}
		else{
			alert("没有孩子相关乘车记录!");
		}
	}
}

// 播放
function baofangOpt(){

	// 为播放时
	if(playstate==2){
		//dengdai();
		if(loaddate==1){
			return;
		}
		postselectline();
		
	}
	// 暂停时
	else if(playstate==1){
		if(loaddate==1){
			return;
		}
		document.getElementById("playimg").src="../images/btn_stop.gif";
		document.getElementById("playimg").title="暂停";
		AscUpdateLine();
		//playstate=0;
	}
	else if(playstate==0){
		zhantingOpt();
	}
	
	
}

// 结束
function jieshuOpt(){
	
	if(loaddate==1){
		return;
	}
	pointIndex=0;
	playstate = 2;
	document.getElementById("playimg").src="../images/btn_play.gif";
	document.getElementById("playimg").title="播放";
	document.getElementById("MapbarDiv").style.display = "none";
	isshow=false;
	//document.getElementById("bofangtime").innerHTML="";
    //document.getElementById("OIL_INSTANT").innerHTML="";
    //document.getElementById("SPEEDING").innerHTML="";
    //document.getElementById("DIRECTION").innerHTML="";
    //document.getElementById("ENGINE_ROTATE_SPEED").innerHTML="";
    //document.getElementById("FIRE_UP_STATE").innerHTML="";
	mapObj.removeAllOverlays();
	setonunload();
}

// 播放数据增加
function kuaijinOpt(){
	var bofangbeishuObj = document.getElementById("bofangbeishu");
	if(playShudu < 20){
		playShudu = playShudu+1;
	}
	//alert(playShudu);
	bofangbeishuObj.innerHTML =  "X "+playShudu;
	
	
}

// 返回到尾
function weibuOpt(){
	
	if(loaddate==1){
		return;
	}
	if(terminalinfolist != null && terminalinfolist.length > 0){
		//pointIndex=terminalinfolist.length-1;
		playstate = 2;
		document.getElementById("playimg").src="../images/btn_play.gif";
		document.getElementById("playimg").title="播放";
		setonunload();
		//mapObj.removeAllOverlays();
		//drawLine(terminalinfolist);
		pointIndex = 0;
		alert("回放结束!");
	}else{
		alert("没有可播放数据!");
	}
	
}

function dengdai(){
	var bmask = document.createElement("div");
	var mask = document.createElement("iframe");
	var tmask = document.createElement("div");
	//var main = document.getElementById('main');
	//if (!main) {
	//	main = document.documentElement;
	//}
	bmask.className = "bmask";
	mask.className = "mask";
	tmask.className = "tmask";
	//tmask.innerHTML = "<img src='../images/loading.gif' align='absmiddle'>&nbsp;" + msg;
	tmask.innerHTML = "<img src='../images/loading.gif' align='absmiddle'>&nbsp;";
	bmask.style.height = (document.compatMode != "CSS1Compat") ? document.body.scrollHeight : document.documentElement.scrollHeight;
	tmask.style.top = (window.screen.height/2)-120+'px';
 	if (!(window.navigator.userAgent.indexOf("MSIE")>=1)){
 		tmask.style.position = 'fixed';
 	}
	//main.appendChild(bmask);
	bmask.appendChild(mask);
	//main.appendChild(tmask);
}

function draw2() {
	 var mapdiv = document.getElementById("MapbarDivNO");
	 
	 mapdiv.style.bottom = "450px";
	 mapdiv.style.left = "110px";
	 mapdiv.style.zIndex = "1005";
	 mapdiv.unselectable = "on";
	 mapdiv.style.position = "absolute";
	 return mapdiv;
	 
}

function draw() {
	var mapdiv = document.getElementById("MapbarDiv");
	
	mapdiv.style.top = "0px";
	mapdiv.style.right = "0px";
	mapdiv.style.zIndex = "1005";
	mapdiv.unselectable = "on";
	mapdiv.style.position = "absolute";
	return mapdiv;
	
}


function pickedstarttime(){
    var line_start_time=document.getElementById('line_start_time').value.replace(/-/g,"/");
    var line_end_time=document.getElementById('line_end_time').value.replace(/-/g,"/");
    
	var startdate=new Date(line_start_time);
	var enddate=new Date(line_end_time);
	var iHours = parseInt((enddate - startdate) / 1000 / 60 / 60);
	
	if(iHours < 0 || iHours > 2){
		enddate.setTime(startdate.getTime() + 1000*60*60*2);
	}
	var str1 = startdate.pattern("yyyy/MM/dd");
	var str2 = enddate.pattern("yyyy/MM/dd");
	if(str1 != str2){
		enddate = new Date(str1+" 23:59:59");
	}
	$dp.$('line_end_time').value=enddate.pattern("yyyy-MM-dd HH:mm:ss") ;
}
function pickedendtime(){
    var line_start_time=document.getElementById('line_start_time').value.replace(/-/g,"/");
    var line_end_time=document.getElementById('line_end_time').value.replace(/-/g,"/");

	var startdate=new Date(line_start_time);
	var enddate=new Date(line_end_time);
	var iHours = parseInt((enddate - startdate) / 1000 / 60 / 60);

	if(iHours < 0 || iHours > 2){
		startdate.setTime(enddate.getTime() - 1000*60*60*2);
	}
	var str1 = startdate.pattern("yyyy/MM/dd");
	var str2 = enddate.pattern("yyyy/MM/dd");
	if(str1 != str2){
		startdate = new Date(str2+" 00:00:00");
	}
	$dp.$('line_start_time').value=startdate.pattern("yyyy-MM-dd HH:mm:ss") ;
}

/**      
* 对Date的扩展，将 Date 转化为指定格式的String      
* 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符      
* 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)      
* eg:      
* (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423      
* (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
* (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
* (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
* (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
*/        
Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "\u65e5",         
    "1" : "\u4e00",         
    "2" : "\u4e8c",         
    "3" : "\u4e09",         
    "4" : "\u56db",         
    "5" : "\u4e94",         
    "6" : "\u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}
</script>

</head>
<body onLoad="mapInit('iCenter')" onunload="setonunload()">

<div style="width: 900px; height: 420px;">
<div id="iCenter" style="width: 690px; height: 444px; float:left; border-right:4px double #999999;"></div>

<div  id ="MapbarDivNO" valign="right">
      <table  width="180px" border="0" cellspacing="0" cellpadding="0">
                    <tr id="maptoolbar" valign="right">
                      <td height="33" valign="right">
                      ©2011 MapABC - GS(2010)6011
                      </td>
                    </tr>       
                  </table>
</div>
<div id="selectline"
	style="width: 206px; z-index: 1; background-color: #F8F8F8; float:right; margin:2px 0 0 0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        
						<td class="reportOnline3">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td height="22" align="left" class="biaotitiao">轨迹回放</td>
                          </tr>
                          <tr>
                            <td align="center">
                            <table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                            <td height="25" align="left">开始时间：</td></tr>
                            <tr><td height="25" align="left">
                            <input readonly="readonly" type="text"  size="24" id="line_start_time" name="line_start_time" 
                            onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,maxDate:'#F{$dp.$D(\'line_end_time\')}',onpicked:pickedstarttime})"
			class="Wdate"  value="<s:property value='line_start_time'/>"/>
			</td></tr>
                            <tr><td height="25" align="left">结束时间：</td></tr>
                            <tr>
                            <td height="25" align="left">
                            <input readonly="readonly" type="text"  size="24" id="line_end_time" name="line_end_time" 
                            onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,minDate:'#F{$dp.$D(\'line_start_time\')}',onpicked:pickedendtime})"
			class="Wdate" value="<s:property value='line_end_time'/>"/>
							</td>
							</tr>
							<tr>
								<td align="center">
								<a href="#" class="sbutton" onclick="selectgj()" >查询</a>
								</td>
							</tr>
							
							
							
                            <tr>
                            <td>
                                <input id="vin" name="vin" type="hidden" value="<s:property value='vin'/>" />
                                <input id="lookflag" name="lookflag" type="hidden"  value="<s:property value='lookflag'/>"/>
                                <input id="lon" name="lon" type="hidden" value="<s:property value='lon'/>" />
                                <input id="lat" name="lat" type="hidden" value="<s:property value='lat'/>" />
                                <input id="route_id" name="route_id" type="hidden" value="<s:property value='route_id'/>" />
                                <input id="stu_id" name="stu_id" type="hidden" value="<s:property value='stu_id'/>" />
                            </td>
                          </tr>
                          <tr>
                            <td height="8" align="left" >

							</td>
                         </tr>
                            </table>
                            </td>
                          </tr>
                          
                        </table>
                        </td>
                      </tr>
					  
					  
					    <tr>
                            <td  align="left"  style=" padding:0px;">


回放速率：<s:label id="bofangbeishu" name="bofangbeishu" ></s:label>  <s:label id="loading" name="loading" cssStyle="float:right; padding:0 40px 0 0;"  ></s:label>
  													
  							</td>
  							
      </tr>
      
      <tr>
      <td height="25" align="left"  style=" padding:0px;">
      <a href="javascript:void(0)" onclick="baofangOpt()" onfocus="this.blur()"><img id="playimg" src="../images/btn_play.gif" width="10" height="13" class="play_stop" border="0"  title="播放" /></a><img src="../images/bofangtiao.jpg" border="0" usemap="#Map" />	
      </td>
      </tr>
                         
    </table>
</div>


</div>
<map name="Map" id="Map">
<area id="tongbu" shape="rect" coords="8,8,27,21" href="javascript:void(0)" onclick="tongbuOpt()" title="重新开始" onfocus="this.blur()"/>
<area shape="kuaitui" coords="33,8,50,21" href="javascript:void(0)" onclick="kuaituiOpt()" title="慢速" onfocus="this.blur()"/>
<!--<area shape="zhanting" coords="57,8,75,21" href="javascript:void(0)" onclick="zhantingOpt()" title="暂停" onfocus="this.blur()"/>-->
<!--<area shape="baofang" coords="84,8,99,21" href="javascript:void(0)" onclick="baofangOpt()" title="播放" onfocus="this.blur()"/>-->
<area shape="jieshu" coords="83,8,98,21" href="javascript:void(0)" onclick="jieshuOpt()" title="取消" onfocus="this.blur()"/>
<area shape="kuaijin" coords="108,8,125,21" href="javascript:void(0)" onclick="kuaijinOpt()" title="快速" onfocus="this.blur()"/>
<area id="weibu" shape="rect" coords="129,8,146,21" href="javascript:void(0)" onclick="weibuOpt()" title="完成播放" onfocus="this.blur()"/>
</map>


</body>
</html>

