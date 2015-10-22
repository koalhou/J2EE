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
 * 出勤明细
 * @return
 */
function workDetailList(route_class,vin,code,ln){
	var start_time=jQuery('#beginTime').val();
	var end_time=jQuery('#endTime').val();
	var dateFlag=jQuery('#dateFlag').val();
	var stitle="班车出勤明细--"+code+"("+ln+")";
	art.dialog.open(encodeURI("<s:url value='/runStatistic/workDetailPage.shtml' />?vin="+vin+"&start_time="+start_time+"&end_time="+end_time+"&class="+route_class+"&dateFlag="+dateFlag),{
		title:stitle,
		lock:true,
		width:900,
		height:435
	});
}
/**
 * 维保明细
 */
function repaireDetailList(vin,code,ln){
	var start_time=jQuery('#beginTime').val();
	var end_time=jQuery('#endTime').val();
	var dateFlag=jQuery('#dateFlag').val();
	var stitle="维保明细--"+code+"("+ln+")";
	art.dialog.open(encodeURI("<s:url value='/runStatistic/repaireDetailPage.shtml' />?vin="+vin+"&start_time="+start_time+"&end_time="+end_time+"&dateFlag="+dateFlag),{
		title:stitle,
		lock:true,
		width:900,
		height:435
	});
}
/**
 * 公差明细
 */
 function pubDetailList(vin,code,ln){
	    var start_time=jQuery('#beginTime').val();
		var end_time=jQuery('#endTime').val();
		var dateFlag=jQuery('#dateFlag').val();
		var stitle="公差明细--"+code+"("+ln+")";
		art.dialog.open(encodeURI("<s:url value='/runStatistic/pubDetailPage.shtml' />?vin="+vin+"&start_time="+start_time+"&end_time="+end_time+"&dateFlag="+dateFlag),{
			title:stitle,
			lock:true,
			width:900,
			height:435
		});
}
	/**
	 * 导出列表
	 */
	function listExport() {
		if (jQuery('#usertbl').find("td").length == 0) {
			alert("没有您要导出的信息!");
			return;
		}
		if (confirm("确定要导出列表信息吗？")) {
			document.getElementById('conditionselectform').action = "<s:url value='/runStatistic/exportList.shtml' />";
			document.getElementById('searchVO.vins').value = treeVins;
			
			document.getElementById('conditionselectform').submit();
		}
	}
	
	/**
	 * 加载列表信息
	 */
	function vehicleList() {
		var gala = jQuery('#usertbl');
		gala.flexigrid({
					url : '../runStatisticList/list.shtml',
					dataType : 'json',
					params : [ {
						name : 'searchVO.beginTime',
						value : jQuery('#beginTime').val()
					}, {
						name : 'searchVO.endTime',
						value : jQuery('#endTime').val()
					}, {
						name : 'searchVO.dateFlag',
						value : jQuery('#dateFlag').val()
					}
					],
					height : 600,
					width : 400,
					colModel : [
							{
								display : '厂区',
								name : 'short_name',
								width : 81,
								sortable : true,
								escape : true,
								align : 'center'
							},{
								display : '班车号',
								name : 'vehicle_code',
								width : 50,
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '车牌号',
								name : 'vehicle_ln',
								width : 60,
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '驾驶员',
								name : 'driver_name',
								width : 50,
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '载重里程',
								name : 'load_mileage',
								width : 50,
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '空驶里程',
								name : 'empty_mileage',
								width : 50,
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '总里程',
								name : 'total_mileage',
								width : 50,
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '耗油量',
								name : 'total_oil',
								width : 50,
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '百公里耗油量',
								name : 'oil_100',
								width : 80,
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '服务人次',
								name : 'load_number',
								width : 50,
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '早班',
								name : 'morning_cnt',
								width : 50,
								sortable : true,
								align : 'center',
								process : reWriteMorning
							}, {
								display : '晚班',
								name : 'night_cnt',
								width : 50,
								sortable : true,
								escape : true,
								align : 'center',
								process : reWriteNight
							} , {
								display : '合计',
								name : 'total_cnt',
								width : 50,
								sortable : true,
								escape : true,
								align : 'center',
								process : reWriteTotal
							} , {
								display : '维修次数',
								name : 'repare_cnt',
								width : 50,
								sortable : true,
								escape : true,
								align : 'center',
								process : reWriteRepare
							} , {
								display : '维修费用',
								name : 'cost_component',
								width : 50,
								sortable : true,
								escape : true,
								align : 'center'
							} , {
								display : '公差次数',
								name : 'p_cnt',
								width : 70,
								sortable : true,
								escape : true,
								align : 'center',
								process : reWritePublic
							}, {
								display : '公差里程',
								name : 'p_mileage',
								width : 70,
								sortable : true,
								escape : true,
								align : 'center'
							} ],
					sortname : '',
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
		
		//隐藏标题栏
		jQuery(".flexigrid div.hDivBox").hide();
		AutoWidthHeight();
	}
	//早班
	function reWriteMorning(tdDiv,pid,row){
		return '<a href="javascript:void(0)" onclick="workDetailList(\'0\',\''+pid+'\',\''+row.cell[1]+'\',\''+row.cell[2]+'\')">'+tdDiv+'</a>';
	}
	//晚班
	function reWriteNight(tdDiv,pid,row){
		return '<a href="javascript:void(0)" onclick="workDetailList(\'1\',\''+pid+'\',\''+row.cell[1]+'\',\''+row.cell[2]+'\')">'+tdDiv+'</a>';
	}
	//早晚合计
	function reWriteTotal(tdDiv,pid,row){
		return '<a href="javascript:void(0)" onclick="workDetailList(\'\',\''+pid+'\',\''+row.cell[1]+'\',\''+row.cell[2]+'\')">'+tdDiv+'</a>';
	}
	//维修
	function reWriteRepare(tdDiv,pid,row){
		if(tdDiv=='/'){
			return tdDiv;
		}
		return '<a href="javascript:void(0)" onclick="repaireDetailList(\''+pid+'\',\''+row.cell[1]+'\',\''+row.cell[2]+'\')">'+tdDiv+'</a>';
	}
	//公差
	function reWritePublic(tdDiv,pid,row){
		return '<a href="javascript:void(0)" onclick="pubDetailList(\''+pid+'\',\''+row.cell[1]+'\',\''+row.cell[2]+'\')">'+tdDiv+'</a>';
	}
	/**
	 * 点击本月、本年、自定义
	 */
	function changeAcolor(obj){
		jQuery("a.buleLink").addClass('blackLink');
		jQuery("a.buleLink").removeClass('buleLink');
		jQuery(obj).removeClass('blackLink');
		jQuery(obj).addClass('buleLink');
		
		if(obj.id=='amonth'){
			jQuery('#dateFlag').val('month');
			jQuery('#beginTime').attr('disabled','disabled');
			jQuery('#endTime').attr('disabled','disabled');
		}else if(obj.id=='ayear'){
			jQuery('#dateFlag').val('year');
			jQuery('#beginTime').attr('disabled','disabled');
			jQuery('#endTime').attr('disabled','disabled');
		}else if(obj.id=='afree'){
			jQuery('#dateFlag').val('free');
			jQuery('#beginTime').removeAttr('disabled');
			jQuery('#endTime').removeAttr('disabled');
		}
		searchList();
	}
	/**
	 * 选择树上车辆标点方法，list为vin数组，flag为选择状态
	 */
	var treeVins = '';
	function decodeSelectV(list, flag) {
		var strsql = "";
		if (list.length > 0) {
			//if (flag == true) {
				for ( var i = 0; i < list.length; i++) {
					if (i == list.length - 1) {
						strsql += "'" + list[i] + "'";
					} else {
						strsql += "'" + list[i] + "',";
					}
				}
			//}
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
		jQuery('#usertbl').flexOptions({
			newp : 1,
			params : [ {
				name : 'searchVO.beginTime',
				value : jQuery('#beginTime').val()
			}, {
				name : 'searchVO.endTime',
				value : jQuery('#endTime').val()
			}, {
				name : 'searchVO.dateFlag',
				value : jQuery('#dateFlag').val()
			}, {
				name : 'searchVO.vins',
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
		        width_offset:45,// 该值各页面根据自己的页面布局调整
		        is_handler: false
		    });	
		}else{//隐藏状态
			jQuery('#middeldiv').hide();
			jQuery('#leftdiv').show();
			jQuery('#content').mk_autoresize({
		        width_exclude: '#content_leftside',
		        width_include: '.flexigrid',
		        width_offset: 23,// 该值各页面根据自己的页面布局调整
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
			min_width : 1430,
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
			height_offset : 100
		});
		//计算右侧宽度
		jQuery('#content').mk_autoresize({
		    width_exclude:'#content_leftside',
		    width_include: '.flexigrid',
		    width_offset: 23
		});
	}
	//初始化,装载树
	jQuery(document).ready(function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
		//默认加载表格
		vehicleList();
		//自适应
		//AutoWidthHeight();

	});
</script>


