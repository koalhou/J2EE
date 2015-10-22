<%@page language="java" contentType="text/html;charset=utf-8"%>
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
	
	
<script type="text/javascript">
var gtype=null;
var indicator1=null;
var chartData_overall=null;
var chartData_srv=null;
var currentTab=null;
var overalllist=null;
var entlist=null;
var srvlist=null;
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
	else
	{
		jQuery("#plat").val("web");
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
	query();
	
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
	
	query();
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
	query();
}

function platClick()
{		
	//iniServiceList();
	query();
}
function onGTypeChange(data)
{
	query();
}
function onIndicatorChange(data)
{		
	loadChart_overall(chartData_overall);	
}


//折线图统计
function searchChartData_overall()
{
	setChartLoading("n_chart_overall",1000,300);
	///////////////////////
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	var plat=jQuery("#plat");
	
	var dateType=gtype.selectedType;
	var z=jQuery("#zoneSelect");
	var p=jQuery("#provinceSelect");
	var c=jQuery("#citySelect");
	var ctype=jQuery("#entTypeSelect");
	var ac=jQuery("#activitySelect");
	
	var url='<s:url value="/ub/custom/json/getLineChart.shtml" />'
			+'?startDay='+startDate+'&endDay='+endDate+'&dateType='+dateType
					+"&plat="+plat.val()+'&area='+z.val()+'&province='+p.val()+'&city='+c.val()
					+"&customerType="+ctype.val()+"&ac="+ac.val();
	
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			chartData_overall=data;
			loadChart_overall(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			setChartNoData("n_chart_overall",1000,300);
		}
	});
	
}

