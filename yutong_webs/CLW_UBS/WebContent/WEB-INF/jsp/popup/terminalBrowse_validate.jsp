<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type="text/javascript">
  /** 查询终端**/
  function submit() {
    Wit.commitAll($('terminalbrowse_form'));
  }

  /** 选择终端 **/
  function operate(str1,str2,str3){
    window.dialogArguments.document.getElementById("terminalId").value=str1;
    window.dialogArguments.document.getElementById("terminalCode").value=str2;
    window.dialogArguments.document.getElementById("videoId").value=str3;
    window.close();
  }
</script>