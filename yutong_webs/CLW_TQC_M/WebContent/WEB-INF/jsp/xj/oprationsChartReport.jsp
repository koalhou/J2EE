<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<base target='_self'>
<title><s:property value="getText('oprationsReportChart.info')" /></title>
</head>
<script type="text/javascript">
function init(){
    document.getElementById("butLink").focus();
	document.getElementById("vehicle_vin").innerHTML="<s:property value='vehicle_vin'/>";
}

function searchDayChart(){
	var time_begin=document.getElementById("time_begin").value;
	var time_end=document.getElementById("time_end").value;
	var enterprise_code="<s:property value='enterprise_code'/>";
	var vehicle_vin="<s:property value='vehicle_vin'/>";
	//DWREngine.setAsync(false);
	//var ret = false; 

	ReportDayChartDwr.getDayChart(time_begin, time_end,enterprise_code,vehicle_vin,callBackFun);
	    
	function callBackFun(str){
	  //ret = data;
	  var chart = new FusionCharts("../scripts/fusioncharts/ScrollColumn2D.swf?ChartNoDataText=无查询结果", "ChartId", "680", "310", "0", "0");
	 	chart.setDataXML(str);		   
	    chart.render("chartdiv"); 
	}
	    
	//DWREngine.setAsync(true);
}
function searchHourChart(){
	var reportDate=document.getElementById("reportDate").value;
	var enterprise_code="<s:property value='enterprise_code'/>";
	var vehicle_vin="<s:property value='vehicle_vin'/>";
	//DWREngine.setAsync(false);
	//var ret = false; 

	ReportDayChartDwr.getHourChart(reportDate,enterprise_code,vehicle_vin,callBackFun);
	    
	function callBackFun(str){
	  //ret = data;
	  var chart = new FusionCharts("../scripts/fusioncharts/Column2D.swf?ChartNoDataText=无查询结果", "ChartId", "680", "310", "0", "0");
	 	chart.setDataXML(str);		   
	    chart.render("chartdiv"); 
	}
	    
	//DWREngine.setAsync(true);
}

/*
* 点击页签显示不同饼图
*/
function changeChoose(id){
	if(id=="tab1"){
		document.getElementById("tab1").className="current2";
		document.getElementById("tab2").className="";
		document.getElementById("td1").style.display= "";
		document.getElementById("td2").style.display= "none";
		document.getElementById("chartdiv").innerHTML="";
	}else{
		document.getElementById("tab2").className="current2";
		document.getElementById("tab1").className="";
		document.getElementById("td1").style.display= "none";
		document.getElementById("td2").style.display= "";
		document.getElementById("chartdiv").innerHTML="";
	}

}
</script>

<body onload="init();">
<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/list.css'/>">
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript'
	src='../dwr/interface/ReportDayChartDwr.js'></script>
<script type="text/javascript"
	src="<s:url value='/scripts/fusioncharts/FusionCharts.js' />"></script>
<s:form id="enterprisebrowse_form" action="queryEnterprise"
	method="post" onsubmit="return false;">
	<s:hidden id="flag" name="flag" />

	<table align="center" border="0" width="100%" cellspacing="0" cellpadding="0"
		class="reportOnline2">

		<tr>
			<td align="center">
			<div class="reportTab">
			<ul>
				<li><a id="tab1" class="current2" style="cursor: pointer" onclick="changeChoose(this.id)"><s:property value="getText('oprationsReportSecond.info.day')" /></a></li>
				<li><a id="tab2" style="cursor: pointer" onclick="changeChoose(this.id)"><s:property value="getText('oprationsReportSecond.info.hour')" /></a></li>
			</ul>
			</div>
			</td>
		</tr>

		<tr>
			<td bgcolor="#FFFFFF">
				<div class="reportOnline2" style="margin: 5px 10px 0; padding:5px;">
				<table align="center" id="td1" border="0" width="100%" cellspacing="0" cellpadding="0">
					<tr>
						<td width="500">
						<s:property
							value="getText('statistic.info.time_begin')" />： <s:textfield
							id="time_begin" name="time_begin"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})"
							cssClass="Wdate" /> &nbsp;&nbsp; <s:property
							value="getText('statistic.info.time_end')" />： <s:textfield
							id="time_end" name="time_end"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})"
							cssClass="Wdate" /></td>
						<td align="left"><a id="butLink" href="#" onclick="searchDayChart()"
							class="sbutton"><s:property value="getText('btn.query')" /></a></td>
					</tr>
				</table>
				<table id="td2" style="display:none" border="0" width="100%" cellspacing="0" cellpadding="0">
					<tr>
						<td width="500"><s:property
							value="getText('oprationsReport.info.date')" />： <s:textfield
							id="reportDate" name="reportDate"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})"
							cssClass="Wdate" /> &nbsp;&nbsp; </td>
						<td align="left"><a  href="#" onclick="searchHourChart()" 
							class="sbutton"><s:property value="getText('btn.query')" /></a></td>
					</tr>
				</table>
				</div>
				<div style="margin: 0 0 5px 100px;" ></div>
				<div style="height:320px" id="chartdiv" align="center"></div>
				<div style="margin: 0 0 5px 200px;"><s:property
					value="getText('oprationsReportSecond.info.vehicle_vin')" />： <label
					id="vehicle_vin" name="vehicle_vin" /> <input type="hidden"
					id="enterprise_code" name="enterprise_code" /></div>
			</td>
			
		</tr>

	</table>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td class="footer"><s:property value="getText('copyright.info')" />
			&copy; <s:property value="getText('copyright.company')" /> 2011</td>
		</tr>
	</table>
</s:form>

</body>
</html>