<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/CarDwr.js'></script>
<script type="text/javascript">
function checkAPPLY_ID(){
	var elm = $('apply_id');
    if(Mat.checkRequired(elm)){
    	Mat.showSucc(elm);
  	    return true; 
	}else{
      Wit.showErr(elm, "<s:property value="getText('versionmanage.info.apply_id')"/><s:property value="getText('must.need')"/>");
      return false;
	}
}
function checkVERSION_NAME(){
	var elm = $('version_name');
    if(Mat.checkRequired(elm)){
    	Mat.showSucc(elm);
  	    return true; 
	}else{
      Wit.showErr(elm, "<s:property value="getText('versionmanage.info.version_name2')"/><s:property value="getText('must.need')"/>");
      return false;
	}
}
function checkVERSION_TIME(){
	var elm = $('version_time');
    if(Mat.checkRequired(elm)){
    	Mat.showSucc(elm);
  	    return true; 
	}else{
      Wit.showErr(elm, "<s:property value="getText('versionmanage.info.version_time')"/><s:property value="getText('must.need')"/>");
      return false;
	}
}
function checkVERSION_DESC(){
	var elm=$('version_desc');
	var showelm=document.getElementById('version_desc_show');
	if(elm.value.length>500){
		showelm.className='error';
		if(isIE()){
			showelm.innerText="<s:property value="getText('versionmanage.js.info.lenles500')"/>";
		}else{
			showelm.textContent="<s:property value="getText('versionmanage.js.info.lenles500')"/>";
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
function isIE(){ //ie? 
	   if (window.navigator.userAgent.toLowerCase().indexOf("msie")>=1) 
	    return true; 
	   else 
	    return false;
	} 
  /** 初始化样式 **/
  function setFormMessage() {
	  Mat.setDefault($('apply_id'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('version_name'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('version_time'),'<span class="noticeMsg">*</span>');
	  Mat.setDefault($('version_desc'),'');
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('apply_id').onkeyup = checkAPPLY_ID;
    $('apply_id').onblur = checkAPPLY_ID;
    $('version_name').onkeyup = checkVERSION_NAME;
    $('version_name').onblur = checkVERSION_NAME;
    //$('version_time').onkeyup = checkVERSION_TIME;
    //$('version_time').onblur = checkVERSION_TIME;
    $('version_desc').onkeyup = checkVERSION_DESC;
    $('version_desc').onblur =checkVERSION_DESC;
  }

  function submitPostFrom() {
	var f1 = checkAPPLY_ID();
	var f2 = checkVERSION_NAME();
	var f3 = checkVERSION_TIME();
	var f4 = checkVERSION_DESC();
	
		if (f1&&f2&&f3&&f4) {
			if(confirm("<s:property value="getText('confirm.modify')" />")) {
		        Wit.commitAll($('clwForm'));
		    }
		}
		else  {
			  return false;
		}
	}
  function submitPostDelFrom(){
	  if(confirm("<s:property value="getText('confirm.delete')" />")) {
		  var fm=$('clwForm');
		  fm.action="<s:url value='/xs/versionmanage_do_del.shtml' />";
		  Wit.commitAll(fm);
	  }else{
		  return false;
	}
  }
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>