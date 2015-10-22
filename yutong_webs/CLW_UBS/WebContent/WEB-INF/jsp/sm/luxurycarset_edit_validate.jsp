<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/MaintDWR.js'></script>
<script type="text/javascript">

function checkVehicleNum(){
	var elm = $('vehicle_number');
	if(Mat.checkRequired(elm)){
		if(!Wit.checkErr(elm,/^\d{2}[A-Z]{1}\d{3}[A-Z]{1}-\d{4}$/)){
			Wit.showErr(elm, "车工号格式错误");
			return false;
		}else{
	      Mat.showSucc(elm);
	      return true;
		}
	}else{
      Wit.showErr(elm, "请填写,格式为：11A222B-1234");
      return false;
	}
  }
function isModify(){
	var elm = $('vehicle_number');
	if(jQuery.trim(jQuery("#vehicle_numberbk").attr("value")) == jQuery.trim(jQuery("#vehicle_number").attr("value"))){
		Wit.showErr(elm, "您还没有修改车工号!");
		return false;
	}
	return true;
}
function clearInfo(){
	var elm1=$('vehicle_number');
	elm1.value='';
}
  /** 初始化样式 **/
  function setFormMessage() {
	  Mat.showSucc($('vehicle_number'));
  }

//车工号唯一验证
function checkVehicleNumber(){
	if(!checkVehicleNum()){
		return false;
	}
	if(!isModify()){
		return false;
	}
	var elm = $('vehicle_number');
	if(elm.value == '' || !checkVehicleNum()) {
		Wit.showErr(elm, "请填写,格式为：11A222B-1234");
		return false;
	}
	DWREngine.setAsync(false);
    var ret = true; 
    MaintDWR.checkVNumber(elm.value, callBackFun);
    function callBackFun(data){ret = data;}
    DWREngine.setAsync(true);
    if(ret) {
    	Mat.showSucc($('vehicle_number'));
    	return true;
  	} else {
  		Wit.showErr(elm, "车工号已经存在!");
      	return false;
  	}
}

/** 加载事件 **/
function setFormEvent() {
  $('vehicle_number').onkeyup = checkVehicleNumber;
  $('vehicle_number').onblur = checkVehicleNumber;
}

  function submitPostFrom() {
	 if(!isModify()){
		return false;
		}
	if(confirm("<s:property value="getText('confirm.modify')" />")) {
		trimAllObjs();
		Wit.commitAll($('luxurycarsetForm'));
	}
  }
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>