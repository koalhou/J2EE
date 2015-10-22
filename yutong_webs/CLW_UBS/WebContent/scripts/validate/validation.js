var Mat={};

Mat.succMsg = '\u221A';
Mat.errMsg = '\u00d7';

Mat.requiredSign = '<span class="noticeMsg">*</span>';
Mat.requiredMsg = '\u8BF7\u586B\u5199';

function trimAllObjs(){
	inputObjs=document.body.getElementsByTagName("INPUT");
	for(var i=0;i<inputObjs.length;i++){
		if(inputObjs[i].type=='text'){
			mytrim(inputObjs[i]);
		}
	}
}
function mytrim(obj){
	obj.value= obj.value.replace(/(^\s*)|(\s*$)/g, "");
};

/*取元素验证成功时显示样式*/
function getSuccStyle(elm){
	if(elm.type == 'text' || elm.type=='password'){
		return 'tf';
	}
	if(elm.type == 'textarea'){
		return 'ta';
	}
	if(elm.type == 'select-one'){
		return 'sl';
	}
	if(elm.type == 'radio'){
		return 'ra';
	}
	if(elm.type == 'checkbox'){
		return 'cb';
	}
}

/*取元素验证失败时显示样式*/
function getErrStyle(elm){
	if(elm.type == 'text' || elm.type=='password'){
		return 'tf errorInput';
	}
	if(elm.type == 'textarea'){
		return 'ta errorInput';
	}
	if(elm.type == 'select-one'){
		return 'sl errorInput';
	}
	if(elm.type == 'radio'){
		return 'ra errorInput';
	}
	if(elm.type == 'checkbox'){
		return 'cb errorInput';
	}

}

/*取显示的消息，如果没有定义则取默认消息*/
function getSuccMsg(msg){
	return msg = (msg == undefined ? Mat.succMsg : msg);
}

/*取显示的消息，如果没有定义则取默认消息*/
function getErrMsg(msg){
	return msg = (msg == undefined ? Mat.errMsg : msg);
}

/*设置默认显示样式*/
Mat.setDefault = function (elm,msg,style){
	var st = style == undefined ? getSuccStyle(elm) : style;
	Wit.showDefault(elm,msg,getSuccStyle(st));
}

/*显示校验成功*/
Mat.showSucc = function (elm,msg){
	Wit.showSucc(elm,getSuccMsg(msg),getSuccStyle(elm));
}
/*显示校验失败*/
Mat.showErr = function (elm,msg){
	msg = (msg == undefined ? Mat.errMsg : msg);
	Wit.showErr(elm,getErrMsg(msg),getErrStyle(elm));
}

/*检查必填*/
Mat.checkRequired = function (elm,emsg,smsg){
	var reg = /^\s*$/g;
	var errMsg = emsg == undefined ? Mat.requiredMsg  : emsg;
	if(elm.value.length > 0 && !(reg.test(elm.value))){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,errMsg,getErrStyle(elm));
		return false;
	}
}

