<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('usermanage.menu')" />&nbsp;|&nbsp;<s:property value="getText('usermanage.name')" />
</title>
<%@include file="../common/meta.jsp" %>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="userPwdReset_validate.jsp"%>
  <s:form id="userReset_form" action="resetPwd" method="post">
  <s:hidden id="page" name="page" />
  <s:hidden id="pageSize" name="pageSize" />
  <s:hidden id="userId" name="userId" />
  
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
        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>" align="left">
          <span class="msgTitle">&nbsp;&nbsp;
            <s:property value="getText('user.reset.password')" />
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="left">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('password.new')" />：
              </td>
              <td width="20%" class="fsBlack">
                <s:password id="password" name="userDetail.password" cssStyle="width:131px" maxlength="30"/>
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('user.confirm.password')" />：
              </td>
              <td width="20%" class="fsBlack">
                <s:password id="confirmPassword" name="userDetail.confirmPassword" cssStyle="width:131px" maxlength="30"/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('usermanage.shtml')">
            <s:property value="getText('btn.back')" />
          </a>
          <a href="#" class="buttontwo" onclick="submitForm()">
            <s:property value="getText('btn.sure')" />
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
