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
    var obj = window.showModalDialog("<s:url value='/popup/enterpriseinit.shtml' />" + "?count=" + (++count),
    	                             self,
    	                             "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }

  /** 打开车主选择页面 **/
  function openOwnerBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/ownerinit.shtml' />" + "?count=" + (++count),
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
    //Mat.setDefault($('userName'),'');
  }


  /** 检查文件是否选择 **/
  function checkFilePath() {
	var fileObj = $('vehicleregisterimport_form_file');
    var filePath = fileObj.value;
    if("" == filePath) {
      Wit.showErr(fileObj, "<s:property value="getText('please.select.flie')" />");
      return false;
    } else {
      Mat.showSucc(fileObj);
      return true;
    }
  }
  
  /** 加载事件 **/
  function setFormEvent() {
  }

  /** 提交form **/
  function submitForm() {
    var f1 = checkEntiprise();
    var f2 = checkFilePath();
	if (f1 && f2) {
      if(confirm("<s:property value="getText('confirm.import.file')" />")) {
    	NoticeCoreDwr.noticeCore("0", "1");
	    Wit.commitAll($('vehicleregisterimport_form'));
      }
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