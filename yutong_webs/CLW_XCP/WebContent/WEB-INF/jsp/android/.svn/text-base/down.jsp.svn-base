<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安芯企业版下载页面</title>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<meta name="viewport" content="width=device-width; initial-scale=1.0;  minimum-scale=1.0; maximum-scale=2.0"/>
<meta name="MobileOptimized" content="240"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/android/css/style.css'/>" />
<script src="<s:url value='/android/js/jquery-1.7.2.min.js'/>"></script>
<script>
if (/ipad|iphone|mac/i.test(navigator.userAgent)){
	location = "https://itunes.apple.com/us/app/an-xin-xiao-che/id873929639?l=zh&ls=1&mt=8";
}

$(window).ready( function() {
	initLinkBtn();
});
function initLinkBtn() {
	//用脚本的方式，可以避免a标签上的虚线框，影响美观。
	//$("#backbtn").on("click", function() {
		//location=webloadUrl;
		//window.close();
	//});
	$("#androidDownBtn").on("click", function() {
		location="getAndroidAPK?type=1";
	});

}
</script>
</head>
<body>
     <div class="main">
          <div class="top">
              <div class="logo">
                   <img src="<s:url value='/android/images/logo1.png'/>" width="100%"/>
              </div>
              <div class="botton">
                   <a class="download" id="androidDownBtn" href="#"><img src="<s:url value='/android/images/botton.png'/>" style="width:100%" /></a>
              </div>
          </div>
          <div class="img">
             <img src="<s:url value='/android/images/tu.png'/>" style="width:96%"/>
          </div>
     </div>  
</body>
</html>