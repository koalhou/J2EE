<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<s:hidden id="iframe_alarmId" name="iframe_alarmId" value="%{#request.alarmId}"/>
<div style="height: 484px;width: 500px;">
<iframe id="iframeshowArea" name="iframeshowArea" src='' height="484px" width="100%" frameborder="0" scrolling="no" ></iframe>
</div>
<script type="text/javascript">
jQuery(function() {
	iframeAutoWH();
	loadPage();
});
//列表页面地图
function loadPage(){
	var iframe_alarmId=document.getElementById('iframe_alarmId').value;
	document.getElementById('iframeshowArea').src=encodeURI('<s:url value="/checking/showMapPage.shtml" />?id='+iframe_alarmId);
}
function iframeAutoWH(){
	var iframeobj = document.getElementById("iframeshowArea");
	if(jQuery(window).height() >= 900){
		jQuery("#iframeshowArea").width("655px");
		jQuery("#iframeshowArea").height("660px");
		
	}else{
		jQuery("#iframeshowArea").width("500px");
		jQuery("#iframeshowArea").height("484px");
	}
}
</script>