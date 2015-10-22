<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/TmAttributeDwr.js'></script>

<script type="text/javascript">
  /** 获取终端型号列表 **/
  function getTypeInfo() {
    var oemObj = $('oemId');
    if(oemObj.value != "") {
      TmAttributeDwr.getTmTypeByOemId(oemObj.value, callBackFun);
      Mat.showSucc(oemObj);
    }

    function callBackFun(data) {
      var typeObj = $('typeId');  
      DWRUtil.removeAllOptions(typeObj);  
      DWRUtil.addOptions(typeObj,{'':'<s:property value="getText('please.select')" />'});  
      DWRUtil.addOptions(typeObj,data);  
    }
  }

  /** 终端型号列表变化 **/
  function onTypeChange() {
    var typeObj = $('typeId');
    if(typeObj.value != "") {
      Mat.showSucc(typeObj);
    }
  }
  
  /** 协议名称必填项 **/
  function checkProtocalName() {
    var elm = $('protocalName');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }
  
  /** 初始化样式 **/
  function setFormMessage() {
	Mat.setDefault($('oemId'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('typeId'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('protocalName'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('protocalName').onkeyup = checkProtocalName;
    $('protocalName').onblur = checkProtocalName;
  }

  /** 终端厂商必填 **/
  function checkOem() {
    var oemObj = $('oemId');
    if(oemObj.value == "") {
      Wit.showErr(oemObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }
  
  /** 终端型号必填 **/
  function checkType() {
    var typeObj = $('typeId');
    if(typeObj.value == "") {
      Wit.showErr(typeObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }
  
  /** 提交form **/
  function submitForm() {
    var f1 = checkProtocalName();
    var f2 = checkOem();
    var f3 = checkType();
	if (f1 && f2 && f3) {
	  if(confirm("<s:property value="getText('confirm.modify')" />")) {
	    Wit.commitAll($('protocaledit_form'));
	  } else {
        return false;
      }
	} else  {
	  return false;
	}
  }

  /** 取消更新操作 **/
  function goBack(url) {
    Wit.goBack(url);
  }

  /** 删除协议信息 **/
  function delProtocal(url) {
    if(confirm("<s:property value="getText('confirm.delete')" />")) {
      Wit.goBack(url);
    } else {
      return false;
    }
  }
  
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>