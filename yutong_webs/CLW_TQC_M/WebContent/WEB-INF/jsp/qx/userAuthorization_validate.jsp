<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>

<script type="text/javascript">
  var count = 0;
  
  /** 打开车主选择页面 **/
  function openUserBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/userinit.shtml' />" + "?count=" + (++count),
                                     self,
                                     "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }

  /** 打开角色选择页面 **/
  function openRoleBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/roleinit.shtml' />" + "?count=" + (++count),
                                     self,
                                     "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }
  
  /** 用户名称必填项 **/
  function checkUserName() {
    var elm = $('userName');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }

  /** 用户名称必填项 **/
  function checkRoleName() {
    var elm = $('roleName');
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
	Mat.setDefault($('userName'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('roleName'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
  }

  /** 提交form **/
  function submitForm() {
    var f1 = checkUserName();
    var f2 = checkRoleName();
	if (f1 && f2) {
	  if(confirm("<s:property value="getText('confirm.authorization')" />")) {
	    Wit.commitAll($('userauthorization_form'));
      } else {
        return false;
      }
	} else {
	  return false;
	}
  }

  function goBack(url) {
    Wit.goBack(url);
  }

  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>