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

function mytreeonClick(event, treeId, treeNode){
	document.getElementById('chooseorgid').value=treeNode.id;
    searchList();
}

function mychange(){
	searchList();
}

function chaxun(){
	if(event.keyCode==13){
		searchList();
	}
}

function qingli(){
	jQuery("#carln").html('未选车');
	jQuery("#carln").attr("title","");
	jQuery("#messagetime").css('display','none');
	jQuery("#messagetime").html(jQuery('#year').val()+"-00-00 00:00:00");
	jQuery('#yy').removeClass();
    jQuery('#zdqqy').removeClass();
    jQuery('#zdtpms').removeClass();
    jQuery('#klzs').removeClass();
    jQuery('#jlzs').removeClass();
    jQuery('#ryzs').removeClass();
    jQuery('#jywd').removeClass();
    jQuery('#hsqgw').removeClass();
    jQuery('#cwg').removeClass();
    jQuery('#fdjwd').removeClass();
    jQuery('#wbdy').removeClass(); 
    jQuery('#shuiwei').removeClass(); 
    jQuery('#abs').removeClass(); 
    jQuery('#rygj').removeClass(); 
}
// 查询
function searchList() {
	qingli();
	jQuery('#vehicletbl').flexOptions({
		newp: 1,
		params: [{name: 'vehicle_ln', value: jQuery('#vehicle_ln').val()}, 
		 		{ name: 'searchState', value: jQuery('#searchState').val()},
		 		{ name: 'chooseorgid', value: jQuery('#chooseorgid').val()}
		 		]
	});
    jQuery('#vehicletbl').flexReload();
}


function yanzhongguzhang(tdDiv,pid,row){
	//tdDiv.style.height='16px';
	/*
    if(tdDiv.innerHTML==0){
    	tdDiv.innerHTML = '<span class="statusspan1">正常</span>';
    }else if(tdDiv.innerHTML==1){
    	tdDiv.innerHTML = '<span class="unusual">异常</span>';
    }else{
    	tdDiv.innerHTML = '<span class="statusspan1">正常</span>';
    	//tdDiv.innerHTML = '不在线';
    	//tdDiv.innerHTML = '异常';
    	//tdDiv.style.backgroundColor ='#ffccff';
    	//tdDiv.style.background='url(../images/qipaoimages/btn_blue.gif) repeat-x';
    	//tdDiv.style.filter='progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\'../images/qipaoimages/btn_blue.gif\', sizingMethod=\'scale\')';
    }*/
    if(tdDiv==0){
    	return '<span class="statusspan1">正常</span>';
    }else if(tdDiv==1){
    	return  '<span class="unusual">异常</span>';
    }else{
    	return '<span class="statusspan1">正常</span>';
    }
    

}

function zushe(tdDiv,pid,row){
	//tdDiv.style.margin-top=-3px;
	/*
    if(tdDiv.innerHTML==0){
    	tdDiv.innerHTML = '<span class="statusspan1">正常</span>';
    }else if(tdDiv.innerHTML==1){
    	tdDiv.innerHTML = '<span class="unusual">异常</span>';
    	//tdDiv.innerHTML='<span style=" disply:block; bckgrou">异常</span>';
    }else{
    	tdDiv.innerHTML = '<span class="statusspan1">正常</span>';
    }*/

    if(tdDiv==0){
    	return  '<span class="statusspan1">正常</span>';
    }else if(tdDiv==1){
    	return  '<span class="unusual">异常</span>';
    	//tdDiv.innerHTML='<span style=" disply:block; bckgrou">异常</span>';
    }else{
    	return  '<span class="statusspan1">正常</span>';
    }

}

function hightemp(tdDiv,pid,row){
	/*
    if(tdDiv.innerHTML==0){
    	tdDiv.innerHTML = '<span class="statusspan1">正常</span>';
    }else if(tdDiv.innerHTML==1){
    	tdDiv.innerHTML = '<span class="unusual">异常</span>';
    }else{
    	tdDiv.innerHTML = '<span class="statusspan1">正常</span>';
    }*/

    if(tdDiv==0){
    	return '<span class="statusspan1">正常</span>';
    }else if(tdDiv==1){
    	return '<span class="unusual">异常</span>';
    }else{
    	return '<span class="statusspan1">正常</span>';
    }

}

