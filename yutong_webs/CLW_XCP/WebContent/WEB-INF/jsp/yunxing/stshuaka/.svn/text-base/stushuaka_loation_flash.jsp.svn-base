<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/nyroModal.css' />" type="text/css" media="screen" />
<%@ include file="/WEB-INF/jsp/common/key2.jsp"%>
<title>地理位置</title>
<!-- 地理位置布局样式  
	<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/pop.css'/>">
	-->
<link rel="stylesheet" type="text/css"
		href="<s:url value="/stylesheets/global.css" />" />
<link rel="stylesheet" type="text/css"
		href="<s:url value="/stylesheets/photo.css" />" />
<link rel="stylesheet" type="text/css"
		href="<s:url value="/stylesheets/pop.css" />" />
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/galleria/jquery.js'/>"></script>
</head>
<body onload="mapInit()">
<div class="popArea3_mid">
	<div class="popArea3_mid_left" style="position:relative;">
       <!-- map 
		<div class="maps" align="center"></div>
			<div id="MapbarDivNO" style="position: absolute; left:0px; top: 20px; z-index: 1000;border:1px solid green;">©2011 MapABC - GS(2010)6011</div>
			-->
			<div id="iCenter" style="position: absolute; left:0px; top:0px;width: 478px; height: 380px;"></div>
    </div>
  	<div class="popArea3_mid_right">
  		<div style=" height:10px;"></div>
   		<!-- 学生信息 -->
   		<s:hidden id="lon" name="stuLocationInfo.lon" />
        <s:hidden id="lat" name="stuLocationInfo.lat" /> 
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <!-- 姓名 -->
        <tr>
            <th width="24%" height="28" align="left">姓&nbsp;&nbsp;&nbsp;名</th>
            <td width="10%" align="left">：</td>
            <td width="66%" align="left"><s:property value="stuLocationInfo.studentName" /></td>
        </tr>
        <!-- 学号 -->
        <tr>
            <th width="24%" height="28" align="left">学&nbsp;&nbsp;&nbsp;号</th>
            <td width="10%" align="left">：</td>
            <td width="66%" align="left"><s:property value="stuLocationInfo.studentCode" /></td>
        </tr>
        <!-- 状态 -->
        <tr>
            <th width="24%" height="28" align="left">状&nbsp;&nbsp;&nbsp;态</th>
            <td width="10%" align="left">：</td>
            <td width="66%" align="left"><s:property value="stuLocationInfo.vssFlag" /></td>
        </tr>
        <!-- 学校 -->
        <tr>
            <th width="24%" height="28" align="left">学&nbsp;&nbsp;&nbsp;校</th>
            <td width="10%" align="left">：</td>
            <td width="66%" align="left"><s:property value="stuLocationInfo.studentSchool" /></td>
        </tr>
        <!-- 班级 -->
        <tr>
            <th width="24%" height="28" align="left">班&nbsp;&nbsp;&nbsp;级</th>
            <td width="10%" align="left">：</td>
            <td width="66%" align="left"><s:property value="stuLocationInfo.studentClass" /></td>
        </tr>
        <!-- 车牌号 -->
        <tr>
            <th width="24%" height="28" align="left">车牌号</th>
            <td width="10%" align="left">：</td>
            <td width="66%" align="left"><strong><s:property value="stuLocationInfo.vehicleLn" /></strong></td>
        </tr>
         <!-- 刷卡时间 -->
        <tr>
            <th width="24%" height="28" align="left">时&nbsp;&nbsp;&nbsp;间</th>
            <td width="10%" align="left">：</td>
            <td width="66%" align="left"><s:property value="stuLocationInfo.terminalTime" /></td>
        </tr>
         <!-- 当前位置 -->
        <tr>
            <th width="24%" height="28" align="left">位&nbsp;&nbsp;&nbsp;置</th>
            <td width="10%" align="left">：</td>
            <td width="66%" align="left"><div id="addressDiv"/></td>
        </tr>
     </table> 
  	</div>
</div>
<script type="text/javascript">
var mapObj=null;
function mapInit() {
	var x = document.getElementById("lon").value;
	var y = document.getElementById("lat").value;
	
	//设置地图默认的初始化参数
	var mapOptions = setInitOption(x,y,4);
	//地图对象初始化  
	mapObj = new MMap("iCenter", mapOptions); 
	mapObj.setKeyboardEnabled(false);
	mapObj.setZoomLevel(17);
	
	//地图上添加点()
	var location=new MLngLat(x,y);
	var pointId = "VEHICLE_VIN";
	var imageUrl = "../images/arrow_blue.gif";
	var markerObj= setPointMarker(pointId,imageUrl,location);
	
	//向地图添加点
	mapObj.addOverlay(markerObj,true);
	//逆地理信息获取
	getAddress(x,y);
}

