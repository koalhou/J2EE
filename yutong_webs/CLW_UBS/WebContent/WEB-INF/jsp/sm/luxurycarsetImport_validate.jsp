<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
  /** 检查文件是否选择 **/
  function checkFilePath() {
	var fileObj = $('luxurycarsetImport_form_file');
    var filePath = fileObj.value;
    if("" == filePath) {
      Wit.showErr(fileObj, "<s:property value="getText('please.select.flie')" />");
      return false;
    } else if(fileType(filePath,".xls,.xlsx")) {
        Mat.showSucc(fileObj);
        return true;   
    }else{
        Wit.showErr(fileObj, "请您选择Excel文件");
        return false;
    }
  }
  /** 判断文件类型 **/
  function fileType(path,uType){
	var tType,i=0,suf="",isOk=false;
	if(path=='undefined' || path=='' || uType=='undefined' || uType==''){
		return false;
	}else{
		suf = path.substring(path.lastIndexOf('.'),path.length).toLowerCase();
		tType = uType.split(",");
		for(i=0;i<tType.length;i++){
			if(suf==tType[i]){
				isOk=true;
				break;
			}
		}
	}
	return isOk;
  }
  /** 加载事件 **/
  function setFormEvent() {
  }

  /** 提交form **/
  function submitForm() {
    var f2 = checkFilePath();
	if (f2) {
      if(confirm("<s:property value="getText('confirm.import.file')" />")) {
	    Wit.commitAll($('luxurycarsetImport_form'));
      }
	} else  {
	  return false;
	}
  }

  /** 取消填加操作 **/
  function goBack(url) {
    Wit.goBack(url);
  }

  window.addEvent('domready', setFormEvent);
</script>