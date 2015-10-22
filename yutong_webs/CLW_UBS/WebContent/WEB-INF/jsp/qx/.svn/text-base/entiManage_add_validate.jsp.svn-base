<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/EntiDwr.js'></script>

<script type="text/javascript">
function isIE(){ //ie? 
   if (window.navigator.userAgent.toLowerCase().indexOf("msie")>=1) 
    return true; 
   else 
    return false;
} 
function clickEnterEvent(obj) {
	var elm=$('PARENT_ID');
	if(isIE()){
		document.getElementById('PARENT_NAME').innerText = obj.short_name;
	}else{
		document.getElementById('PARENT_NAME').textContent = obj.attributes.getNamedItem('short_name').value;
	}
	
	elm.value = obj.id;
	var code = document.getElementById('ENTERPRISE_CODE');
	var msgnum=  document.getElementById('MSG_NUM');
	var filestr=$('file');
	var netadd=$('NETADDRESS');
	re_enti_model(obj);
	if (obj.id == '111') {
		code.disabled = false;
		msgnum.disabled = false;
		filestr.disabled = false;
		netadd.disabled = false;

		document.getElementById('ENTERPRISE_MODEL').disabled = false;
		document.getElementById('ENTERPRISE_GETWAY').disabled = false;
	} else {
		code.disabled = true;
		msgnum.disabled = true;
		clearFile(filestr);
		netadd.disabled = true;

		document.getElementById('ENTERPRISE_MODEL').disabled = true;
		document.getElementById('ENTERPRISE_GETWAY').disabled = true;		
	}
	setFormMessage();
	
	//setEnterTreeMode();
}
function re_enti_model(obj) {
	if(onfocusEnterID=='111'){
		var modelArr = document.getElementById("ENTERPRISE_MODEL");
		var gatewayArr = document.getElementById('ENTERPRISE_GETWAY');
		for(var i=0;i<modelArr.options.length;i++) {
			if(modelArr.options[i].value == ""){
				modelArr.options[i].selected = true;
		    }
		}
		for(var i=0;i<gatewayArr.options.length;i++) {
			if(gatewayArr.options[i].value == ""){
				gatewayArr.options[i].selected = true;
		    }
		}
		document.getElementById('ENTERPRISE_CODE').value = "";
		document.getElementById('MSG_NUM').value='100';
		document.getElementById('NETADDRESS').value='';
	}else{
		EntiDwr.getEnterpriseModleByID(obj.id ,{
			callback : function(data) {
			var modelArr = document.getElementById("ENTERPRISE_MODEL");
			var code = document.getElementById('ENTERPRISE_CODE');
			var gatewayArr = document.getElementById('ENTERPRISE_GETWAY');			
			for(var i=0;i<modelArr.options.length;i++) {
				if(modelArr.options[i].value == data.enterprise_model){
					modelArr.options[i].selected = true;
			    }
			}
			for(var i=0;i<gatewayArr.options.length;i++) {
				if(gatewayArr.options[i].value == data.enterprise_getway){
					gatewayArr.options[i].selected = true;
			    }
			}
			code.value = data.enterprise_code;
			document.getElementById('MSG_NUM').value=data.msg_num;
			document.getElementById('NETADDRESS').value=data.netaddress;
			},
			errorHandler : function(msg, ex) {
				alert(msg);
			}
			});
	}
}
function clearFile(oldFile) {
    var newFile = document.createElement("input");
    newFile.id = oldFile.id;
    newFile.type = "file";
    newFile.onkeydown="return false;"
    newFile.className ="phyfile";
    oldFile.parentNode.replaceChild(newFile, oldFile);
    newFile.disabled = true;
}
function checkPARENT_ID(){
	var elm=$('PARENT_ID');
	if(onfocusEnterID==null||onfocusEnterID==""){
		Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.selectPID')"/>");
		return false;
	}if(onfocusEnterID=='222'){
		Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.222NotInsert')"/>");
		return false;
	}else{
		Mat.showSucc(elm);
		return true;
	}
}
function checkENTERPRISE_CODE(){
	var elm=$('ENTERPRISE_CODE');
	if(onfocusEnterID==null){
		Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.selectPID')"/>");
		return false;
	}else if(onfocusEnterID=='111'){
		if(Wit.checkErr(elm,/^[0-9a-zA-Z]+$/)){
    	    Mat.showSucc(elm);
		    return true;
    	}
    	else{
    		Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.enterprise_code')" />");
    	    return false;
		}
	}else{
		Mat.showSucc(elm);
		return true;
	}
}
function checkSHORT_NAME(){
	var elm=$('SHORT_NAME');
	if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	    return false;
	}else if(!Mat.checkLength(elm,10)){
		Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.lenles10')"/>");
	    return false;
	}else{
		Mat.showSucc(elm);
		return true;
	}
}
function checkFULL_NAME(){
	var elm=$('FULL_NAME');
	if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	    return false;
	}else if(!Mat.checkLength(elm,60)){
		Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.lenles60')"/>");
	    return false;
	}else{
		Mat.showSucc(elm);
		return true;
	}
	
}
function checkEMAIL(){
	var elm=$('EMAIL');
	if(elm.value!=''){
		if(!Mat.checkEmail(elm)){
			Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.emailSucc')"/>");
		    return false;
		}
	}
	Mat.showSucc(elm);
	return true;
}
function checkCONTACT_TEL(){
	var elm=$('CONTACT_TEL');
	if(elm.value!=''){
		if(!Mat.checkTelePhone(elm)){
			Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.telSucc')"/>");
		    return false;
		}
	}
	Mat.showSucc(elm);
	return true;
}
function checkENTERPRISE_DESC(){
	var elm=$('ENTERPRISE_DESC');
	var showelm=document.getElementById('ENTERPRISE_DESC_show');
	if(elm.value.length>100){
		showelm.className='error';
		if(isIE()){
			showelm.innerText="<s:property value="getText('enterprise.js.info.lenles100')"/>";
		}else{
			showelm.textContent="<s:property value="getText('enterprise.js.info.lenles100')"/>";
		}
		
	    return false;
	}else{
		showelm.className='success';
		if(isIE()){
			showelm.innerText='\u221A';
		}else{
			showelm.textContent='\u221A';
		}
		return true;
	}
}
function checkMSG_NUM(){
	var elm=$('MSG_NUM');
	if(onfocusEnterID==null){
		Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.selectPID')"/>");
		return false;
	}else if(onfocusEnterID=='111'){
		if(!Mat.checkRequired(elm)){
		    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
		    return false;
		}else if(!Mat.checkDigital(elm)){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}else if(!Mat.checkLength(elm,5)){
			Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.lenles5')"/>");
		    return false;
		}else{
			Mat.showSucc(elm);
			return true;
		}
	}else{
		Mat.showSucc(elm);
		return true;
	}
}
function checkENTERPRISE_COUNTRY(){
	var elm=$('ENTERPRISE_COUNTRY');
	if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('please.select')" />");
	    return false;
	}
	return true;
}
function checkENTERPRISE_PROVINCE(){
	var elm=$('ENTERPRISE_PROVINCE');
	if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('please.select')" />");
	    return false;
	}
	return true;
}
function checkENTERPRISE_CITY(){
	var elm=$('ENTERPRISE_CITY');
	if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('please.select')" />");
	    return false;
	}
	return true;
}

