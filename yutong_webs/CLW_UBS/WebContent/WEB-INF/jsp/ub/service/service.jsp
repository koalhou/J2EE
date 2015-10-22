<%@page language="java" contentType="text/html;charset=utf-8"%>

<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/poshytip/tip-yellow/tip-yellow.css'/>">
<title><s:property
	value="getText('menu.ub.serviceliveness')" /></title>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>



</head>
<body>
<%@include file="../../common/menu.jsp"%>
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
	src="<s:url value='/scripts/indicator/Indicator.js' />"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/common/moment.min.js' />"></script>
<script type="text/javascript" 
	src="<s:url value='/scripts/grouptype/GroupType.js' />"></script>
	
	<script type="text/javascript"
	src="<s:url value='/scripts/poshytip/jquery.poshytip.js' />"></script>	
<script type="text/javascript"
	src="<s:url value='/scripts/common/HelpTip.js' />"></script>
<script type="text/javascript">
var gtype=null;
var indicator1=null;
var chartData=null;
var pieData=null;
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
function iniServiceId()
{
	var v_srvid=getQueryStringByName("srvID");
	if(v_srvid)
	{
		jQuery("#servicelist").val(v_srvid);
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
	dealSummary();
	searchChartData();
	searchList();
	dealSingleStat();
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
	dealSummary();
	searchChartData();
	searchList();
	dealSingleStat();
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
	dealSummary();
	searchChartData();
	searchList();
	dealSingleStat();
}
function serviceClick()
{
	dealSummary();
	searchChartData();
	searchList();
	dealSingleStat();
}
function platClick()
{		
	iniServiceList();
}
function onGTypeChange(data)
{
	loger(data);
	searchChartData();
	searchList();
}
function onIndicatorChange(data)
{	
	loger(data.join(","));
	loadChart(chartData);
	
}
function dealSummary()
{
	var serviceId=jQuery("#servicelist").val();
	if(serviceId==0 )
	{
		jQuery("#oneSummaryCon").hide();
		jQuery("#summaryCon").show();
		
		getSummary();
	}
	else
	{
		jQuery("#oneSummaryCon").show();
		jQuery("#summaryCon").hide();
		
		getOneSummary();
		
	}
}
//所有服务汇总摘要统计
function getSummary()
{
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	var plat=jQuery("#plat").val();
	var serviceId=jQuery("#servicelist").val();
	
	var url='<s:url value="/ub/srv/json/getSummary.shtml" />'
		+'?startDay='+startDate+'&endDay='+endDate+"&plat="+plat+"&serviceID="+serviceId;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			loadSummary(data);
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			clearSummary();
		}
	});
}
function loadSummary(data)
{
	if(data==null || data.summary==null)
	{
		loger("gather data is null.");
		clearGather();
		return;
	}
	var d=data.summary;
	var sum_totalvcnt=jQuery("#sum_totalvcnt");
	var sum_totalentvcnt=jQuery("#sum_totalentvcnt");
	var sum_entvdaycnt=jQuery("#sum_entvdaycnt");
	var sum_openentcnt=jQuery("#sum_openentcnt");
	var sum_fullbasecnt_rate=jQuery("#sum_fullbasecnt_rate");
	var sum_fullquacnt_rate=jQuery("#sum_fullquacnt_rate");
	
	sum_totalvcnt.html(d.vis);
	sum_totalentvcnt.html(d.visEntCnt);
	sum_entvdaycnt.html(d.dayVis);
	sum_openentcnt.html(d.openEntCnt);
	sum_fullbasecnt_rate.html(d.entBaseReqStr);
	sum_fullquacnt_rate.html(d.entQualityReqStr);
}
function clearSummary()
{
	var sum_totalvcnt=jQuery("#sum_totalvcnt");
	var sum_totalentvcnt=jQuery("#sum_totalentvcnt");
	var sum_entvdaycnt=jQuery("#sum_entvdaycnt");
	var sum_openentcnt=jQuery("#sum_openentcnt");
	var sum_fullbasecnt_rate=jQuery("#sum_fullbasecnt_rate");
	var sum_fullquacnt_rate=jQuery("#sum_fullquacnt_rate");
	
	sum_totalvcnt.html("-");
	sum_totalentvcnt.html("-");
	sum_entvdaycnt.html("-");
	sum_openentcnt.html("-");
	sum_fullbasecnt_rate.html("-");
	sum_fullquacnt_rate.html("-");
	
}
/*
 * 
 *单个服务汇总摘要统计
 * 
 */
