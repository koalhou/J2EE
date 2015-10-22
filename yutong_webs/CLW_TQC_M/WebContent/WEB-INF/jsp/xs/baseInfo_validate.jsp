<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/BaseInfoManageDwr.js'></script>
<script type="text/javascript">
  /** 名称必填项 **/
  function checkCodeName() {
    var elm = $('codeName');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }

  /** 消息类型变化 **/
  function onCodeTypeChange() {
    var codeTypeObj = $('codeType');
    if(codeTypeObj.value != "") {
      Mat.showSucc(codeTypeObj);
    }
  }

  /** 品牌类型变化 **/
  function onBrandChange() {
    var obj = $('parentId');
    if(obj !=null && obj.value != "") {
      Mat.showSucc(obj);
    }
  }
  
  /** 消息编码必填 **/
  function checkCodeId() {
    var codeIdObj = $('codeId');
    if(Mat.checkRequired(codeIdObj)) {
    	Mat.showSucc(codeIdObj);
        return true;
    } else {
    	 Wit.showErr(codeIdObj, "<s:property value="getText('msg.check.required')" />");
         return false;
    }
  }  
  
  /** 消息类型必选 **/
  function checkCodeType() {
    var codeTypeObj = $('codeType');
    if(codeTypeObj.value == "") {
      Wit.showErr(codeTypeObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }
  
  /** 初始化样式 **/
  function setFormMessage() {
	if($('parentId')!=null) {
      Mat.setDefault($('parentId'),'<span class="noticeMsg">*</span>');
	}
	Mat.setDefault($('codeType'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('codeId'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('codeName'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('codeLevel'),'');
    Mat.setDefault($('remark'),'');
  }

  /** 消息类型必选 **/
  function checkBrand() {
    var obj = $('parentId');
    
    if(obj!=null && obj.value == "") {
      Wit.showErr(obj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }
  
  /** 加载事件 **/
  function setFormEvent() {
    $('codeName').onkeyup = checkCodeName;
    $('codeName').onblur = checkCodeName;
  }

  /** 检查CodeId唯一性 **/
  function checkCodeIdUnique(){
    var codeObj = $('codeId');
    var codeTypeObj = $('codeType');
        
	DWREngine.setAsync(false);
	var ret = false; 
	BaseInfoManageDwr.checkCodeIdUnique(codeObj.value, codeTypeObj.value, callBackFun);
	    
	function callBackFun(data)
	{
	  ret = data;
	}
	    
	DWREngine.setAsync(true);
	   
	if(ret) {
	  Wit.showErr(codeObj, "<s:property value="getText('codeid.exist')" />");
	  return false;
	} else {
	  return true;
	}
  }

  /** 备注信息长度check **/
  function checkRemarkInput() {
    var elm = $('remark');
    if(elm.value != "") {
      if(!Mat.checkLength(elm,30,'<s:text name="baseinfo.remark.length" />')) {
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
    var f1 = checkCodeName();
    var f2 = checkCodeType();
    var f3 = checkCodeId();
    var f4 = checkCodeIdUnique();
    var f5 = checkBrand();
    var f6 = checkRemarkInput();
	if (f1 && f2 && f3 && f4 && f5 && f6) {
	  Wit.commitAll($('baseinfo_form'));
	} else  {
	  return false;
	}
  }

  /** 取消填加操作 **/
  function goBack(url) {
    Wit.goBack(url);
  }

  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>