//ENTERPRISE_CODE唯一验证
function checkCODE(){
	if(onfocusEnterID=='111'){
		var elm = $('ENTERPRISE_CODE');
		var v_temp=elm.value;
		for(var i=v_temp.length;i<10;i++){
			v_temp="0"+v_temp;
		}
		elm.value=v_temp;
		DWREngine.setAsync(false);
	    var ret = true; 
	    EntiDwr.checkCODE(elm.value, callBackFun);
	    function callBackFun(data)
	    {
	        ret = data;
	    }
	    DWREngine.setAsync(true);
	    if(ret) {
	    	return true;
	  	} else {
	  		Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.enterprise_code.only')" />");
	      	return false;
	  	}
	}else{
		return true;
	}
}

function checkADDRESS(){
	Mat.showSucc($('ADDRESS'));
	return true;
}
function checkPOSTCODE(){
	var elm=$('POSTCODE');
	if(elm.value.trim()!=''){
		if(!Mat.checkPostNm2(elm)){
			Wit.showErr(elm, "<s:property value="getText('postcode.check')" />");
		    return false;
		}
	}
	Mat.showSucc($('POSTCODE'));
	return true;
}
function checkCONTACT_P(){
	Mat.showSucc($('CONTACT_P'));
	return true;
}
function checkNETADDRESS(){
	Mat.showSucc($('NETADDRESS'));
	return true;
}
function checkENTERPRISE_TYPE_CFG(){
	Mat.showSucc($('ENTERPRISE_TYPE_CFG'));
	return true;
}
function checkENTERPRISE_LEVE_CFG(){
	Mat.showSucc($('ENTERPRISE_LEVE_CFG'));
	return true;
}
function checkENTERPRISE_KIND_CFG(){
	Mat.showSucc($('ENTERPRISE_KIND_CFG'));
	return true;
}
function checkMONEY_KIND_CFG(){
	Mat.showSucc($('MONEY_KIND_CFG'));
	return true;
}
function checkLANGUAGE_CFG(){
	Mat.showSucc($('LANGUAGE_CFG'));
	return true;
}
function checkISUSED(){
	Mat.showSucc($('ISUSED'));
	return true;
}
function checkFAX(){
	Mat.showSucc($('FAX'));
	return true;
}


