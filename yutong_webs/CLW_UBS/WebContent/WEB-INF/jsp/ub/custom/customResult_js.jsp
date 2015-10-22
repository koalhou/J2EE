<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<link rel="stylesheet" href="<s:url value='/scripts/indicator/indicator.css' />"
	type="text/css" media="all" />	
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/common/function_common.js' />"></script>
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
var chartData=null;

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
	query();
}
function onGTypeChange(data)
{
	query();
}


////////////
function changeBoolean(tdDiv,pid)
{
	if(tdDiv.innerHTML=="0")
		tdDiv.innerHTML="<span style='color:red'>否</span>";
	else
		tdDiv.innerHTML="<span style='color:green'>是</span>";
}
function viewSrv(tdDiv,pid)
{
	
	tdDiv.innerHTML = '<a href="#" onclick="goToEntSrv(\''+pid+'\')" >查看</a>';
	
}
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

function goToEntSrv(sid)
{
	var q='?st='+jQuery("#startTime").val()+"&et="+jQuery("#endTime").val()+"&dateType="+gtype.selectedType+"&plat="+jQuery("#plat").val()+"&entID="+sid;
	var url='<s:url value="/ub/custom/entsrv.shtml"/>';
	var url_q=url+q;
	window.open(url_q);
}
function goToEntOverall(sid)
{
	var q='?st='+jQuery("#startTime").val()+"&et="+jQuery("#endTime").val()+"&plat="+jQuery("#plat").val()+"&entID="+sid;
	var url='<s:url value="/ub/custom/entoverall.shtml"/>';
	var url_q=url+q;
	window.open(url_q);

}
	//flexgrid
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}
function iniSearchText()
{
	var v=getQueryStringByName("searchText");
	if(v)
	{
		jQuery("#searchText").val(v);
	}
}
jQuery(function() {
	iniTimeSpan();
   	var q_gtype=getQueryStringByName("datetype");
   	if(typeof(q_gtype)=="undefined")q_gtype="day";
	gtype=new GroupType({containerId:"timespancon1",startTime:jQuery("#startTime").val(),endTime:jQuery("#endTime").val(),selectedType:q_gtype,onChange:onGTypeChange});
   	gtype.render();
	   	
   	iniPlat();
   	iniSearchText();
   	//iniServiceId();  
	
	//dealSummary();			
	//searchChartData();
	//searchList();
	gtype.disable(true);
   	
   	var roleList = jQuery('#vlist');
	roleList.flexigrid({
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},		         
				{name: 'plat', value: jQuery('#plat').val()},
				{name: 'ac', value: jQuery('#activitySelect').val()},
				{name: 'customerID', value: (jQuery('#searchText').val()=="请输入企业客户名称或企业编码")?"":jQuery('#searchText').val()},
				{name: 'area', value: jQuery('#zoneSelect').val()},
				{name: 'province', value: jQuery('#provinceSelect').val()},
				{name: 'city', value: jQuery('#citySelect').val()},
				{name: 'customerType', value: jQuery('#entTypeSelect').val()}
				],
		url: '<s:url value="/ub/custom/json/search.shtml" />',
		dataType: 'json',
		height: 'auto',
		width: 'auto',
		colModel : [
					
		    		{display: '企业名称', name : 'time', width : '300', sortable : false, align: 'left',process:viewEnt},
		    		{display: '企业编码', name : 'vcnt', width : '100', sortable : false, align: 'left'},
		    		{display: '企业类别', name : 'ventcnt', width : '100', sortable : false, align: 'left',process:viewEntClass},
		    		{display: '开通时间', name : 'opententcnt', width : '200', sortable : false, align: 'left'},
		    		{display: '是否活跃', name : 'opententcnt', width : '100', sortable : false, align: 'left',process:changeBoolean},
		    		{display: '访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
		    		{display: '上午访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
		    		{display: '下午访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
		    		{display: '晚上访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
		    		{display: '追加访问次数', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
		    		{display: '服务访问情况', name : 'activity', width : '100', sortable : false, align: 'left',process:viewSrv}
		    		],
		    	sortname: 'id',
		    	sortorder: 'asc',
		    	sortable: false,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp:20,	
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		    	autoload:true
	});

});


function searchList() {
	jQuery('#empDiv').css("display","none");
	jQuery('#vlist').flexOptions({
		newp: 1,
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},		         
				{name: 'plat', value: jQuery('#plat').val()},
				{name: 'ac', value: jQuery('#activitySelect').val()},
				{name: 'customerID', value: (jQuery('#searchText').val()=="请输入企业客户名称或企业编码")?"":jQuery('#searchText').val()},
				{name: 'area', value: jQuery('#zoneSelect').val()},
				{name: 'province', value: jQuery('#provinceSelect').val()},
				{name: 'city', value: jQuery('#citySelect').val()},
				{name: 'customerType', value: jQuery('#entTypeSelect').val()}
				]
	});
	jQuery('#vlist').flexReload();
}


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

function query()
{
	var searchText=jQuery("#searchText").val();
	if(searchText=='请输入企业客户名称或企业编码')searchText='';
		searchList();
}

</script>