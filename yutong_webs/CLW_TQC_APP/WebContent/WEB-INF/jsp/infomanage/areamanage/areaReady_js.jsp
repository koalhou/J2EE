<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function searchList() {
	jQuery('#usertbl').flexOptions({
		newp: 1,
		params: [{name: 'vehicle_vin', value: formatSpecialChar(jQuery('#vehicle_vin').val()) }]
	});
	jQuery('#usertbl').flexReload();
}

function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

jQuery(function() {
	var gala = jQuery('#usertbl');
	var url = "../areaplanpkg/showareaplanlist.shtml";
	gala.flexigrid({
		url: url,
		dataType: 'json',
		params: [{name: 'vehicle_vin', value: jQuery('#vehicle_vin').val()}],
		height: 300,
		width:2000,
		colModel : [
			{display: '序号', name : '', width : calcColumn(0.04,40), sortable : false, align: 'center', escape:true},
			{display: '大区名称', name : 'area_name', width : calcColumn(0.15,80), sortable : true, escape:true, align: 'center'},
			{display: '站点数', name : 'sitenum', width : calcColumn(0.15,80), sortable : true, escape:true, align: 'center'},
			{display: '备注', name : '', width : calcColumn(0.15,80), sortable : false, escape:true, align: 'center'},
			{display: '创建时间', name : '', width : calcColumn(0.15,80), sortable : false, escape:true, align: 'center'},
			{display: '操作', name : '', width : calcColumn(0.08,80), sortable : false, align: 'center',process: deleteupdateArea},
			{display: '查看', name : '', width : 40, sortable : false, align: 'center',process: checkArea}
		],
		sortname: 'area_id',
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
function deleteupdateArea(tdDiv,pid,row) {
	return '<a href="javascript:updatebrwose(\'' + tdDiv + '\')">' + '修改' +'</a>&nbsp;&nbsp;&nbsp;<a href="javascript:delbrwose(\'' + tdDiv + '\')">' + '删除' +'</a>';
}
function checkArea(tdDiv,pid,row) {
	return '<a href="javascript:urlto(\'' + tdDiv + '\')">' + '查看' +'</a>';
}
function urlto(id){
	jQuery("#areaidto").val(id);
	jQuery("#siteareacheck").submit();
}
function delbrwose(areaid) {
	var param = {areaid:areaid};
	jQuery.post("../area/deleteareaPlan.shtml",param,function(data){
		if(data!=null){
			searchList();
		} else {
			alert("删除失败！");
		}
	},"text");
}
function updatebrwose(areaid) {
	jQuery("#updateByAreaId").attr("action","../area/updateareaReady.shtml");
	jQuery("#areaId_hidden").val(areaid);
	jQuery("#updateByAreaId").submit();
}
//页面自适应
$(function(){
	jQuery(window).resize(function(){
		testWidthHeight();
	});
	testWidthHeight();
});
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
	 var h = get_page_height();
	 var w = get_page_width();
	 jQuery(".flexigrid").width(w-25);
	 //jQuery("#reportTab").height(h-100);
	 jQuery(".bDiv").height(h-320);
	
}
//计算控件宽高
function testWidthHeight(){
	
	 autoWidthHeight();
	
	 jQuery(window).mk_autoresize({
			height_include: '#content',
			height_exclude: ['#header', '#footer'],
			height_offset: 0,
			width_include: ['#header', '#content', '#footer'],
			width_offset: 0});
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

</script>