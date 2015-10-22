<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function sumStyle(tdDiv,pid,row){
	//alert("111");
	if(pid=="sumid"){
		//alert(pid + "-----" + row.cell[11]);
		jQuery("#totalmileage").html(row.cell[11] + "(km)");
		jQuery("#totalemptymileage").html(row.cell[9] + "(km)");
		jQuery("#totalloadmileage").html(row.cell[10] + "(km)");
		jQuery("#emptyload").html(row.cell[12] + "%");
// 		jQuery("#totaloil").html(row.cell[10] + "(L)");
// 		jQuery("#totalspnoil").html(row.cell[9] + "(L)");
		//jQuery("#countmileage").html(row.cell[12] + "(km)");
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
	//alert(333);
	routename = jQuery("#search_route_name").val();
	searchTree();
}

//右侧查询函数
function searchList(){	
	//alert(222);
	document.getElementById('Below_new').style.display='none';
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
	//alert("jinlail");
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
	//alert(1111);
	jQuery("#emptyload").html("");
	jQuery("#totalemptymileage").html("");
	jQuery("#totalloadmileage").html("");
	jQuery("#totalmileage").html("");
// 	jQuery("#totaloil").html("");
// 	jQuery("#totalspnoil").html("");
	
	var user_beg_time = jQuery('#begintime').val();
	var user_end_time = jQuery('#endtime').val();	
	var user_org_id =jQuery('#user_org_id').val();
	
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '<s:url value="/rightranking/historyList.shtml" />',
		params: [{name: 'queryObj.VIN', value: finalVIN },
		        {name: 'route_class', value: jQuery('#route_class').val()},
		        {name: 'route_id', value: jQuery('#choicerouteid').val()},
			    {name:'queryObj.begintime',value:user_beg_time},
			    {name:'queryObj.endtime',value:user_end_time}
			    ]
	});
	jQuery('#galaList').flexReload();
}

function searchOrganRunList() {
	jQuery("#emptyload").html("");
	jQuery("#totalemptymileage").html("");
	jQuery("#totalloadmileage").html("");
	jQuery("#totalmileage").html("");
// 	jQuery("#totaloil").html("");
// 	jQuery("#totalspnoil").html("");
	
	var user_beg_time = jQuery('#begintime').val();
	var user_end_time = jQuery('#endtime').val();	
	var user_org_id =jQuery('#user_org_id').val();
	
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '<s:url value="/rightranking/historyList.shtml" />',
		params: [
		         //{name: 'queryObj.VIN', value: finalVIN },
		        //{name: 'queryObj.re_flag', value: selectType},
		        {name: 'route_class', value: jQuery('#route_class').val()},
		        {name: 'route_id', value: jQuery('#choicerouteid').val()},
			    {name:'queryObj.begintime',value:user_beg_time},
			    {name:'queryObj.endtime',value:user_end_time},
			    {name:'queryObj.user_organization_id',value:user_org_id}
			    ]
	});
	jQuery('#galaList').flexReload();
}
function exportData(){	
	if($("#galaList").find("td").length == 0){
		alert("没有发车统计数据!");
		return;	
	}
	//alert($("#galaList").find("td").length);
	//alert($("#galaList").pDiv);
	if($("#galaList").flex_totalc > 50000){
		alert("无法导出，系统最多可一次导出5W条记录!");
		return;
	}
	if(confirm("确定要导出发车统计吗？")) {			
		
		document.getElementById('queryObj.sortname').value = jQuery('#galaList').flex_sortname();	
		document.getElementById('queryObj.sortorder').value = jQuery('#galaList').flex_sortorder();
		document.getElementById('queryObj.route_class').value =jQuery('#route_class').val();
		
		document.getElementById('export_form').submit();
	}
	//document.getElementById('Below_new').style.display='none';
	//alert(document.getElementById('Below_new').html);
	//var mess=jQuery("#Below_new").val();
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
		  width: 1410,
		  colModel : [ 
				//{display: '序号', name : 'ROWNUMBER', width : 25, sortable : false, align: 'center',escape: true},
				//{display: '车牌号',name : 'vehicle_no', width : calcColumn(0.1,10,138), sortable : true, align: 'center',escape: true},
				{display: '班车号',name : 'vehicle_code', width : 50, sortable : true, align: 'center',escape: true},
				{display: '车牌号',name : 'vehicle_no', width : 100, sortable : true, align: 'center',escape: true},
		        {display: '驾驶员',name : "NLSSORT(driver_name,'NLS_SORT = SCHINESE_PINYIN_M')", width : 100, sortable : true, align: 'center',escape: true},
				{display: '行驶线路', name : "NLSSORT(ROUTE_NAME,'NLS_SORT = SCHINESE_PINYIN_M')", width : 210, sortable : true, align: 'center',escape: true},
				{display: '线路类型', name : 'empty_load', width : 60, sortable : true, align: 'center',toggle:false,escape: true, process:sumStyle},
				{display: '乘车人数',name : 'load_number', width : 60, sortable : true, align: 'center',escape: true},
				{display: '核载人数',name : 'limite_number', width : 60, sortable : true, align: 'center',escape: true},
				{display: '开始时间', name : 'start_time', width : 118, sortable : true, align: 'center',escape: true},
				{display: '结束时间', name : 'end_time', width : 118, sortable : true, align: 'center',escape: true},
// 				{display: '怠速油耗(L)', name : 'idle_oil', width : 80, sortable : true, align: 'center',hide:true,toggle:false,escape: true,process:sumStyle},
// 				{display: '总油耗(L)', name : 'total_oil', width : 80, sortable : true, align: 'center',escape: true,hide:true, process:sumStyle},
				{display: '空驶里程(km)', name : 'empty_mileage', width : 110, sortable : true, align: 'center',escape: true, process:sumStyle},
				{display: '载重里程(km)', name : 'load_mileage', width : 110, sortable : true, align: 'center',escape: true, process:sumStyle},
				{display: '总里程(km)', name : 'total_mileage', width : 110, sortable : true, align: 'center',escape: true, process:sumStyle},
				{display: '空驶率(%)', name : 'empty_load', width : 100, sortable : true, align: 'center',hide:true,toggle:false,escape: true, process:sumStyle}
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
		       onSuccess:function(data){
			  	   jQuery("#rowsumid").css("display","none");
			       return true;
		       }
	     }); 
	 
});
jQuery(function() {
	//resizeBgDiv();
	auto_size();
	
});
/**
 * 树tab且换
 */
 function tabSwitch(id){

	 if (!check()) {
			return;
		}
		 
 	jQuery("#vehicleLn").val("");
	if(id=="enttabid"){
		jQuery("#treetabid").removeClass();
		jQuery("#enttabid").addClass('tabfocus');
		jQuery("#treetabid").addClass('tab');
		//下两行加载树
		treeType='route1';
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}
	else if(id=="treetabid"){
		jQuery("#enttabid").removeClass();
		jQuery("#enttabid").addClass('tab');
		jQuery("#treetabid").addClass('tabfocus');
		//下两行加载树
		treeType='route';
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}
	//mapObj.removeAllOverlays();
}

</script>