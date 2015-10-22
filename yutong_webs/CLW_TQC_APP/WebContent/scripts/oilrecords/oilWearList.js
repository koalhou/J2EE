function get_page_width() {
	return jQuery(document).width();
}
function get_page_height() {
	return jQuery(document).height();
}
var ftlyOilRecordsHistory = function(o) {
	this.init();
};
function checkDate(start_time,end_time){
	if($.trim(start_time).length==0||$.trim(end_time).length==0){
		alert("查询时间不能为空");
		return false;
	}
	if(string2Date(start_time)>string2Date(end_time)){
		alert("开始时间应早于结束时间！");
		return false;
	}
//	if(GetDateDiff(start_time,end_time,'day')>31){
//		alert("查询时间段应控制在一个月内！");
//		return false;
//	}
	return true;
}
function getSelectedCars(){
	var tempList = new Array();
	var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	for ( var i = 0, len = nodes.length; i < len; i++) {
		if ("pIcon" != nodes[i].iconSkin) {
			tempList.push(nodes[i].id);
		}
	}
	return tempList.toString();
}
ftlyOilRecordsHistory.prototype = {
		/**页面初始化时间 */
	init:function (){
		var curDate = new Date();
		var dd = new Date();
		dd.setDate(curDate.getDate());
		jQuery("#getVehicleStartDate").val(date2ShortString(getLastMonthTomorrow(),0));
		jQuery("#getVehicleEndDate").val(date2ShortString(dd));
		dd.setDate(curDate.getDate()-1);
		jQuery("#getVehicleStartTime").val(date2ShortString(getLastMonthTomorrow(),0));
		jQuery("#getVehicleEndTime").val(date2ShortString(dd));
	},
	/** 查询车辆偷(加)油信息 **/
	searchOilWearDetailList:function () {
		
		finalVINs = getSelectedCars();
		
		if(finalVINs.length==0){
			$("#wearGrid").find("tbody").children().remove();
//    		return false;
			finalVINs[0] = "00000000000000000000000000000000";
			//return;
    	}
		var start_time = jQuery('#getVehicleStartTime').val();
    	var end_time = jQuery('#getVehicleEndTime').val();
    	if(!checkDate(start_time,end_time)){
    		return false;
    	}
    	
    	var vehicleList = finalVINs;
    	jQuery('#wearGrid').flexOptions({
			newp: 1,
			params: [
					 {name: 'vehicle_vin', 	value: vehicleList.toString()}, 
					 {name: 'start_time', 	value: start_time+' 00:00:00' }, 
					 {name: 'end_time', 	value: end_time	+' 23:59:59'}
			 ]
		});
		jQuery('#wearGrid').flexReload();
	},
	
	/** 油耗列表 **/
	wear:function() {
		var accessFlag = '0';
		
		var startDate =jQuery("#getVehicleStartTime").val();
		var endDate =jQuery("#getVehicleEndTime").val();
		
    	if(!checkDate(startDate,endDate)){
    		return false;
    	}
		
		var addOilList = jQuery('#wearGrid');
		
		addOilList.flexigrid({
			url: 'getOilWearList.shtml',
			params: [
				 {name: 'vehicle_vin', value: finalVINs.join(",")}, 
				 {name: 'start_time', value: startDate+' 00:00:00' },
		 		 {name: 'end_time', value: endDate+' 23:59:59'}
				],
			dataType: 'json',
			height: accessFlag==0?get_page_height()-550:get_page_height()-415,
			width: get_page_width()-$('#leftdiv').width(),
			colModel : [
			    		{display: '序号', height:15,name : '', width : 30, sortable : false, align: 'center'},
			    		{display: '班车号', name : 'vehicle_code',height:30, width : 80, sortable : true, align: 'center'},
			    		{display: '车牌号', name : 'vehicle_ln',height:30, width : 110, sortable : true, align: 'center'},
			    		{display: '燃油消耗(L)', name : 'usedOil', width : 110, sortable : true, align: 'center',process: ftlyOilRecordsObj.converFloat},
			    		{display: '行驶里程(KM)', name : 'mileage', width : 110, sortable : true, align: 'center',process: ftlyOilRecordsObj.converFloat},
			    		{display: '燃油费用(元)', name : 'totalOilPrice', width : 110, sortable : true, align: 'center',process: ftlyOilRecordsObj.converFloat}
			    	],
			    	sortname:'totalOilPrice',
			    	sortorder: 'desc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
					autoload:true,
			    	useRp: true,
			    	rp:20,
			    	gridId:'wearFlexGrid',
					rpOptions : [10,20,50,100],// 可选择设定的每页结果数
			    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		});
	},
	converFloat: function (tdDiv,pid){
			var datas = tdDiv.innerHTML;
			var strTmp = datas.split(".")[0];
			if(strTmp.length == 0){
				return tdDiv.innerHTML = "0"+datas;
			}
		return tdDiv.innerHTML = datas;
	},
	exportOilWearExcel:function (){
    	if($("#wearGrid").find("tbody").find("tr").length == 0){
    		alert("没有油耗统计信息!");
    		//$("#wearGrid").find("tbody").children().remove();
    		return false;
    	}
    	var startD = jQuery('#getVehicleStartTime').val();
    	var endD = jQuery('#getVehicleEndTime').val();
    	if(!checkDate(startD,endD)){
    		return false;
    	}
    	$('#vehicle_vin_wear').val(finalVINs);
    	jQuery("#oilWear_form").submit();
    },
    /**  以下为变动记录 **/
    /** 查询车辆偷(加)油信息 **/
	searchOilRecordsDetailList:function () {
		
		finalVINs = getSelectedCars();
		
		if(finalVINs.length==0){
    		//alert("请选择车辆");
			$("#addOil").find("tbody").children().remove();
    		//return false;
			finalVINs[0] = "0000000000000000000000000000000";
			//return;
    	}
		var start_time = jQuery('#getVehicleStartDate').val();
    	var end_time = jQuery('#getVehicleEndDate').val();
    	if(!checkDate(start_time,end_time)){
    		return false;
    	}
    	
    	var vehicleList = finalVINs;
    	jQuery('#addOil').flexOptions({
			newp: 1,
			params: [
					 {name: 'oilbox_state', value: jQuery('#oilboxState').val()}, 
					 {name: 'vehicle_vin', 	value: vehicleList}, 
					 {name: 'start_time', 	value: start_time +' 00:00:00'}, 
					 {name: 'end_time', 	value: end_time+' 23:59:59'	}
			 ]
		});
		jQuery('#addOil').flexReload();
	},
	
	/** 油耗异动(加油记录)列表加载 **/
	change:function() {
		var accessFlag = '0';
		
		var vehicleNo =jQuery("#getVehicleNo").val();
		var oilboxState =jQuery('#oilboxState').val();
		var vin = finalVINs;
		var startDate =jQuery("#getVehicleStartDate").val();
		var endDate =jQuery("#getVehicleEndDate").val();
		
		if(!checkDate(startDate,endDate)){
    		return false;
    	}
		
		var addOilList = jQuery('#addOil');
		
		addOilList.flexigrid({
			url: 'ftlyInfoNew/getStealOilList.shtml',
			params: [
		   		 
		   		{name: 'oilbox_state', 	value:oilboxState },
				 {name: 'vehicle_ln', value: vehicleNo}, 
				 {name: 'start_time', value: startDate+' 00:00:00' },
		 		 {name: 'end_time', value: endDate+' 23:59:59'}
				],
			dataType: 'json',
			height: accessFlag==0?get_page_height()-550:get_page_height()-415,
			width: get_page_width()-$('#leftdiv').width(),
			onSuccess: function() {//获取数据结束时执行  
				$('#addOil tr').each(function(i,n){
					if($(n).text().indexOf('骤减')>-1){$(n).find('td').css({ color: "red" });}
					var t=$(n).children('td').eq(7);t.attr('title',t.text());
				});
		    },  
			colModel : [
			    		{display: '序号', height:15,name : '', width : 30, sortable : false, align: 'center'},
			    		{display: '班车号', name : 'vehicle_code',height:30, width : 80, sortable : true, align: 'center'},
			    		{display: '车牌号', name : 'vehicle_ln',height:30, width : 110, sortable : true, align: 'center'},
			    		{display: '变动前油量(L)', name : 'oilbox_level', width : 110, sortable : true, align: 'center'},
			    		{display: '变动油量(L)', name : 'add_oill', width : 110, sortable : true, align: 'center'},
			    		{display: '变动后油量(L)', name : 'oilbox_mass', width : 110, sortable : true, align: 'center'},
			    		{display: '变动形式', name : '', width : 60, sortable : false, align: 'center'},
			    		{display: '变动时间', name : 'report_time', width : 120, sortable : true, align: 'center'},
			    		{display: '位置', name : 'zonename',width : 320, sortable : false, align: 'center'}
			    	],
			    	sortname:'report_time',
			    	sortorder: 'desc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
					autoload:false,
			    	useRp: true,
			    	rp:20,
			    	gridId:'addOilRecordsGrid',
					rpOptions : [10,20,50,100],// 可选择设定的每页结果数
			    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		});
		
	},
    exportAddExcel:function (){
//    	if(finalVINs.length==0){
//    		//alert("请选择车辆");
//    		//$("#addOil").find("tbody").children().remove();
//    		return false;
//    	}   	
    	if($("#addOil").find("tbody").find("tr").length == 0){
    		alert("没有变动记录信息!");
    		return false;
    	}
    	var start_time = jQuery('#getVehicleStartDate').val()+' 00:00:00';
    	var end_time = jQuery('#getVehicleEndDate').val()+' 23:59:59';
    	
    	if(!checkDate(start_time,end_time)){
    		return false;
    	}
    	$('#vehicle_vin').val(finalVINs);
    	//jQuery('#getVehicleStartDate').val(start_time);
    	//jQuery('#getVehicleEndDate').val(end_time);
    	jQuery("#ftlyInfoDetails_form").submit();
    }
};


