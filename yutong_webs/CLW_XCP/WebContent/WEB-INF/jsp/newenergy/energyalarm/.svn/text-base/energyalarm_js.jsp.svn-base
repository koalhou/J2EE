<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
/**
 * 左侧树区域显示控制
 */
function HideandShowControl(){//leftdiv
	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
		jQuery('#middeldiv').css("display","block");
		jQuery('#leftdiv').css("display","none");
		//计算中区高度
		jQuery('#content').mk_autoresize( {
			height_include : [ '#content_rightside', '#content_middelside' ],
			height_offset : 0,
			width_exclude: ['#content_middelside'],
			width_include : [ '#content_rightside'],
			width_offset : 1
		});

		//计算右测区域高度
		jQuery('#content_rightside').mk_autoresize( {
			height_exclude : [ '.titleBar'],
			height_include : '#right_main',
			height_offset : 0,
			width_include : [ '.titleBar','#right_main'],
			width_offset : 0
		});
		jQuery('#right_main').mk_autoresize( {
			height_exclude : ['.car-info'],
			height_include : '.table_list',
			height_offset : 70,
			width_include : ['.car-info','.table_list'],
			width_offset : 0
		});		
		jQuery('.table_list').mk_autoresize({
		    height_include: '.bDiv',
		    height_offset: 70, // 该值各页面根据自己的页面布局调整
		    width_include: '.flexigrid',
		    width_offset: 8// 该值各页面根据自己的页面布局调整
		  });			
	}else{//隐藏状态
		jQuery('#middeldiv').css("display","none");
		jQuery('#leftdiv').css("display","block");
		auto_size();
		jQuery(window).mk_autoresize({
			height_include: '#content',
			height_exclude: ['#header', '#footer'],
			height_offset: 0,
			width_include: ['#header', '#content', '#footer'],
			width_offset: 0});
		auto_size();
	}
}

function mytreeonClick(type, treeId){
	jQuery("#isSearch").val("");
	jQuery("#alarmKey").val("");
	if(type=='1'){
		selectCar(treeId);
	}else{
		selectEnt(treeId);	
	}
}

function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}
var pomSel = null;
jQuery(function() {
	pomSel = new newSelect(jQuery("#routeName"),120);
	pomSel.addSelect();
// 	pomSel = $.fn.lqSelect({
//                 renderTo: 'routeName',
//                 enable : true
//             });
	
	getRouteSel();
	var galaList = jQuery('#galaList');
	 galaList.flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width: 1500,
		  colModel : [ 
				{display: 'id', name : 'id', width : 25, sortable : false, align: 'center', hide: true,escape: true},
				{display: 'id', name : 'vehicle_vin', width : 25, sortable : false, align: 'center', hide: true,escape: true},
				{display: '车牌号', name : 'vehicle_ln', width : 120, sortable : true, align: 'center',escape: true},
				{display: '报警信息', name : 'alarmMsg', width : 100, sortable : false, align: 'center',escape: true},
				{display: '报警类型', name : 'alarm_address', width : 80, sortable : false, align: 'center',escape: true},
				{display: '线路名称', name : 'route_name', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: '报警位置', name : 'address', width : 200, sortable : false, align: 'center',escape: true},
		        {display: '时间', name : 'terminal_time', width : 120, sortable : true, align: 'center',escape: true},
		        {display: '处理信息', name : 'record', width : 260, sortable : false, align: 'center',escape: true,process: reWriteLink}
		        
		        ], 
		       sortname: 'terminal_time',
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true,   // 是否允许显示隐藏列
		       onSuccess: function(){
		    	   if(jQuery("#isSearch").val()=="1"){
		    		   var alarmKeys = jQuery("#alarmKey").val();
		    		   jQuery('#galaList tr').each(function(i,n){
						   if(jQuery(n).text().indexOf(alarmKeys)>-1){openWin(alarmKeys,"")}
						});
		    	   }
		       }
	     }); 
	
	 var url = window.location.href;
// 	 if(url.indexOf("isSearch") > -1){
		 searchAlarmList("1");
// 	 }
});

function keyEnter(event){
	var e = event ? event : window.event;
	if(e.keyCode=='13'){
		searchAlarmList();
	}
}

