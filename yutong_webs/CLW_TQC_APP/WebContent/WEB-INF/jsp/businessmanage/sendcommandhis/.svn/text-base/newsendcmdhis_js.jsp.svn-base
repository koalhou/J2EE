<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function mytreeonClick(event, treeId, treeNode){
	document.getElementById('chooseorgid').value=treeNode.id;
    searchList();
}

function chaxun(){
	if(event.keyCode==13){
		searchList();
	}
}

function mychange(){
	searchList();
}

function searchList() {
	   var sendtype = document.getElementsByName("checkleixing");
	   var temp="";
	   for(var i=0; i<sendtype.length; i++) {
	       if(sendtype[i].checked==true) {
	    	   temp+=sendtype[i].value+",";
	       }
       }
       temp=temp.substr(0,temp.length-1);
       jQuery('#send_type').val(temp);
	   //var sendtype=jQuery('');
		jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'sendcommand.vehicle_ln', value: formatSpecialChar(jQuery('#vehicle_ln').val()) }, 
		         {name: 'sendcommand.vehicle_code', value: formatSpecialChar(jQuery('#vehicle_code').val())}, 
			 	 {name: 'sendcommand.send_type', value: jQuery('#send_type').val()},
			 	 {name: 'sendcommand.deal_state', value: jQuery('#deal_state').val()},
			 	 {name: 'sendcommand.operate_user_name', value: formatSpecialChar(jQuery('#operate_user_name').val())},
			 	 {name: 'chooseorgid', value: jQuery('#chooseorgid').val()},
			 	 {name: 'sendcommand.start_time', value: jQuery('#begintime').val()},
			 	 {name: 'endime', value: jQuery('#endime').val()}]	
	});
	jQuery('#gala').flexReload();	
}

function get_cell_text(pid, cell_idx) {
			return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
		}
       
function reWriteflag(tdDiv,pid){
	var deal_flag=tdDiv;
	if(deal_flag==3){
		 return  '下发成功';
	}else{
		 return '已下发';
	}
}

function reWritetype(tdDiv,pid){
	var send_type= tdDiv;
	   if(send_type ==2002 ){
	      return '消息';
	   }else if(send_type ==2001){
		  return '拍照';
	   }else if(send_type ==2400){
		 return '监听';
	   }else{
	     return  '重点监控';
	   }
	}

jQuery(function() {
	var gala = jQuery('#gala');
	gala.flexigrid({
		url: '<s:url value="/sendcmdflexgrid/newgetsendcmdlist.shtml" />',
		dataType: 'json',
		height: '2000',
		width:870,
		colModel : [
{display: '班车号', name : '', width :  calcColumn(0.05,120,250), sortable : true, align: 'center'},
		    		{display: '<s:text name="common.vehcileln" />', name : 'vehicle_ln', width : calcColumn(0.15,120,250), sortable : true, align: 'center'},
		    		{display: '指令类型', name : 'send_type', width :  calcColumn(0.1,120,250), sortable : true, align: 'center',process: reWritetype},
		    		{display: '指令状态', name : 'deal_state', width : calcColumn(0.1,126,250), sortable : true, align: 'center',process: reWriteflag},
		    		{display: '下发时间', name : 'operate_time', width :calcColumn(0.2,200,250), sortable : true, align: 'center'},
		    		{display: '操作人', name : 'operate_user_name', width :calcColumn(0.2,160,250), sortable : true, align: 'center',escape: true}
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
		    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		    	params: [{name: 'sendcommand.vehicle_ln', value: jQuery('#vehicle_ln').val() }, 
					 		{ name: 'sendcommand.send_type', value: jQuery('#send_type').val()},
					 		{ name: 'sendcommand.deal_state', value: jQuery('#deal_state').val()},
					 		{ name: 'sendcommand.operate_user_name', value: jQuery('#operate_user_name').val()},
					 		{ name: 'chooseorgid', value: jQuery('#chooseorgid').val()},
					 		{ name: 'sendcommand.start_time', value: jQuery('#begintime').val()},
					 		{ name: 'endime', value: jQuery('#endime').val()}]	
	});
});

//页面自适应
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

	jQuery('#content').mk_autoresize({
	    width_exclude:'#content_leftside',
	    width_include: '.flexigrid',
	    width_offset: 10// 该值各页面根据自己的页面布局调整
	  });

	 
	jQuery('#content_rightside').mk_autoresize({
	    height_include: '.bDiv',
	    height_offset: 183 // 该值各页面根据自己的页面布局调整
	  });
}

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
</script>