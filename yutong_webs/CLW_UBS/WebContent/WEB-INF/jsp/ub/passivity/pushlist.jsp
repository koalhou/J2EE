<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
<title><s:property
	value="getText('menu.ub.passiveliveness')" /></title>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
		<script type="text/javascript"
	src="<s:url value='/scripts/common/moment.min.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/common/function_common.js' />"></script>


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
	<script type="text/javascript" 
	src="<s:url value='/scripts/indicator/GlareTpl.js' />"></script>
	<script type="text/javascript" 
	src="<s:url value='/scripts/grouptype/GroupType.js' />"></script>
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
	//gtype.setTimeSpan(jQuery("#startTime").val(),jQuery("#endTime").val(),false);
	
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
	//gtype.setTimeSpan(jQuery("#startTime").val(),jQuery("#endTime").val(),false);
	
	searchList();
	
}

function startTimeClick()
{
	clearQSwitch();
	jQuery("#selMonth").attr("value","");
	//gtype.setTimeSpan(jQuery("#startTime").val(),jQuery("#endTime").val(),false);
}
function endTimeClick()
{
	clearQSwitch();
	jQuery("#selMonth").attr("value","");
	//gtype.setTimeSpan(jQuery("#startTime").val(),jQuery("#endTime").val(),false);
	
	searchList();
	
}



function tabClick(url)
{
	//var q='?st='+jQuery("#startTime").val()+"&et="+jQuery("#endTime").val()+"&datetype="+gtype.selectedType+"&pushID="+questionID;
	var q='?st='+jQuery("#startTime").val()+"&et="+jQuery("#endTime").val()+"&datetype="+gtype.selectedType;
	var url_q=url+q;
	location=url_q;
}
///////////////////
	
	//flexgrid
function get_cell_text(pid, cell_idx) 
{
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}
//转换链接
function reWriteLink(tdDiv,pid)
{
	tdDiv.innerHTML = '<a href="<s:url value="/ub/passivity/overall.shtml" />?st='+jQuery("#startTime").val()+'&et='+jQuery("#endTime").val()+"&datetype="+gtype.selectedType+'&pushID='+ pid +'">' + tdDiv.innerHTML +'</a>';
}

function setPercent(tdDiv,pid)
{
	tdDiv.innerHTML=tdDiv.innerHTML+"%";	
}
jQuery(function() {
	iniTimeSpan();	
	questionID=getQueryStringByName("pushID");
	if(typeof(questionID)=="undefined")questionID=0;
	
   	var q_gtype=getQueryStringByName("datetype");
   	if(typeof(q_gtype)=="undefined")q_gtype="day";
	gtype=new GroupType({containerId:"timespancon1",startTime:jQuery("#startTime").val(),endTime:jQuery("#endTime").val(),selectedType:q_gtype});
   	gtype.render();   	
	gtype.disable(true);
	
		
	var roleList = jQuery('#vlist');
	roleList.flexigrid({
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()}],
		url: '<s:url value="/ub/passivity/json/questtionGrid.shtml" />',
		dataType: 'json',
		height: 'auto',
		width: 'auto',
		colModel : [
		    		{display: '<s:text name="ub.passivity.pushsubject" />', name : 'role_name', width : '300', sortable : true, align: 'left', process: reWriteLink},
		    		{display: '<s:text name="ub.passivity.pushtime" />', name : 'role_name', width : '100', sortable : true, align: 'left'},
		    		{display: '<s:text name="ub.passivity.pushendtime" />', name : 'role_name', width : '100', sortable : true, align: 'left'},
		    		{display: '<s:text name="ub.passivity.pushentcnt" />', name : 'role_name', width : '100', sortable : true, align: 'left'},
		    		{display: '<s:text name="ub.system.ventcnt" />', name : 'role_name', width : '100', sortable : true, align: 'left'},
		    		{display: '<s:text name="ub.system.vcnt" />', name : 'role_name', width : '100', sortable : true, align: 'left'},
		    		{display: '<s:text name="ub.passivity.vrate" />', name : 'full_name', width : '100', sortable : true, align: 'left',process:setPercent}
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
});

function searchList() {
	jQuery('#empDiv').css("display","none");
	jQuery('#vlist').flexOptions({
		newp: 1,
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()}]
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
							<li><a href="#" onclick="tabClick('<s:url value='/ub/passivity/overall.shtml'/>')"> <s:property
								value="getText('ub.passivity.totalv')" /> </a></li>
							<li class="ubtabselect"><a href="#" > <s:property
								value="getText('ub.passivity.everyv')" /> </a></li>
								
							</ul>
							<div class="ubtabcontent" >
								<div style="margin:5px">
								<!-- tab内容 -->
									
												
									
								  	<table id="vlist" width="100%"  cellspacing="0">
		                          	</table>
                          	
						
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

<%@include file="../../common/copyright.jsp"%>
</body>
</html>