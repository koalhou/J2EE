<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/UserManageDwr.js'></script>

<script type="text/javascript">
  /** 密码验证 **/
  function checkNewPwd(){
    var elm = $('password');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }
  
  /** 确认密码验证 **/
  function checkConfirmPwd(){
    var elm = $('confirmPassword');
    var confirm=$('password');

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
	Mat.setDefault($('password'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('confirmPassword'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('password').onkeyup = checkNewPwd;
    $('password').onblur = checkNewPwd;
    $('confirmPassword').onkeyup = checkConfirmPwd;
    $('confirmPassword').onblur = checkConfirmPwd;
  }

  /** 重置密码 **/
  function submitForm() {
    var f1 = checkNewPwd();
    var f2 = checkConfirmPwd();
	if (f1 && f2) {
      if(confirm("<s:property value="getText('user.reset.confirm')"/>")) {
	    Wit.commitAll($('userReset_form'));
      }
	} else  {
	  return false;
	}
  }

  /** 取消操作 **/
  function goBack(url) {
    Wit.goBack(url);
  }
  
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>