<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>

    
<script type="text/javascript">

var real_mereg=/^\d+$/;
//单车监控加载地图对象
var real_mapObj = null; 
var real_vinName = "";
var real_intreal = 0;
var real_flash = 10000;
var real_oldflash = 10000;
var ral_resultShowflash= 2000;
//刷新次数
var real_flashtime = 0;

function init() {
	document.getElementById("focus_txt").focus();
	document.getElementById("focusDiv").style.display = "none";  
	real_mapObj =  sidemapInit("realiCenter");

	real_mapObj.addEventListener(real_mapObj,MConstants.MAP_READY,map_ready);
	
	
	
}
function map_ready(param){  
	real_vinName = document.getElementById("real_vin").value ;
	real_getdialogArguments();
	
}

function real_getdialogArguments(){
	
	GPSDwr.getRealVehcileByVin(real_vinName,getRealVehcileByVin_call_back);
  
	function getRealVehcileByVin_call_back(data){
		//alert(data.length);
		if(data != null && data.length>0){
			
			real_AscUpdateLine(data);
			//alert("end");
			window.clearInterval(real_intreal);

			if(real_flashtime > 0){
				real_flashtime = real_flashtime-1;
				real_intreal= window.setTimeout("real_getdialogArguments()",real_flash);
			}
			else{
				real_intreal= window.setTimeout("real_getdialogArguments()",real_oldflash);
			}	 
		}
	}
}

function real_AscUpdateLine(terminalinfolist){
	var terminalinfo ;

	if(terminalinfolist != null && terminalinfolist.length>0){

		var arr = new Array();
		var lon = terminalinfolist[0].LONGITUDE;
		var lat = terminalinfolist[0].LATITUDE;
		//alert(lon+","+lat);
		if(lon!= null && lon!=""&& lat!=null && lat!=""&& lon!="FFFF" && lat!="FFFF"){
			//alert("real_mapObj == null->" + (real_mapObj == null));
			var arrsline = real_mapObj.getOverlayById("LINEONE");
				//alert(arrsline);
			var arrsMarker = real_mapObj.getOverlayById("MARKERONE");
			
			if(arrsline){
				//alert("line=true");
				arrsline.lnglatArr.push(new MLngLat(lon,lat));
				real_mapObj.updateOverlay(arrsline);
			}
			else{
				
				arr.push(new MLngLat(lon,lat));
				var polyline=new MPolyline(arr);
				polyline.id="LINEONE";
				real_mapObj.addOverlay(polyline,false);
				//alert("line=false");
			}

			if(arrsMarker){
				//alert("456");
				real_addmarkerObj(terminalinfolist,false);
			}else{
				//alert("789");
				real_addmarkerObj(terminalinfolist,false);
				real_mapObj.setCenter(new MLngLat(lon,lat));
				real_mapObj.setZoomLevel(17);
				
			}	
		}		
	}
}

