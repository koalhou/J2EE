<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>


<script type="text/javascript">

function submitPostFrom() {
	var f1 = checkZONE_NAME();
	var f2 = checkZONE_CODE();
	var f3 = checkZONE_NAME_only();
	var f4 = checkZONE_CODE_only();
	if(f1&&f2&&f3&&f4){
		var fm=$('clwForm');
		fm.action="<s:url value='/xs/zonemanage_do_add.shtml' />";
		Wit.commitAll(fm);
	}
}
function submitRollBack(){
	var fm=$('clwForm');
	fm.action="<s:url value='/xs/zonemanage.shtml' />";
	Wit.commitAll(fm);
}

function checkZONE_NAME(){
	var elm = $('zone_name_input');
    if(Mat.checkRequired(elm)){
    	Mat.showSucc(elm);
  	    return true; 
	}else{
      Wit.showErr(elm, "<s:property value="getText('zonemanage.info.zone_name')"/><s:property value="getText('must.need')"/>");
      return false;
	}
  }
function checkZONE_CODE(){
	var elm = $('zone_code');
    if(Mat.checkRequired(elm)){
    	Mat.showSucc(elm);
  	    return true; 
	}else{
      Wit.showErr(elm, "<s:property value="getText('zonemanage.info.zone_code')"/><s:property value="getText('must.need')"/>");
      return false;
	}
  }
//地名唯一验证
function checkZONE_NAME_only(){
	var elm = $('zone_name_input');
	DWREngine.setAsync(false);
    var ret = true; 
    ZoneDwr.checkZone_name(elm.value, callBackFun);
    function callBackFun(data)
    {
        ret = data;
    }
    DWREngine.setAsync(true);
    if(ret) {
    	return true;
  	} else {
  		Wit.showErr(elm, "<s:property value="getText('zonemanage.zone_name.only')" />");
      	return false;
  	}
}
//地区编码唯一验证
function checkZONE_CODE_only(){
	var elm = $('zone_code');
	DWREngine.setAsync(false);
    var ret = true; 
    ZoneDwr.checkZone_code(elm.value, callBackFun);
    function callBackFun(data)
    {
        ret = data;
    }
    DWREngine.setAsync(true);
    if(ret) {
    	return true;
  	} else {
  		Wit.showErr(elm, "<s:property value="getText('zonemanage.zone_code.only')" />");
      	return false;
  	}
}

/** 加载事件 **/
function setFormEvent() {
  $('zone_name_input').onkeyup = checkZONE_NAME;
  $('zone_name_input').onblur = checkZONE_NAME;
  $('zone_code').onkeyup = checkZONE_CODE;
  $('zone_code').onblur = checkZONE_CODE;
  
}
/** 初始化样式 **/
function setFormMessage() {
	  Mat.setDefault($('zone_name_input'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('zone_code'),'<span class="noticeMsg">*</span>');
}
window.addEvent('domready', setFormEvent);
window.addEvent('domready', setFormMessage);

</script>