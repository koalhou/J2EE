<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/poshytip/tip-yellow/tip-yellow.css'/>">
<title><s:property
	value="getText('menu.ub.passiveliveness')" /></title>
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
	<script type="text/javascript" 
	src="<s:url value='/scripts/indicator/GlareTpl.js' />"></script>
	<script type="text/javascript" 
	src="<s:url value='/scripts/grouptype/GroupType.js' />"></script>
	<script type="text/javascript"
	src="<s:url value='/scripts/poshytip/jquery.poshytip.js' />"></script>	
<script type="text/javascript"
	src="<s:url value='/scripts/common/HelpTip.js' />"></script>
<script type="text/javascript">
var gtype=null;
var indicators=["vcnt","ventcnt"];
var chartData=null;
var questionID=0;
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
	loger(data);
	searchChartData();
	searchList();
}
function onIndicatorChange(data)
{	
	loger(data.join(","));
	loadChart(chartData);
	
}
function getGather()
{
	clearGather();
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	
	var serviceId=jQuery("#selService").val();
	var dateType=gtype.selectedType;
	
	
	var url='<s:url value="/ub/passivity/json/gather.shtml" />'
			+'?startDay='+startDate+'&endDay='+endDate+'&dateType='+dateType+"&questionID="+questionID;
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
	var pushentvcnt=jQuery("#sum_pushentcnt");
	var ventcnt=jQuery("#sum_ventcnt");
	var vcnt=jQuery("#sum_vcnt");
	var vrate=jQuery("#sum_vrate");
	
	pushentvcnt.html(data.gather.openEp);
	ventcnt.html(data.gather.visEp);
	vcnt.html(data.gather.vis);
	vrate.html(data.gather.visPercent+"%");
	
}
function clearGather()
{
	var pushentvcnt=jQuery("#sum_pushentcnt");
	var ventcnt=jQuery("#sum_ventcnt");
	var vcnt=jQuery("#sum_vcnt");
	var vrate=jQuery("#sum_vrate");
	
	pushentvcnt.html("-");
	ventcnt.html("-");
	vcnt.html("-");
	vrate.html("-");
	
}
function searchChartData()
{
	setChartLoading("n_chart");

	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	
	var serviceId=jQuery("#selService").val();
	var dateType=gtype.selectedType;
	
	
	var url='<s:url value="/ub/passivity/json/lines.shtml" />'
			+'?startDay='+startDate+'&endDay='+endDate+'&dateType='+dateType+"&questionID="+questionID;
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
	 
	if(indicators.length>1)
	{
		var strbarms=[];var categoryarray=[],vcntarray=[],ventarray=[];
		strbarms.push("<chart caption='' xAxisName='' yAxisName='' >");
		categoryarray.push(" <categories>");
		
		vcntarray.push("<dataset seriesName='访问次数'>");
		ventarray.push("<dataset seriesName='访问企业数'>");
		
		for(var i=0;i<data.lines.length;i++)
		{
			var item=data.lines[i];
			
			var s= "   <category  label='"+item.reportDate+"' />";
			categoryarray.push(s);
			
			vcntarray.push("<set value='"+item.vis+"'  tooltext='"+item.reportDate+"{br}访问次数:"+item.vis+" 占比："+item.visCntPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			ventarray.push("<set value='"+item.visEp+"'  tooltext='"+item.reportDate+"{br}访问企业数:"+item.visEp+" 占比："+item.visEpPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			
		}
		categoryarray.push("</categories>");
		vcntarray.push("</dataset>");
		ventarray.push("</dataset>");
		
		
		strbarms.push(categoryarray.join(""));
		
		
		var check=jQuery.inArray("vcnt",indicators);		
		if(check!=-1)strbarms.push(vcntarray.join(""));
		
		check=jQuery.inArray("ventcnt",indicators);
		if(check!=-1)strbarms.push(ventarray.join(""));
		
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
		var categoryarray=[],vcntarray=[],ventcntarray=[];
		sbar.push("<chart caption=''  xAxisName=''  yAxisName='' >");
				
		var indct=indicators[0];
		
		for(var i=0; i< data.lines.length;i++)
		{
			var item=data.lines[i];
			vcntarray.push("<set label='"+item.reportDate+"' value='"+item.vis+"' tooltext='"+item.reportDate+";访问次数："+item.vis+";占比："+item.visCntPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+"/>");
			ventcntarray.push("<set label='"+item.reportDate+"' value='"+item.visEp+"' tooltext='"+item.reportDate+";访问企业数："+item.visEp+";占比："+item.visEpPercent.replace("%","%25")+"' "+getHolidayTip(item.isHoliday)+" />");
			
		}
		switch(indct)
		{
		case "vcnt":
			sbar.push(vcntarray.join(""));
			break;
		case "centcnt":
		default:
			sbar.push(ventcntarray.join(""));
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
	var q='?st='+jQuery("#startTime").val()+"&et="+jQuery("#endTime").val()+"&datetype="+gtype.selectedType+"&pushID="+questionID;	
	var url_q=url+q;
	location=url_q;
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
	loadChart(chartData);
}
////////////
	
	//flexgrid
	function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}



function updateIndicators()
{
	var vcnt=jQuery("#check_vcnt");
	var ventcnt=jQuery("#check_ventcnt");
	
	indicators=[];
	if(vcnt.attr("checked"))indicators.push(vcnt.attr("value"));
	if(ventcnt.attr("checked"))indicators.push(ventcnt.attr("value"));
	
}
function iniHelpTip()
{
	if(questionID!=0)
	{
		jQuery("#lbl_pushent").attr("_ubtip","pas_sin_pushent");
		jQuery("#lbl_vent").attr("_ubtip","pas_sin_vent");
		jQuery("#lbl_vcnt").attr("_ubtip","pas_sin_vcnt");
		jQuery("#lbl_vrate").attr("_ubtip","pas_sin_vrate");
		
		
	}	
}
function iniQuestion()
{
	var qui=jQuery("#questionTitle");
	qui.hide();
	questionID=getQueryStringByName("pushID");
	if(typeof(questionID)=="undefined")questionID=0;
	if(questionID!=0)
	{
		var url='<s:url value="/ub/passivity/json/getQuestion.shtml" />'
			+'?questionID='+questionID;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			if(data==null||data.question==null)
			{
				qui.html(questionID);
				qui.show();
				return;
			}
			qui.html(data.question.name);
			
			qui.show();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			qui.html(questionID);
			qui.show();
		}
		});	
	}
	
}
jQuery(function() {
	iniQuestion();
	

	iniTimeSpan();	
	updateIndicators();
   	var q_gtype=getQueryStringByName("datetype");
   	if(typeof(q_gtype)=="undefined")q_gtype="day";
	gtype=new GroupType({containerId:"timespancon1",startTime:jQuery("#startTime").val(),endTime:jQuery("#endTime").val(),selectedType:q_gtype,onChange:onGTypeChange});
   	gtype.render();
   	
   	searchChartData();
	
   	getGather();
   	
	var roleList = jQuery('#vlist');
	roleList.flexigrid({
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},
		         {name: 'questionID', value: questionID},
				{name: 'dateType', value: gtype.selectedType}],
		url: '<s:url value="/ub/passivity/json/grid.shtml" />',
		dataType: 'json',
		height: 'auto',
		width: 'auto',
		colModel : [
		    		{display: '<s:text name="ub.passivity.time" />', name : 'rowNumber', width : '200', sortable : false, align: 'left'},
		    		{display: '<s:text name="ub.system.ventcnt" />', name : 'role_name', width : '200', sortable : true, align: 'left'},
		    		{display: '<s:text name="ub.system.vcnt" />', name : 'full_name', width : '200', sortable : true, align: 'left'}
		    		],
		    	sortname: 'apply_id',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp:20,	
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	});
	iniHelpTip();
	HelpTip.initTip();
	
});
function searchList() {
	jQuery('#empDiv').css("display","none");
	jQuery('#vlist').flexOptions({
		newp: 1,
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},
		         {name: 'questionID', value: questionID},
				{name: 'dateType', value: gtype.selectedType}]
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
									<li><a href="#" onclick="tabClick('<s:url value='/ub/passivity/pushlist.shtml'/>')"> <s:property
										value="getText('ub.passivity.everyv')" /> </a></li>
										
								</ul>
							<div class="ubtabcontent" >
								<div style="margin:5px">
								<!-- tab内容 -->
									
							<div style="margin:15px 15px 10px 15px;">
								<span id="questionTitle" class="questiontitle"></span>
							</div>
						
							<div style="margin:20px 15px 10px 15px;">
								<table class="summary" width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
									<span id="lbl_pushent" class="text" _ubtip="pas_pushent"><s:property
							value="getText('ub.passivity.pushentcnt')" /></span><br/>
									<span id="sum_pushentcnt" class="value">-</span>
									</td>
									<td>
									<span id="lbl_vent" class="text" _ubtip="pas_vent"><s:property
							value="getText('ub.system.ventcnt')" /></span><br/>
									<span id="sum_ventcnt" class="value">-</span>
									</td>
									<td>
									<span id="lbl_vcnt" class="text" _ubtip="pas_vcnt"><s:property
							value="getText('ub.system.vcnt')" /></span><br/>
									<span id="sum_vcnt" class="value">-</span>
									</td>
									<td>
									<span id="lbl_vrate" class="text" _ubtip="pas_vrate"><s:property
							value="getText('ub.passivity.vrate')" /></span><br/>
									<span id="sum_vrate" class="value">-</span>
									</td>
								</tr>
								</table> 
							</div>
							
							<div id="n_indicator" style="height:30px;width:100%;margin:10px;padding:0;">
								<div class="chartlegent"><span>节假日</span></div>
								<s:property
								value="getText('ub.system.indicator')" />:
															
								<span class="n_indicator_cbx">  
								
								<label for="check_vcnt"  style="margin-left:3px;vertical-align:middle;" >
									<input type="checkbox" id="check_vcnt" onclick="indicatorClick(this)" checked="checked" value="vcnt" style="vertical-align:middle;" /><s:property
								value="getText('ub.system.vcnt')" /></label>   
								</span>  
								<span class="n_indicator_cbx">  
								<label for="check_ventcnt"  style="margin-left:3px;vertical-align:middle;" >
									<input type="checkbox" id="check_ventcnt" onclick="indicatorClick(this)" value="ventcnt"  style="vertical-align:middle;" />
								<s:property
								value="getText('ub.system.ventcnt')" /></label>   
								</span> 
								
							</div>
						
						<div style="text-align:center;width:100%;">
							<div id="n_chart"></div>
						</div>
						<div style="margin:5px;border:0px solid #cccccc;">
							<div style="height:30px;background:#eeeeee;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:1px solid #cccccc;margin-bottom:0px;line-height:30px;font-weight:bold;padding-left:10px;"><s:property
							value="getText('ub.passivity.vrecordlist')" /></div>		
							<div style="margin:0px;">				
						  	<table id="vlist" width="100%"  cellspacing="0">
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
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:400px;width:500px;color:rgb(234,118,79);line-height:400px;border:1px solid #ccc;">图表无数据！</div>
</script>
<script type="text/html" id="tmp_chart_loading">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:400px;width:500px;color:rgb(72,190,244);line-height:400px;border:1px solid #ccc;">正在加载图表数据...</div>
</script>

<%@include file="../../common/copyright.jsp"%>
</body>
</html>