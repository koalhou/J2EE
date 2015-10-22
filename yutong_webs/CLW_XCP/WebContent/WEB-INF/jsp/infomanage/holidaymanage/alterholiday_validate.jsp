<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript">
	/** 开始时间必填项 **/
	function checkStartTime() {
		var obj = $('holiday_start_time');
		if (!Mat.checkRequired(obj))
			return false;
		return true;
	}

	/** 结束时间必填项 **/
	function checkStopTime() {
		var obj = $('holiday_end_time');
		if (!Mat.checkRequired(obj))
			return false;
		return true;
	}

	
	/** 初始化样式 **/
	function setFormMessage() {
		<s:if test="0==holidayInfo.holiday_flag">
		Mat.setDefault($('holiday_start_time'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('holiday_end_time'),'<span class="noticeMsg">*</span>');
		</s:if>
	}

	/** 加载事件 **/
	function setFormEvent() {
		/**
		<s:if test="0==holidayInfo.holiday_flag">
		$('holiday_start_time').onkeyup = checkStartTime;
		$('holiday_start_time').onblur=checkStartTime;
		$('holiday_end_time').onkeyup = checkStopTime;
		$('holiday_end_time').onblur=checkStopTime;
		</s:if>
		**/
	}

	//输入时间早于当前系统时间  
	function smallerThanNow(){  
		var time = $('holiday_end_time').value;
	    var myDate = new Date();  
	    var now = myDate.toLocaleDateString()+" "+myDate.toLocaleTimeString();  
	    now = now.replace("年","-").replace("月","-").replace("日","");   
	    now = new Date(now.replace(/-/g, "/")); 
	    time = new Date(time.replace(/-/g, "/"));  
	    if(time==null || Date.parse(time) - Date.parse(now) < 0){  
	        return true;  
	    }else{  
	        return false;  
	    }  
	}  
	
	function submitalterForm() {
		trimAllObjs();
        var f0 = smallerThanNow();
        if(f0) {
        	Wit.showErr($('holiday_end_time'), "请假结束时间小于当前时间");
			return false;
        }
		var f1 = checkStartTime();
		var f2 = checkStopTime();
		
		if (f1 && f2) {
			Wit.commitAll($('alter_holidayform'));
		} else {
			return false;
		}
	}

	function goBack(url) {
		Wit.goBack(url);
	}

	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);

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
		var test=document.getElementById("holidayTable");
		if(h>165){
			test.style.height = h-165;
		}
		
		jQuery(window).mk_autoresize({
		       height_include: '#content',
		       height_exclude: ['#header', '#footer'],
		       height_offset: 0,
		       width_include: ['#header', '#content', '#footer'],
		       width_offset: 0
		    });

		h = get_page_height();
		test=document.getElementById("holidayTable");
		if(h>165){
			test.style.height = h-165;
		}
	}
</script>