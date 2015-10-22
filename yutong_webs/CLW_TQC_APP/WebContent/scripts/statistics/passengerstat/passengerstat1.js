//宇通通勤车、一厂、二厂，组织ID，生产环境需要根据情况修改,已拉出来在配置文件中设置
//var yutong_enterprise_id = "7287dce9-46b5-4b7c-af89-973f439ab4ed";
//var yichang_enterprise_id = "b4d34bd6-4015-400e-992a-316e99724976";
//var erchang_enterprise_id = "9d12cce5-11f9-4b4d-a499-e25d20add636";
var yutong_enterprise_id = "";
var yichang_enterprise_id = "";
var erchang_enterprise_id = "";
var route_class = "";//默认早班线路
var holidayList=""; //节假日信息




function auto_size(){
	jQuery('#content').mk_autoresize( {
		height_include :'#content_rightside',
		height_offset : 0,	
		width_include :'#content_rightside',
		width_offset :0
	});	

	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize( {
		height_exclude : '.titleBar',
		height_include : '#statusManger',
		height_offset : 0,
		width_include : [ '#statusManger','.titleBar'],
		width_offset : 0
	});	

	jQuery('#statusManger').mk_autoresize( {
		height_exclude : ['.showVacation','.list-area'],
		height_offset : 0,
		width_include : ['.list-area','.showVacation'],
		width_offset : 0
	});	
	
}


function auto_size22(){
	jQuery('#wrapper').mk_autoresize({
		height_exclude :['#header','#footer'],
		height_include :['#content'],
		height_offset : 0,		
		width_include : ['#header',  '#footer','#content'],
		width_offset : 0
	});
	
	
	jQuery('#content').mk_autoresize({		
		height_include :['#content_rightside'],
		height_offset : 20,		
		width_include : ['#content_rightside'],
		width_offset : 0
	});
	
	jQuery('#content_rightside').mk_autoresize({
		height_exclude : ['.titleBar' ,'#statusManger'],
		height_include : '#contenData',
		height_offset : 0,
		width_include : ['.titleBar','#statusManger','#contenData'],
		width_offset : 0
	});
	
	
	jQuery('#statusManger').mk_autoresize({
		height_include : '.list-area',
		height_offset : 0,
		width_include : [ '.list-area'],
		width_offset : 0
	});	
	
	jQuery('#contenData').mk_autoresize({
		height_exclude : ['#lineChart','#statInfo'],	
		height_offset : 0,
		width_include : ['#lineChart','#statInfo'],
		width_offset : 20
	});
}



function checkDate() {
	
	
	var startTime=jQuery("#begintime").val();
	var endTime=jQuery("#endtime").val();
	var startDate= new Date(Date.parse(startTime.replace(/-/g,"/"))); 
	var endDate= new Date(Date.parse(endTime.replace(/-/g,"/"))); 
	
	if (endDate.getTime()-startDate.getTime()>1000*60*60*24*35-1){
		jQuery('#vehicle_codeDesc').text("日期区间不能超过35天!");
		 setTimeout(function(){jQuery('#vehicle_codeDesc').text("")},3000);		
		return false;
	 }
	getLineChartFirstPage();
	getDataByOrganization();
	
}

//第一个页面折线图
function getLineChartFirstPage() {	
	var showVacation = "";
	if(jQuery('#showVacation')[0].checked) {
		showVacation = "1";
	} else {
		showVacation = "";
	}
	PassengerStatDwr.getLineChartFirstPage(yutong_enterprise_id, yichang_enterprise_id, erchang_enterprise_id, jQuery('#begintime').val(), jQuery('#endtime').val()+" 23:59:59", showVacation, route_class, backFun_getLineChartFirstPage);
}

function backFun_getHolidayInfo(data){
	for(var i = 0; i < data.length; i++){
		holidayList+=data[i].VACATION_DAY+",";
	}
}

