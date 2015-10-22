<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/TmAttributeDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/TerminalManageDWR.js'></script>
<script type='text/javascript' src='../dwr/interface/NoticeCoreDwr.js'></script>
<script type="text/javascript">
  /** 获取终端型号列表 **/
  function getTypeInfo() {
    var oemObj = $('oemId');
    TmAttributeDwr.getTmTypeByOemId(oemObj.value, callBackFun);

    if(oemObj.value != "") {
      Mat.showSucc(oemObj);
    }
    
    function callBackFun(data) {
      var typeObj = $('typeId');  
      DWRUtil.removeAllOptions(typeObj);  
      DWRUtil.addOptions(typeObj,{'':'<s:property value="getText('please.select')" />'});  
      DWRUtil.addOptions(typeObj,data); 
    
      var protocalObj = $('protocalId');
      DWRUtil.removeAllOptions(protocalObj);  
      DWRUtil.addOptions(protocalObj,{'':'<s:property value="getText('please.select')" />'});  
    }
  }

  /** 获取终端协议列表 **/
  function getProtocalInfo() {
    var typeObj = $('typeId');
    TmAttributeDwr.getProtocalByTypeId(typeObj.value, callBackFun);
    
    if(typeObj.value != "") {
      Mat.showSucc(typeObj);
    }
    
    function callBackFun(data) {
      var protocalObj = $('protocalId');  
      DWRUtil.removeAllOptions(protocalObj);  
      DWRUtil.addOptions(protocalObj,{'':'<s:property value="getText('please.select')" />'});  
      DWRUtil.addOptions(protocalObj,data);  
    }
  }

  /** 终端协议列表变化 **/
  function onProtocalChange() {
	var protocalObj = $('protocalId');
	if(protocalObj.value != "") {
	  Mat.showSucc(protocalObj);
	}
  }
  

  /** 终端厂商必选 **/
  function checkOem() {
    var oemObj = $('oemId');
    if(oemObj.value == "") {
      Wit.showErr(oemObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }
  
  /** 终端型号必选 **/
  function checkType() {
    var typeObj = $('typeId');
    if(typeObj.value == "") {
      Wit.showErr(typeObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }

  /** 终端协议必选 **/
  function checkProtocal() {
    var protocalObj = $('protocalId');
    if(protocalObj.value == "") {
      Wit.showErr(protocalObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }

  /** 检查文件是否选择 **/
  function checkFilePath() {
	var fileObj = $('terminalimport_form_file');
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
	Mat.setDefault($('oemId'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('typeId'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('protocalId'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
  }

  /** 提交form **/
  function submitForm() {
    var f1 = checkOem();
    var f2 = checkType();
    var f3 = checkProtocal();
    var f4 = checkFilePath();
    
	if (f1 && f2 && f3 && f4) {
      if(confirm("<s:property value="getText('confirm.import.file')" />")) {
    	NoticeCoreDwr.noticeCore("0", "1");  
	    Wit.commitAll($('terminalimport_form'));
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