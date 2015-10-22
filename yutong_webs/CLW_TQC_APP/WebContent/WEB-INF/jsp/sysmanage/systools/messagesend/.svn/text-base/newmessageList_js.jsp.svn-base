<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function qingli(){
	jQuery("#carln").html('未选车');
	jQuery("#carln").attr("title","");
	jQuery("#messagetime").html(jQuery('#year').val()+"-00-00 00:00:00");
	jQuery("#zhuangtai").val('');
	jQuery("#user").val('');
	jQuery("#upanddown").html('');
	jQuery("#neirong").val('');
}

function chaxun(){
	if(event.keyCode==13){
		searchList();
	}
}

function mychange(){
	searchList();
}

function changeMe(obj){
  var sms_type = obj;  
  var serchtime = document.getElementById("serchtime");
	    
   if(sms_type=='全部'){
   var serchtime = document.getElementById("serchtime");
	serchtime.style.display="";
   }else if(sms_type=='下行'){
   var serchtime = document.getElementById("serchtime");
	serchtime.style.display="none";
   }else{
   var serchtime = document.getElementById("serchtime");
	serchtime.style.display="";
   }
 }


function mytreeonClick(event, treeId, treeNode){
	document.getElementById('chooseorgid').value=treeNode.id;
	//qingli();
    searchList();
}
		
		


function midScreen(){
	show();
}

		function searchList() {
		qingli();
		jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'vehicle_ln', value: jQuery('#vehicle_ln').val() },
		         {name: 'vehicle_code', value: jQuery('#vehicle_code').val() },
		{name: 'sms_type', value: jQuery('#sms_type').val() },
		{name: 'start_time', value: jQuery('#start_time').val() },
		{name: 'end_time', value: jQuery('#end_time').val() },
		{ name: 'chooseorgid', value: jQuery('#chooseorgid').val()}]
			});
			jQuery('#gala').flexReload();		 	
		}
		
		
		function reWriteLink(tdDiv,pid,row){
		    	var username=row.cell[4];
		    	/* if(username.length>7){
		    		return username.substring(0,7);
		    	}else{
			    	return username;
		    	} */
		    	return username;
		}
		
		jQuery(function() {
			var gala = jQuery('#gala');
			gala.flexigrid({
				url: '<s:url value="/messagepkg/newmessagesendflexgrid.shtml" />',
				dataType: 'json',
				height: '2000',
				width:'800',
				colModel : [
{display: '班车号', name : 'vehicle_code', width : calcColumn(0.1,100,250), sortable : true, align: 'center',toggle:false},
				    		{display: '<s:text name="common.vehcileln" />', name : 'vehicle_ln', width : calcColumn(0.1,65,250), sortable : true, align: 'center',escape: true},
				    		{display: '<s:text name="message.oprtime" />', name : 'operate_time', width : calcColumn(0.15,100,250), sortable : true, align: 'center'},
				    		{display: '<s:text name="message.state" />', name : 'deal_state', width : 120, sortable : true, align: 'center'},
				    		{display: '发消息人', name : 'user_name', width : calcColumn(0.05,85,250), sortable : true, align: 'center',escape: true,process:reWriteLink},
				    		{display: '上下行', name : 'sms_type', width : calcColumn(0.06,55,250), sortable : true, align: 'center',hide:true,toggle:false},
				    		{display: '<s:text name="message.content" />', name : 'remark', width : calcColumn(0.43,120,250), sortable : true, align: 'center',escape: true}	
				    		],
				    		    		
				    	sortname: 'operate_time',
				    	sortorder: 'desc',
				    	sortable: true,
						striped :true,
						usepager :true, 
						resizable: false,
				    	useRp: true,
				    	rp: 20,
				    	
						rpOptions : [10,20,50,100 ],// 可选择设定的每页结果数
				    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
				    	params: [{name: 'sms_type', value: jQuery('#sms_type').val()}],
				    	onRowSelect:rowstandbyclick
				    	//getQuery :getQuery
			});
		});

		var rowstandbyclick =function(rowData) {
			var vehln=jQuery(rowData).data("vehicle_ln");
			var code=jQuery(rowData).data("vehicle_code");
			if(vehln.length>7){
				jQuery("#carln").html(code+"号 (*"+encodeHtml(vehln.substr(vehln.length-7))+")");
				jQuery("#carln").attr("title",vehln);
			}else{
				jQuery("#carln").html(code+"号 ("+encodeHtml(vehln)+")");
				jQuery("#carln").attr("title","");
			}
			$("#messagetime").html(jQuery(rowData).data("operate_time"));
			$("#zhuangtai").val(jQuery(rowData).data("deal_state"));
			$("#user").val(encodeHtml(jQuery(rowData).data("user_name")));
			//$("#upanddown").html(jQuery(rowData).data("sms_type"));
			var remark=jQuery(rowData).data("remark");
			//remark=encodeHtml(remark);
			$("#neirong").val(remark);
			};	
		
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
	    height_offset: 288 // 该值各页面根据自己的页面布局调整
	    //width_include: '.flexigrid',
	    //width_offset: -5// 该值各页面根据自己的页面布局调整
	  });

	/*
	jQuery('#content').mk_autoresize({
	    width_exclude:'#content_leftside',
	    height_include: '.bDiv',
	    height_offset: 289, // 该值各页面根据自己的页面布局调整
	    width_include: '.flexigrid',
	    width_offset: 10 // 该值各页面根据自己的页面布局调整
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
	        width_offset:32,// 该值各页面根据自己的页面布局调整
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

function show() {
    var leftInfo = $('#content_leftside');
    var leftTab = $('#leftTab');
    leftInfo.css('display', '');
    leftTab.css('display', 'none');
    var search  = $('#leftdiv');
    search.css('display', '');
    jQuery('#content').mk_autoresize({
        width_exclude: '#content_leftside',
        width_include: '.flexigrid',
        width_offset: -15,// 该值各页面根据自己的页面布局调整
        is_handler: false
    });
}

function hide() {
    var leftInfo = $('#content_leftside');
    var leftTab = $('#leftTab');
    leftInfo.css('display', 'none');
    leftTab.css('display', '');
    var search  = $('#leftdiv');
    search.css('display', 'none');
    jQuery('#content').mk_autoresize({
        width_exclude: '#content_leftside',
        width_include: '.flexigrid',
        width_offset:25,// 该值各页面根据自己的页面布局调整
        is_handler: false
    });
}
</script>