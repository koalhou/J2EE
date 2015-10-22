<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>





<input id="iframe_optpageid" name="optpageid" type="hidden" value="<s:property value='optpageid'/>"/>
<input id="iframe_vin" name="vin" type="hidden" value="<s:property value='vin'/>" />
<input id="iframe_lon" name="lon" type="hidden" value="<s:property value='lon'/>" />
<input id="iframe_lat" name="lat" type="hidden" value="<s:property value='lat'/>" />
<input id="iframe_route_id" name="route_id" type="hidden" value="<s:property value='route_id'/>" />
<s:hidden  id="iframe_VEH_EXT_INFO" name="terminalViBean.VEH_EXT_INFO"/>
<s:hidden  id="iframe_COLOR" name="terminalViBean.color"/>
<input id="iframe_alarmid" name="iframe_alarmid" type="hidden" value="<s:property value='alarmid'/>" />
<input id="iframe_alarmtypeid" name="iframe_alarmtypeid" type="hidden" value="<s:property value='alarmtypeid'/>" />
<input id="iframe_alarmtime" name="iframe_alarmtime" type="hidden" value="<s:property value='alarmtime'/>" />
<input id="alarm_sourceid" name="alarm_sourceid" type="hidden" value="<s:property value='sourceid'/>" />

<div style="height: 484px;width: 100%;">
<iframe id="iframeshowArea" name="iframeshowArea" src='' height="484px" width="100%" frameborder="0" scrolling="no" ></iframe>
</div>
<script type="text/javascript">
jQuery(function() {
	iframeAutoWH();
	loadPage();
	//setTimeout("loadPage()",2000);
	
});

function loadPage(){
	//alert("test1");
	var optpageid = document.getElementById("iframe_optpageid").value;
	var vin = document.getElementById("iframe_vin").value;

	var lon = document.getElementById("iframe_lon").value;
	var lat = document.getElementById("iframe_lat").value;
	var route_id = document.getElementById("iframe_route_id").value;
	
	var VEH_EXT_INFO = document.getElementById("iframe_VEH_EXT_INFO").value;
	var color = document.getElementById("iframe_COLOR").value;
	
	var iframe_alarmid = document.getElementById("iframe_alarmid").value;
	var iframe_alarmtypeid = document.getElementById("iframe_alarmtypeid").value;
	var iframe_alarmtime = document.getElementById("iframe_alarmtime").value;
	var alarmsourceid=document.getElementById("alarm_sourceid").value;
	
	
	var iframeobj = document.getElementById("iframeshowArea");

	if(optpageid==1){//轨迹回放
		iframeobj.src = "<s:url value='/gps/bitlookAction.shtml' />?vin="+vin+"&lon="+lon+"&lat="+lat+"&route_id="+route_id;
	}else if(optpageid==2){//重点监控
		//alert("test");
		iframeobj.src = "<s:url value='/gps/realtimelookAction.shtml' />?vin="+vin;
	}else if(optpageid==3){//视频监控
		iframeobj.src = '<s:url value="/gps/VideomonitorAction.shtml" />?vin='+vin;
	}
	else if(optpageid==4){//车辆拍照
		iframeobj.src ='<s:url value="/gps/SendPhtotAction.shtml" />?vin='+vin;
	}
	else if(optpageid==5){//消息调度
		iframeobj.src ='<s:url value="/gps/SendMsgAction.shtml" />?vin='+vin;
	}else if(optpageid==6){//告警处理
		iframeobj.src = '<s:url value="/busalarm/getdetailAlarm.shtml" />?vin='+vin+'&alarmid='+iframe_alarmid+'&alarmtypeid='+iframe_alarmtypeid+'&alarmtime='+iframe_alarmtime+'&lon='+lon+'&lat='+lat+'&sourceid='+alarmsourceid;
	}else if(optpageid==7){//视频回放
		if(jQuery("#iframeshowArea").width() > 500){
			iframeobj.src = '<s:url value="/gps/RePlayVideomonitorAction.shtml" />?vin='+vin+'&selfheight=400px';
		}else{
			iframeobj.src = '<s:url value="/gps/RePlayVideomonitorAction.shtml" />?vin='+vin+'&selfheight=280px';
		}
	}	
}
function iframeAutoWH(){
	
	//alert(jQuery(window).height()+","+jQuery(document).height()+","+jQuery(document.body).height());
	var iframeobj = document.getElementById("iframeshowArea");
	//alert(jQuery(window).attr("location"));
	var path = new String(jQuery(window.location).attr("pathname"));
	//alert(path.indexOf("gps"));
	if(path.indexOf("gps") != -1){
		if(jQuery(window).height() >= 900){
			
			//iframeobj.width = "655px";
			//iframeobj.height = "660px";
			jQuery("#iframeshowArea").width("100%");
			jQuery("#iframeshowArea").height("660px");
			
		} 
		else{
			
			//iframeobj.width = "500px";
			//iframeobj.height = "484px";
			jQuery("#iframeshowArea").width("100%");
			jQuery("#iframeshowArea").height("484px");
			
		}
	}
	
}

</script>