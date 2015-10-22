<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<s:hidden id="iframe_vin" name="iframe_vin" value="%{#request.vin}"/>
<s:hidden id="iframe_start_time" name="iframe_start_time" value="%{#request.start_time}"/>
<s:hidden id="iframe_end_time" name="iframe_end_time" value="%{#request.end_time}"/>
<s:hidden id="iframe_mileage" name="iframe_mileage" value="%{#request.mileage}"/>
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
	var iframe_vin=document.getElementById('iframe_vin').value;
	var iframe_start_time=document.getElementById('iframe_start_time').value;
	var iframe_end_time=document.getElementById('iframe_end_time').value;
	var iframe_mileage=document.getElementById('iframe_mileage').value;
	document.getElementById('iframeshowArea').src=encodeURI('<s:url value="/tripPatch/showMapPage.shtml" />?vin='+iframe_vin+'&start_time='+iframe_start_time+'&end_time='+iframe_end_time+'&mg='+iframe_mileage);
}
function iframeAutoWH(){
	var iframeobj = document.getElementById("iframeshowArea");
	if(jQuery(window).height() >= 900){
		jQuery("#iframeshowArea").width("655px");
		jQuery("#iframeshowArea").height("550px");
		
	}else{
		jQuery("#iframeshowArea").width("500px");
		jQuery("#iframeshowArea").height("396px");
	}
}
</script>