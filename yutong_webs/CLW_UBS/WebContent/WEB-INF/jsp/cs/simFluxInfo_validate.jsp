<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/SimManageDWR.js'></script>
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

  /** SIM卡号必填项 **/
  function checkSimCard() {
    var elm = $('simCardNumber');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }

  /** 检查SIM卡号唯一性 **/
  function checkSimNumberUnique(){
    var simNumberObj = $('simCardNumber');
	    
	DWREngine.setAsync(false);
	var ret = false; 
	SimManageDWR.checkSimFluxUnique(simNumberObj.value, callBackFun);
	    
	function callBackFun(data)
	{
	  ret = data;
	}
	    
	DWREngine.setAsync(true);
	   
	if(ret) {
	  Wit.showErr(simNumberObj, "<s:property value="getText('sim.code.exist')" />");
	  return false;
	} else {
	  return true;
	}
  }
  
  /** 初始化样式 **/
  function setFormMessage() {
	Mat.setDefault($('businessId'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('simCardNumber'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('cellPhone'),'');
    Mat.setDefault($('fluxValue'),'');
    Mat.setDefault($('callTime'),'');
    Mat.setDefault($('closeTime'),'');
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('simCardNumber').onkeyup = checkSimCard;
    $('simCardNumber').onblur = checkSimCard;
  }

  /** 提交form **/
  function submitForm() {
    var f1 = checkSimCard();
    var f2 = checkBusiness();
    var f3 = checkSimNumberUnique();
    
	if (f1 && f2 && f3) {
	  Wit.commitAll($('simfluxinfo_form'));
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