function mytreeonClick(carList){
	//保存vehicle_vin信息
	finalVINs = new Array();
	var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	 for(var i = 0, len = nodes.length; i < len; i++) {
			 if("pIcon" != nodes[i].iconSkin) {
			 finalVINs.push(nodes[i].id);
		 }
	}
	 
	 var tabsFlag = $('.alarm_tab a[class=alarm_tabs]').attr("flag");
	 if(tabsFlag == "1"){
		 ftlyOilRecordsObj.searchOilRecordsDetailList();
	 } else {
		 ftlyOilRecordsObj.searchOilWearDetailList();
	 }
	
}
//页面自适应
function firstrisize(){
	
	jQuery(window).mk_autoresize({
	     height_include: '#content',
	     height_exclude: ['#header', '#footer'],
	     height_offset: 0,
	     width_include: ['#header', '#content', '#footer'],
	     width_offset: 0
	  });
	
	//计算中区高度
	jQuery('#content').mk_autoresize( {
		height_include : [ '#content_rightside', '#content_leftside','#middeldiv' ],
		height_offset : 5
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize( {
		height_exclude : ['#leftInfoDiv','#newsearchPlan'],
		height_include : '#lefttree',
		height_offset :8

	});

	//计算左测区域高度
//	jQuery('#sleftline').mk_autoresize( {
//		width_exclude : ['#leftInfoDiv','#newsearchPlan'],
//		height_include : '#lefttree',
//		height_offset : 15
//
//	});
	
	jQuery('#content').mk_autoresize({
	    width_exclude:'#content_leftside',
	    width_include: '.flexigrid',
	    width_offset: 1// 该值各页面根据自己的页面布局调整
	  });

	 
	jQuery('#content_rightside').mk_autoresize({
	    height_include: '.bDiv',
	    height_offset: 180 // 该值各页面根据自己的页面布局调整
	  });

}

function firstrisize2(){
//	jQuery("#content_rightside").css("width",jQuery(window).width() - 26);
//	jQuery("#centerContent").css("width",jQuery("#content_rightside").width());
//	jQuery("#alarm_tab").css("width",jQuery("#content_rightside").width());
//	jQuery("#changeTable").css("width",jQuery("#content_rightside").width());
	
	jQuery(window).mk_autoresize({
	     height_include: '#content',
	     height_exclude: ['#header', '#footer'],
	     height_offset: 0,
	     width_include: ['#header', '#content', '#footer'],
	     width_offset: 0
	  });
	
	//计算中区高度
	jQuery('#content').mk_autoresize( {
		height_include : [ '#content_rightside', '#content_leftside','#middeldiv' ],
		height_offset : 5
	});
	//计算左测区域高度
//	jQuery('#sleftline').mk_autoresize( {
//		width_exclude : ['#leftInfoDiv','#newsearchPlan'],
//		height_include : '#lefttree',
//		height_offset : 15
//
//	});

	jQuery('#content').mk_autoresize({
	    width_exclude:'#content_leftside',
	    width_include: '.flexigrid',
	    width_offset: 26// 该值各页面根据自己的页面布局调整
	  });

	 
	jQuery('#content_rightside').mk_autoresize({
	    height_include: '.bDiv',
//	    width_include: ['#titleBar',''],
	    height_offset: 180 // 该值各页面根据自己的页面布局调整
//	    width_offset: 50
	  });

}

/**
 * 左侧树区域显示控制
 */
function HideandShowControl(){//leftdiv
	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
		jQuery('#middeldiv').show();
		jQuery('#leftdiv').hide();
		jQuery('#content').mk_autoresize({
	        width_exclude: '#content_leftside',
	        width_include: '.flexigrid',
	        width_offset:32,// 该值各页面根据自己的页面布局调整
	        is_handler: false
	    });	
	}else{//隐藏状态
		jQuery('#middeldiv').hide();
		jQuery('#leftdiv').show();
		jQuery('#content').mk_autoresize({
	        width_exclude: '#content_leftside',
	        width_include: '.flexigrid',
	        width_offset: 10,// 该值各页面根据自己的页面布局调整
	        is_handler: false
	    });
	}
}

var ftlyOilRecordsObj=null;
jQuery(document).ready(function(){
	ftlyOilRecordsObj = new ftlyOilRecordsHistory();
	ftlyOilRecordsObj.change();
	ftlyOilRecordsObj.wear();
	$('.alarm_tab a').click(function(){
		$(this).parent().find('a').removeClass('alarm_tabs');
		$(this).addClass('alarm_tabs');
		if($(this).attr('flag')=='1'){
			ftlyOilRecordsObj.change();
			$('#wearTable').hide();
			$('#changeTable').show();
			$('#searchOil').click();
			firstrisize(); 
		}else{
			$('#wearTable').show();
			$('#changeTable').hide();
			$('#searchOilAna').click();
			ftlyOilRecordsObj.searchOilWearDetailList();
			firstrisize(); 
		}
	});
	jQuery(window).mk_autoresize({
	     height_include: '#content',
	     height_exclude: ['#header', '#footer'],
	     height_offset: 0,
	     width_include: ['#header', '#content', '#footer'],
	     width_offset: 0
	  });
	firstrisize(); 
	$(window).resize(function(){
		if(jQuery('#middeldiv').css("display")=="none"){//展开状态
			firstrisize();
		} else {
			firstrisize2();
		}
	});
});