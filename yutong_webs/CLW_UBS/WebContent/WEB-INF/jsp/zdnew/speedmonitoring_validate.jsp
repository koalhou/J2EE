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
	
	/** 全选 **/
	function setOrCancelSelect(obj) {
		 if(jQuery(obj).attr("checked")){
			jQuery("input[name='carChoice']").each(function(){
			jQuery("input[name='carChoice']").attr("checked","true");
			});
		 }else{
			 jQuery("input[name='carChoice']").each(function(){
				 jQuery("input[name='carChoice']").removeAttr("checked"); 
			});
		}
	}
	
	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}			

	function reWriteCheckBox(tdDiv,pid){
		tdDiv.innerHTML = '<input name="carChoice" value="' + pid + '"  type="checkbox" />';
	}
	
	jQuery(function() {
		var gala = jQuery('#carList');
		gala.flexigrid({
			url: '<s:url value="/zdnf/getSpeedMonitoringListByEnterpriseId.shtml" />',
			params: [{name: 'enterpriseId', value: jQuery('#enterpriseId').val() }],
			         //{name: 'vehicleLn', value: jQuery('#vehicleId').val()}],
			dataType: 'json',
			height: 240,
			width:264,
			colModel : [
	                    {display: '<input id="carChoiceAll" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', 
		                 name : '', 
		                 width : 30, 
		                 align: 'center', 
		                 process:reWriteCheckBox},
			    		{display: '车牌号', name : 'VEHICLE_LN', width : 100, sortable : true, align: 'center'},
			    		{display: 'GPS速度(km/h)', name : 'TO_NUMBER(GPSSPEED)', width : 100, sortable : true, align: 'center'},
			    		{display: 'VSS速度(km/h)', name : 'TO_NUMBER(VSSSPEED)', width : 100, sortable : true, align: 'center'},
			    		{display: '偏差比例(%)', name : 'TO_NUMBER(OFFSETSCALE)', width : 100, sortable : true, align: 'center'},
			    		{display: '汇报时间', name : 'TERMINAL_TIME', width : 150, sortable : true, align: 'center'},
			    		{display: '处理状态', name : 'CHARACTER_OEFFICIENT_STATUS', width : 100, sortable : true, align: 'center'}
				    	   ],
				    newp: 1,
			    	sortname: 'VEHICLE_LN',
			    	sortorder: 'asc',
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

	/** 查询车辆信息 **/
	function searchVehicleList() {
		jQuery('#carList').flexOptions({
			newp: 1,
			params: [{name: 'enterpriseId', value: jQuery('#enterpriseId').val() },
			         {name: 'vehicleLn', value: jQuery('#vehicle_ln').val()}]
		});
		jQuery('#carList').flexReload();
	}

	// 判断车辆选择情况
	function vehicleIsChecked() {
		var carsChoice = document.getElementsByName("carChoice");
	    var carIdList = "";
	    for(var i=0; i<carsChoice.length; i++) {
	        if(carsChoice[i].checked==true) {
	            if(carIdList=="") {
	            	carIdList = "'" + carsChoice[i].value + "'";
	            	//carIdList =carsChoice[i].value;
	            } else {
	            	carIdList=carIdList + "," + "'" + carsChoice[i].value  + "'";
	            	//carIdList=carIdList + "," + carsChoice[i].value;
	            }
	        }
	    }
	
	    if(carIdList==""){
			alert("请选择车辆！");
			return false;
		} else {
			document.getElementById("carIdList").value = carIdList;
			return true;
		}
	}

	// 开启矫正
	function startAdjust() {
		$('successLbl').innerHTML = "";
		$('errorLbl').innerHTML = "";
		
		if(!vehicleIsChecked()) {
			return false;
		}

		if(confirm("确定下发指令？")) {
			jQuery.ajax({
				type: 'POST', 
				url: "<s:url value='/zdnew/startAdjust.shtml' />",	
				data: {
					carIdList:$('carIdList').value
				},
				success: function(data) {
					if(data=="0"){
						var obj = $('successLbl');
						obj.innerHTML = "下发开启VSS特征系数矫正指令成功！";
						return true;
					} else {
						var obj = $('errorLbl');
						obj.innerHTML = data + "台车下发开启VSS特征系数矫正指令失败！";
						return false;
					}
				}  
			});
			
			return true;
		} else {
			return false;
		}
	}

	// 关闭矫正
	function closeAdjust() {
		$('successLbl').innerHTML = "";
		$('errorLbl').innerHTML = "";
		
		if(!vehicleIsChecked()) {
			return false;
		}

		if(confirm("确定下发指令？")) {
			jQuery.ajax({
				type: 'POST', 
				url: "<s:url value='/zdnew/closeAdjust.shtml' />",	
				data: {
					carIdList:$('carIdList').value
				},
				success: function(data) {
					if(data=="0"){
						var obj = $('successLbl');
						obj.innerHTML = "下发关闭VSS特征系数矫正指令成功！";
						return true;
					} else {
						var obj = $('errorLbl');
						obj.innerHTML = data + "台车下发关闭VSS特征系数矫正指令失败！";
						return false;
					}
				}  
			});
			
			return true;
		} else {
			return false;
		}
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
		 if(screenFlag == false && h>378) {
			 jQuery("#leftInfoDiv").height(h-165);
			 jQuery(".ztree").css('height',h-215);
			 jQuery(".bDiv").height(h-325);
			 jQuery(".flexigrid").width(w-280);
		 } else if(screenFlag == true && h>378) {
			 jQuery("#leftTabtd").height(h-165);
			 jQuery(".ztree").css('height',h-215);
			 jQuery('.bDiv').height(h-325);
			 jQuery(".flexigrid").width(w-43);
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

