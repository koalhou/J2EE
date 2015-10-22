<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script>
  /** 提交form **/
  function submitQueryFlux() {
	$('simflux_form').action="<s:url value='/cs/querySimFlux.shtml' />";
    Wit.commitAll($('simflux_form'));
  }
	
   /** 导出SIM卡流量信息 **/
  function exportSimFlux(){
    if(confirm("<s:property value="getText('confirm.export.file')" />")) {
      $('simflux_form').action="<s:url value='/cs/exportSimFlux.shtml' />";
      $('simflux_form').submit();
    }
  }
</script>