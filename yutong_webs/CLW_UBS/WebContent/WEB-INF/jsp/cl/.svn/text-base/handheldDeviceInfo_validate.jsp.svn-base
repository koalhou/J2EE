<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/NoticeCoreDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/HandheldDeviceDWR.js'></script>
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

  /** 验证IMEI信息 **/
  function checkCellPhoneImei() {
    var obj = $('cellPhoneImei');
    var reg = /^[a-z-A-Z-0-9]{15}$/;
	if(reg.test(obj.value)) {
		Mat.showSucc(obj);
		return true;
	} else {
		Wit.showErr(obj, "请输入15位字符");
		return false;
	}
  }

  /** 设备编号必选 **/
  function checkHandheldDeviceNo() {
	var obj = $('handheldDeviceNo');
	var reg = /^[a-z-A-Z-0-9]{6}$/;
	if(reg.test(obj.value)) {
		Mat.showSucc(obj);
		return true;
	} else {
		Wit.showErr(obj, "请输入6位字符");
		return false;
	}
  }
  
  /** 手机号必填项 **/
  function checkCellPhone() {
    var obj = $('cellPhone');
    var reg = /^[a-z-A-Z-0-9]{11}$/;
	if(reg.test(obj.value)) {
		Mat.showSucc(obj);
		return true;
	} else {
		Wit.showErr(obj, "请输入11位字符");
		return false;
	}
  }

  /** 初始化样式 **/
  function setFormMessage() {
    Mat.setDefault($('entipriseName'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('cellPhoneImei'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('handheldDeviceNo'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('cellPhone'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
	$('entipriseName').onkeyup = checkEntiprise;
	$('entipriseName').onblur = checkEntiprise;
	$('cellPhoneImei').onkeyup = checkCellPhoneImei;
	$('cellPhoneImei').onblur = checkCellPhoneImei;
	$('handheldDeviceNo').onkeyup = checkHandheldDeviceNo;
	$('handheldDeviceNo').onblur = checkHandheldDeviceNo;
	$('cellPhone').onkeyup = checkCellPhone;
	$('cellPhone').onblur = checkCellPhone;
  }

  /** 检查手机IMEI号唯一性 **/
  function checkCellphoneImeiUnique(){
    var obj = $('cellPhoneImei');
	    
	DWREngine.setAsync(false);
	var ret = false; 
	HandheldDeviceDWR.checkCellphoneImeiUnique(obj.value, callBackFun);
	    
	function callBackFun(data)
	{
	  ret = data;
	}
	    
	DWREngine.setAsync(true);
	   
	if(ret) {
	  Wit.showErr(obj, "手机IMEI号已存在！");
	  return false;
	} else {
	  return true;
	}
  }

  /** 检查设备号唯一性 **/
  function checkDeviceNumberUnique(){
    var obj = $('handheldDeviceNo');
	    
	DWREngine.setAsync(false);
	var ret = false; 
	HandheldDeviceDWR.checkDeviceNumberUnique(obj.value, callBackFun);
	    
	function callBackFun(data)
	{
	  ret = data;
	}
	    
	DWREngine.setAsync(true);
	   
	if(ret) {
	  Wit.showErr(obj, "设备编号已存在！");
	  return false;
	} else {
	  return true;
	}
  }
  
  /** 检查手机号唯一性 **/
  function checkCellphoneUnique(){
    var obj = $('cellPhone');
	    
	DWREngine.setAsync(false);
	var ret = false; 
	HandheldDeviceDWR.checkCellNumberUnique(obj.value, callBackFun);
	    
	function callBackFun(data)
	{
	  ret = data;
	}
	    
	DWREngine.setAsync(true);
	   
	if(ret) {
	  Wit.showErr(obj, "手机号已存在！");
	  return false;
	} else {
	  return true;
	}
  }
  
  /** 提交form **/
  function submitForm() {
    var f1 = checkEntiprise();
    var f2 = checkCellPhoneImei();
    var f3 = checkHandheldDeviceNo();
    var f4 = checkCellPhone();
    var f5 = checkCellphoneImeiUnique();
    var f6 = checkDeviceNumberUnique();
    var f7 = checkCellphoneUnique();
	if (f1 && f2 && f3 && f4 && f5 && f6 && f7) {
      NoticeCoreDwr.noticeCore("1", "1");
	  Wit.commitAll($('handhelddeviceinfo_form'));
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