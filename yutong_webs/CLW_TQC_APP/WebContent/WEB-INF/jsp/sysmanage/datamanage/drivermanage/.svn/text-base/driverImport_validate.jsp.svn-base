<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript"><!--
 
  /** 检查文件是否选择 **/
  function checkFilePath() {
	var fileObj = $('fileupload');
    var filePath = fileObj.value;
    if("" == filePath) {
      Wit.showErr(fileObj, "<s:property value="getText('please.select.flie')" />");
      return false;
    } else {
      Mat.showSucc(fileObj);
      return true;
    }
  }
  
  /*function uploadImage() {
	  var doc=document.getElementById("fileupload").value;
	  alert(doc);
	  $(document)
		.ready(
				function() {
					var options = {
						url : '<s:url value='/driver/upDriver.shtml'/>,
						type : "POST",
						dataType : "script",
						success : function(msg) {
							if (msg.indexOf("#") > 0) {
								$("#successLbl").html(msg);
								
							} else {
								$("#successLbl").html(msg);
							}
						}
					};
					//$("#form2").ajaxSubmit(options);
					return false;
				});
	  //val eee=doc.substr(doc.length()-3);
	  //alert(eee);
	  //document.getElementById('driverImport_form').action = "<s:url value='/driver/upDriver.shtml' />";
	  //document.getElementById('driverImport_form').submit();
	}*/

  /** 初始化样式 **/
  function setFormMessage() {
  }

  /** 加载事件 **/
  function setFormEvent() {
  }
  /** 提交form **/
  var extArray = new Array(".xls", ".xlsx");
  
  
  
  function submitForm(form, file) {
	  jQuery('#message').hide();
	  jQuery('#Below_new').show();
	
	  var value=jQuery('#enfile').val();
		if(value.length<1){
			//alert("qingshuru");
			jQuery('#Below_new').html("您没有选择.xls后缀文件，请选择！");
			return false;
		}
		if(value.substr(value.length-4)!=".xls" ){
			//alert("选择文件类型错误，请您选择Excel文件！");
			jQuery('#Below_new').html("选择文件类型错误，请您选择.xls后缀文件！");
			return false;
		}
	jQuery('#Below_new').hide();
      if(confirm("<s:property value="getText('confirm.import.file')" />")) {
	    Wit.commitAll($('driverImport_form'));
      }
  }
 
	  /*
	  allowSubmit = false;  
	  if (!file) return;  
	 while (file.indexOf("\\") != -1)
	  file = file.slice(file.indexOf("\\") + 1);  
	  ext = file.slice(file.indexOf(".")).toLowerCase();  
	  for (var i = 0; i < extArray.length; i++) {  
	  if (extArray[i] == ext) { allowSubmit = true; break; }  
	  }  
	  if (allowSubmit) {
		  
	    var f2 = checkFilePath();
		if (f2) {
	      if(confirm("<s:property value="getText('confirm.import.file')" />")) {
		    Wit.commitAll($('driverImport_form'));
	      }
		} else {
		  return false;
		}
	  }else  {
		  alert("对不起，只能上传以下格式的文件:  "  + (extArray.join("  ")) + "\n请重新选择符合条件的文件"  + "再上传.");
		  return false;
		  }
	  }
	  */
  

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