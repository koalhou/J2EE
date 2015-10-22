<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type='text/javascript' src='../scripts/fusioncharts/FusionCharts.js'></script>
<script>
	// 初始化弹出层对象
	(function($){
		// 预缓存皮肤，数组第一个为默认皮肤
		$.dialog.defaults.skin = ['aero'];
	})(art);

	/** 饼图弹出层 **/
	function pieChart(normalcnt, unnormalcnt) {
		art.dialog( {
			title : '车辆当前状态',
			id : 'myShowInfo',
			lock : true,
			width : 700,
			height : 425,
			window : 'parent',
			content : '<div id="chartdiv" class="reportOnline"></div>',
			closeFn : function() {
				changeWidthHeight();
			}
		});
		
		window.parent.pieChartDraw(normalcnt, unnormalcnt);
	}

	/** 组装饼图数据 **/
	function pieChartDraw(normalcnt, unnormalcnt) {
		var xmlStr = "<chart  rotateYAxisName='0' showValues='1'   baseFontSize='16' outCnvBaseFontSize='14'  decimalPrecision='2'>";
		xmlStr = xmlStr + "<set label='正常上报台数' value='" + normalcnt
				+ "' color='01B468' isSliced='1' />";
		xmlStr = xmlStr + "<set label='不正常上报台数' value='" + unnormalcnt
				+ "' color='9D080D' isSliced='1' />";
		xmlStr = xmlStr + "</chart>";

		var chart1 = new FusionCharts("../scripts/fusioncharts/Pie3D.swf?ChartNoDataText=无查询结果",
				                      "ChartId", 
				                      "680", 
				                      "400", 
				                      "0", 
				                      "0");
		chart1.setDataXML(xmlStr);
		chart1.render("chartdiv");
	}

	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}

	/** 显示详细 **/
	function showDetail(tdDiv, pid) {
		tdDiv.innerHTML = '<a href="../xj/queryUnnormalVehicle.shtml">' + tdDiv.innerHTML + '</a>';
	}

	/** 饼图加载 **/
	function reWritephoto(tdDiv, pid) {
		tdDiv.innerHTML = '<img src=\'../images/bingtu.gif\' border=\'0\' style=\' width:16px; height:16px;cursor:pointer;\' onclick=\'pieChart("'
				+ get_cell_text(pid, 5)
				+ '","'
				+ get_cell_text(pid, 6)
				+ '")\'/>';
	}

	/** 列表加载 **/
	jQuery( function() {
		var operatingTotalList = jQuery('#operatingTotalList');
		operatingTotalList
				.flexigrid( {
					url : '<s:url value="/xjf/getOperatingTotal.shtml" />',
					dataType : 'json',
					height : '100',
					width : '2000',
					colModel : [
							{
								display : '<s:text name="operationsInfo.info.total_count" />',
								name : 'ENTERPRISE_CNT',
								width : 100,
								sortable : false,
								align : 'left'
							},
							{
								display : '<s:text name="operationsInfo.info.zhuce_num" />',
								name : 'REGISTERED_CNT',
								width : 100,
								sortable : false,
								align : 'left'
							},
							{
								display : '<s:text name="operationsInfo.info.curr_active_num" />',
								name : 'ONLINE_CNT',
								width : 100,
								sortable : false,
								align : 'left'
							},
							{
								display : '<s:text name="operationsInfo.info.onlinerate" />',
								name : 'ONLINE_RATE',
								width : 100,
								sortable : false,
								align : 'left'
							},
							{
								display : '<s:text name="operationsInfo.info.his_active_num" />',
								name : 'OFFLINE_CNT',
								width : 100,
								sortable : false,
								align : 'left'
							},
							{
								display : '<s:text name="operationsInfo.info.normalcount" />',
								name : 'NORMAL_CNT',
								width : 100,
								sortable : false,
								align : 'left'
							},
							{
								display : '<s:text name="operationsInfo.info.unnormalcount" />',
								name : 'UNNORMAL_CNT',
								width : 140,
								sortable : false,
								align : 'left',
								process : showDetail
							}, {
								display : '',
								name : '',
								width : 30,
								align : 'center',
								process : reWritephoto
							} ],
					sortname : '',
					sortorder : '',
					sortable : false,
					striped : true,
					usepager : false,
					resizable : false,
					useRp : true,
					rp : 10,
					rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
					showTableToggleBtn : true
				// 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
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
		jQuery(".bDiv").height(h - 230);
	}

	//页面自适应
	( function(jQuery) {
		jQuery(window).resize( function() {
			changeWidthHeight();
			jQuery('#operatingTotalList').fixHeight();
		});

		jQuery(window).load( function() {
			changeWidthHeight();
			jQuery('#operatingTotalList').fixHeight();
		});
	})(jQuery);
</script>