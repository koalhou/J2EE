<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type="text/javascript">
	/** 获取所属市信息 **/
	function getCityInfo() {
	  var provinceObj = $('studentProvince');
	  
	  ZoneDwr.getGeographyInfoByPid(provinceObj.value, callBackFun);
	
	  if(provinceObj.value != "") {
	    Mat.showSucc(provinceObj);
	  }
	  
	  function callBackFun(data) {
	    var cityObj = $('studentCity');  
	    DWRUtil.removeAllOptions(cityObj);  
	    DWRUtil.addOptions(cityObj,{'':'<s:property value="getText('please.select')" />'});  
	    if(provinceObj.value != "") {
	      if(null!=data){
			if(data.length>0){
			  DWRUtil.addOptions(cityObj,data,"zone_id","zone_name");
			}
		  }
	    }
	    var districtObj = $('studentDistrict');
	    DWRUtil.removeAllOptions(districtObj);  
	    DWRUtil.addOptions(districtObj,{'':'<s:property value="getText('please.select')" />'});  
	  }
	}
	
	/** 获取所属县信息 **/
	function getDistrictInfo() {
	    var cityObj = $('studentCity');
	    ZoneDwr.getGeographyInfoByPid(cityObj.value, callBackFun);
	    
	    if(cityObj.value != "") {
	      Mat.showSucc(cityObj);
	    }
	    
	    function callBackFun(data) {
	      var districtObj = $('studentDistrict');  
	      DWRUtil.removeAllOptions(districtObj);  
	      DWRUtil.addOptions(districtObj,{'':'<s:property value="getText('please.select')" />'});  
	      if(cityObj.value != "") {
	    	  if(null!=data){
	  			if(data.length>0){
	  				DWRUtil.addOptions(districtObj,data,"zone_id","zone_name");
	  			}
			  }
	      }
	        
	    }
	}
	
	/** 所属县变化 **/
	function onDistrictChange() {
		  var districtObj = $('studentDistrict');
		  if(districtObj.value != "") {
		    Mat.showSucc(districtObj);
		  }
	}
	
	/** 所属省必选 **/
	function checkProvince() {
	  var provinceObj = $('studentProvince');
	  if(provinceObj.value == "") {
	    Wit.showErr(provinceObj, "<s:property value="getText('please.select')" />");
	    return false;
	  } else {
		Mat.showSucc(provinceObj);
	    return true;
	  }
	}
		  
	/** 所属市必选 **/
	function checkCity() {
	  var cityObj = $('studentCity');
	  var provinceObj = $('studentProvince');
	  if(provinceObj.value == "710000" ||
	     provinceObj.value == "810000" ||
	     provinceObj.value == "820000") {
		Mat.showSucc(cityObj);
	    return true;
	  } else {
	    if(cityObj.value == "") {
	      Wit.showErr(cityObj, "<s:property value="getText('please.select')" />");
	      return false;
	    } else {
	      Mat.showSucc(cityObj);
	      return true;
	    }
	  }
	}
	
	/** 所属县区必选 **/
	function checkDistrict() {
	  var districtObj = $('studentDistrict');
	  var provinceObj = $('studentProvince');
	  if(provinceObj.value == "710000" ||
	     provinceObj.value == "810000" ||
	     provinceObj.value == "820000") {
	    Mat.showSucc(districtObj);
	    return true;
	  } else {
	    if(districtObj.value == "") {
	      Wit.showErr(districtObj, "<s:property value="getText('please.select')" />");
	      return false;
	    } else {
	      Mat.showSucc(districtObj);
	      return true;
	    }
	  }
	}
	
  /** 检查文件是否选择 **/
  function checkFilePath() {
	var fileObj = $('studentImport_form_file');
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
    Mat.setDefault($('studentProvince'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('studentCity'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('studentDistrict'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
  }

  /** 提交form **/
  function submitForm() {
    var f2 = checkFilePath();
    var f3 = checkProvince();
	var f4 = checkCity();
	var f5 = checkDistrict();
	
	if (f2 && f3 && f4 && f5) {
      if(confirm("<s:property value="getText('confirm.import.file')" />")) {
	    Wit.commitAll($('studentImport_form'));
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
		var test=document.getElementById("studentTable");
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