function checkFILENAME(){
	var elm=$('file');
	var filename=elm.value;
	if(filename!=''&&'111'==onfocusEnterID){
		if(filename.lastIndexOf('.')!=-1){
			filename=filename.substr(filename.lastIndexOf('.')+1);
			if(filename.toLowerCase()=='jpg'){
				Mat.showSucc(elm);
				return true;
			}else{
				Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.file')" />");
				return false;
			}
		}else{
			Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.file')" />");
			return false;
		}
	}
	Mat.showSucc(elm);
	return true;
	
}

function checkENTERPRISE_MODEL(){	
		var elm=$('ENTERPRISE_MODEL');
		if(!Mat.checkRequired(elm)){
		    Wit.showErr(elm, "<s:property value="getText('please.select')" />");
		    return false;
		}else{
			Mat.showSucc(elm);
    	    return true;
		}
}

function checkENTERPRISE_GETWAY(){	
		var elm=$('ENTERPRISE_GETWAY');
		if(!Mat.checkRequired(elm)){
		    Wit.showErr(elm, "<s:property value="getText('please.select')" />");
		    return false;
		}else{
			Mat.showSucc(elm);
    	    return true;
		}
}

//展示国家选择框
function show_enterprise_country(){
    ZoneDwr.showZoneXsInfo('',callBackFun_show_enterprise_country);

}
function callBackFun_show_enterprise_country(data) {
	var tempObj = $('ENTERPRISE_COUNTRY');  
	DWRUtil.removeAllOptions(tempObj);  
	DWRUtil.addOptions(tempObj,{'':'<s:property value="getText('please.select')" />'});  
	DWRUtil.addOptions(tempObj,data);  

	var tempObj2 = $('ENTERPRISE_PROVINCE');  
	DWRUtil.removeAllOptions(tempObj2);  
	DWRUtil.addOptions(tempObj2,{'':'<s:property value="getText('please.select')" />'}); 

	var tempObj3 = $('ENTERPRISE_CITY');  
	DWRUtil.removeAllOptions(tempObj3);  
	DWRUtil.addOptions(tempObj3,{'':'<s:property value="getText('please.select')" />'});    

}
//展示省/直辖市选择框
function show_enterprise_province(){
	var oemObj = $('ENTERPRISE_COUNTRY');
	if(oemObj.value != "") {
	    ZoneDwr.showZoneXsInfo(oemObj.value,callBackFun_show_enterprise_province);
    }else{
    	DWRUtil.removeAllOptions($('ENTERPRISE_PROVINCE'));  
    	DWRUtil.addOptions($('ENTERPRISE_PROVINCE'),{'':'<s:property value="getText('please.select')" />'});  
    	DWRUtil.removeAllOptions($('ENTERPRISE_CITY'));  
    	DWRUtil.addOptions($('ENTERPRISE_CITY'),{'':'<s:property value="getText('please.select')" />'});  
    }
}
function callBackFun_show_enterprise_province(data) {
	var tempObj = $('ENTERPRISE_PROVINCE');  
	DWRUtil.removeAllOptions(tempObj);  
	DWRUtil.addOptions(tempObj,{'':'<s:property value="getText('please.select')" />'});  
	DWRUtil.addOptions(tempObj,data);  

	var tempObj3 = $('ENTERPRISE_CITY');  
	DWRUtil.removeAllOptions(tempObj3);  
	DWRUtil.addOptions(tempObj3,{'':'<s:property value="getText('please.select')" />'});    
}
//展示市/县选择框
function show_enterprise_city(){
	var oemObj = $('ENTERPRISE_PROVINCE');
	if(oemObj.value != "") {
	    ZoneDwr.showZoneXsInfo(oemObj.value,callBackFun_show_enterprise_city);
    }else{
    	DWRUtil.removeAllOptions($('ENTERPRISE_CITY'));  
    	DWRUtil.addOptions($('ENTERPRISE_CITY'),{'':'<s:property value="getText('please.select')" />'});  
    }
}
function callBackFun_show_enterprise_city(data) {
	var tempObj = $('ENTERPRISE_CITY');  
	DWRUtil.removeAllOptions(tempObj);  
	DWRUtil.addOptions(tempObj,{'':'<s:property value="getText('please.select')" />'});  
	DWRUtil.addOptions(tempObj,data);  
}



