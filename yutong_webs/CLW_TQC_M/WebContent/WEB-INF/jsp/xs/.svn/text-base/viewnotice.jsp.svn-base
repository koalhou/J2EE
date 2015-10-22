<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<title><s:property value="getText('xs.menu')"/>&nbsp;|&nbsp;<s:property value="getText('ajtnotice.info')"/>&nbsp;|&nbsp;<s:text
	name="noticemanage.view.title" /></title>
	<script type="text/javascript" src="<s:url value='/scripts/ckeditor/ckeditor.js' />"></script>

<script type="text/javascript">

    window.onload = function()
    {
    	CKEDITOR.replace( 'notice_content',	{sharedSpaces :
		{
			bottom : 'bottomSpace'
		},
    		customConfig : 'notoolsconfig.js'
		} );
    };
	CKEDITOR.on( 'instanceReady', function( ev )
	{		editor = ev.editor;
			editor.setReadOnly( true );
			
	});

</script>
</head>



<body>
<div id="main"><%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="../common/menu.jsp"%>
<%@ include file="notice_validate.jsp"%>
<s:form id="viewnoticeform" action="dropEnterpriseNotice" method="post" onsubmit="return false;">
<s:hidden id="notice_id" name="notice_id" />
	<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="36" valign="top">
	  			<div class="toolbar">
	            	<div class="contentTil"><s:text	name="ajtnotice.info" /></div>
			    </div>
		    </td>
 		 </tr>
 	     <tr>
		     <td>
			<!-- message -->
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                 <tr>
                  <td align="center">
	                  <s:actionmessage cssStyle="font-size: 12px;color: #009900"/>
	                  <s:actionerror cssStyle="font-size: 12px;color: #CC0000"/>
	             </td>
                </tr>
              </table>
             </td>
		  </tr>
		  <tr>
				<td valign="top">
				<table class="msgBox6" border="0" align="center"
					cellpadding="0" cellspacing="0">
					<tr>
						<td height="32"><span class="msgTitle">&nbsp;&nbsp;<s:text name="noticemanage.view.title" /></span></td>
					</tr>
					<tr>
						<td align="center" valign="top"><table width="100%" border="0" cellspacing="8" cellpadding="0">
						  <tr>
						    <td height="50" align="center" class="noticeTi2"><label><s:property value='ajtNotice.notice_theme'/></label></td>
					      </tr>
						  
						  <tr>
						    <td align="left" class="lineHeight"><textarea id="notice_content" name="notice_content"  rows="2" cols="4" ><s:property value="ajtNotice.notice_content"/></textarea><div id="bottomSpace" style="display:none">
								</div></td>
					      </tr>
						  <tr>
						    <td height="30" align="right">&nbsp;</td>
					      </tr>
						  <tr>
						    <td align="right"><table width="180" border="0" cellpadding="0" cellspacing="0" bordercolor="#c3c3c3" style="border-collapse:collapse; margin: 0 25px 0 0;">
						      <tr>
						        <td align="right" bgcolor="" style=" padding:8px 5px 8px 8px; line-height:20px; white-space:nowrap;"><label><s:property value='ajtNotice.publish_user'/></label><br />
						          <s:if test="ajtNotice.notice_type==0">
									<label><s:property value="getText('select.type.ajt0')" /></label>
								</s:if>
								<s:if test="ajtNotice.notice_type==1">
									<label><s:property value="getText('select.type.ajt1')" /></label>
								</s:if>
								<s:if test="ajtNotice.notice_type==2">
									<label><s:property value="getText('select.type.ajt2')" /></label>
								</s:if><br />
						          <label><s:property value='ajtNotice.publish_time'/></label></td>
					          </tr>
						      </table></td>
					      </tr>
						  <tr>
						    <td align="right">&nbsp;</td>
					      </tr>
					      <s:if test="AccessoryList.size() != 0">
						  <tr>
						    <td align="left" class="lineHeight">&nbsp;&nbsp;&nbsp;&nbsp;附件：<br />
						      <table width="95%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
								    <td width="70">&nbsp;</td>
								    <td><s:iterator value="AccessoryList">
										<a href="downAccessory.shtml?accessoryinfo.accessoryId=<s:property value="accessoryId"/>"><s:property value="accessoryName"/></a><br>
									</s:iterator></td>
								  </tr>
								</table></td>
					      </tr>	
					      </s:if>					  
					    </table></td>
				  </tr>
					<tr>
				        <td class="btnBar"> 
				          <a href="#" onclick="history.go(-1);" class="buttontwo"><s:text name="btn.back" /></a>
				          <s:if test="#session.perUrlList.contains('111_0_6_6_5')">
				          	  <s:if test="enterpriseNotice.creater!=#session.adminProfile.userID">
					          </s:if>
					       	  <s:else>
					          <a href="#" onclick="dropViewEnterpriseNotice()" class="buttontwo">废弃</a>
					          </s:else>
				          </s:if>
				        </td>
			      	</tr>
				</table>
			</td>
	  </tr>
</table>

</s:form>
</div>
</body>
</html>

