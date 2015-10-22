<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/SimManageDWR.js'></script>
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

  /** 开卡时间必填 **/
  function checkStartUseTime() {
    var elm = $('startUseTime');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
  	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
  	}
  }
  
  /** SIM卡号必填项 **/
  function checkSimCard() {
    var elm = $('simCardNumber');
    var oemObj = $('businessId');

    /**
    if(oemObj.options[oemObj.selectedIndex].text.indexOf("移动") > 0) {
		var reg = /^[a-z-A-Z-0-9]{20}$/;
		if(reg.test(elm.value)) {
			Mat.showSucc(elm);
			return true;
		} else {
			Wit.showErr(elm, "<s:property value="getText('common.twenty')" />");
			return false;
		}
    } else {
        **/
    	var reg = /^[a-z-A-Z-0-9]+$/;
		if(reg.test(elm.value)) {
			Mat.showSucc(elm);
			return true;
		} else {
			Wit.showErr(elm, "<s:property value="getText('common.number.string')" />");
			return false;
		}
		/**
    }
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}**/
  }

  /** 手机号必填项 **/
  function checkCellNumber() {
    var elm = $('cellPhone');
    var reg = /^[0-9]{11}$/;
	if(reg.test(elm.value)) {
	  Mat.showSucc(elm);
	  return true;
	} else {
	  Wit.showErr(elm, "请输入11位数字");
	  return false;
	}
	/**
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}**/
  }
  
  /** 检查SIM卡号唯一性 **/
  function checkSimNumberUnique(){
    var simNumberObj = $('simCardNumber');
	    
	DWREngine.setAsync(false);
	var ret = false; 
	SimManageDWR.checkSimNumberUnique(simNumberObj.value, callBackFun);
	    
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

  /** 检查手机号唯一性 **/
  function checkCellNumberUnique(){
    var cellNumberObj = $('cellPhone');
	    
	DWREngine.setAsync(false);
	var ret = false; 
	SimManageDWR.checkCellNumberUnique(cellNumberObj.value, callBackFun);
	    
	function callBackFun(data)
	{
	  ret = data;
	}
	    
	DWREngine.setAsync(true);
	   
	if(ret) {
	  Wit.showErr(cellNumberObj, "<s:property value="getText('cellnumber.exist')" />");
	  return false;
	} else {
	  return true;
	}
  }    
  
  /** 初始化样式 **/
  function setFormMessage() {
	Mat.setDefault($('businessId'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('simCardNumber'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('cellPhone'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('iccidPrint'),'');
    Mat.setDefault($('startUseTime'),'<span class="noticeMsg">*</span>');
  }

//add by jinp start
//自动生成电子ICCID号
var cellVal = "";
//手动输入
var cellInput = "";
function checkIfSimcardIsEmpty() {
	var elm = $('cellPhone');
	var simCard = $('simCardNumber');
	if(simCard.value == cellVal && cellInput.length == 0) {
		simCard.value = elm.value;
		cellVal = simCard.value;
	}
}

function checkIfSimcardIsInput() {
	var elm = $('simCardNumber');
	if(elm.value != cellVal && elm.value.length > 0) {
		cellInput = elm.value;
	}
	if(elm.value.length == 0) {
	  	var cellPhone = $('cellPhone');
	  	if(cellPhone.value.length > 0) {
	  	  elm.value = cellPhone.value;
	  	  cellVal = elm.value;
	  	  cellInput = "";
	  	} else {
	  	  cellInput = "";
	  	  cellVal = "";
	  	}
	}
}
//add by jinp stop
  
  /** 加载事件 **/
  function setFormEvent() {
	  $('simCardNumber').onkeyup = checkSimCard;
	    //$('simCardNumber').onblur = checkSimCard;
	    $('simCardNumber').onblur = checkIfSimcardIsInput;
	    $('cellPhone').onkeyup = checkCellNumber;
	   // $('cellPhone').onblur = checkCellNumber;
	    $('cellPhone').onblur = checkIfSimcardIsEmpty;
  }

  /** 提交form **/
  function submitForm() {
    var f1 = checkSimCard();
    var f2 = checkBusiness();
    var f3 = checkStartUseTime();
    var f4 = checkCellNumber();
    
	if (f1 && f2 && f3 && f4) {
      var oldSimNumber = $('simOldNumber');
      var simNumber = $('simCardNumber');
      var oldPhoneNumber = $('oldPhoneNumber');
      var phoneNumber = $('cellPhone');
      
      if(oldSimNumber.value != simNumber.value) {
        // SIM卡号变化时，检查SIM卡号唯一性  
        var f5 = checkSimNumberUnique();
        if(f5) {
          if(oldPhoneNumber.value != phoneNumber.value) {
            var f6 = checkCellNumberUnique();
            if(f6) {
              if(confirm("<s:property value="getText('confirm.modify')" />")) {
               	NoticeCoreDwr.noticeCore("0", "1");
                Wit.commitAll($('simedit_form'));
              } 
            }
          } else {
            if(confirm("<s:property value="getText('confirm.modify')" />")) {
              NoticeCoreDwr.noticeCore("0", "1");
              Wit.commitAll($('simedit_form'));
            }
          }
        }
      } else {
        if(oldPhoneNumber.value != phoneNumber.value) {
          // SIM卡号无变化时，检查手机号是否有变化
          var f7 = checkCellNumberUnique();
          if(f7) {
            if(confirm("<s:property value="getText('confirm.modify')" />")) {
              NoticeCoreDwr.noticeCore("0", "1");
              Wit.commitAll($('simedit_form'));
            } 
          }
        } else {
          if(confirm("<s:property value="getText('confirm.modify')" />")) {
            NoticeCoreDwr.noticeCore("0", "1");
            Wit.commitAll($('simedit_form'));
          } 
        }
      }
	} else {
	  return false;
	}
  }

  /** 删除SIM卡信息 **/
  function delSim(url) {
    if(confirm("<s:property value="getText('confirm.delete')" />")) {
      Wit.goBack(url);
    } else {
      return false;
    }
  }
  
  /** 取消修改操作 **/
  function goBack(url) {
    Wit.goBack(url);
  }

  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>