function getOneSummary()
{
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	var plat=jQuery("#plat").val();
	var serviceId=jQuery("#servicelist").val();
	
	var url='<s:url value="/ub/srv/json/getOneSummary.shtml" />'
		+'?startDay='+startDate+'&endDay='+endDate+"&plat="+plat+"&serviceID="+serviceId;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			loadOneSummary(data);
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			clearOneSummary();
		}
	});
}
function loadOneSummary(data)
{
	if(data==null || data.summary==null)
	{
		loger("gather data is null.");
		clearGather();
		return;
	}
	var d=data.summary;
	var onesum_totalvcnt=jQuery("#onesum_totalvcnt");
	var onesum_totalentvcnt=jQuery("#onesum_totalentvcnt");
	var onesum_openentcnt=jQuery("#onesum_openentcnt");
	
	var onesum_isfullbase=jQuery("#onesum_isfullbase");
	var onesum_isfullqua=jQuery("#onesum_isfullqua");
	var onesum_srvopentime=jQuery("#onesum_srvopentime");
	
	onesum_totalvcnt.html(d.vis);
	onesum_totalentvcnt.html(d.visEntCnt);
	onesum_openentcnt.html(d.openEntCnt);
	onesum_isfullbase.html(d.entBaseReqStr);
	onesum_isfullqua.html(d.entQualityReqStr);
	onesum_srvopentime.html(d.reportDate);
}
function clearOneSummary()
{
	var onesum_totalvcnt=jQuery("#onesum_totalvcnt");
	var onesum_totalentvcnt=jQuery("#onesum_totalentvcnt");
	var onesum_entvdaycnt=jQuery("#onesum_openentcnt");
	
	var onesum_isfullbase=jQuery("#onesum_isfullbase");
	var onesum_isfullqua=jQuery("#onesum_isfullqua");
	var onesum_srvopentime=jQuery("#onesum_srvopentime");
	
	onesum_totalvcnt.html("-");
	onesum_totalentvcnt.html("-");
	onesum_entvdaycnt.html("-");
	onesum_isfullbase.html("-");
	onesum_isfullqua.html("-");
	onesum_srvopentime.html("-");
	
}
/*
 * 单个服务下，统计饼图，及区域统计二级列表
 
 
 */
