<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title><s:property value="getText('modify.password.menu')"/></title>
<%@include file="../common/meta.jsp" %>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="modifyPassword_validate.jsp"%>
  
  <s:form id="modifypassword_form" action="updateUserPassword" method="post">
    
    <table height="628px" width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td align="center" valign="top" >
    
    <div id="message">
      <s:actionerror theme="mat" />
      <s:fielderror theme="mat"/>
      <s:actionmessage theme="mat"/>
    </div>
    
    <table class="msgBox2" width="650" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>">
          <span class="msgTitle">&nbsp;&nbsp;
            <s:property value="getText('modify.password.menu')"/>
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('password.old')"/>：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:password id="oldPwd" name="oldPwd"/>
              </td>
              <td width="1%">&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('password.new')"/>：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:password id="newPwd" name="newPwd"/>
              </td>
              <td width="1%">&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('password.confirm')"/>：
              </td>
              <td width="20%" align="left">
                <s:password id="confirmPwd" name="confirmPwd"/>
              </td>
              <td width="1%">&nbsp;</td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="resetForm()">
            <s:property value="getText('btn.reset')"/>
          </a>
          <a href="#" class="buttontwo" onclick="submitForm()">
            <s:property value="getText('btn.sure')"/>
          </a>
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
