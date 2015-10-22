<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script>
	(function($){
		// 预缓存皮肤，数组第一个为默认皮肤
		$.dialog.defaults.skin = ['aero'];
	})(art);
	
	jQuery(function() {
		var gala = jQuery('#carList');
		gala.flexigrid({
			url: '<s:url value="/zdnf/getDeviceStatusByEnterpriseId.shtml" />',
			dataType: 'json',
			colModel : [
	                    {display: '车牌号', name : 'VEHICLE_LN', width : 130, sortable : true, align: 'center',process:reWriteHeight},
	                    {display: '上报时间', name : 'TERMINAL_TIME', width : 120, sortable : true, align: 'center',process:reWriteHeight},
	                    {display: 'DVR', name : 'DVR_FLAG', width : 50, sortable : true, align: 'center',process:statusShow},
	                    {display: '刷卡器', name : 'CARD_FLAG', width : 50, sortable : true, align: 'center',process:statusShow},
	                    {display: '司机助手', name : 'DRIVER_HELPER_FLAG', width : 60, sortable : true, align: 'center',process:statusShow},
	                    {display: '锂电池', name : 'BATTERY_FLAG', width : 50, sortable : true, align: 'center',process:statusShow},
	                    {display: 'GPS天线', name : 'GPS_AERIAL_FLAG', width : 60, sortable : true, align: 'center',process:statusShow},
	                    {display: 'GPS模块', name : 'GPS_MODE_FLAG', width : 60, sortable : true, align: 'center',process:statusShow},
	                    {display: 'VSS车速传感器', name : 'VSS_FLAG', width : 90, sortable : true, align: 'center',process:statusShow},
	                    {display: 'TSS文语模块', name : 'TTS_FLAG', width : 80, sortable : true, align: 'center',process:statusShow},
	                    {display: '摄像头1', name : 'CAMERA1', width : 50, sortable : true, align: 'center',process:statusShow},
	                    {display: '摄像头2', name : 'CAMERA2', width : 50, sortable : true, align: 'center',process:statusShow},
	                    {display: '摄像头3', name : 'CAMERA3', width : 50, sortable : true, align: 'center',process:statusShow},
	                    {display: '摄像头4', name : 'CAMERA4', width : 50, sortable : true, align: 'center',process:statusShow}
				    	   ],
			    	sortname: 'ONLINE_FLAG DESC,TERMINAL_TIME',
			    	sortorder: 'desc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
			    	useRp: true,
			    	rp: 20,
					rpOptions : [10,20,50,100],// 可选择设定的每页结果数
			    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		});
	});	

	 function reWriteHeight(tdDiv, pid) {
			tdDiv.style.height="15px";
		}
	

	/** 查询终端设备状态 **/
	function searchVehicleList() {
		//alert("searchVehicleList");
		jQuery('#carList').flexOptions({
			newp: 1,
			params: [{name: 'queryObj.enterprise_id', value: jQuery('#enterpriseId').val() },
			         {name: 'queryObj.vehicle_ln', value: jQuery('#vehicle_ln').val() },
			         {name: 'queryObj.fault_flag', value: jQuery('#device_status').val() }]
		});
		jQuery('#carList').flexReload();
	}

	function statusShow(tdDiv,pid){
	    if(tdDiv.innerHTML == 10 ){
	    	tdDiv.innerHTML = '<h5 class="lvd_dot">●</h5>';
	    }else if(tdDiv.innerHTML == 11){
	    	tdDiv.innerHTML = '<h5 class="hongd_dot">●</h5>';
	    }else{
	    	tdDiv.innerHTML = '<h5 class="huid_dot">●</h5>';
	    }	
	    tdDiv.style.height="15px";	
	}

	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}

	/*
	 * 查询按钮，选择车辆
	 */	
	function choiceCar() {	
		art.dialog.open("<s:url value='/xj/vehListSearch.shtml' />",{
			title:"车辆信息",
			lock:true,
			width:700,
			height:435
		});
	}				
	
	//页面自适应
	(function (jQuery) {
		 jQuery(window).load(function (){
			 changeWidthHeight();
			 jQuery('#carList').fixHeight();
		});			
		 jQuery(window).resize(function(){
			 changeWidthHeight();
		});
	})(jQuery);
	//获取页面宽度
	function get_page_width() {
		 var width = 0;
		 if(jQuery.browser.msie){ 
		  width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
		 } else { 
		  width = self.innerWidth; 
		 } 
		 return width;
	}
	
	//获取页面高度
	function get_page_height() {
		 var height = 0;
		 if (jQuery.browser.msie) {
		     height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
		 } else {
		     height = self.innerHeight;
		 }
		 return height;
	}
	
	//计算控件宽高
	function changeWidthHeight(){
		 var h = get_page_height();
		 var w = get_page_width();
		 var leftInfoDiv = document.getElementById("leftInfoDiv");
		 if(screenFlag == false && h>315) {
			 jQuery("#leftInfoDiv").height(h-165);
			 jQuery(".ztree").css('height',h-215);
			 jQuery(".bDiv").height(h-315);
			 jQuery(".flexigrid").width(w-290);
		 } else if(screenFlag == true && h>315) {
			 jQuery("#leftTabtd").height(h-165);
			 jQuery(".ztree").css('height',h-215);
			 jQuery('.bDiv').height(h-315);
			 jQuery(".flexigrid").width(w-55);
		 } else {
			 if(h>165) {
				 jQuery("#leftTabtd").height(h-165);
			 } else {
				 jQuery("#leftTabtd").height(h);
			 }
		 }
	}
	
	var screenFlag = false;
	function LeftScreen(){
		screenFlag = true;
		var left = document.getElementById("leftInfoDiv");
		var Lefttab = document.getElementById("leftTab");
		left.style.display="none";
		Lefttab.style.display="";
		
		var searchPlanId = document.getElementById("searchPlanId");
		searchPlanId.style.display="none";
		
		var leftTabtd= document.getElementById("leftTabtd");
	    leftTabtd.style.display="";	
	    changeWidthHeight();		
	}
	function midScreen(){
		screenFlag = false;
		var left = document.getElementById("leftInfoDiv");
		var Lefttab = document.getElementById("leftTabtd");
		var searchPlanId = document.getElementById("searchPlanId");
		left.style.display="";
		Lefttab.style.display="none";
		searchPlanId.style.display="";
		changeWidthHeight();			
	}  
</script>

