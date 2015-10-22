<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type="text/javascript">
function f1(){
	var elm = $('conditionNewCar');
   	if(Wit.checkErr(elm,/^[0-9]*$/)){
   	    Mat.showSucc(elm);
	    return true;
   	}
   	Mat.setDefault($('conditionNewCar'),'<span class="noticeMsg">请输入正整数</span>');
   	return false;
}
function f2(){
	var elm = $('conditionRemindNewCar');
   	if(Wit.checkErr(elm,/^[0-9]*$/)){
   	    Mat.showSucc(elm);
	    return vs1();
   	}
   	Mat.setDefault($('conditionRemindNewCar'),'<span class="noticeMsg">请输入正整数</span>');
   	return false;
}
function f3(){
	var elm = $('goCondition');
   	if(Wit.checkErr(elm,/^[0-9]*$/)){
   	    Mat.showSucc(elm);
	    return true;
   	}
   	Mat.setDefault($('goCondition'),'<span class="noticeMsg">请输入正整数</span>');
   	return false;
}
function f4(){
	var elm = $('goRemind');
   	if(Wit.checkErr(elm,/^[0-9]*$/)){
   	    Mat.showSucc(elm);
	    return vs2();
   	}
   	Mat.setDefault($('goRemind'),'<span class="noticeMsg">请输入正整数</span>');
   	return false;
}
function f5(){
	var elm = $('conditionCompulsory');
   	if(Wit.checkErr(elm,/^[0-9]*$/)){
   	    Mat.showSucc(elm);
	    return true;
   	}
   	Mat.setDefault($('conditionCompulsory'),'<span class="noticeMsg">请输入正整数</span>');
   	return false;
}
function f6(){
	var elm = $('conditionRemindCompulsory');
   	if(Wit.checkErr(elm,/^[0-9]*$/)){
   	    Mat.showSucc(elm);
	    return vs3();
   	}
   	Mat.setDefault($('conditionRemindCompulsory'),'<span class="noticeMsg">请输入正整数</span>');
   	return false;
}
function f7(){
	var elm = $('conditionCompulsoryLuxury');
   	if(Wit.checkErr(elm,/^[0-9]*$/)){
   	    Mat.showSucc(elm);
	    return true;
   	}
   	Mat.setDefault($('conditionCompulsoryLuxury'),'<span class="noticeMsg">请输入正整数</span>');
   	return false;
}
function f8(){
	var elm = $('conditionRemindCompulsoryLuxury');
   	if(Wit.checkErr(elm,/^[0-9]*$/)){
   	    Mat.showSucc(elm);
	    return vs4();
   	}
   	Mat.setDefault($('conditionRemindCompulsoryLuxury'),'<span class="noticeMsg">请输入正整数</span>');
   	return false;
}
function f9(){
	var elm = $('condition_luxury');
   	if(Wit.checkErr(elm,/^[0-9]*$/)){
   	    Mat.showSucc(elm);
	    return true;
   	}
   	Mat.setDefault($('condition_luxury'),'<span class="noticeMsg">请输入正整数</span>');
   	return false;
}
function checkInput(){
	if(f1() && f2() && f3()&&f4() && f5() && f6()&&f7() && f8() && f9()){
		return true;
	}else{
		return false;
	}
}
function compareVal(id1,id2){
	try{
		var v1 = parseInt(jQuery("#"+id1).attr("value"));
		var v2 = parseInt(jQuery("#"+id2).attr("value"));
		if(v1 >= v2){
			return true;
		}
	}catch(e){}
	return false;
}
function vs1(){
	if(!compareVal("conditionNewCar","conditionRemindNewCar")){
		Mat.setDefault($('conditionRemindNewCar'),'<span class="noticeMsg">提醒条件应该大于或等于条件</span>');
		return false;
	}
	return true;
}
function vs2(){
	if(!compareVal("goCondition","goRemind")){
		Mat.setDefault($('goRemind'),'<span class="noticeMsg">提醒条件应该大于或等于条件</span>');
		return false;
	}
	return true;
}
function vs3(){
	if(!compareVal("conditionCompulsory","conditionRemindCompulsory")){
		Mat.setDefault($('conditionRemindCompulsory'),'<span class="noticeMsg">提醒条件应该大于或等于条件</span>');
		return false;
	}
	return true;
}
function vs4(){
	if(!compareVal("conditionCompulsoryLuxury","conditionRemindCompulsoryLuxury")){
		Mat.setDefault($('conditionRemindCompulsoryLuxury'),'<span class="noticeMsg">提醒条件应该大于或等于条件</span>');
		return false;
	}
	return true;
}
  /** 初始化样式 **/
  function setFormMessage() {
	  Mat.showSucc($('conditionNewCar'));
	  Mat.showSucc($('conditionRemindNewCar'));
	  Mat.showSucc($('goCondition'));
	  Mat.showSucc($('goRemind'));
	  Mat.showSucc($('conditionCompulsory'));
	  Mat.showSucc($('conditionRemindCompulsory'));
	  Mat.showSucc($('conditionCompulsoryLuxury'));
	  Mat.showSucc($('conditionRemindCompulsoryLuxury'));
	  Mat.showSucc($('condition_luxury'));
	  //Mat.setDefault($('vehicleVin'),'<span class="noticeMsg">*</span>');
	  //Mat.setDefault($('vehicle_ln'),'');
  }

  /** 加载事件 **/
  function setFormEvent() {
	try{
    	$('conditionNewCar').onkeyup = f1;
    	$('conditionNewCar').onblur = f1;
    	$('conditionRemindNewCar').onkeyup = f2;
    	$('conditionRemindNewCar').onblur = f2;
    	$('goCondition').onkeyup = f3;
    	$('goCondition').onblur = f3;
    	$('goRemind').onkeyup = f4;
    	$('goRemind').onblur = f4;
    	$('conditionCompulsory').onkeyup = f5;
    	$('conditionCompulsory').onblur = f5;
    	$('conditionRemindCompulsory').onkeyup = f6;
    	$('conditionRemindCompulsory').onblur = f6;
    	$('conditionCompulsoryLuxury').onkeyup = f7;
    	$('conditionCompulsoryLuxury').onblur = f7;
    	$('conditionRemindCompulsoryLuxury').onkeyup = f8;
    	$('conditionRemindCompulsoryLuxury').onblur = f8;
    	$('condition_luxury').onkeyup = f9;
    	$('condition_luxury').onblur = f9;
	}catch(e){}
  }

function submitPostFrom() {
	trimAllObjs();

	if (checkInput() && vs1() && vs2() && vs3()) {
		Wit.commitAll($('maintenanceForm'));
	}else {
		return false;
	}
}
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>