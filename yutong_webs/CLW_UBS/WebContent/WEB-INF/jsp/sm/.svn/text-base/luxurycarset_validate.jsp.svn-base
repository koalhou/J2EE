<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
			src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
			src="<s:url value='/scripts/md5/base64.js' />"></script>	
					
<script>
	/** 查询高档车信息 **/
	function searchList() {
    	jQuery('#empDiv').css("display","none");
		jQuery('#maintenanceList').flexOptions({
			newp: 1,
			params: [{name: 'luxuryCar.vehicle_number', value: jQuery('#vehicle_number').val() }]
		});
		jQuery('#maintenanceList').flexReload();
	}
	
	/** 导出高档车信息 **/
	function exportVehicleRegister(){
		if(confirm("<s:property value="getText('confirm.export.file')" />")) {
			$('luxurycarset_form').action="<s:url value='luxurycarsetExport.shtml' />";
			$('luxurycarset_form').submit();
			//Wit.commitAll($('luxurycarset_form'));
		}
	}

	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
    
	/** 操作高档车信息 **/
	function operation(tdDiv,pid){
		//tdDiv.innerHTML = "<a href='javascript:del(\"" + get_cell_text(pid, 0) + "\")'>删除 </a>";
		tdDiv.innerHTML = "<a href='javascript:modify(\"" + get_cell_text(pid, 6) + "\")'>修改 </a>&nbsp;&nbsp;<a href='javascript:del(\"" + get_cell_text(pid, 6) + "\")'>删除 </a>";
	}
	
	/** 修改 **/
	function modify(luxuryCarId){
		jQuery("#luxury_car_id").attr('value',jQuery.trim(luxuryCarId));
		jQuery("#luxurycarset_form").attr("action","luxuryCarSetToMod.shtml");
		jQuery("#luxurycarset_form").submit();
	}
	/** 删除 **/
	function del(luxuryCarId){
		if(confirm("<s:property value="getText('confirm.delete')" />")) {
			jQuery("#luxury_car_id").attr('value',luxuryCarId);
			jQuery("#luxurycarset_form").attr("action","luxuryCarSetDoDel.shtml");
			jQuery("#luxurycarset_form").submit();
		}
	}	
	
	/** 列表加载 **/
	jQuery(function() {
		var maintenanceList = jQuery('#maintenanceList');
		maintenanceList.flexigrid({
			url: '<s:url value="/smquery/luxuryCarQuery.shtml" />',
			dataType: 'json',
			height: '375',
			width: '2000',
			colModel : [
						{display: '车型', name : 'vehicle_type_name', width : 240, sortable : true, align: 'left'},
			    		{display: '车工号', name : 'vehicle_number', width : 200, sortable : true, align: 'left'},
			    		{display: '创建日期', name : 'create_time', width : 120, sortable : true, align: 'left'},
			    		{display: '创建人', name : 'creater_id', width : 120, sortable : true, align: 'left'},
			    		{display: '最后修改人', name : 'modifier_id', width : 120, sortable : true, align: 'left'},
			    		{display: '操作', name : '', width : 100, sortable : false, align: 'center', process: operation},
			    		{display: 'ID', name : 'luxury_car_id', width : 120, sortable : true, align: 'left',hide:true}
			    		],
			    	   		
			    	sortname: 'vehicle_number',
			    	sortorder: 'desc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
			    	useRp: true,
			    	rp:10,	
					rpOptions : [1,10,20,50,100],// 可选择设定的每页结果数
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
		jQuery(".bDiv").height(h-306); 
    }
    
    //页面自适应
    (function (jQuery) {
	    jQuery(window).resize(function(){
	    changeWidthHeight();
	    jQuery('#maintenanceList').fixHeight();
	});
		
    jQuery(window).load(function (){
    	changeWidthHeight();
    	jQuery('#maintenanceList').fixHeight();
    });
    })(jQuery);
	
</script>