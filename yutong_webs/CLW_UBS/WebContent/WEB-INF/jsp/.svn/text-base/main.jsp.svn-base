<%@include file="common/taglibs.jsp" %>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="common/meta.jsp" %>
<title><s:property value="getText('main.name')" /></title>

	<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/poshytip/tip-yellow/tip-yellow.css'/>">
	
<style type="text/css">
.reportOnline2 p{
	font-size: 12px;
	text-indent: 25px;
	line-height: 24px;
	padding:5px 0 12px 0;
	border-bottom:1px dotted #CCCCCC;
}

.msgBoxind p{
	font-size: 12px;
	text-indent: 25px;
	line-height: 24px;
	padding:18px 0 0 0;
}

.STYLE6 {
	font-size: 10px;
	color: #43132F;
}
.summaryfont
{
	font-weight:bold;
	font-size:16px;
	color:#333;
	
	padding:0 5px;
}
.indextip
{
	font-weight:bold;
	font-size:16px;
	color:red;
	
	padding:0 5px;
}
</style>
</head>
<body>
  <%@include file="common/menu.jsp"%>
  <script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/common/function_common.js' />"></script>
	<script type="text/javascript" 
	src="<s:url value='/scripts/indicator/GlareTpl.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/fusioncharts/FusionCharts.js' />"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/common/moment.min.js' />"></script>

<script type="text/javascript"
	src="<s:url value='/scripts/poshytip/jquery.poshytip.js' />"></script>	
<script type="text/javascript"
	src="<s:url value='/scripts/common/HelpTip.js' />"></script>
<script>
jQuery(function() {
	loadSummary();
	//企业
	<s:if test="#session.perUrlList.contains('111_4_2')">
		loadEntSum();
		loadEntChart();
		loadEntRank();	
		
	</s:if>
	<s:else>
		NoPermition("body_entchart");
		NoPermition("ent_rank");
	
	</s:else>
	
	//服务
	<s:if test="#session.perUrlList.contains('111_4_3')">
		loadSrv();
	</s:if>
	<s:else>
		NoPermition("body_srvchart");
		NoPermition("srv_rank");
	</s:else>
	
	//passitivy
	<s:if test="#session.perUrlList.contains('111_4_4')">
		loadPassivity();
	</s:if>
	<s:else>
		NoPermition("body_pas");
	</s:else>
	
	
	//login statistic
	<s:if test="#session.perUrlList.contains('111_4_6')">
		loadLoginCnt();
	</s:if>
	<s:else>
		NoPermition("body_login");
	</s:else>
	
	//zone statistic
	<s:if test="#session.perUrlList.contains('111_4_5')">
		loadZoneChart();
	</s:if>
	<s:else>
		NoPermition("chart_zone");
	</s:else>
	
	HelpTip.initTip();
});
function NoPermition(divid)
{
	jQuery("#"+divid).empty().html("无权限.");
	
}
function onTipMouseOver(e)
{
		loger(this);
		loger(e);
		loger("tip on");
		
}
function onTipMouseOut(e)
{
	loger(this);
	loger(e);
	loger("tip on");
}

function loadSummary()
{
	var dt=moment().format("YYYY-MM-DD");
	var lastdt=moment().subtract("months",1).format("YYYY年MM月");
	jQuery("#sum_date").html(lastdt);
	var url='<s:url value="/ub/index/json/getEntAndBus.shtml" />'+'?curDate='+dt;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			
			onLoadSummarySucc(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			setChartNoData("chart_ent",500,200);
		}
	});	
}
function onLoadSummarySucc(data)
{
	if(data==null || data.ga==null )
	{
		loger("data.rows is null");
		jQuery("#sum_vehicle").html("99999");
		jQuery("#sum_ent").html("99999");
		return;	
		
	}
	jQuery("#sum_vehicle").html(data.ga.busCnt);
	jQuery("#sum_ent").html(data.ga.openEntCnt);
}

