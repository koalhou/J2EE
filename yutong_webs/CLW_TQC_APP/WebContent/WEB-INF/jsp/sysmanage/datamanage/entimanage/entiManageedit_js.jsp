<!-- saved from url=(0022)http://internet.e-mail -->
<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript'
	src='../dwr/interface/EntiDwr.js'></script>
<script type='text/javascript'
	src='../dwr/interface/ZoneDwr.js'></script>

<script type="text/javascript">

function checkSHORT_NAME(){
	var elm=$('SHORT_NAME');
	//CtoH(elm);
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
function CtoH(obj){
	var str=obj.value;
	var result="";
	for (var i = 0; i < str.length; i++)
	{
	if (str.charCodeAt(i)==12288)
	{
	result+= String.fromCharCode(str.charCodeAt(i)-12256);
	continue;
	}
	if (str.charCodeAt(i)>65280 && str.charCodeAt(i)<65375)
	result+= String.fromCharCode(str.charCodeAt(i)-65248);
	else result+= String.fromCharCode(str.charCodeAt(i));
	} 
	obj.value=result;
} 
function checkFULL_NAME(){
	
	var elm=$('FULL_NAME');

	var _pareId = null;


    var _entId = $('ENTERPRISE_ID');

	
	var _eFullNameSigle = getEnterpriseFullNameSingleRes(elm.value,_entId.value,_pareId);

	if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	    return false;
	}else if(!Mat.checkLength(elm,60)){
		Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.lenles60')"/>");
	    return false;
	}else if(!_eFullNameSigle){
		Wit.showErr(elm,"<s:property value="getText('validate.info.single.enterprise')" />");
		return false;
	}else{
		Mat.showSucc(elm);
		return true;
	}
	
}

function getEnterpriseFullNameSingleRes(eFullName,entId,pareId){

	var _eFullNameSigle = false; 

   DWREngine.setAsync(false);
       
   EntiDwr.getSingleEnterpriseName(eFullName,entId,pareId,callBackFun);
       
   DWREngine.setAsync(true);
   
   function callBackFun(data)
   {
   	_eFullNameSigle = data;
   }
   return _eFullNameSigle;
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
		showelm.innerHTML="<s:property value="getText('enterprise.js.info.lenles100')"/>";
	    return false;
	}else{
		showelm.className='success';
		showelm.innerHTML='\u221A';
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
function checkADDRESS(){
	Mat.showSucc($('ADDRESS'));
	return true;
}
function checkPOSTCODE(){
	var postcode=$('POSTCODE');
	if(postcode.value.length>0){
	if(!Mat.checkDigital(postcode)){
		  Wit.showErr(postcode, "<s:property value="getText('enterprise.postcode.onlynumber')" />");	
		  return false;
	 }
	}
	return true;
}
function checkCONTACT_P(){
	Mat.showSucc($('CONTACT_P'));
	return true;
}
/** 初始化样式 **/
function setFormMessage() {
	Mat.setDefault($('SHORT_NAME'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('FULL_NAME'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('EMAIL'),'');
	Mat.setDefault($('CONTACT_TEL'),'');
	Mat.setDefault($('ENTERPRISE_DESC'),'');
	Mat.setDefault($('ADDRESS'),'');
	Mat.setDefault($('POSTCODE'),'');
	Mat.setDefault($('CONTACT_P'),'');
	Mat.setDefault($('ENTERPRISE_COUNTRY'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('ENTERPRISE_PROVINCE'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('ENTERPRISE_CITY'),'<span class="noticeMsg">*</span>');
}
/** 加载事件 **/
function setFormEvent() {

  $('SHORT_NAME').onkeyup = checkSHORT_NAME;
  $('SHORT_NAME').onblur = checkSHORT_NAME;
  $('FULL_NAME').onkeyup = checkFULL_NAME;
  $('FULL_NAME').onblur = checkFULL_NAME;
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

}
window.addEvent('domready', setFormEvent);
window.addEvent('domready', setFormMessage);
function submitPostFrom() {
	trimAllObjs();
	mytrim($('ENTERPRISE_DESC'));
	var f3=checkSHORT_NAME();
	var f4=checkFULL_NAME();
	var f6=checkEMAIL();
	var f7=checkCONTACT_TEL();
	var f8=checkENTERPRISE_DESC();
	var f9=checkENTERPRISE_COUNTRY();
	var f10=checkENTERPRISE_PROVINCE();
	var f11=checkENTERPRISE_CITY();
	var f12=checkPOSTCODE();
	Mat.showSucc($('ADDRESS'));
	Mat.showSucc($('CONTACT_P'));
	
	
	if(f3&&f4&&f6&&f7&&f8&&f9&&f10&&f11&&f12){
		Wit.commitAll($('clwForm'));
	}
	else  {
		  return false;
	}
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

function firstrisize(){
	jQuery('#content').mk_autoresize({
        height_include: '#main',
        height_offset: 0 
      });
}
jQuery(function() {
	firstrisize();
	jQuery(window).mk_autoresize({
	     height_include: '#content',
	     height_exclude: ['#header', '#footer'],
	     height_offset: 0,
	     width_include: ['#header', '#content', '#footer'],
	     width_offset: 0
	  });
	 firstrisize();
});
</script>