<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
jQuery(function() {	
	auto_size();
	jQuery('#begintime').val(getCurMonOneDay());
	
	init();	
	searchRepareCarListInit();
	
	
	
});


function searchRepareCarListInit() {
	
	var user_beg_time = jQuery('#begintime').val();
	var user_end_time = jQuery('#endtime').val() + " 23:59:59";	
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '<s:url value="/rightranking/getRepare.shtml" />',
		params: [{name: 'vehicle_vin', value: jQuery('#vehicle_vins').val()},
		        {name: 'fix_type', value: jQuery('#fix_type').val()},
			    {name:'begintime',value:user_beg_time},
			    {name:'endtime',value:user_end_time}
			    ]
	});
	jQuery('#galaList').flexReload();	
	jQuery(".flexigrid div.hDivBox").hide();
}


function searchRepareCarList() {
	//if(jQuery('#vehicle_vins').val()==""||jQuery('#vehicle_vins').val()==null){
		//alert("请在左侧树中选择车辆！");
		//return false;		
	//jQuery('#vehicle_vins').val("'null'");
	//}
	var user_beg_time = jQuery('#begintime').val();
	var user_end_time = jQuery('#endtime').val() + " 23:59:59";	
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '<s:url value="/rightranking/getRepare.shtml" />',
		params: [{name: 'vehicle_vin', value: jQuery('#vehicle_vins').val()},
		        {name: 'fix_type', value: jQuery('#fix_type').val()},
			    {name:'begintime',value:user_beg_time},
			    {name:'endtime',value:user_end_time}
			    ]
	});
	jQuery('#galaList').flexReload();	
	jQuery(".flexigrid div.hDivBox").hide();
}


 function init(){
	var gala = jQuery('#galaList');
	 gala.flexigrid({
		  dataType: 'json',    //json格式
		  height: 500,
		  width: 1400,
		  colModel : [ 
				//{display: '序号', name : 'ROWNUMBER', width : 25, sortable : false, align: 'center',escape: true},
				//{display: '车牌号',name : 'vehicle_no', width : calcColumn(0.1,10,138), sortable : true, align: 'center',escape: true},
				{display: '维保日期',name : 'vehicle_code', width : 70, sortable : false, align: 'center',escape: true},
				{display: '班车号',name : 'vehicle_no', width : 70, sortable : false, align: 'center',escape: true},
		        {display: '车牌号',name : "driver_name", width : 70, sortable : false, align: 'center',escape: true},
				{display: '故障描述', name : "ROUTE_NAME", width : 140, sortable : false, align: 'center',escape: true,process:showTitle},
				{display: '维保项目', name : 'empty_load', width : 140, sortable : false, align: 'center',escape: true,process:showTitle},
				{display: '类别',name : 'load_number', width : 70, sortable : false, align: 'center',escape: true},
				{display: '是否正常维修',name : 'limite_number', width : 80, sortable : false, align: 'center',escape: true},
				{display: '责任人', name : 'start_time', width : 70, sortable : false, align: 'center',escape: true},
				{display: '工时', name : 'end_time', width : 50, sortable : false, align: 'center',escape: true},
				{display: '配件', name : 'end_time', width : 50, sortable : false, align: 'center',escape: true},
				{display: '', name : '', width : 50, sortable : false, align: 'center',process: sumMoney},
				{display: '', name : '', width : 50, sortable : false, align: 'center',process: modifyHtml}
		        ],
		       sortname: 'end_time',
		       autoload: true,
		       sortorder: 'desc',  //升序OR降序
		       sortable: false,   //是否支持排序
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
	 	jQuery(".flexigrid div.hDivBox").hide();
	 	auto_size();
}


function showTitle(tdDiv,pid,row){
	var reStr=tdDiv;
	if(tdDiv.length>10){
		reStr=tdDiv.substr(0,8)+"...";
	}
	return '<label title="'+tdDiv+'">'+reStr+'</label>';
}

function mytreeonClick(list){	
	var strsql = "";
	if (list.length > 0) {
		for ( var i = 0; i < list.length; i++) {
			if (i == list.length - 1) {
				strsql += "'" + list[i] + "'";
			} else {
				strsql += "'" + list[i] + "',";
			}
		}
	}
	//保存vehicle_vin信息
	document.getElementById('vehicle_vins').value=strsql;	
	searchRepareCarList();
}
//获取本月1号
function getCurMonOneDay(){
	var dong=jQuery("#serverTime").val();
	var han=dong.split("-");
	
    var date = han[0] + "-" + han[1] + "-01";
    return date;
};

//左侧树上方的查询框
function searchTreeClick() {
	//jQuery('#vehicle_vins').val(jQuery('#vehicleLn').val());
	searchTree();
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
	    height_offset: 170, // 该值各页面根据自己的页面布局调整
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
	    height_offset: 150, // 该值各页面根据自己的页面布局调整
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
	    height_offset: 150, // 该值各页面根据自己的页面布局调整
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

var finalVIN = "";
var user_org_id="";
function selectCar(radioObj){
	finalVIN = radioObj;
	user_org_id = "";
	jQuery('#vehicle_vin').val(finalVIN);
	vehicle_ln=radioObj;
	searchCarRunList();
}
function selectOrganization(organObj){
	user_org_id=organObj;
	finalVIN = "";
	jQuery('#user_org_id').val(organObj);
	searchOrganRunList();	
}


var user_beg_time = "";
var user_end_time = "";



function searchOrganRunList() {
	var user_beg_time = jQuery('#begintime').val();
	var user_end_time = jQuery('#endtime').val() + " 23:59:59";
	
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '<s:url value="/rightranking/getRepare.shtml" />',
		params: [
		        {name: 'fix_type', value: jQuery('#fix_type').val()},
			    {name:'begintime',value:user_beg_time},
			    {name:'endtime',value:user_end_time},
			    {name:'user_org_id',value:user_org_id}
			    ]
	});
	jQuery('#galaList').flexReload();
}
function exportData(){	
	if($("#galaList").find("td").length == 0){
		alert("没有发车统计数据!");
		return;	
	}
	if(confirm("确定要导出发车统计吗？")) {			
		
		//document.getElementById('sortname').value = jQuery('#galaList').flex_sortname();	
		//document.getElementById('sortorder').value = jQuery('#galaList').flex_sortorder();
		
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
function addRepare() {
	window.location = "gotoAddRepare.shtml?action=add";
}
function modifyRepare(repareId) {
	window.location = "gotoUpdateRepare.shtml?action=update&repare_id="+repareId;
}
function delRepare(repareId) {
}
function sumMoney(tdDiv,pid,row){
	return returnFloat1(parseFloat(row.cell[8])+parseFloat(row.cell[9]));
	
}

function returnFloat1(value) {    
value = Math.round(parseFloat(value) * 10) / 10;

return value;
}





function modifyHtml (repareId){
	return "<a class=\"modifyLink\" href=\"javascript:void(0)\" onclick=\"modifyRepare("+repareId+")\">修改</a>";
}
function delHtml (repareId){
	return "<a class=\"delLink\" href=\"javascript:void(0)\" onclick=\"delRepare("+repareId+")\">删除</a>";
}
//导出Excel
function exportData() {
	var begintime = jQuery('#begintime').val();
	var endtime = jQuery('#endtime').val() + " 23:59:59";
	var fix_type = jQuery('#fix_type').val();
	window.open("repareExcel.shtml?begintime="+begintime+"&endtime="+endtime+"&fix_type="+fix_type+"&vehicle_vin="+finalVIN+"&user_org_id="+user_org_id, "_self");
}






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