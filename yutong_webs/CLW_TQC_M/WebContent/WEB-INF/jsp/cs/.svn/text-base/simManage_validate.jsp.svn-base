<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script>
  /** 提交form **/
  function submitQuerySim() {
	$('sim_form').action = "<s:url value='/cs/querySim.shtml' />";
    Wit.commitAll($('sim_form'));
  }

  /** 导出SIM卡信息 **/
  function exportSim(){
    if(confirm("<s:property value="getText('confirm.export.file')" />")) {
      $('sim_form').action="<s:url value='/cs/exportSim.shtml' />";
      $('sim_form').submit();
    }
  }
</script>