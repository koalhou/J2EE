<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/md5/base64.js' />"></script>	
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery.wresize.js'/>"></script>
<script>
	/** 提交form **/
	function submitVehicleRegister() {
		$('vehicleregister_form').action="<s:url value='/cl/queryVehicleRegister.shtml' />";
		Wit.commitAll($('vehicleregister_form'));
	}

	/** 查询车辆注册信息 **/
	function searchList() {
    	jQuery('#empDiv').css("display","none");
		jQuery('#vehicleRegisteList').flexOptions({
			newp: 1,
			params: [{name: 'vehicleLn', value: jQuery('#vehicleLn').val() }, 
					 {name: 'vehicleVin', value: jQuery('#vehicleVin').val() }, 
			         {name: 'terminalCode', value: jQuery('#terminalCode').val()},
			 		 {name: 'simCardNumber', value: jQuery('#simCardNumber').val()},
			 		 {name: 'cellPhone', value: jQuery('#cellPhone').val()},
			 		 {name: 'enterpriseCode', value: jQuery('#enterpriseCode').val()},
			 		 {name: 'deliveryFlag', value: jQuery('#deliveryFlag').val()},
			 		 {name: 'fixType', value: jQuery('#fixType').val()},
			 		 {name: 'start_time', value: jQuery('#start_time').val()},
			 		 {name: 'end_time', value: jQuery('#end_time').val()}]
		});
		jQuery('#vehicleRegisteList').flexReload();
	}
	
	/** 导出车辆注册信息 **/
	function exportVehicleRegister(){
		if(confirm("<s:property value="getText('confirm.export.file')" />")) {
			$('vehicleregister_form').action="<s:url value='/cl/exportVehicleRegister.shtml' />";
			$('vehicleregister_form').submit();
			//Wit.commitAll($('vehicleregister_form'));
		}
	}

	/** 显示GPS位置 **/
	function choiceGPS(longitude,latitude,vehicle_ln) {
	window.open("<s:url value='/cl/gpsbrowse.shtml' />?vehicleRegisterInfo.longitude="+longitude+"&vehicleRegisterInfo.latitude="+latitude+"&vehicleRegisterInfo.vehicleLn="+base64encode(utf16to8(vehicle_ln)),
    	                             "self",
    	                             "width=700,height=370,top=200, left=200");
	
	}

	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
	
	/** 显示详细 **/
	function showDetail(tdDiv,pid){
		tdDiv.innerHTML = '<a href="../cl/queryVehicleRegisterById.shtml?terminalId='+ pid +'">' + tdDiv.innerHTML +'</a>';
    }
    
	/** 位置信息 **/
	function showGpsPosition(tdDiv,pid){
		if(get_cell_text(pid, 14).length > 1 && get_cell_text(pid, 15).length > 1){
			tdDiv.innerHTML = "<a href='javascript:choiceGPS(" + get_cell_text(pid, 14) + "," 
			                                                  + get_cell_text(pid, 15) + ",\"" 
			                                                + get_cell_text(pid, 1) + "\")'>位置</a>";
		} else {
			tdDiv.innerHTML = "--";
		}
	}
	
	/** 列表加载 **/
	jQuery(function() {
		var vehicleRegisteList = jQuery('#vehicleRegisteList');
		vehicleRegisteList.flexigrid({
			url: '<s:url value="/clf/vehicleregisterList.shtml" />',
			dataType: 'json',
			height: '375',
			width: '2000',
			colModel : [
			    		{display: '<s:text name="list.number" />', name : 'rowNumber', width : 30, sortable : false, align: 'left'},
			    		{display: '<s:text name="common.list.vehicleln" />', name : 'VEHICLE_LN', width : 60, sortable : true, align: 'left', process: showDetail},
			    		{display: '<s:text name="common.list.vehiclevin" />', name : 'VEHICLE_VIN', width : 140, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.terminal" />', name : 'TERMINAL_ID', width : 140, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.simcardnumber" />', name : 'SIM_CARD_NUMBER', width : 140, sortable : true, align: 'left'},
			    		{display: '手机号', name : 'CELLPHONE', width : 140, sortable : true, align: 'left'},
			    		{display: '<s:text name="enterprise.info.ENTERPRISE_CODE" />', name : 'ENTERPRISE_CODE', width : 80, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.enterprise" />', name : 'FULL_NAME', width : 140, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.registant" />', name : 'REGISTRANT', width : 60, sortable : true, align: 'left'},
			    		{display: '注册时间', name : 'REGISTRATION_TIME', width : 120, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.modifier" />', name : 'MODIFIER', width : 60, sortable : true, align: 'left'},
			    		{display: '最后修改时间', name : 'MODIFY_TIME', width : 120, sortable : true, align: 'left'},
			    		{display: '<s:text name="vehicleregister.list.delivery" />', name : 'DELIVERY_FLAG', width : 50, sortable : true, align: 'left'},
			    		{display: '<s:text name="vehicleregister.list.fixtype" />', name : 'FIX_TYPE', width : 50, sortable : true, align: 'left'},
			    		{display: 'GPS位置', name : '', width : 50, sortable : false, align: 'left', process:showGpsPosition},
			    		{display: '', name : '', width : 50, sortable : false, align: 'left', hide:true, toggle:false}
			    		],
			    	   		
			    	sortname: 'REGISTRATION_TIME',
			    	sortorder: 'desc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
			    	useRp: true,
			    	rp:10,	
					rpOptions : [10,20,50,100],// 可选择设定的每页结果数
			    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		});
	});

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
    
    //计算控件宽高
    function changeWidthHeight(){
		var h = get_page_height();
		var w = get_page_width();
		jQuery(".flexigrid").width(w-20);
		jQuery(".bDiv").height(h-365); 
    }
    
    //页面自适应
    (function (jQuery) {
	    jQuery(window).wresize(function(){
	    changeWidthHeight();
	    jQuery('#userMangeList').fixHeight();
	});
		
    jQuery(window).load(function (){
    	changeWidthHeight();
    	jQuery('#userMangeList').fixHeight();
    });
    })(jQuery);
	
</script>