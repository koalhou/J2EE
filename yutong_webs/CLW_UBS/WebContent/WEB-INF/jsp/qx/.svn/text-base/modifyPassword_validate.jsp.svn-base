<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
  /** 检查旧密码 **/
  function checkOldPwd(){
    var elm = $('oldPwd');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
    }
  }

  /** 检查新密码 **/
  function checkNewPwd(){
    var elm = $('newPwd');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
    }
  }

  /** 检查确认密码 **/
  function checkConfirmPwd(){
    var elm = $('confirmPwd');
    var confirm=$('newPwd');

    if(Mat.checkRequired(elm)){
      if(elm.value==confirm.value){
        Mat.showSucc(elm);
        return true;
      } else {
        Wit.showErr(elm, "<s:property value="getText('user.pwd.confirm')" />");
        return false;
      }	
    } else {
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
    }
  }

  /** 初始化样式 **/
  function setFormMessage() {
    Wit.showDefault($('oldPwd'), '<span class="noticeMsg">*</span>');
    Wit.showDefault($('newPwd'), '<span class="noticeMsg">*</span>');
    Wit.showDefault($('confirmPwd'), '<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('oldPwd').addEvent('keyup', checkOldPwd);
    $('oldPwd').addEvent('blur', checkOldPwd);
    $('newPwd').addEvent('keyup', checkNewPwd);
    $('newPwd').addEvent('blur', checkNewPwd);
    $('confirmPwd').addEvent('keyup', checkConfirmPwd);
    $('confirmPwd').addEvent('blur', checkConfirmPwd);
  }

  /** 提交form **/
  function submitForm() {
    var f1 = checkOldPwd();
    var f2 = checkNewPwd();
    var f3 = checkConfirmPwd();
    
    if (f1 && f2 && f3) {
      Wit.commitAll($('modifypassword_form'));
    } else {
      return false;
    }
  }

  /** 重置操作 **/
  function resetForm() {
    setFormMessage();
    $('modifypassword_form').reset();
  }

  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>