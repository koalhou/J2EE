<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function auto_size(){
	//计算中区高度
	jQuery('#content').mk_autoresize( {
		height_include : [ '#content_rightside', '#content_leftside' ],
		height_offset : 0,
		width_exclude: ['#content_leftside'],
		width_include : [ '#content_rightside'],
		width_offset : 1
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize({
		height_exclude : ['.treeTab', '.newsearchPlan'],
		height_include : '#treeDemoDiv',
		width_include : '#treeDemoDiv',
		height_offset : 10
	});

	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize( {
		height_exclude : '.titleBar',
		height_include : '#statusManger',
		height_offset : 0,
		width_include : [ '#statusManger','.titleBar'],
		width_offset : 0
	});	

	jQuery('#statusManger').mk_autoresize({
		height_include : '.list-area',
		height_offset : 0,
		width_include : [ '.list-area'],
		width_offset : 0
	});	
	jQuery('.list-area').mk_autoresize({
	  height_include: '#tableparent .bDiv',
	    height_offset: 110, // 该值各页面根据自己的页面布局调整
	    width_include: '#tableparent .flexigrid',
	    width_offset: 10// 该值各页面根据自己的页面布局调整
	});
}
/**
 * 左侧树区域显示控制
 */
function HideandShowControl(){
	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
		jQuery('#middeldiv').css("display","block");
		jQuery('#leftdiv').css("display","none");
		treeHide();
		
	}else{//隐藏状态
		jQuery('#middeldiv').css("display","none");
		jQuery('#leftdiv').css("display","block");
		treeShow();
	}
}
function treeHide(){
	jQuery('#content').mk_autoresize({
		width_exclude: '#content_middelside',
		width_include: '#content_rightside',
		width_offset: 2,// 该值各页面根据自己的页面布局调整
		is_handler: false
	});
	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize({
		height_exclude : '.titleBar' ,
		height_include : '.rmMain',
		height_offset : 0
	});
	jQuery('.rmMain').mk_autoresize({
		height_exclude : ['#rm_route_big'],
		height_offset : 80,
		width_offset : 0
	});
	jQuery('#statusManger').mk_autoresize({
		height_include : '.list-area',
		height_offset : 0,
		width_include : [ '.list-area'],
		width_offset : 0
	});	
	jQuery('.list-area').mk_autoresize({
	    height_include: '#tableparent .bDiv',
	    height_offset: 110, // 该值各页面根据自己的页面布局调整
	    width_include: '#tableparent .flexigrid',
	    width_offset: 10// 该值各页面根据自己的页面布局调整
	});
}
function treeShow(){
	jQuery('#content').mk_autoresize({
        width_exclude: ['#content_leftside','#content_middelside'],
        width_include: '#content_rightside',
        width_offset: 2,// 该值各页面根据自己的页面布局调整
        is_handler: false
    });
	jQuery('#content_leftside').mk_autoresize({
		height_exclude : [ '.treeTab', '.newsearchPlan'],
		height_include : '#treeDemoDiv',
		height_offset : 10
	});
	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize({
		height_exclude : '.titleBar' ,
		height_include : '.rmMain',
		height_offset : 0
	});
	jQuery('.list-area').mk_autoresize({
	    height_include: '#tableparent .bDiv',
	    height_offset: 110, // 该值各页面根据自己的页面布局调整
	    width_include: '#tableparent .flexigrid',
	    width_offset: 10// 该值各页面根据自己的页面布局调整
	});
}
///左侧树，查询线路
function searchRoute() {
	routename = jQuery("#search_route_name").val();
	searchTree();
}

//右侧查询函数
function searchList(){
	if(selectType=='1'){		
		jQuery('#re_flag').val(1);
		searchCarRunList();
	} else{
		jQuery('#re_flag').val(0);
		searchOrganRunList();
	}
}
var selectType="1";// 企业/车辆选中类型 0：企业 1：车辆
function mytreeonClick(type, treeId){
	if(type=='1'){
		selectType='1';
		jQuery('#re_flag').val(1);
		selectCar(treeId);
	} else{
		selectType='0';
		jQuery('#re_flag').val(0);
		selectOrganization(treeId);
	}
}
//选择车辆后，刷新车辆运行日志数据
var finalVIN = "";
//var vehcle_vin="";
function selectCar(radioObj){
	finalVIN = radioObj;
	jQuery('#vehicle_vin').val(finalVIN);
	vehicle_ln=radioObj;
	searchCarRunList();
}
function selectOrganization(organObj){
	user_org_id=organObj;
	jQuery('#user_org_id').val(organObj);
	searchOrganRunList();	
}

function searchCarRunList() {
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '../driverecordList/driveRecordList.shtml',
		params: [{name: 'vehiclevin', value: finalVIN },
			    {name:'drivername',value:jQuery("#drivername").val()},
			    {name:'vehiclecode',value:jQuery("#choiceroutename").val()},
			    {name:'begintime',value:jQuery("#begintime").val()},
			    {name:'endtime',value:jQuery("#endtime").val()}
			    ]
	});
	jQuery('#galaList').flexReload();
}