function dealSingleStat()
{
	var serviceId=jQuery("#servicelist").val();
	var con=jQuery("#singleSrvContainer");
	(serviceId==0)?con.hide():con.show();
	if(serviceId!=0)
	{
		//加载饼图，列表数据
		getPie();
	}
	
}
function getPie()
{
	setChartLoading("n_chartsrv",500,400);
	
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	var plat=jQuery("#plat").val();
	var serviceId=jQuery("#servicelist").val();
		
	var url='<s:url value="/ub/srv/json/getVis4Area.shtml" />'
			+'?startDay='+startDate+'&endDay='+endDate+"&plat="+plat+"&serviceID="+serviceId;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			pieData=data;
			loadPie(data);
			loadZoneList(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			setChartNoData("n_chartsrv",500,400);
			clearZoneList();
		}
	});
}
function sumtableToggle(o)
{
	var dd=jQuery(o);
	var paid=dd.attr("_target");
	if(dd.hasClass("collapse"))
	{
		dd.removeClass("collapse");
		dd.addClass("open");		
		jQuery('tr[_parent="' + paid+ '"]').show();
	}
	else
	{	
		dd.addClass("collapse");
		dd.removeClass("open");
		jQuery('tr[_parent="' + paid+ '"]').hide();		
	}
}
function loadZoneList(data)
{
	var str=[];
	if(data==null || data.apgList==null ||data.apgList.length<=0)
	{
		loger("no pie data.");
		clearZoneList();
		return;
	}
	var tmpparent='<tr id="[[trid]]" class="parent">'+
						'<td class="collapse"  onclick="sumtableToggle(this)" _target="[[trid]]" ></td>'+
						'<td>[[name]]</td>'+
						'<td>[[value]]</td>'+
					'</tr>';
	var tmpchild='<tr _parent="[[trid]]" class="child" style="display:none;">'+
						'<td></td>'+
						'<td>[[name]]</td>'+
						'<td>[[value]]</td>'+
					'</tr>';
	for(var i=0;i<data.apgList.length;i++)
	{
		var o={trid:"sumtable_tr"+i,name:data.apgList[i].name,value:data.apgList[i].code};
		var t=GlareTpl.parse(tmpparent,o);
		str.push(t);
		if(data.apgList[i].list!=null)
		{
			for(var j=0;j<data.apgList[i].list.length;j++)
			{
				var jo=	{trid:"sumtable_tr"+i,name:data.apgList[i].list[j].name,value:data.apgList[i].list[j].code};
				var tt=GlareTpl.parse(tmpchild,jo);
				str.push(tt);
			}
		}
	}
	var ele=str.join("");
	jQuery("#zoneStatList tbody").html(ele);
}
function clearZoneList()
{
	jQuery("#zoneStatList tbody").empty();	
	jQuery("#zoneStatList tbody").append("<tr><td colspan='3' class='nodata'>无数据</td></tr>");
}
function loadPie(data)
{
	var pieZone=jQuery("#pie_zone").attr("checked");
	
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
	var piestr=[];
	piestr.push("<chart caption='' xAxisName='' yAxisName='' >");
	if(pieZone)
	{
		if(data==null || data.apList==null||data.apList.length<=0)
		{
			loger("no pie data.");
			setChartNoData("n_chartsrv",500,400);
			return;
		}
		
		
		for(var i=0;i<data.apList.length;i++)
		{
			piestr.push("<set label='"+data.apList[i].name+"' value='"+data.apList[i].code+"' />");
		}		
		
	}
	else
	{
		if(data==null || data.ppList==null||data.ppList.length<=0)
		{
			loger("no pie data.");
			setChartNoData("n_chartsrv",500,400);
			return;
		}
		for(var i=0;i<data.ppList.length;i++)
		{
			piestr.push("<set label='"+data.ppList[i].name+"' value='"+data.ppList[i].code+"' />");
		}		
		
	}
	piestr.push(tail);
	var xml=piestr.join("");
	var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/Pie2D.swf' />","myChartId4", "500","400","0");
	
	myChartbarms.setDataXML(xml);
	myChartbarms.render("n_chartsrv");
	
}
function pieRadioClick(o)
{
	loadPie(pieData);
}
//折线图统计
function searchChartData()
{
	setChartLoading("n_chart");
	
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	var plat=jQuery("#plat").val();
	var serviceId=jQuery("#servicelist").val();
	var dateType=gtype.selectedType;
	
	//?plat=web&startDay=20130601&endDay=20130720&dateType=week&serviceID=2
	var url='<s:url value="/ub/srv/json/getChartByIndex.shtml" />'
			+'?startDay='+startDate+'&endDay='+endDate+'&dateType='+dateType+"&plat="+plat+"&serviceID="+serviceId;
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
		var strbarms=[];var categoryarray=[],vcntarray=[],ventarray=[],openentcntarray=[],activityarray=[];
		strbarms.push("<chart caption='' xAxisName='' yAxisName='' >");
		categoryarray.push(" <categories>");
		
		vcntarray.push("<dataset seriesName='访问次数'>");
		ventarray.push("<dataset seriesName='访问企业次数'>");
		openentcntarray.push("<dataset seriesName='开通企业数'>");
		activityarray.push("<dataset seriesName='活跃度'>");
		for(var i=0;i<data.lines.length;i++)
		{
			var item=data.lines[i];
			
			var s= "   <category  label='"+item.reportDate+"' />";
			categoryarray.push(s);
			
			vcntarray.push("<set value='"+item.vis+"'  tooltext='"+item.reportDate+"{br}访问次数:"+item.vis+" 占比："+item.visPercent.replace("%","%25")+"' "+getHolidayTip(item.holiday)+"/>");
			ventarray.push("<set value='"+item.visEntCnt+"'  tooltext='"+item.reportDate+"{br}访问企业数:"+item.visEntCnt+" 占比："+item.visEntCntPercent.replace("%","%25")+"' "+getHolidayTip(item.holiday)+"/>");
			openentcntarray.push("<set value='"+item.openEntCnt+"'  tooltext='"+item.reportDate+"{br}开通企业数:"+item.openEntCnt+" 占比："+item.openEntCntPercent.replace("%","%25")+"' "+getHolidayTip(item.holiday)+"/>");
			activityarray.push("<set value='"+item.visActivity+"'  tooltext='"+item.reportDate+"{br}活跃度:"+item.visActivity+"' "+getHolidayTip(item.holiday)+"/>");
		}
		categoryarray.push("</categories>");
		vcntarray.push("</dataset>");
		ventarray.push("</dataset>");
		openentcntarray.push("</dataset>");
		activityarray.push("</dataset>");
		
		strbarms.push(categoryarray.join(""));
		
		
		var check=jQuery.inArray("vcnt",indicators);		
		if(check!=-1)strbarms.push(vcntarray.join(""));
		
		check=jQuery.inArray("ventcnt",indicators);
		if(check!=-1)strbarms.push(ventarray.join(""));
		
		check=jQuery.inArray("openentcnt",indicators);
		if(check!=-1)strbarms.push(openentcntarray.join(""));
		
		check=jQuery.inArray("activity",indicators);
		if(check!=-1)strbarms.push(activityarray.join(""));
		
		
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
		var categoryarray=[],vcntarray=[],ventarray=[],openentcntarray=[],activityarray=[];
		sbar.push("<chart caption=''  xAxisName=''  yAxisName='' >");
				
		var indct=indicator1.selectedItems[0];
		
		for(var i=0; i< data.lines.length;i++)
		{
			var item=data.lines[i];
			vcntarray.push("<set label='"+item.reportDate+"' value='"+item.vis+"' tooltext='"+item.reportDate+";访问次数："+item.vis+";占比："+item.visPercent.replace("%","%25")+"' "+getHolidayTip(item.holiday)+"/>");
			ventarray.push("<set label='"+item.reportDate+"' value='"+item.visEntCnt+"' tooltext='"+item.reportDate+";访问企业数："+item.visEntCnt+";占比："+item.visEntCntPercent.replace("%","%25")+"' "+getHolidayTip(item.holiday)+" />");
			openentcntarray.push("<set label='"+item.reportDate+"' value='"+item.openEntCnt+"' tooltext='"+item.reportDate+";开通企业数："+item.openEntCnt+";占比："+item.openEntCntPercent.replace("%","%25")+"' "+getHolidayTip(item.holiday)+"/>");
			activityarray.push("<set label='"+item.reportDate+"' value='"+item.visActivity+"' tooltext='"+item.reportDate+";活跃度："+item.activity+"' "+getHolidayTip(item.holiday)+"/>");
			
		}
		switch(indct)
		{
		case "vcnt":
			sbar.push(vcntarray.join(""));
			break;
		case "ventcnt":
			sbar.push(ventarray.join(""));
			break;
		case "openentcnt":
			sbar.push(openentcntarray.join(""));
			break;
		case "activity":
		default:
			sbar.push(activityarray.join(""));
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
function setChartNoData(chartdiv,w,h)
{
	if(typeof(w)=="undefined")w=1000;
	if(typeof(h)=="undefined")h=300;
	var tmp_nodata=jQuery("#tmp_chart_nodata").html();
	var tmp=GlareTpl.parse(tmp_nodata,{width:w,height:h});
	var chardiv=jQuery("#"+chartdiv);	
	chardiv.html(tmp);
}
function setChartLoading(chartdiv,w,h)
{	
	if(typeof(w)=="undefined")w=1000;
	if(typeof(h)=="undefined")h=300;
	var tmp_loading=jQuery("#tmp_chart_loading").html();
	var tmp=GlareTpl.parse(tmp_loading,{width:w,height:h});
	//var tmp_nodata=jQuery("#tmp_chart_nodata").html();
	var chardiv=jQuery("#"+chartdiv);
	chardiv.html(tmp);
}
function tabClick(url)
{
	var q='?st='+jQuery("#startTime").val()+"&et="+jQuery("#endTime").val()+"&plat="+jQuery("#plat").val()+"&datetype="+gtype.selectedType+"&srvID="+jQuery("#servicelist").val();
	var url_q=url+q;
	location=url_q;
}
function iniServiceList()
{
	var ser=jQuery("#servicelist");
	ser.empty();
	var url='<s:url value="/ub/srv/json/getSrvList.shtml" />'
		+"?plat="+jQuery('#plat').val();
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			if(data==null || data.srvList==null)
			{
				loger("加载服务列表：数据错误。");
				return;
			}
			loadServiceList(data.srvList);			
			iniServiceId();  
			
			dealSummary();			
			searchChartData();
			searchList();
			dealSingleStat();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			alert("加载服务列表出错！错误信息："+textStatus);
		}
	});	
}
function loadServiceList(data)
{
	var ser=jQuery("#servicelist");
	ser.append("<option value='0'>全部服务</option>");
	for(var i=0;i<data.length;i++)
	{
		ser.append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
		
	}
}
////////////

