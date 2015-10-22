<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
	<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/flexigrid/jquery.wresize.js'/>"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<script type="text/javascript">
function sumStyle(tdDiv,pid,row){
	if(pid=="sumid"){
		//alert(pid + "-----" + row.cell[11]);
		jQuery("#totalruntime").html(row.cell[4] + "(min)");
		//jQuery("#totalidletime").html(row.cell[5] + "(min)");
		jQuery("#totalspdoil").html(row.cell[5] + "(L)");
		jQuery("#totalmileage").html(row.cell[6] + "(km)");
	}else{
		return tdDiv;
	}
}
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
	    height_offset: 130, // 该值各页面根据自己的页面布局调整
	    width_include: '#tableparent .flexigrid',
	    width_offset: 10// 该值各页面根据自己的页面布局调整
	});
}
/**
 * 左侧树区域显示控制
 */
// function HideandShowControl(){
// 	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
// 		jQuery('#middeldiv').css("display","block");
// 		jQuery('#leftdiv').css("display","none");
// 		treeHide();
		
// 	}else{//隐藏状态
// 		jQuery('#middeldiv').css("display","none");
// 		jQuery('#leftdiv').css("display","block");
// 		treeShow();
// 	}
// }
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
	    height_offset: 130, // 该值各页面根据自己的页面布局调整
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
	jQuery('.rmMain').mk_autoresize({
		height_exclude : ['#rm_route_big'],
		height_offset : 80
	});
	jQuery('.rmMain').mk_autoresize( {
		width_include : ['#rm_route1','#rm_route2','#rm_route3','#rm_route4','#rm_route5','#rm_route6','#rm_route7'],
		width_offset : 80
	});
	jQuery('.list-area').mk_autoresize({
	    height_include: '#tableparent .bDiv',
	    height_offset: 130, // 该值各页面根据自己的页面布局调整
	    width_include: '#tableparent .flexigrid',
	    width_offset: 10// 该值各页面根据自己的页面布局调整
	});
}
function choiceCarln() {
	art.dialog.open("<s:url value='/infomanage/getchooseCarList.shtml' />",{
		title:"车辆信息",
		lock:true,
		width:700,
		height:435
	});
} 
function choiceRoute() {
	//var url = jQuery("#route_class").val()=='-1'?"<s:url value='/infomanage/getchooseRoutenores.shtml' />?route_class="+'2':"<s:url value='/infomanage/getchooseRoutenores.shtml' />?route_class="+jQuery("#route_class").val();
	var url ="<s:url value='/infomanage/getchooseRoutenores.shtml' />?route_class="+jQuery("#route_class").val();
	art.dialog.open(url,{
		title:"线路查询",
		lock:true,
		width:260,
		height:435
	});
} 
///左侧树，查询线路
function searchRoute() {
	routename = jQuery("#search_route_name").val();
	searchTree();
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
	//jQuery("#vehicleVin").val(treeVins);
	searchList();
}

//右侧查询函数
function searchList(){
	
//	if(treeVins==""){
//		alert("请选择车辆！");
//		return false;
//	}
	
	
	var startDate=jQuery("#begintime").val();
	var endDate=jQuery("#endtime").val();
	
	
	var startTime = new Date(startDate.replace(/-/g,"/")).getTime();
	var endTime = new Date(endDate.replace(/-/g,"/")).getTime();
	
	
	var dates = ((endTime-startTime))/(1000*60*60*24);     
	
	if(dates>6){
		alert("查询时间不能大于7天!");
		return false;
	}
	
	if(selectType=='1'){		
		jQuery('#re_flag').val(1);
		searchCarRunList();
	}
	else{
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
	}
	else{
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


var user_beg_time = "";
var user_end_time = "";

function searchCarRunList() {
	jQuery("#totalruntime").html("");
	//jQuery("#totalidletime").html("");
	jQuery("#totalspdoil").html("");
	jQuery("#totalmileage").html("");
	
	var user_beg_time = jQuery('#begintime').val();
	var user_end_time = jQuery('#endtime').val();	
	var user_org_id =jQuery('#user_org_id').val();
	
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '<s:url value="/daydriverecordsearch/dayDriveRecordList.shtml" />',
		params: [
		        //{name: 'queryObj.VIN', value: finalVIN },
			    {name:'begintime',value:user_beg_time},
			    {name:'endtime',value:user_end_time},
			    {name:'queryObj.vins',value:treeVins}
			    ]
	});
	jQuery('#galaList').flexReload();
}

