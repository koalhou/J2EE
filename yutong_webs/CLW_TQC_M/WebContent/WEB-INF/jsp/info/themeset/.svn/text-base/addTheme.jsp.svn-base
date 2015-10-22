<%@include file="../../common/taglibs.jsp" %>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="../../common/meta.jsp" %>
<title>系统设置&nbsp;|&nbsp;添加用户界面</title>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/spectrum.css' />" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jQuery/jquery-1.9.1.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jQuery/json.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/spectrum.js' />"></script>
<script type="text/javascript" src="http://app.mapabc.com/apis?t=flashmap&v=2.4.1&key=1c1f088154552328dd9927181a2616b6a62971c7a9db6d6a36bea17f5adf2413fc7c61ccabe6a62f">
</script>
<style type="text/css">
*{ margin: 0; padding: 0;}
html, body{ margin:0; padding:0; border:none; font:12px/18px  'Microsoft Yahei','微软雅黑',Arial,Verdana;}
table {
margin-left: 20px;
}
.logo{ background: url(../images/common/header_line.png) no-repeat right top; float: left; height: 92px; text-align: left; width: 240px;}
.userInfo{ 
	background: url(../images/common/ico_user.gif) no-repeat left top; 
	float: left; 
	height: 50px; 
	line-height: 30px; 
	margin: 25px 12px 0;
	padding-left: 35px; 
	width: 200px;
}
.userInfo a{ color: #fff; }
.userLink{ color: #fff; line-height: 14px;}

.navigation{ float: right; padding-top: 0px; height: 75px; width: 600px;}
.navigation ul{ float: left; height: 86px; margin: 0; width: 581px;}
.navigation li{ float: right; padding: 0; list-style: none;}
.navigation a{
	color: #fff;
    display: block;
    margin: 0 2px;
	padding-top: 55px;
	text-decoration: none;
	text-align: center;
	width: 86px;
	height: 31px;
}

.button_tab{
	background: url(../images/sbutton_bg.gif) repeat-x top left;
	border: 1px solid #b4b4b4;
	color: #2a2a2a;
	margin: 0 2px;
	padding: 1px 0 0 0;
	line-height: 20px;
	text-align: center;
	width: 60px;
}
</style>
</head>
<body>

<div id="header" style="background: url(showPhoto.shtml?id=default&type=headerclass) repeat-x left top; height: 80px;  min-width: 1256px; width:100%;">


<div style="background: url(showPhoto.shtml?id=default&type=headbgclass) no-repeat left top; float: left; height: 80px;width:100%;" style="position: relative;">
<div class="logo"><img  style="margin: 2px 0px 0px 0px;" src="showPhoto.shtml?id=default&type=companylogo" /></div>

	<div class="userInfo">
	    <nobr>
		    <strong style="color:#000;">
				    测试用户超级管理...
		    </strong>
	    </nobr>
		<p class="userLink"><a href="#' />">注销</a> | <a href="#">设置</a> | 
		<a href="#' onclick="">帮助 </a></p>
	</div>
	<div class="navigation">
		<ul>
			<li>
				<a id="mi5" href="javascript:void(0)" class="mk-menubar-item" data-options="menu: '#mm5'">设置中心</a>
			</li>
			<li>
				<a id="mi4" href="javascript:void(0)" class="mk-menubar-item" data-options="menu: '#mm4'">统计中心</a>
			</li>
			<li>
				<a id="mi3" href="javascript:void(0)" class="mk-menubar-item" data-options="menu: '#mm3'">调度中心</a>
			</li>
			<li>
				<a id="mi2" href="javascript:void(0)" class="mk-menubar-item" data-options="menu: '#mm2'">油量监控</a>
			</li>
			<li>
				<a id="mi1" href="javascript:void(0)" class="mk-menubar-item">车辆监控</a>
			</li>
		</ul>
	</div>
</div>
</div>

<div align="center">
<div style="margin-left:30px;">
<label>用户：</label>
<textarea id="userName" name="userDetail.userName" cols="50" rows="6"
                             onclick="openUserBrowse()" 
                             readonly="readonly"></textarea>
</div>
<font color="red"><s:property value="message" /></font>
<div id="navphotos_value" style="display:none;">
<s:property value="theme.navphotos" />
</div>


<form id="form" action="imageuploadFileUsers.shtml" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="<s:property value="theme.id" />">
<input type="hidden" id="userIdStr" name="userIdStr"/>
<table>
<thead>
<tr>
<th>名称</th>
<th>上传文件</th>
<th>当前文件</th>
<th>图例</th>
<th></th>
<th>大小</th>
</tr>
</thead>
<tr>
<td>修改header</td>
<td><input type="file" name="fileheader" value="文件1" /></td>
<td><label id="headerhref"></label></td>
<td><img id="headerimg" src="showPhoto.shtml?id=default&type=headerclass"/></td>
<td><a id="headera" href="showPhoto.shtml?id=default&type=headerclass">查看</a></td>
<td><label>1*80</label></td>
</tr>
<tr>
<td>修改headbg</td>
<td><input type="file" name="fileheadbg" value="文件2" /></td>
<td><label id="headbghref"></label></td>
<td><img style="width:200px;" id="headbgimg" src="showPhoto.shtml?id=default&type=headbgclass"/></td>
<td><a id="headbga" href="showPhoto.shtml?id=default&type=headbgclass">查看</a></td>
<td><label>1024*80或1*80</label></td>
</tr>

<tr>
<td>修改logo</td>
<td><input type="file" name="filelogo" value="文件3" /></td>
<td><label id="logohref"></label></td>
<td><img id="logoimg" src="showPhoto.shtml?id=default&type=companylogo"/></td>
<td><a id="logoa" href="showPhoto.shtml?id=default&type=companylogo">查看</a></td>
<td><label>208*72</label></td>
</tr>

</table>


<table>
<tr>
<td>修改底部background</td>
<td><input id="footbg" /></td>
<td><label>修改前：</label></td>
<td><label id="footbghref"></label></td>
<td><label>修改后：</label></td>
<td><input name="footbgcolor" id="footbgimg"/></td>
</tr>
<tr>
<td>修改底部color</td>
<td><input id="footcolor" /></td>
<td><label>修改前：</label></td>
<td><label id="footcolorhref"></label></td>
<td><label>修改后：</label></td>
<td><input name="footcolor" id="footcolorimg"/></td>
</tr>
</table>

<table>
<tr>
<td>车辆监控背景</td>
<td><input type="file" name="jkheader" value="文件1" /></td>
<td><label id="jkhref"></label></td>
<td><img id="jkimg" src="showPhoto.shtml?id=default&type=mi1"/></td>
<td><a id="jka" href="showPhoto.shtml?id=default&type=mi1">下载</a></td>
<td><label>84*80</label></td>
</tr>
<tr>
<td>车辆监控选中背景</td>
<td><input type="file" name="jkactiveheader" value="文件1" /></td>
<td><label id="jkactivehref"></label></td>
<td><img id="jkactiveimg" src="showPhoto.shtml?id=default&type=nav1focus"/></td>
<td><a id="jkactivea" href="showPhoto.shtml?id=default&type=nav1focus">下载</a></td>
<td><label>84*80</label></td>
</tr>
<tr>
<td>油量监控背景</td>
<td><input type="file" name="ylheader" value="文件1" /></td>
<td><label id="ylhref"></label></td>
<td><img id="ylimg" src="showPhoto.shtml?id=default&type=mi2"/></td>
<td><a id="yla" href="showPhoto.shtml?id=default&type=mi2">下载</a></td>
<td><label>84*80</label></td>
</tr>
<tr>
<td>油量监控选中背景</td>
<td><input type="file" name="ylactiveheader" value="文件1" /></td>
<td><label id="ylactivehref"></label></td>
<td><img id="ylactiveimg" src="showPhoto.shtml?id=default&type=nav2focus"/></td>
<td><a id="ylactivea" href="showPhoto.shtml?id=default&type=nav2focus">下载</a></td>
<td><label>84*80</label></td>
</tr>

<tr>
<td>调度中心背景</td>
<td><input type="file" name="ddheader" value="文件1" /></td>
<td><label id="ddhref"></label></td>
<td><img id="ddimg" src="showPhoto.shtml?id=default&type=mi3"/></td>
<td><a id="dda" href="showPhoto.shtml?id=default&type=mi3">下载</a></td>
<td><label>84*80</label></td>
</tr>
<tr>
<td>调度中心选中背景</td>
<td><input type="file" name="ddactiveheader" value="文件1" /></td>
<td><label id="ddactivehref"></label></td>
<td><img id="ddactiveimg" src="showPhoto.shtml?id=default&type=nav3focus"/></td>
<td><a id="ddactivea" href="showPhoto.shtml?id=default&type=nav3focus">下载</a></td>
<td><label>84*80</label></td>
</tr>
<tr>
<td>统计中心背景</td>
<td><input type="file" name="tjheader" value="文件1" /></td>
<td><label id="tjhref"></label></td>
<td><img id="tjimg" src="showPhoto.shtml?id=default&type=mi4"/></td>
<td><a id="tja" href="showPhoto.shtml?id=default&type=mi4">下载</a></td>
<td><label>84*80</label></td>
</tr>
<tr>
<td>统计中心选中背景</td>
<td><input type="file" name="tjactiveheader" value="文件1" /></td>
<td><label id="tjactivehref"></label></td>
<td><img id="tjactiveimg" src="showPhoto.shtml?id=default&type=nav4focus"/></td>
<td><a id="tjactivea" href="showPhoto.shtml?id=default&type=nav4focus">下载</a></td>
<td><label>84*80</label></td>
</tr>
<tr>
<td>设置中心背景</td>
<td><input type="file" name="szheader" value="文件1" /></td>
<td><label id="szhref"></label></td>
<td><img id="szimg" src="showPhoto.shtml?id=default&type=mi5"/></td>
<td><a id="sza" href="showPhoto.shtml?id=default&type=mi5">下载</a></td>
<td><label>84*80</label></td>
</tr>
<tr>
<td>设置中心选中背景</td>
<td><input type="file" name="szactiveheader" value="文件1" /></td>
<td><label id="szactivehref"></label></td>
<td><img id="szactiveimg" src="showPhoto.shtml?id=default&type=nav5focus"/></td>
<td><a id="szactivea" href="showPhoto.shtml?id=default&type=nav5focus">下载</a></td>
<td><label>84*80</label></td>
</tr>
</table>

<input type="hidden" name="userId" value="<s:property value="theme.userId" />" />

<div id="mapobj" style="width:800px;height:400px;">

</div>
<table>
<tr>
<td><input type="button" class="button_tab" onClick="javascript:addMarker();" value="修改"/></td>
<td><input type="button" class="button_tab" onClick="javascript:mapObj.removeOverlaysByType(MOverlay.TYPE_MARKER);" value="删除"/></td>
</tr>
<tr>
<td><label>纬度</label><input id="theme_lon" name="theme_lon" readonly="readonly"/>
<label>经度</label><input id="theme_lat" name="theme_lat" readonly="readonly" /></td>
<td><input value="确定" class="button_tab" type="button" onclick="mapcheck()" /></td>
</tr>
</table>
<div style="margin-left:30px;">
<input value="上传" type="button" class="button_tab" onclick="check()" />
<input value="返回" type="button" class="button_tab" onclick="back()" />
</div>
</form>

</div>
<div id="footer" style="<s:property value="theme.footerclass" />">版权所有<span>&copy;</span> 宇通集团 2013-2014 </div>

<script type="text/javascript">
var fileSplit = "/";
var fileSplit2 = "\\";
jQuery(function() {
	var split = "";
	/* var headerimg = "<s:property value="theme.headerclass" />";
	
	//判断部署环境
	if(headerimg.indexOf(fileSplit)>0)
		split="/";
	else if(headerimg.indexOf(fileSplit2)>0)
		split="\\"; */
	
	/* var headerhref = headerimg.substring(headerimg.indexOf("(")+1,headerimg.indexOf(")"));
	$("#headerhref").html(headerhref.substring(headerhref.lastIndexOf(split),headerhref.length));
	$("#headera").attr("href",headerhref);
	$("#headerimg").attr("src",headerhref);
	
	var headbgimg = "<s:property value="theme.headbgclass" />";
	var headbghref = headbgimg.substring(headbgimg.indexOf("(")+1,headbgimg.indexOf(")"));
	$("#headbghref").html(headbghref.substring(headbghref.lastIndexOf(split),headbghref.length));
	$("#headbga").attr("href",headbghref);
	$("#headbgimg").attr("src",headbghref);
	
	var logoimg = "<s:property value="theme.companylogo" />";
	$("#logohref").html(logoimg.substring(logoimg.lastIndexOf(split),logoimg.length));
	$("#logoa").attr("href",logoimg);
	$("#logoimg").attr("src",logoimg); */
	
	var footerbg_v=rgb2hex($("#footer").css("background-color"));
	$("#footbg").spectrum({
	    color: $("#footer").css("background-color"),/* footerbg_v */
	    preferredFormat:"rgb",
	    change: function(tinycolor) {
	    	$("#footbgimg").val(rgb2hex(tinycolor.toString()));
	    }
	});
	$("#footbghref").html(footerbg_v);
	$("#footbgimg").val(footerbg_v);
	
	var footerc_v=rgb2hex($("#footer").css("color"));
	$("#footcolor").spectrum({
	    color: $("#footer").css("color"),/* footerc_v */
	    preferredFormat:"rgb",
	    change: function(tinycolor) {
	    	$("#footcolorimg").val(rgb2hex(tinycolor.toString()));
	    }
	});
	$("#footcolorhref").html(footerc_v);
	$("#footcolorimg").val(footerc_v);
	
	
	//------导航图标----------
	var navphotos_v = JSON.parse(jQuery("#navphotos_value").html());
	/* var jk_v = navphotos_v["mi1"].substring(navphotos_v["mi1"].indexOf("(")+1,navphotos_v["mi1"].indexOf(")"));
	$("#jkhref").html(jk_v.substring(jk_v.lastIndexOf(split),jk_v.length));
	$("#jka").attr("href",jk_v);
	$("#jkimg").attr("src",jk_v);
	var jkactive_v = navphotos_v["nav1focus"].substring(navphotos_v["nav1focus"].indexOf("(")+1,navphotos_v["nav1focus"].indexOf(")"));
	$("#jkactivehref").html(jkactive_v.substring(jkactive_v.lastIndexOf(split),jkactive_v.length));
	$("#jkactivea").attr("href",jkactive_v);
	$("#jkactiveimg").attr("src",jkactive_v);
	
	var yl_v = navphotos_v["mi2"].substring(navphotos_v["mi2"].indexOf("(")+1,navphotos_v["mi2"].indexOf(")"));
	$("#ylhref").html(yl_v.substring(yl_v.lastIndexOf(split),yl_v.length));
	$("#yla").attr("href",yl_v);
	$("#ylimg").attr("src",yl_v);
	var ylactive_v = navphotos_v["nav2focus"].substring(navphotos_v["nav2focus"].indexOf("(")+1,navphotos_v["nav2focus"].indexOf(")"));
	$("#ylactivehref").html(ylactive_v.substring(ylactive_v.lastIndexOf(split),ylactive_v.length));
	$("#ylactivea").attr("href",ylactive_v);
	$("#ylactiveimg").attr("src",ylactive_v);

	var dd_v = navphotos_v["mi3"].substring(navphotos_v["mi3"].indexOf("(")+1,navphotos_v["mi3"].indexOf(")"));
	$("#ddhref").html(dd_v.substring(dd_v.lastIndexOf(split),dd_v.length));
	$("#dda").attr("href",dd_v);
	$("#ddimg").attr("src",dd_v);
	var ddactive_v = navphotos_v["nav3focus"].substring(navphotos_v["nav3focus"].indexOf("(")+1,navphotos_v["nav3focus"].indexOf(")"));
	$("#ddactivehref").html(ddactive_v.substring(ddactive_v.lastIndexOf(split),ddactive_v.length));
	$("#ddactivea").attr("href",ddactive_v);
	$("#ddactiveimg").attr("src",ddactive_v);

	var tj_v = navphotos_v["mi4"].substring(navphotos_v["mi4"].indexOf("(")+1,navphotos_v["mi4"].indexOf(")"));
	$("#tjhref").html(tj_v.substring(tj_v.lastIndexOf(split),tj_v.length));
	$("#tja").attr("href",tj_v);
	$("#tjimg").attr("src",tj_v);
	var tjactive_v = navphotos_v["nav4focus"].substring(navphotos_v["nav4focus"].indexOf("(")+1,navphotos_v["nav4focus"].indexOf(")"));
	$("#tjactivehref").html(tjactive_v.substring(tjactive_v.lastIndexOf(split),tjactive_v.length));
	$("#tjactivea").attr("href",tjactive_v);
	$("#tjactiveimg").attr("src",tjactive_v);

	var sz_v = navphotos_v["mi5"].substring(navphotos_v["mi5"].indexOf("(")+1,navphotos_v["mi5"].indexOf(")"));
	$("#szhref").html(sz_v.substring(sz_v.lastIndexOf(split),sz_v.length));
	$("#sza").attr("href",sz_v);
	$("#szimg").attr("src",sz_v);
	var szactive_v = navphotos_v["nav5focus"].substring(navphotos_v["nav5focus"].indexOf("(")+1,navphotos_v["nav5focus"].indexOf(")"));
	$("#szactivehref").html(szactive_v.substring(szactive_v.lastIndexOf(split),szactive_v.length));
	$("#szactivea").attr("href",szactive_v);
	$("#szactiveimg").attr("src",szactive_v); */
	
	defaultMenu(navphotos_v);
	jQuery.each(jQuery(".navigation ul li"),function(i){
		jQuery(this).children("a").click(function(){
			defaultMenu(navphotos_v);
			var id_v = $(this).attr("id");
			//$(this).attr("style",navphotos_v["nav"+id_v.substring(id_v.length-1,id_v.length)+"focus"]);
			$(this).attr("style","background: url(showPhoto.shtml?id=default&type=nav"+id_v.substring(id_v.length-1,id_v.length)+"focus) no-repeat center top;");
		});
	});
	mapInit();
});

var mapObj=null;
function  mapInit() {
	var mapoption = new MMapOptions();
	mapoption.toolbar = MConstants.ROUND; //设置地图初始化工具条，ROUND:新版圆工具条
	mapoption.overviewMap = MConstants.SHOW; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）
	mapoption.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。
	mapoption.zoom = 13;//要加载的地图的缩放级别
	mapoption.center = new MLngLat("113.686","34.693");//要加载的地图的中心点经纬度坐标
	mapoption.language = MConstants.MAP_CN;//设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图
	mapoption.fullScreenButton = MConstants.SHOW;//设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏
	mapoption.centerCross = MConstants.SHOW;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏
	mapoption.toolbarPos=new MPoint(20,20); //设置工具条在地图上的显示位置
	mapObj = new MMap("mapobj", mapoption); //地图初始化
	
	var mapground = "<s:property value="theme.ismapgroundlogo" />";
	var lon = mapground.split(",")[0];
	var lat = mapground.split(",")[1];
	$("#theme_lon").val(lon);
	$("#theme_lat").val(lat);
	callback_default(lon,lat);
}
var markerid = null;
function callback_default(lon,lat){
	var tipOption=new MTipOptions();//添加信息窗口
	tipOption.title="信息窗口标题";//信息窗口标题
	tipOption.content="信息窗口内容";//信息窗口内容

	var labelOption=new MLabelOptions();//添加标注

	//构建一个名为markerOption的点选项对象。
	var markerOption = new MMarkerOptions();
	//标注图片或SWF的url，默认为蓝色气球图片
	markerOption.imageUrl="http://code.mapabc.com/images/lan_1.png";
	markerOption.isEditable=true;
	//是否使用图片代理形式
	//如果imageUrl属性的图片资源所在域名下没有crossdomain.xml，则需要用代理形式添加该图片资源
	markerOption.picAgent=false;
	//设置图片相对于加点经纬度坐标的位置。九宫格位置。默认BOTTOM_CENTER代表正下方
	markerOption.imageAlign=MConstants.BOTTOM_CENTER;
	//设置点的标注参数选项
	markerOption.labelOption=labelOption;
	//拖动结束后是否有弹跳效果,ture，有弹跳效果；false，没有弹跳效果（默认）
	//当有弹跳效果的时候，marker的imageAlign属性必须为BOTTOM_CENTER，否则弹跳效果显示不正确
	markerOption.isBounce=false;
	//设置点是否为可编辑状态,rue，可以编辑；	false，不可编辑（默认）
	markerOption.isEditable=false;
	//设置点的信息窗口参数选项
	markerOption.tipOption = tipOption;
	//是否在地图中显示信息窗口，true，可以显示（默认）；false，不显示
	markerOption.canShowTip= true;
	//设置图标旋转的角度
	markerOption.rotation="40";
	//是否显示阴影，默认为true，即有阴影
	markerOption.hasShadow=true;
	//设置点是否高亮显示
	//设置高亮显示与设置可编辑有冲突，只能设置一个，不能同时设置。
	markerOption.isDimorphic=true;
	//设置第二种状态的颜色，默认为0xFF0000，即红色
	markerOption.dimorphicColor="0x00A0FF";
	//通过经纬度坐标及参数选项确定标注信息
	Mmarker = new MMarker(new MLngLat(lon,lat),markerOption);
	//对象编号，也是对象的唯一标识
	Mmarker.id="mark101";
	//向地图添加覆盖物
	mapObj.addOverlay(Mmarker,true);
	
	markerid = "mark101";
}
function addMarker() {
	mapObj.addEventListener(mapObj, MConstants.MOUSE_UP,showMarker);
}
function showMarker(point) {
	mapObj.removeOverlaysByType(MOverlay.TYPE_MARKER);
	$("#theme_lon").val(point.eventX);
	$("#theme_lat").val(point.eventY);
	callback_default(point.eventX,point.eventY);
	
	mapObj.removeEventListener(mapObj, MConstants.MOUSE_UP,removeListener);
	
}

function defaultMenu(navphotos_v) {
	jQuery.each(jQuery(".navigation ul li"),function(i){
		var midid = jQuery(this).children("a").attr("id");
		//jQuery("#"+midid).attr("style",navphotos_v[midid]);
		jQuery("#"+midid).attr("style","background: url(showPhoto.shtml?id=<s:property value='theme.id' />&type="+midid+") no-repeat center top;");
	});
}
function uploadImage() {
	if(document.getElementById("fileupload").value == "") {
		alert("请选择文件");
		return false;
	}
};
function check() {
	if($("#userName").val()=="") {
		alert("用户信息不能为空");
		return false;
	}
	document.getElementById("form").submit();
		
}
function getUser() {
	
}

function view() {
	
}
var count = 0;
function openUserBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/userinit.shtml' />" + "?count=" + (++count),
                                     self,
                                     "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }
function zero_fill_hex(num, digits) {
	  var s = num.toString(16);
	  while (s.length < digits)
	    s = "0" + s;
	  return s;
	}


function rgb2hex(rgb) {

  if (rgb.charAt(0) == '#')
    return rgb;
 
  var ds = rgb.split(/\D+/);
  var decimal = Number(ds[1]) * 65536 + Number(ds[2]) * 256 + Number(ds[3]);
  return "#" + zero_fill_hex(decimal, 6);
}
function back() {
	window.location.href = "<s:url value='/paramset/themesetManage.shtml' />";
}
</script>
</body>
</html>