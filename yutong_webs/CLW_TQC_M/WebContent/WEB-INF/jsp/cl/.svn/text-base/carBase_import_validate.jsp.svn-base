<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/CarDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/NoticeCoreDwr.js'></script>
<script type="text/javascript">
function checkCR_CONFIG_ID(){
	var elm = $('cr_config_id');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
	}
  }
function checkENGINE_TYPE(){
    var elm = $('engine_type_info');
	var elm_bra=$('brand');
	if(elm_bra.value==""){
		Wit.showErr(elm, "<s:property value="getText('carbase.js.brand')" />");
	}
	else if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
	}
  }
/** 检查文件是否选择 **/
function checkFilePath() {
	var fileObj = $('clwForm_file');
    var filePath = fileObj.value;
    if("" == filePath) {
      Wit.showErr(fileObj, "<s:property value="getText('please.select.flie')" />");
      return false;
    } else {
      Mat.showSucc(fileObj);
      return true;
    }
  }
function checkCAR_TYPE_BRAND(){
	var elm = $('car_type_brand');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
	}
}
function checkBRAND(){
	var elm = $('brand');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
	}
}

function show_vehicle_type_info(val){
	var elm=$('vehicle_type_info');
	if(val==""){
		elm.value="";
	}
	else{
		CarDwr.getVehicleInfo(val, callBackFun3);
	    function callBackFun3(data)
	    {
	    	elm.value=data;
	    }
	}
}
function show_vehicle_type_list(){
	var elm_bra=$('car_type_brand');
	var elm=$('vehicle_type_id_info');
	var show_info=$('vehicle_type_info');
	if(elm_bra.value==""){
		show_info.value="";
		Wit.showErr(elm, "<s:property value="getText('carbase.js.car_type_brand')" />");
	}
	else{
		show_info.value="";
	    /** 打开车型选择子页面 **/
	      var obj = window.showModalDialog("<s:url value='/popup/vehicle_type_init.shtml' />" + "?rad="+Math.random()
	    	      +"&popup_name=&popup_code="+elm_bra.value,
	      	                             self,
	      	                             "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
	}
}
function clearInfo(){
	var elm1=$('vehicle_type_id');
	var elm2=$('vehicle_type_id_info');
	var elm3=$('vehicle_type_info');
	elm1.value='';
	elm2.value='';
	elm3.value='';
}
function clearInfo2(){
	var elm1=$('engine_type');
	var elm2=$('engine_type_info');
	elm1.value='';
	elm2.value='';
}
function show_engine_type_list(){
	var elm_bra=$('brand');
	var elm=$('engine_type_info');
	if(elm_bra.value==""){
		Wit.showErr(elm, "<s:property value="getText('carbase.js.brand')" />");
	}
	else{
	    /** 打开车型选择子页面 **/
	      var obj = window.showModalDialog("<s:url value='/popup/engine_type_init.shtml' />" + "?rad="+Math.random()
	    	      +"&popup_name=&popup_code="+elm_bra.value,
	      	                             self,
	      	                             "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
	}
}


function checkVEHICLE_TYPE_ID(){
	var elm = $('vehicle_type_id_info');
	var elm_bra=$('car_type_brand');
	if(elm_bra.value==""){
		Wit.showErr(elm, "<s:property value="getText('carbase.js.car_type_brand')" />");
	}
	else if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
	}
}
/** 初始化样式 **/
function setFormMessage() {
	  Mat.setDefault($('cr_config_id'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('engine_type_info'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('vehicle_type_id_info'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('car_type_brand'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('brand'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('vehicle_type_info'),'');
}

/** 加载事件 **/
function setFormEvent() {
  $('cr_config_id').onkeyup = checkCR_CONFIG_ID;
  $('cr_config_id').onblur = checkCR_CONFIG_ID;
  $('engine_type_info').onkeyup = checkENGINE_TYPE;
  $('engine_type_info').onblur = checkENGINE_TYPE;
  $('vehicle_type_id_info').onkeyup = checkVEHICLE_TYPE_ID;
  $('vehicle_type_id_info').onblur = checkVEHICLE_TYPE_ID;

  $('car_type_brand').onkeyup = checkCAR_TYPE_BRAND;
  $('car_type_brand').onblur = checkCAR_TYPE_BRAND;
  $('brand').onkeyup = checkBRAND;
  $('brand').onblur = checkBRAND;
  
}
function submitPostFrom() {
	var f1 = checkCR_CONFIG_ID();
	var f2 = checkENGINE_TYPE();
	var f3 = checkFilePath();
	var f4 = checkCAR_TYPE_BRAND();
	var f5 = checkBRAND();
	var f6 = checkVEHICLE_TYPE_ID();
	

		if (f1&&f2&&f3&&f4&&f5&&f6) {
			if(confirm("<s:property value="getText('confirm.import.file')" />")) {
				NoticeCoreDwr.noticeCore("1", "0");
				Wit.commitAll($('clwForm'));
		      }
		}
		else  {
			  return false;
		}
	}
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>