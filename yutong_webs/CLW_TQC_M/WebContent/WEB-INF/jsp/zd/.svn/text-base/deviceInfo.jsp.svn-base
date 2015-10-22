<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('menu.xs')"/>&nbsp;|&nbsp;<s:property value="getText('menu.xs.vehiclebaseinfo')"/>&nbsp;|&nbsp;<s:property value="getText('tmmanage.device')" />
</title>
<%@include file="../common/meta.jsp" %>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="deviceInfo_validate.jsp"%>
  <s:form id="deviceinfo_form" action="addDevice" method="post" onsubmit="return false;">
    <s:hidden id="page" name="page" />
    <s:hidden id="pageSize" name="pageSize" />
    
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
            <s:property value="getText('device.info.name')" />
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('device.oem.own')" />：
              </td>
              <td align="left">
                <s:select id="oemId" name="deviceInfo.oemId" list="oemList" listKey="oemId" listValue="fullName" >
                </s:select>
              </td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('device.name')" />：
              </td>
              <td colspan="5" align="left">
                <s:textfield id="typeName" name="deviceInfo.typeName" maxlength="20"/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('devicemanage.shtml')">
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
