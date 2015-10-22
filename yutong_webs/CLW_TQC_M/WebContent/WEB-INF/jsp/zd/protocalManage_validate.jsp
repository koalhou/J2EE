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
    }
  }

  /** 提交form **/
  function submit() {
    Wit.commitAll($('protocal_form'));
  }

</script>

