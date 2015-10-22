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
	/** 查询维保设置信息 **/
	function searchList() {
    	jQuery('#empDiv').css("display","none");
		jQuery('#maintenanceList').flexOptions({
			newp: 1,
			params: [{name: 'maintenance.type_id', value: jQuery('#typeId').val()}]
		});
		jQuery('#maintenanceList').flexReload();
	}
	
	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
    
	/** 操作信息 **/
	function operation(tdDiv,pid){
		tdDiv.innerHTML = "<a href='javascript:modify(\"" + get_cell_text(pid, 1) + "\")'>修改 </a>";
	}
	/** 修改 **/
	function modify(vin){
		jQuery("#maintenanceset_form").attr("action","maintenanceToMod.shtml");
		jQuery("#maintenanceset_form").submit();
	}
	/** 删除 **/
	function del(vin){
		jQuery("#maintenanceset_form").attr("action","maintenanceDoDel.shtml");
		jQuery("#maintenanceset_form").submit();
	}	
	/** 列表加载 **/
	jQuery(function() {
		var maintenanceList = jQuery('#maintenanceList');
		maintenanceList.flexigrid({
			url: '<s:url value="/smquery/maintenanceSetQueryList.shtml" />',
			dataType: 'json',
			height: '375',
			width: '2000',
			colModel : [
			    		{display: '<s:text name="list.number" />', name : 'rowNumber', width : 40, sortable : false, align: 'left'},
			    		{display: '<s:text name="service.management.maintenance.item" />', name : 'TYPE_ID', width : 200, sortable : true, align: 'left'},
			    		{display: '修改日期', name : 'MODIFY_TIME', width : 120, sortable : true, align: 'left'},
			    		{display: '修改人', name : 'MODIFIER_ID', width : 120, sortable : true, align: 'left'},
			    		{display: '操作', name : '', width : 80, sortable : false, align: 'center'}
			    		],
			    	   		
			    	sortname: 'TYPE_ID',
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
		jQuery(".bDiv").height(h-345); 
    }
    
    //页面自适应
    (function (jQuery) {
	    jQuery(window).resize(function(){
	    changeWidthHeight();
	    jQuery('#userMangeList').fixHeight();
	});
		
    jQuery(window).load(function (){
    	changeWidthHeight();
    	jQuery('#userMangeList').fixHeight();
    });
    })(jQuery);
	
</script>