function namePercent(tdDiv,pid)
{
	tdDiv.innerHTML=tdDiv.innerHTML+"%";	
	
}
	//flexgrid
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

jQuery(function() {
	iniTimeSpan();
   	var q_gtype=getQueryStringByName("datetype");
   	if(typeof(q_gtype)=="undefined")q_gtype="day";
	gtype=new GroupType({containerId:"timespancon1",startTime:jQuery("#startTime").val(),endTime:jQuery("#endTime").val(),selectedType:q_gtype,onChange:onGTypeChange});
   	gtype.render();
	//初始化indicator
	var g=[
	   	{title:"访问次数",id:"c1",value:"vcnt",selected:true}
	   	,{title:"访问企业数",id:"c2",value:"ventcnt",selected:false}
	   	,{title:"开通企业数",id:"c3",value:"openentcnt",selected:false}
	   	,{title:"活跃度",id:"c4",value:"activity",selected:false}
	   	];
	
   	indicator1=new Indicator({onChange:onIndicatorChange,containerId:"indicator_container",maxSel:2,selGroup:g});
   	indicator1.render();
   	
   	iniPlat();
   	iniServiceList();
   	
   	var roleList = jQuery('#vlist');
	roleList.flexigrid({
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},
		         {name: 'serviceID', value: jQuery('#servicelist').val()},
		         {name: 'dateType', value: gtype.selectedType},
				{name: 'plat', value: jQuery('#plat').val()}],
		url: '<s:url value="/ub/srv/json/getVisList.shtml" />',
		dataType: 'json',
		height: 'auto',
		width: 'auto',
		colModel : [
					
		    		{display: '时间', name : 'time', width : '200', sortable : true, align: 'left'},
		    		{display: '访问次数', name : 'vcnt', width : '100', sortable : true, align: 'left'},
		    		{display: '访问企业数', name : 'ventcnt', width : '100', sortable : true, align: 'left'},
		    		{display: '开通企业数', name : 'opententcnt', width : '100', sortable : true, align: 'left'},
		    		{display: '活跃度', name : 'activity', width : '100', sortable : true, align: 'left',process:namePercent}
		    		],
		    	sortname: 'id',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp:20,	
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		    	autoload:false//先不要自动加载
	});
	HelpTip.initTip();

});