function loadChart_overall(data)
{
	if(data==null)
	{
		loger("loadChart:data is null");
		setChartNoData("n_chart_overall",1000,300);
		return;		
	}
	if(data.lines==null || data.lines.length<=0)
	{
		loger("loadChart:data.lines is null or empty.");
		setChartNoData("n_chart_overall",1000,300);
		return;			
	}
	 var indicators=indicator1.selectedItems;
	if(indicators.length>1)
	{
		var strbarms=[];
		var categoryarray=[],vocntarray=[],vcntarray=[],vamcntarray=[],vpmcntarray=[],
		vnicntarray=[],vaddcntarray=[],openentcntarray=[],acentcntarray=[],activityarray=[];
		
		strbarms.push("<chart caption='' xAxisName='' yAxisName='' >");
		categoryarray.push(" <categories>");
		
		vocntarray.push("<dataset seriesName='原始访问次数'>");
		vcntarray.push("<dataset seriesName='有效访问次数'>");
		vamcntarray.push("<dataset seriesName='上午访问次数'>");
		vpmcntarray.push("<dataset seriesName='下午访问次数'>");
		vnicntarray.push("<dataset seriesName='晚上访问次数'>");
		vaddcntarray.push("<dataset seriesName='追加访问次数'>");
		openentcntarray.push("<dataset seriesName='开通企业数'>");
		acentcntarray.push("<dataset seriesName='活跃企业客户数'>");
		activityarray.push("<dataset seriesName='活跃度'>");
		for(var i=0;i<data.lines.length;i++)
		{
			var item=data.lines[i];
			
			var s= "   <category  label='"+item.reportDate+"' />";
			categoryarray.push(s);
			
			vocntarray.push("<set value='"+item.oVis+"'  tooltext='"+item.reportDate+"{br}原始访问次数:"+item.oVis+" 占比："+item.oVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");//新增“原始访问次数”
			vcntarray.push("<set value='"+item.vis+"'  tooltext='"+item.reportDate+"{br}有效访问次数:"+item.vis+" 占比："+item.visPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");//由之前的“访问次数”改为“有效访问次数”
			vamcntarray.push("<set value='"+item.amVis+"'  tooltext='"+item.reportDate+"{br}上午访问次数:"+item.amVis+" 占比："+item.amVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			vpmcntarray.push("<set value='"+item.pmVis+"'  tooltext='"+item.reportDate+"{br}下午访问次数:"+item.pmVis+" 占比："+item.pmVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			vnicntarray.push("<set value='"+item.niVis+"'  tooltext='"+item.reportDate+"{br}晚上访问次数:"+item.niVis+" 占比："+item.niVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			vaddcntarray.push("<set value='"+item.addVis+"'  tooltext='"+item.reportDate+"{br}追加访问次数:"+item.addVis+" 占比："+item.addVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			
			openentcntarray.push("<set value='"+item.openEntCnt+"'  tooltext='"+item.reportDate+"{br}开通企业数:"+item.openEntCnt+" 占比："+item.openEntCntPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			acentcntarray.push("<set value='"+item.emEntCnt+"'  tooltext='"+item.reportDate+"{br}活跃企业客户数:"+item.emEntCnt+" 占比："+item.emEntCntPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			activityarray.push("<set value='"+item.ep+"'  tooltext='"+item.reportDate+"{br}活跃度:"+item.ep+"%25' "+getHolidayTip(item.isHoliday)+"/>");
		}
		categoryarray.push("</categories>");
		vocntarray.push("</dataset>");
		vcntarray.push("</dataset>");
		vamcntarray.push("</dataset>");
		vpmcntarray.push("</dataset>");
		vnicntarray.push("</dataset>");
		vaddcntarray.push("</dataset>");
		
		acentcntarray.push("</dataset>");
		openentcntarray.push("</dataset>");
		activityarray.push("</dataset>");
		
		strbarms.push(categoryarray.join(""));
		
		var check=jQuery.inArray("vocnt",indicators);		
		if(check!=-1)strbarms.push(vocntarray.join(""));
		
		check=jQuery.inArray("vcnt",indicators);		
		if(check!=-1)strbarms.push(vcntarray.join(""));
		
		check=jQuery.inArray("vamcnt",indicators);
		if(check!=-1)strbarms.push(vamcntarray.join(""));
		
		check=jQuery.inArray("vpmcnt",indicators);
		if(check!=-1)strbarms.push(vpmcntarray.join(""));
		
		check=jQuery.inArray("vnicnt",indicators);
		if(check!=-1)strbarms.push(vnicntarray.join(""));

		check=jQuery.inArray("vaddcnt",indicators);
		if(check!=-1)strbarms.push(vaddcntarray.join(""));

		check=jQuery.inArray("openentcnt",indicators);
		if(check!=-1)strbarms.push(openentcntarray.join(""));

		check=jQuery.inArray("acentcnt",indicators);
		if(check!=-1)strbarms.push(acentcntarray.join(""));

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
		myChartbarms.render("n_chart_overall");
		
	}
	else if(indicators.length=1)
	{
		
		var sbar=[];
		var categoryarray=[],vocntarray=[],vcntarray=[],vamcntarray=[],vpmcntarray=[],
		vnicntarray=[],vaddcntarray=[],openentcntarray=[],acentcntarray=[],activityarray=[];
		sbar.push("<chart caption=''  xAxisName=''  yAxisName='' >");
				
		var indct=indicator1.selectedItems[0];
		
		for(var i=0; i< data.lines.length;i++)
		{
			var item=data.lines[i];
			vocntarray.push("<set label='"+item.reportDate+"' value='"+item.oVis+"' tooltext='"+item.reportDate+";原始访问次数："+item.oVis+";占比："+item.oVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			vcntarray.push("<set label='"+item.reportDate+"' value='"+item.vis+"' tooltext='"+item.reportDate+";有效访问次数："+item.vis+";占比："+item.visPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			vamcntarray.push("<set label='"+item.reportDate+"' value='"+item.amVis+"' tooltext='"+item.reportDate+";上午访问次数："+item.amVis+";占比："+item.amVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+" />");
			vpmcntarray.push("<set label='"+item.reportDate+"' value='"+item.pmVis+"' tooltext='"+item.reportDate+";下午访问次数："+item.pmVis+";占比："+item.pmVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+" />");
			vnicntarray.push("<set label='"+item.reportDate+"' value='"+item.niVis+"' tooltext='"+item.reportDate+";晚上访问次数："+item.niVis+";占比："+item.niVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+" />");
			vaddcntarray.push("<set label='"+item.reportDate+"' value='"+item.addVis+"' tooltext='"+item.reportDate+";追加访问次数："+item.addVis+";占比："+item.addVisPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+" />");

			openentcntarray.push("<set label='"+item.reportDate+"' value='"+item.openEntCnt+"' tooltext='"+item.reportDate+";开通企业数："+item.openEntCnt+";占比："+item.openEntCntPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			acentcntarray.push("<set label='"+item.reportDate+"' value='"+item.emEntCnt+"' tooltext='"+item.reportDate+";活跃企业客户数："+item.emEntCnt+";占比："+item.emEntCntPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			activityarray.push("<set label='"+item.reportDate+"' value='"+item.ep+"' tooltext='"+item.reportDate+";活跃度："+item.ep+"%25' "+getHolidayTip(item.isHoliday)+"/>");
			
		}
		switch(indct)
		{
		case "vocnt":
			sbar.push(vocntarray.join(""));
			break;
		case "vcnt":
			sbar.push(vcntarray.join(""));
			break;
		case "vamcntcnt":
			sbar.push(vamcntarray.join(""));
			break;
		case "vpmcntcnt":
			sbar.push(vpmcntarray.join(""));
			break;
		case "vnicntcnt":
			sbar.push(vnicntarray.join(""));
			break;
		case "vaddcntcnt":
			sbar.push(vaddcntarray.join(""));
			break;
		case "openentcnt":
			sbar.push(openentcntarray.join(""));
			break;
		case "acentcnt":
			sbar.push(acentcntarray.join(""));
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
		myChartbarms.render("n_chart_overall");
		
	}
	else
	{
		loger("未选择指标");
		alert("未选择指标");
	}
}
function searchChartData_srv()
{
	setChartLoading("n_chart_srv",1000,300);
	///////////////////////
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	var plat=jQuery("#plat");
	
	var dateType=gtype.selectedType;
	var z=jQuery("#zoneSelect");
	var p=jQuery("#provinceSelect");
	var c=jQuery("#citySelect");
	var ctype=jQuery("#entTypeSelect");
	var ac=jQuery("#activitySelect");
	
	var url='<s:url value="/ub/custom/json/getServiceVisBars.shtml" />'
			+'?startDay='+startDate+'&endDay='+endDate+'&dateType='+dateType
					+"&plat="+plat.val()+'&area='+z.val()+'&province='+p.val()+'&city='+c.val()
					+"&customerType="+ctype.val()+"&ac="+ac.val();
	
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			chartData_srv=data;
			loadChart_srv(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			setChartNoData("n_chart_srv",1000,300);
		}
	});
	
}

function loadChart_srv(data)
{
	if(data==null)
	{
		loger("loadChart:data is null");
		setChartNoData("n_chart_srv",1000,300);
		return;		
	}
	if(data.bars==null || data.bars.length<=0)
	{
		loger("bars is null or empty.");
		setChartNoData("n_chart_srv",1000,300);
		return;			
	}
	
		
		var sbar=[];
		var srvarray=[];
		sbar.push("<chart caption=''  xAxisName=''  yAxisName='' >");
				
		
		
		for(var i=0; i< data.bars.length;i++)
		{
			var item=data.bars[i];
			srvarray.push("<set label='"+item.name+"' value='"+item.vis+"' tooltext='"+item.name+";访问次数："+item.vis+"' />");			
		}
	
		sbar.push(srvarray.join(""));
		
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
		var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/Column2D.swf' />","myChartId4", "1000","300","0");
		myChartbarms.setDataXML(charXml);
		myChartbarms.render("n_chart_srv");
		
	
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

function tabClick(tabid)
{
	setTab(tabid);
	query();
}
function setTab(tabid)
{	
	if(tabid==currentTab)
		return;
	var splat=jQuery("#plat");
	var p=splat.val();
	splat.empty();

	switch(tabid)
	{
		case "overall":
			jQuery("#tab_content_overall").show();
			jQuery("#tab_content_ent").hide();
			jQuery("#tab_content_srv").hide();
			splat.append("<option value='all'>所有</option>");
			break;
		case "ent":
			jQuery("#tab_content_overall").hide();
			jQuery("#tab_content_ent").show();
			jQuery("#tab_content_srv").hide();
			splat.append("<option value='all'>所有</option>");
			break;
		case "srv":
			jQuery("#tab_content_overall").hide();
			jQuery("#tab_content_ent").hide();
			jQuery("#tab_content_srv").show();
			break;
	}
	splat.append("<option value='web'>WEB平台</option>");
	splat.append("<option value='app'>手机客户端</option>");
	splat.val(p);
	
	//var currenttabid=jQuery("#customertab #current").attr("id","");
	//jQuery("#customertab li a[_tabid='"+tabid+"']").attr("id","current");
	var currentel=jQuery("[_tabid='"+tabid+"']");
	currentel.parent().parent().children("li").removeClass("ubtabselect");
	currentel.parent().addClass("ubtabselect");
	currentTab=tabid;
}
function iniTab()
{
	var v=getQueryStringByName("tab");
	if(jQuery.inArray(v,['overall','ent','srv'])<0)
	{
		v="overall";
	}
	setTab(v);
}
////////////
function viewEnt(tdDiv,pid)
{
	tdDiv.innerHTML = '<a href="#" onclick="goToEntOverall(\''+pid+'\')" >' + tdDiv.innerHTML +'</a>';
	
}
function viewEntClass(tdDiv,pid)
{
	var c="A";
	if(tdDiv.innerHTML=="1")c="A";
	if(tdDiv.innerHTML=="2")c="B";
	if(tdDiv.innerHTML=="3")c="C";
	tdDiv.innerHTML = c;
	
}

function goToEntOverall(sid)
{
	var q='?st='+jQuery("#startTime").val()+"&et="+jQuery("#endTime").val()+"&dateType="+gtype.selectedType+"&plat="+jQuery("#plat").val()+"&entID="+sid;
	var url='<s:url value="/ub/custom/entoverall.shtml"/>';
	var url_q=url+q;
	window.open(url_q);

}
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
   	iniOverallIndicator();
   	iniPlat();
	
   	iniOverAllList();
   	iniEntList();
   	iniSrvList();
   	
   	iniTab();
   	iniLoad();
   	
   	HelpTip.initTip();
});
function iniLoad()
{
	searchChartData_overall();
	searchList_overall();
	gatherAll();
}
function iniOverallIndicator()
{
	//初始化indicator
	var g=[
		 {title:"原始访问次数",id:"c0",value:"vocnt",selected:true}
		,{title:"有效访问次数",id:"c1",value:"vcnt",selected:false}
	   	,{title:"上午访问次数",id:"c2",value:"vamcnt",selected:false}
	   	,{title:"下午访问次数",id:"c3",value:"vpmcnt",selected:false}
	   	,{title:"晚上访问次数",id:"c4",value:"vnicnt",selected:false}
	   	,{title:"追加访问次数",id:"c5",value:"vaddcnt",selected:false}
	   	,{title:"开通企业客户数",id:"c6",value:"openentcnt",selected:false}
	   	,{title:"活跃企业客户数",id:"c7",value:"acentcnt",selected:false}
	   	,{title:"活跃度",id:"c8",value:"activity",selected:false}
	   	];
	
   	indicator1=new Indicator({onChange:onIndicatorChange,containerId:"indicator_container",maxSel:2,selGroup:g});
   	indicator1.render();
}
function iniOverAllList()
{
	overalllist = jQuery('#vlist_overall');
	overalllist.flexigrid({
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},
		         {name: 'dateType', value: gtype.selectedType},
				{name: 'plat', value: jQuery('#plat').val()},
		         {name: 'customerType', value: jQuery('#entTypeSelect').val()},		         
		         {name: 'ac', value: jQuery('#activitySelect').val()},
		         {name: 'area', value: jQuery('#zoneSelect').val()},
		         {name: 'province', value: jQuery('#provinceSelect').val()},
		         {name: 'city', value: jQuery('#citySelect').val()}
		         ],
		url: '<s:url value="/ub/custom/json/getGrid.shtml" />',
		dataType: 'json',
		height: 'auto',
		width: 'auto',
		
		colModel : [
					{display: '时间', name : 'opententcnt', width : '200', sortable : false, align: 'left'},
					{display: '访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '上午访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '下午访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '晚上访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '追加访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '开通企业数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '活跃企业数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '企业活跃度', name : 'opententcnt', width : '100', sortable : false, align: 'left',process:namePercent}
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
}
function iniEntList()
{
	entlist = jQuery('#vlist_ent');
	entlist.flexigrid({
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},
		         {name: 'dateType', value: gtype.selectedType},
				{name: 'plat', value: jQuery('#plat').val()},
		         {name: 'customerType', value: jQuery('#entTypeSelect').val()},		         
		         {name: 'ac', value: jQuery('#activitySelect').val()},
		         {name: 'area', value: jQuery('#zoneSelect').val()},
		         {name: 'province', value: jQuery('#provinceSelect').val()},
		         {name: 'city', value: jQuery('#citySelect').val()}
		         ],
		url: '<s:url value="/ub/custom/json/getEveryEntInfo.shtml" />',
		dataType: 'json',
		height: 'auto',
		width: 'auto',
		colModel : [
					{display: '企业名称', name : 'opententcnt', width : '200', sortable : false, align: 'left',process:viewEnt},
					{display: '企业编码', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '企业类别', name : 'opententcnt', width : '100', sortable : false, align: 'left',process:viewEntClass},
					{display: '开通时间', name : 'opententcnt', width : '200', sortable : false, align: 'left'},
					{display: '是否活跃', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '上午访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '下午访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '晚上访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
					{display: '追加访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'}					
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
}
function iniSrvList()
{
	srvlist = jQuery('#vlist_srv');
	srvlist.flexigrid({
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},
		         {name: 'dateType', value: gtype.selectedType},
				 {name: 'plat', value: jQuery('#plat').val()},
		         {name: 'customerType', value: jQuery('#entTypeSelect').val()},		         
		         {name: 'ac', value: jQuery('#activitySelect').val()},
		         {name: 'area', value: jQuery('#zoneSelect').val()},
		         {name: 'province', value: jQuery('#provinceSelect').val()},
		         {name: 'city', value: jQuery('#citySelect').val()}
		         ],
		url: '<s:url value="/ub/custom/json/getServiceVisGrid.shtml" />',
		dataType: 'json',
		height: 'auto',
		width: 'auto',
		colModel : [
					
		    		{display: '服务名称', name : 'time', width : '300', sortable : true, align: 'left'},
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
}

function searchList_overall() {
	//jQuery('#empDiv').css("display","none");
	overalllist.flexOptions({
		newp: 1,
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},
		         {name: 'dateType', value: gtype.selectedType},
				{name: 'plat', value: jQuery('#plat').val()},
		         {name: 'customerType', value: jQuery('#entTypeSelect').val()},		         
		         {name: 'ac', value: jQuery('#activitySelect').val()},
		         {name: 'area', value: jQuery('#zoneSelect').val()},
		         {name: 'province', value: jQuery('#provinceSelect').val()},
		         {name: 'city', value: jQuery('#citySelect').val()}
		         ]
	});
	overalllist.flexReload();
}
function searchList_ent() {
	//jQuery('#empDiv').css("display","none");
	entlist.flexOptions({
		newp: 1,
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},
		         {name: 'dateType', value: gtype.selectedType},
				{name: 'plat', value: jQuery('#plat').val()},
		         {name: 'customerType', value: jQuery('#entTypeSelect').val()},		         
		         {name: 'ac', value: jQuery('#activitySelect').val()},
		         {name: 'area', value: jQuery('#zoneSelect').val()},
		         {name: 'province', value: jQuery('#provinceSelect').val()},
		         {name: 'city', value: jQuery('#citySelect').val()}
		         ]
	});
	entlist.flexReload();
}
function searchList_srv() {
	//jQuery('#empDiv').css("display","none");
	srvlist.flexOptions({
		newp: 1,
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},
		         {name: 'dateType', value: gtype.selectedType},
				{name: 'plat', value: jQuery('#plat').val()},
		         {name: 'customerType', value: jQuery('#entTypeSelect').val()},		         
		         {name: 'ac', value: jQuery('#activitySelect').val()},
		         {name: 'area', value: jQuery('#zoneSelect').val()},
		         {name: 'province', value: jQuery('#provinceSelect').val()},
		         {name: 'city', value: jQuery('#citySelect').val()}
		         ]
	});
	srvlist.flexReload();
}
//获取页面高度
function get_page_height() {
	var height = 0;
	
	if (jQuery.browser.msie) {
	    height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
	} else {
	    height = self.innerHeight;
	}
	return height;
}
//获取页面宽度
function get_page_width() {
	var width = 0;
	if(jQuery.browser.msie){ 
		width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
	} else { 
		width = self.innerWidth; 
	} 
	return width;
}
//计算控件宽高
function changeWidthHeight(){
	var h = get_page_height();
	var w = get_page_width();
	//jQuery(".flexigrid").width(w-60);
	//jQuery(".bDiv").height(h-550); 
}
//页面自适应
(function (jQuery) {
    jQuery(window).resize(function(){
    changeWidthHeight();
    //jQuery('#vlist').fixHeight();
});
jQuery(window).load(function (){
	changeWidthHeight();
	//jQuery('#vlist').fixHeight();
});
})(jQuery);


/////////////////////////////////
function zoneSelClick()
{
	var z=jQuery("#zoneSelect");
	var p=jQuery("#provinceSelect");
	var c=jQuery("#citySelect");
	p.val("");
	c.val("");
	if(z.val()=="")
	{
		query();
		return;
	}
	p.empty();
	p.append("<option value=''>全部</option>");
	var url='<s:url value="/ub/custom/json/getProvinceByArea.shtml" />'
		+'?area='+z.val();
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			if(data!=null &&data.provinces!=null)
			{
				for(var i=0;i<data.provinces.length;i++)
				{
					p.append("<option value='"+data.provinces[i].code+"'>"+data.provinces[i].name+"</option>");
				}
				query();
			}
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			
		}
	});
}
function provinceSelClick()
{
	var c=jQuery("#citySelect");
	var p=jQuery("#provinceSelect");
	
	c.val("");
	if(p.val()=="")
	{
		query();
		return;
	}
	c.empty();
	c.append("<option value=''>全部</option>");
	var url='<s:url value="/ub/custom/json/getCityByProvince.shtml" />'
		+'?province='+p.val();
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			if(data!=null &&data.citys!=null)
			{
				for(var i=0;i<data.citys.length;i++)
				{
					c.append("<option value='"+data.citys[i].code+"'>"+data.citys[i].name+"</option>");
				}
				query();
			}
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			
		}
	});
}
function citySelClick()
{
	query();
}
function entTypeSelClick()
{
	query();
}
function acSelClick()
{
	query();
}
function searchent()
{
	
	var searchText=jQuery("#searchText").val();
	if(searchText=='请输入企业客户名称或企业编码')searchText='';
	
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	var plat=jQuery("#plat");
	
	var dateType=gtype.selectedType;
	var z=jQuery("#zoneSelect");
	var p=jQuery("#provinceSelect");
	var c=jQuery("#citySelect");
	var ctype=jQuery("#entTypeSelect");
	var ac=jQuery("#activitySelect");
	
	var url='<s:url value="/ub/custom/gotoSearch.shtml" />'
			+'?searchText='+searchText+'&st='+startDate+'&et='+endDate+'&dateType='+dateType
					+"&plat="+plat.val()+'&area='+z.val()+'&province='+p.val()+'&city='+c.val()
					+"&customerType="+ctype.val()+"&ac="+ac.val();
	location=url;
	//window.open(url);
}
function query()
{
	gatherAll(); 
	switch(currentTab)
	{
		case "overall":
			
			searchChartData_overall();
			searchList_overall();
			break;
		case "ent":
			searchList_ent();
			break;
		case "srv":
			searchChartData_srv();
			searchList_srv();
			break;
	}
	
	
}
function gatherAll()
{
	clearGather();
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	var plat=jQuery("#plat");
	
	var dateType=gtype.selectedType;
	var z=jQuery("#zoneSelect");
	var p=jQuery("#provinceSelect");
	var c=jQuery("#citySelect");
	var ctype=jQuery("#entTypeSelect");
	var ac=jQuery("#activitySelect");
	
	var url='<s:url value="/ub/custom/json/gatherAll.shtml" />'
			+'?startDay='+startDate+'&endDay='+endDate+'&dateType='+dateType
					+"&plat="+plat.val()+'&area='+z.val()+'&province='+p.val()+'&city='+c.val()
					+"&customerType="+ctype.val()+"&ac="+ac.val();
	
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
	var sum_viheclecnt=jQuery("#sum_viheclecnt");
	var sum_openentcnt=jQuery("#sum_openentcnt");
	var sum_liveentcnt=jQuery("#sum_liveentcnt");
	var sum_activity=jQuery("#sum_activity");
	var sum_totalcnt=jQuery("#sum_totalcnt");
	var sum_dayavgcnt=jQuery("#sum_dayavgcnt");
	
	sum_viheclecnt.html(data.gather.busCnt);
	sum_openentcnt.html(data.gather.openEntCnt);
	sum_liveentcnt.html(data.gather.emEntCnt);
	
	sum_activity.html(data.gather.ep+"%");
	sum_totalcnt.html(data.gather.vis);
	sum_dayavgcnt.html(data.gather.dayVis);
	
	
}
function clearGather()
{
	var sum_viheclecnt=jQuery("#sum_viheclecnt");
	var sum_openentcnt=jQuery("#sum_openentcnt");
	var sum_liveentcnt=jQuery("#sum_liveentcnt");
	var sum_activity=jQuery("#sum_activity");
	var sum_totalcnt=jQuery("#sum_totalcnt");
	var sum_dayavgcnt=jQuery("#sum_dayavgcnt");
	
	sum_viheclecnt.html("-");
	sum_openentcnt.html("-");
	sum_liveentcnt.html("-");
	
	sum_activity.html("-");
	sum_totalcnt.html("-");
	sum_dayavgcnt.html("-");
	
}
</script>