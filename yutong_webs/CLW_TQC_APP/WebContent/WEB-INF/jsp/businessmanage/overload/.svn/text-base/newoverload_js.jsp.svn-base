<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
/**
 * 左侧树区域显示控制
 */
function HideandShowControl(){//leftdiv
	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
		jQuery('#middeldiv').css("display","block");
		jQuery('#leftdiv').css("display","none");
		 jQuery('#content').mk_autoresize({
		        width_exclude: '#content_leftside',
		        width_include: '.flexigrid',
		        width_offset: 32,// 该值各页面根据自己的页面布局调整
		        is_handler: false
	    });	
	}else{//隐藏状态
		jQuery('#middeldiv').css("display","none");
		jQuery('#leftdiv').css("display","block");
		jQuery('#content').mk_autoresize({
	        width_exclude: '#content_leftside',
	        width_include: '.flexigrid',
	        width_offset: 10,// 该值各页面根据自己的页面布局调整
	        is_handler: false
	    });
	}
}

function chaxun(){
	if(event.keyCode==13){
		searchList();
	}
}

function qingli(){
	jQuery("#carln").html('未选车');
	jQuery("#carln").attr("title","");
	jQuery("#messagetime").html(jQuery('#year').val()+"-00-00 00:00:00");
	jQuery("#zhuangtai").val('');
	jQuery("#operuser").val('');
	jQuery("#overimg").attr('src','../newimages/backgroup.gif');
	jQuery("#miaoshu").html('');
}

function mytreeonClick(event, treeId, treeNode){
	document.getElementById('chooseorgid').value=treeNode.id;
    searchList();
}

function searchList() {
	qingli();
	jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'vehicle_ln', value: formatSpecialChar(jQuery('#vehicle_ln').val()) },
		         {name: 'operate_user_name', value: formatSpecialChar(jQuery('#operate_user_name').val())},
	 		 	 {name: 'start_time', value: jQuery('#start_time').val()},
	 		 	 {name: 'end_time', value: jQuery('#end_time').val()},
	 		 	 {name: 'chooseorgid', value: jQuery('#chooseorgid').val()}] 	
	});
	jQuery('#gala').flexReload();	
}

jQuery(function() {
	var gala = jQuery('#gala');
	gala.flexigrid({
		url: '<s:url value="/overloadflexgrid/newgetoverloadlist.shtml" />',
		dataType: 'json',
		height: '2000',
		width:'700',
		colModel : [{display: '车辆VIN', name : 'VEHICLE_VIN', width : 120, sortable : true, align: 'center',hide:true,toggle:false},	
		    		{display: '<s:text name="common.vehcileln" />', name : 'vehicle_ln', width : calcColumn(0.15,132,240), sortable : true, align: 'center',escape: true},
		    		{display: '状态', name : '', width : 60, sortable : false, align: 'center',hide:true,toggle:false},
		    		{display: '操作人', name : 'USER_NAME', width : calcColumn(0.1,100,240), sortable : true, align: 'center',escape: true},
		    		{display: '巡检时间', name : 'FLAG_TIME', width : calcColumn(0.15,120,240), sortable : true, align: 'center'},
		    		{display: '拍照时间', name : 'PHOTO_TIME', width : calcColumn(0.15,120,240), sortable : true, align: 'center'},
		    		{display: '描述信息', name : 'PHOTO_DESC', width : calcColumn(0.35,130,240), sortable : true, align: 'center',escape: true},			
		    		{display: '查看照片', name : 'photo_file', width : 80, sortable : false, align: 'center',hide:true,toggle:false}			
		    		],
		    		    		
		    	sortname: 'vehicle_ln,FLAG_TIME',
		    	sortorder: 'desc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 20,
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		    	params: [{name: 'vehicle_ln', value: jQuery('#vehicle_ln').val() }, 
		 		 		{ name: 'operate_user_name', value: jQuery('#operate_user_name').val()},
		 		 		{ name: 'start_time', value: jQuery('#start_time').val()},
		 		 		{ name: 'end_time', value: jQuery('#end_time').val()}]  ,
		        onRowSelect:rowstandbyclick  	
	});
});

