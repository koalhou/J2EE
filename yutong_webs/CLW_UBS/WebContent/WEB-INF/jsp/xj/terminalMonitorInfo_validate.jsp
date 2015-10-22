<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
  /** 初始化样式 **/
  function setFormMessage() {
	<s:if test="#session.perUrlList.contains('111_0_5_4_2')">
    Mat.setDefault($('indexProperty'),'');  
    </s:if>
  }

  /** 检查特征系数输入项 **/
  function checkIndexInput() {
    var elm = $('indexProperty');

    if(Mat.checkRequired(elm, "<s:property value="getText('msg.check.required')" />")) {
      if(Mat.checkDigital(elm, "<s:property value="getText('number.permission')" />")) {
        return true;
      } else {
		return false;
      }
    } else {
      return false;
    }
  }
  
  /** 加载事件 **/
  function setFormEvent() {
	<s:if test="#session.perUrlList.contains('111_0_5_4_2')">
      $('indexProperty').onkeyup = checkIndexInput;
      $('indexProperty').onblur = checkIndexInput;
    </s:if>
  }

  /** 设置终端特征系数 **/
  function setIndexProperty() {
    var form = $('terminalmonitorinfo_form');
    form.action = 'setIndexPropertyByCode.shtml';
    if(checkIndexInput()) {
      if(confirm("<s:property value="getText('params.indexproperty.confirm')" />")) {
        Wit.commitAll(form);
      }
    }
  }
  
  /** 获取终端参数操作 **/
  function queryTerminalParams(url) {
    if(confirm("<s:property value="getText('params.query.confirm')" />")) {
      Wit.goBack(url);
    }
  }

  /** 显示最新终端参数信息 **/
  function showTerminalParams(url) {
    Wit.goBack(url);
  }
  
  /** 返回 **/
  function goBack(url) {
    Wit.goBack(url);
  }

  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>