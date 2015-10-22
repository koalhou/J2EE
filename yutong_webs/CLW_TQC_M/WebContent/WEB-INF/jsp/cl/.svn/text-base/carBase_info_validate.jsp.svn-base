<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/CarDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/NoticeCoreDwr.js'></script>
<script type="text/javascript">
function checkVEHICLE_VIN(){
	var elm = $('vehicle_vin');
	/**
    if(Mat.checkRequired(elm)){
    	Mat.showSucc(elm);
  	    return true; 
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
	}**/

	var reg = /^[a-z-A-Z-0-9]{17}$/;
	if(reg.test(elm.value)) {
		Mat.showSucc(elm);
		return true;
	} else {
		Wit.showErr(elm, "请输入17位字符");
		return false;
	}
  }
function checkVEHICLE_LN(){
	var elm = $('vehicle_ln');
	if(Mat.checkRequired(elm)){
    	Mat.showSucc(elm);
  	    return true; 
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
	}
  }
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

function checkBUSINESS_TYPE(){
	var elm = $('business_type');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
	}
  }
function checkTYRE_R(){
	var elm = $('tyre_r');
	if(Wit.checkErr(elm,/^(\d+(.\d{1,3})?)?$/)){
   	    Mat.showSucc(elm);
	    return true;
   	}
   	else{
   		Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber3')" />");
   	    return false;
	}
  }
function checkREAR_AXLE_RATE(){
	var elm = $('rear_axle_rate');
    if(Mat.checkRequired(elm)){
    	if(Wit.checkErr(elm,/^\d+(.\d{1,3})?$/)){
    	    Mat.showSucc(elm);
		    return true;
    	}
    	else{
    		Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber3')" />");
    	    return false;
		}
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
	}
  }
function checkSTANDARD_ROTATE(){
	var elm = $('standard_rotate');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
	}
  }
function checkLIMITE_NUMBER(){
	var elm = $('limite_number');
	/**
	if(!Mat.checkDigital(elm)&&elm.value!=''){
		Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
		return false;
	}
	Mat.showSucc(elm);
	return true;**/

	if(Mat.checkRequired(elm)){
    	if(Mat.checkDigital(elm)){
    	    Mat.showSucc(elm);
		    return true;
    	}
    	else{
    		Wit.showErr(elm, "<s:property value="getText('请输入不超过两位正整数')" />");
    		//Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
    		return false;
		}
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
	}
  }
function checkSTANDARD_OIL(){
	var elm = $('standard_oil');
	if(!Mat.checkDigital(elm)&&elm.value!=''){
		Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
		return false;
	}
	Mat.showSucc(elm);
	return true;
  }
function checkDEAD_LOAD(){
	var elm = $('dead_load');
	if(!Mat.checkDigital(elm)&&elm.value!=''){
		Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
		return false;
	}
	Mat.showSucc(elm);
	return true;
  }
