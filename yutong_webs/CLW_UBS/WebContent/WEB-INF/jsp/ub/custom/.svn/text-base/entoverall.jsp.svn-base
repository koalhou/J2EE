<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/poshytip/tip-yellow/tip-yellow.css'/>">
<title><s:property
	value="getText('menu.ub.customliveness')" /></title>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<script type="text/javascript"
	src="<s:url value='/scripts/common/moment.min.js' />"></script>
	
	
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/nyroModal.css' />"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global_manage.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">

<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/flexigrid/flexigrid.css'/>" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>

<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jQuery/jquery.nyroModal-1.6.2.pack.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
	



</head>
<body>

<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
	<link rel="stylesheet" href="<s:url value='/scripts/indicator/indicator.css' />"
	type="text/css" media="all" />	
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/fusioncharts/FusionCharts.js' />"></script>
	<script type="text/javascript" 
	src="<s:url value='/scripts/indicator/GlareTpl.js' />"></script>
	<script type="text/javascript" 
	src="<s:url value='/scripts/grouptype/GroupType.js' />"></script>
	<script type="text/javascript" 
	src="<s:url value='/scripts/indicator/Indicator.js' />"></script>
	
	<script type="text/javascript"
	src="<s:url value='/scripts/poshytip/jquery.poshytip.js' />"></script>	
<script type="text/javascript"
	src="<s:url value='/scripts/common/HelpTip.js' />"></script>
<script type="text/javascript">
var gtype=null;

var chartData=null;
var customID='';
///////////////

function iniTimeSpan()
{
	
	var st=getQueryStringByName("st");
	var et=getQueryStringByName("et");
	if(st)
	{
		jQuery("#startTime").attr("value",st);
	}
	if(et)
	{
		jQuery("#endTime").attr("value",et);
	}
	if((!st)|| (!et))
	{
		var month=moment().subtract("months",1).format("YYYY-MM");	
		jQuery("#selMonth").attr("value",month);
		setMonth(month);
	}
	
}
function iniPlat()
{
	var v_plat=getQueryStringByName("plat");
	if(v_plat)
	{
		jQuery("#plat").val(v_plat);
	}
}
function clearQSwitch()
{
	jQuery('#q_r7').attr("class", "");
	jQuery('#q_r30').attr("class", "");

}
function q_switch(d) {
	clearQSwitch();
	jQuery("#selMonth").attr("value","");
	
	switch (d) {
	case 7:
		jQuery('#q_r7').attr("class", "r_current");
		var m=moment().subtract("days",1);
		var lm=moment().subtract("days",7);
		jQuery("#startTime").attr("value",lm.format("YYYY-MM-DD"));
		jQuery("#endTime").attr("value",m.format("YYYY-MM-DD"));
		break;
	case 30:
		jQuery('#q_r30').attr("class", "r_current");
		var m=moment().subtract("days",1);
		var lm=moment().subtract("days",30);
		jQuery("#startTime").attr("value",lm.format("YYYY-MM-DD"));
		jQuery("#endTime").attr("value",m.format("YYYY-MM-DD"));
		break;

	}
	gtype.setTimeSpan(jQuery("#startTime").val(),jQuery("#endTime").val(),false);
	searchChartData();
	searchList();
	getGather();
}
//如果当前日期是每月2号以前，则不允许选择当前月。
function getMaxDateMonth()
{
	var d=moment().date();
	var r=moment().format("YYYY-MM-DD");
	if(d<=2)
		r=moment().subtract("days",d).format("YYYY-MM-DD");
	return r;
}
//m:日期字符串，格式：YYYY-MM
function setMonth(m)
{	
	var d=moment(m, "YYYY-MM");	
	
	var curmaonth=moment().format("YYYY-MM");
	
	var firstDay = d.startOf('month').format('YYYY-MM-DD');
	var  lastDay  = (curmaonth==m)?moment().subtract("days",1).format('YYYY-MM-DD'):d.endOf('month').format('YYYY-MM-DD');
	jQuery("#startTime").attr("value",firstDay);		
	jQuery("#endTime").attr("value",lastDay);	
}
function selMonthClick()
{
	clearQSwitch();
	var m=jQuery("#selMonth").attr("value");	
	setMonth(m);
	gtype.setTimeSpan(jQuery("#startTime").val(),jQuery("#endTime").val(),false);
	searchChartData();
	searchList();
	getGather();
}

