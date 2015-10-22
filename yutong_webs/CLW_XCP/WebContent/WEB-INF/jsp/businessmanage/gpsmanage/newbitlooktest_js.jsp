<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>

    
<script type="text/javascript">
function trim(v){
	return v.replace(/^\s+|\s+$/g, '');
}

var ral_resultShowflash= 2000;

function inintSelect() {
	var selectobj = document.getElementById("selectDriveingNode");
	if(selectobj.options.length >0){
		for(var i = selectobj.options.length; i >= 0; i--){
			//alert(selectobj.options.length);
			selectobj.remove(i);
		}
	}	
	var optionobj = document.createElement('option');
	optionobj.value = 0;
	optionobj.text = "请点击查询";
	selectobj.add(optionobj);
	jQuery("#selectDriveingNode").removeAttr("title");
		
}

function selectModel(obj){

	var val = obj.value;
	//alert(val);
	if(val == "1" || val == "3"){
		document.getElementById("diveringNode").style.display = "block";
		document.getElementById("everyTime").style.display = "none";
		inintSelect();
	}else if(val == "2" ){
		document.getElementById("diveringNode").style.display = "none";
		document.getElementById("everyTime").style.display = "block";
	}
}

//加载地图对象
var mapObj = null; 
// 自适应zoom数组
var arrForFitView=new Array();

var intreal = 0;

function setonunload(){
	window.clearInterval(intreal);
	//document.removeChild(document.getElementById("bitiCenter"));
}

// 加载地图
function mapInit(divid) { 
	
	var lon = document.getElementById("lon").value;
	var lat = document.getElementById("lat").value;
	//alert("lon:"+lon+",lat:"+lat);
	
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
    

   // document.getElementById("submit"); 
    document.getElementById("bofangbeishu").innerHTML ="x2倍速";
    document.getElementById("bofangtime").innerHTML="";
    document.getElementById("DINGWEI_STAT").innerHTML="";
    document.getElementById("SPEEDING").innerHTML="";
    document.getElementById("DIRECTION").innerHTML="";
    document.getElementById("ENGINE_ROTATE_SPEED").innerHTML="";
    document.getElementById("FIRE_UP_STATE").innerHTML="";
    document.getElementById("LIMITE_NUMBER").innerHTML="";
    //document.getElementById("alllength").innerHTML="";
    document.getElementById("oil_instant").innerHTML="";
    //var div = draw();
   // mapObj.addControl(div);
    
    mapObj.addEventListener(mapObj,MConstants.MAP_READY,map_ready);
    
}   
function map_ready(param){  
	
	
}

var bitichart = null;

var tiBiChartXml = "";

var tiBiChartXmlCount = 0;

var chartPre = "<graph caption='' rotateNames='1' animation='0' canvasBgAlpha='0' bgAlpha='0' bgColor='FFFFFF' formatNumber='1' xAxisName='' yAxisMaxValue='80' yAxisMinValue='60'  decimalPrecision='0' formatNumberScale='0' numberSuffix='' showNames='0' showValues='0'  showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' >";
var chartLate = "</graph>";
function chartCreate(){
	var xmlTotal = chartPre + tiBiChartXml + chartLate; 
	createChart(xmlTotal);
}
function chartInit(){
	var xmlTotal = chartPre + tiBiChartXml + chartLate; 
	if(jQuery("#ChartIds").length == 0){
		createChart(xmlTotal);
 	} else {
 		updateChart(xmlTotal);
 	}
}
//创建新的图表
function createChart(chartsXml){
	var s = jQuery("#bitiCenter").css("width");
	s = s.replace("px","");
	bitichart = new FusionCharts("../scripts/fusioncharts/FCF_Line.swf", "ChartIds", s, "210","1","1");
	bitichart.setDataXML(chartsXml);		   
	bitichart.render("bitiChar");
}
//更新图表
function updateChart(chartsXml){
	//alert(jQuery("#ChartIds").length);
	updateChartXML("ChartIds",chartsXml);
}
//关闭图表
function chartClose(){
	//tiBiChartXml = "";
    jQuery("#bitiChar").css("display","none");
    jQuery("#bitiDoor").css("display","block");
    jQuery("#bitiClose").css("display","none");
}
//打开图表
function chartOpen(){
	jQuery("#bitiChar").css("display","block");
	jQuery("#bitiDoor").css("display","none");
    jQuery("#bitiClose").css("display","block");
}


function addMarkerLabel2(pointname,direction){   
    
    var fontstyle=new MFontStyle();//创建字体风格对象   
    fontstyle.name="";//设置字体名称，默认为宋体   
    fontstyle.size=10;//设置字体大小，默认为12   
    fontstyle.color=0xFFFFFF;//设置字体的颜色，默认为0x000d46(黑色)   
    fontstyle.bold=false;//设置字体是否为粗体，true，是，fasle，否（默认）   
    var labeloption=new MLabelOptions();//添加标注   
    labeloption.fontStyle=fontstyle;//设置标注的字体样式   
    labeloption.alpha=0.8;//设置标背景及边框的透明度，默认为1，及不透明   
    labeloption.hasBackground=true;//设置标注是否有背景，默认为false，即没有背景   
    labeloption.hasBorder=true;//设置标注背景是否有边框，默认为false，即没有边框   
    labeloption.backgroundColor=0x145697;//设置标注的背景颜色   
    labeloption.borderColor=0x145697;//设置标注的边框颜色   
    labeloption.content=pointname;//标注的显示内容   
   //设置标注左上角相对于面对象中心的锚点。标注左上角与面对象中心重合时，像素坐标原点(0,0)   
    labeloption.labelPosition=new MPoint(0,18);   
    //设置对准点正下方的文字标签锚点   
    labeloption.labelAlign=direction;   
  
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
			   

		   //}
		   

	   }
	   else{
			//alert("没有坐标数据,无法标点!");
			tishiShow("没有坐标数据,无法标点！");
			tishiHide();
	   }
	   

}
//上一开门点状态记录

function addmarkerObjNew(array,index){
	  //mapObj.removeOverlaysByType(MOverlay.TYPE_MARKER);
	  var arr = new Array();
	   if(array != null && array.length > 0){
		   var ok = 0;
		   
		   //if(array[0].gpsIsExc){
			   for(var i=0; i < array.length;i++){
	
				   var lon = array[i].LONGITUDE;
				   var lat = array[i].LATITUDE;
	
				   var oldmarker = mapObj.getOverlayById("marker");
				   if(oldmarker == null ){
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
						 MapMoveToPoint(lon,lat);
				   }
				   else{
					   if(array[i].DIRECTION!="FFFF"){
						   hudu = array[i].DIRECTION;
					 	}
				     	
					   mapObj.markerMoveTo("marker", new MLngLat(lon,lat), hudu, 0);
					   MapMoveToPoint(lon,lat);
				   }
					
			}

	   }
	   else{
			//alert("没有坐标数据,无法标点!");
			tishiShow("没有坐标数据,无法标点！");
			tishiHide();
	   }
	   

}



var isshow = true;
var showNo = "";

