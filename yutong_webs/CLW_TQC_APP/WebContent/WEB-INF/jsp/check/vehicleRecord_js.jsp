<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<script src="<s:url value='/scripts/swf/swfobject_modified.js'/>"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script>
	/**
	 * 异常导出列表
	 */
	function exportIllList() {
		if (jQuery('#illTabl').find("td").length == 0) {
			alert("没有要导出的异常用车!");
			return;
		}
		if (jQuery('#illTabl').flex_totalc > 50000) {
			alert("无法导出，系统最多可一次导出5W条记录!");
			return;
		}
		if (confirm("确定要导出异常用车记录吗？")) {
			document.getElementById('illform').action = "<s:url value='/checking/exportIllListAction.shtml' />";
			document.getElementById('illform').submit();
		}
	}
	/**
	 * 处理记录导出列表
	 */
	function exportOperatedList() {
		if (jQuery('#operatedTabl').find("td").length == 0) {
			alert("没有要导出的处理记录!");
			return;
		}
		if (jQuery('#operatedTabl').flex_totalc > 50000) {
			alert("无法导出，系统最多可一次导出5W条记录!");
			return;
		}
		if (confirm("确定要导出处理记录吗？")) {
			document.getElementById('operatedform').action = "<s:url value='/checking/exportOperatedListAction.shtml' />";
			document.getElementById('operatedform').submit();
		}
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
		var popheight = "540px";
		var popIframeH = "484px";
		if(jQuery(window).height() >= 900){
			var popwidth = "655px";
			var popheight = "716px";
			var popIframeH = "660px";	
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
	/**
	 * 意见批量处理弹出框
	 */
	function batchOperateShow() {
		//默认提示信息
		jQuery('#errormsg').text('');
		jQuery("input:radio[name='rtype']:checked").each(function() {
			jQuery(this).attr('checked', false);
		});
		jQuery('#desc').val('处理意见(必填)');
		//选择的行
		var ids = checkedIds();
		if (ids.length < 1) {
			alert('请选择要处理的异常用车记录!');
			return;
		}
		//弹出处理意见对话框
		//弹出覆盖层的大小自适应整个屏幕
		jQuery("#overDiv").css('display', 'block');
		jQuery("#msgDiv").css('display', 'block');
		jQuery(":radio[name='rtype']")[0].checked=true;
		calOverDiv();
	}
	//计算模态层大小
	function calOverDiv(){
		jQuery("#overDiv").height(jQuery(window).height());
		//设置弹出框显示在中心位置
		var height = (jQuery(window).height() - jQuery("#msgDiv").height()) / 2;
		var width = (jQuery(window).width() - jQuery("#msgDiv").width()) / 2;
		jQuery("#msgDiv").offset({
			top : height,
			left : width
		});
	}
	//意见批量处理提交
	function batchOperateOk() {

		var idsArray = checkedIds();
		var ids = idsArray.join(',');
		var type = jQuery("input:radio[name='rtype']:checked").val();
		var desc = jQuery.trim(jQuery('#desc').val());
		if (type == null) {
			jQuery('#errormsg').text('请选择是否为公车私用!');
			return false;
		}
		if (desc == '' || desc == null || desc == '处理意见(必填)') {
			jQuery('#errormsg').text('请填写处理意见!');
			return false;
		}
		if (desc.length > 50) {
			jQuery('#errormsg').text('处理意见不能超过50字!');
			return false;
		}
		jQuery.post("../checking/batchOperate.shtml", {
			'ids' : ids,
			'type' : type,
			'desc' : desc
		}, function(data) {
			closeDialog();
			illSearchList();//异常用车
		});
	}
	//关闭意见批量处理弹出框
	function closeDialog() {
		jQuery("#overDiv").css('display', 'none');
		jQuery("#msgDiv").css('display', 'none');
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
		//根据车辆vin查询“车辆巡检列表信息”
		treeVins = strsql;
		jQuery("#vehicleVin").val(treeVins);
		if(jQuery('#changeTable').css('display') != 'none'){
			illSearchList();//异常用车
		}else{
			operatedSearchList();//处理记录
		}
	}
	/**
	 * 加载公车私用巡检列表信息
	 */
	function illList() {
		jQuery('input').each(function(){
			var value=jQuery.trim(jQuery(this).val());
			jQuery(this).val(value);
		});
		var gala = jQuery('#illTabl');
		gala
				.flexigrid({
					url : '../checkingList/illList.shtml',
					dataType : 'json',
					params : [ {
						name : 'searchVO.beginTime',
						value : jQuery('#beginTime', '#illform').val()
					}, {
						name : 'searchVO.endTime',
						value : jQuery('#endTime', '#illform').val()
					}, {
						name : 'searchVO.operate_state',
						value : '0'
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
								width : calcColumn(0.02, 80, 0),
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
								display : '联系方式',
								name : 'driver_tel',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '授权运行时间',
								name : 'effect_time',
								width : calcColumn(0.1, 80, 0),
								escape : true,
								align : 'center'
								//process : reWriteTime
							}, {
								display : '开始时间',
								name : 'alarm_time',
								width : calcColumn(0.1, 80, 0),
								sortable : true,
								align : 'center',
								toggle : false
							}, {
								display : '结束时间',
								name : 'alarm_end_time',
								width : calcColumn(0.1, 80, 0),
								sortable : true,
								align : 'center',
								toggle : false
							}, {
								display : '用车时长(min)',
								name : 'use_time',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '行驶里程(km)',
								name : 'mileage',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '操作',
								name : '',
								width : calcColumn(0.04, 40, 0),
								align : 'center',
								process : reWriteLink1
							} ],
					sortname : 'alarm_time',
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
		//自适应
		AutoWidthHeight();
	}
	function reWriteLink1(tdDiv, pid, row) {
		return '<a href="javascript:void(0)" onclick="showMap(\''+pid+'\',\''+row.cell[1]+'\',\''+row.cell[2]+'\');">点击处理</a>';
	}
	function reWriteLink2(tdDiv, pid, row) {
		return '<a href="javascript:void(0)" onclick="showMap(\''+pid+'\',\''+row.cell[1]+'\',\''+row.cell[2]+'\');">查看轨迹</a>';
	}
	function showMap(id,code,ln) {
		var iframeObj=document.getElementById('iframeshowArea');
		if(iframeObj!=null){
			iframeObj.src="";
		}
		//显示地图泡泡页
		var title=code+','+ln;
		jQuery('#popArea').mk_sidelayer('set_title',encodeHtml(title));
		jQuery('#popArea').mk_sidelayer('reload','<s:url value="/checking/loadIframe.shtml" />?id='+id);
	}
	/**
	 * 加载公车私用巡检列表信息
	 */
	function operatedList() {
		jQuery('input').each(function(){
			var value=jQuery.trim(jQuery(this).val());
			jQuery(this).val(value);
		});
		var gala = jQuery('#operatedTabl');
		gala
				.flexigrid({
					url : '../checkingList/operatedList.shtml',
					dataType : 'json',
					params : [ {
						name : 'searchVO.beginTime',
						value : jQuery('#beginTime1', '#operatedform').val()
					}, {
						name : 'searchVO.endTime',
						value : jQuery('#endTime1', '#operatedform').val()
					}, {
						name : 'searchVO.operate_state',
						value : '1'
					}, {
						name : 'searchVO.operate_type',
						value : jQuery('#operate_type', '#operatedform').val()
					} ],
					height : 300,
					width : 400,
					colModel : [
							{
								display : '<input id="allCheck" name="allCheck" type="checkbox" value="allCheck" onclick="javascript:setOrCancelSelect(this)"/>',
								name : '',
								width : calcColumn(0, 20, 0),
								hide:true,
								process : reWriteCheckBox
							}, 
							{
								display : '班车号',
								name : 'vehicle_code',
								width : calcColumn(0.01, 40, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '车牌号',
								name : 'vehicle_ln',
								width : calcColumn(0.02, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '驾驶员',
								name : 'driver_name',
								width : calcColumn(0.02, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '联系方式',
								name : 'driver_tel',
								width : calcColumn(0.03, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '授权运行时间',
								name : 'effect_time',
								width : calcColumn(0.06, 80, 0),
								escape : true,
								align : 'center'
								//process : reWriteTime
							}, {
								display : '开始时间',
								name : 'alarm_time',
								width : calcColumn(0.08, 80, 0),
								sortable : true,
								align : 'center',
								toggle : false
							}, {
								display : '结束时间',
								name : 'alarm_end_time',
								width : calcColumn(0.08, 80, 0),
								sortable : true,
								align : 'center',
								toggle : false
							}, {
								display : '用车时长(min)',
								name : 'use_time',
								width : calcColumn(0.02, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '行驶里程(km)',
								name : 'mileage',
								width : calcColumn(0.02, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '类别',
								name : 'operate_type',
								width : calcColumn(0.04, 40, 0),
								sortable : true,
								align : 'center',
								process : reWriteType
							}, {
								display : '处理意见',
								name : 'opeerate_desc',
								width : calcColumn(0.06, 80, 0),
								align : 'center'
							}, {
								display : '处理人',
								name : 'user_name',
								width : calcColumn(0.02, 80, 0),
								sortable : true,
								align : 'center'
							}, {
								display : '处理日期',
								name : 'confirm_time',
								width : calcColumn(0.04, 80, 0),
								sortable : true,
								align : 'center',
								toggle : false
							}, {
								display : '操作',
								name : '',
								width : calcColumn(0.04, 40, 0),
								align : 'center',
								process : reWriteLink2
							} ],
					sortname : 'confirm_time',
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
		//自适应
		AutoWidthHeight();
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
	function reWriteType(tdDiv, pid, row) {
		var stype = '公车私用';
		if (tdDiv == '1') {
			stype = '正常用车';
		}
		return stype;
	}
	function reWriteTime(tdDiv, pid, row){
		var str="";
		if(tdDiv==','){
			return "";
		}
		var arr=tdDiv.split(',');
		var start_times=arr[0].split('/');
		var end_times=arr[1].split('/');
		var len=start_times.length;
		for(var i=0;i<len;i++){
			if(i==len-1){
				str += start_times[i]+'-'+end_times[i];
			}else{
				str += start_times[i]+'-'+end_times[i]+'/';
			}
		}
		return str;
	}
	//查询公车私用异常查询
	function illSearchList() {
		jQuery('input').each(function(){
			var value=jQuery.trim(jQuery(this).val());
			jQuery(this).val(value);
		});
		jQuery('#illTabl').flexOptions({
			newp : 1,
			params : [{
				name : 'searchVO.beginTime',
				value : jQuery('#beginTime', '#illform').val()
			}, {
				name : 'searchVO.endTime',
				value : jQuery('#endTime', '#illform').val()
			}, {
				name : 'searchVO.operate_state',
				value : '0'
			}, {
				name : 'searchVO.vins',
				value : treeVins
			} ]
		});
		jQuery('#illTabl').flexReload();
	}
	//查询公车私用处理记录查询
	function operatedSearchList() {
		jQuery('input').each(function(){
			var value=jQuery.trim(jQuery(this).val());
			jQuery(this).val(value);
		});
		jQuery('#operatedTabl').flexOptions({
			newp : 1,
			params : [{
				name : 'searchVO.beginTime',
				value : jQuery('#beginTime1', '#operatedform').val()
			}, {
				name : 'searchVO.endTime',
				value : jQuery('#endTime1', '#operatedform').val()
			}, {
				name : 'searchVO.operate_state',
				value : '1'
			}, {
				name : 'searchVO.operate_type',
				value : jQuery('#operate_type', '#operatedform').val()
			}, {
				name : 'searchVO.vins',
				value : treeVins
			} ]
		});
		jQuery('#operatedTabl').flexReload();
	}
	//公车巡检左侧树查询
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
		        width_offset:25,// 该值各页面根据自己的页面布局调整
		        is_handler: false
		    });	
		}else{//隐藏状态
			jQuery('#middeldiv').hide();
			jQuery('#leftdiv').show();
			jQuery('#content').mk_autoresize({
		        width_exclude: '#content_leftside',
		        width_include: '.flexigrid',
		        width_offset: 1,// 该值各页面根据自己的页面布局调整
		        is_handler: false
		    });
		}
	}
	var defaultTab=true;//判断是否为第一个页签
	function AutoWidthHeight() {
		//计算右侧区域高度
		jQuery(window).mk_autoresize({
			height_include : '#content',
			height_exclude : [ '#header', '#footer' ],
			height_offset : 0,
			width_include : [ '#header', '#content', '#footer' ],
			width_offset : 0
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
		//表格高度自适应
		var tab_height_offset=80;
		if(!defaultTab){
			tab_height_offset=115;
		}
		jQuery('#content_rightside').mk_autoresize({
			height_exclude : ['.titleBar','.alarm_tab','.reportOnline2'],
			height_include : '.bDiv',
			height_offset : tab_height_offset
		});
		//表格自适应
		jQuery('#content').mk_autoresize({
		    width_exclude:'#content_leftside',
		    width_include: '.flexigrid',
		    width_offset: 1// 该值各页面根据自己的页面布局调整
		  });
	}
	//窗口自适应
	jQuery(window).wresize(function(){
		 AutoWidthHeight();
		 popAutoWH();
		 if(jQuery("#overDiv").css('display') == 'block'){
			 jQuery("#overDiv").height(jQuery("#wrapper").height());
			 calOverDiv();
		 }
	});
	//初始化,装载树
	jQuery(document).ready(function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
		//表格切换
		jQuery('.alarm_tab a').click(function() {
			jQuery(this).parent().find('a').removeClass('alarm_tabs');
			jQuery(this).addClass('alarm_tabs');
			if (jQuery(this).attr('flag') == '1') {
				jQuery('#wearTable').hide();
				jQuery('#changeTable').show();
				defaultTab=true;
				//illList();
				illSearchList();
				//自适应
				//AutoWidthHeight();
			} else {
				jQuery('#wearTable').show();
				jQuery('#changeTable').hide();
				defaultTab=false;
				//operatedList();
				operatedSearchList();
				//自适应
				//AutoWidthHeight();
			}
		});
		//默认加载表格
		illList();
		operatedList();
		//自适应
		//AutoWidthHeight();
		//初始化地图弹出框
		initpop();
		//地图弹出框自适应
		popAutoWH();
	});
</script>


