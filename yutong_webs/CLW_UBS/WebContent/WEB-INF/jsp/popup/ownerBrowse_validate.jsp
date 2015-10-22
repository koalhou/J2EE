<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type="text/javascript">
  /** 查询车主 **/
  function submit() {
    Wit.commitAll($('ownerbrowse_form'));
  }
  
  /** 选择车主 **/
  function operate(str1,str2){
    window.dialogArguments.document.getElementById("userId").value=str1;
    window.dialogArguments.document.getElementById("userName").value=str2;
    window.close();
  }
  
  /** 取消车主选择 **/
  function cancelSelect(){
    window.dialogArguments.document.getElementById("userId").value="";
    window.dialogArguments.document.getElementById("userName").value="";
    window.close();
  }
</script>