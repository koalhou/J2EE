<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
  function submit() {
	  var form=document.getElementById('ocktimeset_form');
	  form.submit();
  }
  
//查询考核月度
  function searchList() {

  	   document.getElementById('Below_new').style.display='none';
  	   
  	jQuery('#ocktimetbl').flexOptions({
  		newp: 1,
  		params: [{name: 'check_time_code', value: jQuery('#check_time_code').val() }]
  	});
  	jQuery('#ocktimetbl').flexReload();
  }


  function detialbrwose(ocktimeId){
  	var form = document.getElementById('ocktimeset_form');
  	form.action = 'editTimeSetbefore.shtml?check_time_id=' + ocktimeId;
  	form.submit();
  }
  function reWriteLink(tdDiv,pid){
  	//tdDiv.innerHTML = '<a href="javascript:detialbrwose(\'' + pid + '\')">' + tdDiv.innerHTML +'</a>';
  	return '<a href="javascript:detialbrwose(\'' + pid + '\')">' + tdDiv +'</a>';
  	}
  //<td class="tdClass"><a href="<s:url value='updateRoleBefore.shtml' />?role_id=<s:property value='role_id'/>"><s:property value="role_name" /></a></td>
  jQuery(function() {
  	var gala = jQuery('#ocktimetbl');
  	//alert(gala);
  	gala.flexigrid({
  		url: '<s:url value="/ocktimepkg/ocktimebrowse.shtml" />',
  		dataType: 'json',
  		height: 375,
  		width:2000,
  		colModel : [
  		    		{display: '考核月度', name : 'check_time_code', width : 100, sortable : true, align: 'center', process: reWriteLink},
  		    		{display: '开始时间', name : 'start_time', width : 150, sortable : true, align: 'center'},
  		    		{display: '结束时间', name : 'end_time', width : 150, sortable : true, align: 'center'},
  		    		{display: '月度描述', name : 'check_time_desc', width : 400, sortable : true, escape:true,align: 'center'}
  		    	   ],
  		    		    		
  		    	sortname: 'check_time_code',
  		    	sortorder: 'asc',
  		    	sortable: true,
  				striped :true,
  				usepager :true, 
  				resizable: false,
  		    	useRp: true,
  		    	rp: 10,
  		    	
  				rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
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
  	/*
   var height = 0;
   
   if (jQuery.browser.msie) {
       height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
   } else {
       height = self.innerHeight;
   }
   return height;*/
   var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
   return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
  }
  //获取页面宽度
  function get_page_width() {
  	/*
   var width = 0;
   if(jQuery.browser.msie){ 
    width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
   } else { 
    width = self.innerWidth; 
   } 
   return width;*/
   var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
   return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
  }

  function firstrisize(){
  	 var h = get_page_height();
  	 var w = get_page_width();
  	 jQuery(".flexigrid").width(w-30);
  	 //jQuery("#reportTab").height(h-100);
  	 jQuery(".bDiv").height(h-325);
  }
  //计算控件宽高
  function testWidthHeight(){
  	 firstrisize();
  	 jQuery(window).mk_autoresize({
  	     height_include: '#content',
  	     height_exclude: ['#header', '#footer'],
  	     height_offset: 0,
  	     width_include: ['#header', '#content', '#footer'],
  	     width_offset: 0
  	  });
  	 firstrisize();
  }
</script>