function searchOrganRunList() {
	jQuery("#totalruntime").html("");
	//jQuery("#totalidletime").html("");
	jQuery("#totalspdoil").html("");
	jQuery("#totalmileage").html("");
	
	var user_beg_time = jQuery('#begintime').val();
	var user_end_time = jQuery('#endtime').val();	
	var user_org_id =jQuery('#user_org_id').val();
	
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '<s:url value="/daydriverecordsearch/dayDriveRecordList.shtml" />',
		params: [
			    {name:'begintime',value:user_beg_time},
			    {name:'endtime',value:user_end_time},
			    {name:'queryObj.user_organization_id',value:user_org_id},
			    {name:'queryObj.vins',value:treeVins}
			    ]
	});
	jQuery('#galaList').flexReload();
}
function exportData(){	
	if(jQuery("#galaList").find("td").length == 0){
		alert("没有行车记录!");
		return;	
	}
	var start_time=document.getElementById('begintime').value.replace(/-/g,"/");
	 var end_time=document.getElementById('endtime').value.replace(/-/g,"/");
	
    var startdate=new Date(start_time);	
    var enddate=new Date(end_time);
	 var idays = parseInt((enddate - startdate)/24/ 1000 / 60 / 60);
		if(idays> 6){
			alert("时间跨度大于7天,不可导出");
			return;
		}
	if(confirm("确定要导出行车记录吗？")) {			
		
		document.getElementById('queryObj.sortname').value = jQuery('#galaList').flex_sortname();	
		document.getElementById('queryObj.sortorder').value = jQuery('#galaList').flex_sortorder();
		document.getElementById('queryObj.route_class').value =jQuery('#route_class').val();
		document.getElementById('queryObj.vins').value = treeVins;
		
		document.getElementById('export_form').submit();
	}
}

