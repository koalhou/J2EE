// JavaScript Document
//宇通通勤车，组织ID，生产环境需要根据情况修改，已拉出来在配置文件中配置
var yutong_enterprise_id = "";
function tabSwitch(id){
	if(id=="treeupid"){
		jQuery("#treeupid").removeClass();
		jQuery("#treedownid").removeClass();
		jQuery("#treetqcid").removeClass();
		jQuery("#treeupid").addClass('tabfocus');
		jQuery("#treedownid").addClass('tab');
		jQuery("#treetqcid").addClass('tab');
		//下两行加载树
		treeType='routeup';

		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}else if(id=="treedownid"){
		jQuery("#treeupid").removeClass();
		jQuery("#treedownid").removeClass();
		jQuery("#treetqcid").removeClass();
		jQuery("#treeupid").addClass('tab');
		jQuery("#treedownid").addClass('tabfocus');
		jQuery("#treetqcid").addClass('tab');
		//下两行加载树
		treeType='routedown';

		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}else if(id=="treetqcid"){
		jQuery("#treeupid").removeClass();
		jQuery("#treedownid").removeClass();
		jQuery("#treetqcid").removeClass();
		jQuery("#treeupid").addClass('tab');
		jQuery("#treedownid").addClass('tab');
		jQuery("#treetqcid").addClass('tabfocus');
		//下两行加载树
		treeType='routetqc';

		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}
}
//左侧树勾选线路调用
var chart_route_id = "";
function onClickRoute_click(route_id){
	if(chart_route_id != route_id){
		chart_route_id = route_id;
	} else {
		return;
	}
	getStackChart();
	getDataByRoute();
}
function getStackChart() {
	
	var startTime=jQuery("#begintime").val();
	var endTime=jQuery("#endtime").val();
	var startDate= new Date(Date.parse(startTime.replace(/-/g,"/"))); 
	var endDate= new Date(Date.parse(endTime.replace(/-/g,"/"))); 
	
	if (endDate.getTime()-startDate.getTime()>1000*60*60*24*35-1){
		jQuery('#vehicle_codeDesc').text("日期区间不能超过35天!");
		 setTimeout(function(){jQuery('#vehicle_codeDesc').text("")},3000);		
		return false;
	 }
	
	
	var showVacationDetail = "";
	if(jQuery('#showVacationDetail')[0].checked) {
		showVacationDetail = "1";
	} else {
		showVacationDetail = "";
	}
	PassengerStatDwr.getStackChart(chart_route_id, jQuery('#begintime').val(), jQuery('#endtime').val()+" 23:59:59", showVacationDetail, backFun_getRoutInfo);
}
function backFun_getRoutInfo(data) {
	if(data.length > 0) {
		jQuery('#routeName').html(data[0].ROUTE_NAME);//线路名称
		
		var xmlDoc = "<chart  yAxisName='人次'  yAxisMaxValue='5'  showValues='0' labelDisplay='ROTATE' slantLabels='1'  >";//堆栈图XML
		var categories = "<categories>";
		var dataset = "";
		//站点
		jQuery('#siteName').html("");
		var site_id = "";
		var isAdd = true;
		for(var i = 0; i < data.length; i++) {
			if(i == 0) {
				jQuery('#siteName').append(data[i].SITE_NAME+"<img style='vertical-align:bottom;' src='../newimages/statistics/start.png'/>");
				site_id = data[i].SITE_ID;
				categories += "<category label='"+data[i].RQ+"' />";
				dataset += "<dataset seriesname='"+data[i].SITE_NAME+"' >";
				dataset += "<set value='"+data[i].PERSON_COUNT+"' />";
			} else if(i > 0 && i < data.length - 1) {
				if(site_id == data[i].SITE_ID) {
					if(isAdd) {
						categories += "<category label='"+data[i].RQ+"' />";
					}
					dataset += "<set value='"+data[i].PERSON_COUNT+"' />";
				} else {
					jQuery('#siteName').append("<img src='../newimages/statistics/direct.png'/><span style='white-space:nowrap;'>" + data[i].SITE_NAME + "</span>");
					dataset += "</dataset>";
					dataset += "<dataset seriesname='"+data[i].SITE_NAME+"' >";
					dataset += "<set value='"+data[i].PERSON_COUNT+"' />";
					
					site_id = data[i].SITE_ID;
					isAdd = false;
				}
			} else if(i == data.length - 1) {
				
				if(site_id == data[i].SITE_ID) {
					if(isAdd) {
						categories += "<category label='"+data[i].RQ+"' />";
					}
					dataset += "<set value='"+data[i].PERSON_COUNT+"' />";
				} else {
					jQuery('#siteName').append("<img src='../newimages/statistics/direct.png'/><span style='white-space:nowrap;'>" + data[i].SITE_NAME + "</span>");
					dataset += "</dataset>";
					dataset += "<dataset seriesname='"+data[i].SITE_NAME+"' >";
					dataset += "<set value='"+data[i].PERSON_COUNT+"' />";
					
					site_id = data[i].SITE_ID;
					isAdd = false;
				}
			}
		}
		jQuery('#siteName').append("<img style='vertical-align:bottom;' src='../newimages/statistics/end.png'/>");
		//jQuery('#site').css("margin-bottom", "20px;");
		categories += "</categories>";
		dataset += "</dataset>";
		xmlDoc += categories;
		xmlDoc += dataset;
		xmlDoc += "</chart>";

		var chart1 = new FusionCharts("../scripts/fusioncharts/StackedColumn2D.swf", "myChartId", "100%", "100%", "0", "0");
		chart1.setDataXML(xmlDoc);
		chart1.render("stackChart");
	}
}



