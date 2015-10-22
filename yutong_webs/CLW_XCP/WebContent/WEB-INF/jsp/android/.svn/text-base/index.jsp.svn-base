<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安芯手机版</title>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/android/css/main.css'/>" />
<style>
.sliderobject
{
	position:absolute;height:622px;width:700px;left:-100px;top:-5px;z-index:1;opacity:0;
}

.zydot{position:absolute;bottom:35px;left:480px;height;25px;z-index:2;text-align:center;vertical-align:center;}

.zydot-ul
{
	list-style:none; /* 去掉ul前面的符号 */
    margin: 0px; /* 与外界元素的距离为0 */
    padding: 0px; /* 与内部元素的距离为0 */
    width: auto; /* 宽度根据元素内容调整 */
}
.zydot-ul li
{
	
	float:left;
	margin-right:10px;
}
.zydot-ul li span
{
	display:block;
	width:11px;
	height:11px;
	cursor:pointer;
}
.dotcheck
{
	background-image:url(../android/images/dot_check.png);
}
.dotuncheck
{
	background-image:url(../android/images/dot_uncheck.png);
}
</style>
<script src="<s:url value='/android/js/jquery-1.7.2.min.js'/>"></script>
<script src="<s:url value='/android/js/masklayer.js'/>"></script>
<script src="<s:url value='/android/js/managerdata.js'/>"></script>
<script src="<s:url value='/android/js/slider.js'/>"></script>
<script>
	/*
	 *各超链接URL配置信息。如果有变动，请更改此处
	 *
	 */
	var sinaweiboUrl = "http://weibo.com/3512877500";
	var tencentweiboUrl = "http://t.qq.com/ytaxxc";
	var axxcUrl = "http://www.axxc.cn";
	var pcdownloadUrl = "../getAndroidAPK?type=1";
	var webloadUrl="../gps/gpsAction.shtml";
	var codedownloadUrl = "http://www.axxc.cn/CLW_XCP/getapk";

	$(window).ready( function() {
		//zyong 2014 add
		var slider=new Slider({sliders:["3gdiv","adpicdiv"],dotCotainerId:"slider_dot",interval:5000});
		/*$("#adpicdiv").animate( {
			"left" : "-100px",
			"opacity" : " 1"
		}, 500);*/
		$("#adworddiv").animate( {
			"right" : "0px",
			"opacity" : " 1"
		}, 500);
		slider.switchSlider();
		slider.start();
		initLinkBtn();
		var maskLayer = new MaskLayer();
		maskLayer.init("maskbackground", "maskcontent", "continuebtn");

		if($("#isMobileAllow").val() == "0") {
			/*这里需要判断是否有手机访问权限,如果没有权限，则弹出提示层*/
			maskLayer.open();
			$("#zonename").text($("#regionName").val());
			$("#managername").text($("#managerName").val());
			$("#managertel").text($("#cellPhone").val());
			if($("#photoPath").val() != "") {
				$("#managerhead").css("background-image","url('"+"../android/images/" + $("#photoPath").val()+"')");
			}
		}
	});

	function initLinkBtn() {
		//用脚本的方式，可以避免a标签上的虚线框，影响美观。
		//$("#backbtn").on("click", function() {
			//location=webloadUrl;
			//window.close();
		//});
		$("#downloadbtn").on("click", function() {
			download();
		});

		$("#weibosinabtn").on("click", function() {

			location = sinaweiboUrl;
		});
		$("#weibotencentbtn").on("click", function() {

			location = tencentweiboUrl;
		});

		$("#iosbtn").on("click", function() {
			location = "https://itunes.apple.com/us/app/an-xin-xiao-che/id873929639?l=zh&ls=1&mt=8";
		});
	}

	function download() {
		/**
		var elemIF = document.createElement("iframe");
		elemIF.src = downloadUrl;
		elemIF.style.display = "block";
		document.body.appendChild(elemIF);
		**/
		//var maskLayer = new MaskLayer();
		//maskLayer.init("maskbackground", "maskcontent", "continuebtn");
		
		//if($("#isMobileAllow").val() == "0") {
		//	maskLayer.open();
		//} else {
			location=pcdownloadUrl;
		//}

	}
</script>
</head>
<body>
<!--遮罩层内容-->
<div id="maskbackground"></div>
<div id="maskcontent">
<div id="masktextcon">
<div class="masktext1">您可通过联系您所在的<span id="zonename">华中</span>区域经理进行开通：</div>
<div id="managerhead"></div>
<div id="managername">魏柯</div>
<div id="managertel">13674966956</div>
<a class="viewallmanager" href="<s:url value='/android/managelist.xls' />">查看所有区域经理</a></div>
<a id="continuebtn"></a></div>
<!--遮罩层内容-->
<div class="autowidthheight"><!--topbar-->
<div id="top">
<!-- <a id="backbtn"></a> -->
<div class="innercontainer"><a id="logo"></a> </div>
</div>
<!--topmain-->
<div id="topmain">
<div class="innercontainer mainbg">
<div id="adpicdiv" class="sliderobject"><img src="<s:url value='/android/images/adpic-1.png'/>"/></div>
<div id="3gdiv" class="sliderobject"><img src="<s:url value='/android/images/3G.png'/>"/></div>
<div id="adworddiv" style="position: absolute; height: 79px; width: 480px; right: -500px; top: 75px; opacity: 0"><img src="<s:url value='/android/images/adword1.png'/>" /></div>
<div id="downloaddiv"><a class="downloadlink" id="downloadbtn"></a><a class="downloadios" id="iosbtn"></a>
<img src="<s:url value='/android/images/scan2dcode.png'/>" style=" width:384px; height:85px; position:absolute;bottom:10px;right:0px;" /></div>
<div class="zydot" ><ul class="zydot-ul" id="slider_dot"></ul></div>
</div>
</div>
<!--main-->
<div id="main">
<div class="innercontainer" style="min-height: 300px; padding-top: 30px;">
<div id="mainfuncpic"></div>
</div>
</div>
<!--foot-->
<div id="foot">
<div class="innercontainer">
<div id="copyright" class="footelement"></div>
<div id="weibotext" class="footelement"></div>
<a id="weibosinabtn" class="footelement"> </a> <a id="weibotencentbtn" class="footelement"> </a>
<div id="contact" class="footelement"></div>
</div>
</div>
</div>
<s:hidden id="regionName" name="regionManageInfo.regionName"></s:hidden>
<s:hidden id="managerName" name="regionManageInfo.managerName"></s:hidden>
<s:hidden id="cellPhone" name="regionManageInfo.cellPhone"></s:hidden>
<s:hidden id="isMobileAllow" name="regionManageInfo.isMobileAllow"></s:hidden>
<s:hidden id="photoPath" name="regionManageInfo.photoPath"></s:hidden>
</body>
</html>

