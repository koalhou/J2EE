<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
  /** 型号名称必填项 **/
  function checkDeviceName() {
    var elm = $('typeName');
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
	Mat.setDefault($('typeName'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('typeName').onkeyup = checkDeviceName;
    $('typeName').onblur = checkDeviceName;
  }

  /** 提交form **/
  function submitForm() {
    var f1 = checkDeviceName();
	if (f1) {
	  Wit.commitAll($('deviceinfo_form'));
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