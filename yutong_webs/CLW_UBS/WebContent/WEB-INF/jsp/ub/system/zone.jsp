<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
<title><s:property
	value="getText('menu.ub.enviroment')" /></title>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	
	<script type="text/javascript"
	src="<s:url value='/scripts/common/moment.min.js' />"></script>
	



</head>
<body>
<%@include file="../../common/menu.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/fusioncharts/FusionCharts.js' />"></script>
<script type="text/javascript">
var chartData_zone=null;
var chartData_p=null;
var indicators=["vcnt","ventcnt","vactcnt"];
function query() {

	Wit.commitAll($('logincntstat_form'));

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
	searchChartData();
	
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
	searchChartData();
	
}
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
function iniIndicators()
{
	var idt=getQueryStringByName("idt");
	if(idt)
	{
		var idtarray=idt.split(",");
		
		var vcnt=jQuery("#check_vcnt");
		var ventcnt=jQuery("#check_ventcnt");
		var vacountcnt=jQuery("#check_vacountcnt");
		
		vacountcnt.attr("checked",jQuery.inArray("vactcnt",idtarray)!=-1);
		ventcnt.attr("checked",jQuery.inArray("ventcnt",idtarray)!=-1);		
		vcnt.attr("checked",jQuery.inArray("vcnt",idtarray)!=-1);
	}
}
function updateIndicators()
{
	var vcnt=jQuery("#check_vcnt");
	var ventcnt=jQuery("#check_ventcnt");
	var vacountcnt=jQuery("#check_vacountcnt");
	indicators=[];
	if(vcnt.attr("checked"))indicators.push(vcnt.attr("value"));
	if(ventcnt.attr("checked"))indicators.push(ventcnt.attr("value"));
	if(vacountcnt.attr("checked"))indicators.push(vacountcnt.attr("value"));
}
function startTimeClick()
{
	clearQSwitch();
	jQuery("#selMonth").attr("value","");
}
function endTimeClick()
{
	clearQSwitch();
	jQuery("#selMonth").attr("value","");
	searchChartData();
	
}
function searchChartData()
{
	setChartLoading("n_chartz");
	
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	var url='<s:url value="/ubs/zoneAreaList.shtml" />'+'?searchparam.startDate='+startDate+'&searchparam.endDate='+endDate;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			chartData_zone=data;
			loadChart_zone(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			setChartNoData_zone("n_chartz");
		}
	});
	setChartLoading("n_chartp");
	url='<s:url value="/ubs/zoneProvinceList.shtml" />'+'?searchparam.startDate='+startDate+'&searchparam.endDate='+endDate;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			chartData_p=data;
			loadChart_p(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			setChartNoData_p("n_chartp");
		}
	});
	
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
function loadChart_zone(data)
{
	if(data==null)
	{
		loger("loadChart:data is null");
		setChartNoData("n_chartz");
		return;		
	}
	if(data.rows==null)
	{
		loger("loadChart:data.rows is null");
		setChartNoData("n_chartz");
		return;	
		
	}
	 
	if(indicators.length>=1)
	{
		var sbar=[];
		sbar.push("<chart caption='大区'  xAxisName='大区' rotateYAxisName='0' yAxisName='次数' >");
		var offset=0;
		
		var indct=indicators[0];
		if(indct=="vcnt")
		{
			offset=1;
		}
		else if(indct=="ventcnt")
		{
			offset=2;
		}
		else
		{
			offset=3;
		}
		for(var i=0; i< data.rows.length;i++)
		{
			var item=data.rows[i].cell;
			var str="<set label='"+item[0]+"' value='"+item[offset]+"' />";
			sbar.push(str);
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
		var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/Pie2D.swf' />","myChartId4", "500","400","0");
		myChartbarms.setDataXML(charXml);
		myChartbarms.render("n_chartz");
	}
	else
	{
		loger("未选择指标");
		alert("未选择指标");
	}
}
function loadChart_p(data)
{
	if(data==null)
	{
		loger("loadChart:data is null");
		setChartNoData("n_chartp");
		return;		
	}
	if(data.rows==null)
	{
		loger("loadChart:data.rows is null");
		setChartNoData("n_chartp");
		return;	
		
	}	 
	if(indicators.length>=1)
	{
		
		var sbar=[];
		sbar.push("<chart caption='省份'  xAxisName='省份' rotateYAxisName='0' yAxisName='次数' >");
		var offset=0;
		
		var indct=indicators[0];
		var datas=sortData(data,indct);
		
		for(var i=0; i< datas.length;i++)
		{
			var item=datas[i];
			var str="<set label='"+item.name+"' value='"+item.value+"'/>";
			sbar.push(str);
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
		var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/Pie2D.swf' />","myChartId4", "500","400","0");
		myChartbarms.setDataXML(charXml);
		myChartbarms.render("n_chartp");
	}
	else
	{
		loger("未选择指标");
		alert("未选择指标");
	}
}
//处理省份数据，前9个省份单独列出，其余的省份汇总为 其他。不满10条记录，则全部列出
function sortData(data,indct)
{
	//[{name:p,value:1}]
	var slicecount=10;
	var datas=[];
	var offset=1;
	if(indct=="vcnt")
	{
		offset=1;
	}
	else if(indct=="ventcnt")
	{
		offset=2;
	}
	else
	{
		offset=3;
	}
	for(var i=0;i<data.rows.length;i++)
	{
		datas.push({name:data.rows[i].cell[0],value:data.rows[i].cell[offset]});
	}
	datas = datas.sort(function(a,b){
		return b.value-a.value;
		});
		
	if(data.rows.length<=slicecount)
	{
		return datas;
	}
	else
	{
		var v=0;
		for(var j=slicecount-1;j<datas.length;j++)
		{
			v+=datas[j].value;
		}
		var d=datas.slice(0,slicecount-1);//该方法不包括第9个索引的元素。
		d.push({name:"其他",value:v});
		return d;
	}
	

}
//a 是一个小数字符串
function getPercentVal(a)
{
	try
	{
		var o=parseFloat(a);
		 b=o.toFixed(4);
		 c=b.slice(2,4)+"."+b.slice(4,6)+"%25";//百分号符号 在FusionCharts里显示“%”,你需要用“%25”来替换它
		return c;
	}
	catch(e)
	{
		loger(e);
		return "error";
	}
}

function indicatorClick(chkbox)
{
	if(!chkbox.checked)
	{
		if(indicators.length<=1)
		{
			chkbox.checked=true;//必须有一个指标			
			return;
		}
	}
	
	updateIndicators();
	loadChart_zone(chartData_zone);
	loadChart_p(chartData_p);
}

jQuery(function() {
	iniTimeSpan();
	iniIndicators();
	updateIndicators();
	
	searchChartData();

});

function tabClick(url)
{
	var q='?st='+jQuery("#startTime").val()+"&et="+jQuery("#endTime").val();
	var idt=indicators.join(",");
	q+="&idt="+idt;
	//"<s:url value='/ub/system/xxx.shtml'/>"
	var url_q=url+q;
	location=url_q;
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
							

						</div>

						</td>
					</tr>
				
				<tr>
					<td valign="top" style="backgrond:white;"><!-- body -->
					<div style="margin:10px;min-height:600px;background-color:white;">
						<div class="ubtab">
							<ul>
							<li><a href="#" onclick="tabClick('<s:url value='/ub/system/ubsolution.shtml'/>')"> <s:property
								value="getText('ub.system.solution')" /> </a></li>
							<li><a href="#" onclick="tabClick('<s:url value='/ub/system/ubos.shtml'/>')"> <s:property
								value="getText('ub.system.os')" /> </a></li>
								<li><a href="#" onclick="tabClick('<s:url value='/ub/system/ubbrowser.shtml'/>')"> <s:property
								value="getText('ub.system.browser')" /> </a></li>
								<li><a href="#" onclick="tabClick('<s:url value='/ub/system/ubflash.shtml'/>')"> <s:property
								value="getText('ub.system.flash')" /> </a></li>
								<li class="ubtabselect"><a href="#" > <s:property
								value="getText('ub.system.zone')" /> </a></li>
						</ul>
							<div class="ubtabcontent" >
								<div style="margin:5px">
								<!-- tab内容 -->
									
							<div id="n_indicator" style="height:30px;width:100%;margin:10px 10px 10px 10px;">
							<s:property
							value="getText('ub.system.indicator')" />:
														
							<span class="n_indicator_cbx">  
							
							<label for="check_vcnt"  style="margin-left:3px;vertical-align:middle;" ><input name="indicator_radio" type="radio" id="check_vcnt" style="vertical-align:middle;" onclick="indicatorClick(this)" checked="checked" value="vcnt"/><s:property
							value="getText('ub.system.vcnt')" /></label>   
							</span>  
							<span class="n_indicator_cbx">  
							
							<label for="check_ventcnt"  style="margin-left:3px;vertical-align:middle;" ><input name="indicator_radio" type="radio" id="check_ventcnt" style="vertical-align:middle;" onclick="indicatorClick(this)"  value="ventcnt"/><s:property
							value="getText('ub.system.ventcnt')" /></label>   
							</span> 
							<span class="n_indicator_cbx">  
							
							<label for="check_vacountcnt"  style="margin-left:3px;vertical-align:middle;" ><input name="indicator_radio" type="radio" id="check_vacountcnt" style="vertical-align:middle;" onclick="indicatorClick(this)"  value="vactcnt"/><s:property
							value="getText('ub.system.vamaountcnt')" /></label>   
							</span> 
							</div>
						
						<div style="text-align:center;width:100%;">
							<table  width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="padding:5px;"><div id="n_chartz"></div></td>
									<td style="padding:5px;"><div id="n_chartp"></div></td>
								</tr>
							</table>
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
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:400px;width:500px;color:rgb(234,118,79);line-height:400px;border:1px solid #ccc;">图表无数据！</div>
</script>
<script type="text/html" id="tmp_chart_loading">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:400px;width:500px;color:rgb(72,190,244);line-height:400px;border:1px solid #ccc;">正在加载图表数据...</div>
</script>
<%@include file="../../common/copyright.jsp"%>
</body>
</html>