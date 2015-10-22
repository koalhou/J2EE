<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/OemManageDWR.js'></script>

<script type="text/javascript">
  /** 厂家名称必填项 **/
  function checkOemName() {
    var elm = $('fullName');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }

  /** 获取所属省信息 **/
  function getProvinceInfo() {
    var countryObj = $('countryId');
    
    ZoneDwr.showZoneXsInfo(countryObj.value, callBackFun);

    if(countryObj.value != "") {
      Mat.showSucc(countryObj);
    }
    
    function callBackFun(data) {
      var provinceObj = $('provinceId');  
      DWRUtil.removeAllOptions(provinceObj);  
      DWRUtil.addOptions(provinceObj,{'':'<s:property value="getText('please.select')" />'});  
      if(countryObj.value != "") {
    	  DWRUtil.addOptions(provinceObj,data);
      }
    
      var cityObj = $('cityId');
      DWRUtil.removeAllOptions(cityObj);  
      DWRUtil.addOptions(cityObj,{'':'<s:property value="getText('please.select')" />'});  
    }
  }

  /** 获取所属市信息 **/
  function getCityInfo() {
    var provinceObj = $('provinceId');
    ZoneDwr.showZoneXsInfo(provinceObj.value, callBackFun);
    
    if(provinceObj.value != "") {
      Mat.showSucc(provinceObj);
    }
    
    function callBackFun(data) {
      var cityObj = $('cityId');  
      DWRUtil.removeAllOptions(cityObj);  
      DWRUtil.addOptions(cityObj,{'':'<s:property value="getText('please.select')" />'});  
      if(provinceObj.value != "") {
    	  DWRUtil.addOptions(cityObj,data);
      }
        
    }
  }

  /** 所属市变化 **/
  function onCityChange() {
	var cityObj = $('cityId');
	if(cityObj.value != "") {
	  Mat.showSucc(cityObj);
	}
  }
  
  /** 初始化样式 **/
  function setFormMessage() {
	Mat.setDefault($('oemCode'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('fullName'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('shortName'),'');
    Mat.setDefault($('concatAddress'),'');
    Mat.setDefault($('webAddress'),'');
    Mat.setDefault($('concatPerson'),'');
    Mat.setDefault($('cellPhone'),'');
    Mat.setDefault($('email'),'');
    Mat.setDefault($('tel'),'');
    Mat.setDefault($('oemDesc'),'');
    Mat.setDefault($('serviceTel'),'');
    Mat.setDefault($('fax'),'');
    Mat.setDefault($('postcode'),'');
  }

  /** 加载事件 **/
  function setFormEvent() {
	$('oemCode').onkeyup = checkOemCode;
	$('oemCode').onblur = checkOemCode;
    $('fullName').onkeyup = checkOemName;
    $('fullName').onblur = checkOemName;
  }

  /** 厂家编码必填 **/
  function checkOemCode() {
    var obj = $('oemCode');
    if(Mat.checkRequired(obj)){
        Mat.showSucc(obj);
        return true;
  	}else{
        Wit.showErr(obj, "<s:property value="getText('msg.check.required')" />");
        return false;
  	}
  }  
  
  /** 检查厂家编码唯一性 **/
  function checkOemCodeUnique(){
    var codeObj = $('oemCode');
        
	DWREngine.setAsync(false);
	var ret = false; 
	OemManageDWR.checkOemCodeUnique(codeObj.value, callBackFun);
	    
	function callBackFun(data)
	{
	  ret = data;
	}
	    
	DWREngine.setAsync(true);
	   
	if(ret) {
	  Wit.showErr(codeObj, "<s:property value="getText('oem.code.exist')" />");
	  return false;
	} else {
	  return true;
	}
  }

  /** check描述文字长度 **/
  function checkMessageInput() {
    var elm = $('oemDesc');
    if(elm.value != "") {
      if(!Mat.checkLength(elm,30,'<s:text name="oem.desc.length" />')) {
        return false;
      } else {
        Mat.showSucc(elm);
        return true;
      }
	} else {
      return true;
	}
  }
    
  /** 提交form **/
  function submitForm() {
    var f1 = checkOemName();
    var f2 = checkOemCode();
    var f4 = checkMessageInput();
	if (f1 && f2 && f4) {
      var oemCodeOld = $('oemCodeOld');
      var oemCode = $('oemCode');		
      if(oemCodeOld.value != oemCode.value) {
        // CODEID变化时，检查CODEID唯一性  
        var f3 = checkOemCodeUnique();
        if(f3) {
          if(confirm("<s:property value="getText('confirm.modify')" />")) {
       	    Wit.commitAll($('oemEdit_form'));
          }  
        }
      } else {
	    if(confirm("<s:property value="getText('confirm.modify')" />")) {
	      Wit.commitAll($('oemEdit_form'));
        } 
      }
	} else  {
	  return false;
	}
  }

  function goBack(url) {
    Wit.goBack(url);
  }

  /** 删除厂家信息 **/
  function delOem(url) {
    if(confirm("<s:property value="getText('confirm.delete')" />")) {
      Wit.goBack(url);
    } else {
        return false;
    }
  }

  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>