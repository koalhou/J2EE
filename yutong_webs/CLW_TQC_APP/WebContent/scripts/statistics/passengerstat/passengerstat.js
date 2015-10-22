// JavaScript Document
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
var pagenumlength = 1;
var selectVIN = "";
function onClickRoute_check(list, route_id){
	for ( var i = 0; i < list.length; i++) {
		if(i == list.length-1){
			route_id += list[i];
        }
    	else{
    		route_id +=list[i]+",";
       	}	
	}
	if(chart_route_id != route_id){
		chart_route_id = route_id;
	}
	makeChart();
}
//查询线路
function makeChart() {
	PassengerStatDwr.getChart(chart_route_id,treeType, pagenumlength,jQuery('#begintime').val(), jQuery('#endtime').val()+" 23:59:59", jQuery('#user_org_id').val(), backFun_getRoutInfo);
	jQuery("#routempageslipe").css("display","block");
	jQuery("#lineChart").css("display","none");
}
//翻页
function routempagechange(obj) {
	var nowpagelno = parseInt(jQuery("#routempageall").html().substring(1,jQuery("#routempageall").html().length-1));
	var nowpageno = jQuery("#routempageno").val();
	if(nowpageno>0&&nowpageno<=nowpagelno) {
		makeChart();
	}else {
		jQuery("#routempageno").val(pagenumlength);
	}
}
//翻页
function routempagec(obj) {
	var nowpagelno = parseInt(jQuery("#routempageall").html().substring(1,jQuery("#routempageall").html().length-1));
	//这里需要有一个最大页的值 用来显示后一页和最大页
	var nowpageno = pagenumlength;//jQuery("#routempageno").val();
	if(jQuery(obj).attr("id") == 'routempagefirst') {
		if(nowpageno!=1) {
			pagenumlength = 1;
			makeChart();
			jQuery("#routempageno").val(pagenumlength);
		};
	}else if(jQuery(obj).attr("id") == 'routempageprev') {
		if(nowpageno!=1) {
			pagenumlength--;
			makeChart();
			jQuery("#routempageno").val(pagenumlength);
		}else {
			jQuery("#routempageno").val(pagenumlength);
		}
	}else if(jQuery(obj).attr("id") == 'routempagenext') {
		if(nowpageno!=nowpagelno) {
			pagenumlength++;
			makeChart();
			jQuery("#routempageno").val(pagenumlength);
		}else {
			jQuery("#routempageno").val(pagenumlength);
		}
	}else if(jQuery(obj).attr("id") == 'routempagelast') {
		if(nowpageno!=nowpagelno) {
			pagenumlength = nowpagelno;
			makeChart();
			jQuery("#routempageno").val(pagenumlength);
		};
	};
}
///左侧树，查询线路
function searchRoute() {
	routename = jQuery("#search_route_name").val();
	searchTree();
}
//多条线路，带分页
function backFun_getRoutInfo(data) {
	jQuery("#passengerStatRoute").html("");//清空div
	//循环线路
	for(var i = 0; i < data.length; i ++) {
		jQuery("#passengerStatRoute").append("<div id='passengerStatRouteTitle"+i+"' style='height:38px;border-left:1px solid #CCCCCC;border-top:1px solid #CCCCCC;border-right:1px solid #CCCCCC;background-color:#E7E7E7;margin-left:10px;margin-right:10px;margin-top:10px;'><img src='../newimages/statistics/route.png' style='margin-left:10px;margin-top:12px;'/><span style='margin-left:10px;font-size:18px;font-weight:bold;font-family:\"宋体\"'>线路：<span id='routeName'>"+data[i][0].ROUTE_NAME+"</span></span><span style='margin-left:10px;font-size:16px;font-family:\"宋体\"'>总人数：<span id='personNum"+i+"'></span></span></div>");
		jQuery("#passengerStatRoute").append("<div id='passengerStatRouteDetail"+i+"' style='height:96px;line-height:96px;border:1px solid #CCCCCC;background-color:#F5F5F5;margin-left:10px;margin-right:10px;margin-bottom:5px;overflow-x:scroll;overflow-y:hidden;white-space:nowrap;'></div>");
		jQuery("#passengerStatRouteTitle"+i).append("<span style='margin-top:8px;margin-right:20px;float:right; width:82px; height:25px; background:url(../newimages/alarm_btn_bg.gif) no-repeat left top; line-height:25px; text-align:left;'><a href='javascript:void(0);' onclick='viewDetail("+data[i][0].ROUTE_ID+");' style='width:82px; height:25px; display:block; color:#1e1e1e;background:url(../newimages/statistics/viewDetail.png) no-repeat 4px 3px;text-indent:26px;'>查看详情</a></span>");
		//循环站点
		var totalNum = 0;
		for(var j = 0; j < data[i].length; j++) {
			//计算宽度，宽度超出后出现滚动条
			if(data[i].length*140 > jQuery("#passengerStatRouteDetail"+i).width()) {
				jQuery("#passengerStatRouteDetail"+i).css("overflow-x", "scroll");
			} else {
				jQuery("#passengerStatRouteDetail"+i).css("overflow-x", "auto");
			}
			//alert(data[i][j].SITE_ID + "---------" + data[i][j].ROUTE_ID + "+++" + data[i][j].UPNUM);
			if(j == 0) {//第一个站点
				jQuery("#passengerStatRouteDetail"+i).append("<div style='height:66px;display:inline-block;margin-top:14px;'><div style='width:140px;height:22px;line-height:22px;text-align:center;color:black;'>"+data[i][j].UPNUM+"人</div><div style='width:140px;background:url(../mooko-ui/themes/default/images/rm_line_left_bg.png) no-repeat;'><img src='../mooko-ui/themes/default/images/rm_station_blue.png' style='margin-left:58px;vertical-align:top;'/></div><div style='width:140px;height:22px;line-height:22px;text-align:center;color:black;'>"+data[i][j].SITE_NAME+"</div></div>");
			} else if(j >= 0 && j < data[i].length - 1) {//中间的站点
				jQuery("#passengerStatRouteDetail"+i).append("<div style='height:66px;display:inline-block;margin-top:14px;'><div style='width:140px;height:22px;line-height:22px;text-align:center;color:black;'>"+data[i][j].UPNUM+"人</div><div style='width:140px;background:url(../mooko-ui/themes/default/images/rm_line_mid_bg.png) no-repeat;'><img src='../mooko-ui/themes/default/images/rm_station_blue.png' style='margin-left:58px;vertical-align:top;'/></div><div style='width:140px;height:22px;line-height:22px;text-align:center;color:black;'>"+data[i][j].SITE_NAME+"</div></div>");
			} else if(j == data[i].length - 1) {//最后一个站点
				jQuery("#passengerStatRouteDetail"+i).append("<div style='height:66px;display:inline-block;margin-top:14px;'><div style='width:140px;height:22px;line-height:22px;text-align:center;color:black;'>"+data[i][j].UPNUM+"人</div><div style='width:140px;background:url(../mooko-ui/themes/default/images/rm_line_right_bg.png) no-repeat;'><img src='../mooko-ui/themes/default/images/rm_station_blue.png' style='margin-left:58px;vertical-align:top;'/></div><div style='width:140px;height:22px;line-height:22px;text-align:center;color:black;'>"+data[i][j].SITE_NAME+"</div></div>");
			}
			totalNum += data[i][j].UPNUM;
		}
		jQuery("#personNum" + i).html(totalNum);
	}
	writepageallsize(chart_route_id.split(',').length);
}
//单条线路
function backFun_getRoutInfo1(data) {
	jQuery("#passengerStatRoute").html("");//清空div
	//循环线路
	for(var i = 0; i < data.length; i ++) {
		jQuery("#passengerStatRoute").append("<div id='passengerStatRouteTitle"+i+"' style='height:38px;border-left:1px solid #CCCCCC;border-top:1px solid #CCCCCC;border-right:1px solid #CCCCCC;background-color:#E7E7E7;margin-left:10px;margin-right:10px;margin-top:10px;'><img src='../newimages/statistics/route.png' style='margin-left:10px;margin-top:12px;'/><span style='margin-left:10px;font-size:18px;font-weight:bold;font-family:\"宋体\"'>线路：<span id='routeName'>"+data[i][0].ROUTE_NAME+"</span></span><span style='margin-left:10px;font-size:16px;font-family:\"宋体\"'>总人数：<span id='personNum"+i+"'></span></span></div>");
		jQuery("#passengerStatRoute").append("<div id='passengerStatRouteDetail"+i+"' style='height:96px;line-height:96px;border:1px solid #CCCCCC;background-color:#F5F5F5;margin-left:10px;margin-right:10px;margin-bottom:5px;overflow-x:scroll;overflow-y:hidden;white-space:nowrap;'></div>");
		//jQuery("#passengerStatRouteTitle"+i).append("<span style='margin-top:8px;margin-right:20px;float:right; width:82px; height:25px; background:url(../newimages/alarm_btn_bg.gif) no-repeat left top; line-height:25px; text-align:left;'><a href='javascript:void(0);' onclick='viewDetail("+data[i][0].ROUTE_ID+");' style='width:82px; height:25px; display:block; color:#1e1e1e;background:url(../newimages/statistics/viewDetail.png) no-repeat 4px 3px;text-indent:26px;'>查看详情</a></span>");
		//循环站点
		var totalNum = 0;
		for(var j = 0; j < data[i].length; j++) {
			//计算宽度，宽度超出后出现滚动条
			if(data[i].length*140 > jQuery("#passengerStatRouteDetail"+i).width()) {
				jQuery("#passengerStatRouteDetail"+i).css("overflow-x", "scroll");
			} else {
				jQuery("#passengerStatRouteDetail"+i).css("overflow-x", "auto");
			}
			
			if(j == 0) {//第一个站点
				jQuery("#passengerStatRouteDetail"+i).append("<div style='height:66px;display:inline-block;margin-top:14px;'><div style='width:140px;height:22px;line-height:22px;text-align:center;color:black;'>"+data[i][j].UPNUM+"人</div><div style='width:140px;background:url(../mooko-ui/themes/default/images/rm_line_left_bg.png) no-repeat;'><img id='siteImg"+i+j+"' onclick=\"selectSite(this,'"+data[i][j].ROUTE_ID+"','"+data[i][j].SITE_ID+"');\" src='../mooko-ui/themes/default/images/rm_station_blue.png' style='margin-left:58px;vertical-align:top;'/></div><div style='width:140px;height:22px;line-height:22px;text-align:center;color:black;'>"+data[i][j].SITE_NAME+"</div></div>");
			} else if(j >= 0 && j < data[i].length - 1) {//中间的站点
				jQuery("#passengerStatRouteDetail"+i).append("<div style='height:66px;display:inline-block;margin-top:14px;'><div style='width:140px;height:22px;line-height:22px;text-align:center;color:black;'>"+data[i][j].UPNUM+"人</div><div style='width:140px;background:url(../mooko-ui/themes/default/images/rm_line_mid_bg.png) no-repeat;'><img id='siteImg"+i+j+"' onclick=\"selectSite(this,'"+data[i][j].ROUTE_ID+"','"+data[i][j].SITE_ID+"');\" src='../mooko-ui/themes/default/images/rm_station_blue.png' style='margin-left:58px;vertical-align:top;'/></div><div style='width:140px;height:22px;line-height:22px;text-align:center;color:black;'>"+data[i][j].SITE_NAME+"</div></div>");
			} else if(j == data[i].length - 1) {//最后一个站点
				jQuery("#passengerStatRouteDetail"+i).append("<div style='height:66px;display:inline-block;margin-top:14px;'><div style='width:140px;height:22px;line-height:22px;text-align:center;color:black;'>"+data[i][j].UPNUM+"人</div><div style='width:140px;background:url(../mooko-ui/themes/default/images/rm_line_right_bg.png) no-repeat;'><img id='siteImg"+i+j+"' onclick=\"selectSite(this,'"+data[i][j].ROUTE_ID+"','"+data[i][j].SITE_ID+"');\" src='../mooko-ui/themes/default/images/rm_station_blue.png' style='margin-left:58px;vertical-align:top;'/></div><div style='width:140px;height:22px;line-height:22px;text-align:center;color:black;'>"+data[i][j].SITE_NAME+"</div></div>");
			}
			totalNum += data[i][j].UPNUM;
		}
		jQuery("#personNum" + i).html(totalNum);
	}
}
var previousSelect = null;
function selectSite(imgObj, route_id, site_id){
	debugger;
	imgObj.src = "../mooko-ui/themes/default/images/rm_station_yellow.png";
	if(previousSelect) {
		previousSelect.src = "../mooko-ui/themes/default/images/rm_station_blue.png";
	}
	previousSelect = imgObj;
	
	//单个站点折线图
	PassengerStatDwr.getLineChart(route_id, site_id, jQuery('#begintime').val(), jQuery('#endtime').val()+" 23:59:59",{
		callback:function(data){
			debugger;
			var xmlStr = "<chart xAxisName='' rotateYAxisName='0'  yAxisName='人数' yAxisValuesStep='2' yAxisValuesPadding='15'"
                    + "baseFontSize='10' outCnvBaseFontSize='10' labelDisplay='ROTATE' slantLabels='1' rotateLabels='1' rotateValues ='0' valuePosition ='AUTO'"
                    + "alternateHGridColor='FCB541' alternateHGridAlpha='20' bgColor='FFFFFF' borderColor='FFFFFF' divLineColor='FCB541' "
                    + "divLineAlpha='50' canvasBorderColor='666666' baseFontColor='666666' lineColor='FCB541' chartRightMargin='50'>";
            for(var i = 0; i < data.length; i++) {
            	xmlStr += "<set showName='1' showValue='1' label='" + data[i].RQ + "' value='" + data[i].PERSON_COUNT + "' isSliced='1' />";
            }
            var style="<styles>"+
    		"<definition>"+
    			"<style name='MyCaptionFontStyle' type='font' face='Verdana' size='12'/>"+
    		"</definition>"+
    		"<application>"+
    			"<apply toObject='ToolTip' styles='MyCaptionFontStyle' />"+
    		"</application>"+
    		"</styles>";
        	xmlStr = xmlStr + style;
            xmlStr = xmlStr + "</chart>";
            
		 	var chart1 = new FusionCharts("../scripts/fusioncharts/Line.swf?ChartNoDataText=无查询结果", "ChartId", "100%", "100%", "0", "0");
		 	chart1.setDataXML(xmlStr);	
			chart1.render("lineChart");
		}}
	);
}
//查看单条线路
function viewDetail(routeId) {
	PassengerStatDwr.getChart(routeId,treeType, pagenumlength,jQuery('#begintime').val(), jQuery('#endtime').val()+" 23:59:59", jQuery('#user_org_id').val(), backFun_getRoutInfo1);
	jQuery("#routempageslipe").css("display","none");
	jQuery("#lineChart").css("display","block");

	//单条线路折线图
	PassengerStatDwr.getLineChart(routeId, "", jQuery('#begintime').val(), jQuery('#endtime').val()+" 23:59:59",{
		callback:function(data){
			debugger;
			var xmlStr = "<chart xAxisName='' rotateYAxisName='0'  yAxisName='人数' yAxisValuesStep='2' yAxisValuesPadding='15'"
                    + "baseFontSize='10' outCnvBaseFontSize='10' labelDisplay='ROTATE' slantLabels='1' rotateLabels='1' rotateValues ='0' valuePosition ='AUTO'"
                    + "alternateHGridColor='FCB541' alternateHGridAlpha='20' bgColor='FFFFFF' borderColor='FFFFFF' divLineColor='FCB541' "
                    + "divLineAlpha='50' canvasBorderColor='666666' baseFontColor='666666' lineColor='FCB541' chartRightMargin='50'>";
            for(var i = 0; i < data.length; i++) {
            	xmlStr += "<set showName='1' showValue='1' label='" + data[i].RQ + "' value='" + data[i].PERSON_COUNT + "' isSliced='1' />";
            }
            var style="<styles>"+
    		"<definition>"+
    			"<style name='MyCaptionFontStyle' type='font' face='Verdana' size='12'/>"+
    		"</definition>"+
    		"<application>"+
    			"<apply toObject='ToolTip' styles='MyCaptionFontStyle' />"+
    		"</application>"+
    		"</styles>";
        	xmlStr = xmlStr + style;
            xmlStr = xmlStr + "</chart>";
            
		 	var chart1 = new FusionCharts("../scripts/fusioncharts/Line.swf?ChartNoDataText=无查询结果", "ChartId", "100%", "100%", "0", "0");
		 	chart1.setDataXML(xmlStr);	
			chart1.render("lineChart");
		}}
	);
}
function writepageallsize(res) {
	var no = null;
	if(res%3==0)
		no = parseInt(res/3);
	else
		no = parseInt(res/3)+1;
	jQuery("#routempageall").html("共"+no+"页");
}
function auto_size(){
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
		height_offset : 10
	});
	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize({
		height_exclude : '.titleBar' ,
		height_include : '.rmMain',
		height_offset : 0
	});
	//计算线路图区域高度
	jQuery('.rmMain').mk_autoresize({
		height_exclude : ['.rmTitle','.rmRoutePlan','.rmForm'],
		height_include : '#rm_route_big',
		height_offset : 60
	});
	jQuery('#rm_route_big').mk_autoresize({
		width_include : ['#rm_route','#rm_route2','#rm_route3'],
		width_offset : 80
	});
	jQuery('.rmMain').mk_autoresize({
		width_include : ['#rm_route','#rm_route2','#rm_route3'],
		width_offset : 80
	});
	
	jQuery('.rmMain').mk_autoresize({
		width_include : ['#tonqincar','#tonqincar2','#tonqincar3','#mk_routemonitor_route_up','#mk_routemonitor_route_up2','#mk_routemonitor_route_up3'],
		width_offset : 85
	});
}
function exportData() {
	window.open("averagefuelranking/passengerStatExcel.shtml?routeId="+chart_route_id+"&begintime="+jQuery('#begintime').val()+"&endtime="+jQuery('#endtime').val()+" 23:59:59", "_self");
}
/**
 * 事件绑定
 */
