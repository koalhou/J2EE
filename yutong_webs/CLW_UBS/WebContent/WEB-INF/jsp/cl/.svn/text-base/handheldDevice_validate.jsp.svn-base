<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/md5/base64.js' />"></script>	
					
<script>
	/** 查询车辆注册信息 **/
	function searchList() {
    	jQuery('#empDiv').css("display","none");
		jQuery('#handheldDeviceList').flexOptions({
			newp: 1,
			params: [{name: 'handheldDeviceInfo.cellPhoneImei', value: jQuery('#cellPhoneImei').val() }, 
			         {name: 'handheldDeviceInfo.handheldDeviceNo', value: jQuery('#handheldDeviceNo').val()},
			 		 {name: 'handheldDeviceInfo.cellPhone', value: jQuery('#cellPhone').val()},
			 		 {name: 'handheldDeviceInfo.enterpriseCode', value: jQuery('#enterpriseCode').val()}]
		});
		jQuery('#handheldDeviceList').flexReload();
	}
	
	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
	
	/** 显示详细 **/
	function showDetail(tdDiv,pid){
		tdDiv.innerHTML = '<a href="../cl/queryHandheldDeviceById.shtml?terminalId='+ pid +'">' + tdDiv.innerHTML +'</a>';
    }
    
	/** 列表加载 **/
	jQuery(function() {
		var handheldDeviceList = jQuery('#handheldDeviceList');
		handheldDeviceList.flexigrid({
			url: '<s:url value="/clf/handheldDeviceList.shtml" />',
			dataType: 'json',
			height: '375',
			width: '2000',
			colModel : [
			    		{display: '<s:text name="list.number" />', name : 'rowNumber', width : 30, sortable : false, align: 'left'},
			    		{display: '手机IMEI号', name : 'VEHICLE_VIN', width : 140, sortable : true, align: 'left',process: showDetail},
			    		{display: '设备编号', name : 'TERMINAL_ID', width : 140, sortable : true, align: 'left'},
			    		{display: '手机号', name : 'SIM_CARD_NUMBER', width : 140, sortable : true, align: 'left'},
			    		{display: '<s:text name="enterprise.info.ENTERPRISE_CODE" />', name : 'ENTERPRISE_CODE', width : 80, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.enterprise" />', name : 'FULL_NAME', width : 140, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.registant" />', name : 'REGISTRANT', width : 60, sortable : true, align: 'left'},
			    		{display: '注册时间', name : 'REGISTRATION_TIME', width : 120, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.modifier" />', name : 'MODIFIER', width : 60, sortable : true, align: 'left'},
			    		{display: '最后修改时间', name : 'MODIFY_TIME', width : 120, sortable : true, align: 'left'}
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
		jQuery(".bDiv").height(h-320); 
    }
    
    //页面自适应
    (function (jQuery) {
	    jQuery(window).resize(function(){
	    changeWidthHeight();
	});
		
    jQuery(window).load(function (){
    	changeWidthHeight();
    });
    })(jQuery);
	
</script>