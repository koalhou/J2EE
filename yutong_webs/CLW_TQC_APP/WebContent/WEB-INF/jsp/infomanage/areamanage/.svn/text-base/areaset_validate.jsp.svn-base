<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<%@include file="/WEB-INF/jsp/common/key2.jsp" %> 

<script>
function checkareaname(){
	var areaName = $('areaName');//document.getElementById('areaName');
	if(areaName.value==""){
	    Wit.showErr(areaName, "请输入！");
		return false;
	}
	if(areaName.value.length>6){
	    Wit.showErr(areaName, "大区名称不能超过6个字！");
		return false;
	}
	Wit.showSucc(areaName,Mat.succMsg);
	return true;
}
function checkarealatlon(){
	var minlat = $('upleftlat');
	var minlon = $('upleftlon');
	var maxlat = $('downrightlat');
	var maxlon = $('downrightlon');
	if(minlat.value==""){
	    Wit.showErr(minlat, "请输入！");
	}
	if(minlon.value==""){
	    Wit.showErr(minlon, "请输入！");
	}
	if(maxlat.value==""){
	    Wit.showErr(maxlat, "请输入！");
	}
	if(maxlon.value==""){
	    Wit.showErr(maxlon, "请输入！");
	}
	if(minlat.value==""||minlon.value==""||maxlat.value==""||maxlon.value=="") {
		return false;
	}
	return true;
}
function addForm() {
	trimAllObjs();
	var f0=checkarealatlon();
	var f1=checkareaname();
	if(f0&&f1){
		Wit.commitAll($('add_form'));
	}else{
		return false;
	}
	
}
function setFormEvent() {
	$('addbutton').onclick = addForm;
	mapInit();
}
//页面自适应
$(function(){
	jQuery(window).resize(function(){
		testWidthHeight();
	});
	testWidthHeight();
});
//计算控件宽高
function testWidthHeight(){
	 jQuery(window).mk_autoresize({
			height_include: '#content',
			height_exclude: ['#header', '#footer'],
			height_offset: 0,
			width_include: ['#header', '#content', '#footer'],
			width_offset: 0});
	//计算中区高度
		jQuery('#content').mk_autoresize( {
			height_include : [ '#center'],
			height_offset : 0,
			width_include : [ '#content_rightside'],
			width_offset : 1
		});
	//计算右测区域高度
		jQuery('#content_rightside').mk_autoresize( {
			height_include : '#iCenter',
			height_offset : 30,
			width_include : '#iCenter',
			width_offset : 0
		});
}
window.addEvent('domready', setFormEvent);
/** 取消填加操作 **/
function goBack(url) {
    Wit.goBack(url);
}
var mapObj=null;
/** 地图初始化 **/
function mapInit() {
	var mapOptions = new MMapOptions();//构建地图辅助类   
	mapOptions.zoom=10;//要加载的地图的缩放级别   
	mapOptions.toolbar = MConstants.ROUND; //设置地图初始化工具条，ROUND:新版圆工具条   
	mapOptions.toolbarPos = new MPoint(20,20); //设置工具条在地图上的显示位置   
	mapOptions.overviewMap = MConstants.MINIMIZE; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）  MINIMIZE:最小化
	mapOptions.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。
	mapOptions.language = MConstants.MAP_CN;//设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图
	mapOptions.fullScreenButton = MConstants.HIDE;//设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏
	mapOptions.centerCross = MConstants.HIDE;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏
	mapOptions.requestNum=100;//设置地图切片请求并发数。默认100。
	mapOptions.isQuickInit=true;//设置是否快速显示地图，true显示，false不显示。	
	mapOptions.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标   		
	mapOptions.hasDefaultMenu=false;  //去掉鼠标右键	
	//地图右下角logo去掉
	mapOptions.logoUrl="../newimages/sidelayerimages/mask.png";
	//背景图片
	mapOptions.groundLogo="../newimages/sidelayerimages/mapbackgroud.jpg";
	
	var mlglat = new MLngLat("113.686","34.693",MConstants.COORD_TYPE_OFFSET);
	mapOptions.center = mlglat;
	mapObj=new MMap("iCenter",mapOptions); //地图初始化   
	mapObj.setKeyboardEnabled(false);
	//地图加载完成
	mapObj.addEventListener(mapObj,MConstants.ADD_OVERLAY,callback);
}

