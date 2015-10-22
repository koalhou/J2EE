<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script>
	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}

	/** 显示详细 **/
	function showDetail(tdDiv, pid) {
		tdDiv.innerHTML = '<a href="../xj/queryUnnormalVehicle.shtml">' + tdDiv.innerHTML + '</a>';
	}

	/** 燃油报警状态判断 **/
	function reWarnStatus(tdDiv,pid){
		if(get_cell_text(pid, 3)=="0"){
			tdDiv.innerHTML=" <span style= 'color:red'><s:text name='operationsInfo.nosignal'/></span> "; 
		} else if(get_cell_text(pid, 3)=="2") {
			tdDiv.innerHTML=" <span style= 'color:green'><s:text name='operationsInfo.nodevice'/></span> ";
		} else {
			tdDiv.innerHTML=" <span style= 'color:green'><s:text name='operationsInfo.normal'/></span> "; 
		}
	}

	/** 瞬时油耗状态判断 **/
	function reInstantStatus(tdDiv,pid){
		if(get_cell_text(pid, 4)=="0"){
			tdDiv.innerHTML=" <span style= 'color:red'><s:text name='operationsInfo.nosignal'/></span> "; 
		} else if(get_cell_text(pid, 3)=="2") {
			tdDiv.innerHTML=" <span style= 'color:green'><s:text name='operationsInfo.nodevice'/></span> ";
		} else {
			tdDiv.innerHTML=" <span style= 'color:green'><s:text name='operationsInfo.normal'/></span> "; 
		}
	}

	/** 前门开关状态判断 **/
	function reFrontGateStatus(tdDiv,pid){
		if(get_cell_text(pid, 5)=="0"){
			tdDiv.innerHTML=" <span style= 'color:red'><s:text name='operationsInfo.nosignal'/></span> "; 
		} else if(get_cell_text(pid, 3)=="2") {
			tdDiv.innerHTML=" <span style= 'color:green'><s:text name='operationsInfo.nodevice'/></span> ";
		} else {
			tdDiv.innerHTML=" <span style= 'color:green'><s:text name='operationsInfo.normal'/></span> "; 
		}
	}

	/** GPS状态判断 **/
	function reGpsStatus(tdDiv,pid){
		if(get_cell_text(pid, 6)=="0"){
			tdDiv.innerHTML=" <span style= 'color:red'><s:text name='operationsInfo.nosignal'/></span> "; 
		} else if(get_cell_text(pid, 3)=="2") {
			tdDiv.innerHTML=" <span style= 'color:green'><s:text name='operationsInfo.nodevice'/></span> ";
		} else {
			tdDiv.innerHTML=" <span style= 'color:green'><s:text name='operationsInfo.normal'/></span> "; 
		}
	}

	/** 状态编辑 **/
	function reStatus(tdDiv,pid){
		tdDiv.innerHTML = '<a href="../xj/editVehicleStatus.shtml?vehicleId='+ pid +'"><s:text name="btn.update"/></a>';
	}
	
	/** 列表加载 **/
	jQuery( function() {
		var unnormalCarList = jQuery('#unnormalCarList');
		unnormalCarList
				.flexigrid( {
					url : '<s:url value="/xjf/queryUnnormalCars.shtml" />',
					dataType : 'json',
					height : '100',
					width : '2000',
					colModel : [
							{
								display : '<s:text name="list.number" />',
								name : 'ROWNUM',
								width : 30,
								sortable : false,
								align : 'left'
							},
							{
								display : '<s:text name="operationsInfo.sales.order" />',
								name : 'SELL_ORDER_NUM',
								width : 100,
								sortable : true,
								align : 'left'
							},
							{
								display : '<s:text name="oprationsReportSecond.info.vehicle_vin" />',
								name : 'VEHICLE_VIN',
								width : 140,
								sortable : true,
								align : 'left'
							},
							{
								display : '<s:text name="operationsInfo.oil.warn" />',
								name : 'OIL_WARN_STATUS',
								width : 100,
								sortable : true,
								process : reWarnStatus,
								align : 'left'
							},
							{
								display : '<s:text name="operationsInfo.oil.instant" />',
								name : 'OIL_INSTANT_STATUS',
								width : 100,
								sortable : true,
								process : reInstantStatus,
								align : 'left'
							},
							{
								display : '<s:text name="operationsInfo.frontgate" />',
								name : 'FRONT_GATE_STATUS',
								width : 100,
								sortable : true,
								process : reFrontGateStatus,
								align : 'left'
							},
							{
								display : '<s:text name="operationsInfo.gps" />',
								name : 'GPS_STATUS',
								width : 100,
								sortable : true,
								process : reGpsStatus,
								align : 'left'
							}, 
							{
								display : '<s:text name="operationsInfo.modify" />',
								name : '',
								width : 100,
								align : 'left',
								sortable : false,
								process : reStatus
							} ],
					sortname : 'VEHICLE_VIN',
					sortorder : 'desc',
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
			height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight
					: document.body.clientHeight;
		} else {
			height = self.innerHeight;
		}
		return height;
	}

	//获取页面宽度
	function get_page_width() {
		var width = 0;
		if (jQuery.browser.msie) {
			width = document.compatMode == "CSS1Compat" ? document.documentElement.clientWidth
					: document.body.clientWidth;
		} else {
			width = self.innerWidth;
		}
		return width;
	}

	//计算控件宽高
	function changeWidthHeight() {
		var h = get_page_height();
		var w = get_page_width();
		jQuery(".flexigrid").width(w - 20);
		jQuery(".bDiv").height(h - 260);
	}

	//页面自适应
	( function(jQuery) {
		jQuery(window).resize( function() {
			changeWidthHeight();
			jQuery('#unnormalCarList').fixHeight();
		});

		jQuery(window).load( function() {
			changeWidthHeight();
			jQuery('#unnormalCarList').fixHeight();
		});
	})(jQuery);
</script>