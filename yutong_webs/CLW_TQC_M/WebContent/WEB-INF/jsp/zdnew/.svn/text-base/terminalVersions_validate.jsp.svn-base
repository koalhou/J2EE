<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script>
	jQuery(function() {
		var gala = jQuery('#carList');
		gala.flexigrid({
			url: '<s:url value="/zdnf/getVersionListByEnterpriseId.shtml" />',
			dataType: 'json',
			colModel : [
	                    {display: '平台注册车牌号', name : 'VEHICLE_LN', width : 120, sortable : true, align: 'center'},
	                    {display: '终端上报车牌号', name : 'HARDWARE_VEHICLE_LN', width : 120, sortable : true, align: 'center',process:lnRedSign},
	                    {display: '平台注册VIN', name : 'VEHICLE_VIN', width : 130, sortable : true, align: 'center'},
	                    {display: '终端上报VIN', name : 'HARDWARE_VEHICLE_VIN', width : 130, sortable : true, align: 'center',process:vinRedSign},
	                    {display: '平台注册终端号', name : 'TERMINAL_ID', width : 130, sortable : true, align: 'center'},
	                    {display: '终端上报终端号', name : 'HARDWARE_TERMINAL_ID', width : 130, sortable : true, align: 'center',process:terRedSign},
	                    {display: '平台注册车牌颜色', name : 'VEH_PAI_COLOR', width : 100, sortable : true, align: 'center',process:transferColor},
	                    {display: '终端上报车牌颜色', name : 'HARDWARE_VEH_PAI_COLOR', width : 100, sortable : true, align: 'center',process:colRedSign},
	                    {display: '手机号', name : 'CELLPHONE', width : 80, sortable : true, align: 'center'},
	                    {display: 'ICCID', name : 'SIM_SCCID', width : 60, sortable : true, align: 'center'},
	                    {display: '主机硬件版本', name : 'HOST_HARD_VER', width : 80, sortable : true, align: 'center'},
	                    {display: '主机固件版本', name : 'HOST_FIRM_VER', width : 80, sortable : true, align: 'center'},
	                    {display: '显示屏硬件版本', name : 'XIANSHI_HARD_VER', width : 90, sortable : true, align: 'center'},
	                    {display: '显示屏固件版本', name : 'XIANSHI_FIRM_VER', width : 90, sortable : true, align: 'center'},
	                    {display: 'DVR硬件版本', name : 'DVR_HARD_VER', width : 75, sortable : true, align: 'center'},
	                    {display: 'DVR固件版本', name : 'DVR_FIRM_VER', width : 75, sortable : true, align: 'center'},
	                    {display: '射频读卡器硬件版本', name : 'SHEPIN_HARD_VER', width : 105, sortable : true, align: 'center'},
	                    {display: '射频读卡器固件版本', name : 'SHEPIN_FIRM_VER', width : 105, sortable : true, align: 'center'},
	                    {display: '上报时间', name : 'TERMINAL_TIME', width : 120, sortable : true, align: 'center'}
				    	   ],
			    	sortname: 'VEHICLE_LN',
			    	sortorder: 'asc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
			    	useRp: true,
			    	rp: 10,
					rpOptions : [10,20,50,100],// 可选择设定的每页结果数
			    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		});
	});	

	/** 查询版本信息 **/
	function searchVehicleList() {
		jQuery('#carList').flexOptions({
			newp: 1,
			params: [{name: 'queryObj.enterprise_id', value: jQuery('#enterpriseId').val() },
			         {name: 'queryObj.vehicle_ln', value: jQuery('#vehicle_ln').val() },
			         {name: 'queryObj.vehicle_vin', value: jQuery('#vehicle_vin').val() },
			         {name: 'queryObj.terminal_id', value: jQuery('#terminal_id').val() },
			         {name: 'queryObj.cellphone', value: jQuery('#cellphone').val() },
			         {name: 'queryObj.sim_sccid', value: jQuery('#sim_sccid').val() },
			         {name: 'queryObj.host_hard_ver', value: jQuery('#host_hard_ver').val() },
			         {name: 'queryObj.host_firm_ver', value: jQuery('#host_firm_ver').val() },
			         {name: 'queryObj.xianshi_hard_ver', value: jQuery('#xianshi_hard_ver').val() },
			         {name: 'queryObj.xianshi_firm_ver', value: jQuery('#xianshi_firm_ver').val() },
			         {name: 'queryObj.dvr_hard_ver', value: jQuery('#dvr_hard_ver').val() },
			         {name: 'queryObj.dvr_firm_ver', value: jQuery('#dvr_firm_ver').val() },
			         {name: 'queryObj.shepin_hard_ver', value: jQuery('#shepin_hard_ver').val() },
			         {name: 'queryObj.shepin_firm_ver', value: jQuery('#shepin_firm_ver').val() }]
		});
		jQuery('#carList').flexReload();
	}

	function lnRedSign(tdDiv,pid){
		if(get_cell_text(pid, 0) != tdDiv.innerHTML){
			tdDiv.innerHTML = '<font color="red">' + tdDiv.innerHTML +'</font>';
		}
	}

	function vinRedSign(tdDiv,pid){
		if(get_cell_text(pid, 2) != tdDiv.innerHTML){
			tdDiv.innerHTML = '<font color="red">' + tdDiv.innerHTML +'</font>';
		}
	}

	function terRedSign(tdDiv,pid){
		if(get_cell_text(pid, 4) != tdDiv.innerHTML){
			tdDiv.innerHTML = '<font color="red">' + tdDiv.innerHTML +'</font>';
		}
	}

	function colRedSign(tdDiv,pid){
		var str = tdDiv.innerHTML;
		if("1" == tdDiv.innerHTML){
			str = '蓝色';
		}else if ("2" == tdDiv.innerHTML){
			str = '黄色';
		}else if ("3" == tdDiv.innerHTML){
			str = '黑色';
		}else if ("4" == tdDiv.innerHTML){
			str = '白色';
		}else if ("9" == tdDiv.innerHTML){
			str = '其他';
		}else if ("0" == tdDiv.innerHTML){
			str = '未设定';
		}		
		if(get_cell_text(pid, 6) != str){			
			tdDiv.innerHTML = '<font color="red">' + str +'</font>';
		}else{
			tdDiv.innerHTML = str;
		}
	}

	function transferColor(tdDiv,pid){
		if("1" == tdDiv.innerHTML){
			tdDiv.innerHTML = '蓝色';
		}else if ("2" == tdDiv.innerHTML){
			tdDiv.innerHTML = '黄色';
		}else if ("3" == tdDiv.innerHTML){
			tdDiv.innerHTML = '黑色';
		}else if ("4" == tdDiv.innerHTML){
			tdDiv.innerHTML = '白色';
		}else if ("9" == tdDiv.innerHTML){
			tdDiv.innerHTML = '其他';
		}else if ("0" == tdDiv.innerHTML){
			tdDiv.innerHTML = '未设定';
		}
	}

	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
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
		 if(screenFlag == false && h>398) {
			 jQuery("#leftInfoDiv").height(h-165);
			 jQuery(".ztree").css('height',h-215);
			 jQuery(".bDiv").height(h-398);
			 jQuery(".flexigrid").width(w-290);
		 } else if(screenFlag == true && h>398) {
			 jQuery("#leftTabtd").height(h-165);
			 jQuery(".ztree").css('height',h-215);
			 jQuery('.bDiv').height(h-398);
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

