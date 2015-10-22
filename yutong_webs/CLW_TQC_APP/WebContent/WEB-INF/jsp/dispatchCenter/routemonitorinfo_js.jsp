<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function searchList() {
	<s:if test="#session.perUrlList.contains('111_3_3_3_1')">
	jQuery('#usertbl').flexOptions({
		newp: 1,
		params: [{name: 'vehicle_vin', value: formatSpecialChar(jQuery('#vehicle_vin').val()) },
		         {name: 'vehicle_code', value: formatSpecialChar(jQuery('#vehicle_code').val()) },
		         {name: 'begTime', value: jQuery('#begTime').val() },
		         {name: 'endTime', value: jQuery('#endTime').val() },
		         {name: 'route_class', value: jQuery('#route_class').val() },
		         {name: 'route_id', value: jQuery('#choicerouteid').val() },
		         {name: 'route_res', value: jQuery('#route_res').val() }]
	});
	jQuery('#usertbl').flexReload();
	</s:if>
}

function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

function getnowtime(){
	var myDate = new Date();
	var month = parseInt(myDate.getMonth())<=8?"0"+(parseInt(myDate.getMonth())+1):parseInt(myDate.getMonth())+1;
	var day = parseInt(myDate.getDate())<=9?"0"+parseInt(myDate.getDate()):parseInt(myDate.getDate());
	return myDate.getFullYear()+"-"+month+"-"+day;
}
function reWriteTypeLink(tdDiv,pid,row) {
	return tdDiv=='0'?'早班线路':tdDiv=='1'?'晚班线路':tdDiv=='2'?'厂内通勤':'';
}
function resendconnType(tdDiv,pid,row) {
	return tdDiv=='0'?'坐满发车':tdDiv=='1'?'循环发车':tdDiv=='2'?'按时间发车':'';
}
jQuery(function() {
	//jQuery("#begTime").val(getnowtime());
	//jQuery("#endTime").val(getnowtime());
	
	var gala = jQuery('#usertbl');
	var url = "../dispatchroute_chart/route_info_List.shtml";
	gala.flexigrid({
		url: url,
		dataType: 'json',
		params: [{name: 'vehicle_vin', value: jQuery('#vehicle_vin').val() },
		         {name: 'vehicle_code', value: jQuery('#vehicle_code').val() },
		         {name: 'begTime', value: jQuery('#begTime').val() },
		         {name: 'endTime', value: jQuery('#endTime').val() },
		         {name: 'route_class', value: jQuery('#route_class').val() },
		         {name: 'route_id', value: jQuery('#choicerouteid').val() },
		         {name: 'route_res', value: jQuery('#route_res').val() }],
		height: 300,
		width:2000,
		colModel : [
			{display: '序号', name : '', width : calcColumn(0.04,40), sortable : false, align: 'center', escape:true},
			{display: '发车日期', name : 'exe_date', width : calcColumn(0.04,80), sortable : true, align: 'center'},
			{display: '班车号', name : 'vehicle_code', width : calcColumn(0.05,80), sortable : true, escape:true, align: 'center'},
			{display: '车牌号', name : 'vehicle_ln', width : calcColumn(0.05,80), sortable : true, escape:true, align: 'center'},
			{display: '线路类别', name : 'route_class', width : calcColumn(0.04,40), sortable : true, align: 'center', process: reWriteTypeLink},
			{display: '行驶线路', name : '', width : calcColumn(0.04,150), sortable : false, escape:true,align: 'center'},
			{display: '站点', name : '', width : calcColumn(0.04,80), sortable : false, align: 'center'},
			{display: '发车条件', name : 'send_condition', width : calcColumn(0.04,100), sortable : true, escape:true,align: 'center', process: resendconnType},
			{display: '发车顺序', name : 'send_order', width : calcColumn(0.04,60), sortable : true, align: 'center'},
			{display: '最后修改时间', name : 'operate_time', width : calcColumn(0.1,80), sortable : true, align: 'center',toggle:false},
			{display: '终端响应时间', name : '', width : calcColumn(0.1,80), sortable : false, align: 'center',toggle:false},	    	
			{display: '下发结果', name : 'mesg_flag', width : calcColumn(0.1,80), sortable : true, align: 'center'}	    		
			],
		sortname: 'operate_time',
		sortorder: 'desc',
		sortable: true,
		striped :true,
		usepager :true, 
		resizable: false,
		useRp: true,
		rp: 20,
		rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		//getQuery :getQuery
		});
	});


//页面自适应
(function (jQuery) {
 jQuery(window).resize(function(){
 
  testWidthHeight();
  
 });
 jQuery(window).load(function (){
  
  testWidthHeight();
  
 });
 
})(jQuery);
//获取页面高度
function get_page_height() {
 var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
 return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();

}
//获取页面宽度
function get_page_width() {
	 var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
	 return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
}

function  autoWidthHeight(){
	 /* var h = get_page_height();
	 var w = get_page_width();
	 jQuery(".flexigrid").width(w-25);
	 //jQuery("#reportTab").height(h-100);
	 jQuery(".bDiv").height(h-300); */
	var h = get_page_height();
	var w = get_page_width();
	 
	if(jQuery(window).width()>1300) {
		jQuery("#tabAreaId").width(jQuery("#header").width()-30);
		jQuery(".flexigrid").width("100%");
	}else {
		jQuery("#tabAreaId").width(jQuery("#header").width()-25);
		jQuery(".flexigrid").width(jQuery("#tabAreaId").width()-10);
	} 
	jQuery(".bDiv").height(h-300);
}
//计算控件宽高
function testWidthHeight(){
	
	 autoWidthHeight();
	
	 /* jQuery(window).mk_autoresize({
			height_include: '#content',
			height_exclude: ['#header', '#footer'],
			height_offset: 0,
			width_include: ['#header', '#content', '#footer'],
			width_offset: 0}); */
	 autoWidthHeight();
}

(function(){
    var d = art.dialog.defaults;
    
    // 按需加载要用到的皮肤，数组第一个为默认皮肤
    // 如果只使用默认皮肤，可以不填写skin
    d.skin = ['aero'];
    
    // 支持拖动
    d.drag = true;
    
    // 超过此面积大小的对话框使用替身拖动
    d.showTemp = 100000;
})();

function choiceCarln() {
	art.dialog.open("<s:url value='/infomanage/chooseCar_isfalse1.shtml' />",{
		title:"车辆信息",
		lock:true,
		width:260,
		height:435
	});
}
function choiceRoute() {
	var url = jQuery("#route_class").val()=='-1'?"<s:url value='/infomanage/getchooseRoutenores.shtml' />":"<s:url value='/infomanage/getchooseRoutenores.shtml' />?route_class="+jQuery("#route_class").val();
	art.dialog.open(url,{
		title:"线路查询",
		lock:true,
		width:260,
		height:435
	});
} 
</script>