var energyAlarmId = "";
function addRecordSet(){
	var record = jQuery("#processMsgcontent").children("#dataTable").children("tbody").children("tr").eq(0).children("td").eq(0).children("#recordName").val();
	if(record.length > 100){
		tishiShow("处理意见字数超过100个字！");
		return ;
	}
// 	jQuery.blockUI();
	jQuery.ajax({
 		  type: 'POST',  
 		  url: '<s:url value="/energyAlarm/processAlarm.shtml" />',	
 		  data: {record: record, alarmId: energyAlarmId},	
 		 success: function(data) {
 			 if(data.message == "success"){
//  				jQuery("#isSearch").val("");
//  	 			jQuery("#alarmKey").val("");
//  	 			jQuery("#alarmId").val("");
 				//searchAlarmList();
// 	 			jQuery.unblockUI();
	 			tishiShow("报警意见处理成功！");
	 			cancelArt();
	 			var url = window.location.href;
// 	 			 if(url.indexOf("isSearch") > -1){
	 				searchAlarmList();
	 				 //document.location.href="<s:url value='/energyAlarm/energyAlarmBrows.shtml'/>";
// 	 			 }else{
// 	 				searchAlarmList();
// 	 			 }
 			 } else {
//  				jQuery.unblockUI();
 				tishiShow("报警意见处理失败！");
 			 }
	      },
		  error:function (){
			  tishiShow("报警处理失败！");
// 	    	jQuery.unblockUI();  
	      }
  	});
}

function getRouteSel(){
	jQuery.ajax({
		  type: 'POST',  
		  url: '<s:url value="/routeGrid/getUserRouteFind.shtml" />',	
		  data: {},	
		 success: function(data) {
			 if(data.message == "success"){
	 			var routeList = data.list;
	 			for(var i = 0,len = routeList.length; i < len; i++){
	 				var objs = routeList[i];
	 				pomSel.addOption(objs.route_id,objs.route_name);
	 			}
	 			pomSel.seBindOption(function(){searchAlarmList();});
// 	 			pomSel.setSelValue(list,"route_id","route_name",function(){searchAlarmList();});
			 } else {
				 
			 }
	      },
		  error:function (){
	      }
	});
}


function reWriteLink(tdDiv,pid,row){
	var tdV = "";
	var record = row.cell[8];
	var alarmId = row.cell[0];
	
	if( record != '' && record!='null' && record != null && record.length != 0 && record != undefined){
		tdV = "<a href=\"javascript:void(0);\" onclick=\"openWin('"+alarmId+"','"+record+"')\">"+record+"</a>";
	} else {
		tdV = "<a href=\"javascript:void(0);\" onclick=\"openWin('"+alarmId+"','')\">点击处理</a>";
	}
	return tdV;	
}

function openWin(alarmId,record){
	jQuery("#recordName").html(record);
	energyAlarmId = alarmId;
	art.dialog({
		id:'processMsg',
		title:'处理意见',
		lock:true,
		width:500,
		height:240,
		fixed:true,
		content:jQuery("#stationInfoDiv").html(),
		initFn:function(){
			
		},
		closeFn:function(){
			//cancelArt();
		}
	}); 
}

/* 关闭dialog 窗口 */
function cancelArt(){
	energyAlarmId = "";
// 	jQuery("#isSearch").val("");
// 	jQuery("#alarmKey").val("");
// 	jQuery("#alarmId").val("");
	art.dialog.get('processMsg').close();
}

function tishiShow(info){
	tishiHide();
	hideshowresultDiv(0);
	document.getElementById("inforeault").innerHTML=info;
}
function tishiHide(){
	window.setTimeout("hideshowresultDiv(1)",1500);
}

function hideshowresultDiv(flag){
	if(flag==1){
		jQuery('#Layer1').css('display','none');
	}
	else if(flag==0){
		jQuery('#Layer1').css('display','block');
	}	
}

function calcColumn(persent, minwidth, offset) {
    var pagewidth = 0;
    
    var width = 0;
    if(jQuery.browser.msie){ 
       pagewidth = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
    } else { 
       pagewidth = self.innerWidth; 
    } 
    if(offset > 0) {
       width = pagewidth - offset;
    } else {
       width = pagewidth;
    }
    return width*persent < minwidth ? minwidth : width*persent;
}



jQuery(function() {
	auto_size();	
	jQuery(window).mk_autoresize({
		height_include: '#content',
		height_exclude: ['#header', '#footer'],
		height_offset: 0,
		width_include: ['#header', '#content', '#footer'],
		width_offset: 0});
	auto_size();
});

