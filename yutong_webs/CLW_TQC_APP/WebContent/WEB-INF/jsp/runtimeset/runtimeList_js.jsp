<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/flexigrid/jquery.wresize.js'/>"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<script>
   //修改状态
   function changeStatus(status){
	   var ids=checkedIds();
		if(ids.length>1){
			alert('只能选择一个!');
			return false;
		}
		if(ids.length<1){
			alert('请选择一条记录');
			return false;
		}
	   jQuery.post('<s:url value="/runtimeSet/updateStatus.shtml" />',{'runtime.status':status,'runtime.time_id':ids[0]},function(data){
		   if(data=='success'){
			   alert('修改成功');
		   }else{
			   alert('修改失败');
		   }
	   });
	   searchList();
   }
   //添加
	function addpage(){
		window.location.href='<s:url value="/runtimeSet/addPage.shtml" />';
	}
	//修改
	function updatepage(pid){
		window.location.href='<s:url value="/runtimeSet/updatePage.shtml" />?id='+pid;
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
					strsql = "'"+list[i]+"'";
					//if (i == list.length - 1) {
					//	strsql += "'" + list[i] + "'";
					//} else {
					//	strsql += "'" + list[i] + "',";
					//}
				}
			}
		}
		//根据车辆vin查询列表信息
		treeVins = strsql;
		searchList();
	}
	/**
	 * 加载列表信息
	 */
	function vehicleList() {
		//document.getElementById('Below_new').style.display = 'none';
		var gala = jQuery('#usertbl');
		gala.flexigrid({
					url : '<s:url value="/runtimeSet/runtimeList.shtml" />',
					dataType : 'json',
					params : [ {
						name : 'org_id',
						value : ''
					}],
					height : 300,
					width : 400,
					colModel : [
							{
								display : '组织',
								name : 'org_name',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '授权运行时间',
								name : 'start_time',
								width : calcColumn(0.15, 80, 0),
								escape : true,
								align : 'center',
								process : reWriteTime
							}, {
								display : '参数生效周期',
								name : 'effect_often',
								width : calcColumn(0.1, 80, 0),
								escape : true,
								align : 'center',
								process : reWriteEffect
							}, {
								display : '备注',
								name : 'remark',
								width : calcColumn(0.15, 80, 0),
								escape : true,
								align : 'center'
							}, {
								display : '状态',
								name : 'status',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center',
							    process : reWriteStatus
							}, {
								display : '操作人',
								name : 'oper_name',
								width : calcColumn(0.05, 80, 0),
								sortable : true,
								escape : true,
								align : 'center'
							}, {
								display : '最后修改时间',
								name : 'modify_time',
								width : calcColumn(0.15, 80, 0),
								sortable : true,
								align : 'center',
								toggle : false
							}, {
								display : '操作',
								name : 'operator',
								width : calcColumn(0.05, 80, 0),
								escape : true,
								align : 'center',
							    process : reWriteOperate
							}],
					sortname : 'modify_time',
					sortorder : 'desc',
					sortable : true,
					striped : true,
					usepager : true,
					resizable : true,
					useRp : true,
					rp : 20,
					rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
					showTableToggleBtn : true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错
					onSuccess : function() {//等待ajax查询数据完成，添加点击行事件
					}
				});
		AutoWidthHeight();
	}
	function reWriteStatus(tdDiv, pid, row){
		var statues="启用";
		if(tdDiv=='0'){
			statues="禁用";
		}
		return statues;
	}
	function reWriteTime(tdDiv, pid, row){
		var str="";
		if(tdDiv==''){
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
	function reWriteEffect(tdDiv, pid, row){
		var weeks=['周一','周二','周三','周四','周五','周六','周日'];
		var str="";
		if(tdDiv==''){
			return "";
		}
		var len=tdDiv.length;
		for(var i=0;i<len;i++){
			var s=tdDiv.charAt(i);
			if(s=='1'){
				str += weeks[i]+'/';
			}
		}
		str=str.substring(0,str.length-1);
		return str;
		
	}
	//列表修改
	function reWriteOperate(tdDiv, pid, row){
		return '<a href="javascript:void(0)" onclick="updatepage(\''+tdDiv+'\')">修改</a>';
	}

	//查询列表
	function searchList() {
		jQuery('#usertbl').flexOptions({
			newp : 1,
			params : [ {
				name : 'org_id',
				value : treeVins
			}]
		});
		jQuery('#usertbl').flexReload();
	}
	
	
	//左侧树查询
	function searchTreeClick() {
		document.getElementById('Below_new').style.display = 'none';
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
			height_exclude : '.titleBar',
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
	//初始化,装载树
	jQuery(document).ready(function() {
		treeType="listTree";
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
		//默认加载表格
		vehicleList();
		//自适应
		//AutoWidthHeight();
	});
</script>