function searchOrganRunList() {
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '../driverecordList/driveRecordList.shtml',
		params: [
				{name: 'organization_id', value: user_org_id },
				{name:'drivername',value:jQuery("#drivername").val()},
				{name:'vehiclecode',value:jQuery("#choiceroutename").val()},
			    {name:'begintime',value:jQuery("#begintime").val()},
			    {name:'endtime',value:jQuery("#endtime").val()}
		]
	});
	jQuery('#galaList').flexReload();
}
function exportData(){	
	if($("#galaList").find("td").length == 0){
		alert("没有统计数据!");
		return;	
	}
	if($("#galaList").flex_totalc > 50000){
		alert("无法导出，系统最多可一次导出5W条记录!");
		return;	
	}
	if(confirm("确定要导出驾驶员刷卡统计吗？")) {			
		document.getElementById('sortname').value = jQuery('#galaList').flex_sortname();	
		document.getElementById('sortorder').value = jQuery('#galaList').flex_sortorder();
		document.getElementById('export_form').submit();
	}
}

jQuery(function() {
	var gala = jQuery('#galaList');
	 gala.flexigrid({
		dataType: 'json',    //json格式
		height: 550,
		width: 1410,
		colModel : [ 
			{display: '序号',name : '', width : 50, sortable : true, align: 'center',escape: true},
			{display: '姓名',name : 'driver_name', width : 100, sortable : true, align: 'center',escape: true},
			/* {display: '员工号',name : "", width : 100, sortable : true, align: 'center',escape: true}, */
			{display: '班车号', name : "vehicle_code", width : 130, sortable : true, align: 'center',escape: true},
			{display: '车牌号', name : 'vehicle_ln', width : 140, sortable : true, align: 'center',toggle:false,escape: true},
			/* {display: '刷卡类型',name : 'load_number', width : 60, sortable : true, align: 'center',escape: true}, */
			{display: '线路名称',name : 'route_name', width : 180, sortable : true, align: 'center',escape: true},
			{display: '刷卡时间', name : 'terminal_time', width : 170, sortable : true, align: 'center',escape: true}
			/* {display: '刷卡地点', name : '', width : 130, sortable : true, align: 'center',escape: true} */
		],
		 sortname: 'terminal_time',
		 sortorder: 'desc',//升序OR降序
		 sortable: true, //是否支持排序
		 striped :true, // 是否显示斑纹效果，默认是奇偶交互的形式
		 usepager :true,//是否分页
		 resizable: false,//是否可以设置表格大小
		 useRp: true,// 是否可以动态设置每页显示的结果数
		 rp: 20,//每页显示记录数
		 rpOptions : [10,20,50,100], // 可选择设定的每页结果数
		 showTableToggleBtn: true
	 }); 
	 
});
jQuery(function() {
	//resizeBgDiv();
	auto_size();
	
});


</script>