function startTimeClick()
{
	clearQSwitch();
	jQuery("#selMonth").attr("value","");
	gtype.setTimeSpan(jQuery("#startTime").val(),jQuery("#endTime").val(),false);
}
function endTimeClick()
{
	clearQSwitch();
	jQuery("#selMonth").attr("value","");
	gtype.setTimeSpan(jQuery("#startTime").val(),jQuery("#endTime").val(),false);
	searchChartData();
	searchList();
	getGather();
}

function onGTypeChange(data)
{
	
	searchChartData();
	searchList();
}
function onIndicatorChange(data)
{	
	
	loadChart(chartData);
	
}
function platClick()
{		
	searchChartData();
	searchList();
	getGather();
}
function getGather()
{
	clearGather();
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");	
	var dateType=gtype.selectedType;
	var plat=jQuery("#plat").val();
	
	
	var url='<s:url value="/ub/custom/json/getOneGather.shtml" />'
		+'?startDay='+startDate+'&endDay='+endDate+'&dateType='+dateType+"&plat="+plat+"&customerID="+customID;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			
			loadGather(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			clearGather();
		}
	});	
}
function loadGather(data)
{
	if(data==null || data.gather==null)
	{
		loger("gather data is null.");
		clearGather();
		return;
	}
	var entname=jQuery("#entname");	
	
	var sum_viheclecnt=jQuery("#sum_viheclecnt");
	var sum_totalcnt=jQuery("#sum_totalcnt");
	var sum_dayavgcnt=jQuery("#sum_dayavgcnt");
	var sum_amvcnt=jQuery("#sum_amvcnt");

	var sum_pmvcnt=jQuery("#sum_pmvcnt");
	var sum_nightvcnt=jQuery("#sum_nightvcnt");
	var sum_addtovcnt=jQuery("#sum_addtovcnt");
	var sum_activity=jQuery("#sum_activity");
	
	
	//entname.html(data.);
	entname.html(data.gather.entName);
	sum_viheclecnt.html(data.gather.busCnt);
	sum_totalcnt.html(data.gather.vis);
	sum_dayavgcnt.html(data.gather.dayVis);
	
	sum_amvcnt.html(data.gather.amVis);
	sum_pmvcnt.html(data.gather.pmVis);
	
	sum_nightvcnt.html(data.gather.niVis);
	sum_addtovcnt.html(data.gather.addVis);
	sum_activity.html(data.gather.ac?"是":"否");
	
}
function clearGather()
{
	var entname=jQuery("#entname");	
	
	var sum_viheclecnt=jQuery("#sum_viheclecnt");
	var sum_totalcnt=jQuery("#sum_totalcnt");
	var sum_dayavgcnt=jQuery("#sum_dayavgcnt");
	var sum_amvcnt=jQuery("#sum_amvcnt");

	var sum_pmvcnt=jQuery("#sum_pmvcnt");
	var sum_nightvcnt=jQuery("#sum_nightvcnt");
	var sum_addtovcnt=jQuery("#sum_addtovcnt");
	var sum_activity=jQuery("#sum_activity");

	
	entname.html("-");
	sum_viheclecnt.html("-");
	sum_totalcnt.html("-");
	sum_dayavgcnt.html("-");
	
	sum_amvcnt.html("-");
	sum_pmvcnt.html("-");
	
	sum_nightvcnt.html("-");
	sum_addtovcnt.html("-");
	sum_activity.html("-");
}
function searchChartData()
{
	setChartLoading("n_chart");

	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");	
	var dateType=gtype.selectedType;
	var plat=jQuery("#plat").val();
	
	
	var url='<s:url value="/ub/custom/json/getOneLines.shtml" />'
			+'?startDay='+startDate+'&endDay='+endDate+'&dateType='+dateType+"&plat="+plat+"&customerID="+customID;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			chartData=data;
			loadChart(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			setChartNoData("n_chart");
		}
	});

	
}
function loadChart(data)
{
	if(data==null)
	{
		loger("loadChart:data is null");
		setChartNoData("n_chart");
		return;		
	}
	if(data.lines==null || data.lines.length<=0)
	{
		loger("loadChart:data.lines is null or empty.");
		setChartNoData("n_chart");
		return;			
	}
	var indicators=indicator1.selectedItems;
	if(indicators.length>1)
	{
		var strbarms=[];
		var categoryarray=[],vcntarray=[],vamcntarray=[],vpmcntarray=[],vnicntarray=[],vaddcntarray=[];
		strbarms.push("<chart caption='' xAxisName='' yAxisName='' >");
		categoryarray.push(" <categories>");
		
		vcntarray.push("<dataset seriesName='访问次数'>");
		vamcntarray.push("<dataset seriesName='上午访问数'>");
		vpmcntarray.push("<dataset seriesName='下午访问数'>");
		vnicntarray.push("<dataset seriesName='晚上访问数'>");
		vaddcntarray.push("<dataset seriesName='追加访问数'>");
		
		for(var i=0;i<data.lines.length;i++)
		{
			var item=data.lines[i];
			
			var s= "<category  label='"+item.reportDate+"' />";
			categoryarray.push(s);
			
			vcntarray.push("<set value='"+item.vis+"'  tooltext='"+item.reportDate+"{br}访问次数:"+item.vis+" 占比："+item.visPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			vamcntarray.push("<set value='"+item.amVis+"'  tooltext='"+item.reportDate+"{br}上午访问次数:"+item.amVis+" 占比："+item.amVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			vpmcntarray.push("<set value='"+item.pmVis+"'  tooltext='"+item.reportDate+"{br}下午访问次数:"+item.pmVis+" 占比："+item.pmVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			vnicntarray.push("<set value='"+item.niVis+"'  tooltext='"+item.reportDate+"{br}晚上访问次数:"+item.niVis+" 占比："+item.niVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			vaddcntarray.push("<set value='"+item.addVis+"'  tooltext='"+item.reportDate+"{br}追加访问次数:"+item.addVis+" 占比："+item.addVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			
		}
		categoryarray.push("</categories>");
		vcntarray.push("</dataset>");
		vamcntarray.push("</dataset>");
		vpmcntarray.push("</dataset>");
		vnicntarray.push("</dataset>");
		vaddcntarray.push("</dataset>");
		
		
		strbarms.push(categoryarray.join(""));
		
		
		var check=jQuery.inArray("vcnt",indicators);		
		if(check!=-1)strbarms.push(vcntarray.join(""));
		
		check=jQuery.inArray("amvcnt",indicators);
		if(check!=-1)strbarms.push(vamcntarray.join(""));

		check=jQuery.inArray("pmvcnt",indicators);
		if(check!=-1)strbarms.push(vpmcntarray.join(""));

		check=jQuery.inArray("nightvcnt",indicators);
		if(check!=-1)strbarms.push(vnicntarray.join(""));

		check=jQuery.inArray("addtovcnt",indicators);
		if(check!=-1)strbarms.push(vaddcntarray.join(""));

		var tail="<styles>"+
						"<definition>"+
						"<style name='MyCaptionFontStyle' type='font' face='Verdana' size='16' bold='1' />"+
						"<style name='myToolTipFont' type='font' font='Arial' size='12' color='FF5904'/>"+
						"</definition>"+
						"<application>"+
							"<apply toObject='caption' styles='MyCaptionFontStyle' />"+
							"<apply toObject='ToolTip' styles='myToolTipFont' />"+
						"</application>"+
						"</styles>" +
					"</chart>";
		strbarms.push(tail);
		var xml=strbarms.join("");
		var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/MSLine.swf' />","myChartId4", "1000","300","0");
		
		myChartbarms.setDataXML(xml);
		myChartbarms.render("n_chart");
		
	}
	else if(indicators.length=1)
	{
		loger("选择一个指标");
		
		var sbar=[];
		var categoryarray=[],vcntarray=[],vamcntarray=[],vpmcntarray=[],vnicntarray=[],vaddcntarray=[];
		sbar.push("<chart caption=''  xAxisName=''  yAxisName='' >");
				
		var indct=indicators[0];
		
		for(var i=0; i< data.lines.length;i++)
		{
			var item=data.lines[i];
			vcntarray.push("<set label='"+item.reportDate+"' value='"+item.vis+"' tooltext='"+item.reportDate+";访问次数："+item.vis+";占比："+item.visPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			vamcntarray.push("<set label='"+item.reportDate+"' value='"+item.amVis+"' tooltext='"+item.reportDate+";上午访问次数："+item.amVis+";占比："+item.amVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+" />");
			vpmcntarray.push("<set label='"+item.reportDate+"' value='"+item.pmVis+"' tooltext='"+item.reportDate+";下午访问次数："+item.pmVis+";占比："+item.pmVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+" />");
			vnicntarray.push("<set label='"+item.reportDate+"' value='"+item.niVis+"' tooltext='"+item.reportDate+";晚上访问次数："+item.niVis+";占比："+item.niVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+" />");
			vaddcntarray.push("<set label='"+item.reportDate+"' value='"+item.addVis+"' tooltext='"+item.reportDate+";追加访问次数："+item.addVis+";占比："+item.addVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+" />");
			
		}
		switch(indct)
		{
		case "vcnt":
			sbar.push(vcntarray.join(""));
			break;
		case "amvcnt":
			sbar.push(vamcntarray.join(""));
			break;
		case "pmvcnt":
			sbar.push(vpmcntarray.join(""));
			break;
		case "nightvcnt":
			sbar.push(vnicntarray.join(""));
			break;
		case "addtovcnt":
		default:
			sbar.push(vaddcntarray.join(""));
			break;
		}
		var tail="<styles>"+
		"<definition>"+
		"<style name='MyCaptionFontStyle' type='font' face='Verdana' size='16' bold='1' />"+
		"<style name='myToolTipFont' type='font' font='Arial' size='12' color='FF5904'/>"+
		"</definition>"+
		"<application>"+
			"<apply toObject='caption' styles='MyCaptionFontStyle' />"+
			"<apply toObject='ToolTip' styles='myToolTipFont' />"+
		"</application>"+
		"</styles>" +
		"</chart>";
		var charXml=sbar.join("")+tail;
		var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/Line.swf' />","myChartId4", "1000","300","0");
		myChartbarms.setDataXML(charXml);
		myChartbarms.render("n_chart");
		
	}
	else
	{
		loger("未选择指标");
		alert("未选择指标");
	}
}
function getHolidayTip(t)
{
	return (t=="1")?"anchorBgColor='ff0000'":"";
}
function setChartNoData(chartdiv)
{
	//var tmp_loading=jQuery("#tmp_chart_loading").html();
	var tmp_nodata=jQuery("#tmp_chart_nodata").html();
	var chardiv=jQuery("#"+chartdiv);	
	chardiv.html(tmp_nodata);
}
function setChartLoading(chartdiv)
{
	var tmp_loading=jQuery("#tmp_chart_loading").html();
	//var tmp_nodata=jQuery("#tmp_chart_nodata").html();
	var chardiv=jQuery("#"+chartdiv);
	chardiv.html(tmp_loading);
}
function tabClick(url)
{
	var q='?st='+jQuery("#startTime").val()+"&et="+jQuery("#endTime").val()+"&plat="+jQuery("#plat").val()+"&datetype="+gtype.selectedType+"&entID="+customID;	
	var url_q=url+q;
	location=url_q;
}

////////////
	
	//flexgrid
function get_cell_text(pid, cell_idx) 
{
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}




function iniEntID()
{
	customID=getQueryStringByName("entID");
	if(typeof(customID)=="undefined")customID=0;
	
}

jQuery(function() {
	iniEntID();
	
	iniTimeSpan();	
	
   	var q_gtype=getQueryStringByName("datetype");
   	if(typeof(q_gtype)=="undefined")q_gtype="day";
	gtype=new GroupType({containerId:"timespancon1",startTime:jQuery("#startTime").val(),endTime:jQuery("#endTime").val(),selectedType:q_gtype,onChange:onGTypeChange});
   	gtype.render();
 	//初始化indicator
	var g=[
	   	{title:"访问次数",id:"c1",value:"vcnt",selected:true}
	   	,{title:"上午访问次数",id:"c2",value:"amvcnt",selected:false}
		,{title:"下午访问次数",id:"c3",value:"pmvcnt",selected:false}
		,{title:"晚上访问次数",id:"c4",value:"nightvcnt",selected:false}
		,{title:"追加访问次数",id:"c5",value:"addtovcnt",selected:false}
	   	];
	
   	indicator1=new Indicator({onChange:onIndicatorChange,containerId:"indicator_container",maxSel:2,selGroup:g});
   	indicator1.render();
   	iniPlat();
   	searchChartData();	
   	getGather();
   	
	var roleList = jQuery('#vlist');
	roleList.flexigrid({
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},		         
				{name: 'plat', value: jQuery('#plat').val()},
				{name: 'dateType', value: gtype.selectedType},
				{name: 'customerID', value: customID}				
				],
		url: '<s:url value="/ub/custom/json/getOneGrid.shtml" />',
		dataType: 'json',
		height: 'auto',
		width: 'auto',
		colModel : [
		    		{display: '时间', name : 'opententcnt', width : '200', sortable : false, align: 'left'},
		    		{display: '访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
		    		{display: '上午访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
		    		{display: '下午访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
		    		{display: '晚上访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
		    		{display: '追加访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'}	    		
		    		],
		    	//sortname: 'apply_id',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp:20,	
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		    	autoload:true
	});
	
	HelpTip.initTip();
});
function searchList() {
	jQuery('#empDiv').css("display","none");
	jQuery('#vlist').flexOptions({
		newp: 1,
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},		         
				{name: 'plat', value: jQuery('#plat').val()},
				{name: 'dateType', value: gtype.selectedType},
				{name: 'customerID', value: customID}				
				]
	});
	jQuery('#vlist').flexReload();
}


</script>
<s:form id="logincntstat_form" action="querystat" method="post">

	<table height="628px" width="100%" border="0" cellspacing="0"
		cellpadding="0">
		<tr valign="top">
			<td class="reportInline">

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				
					<tr>
						<td class="reportOnline2" >
						<div
							style="float: left;  padding: 5px; line-height: 30px;width:100%;">
						
						<ul class="recent_ul">
							<li id="recent7"><a href="#" id="q_r7" onclick="q_switch(7)">
							 <s:property
							value="getText('ub.recent7day')" /></a></li>

							<a href="#" id="q_r30" onclick="q_switch(30)">
							<s:property
							value="getText('ub.recent30day')" /> </a>
							</li>
							</ul>
							
							<span style="float:left;padding:0px 10px;"> <s:property
							value="getText('ub.selectmonth')" />：</span>
							<div style="float:left;padding:0px 10px;">
 &nbsp;
								 <s:textfield
												id="selMonth" name="selMonth" 
												onfocus="var date=getMaxDateMonth();WdatePicker({maxDate:date,skin:'whyGreen',dateFmt:'yyyy-MM',onpicked:selMonthClick})"
												cssClass="Wdate" ></s:textfield>
							</div>
							<span style="float:left;padding:0px 10px;"> <s:property
							value="getText('ub.dateduration')" />：</span>
							<div style="float:left;padding:0px 10px;">

								 <s:textfield
								id="startTime" name="searchparam.startDate"
								onfocus="WdatePicker({maxDate:'#F{$dp.$D(\\\'endTime\\\');}',dateFmt:'yyyy-MM-dd',autoPickDate:true,onpicked:function(){startTimeClick();endTime.focus();}})"
								cssClass="Wdate">
							</s:textfield>-<s:textfield id="endTime" name="searchparam.endDate"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\\\'startTime\\\');}',maxDate:'%y-%M-{%d-1}',dateFmt:'yyyy-MM-dd',autoPickDate:true,onpicked:endTimeClick})"
								cssClass="Wdate">
							</s:textfield>
							</div>
							<!-- timespan -->
							<div id="timespancon1" style="margin:0px 10px;float:left;"></div>						
							<!-- timespan -->
							<div style="float:left;padding:0px 10px;" >
									<s:property value="getText('ub.selectplat')" />：
	                              
	                              	 <s:select id="plat" list="#{'all':'所有','web':'WEB平台','app':'手机客户端'}"  listKey="key" listValue="value" onchange="platClick()">
			                        </s:select>
			                        </div>

						</div>

						</td>
					</tr>
				
				<tr style="background:white;">
					<td valign="top"><!-- body -->
					<div style="margin:15px 15px 10px 15px;">
								<span id="entname" class="questiontitle"></span>
							</div>
					<div style="margin:10px;min-height:600px;background-color:white;">
						<div class="ubtab">
							<ul>
							<li class="ubtabselect"><a href="#" > 总体访问情况</a></li>
							<li><a href="#" onclick="tabClick('<s:url value='/ub/custom/entaccount.shtml'/>')"> 各个账号访问情况 </a></li>
							<li><a href="#" onclick="tabClick('<s:url value='/ub/custom/entsrv.shtml'/>')"> 服务访问情况 </a></li>
								
							</ul>
							<div class="ubtabcontent" >
								<div style="margin:5px">
								
							
						
							<div style="margin:20px 15px 10px 15px;">
								<table class="summary" width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
									<span class="text" _ubtip="ent_sin_vehicletotal">车辆总数</span><br/>
									<span class="value" id="sum_viheclecnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="ent_sin_total">总访问次数</span><br/>
									<span class="value" id="sum_totalcnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="ent_sin_dayvis">日均访问次数</span><br/>
									<span class="value" id="sum_dayavgcnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="ent_sin_amvis">上午访问次数</span><br/>
									<span class="value" id="sum_amvcnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="ent_sin_pmvis">下午访问次数</span><br/>
									<span class="value" id="sum_pmvcnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="ent_sin_nivis">晚上访问次数</span><br/>
									<span class="value" id="sum_nightvcnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="ent_sin_addvis">追加访问次数</span><br/>
									<span class="value" id="sum_addtovcnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="ent_sin_isac">是否活跃</span><br/>
									<span class="value" id="sum_activity">-</span>
									</td>
								</tr>
								</table> 
							</div>
							
							<div id="n_indicator" style="height:30px;width:100%;margin:10px 10px 10px 10px;">
							<div class="chartlegent"><span>节假日</span></div>
							<div id="indicator_container"></div>
							
								
							</div>
						
						<div style="text-align:center;width:100%;">
							<div id="n_chart"></div>
						</div>
						<div style="margin:5px;border:0px solid #cccccc;">
							<div style="height:30px;background:#eeeeee;border-top:1px solid #cccccc;border-right:1px solid #cccccc;border-left:1px solid #cccccc;margin-bottom:0px;line-height:30px;font-weight:bold;padding-left:10px;"><s:property
							value="getText('ub.passivity.vrecordlist')" /></div>		
							<div style="margin:0px;">				
						  	<table id="vlist" width="100%"  cellspacing="0">
                          	</table>
                          	</div>
						</div>
						
								</div>
							</div>
						</div><!-- ubtab -->
						
						
						
						<!-- tab内容 -->
						
					</div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
</s:form>
<script type="text/html" id="tmp_chart_nodata">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:400px;width:500px;color:rgb(234,118,79);line-height:400px;border:1px solid #ccc;">图表无数据！</div>
</script>
<script type="text/html" id="tmp_chart_loading">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:400px;width:500px;color:rgb(72,190,244);line-height:400px;border:1px solid #ccc;">正在加载图表数据...</div>
</script>

<%@include file="../../common/copyright.jsp"%>
</body>
</html>