function clickMouse(event){
	//alert(event.overlayId);

	var n = event.overlayId.substring(0,5);
	if(n != "error"){
		return ;
	}
	var id = event.overlayId.substring(6, event.overlayId.length);
	//alert(id);
	
	var tt = Errorterminalinfolist[id];
	//var tt = terminalinfolist[id];
	//alert(tt.ALARM_BASE_INFO);
	
	document.getElementById("errorinfo").innerHTML=tt.ALARM_TYPE_NAME;

	alarminfoshow("block", "none");

	if(playstate== 0){
		playstate= 1;
		setonunload();
		document.getElementById("playimg").src="../newimages/arr_play.gif";
		document.getElementById("playimg").title="播放";
	}
	

	//播放时间
	document.getElementById("alarmbofangtime").innerHTML=timeoptval(tt.TERMINAL_TIME);
	//瞬时油耗
	document.getElementById("alarmDINGWEI_STAT").innerHTML=(tt.dingwei_stat!=0?'有效':'无效');
	//车速
	document.getElementById("alarmSPEEDING").innerHTML=nullToZore(tt.SPEEDING)+"&nbsp;km/h";
	//方向
	document.getElementById("alarmDIRECTION").innerHTML=diretionToStr(tt.DIRECTION);
	//转速
	document.getElementById("alarmENGINE_ROTATE_SPEED").innerHTML=nullToZore(tt.ENGINE_ROTATE_SPEED)+"&nbsp;rpm";
	//点火状态
	document.getElementById("alarmFIRE_UP_STATE").innerHTML=(tt.STAT_INFO==0?'关':'开');
	//承载情况
	document.getElementById("alarmLIMITE_NUMBER").innerHTML=nullToZore(tt.STU_NUM)+"/"+nullToZore(tt.LIMITE_NUMBER)+"人";
	//瞬时油耗
	document.getElementById("alarmoil_instant").innerHTML=nullToZore(tt.OIL_INSTANT)+"&nbsp;L/h";
	//告警类型
	document.getElementById("alarm_type_name").value=tt.ALARM_TYPE_NAME;
	
	
}
function alarminfoshow(no1, no2){
	 	document.getElementById("alarmtable").style.display = no1;
	 	document.getElementById("MapbarDiv").style.display = no1;
		document.getElementById("notalarmtable").style.display = no2;
		
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
    labeloption.backgroundColor=0x145697;//设置标注的背景颜色   
    labeloption.borderColor=0x000088;//设置标注的边框颜色   
    labeloption.content=pointname;//标注的显示内容   
   //设置标注左上角相对于面对象中心的锚点。标注左上角与面对象中心重合时，像素坐标原点(0,0)   
    labeloption.labelPosition=new MPoint(1,2);   
    //设置对准点正下方的文字标签锚点   
    labeloption.labelAlign=TOP_LEFT;   
  
   return  labeloption;
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
		var arrAlarm = new Array();
		if(terminalinfolist[0].gpsIsExc){
			var errorArr = new Array();
			var len = terminalinfolist.length;
				for(var i = 0; i < len; i++){
										
					 var lon = terminalinfolist[i].LONGITUDE;
					 var lat = terminalinfolist[i].LATITUDE;
					 var alarm_base_info = terminalinfolist[i].ALARM_BASE_INFO;
					 //alert(lon+"-"+lat);
					 //alert(lat);
					 
					if(lon!= null && lon!=""&& lat!=null && lat!=""&& lon!="FFFF" && lat!="FFFF"&& lon>0&& lat>0&&lon<180 && lat <90){
						
						/*if(terminalinfolist[i].color=="r"){
							errorArr.push(i);
						}*/
						arr.push(new MLngLat(lon,lat));
						terminalinfo = terminalinfolist[i];
						
						if (alarm_base_info != null) {
							 var alarmFlag = alarm_base_info.substring(62,63);
							 if (alarmFlag == '1')
								 arrAlarm.push(new MLngLat(lon,lat));
						 }
						
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
				    
			    	var arrayterminalinfo = new Array();
				    arrayterminalinfo.push(terminalinfolist[0]);
				    addmarkerObjNew(arrayterminalinfo,0);
					
				   
				}
				/* if(arrAlarm.length >0){
					var lineopt = new MLineOptions(); //构建一个名为lineopt的线选项对象
					lineopt.lineStyle.color = '0xFF0000';
					lineopt.lineStyle.alpha = 1;
					
					var polyline=new MPolyline(arrAlarm, lineopt);
					
					polyline.id="LINEALARM";
				    mapObj.addOverlay(polyline,false);
				    setFitV(polyline.id,arrForFitView);
				   
				} */
		}
		else{
            //alert("GPS数据偏移异常，请重新尝试或联系地图厂商");
            tishiShow("GPS数据偏移异常，请重新尝试或联系地图厂商！");
			tishiHide();
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
		else{
            //alert("GPS数据偏移异常，请重新尝试或联系地图厂商");
            tishiShow("GPS数据偏移异常，请重新尝试或联系地图厂商！");
			tishiHide();
        }
		
	}
	else{
		//mapObj.removeAllOverlays();
		//alert("没有轨迹数据!");
		tishiShow("没有轨迹数据！");
		tishiHide();
	}

}

// 实时更新轨迹
var terminalinfolist = new Array();
var Errorterminalinfolistnew = new Array();
var Errorterminalinfolist = new Array();
// 播放位置计数
var pointIndex = 0;
// 跟新轨迹
function AscUpdateLine(){
	
	if(playstate != 0){
		setonunload();
		return;
	}
	//var terminalinfo ;
	var settime = 2000/Math.pow(2,playShudu);//(playShudu*5);//document.getElementById('huifangspeed').value;
	//alert(settime);
	if(terminalinfolist != null && terminalinfolist.length>0){

		if(terminalinfolist[0].gpsIsExc){ 
				if(pointIndex < terminalinfolist.length){//alert(pointIndex);
					
					var arrsMarker = mapObj.getOverlayById("marker");
					var lon = terminalinfolist[pointIndex].LONGITUDE;
					var lat = terminalinfolist[pointIndex].LATITUDE;
					if(lon!= null && lon!=""&& lat!=null && lat!=""&& lon!="FFFF" && lat!="FFFF"&& lon>0&& lat>0&&lon<180 && lat <90){
								
						var arrayterminalinfo = new Array();
						arrayterminalinfo.push(terminalinfolist[pointIndex]);
						addmarkerObjNew(arrayterminalinfo,pointIndex);
						//播放时间
						document.getElementById("bofangtime").innerHTML=timeoptval(terminalinfolist[pointIndex].TERMINAL_TIME);
					    //里程计算
						//mousePolylineDistance();
						//瞬时油耗
						document.getElementById("DINGWEI_STAT").innerHTML=(terminalinfolist[pointIndex].dingwei_stat!=0?'有效':'无效');
						//车速
						document.getElementById("SPEEDING").innerHTML=nullToZore(terminalinfolist[pointIndex].SPEEDING)+"&nbsp;km/h";
						//方向
						document.getElementById("DIRECTION").innerHTML=diretionToStr(terminalinfolist[pointIndex].DIRECTION);
						//转速
						document.getElementById("ENGINE_ROTATE_SPEED").innerHTML=nullToZore(terminalinfolist[pointIndex].ENGINE_ROTATE_SPEED)+"&nbsp;rpm";
						//点火状态
						document.getElementById("FIRE_UP_STATE").innerHTML=(terminalinfolist[pointIndex].STAT_INFO==0?'关':'开');
						//承载情况
						document.getElementById("LIMITE_NUMBER").innerHTML=nullToZore(terminalinfolist[pointIndex].STU_NUM)+"/"+nullToZore(terminalinfolist[pointIndex].LIMITE_NUMBER)+"人";
						//瞬时油耗
						document.getElementById("oil_instant").innerHTML=nullToZore(terminalinfolist[pointIndex].OIL_INSTANT)+"&nbsp;L/h";
						
							//图表数据
							tiBiChartXml += "<set name='"+timeoptval(terminalinfolist[pointIndex].TERMINAL_TIME)+"' value='"+nullToZore(terminalinfolist[pointIndex].SPEEDING)+"' />";
							chartInit();
						}
					pointIndex++;
					jQuery("#slider-range-min").slider( "option", "value", pointIndex );
					intreal = window.setTimeout("AscUpdateLine()",settime);
					
				}
				else{
					//alert(pointIndex);
					pointIndex = 0;
					jQuery("#slider-range-min").slider( "option", "value", pointIndex );
					document.getElementById("playimg").src="../newimages/arr_play.gif";
					document.getElementById("playimg").title="播放";
					playstate = 2;
					tiBiChartXml = "";
					chartInit();
					chartClose();
					//alert("回放结束!");
					tishiShow("回放结束！");
					tishiHide();
				}
		}
		else{
            //alert("GPS数据偏移异常，请重新尝试或联系地图厂商");
            tishiShow("GPS数据偏移异常，请重新尝试或联系地图厂商！");
			tishiHide();
        }
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
		//alert("开始时间要小于结束时间!");
		tishiShow("开始时间要小于结束时间！");
		tishiHide();
		return false;
	}
	else{
		//alert("时间范围不能大于2小时!");
		tishiShow("时间范围不能大于2小时！");
		tishiHide();
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

	if(terminalinfolist == null || terminalinfolist.length ==0){
		//alert("请点击查询按钮进行数据查询!");
		tishiShow("请点击查询按钮进行数据查询！");
		tishiHide();
		return;
	}
	
	pointIndex=0;
	jQuery("#slider-range-min").slider( "option", "value", pointIndex );
	jQuery("#slider-range-min").slider( "option", "disabled", false );
	setonunload();
	tiBiChartXml="";
	chartInit();
	//mapObj.removeAllOverlays();

	/*var markerlist = mapObj.getOverlaysByType(MOverlay.TYPE_MARKER);
	if(markerlist != null && markerlist.length>0){
		for(var i = 0; i < markerlist.length; i++){
			var markerid = markerlist[i].id;
			 if(markerid.indexOf("error") != -1){
				 mapObj.removeOverlayById(markerid);
			 }
			  
		}
	}*/
	//mapObj.removeOverlayById("marker");
		
	playstate= 0;
	document.getElementById("playimg").src="../newimages/arr_stop.gif";
	document.getElementById("playimg").title="暂停";
	
	postselectline();
	
	
}

// 播放速度减少
function kuaituiOpt(){
	var bofangbeishuObj = document.getElementById("bofangbeishu");
	if(playShudu > 1){
		playShudu = playShudu-1;
	}
	//alert(playShudu);
	bofangbeishuObj.innerHTML = "x"+Math.pow(2,playShudu)+"倍速";
}



//轨迹信息查询
function selectgj(){
	if(!timeBound()){
		return ;
	}

	if(playstate != 2){
		//alert("轨迹回放没有结束,请结束回放后在进行查询!");
		tishiShow("轨迹回放没有结束,请结束回放后在进行查询！");
		tishiHide();
		return ;
	}
	mapObj.removeAllOverlays();
	var vin = document.getElementById("vin").value;
	//alert(vin);
	var flag = document.getElementById('lookflag').value;
	//alert(flag);
	var startt = document.getElementById('line_start_time').value;
	//alert(startt);
	var endt = document.getElementById('line_end_time').value;
	//alert(endt);
	
	loaddate=1;
	//document.getElementById("loading").innerHTML="数据加载...";
	document.getElementById("loading").style.display = "";
	
	GPSDwr.getVehcileLineList(vin,startt,endt,getpostselectline);

	
}
function getpostselectline(array){
    loaddate=0;
    //document.getElementById("loading").innerHTML="加载完成!";
    document.getElementById("loading").style.display = "none";
	//alert("fff");
	terminalinfolist = array.data; 
	Errorterminalinfolist = array.errdata;
	
	//mapObj.removeAllOverlays();

	if(terminalinfolist!=null && terminalinfolist.length>0){
		terminalinfolist[terminalinfolist.length-1].color="b";
		jQuery("#slider-range-min").slider( "option", "disabled", false );
		jQuery("#slider-range-min").slider( "option", "max", terminalinfolist.length-1 );
	}
	
	drawLine(terminalinfolist);
	//var load_alarm_event = document.getElementById("load_alarm_event");

	//if(load_alarm_event.checked){
		drawErrorPoint(Errorterminalinfolist);
	//}
	
}

function showErrButton(){

	//var load_alarm_event = document.getElementById("load_alarm_event");
	//if(load_alarm_event.checked){
		hideErrorPoint();
		if(Errorterminalinfolist != null && Errorterminalinfolist.length > 0){
			drawErrorPoint(Errorterminalinfolist);
		}
	//}else{
	//	hideErrorPoint();
	//}

	
}

//移除异常点
function hideErrorPoint(){
	var markerlist = mapObj.getOverlaysByType(MOverlay.TYPE_MARKER);

	if(markerlist != null && markerlist.length > 0){
		for(var i = 0 ; i < markerlist.length; i ++ ){
			var markerid = markerlist[i].id;
			var str = markerid.split("_");
			if(str[0]=="error"){
				mapObj.removeOverlayById(markerid);
			}
		}
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
	  var boxArr = new Array();
	  var boxStr = "";
	   if(array != null && array.length > 0){
		   var ok = 0;
		   var arr = new Array();
		   jQuery("input[name=load_alarm_event]").each(function(i){
				if(jQuery(this).attr("checked")){
					boxArr.push(jQuery(this).attr("alarmid"));
				}
			});
		   boxStr = boxArr.join(",");
	       for(var j=0; j < array.length;j++){//处理超转告警
			  if(array[j].ALARM_TYPE_ID!="49")
				  arr[j]=array[j];
		   }
		   for(var i=0; i < arr.length;i++){

			 var lon = arr[i].LONGITUDE;
			 var lat = arr[i].LATITUDE;

			 //mapObj.removeOverlayById(array[i].VEHICLE_VIN);
		  	 var markerOption = new MMarkerOptions();
		  	 
// 			 if((arr[i].ALARM_TYPE_ID=="40") || arr[i].ALARM_TYPE_ID=="72"){//紧急告警
// 				 markerOption.imageUrl="../newimages/sidelayerimages/alarm01.gif";
// 			 }else if((arr[i].ALARM_TYPE_ID=="32") ||  (arr[i].ALARM_TYPE_ID=="49") || (arr[i].ALARM_TYPE_ID=="54")){//超速告警
// 				 markerOption.imageUrl="../newimages/sidelayerimages/alarm02.gif";
// 			 }else if(arr[i].ALARM_TYPE_ID=="75"){//开门
// 				 markerOption.imageUrl="../newimages/sidelayerimages/alarm05.gif";
// 			 }else{//车辆故障
// 				 markerOption.imageUrl="../newimages/sidelayerimages/alarm03.gif";
// 			 }
			 if((arr[i].ALARM_TYPE_ID=="40") ){//紧急告警
				if(boxStr.indexOf("3") > -1){
				 markerOption.imageUrl="../newimages/sidelayerimages/alarm01.gif";
				} else {
					continue;
				}
			 } else if((arr[i].ALARM_TYPE_ID=="32")){//超速告警
				if(boxStr.indexOf("1") > -1){
				 markerOption.imageUrl="../newimages/sidelayerimages/alarm02.gif";
				} else {
					continue;
				}
			 } else if(arr[i].ALARM_TYPE_ID=="49"){ //超转告警
				 continue;
			 } else if(arr[i].ALARM_TYPE_ID=="75"){//开门告警
				 if(boxStr.indexOf("4") > -1){
				 	markerOption.imageUrl="../newimages/sidelayerimages/alarm05.gif";
				 } else {
					 continue;
				 }
			 } else if(arr[i].ALARM_TYPE_ID=="72"){//超载
				 continue;
			 } else if(arr[i].ALARM_TYPE_ID=="54"){//未鉴权驾驶
				 continue;
			 } else if((arr[i].ALARM_TYPE_ID=="73" || arr[i].ALARM_TYPE_ID=="74" ||
					 arr[i].ALARM_TYPE_ID=="79" || arr[i].ALARM_TYPE_ID=="80")){//乘车异常
				if(boxStr.indexOf("2") > -1){
				 	markerOption.imageUrl="../newimages/sidelayerimages/alarm06.gif";
				} else {
					continue;
				}
			 } else if(boxStr.indexOf("5") > -1){
//			 } else if("09,10,13,25,26,64,65,66,67,68,69,70,71".indexOf(arr[i].ALARM_TYPE_ID) > -1 && boxStr.indexOf("5") > -1){//车辆故障
				 markerOption.imageUrl="../newimages/sidelayerimages/alarm03.gif";
// 				 continue;
			 } else {continue;}
			 
		     markerOption.imageSize = new MSize(16,16);
		     markerOption.imageAlign=MConstants.MIDDLE_CENTER;
		     if(arr[i].DIRECTION!="FFFF"){
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

			 mapObj.addEventListener(marker,MConstants.MOUSE_CLICK,clickMouse);
			 
		}
	   }
	   else{
			//alert("没有坐标数据,无法标点!");
			tishiShow("没有异常点数据！");
			tishiHide();
	   }
	   

}



//数据加载中0未加载，1加载中
var loaddate = 0;

// 点击播放按钮
function postselectline(){

	if(terminalinfolist != null&&terminalinfolist.length>0){
		mapObj.removeOverlayById(terminalinfolist[0].VEHICLE_VIN);
	}
	playstate=0;
	AscUpdateLine();

}

//暂停
function zhantingOpt(){
	if(loaddate==1){
		return;
	}
	playstate= 1;
	setonunload();
	document.getElementById("playimg").src="../newimages/arr_play.gif";
	document.getElementById("playimg").title="播放";
	
	
}

// 播放
function baofangOpt(){
	
	if(terminalinfolist == null || terminalinfolist.length ==0){
		//alert("请点击查询按钮进行数据查询!");
		tishiShow("请点击查询按钮进行数据查询！");
		tishiHide();
		return;
	}
    //alert(playstate);
	// 为播放时
	if(playstate==2){
		
		if(loaddate==1){
			return;
		}
		//playstate=0;
		//document.getElementById("loading").innerHTML="";
		
		if(terminalinfolist != null&&terminalinfolist.length>0){
			mapObj.removeOverlayById(terminalinfolist[0].VEHICLE_VIN);
			//mapObj.removeOverlaysByType(MOverlay.TYPE_MARKER);
			playstate=0;
			document.getElementById("playimg").src="../newimages/arr_stop.gif";
			document.getElementById("playimg").title="暂停";
			//jQuery("#bitiChar").css("display","block");
			chartOpen();
			postselectline();
			alarminfoshow("none", "block");
			//alarmInfoView(pointIndex);
		}
		else{
			playstate=2;
			document.getElementById("playimg").src="../newimages/arr_play.gif";
			document.getElementById("playimg").title="播放";
			//alert("没有可播放数据!");
			tishiShow("没有可播放数据！");
			tishiHide();
		}
		
		
	}
	// 暂停时
	else if(playstate==1){
		if(loaddate==1){
			return;
		}
		//document.getElementById("loading").innerHTML="";
		playstate=0;
		document.getElementById("playimg").src="../newimages/arr_stop.gif";
		document.getElementById("playimg").title="暂停";
		chartOpen();
		AscUpdateLine();

		alarminfoshow("none", "block");
		
	}
	else if(playstate==0){
		//alert("ting");
		//zhantingOpt();
		if(loaddate==1){
			return;
		}
		playstate= 1;
		setonunload();
		document.getElementById("playimg").src="../newimages/arr_play.gif";
		document.getElementById("playimg").title="播放";
	}
	
	jQuery("#slider-range-min").slider( "option", "disabled", false );
}

// 结束
function jieshuOpt(){
	
	if(loaddate==1){
		return;
	}
	/*setonunload();
	pointIndex=0;
	playstate = 2;
	document.getElementById("playimg").src="../newimages/arr_play.gif";
	document.getElementById("playimg").title="播放";
	document.getElementById("MapbarDiv").style.display = "none";
	isshow=false;
	document.getElementById("bofangtime").innerHTML="";
    document.getElementById("DINGWEI_STAT").innerHTML="";
    document.getElementById("SPEEDING").innerHTML="";
    document.getElementById("DIRECTION").innerHTML="";
    document.getElementById("ENGINE_ROTATE_SPEED").innerHTML="";
    document.getElementById("FIRE_UP_STATE").innerHTML="";
    document.getElementById("LIMITE_NUMBER").innerHTML="";
    document.getElementById("oil_instant").innerHTML="";
    //document.getElementById("loading").innerHTML="";
	//mapObj.removeAllOverlays();
	jQuery("#slider-range-min").slider( "option", "value", pointIndex );
	jQuery("#slider-range-min").slider( "option", "disabled", true  );
    removeMarker();
    alarminfoshow("none", "block");*/

    //结束后清除图表
    chartClose();
    tiBiChartXml = "";
    
	if(terminalinfolist == null || terminalinfolist.length ==0){
		//alert("请点击查询按钮进行数据查询!");
		tishiShow("请点击查询按钮进行数据查询！");
		tishiHide();
		return;
	}
    setonunload();
	pointIndex=0;
	playstate = 2;
	document.getElementById("playimg").src="../newimages/arr_play.gif";
	document.getElementById("playimg").title="播放";
	document.getElementById("MapbarDiv").style.display = "none";
	//isshow=false;
	document.getElementById("bofangtime").innerHTML="";
    document.getElementById("DINGWEI_STAT").innerHTML="";
    document.getElementById("SPEEDING").innerHTML="";
    document.getElementById("DIRECTION").innerHTML="";
    document.getElementById("ENGINE_ROTATE_SPEED").innerHTML="";
    document.getElementById("FIRE_UP_STATE").innerHTML="";
    document.getElementById("LIMITE_NUMBER").innerHTML="";
    document.getElementById("oil_instant").innerHTML="";
	//pointIndex=0;
	jQuery("#slider-range-min").slider( "option", "value", pointIndex );
	jQuery("#slider-range-min").slider( "option", "disabled", true );
	//mapObj.removeOverlaysByType(MOverlay.TYPE_MARKER);
	
	if(terminalinfolist!=null && terminalinfolist.length>0){
		terminalinfolist[terminalinfolist.length-1].color="b";
		jQuery("#slider-range-min").slider( "option", "disabled", false );
		jQuery("#slider-range-min").slider( "option", "max", terminalinfolist.length-1 );
	}
	
	drawLine(terminalinfolist);
}

function removeMarker(){
	var markerlist = mapObj.getOverlaysByType(MOverlay.TYPE_MARKER);
	var lineid = mapObj.getOverlayById();
	if(markerlist != null && markerlist.length>0){
		for(var i = 0; i < markerlist.length; i++){
			var markerid = markerlist[i].id;
			 if(markerid.indexOf("error") != -1){
				 mapObj.removeOverlayById(markerid);
			 }
			  
		}
	}
	//if(lineid != null && lineid != "" && lineid != "undefined"){
		mapObj.removeOverlayById("LINE");
		mapObj.removeOverlayById("marker");
		terminalinfolist = null;
	//}
}

// 播放数据增加
function kuaijinOpt(){
	var bofangbeishuObj = document.getElementById("bofangbeishu");
	if(playShudu < 4){
		playShudu = playShudu+1;
	}
	//alert(playShudu);
	bofangbeishuObj.innerHTML =  "x"+Math.pow(2,playShudu) +"倍速";
	
	//alert(Math.pow(2,0));
}

// 返回到尾
function weibuOpt(){
	
	if(loaddate==1){
		return;
	}
	if(terminalinfolist != null && terminalinfolist.length > 0){
		//pointIndex=terminalinfolist.length-1;
		playstate = 2;
		document.getElementById("playimg").src="../newimages/arr_play.gif";
		document.getElementById("playimg").title="播放";
		setonunload();
		//mapObj.removeAllOverlays();
		/*var markerlist = mapObj.getOverlaysByType(MOverlay.TYPE_MARKER);
		var lineid = mapObj.getOverlayById();
		if(markerlist != null && markerlist.length>0){
			for(var i = 0; i < markerlist.length; i++){
				var markerid = markerlist[i].id;
				 if(markerid.indexOf("error") != -1){
					 mapObj.removeOverlayById(markerid);
				 }
				  
			}
		}*/
			//mapObj.removeOverlayById("LINE");
			//mapObj.removeOverlayById("marker");
			//alert(terminalinfolist.length);
			//alarmInfoView(terminalinfolist.length-1);
		drawLine(terminalinfolist);
		
		pointIndex = terminalinfolist.length-2;

		playstate=0;
		AscUpdateLine();
		jQuery("#slider-range-min").slider( "option", "value", pointIndex );
		jQuery("#slider-range-min").slider( "option", "disabled", true );
	
		chartClose();
		
		//var arrayterminalinfo = new Array();
	    //arrayterminalinfo.push(terminalinfolist[terminalinfolist.length-1]);
	   // addmarkerObjNew(arrayterminalinfo,0);
		
		//alert("回放结束!");
		tishiShow("回放结束！");
		tishiHide();
	}else{
		//alert("没有可播放数据!");
		tishiShow("没有可播放数据！");
		tishiHide();
	}
	
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


jQuery(function() {
	jQuery("#slider-range-min").slider({
		animate: true ,
		
		range: "min",
		value: 0,
		min: 0,
		max: 100,
		slide: function(event, ui) {
			//$("#amount").val('$' + ui.value);
			//alert(ui.value);
			//alert("slide"+ui.value);
			pointIndex = ui.value;

			
			//alarmInfoView(pointIndex);
		},
		start: function(event, ui){ 
			//alert("start"+ui.value);
		}

	});
	jQuery("#slider-range-min").slider( "option", "disabled", true );
	//jQuery("#slider-range-min").slider( "option", "disabled", false );
	
});


function alarmInfoView(length){
	
	mapObj.removeOverlaysByType(MOverlay.TYPE_MARKER);

	var lon = terminalinfolist[length].LONGITUDE;
	var lat = terminalinfolist[length].LATITUDE;

	   var oldmarker = mapObj.getOverlayById("marker");
	   if(oldmarker == null ){
		   var markerOption = new MMarkerOptions();
		     markerOption.imageUrl="../images/arrow_blue.gif";
		     markerOption.imageSize = new MSize(14,32);
		     markerOption.imageAlign=MConstants.MIDDLE_CENTER;
		     if(terminalinfolist[length].DIRECTION!="FFFF"){
				   hudu = terminalinfolist[length].DIRECTION;
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
			 MapMoveToPoint(lon,lat);
	   }
	   else{
		   if(terminalinfolist[length].DIRECTION!="FFFF"){
			   hudu = terminalinfolist[length].DIRECTION;
		 	}
	     	
		   mapObj.markerMoveTo("marker", new MLngLat(lon,lat), hudu, 0);
	   }




	
	for(var i=0; i < length; i++){

		if(terminalinfolist[i].color=="r" ){
			  
				// mapObj.removeOverlayById(terminalinfolist[i].VEHICLE_VIN);
			  	 var markerOption = new MMarkerOptions();
			  	 
// 				 if((terminalinfolist[i].ALARM_TYPE_ID=="40") || terminalinfolist[i].ALARM_TYPE_ID=="72"){//紧急告警
// 					 markerOption.imageUrl="../newimages/sidelayerimages/alarm01.gif";
// 				 }else if((terminalinfolist[i].ALARM_TYPE_ID=="32") ||  (terminalinfolist[i].ALARM_TYPE_ID=="49") || (terminalinfolist[i].ALARM_TYPE_ID=="54")){//超速告警,超转告警,未鉴权
// 					 markerOption.imageUrl="../newimages/sidelayerimages/alarm02.gif";
// 				 }else if(terminalinfolist[i].stat_info_door == "1"){//开门
// 					 markerOption.imageUrl="../newimages/sidelayerimages/alarm05.gif";
// 				 }else{//车辆故障
						
// 					 markerOption.imageUrl="../newimages/sidelayerimages/alarm03.gif";
// 				 }
				 
			  	if((terminalinfolist[i].ALARM_TYPE_ID=="40") ){//紧急告警
					if(boxStr.indexOf("3") > -1){
					 markerOption.imageUrl="../newimages/sidelayerimages/alarm01.gif";
					} else {
						continue;
					}
				 } else if((terminalinfolist[i].ALARM_TYPE_ID=="32")){//超速告警
					if(boxStr.indexOf("1") > -1){
					 markerOption.imageUrl="../newimages/sidelayerimages/alarm02.gif";
					} else {
						continue;
					}
				 } else if(terminalinfolist[i].ALARM_TYPE_ID=="49"){ //超转告警
					 continue;
				 } else if(terminalinfolist[i].ALARM_TYPE_ID=="75"){//开门告警
					 if(boxStr.indexOf("4") > -1){
					 	markerOption.imageUrl="../newimages/sidelayerimages/alarm05.gif";
					 } else {
						 continue;
					 }
				 } else if(terminalinfolist[i].ALARM_TYPE_ID=="72"){//超载
					 continue;
				 } else if(terminalinfolist[i].ALARM_TYPE_ID=="54"){//未鉴权驾驶
					 continue;
				 } else if((terminalinfolist[i].ALARM_TYPE_ID=="73" || terminalinfolist[i].ALARM_TYPE_ID=="74" ||
						 terminalinfolist[i].ALARM_TYPE_ID=="79" || terminalinfolist[i].ALARM_TYPE_ID=="80")){//乘车异常
					if(boxStr.indexOf("2") > -1){
					 	markerOption.imageUrl="../newimages/sidelayerimages/alarm06.gif";
					} else {
						continue;
					}
				 } else if(boxStr.indexOf("5") > -1){
//				 } else if("09,10,13,25,26,64,65,66,67,68,69,70,71".indexOf(terminalinfolist[i].ALARM_TYPE_ID) > -1 && boxStr.indexOf("5") > -1){//车辆故障
					 markerOption.imageUrl="../newimages/sidelayerimages/alarm03.gif";
//	 				 continue;
				 } else {continue;}
			     
			     markerOption.imageSize = new MSize(16,16);
			     markerOption.imageAlign=MConstants.MIDDLE_CENTER;
			     if(terminalinfolist[i].DIRECTION!="FFFF"){
					   hudu = terminalinfolist[i].DIRECTION;
				 }
			     markerOption.rotation = "0";

			     markerOption.picAgent=false;
				 markerOption.isEditable=false; //设置点是否可编辑。
				 markerOption.hasShadow=false;  //是否显示阴影。	
				 markerOption.zoomLevels=[]; //设置点的缩放级别范围。
				 markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
				 markerOption. dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
			     
			     var marker = new MMarker(new MLngLat(terminalinfolist[i].LONGITUDE,terminalinfolist[i].LATITUDE),markerOption);
				 marker.id="error"+i;
				 mapObj.addOverlay(marker,false);

				 mapObj.addEventListener(marker,MConstants.MOUSE_CLICK,clickMouse);
				 //MapMoveToPoint(lon,lat);
	   }
	}
}

/**
* 行驶线路查询
*/
function tabRunLine(){
	if(playstate != 2){
		//alert("轨迹回放没有结束,请结束回放后在进行查询!");
		tishiShow("轨迹回放没有结束,请结束回放后在进行查询！");
		tishiHide();
		return ;
	}

	mapObj.removeAllOverlays();
	var vin = document.getElementById("vin").value;
	var timetab_time = document.getElementById("timetab_time").value;
	GPSDwr.tabRunLineDwr(vin,timetab_time,tabRunLineDwr_callback);
}
function tabRunLineDwr_callback(data){

	var timetab_time = document.getElementById("timetab_time");
	var selectobj = document.getElementById("selectDriveingNode");
	
	if(selectobj.options.length >0){
		
		for(var i = selectobj.options.length; i >= 0; i--){
			//alert(selectobj.options.length);
			selectobj.remove(i);
		}
		
	}	

	if(data != null && data.length >0){

		for(var i = 0; i < data.length; i++){
			var id = i;
			var ROUTE_NAME = data[i].ROUTE_NAME !=null ? data[i].ROUTE_NAME :" ";
			var START_TIME = data[i].START_TIME;
			var END_TIME = data[i].END_TIME;
			var TRIP_ID = data[i].TRIP_ID;
			var VIN = data[i].VEHICLE_VIN;
			
			var optionobj = document.createElement('option');
			optionobj.value = TRIP_ID + "-" + VIN + "-" + START_TIME+"-" + END_TIME;
			optionobj.text = (id+1) +" "+ ROUTE_NAME + ". " + " " + START_TIME+ "-" +END_TIME;
			selectobj.add(optionobj);
		}
		jQuery("#selectDriveingNode").attr("title", jQuery("#selectDriveingNode").find("option:selected").text());
		loadRunLinesiteList(selectobj.value,timetab_time.value);

		/*var yusheObj = selectobj
		var yusheInfo = yusheObj.value;
		var yusheInfotext = yusheObj.options[yusheInfo].text;
		var vallist = yusheInfotext.split("  ")[0].split("-");
		timeonclick(vallist[0],vallist[1]);*/
	}
	else{
		removeMarker();
	    alarminfoshow("none", "block");
		tishiShow("没有行驶线路！");
		tishiHide();
	}
	
}

//行车记录查询
function tabNodeSelect(){

	if(playstate != 2){
		//alert("轨迹回放没有结束,请结束回放后在进行查询!");
		tishiShow("轨迹回放没有结束,请结束回放后在进行查询！");
		tishiHide();
		return ;
	}
	mapObj.removeAllOverlays();
	var organizationid = document.getElementById("organizationid").value;
	var vin = document.getElementById("vin").value;
	var timetab_time = document.getElementById("timetab_time").value;

	GPSDwr.tabNodeSelectDwr(organizationid,vin,timetab_time,tabNodeSelectDwr_callback);
	
}
function tabNodeSelectDwr_callback(data){
	var selectobj = document.getElementById("selectDriveingNode");
	if(selectobj.options.length >0){
		
		for(var i = selectobj.options.length; i >= 0; i--){
			//alert(selectobj.options.length);
			selectobj.remove(i);
		}
	}

	if(data != null && data.length >0){
		for(var i = 0; i < data.length; i++){
			var id = i;
			var starttime = data[i].on_date;
			var endtime = data[i].off_date;
			var mileage = data[i].mileage;
			var optionobj = document.createElement('option');
			optionobj.value = id;
			optionobj.text = starttime+ "-" +endtime+ "  " +mileage+"km";
			selectobj.add(optionobj);
		}

		var yusheObj = selectobj
		var yusheInfo = yusheObj.value;
		var yusheInfotext = yusheObj.options[yusheInfo].text;
		var vallist = yusheInfotext.split("  ")[0].split("-");
		timeonclick(vallist[0],vallist[1]);
	}
	else{
		removeMarker();
	    alarminfoshow("none", "block");
		tishiShow("没有行车记录！");
		tishiHide();
	}
}

function selectbutton(){
	
	if(playstate != 2){
		//alert("轨迹回放没有结束,请结束回放后在进行查询!");
		tishiShow("轨迹回放没有结束,请结束回放后在进行查询！");
		tishiHide();
		return ;
	}
	
	var selectModel = document.getElementById("selectModel").value;

	var timetab_time = document.getElementById("timetab_time").value;
	
	var yusheObj = document.getElementById('selectDriveingNode');
	var yusheInfo = yusheObj.value;
	jQuery("#selectDriveingNode").attr("title", jQuery("#selectDriveingNode").find("option:selected").text());

	mapObj.removeAllOverlays();
	if(selectModel == "1"){// 行车记录
		var yusheInfotext = yusheObj.options[yusheInfo].text;
	
		var vallist = yusheInfotext.split("  ")[0].split("-");
		//alert(vallist.length+";"+vallist[0]+";"+vallist[1]);		
		timeonclick(vallist[0],vallist[1]);
	}else if(selectModel == "3"){// 行驶线路
		loadRunLinesiteList(yusheInfo,timetab_time);
	}
	
	document.getElementById("bofangtime").innerHTML="";
    document.getElementById("DINGWEI_STAT").innerHTML="";
    document.getElementById("SPEEDING").innerHTML="";
    document.getElementById("DIRECTION").innerHTML="";
    document.getElementById("ENGINE_ROTATE_SPEED").innerHTML="";
    document.getElementById("FIRE_UP_STATE").innerHTML="";
    document.getElementById("LIMITE_NUMBER").innerHTML="";
    document.getElementById("oil_instant").innerHTML="";
    //document.getElementById("loading").innerHTML="";
}



/*
 * 加载站点到地图
 */
function loadRunLinesiteList(infos,day){
	//DWREngine.setAsync(false);
	
	loaddate=1;
	//document.getElementById("loading").innerHTML="数据加载...";
	document.getElementById("loading").style.display = "";
	
	GPSDwr.getRunLineSiteList(infos,day,getRunLineSiteList_callback);

	//DWREngine.setAsync(true);
	
	var time = document.getElementById('timetab_time').value;
	//alert(infos);
	var infos = infos.split("-");
	var tripid = infos[0];
	var vin = infos[1];
	var starttime = infos[2];
	var endtime = infos[3];

	var startt = time+" " + starttime;
	//alert(startt);
	var endt = time+" " + endtime;
	
	//alert(infos+","+day);

	GPSDwr.getVehcileLineList(vin,startt,endt,getpostselectline);
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


function timeonclick(start,end){
	
	//mapObj.removeAllOverlays();
	var vin = document.getElementById("vin").value;

	var time = document.getElementById('timetab_time').value;
	
	var startt = time+" " + start;
	//alert(startt);
	var endt = time+" " + end;
	//alert(endt);
	
	loaddate=1;
	//document.getElementById("loading").innerHTML="数据加载...";
	document.getElementById("loading").style.display = "";
	
	GPSDwr.getVehcileLineList(vin,startt,endt,getpostselectline);
}

function selectControl(){

	
	var val = document.getElementById("selectModel").value;
	if(val == "1"){
		tabNodeSelect();
	}else if(val == "2"){
		selectgj();
	}else if(val == "3"){
		tabRunLine();
	}
	alarminfoshow("none", "block");
	document.getElementById("bofangtime").innerHTML="";
    document.getElementById("DINGWEI_STAT").innerHTML="";
    document.getElementById("SPEEDING").innerHTML="";
    document.getElementById("DIRECTION").innerHTML="";
    document.getElementById("ENGINE_ROTATE_SPEED").innerHTML="";
    document.getElementById("FIRE_UP_STATE").innerHTML="";
    document.getElementById("LIMITE_NUMBER").innerHTML="";
    document.getElementById("oil_instant").innerHTML="";
    //document.getElementById("loading").innerHTML="";
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

function hideshowresultDiv(flag){
	//alert(111);
	if(flag==1){
		jQuery('#Layer1').css('display','none');
	}
	else if(flag==0){
		jQuery('#Layer1').css('display','block');
	}
	
	
}
function tishiShow(info){
	
	hideshowresultDiv(0);
	document.getElementById("inforeault").innerHTML=info;
}
function tishiHide(){
	window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);
}

function testspeed(){
	mapObj = null;
	document.getElementById("bitiCenter").innerHTML = "";

}

//打开打印页面
function openprintPage(){

	var lineobj = mapObj.getOverlaysByType(MOverlay.TYPE_POLYLINE);
	if(lineobj == null || lineobj.length==0){
		tishiShow("请选择要打印的轨迹！");
		tishiHide();
		return;
	}
	//alert(terminalinfolist[terminalinfolist.length-1].DRIVER_NAME);
	//pointIndex
	var selectModel = document.getElementById("selectModel").value;
	var vin = document.getElementById("vin").value;
	
	//是否加载告警
	var alarmArray = new Array();
	jQuery(":checked",jQuery("#alarmParm")).each(function(i){
		alarmArray.push(jQuery(this).attr("alarmid"));
	});
	
	var load_alarm_event = alarmArray.length > 0?alarmArray.join(","):"";

	var lon = document.getElementById("lon").value;
	var lat = document.getElementById("lat").value;

	if(selectModel == "3"){// 行驶线路，需要站点
		var day = document.getElementById("timetab_time").value;
		var selectDriveingNode = document.getElementById("selectDriveingNode").value;
		if(selectDriveingNode!=null && selectDriveingNode !=""&& selectDriveingNode !="0"){
			var infos = selectDriveingNode.split("-");
			var tripid = infos[0];
			var startt = day+" "+infos[2];
			var endt = day+" "+infos[3];

			window.open("<s:url value='/gps/bitlookPrintAction.shtml' />?terminalViBean.selectModel="+selectModel
					+"&terminalViBean.VEHICLE_VIN="+vin
					+"&terminalViBean.START_TIME="+startt
					+"&terminalViBean.END_TIME="+endt
					+"&terminalViBean.load_alarm_event="+load_alarm_event
					+"&terminalViBean.TRIP_ID="+tripid
					+'&terminalViBean.LONGITUDE='+lon+'&terminalViBean.LATITUDE='+lat+"&terminalViBean.pointIndex="+pointIndex 
					+'&terminalViBean.DRIVER_NAME='+encodeURIComponent(encodeURIComponent(terminalinfolist[terminalinfolist.length-1].DRIVER_NAME)),
					"",
					"toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=yes, width=800, height=730",false);
		}else{
			window.open("<s:url value='/gps/bitlookPrintAction.shtml' />?terminalViBean.selectModel="+selectModel
					+"&terminalViBean.VEHICLE_VIN="+vin
					+"&terminalViBean.START_TIME="+""
					+"&terminalViBean.END_TIME="+""
					+"&terminalViBean.load_alarm_event="+load_alarm_event
					+"&terminalViBean.TRIP_ID="+""
					+'&terminalViBean.LONGITUDE='+lon+'&terminalViBean.LATITUDE='+lat+"&terminalViBean.pointIndex="+pointIndex 
					+'&terminalViBean.DRIVER_NAME='+encodeURIComponent(encodeURIComponent(terminalinfolist[terminalinfolist.length-1].DRIVER_NAME)),
					"",
					"toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=yes, width=800, height=730",false);
		}
		
		
	}else if(selectModel =="1"){ // 日行车记录
		var day = document.getElementById("timetab_time").value;
		var selectDriveingNode = document.getElementById("selectDriveingNode");
		
		if(selectDriveingNode.value !=null && selectDriveingNode.value != ""){
			var yusheInfo = selectDriveingNode.value;
			var yusheInfotext = selectDriveingNode.options[yusheInfo].text;
			var vallist = yusheInfotext.split("  ")[0].split("-");
			var startt = day+" "+ vallist[0];
			var endt   = day+" "+ vallist[1];
			window.open("<s:url value='/gps/bitlookPrintAction.shtml' />?terminalViBean.selectModel="+selectModel
					+"&terminalViBean.VEHICLE_VIN="+vin
					+"&terminalViBean.START_TIME="+startt
					+"&terminalViBean.END_TIME="+endt
					+"&terminalViBean.load_alarm_event="+load_alarm_event
					+'&terminalViBean.LONGITUDE='+lon+'&terminalViBean.LATITUDE='+lat+"&terminalViBean.pointIndex="+pointIndex
					+'&terminalViBean.DRIVER_NAME='+encodeURIComponent(encodeURIComponent(terminalinfolist[terminalinfolist.length-1].DRIVER_NAME)),
					"",
					"toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=yes, width=800, height=730",false);
		}else{
			window.open("<s:url value='/gps/bitlookPrintAction.shtml' />?terminalViBean.selectModel="+selectModel
					+"&terminalViBean.VEHICLE_VIN="+vin
					+"&terminalViBean.START_TIME="+""
					+"&terminalViBean.END_TIME="+""
					+"&terminalViBean.load_alarm_event="+load_alarm_event
					+'&terminalViBean.LONGITUDE='+lon+'&terminalViBean.LATITUDE='+lat+"&terminalViBean.pointIndex="+pointIndex
					+'&terminalViBean.DRIVER_NAME='+encodeURIComponent(encodeURIComponent(terminalinfolist[terminalinfolist.length-1].DRIVER_NAME)),
					"",
					"toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=yes, width=800, height=730",false);
		}
		

		

	}else{// 任意时间段
		var line_start_time = document.getElementById("line_start_time").value;
		var line_end_time = document.getElementById("line_end_time").value;
		
		window.open("<s:url value='/gps/bitlookPrintAction.shtml' />?terminalViBean.selectModel="+selectModel
				+"&terminalViBean.VEHICLE_VIN="+vin
				+"&terminalViBean.START_TIME="+line_start_time
				+"&terminalViBean.END_TIME="+line_end_time
				+"&terminalViBean.load_alarm_event="+load_alarm_event
				+'&terminalViBean.LONGITUDE='+lon+'&terminalViBean.LATITUDE='+lat+"&terminalViBean.pointIndex="+pointIndex
				+'&terminalViBean.DRIVER_NAME='+encodeURIComponent(encodeURIComponent(terminalinfolist[terminalinfolist.length-1].DRIVER_NAME)),
				"",
				"toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=yes, width=800, height=730",false);
	}
	
	
	
	
}



/**POP自适应-suyingtao
 * 1、	对于浏览器网页内容body的高度大于900时，使用655*716像素的页面进行调用
 */

jQuery( function() {
     jQuery("#bgdiv").css('display','');
	 jQuery(window.parent.iframeshowArea).resize(function(){
		styleControl();
		chartCreate();
		jQuery("#alarmParm").css("margin-top",jQuery("#bitiCenter").css("height").replace("px","")-22);
		jQuery("#bitiDoor").css("margin-left",jQuery("#bitiCenter").css("width").replace("px","")-50);
		jQuery("#bitiClose").css("margin-left",jQuery("#bitiCenter").css("width").replace("px","")-30);
// 		jQuery("#bitiChar").css("margin-top",jQuery("#bitiCenter").css("height").replace("px",""));
		
	});
	styleControl();
	jQuery("#alarmParm").css("margin-top",jQuery("#bitiCenter").css("height").replace("px","")-20);
	jQuery("#bitiDoor").css("margin-left",jQuery("#bitiCenter").css("width").replace("px","")-50);
	jQuery("#bitiClose").css("margin-left",jQuery("#bitiCenter").css("width").replace("px","")-40);
// 	jQuery("#bitiChar").css("margin-top",jQuery("#bitiCenter").css("height").replace("px",""));
	
});

var popWidth ="500px";
var popMaxWidth ="655px";
var popHeight = "484px";
var popMaxHeiht = "660px";

function styleControl(){
	if(jQuery(window.parent.iframeshowArea).width() > 500 && jQuery(window.parent.iframeshowArea).height() > 645){
		jQuery('#bgdiv').width("100%");
		jQuery('#bgdiv').height(popMaxHeiht);
	
		jQuery('#loading').css("height","660");
		jQuery('#loading').css("width","655");
		
		jQuery('#bitmap').height("501px");
		jQuery('#bitiCenter').height("456px");	

		jQuery('.text_bg72').css("width","124");
		(jQuery('.text_bg72').removeClass("text_bg72")).toggleClass("text_bg118");
		jQuery('#alarm_type_name').css("background","url(../newimages/input_bg_118.png) no-repeat left top");
		jQuery('#alarm_type_name').css("width","113");		
	}
	else{		
		jQuery('#bgdiv').width("100%");
		jQuery('#bgdiv').height(popHeight);

		jQuery('#loading').css("height","484");
		jQuery('#loading').css("width","500");

		jQuery('#bitmap').height("325px");
		jQuery('#bitiCenter').height("280px");

		jQuery('.text_bg118').css("width","86");
		(jQuery('.text_bg118').removeClass("text_bg118")).toggleClass("text_bg72");
		jQuery('#alarm_type_name').css("background","url(../newimages/info_bg72.png) no-repeat left top");
		jQuery('#alarm_type_name').css("width","67");
	}	
}
function charOver(obj){
	jQuery(obj).removeClass("charOut");
	jQuery(obj).addClass("charOver");
}
function charOut(obj){
	jQuery(obj).removeClass("charOver");
	jQuery(obj).addClass("charOut");
}
function dispayAlarmDv(flag){
	if(flag){
		//jQuery("#alarmParm").slideDown(500,function(){});
		jQuery("#alarmParm").show();
	} else {
		//jQuery("#alarmParm").slideUp(500,function(){});
		jQuery("#alarmParm").hide();
	}

}
</script>