<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type="text/javascript">
  /** 运营商名称必填项 **/
  function checkBusinessName() {
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
	  Mat.setDefault($('fullName'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('shortName'),'');
	  Mat.setDefault($('address'),'');
	  Mat.setDefault($('webSite'),'');
	  Mat.setDefault($('concatePerson'),'');
	  Mat.setDefault($('cellPhone'),'');
	  Mat.setDefault($('email'),'');
	  Mat.setDefault($('tel'),'');
	  Mat.setDefault($('businessDesc'),'');
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('fullName').onkeyup = checkBusinessName;
    $('fullName').onblur = checkBusinessName;
  }

  /** check描述文字长度 **/
  function checkMessageInput() {
    var elm = $('businessDesc');
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
    var f1 = checkBusinessName();
    var f2 = checkMessageInput();
	if (f1 && f2) {
	  if(confirm("<s:property value="getText('confirm.modify')" />")) {
	    Wit.commitAll($('businessEdit_form'));
      } else {
        return false;
      }
	  
	} else  {
	  return false;
	}
  }

  /** 取消操作 **/
  function goBack(url) {
    Wit.goBack(url);
  }

  /** 删除运营商信息 **/
  function delBusiness(url) {
    if(confirm("<s:property value="getText('confirm.delete')" />")) {
      Wit.goBack(url);
    } else {
        return false;
    }
  }

  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>