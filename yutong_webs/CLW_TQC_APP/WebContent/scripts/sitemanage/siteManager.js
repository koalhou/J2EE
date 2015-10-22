function  get_chinese_week(day){
		var ret='';
		switch (day) {
		case '1':ret="周一";	break;
		case '2':ret="周二";	break;
		case '3':ret="周三";	break;
		case '4':ret="周四";	break;
		case '5':ret="周五";	break;
		case '6':ret="周六";	break;
		case '7':ret="周日";	break;
		default:ret="";	break;
		}
		return ret;
	}
function get_page_width() {
	return jQuery(document).width();
}
function get_page_height() {
	return jQuery(document).height();
}
function seesee(obj){
	var st_id=$(obj).parent().parent().parent().children('td').eq(0).find('div input').val();
	mapPointFlashObj.addMarkerToMap(st_id,true);
}



var sitManager = function() {
	//this.init();
};
sitManager.prototype = {
	init: function(){
		var that = this;
		jQuery('#gala').flexigrid({
			url: '../sitGrid/stationList.shtml', 
			dataType: 'json',    //json格式
			height: ($('#content').height()<478?478:$('#content').height())-108-89,
			width:350,
			colModel : [ 
				  {display: '<input id="carChoiceAll" name="carChoiceAll" style="_margin-top:-3px;" value="choiceflagAll" type="checkbox" onclick="javascript:sitManagerObj.setOrCancelSelect(this)"/>', name : '', width : 20,process: that.reWriteCheckBox, align: 'center'},
			      {display: '加油站点名称', name : 'site_name', width : 162, sortable : true, align: 'center',escape:true},
			      {display: '服务车辆', name : 'vehicle_count',width : 50, sortable : true, align: 'center'},
			      {display: '', name : 'site_longitude', width : 80, sortable : false, align: 'center',hide:true,toggle:false},
			      {display: '', name : 'site_latitude', width : 70, sortable : false, align: 'center',hide:true,toggle:false},
			      {display: '', name : 'sichen_addr', width : 70, sortable : false, align: 'center',hide:true,toggle:false},
			      {display: '', name : 'organization_id', width : 70, sortable : false, align: 'center',hide:true,toggle:false},
			      {display: '', name : 'organizationName', width : 70, sortable : false, align: 'center',hide:true,toggle:false},
			      {display: '操作', name : '', width : 45,process: that.reWriteBtn, align: 'center'}
			      ],
		    buttons : [
				  {name: '加油站点名称：<input type="text" id="stationName" style="width:85px" maxlength="20" onkeypress="if(event.keyCode==13){sitManagerObj.searchStation();}"/>'},
				  {separator: true},
				  {name: '查询', bclass: 'searchInfo', onpress : that.searchStation},
	  			  {name: '新建', bclass: 'add',onpress : that.addNewStation}
	  	          ],
			     sortname: 'CXST.CREATE_TIME',
			     sortorder: 'desc',  //升序OR降序
			     sortable: true,   //是否支持排序
			     striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
			     usepager :true,  //是否分页
			     resizable: false,  //是否可以设置表格大小
			     useRp: false,    // 是否可以动态设置每页显示的结果数
			     pagestat: ' 共 {total} 条',
			     pagetext: '',
			  	 outof: '/',
			  	 procmsg: '加载中',
			     rp: 10,  //每页显示记录数
			     rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
			     setListChecked:true,
			     showTableToggleBtn: false,
			     showToggleBtn:false,
			     onSubmit:function(){
			    	jQuery("input[name='carChoice']").each(function(){
			 			jQuery("input[name='carChoice']").removeAttr("checked"); 
			 			mapPointFlashObj.cancelMarkerFromMap(jQuery(this).val());
			 		});
			    	 jQuery("input[name='carChoiceAll']").removeAttr("checked"); 
			    	 return true;
			     }
		});
	},
	//设置 选择框
	reWriteCheckBox: function(tdDiv,pid){
		tdDiv.innerHTML = '<input name="carChoice" id="checkbox_'+pid+'" style="_margin-top:-3px;" value="'+pid+'#!#'+sitManagerObj.get_cell_text(pid, 1)+'#!#' + sitManagerObj.get_cell_text(pid, 3) + '#!#'+sitManagerObj.get_cell_text(pid, 4) + '#!#'+sitManagerObj.get_cell_text(pid, 2)+'#!#' +sitManagerObj.get_cell_text(pid, 5)+'#!#'+sitManagerObj.get_cell_text(pid, 6)+'#!#'+sitManagerObj.get_cell_text(pid, 7)+'"  type="checkbox" onclick="sitManagerObj.chooseToMap(this)"/>';
	},
	//设置“查看”
	reWriteBtn: function (tdDiv,pid){
    	tdDiv.innerHTML = "<a href=\"javascript:void(0)\" onclick=\"seesee(this)\">查看</a>";
    },
	//显示加油站
	chooseToMap: function (obj){
		if(jQuery(obj).attr("checked")){
			mapPointFlashObj.addMarkerToMap(jQuery(obj).val());
		}else{
			mapPointFlashObj.cancelMarkerFromMap(jQuery(obj).val());
		}
	},
    
	//查询
	searchStation: function () {
		jQuery("input[name='carChoiceAll']").removeAttr("checked");
		jQuery('#gala').flexOptions({
			//newp: 1,
			params: [{name: 'site.site_name', value: formatSpecialChar(jQuery('#stationName').val())}]	
		});
		jQuery('#gala').flexReload();
	},
	//新建加油站点
	addNewStation: function (){
		$("#sichen_vin").val("");
		mapPointFlashObj.setStationInfo('BE66855BD543127FE0440019BB600AC6',false);
	},
	openAddOilTimer: function(){
		art.dialog.open("dialog_times.shtml", {width: 475, height: 270,id:'addOilTimerWin',lock: true,fixed:true,title:'设置加油时段 '});
	},
	
	openAddLowerVal: function(){

		art.dialog.open("dialog_low.shtml", {width: 485, height: 280,id:'addLowerValWin',lock: true,fixed:true,title: '设置低油量提醒'});

	},

	getSiteConfig: function(){
		jQuery.ajax({
			type: 'POST', 
			url: '../sitGrid/selectOilTimerConfig.shtml',	
			dataType: 'json', 
			data: 
				{}
			,
			success: function(data) {
				if(data.length==1 && data[0].oilRateCheck == "1"){
					if(data[0].addOilRate=='1'){
						$("#amWeeks").html( data[0].startTimeQuantum  + "号  至 " + data[0].endTimeQuantum +"号");
					}else{
						$("#amWeeks").html(get_chinese_week(data[0].startTimeQuantum) + " 至 " + get_chinese_week(data[0].endTimeQuantum));
					}					
				} else {
					$("#amWeeks").html("");
				}
				if(data.length==1 && data[0].oilValueCheck == "1"){
					$("#amLower").html(Number(data[0].lowOilValue * 100) + "%");
				} else {
					$("#amLower").html("");
				}
			}
		});
	},



	get_cell_text: function (pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	},
	setOrCancelSelect: function (obj) {
		if(jQuery(obj).attr("checked")){
			jQuery("input[name='carChoice']").each(function(){
				if(jQuery("input[name='carChoice']").attr("checked")){
					jQuery("input[name='carChoice']").removeAttr("checked"); 
					mapPointFlashObj.cancelMarkerFromMap(jQuery(this).val());
				}else{
					return true;
				}
			});
			
			jQuery("input[name='carChoice']").each(function(){
				jQuery("input[name='carChoice']").attr("checked","true");
//				mapPointFlashObj.addMarkerToMap(jQuery(this).val());
			});
			
			mapPointFlashObj.selectAllStation(jQuery('#stationName').val(),jQuery('#stationProperties').val());
		}else{
			jQuery("input[name='carChoice']").each(function(){
				jQuery("input[name='carChoice']").removeAttr("checked"); 
				mapPointFlashObj.cancelMarkerFromMap(jQuery(this).val());
			});
		}
	},


	openorganizidtree: function (){
		art.dialog.open("selectTreeWin.shtml?vehicle_vin="+encodeURIComponent($('#sichen_vin').val())+"&vehicle_ln="+encodeURIComponent($('#sichen_vehicle').val()),{
			title:"选择车辆",
			width :330,
			height:300,
			id: 'treeOID',
			skin: 'aero',
			limit: true,
			lock: true,
			drag: true,
			fixed: false,
			yesFn: function(iframeWin, topWin){
	        	//对话框iframeWin中对象
	        	var vehiclelnList = iframeWin.document.getElementById('sichen_vehicle').value;//车牌号
	        	var vehicleVinList = iframeWin.document.getElementById('sichen_vin').value;//车架号
	        	$('#sichen_vehicle').val(vehiclelnList);
	        	$('#sichen_vin').val(vehicleVinList);
	        	//当前页面中对象
	            jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_vehicle").val(vehiclelnList);
				jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_vin").val(vehicleVinList);
	    	}
		  
		});
	},
	
	cancelArtWinClosed: function(winID){
		art.dialog.get(winID).close();
	},
	maptoolbarIsshow: function (option){
		var map = document.getElementById("maptoolbar");
		var down = document.getElementById("fudong_down");

		if(option=="up"){
			map.style.display = "none";
			down.style.display = "";
		}
		else if(option=="down"){
			map.style.display = "";
			down.style.display = "none";
		}
	}

};
//页面自适应
function firstrisize(){
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
		height_exclude : ['#leftInfoDiv','#set_item1','#set_item2'],
		height_include : '.bDiv',
		height_offset :95
	});

