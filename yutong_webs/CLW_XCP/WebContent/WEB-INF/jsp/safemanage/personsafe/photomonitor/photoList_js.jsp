<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
var autoView = false;
var showLast = false;
var pageIndexParent = 0;
var pageCount = 0;
jQuery(function() {
	auto_size();
	//重新刷新页面 适配IE9的高分辨率
	jQuery(window).mk_autoresize({
		height_include: '#content',
		height_exclude: ['#header', '#footer'],
		height_offset: 0,
		width_include: ['#header', '#content', '#footer'],
		width_offset: 0});
});

//初始化页面时 自适应方法
function auto_size(){
	//计算中区高度
	jQuery('#content').mk_autoresize( {
		height_include : ['#content_rightside','#content_leftside'],
		height_offset : 0
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize( {
		height_exclude : ['#leftInfoDiv','#searchPlanDiv'],
		height_include : '#lefttree',
		height_offset :10

	});
		
	jQuery('#content_rightside').mk_autoresize({
		height_exclude: '.titleBar',
		height_include: '#photoManger',
		height_offset: 40
	});
	
	jQuery('#photoManger').mk_autoresize({
		height_exclude: '.photo-sech',
		height_include: '#gala',
		height_offset: 40//该偏值等于分页标签的高度
	});
	
	//添加拖动事件  浮动层div（z-index:99）
	jQuery("#popArea3").easydrag();
	//设置拖拽区域
	jQuery("#popArea3").setHandler("divTitle");
	//初始化-加载提示图片"请选择组织或车辆"
	initImgTip();
}

/**
 * (组织数没有被选中的情况)初始化-加载提示图片"请选择组织或车辆"
 */
function initImgTip() {
	//设置图片
	$('#gala').empty();//清空内容
	$('#gala').html('<li><img src="../newimages/select.png"></li>');
	//隐藏分页标签 （处理选中树后又取消的操作 保证和初始化页面展示一致）
	//id="Pagination"  id="ResultInfo"
	$('#Pagination').css("display","none");
	$('#ResultInfo').css("display","none");
}
		/**
		 * 左侧树区域显示控制
		 */
		function HideandShowControl() {//leftdiv
			if (jQuery('#middeldiv').css("display") == "none") {//展开状态
				jQuery('#middeldiv').css("display", "block");
				jQuery('#leftdiv').css("display", "none");
			} else {//隐藏状态
				jQuery('#middeldiv').css("display", "none");
				jQuery('#leftdiv').css("display", "block");
			}
		}

//照片相关  2012-8-15 start
function getVinsByTree(){
	//组织数的判断
	var carList = new Array();
	var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	var vehicle_vins;
	//如果选中nodes长度为0 即组织树没有被选中
	if(nodes.length == 0){
		initImgTip();//初始化-加载提示图片"请选择组织或车辆"
		
	}else{//组织树被选中，执行查询操作
		for(var i = 0, len = nodes.length; i < len; i++) {
			if("pIcon" != nodes[i].iconSkin) {
				//update by fxy(转换为 'A','B','C' 形式的字符串  为后台拼装SQL准备)
				//carList.push(nodes[i].id);
				carList.push("'"+nodes[i].id+"'");} //end if
		}//end for
		vehicle_vins = carList.join(",");
		return vehicle_vins;
    }//end else
}

//点击查询按钮 查询照片列表信息
function searchList(){
	
	//组织数的判断
	var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	//如果选中nodes长度为0 即组织树没有被选中
	if(nodes.length == 0){
		initImgTip();//初始化-加载提示图片"请选择组织或车辆"
	}else{//组织树被选中，执行查询操作
		//初始化-查询数据库获取照片列表
		initPage();
    }//end else
}

//初始化-查询数据库获取照片列表          
function initPage(options) {
	var opts=jQuery.extend({},{pos:0,size:15},options||{});
	//车辆vin信息
	//var vehicle_vins = jQuery('#vehicle_vins').val();
	var vehicle_vins = getVinsByTree();
	//空字符串转换为''
	if(vehicle_vins == ''){vehicle_vins = "''";}
	
	//触发类型
	var photo_event = jQuery('#photo_event').val();
	var start_time = jQuery('#start_time').val();
	var end_time = jQuery('#end_time').val();
	$.ajax({
		type: "POST",
		dataType: "json",
		url: '<s:url value="/photoflexgrid/photoMonitorFlexigrid.shtml"/>',
		data: {pageindex:opts.pos,vehicle_vins:vehicle_vins,photo_event:photo_event,start_time:start_time,end_time:end_time },
		beforeSend:function(){$("#Pagination").hide();},//发送数据之前
		complete:function(){$("#Pagination").show();},//接收数据完毕
		success: function(data) {          
			pageIndexParent = opts.pos;
			//数据总数
			pageCount = data.pagetotal;
			
			//照片列表展示
			showPhotoList(pageCount,data);
					
			//分页标签
			$("#Pagination").pagination(pageCount, {
				callback: pageselectCallback,
				prev_text: '<< 上一页',
				next_text: '下一页 >>',
				items_per_page:opts.size,
				num_display_entries:6,
				current_page:opts.pos,
				num_edge_entries:2}); //pagination end
			
			// 自动预览照片
			if (autoView) {
				jQuery('#gala li a:first').click();
				autoView = false;
			}			
				}//success end              
              });//ajax end
           }//initPage end
function pageselectCallback(page_index,jq){
	
	initPage($.extend({},{'pos':page_index}));
}


           
/**
 * 照片列表展示
 * update:
 * 1.img标签的 onerror 防止照片路径无法找到是报错
 * 2.img标签的 alt 防止照片文件损害时报错
 */
function showPhotoList(pageCount,data){
	if(pageCount > 0){//有照片数据时的显示
		var myData = data.list;
		var newcontent = "";
		//照片列表信息拼装
		//1.tip提示完整vehicle_ln
		//2.如果vehicle_ln过长,显示
		$.each(myData, function(i, n) {
			//vehicle_ln过程处理
			var titleln=myData[i].vehicle_ln;
			if(titleln.length>7){
				titleln=titleln.substr(titleln.length-7);
				titleln="*"+titleln;
			}
			newcontent += '<li><a href="javascript:void(0);" onclick="intoGalleriaPage(this)" id="'+myData[i].photo_id+'" tabindex="'+i+'"><img src="../'+myData[i].photo_file+'" id="'+myData[i].photo_id+'" title="'+myData[i].vehicle_ln+'" alt="照片文件损坏" onerror="this.src=\'../newimages/backgroup.gif\'"></a>';
			newcontent += '<div class="photo_table_txt"><b>'+titleln+'</b><span>'+myData[i].photo_event+'</span></div></li>';
			});//each end
		$('#gala').empty();//清空内容
		$('#gala').html(newcontent);
		$('#ResultInfo').html('<span>照片总计 '+pageCount+' 张</span>');
		//重新显示
		$('#ResultInfo').css("display","block");
	}else{//无照片数据时的显示
		$('#gala').empty(); //append
		$('#gala').html('<li><img src="../newimages/noPic.png"></li>');		
		$('#ResultInfo').html('<span>照片总计 0 张</span>');
		$('#ResultInfo').css("display","block");
	}  	   
        	   
}     


//获取数据    
function getData(page_id) {
	//车辆vin信息
	//var vehicle_vins = jQuery('#vehicle_vins').val();
	var vehicle_vins = getVinsByTree();
	//空字符串转换为''
	if(vehicle_vins == ''){vehicle_vins = "''";}
	//触发类型
	var photo_event = jQuery('#photo_event').val();
	var start_time = jQuery('#start_time').val();
	var end_time = jQuery('#end_time').val();

           	$.ajax({
           		type: "POST",
           		dataType: "json",
           		url: '<s:url value="/photoflexgrid/photoMonitorFlexigrid.shtml"/>',
           		data: {pageindex:page_id,vehicle_vins:vehicle_vins,photo_event:photo_event,start_time:start_time,end_time:end_time },
           		success: function(data) {
           		//照片列表展示
    			showPhotoList(pageCount,data);
           				}//success end             
           			});//ajax end
           		}//getData end
//照片相关  2012-8-15 end
/**
 * 点击单张照片进入相册
 */
function intoGalleria(obj){
	
	//id-选中的照片ID
	//获取本页照片列表的id信息 （photo_id 的列表）
	var count = $('#gala img').length;
	//将选中的图片ID放置到列表的第一个位置上
	//数据库获取列表信息是 通过数据库查询时SQL中的or 排重
	var photoIdStr = "",checkedId=obj.id;
	
	for(var i = 0;i<count;i++) {
			
		if(i == count-1){
		    //最后一个元素
		    if($('#gala img')[i].id != checkedId){
			photoIdStr += $('#gala img')[i].id;
		    }
		
		}else{
			if($('#gala img')[i].id != checkedId){
			photoIdStr += $('#gala img')[i].id+",";
			}
		}
		
	}
	
	//跳入到弹出框--相册
	var iframeobj = document.getElementById("iframeshowArea");
	//var poparea3obj = document.getElementById("");
	iframeobj.src = "<s:url value='/photomonitor/photoGalleriaView.shtml' />?photoIdStr="+photoIdStr+"&checkedId="+checkedId;
	//显示照片相册
	jQuery("#popArea3").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#popArea3").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#popArea3").height())/2))+"px");
	jQuery('#popArea3').css("display", "block");
	
	//显示拖拽层
	jQuery("#BgDiv").css("display","block");
	
}
var begin = 0;
var pageSize = 12;
var showIndex = 0;
function intoGalleriaPage(obj, isNext){
	var pageSizeParent = 15;
	
	var vehicle_vins = getVinsByTree();
	//空字符串转换为''
	if(vehicle_vins == ''){vehicle_vins = "''";}
	//触发类型
	var photo_event = jQuery('#photo_event').val();
	var start_time = jQuery('#start_time').val();
	var end_time = jQuery('#end_time').val();
	if (obj == null) {
		if (isNext)
			begin = begin + pageSize;
		else {
			begin = begin - pageSize;
			showIndex = pageSize - 1;
		}
		if (begin < 0) {
			showIndex = pageSize - 1 + begin;
			begin = 0;
		}
		if (begin > (pageCount-1)) 
			begin = pageCount - 1;
	} else {
		begin = pageIndexParent * pageSizeParent + obj.tabIndex;
	}
	
	//跳入到弹出框--相册
	var iframeobj = document.getElementById("iframeshowArea");
	//var poparea3obj = document.getElementById("");
	iframeobj.src = "<s:url value='/photomonitor/photoGalleriaViewPage.shtml' />?vehicle_vins="+vehicle_vins+"&photo_event="+photo_event+"&start_time="+start_time+"&end_time="+end_time+"&begin="+begin+"&pageSize="+pageSize;
	//显示照片相册
	jQuery("#popArea3").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#popArea3").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#popArea3").height())/2))+"px");
	jQuery('#popArea3').css("display", "block");
	
	//显示拖拽层
	jQuery("#BgDiv").css("display","block");
	
}

//隐藏相册
function HideGalleria(){
	var iframeobj = document.getElementById("iframeshowArea");
	iframeobj.src="";
	jQuery('#popArea3').css("display", "none");
	//隐藏拖拽层
	jQuery("#BgDiv").css("display","none");
}
     
/*
* resize时让DIV在文档居中
*/
 jQuery(window).wresize(function(){
	//显示照片相册
		jQuery("#popArea3").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#popArea3").width())/2))+"px")
	        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#popArea3").height())/2))+"px");
 });

</script>