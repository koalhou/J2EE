<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script>
/** 提交form **/
function submitForm() {
  
	var f1 = checkName();
    var f2 = checkStartTime();
    var f3 = checkEndTime();
    if (f1 && f2 && f3 ) {
  	  Wit.commitAll($('holidayinfo_form'));
  	} else  {
  	  return false;
  	}

	
}
/** 删除**/
function del(url) {
  if(confirm("<s:property value="getText('confirm.delete')" />")) {
    Wit.goBack(url);
  } else {
    return false;
  }
}
/** 取消填加操作 **/
function goBack(url) {
  Wit.goBack(url);
}

function checkName()
{
	var elm = $('holidayname');
  	if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	    return false;
	}
  	if(!Mat.checkLength(elm,10)){
		Wit.showErr(elm, "<s:property value="getText('rolemanage.js.info.lenles10')"/>");
	    return false;
	}
  	Mat.showSucc(elm);
    return true;
}
function checkStartTime()
{
	 var elm = $('startTime');
	    if(Mat.checkRequired(elm)){
	      //Mat.showSucc(elm);
	      return true;
	  	}else{
	      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	      return false;
	  	}
}
function checkEndTime()
{
	 var elm = $('endTime');
	    if(Mat.checkRequired(elm)){
	      //Mat.showSucc(elm);
	      return true;
	  	}else{
	      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	      return false;
	  	}
}
/** 初始化样式 **/
function setFormMessage() {
	
  //Mat.setDefault($('cellPhone'),'<span class="noticeMsg">*</span>');

  //Mat.setDefault($('endTime'),'<span class="noticeMsg">*</span>');
}
/** 加载事件 **/
function setFormEvent() {
	  $('holidayname').onkeyup = checkName;
	  $('holidayname').onblur = checkName;
	  
	 // $('startTime').onkeyup = checkStartTime;
	 // $('startTime').onblur = checkStartTime;
	  
	  //$('endTime').onkeyup = checkEndTime;
	  //$('endTime').onblur = checkEndTime;
	  
	  
}
window.addEvent('domready', setFormMessage);
window.addEvent('domready', setFormEvent);
</script>