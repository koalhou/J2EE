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
	function clickEnterEvent(obj){
		if(obj.id=='undefined' || obj.id == ''){
			return false;
		}
		jQuery('#ChooseEnterID_tree').attr("value",obj.id);
		searchList();
	}
	/** 查询车辆注册信息 **/
	function searchList() {
    	jQuery('#empDiv').css("display","none");
		jQuery('#maintenanceList').flexOptions({
			newp: 1,
			params: [{name: 'maintenance.vehicle_number', value: jQuery('#vehicle_number').val() }, 
			         {name: 'maintenance.type_name', value: jQuery('#type_name').val()},
			 		 {name: 'maintenance.vehicle_vin', value: jQuery('#vehicle_vin').val()},
			 		 {name: 'maintenance.vehicle_ln', value: jQuery('#vehicle_ln').val()},
			 		 {name: 'ChooseEnterID_tree', value: ''}]
		});
		jQuery('#maintenanceList').flexReload();
	}

	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
	
	/** 显示详细 **/
	function showDetail(tdDiv,pid){
		tdDiv.innerHTML = '<a href="../cl/queryVehicleRegisterById.shtml?terminalId='+ pid +'">' + tdDiv.innerHTML +'</a>';
    }
    
	/** 操作信息 **/
	function operation(tdDiv,pid){
		if(get_cell_text(pid, 9) == '1'){
			tdDiv.innerHTML = "<input name=noremind type='checkbox' value=\'"+get_cell_text(pid, 10)+"\' onclick=\"remindState(\'"+get_cell_text(pid, 10)+"\');\" checked='checked'/>";
		}else{
			tdDiv.innerHTML = "<input name=noremind type='checkbox' value=\'"+get_cell_text(pid, 10)+"\' onclick=\"remindState(\'"+get_cell_text(pid, 10)+"\');\"/>";
		}
		//"<input type=\"hidden\" id=\"trid"+get_cell_text(pid, 6)+"\" value=\""+get_cell_text(pid, 6)+"\"/>";
	}
	
	/** 提醒状态设置 **/
	function remindState(vin){
		
	}

	/** 全选 **/
	function checkAll(obj){
		var i=0,boxes = jQuery("noremind"),hidVal=jQuery("input:hidden");
		if(obj.checked){
			for(i=0;i<boxes.length;i++){
				boxes[i].checked = true;
				//alert(boxes[i].value);
				//boxes[i].click();
			}
		}else{
			for(i=0;i<boxes.length;i++){
				boxes[i].checked = false;
				//boxes[i].click();
			}
		}
	}
  	//不再提醒
  	function doNotRemind(){
  		var i=0,boxes = jQuery("input[name=noremind]"),checkItemIds='',unCheckItemIds='';
  		for(i=0;i<boxes.length;i++){
  			if(boxes[i].checked){
  				checkItemIds = checkItemIds + jQuery("#"+boxes[i].id).attr("lang")+",";
  			}else{
  				unCheckItemIds = unCheckItemIds + jQuery("#"+boxes[i].id).attr("lang")+",";
  	  		}
  		}
  		if(checkItemIds == '' && unCheckItemIds == ''){
  			alert('请您至少取消/选择一条记录!');
  		}
  		jQuery("#checkItemIds").attr("value",checkItemIds.substring(0,checkItemIds.length-1));
  		jQuery("#unCheckItemIds").attr("value",unCheckItemIds.substring(0,unCheckItemIds.length-1));
  		jQuery("#maintenancesetquery_form").attr("action","modifyRemindState.shtml");
  		Wit.commitAll($('maintenancesetquery_form'));
  	}
	/** 列表加载 **/
	jQuery(function() {
		var maintenanceList = jQuery('#maintenanceList');
		maintenanceList.flexigrid({
			url: '<s:url value="/smquery/maintenanceQuery.shtml" />',
			dataType: 'json',
			height: '395',
			width: '2000',
			colModel : [
			    		{display: '车牌号', name : 'vehicle_ln', width : 60, sortable : false, align: 'left'},
			    		{display: '车型', name : 'type_name', width : 100, sortable : false, align: 'left'},
			    		{display: '车工号', name : 'vehicle_number', width : 80, sortable : false, align: 'left'},
			    		{display: 'VIN号', name : 'vehicle_vin', width : 100, sortable : false, align: 'left'},
			    		{display: '维保项目', name : 'config_id', width : 80, sortable : false, align: 'left'},
			    		{display: '条件', name : 'conditionnewcar', width : 30, sortable : false, align: 'left'},
			    		{display: '提醒条件', name : 'conditionremindnewcar', width : 60, sortable : false, align: 'left'},
			    		{display: '高档车条件', name : 'condition_luxury', width : 60, sortable : false, align: 'left'},
			    		<s:if test="#session.perUrlList.contains('111_0_2')">
			    		{display: '提醒设置', name : '', width : 80, sortable : false, align: 'center'}
			    		</s:if>
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
	
	function LeftScreen(){
		var left = document.getElementById("leftInfoDiv");
		var Lefttab = document.getElementById("leftTab");
			left.style.display="none";
			Lefttab.style.display="";
		var searchPlanId = document.getElementById("searchPlanId");
		    searchPlanId.style.display="none";
	}
	
	function midScreen(){
		var left = document.getElementById("leftInfoDiv");
		var Lefttab = document.getElementById("leftTab");
		left.style.display="";
		Lefttab.style.display="none";
		var searchPlanId = document.getElementById("searchPlanId");
		searchPlanId.style.display="";
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
		jQuery(".flexigrid").width(w-240);
		jQuery(".bDiv").height(h-332); 
		jQuery("#treeDiv").height(h-191);
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