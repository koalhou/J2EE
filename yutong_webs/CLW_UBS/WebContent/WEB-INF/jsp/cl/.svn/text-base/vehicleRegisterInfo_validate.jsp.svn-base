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
    var obj = window.showModalDialog("<s:url value='/popup/enterpriseViewAndCreate.shtml' />" + "?rad="+Math.random(),
    	                             self,
    	                             "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }

  /** 打开车主选择页面 **/
  /**
  function openOwnerBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/ownerinit.shtml' />" + "?count=" + (++count),
                                     self,
                                     "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }**/

  /** 打开车辆选择页面 **/
  function openVehicleBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/vehicleinit.shtml' />" + "?rad="+Math.random(),
                                     self,
                                     "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }

  /** 打开终端选择页面 **/
  function openTerminalBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/terminalinit.shtml' />" + "?rad="+Math.random(),
                                     self,
                                     "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }

  /** 打开SIM卡选择页面 **/
  function openSimBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/siminit.shtml' />" + "?rad="+Math.random(),
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

  /** 车辆信息必选 **/
  function checkVehicle() {
    var obj = $('vehicleVin');
    if(Mat.checkRequired(obj)) {
      Mat.showSucc(obj);  
      return true;
    } else {
      Wit.showErr(obj, "<s:property value="getText('msg.check.required')" />");
      return false;        
    }
  }

  /** 终端必选 **/
  function checkTerminal() {
	var obj = $('terminalCode');
    if(Mat.checkRequired(obj)) {
      Mat.showSucc(obj);  
      return true;
    } else {
      Wit.showErr(obj, "<s:property value="getText('msg.check.required')" />");
      return false;        
    }
  }
  
  /** SIM卡必填项 **/
  function checkSim() {
    var elm = $('simCardNumber');
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
    Mat.setDefault($('entipriseName'),'<span class="noticeMsg">*</span>');
    //Mat.setDefault($('userName'),'');
    Mat.setDefault($('vehicleVin'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('terminalCode'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('simCardNumber'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
  }

  /** 提交form **/
  function submitForm() {
    var f1 = checkEntiprise();
    var f2 = checkVehicle();
    var f3 = checkTerminal();
    var f4 = checkSim();
	if (f1 && f2 && f3 && f4) {
      NoticeCoreDwr.noticeCore("0", "1");
	  Wit.commitAll($('vehicleregisterinfo_form'));
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