//	$('.flexigrid').height(($('#content').height()<478?$('#content_rightside').height():$('#content').height())-108);
//	jQuery('#content_leftside').mk_autoresize( {
//		height_exclude : ['#leftInfoDiv','#set_item1','#set_item2'],
//		height_include : '#lefttree',
//		height_offset :5
//	});
	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize( {
		height_include : '#iCenter',
		height_offset : 0,
		width_include : '#iCenter',
		width_offset : 0
	});
	
	
}
function HideandShowControl(){//leftdiv
	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
		jQuery('#middeldiv').show();
		jQuery('#leftdiv').hide();
		jQuery('#content').mk_autoresize( {
			height_include : [ '#content_rightside', '#content_leftside' ],
			height_offset : 0,
			width_exclude: ['#content_leftside'],
			width_include : [ '#content_rightside'],
			width_offset : 22
		});
		jQuery('#content_rightside').mk_autoresize( {
			height_include : '#iCenter',
			height_offset : 0,
			width_include : '#iCenter',
			width_offset : 2
		});
	}else{//隐藏状态
		jQuery('#middeldiv').hide();
		jQuery('#leftdiv').show();
		jQuery('#content').mk_autoresize( {
			height_include : [ '#content_rightside', '#content_leftside' ],
			height_offset : 0,
			width_exclude: ['#content_leftside'],
			width_include : [ '#content_rightside'],
			width_offset : 1
		});
		jQuery('#content_rightside').mk_autoresize( {
			height_include : '#iCenter',
			height_offset : 0,
			width_include : '#iCenter',
			width_offset : 0
		});
	}
}
var sitManagerObj = null;
var mapPointFlashObj = null;
var clusterOptions=new MClusterOptions();
jQuery(document).ready(function(){
	sitManagerObj = new sitManager();
	sitManagerObj.init();
	sitManagerObj.getSiteConfig();
	//设置地图大小
	if (jQuery.browser.msie && jQuery.browser.version < 7.0) {
		document.getElementById("mapbartab").style.width='600px';
	}else{
		document.getElementById("mapbartab").style.width='590px';
		jQuery("#start_time").attr("size",'24');
		jQuery("#end_time").attr("size",'24');
	}

	firstrisize();
	
	mapPointFlashObj = new mapPointFlash();
	mapPointFlashObj.mapInit();
});


