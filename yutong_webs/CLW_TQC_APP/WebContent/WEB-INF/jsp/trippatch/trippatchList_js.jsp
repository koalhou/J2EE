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
	 * 新增
	 */
	function addTripPatch() {
		window.location.href = "<s:url value='/tripPatch/showAddPage.shtml' />";
	}
	/**
	 * 删除
	 */
	function delTripPatch() {
		var idArr = checkedIds();
		if (idArr.length < 1) {
			alert('请选择要删除的行');
			return false;
		}
		if (!confirm('确定要删除吗？')) {
			return false;
		}
		var ids = idArr.join(',');
		jQuery.post("../tripPatch/delTripPatch.shtml", {
			'ids' : ids
		}, function(data) {
			//alert(data);
			jQuery('.actionMessage').empty();
			jQuery('#tipmsg').html(data);
			searchList();
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
			document.getElementById('conditionselectform').action = "<s:url value='/tripPatch/exportTripPatchList.shtml' />";
			document.getElementById('searchVO.sortName').value = jQuery('#usertbl').flex_sortname();	
			document.getElementById('searchVO.sortOrder').value = jQuery('#usertbl').flex_sortorder();
			
			document.getElementById('conditionselectform').submit();
		}
	}
	function showMap(start_time, end_time, mileage, vin, title) {
		var iframeObj = document.getElementById('iframeshowArea');
		if (iframeObj != null) {
			iframeObj.src = "";
		}
		//显示地图泡泡页
		jQuery('#popArea').mk_sidelayer('set_title', encodeHtml(title));
		//jQuery('#popArea').mk_sidelayer('reload','<s:url value="/tripPatch/loadIframe.shtml" />?id='+id);
		jQuery('#popArea').mk_sidelayer(
				'reload',
				encodeURI('<s:url value="/tripPatch/loadIframe.shtml" />?vin='
						+ vin + '&start_time=' + start_time + '&end_time='
						+ end_time + '&mg=' + mileage));
	}
	/**
	 * 初始化地图弹出框信息
	 */
	function initpop() {
		jQuery('#popArea').mk_sidelayer({
			'height' : '610px',
			'width' : '600px',
			//'url': '<s:url value="../checking/loadIframe.shtml" />',
			'is_show' : false,
			'can_close' : true,
			close_callback : function() {
				var srcPage = document.getElementById('iframeshowArea');
				if (srcPage != null) {
					srcPage.src = '';
				}
				var is_show=jQuery('#popArea').mk_sidelayer('is_show');
				if(is_show){
					jQuery('#popArea').mk_sidelayer('close');
				}
			},
			hide_callback : function() {
				var srcPage = document.getElementById('iframeshowArea');
				if (srcPage != null) {
					srcPage.src = '';
				}
			}
		});
	}
	function popAutoWH() {
		var popwidth = "500px";
		var popheight = "450px";
		var popIframeH = "396px";
		if(jQuery(window).height() >= 900){
			var popwidth = "655px";
			var popheight = "610px";
			var popIframeH = "550px";	
		} 
		jQuery('#popArea').mk_sidelayer('set_width', popwidth);
		jQuery('#popArea').mk_sidelayer('set_height', popheight);
		if(jQuery('#popArea').mk_sidelayer("is_show") ==true){
			jQuery("#popArea").width(popwidth);
			jQuery("#popArea").height(popheight);
			jQuery("#iframeshowArea").width(popwidth);
			jQuery("#iframeshowArea").height(popIframeH);
		}
	}
	//选中的车辆巡检信息行
	function checkedIds() {
		var ids = new Array();
		jQuery("input[name='busCheck']").each(function(idx, em) {
			if (jQuery(this).attr("checked")) {
				ids.push(jQuery(this).val());
			}
		});
		return ids;
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
		jQuery("#vehicleVin").val(treeVins);
		searchList();
	}
	/**
	 * 加载列表信息
	 */
	function vehicleList() {
		var gala = jQuery('#usertbl');
		gala
				.flexigrid({
					url : '../tripPatchList/tripPatchList.shtml',
					dataType : 'json',
					params : [ {
						name : 'searchVO.start_time',
						value : jQuery('#beginTime').val()
					}, {
						name : 'searchVO.end_time',
						value : jQuery('#endTime').val()
					}, {
						name : 'searchVO.type',
						value : jQuery('#use_type').val()
					}, {
						name : 'searchVO.route_name',
						value : jQuery('#route_name').val()
					} ],
					height : 300,
					width : 400,
					colModel : [
							{
								display : '<input id="allCheck" name="allCheck" type="checkbox" value="allCheck" onclick="javascript:setOrCancelSelect(this)"/>',
								name : '',
								width : calcColumn(0.01, 40, 0),
								process : reWriteCheckBox
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
								display : '用车类型',
								name : 'type',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center',
								process : reWriteUseType
							}, {
								display : '行驶线路',
								name : 'route_name',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '线路类别',
								name : 'route_type',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center',
								process : reWriteRouteType
							}, {
								display : '开始时间',
								name : 'start_time',
								width : calcColumn(0.06, 80, 0),
								sortable : true,
								align : 'center',
								toggle : false
							}, {
								display : '结束时间',
								name : 'end_time',
								width : calcColumn(0.06, 80, 0),
								sortable : true,
								align : 'center',
								toggle : false
							}, {
								display : '里程(km)',
								name : 'mileage',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '乘车人数',
								name : 'count',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								align : 'center'
							}, {
								display : '操作人',
								name : 'update_by',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								align : 'center'
							}, {
								display : '最后修改时间',
								name : 'operate_time',
								width : calcColumn(0.06, 80, 0),
								sortable : true,
								align : 'center'
							}, {
								display : '查看轨迹',
								name : 'vehicle_vin',
								width : calcColumn(0.05, 80, 0),
								escape : true,
								align : 'center',
								process : reWriteTrack
							} ],
					sortname : 'operate_time',
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
		AutoWidthHeight();
	}
	function reWriteUseType(tdDiv, pid, row) {
		if (tdDiv == '1') {
			return "通勤";
		} else {
			return "公差";
		}
	}
	function reWriteRouteType(tdDiv, pid, row) {
		var typeName = "";
		if (tdDiv == '0') {
			typeName = "早班";
		}else if (tdDiv == '1') {
			typeName = "晚班";
		}else if (tdDiv == '2') {
			typeName = "厂内通勤";
		} 
		return typeName;
	}
	function reWriteTrack(tdDiv, pid, row) {
		var title = row.cell[1] + ',' + row.cell[2];
		var start_time = row.cell[7];
		var end_time = row.cell[8];
		var mileage = row.cell[9];
		return '<a href="javascript:void(0)" onclick="showMap(\'' + start_time
				+ '\',\'' + end_time + '\',\'' + mileage + '\',\'' + tdDiv
				+ '\',\'' + title + '\');">查看轨迹</a>';
	}
	//添加复选框
	function reWriteCheckBox(tdDiv, pid, row) {
		//value为告警ID
		return '<input name="busCheck"  type="checkbox" value="'+pid+'"/>';
	}
	//复选框选择或取消所有
	function setOrCancelSelect(obj) {
		if (jQuery(obj).attr("checked")) {
			jQuery("input[name='busCheck']").each(function() {
				jQuery("input[name='busCheck']").attr("checked", "true");
			});
		} else {
			jQuery("input[name='busCheck']").each(function() {
				jQuery("input[name='busCheck']").removeAttr("checked");
			});
		}
	}

	//查询列表
	function searchList() {
		jQuery('input').each(function(){
			var value=jQuery.trim(jQuery(this).val());
			jQuery(this).val(value);
		});
		jQuery('#usertbl').flexOptions({
			newp : 1,
			params : [{
				name : 'searchVO.start_time',
				value : jQuery('#beginTime').val()
			}, {
				name : 'searchVO.end_time',
				value : jQuery('#endTime').val()
			}, {
				name : 'searchVO.type',
				value : jQuery('#use_type').val()
			}, {
				name : 'searchVO.route_name',
				value : formatSpecialChar(jQuery('#route_name').val())
			}, {
				name : 'searchVO.vins',
				value : treeVins
			} ]
		});
		jQuery('#usertbl').flexReload();
	}
	//左侧树查询
	function searchTreeClick() {
		jQuery('#vehicleLn').val(jQuery('#vehicleLntext').val());
		searchTree();
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
		        width_offset:48,// 该值各页面根据自己的页面布局调整
		        is_handler: false
		    });	
		}else{//隐藏状态
			jQuery('#middeldiv').hide();
			jQuery('#leftdiv').show();
			jQuery('#content').mk_autoresize({
		        width_exclude: '#content_leftside',
		        width_include: '.flexigrid',
		        width_offset: 26,// 该值各页面根据自己的页面布局调整
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
			height_exclude : ['.titleBar','.reportOnline2','.titleStyle'],
			height_include : '.bDiv',
			height_offset : 100
		});
		//计算右侧宽度
		jQuery('#content').mk_autoresize({
		    width_exclude:'#content_leftside',
		    width_include: '.flexigrid',
		    width_offset: 26
		});
	}
	//窗口自适应
	jQuery(window).wresize(function(){
		 AutoWidthHeight();
		 popAutoWH();
	});
	//初始化,装载树
	jQuery(document).ready(function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
		//默认加载表格
		vehicleList();
		//自适应
		//AutoWidthHeight();
		initpop();
		//地图弹出框自适应
		popAutoWH();

	});
</script>