/**
 * （地图初始化）快速显示地图
 * cx cy 中心点的经纬度坐标
 * mapZoom(地图的缩放级别)
 */
function  setInitOption(cx,cy,mapZoom) {
	var mapOptions = new MMapOptions();
	mapOptions.toolbar = MConstants.MINI; //设置地图初始化工具条，ROUND:新版圆工具条
	mapOptions.toolbarPos=new MPoint(15,15); //设置工具条在地图上的显示位置
	mapOptions.overviewMap = MConstants.HIDE; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）
	mapOptions.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。
	mapOptions.zoom = mapZoom;//要加载的地图的缩放级别
	mapOptions.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标
	//地图右下角logo去掉
	mapOptions.logoUrl = "../newimages/sidelayerimages/mask.png";
	//背景图片
	mapOptions.groundLogo = "../newimages/sidelayerimages/mapbackgroud.jpg";
	mapOptions.center = new MLngLat(cx,cy);//要加载的地图的中心点经纬度坐标
	mapOptions.language = MConstants.MAP_CN;//设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图
	mapOptions.fullScreenButton = MConstants.HIDE;//设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏
	mapOptions.centerCross = MConstants.HIDE;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏
	mapOptions.requestNum=100;//设置地图切片请求并发数。默认100。
	mapOptions.isQuickInit=true;//设置是否快速显示地图，true显示，false不显示。
	return mapOptions;
}

/**
 * 添加点工具类
 */
function setPointMarker(pointId,imageUrl,location){
	
	//构建一个名为markerOption的点选项对象。
	var markerOption = new MMarkerOptions();
	//添加本地图片
	markerOption.imageUrl = imageUrl;
	//是否使用图片代理形式
	//如果imageUrl属性的图片资源所在域名下没有crossdomain.xml，则需要用代理形式添加该图片资源
	markerOption.picAgent=false;
	//设置图片相对于加点经纬度坐标的位置。九宫格位置。默认BOTTOM_CENTER代表正下方
	markerOption.imageAlign=MConstants.BOTTOM_CENTER;
	//拖动结束后是否有弹跳效果,ture，有弹跳效果；false，没有弹跳效果（默认）
	//当有弹跳效果的时候，marker的imageAlign属性必须为BOTTOM_CENTER，否则弹跳效果显示不正确
	markerOption.isBounce=false;
	//设置点是否为可编辑状态,rue，可以编辑；	false，不可编辑（默认）
	markerOption.isEditable=false;
	//是否在地图中显示信息窗口，true，可以显示（默认）；false，不显示
	markerOption.canShowTip= false;
	//设置图标旋转的角度
	markerOption.rotation=0;
	//设置点是否高亮显示
	//设置高亮显示与设置可编辑有冲突，只能设置一个，不能同时设置。
	markerOption.isDimorphic=true;
	//设置第二种状态的颜色，默认为0xFF0000，即红色
	markerOption.dimorphicColor="0x00A0FF";
	//通过经纬度坐标及参数选项确定标注信息
	var markerObj = new MMarker(location,markerOption);
	//对象编号，也是对象的唯一标识
	markerObj.id = pointId;
	return markerObj;
}




function getAddress(lon, lat) {
	var tdDiv = document.getElementById("addressDiv");
	if(lon.length >1 && lat.length >1){
		 var lnglatXY=new MLngLat(lon,lat); 
		 var mls =new MReGeoCodeSearch();   
		 var opt= new MReGeoCodeSearchOptions();   
		 opt.poiNumber=10;//返回周边的POI数量,默认10   
		 opt.range=100;//限定周边热点POI和道路的距离范围   
		 opt.pattern=1;//返回数据的模式,0表示返回地标性POI,1表示返回全部POI，   
		 opt.exkey="";//排除的关键字   
		 mls.setCallbackFunction(poiToAddressSearch_CallBack);   
		 mls.poiToAddress(lnglatXY,opt);   
	
		 var addr ="";
		function poiToAddressSearch_CallBack(data){
			
			if(data.error_message != null){   
				resultStr="查询异常！"+data.error_message;
				tdDiv.innerHTML = "";   
			}else{   
				switch(data.message){
				case 'ok':
					if(data.SpatialBean.roadList!=null && data.SpatialBean.roadList.length>0){
						addr = data.SpatialBean.roadList[0].name;
					}
					if(addr != null && addr != "" && addr.trim != "undefined"){
						tdDiv.innerHTML = addr+"附近";
					}
					else{
						tdDiv.innerHTML = "";
					}
					break; 
				case 'error':
					addr ="";
					tdDiv.innerHTML = "";
					break; 
				default: 
					addr ="";
					tdDiv.innerHTML = "";
				}
			}
		}
	}
		
}
</script>
</body>
</html>