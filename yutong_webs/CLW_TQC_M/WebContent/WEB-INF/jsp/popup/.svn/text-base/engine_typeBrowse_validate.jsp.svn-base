<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript">
  /** 查询角色 **/
  function submit() {
    Wit.commitAll($('enginebrowse_form'));
  }
  
  /** 选择角色 **/
  function operate(str1,str2){
    window.dialogArguments.document.getElementById("engine_type").value=str1;
    window.dialogArguments.document.getElementById("engine_type_info").value=str2;
    window.dialogArguments.document.getElementById("engine_type_info").onkeyup();
    window.close();
  }
  
  /** 取消角色选择 **/
  function cancelSelect(){
	    window.dialogArguments.document.getElementById("engine_type").value="";
	    window.dialogArguments.document.getElementById("engine_type_info").value="";
	    window.dialogArguments.document.getElementById("engine_type_info").onkeyup();
    window.close();
  }

  (function($){
		// 预缓存皮肤，数组第一个为默认皮肤
		$.dialog.defaults.skin = ['aero'];
	  })(art);
  
  /** 新建发动机型号 **/
  function createEngineType() {	
	art.dialog.open("<s:url value='/popup/engineTypeCreate.shtml' />",{
		title:"发动机型号新建",
		lock:true,
		width:400,
		height:200
	});
  }
</script>