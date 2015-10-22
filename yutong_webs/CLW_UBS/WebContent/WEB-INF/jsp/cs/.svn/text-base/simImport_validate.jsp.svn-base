<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/NoticeCoreDwr.js'></script>
<script type="text/javascript">
  /** 运营商列表变化 **/
  function onBusinessChange() {
    var businessObj = $('businessId');
    if(businessObj.value != "") {
      Mat.showSucc(businessObj);
    }
  }

  /** 运营商必选 **/
  function checkBusiness() {
    var businessObj = $('businessId');
    if(businessObj.value == "") {
      Wit.showErr(businessObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }

  /** 检查文件是否选择 **/
  function checkFilePath() {
	var fileObj = $('simimport_form_file');
    var filePath = fileObj.value;
    if("" == filePath) {
      Wit.showErr(fileObj, "<s:property value="getText('please.select.flie')" />");
      return false;
    } else {
      Mat.showSucc(fileObj);
      return true;
    }
  }

  /** 初始化样式 **/
  function setFormMessage() {
	Mat.setDefault($('businessId'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
  }

  /** 提交form **/
  function submitForm() {
    var f1 = checkBusiness();
    var f2 = checkFilePath();
	if (f1 && f2) {
      if(confirm("<s:property value="getText('confirm.import.file')" />")) {
    	NoticeCoreDwr.noticeCore("0", "1");
	    Wit.commitAll($('simimport_form'));
      }
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