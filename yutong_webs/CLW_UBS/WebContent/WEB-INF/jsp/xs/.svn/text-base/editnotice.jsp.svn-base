<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<%
	StringBuffer uploadUrl = new StringBuffer();
	uploadUrl.append(request.getContextPath());
	uploadUrl.append("/UploadServlet.save");
%>
<html>
<head>
<title><s:property value="getText('xs.menu')"/>&nbsp;|&nbsp;<s:property value="getText('ajtnotice.info')"/>&nbsp;|&nbsp;<s:text
	name="noticemanage.edit.title" /></title>
<script type="text/javascript"
	src="<s:url value='/scripts/ckeditor/ckeditor.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/swfupload/swfupload.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/swfupload/handlers.js' />"></script>
	
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
	
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/AccessoryDwr.js'></script>	
<script type="text/javascript">
var swfu;
    window.onload = function(){
    	var folderName = document.getElementById("notice_id").value;  
	    swfu = new SWFUpload({upload_url: "<%=uploadUrl.toString()%>",
	    	                  //upload_url: "<s:url value='/cs/addAccessory.shtml' />",
	                          post_params: {"filePath":folderName},
	                          use_query_string : true,
	                          
	                          file_size_limit : "10 MB",	
		                      file_types : "*.*",
	                          file_types_description : "所有文件",
	                          file_upload_limit : "0",
	                          
	                          
	                          file_queue_error_handler : fileQueueError,
	                          file_dialog_complete_handler : fileDialogComplete,
	                          file_queued_handler : fileQueued,
	            			  
	                          upload_progress_handler : uploadProgress,
	                          upload_error_handler : uploadError,
	                          upload_success_handler : uploadSuccess,
	                          upload_complete_handler : uploadComplete,
	                          
	                          button_image_url : '<s:url value="/images/SmallSpyGlassWithTransperancy_17x18.png" />',
	                          button_placeholder_id : "spanButtonPlaceholder",
	                          button_width: 180,
	                          button_height: 18,
	                          button_text : '<span class="button">选择附件 <span class="buttonSmall">(10 MB Max)</span></span>',
	                          button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
	                          button_text_top_padding: 0,
	                          button_text_left_padding: 18,
	                          button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
	                          button_cursor: SWFUpload.CURSOR.HAND,
		
	                          flash_url : '<s:url value="/scripts/swfupload/swfupload.swf" />',
	                          custom_settings : {
	                            upload_target : "divFileProgressContainer",
	                            	progressTarget : "fsUploadProgress",
	            					cancelButtonId : "btnCancel",
	            					upload_successful : false
	                          },
	                          debug: false
	    });
    	CKEDITOR.replace( 'notice_content',{sharedSpaces :
		{
			bottom : 'bottomSpace'
		},
       		toolbar :
          		 [['DocProps','-','NewPage','Preview','-','Templates'], 
       			['Cut','Copy','Paste','PasteText','PasteWord','-','Print','SpellCheck'], 
       			['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'], 
       			['Form','Checkbox','Radio','TextField','Textarea','Select','Button','ImageButton','HiddenField'], 
       			'/', 
       			['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'], 
       			['OrderedList','UnorderedList','-','Outdent','Indent'], 
       			['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'], 
       			['Link','Unlink','Anchor'], 
       			['Image','Flash','Table','Rule','Smiley','SpecialChar','PageBreak'], 
       			['Style','FontFormat','FontName','FontSize'], 
       			['TextColor','BGColor','FitWindow']
       			]
       			});
    	showUploadedFiles();
    };

    function startUploadFile(){
	    swfu.startUpload();
	  }

	var delFiles = "";

    function delUploadedAccessory(fileId) {
        
    	var infoTableUploaded = document.getElementById("infoTableUploaded");
    	var row = document.getElementById(fileId);
    	infoTableUploaded.deleteRow(row.rowIndex);
    	if(delFiles == "") {
    		delFiles = delFiles + fileId;
    	} else {
    		delFiles = delFiles + ',' + fileId;
    	}
    	document.getElementById("del_ids").value = delFiles;
    }

	  
	function addTableRows(data){
		//用表格显示
		var infoTable = document.getElementById("infoTableUploaded");
		for(var i = 0; i < data.length; i ++ ) {
			var row = infoTable.insertRow(-1);
			row.id = data[i].accessoryId;
			var col1 = row.insertCell(-1);
			var col2 = row.insertCell(-1);
			var col3 = row.insertCell(-1);
			col1.innerHTML = "已填加附件 : ";
			col2.innerHTML = "<a href='downAccessory.shtml?accessoryinfo.accessoryId="+data[i].accessoryId+"'>"+data[i].accessoryName+"</a>&nbsp;&nbsp;";
			col3.innerHTML = "<img src='../images/delAccessory.gif' style='cursor:pointer;' title='删除' onclick='javascript:delUploadedAccessory(\""+data[i].accessoryId+"\")'></img>";
		}	
	}

	function showUploadedFiles() {
		// 取消队列中准备上传的文件
		cancelUpload();
		// 获取公告ID
		var noticeId = document.getElementById("notice_id").value;
		DWRUtil.removeAllRows("infoTableUploaded");
		AccessoryDwr.getAccessoryInfos(noticeId, delFiles, getAccessoryInfos_callback);
		function getAccessoryInfos_callback(data){
			addTableRows(data.data); //把返回的数据添加到表格中，   
		}
		//document.getElementById("accessoryPrepare").style.display = "none";
	}
	
   /* function deleteAccessory(accessoryId){
		if(document.getElementById(accessoryId).className=="sbutton3"){
			EnterpriseNoticeDwr.deleteAccessory(accessoryId,{
		    	callback : function(status) {
		    		document.getElementById(accessoryId).className = "sbutton4";;
		    	},
		    	errorHandler : function(msg, ex) {
		    		alert(msg);
		    	}
		    	});
		}
    	
    }*/
    window.onbeforeunload = function() {
		var destroyed = swfu.destroy();
	}
	