function changgui(tdDiv,pid,row){
	/*
    if(tdDiv.innerHTML==0){
    	tdDiv.innerHTML = '<span class="statusspan1">正常</span>';
    }else if(tdDiv.innerHTML==1){
    	tdDiv.innerHTML = '<span class="unusual">异常</span>';
    }else{
    	tdDiv.innerHTML = '<span class="statusspan1">正常</span>';
    }*/

    if(tdDiv==0){
    	return '<span class="statusspan1">正常</span>';
    }else if(tdDiv==1){
    	return '<span class="unusual">异常</span>';
    }else{
    	return '<span class="statusspan1">正常</span>';
    }

}


function pOtherPhoto(tdDiv,pid,row){
	/*
    if(tdDiv.innerHTML==0){
    	tdDiv.innerHTML = '<span class="statusspan1">正常</span>';
    }else if(tdDiv.innerHTML==1){
    	tdDiv.innerHTML = '<span class="unusual">异常</span>';
    }else{
    	tdDiv.innerHTML = '<span class="statusspan1">正常</span>';
    }*/

    if(tdDiv==0){
    	return '<span class="statusspan1">正常</span>';
    }else if(tdDiv==1){
    	return '<span class="unusual">异常</span>';
    }else{
    	return '<span class="statusspan1">正常</span>';
    }

}

function rewriteveh(tdDiv,pid,row) {
	//var vehln=tdDiv.innerHTML;
	//tdDiv.innerHTML='<span class="statusspan1">'+vehln+'</span>'
	return '<span class="statusspan1">'+tdDiv+'</span>';
}

function rewritetime(tdDiv,pid,row){
	//var vehtime=tdDiv.innerHTML;
	//tdDiv.innerHTML='<span class="statusspan1">'+vehtime+'</span>'
	return '<span class="statusspan1">'+tdDiv+'</span>';	
}


function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}



