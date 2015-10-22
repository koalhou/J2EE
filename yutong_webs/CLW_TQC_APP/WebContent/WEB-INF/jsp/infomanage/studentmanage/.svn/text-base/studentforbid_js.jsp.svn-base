<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
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
	function get_page_width() {
	  var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
	  return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
	}
		
	//获取页面高度
	function get_page_height() {
	  var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
	  return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
	}
		 
	//计算控件宽高
	function testWidthHeight(){
		var h = get_page_height();
		var w = get_page_width();
		var test=document.getElementById("forbidTbl");
		if(h>165){
			test.style.height = h-200;
		}
			
		jQuery(window).mk_autoresize({
	       height_include: '#content',
	       height_exclude: ['#header', '#footer'],
	       height_offset: 0,
	       width_include: ['#header', '#content', '#footer'],
	       width_offset: 0
	    });
		    
		h = get_page_height();
		w = get_page_width();
		if(h>165){
			test.style.height = h-200;
		}
	}
</script>