function bindEvent() {
	var checked = "today";
	jQuery('#today').click(function() {
		if($(this).attr("id") == checked) {
			return;
		}
		$(this).css({"color":"white","font-weight":"bold","background-color":"#5CB0EE"});
		jQuery('#last7').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		jQuery('#last30').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		checked = $(this).attr("id");
		jQuery('#begintime').val(getToday());
		jQuery('#endtime').val(getToday());
	});
	jQuery('#last7').click(function() {
		if($(this).attr("id") == checked) {
			return;
		}
		$(this).css({"color":"white","font-weight":"bold","background-color":"#5CB0EE"});
		jQuery('#today').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		jQuery('#last30').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		checked = $(this).attr("id");
		jQuery('#begintime').val(getPreviousDay(6));
		jQuery('#endtime').val(getToday());
	});
	jQuery('#last30').click(function() {
		if($(this).attr("id") == checked) {
			return;
		}
		$(this).css({"color":"white","font-weight":"bold","background-color":"#5CB0EE"});
		jQuery('#last7').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		jQuery('#today').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		checked = $(this).attr("id");
		jQuery('#begintime').val(getPreviousDay(29));
		jQuery('#endtime').val(getToday());
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
	//tab切换，不同的需要初始化页数
	$('.alarm_tab a').click(function(){
		$(this).parent().find('a').removeClass('alarm_tabs');
		$(this).addClass('alarm_tabs');
		var type = $(this).attr('type');
	});
	auto_size();
	bindEvent();
})