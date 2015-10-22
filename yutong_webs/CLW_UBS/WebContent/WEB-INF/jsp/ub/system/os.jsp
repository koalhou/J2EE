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
var chartData=null;
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
	searchList();
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
	searchList();
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
		vcnt.attr("checked",jQuery.inArray("vcnt",idtarray)!=-1);
		ventcnt.attr("checked",jQuery.inArray("ventcnt",idtarray)!=-1);
		vacountcnt.attr("checked",jQuery.inArray("vactcnt",idtarray)!=-1);
		
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
	searchList();
}
function searchChartData()
{
	setChartLoading();
	
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	var url='<s:url value="/ubs/osList.shtml" />'+'?searchparam.startDate='+startDate+'&searchparam.endDate='+endDate;
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
			setChartNoData();
		}
	});

	
}
function setChartNoData()
{
	//var tmp_loading=jQuery("#tmp_chart_loading").html();
	var tmp_nodata=jQuery("#tmp_chart_nodata").html();
	var chardiv=jQuery("#n_chart");	
	chardiv.html(tmp_nodata);
}
function setChartLoading()
{
	var tmp_loading=jQuery("#tmp_chart_loading").html();
	//var tmp_nodata=jQuery("#tmp_chart_nodata").html();
	var chardiv=jQuery("#n_chart");
	chardiv.html(tmp_loading);
}
function loadChart(data)
{
	if(data==null)
	{
		loger("loadChart:data is null");
		setChartNoData();
		return;		
	}
	if(data.rows==null)
	{
		loger("loadChart:data.rows is null");
		setChartNoData();
		return;	
		
	}
	 
	if(indicators.length>1)
	{
		var strbarms=[];var categoryarray=[],vcntarray=[],ventarray=[],vactarray=[];
		strbarms.push("<chart caption='' xAxisName='操作系统' yAxisName='次数' >");
		categoryarray.push(" <categories>");
		
		vcntarray.push("<dataset seriesName='访问次数'>");
		ventarray.push("<dataset seriesName='访问企业次数'>");
		vactarray.push("<dataset seriesName='访问账号次数'>");
		for(var i=0;i<data.rows.length;i++)
		{
			var item=data.rows[i].cell;
			
			var s= "   <category  label='"+item[0]+"' />";
			categoryarray.push(s);
			
			vcntarray.push("<set value='"+item[1]+"'  tooltext='访问次数:"+item[1]+"{br}总访问次数: "+item[2]+" 占比："+getPercentVal(item[3])+"'/>");
			ventarray.push("<set value='"+item[4]+"'  tooltext='访问企业次数:"+item[4]+"{br}总访问次数: "+item[5]+" 占比："+getPercentVal(item[6])+"'/>");
			vactarray.push("<set value='"+item[7]+"'  tooltext='访问账号次数:"+item[7]+"{br}总访问次数: "+item[8]+" 占比："+getPercentVal(item[9])+"'/>");
		}
		categoryarray.push("</categories>");
		vcntarray.push("</dataset>");
		ventarray.push("</dataset>");
		vactarray.push("</dataset>");
		
		strbarms.push(categoryarray.join(""));
		
		
		var check=jQuery.inArray("vcnt",indicators);		
		if(check!=-1)strbarms.push(vcntarray.join(""));
		
		check=jQuery.inArray("ventcnt",indicators);
		if(check!=-1)strbarms.push(ventarray.join(""));
		
		check=jQuery.inArray("vactcnt",indicators);
		if(check!=-1)strbarms.push(vactarray.join(""));
		
		
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
		var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/MSColumn2D.swf' />","myChartId4", "1000","300","0");
		
		myChartbarms.setDataXML(xml);
		myChartbarms.render("n_chart");
		
	}
	else if(indicators.length=1)
	{
		var sbar=[];
		sbar.push("<chart caption='操作系统'  xAxisName='操作系统' rotateYAxisName='0' yAxisName='次数' >");
		var offset=0;
		var indcttext="";
		var indct=indicators[0];
		if(indct=="vcnt")
		{
			offset=0;indcttext="访问次数";
		}
		else if(indct)
		{
			offset=3;indcttext="访问企业次数";
		}
		else
		{
			offset=6;indcttext="访问账号次数";
		}
		for(var i=0; i< data.rows.length;i++)
		{
			var item=data.rows[i].cell;
			var str="<set label='"+item[0+offset]+"' value='"+item[1+offset]+"' tooltext='"+indcttext+"："+item[1+offset]+";总访问次数: "+item[2+offset]+";占比："+getPercentVal(item[3+offset])+"' />";
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
		var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/Column2D.swf' />","myChartId4", "1000","300","0");
		myChartbarms.setDataXML(charXml);
		myChartbarms.render("n_chart");
	}
	else
	{
		loger("未选择指标");
		alert("未选择指标");
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
	loadChart(chartData);
}
	
//flexgrid
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

function setDefaultName(tdDiv,pid){
	if(tdDiv.innerHTML == "&nbsp;"){
		tdDiv.innerHTML = "0";
	}
	
}


jQuery(function() {
	iniTimeSpan();
	iniIndicators();
	updateIndicators();
	
	searchChartData();
	
	var roleList = jQuery('#vlist');
	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");
	roleList.flexigrid({
		params: [{name: 'searchparam.startDate', value: jQuery('#startTime').val() }, 
		         {name: 'searchparam.endDate', value: jQuery('#endTime').val()}],
		//url: '<s:url value="/ubs/solutionPageList.shtml" />'+'?searchparam.startDate='+startDate+'&searchparam.endDate='+endDate,
		url: '<s:url value="/ubs/osPageList.shtml" />',
		dataType: 'json',
		height: 'auto',
		width: 'auto',
		colModel : [
		    		{display: '<s:text name="ub.system.os" />', name : 'systemname', width : '200', sortable : true, align: 'left'},
		    		{display: '<s:text name="ub.system.vcnt" />', name : 'visitCount', width : '100', sortable : true, align: 'left', process: setDefaultName},
		    		{display: '<s:text name="ub.system.ventcnt" />', name : 'visitEpCount', width : '100', sortable : true, align: 'left', process: setDefaultName},
		    		{display: '<s:text name="ub.system.vamaountcnt" />', name : 'visitUserCount', width : '100', sortable : true, align: 'left',process: setDefaultName}
		    		],
		    	sortname: 'systemname',
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
	
	

});

function searchList() {
	jQuery('#empDiv').css("display","none");
	
	jQuery('#vlist').flexOptions({
		newp: 1,
		params: [{name: 'searchparam.startDate', value: jQuery('#startTime').val() }, 
		         {name: 'searchparam.endDate', value: jQuery('#endTime').val()}]
	});
	
	jQuery('#vlist').flexReload();
	
}


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
							<li class="ubtabselect"><a href="#"  > <s:property
								value="getText('ub.system.os')" /> </a></li>
								<li><a href="#" onclick="tabClick('<s:url value='/ub/system/ubbrowser.shtml'/>')"> <s:property
								value="getText('ub.system.browser')" /> </a></li>
								<li><a href="#" onclick="tabClick('<s:url value='/ub/system/ubflash.shtml'/>')"> <s:property
								value="getText('ub.system.flash')" /> </a></li>
								<li><a href="#" onclick="tabClick('<s:url value='/ub/system/ubzone.shtml'/>')"> <s:property
								value="getText('ub.system.zone')" /> </a></li>
						</ul>
							<div class="ubtabcontent" >
								<div style="margin:5px">
								<!-- tab内容 -->
								
							<div id="n_indicator" style="height:30px;width:100%;margin:10px 10px 10px 10px;">
							<s:property
							value="getText('ub.system.indicator')" />:
														
							<span class="n_indicator_cbx">  
							
							<label for="check_vcnt"  style="margin-left:3px;vertical-align:middle;" ><input type="checkbox" id="check_vcnt" style="vertical-align:middle;" onclick="indicatorClick(this)" checked="checked" value="vcnt"/><s:property
							value="getText('ub.system.vcnt')" /></label>   
							</span>  
							<span class="n_indicator_cbx">  
							
							<label for="check_ventcnt"  style="margin-left:3px;vertical-align:middle;" ><input type="checkbox" id="check_ventcnt" style="vertical-align:middle;" onclick="indicatorClick(this)" checked="checked" value="ventcnt"/><s:property
							value="getText('ub.system.ventcnt')" /></label>   
							</span> 
							<span class="n_indicator_cbx">  
							
							<label for="check_vacountcnt"  style="margin-left:3px;vertical-align:middle;" ><input type="checkbox" id="check_vacountcnt" style="vertical-align:middle;" onclick="indicatorClick(this)" checked="checked" value="vactcnt"/><s:property
							value="getText('ub.system.vamaountcnt')" /></label>   
							</span> 
							</div>
						
						<div style="text-align:center;width:100%;">
							<div id="n_chart" >
							</div>
						</div>
						<div style="margin: 5px; border: 0px solid #cccccc;">
					<div
						style="height: 30px; background: #eeeeee; border-left: 1px solid #cccccc; border-top: 1px solid #cccccc;border-right: 1px solid #cccccc;margin-bottom: 0px; line-height: 30px; font-weight: bold; padding-left: 10px;"><s:property
						value="getText('ub.system.vstat')" /></div>
					<div style="margin: 0px;">
					<table id="vlist" width="100%" cellspacing="0">
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
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:300px;width:1000px;color:rgb(234,118,79);line-height:300px;border:1px solid #ccc;">图表无数据！</div>
</script>
<script type="text/html" id="tmp_chart_loading">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:300px;width:1000px;color:rgb(72,190,244);line-height:300px;border:1px solid #ccc;">正在加载图表数据...</div>
</script>
<%@include file="../../common/copyright.jsp"%>
</body>
</html>