jQuery(function() {
	var gala = jQuery('#vehicletbl');
	//alert(gala);
	gala.flexigrid({
		url: '<s:url value="/vehiclestatuspkg/newvehiclebrowse.shtml" />',
		dataType: 'json',
		height: 375,
		width:'700',
		colModel : [
		    		{display: '<s:text name="common.vehcileln" />', name : 'VEHICLE_LN', width : calcColumn(0.1,100,240), sortable : true, align: 'center',process:rewriteveh},
		    		{display: '车辆VIN号', name : 'vin', width : 120, sortable : false, align: 'center',hide:true,toggle:false},
		    		{display: '', name : 'ONLINE_FLAG', width : 80, sortable : false, align: 'center',hide:true,toggle:false},
		    		{display: '', name : 'VEHSTAT', width : 80, sortable : false, align: 'center',hide:true,toggle:false},
		    		{display: '<s:text name="vehstatus.shangbaotime" />', name : 'TERMINAL_TIME', width : calcColumn(0.21,170,240), sortable :true, align: 'center',process:rewritetime},
		    		{display: '严重故障', name : 'YANZHONGSTATE', width : calcColumn(0.116,103,240), sortable : true, align: 'center',process: yanzhongguzhang},
		    		{display: '<s:text name="zusai" />', name : 'zuse', width : calcColumn(0.116,103,240), sortable : true, align: 'center',process: zushe},
		    		{display: '<s:text name="gaowen" />', name : 'HIGHTEMP', width : calcColumn(0.116,103,240), sortable : true, align: 'center',process: hightemp},
		    		{display: '常规故障', name : 'CANGGUI', width : calcColumn(0.116,103,240), sortable : true, align: 'center',process: changgui},
		    		{display: '其他故障', name : 'POTHER', width : calcColumn(0.116,103,240), sortable : true, align: 'center',process: pOtherPhoto}
		    	    ],  		
		    	sortname: 'VEHSTAT',
		    	sortorder: 'desc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 20,
				rpOptions :[10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		    	onRowSelect:rowstandbyclick,
		    	onSuccess:function(){
	                jQuery("tr[id^='row'] div").css('height','16px');
	                return true;
		        }
		    	//getQuery :getQuery
	});
});

  var rowstandbyclick =function(rowData) {
        //var onlineflag=jQuery(rowData).data("ONLINE_FLAG");
         var vehstat=jQuery(rowData).data("VEHSTAT");
         var vehln=jQuery(rowData).data("VEHICLE_LN");
         if(vehln.length>7){
				jQuery("#carln").html("*"+vehln.substr(vehln.length-7));
				jQuery("#carln").attr("title",vehln);
			}else{
				jQuery("#carln").html(vehln);
				jQuery("#carln").attr("title","");
			}
         jQuery("#messagetime").html(jQuery(rowData).data("TERMINAL_TIME"));
         jQuery("#messagetime").css('display','');
 
	     var yy=vehstat.substr(0,1);
	     if(yy=='0'){
		    jQuery('#yy').removeClass();
		    jQuery('#yy').addClass("green"); 
	     }else{
		    jQuery('#yy').removeClass();
		    jQuery('#yy').addClass("red"); 
	    }
		    
	    var zdqqy=vehstat.substr(1,1);
	    if(zdqqy=='0'){
		   jQuery('#zdqqy').removeClass();
		   jQuery('#zdqqy').addClass("green"); 
	    }else{
		   jQuery('#zdqqy').removeClass();
		   jQuery('#zdqqy').addClass("red"); 
	    }
	    
	   var zdtpms=vehstat.substr(2,1);
	   if(zdtpms=='0'){
		  jQuery('#zdtpms').removeClass();
		  jQuery('#zdtpms').addClass("green"); 
	   }else{
		 jQuery('#zdtpms').removeClass();
		 jQuery('#zdtpms').addClass("red"); 
	  }
		  
	  var klzs=vehstat.substr(3,1);
	  if(klzs=='0'){
		  jQuery('#klzs').removeClass();
		  jQuery('#klzs').addClass("green"); 
	  }else{
		  jQuery('#klzs').removeClass();
		  jQuery('#klzs').addClass("red"); 
	  }

	 var jlzs=vehstat.substr(4,1);
	 if(jlzs=='0'){
		 jQuery('#jlzs').removeClass();
		 jQuery('#jlzs').addClass("green"); 
	 }else{
		 jQuery('#jlzs').removeClass();
		 jQuery('#jlzs').addClass("red"); 
	 }

	 var ryzs=vehstat.substr(5,1);
	 if(ryzs=='0'){
		 jQuery('#ryzs').removeClass();
		 jQuery('#ryzs').addClass("green"); 
	 }else{
		 jQuery('#ryzs').removeClass();
		 jQuery('#ryzs').addClass("red"); 
	 }

	 var jywd=vehstat.substr(6,1);
	 if(jywd=='0'){
		 jQuery('#jywd').removeClass();
		 jQuery('#jywd').addClass("green"); 
	 }else{
		 jQuery('#jywd').removeClass();
		 jQuery('#jywd').addClass("red"); 
	 }

	 var hsqgw=vehstat.substr(7,1);
	 if(hsqgw=='0'){
		 jQuery('#hsqgw').removeClass();
		 jQuery('#hsqgw').addClass("green"); 
	 }else{
		 jQuery('#hsqgw').removeClass();
		 jQuery('#hsqgw').addClass("red"); 
	 }

	 var cwg=vehstat.substr(8,1);
	 if(cwg=='0'){
		 jQuery('#cwg').removeClass();
		 jQuery('#cwg').addClass("green"); 
	 }else{
		 jQuery('#cwg').removeClass();
		 jQuery('#cwg').addClass("red"); 
	 }

	 var fdjwd=vehstat.substr(9,1);
	 if(fdjwd=='0'){
		 jQuery('#fdjwd').removeClass();
		 jQuery('#fdjwd').addClass("green"); 
	 }else{
		 jQuery('#fdjwd').removeClass();
		 jQuery('#fdjwd').addClass("red"); 
	 }

	 var wbdy=vehstat.substr(10,1);
	 if(wbdy=='0'){
		 jQuery('#wbdy').removeClass(); 
		 jQuery('#wbdy').addClass("green"); 
	 }else{
		 jQuery('#wbdy').removeClass(); 
		 jQuery('#wbdy').addClass("red"); 
	 }

	 var shuiwei=vehstat.substr(11,1);
	 if(shuiwei=='0'){
		 jQuery('#shuiwei').removeClass(); 
		 jQuery('#shuiwei').addClass("green"); 
	 }else{
		 jQuery('#shuiwei').removeClass(); 
		 jQuery('#shuiwei').addClass("red"); 
	 }

	 var abs=vehstat.substr(12,1);
	 if(abs=='0'){
		 jQuery('#abs').removeClass(); 
		 jQuery('#abs').addClass("green"); 
	 }else{
		 jQuery('#abs').removeClass(); 
		 jQuery('#abs').addClass("red"); 
	 }

	 var rygj=vehstat.substr(13,1);
	 if(rygj=='0'){
		 jQuery('#rygj').removeClass(); 
		 jQuery('#rygj').addClass("green"); 
	 }else{
		 jQuery('#rygj').removeClass(); 
		 jQuery('#rygj').addClass("red"); 
	 }

	 
};


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
	    height_offset: 287 // 该值各页面根据自己的页面布局调整
	    //width_include: '.flexigrid',
	    //width_offset: -5// 该值各页面根据自己的页面布局调整
	  });
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
</script>