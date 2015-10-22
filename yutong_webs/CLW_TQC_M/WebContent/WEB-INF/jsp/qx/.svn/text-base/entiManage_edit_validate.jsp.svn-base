<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

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

function do_del(){
	if(!confirm('<s:property value="getText('enterprise.js.info.sureDel')"/>'+$('short_name_delshow').value+'?')){
		return;
	}
	//确认窗口
	document.getElementById('clwForm').action='<s:url value='entimanage_do_del.shtml' />';
	document.getElementById('clwForm').submit();
}
function clickEnterEvent(obj) {
	var msgnum=  document.getElementById('MSG_NUM');
	var model = document.getElementById('ENTERPRISE_MODEL');
	var getway = document.getElementById('ENTERPRISE_GETWAY');
	var parentid=$('PARENT_ID');
	var editentiid=$('ENTERPRISE_ID');
	var netadd=$('NETADDRESS');
	var show_info=$('show_info');
	var edit_info=$('edit_info');
	if(obj.id==editentiid.value){
		show_info.style.display='none';
		edit_info.style.display='';
		if (parentid.value == '111') {
			msgnum.disabled = false;
			netadd.disabled = false;
			model.disabled = false;
			getway.disabled = false;
		} else {
			msgnum.value = '';
			msgnum.disabled = true;
			netadd.value='';
			netadd.disabled = true;
			model.disabled = true;
			getway.disabled = true;
		}
		setFormMessage();
	}else{
		show_info.style.display='';
		edit_info.style.display='none';
		re_show_cars_info(obj);
	}
	
}
function re_show_cars_info(obj){
	EntiDwr.getEnterpriseDataInfoToShow(obj.id ,{
		callback : function(data) {
		if(isIE()){
			document.getElementById('show_info_ENTERPRISE_CODE').innerText=data.enterprise_code;
			document.getElementById('show_info_FULL_NAME').innerText=data.full_name;
			document.getElementById('show_info_SHORT_NAME').innerText=data.short_name;
			document.getElementById('show_info_ENTERPRISE_COUNTRY').innerText=data.enterprise_country;
			document.getElementById('show_info_ENTERPRISE_PROVINCE').innerText=data.enterprise_province;
			document.getElementById('show_info_ENTERPRISE_CITY').innerText=data.enterprise_city;
			if('0'==data.enterprise_type){
				document.getElementById('show_info_ENTERPRISE_TYPE').innerText='<s:property value="getText('enterprise.type0')" />';
			}else{
				document.getElementById('show_info_ENTERPRISE_TYPE').innerText='<s:property value="getText('enterprise.type1')" />';
			}
			document.getElementById('show_info_ADDRESS').innerText=data.address;
			document.getElementById('show_info_EMAIL').innerText=data.email;
			document.getElementById('show_info_POSTCODE').innerText=data.postcode;
			document.getElementById('show_info_CONTACT_P').innerText=data.contact_p;
			document.getElementById('show_info_CONTACT_TEL').innerText=data.contact_tel;
			document.getElementById('show_info_MSG_NUM').innerText=data.msg_num;
			document.getElementById('show_info_ENTERPRISE_DESC').innerText=data.enterprise_desc;
			document.getElementById('show_info_NETADDRESS').innerText=data.netaddress;
			document.getElementById('show_info_ENTERPRISE_TYPE_CFG').innerText=data.enterprise_type_cfg;
			document.getElementById('show_info_ENTERPRISE_LEVE_CFG').innerText=data.enterprise_leve_cfg;
			document.getElementById('show_info_ENTERPRISE_KIND_CFG').innerText=data.enterprise_kind_cfg;
			document.getElementById('show_info_MONEY_KIND_CFG').innerText=data.money_kind_cfg;
			document.getElementById('show_info_LANGUAGE_CFG').innerText=data.language_cfg;
			if('0'==data.isused){
				document.getElementById('show_info_ISUSED').innerText='<s:property value="getText('enterprise.info.ISUSED_0')" />';
			}else{
				document.getElementById('show_info_ISUSED').innerText='<s:property value="getText('enterprise.info.ISUSED_1')" />';
			}
			document.getElementById('show_info_FAX').innerText=data.fax;
			if(obj.id == "111"){
				document.getElementById('show_info_MODEL').innerText='<s:property value="getText('enterprise.info.MODEL_NO')" />';
				document.getElementById('show_info_GETWAY').innerText='<s:property value="getText('enterprise.info.MODEL_NO')" />';
			}else if(data.enterprise_code == ""){
				document.getElementById('show_info_MODEL').innerText='<s:property value="getText('enterprise.info.MODEL_0')" />';
				document.getElementById('show_info_GETWAY').innerText='<s:property value="getText('enterprise.info.getway_0')" />';
			}else{
				if('1'==data.enterprise_model){
					document.getElementById('show_info_MODEL').innerText='<s:property value="getText('enterprise.info.MODEL_1')" />';
				}else if('2'==data.enterprise_model){
					document.getElementById('show_info_MODEL').innerText='<s:property value="getText('enterprise.info.MODEL_2')" />';
				}else if('3'==data.enterprise_model){
					document.getElementById('show_info_MODEL').innerText='<s:property value="getText('enterprise.info.MODEL_3')" />';
				}
				if('1'==data.enterprise_getway){
					document.getElementById('show_info_GETWAY').innerText='<s:property value="getText('enterprise.info.getway_1')" />';
				}else if('2'==data.enterprise_getway){
					document.getElementById('show_info_GETWAY').innerText='<s:property value="getText('enterprise.info.getway_2')" />';
				}
			}

		}else{
			document.getElementById('show_info_ENTERPRISE_CODE').textContent=data.enterprise_code;
			document.getElementById('show_info_FULL_NAME').textContent=data.full_name;
			document.getElementById('show_info_SHORT_NAME').textContent=data.short_name;
			document.getElementById('show_info_ENTERPRISE_COUNTRY').textContent=data.enterprise_country;
			document.getElementById('show_info_ENTERPRISE_PROVINCE').textContent=data.enterprise_province;
			document.getElementById('show_info_ENTERPRISE_CITY').textContent=data.enterprise_city;
			if('0'==data.enterprise_type){
				document.getElementById('show_info_ENTERPRISE_TYPE').textContent='<s:property value="getText('enterprise.type0')" />';
			}else{
				document.getElementById('show_info_ENTERPRISE_TYPE').textContent='<s:property value="getText('enterprise.type1')" />';
			}
			document.getElementById('show_info_ADDRESS').textContent=data.address;
			document.getElementById('show_info_EMAIL').textContent=data.email;
			document.getElementById('show_info_POSTCODE').textContent=data.postcode;
			document.getElementById('show_info_CONTACT_P').textContent=data.contact_p;
			document.getElementById('show_info_CONTACT_TEL').textContent=data.contact_tel;
			document.getElementById('show_info_MSG_NUM').textContent=data.msg_num;
			document.getElementById('show_info_ENTERPRISE_DESC').textContent=data.enterprise_desc;
			document.getElementById('show_info_NETADDRESS').textContent=data.netaddress;
			document.getElementById('show_info_ENTERPRISE_TYPE_CFG').textContent=data.enterprise_type_cfg;
			document.getElementById('show_info_ENTERPRISE_LEVE_CFG').textContent=data.enterprise_leve_cfg;
			document.getElementById('show_info_ENTERPRISE_KIND_CFG').textContent=data.enterprise_kind_cfg;
			document.getElementById('show_info_MONEY_KIND_CFG').textContent=data.money_kind_cfg;
			document.getElementById('show_info_LANGUAGE_CFG').textContent=data.language_cfg;
			document.getElementById('show_info_ISUSED').textContent=data.isused;
			if('0'==data.isused){
				document.getElementById('show_info_ISUSED').textContent='<s:property value="getText('enterprise.info.ISUSED_0')" />';
			}else{
				document.getElementById('show_info_ISUSED').textContent='<s:property value="getText('enterprise.info.ISUSED_1')" />';
			}
			document.getElementById('show_info_FAX').textContent=data.fax;

			if(obj.id == "111"){
				document.getElementById('show_info_MODEL').innerText='<s:property value="getText('enterprise.info.MODEL_NO')" />';
				document.getElementById('show_info_GETWAY').innerText='<s:property value="getText('enterprise.info.MODEL_NO')" />';
			}else if(data.enterprise_code == ""){
				document.getElementById('show_info_MODEL').innerText='<s:property value="getText('enterprise.info.MODEL_0')" />';
				document.getElementById('show_info_GETWAY').innerText='<s:property value="getText('enterprise.info.getway_0')" />';
			}else{
				if('1'==data.enterprise_model){
					document.getElementById('show_info_MODEL').textContent='<s:property value="getText('enterprise.info.MODEL_1')" />';
				}else if('2'==data.enterprise_model){
					document.getElementById('show_info_MODEL').textContent='<s:property value="getText('enterprise.info.MODEL_2')" />';
				}else if('3'==data.enterprise_model){
					document.getElementById('show_info_MODEL').textContent='<s:property value="getText('enterprise.info.MODEL_3')" />';
				}
				if('1'==data.enterprise_getway){
					document.getElementById('show_info_GETWAY').textContent='<s:property value="getText('enterprise.info.getway_1')" />';
				}else if('2'==data.enterprise_getway){
					document.getElementById('show_info_GETWAY').textContent='<s:property value="getText('enterprise.info.getway_2')" />';
				}
			}

		}
		},
		errorHandler : function(msg, ex) {
			alert(msg);
		}
		});
		if(Number(obj.attributes.getNamedItem('left_num').value)+1==Number(obj.attributes.getNamedItem('right_num').value)){
			EntiDwr.getEnterpriseCarsNumsToShow(obj.id ,{
				callback : function(data) {
				if(isIE()){
					document.getElementById('show_info_CARSNUMS').innerText=data;
					carsnumber=Number(data);
				}else{
					document.getElementById('show_info_CARSNUMS').textContent=data;
					carsnumber=Number(data);
					}
				},
				errorHandler : function(msg, ex) {
					alert(msg);
				}
				});
		}else{
			if(isIE()){
				document.getElementById('show_info_CARSNUMS').innerText="0";
				carsnumber=0;
			}else{
				document.getElementById('show_info_CARSNUMS').textContent="0";
				carsnumber=0;
			}
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

function checkMSG_NUM(){
	var elm=$('MSG_NUM');
	if('<s:property value="enterpriseDataInfo.msg_num" />'!=''){
		if(!Mat.checkRequired(elm)){
		    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
		    return false;
		}else if(!Mat.checkDigital(elm)){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')"/>");
			return false;
		}else if(!Mat.checkLength(elm,5)){
			Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.lenles5')"/>");
		    return false;
		}else{
			Mat.showSucc(elm);
			return true;
		}
	}else{
		return true;
	}
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

function checkENTERPRISE_MODEL(){
	var elm=$('ENTERPRISE_MODEL');
	if($('PARENT_ID').value=='111'){
		if(!Mat.checkRequired(elm)){
		    Wit.showErr(elm, "<s:property value="getText('please.select')" />");
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

function checkENTERPRISE_GETWAY(){
	var elm=$('ENTERPRISE_GETWAY');
	if($('PARENT_ID').value=='111'){
		if(!Mat.checkRequired(elm)){
		    Wit.showErr(elm, "<s:property value="getText('please.select')" />");
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

/** 初始化样式 **/
function setFormMessage() {
	Mat.setDefault($('SHORT_NAME'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('FULL_NAME'),'<span class="noticeMsg">*</span>');
	if($('PARENT_ID').value=='111'){
		Mat.setDefault($('MSG_NUM'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('ENTERPRISE_MODEL'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('ENTERPRISE_GETWAY'),'<span class="noticeMsg">*</span>');
	}else{
		Mat.setDefault($('MSG_NUM'),'');
		Mat.setDefault($('ENTERPRISE_MODEL'),'');
		Mat.setDefault($('ENTERPRISE_GETWAY'),'');
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
	var editentiid=$('ENTERPRISE_ID');
	if(onfocusEnterID!=editentiid.value){
		return false;
	}
	
	var f3=checkSHORT_NAME();
	var f4=checkFULL_NAME();
	var f5=checkMSG_NUM();
	var f6=checkEMAIL();
	var f14=checkPOSTCODE();
	var f7=checkCONTACT_TEL();
	var f8=checkENTERPRISE_DESC();
	var f9=checkENTERPRISE_COUNTRY();
	var f10=checkENTERPRISE_PROVINCE();
	var f11=checkENTERPRISE_CITY();
	var f12=checkENTERPRISE_MODEL();
	var f13=checkENTERPRISE_GETWAY();
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
	if(f3&&f4&&f5&&f6&&f7&&f8&&f9&&f10&&f11&&f12&&f13&&f14){
		document.getElementById('clwForm').action='<s:url value='entimanage_do_edit.shtml' />';
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


</script>