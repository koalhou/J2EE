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
	/** 查询信息 **/
	function searchList() {
    	jQuery('#empDiv').css("display","none");
		jQuery('#informationList').flexOptions({
			newp: 1,
			params: [{name: 'theme', value: jQuery('#theme').val() }, 
			         {name: 'createTime', value: jQuery('#createTime').val()}]
		});
		jQuery('#informationList').flexReload();
	}
	
	/** 导出信息 **/
	function exportInformations(){
		if(confirm("<s:property value="getText('confirm.export.file')" />")) {
			$('informationmanage_form').action="<s:url value='/xs/exportInfo.shtml' />";
			$('informationmanage_form').submit();
		}
	}

	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
	
	/** 显示详细 **/
	function showDetail(tdDiv,pid){
		tdDiv.innerHTML = '<a href="../xs/queryInfoById.shtml?issueId='+ pid +'">' + tdDiv.innerHTML +'</a>';
    }
    
	/** 列表加载 **/
	jQuery(function() {
		var informationList = jQuery('#informationList');
		informationList.flexigrid({
			url: '<s:url value="/xsf/informationList.shtml" />',
			dataType: 'json',
			height: '375',
			width: '2000',
			colModel : [
			    		{display: '<s:text name="list.number" />', name : 'rowNumber', width : 30, sortable : false, align: 'left'},
			    		{display: '<s:text name="informationmanage.theme" />', name : 'ISSUE_THEME', width : 200, sortable : true, align: 'left', process: showDetail},
			    		{display: '<s:text name="informationmanage.creater" />', name : 'USER_NAME', width : 140, sortable : true, align: 'left'},
			    		{display: '<s:text name="informationmanage.publishtime" />', name : 'PUBLISH_TIME', width : 140, sortable : true, align: 'left'},
			    		{display: '<s:text name="informationmanage.replyornot" />', name : 'REPLY_OR_NOT', width : 80, sortable : true, align: 'left'},
			    		{display: '<s:text name="informationmanage.status" />', name : 'ISSUE_STATUS', width : 80, sortable : true, align: 'left'}],
			    	sortname: 'PUBLISH_TIME',
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
		jQuery(".bDiv").height(h-322); 
    }
    
    //页面自适应
    (function (jQuery) {
	    jQuery(window).resize(function(){
	    changeWidthHeight();
	    jQuery('#informationList').fixHeight();
	});
		
    jQuery(window).load(function (){
    	changeWidthHeight();
    	jQuery('#informationList').fixHeight();
    });
    })(jQuery);
	
</script>