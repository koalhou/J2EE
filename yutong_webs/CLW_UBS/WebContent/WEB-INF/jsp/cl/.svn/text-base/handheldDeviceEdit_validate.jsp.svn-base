<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/NoticeCoreDwr.js'></script>
<script type="text/javascript">
  var count = 0;

  /** 打开企业选择子页面 **/
  function openEnterpriseBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/enterpriseinit.shtml' />" + "?rad="+Math.random(),
    	                             self,
    	                             "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }

  /** 所属企业必选 **/
  function checkEntiprise() {
    var entipriseObj = $('entipriseName');
    if(Mat.checkRequired(entipriseObj)) {
      Mat.showSucc(entipriseObj);
      return true;      
    } else {
      Wit.showErr(entipriseObj, "<s:property value="getText('msg.check.required')" />");
      return false;  
    }
  }

  /** 初始化样式 **/
  function setFormMessage() {
    Mat.setDefault($('entipriseName'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
	$('entipriseName').onkeyup = checkEntiprise;
	$('entipriseName').onblur = checkEntiprise;
  }

  /** 提交form **/
  function submitForm() {
    var f1 = checkEntiprise();
	if (f1) {
	  if(confirm("<s:property value="getText('confirm.modify')" />")) {
	    Wit.commitAll($('handhelddeviceedit_form'));
	  }
	} else  {
	  return false;
	}
  }

  /** 删除手持设备注册信息 **/
  function delHandheldDevice(url) {
    if(confirm("<s:property value="getText('confirm.delete')" />")) {
      NoticeCoreDwr.noticeCore("1", "1");  
      Wit.goBack(url);
    } else {
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