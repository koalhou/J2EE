<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<script src="<s:url value='/scripts/swf/swfobject_modified.js'/>"
	type="text/javascript"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/flexigrid/jquery.wresize.js'/>"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<script>
	/**
	 * 导出列表
	 */
	function listExport() {
		if (jQuery('#usertbl').find("td").length == 0) {
			alert("没有您要导出的信息!");
			return;
		}
		if (jQuery('#usertbl').flex_totalc > 50000) {
			alert("无法导出，系统最多可一次导出5W条记录!");
			return;
		}
		if (confirm("确定要导出列表信息吗？")) {
			document.getElementById('conditionselectform').action = "<s:url value='/runStatistic/exportStCheckList.shtml' />";
			document.getElementById('conditionselectform').submit();
		}
	}
	
	/**
	 * 加载列表信息
	 */
	function vehicleList() {
		var gala = jQuery('#usertbl');
		gala.flexigrid({
					url : '../runStatisticList/stcheckList.shtml',
					dataType : 'json',
					params : [ {
						name : 'stcheck.start_time',
						value : jQuery('#start_time').val()
					}, {
						name : 'stcheck.end_time',
						value : jQuery('#end_time').val()
					}],
					height : 600,
					width : 400,
					colModel : [
							{
								display : '姓名',
								//name : 'emp_name',
								name : "NLSSORT(emp_name,'NLS_SORT = SCHINESE_PINYIN_M')",
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							},{
								display : '员工号',
								name : 'emp_code',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '卡号',
								name : 'emp_card_id',
								width : calcColumn(0.06, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '部门',
								name : 'org_name',
								width : calcColumn(0.1, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '班车号',
								name : 'vehicle_code',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '车牌号',
								name : 'vehicle_ln',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '驾驶员',
								name : 'driver_name',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '乘车线路',
								name : 'route_name',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '乘车站点',
								name : 'site_name',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								align : 'center'
							}, {
								display : '刷卡时间',
								name : 'terminal_time',
								width : calcColumn(0.08, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							} , {
								display : '刷卡位置',
								name : 'location',
								width : calcColumn(0.15, 80, 0),
								escape : true,
								align : 'center'
							} ],
					sortname : 'terminal_time',
					sortorder : 'desc',
					sortable : true,
					striped : true,
					usepager : true,
					resizable : false,
					useRp : true,
					rp : 20,
					rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
					showTableToggleBtn : true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错
					onSuccess : function() {//等待ajax查询数据完成，添加点击行事件
					}
				});
		
	}
	/**
	 * 选择树上车辆标点方法，list为vin数组，flag为选择状态
	 */
	var treeVins = '';
	function decodeSelectV(list, flag) {
		var strsql = "";
		if (list.length > 0) {
			if (flag == true) {
				for ( var i = 0; i < list.length; i++) {
					if (i == list.length - 1) {
						strsql += "'" + list[i] + "'";
					} else {
						strsql += "'" + list[i] + "',";
					}
				}
			}
		}
		//根据车辆vin查询列表信息
		treeVins = strsql;
		searchList();
	}
	
	//左侧树查询
	function searchTreeClick() {
		jQuery('#vehicleLn').val(jQuery('#vehicleLntext').val());
		searchTree();
	}
	
	
	//查询列表
	function searchList(){
		document.getElementById('Below_new').style.display='none';
		jQuery('input').each(function(){
			var value=jQuery.trim(jQuery(this).val());
			jQuery(this).val(value);
			//var val=$.trim(jQuery('#emp_name').val())
		});
		jQuery('#usertbl').flexOptions({
			newp : 1,
			params : [ {
				name : 'stcheck.start_time',
				value : jQuery('#start_time').val()
			}, {
				name : 'stcheck.end_time',
				value : jQuery('#end_time').val()
			}, {
				name : 'stcheck.emp_name',
				value : formatSpecialChar(jQuery('#emp_name').val().trim())
			}, {
				name : 'stcheck.emp_code',
				value : formatSpecialChar(jQuery('#emp_code').val().trim())
			}, {
				name : 'stcheck.vins',
				value : treeVins
			}]
		});
		jQuery('#usertbl').flexReload();
	}
	
	
	
	/**
	 * 左侧树区域显示控制
	 */
	function HideandShowControl() {
		if(jQuery('#middeldiv').css("display")=="none"){//展开状态
			jQuery('#middeldiv').show();
			jQuery('#leftdiv').hide();
			jQuery('#content').mk_autoresize({
		        width_exclude: '#content_leftside',
		        width_include: '.flexigrid',
		        width_offset:42,// 该值各页面根据自己的页面布局调整
		        is_handler: false
		    });	
		}else{//隐藏状态
			jQuery('#middeldiv').hide();
			jQuery('#leftdiv').show();
			jQuery('#content').mk_autoresize({
		        width_exclude: '#content_leftside',
		        width_include: '.flexigrid',
		        width_offset: 20,// 该值各页面根据自己的页面布局调整
		        is_handler: false
		    });
		}
	}
	function AutoWidthHeight() {
		//计算全局高度和宽度自适应
		jQuery(window).mk_autoresize({
			height_include : '#content',
			height_exclude : [ '#header', '#footer' ],
			height_offset : 0,
			width_include : [ '#header', '#content', '#footer' ]
		});

		//计算中区高度
		jQuery('#content').mk_autoresize({
			height_include : [ '#content_rightside', '#content_leftside',"#middeldiv"],
			height_offset : 0
		});
		//计算左测区域高度
		jQuery('#content_leftside').mk_autoresize({
			height_exclude : ['.treeTab','.newsearchPlan'],
			height_include : '.treeBox',
			height_offset : 10
		});
		//计算右侧高度
		jQuery('#content_rightside').mk_autoresize({
			height_exclude : ['.titleBar','.reportOnline2'],
			height_include : '.bDiv',
			height_offset : 80
		});
		//计算右侧宽度
		jQuery('#content').mk_autoresize({
		    width_exclude:'#content_leftside',
		    width_include: '.flexigrid',
		    width_offset: 20
		});
	}
	//初始化,装载树
	jQuery(document).ready(function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
		//默认加载表格
		vehicleList();
		//自适应
		AutoWidthHeight();

	});
</script>


