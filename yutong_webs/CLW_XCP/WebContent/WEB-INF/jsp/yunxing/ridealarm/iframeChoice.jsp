<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<input id="iframe_optpageid" name="optpageid" type="hidden" value="<s:property value='detailObj'/>"/>

<div style="height: 484px;width: 500px;">
<s:hidden id="carLn" name="detailObj.vehicle_ln"/>
<iframe id="iframeshowArea" name="iframeshowArea" src='' height="484px" width="100%" frameborder="0" scrolling="no"></iframe>
</div>
<script type="text/javascript">
jQuery(function() {
	loadPage();
});

function loadPage(){
	var iframeobj = document.getElementById("iframeshowArea");
	
	iframeobj.src = '<s:url value="/ridealarm/detailbrowse.shtml"/>?detailObj.VIN='+ "<s:property value='detailObj.VIN'/>" + 
	'&detailObj.user_organization_id='+ "<s:property value='detailObj.user_organization_id'/>" +
    '&detailObj.time_list='+ "<s:property value='detailObj.time_list'/>" +
    '&detailObj.begTime='+ "<s:property value='detailObj.begTime'/>" +
    '&detailObj.endTime='+ "<s:property value='detailObj.endTime'/>" +
    '&detailObj.vehicle_ln='+ encodeURI(encodeURI('${detailObj.vehicle_ln}')) +
    '&detailObj.no_up_num='+"<s:property value='detailObj.no_up_num'/>" +
    '&detailObj.no_down_num='+"<s:property value='detailObj.no_down_num'/>" +
    '&detailObj.nofix_up_num='+"<s:property value='detailObj.nofix_up_num'/>" +
    '&detailObj.nofix_down_num='+"<s:property value='detailObj.nofix_down_num'/>"
	// add by jinp begin
	+ '&detailObj.car_state=' + "<s:property value='detailObj.car_state'/>"
	+ '&detailObj.valid_flag=' + "<s:property value='detailObj.valid_flag'/>"
	// add by jinp end
    ;
}
</script>