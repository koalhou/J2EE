<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="common.title" /></title>
<!-- 中文注释 -->
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/jquery-easyui-1.2.2/themes/default/menu.css'/>"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/jquery-easyui-1.2.2/themes/default/menubutton.css'/>"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/jquery-easyui-1.2.2/themes/icon.css'/>"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/flexigrid/flexigrid.css'/>" />
<style type="text/css">
.xianyin,.xianyin2,.xianyin3 {
	width: 100%;
}

.searchTab a.current {
	background: url(../images/tree_tab.gif) no-repeat left top;
	color: #121212;
	text-decoration: none;
}

.contentTil {
	color: #121212;
	font-size: 14px;
	font-weight: bold;
	float: left;
	line-height: 35px;
	padding-left: 8px;
	width: 30%;
	padding: 6px 8px 0 4px;
}

.searchPlan {
	float: left;
	width: 250px;
}

.biaoge {
	width: 100%;
}
.fanwei{ width:250px; height:158px; overflow:auto;}

.searchBox3{ background: #f5f5f5; border: 1px solid #c3c3c3; float: left; line-height: 32px; margin: 5px;  width:230px; height:35px;}
.fudongBar{ width:100%; height:41px; position:absolute; z-index:1; left:0; top:0;}
.mapbox{ width:100%; position:relative;}
.toolbar2{
	background: url(../images/contentTil_bg.gif) repeat-x left center; 
	float: left; 
	height: 38px; 
	width: 100%;
}
.contentTil2{
	color: #121212;
	font-size: 14px;
	font-weight: bold;
	float: left; 
	line-height: 38px;
	padding-left: 8px;
	width: 30%;
}
.jiange{ height:6px;}


img{ border: 0;}
.balloon{ height: 204px; width: 320px;}
.balloon ul, .balloon li{ list-style: none; margin: 0;}
.balloon .balloonLeft{
	background:url(../images/qipaoimages/balloons.gif) no-repeat left top;
	float: left;
	height: 204px;
	width: 10px;
}
.balloon .balloonRight{
	background:url(../images/qipaoimages/balloons.gif) no-repeat right top;
	float: left;
	height: 204px;
	width: 10px;
}
.balloon .balloonMain{
	background:url(../images/qipaoimages/balloons_bg.gif) repeat-x right top;
	float: left;
	height: 204px;
	width: 300px;
}
.balloon .balloonTil{
	color: #222;
	float: left;
	height: 42px;
	width: 100%;
}
.balloon a.tab{
	color: #222;
	display: block;
	float: left;
	line-height: 42px;
	margin-right: 10px;
	text-align: center;
	text-decoration: none;
	width: 70px;
}
.balloon a:hover.tab{ text-decoration: underline;}
.balloon a.tabfocus{
	background: url(../images/qipaoimages/balloons_tab.gif) no-repeat left center;
	color: #fff;
	display: block;
	float: left;
	line-height: 42px;
	margin-right: 10px;
	text-align: center;
	text-decoration: none;
	width: 70px;
}
.balloon a.close{
	background: url(../images/qipaoimages/balloons_close.gif) no-repeat left top;
	display: block;
	float: right;
	height: 16px;
	margin-top: 13px;
	width: 16px;
}
.balloon .data{ border-bottom: 1px solid #ccc; float: left; height: 102px; padding-top: 5px; width: 100%;}
.balloon .data2{ border-bottom: 1px solid #ccc; float: left; height: 102px; padding-top: 5px; width: 100%; display:none;}

.balloon .toolbarqp{ border-top: 1px solid #fff; float: left;_float: ; height: 44px; text-align: center; width: 100%;}

a.tool1, a.tool2, a.tool3, a.tool4, a.tool5, a.tool6, a.tool7{ cursor: pointer; display: block; float: left; height: 30px; margin: 4px 6px; width: 30px; }
.balloon a.tool1{ background: url(../images/qipaoimages/btn_tool1.gif) no-repeat center top;}
.balloon a.tool2{ background: url(../images/qipaoimages/btn_tool2.gif) no-repeat center top;}
.balloon a.tool3{ background: url(../images/qipaoimages/btn_tool3.gif) no-repeat center top;}
.balloon a.tool4{ background: url(../images/qipaoimages/btn_tool4.gif) no-repeat center top;}
.balloon a.tool5{ background: url(../images/qipaoimages/btn_tool5.gif) no-repeat center top;}
.balloon a.tool6{ background: url(../images/qipaoimages/btn_tool6.gif) no-repeat center top;}
.balloon a.tool7{ background: url(../images/qipaoimages/btn_tool7.gif) no-repeat center top;}
.balloon .arrow{ background:url(../images/qipaoimages/balloons_arrow.gif) no-repeat top center; float: left; height: 9px; width: 100%;}

/*左侧区域*/
.treeTabMap{ background: url(../images/qipaoimages/title_bg.png) repeat-x left top; float: left; height: 33px; line-height: 32px; padding-top: 5px; width: 100%;}
.treeTabMap a.tabMap{
	color: #222;
	display: block;
	float: left;
	line-height: 32px;
	margin-left: 5px;
	text-align: center;
	text-decoration: none;
	width: 72px;
}
.treeTabMap a:hover.tabMap{ text-decoration: underline;}
.treeTabMap a.tabfocusMap{
	background: url(../images/qipaoimages/tree_tab.gif) no-repeat left center;
	color: #222;
	display: block;
	float: left;
	line-height: 32px;
	margin-left: 5px;
	text-align: center;
	text-decoration: none;
	width: 72px;
}
a.hideLeftMap, a.showLeftMap{ display: block; float: right; height: 18px; margin: 4px 5px 0 0; width: 18px;}
a.showLeftMap{ background: url(../images/qipaoimages/ico_showLeft.gif) no-repeat left top;}
a.hideLeftMap{ background: url(../images/qipaoimages/ico_hideLeft.gif) no-repeat left top;}

.searchPlanMap{ background: #eee url(../images/qipaoimages/search_bg.png) repeat-x left top; border-bottom: 1px solid #b8b8b8; float: left; height: 35px; line-height: 35px; padding-top: 4px; width: 100%;}
.sendPlanMap{ background: #eee url(../images/qipaoimages/search_bg.png) repeat-x left top; border-top: 1px solid #b8b8b8; height: 30px; line-height: 26px; padding-top: 2px; width: 100%;}

a.btnbuleMap{ background: url(../images/qipaoimages/btn_blue.gif) no-repeat left top; color: #fff; display: block; height: 28px; line-height: 28px; text-align: center; 

width: 76px; }

/*右侧区域*/
.titleBarMap{ background: url(../images/qipaoimages/title_bg.png) repeat-x left top; color: #222; float: left; height: 38px; line-height: 36px; width: 100%;}
.titleMap{ float: left; font-size: 14px; font-weight: bold; text-indent: 10px; }
.workLinkMap{ float: right; padding: 5px 10px; text-align: right;}
a.btnLinkMap{ background: url(../images/qipaoimages/btn_c80.gif) no-repeat left top; color: #333; display: block; float: left; height: 26px;_height: 20px; line-height: 26px; margin: 0 
5px; text-align: center; width: 80px; }

/*input styles| 表单样式 */
.input_requiredMap{ color: #c80000; line-height: 18px; padding: 0 2px;}
input[type="text"]:focus, input[type="password"]:focus { background: #eff;}
input[type="text"],input[type="password"] {
	background: #fff url(../images/qipaoimages/input_bg.gif) repeat-x left top;
	border-top: 1px solid #8e8e8e;
	border-left: 1px solid #aaaeb4; 
	border-right: 1px solid #aaaeb4; 
	border-bottom: 1px solid #e1e1e1; 
	color: #222; 
	font: 14px/20px Tahoma, Arial, sans-serif, 微软雅黑, 宋体; 
	height: 20px;
}
.input60{ height: 20px; width: 58px;}
.input120{ height: 20px; width: 118px;}

.moreBg{background:url(../images/listTab2_bg.gif) repeat-x left top; height: 30px; }
</style>
</head>

<body onLoad="mapInit('iCenter');MM_preloadImages('../images/zuosuojin2.gif','../images/yousuojin2.gif')" onUnload="setonunload()">
<%@ include file="/WEB-INF/jsp/common/key.jsp"%>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="childmapabcOption.jsp"%>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<script type="text/javascript">
function MapMoveToPoint(lon,lat){
	var bounds=mapObj.getLngLatBounds();
	//alert(bounds.southWest.lngX+","+bounds.southWest.latY+";"+bounds.northEast.lngX+","+bounds.northEast.latY);

	if(lon < bounds.southWest.lngX || lat < bounds.southWest.latY || lon > bounds.northEast.lngX || lat > bounds.northEast.latY){
		//mapObj.panTo(new MLngLat(lon,lat));
		return true;
	}
	return false;
}

function SelectLinsList_callback(data){
	document.getElementById("lineChoiceAll").checked = false;
	DWRUtil.removeAllRows("GPSLine");
	//alert(data.length);
	if(data!=null && data.length>0){
		addTableRowsByLine(data);
	}
}

function setonunload(){
	window.clearInterval(intalerm);
	//window.clearInterval(intinit);
	window.clearInterval(intOneinit);
}

var isScreen = false;
function AllScreen(){
	//<div id="iCenter" style="width:100%; height: 370px;">fudong_back.gif
	var meunid = document.getElementById("meunid");
	var maincurrentid = document.getElementById("main_currentid");
	//var leftInfoDiv = document.getElementById("leftInfoDiv");
	//var maptopid = document.getElementById("maptopid");
	var iCenter = document.getElementById("iCenter");
	var AlermInfoDiv =  document.getElementById("AlermInfoDiv");
	var copyrightid = document.getElementById("copyrightid");
	//var Lefttab = document.getElementById("leftTab");
	var allscreenid = document.getElementById("allscreenid");
	var mapdiv = document.getElementById("mapbartab");
	if(isScreen){
		//设置正常
		WALL_web();
		//leftInfoDiv.style.display="";
		AlermInfoDiv.style.display="";
		meunid.style.display="";
		maincurrentid.style.display="";
		copyrightid.style.display="";
		//iCenter.style.height="370px"
		//maptopid.style.display="";
		allscreenid.src="../images/fudong_fullscreen.gif";
		isScreen=false;
		mapdiv.style.top = "0px";
	}
	else{
		WALL_web();
		//leftInfoDiv.style.display="none";
		AlermInfoDiv.style.display="none";
		meunid.style.display="none";
		maincurrentid.style.display="none";
		copyrightid.style.display="none";
		//maptopid.style.display="none";
		allscreenid.src="../images/fudong_back.gif";
		isScreen=true;
		mapdiv.style.top = "30px";
		
		
	}
	//if(Lefttab)Lefttab.style.display="none";

	testWidthHeight();
}

	function MM_preloadImages() { //v3.0
	  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
	    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
	    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
	}

	function MM_swapImgRestore() { //v3.0
	  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
	}

	function MM_findObj(n, d) { //v4.01
	  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
	    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	  if(!x && d.getElementById) x=d.getElementById(n); return x;
	}

	function MM_swapImage() { //v3.0
	  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
	   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
	}


	//地图上方工具条缩放
	function maptoolbarIsshow(option){
		var map = document.getElementById("maptoolbar");
		//var up = document.getElementById("fudong_up");
		var down = document.getElementById("fudong_down");

		if(option=="up"){
			map.style.display = "none";

			//up.style.display = "none";
			down.style.display = "";
		}
		else if(option=="down"){
			map.style.display = "";
			//up.style.display = "";
			down.style.display = "none";
		}

		
	}
	function WALL_web()
	{
	   var WshShell = new ActiveXObject('WScript.Shell')
	   WshShell.SendKeys('{F11}');
	}

</script>
<div>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/easyloader.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/plugins/jquery.linkbutton.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/plugins/jquery.menubutton.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/plugins/jquery.menu.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript">
(function($){
	// 预缓存皮肤，数组第一个为默认皮肤
	$.dialog.defaults.skin = ['aero'];
})(art);
function aboutbrwose(){
	art.dialog.open("<s:url value='/about/about.shtml' />",{
		title:"关于 校车智能管理系统",
		lock:true,
		width:486,
		height:290
	});
}

function studentPasswordConfirm() {	
	art.dialog.open("<s:url value='/student/passwordConfirm.shtml' />?moduleName=student_module",{
		title:"输入密码",
		lock:true,
		width:400,
		height:170
	});
}

function messagePasswordConfirm() {	
	art.dialog.open("<s:url value='/student/passwordConfirm.shtml' />?moduleName=message_module",{
		title:"输入密码",
		lock:true,
		width:400,
		height:170
	});
}
</script>

<style type="text/css">
body{min-width:1000px;}
.menu_iframe{ position:absolute; visibility:inherit; top:0; left:0; width:auto; z-index:-1; filter: Alpha(Opacity=0); border-style:none;}
</style>

<div id="wrapper">
  <div id="header">
    <div class="headBg">
      <div class="logo">
      <s:if test="''==#session.adminProfile.img_path || null==#session.adminProfile.img_path">
      <img src="../images/logo.png"/>
      </s:if>
      <s:else>
       <img src="..<s:property
					value="#session.adminProfile.img_path" />"  style="filter:Alpha(opacity=100,finishOpacity=0,style=3)"/>
      </s:else>
      </div>
      <div class="userInfo"><strong><s:property value="#session.adminProfile.userName" /></strong>
        <p class="userLink"><a href="<s:url value='/logout.shtml' />">注销</a> | <a href="<s:url value='/usm/modifyPersonalinitAction.shtml' />">设置</a> | <a href="javascript:aboutbrwose();">帮助</a></p>
      </div>
      <div class="navigation">
        <ul>
          
          <li class="snav1">
          <a href="<s:url value='/gps/gpsAction.shtml' />">位置监控</a>
          </li>
          
          
          <li class="snav2">
          <a href="<s:url value='/gps/ready.shtml' />">乘车记录</a>
          </li>
                  
          
        </ul>
      </div>
    </div>
  </div>
</div>

<div style="display:none" >
     <div id="mm1" style="width: 140px;"><iframe class="menu_iframe"></iframe>
     <!-- 
			<s:if test="#session.perUrlList.contains('111_3_2_2')">
			<div style="background: url(../images/menu_ico/1-2.gif) no-repeat left 2px;"><a href="<s:url value='/stcheck/ready.shtml'/>"><s:text name="menu2.stcheck" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_2_3')">
			<div style="background: url(../images/menu_ico/1-3.gif) no-repeat left 2px;"><a href="<s:url value='/stalarm/ready.shtml' />"><s:text name="menu2.stalarm" /></a></div>
			</s:if>
	 -->
			<div style="background: url(../images/menu_ico/1-1.gif) no-repeat left 2px;"><a href="<s:url value='/carrun/ready.shtml'/>"><s:text name="menu2.clyxrz" /></a></div>
			<div style="background: url(../images/menu_ico/4-7.gif) no-repeat left 2px;"><a href="<s:url value='/ridepro/ready.shtml'/>"><s:text name="menu2.szltj" /></a></div>
			<div style="background: url(../images/menu_ico/1-3.gif) no-repeat left 2px;"><a href="<s:url value='/carrun/ready.shtml'/>"><s:text name="menu2.yccctj" /></a></div>
			<s:if test="#session.perUrlList.contains('111_3_2_4')">
			<div style="background: url(../images/menu_ico/1-4.gif) no-repeat left 2px;"><a href="<s:url value='/humanbaddrv/baddrive.shtml'/>"><s:text name="menu2.weigui" /></a></div>
			</s:if>			
			<div style="background: url(../images/menu_ico/1-2.gif) no-repeat left 2px;"><a href="<s:url value='/stride/ready.shtml'/>"><s:text name="menu2.xsccjl" /></a></div>
	 </div>
	
	 <div id="mm2" style="width:120px;"><iframe class="menu_iframe"></iframe>
	       <s:if test="#session.perUrlList.contains('111_3_3_1')">
			<div style="background: url(../images/menu_ico/2-1.gif) no-repeat left 2px;"><a href="javascript:studentPasswordConfirm();"><s:text name="menu2.xcxx" /></a></div>
		   </s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_2')">
			<div style="background: url(../images/menu_ico/2-2.gif) no-repeat left 2px;"><a href="<s:url value='/driver/drivermanage.shtml' />"><s:text name="menu2.jsyxx" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_3')">
			<div style="background: url(../images/menu_ico/2-3.gif) no-repeat left 2px;"><a href="<s:url value='/steward/stewardmanage.shtml'/>"><s:text name="menu2.scxx" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_4')">
			<div style="background: url(../images/menu_ico/2-4.gif) no-repeat left 2px;"><a href="<s:url value='/vehicle/vehiclemanage.shtml' />"><s:text name="menu2.clxx" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_5')">
			<div style="background: url(../images/menu_ico/2-5.gif) no-repeat left 2px;"><a href="<s:url value='/station/stationmanage.shtml'/>"><s:text name="menu2.zdpz" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_6')">
			<div style="background: url(../images/menu_ico/2-6.gif) no-repeat left 2px;"><a href="<s:url value='/route/routemanage.shtml' />"><s:text name="menu2.xlsz" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_7')">
			<div style="background: url(../images/menu_ico/2-7.gif) no-repeat left 2px;"><a href="<s:url value='/infomanage/ridingplan.shtml'/>"><s:text name="menu2.ccgh" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_1')">
			<div style="background: url(../images/menu_ico/2-1.gif) no-repeat left 2px;"><a href="javascript:messagePasswordConfirm();">短信提醒</a></div>
		   </s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_8')">
			<div style="background: url(../images/menu_ico/2-8.gif) no-repeat left 2px;"><a href="<s:url value='/holiday/holidaymanage.shtml'/>"><s:text name="menu2.qxjgl" /></a></div>
			</s:if>
	 </div>

	 <div id="mm3" style="width:120px;"><iframe class="menu_iframe"></iframe>
	 		<s:if test="#session.perUrlList.contains('111_3_4_1')">
			<div style="background: url(../images/menu_ico/3-2.gif) no-repeat left 2px;"><a href="<s:url value='/vehiclestatus/vehiclestatus.shtml'/>"><s:text name="menu2.ckjk" /></a></div>
			</s:if>
	        <s:if test="#session.perUrlList.contains('111_3_4_2')">
			<div style="background: url(../images/menu_ico/3-1.gif) no-repeat left 2px;"><a href="<s:url value='/oilused/oilused.shtml'/>"><s:text name="menu3.youhaobaogao" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_3')">
			<div style="background: url(../images/menu_ico/3-3.gif) no-repeat left 2px;"><a href="<s:url value='/baddriv/baddriving.shtml'/>"><s:text name="menu2.bljsjl" /></a></div>
			</s:if>
	  </div>
	   
	<div id="mm4" style="width:140px;"><iframe class="menu_iframe"></iframe>
	    	<s:if test="#session.perUrlList.contains('111_3_5_1')">
		    <div style="background: url(../images/menu_ico/4-1.gif) no-repeat left 2px;">
		    	<a href="<s:url value='/rm/roleAction.shtml' />"><s:text name="menu3.jsgl" /></a>
		    </div>
		    </s:if>
		    <s:if test="#session.perUrlList.contains('111_3_5_2')">
		    <div style="background: url(../images/menu_ico/4-2.gif) no-repeat left 2px;">
		    	<a href= "<s:url value='/usm/usermanageAction.shtml' />"><s:text name="menu3.yhgl" /></a>
		    </div>
		    </s:if>
		    <s:if test="#session.perUrlList.contains('111_3_5_3')">
	        <div style="background: url(../images/menu_ico/4-3.gif) no-repeat left 2px;"><a href="<s:url value='/enti/entimanage.shtml' />"><s:text name="menu2.zzjgsz" /></a></div>
	        </s:if>        
		    <s:if test="#session.perUrlList.contains('111_3_5_4')">
		   <div style="background: url(../images/menu_ico/4-4.gif) no-repeat left 2px;">
		    	<a href="<s:url value='/logoset/logoset.shtml' />"><s:text name="menu3.qylogo" /></a>
		    </div>
		    </s:if>
	   	
	        <s:if test="#session.perUrlList.contains('111_3_5_5')">
	        <div style="background: url(../images/menu_ico/4-5.gif) no-repeat left 2px;"><a href="<s:url value='/terparams/vehiclemanage.shtml' />"><s:text name="menu2.ywcssz" /></a></div>
	        </s:if>
	        <s:if test="#session.perUrlList.contains('111_3_5_6')">
	       <div style="background: url(../images/menu_ico/4-6.gif) no-repeat left 2px;"><a href="<s:url value='/ock/ocktimeset.shtml' />"><s:text name="menu2.khyhydsz" /></a></div>
	        </s:if>
	        <s:if test="#session.perUrlList.contains('111_3_5_7')">
	        <div style="background: url(../images/menu_ico/4-7.gif) no-repeat left 2px;"><a href="<s:url value='/oilset/oilSet.shtml' />"><s:text name="menu2.khyhsz" /></a></div>
	        </s:if>
	   </div> 
</div>

<input type="hidden"  id="enterprise_id" name="enterprise_id"
	value="<s:property value='#session.adminProfile.entiID'/>" />
<input type="hidden"  id="organizationid" name="organizationid"
	value="<s:property value='#session.adminProfile.organizationID'/>" />
<input type="hidden"  id="Userorganizationid" name="Userorganizationid"
	value="<s:property value='#session.adminProfile.organizationID'/>" />
<input type="hidden" id="optionUserid" name="optionUserid"
	value="<s:property value='#session.adminProfile.userID'/>" />
<input type="hidden"  id="UserType" name="UserType"
	value="<s:property value='#session.adminProfile.userType'/>" />

<table  width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top" width="100%">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="maps">
			<tr>
				<td valign="top">
				<div class="mapbox">
				<div class="titleBarMap">
		            <div class="titleMap">实时监控</div>
		             <div class="workLinkMap">
		           <!--  <a href="#" class="btnLinkMap" onclick="openhistorymessage();">历史信息</a>
		           <s:if test="#session.perUrlList.contains('111_3_1_2_1')">
		            <a href="#" class="btnLinkMap" onclick="openhistoryphoto();">历史照片</a>
		           </s:if>-->
		            </div>
		        </div>
				

  				<table id="mapbartab"  width="300" border="0" cellspacing="0" cellpadding="0" style="width:300px;  position:absolute; z-index:1; right:0; top:38px;">
                    <tr id="maptoolbar" >
                      <td height="20" align="center" background="../images/fudong_bg.png" style=" border-left:1px solid #4573BE;">
                      <table width="285" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td align="left"><a href="#" title="隐藏" onClick="maptoolbarIsshow('up');"><img id="goup" src="../images/up_arrow.png"  border="0"></a></td>
                          
                          <td align="center"><a href="#" title="测距" onClick="mousePolyline();"><img id="drawLine" src="../images/fudong_test.gif"  border="0"></a></td>
                          
                          
                          <td align="center"><a href="#" title="实时交通" onclick="addTileLayer_TRAFFIC()"><img id="TILELAYERTRAFFIC" src="../images/fudong_trans.gif"  border="0"></a></td>
                         
                          
                          <td align="center"><a href="#" title="放大" onclick="mousezoomin()"><img id="smallToBig" src="../images/fudong_big.gif"  border="0"></a></td>
                          
                          
                          <td align="center"><a href="#" title="缩小" onclick="mousezooout()"><img id="bigToSmall" src="../images/fudong_small.gif"  border="0"></a></td>
                          
                          
                          <td align="center"><a href="#" title="全屏" onClick="AllScreen();"><img id="allscreenid" src="../images/fudong_fullscreen.gif"  border="0"></a></td>
                          
                        </tr>
                      </table>
                      </td>
                    </tr>
                   <!--  <tr id="fudong_up" >
                      <td align="center" valign="top"><a href="#" ><img src="../images/fudong_up.png" width="60" height="8" onclick="maptoolbarIsshow('up')" border="0"></a></td>
                    </tr>-->
                    <tr id="fudong_down" style="display:none" sytle="position:absolute;z-index:1;">
                      <td align="center" valign="top"><a href="#" ><img src="../images/fudong_down.png" width="60" height="8" onclick="maptoolbarIsshow('down')" border="0"></a></td>
                    </tr>
               </table>
			    
				<div id="iCenter" style="width: 100%; height: 370px;"></div>
				<div  id ="MapbarDivNO" valign="right">
				  <table  width="180px" border="0" cellspacing="0" cellpadding="0">

                    <tr id="maptoolbar" valign="right">
                     
                      <td height="33" valign="right">
                      ©2011 MapABC - GS(2010)6011
                      </td>
                    </tr>
                  </table>
				</div>
				<s:form id="clwForm" name="clwForm" action="" method="post">
				<input type="hidden" id="ChooseEnterID_tree"
					name="ChooseEnterID_tree"
					value="<%=session.getValue("ChooseEnterID_tree")%>"/>
				</s:form>
				</div>
				</td>
			</tr>
		
		</table>

<!--		<div id="AlermInfoDiv" class="biaoge" style="overflow-y:auto;overflow-x:hidden">-->
<div class="sreportPlan">
		<table id="AlermInfoDiv" width="100%" border="0" cellspacing="0" cellpadding="0">

			<tr>
				<td valign="top" >
				<table width="99%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
					<tr>
						<td>
						
									<table width="100%" border="0" cellspacing="0" cellpadding="0" class="titleStyle" >
									<tr>
									 <td>
										<div class="reportTab">
											<ul>
												
												<li>
													<a id="tab4" class="current2" style="cursor:pointer" onclick="changeChoose(this.id)">学生违规<span id="tab4count"></span></a>
												</li>
											</ul>
										</div>
									</td>
									<td width="60px" class="moreBg">
									<s:if test="#session.perUrlList.contains('111_3_1_12_1')">
									<a href="#" onclick="openalarmList();"><img src="../images/more.gif" border="0"/></a>
									</s:if>
									</td>
									
									
									</tr>
									</table>
								
						
						</td>
					</tr>
					<tr>
						<td class="reportInline" height="120">
									
									<table id="gala2" width="100%"  cellspacing="0"></table>
									<span id="parentSpan1"><table id="alermtab1" ></table></span>
									<span id="parentSpan2"><table id="alermtab2" ></table></span>
									<span id="parentSpan3"><table id="alermtab3" ></table></span>
						
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</div>
		</td>
	</tr>
</table>

<div id="footer">客服电话：<strong>400-659-6666</strong>&nbsp;&nbsp;&nbsp;&nbsp;豫ICP备05018144号&nbsp;&nbsp;&nbsp;&nbsp;版权所有<span style="font-family:Arial, Helvetica, sans-serif">&copy;</span> 宇通集团 2010-2015 </div>
</div>
<div id="BgDiv"></div>
</body>

</html>

