<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
/**
 * 左侧树区域显示控制
 */
function HideandShowControl(){//leftdiv
	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
		jQuery('#middeldiv').css("display","block");
		jQuery('#leftdiv').css("display","none");
		//计算中区高度
		jQuery('#content').mk_autoresize( {
			height_include : [ '#content_rightside', '#content_middelside' ],
			height_offset : 0,
			width_exclude: ['#content_middelside'],
			width_include : [ '#content_rightside'],
			width_offset : 1
		});

		//计算右测区域高度
		jQuery('#content_rightside').mk_autoresize( {
			height_exclude : [ '.titleBar'],
			height_include : '#right_main',
			height_offset : 0,
			width_include : [ '.titleBar','#right_main'],
			width_offset : 0
		});
		jQuery('#right_main').mk_autoresize( {
			height_exclude : ['.car-info'],
			height_include : '.table_list',
			height_offset : 70,
			width_include : ['.car-info','.table_list'],
			width_offset : 0
		});		
		jQuery('.table_list').mk_autoresize({
		    height_include: '.bDiv',
		    height_offset: 70, // 该值各页面根据自己的页面布局调整
		    width_include: '.flexigrid',
		    width_offset: 8// 该值各页面根据自己的页面布局调整
		  });			
	}else{//隐藏状态
		jQuery('#middeldiv').css("display","none");
		jQuery('#leftdiv').css("display","block");
		auto_size();
		jQuery(window).mk_autoresize({
			height_include: '#content',
			height_exclude: ['#header', '#footer'],
			height_offset: 0,
			width_include: ['#header', '#content', '#footer'],
			width_offset: 0});
		auto_size();
	}
}

function mytreeonClick(type, treeId){
	jQuery("#isSearch").val("");
	jQuery("#alarmKey").val("");
	if(type=='1'){
		selectCar(treeId);
	}else{
		selectEnt(treeId);	
	}
}

function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}
jQuery(function() {
	var galaList = jQuery('#galaList');
	 galaList.flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width: 1500,
		  colModel : [ 
				{display: '序号', name : '', width : 80, sortable : false, align: 'center',escape: true},
				{display: 'id', name : '', width : 25, sortable : false, align: 'center', hide: true,escape: true},						  
				{display: '上学运行时段', name : 'go_start_date', width : 260, sortable : true, align: 'center',escape: true},
				{display: '放学运行时段', name : 'back_start_date', width : 260, sortable : true, align: 'center',escape: true},
				{display: '操作', name : '', width : 200, sortable : true, align: 'center',escape: true,process: reWriteLink}],
		       sortname: 'go_start_date',
		       sortorder: 'asc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true   // 是否允许显示隐藏列
	     }); 
	 searchrunTimeList();
});

function reWriteLink(tdDiv,pid,row){
	var tdV = "";
	tdV = "<a href=\"javascript:void(0);\" onclick=\"openWinSign('"+pid+"','edit')\">修改</a>&nbsp;&nbsp;"+
	"<a href=\"javascript:void(0);\" onclick=\"openWinSign('"+pid+"','look')\">查看</a>";
	return tdV;	
}
var isBtn = "";
function openWinSign(pid,utc){
	isBtn = utc;
	art.dialog.open("<s:url value='/runtimeset/iframeSet.shtml' />?keyId="+pid,{
		id:'runtimeSet',
		title:'运行时段设置',
		lock:true,
		width:380,
		height:180,
		fixed:true,
		initFn:function(){
			
		},
		closeFn:function(){
			//cancelArt();
		}
	}); 
}

function getIsBtn(){
	return isBtn;
}

/* 关闭dialog 窗口 */
function cancelArt(){
	art.dialog.get('runtimeSet').close();
}

function tishiWin(content){
	art.dialog.alert(content);
}

function calcColumn(persent, minwidth, offset) {
    var pagewidth = 0;
    
    var width = 0;
    if(jQuery.browser.msie){ 
       pagewidth = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
    } else { 
       pagewidth = self.innerWidth; 
    } 
    if(offset > 0) {
       width = pagewidth - offset;
    } else {
       width = pagewidth;
    }
    return width*persent < minwidth ? minwidth : width*persent;
}



jQuery(function() {
	auto_size();	
	jQuery(window).mk_autoresize({
		height_include: '#content',
		height_exclude: ['#header', '#footer'],
		height_offset: 0,
		width_include: ['#header', '#content', '#footer'],
		width_offset: 0});
	auto_size();
});

function auto_size(){
	jQuery(window).mk_autoresize({
		min_width: 1170
	  });
	//计算中区高度
	jQuery('#content').mk_autoresize( {
		height_include : [ '#content_rightside'],
		height_offset : 0,
		//width_exclude: ['#content_leftside'],
		width_include : [ '#content_rightside'],
		width_offset : 1
	});
	//计算左测区域高度
// 	jQuery('#content_leftside').mk_autoresize( {
// 		height_exclude : [ '#leftInfoDiv', '.newsearchPlan'],
// 		height_include : '#lefttree',
// 		height_offset : 10

// 	});

	//计算树区域高度
	//jQuery('#lefttree').mk_autoresize( {
	//	height_include : '#treeDemo',
	//	height_offset : 20
	//});


	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize( {
		height_exclude : [ '.titleBar'],
		height_include : '#right_main',
		height_offset : 0,
		width_include : [ '.titleBar','#right_main'],
		width_offset : 0
	});
	jQuery('#right_main').mk_autoresize( {
		height_exclude : ['.car-info'],
		height_include : '.table_list',
		height_offset : 0,
		width_include : ['.car-info','.table_list'],
		width_offset : 0
	});		
	jQuery('.table_list').mk_autoresize({
	    height_include: '.bDiv',
	    height_offset: 70, // 该值各页面根据自己的页面布局调整
	    width_include: '.flexigrid',
	    width_offset: 8// 该值各页面根据自己的页面布局调整
	  });
}

//查询
function searchrunTimeList() {
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '<s:url value="/runtimeset/findRunTimeSetList.shtml" />',
		params: []
	});
	jQuery('#galaList').flexReload();
}


</script>
