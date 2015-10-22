<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
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
<script type="text/javascript">
var plat="";
var chartData=null;
var customerID='';
var accountID='';
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
}
function iniPlat()
{
	var v_plat=getQueryStringByName("plat");
	if(v_plat)
	{
		plat=v_plat;
		
	}
}
function iniCustomID()
{
	var v=getQueryStringByName("customerID");
	if(v)
	{
		customerID=v;
	}
}
function iniAccountID()
{
	var v=getQueryStringByName("accountID");
	if(v)
	{
		accountID=v;
	}
}

function startTimeClick()
{
	
}
function endTimeClick()
{
	
	//searchChartData();
	searchList();
	
}

function searchChartData()
{
	setChartLoading("n_chart",700,200);

	var startDate=jQuery("#startTime").attr("value");
	var endDate=jQuery("#endTime").attr("value");		

	
	
	var url='<s:url value="/ub/custom/json/getOneAccountBars.shtml" />'
			+'?startDay='+startDate+'&endDay='+endDate+"&plat="+plat+"&accountID="+accountID;
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
			setChartNoData("n_chart",700,200);
		}
	});

	
}
function loadChart(data)
{
	if(data==null)
	{
		loger("loadChart:data is null");
		setChartNoData("n_chart",700,200);
		return;		
	}
	if(data.bars==null || data.bars.length<=0)
	{
		loger("loadChart:data.lines is null or empty.");
		setChartNoData("n_chart",700,200);
		return;			
	}
	
		loger("选择一个指标");
		
		var sbar=[];
		var vcntarray=[];
		sbar.push("<chart caption=''  xAxisName=''  yAxisName='' >");
				
		
		
		for(var i=0; i< data.bars.length;i++)
		{
			var item=data.bars[i];
			vcntarray.push("<set label='"+item.name+"' value='"+item.code+"' tooltext='"+item.name+";访问次数："+item.code+"'/>");
		}
		sbar.push(vcntarray.join(""));
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
		var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/Column2D.swf' />","myChartId4", "700","200","0");
		myChartbarms.setDataXML(charXml);
		myChartbarms.render("n_chart");
		
	
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


////////////
function iniServices()
{
	var ser=jQuery("#serviceSel");
	ser.empty();
	var url='<s:url value="/ub/srv/json/getSrvList.shtml" />'
		+"?plat="+plat;
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
	var ser=jQuery("#serviceSel");
	ser.append("<option value='0'>全部服务</option>");
	for(var i=0;i<data.length;i++)
	{
		ser.append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
		
	}
}
function serviceChange()
{
	searchList();
}
function iniList()
{
	var roleList = jQuery('#vlist');
	roleList.flexigrid({
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},		         
				{name: 'plat', value: plat},
				{name: 'accountID', value: accountID},
				{name: 'srvID', value: jQuery("#serviceSel").val()},
				{name: 'customerID', value: customerID}				
				],
		url: '<s:url value="/ub/custom/json/getOneAccountGrid.shtml" />',
		dataType: 'json',
		height: '275',
		width: '700',
		colModel : [
		    		{display: '访问服务名称', name : 'opententcnt', width : '150', sortable : false, align: 'left'},
		    		{display: '操作描述', name : 'opententcnt', width : '150', sortable : false, align: 'left'},
		    		{display: '操作时间', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
		    		{display: 'IP地址', name : 'opententcnt', width : '100', sortable : false, align: 'left'},
		    		{display: 'IP归属地', name : 'opententcnt', width : '120', sortable : false, align: 'left'}
		    			    		
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
		    	autoload:false
	});
}
	//flexgrid
function get_cell_text(pid, cell_idx) 
{
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

jQuery(function() {
	iniCustomID();
	iniAccountID();
	iniTimeSpan();	
	iniPlat();	
	iniServices();
  	iniList();  
  	
   	searchChartData();
   	searchList();
});
function searchList() {
	jQuery('#empDiv').css("display","none");
	jQuery('#vlist').flexOptions({
		newp: 1,
		params: [{name: 'startDay', value: jQuery('#startTime').val() }, 
		         {name: 'endDay', value: jQuery('#endTime').val()},		         
				{name: 'plat', value: plat},
				{name: 'accountID', value: accountID},
				{name: 'srvID', value: jQuery("#serviceSel").val()},
				//{name: 'srvID', value: '5'},
				{name: 'customerID', value: customerID}				
				]
	});
	jQuery('#vlist').flexReload();
}
function query()
{
	//searchChartData();
	searchList();
}

</script>
<style>
.ubcon
{
	
	border:1px solid #ccc;
	margin:5px;	
	padding:5px;
}
.ubcontainer
{
	margin:5px;
	
	
}
.ubtable
{
	border-top:3px solid #ccc;
}
</style>
<div class="ubcontainer">
	<div class="ubcon" style="border:0px;">
		<div id="n_chart"></div>
	</div>
	<div class="ubcon ubtable">
		<div style="float: left;  padding: 5px; line-height: 30px;">
						
							<div style="float:left;padding:0px 10px;" >
							服务:
							<select id="serviceSel" onchange="serviceChange()" style="width:100px;">
							<option value="0">全部</option>
							</select>
			                 </div>
							
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
						 	<div style="float:left;padding:0px 10px;margin-top:3px;" >
                             	 <a href="#" onclick="query()" class="sbutton">
                                <s:property value="getText('btn.query')" />
                             		 </a>
                            </div>
							

		</div>
		<div >
		<table id="vlist" width="100%"  cellspacing="0">
        </table>
	</div>
	</div>
	
</div>
<script type="text/html" id="tmp_chart_nodata">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:[[height]]px;width:[[width]]px;color:rgb(234,118,79);line-height:[[height]]px;border:1px solid #ccc;">图表无数据！</div>
</script>
<script type="text/html" id="tmp_chart_loading">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height[[height]]px;width:[[width]]px;color:rgb(72,190,244);line-height:[[height]]px;border:1px solid #ccc;">正在加载图表数据...</div>
</script>


</body>
</html>