function drawrect() {
	mapObj.setCurrentMouseTool(MConstants.DRAW_RECTANGLE);
}
var layId = null;
function callback(param) {
	layId = param.overlayId;
	mapObj.setOverlayEditableById(param.overlayId,true);
	
	var overlay = mapObj.getOverlayById(param.overlayId);
	jQuery("#upleftlat").val(overlay.lnglatArr[0].lngX);
	jQuery("#upleftlon").val(overlay.lnglatArr[0].latY);
	jQuery("#downrightlat").val(overlay.lnglatArr[1].lngX);
	jQuery("#downrightlon").val(overlay.lnglatArr[1].latY);
	
	mapObj.addEventListener(mapObj, MConstants.MOUSE_UP,callback_function);
}
/* function callback(param) {
	if(param.overlayId != 'recta') {
		var overlay = mapObj.getOverlayById(param.overlayId);
		var maxlngX = overlay.lnglatArr[0].lngX;
		var maxlatY = overlay.lnglatArr[0].latY;
		var minlngX = overlay.lnglatArr[1].lngX;
		var minlatY = overlay.lnglatArr[1].latY;
		jQuery("#upleftlat").val(maxlngX);
		jQuery("#upleftlon").val(maxlatY);
		jQuery("#downrightlat").val(minlngX);
		jQuery("#downrightlon").val(minlatY);
		
		mapObj.removeOverlayById(param.overlayId);
		
		var areopt=new MAreaOptions();
		areopt.canShowTip = false;
		areopt.isEditable=true;
		var xys = new Array();
		//将坐标推入数组
		xys.push(new MLngLat(maxlngX,minlatY));
		xys.push(new MLngLat(minlngX,maxlatY));
		//参数分别为 左上角坐标 和对应和对角线坐标 , 样式 , ID qqhrqjgojKHEJ oipgkglllgOLIJ qqhrqfhohODMF oipgkgmgljOLIJ
		var rect = new MRectangle(xys,areopt);
		rect.id = "recta";
		mapObj.addEventListener(mapObj, MConstants.MOUSE_UP,callback_function);
		mapObj.addOverlay(rect,true);
	}
} */
function callback_function(param) {
	var overlay = mapObj.getOverlayById(layId);
	jQuery("#upleftlat").val(overlay.lnglatArr[0].lngX);
	jQuery("#upleftlon").val(overlay.lnglatArr[0].latY);
	jQuery("#downrightlat").val(overlay.lnglatArr[1].lngX);
	jQuery("#downrightlon").val(overlay.lnglatArr[1].latY);
}
/* function callback_function(param) {
	var overlay = mapObj.getOverlayById("recta");
	jQuery("#upleftlat").val(overlay.lnglatArr[0].lngX);
	jQuery("#upleftlon").val(overlay.lnglatArr[0].latY);
	jQuery("#downrightlat").val(overlay.lnglatArr[1].lngX);
	jQuery("#downrightlon").val(overlay.lnglatArr[1].latY);
}
 */
function removeOverlay() {
	mapObj.removeAllOverlays();
	jQuery("#upleftlat").val("");
	jQuery("#upleftlon").val("");
	jQuery("#downrightlat").val("");
	jQuery("#downrightlon").val("");
	layId = null;
}
function change_l_r_site() {
	 alert(jQuery("#areaId_check").val());
	 var param = {areaId:jQuery("#areaId_check").val()};
	 jQuery.post("../area/",param,function(data){
		var areopt=new MAreaOptions();
		areopt.canShowTip = false;
		areopt.isEditable=true;
		var xys = new Array();
		//将坐标推入数组
		xys.push(new MLngLat(maxlngX,minlatY));
		xys.push(new MLngLat(minlngX,maxlatY));
		//参数分别为 左上角坐标 和对应和对角线坐标 , 样式 , ID qqhrqjgojKHEJ oipgkglllgOLIJ qqhrqfhohODMF oipgkgmgljOLIJ
		var rect = new MRectangle(xys,areopt);
		rect.id = "recta";
		mapObj.addEventListener(mapObj, MConstants.MOUSE_UP,callback_function);
		mapObj.addOverlay(rect,true);
	 });
}
</script>