<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/TmAttributeDwr.js'></script>

<script>
  /** 获取终端型号列表 **/
  function getTypeInfo() {
    var oemObj = $('oemId');
    TmAttributeDwr.getTmTypeByOemId(oemObj.value, callBackFun);
    
    function callBackFun(data) {
      var typeObj = $('typeId');  
      DWRUtil.removeAllOptions(typeObj);  
      DWRUtil.addOptions(typeObj,{'':'<s:property value="getText('please.select')" />'});  
      DWRUtil.addOptions(typeObj,data); 
      
      var protocalObj = $('protocalId');
      DWRUtil.removeAllOptions(protocalObj);  
      DWRUtil.addOptions(protocalObj,{'':'<s:property value="getText('please.select')" />'});  
    }
  }

  /** 获取终端协议列表 **/
  function getProtocalInfo() {
    var typeObj = $('typeId');
    TmAttributeDwr.getProtocalByTypeId(typeObj.value, callBackFun);
    
    function callBackFun(data) {
      var protocalObj = $('protocalId');  
      DWRUtil.removeAllOptions(protocalObj);  
      DWRUtil.addOptions(protocalObj,{'':'<s:property value="getText('please.select')" />'});  
      DWRUtil.addOptions(protocalObj,data);  
    }
  }
  
  /** 提交form **/
  function submitQueryTerminal() {
    $('terminal_form').action="<s:url value='/zd/queryterminal.shtml' />";
    Wit.commitAll($('terminal_form'));
  }

  /** 导出终端信息 **/
  function exportTerminal(){
    if(confirm("<s:property value="getText('confirm.export.file')" />")) {
      $('terminal_form').action="<s:url value='/zd/exportTerminal.shtml' />";
      $('terminal_form').submit();
    }
  }
  
</script>