function checkVEHICLE_NUMBER(){
	var elm = $('vehicle_number');
	if(Mat.checkRequired(elm)){
		if(!Wit.checkErr(elm,/^\d{2}[A-Z]{1}\d{3}[A-Z]{1}-\d{4}$/)){
			Wit.showErr(elm, "<s:property value="getText('carbase.js.vehicle_number')" />");
			return false;
		}else{
	      Mat.showSucc(elm);
	      return true;
		}
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
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
function checkVEHICLE_COLOR(){
	Mat.showSucc($('vehicle_color'));
	return true;
}
function checkENGINE_NUMBER(){
	var elm = $('engine_number');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')"/>");
      return false;
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

function checkOUT_NUMBER(){
	Mat.showSucc($('out_number'));
	return true;
}
function checkCAR_NUMBER(){
	Mat.showSucc($('car_number'));
	return true;
}
function checkSELL_ORDER_NUM(){
	Mat.showSucc($('sell_order_num'));
	return true;
}
function checkMADE_ORDER_NUM(){
	Mat.showSucc($('made_order_num'));
	return true;
}
function checkCHASSIS_ORDER_NUM(){
	Mat.showSucc($('chassis_order_num'));
	return true;
}


//function checkMADE_NUMBER(){
//	Mat.showSucc($('made_number'));
//	return true;
//}



//VIN唯一验证_edit
function checkVIN(){
	var elm = $('vehicle_vin');
	var elm_old = $('vehicle_vin_old');
	if(elm.value==elm_old.value){
		return true;
	}
	DWREngine.setAsync(false);
    var ret = true; 
    CarDwr.checkVin(elm.value, callBackFun);
    function callBackFun(data)
    {
        ret = data;
    }
    DWREngine.setAsync(true);
    if(ret) {
    	return true;
  	} else {
  		Wit.showErr(elm, "<s:property value="getText('carbase.vin.only')" />");
      	return false;
  	}
}
//LN唯一验证_edit
function checkLN(){
	var elm = $('vehicle_ln');
	var elm_old = $('vehicle_ln_old');
	var parten = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
  	if(parten.exec(elm.value)){
	
  	} else {
		Wit.showErr(elm, "只能输入汉字、字母、数字或其组合！");
		return false;
  	}
	if(elm.value==elm_old.value){
		return true;
	}
	if(elm.value==''||elm.value==null){
		return true;
	}
	DWREngine.setAsync(false);
    var ret = true; 
    CarDwr.checkLn(elm.value, callBackFun);
    function callBackFun(data)
    {
        ret = data;
    }
    DWREngine.setAsync(true);
    if(ret) {
    	return true;
  	} else {
  		Wit.showErr(elm, "<s:property value="getText('carbase.ln.only')" />");
      	return false;
  	}
}
function show_vehicle_type_info(val){
	var elm=$('vehicle_type_info');
	var elm2=$('vehicle_type_id_info');
	
	if(val==""){
		elm.value="";
		elm2.value="";
	}
	else{
		CarDwr.getVehicleInfo(val, callBackFun3);
	    function callBackFun3(data)
	    {
	    	elm.value=data;
	    	elm2.value=data;
	    }
	}
}
function show_engine_type_info(val){
	var elm=$('engine_type_info');
	
	if(val==""){
		elm.value="";
	}
	else{
		CarDwr.getEngineInfo(val, callBackFun4);
	    function callBackFun4(data)
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
		//show_info.value="";
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
  /** 初始化样式 **/
  function setFormMessage() {
	  Mat.setDefault($('vehicle_vin'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('vehicle_ln'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('cr_config_id'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('business_type'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('tyre_r'),'');
	  Mat.setDefault($('rear_axle_rate'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('standard_rotate'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('limite_number'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('standard_oil'),'');
	  Mat.setDefault($('dead_load'),'');

	  Mat.setDefault($('vehicle_color'),'');
	  Mat.setDefault($('engine_number'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('out_number'),'');
	  Mat.setDefault($('vehicle_number'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('car_number'),'');
	  //Mat.setDefault($('made_number'),'');
	  
	  Mat.setDefault($('vehicle_type_info'),'');

	  Mat.setDefault($('sell_order_num'),'');
	  Mat.setDefault($('made_order_num'),'');
	  Mat.setDefault($('chassis_order_num'),'');

	  Mat.setDefault($('car_type_brand'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('vehicle_type_id_info'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('brand'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('engine_type_info'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('oil_config'),'<span class="noticeMsg">*</span>');
  }

//add by jinp start
// 自动生成车牌号值
var vehicleLnVal = "";
// 手动输入
var vehicleLnInput = "";
function checkIfVehicleLnIsEmpty() {
	var elm = $('vehicle_vin');
	var vehicleLn = $('vehicle_ln');
	if(elm.value.length > 7 && vehicleLn.value == vehicleLnVal && vehicleLnInput.length == 0) {
		vehicleLn.value = elm.value.substr(elm.value.length - 7, 7);
		vehicleLnVal = vehicleLn.value;
	}
}

function checkIfVehicleLnIsInput() {
	var elm = $('vehicle_ln');
	if(elm.value != vehicleLnVal && elm.value.length > 0) {
		vehicleLnInput = elm.value;
	}
	if(elm.value.length == 0) {
	  	var vin = $('vehicle_vin');
	  	if(vin.value.length > 7) {
	  	  elm.value = vin.value.substr(vin.value.length - 7, 7);
		  vehicleLnVal = elm.value;
		  vehicleLnInput = "";
	  	} else {
	  	  vehicleLnInput = "";
		  vehicleLnVal = "";
	  	}
		
	}
}
// add by jinp stop
  
  /** 加载事件 **/
  function setFormEvent() {
    $('vehicle_vin').onkeyup = checkVEHICLE_VIN;
 // modify by jinp start
    //$('vehicle_vin').onblur = checkVEHICLE_VIN;
    $('vehicle_vin').onblur = checkIfVehicleLnIsEmpty;
    // modify by jinp stop
    $('vehicle_ln').onkeyup = checkVEHICLE_LN;
    // modify by jinp start
    $('vehicle_ln').onblur = checkIfVehicleLnIsInput;
    // modify by jinp stop
    $('cr_config_id').onkeyup = checkCR_CONFIG_ID;
    $('cr_config_id').onblur = checkCR_CONFIG_ID;
    $('engine_type_info').onkeyup = checkENGINE_TYPE;
    $('engine_type_info').onblur = checkENGINE_TYPE;
    $('business_type').onkeyup = checkBUSINESS_TYPE;
    $('business_type').onblur = checkBUSINESS_TYPE;
    $('tyre_r').onkeyup = checkTYRE_R;
    $('tyre_r').onblur = checkTYRE_R;
    $('rear_axle_rate').onkeyup = checkREAR_AXLE_RATE;
    $('rear_axle_rate').onblur = checkREAR_AXLE_RATE;
    $('standard_rotate').onkeyup = checkSTANDARD_ROTATE;
    $('standard_rotate').onblur = checkSTANDARD_ROTATE;
    $('limite_number').onkeyup = checkLIMITE_NUMBER;
    $('limite_number').onblur = checkLIMITE_NUMBER;
    $('standard_oil').onkeyup = checkSTANDARD_OIL;
    $('standard_oil').onblur = checkSTANDARD_OIL;
    $('dead_load').onkeyup = checkDEAD_LOAD;
    $('dead_load').onblur = checkDEAD_LOAD;

    $('vehicle_type_id_info').onkeyup = checkVEHICLE_TYPE_ID;
    $('vehicle_type_id_info').onblur = checkVEHICLE_TYPE_ID;
    $('vehicle_color').onkeyup = checkVEHICLE_COLOR;
    $('vehicle_color').onblur = checkVEHICLE_COLOR;
    $('engine_number').onkeyup = checkENGINE_NUMBER;
    $('engine_number').onblur = checkENGINE_NUMBER;
    $('brand').onkeyup = checkBRAND;
    $('brand').onblur = checkBRAND;
    $('out_number').onkeyup = checkOUT_NUMBER;
    $('out_number').onblur = checkOUT_NUMBER;
    
    $('vehicle_number').onkeyup = checkVEHICLE_NUMBER;
    $('vehicle_number').onblur = checkVEHICLE_NUMBER;

    $('car_number').onkeyup = checkCAR_NUMBER;
    $('car_number').onblur = checkCAR_NUMBER;
    
    //$('made_number').onkeyup = checkMADE_NUMBER;
    //$('made_number').onblur = checkMADE_NUMBER;

    $('sell_order_num').onkeyup = checkSELL_ORDER_NUM;
    $('sell_order_num').onblur = checkSELL_ORDER_NUM;
    $('made_order_num').onkeyup = checkMADE_ORDER_NUM;
    $('made_order_num').onblur = checkMADE_ORDER_NUM;
    $('chassis_order_num').onkeyup = checkCHASSIS_ORDER_NUM;
    $('chassis_order_num').onblur = checkCHASSIS_ORDER_NUM;

    $('car_type_brand').onkeyup = checkCAR_TYPE_BRAND;
    $('car_type_brand').onblur = checkCAR_TYPE_BRAND;
  }

  function submitPostFrom() {
	trimAllObjs();
	var f1 = checkVEHICLE_VIN();
	var f2 = checkVEHICLE_LN();
	var f3 = checkCR_CONFIG_ID();
	var f4 = checkENGINE_TYPE();
	var f5 = checkBUSINESS_TYPE();
	var f6 = checkTYRE_R();
	var f7 = checkREAR_AXLE_RATE();
	var f8 = checkSTANDARD_ROTATE();
	var f9 = checkLIMITE_NUMBER();
	var f10 = checkSTANDARD_OIL();
	var f11 = checkDEAD_LOAD();
	var f12 = checkVIN();
	var f13 = checkLN();
	var f14 = checkVEHICLE_NUMBER();
	var f15 = checkENGINE_NUMBER();
	var f16 = checkVEHICLE_TYPE_ID();
	var f17 = checkCAR_TYPE_BRAND();
	var f18 = checkBRAND();
	
	

	Mat.showSucc($('vehicle_color'));
	//Mat.showSucc($('engine_number'));

	Mat.showSucc($('out_number'));
	Mat.showSucc($('car_number'));
	//Mat.showSucc($('made_number'));
	Mat.showSucc($('sell_order_num'));
	Mat.showSucc($('made_order_num'));
	Mat.showSucc($('chassis_order_num'));
	Mat.showSucc($('sell_date'));
	
		if (f1&&f2&&f3&&f4&&f5&&f6&&f7&&f8&&f9&&f10&&f11&&f12&&f13&&f14&&f15&&f16&&f17&&f18) {
			if(confirm("<s:property value="getText('confirm.modify')" />")) {
				NoticeCoreDwr.noticeCore("1", "1");  
		        Wit.commitAll($('clwForm'));
		      }
		}
		else  {
			  return false;
		}
	}
  function submitPostDelFrom(){
	  if(confirm("<s:property value="getText('confirm.delete')" />")) {
		  NoticeCoreDwr.noticeCore("1", "0");
		  var fm=$('clwForm');
		  fm.action="<s:url value='/cl/carbase_do_del.shtml' />";
		  Wit.commitAll(fm);
	  }else{
		  return false;
	}
  }
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>