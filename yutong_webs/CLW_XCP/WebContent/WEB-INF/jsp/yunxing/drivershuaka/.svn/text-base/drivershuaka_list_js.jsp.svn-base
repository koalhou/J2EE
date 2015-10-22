<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">

//页面自动加载
jQuery(function(){
	var ln=jQuery('#newveh_ln').val();
	var parm={"alarmmanage.alarm_type_id":1,vehicle_ln:ln};
	load_page("<s:url value='/drivershuaka/showDriveDurationPage.shtml'/>",parm);

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
	//计算树区域高度
	 if(jQuery('#middeldiv').css("display")=="none"){
		 jQuery('#content').mk_autoresize({
			    width_exclude:'#content_leftside',
			    //height_include: '.bDiv',
			    //height_offset: 290, // 该值各页面根据自己的页面布局调整
			    width_include: '.flexigrid',
			    width_offset: 10// 该值各页面根据自己的页面布局调整
			  });
			jQuery('#content_rightside').mk_autoresize({
			    height_include: '.bDiv',
			    height_offset: 192 // 该值各页面根据自己的页面布局调整
			  });
	 }else{
		 jQuery('#content').mk_autoresize({
			    width_exclude:'#content_leftside',
			    width_include: '.flexigrid',
			    width_offset: 32// 该值各页面根据自己的页面布局调整
			  });
			jQuery('#content_rightside').mk_autoresize({
			    height_include: '.bDiv',
			    height_offset: 192 // 该值各页面根据自己的页面布局调整
			  });
	 }
}

function load_page(url,parm) {
	jQuery('#content_rightside').load(url, parm, function() {
		jQuery(window).mk_autoresize({
            height_include: '#content',
            height_exclude: ['#header', '#footer'],
            height_offset: 0,
            width_include: ['#header', '#content', '#footer'],
            width_offset: 0
         });
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

//切换告警标题
function changetab(obj,flag){
	jQuery('#changeflag').val(flag);
	if(flag==1){
		load_page("<s:url value='/drivershuaka/showDriveDurationPage.shtml'/>");
	}
	if(flag==2){
		load_page("<s:url value='/drivershuaka/showRecordPage.shtml'/>");
	}
}
</script>