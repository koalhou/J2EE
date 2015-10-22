<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
 
  /** 检查文件是否选择 **/
  function checkFilePath() {
	var fileObj = $('driverImport_form_file');
    var filePath = fileObj.value;
    if("" == filePath) {
      Wit.showErr(fileObj, "<s:property value="getText('please.select.flie')" />");
      return false;
    } else {
      Mat.showSucc(fileObj);
      return true;
    }
  }

  /** 初始化样式 **/
  function setFormMessage() {
  }

  /** 加载事件 **/
  function setFormEvent() {
  }

  /** 提交form **/
  function submitForm() {
    var f2 = checkFilePath();
	if (f2) {
      if(confirm("<s:property value="getText('confirm.import.file')" />")) {
	    Wit.commitAll($('driverImport_form'));
      }
	} else {
	  return false;
	}
  }

  /** 取消填加操作 **/
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
	function get_page_height() {
		var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
		  return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
	}
	//获取页面宽度
	function get_page_width() {
		var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
		  return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
	}
	 
	//计算控件宽高
	function testWidthHeight(){
		var h = get_page_height();
		var test=document.getElementById("driverTable");
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
		if(h>165){
			test.style.height = h-165;
		}
	}
</script>