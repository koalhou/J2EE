<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/ckeditor/ckeditor.js' />"></script>
<script type="text/javascript">
  window.onload = function () {
    <s:if test="issueInfo.issueStatus != \"1\"">
      // 编辑问题信息
      CKEDITOR.replace( 'issueContentEdit',{ customConfig : '',
    	                                     sharedSpaces :{ bottom : 'bottomSpaceContentEdit'},
                                             toolbar :[['Undo','Redo'], 
                                                      ['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'], 
                                                      ['OrderedList','UnorderedList','-','Outdent','Indent'], 
                                                      ['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'], 
                                                      ['Style','FontFormat','FontName','FontSize'], 
                                                      ['TextColor','BGColor','FitWindow']]});
    </s:if>	
    <s:else>
      // 浏览问题信息
      //CKEDITOR.replace( 'issueContentBrowse',	{customConfig : 'notoolsconfig.js'} );
      /**
      CKEDITOR.on( 'instanceReady', function( ev ){editor = ev.editor;
                                                   editor.setReadOnly( true );
                                                  });**/
      CKEDITOR.replace( 'issueContentBrowse',	{customConfig : 'notoolsconfig.js',
    	                                         sharedSpaces :{ bottom : 'bottomSpaceContentBrowse'},
                                                 on :{instanceReady : function( ev ){editor = ev.editor;
                                                                                     editor.setReadOnly( true );
                                  		                                            }
                                                     }
                                                          /**,
                                                 editorConfig:{editorConfig:function( config ){config.toolbarCanCollapse=false;
                                                                                               config.toolbarStartupExpanded = false;
                                                                                               config.resize_enabled = true;}
                                                              }**/
                                                });         
    </s:else>
    
    <s:if test="issueInfo.issueStatus == \"1\" && issueInfo.replyOrNot != \"1\" ">
      // 编辑反馈信息
      CKEDITOR.replace( 'issueReplyContent',{customConfig : '',
    	                                     sharedSpaces :{ bottom : 'bottomSpaceReplyEdit'},
                                             toolbar :[['Undo','Redo'], 
                                                       ['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'], 
                                                       ['OrderedList','UnorderedList','-','Outdent','Indent'], 
                                                       ['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'], 
                                                       ['Style','FontFormat','FontName','FontSize'], 
                                                       ['TextColor','BGColor','FitWindow']]});
    </s:if>	
    <s:if test="issueInfo.issueStatus == \"1\" && issueInfo.replyOrNot == \"1\"">
      // 浏览反馈信息
      CKEDITOR.replace( 'issueReplyContentBrowse',	{customConfig : 'notoolsconfig.js',
    	                                             sharedSpaces :{ bottom : 'bottomSpaceReplyBrowse'},
                                                     on :{instanceReady : function( ev ){editor = ev.editor;
                                                                                     editor.setReadOnly( true );
                                  		                                            }
                                                     }
                                                          /**,
                                                 editorConfig:{editorConfig:function( config ){config.toolbarCanCollapse=false;
                                                                                               config.toolbarStartupExpanded = false;
                                                                                               config.resize_enabled = true;}
                                                              }**/
                                                });
    </s:if>
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
    <s:if test="issueInfo.issueStatus != \"1\"">
	Mat.setDefault($('issueTheme'),'<span class="noticeMsg">*</span>');
	</s:if>
  }

  /** 加载事件 **/
  function setFormEvent() {
    <s:if test="issueInfo.issueStatus != \"1\"">
      $('issueTheme').onkeyup = checkIssueTheme;
      $('issueTheme').onblur = checkIssueTheme;
    </s:if>
  }

  /** 发布信息 **/
  function submitForm() {
    var oldIssueStatus = $('oldIssueStatus').value;

    if("1" == oldIssueStatus) {
      // 发布反馈信息  
      $('replyOrNot').value = "1";
      Wit.commitAll($('informationedit_form'));
    } else {
      // 发布问题信息  
      $('issueStatus').value = "1";
      var f1 = checkIssueTheme();
      if (f1) {
    	Wit.commitAll($('informationedit_form')); 
      } else  {
        return false;
      }
    }
    
  }

  /** 暂存信息  **/
  function summitPurse() {
    var oldIssueStatus = $('oldIssueStatus').value;

    if("1" == oldIssueStatus) {
        // 暂存反馈信息  
        $('replyOrNot').value = "0";
        Wit.commitAll($('informationedit_form'));
    } else {
        // 暂存问题信息  
        $('issueStatus').value = "0";
        var f1 = checkIssueTheme();
        if (f1) {
          Wit.commitAll($('informationedit_form'));
        } else  {
      	  return false;
        }
    }
  }

  /** 废弃信息 **/
  function abandonInfo(url) {
    if(confirm("<s:property value="getText('confirm.abandon')" />")) {
      Wit.goBack(url);
    } else {
      return false;
    }
  }

  /** 废弃状态下发布信息 **/
  function republishInfo() {
	  var f1 = checkIssueTheme();
	  if(f1) {
		  $('informationedit_form').action="<s:url value='/xs/republishInfo.shtml' />";
		  $('informationedit_form').submit();
	  } else {
		  return false;
	  }
  }

  /** 删除信息 **/
  function deleteInfo(url) {
    if(confirm("<s:property value="getText('confirm.delete')" />")) {
      Wit.goBack(url);
    } else {
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