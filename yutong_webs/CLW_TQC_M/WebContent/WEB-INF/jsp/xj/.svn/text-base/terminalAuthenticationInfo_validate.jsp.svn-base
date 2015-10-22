<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
  /** 初始化样式 **/
  function setFormMessage() {
  }

  /** 加载事件 **/
  function setFormEvent() {
  }

  /** 提交form **/
  function submitForm() {
    if(confirm("<s:property value="getText('authentication.deal')" />")) {
      Wit.commitAll($('terminalauthenticationinfo_form'));
    } else {
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