/*检查长度*/
Mat.checkLength = function (elm,maxLength,emsg,smsg){
	if(elm.value.length <= maxLength){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}

/*检查长度*/
Mat.checkEqualLength = function (elm,length,emsg,smsg){
	if(elm.value.length == length){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}

/*检查是否为数字*/
Mat.checkDigital = function (elm,emsg,smsg){
	if(Wit.checkErr(elm,/^\d+$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}

/*检查是否为字符*/
Mat.checkChar = function (elm,emsg,smsg){
	if(Wit.checkErr(elm,/^[a-z-A-Z]+$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}

/*检查是否为数字、字母、下划线及其组合*/
Mat.checkText = function (elm,emsg,smsg){
	if(Wit.checkErr(elm,/^\w+$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}

/*检查是否为合法的EMAIL*/
Mat.checkEmail = function (elm,emsg,smsg){
	if(Wit.checkErr(elm,/^(?:[a-z\d]+[_\-\+\.]?)*[a-z\d]+@(?:([a-z\d]+\-?)*[a-z\d]+\.)+([a-z]{2,})+$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}
/*Added By DuQ*/
/*检查是否为合法的手机号*/
Mat.checkTelePhone = function (elm,emsg,smsg){
	if((Wit.checkErr(elm,/^1[2-9]\d{9}$/))){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}
/*检查是否为合法的自然数*/
Mat.checkNaturalNumber = function (elm,emsg,smsg){
	if(Wit.checkErr(elm,/^([1-9]+[0-9]*|0)$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}
/*检查字符是否为空*/
Mat.checkNameNull = function(val){
	return Wit.trim(val);
}
/*显示隐藏的div对象*/
Mat.displayObj = function(divObj){
	 var divObj = document.getElementById(divObj);
	 if(divObj.style.display == 'none'){
	 	divObj.style.display = 'block';
	 }
}
/*隐藏显示的div对象*/
Mat.hiddenObj = function(divObj){
	 var divObj = document.getElementById(divObj);
	 if(divObj.style.display == 'block'){
	 	divObj.style.display  = 'none';
	 }
}

/*End By DuQ*/

/*检查是否为IP地址*/
Mat.checkIp = function (elm,emsg,smsg){
	if(Wit.checkErr(elm,/^(((2[0-4]\d)|(25[0-5]))|(1\d{2})|([1-9]\d)|[1-9])\.(((2[0-4]\d)|(25[0-5]))|(1\d{2})|([1-9]\d)|[0-9])\.(((2[0-4]\d)|(25[0-5]))|(1\d{2})|([1-9]\d)|[0-9])\.(((2[0-4]\d)|(25[0-5]))|(1\d{2})|([1-9]\d)|[1-9])$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}


/*检查是否为正整数格式*/  
Mat.checkNumber = function (elm,emsg,smsg){ 
    if(Wit.checkErr(elm,/^[1-9]*[1-9][0-9]*$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}

Mat.checkFloatNumber = function(elm,emsg,smsg){
 if(Wit.checkErr(elm,/^100$|^[1-9]?[0-9](\.[0-9]{1,2})?$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}


/*检查是否为端口*/
Mat.checkPort = function(elm,emsg,smsg){ 
	if(elm.value <= 65535 && elm.value >= 1024){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}

Mat.checkID = function(elm,emsg,smsg){ 
	if(Wit.checkErr(elm,/^[A-Za-z_\-0-9]+$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}

Mat.checkPostNm = function(elm,emsg,smsg){ 
	if(Wit.checkErr(elm,/^[1-9]\d{5}$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}

Mat.checkPostNm2 = function(elm,emsg,smsg){ 
	if(Wit.checkErr(elm,/^[0-9]\d{5}$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}

Mat.checkURL = function(elm,emsg,smsg){ 
	if(Wit.checkErr(elm,/^[a-zA-z]+[:][////][\S]{1,}$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}

Mat.checkCallNm = function(elm,emsg,smsg){ 
	if(Wit.checkErr(elm,/^\d{7,12}$/)){
		Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
		return true;
	}
	else{
		Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
		return false;
	}
}

/*检查长度,包括汉字及其他字符*/
Mat.checkLengthOther = function (elm,maxLength,emsg,smsg){
 if(GetLen(elm.value) <= maxLength){
  Wit.showSucc(elm,getSuccMsg(smsg),getSuccStyle(elm));
  return true;
 }
 else{
  Wit.showErr(elm,getErrMsg(emsg),getErrStyle(elm));
  return false;
 }
}

function GetLen(str) { 
  var len=0; 
  for(var i=0;i<str.length;i++) { 
   char = str.charCodeAt(i); 
 
    if(!(char>255)) { 
      len = len + 1; 
   } else { 
      len = len + 2; 
   } 
  } 
  return len; 
 }

Mat.checkNormal = function(elm,elm_name,max_length,required,called) {
    	if(required) {
    		if(!Mat.checkRequired(elm,elm_name+"为必填项！","")) return false;
    	} else {
    	    if(elm.value.length==0) { Wit.showDefault(elm, '');return true; } 
    	}
    	
    	if(!Mat.checkLengthOther(elm,max_length,elm_name+"数据长度有误！","")) return false;
    	
    	if(typeof(called)!="function") return true;
    	
    	var errStr = elm_name+'数据格式有误！';
    	if(called==Mat.checkID) errStr = elm_name+'数据格式有误(字母数字和下划线)！';
    	if(called==Mat.checkPostNm) errStr = elm_name+'数据格式有误(6位数字)！';
    	if(called==Mat.checkCallNm) errStr = elm_name+'数据格式有误(7~12位数字)！';
    	if(called==Mat.checkURL) errStr = elm_name+'数据格式有误(例如:http://10086.cn/)！';
    	//if(called==Mat.checkIp) errStr = elm_name+'数据格式有误！';
    	if(called==Mat.checkPort) errStr = elm_name+'数据格式有误(1024~65535)！';
    	
    	var retVal;
    		eval("retVal="+called+"(elm,errStr,'')");
    	return retVal;
}  
