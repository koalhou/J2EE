<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
  /** 车辆必须选择 **/
  function checkVehicle() {
    var elm = $('vehicleLn');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }
  
  /** 起始时间必须选择 **/
  function checkStartDate() {
    var elm = $('startDate');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }

  /** 时间段必须选择 **/
  function checkMinuteValue() {
    var elm = $('minuteValue');
    if(elm.value == "") {
      Wit.showErr(elm, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      Mat.showSucc(elm);
      return true;
    }
  }
  
  function queryRealTerminalStatus() {
    var f1 = checkVehicle();
    var f2 = checkStartDate();
    var f3 = checkMinuteValue();
    if(f1 && f2 && f3) {
      Wit.commitAll($('terminalmonitorsingle_form'));
    } 
  }
  
  var count = 0;
  /** 打开车辆选择页面 **/
  function openVehicleBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/registevehicleinit.shtml' />" + "?count=" + (++count),
                                     self,
                                     "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }

  function setFormEvent() {
    Mat.setDefault($('vehicleLn'),'');
  }

  function setFormMessage() {
    
  }
  
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>