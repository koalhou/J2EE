<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
  /** 品牌类型变化 **/
  function onBrandChange() {
    var obj = $('parentId');
    if(obj !=null && obj.value != "") {
      Mat.showSucc(obj);
    }
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
  
  /** 检查文件是否选择 **/
  function checkFilePath() {
	var fileObj = $('baseinfoimport_form_file');
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
    if($('parentId')!=null) {
      Mat.setDefault($('parentId'),'<span class="noticeMsg">*</span>');
	}
	Mat.setDefault($('codeType'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
  }

  /** 提交form **/
  function submitForm() {
	var f1 = checkBrand();
    var f2 = checkFilePath();
	if (f1 && f2) {
      if(confirm("<s:property value="getText('confirm.import.file')" />")) {
	    Wit.commitAll($('baseinfoimport_form'));
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