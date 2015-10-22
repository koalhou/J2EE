<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title><s:property value="getText('menu.xs')" />&nbsp;|&nbsp;<s:property value="getText('informationmanage.menu')" /></title>

<%@include file="../common/meta.jsp" %>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="informationEdit_validate.jsp"%>
  <s:form id="informationedit_form" action="updateInfo" method="post" onsubmit="return false;">
    <s:hidden id="issueId" name="issueId" />
    <s:hidden id="issueStatus" name="issueStatus" />
    <s:hidden id="oldIssueStatus" name="oldIssueStatus" />
    <s:hidden id="replyOrNot" name="replyOrNot" />
    
    <table height="628px" width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td align="center" valign="top" >
    
    <div id="message">
      <s:actionerror theme="mat" />
      <s:fielderror theme="mat"/>
      <s:actionmessage theme="mat"/>
    </div>
    
    <table class="msgBox6" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>" align="left">
          <span class="msgTitle">&nbsp;&nbsp;
            <s:if test="issueInfo.issueStatus != \"1\"">
              <s:property value="getText('informationmanage.mod')" />
              (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
            </s:if>
            <s:if test="issueInfo.issueStatus == \"1\" && issueInfo.replyOrNot != \"1\"">
              <s:property value="getText('informationmanage.menu')" />
            </s:if>
            <s:if test="issueInfo.issueStatus == \"1\"  && issueInfo.replyOrNot == \"1\" ">
              <s:property value="getText('informationmanage.browse')" />
            </s:if>
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <tr>
              <td width="10%" height="28" align="right">
                <s:property value="getText('informationmanage.title')" />：
              </td>
              <td class="fsBlack" align="left">
              	<s:if test="issueInfo.issueStatus != \"1\"">
                  <s:textfield id="issueTheme" name="issueInfo.issueTheme" maxlength="60"/>
                </s:if>
                <s:else>
                  <s:property value="issueInfo.issueTheme"/>
                </s:else>
              </td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('informationmanage.content')" />：
              </td>
              <td class="fsBlack" align="left">
                <s:if test="issueInfo.issueStatus != \"1\"">
                  <s:textarea id="issueContentEdit"
						      name="issueInfo.issueContent" 
						      rows="3">
				  </s:textarea>
				  <div id="bottomSpaceContentEdit" style="display:none"></div>
                </s:if>
				<s:else>
				  <s:textarea id="issueContentBrowse"
						      name="issueInfo.issueContent" 
						      rows="3">
				  </s:textarea>
				  <div id="bottomSpaceContentBrowse" style="display:none"></div>
				</s:else>
              </td>
            </tr>
            
            <s:if test="issueInfo.issueStatus == \"1\" && issueInfo.replyOrNot != \"1\" ">
            <tr>
              <td height="28" align="right">
                <s:property value="getText('informationmanage.menu')" />：
              </td>
              <td>
                <s:textarea id="issueReplyContent"
						      name="issueInfo.replyContent" 
						      rows="3">
				</s:textarea>
				<div id="bottomSpaceReplyEdit" style="display:none"></div>
              </td>
            </tr>
            </s:if>
            
            <s:if test="issueInfo.issueStatus == \"1\" && issueInfo.replyOrNot == \"1\"">
            <tr>
              <td height="28" align="right">
                <s:property value="getText('informationmanage.menu')" />：
              </td>
              <td>
                <s:textarea id="issueReplyContentBrowse"
						      name="issueInfo.replyContent" 
						      rows="3">
				</s:textarea>
				<div id="bottomSpaceReplyBrowse" style="display:none"></div>
              </td>
            </tr>
            </s:if>
            
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('informationmanage.shtml')">
            <s:property value="getText('btn.back')" />
          </a>
          
          <s:if test="issueInfo.replyOrNot != \"1\"">
          
	          <s:if test="#session.perUrlList.contains('111_0_6_5_4')">
	          <a href="#" class="buttontwo" onclick="submitForm()">
	            <s:property value="getText('btn.publish')" />
	          </a>
	          </s:if>
          
	          <s:if test="#session.perUrlList.contains('111_0_6_5_3')">
	          <a href="#" class="buttontwo" onclick="summitPurse()">
	          	<s:property value="getText('btn.ts')" />
	          </a>
	          </s:if>
	          
	          <s:if test="#session.perUrlList.contains('111_0_6_5_6')">
	          <s:url id="deleteInfo" action="deleteInfo">
	            <s:param name="issueId">${issueId}</s:param>
	          </s:url>
	          <a href="#" class="buttontwo" onclick="deleteInfo('${deleteInfo}')">
	          	<s:property value="getText('btn.delete')" />
	          </a>
	          </s:if>
          </s:if>
          
          <s:if test="issueInfo.issueStatus == \"1\" ">
	          <s:if test="#session.perUrlList.contains('111_0_6_5_5')">
	          <s:url id="abandonInfo" action="abandonInfo">
	            <s:param name="issueId">${issueId}</s:param>
	          </s:url>
	          <a href="#" class="buttontwo" onclick="abandonInfo('${abandonInfo}');">
	            <s:property value="getText('notice.deletepublisth')" />
	          </a>
	          </s:if>
          </s:if>
          
          <s:if test="issueInfo.issueStatus == \"2\" && issueInfo.replyOrNot == \"1\" ">
	          <s:if test="#session.perUrlList.contains('111_0_6_5_4')">
	          <a href="#" class="buttontwo" onclick="republishInfo()">
	            <s:property value="getText('btn.publish')" />
	          </a>
	          </s:if>
          </s:if>
          
        </td>
      </tr>
    </table>
    
    </td>
    </tr>
    </table>
  </s:form>
  <%@include file="../common/copyright.jsp"%>
</body>
</html>