function tabSwitch(id){
	if(id=="treeupid"&&!jQuery("#treeupid").hasClass('tabfocus')){
		backFun_getChartInfo("");
		jQuery("#treeupid").removeClass();
		jQuery("#treedownid").removeClass();
		jQuery("#treetqcid").removeClass();
		jQuery("#treeupid").addClass('tabfocus');
		jQuery("#treedownid").addClass('tab');
		jQuery("#treetqcid").addClass('tab');
		//下两行加载树
		treeType='routeup';
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}else if(id=="treedownid"&&!jQuery("#treedownid").hasClass('tabfocus')){
		backFun_getChartInfo("");
		jQuery("#treeupid").removeClass();
		jQuery("#treedownid").removeClass();
		jQuery("#treetqcid").removeClass();
		jQuery("#treeupid").addClass('tab');
		jQuery("#treedownid").addClass('tabfocus');
		jQuery("#treetqcid").addClass('tab');
		//下两行加载树
		treeType='routedown';
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}else if(id=="treetqcid"&&!jQuery("#treetqcid").hasClass('tabfocus')){
		backFun_getChartInfo("");
		jQuery("#treeupid").removeClass();
		jQuery("#treedownid").removeClass();
		jQuery("#treetqcid").removeClass();
		jQuery("#treeupid").addClass('tab');
		jQuery("#treedownid").addClass('tab');
		jQuery("#treetqcid").addClass('tabfocus');
		//下两行加载树
		treeType='routetqc';
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}
}
jQuery(function() {
	var gala = jQuery('#galaList');
	 gala.flexigrid({
		  dataType: 'json',    //json格式
		  height: 600,
		  width: 900,
		  colModel : [ 
				//{display: '序号', name : 'ROWNUMBER', width : 25, sortable : false, align: 'center',escape: true},
				//{display: '车牌号',name : 'vehicle_no', width : calcColumn(0.1,10,138), sortable : true, align: 'center',escape: true},
				{display: '班车号',name : 'vehicle_code', width : 50, sortable : true, align: 'center',escape: true},
				{display: '车牌号',name : 'vehicle_ln', width : 100, sortable : true, align: 'center',escape: true},
		        //{display: '驾驶员',name : "NLSSORT(driver_name,'NLS_SORT = SCHINESE_PINYIN_M')", width : 100, sortable : true, align: 'center',escape: true},
				//{display: '行驶线路', name : "NLSSORT(ROUTE_NAME,'NLS_SORT = SCHINESE_PINYIN_M')", width : 210, sortable : true, align: 'center',escape: true},
				//{display: '线路类型', name : 'empty_load', width : 60, sortable : true, align: 'center',toggle:false,escape: true, process:sumStyle},
				//{display: '乘车人数',name : 'load_number', width : 60, sortable : true, align: 'center',escape: true},
				//{display: '核载人数',name : 'limite_number', width : 60, sortable : true, align: 'center',escape: true},
				{display: '点火时间', name : 'start_time', width : 118, sortable : true, align: 'center',escape: true},
				{display: '熄火时间', name : 'end_time', width : 118, sortable : true, align: 'center',escape: true},
				{display: '点火时长(min)', name : 'run_time', width : 110, sortable : true, align: 'center',escape: true, process:sumStyle},
				//{display: '怠速时长(min)', name : 'idle_time', width : 110, sortable : true, align: 'center',escape: true, process:sumStyle},
				{display: '怠速油耗(L)', name : 'spd_oil', width : 110, sortable : true, align: 'center',escape: true, process:sumStyle},
				{display: '里程(km)', name : 'total_mileage', width : 110, sortable : true, align: 'center',escape: true, process:sumStyle},
				{display : '',name : 'vin',width : calcColumn(0.05, 80, 0),escape : true,hide:true,align : 'center'}
				//{display: '处理', name : 'empty_load', width : 100, sortable : true, align: 'center',hide:true,toggle:false,escape: true, process:sumStyle}
				//{display : '查看详情',name : 'vin',width : calcColumn(0.05, 80, 0),escape : true,align : 'center',process : reWriteTrack}
		        ],
		       sortname: 'end_time',
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true,   // 是否允许显示隐藏列
		       onRowSelect:rowclick,
		       onSuccess:function(data){
			  	   jQuery("#rowsumid").css("display","none");
			       return true;
		       }
	     }); 
	 AutoWidthHeight();
});

var rowclick=function(rowData){
	
	var vehln=jQuery(rowData).data("vehicle_ln");
	var vehCoce = jQuery(rowData).data("vehicle_code");
	var vin=jQuery(rowData).data("vin");
	var start_time=jQuery(rowData).data("start_time");
	start_time=start_time.replace(' ',' ');
	var end_time=jQuery.trim(jQuery(rowData).data("end_time"));
	end_time=end_time.replace(' ',' ');
	var title = vehCoce + ',' + vehln;
	showMap(start_time, end_time, vin, title);
}

function showMap(start_time, end_time, vin, title) {
	var iframeObj = document.getElementById('iframeshowArea');
	if (iframeObj != null) {
		iframeObj.src = "";
	}
	//显示地图泡泡页
	jQuery('#popArea').mk_sidelayer('set_title', encodeHtml(title));
	jQuery('#popArea').mk_sidelayer(
			'reload',
			encodeURI('<s:url value="/daydriverecord/loadIframe.shtml" />?vin='+vin+'&start_time='+start_time+'&end_time='+end_time));
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
	var popheight = "490px";
	var popIframeH = "490px";
	if(jQuery(window).height() >= 900){
		var popwidth = "655px";
		var popheight = "655px";
		var popIframeH = "655px";	
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
		height_offset : 130
	});
	//计算右侧宽度
	jQuery('#content').mk_autoresize({
	    width_exclude:'#content_leftside',
	    width_include: '.flexigrid',
	    width_offset: 10
	});
}
//窗口自适应
jQuery(window).wresize(function(){
	 AutoWidthHeight();
	 popAutoWH();
});
jQuery(function() {
	//resizeBgDiv();
	//auto_size();
	searchOrganRunList();
	//自适应
	//AutoWidthHeight();
	initpop();
	//地图弹出框自适应
	popAutoWH();
	AutoWidthHeight();
});


</script>