/** 初始化样式 **/
function setFormMessage() {
	checkPARENT_ID();
	
	Mat.setDefault($('ENTERPRISE_CODE'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('ENTERPRISE_MODEL'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('ENTERPRISE_GETWAY'),'<span class="noticeMsg">*</span>');
	
	Mat.setDefault($('SHORT_NAME'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('FULL_NAME'),'<span class="noticeMsg">*</span>');
	if(onfocusEnterID=='111'){
		Mat.setDefault($('MSG_NUM'),'<span class="noticeMsg">*</span>');
	}else{
		Mat.setDefault($('MSG_NUM'),'');
	}
	Mat.setDefault($('EMAIL'),'');
	Mat.setDefault($('CONTACT_TEL'),'');
	Mat.setDefault($('ENTERPRISE_DESC'),'');
	Mat.setDefault($('ADDRESS'),'');
	Mat.setDefault($('POSTCODE'),'');
	Mat.setDefault($('CONTACT_P'),'');
	Mat.setDefault($('ENTERPRISE_COUNTRY'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('ENTERPRISE_PROVINCE'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('ENTERPRISE_CITY'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('NETADDRESS'),'');
	//Mat.setDefault($('file'),'');
	Mat.setDefault($('ENTERPRISE_TYPE_CFG'),'');
	Mat.setDefault($('ENTERPRISE_LEVE_CFG'),'');
	Mat.setDefault($('ENTERPRISE_KIND_CFG'),'');
	Mat.setDefault($('MONEY_KIND_CFG'),'');
	Mat.setDefault($('LANGUAGE_CFG'),'');
	Mat.setDefault($('ISUSED'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('FAX'),'');
}
/** 加载事件 **/
function setFormEvent() {

  $('ENTERPRISE_CODE').onkeyup = checkENTERPRISE_CODE;
  $('ENTERPRISE_CODE').onblur = checkENTERPRISE_CODE;
  $('SHORT_NAME').onkeyup = checkSHORT_NAME;
  $('SHORT_NAME').onblur = checkSHORT_NAME;
  $('FULL_NAME').onkeyup = checkFULL_NAME;
  $('FULL_NAME').onblur = checkFULL_NAME;
  $('MSG_NUM').onkeyup = checkMSG_NUM;
  $('MSG_NUM').onblur = checkMSG_NUM;
  $('EMAIL').onkeyup = checkEMAIL;
  $('EMAIL').onblur = checkEMAIL;
  $('CONTACT_TEL').onkeyup = checkCONTACT_TEL;
  $('CONTACT_TEL').onblur = checkCONTACT_TEL;
  $('ENTERPRISE_DESC').onkeyup = checkENTERPRISE_DESC;
  $('ENTERPRISE_DESC').onblur = checkENTERPRISE_DESC;
  $('ADDRESS').onkeyup = checkADDRESS;
  $('ADDRESS').onblur = checkADDRESS;
  $('POSTCODE').onkeyup = checkPOSTCODE;
  $('POSTCODE').onblur = checkPOSTCODE;
  $('CONTACT_P').onkeyup = checkCONTACT_P;
  $('CONTACT_P').onblur = checkCONTACT_P;
  $('ENTERPRISE_COUNTRY').onkeyup = checkENTERPRISE_COUNTRY;
  $('ENTERPRISE_COUNTRY').onblur = checkENTERPRISE_COUNTRY;
  $('ENTERPRISE_PROVINCE').onkeyup = checkENTERPRISE_PROVINCE;
  $('ENTERPRISE_PROVINCE').onblur = checkENTERPRISE_PROVINCE;
  $('ENTERPRISE_CITY').onkeyup = checkENTERPRISE_CITY;
  $('ENTERPRISE_CITY').onblur = checkENTERPRISE_CITY;
  $('NETADDRESS').onkeyup = checkNETADDRESS;
  $('NETADDRESS').onblur = checkNETADDRESS;
  $('ENTERPRISE_TYPE_CFG').onkeyup = checkENTERPRISE_TYPE_CFG;
  $('ENTERPRISE_TYPE_CFG').onblur = checkENTERPRISE_TYPE_CFG;
  $('ENTERPRISE_LEVE_CFG').onkeyup = checkENTERPRISE_LEVE_CFG;
  $('ENTERPRISE_LEVE_CFG').onblur = checkENTERPRISE_LEVE_CFG;
  $('ENTERPRISE_KIND_CFG').onkeyup = checkENTERPRISE_KIND_CFG;
  $('ENTERPRISE_KIND_CFG').onblur = checkENTERPRISE_KIND_CFG;
  $('MONEY_KIND_CFG').onkeyup = checkMONEY_KIND_CFG;
  $('MONEY_KIND_CFG').onblur = checkMONEY_KIND_CFG;
  $('LANGUAGE_CFG').onkeyup = checkLANGUAGE_CFG;
  $('LANGUAGE_CFG').onblur = checkLANGUAGE_CFG;
  $('ISUSED').onkeyup = checkISUSED;
  $('ISUSED').onblur = checkISUSED;
  $('FAX').onkeyup = checkFAX;
  $('FAX').onblur = checkFAX;
  $('ENTERPRISE_MODEL').onkeyup = checkENTERPRISE_MODEL;
  $('ENTERPRISE_MODEL').onblur = checkENTERPRISE_MODEL;
  $('ENTERPRISE_GETWAY').onkeyup = checkENTERPRISE_GETWAY;
  $('ENTERPRISE_GETWAY').onblur = checkENTERPRISE_GETWAY;
}
window.addEvent('domready', setFormEvent);
window.addEvent('domready', setFormMessage);
function submitPostFrom() {

	var f1=checkPARENT_ID();
	var f2=checkENTERPRISE_CODE();
	var f3=checkSHORT_NAME();
	var f4=checkFULL_NAME();
	var f5=checkMSG_NUM();
	var f6=checkEMAIL();
	var f17=checkPOSTCODE();
	var f7=checkCONTACT_TEL();
	var f8=checkENTERPRISE_DESC();
	var f9=checkENTERPRISE_COUNTRY();
	var f10=checkENTERPRISE_PROVINCE();
	var f11=checkENTERPRISE_CITY();
	var f12=checkFILENAME();
	var f13=checkCODE();
	var f14=checkENTERPRISE_MODEL();
	var f15=checkENTERPRISE_GETWAY();
	Mat.showSucc($('ADDRESS'));
	Mat.showSucc($('CONTACT_P'));
	Mat.showSucc($('ENTERPRISE_TYPE_CFG'));
	Mat.showSucc($('ENTERPRISE_LEVE_CFG'));
	Mat.showSucc($('ENTERPRISE_KIND_CFG'));
	Mat.showSucc($('MONEY_KIND_CFG'));
	Mat.showSucc($('LANGUAGE_CFG'));
	Mat.showSucc($('ISUSED'));
	Mat.showSucc($('FAX'));
	Mat.showSucc($('NETADDRESS'));
	if(onfocusEnterID !='111'){
		document.getElementById('ENTERPRISE_CODE').value = "";
		document.getElementById('MSG_NUM').value='';
		document.getElementById('NETADDRESS').value='';
	}
	if(f1&&f2&&f3&&f4&&f5&&f6&&f7&&f8&&f9&&f10&&f11&&f13&&f12&&f14&&f15&&f17){
		Wit.commitAll($('clwForm'));
	}
	else  {
		  return false;
	}
}

</script>