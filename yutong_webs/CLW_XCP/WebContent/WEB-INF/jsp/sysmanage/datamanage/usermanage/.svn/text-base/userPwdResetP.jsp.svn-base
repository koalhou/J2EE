<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<div id="main" style="overflow: auto;">
  <s:form id="userReset_form" action="resetPwdP" method="post">
  <s:hidden id="page" name="page" />
  <s:hidden id="pageSize" name="pageSize" />
  <s:hidden id="userId" name="userId" /> 
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
            重置用户密码
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="left">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="14%" height="28" align="right">
                新密码：
              </td>
              <td width="35%" class="fsBlack">
                <s:password id="password" name="password" cssStyle="width:131px" maxlength="30"/>
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                确认密码：
              </td>
              <td width="20%" class="fsBlack">
                <s:password id="confirmPassword" name="confirmPassword" cssStyle="width:131px" maxlength="30"/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('../usm/usermanageAction.shtml')">
           取消
          </a>
          <a href="#" class="buttontwo" onclick="submitForm()">
           确认
          </a>
        </td>
      </tr>
    </table>
    </td>
  </tr>
  </s:form>
  </div>
	</div>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/UserManageDwr.js'></script>
<%@include file="userPwdResetP_validate.jsp"%>
</body>
</html>
