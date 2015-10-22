<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type="text/javascript">
/** 打开车辆选择页面 **/
function openVehicleBrowse() {
  var obj = window.showModalDialog("<s:url value='/popup/vehicleinit.shtml' />" + "?rad="+Math.random(),
                                   self,
                                   "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
}

//如果VIN已经存在,则在相应输入框中写入相应的值
function checkVIN(){
	var elm = $('vehicleVin');
	//DWREngine.setAsync(false);
    var ret = true; 
    //CarDwr.checkVin(elm.value, callBackFun);
    //function callBackFun(data){ret = data;}
    //DWREngine.setAsync(true);
    if(ret) {
    	return true;
  	} else {
  		Wit.showErr(elm, "<s:property value="getText('carbase.vin.only')" />");
      	return false;
  	}
}

function clearInfo(){
	var elm1=$('vehicleVin');
	elm1.value='';
}


  /** 初始化样式 **/
  function setFormMessage() {
	  Mat.setDefault($('vehicleVin'),'<span class="noticeMsg">*</span>');
	  //Mat.setDefault($('vehicle_ln'),'');
  }

  /** 加载事件 **/
  function setFormEvent() {
	try{
    	$('vehicleVin').onkeyup = checkVIN;
    	$('vehicleVin').onblur = checkVIN;
	}catch(e){}
  }

function submitPostFrom() {
	trimAllObjs();
	var f1 = checkVIN();
	var f2 = checkVIN();
	if (f1&&f2) {
		Wit.commitAll($('maintenanceForm'));
	}else {
		return false;
	}
}
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>