function auto_size(){
	jQuery(window).mk_autoresize({
		min_width: 1170
	  });
	//计算中区高度
	jQuery('#content').mk_autoresize( {
		height_include : [ '#content_rightside', '#content_leftside' ],
		height_offset : 0,
		width_exclude: ['#content_leftside'],
		width_include : [ '#content_rightside'],
		width_offset : 1
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize( {
		height_exclude : [ '#leftInfoDiv', '.newsearchPlan'],
		height_include : '#lefttree',
		height_offset : 10

	});

	//计算树区域高度
	//jQuery('#lefttree').mk_autoresize( {
	//	height_include : '#treeDemo',
	//	height_offset : 20
	//});


	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize( {
		height_exclude : [ '.titleBar'],
		height_include : '#right_main',
		height_offset : 0,
		width_include : [ '.titleBar','#right_main'],
		width_offset : 0
	});
	jQuery('#right_main').mk_autoresize( {
		height_exclude : ['.car-info'],
		height_include : '.table_list',
		height_offset : 0,
		width_include : ['.car-info','.table_list'],
		width_offset : 0
	});		
	jQuery('.table_list').mk_autoresize({
	    height_include: '.bDiv',
	    height_offset: 70, // 该值各页面根据自己的页面布局调整
	    width_include: '.flexigrid',
	    width_offset: 8// 该值各页面根据自己的页面布局调整
	  });
}


//查询
function searchAlarmList(flag) {
	var filterFlag = "";
	if(jQuery("#filterFlag").attr("checked")){
		filterFlag = "1";
	}
	if(flag != "1"){
		jQuery("#isSearch").val("");
		jQuery("#alarmKey").val("");
		jQuery("#alarmId").val("");
	}
		jQuery('#galaList').flexOptions({
		newp: 1,
		url: '<s:url value="/energyAlarm/energyAlarmList.shtml" />',
		params: [{name: 'vehicleVin', value: finalVIN },
		         {name: 'vehicleln',value: jQuery("#vehicleln").val()},
			     {name: 'startTime', value: jQuery("#startTime").val()},
			     {name: 'endTime', value: jQuery("#endTime").val()},
			     {name: 'alarmMsg',value: jQuery("#alarmMsg").val()},
			     {name: 'routeName',value: jQuery("#routeName").val()=="--请选择--"?"":jQuery("#routeName").val()},
			     {name: 'organization_id',value: finalENT},
			     {name: 'alarmKey',value: jQuery("#alarmKey").val()},
			     {name: 'car_state',value: filterFlag}]
	});
	jQuery('#galaList').flexReload();
}

function exportData(){
		if(confirm("确定要导出报警明细吗？")) {
			jQuery("#organization_id").val(finalENT);
			jQuery("#vehicleVin").val(finalVIN);
			document.getElementById('vehiclestatus_form').action="<s:url value='/energyAlarm/exportEnergyalarm.shtml' />";
			document.getElementById('vehiclestatus_form').submit();
		}
}



//选择车辆后，刷新车辆运行日志数据
var finalVIN = "";
var finalENT = "";
function selectCar(radioObj){
	finalVIN = radioObj;
	finalENT = "";
	searchAlarmList();
}
function selectEnt(radioObj){
	finalVIN = "";
	finalENT = radioObj;
	searchAlarmList();
}
//点击页签
function changeChoose(id){
	searchCarRunList();			
}		

function pickedstarttime(){
	var date1 = document.getElementById('startTime').value + ":00";
	var date2 = document.getElementById('endTime').value + ":00";
    var line_start_time=date1.replace(/-/g,"/");
    var line_end_time=date2.replace(/-/g,"/");
    
	var startdate=new Date(line_start_time);
	var enddate=new Date(line_end_time);
	var iDays = parseInt((enddate - startdate) / 1000 / 60 / 60 / 24);
	
	if(iDays < 0 || iDays > 6){
		enddate.setTime(startdate.getTime() + 1000*60*60*24*7);
	}
	var str1 = startdate.pattern("yyyy/MM/dd");
	var str2 = enddate.pattern("yyyy/MM/dd");
	//if(str1 != str2){
		enddate = new Date(str1+" 23:59");
	//}
	$dp.$('endTime').value=enddate.pattern("yyyy-MM-dd HH:mm") ;
}
function pickedendtime(){
	var date1 = document.getElementById('startTime').value + ":00";
	var date2 = document.getElementById('endTime').value + ":00";
    var line_start_time=date1.replace(/-/g,"/");
    var line_end_time=date2.replace(/-/g,"/");

	var startdate=new Date(line_start_time);
	var enddate=new Date(line_end_time);
	var iDays = parseInt((enddate - startdate) / 1000 / 60 / 60 / 24);

	if(iDays < 0 || iDays > 6){
		startdate.setTime(enddate.getTime() - 1000*60*60*24*7);
	}
// 	var str1 = startdate.pattern("yyyy/MM/dd");
// 	var str2 = enddate.pattern("yyyy/MM/dd");
// 	if(str1 != str2){
// 		startdate = new Date(str2+" 00:00:00");
// 	}
	$dp.$('startTime').value=startdate.pattern("yyyy-MM-dd HH:mm") ;
}
/**      
* 对Date的扩展，将 Date 转化为指定格式的String      
* 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符      
* 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)      
* eg:      
* (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423      
* (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
* (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
* (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
* (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
*/        
Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "\u65e5",         
    "1" : "\u4e00",         
    "2" : "\u4e8c",         
    "3" : "\u4e09",         
    "4" : "\u56db",         
    "5" : "\u4e94",         
    "6" : "\u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}
</script>
