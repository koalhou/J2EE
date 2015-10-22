<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/ckeditor/ckeditor.js' />"></script>
<script type="text/javascript">
  window.onload = function () {
	  CKEDITOR.replace( 'issueContent',{
		    sharedSpaces :
			{
				bottom : 'bottomSpace'
			},
			toolbar :
			[['Undo','Redo'], 
			 ['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'], 
			 ['OrderedList','UnorderedList','-','Outdent','Indent'], 
			 ['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'], 
			 ['Style','FontFormat','FontName','FontSize'], 
			 ['TextColor','BGColor','FitWindow']
			]});
  }
	
  /** 信息标题必填项 **/
  function checkIssueTheme() {
    var elm = $('issueTheme');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }

  /** 初始化样式 **/
  function setFormMessage() {
	Mat.setDefault($('issueTheme'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('issueTheme').onkeyup = checkIssueTheme;
    $('issueTheme').onblur = checkIssueTheme;
  }

  /** 发布信息 **/
  function submitForm() {
    var f1 = checkIssueTheme();
    $('issueStatus').value = "1";
	if (f1) {
	  Wit.commitAll($('informationinfo_form'));
	} else  {
	  return false;
	}
  }

  /** 暂存信息  **/
  function summitPurse() {
    var f1 = checkIssueTheme();
    $('issueStatus').value = "0";
	if (f1) {
	  Wit.commitAll($('informationinfo_form'));
	} else  {
	  return false;
    }
  }
  
  /** 取消填加操作 **/
  function goBack(url) {
    Wit.goBack(url);
  }

  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>