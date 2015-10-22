<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>

<input id="iframe_optpageid" name="optpageid" type="hidden" value="<s:property value='detailObj'/>"/>

<div style="height: 484px;width: 500px;">
<iframe id="iframeshowArea" name="iframeshowArea" src='' height="484px" width="100%" frameborder="0" scrolling="no"></iframe>
</div>
<script type="text/javascript">
jQuery(function() {
	loadPage();
});

function loadPage(){
	var iframeobj = document.getElementById("iframeshowArea");
	
	iframeobj.src = '<s:url value="/runoil/bitlook.shtml"/>?vin='+ "<s:property value='vin'/>" + 
					    '&begTime='+ "<s:property value='begTime'/>" + 
					    '&endTime='+ "<s:property value='endTime'/>";
}
</script>