var rowstandbyclick =function(rowData) {
	var imgpath=".."+jQuery(rowData).data("photo_file");
	jQuery("#overimg").attr('src',imgpath);
	var vehln=jQuery(rowData).data("vehicle_ln");
	if(vehln.length>7){
		jQuery("#carln").html("*"+encodeHtml(vehln.substr(vehln.length-7)));
		jQuery("#carln").attr("title",vehln);
	}else{
		jQuery("#carln").html(encodeHtml(vehln));
		jQuery("#carln").attr("title","");
	}
	//jQuery("#carln").html(encodeHtml());
	jQuery("#messagetime").html(jQuery(rowData).data("FLAG_TIME"));
	jQuery("#zhuangtai").val('超载');
	jQuery("#operuser").val(encodeHtml(jQuery(rowData).data("USER_NAME")));
	var desc=jQuery(rowData).data("PHOTO_DESC");
	desc=encodeHtml(desc);
	jQuery("#miaoshu").html(desc);	
}

//页面自适应
function firstrisize(){
	//计算中区高度
	jQuery('#content').mk_autoresize( {
		height_include : [ '#content_rightside', '#content_leftside' ],
		height_offset : 0
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize( {
		height_exclude : '#leftInfoDiv',
		height_include : '#lefttree',
		height_offset :8

	});

	//计算树区域高度
	/*
	jQuery('#lefttree').mk_autoresize( {
		height_include : '#treeDemo',
		height_offset : 5
	});*/

	jQuery('#content').mk_autoresize({
	    width_exclude:'#content_leftside',
	    //height_include: '.bDiv',
	    //height_offset: 290, // 该值各页面根据自己的页面布局调整
	    width_include: '.flexigrid',
	    width_offset: 10// 该值各页面根据自己的页面布局调整
	  });

	 
	jQuery('#content_rightside').mk_autoresize({
	    //width_exclude:'#content_leftside',
	    height_include: '.bDiv',
	    height_offset: 383 // 该值各页面根据自己的页面布局调整
	    //width_include: '.flexigrid',
	    //width_offset: -5// 该值各页面根据自己的页面布局调整
	  });

	/*
	jQuery('#content').mk_autoresize({
	    width_exclude:'#content_leftside',
	    height_include: '.bDiv ',
	    height_offset: 385, // 该值各页面根据自己的页面布局调整
	    width_include: '.flexigrid',
	    width_offset: -7// 该值各页面根据自己的页面布局调整
	  });*/
}

jQuery(function() {
	firstrisize();
	jQuery(window).mk_autoresize({
	     height_include: '#content',
	     height_exclude: ['#header', '#footer'],
	     height_offset: 0,
	     width_include: ['#header', '#content', '#footer'],
	     width_offset: 0
	  });
	firstrisize();    
});

function exportOverload() {
	var startime=document.getElementById('start_time').value;
	var endtime=document.getElementById('end_time').value;
	if(""==startime)
	{
		alert("请选择导出开始时间");
		return;	
	}
	if(""==endtime)
	{
		alert("请选择导出结束时间");
		return;	
	}
    var start_time=document.getElementById('start_time').value.replace(/-/g,"/");
    var end_time=document.getElementById('end_time').value.replace(/-/g,"/");
	var startdate=new Date(start_time);	
	var enddate=new Date(end_time);
	var idays = parseInt((enddate - startdate)/24/ 1000 / 60 / 60);
	if(idays> 6){
		alert("时间跨度大于7天,不可导出");
		return;
	}
if(confirm("确定要导出超载记录？")) {
	document.getElementById('overload_form').action="<s:url value='/overload/exportoverload.shtml' />";
	document.getElementById('overload_form').submit();
}
}
</script>