</script>
</head>
<body>
<div id="main">
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="../common/menu.jsp"%>
<%@ include file="notice_validate.jsp"%>
<s:form id="editnoticeform" action="saveEditEnterpriseNotice" method="post" onsubmit="return false;">
<s:hidden id="notice_id" name="notice_id" />
<s:hidden id="del_ids" name="del_ids" />
<s:hidden id="edit_accessory_ids" name="edit_accessory_ids" />
<table height="628" width="100%" border="0" cellspacing="0"
	cellpadding="0">
	<tr>
		<td height="36" valign="top">
		<div class="toolbar">
		<div class="contentTil"><s:text	name="ajtnotice.info" /></div>
		</div>
		</td>
	</tr>
	<tr>
		<td><!-- message -->
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center"><s:actionmessage
					cssStyle="font-size: 12px;color: #009900" /> <s:actionerror
					cssStyle="font-size: 12px;color: #CC0000" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td valign="top">
		<table class="msgBox6" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="32"><span class="msgTitle">&nbsp;&nbsp;<s:text name="noticemanage.edit.title" /></span></td>
			</tr>
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="10%" height="28" align="right"><s:text
							name="notice.title" />：</td>
						<td width="90%"><s:textfield id="notice_theme"
							name="ajtNotice.notice_theme" /><span
								class="noticeMsg">*</span></td>
					</tr>
					<tr>
						<td height="28" align="right"><s:text name="notice.content" />：</td>
						<td><textarea id="notice_content" name="ajtNotice.notice_content"
							rows="2" cols="4"><s:property
							value="ajtNotice.notice_content" /></textarea><div id="bottomSpace" style="display:none">
								</div></td>

					</tr>
					<tr>
						<td height="35" align="right"><s:text name="notice.type" />：</td>
						<td><select name="ajtNotice.notice_type" id="notice_type">
							<option value="0"
								<s:if test="0==ajtNotice.notice_type">
										selected="selected"
									</s:if>><s:property
								value="getText('select.type.ajt0')" /></option>

							<option value="1"
								<s:if test="1==ajtNotice.notice_type">
										selected="selected"
								</s:if>><s:property
								value="getText('select.type.ajt1')" /></option>
							<option value="2"
								<s:if test="2==ajtNotice.notice_type">
										selected="selected"
								</s:if>><s:property
								value="getText('select.type.ajt2')" /></option>
						</select></td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
							<div id="accessoryUploaded">
								<table id="infoTableUploaded" 
								       border="0" 
								       cellpadding="0px"
									   style="display: inline; border: solid 0px #7FAAFF; padding: 0px; margin-top: 8px;">
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td width="10%" height="28" align="right" valign="top">附件：</td>
						<td width="90%">
							<!-- <table>
								<s:iterator value="AccessoryList">
								<tr><td><a href="downAccessory.shtml?accessoryinfo.accessoryId=<s:property value="accessoryId"/>"><s:property
										value="accessoryName" /></a>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><a href="#" class="sbutton3" id="<s:property value="accessoryId"/>" onclick="deleteAccessory(this.id)">删除</a></td></tr>
										<br>
								</s:iterator>
							</table> -->
							<div>
								<span id="spanButtonPlaceholder"></span> 
							</div>
							
							<div id="accessoryPrepare">
								<div id="thumbnails">
									<table id="infoTable" 
									       border="0" 
									       width="530" 
									       cellpadding="0px"
										   style="display: inline; border: solid 0px #7FAAFF; padding: 0px; margin-top: 8px;">
									</table>
								</div>
								
								<div id="divFileProgressContainer"></div>
							</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr><!-- editCancelAccessory() -->
				<td class="btnBar"><a href="#" onclick="deleteEditAccessory()"
					class="buttontwo"><s:text name="btn.back" /></a> 
					<s:if test="#session.perUrlList.contains('111_0_6_6_4')">
						<a href="#"
						class="buttontwo" id="addbutton" onclick="submitEditForm()">发布</a>
					</s:if> 
				<!-- 公告为废弃状态，则只能“发布”操作 -->
				<s:if
					test="ajtNotice.notice_status==2">
				</s:if> 
				<s:else>
					<s:if test="#session.perUrlList.contains('111_0_6_6_3')">
					<a href="#" class="buttontwo" id="addbutton" onclick="editTempNotice()">暂存</a>
					</s:if>
					<s:if test="#session.perUrlList.contains('111_0_6_6_6')">
					<a href="#" class="buttontwo" id="addbutton" onclick="editDeleteNotice()">删除</a>
					</s:if>
				</s:else></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</s:form>
<%@include file="/WEB-INF/jsp/common/copyright.jsp"%>
</body>
</html>