function backFun_getLineChartFirstPage(data) {
	
	PassengerStatDwr.getHolidayInfo(jQuery('#begintime').val(), jQuery('#endtime').val()+" 23:59:59",backFun_getHolidayInfo);
	
	
	var xmlStr = "<chart canvasPadding='10'  yAxisName='人次' yAxisMaxValue='5'  labelDisplay='ROTATE' slantLabels='1' bgColor='F7F7F7, E9E9E9'  numVDivLines='10' divLineAlpha='30' labelPadding ='10' yAxisValuesPadding ='10' showValues='0' valuePosition='auto'>";
    var categories = "<categories>";
    var dataset = "";
    var organization = "";
    var isAdd = true;
    
    var arrayFlag=0;
    
    
    dataset += "<dataset renderAs='Line' color='7FF00'   seriesname='' >";
    for(var i = 0; i < data.length/3; i++){
    	if(data.length>30){
    		if(i%2==0){
    			categories += "<category label='"+data[i].RQ+"'/>";
    		}else{
    			categories += "<category label=''/>";
    		}
    	}else{
    		categories += "<category label='"+data[i].RQ+"'/>";
    	}
    		
    	
    	
    	if(holidayList.indexOf(data[i].RQ)>-1){
    		dataset += "<set anchorBgColor='FF8C00' anchorBorderColor='FF8C00' toolText='"+data[i].RQ+","+data[i].PERSON_COUNT+"' value='"+data[i].PERSON_COUNT+"'/>";
    	}else{
    		dataset += "<set toolText='"+data[i].RQ+","+data[i].PERSON_COUNT+"' value='"+data[i].PERSON_COUNT+"'/>";
    	}
		
		
		arrayFlag++;
    }
    dataset += "</dataset>";
    
    dataset += "<dataset renderAs='Line' color='8A2BE2'  seriesname='' >";
    for(var i = arrayFlag; i < (data.length/3)*2; i++){
    	if(holidayList.indexOf(data[i].RQ)>-1){
    		dataset += "<set anchorBgColor='FF8C00' anchorBorderColor='FF8C00' toolText='"+data[i].RQ+","+data[i].PERSON_COUNT+"'  value='"+data[i].PERSON_COUNT+"'/>";
    	}else{
    		dataset += "<set toolText='"+data[i].RQ+","+data[i].PERSON_COUNT+"' value='"+data[i].PERSON_COUNT+"'/>";
    	}
		
		arrayFlag++;
    }
    dataset += "</dataset>";
    
    dataset += "<dataset renderAs='Line' color='00BFFF'  seriesname='' >";
    for(var i = arrayFlag; i < data.length; i++){		
    	if(holidayList.indexOf(data[i].RQ)>-1){
    		dataset += "<set anchorBgColor='FF8C00' anchorBorderColor='FF8C00' toolText='"+data[i].RQ+","+data[i].PERSON_COUNT+"' value='"+data[i].PERSON_COUNT+"'/>";
    	}else{
    		dataset += "<set toolText='"+data[i].RQ+","+data[i].PERSON_COUNT+"' value='"+data[i].PERSON_COUNT+"'/>";
    	}
		arrayFlag++;
    }
   
   
    categories += "</categories>";
    dataset += "</dataset>";
    xmlStr += categories;
    xmlStr += dataset;
    xmlStr = xmlStr + "</chart>";
 	var chart1 = new FusionCharts("../scripts/fusioncharts/MSLine.swf" , "ChartId", "100%", "100%", "0", "0");
 	chart1.setDataXML(xmlStr);	
	chart1.render("lineChart");	
	//清空节假日信息
     holidayList="";
}
function getDataByOrganization() {	
	var showVacation = "";
	if(jQuery('#showVacation')[0].checked) {
		showVacation = "1";
	} else {
		showVacation = "";
	}
	PassengerStatDwr.getDataByOrganization(yutong_enterprise_id, yichang_enterprise_id, erchang_enterprise_id, jQuery('#begintime').val(), jQuery('#endtime').val()+" 23:59:59", showVacation, route_class, backFun_getDataByOrganization);
}
function backFun_getDataByOrganization(data) {	
	debugger;
	jQuery('#banci1').html(data[0].BANCI);
	jQuery('#zuowei1').html(data[0].ZUOWEI);
	jQuery('#renci1').html(data[0].RENCI);
	
	jQuery('#banci2').html(data[1].BANCI);
	jQuery('#zuowei2').html(data[1].ZUOWEI);
	jQuery('#renci2').html(data[1].RENCI);
	
	jQuery('#banci3').html(data[2].BANCI);
	jQuery('#zuowei3').html(data[2].ZUOWEI);
	jQuery('#renci3').html(data[2].RENCI);
}
//导出Excel
function exportData() {	
	var startTime=jQuery("#begintime").val();
	var endTime=jQuery("#endtime").val();
	var startDate= new Date(Date.parse(startTime.replace(/-/g,"/"))); 
	var endDate= new Date(Date.parse(endTime.replace(/-/g,"/"))); 
	
	if (endDate.getTime()-startDate.getTime()>1000*60*60*24*35-1){
		jQuery('#vehicle_codeDesc').text("日期区间不能超过35天!");
		 setTimeout(function(){jQuery('#vehicle_codeDesc').text("")},3000);		
		return false;	
	 }
	window.open("averagefuelranking/passengerStatExcel.shtml?begintime="+jQuery('#begintime').val()+"&endtime="+jQuery('#endtime').val()+" 23:59:59&route_class="+route_class, "_self");
}
//线路详情页面
function viewDetail() {
	window.location.href = "passengerStatDetail.shtml";
}
/**
 * 事件绑定
 */