var hudu = 0;
function real_addmarkerObj(array,flagg){
	
	//real_mapObj.removeOverlaysByType(MOverlay.TYPE_MARKER);
	//alert("000");
	var arr = new Array();
	if(array != null && array.length > 0){
		var ok = 0;
		var arr = new Array();
		for(var i=0; i < array.length;i++){
		
			var lon = array[i].LONGITUDE;
			var lat = array[i].LATITUDE;

			//点的属性设置
			var markerOption = new MMarkerOptions(); 
			
			if(array[i].color=="b"){
			 	markerOption.imageUrl="../newimages/arrow_blue.png";
			}
			else if(array[i].color=="r"){
			 	markerOption.imageUrl="../newimages/arrow_red.png";
			}
			else{
			 	markerOption.imageUrl="../newimages/arrow_gray.png";
			}
			markerOption.imageSize = new MSize(14,32);
			markerOption.imageAlign = MConstants.MIDDLE_CENTER;

			markerOption.picAgent=false;
			markerOption.isEditable=false; //设置点是否可编辑。
			markerOption.hasShadow=false;  //是否显示阴影。	
			markerOption.zoomLevels=[]; //设置点的缩放级别范围。
			markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
			markerOption. dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
			  
			  
			if(array[i].DIRECTION!="FFFF"){
				   hudu = array[i].DIRECTION;
			}
			//alert("real_addmarkerObj");
			markerOption.rotation = hudu;
			var marker = new MMarker(new MLngLat(lon,lat),markerOption);
			marker.id="MARKERONE";
			real_mapObj.addOverlay(marker,false);
			real_MapMoveToPoint(lon,lat);
			real_fudongInfoShow(array[i]);		   
		}
	
	}
	else{
		alert("没有坐标数据,无法标点!");
	}
}
/**
* 超出地图范围地图移动到点的位置
*/
function real_MapMoveToPoint(lon,lat){
	var bounds=real_mapObj.getLngLatBounds();
	//alert(bounds.southWest.lngX+","+bounds.southWest.latY+";"+bounds.northEast.lngX+","+bounds.northEast.latY);

	if(lon < bounds.southWest.lngX || lat < bounds.southWest.latY || lon > bounds.northEast.lngX || lat > bounds.northEast.latY){
		real_mapObj.panTo(new MLngLat(lon,lat));
	}
}
/**
* 信息区赋值
*/
function real_fudongInfoShow(TerminalViBean){
	//document.getElementById("vLn").innerHTML = TerminalViBean.VEHICLE_LN;
	document.getElementById("real_STU_NUM").innerHTML = real_nullToZore(TerminalViBean.LIMITE_NUMBER)+"&nbsp;(人)";//real_nullToZore(TerminalViBean.STU_NUM)+"/"+
	document.getElementById("real_SPEEDING").innerHTML = real_nullToZore(TerminalViBean.SPEEDING)+"&nbsp;km/h";
	document.getElementById("real_DIRECTION").innerHTML = real_diretionToStr(TerminalViBean.DIRECTION);
	document.getElementById("real_OIL_INSTANT").innerHTML = real_nullToZore(TerminalViBean.OIL_INSTANT)+"&nbsp;L";
	//document.getElementById("real_ENGINE_ROTATE_SPEED").innerHTML = real_nullToZore(TerminalViBean.ENGINE_ROTATE_SPEED)+"&nbsp;rpm";
	document.getElementById("real_TERMINAL_TIME").innerHTML = TerminalViBean.TERMINAL_TIME.substring(10, TerminalViBean.TERMINAL_TIME.length-2);
	document.getElementById("real_STAT_INFO").innerHTML = (TerminalViBean.STAT_INFO!=0?'开':'关');
	document.getElementById("real_DINGWEI_STAT_INFO").innerHTML = (TerminalViBean.dingwei_stat!=0?'有效':'无效');
	
}
/**
* 重点监控按钮事件
*/
function real_Key_monitoring(){
	//alert(123);
	var vin = document.getElementById("real_vin").value;
	var optionUserid = document.getElementById("real_optionUserid").value;
	var Intervaltime = document.getElementById("Intervaltime").value;
	//var realtime = document.getElementById("realtime").value;
	Intervaltime = Intervaltime.replace(/(^\s*)|(\s*$)/g, "");
	if(!real_mereg.test(Intervaltime)){
		//alert("上报间隔必须为2到60之间整数！");
		tishiShow("上报间隔必须为2到60之间整数！");
		tishiHide();
		return;
	}else{
		if(Intervaltime<2||Intervaltime>60){
			//alert("上报间隔必须为2到60之间整数！");
			tishiShow("上报间隔必须为2到60之间整数！");
			tishiHide();
			return;
		}
	}
		
	var Intervaltimes = document.getElementById("Intervaltimes").value;
	Intervaltimes = Intervaltimes.replace(/(^\s*)|(\s*$)/g, "");
	if(!real_mereg.test(Intervaltimes)){
		
		//alert("持续时间必须为大于0的整数！");
		tishiShow("持续时间必须为0-3600的整数！");
		tishiHide();
		return;
	}else{
		if(Intervaltimes<=0){
			//alert("持续时间必须为大于0的整数！");
			tishiShow("持续时间必须为0-3600的整数！");
			tishiHide();
			return;
		}
		if(Intervaltimes>3600){
			//alert("持续时间必须为大于0的整数！");
			tishiShow("持续时间必须为0-3600的整数！");
			tishiHide();
			return;
		}
	}
	
	real_flashtime = Math.round(Intervaltimes/Intervaltime);
	//alert(vin+","+optionUserid+","+Intervaltime+","+Intervaltimes);

	GPSDwr.postKeyMonitor(vin, optionUserid,Intervaltime, Intervaltimes,postKeyMonitor_callback);

	function postKeyMonitor_callback(data){
		//alert(data);
		if(data=="0"){
			//alert("指令已下发!");
			
			tishiShow("指令已下发！");
			
			real_flash = document.getElementById("Intervaltime").value*1000;
			//intreal= window.setTimeout("getdialogArguments()",flash);
			
			real_getdialogArguments();
		}
		else if(data=="8000"){
			
			
			tishiShow("终端不在线,请稍后再试！");
		}
		else{
			//alert("指令下发失败!");
			tishiShow("指令下发失败！");
			
			
		}
		//window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);
		tishiHide();
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
function real_nullToZore(str){
	if(str == null || str == "" ||  str == "undefined" || str == " "||str =="FFFF"){
		return 0;
	}
	else{
		return str;
	}
}

function real_diretionToStr(str){
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

function tishiShow(info){
	
	hideshowresultDiv(0);
	document.getElementById("inforeault").innerHTML=info;
}
function tishiHide(){
	window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);
}

function testspeed(){//alert(123);
	real_mapObj = null;//alert(234);
	document.getElementById("realiCenter").innerHTML = "";
	//alert(456);
}

jQuery( function() {
	//alert(123);
	
	 jQuery(window.parent.iframeshowArea).resize(function(){
		 
		 //alert("msg");
		styleControl();
		
		  
	});

	 styleControl();
	
});

 var popWidth ="500px";
 var popMaxWidth ="655px";
 var popHeight = "484px";
 var popMaxHeiht = "660px";

function styleControl(){

	if(jQuery(window.parent.iframeshowArea).width() > 500){
		//页面最外层div大小
		jQuery('#bgdiv').width(popMaxWidth);
		jQuery('#bgdiv').height(popMaxHeiht);
		
		jQuery('#map3').height("501px");
		jQuery('#realiCenter').height("501px");
		
	}
	else{
		
		jQuery('#bgdiv').width(popWidth);
		jQuery('#bgdiv').height(popHeight);

		jQuery('#map3').height("325px");
		jQuery('#realiCenter').height("325px");

		

	}

	
	
}
</script>