//企业活跃度，排行榜
function loadEntSum()
{
	var lastdt=moment().subtract("months",1).format("MM");
	jQuery("#ent_tip_lastmonth").html(lastdt);
	
	var dt=moment().format("YYYY-MM-DD");	
	var url='<s:url value="/ub/index/json/getCAInfoLastMonth.shtml" />'+'?curDate='+dt;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			
			onLoadEntSummary(data);
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
			loger(textStatus);
			
			
		}
	});
}
function loadEntChart()
{
	var lastdt=moment().subtract("months",1).format("MM");
	jQuery("#ent_tip_lastmonth").html(lastdt);
	
	var dt=moment().format("YYYY-MM-DD");	
	var url='<s:url value="/ub/index/json/getCAInfoLast6Month.shtml" />'+'?curDate='+dt;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			
			
			onLoadEntChartSucc(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
			loger(textStatus);
			
			setChartNoData("chart_ent",500,200);
		}
	});
}
function loadEntRank()
{
	var lastdt=moment().subtract("months",1).format("MM");
	jQuery("#ent_tip_lastmonth").html(lastdt);
	
	var dt=moment().format("YYYY-MM-DD");	
	var url='<s:url value="/ub/index/json/getCARankLastMonth.shtml" />'+'?curDate='+dt;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			
			onLoadEntRankSucc(data);
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
			loger(textStatus);
			
			
		}
	});
}
function onLoadEntSummary(data)
{
	if(data==null||data.lastMonth==null){
		
		return;
	}
	
	try	
	{
		var item=data.lastMonth;
		jQuery("#ent_tip_web_acentcnt").html(item.webEmEntCnt);
		jQuery("#ent_tip_web_ac").html(item.webEp+"%");
		jQuery("#ent_tip_web_dayvis").html(item.webDayVis);
		
		jQuery("#ent_tip_app_acentcnt").html(item.appEmEntCnt);
		jQuery("#ent_tip_app_ac").html(item.appEp+"%");
		jQuery("#ent_tip_app_dayvis").html(item.appDayVis);
	}
	catch(e)
	{
		loger(e);
		
	}
	
}
function onLoadEntChartSucc(data)
{
	if(data==null||data.caBars==null){
		setChartNoData("chart_ent",600,200);
		return;
	}
	
	
	var sbar=[];
	
	var strbarms=[];var categoryarray=[],webacarray=[],appacarray=[];
	strbarms.push("<chart caption='' bgColor='ffffff' showBorder='0' canvasBgColor='ffffff' canvasBorderThickness='0' numberSuffix='%25 '>");
	categoryarray.push(" <categories>");
	
	webacarray.push("<dataset seriesName='Web平台企业活跃度'>");
	appacarray.push("<dataset seriesName='手机客户端企业活跃度'>");
	
	for(var i=0;i<data.caBars.length;i++)
	{
		var item=data.caBars[i];
		
		var s= "   <category  label='"+item.reportDate+"' />";
		categoryarray.push(s);
		
		//webacarray.push("<set value='"+item.webEp+"'  tooltext='活跃企业数:"+item.webEmEntCnt+"{br}总访问次数: "+item.webVis+" 日均访问次数："+item.webDayVis+"'/>");
		//appacarray.push("<set value='"+item.appEp+"'  tooltext='活跃企业数:"+item.appEmEntCnt+"{br}总访问次数: "+item.appVis+" 日均访问次数："+item.appDayVis+"'/>");
		webacarray.push("<set value='"+item.webEp+"'  tooltext='活跃度:"+item.webEp+"%25'/>");
		appacarray.push("<set value='"+item.appEp+"'  tooltext='活跃度:"+item.appEp+"%25'/>");
		
	}
	categoryarray.push("</categories>");
	webacarray.push("</dataset>");
	appacarray.push("</dataset>");
	
	strbarms.push(categoryarray.join(""));
	strbarms.push(webacarray.join(""));
	strbarms.push(appacarray.join(""));
		
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
	var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/MSColumn2D.swf' />","myChartId4", "600","200","0");
	
	myChartbarms.setDataXML(xml);
	myChartbarms.render("chart_ent");
}
function onLoadEntRankSucc(data)
{
	if(data==null||data.caRankList==null||data.caRankList.length<=0){
		loger("caranklist is null.");
		return;
	}
	var div=jQuery("#ent_rank");
	div.empty();
	var dom="<ul class='ubrank'>";
	
	for(var i=0;i<data.caRankList.length;i++)
	{
		if(i>9)
			break;
		var index=i+1;
		var licss="r"+index;
		dom+="<li class='"+licss+"'><span class='rank_right'>"+data.caRankList[i].vis+"次</span><span class='rank_title'>"+data.caRankList[i].name+"</span></li>";
	}
	dom+="</ul>";
	div.append(dom);
	
}
function loadSrv()
{
	var lastdt=moment().subtract("months",1).format("MM");
	jQuery("#srv_tip_lastmonth").html(lastdt);
	
	var dt=moment().format("YYYY-MM-DD");	
	var url='<s:url value="/ub/index/json/getSAInfoLastMonth.shtml" />'+'?curDate='+dt;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			onLoadSrvRankSucc(data);
			onLoadSrvSummary(data);
			onLoadSrvChartSucc(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
			loger(textStatus);
			
			setChartNoData("chart_srv",500,200);
		}
	});
}
function onLoadSrvSummary(data)
{
	if(data==null||data.saBars==null){
		
		return;
	}
	
	var item=data.saBars[data.saBars.length-1];
	jQuery("#srv_tip_srvcnt").html(item.total);
	jQuery("#srv_tip_basesrvcnt").html(item.baseReq);
	jQuery("#srv_tip_quasrvcnt").html(item.qulityReq);
	
}
function onLoadSrvChartSucc(data)
{
	if(data==null||data.saBars==null){
		setChartNoData("chart_srv",600,200);
		return;
	}
	var sbar=[];
	
	var strbarms=[];var categoryarray=[],basearray=[],quaarray=[];
	strbarms.push("<chart caption=''  bgColor='ffffff' showBorder='0' canvasBgColor='ffffff' canvasBorderThickness='0' numberSuffix='%25 '>");
	categoryarray.push(" <categories>");
	
	basearray.push("<dataset seriesName='符合基本要求的服务比例'>");
	quaarray.push("<dataset seriesName='符合质量要求的服务比例'>");
	
	for(var i=0;i<data.saBars.length;i++)
	{
		var item=data.saBars[i];
		
		var s= "   <category  label='"+item.reportDate+"' />";
		categoryarray.push(s);
		
		basearray.push("<set value='"+item.baseReqPercent+"' />");		
		quaarray.push("<set value='"+item.qulityReqPercent+"'  />");
		
		//basearray.push("<set value='20' />");		
		//quaarray.push("<set value='"+i+"'  />");
		
	}
	categoryarray.push("</categories>");
	basearray.push("</dataset>");
	quaarray.push("</dataset>");
	
	strbarms.push(categoryarray.join(""));
	strbarms.push(basearray.join(""));
	strbarms.push(quaarray.join(""));
		
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
	var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/MSColumn2D.swf' />","myChartId4", "600","200","0");
	
	myChartbarms.setDataXML(xml);
	myChartbarms.render("chart_srv");
}
function onLoadSrvRankSucc(data)
{
	if(data==null||data.saRankList==null||data.saRankList.length<=0){
		return;
	}
	var div=jQuery("#srv_rank");
	div.empty();
	var dom="<ul class='ubrank'>";
	
	for(var i=0;i<data.saRankList.length;i++)
	{
		if(i>9)
			break;
		var index=i+1;
		var licss="r"+index;
		dom+="<li class='"+licss+"'><span class='rank_right'>"+data.saRankList[i].visActivity+"%</span><span class='rank_title'>"+data.saRankList[i].name+"</span></li>";
	}
	dom+="</ul>";
	div.append(dom);
	
}
//加载推送活跃度统计
function loadPassivity()
{
	var lastdt=moment().subtract("months",1).format("MM");
	
	var dt=moment().format("YYYY-MM-DD");	
	var url='<s:url value="/ub/index/json/getPAInfoLastMonth.shtml" />'+'?curDate='+dt;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			
			onLoadPassivitySucc(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
			loger(textStatus);
			
			setChartNoData("chart_srv",500,200);
		}
	});		
}
function onLoadPassivitySucc(data)
{
	if(data==null||data.paList==null){
		return;
	}
	var div=jQuery("#palist");
	div.empty();
	var dom="<table class='ubindextable'><thead ><tr><th>推送主题</th><th>访问次数</th><th>访问比例</th></tr></thead>";
	dom+="<tbody>";
	for(var i=0;i<data.paList.length;i++)
	{
		if(i>4)
			break;
		var item=data.paList[i];
		dom+="<tr><td><span title='"+item.name+"'>"+eclipse(item.name,10)+"</span></td><td>"+item.vis+"</td><td>"+item.visPercent+"%</td></tr>"
	}
	dom+="</tbody>";
	div.append(dom);
			
}
//加载登陆次数统计
function loadLoginCnt()
{
	//这里，需要传递上一个月。接口不处理上一个月逻辑。
	var lastdt=moment().subtract("months",1).format("YYYY-MM");
	
		
	var lastdttip=moment().subtract("months",1).format("MM");
	jQuery("#login_tip_lastmonth").html(lastdttip);
	
	
	var url='<s:url value="/ubl/mainlogincnt.shtml" />'+'?searchparam.statis_month='+lastdt;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			
			onLoadLoginCntSucc(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
			loger(textStatus);
			
			setChartNoData("chart_srv",500,200);
		}
	});			
}
function onLoadLoginCntSucc(data)
{
	
	if(data==null||data.rows==null||data.rows.length<=0){
		return;
	}
	var div=jQuery("#loginlist");
	div.empty();
	var dom="<table class='ubindextable'><thead ><tr><th>客户类别</th><th>数量</th><th>活跃客户</th><th>活跃比例</th></tr></thead>";
	dom+="<tbody>";
	for(var i=0;i<data.rows.length;i++)
	{
		if(i>4)
			break;
		var item=data.rows[i].cell;
		dom+="<tr><td><span title='"+item[0]+"'>"+eclipse(item[0],10)+"</span></td><td>"+item[1]+"</td><td>"+item[2]+"</td><td>"+item[4]+"</td></tr>";
	}
	dom+="<tr><td colspan=4 style='padding-left:10px;color:#888;'>月登录次数大于等于8的为活跃客户。</td></tr>";
	dom+="</tbody>";
	div.append(dom);	
	
}