function checkDateDetail() {
	var startTime=jQuery("#begintime").val();
	var endTime=jQuery("#endtime").val();
	var startDate= new Date(Date.parse(startTime.replace(/-/g,"/"))); 
	var endDate= new Date(Date.parse(endTime.replace(/-/g,"/"))); 
	
	if (endDate.getTime()-startDate.getTime()>1000*60*60*24*35-1){
		jQuery('#vehicle_codeDesc').text("日期区间不能超过35天!");
		 setTimeout(function(){jQuery('#vehicle_codeDesc').text("")},3000);		
		return false;
	 }
	getStackChart();
	getDataByRoute();
}






function getDataByRoute() {
	
	var startTime=jQuery("#begintime").val();
	var endTime=jQuery("#endtime").val();
	var startDate= new Date(Date.parse(startTime.replace(/-/g,"/"))); 
	var endDate= new Date(Date.parse(endTime.replace(/-/g,"/"))); 
	
	if (endDate.getTime()-startDate.getTime()>1000*60*60*24*35-1){
		//jQuery('#vehicle_codeDesc').text("日期区间不能超过35天!");
		// setTimeout(function(){jQuery('#vehicle_codeDesc').text("")},3000);		
		return false;
	 }
	
	var showVacationDetail = "";
	if(jQuery('#showVacationDetail')[0].checked) {
		showVacationDetail = "1";
	} else {
		showVacationDetail = "";
	}
	PassengerStatDwr.getDataByRoute(yutong_enterprise_id,chart_route_id, jQuery('#begintime').val(), jQuery('#endtime').val()+" 23:59:59", showVacationDetail, backFun_getDataByRoute);
}
function backFun_getDataByRoute(data) {
	debugger;
	jQuery('#banciDetail').html(data[0].BANCI);
	jQuery('#zuoweiDetail').html(data[0].ZUOWEI);
	jQuery('#renciDetail').html(data[0].RENCI);
}
//隐藏、显示线路站点
function hideSiteInfo(imgObj) {
	
	if(imgObj==null){
		alert(imgObj);
	}
	if(imgObj.src.indexOf("close.png") >= 0) {
		imgObj.src = "../newimages/statistics/open.png";
		jQuery('#siteTitle').css("display","none");
		auto_size();
	} else if(imgObj.src.indexOf("open.png") >= 0) {
		imgObj.src = "../newimages/statistics/close.png";
		jQuery('#siteTitle').css("display","block");
		auto_size2();
	}
}
//左侧树，查询线路
function searchRoute() {
	routename = jQuery("#search_route_name").val();
	searchTree();
}
function auto_size(){	
	
	//计算中区高度
	jQuery('#content').mk_autoresize({
		height_include : ['#content_rightside', '#content_leftside'],
		height_offset : 0,
		width_exclude: '#content_leftside',
		width_include : '#content_rightside',
		width_offset : 2
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize({
		height_exclude : ['.treeTab', '.newsearchPlan'],
		height_include : '#treeDemoDiv',
		height_offset : 0
	});
	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize({
		width_include :  ['.titleBar','#statusManger','#contentData'] ,
		width_offset : 0,
		height_exclude : ['.titleBar','#statusManger'] ,
		height_include : '#contentData',
		height_offset : 0
	});
	
	jQuery('#contentData').mk_autoresize({
		width_include :  ['#stackChart','#statInfo','#passengerStatRouteTitle','#siteTitle'] ,
		width_offset : 30,
		height_exclude : ['#stackChart','#statInfo'] ,
		height_offset :0
	});
}


function auto_size2(){
	//计算中区高度
	jQuery('#content').mk_autoresize({
		height_include : ['#content_rightside', '#content_leftside'],
		height_offset : 0,
		width_exclude: ['#content_leftside'],
		width_include : ['#content_rightside'],
		width_offset : 2
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize({
		height_exclude : ['.treeTab', '.newsearchPlan'],
		height_include : '#treeDemoDiv',
		height_offset : 0
	});
	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize({
		width_include :  ['.titleBar','#statusManger','#contentData'] ,
		width_offset : 0,
		height_exclude : ['.titleBar','#statusManger'] ,
		height_include : '#contentData',
		height_offset : 0
	});
	jQuery('#contentData').mk_autoresize({
		width_include :  ['#stackChart','#statInfo','#passengerStatRouteTitle','#siteTitle'] ,
		width_offset : 30,
		height_exclude : ['#stackChart','#statInfo'] ,
		height_offset : 160
	});
	
}
/**
 * 事件绑定
 */
function bindEvent() {
	
	jQuery('#showVacationDetail').click(function() {		
		getStackChart();
	});
	
	var checked = "last7";
	jQuery('#begintime').click(function() {		
		jQuery('#last7').css({"color":"black","background-color":"#f5f5f5"});
		jQuery('#last30').css({"color":"black","background-color":"#f5f5f5"});
		checked="";
	});
	
	jQuery('#endtime').click(function() {		
		jQuery('#last7').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		jQuery('#last30').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		checked="";
	});	
	
	
	jQuery('#last7').click(function() {
		if($(this).attr("id") == checked) {
			return;
		}
		$(this).css({"color":"white","background-color":"#5CB0EE"});
		jQuery('#last30').css({"color":"black","background-color":"#f5f5f5"});
		checked = $(this).attr("id");
		jQuery('#begintime').val(getPreviousDay(6));
		jQuery('#endtime').val(getToday());
		getStackChart();
		getDataByRoute();
	});
	jQuery('#last30').click(function() {
		if($(this).attr("id") == checked) {
			return;
		}
		$(this).css({"color":"white","background-color":"#5CB0EE"});
		jQuery('#last7').css({"color":"black","background-color":"#f5f5f5"});
		checked = $(this).attr("id");
		jQuery('#begintime').val(getPreviousDay(29));
		jQuery('#endtime').val(getToday());
		getStackChart();
		getDataByRoute();
	});
}
//获取当前日期减去的days天的日期
function getPreviousDay (days) {
	var now = new Date();
    var ls = new Date(now.getTime() - 1000 * 60 * 60 * 24 * days);
    var mon = ls.getMonth() + 1;
    if (mon < 10) {
      mon = '0' + mon;
    }
    var date = ls.getFullYear() + "-" + mon + "-" + ls.getDate();
    return date;
};
//获取今天日期
function getToday () {
	var ls = new Date();
    var mon = ls.getMonth() + 1;
    var date = ls.getDate() < 10 ? '0' + ls.getDate() : ls.getDate();
    if (mon < 10) {
      mon = '0' + mon;
    }
    var date = ls.getFullYear() + "-" + (mon) + "-" + date;
    return date;
};
jQuery(document).ready(function(){
	
	jQuery(window).resize(function(){
		auto_size();
		getStackChart();
		 });
		 jQuery(window).load(function (){
			 auto_size();
		 });
	
	
	//tab切换，不同的需要初始化页数
	$('.alarm_tab a').click(function(){
		$(this).parent().find('a').removeClass('alarm_tabs');
		$(this).addClass('alarm_tabs');
		var type = $(this).attr('type');
	});
	
	bindEvent();
	getStackChart();
	getDataByRoute();
	var objName = new Object(); 
	objName.src="close.png";
	hideSiteInfo(objName);
})