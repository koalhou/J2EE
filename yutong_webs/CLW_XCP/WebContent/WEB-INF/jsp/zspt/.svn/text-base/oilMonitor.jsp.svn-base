<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	
</head>
<body>
	<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">
			<input type="hidden" id="userName" value="<s:property value="#session.adminProfile.loginName" />"/> 
			<input type="hidden" id="password" value="<s:property value="#session.adminProfile.loginPwd" />"/>
			<input type="hidden" id="endCode" value="<s:property value="#session.adminProfile.en_code" />"/>
			<iframe id="iframeEngine" width="99%"  frameborder="0" scrolling="auto" src=""></iframe>
		</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script>
$(document).ready(function() {
// 	$("#iframeEngine").css("height",jQuery(window).height()-90-30);
// 	$("#iframeEngine").css("width",jQuery(window).width());
});
</script>
<script>
	var addressUrl="ytzspt.axxc.cn";
	var engineUrl = "http://"+addressUrl+"/CLW_ZSPT/accesslogin.shtml?ptFlag=1&ptParam=ftlyInfoList&accessFlag=1";
	engineUrl += "&userName="+$("#userName").val();
	engineUrl += "&pwd="+$("#password").val();
	engineUrl += "&entCode="+$("#endCode").val();
	$("#iframeEngine").height($(window).height()-90-10);
	$("#iframeEngine").width($(window).width()); 
	$("#iframeEngine").attr("src",engineUrl); 
		
</script>
</body>
</html>