//加载区域统计
function loadZoneChart()
{
	var d=moment().subtract('months', 1);	
	var startDate = d.startOf('month').format('YYYY-MM-DD');
	var  endDate  = d.endOf('month').format('YYYY-MM-DD');
	
	//var startDate='2013-6-1';
	//var endDate='2013-6-30';
	var url='<s:url value="/ubs/zoneAreaList.shtml" />'+'?searchparam.startDate='+startDate+'&searchparam.endDate='+endDate;
	jQuery.ajax({
		url : url,
		cache : false,
		dataType : "json",
		async : true,
		success : function(data) {
			
			onLoadZoneChartSucc(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("error:"+textStatus);
			loger(textStatus);
			setChartNoData("chart_zone",300,300); 
		}
	});
}
function onLoadZoneChartSucc(data)
{
	var h=330;
	if(data==null)
	{
		loger("loadChart:data is null");
		setChartNoData("chart_zone",300,h);
		return;		
	}
	if(data.rows==null || data.rows.length<=0)
	{
		loger("loadChart:data.rows is null");
		setChartNoData("chart_zone",300,h);
		return;	
		
	}
	 
	
		var sbar=[];
		//sbar.push("<chart bgColor='ffffff' showBorder='0' showvalues='1' showLegend='1' enablesmartlabels='0' showlabels='0' showpercentvalues='0' >");
		sbar.push("<chart bgColor='ffffff' showBorder='0' enablesmartlabels='0' numberSuffix='次'>");
		//sbar.push("<chart showLegend='1' caption='Sales Per Employee for year 1996' palette='2' animation='1' formatNumberScale='0' numberPrefix='$' pieSliceDepth='30' startingAngle='125'>");
		var offset=1;
		for(var i=0; i< data.rows.length;i++)
		{
			var item=data.rows[i].cell;
			var str="<set label='"+item[0]+"' value='"+item[offset]+"' />";
			//var str="<set label='"+item[0]+"' value='"+i+"' />";
			
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
		var myChartbarms = new FusionCharts("<s:url value='/scripts/fusioncharts/Pie2D.swf' />","myChartId4", "300",h,"0");
		myChartbarms.setDataXML(charXml);
		myChartbarms.render("chart_zone");
	
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
</script>
 <div class="maincontainer">
		<div class="topinfo" >
			<img src="<s:url value='/images/ub/gaishu1.png' />" alt="" class="topinfoimg" />
			<span>
				截止<span class="summaryfont" id="sum_date" >-</span>
				底，已有<span _ubtip="index_tip1" _ubtip_iconshow="0"><span class="summaryfont" id="sum_ent" >-</span></span>家企业开通了安芯校车智能管理系统，累计注册车辆<span _ubtip="index_tip2" _ubtip_iconshow="0"><span class="summaryfont" id="sum_vehicle">-</span></span>台。
			</span>
		</div>
		<div class="imain">
			<div class="ibox maringrb">
				<h5 class="imaintitle " >
					<span>
						<a href="<s:url value='/ub/custom/init.shtml'/>">详情>></a>
					</span>
					企业客户活跃度
				</h5>
				<div class="iboxcontent">
					<div style="margin:25px 30px 0px 30px;height:300px;" id="body_entchart">
						<div class="tipcon">
							<div class="tiprectangle">
								<p style="line-height:25px;">
								<span id="ent_tip_lastmonth" class="indextip">-</span>月份WEB平台活跃企业数<span id="ent_tip_web_acentcnt" class="indextip">-</span>，企业活跃度<span id="ent_tip_web_ac" class="indextip">-</span>，日均访问次数<span id="ent_tip_web_dayvis" class="indextip">-</span>；
								<br/>手机客户端活跃企业数<span id="ent_tip_app_acentcnt" class="indextip">-</span>，企业活跃度<span id="ent_tip_app_ac" class="indextip">-</span>，日均访问次数<span id="ent_tip_app_dayvis" class="indextip">-</span>。
								</p>
							</div>
							<div class="tiparrow"></div>
						</div>
						<div id="chart_ent" ></div>
					</div>

				</div>
			</div>
			<div class="ibox maringrb">
				<h5 class="imaintitle">
					<span>
						<a href="<s:url value='/ub/srv/overall.shtml'/>">详情>></a>
					</span>
					服务活跃度
				</h5>
				<div class="iboxcontent">
					<div style="margin:25px 30px 0px 30px;height:300px;" id="body_srvchart">
						<div class="tipcon">
							<div class="tiprectangle">
								<p style="line-height:25px;">
								WEB平台截止<span id="srv_tip_lastmonth" class="indextip">-</span>月份服务总个数<span id="srv_tip_srvcnt" class="indextip">-</span>，符合基本要求的服务数<span id="srv_tip_basesrvcnt" class="indextip">-</span>，符合质量要求的服务数<span id="srv_tip_quasrvcnt" class="indextip">-</span>。
								</p>
							</div>
							<div class="tiparrow"></div>
						</div>
						<div id="chart_srv" ></div>
					</div>
				</div>
			</div>
			
			<div class="ibox maringrb" style="float:left;width:332px;">
				<h5 class="imaintitle">
					<span>
						<a href="<s:url value='/ub/passivity/overall.shtml'/>">详情>></a>
					</span>
					推送访问情况
				</h5>
				<div class="iboxcontent" style="padding:0px;"><div id="palist" style="height:200px;"><span style="padding:10px;display:inline-block;" id="body_pas">无数据.</span></div></div>
			</div>
			<div class="ibox maringrb" style="float:left;width:332px;">
				<h5 class="imaintitle">
					<span>
						<a href="<s:url value='/ub/login/logincountstat.shtml'/>">详情>></a>
					</span>
					<span id="login_tip_lastmonth" style="float:left">-</span>月份Web平台客户活跃度报表
				</h5>
				<div class="iboxcontent" style="padding:0px;"><div id="loginlist" style="height:200px;"><span style="padding:10px;display:inline-block;" id="body_login">无数据.</span></div></div>
			</div>
		</div>
		<div class="iside">
			<div class="ibox maringb">
				<h5 class="isidetitle">
					
					Web平台企业访问次数排名
				</h5>
				<div class="iboxcontent" ><div id="ent_rank" style="height:250px;">无数据.</div></div>
			</div>
			<div class="ibox maringb">
				<h5 class="isidetitle">
					Web平台服务活跃度排名
				</h5>
				<div class="iboxcontent"><div id="srv_rank" style="height:250px;">无数据.</div></div>
			</div>
			<div class="ibox maringb">
				<h5 class="isidetitle">
					区域分布
				</h5>
				<div class="iboxcontent" ><div id="chart_zone" style="height:330px;"></div></div>
			</div>
		</div>
	</div> 
	<script type="text/html" id="tmp_chart_nodata">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:[[height]]px;width:[[width]]px;color:rgb(234,118,79);line-height:[[height]]px;border:1px solid #ccc;">图表无数据！</div>
</script>
<script type="text/html" id="tmp_chart_loading">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height[[height]]px;width:[[width]]px;color:rgb(72,190,244);line-height:[[height]]px;border:1px solid #ccc;">正在加载图表数据...</div>
</script>
  <%@include file="common/copyright.jsp"%>
</body>
</html>