function searchList() {
	jQuery('#empDiv').css("display","none");
	jQuery('#vlist').flexOptions({
		newp: 1,
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},
		         {name: 'serviceID', value: jQuery('#servicelist').val()},
		         {name: 'dateType', value: gtype.selectedType},
				{name: 'plat', value: jQuery('#plat').val()}]
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
						<span style="float:left;padding:0px 10px;font-weight:bold;"> <s:property
							value="getText('ub.system.statduration')" />：</span>
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
                              
                              	 <s:select id="plat" list="#{'web':'WEB平台','app':'手机客户端'}"  listKey="key" listValue="value" onchange="platClick()">
		                        </s:select></div>

						</div>
						</td>
					</tr>
				
				<tr>
					<td valign="top" style="background:white;"><!-- body -->
					<div style="margin:10px;min-height:600px;background-color:white;">
						
						<div class="ubtab">
							<ul>
							<li class="ubtabselect"><a href="#" > <s:property
								value="getText('ub.passivity.totalv')" /> </a></li>
							<li><a href="#" onclick="tabClick('<s:url value='/ub/srv/srvlist.shtml'/>')"> <s:property
								value="getText('ub.service.srvlistv')" /> </a></li>
								
							</ul>
							<div class="ubtabcontent" >
								<div style="margin:5px">
								<div id="tab_content" style="width:100%;height:100%;margin-top:5px;">
							<div style="margin:5px 15px 5px 15px;">
								<span style="font-weight:bold">选择服务：</span>
								<select id="servicelist" onchange="serviceClick()"></select>
								
								
	                        </div>
							<div style="margin:5px 15px 10px 15px;">
							<!-- 所有服务汇总摘要统计 -->
								<table id="summaryCon" class="summary" width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
									<span class="text" _ubtip="srv_total"><s:property
							value="getText('ub.service.totalvcnt')" /></span><br/>
									<span class="value" id="sum_totalvcnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="srv_totalent"><s:property
							value="getText('ub.service.totalentvcnt')" /></span><br/>
									<span class="value" id="sum_totalentvcnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="srv_entdayvis"><s:property
							value="getText('ub.service.entvdaycnt')" /></span><br/>
									<span class="value" id="sum_entvdaycnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="srv_curopenent"><s:property
							value="getText('ub.service.openentcnt')" /></span><br/>
									<span class="value" id="sum_openentcnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="srv_basereq"><s:property
							value="getText('ub.service.fullbasecnt_rate')" /></span><br/>
									<span class="value" id="sum_fullbasecnt_rate">-</span>
									</td>
									<td>
									<span class="text" _ubtip="srv_quareq"><s:property
							value="getText('ub.service.fullquacnt_rate')" /></span><br/>
									<span class="value" id="sum_fullquacnt_rate">-</span>
									</td>
								</tr>
								</table> 
								<!-- 单个服务汇总摘要统计 -->
								<table id="oneSummaryCon" style="display:none;" class="summary" width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
									<span class="text" _ubtip="srv_sin_total"><s:property
							value="getText('ub.service.totalvcnt')" /></span><br/>
									<span class="value" id="onesum_totalvcnt">3000</span>
									</td>
									<td>
									<span class="text" _ubtip="srv_sin_totalent"><s:property
							value="getText('ub.service.totalentvcnt')" /></span><br/>
									<span class="value" id="onesum_totalentvcnt">700</span>
									</td>
									<td>
									<span class="text" _ubtip="srv_sin_curopenent"><s:property
							value="getText('ub.service.openentcnt')" /></span><br/>
									<span class="value" id="onesum_openentcnt">1000</span>
									</td>
									<td>
									<span class="text" _ubtip="srv_sin_basereq"><s:property
							value="getText('ub.service.isfullbase')" /></span><br/>
									<span class="value" id="onesum_isfullbase">20%</span>
									</td>
									<td>
									<span class="text" _ubtip="srv_sin_quareq"><s:property
							value="getText('ub.service.isfullqua')" /></span><br/>
									<span class="value" id="onesum_isfullqua">20%</span>
									</td>
									<td>
									<span class="text" _ubtip="srv_sin_publishtime"><s:property
							value="getText('ub.service.srvopentime')" /></span><br/>
									<span class="value" id="onesum_srvopentime">20%</span>
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
							<div style="height:30px;background:#eeeeee;border-top:1px solid #cccccc;border-left:1px solid #cccccc;border-right:1px solid #cccccc;margin-bottom:0px;line-height:30px;font-weight:bold;padding-left:10px;"><s:property
							value="getText('ub.passivity.vrecordlist')" /></div>		
							<div style="margin:0px;">				
						  	<table id="vlist" width="100%"  cellspacing="0">
                          	</table>
                          	</div>
						</div>
						<!-- 单个服务 -->
						<div id="singleSrvContainer" style="margin:5px;border:1px solid #cccccc;">
							<table  width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="padding:5px;width:50%;" align="center">
										<div id="n_indicator" style="height:30px;width:100%;margin:10px 10px 10px 10px;text-align:left;">
																												
											<span class="n_indicator_cbx">  											
											<label for="pie_zone"  style="margin-left:3px;vertical-align:middle;" ><input name="pie_type" type="radio" id="pie_zone" style="vertical-align:middle;" onclick="pieRadioClick(this)" checked="checked" value="zone"/>大区</label>   
											</span>  
											<span class="n_indicator_cbx">  
											
											<label for="pie_province"  style="margin-left:3px;vertical-align:middle;" ><input  name="pie_type" type="radio" id="pie_province" style="vertical-align:middle;" onclick="pieRadioClick(this)"  value="province"/>省份</label>   
											</span> 
											
										</div>
										<div id="n_chartsrv" ></div>
									</td>
									<td style="padding:5px;width:50%;" align="center">
										<table class="sumtable" id="zoneStatList">
											<thead>
												<tr>
													<th width="20px" style="border:0px;"></th>
													<th width="200px">大区</th>
													<th width="200px">次数</th>
												</tr>
											</thead>
											<tbody>
												<tr id="t1" class="parent">
													<td class="open"  onclick="cc(this)" _target="t1" ></td>
													<td>江浙</td>
													<td>202</td>
												</tr>
												<tr _parent="t1" class="child">
													<td></td>
													<td>浙江</td>
													<td>2022</td>
												</tr>
												<tr _parent="t1" class="child">
													<td></td>
													<td>江苏</td>
													<td>2102</td>
												</tr>
												<tr id="t2" class="parent">
													<td class="open"  onclick="cc(this)" _target="t2" ></td>
													<td>黑吉区域</td>
													<td>202</td>
												</tr>
												<tr _parent="t2" class="child">
													<td></td>
													<td>黑吉区域</td>
													<td>2022</td>
												</tr>
												<tr _parent="t2" class="child">
													<td></td>
													<td>黑吉区域</td>
													<td>2102</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
							</table>
						</div>
						</div>
								</div>
							</div>
						</div>
						
						
						
						
					</div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
</s:form>
<script type="text/html" id="tmp_chart_nodata">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:[[height]]px;width:[[width]]px;color:rgb(234,118,79);line-height:[[height]]px;border:1px solid #ccc;">图表无数据！</div>
</script>
<script type="text/html" id="tmp_chart_loading">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height[[height]]px;width:[[width]]px;color:rgb(72,190,244);line-height:[[height]]px;border:1px solid #ccc;">正在加载图表数据...</div>
</script>

<%@include file="../../common/copyright.jsp"%>
</body>
</html>