function bindEvent() {
	
	var checked = "last7";
	
	jQuery('#showVacation').click(function() {		
		getLineChartFirstPage();		
	});
	
	jQuery('#begintime').click(function() {		
		jQuery('#last7').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		jQuery('#last30').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});		
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
		jQuery('#last30').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		checked = $(this).attr("id");
		jQuery('#begintime').val(getPreviousDay(6));
		jQuery('#endtime').val(getToday());
		getLineChartFirstPage();
		getDataByOrganization();
	});
	jQuery('#last30').click(function() {
		if($(this).attr("id") == checked) {
			return;
		}
		$(this).css({"color":"white","background-color":"#5CB0EE"});
		jQuery('#last7').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		checked = $(this).attr("id");
		jQuery('#begintime').val(getPreviousDay(29));
		jQuery('#endtime').val(getToday());
		getLineChartFirstPage();
		getDataByOrganization();
	});
	
	var checked = "all";
	jQuery('#all').click(function() {
		if($(this).attr("id") == checked) {
			return;
		}
		$(this).css({"color":"white","background-color":"#5CB0EE"});
		jQuery('#morning').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		jQuery('#night').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		checked = $(this).attr("id");
		route_class = "";//全部
		getLineChartFirstPage();
		getDataByOrganization();
	});
	jQuery('#morning').click(function() {
		if($(this).attr("id") == checked) {
			return;
		}
		$(this).css({"color":"white","background-color":"#5CB0EE"});
		jQuery('#night').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		jQuery('#all').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		checked = $(this).attr("id");
		route_class = "0";//早班线路
		getLineChartFirstPage();
		getDataByOrganization();
	});
	jQuery('#night').click(function() {
		if($(this).attr("id") == checked) {
			return;
		}
		$(this).css({"color":"white","background-color":"#5CB0EE"});
		jQuery('#morning').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		jQuery('#all').css({"color":"black","font-weight":"normal","background-color":"#f5f5f5"});
		checked = $(this).attr("id");
		route_class = "1";//晚班线路
		getLineChartFirstPage();
		getDataByOrganization();
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
		getLineChartFirstPage();
		 });
		 jQuery(window).load(function (){
			 auto_size();
		 });
	
	
	bindEvent();
	//jQuery('#statInfoDesc').width(jQuery('#statInfo').width()-150);
	getLineChartFirstPage();
	getDataByOrganization();
	PassengerStatDwr.getHolidayInfo(jQuery('#begintime').val(), jQuery('#endtime').val()+" 23:59:59",backFun_getHolidayInfo);
})