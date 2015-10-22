<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('menu.xs')"/>&nbsp;|&nbsp;<s:property value="getText('menu.xs.vehiclebaseinfo')"/>&nbsp;|&nbsp;<s:property value="getText('tmmanage.protocal')" />
</title>
<%@include file="../common/meta.jsp" %>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="protocalEdit_validate.jsp"%>
  <s:form id="protocaledit_form" action="updateProtocal" method="post" onsubmit="return false;">
    <s:hidden id="page" name="page" />
    <s:hidden id="pageSize" name="pageSize" />
    <s:hidden id="protocalId" name="protocalId" />
    
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
            <s:property value="getText('protocal.info.name')" />
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
                <s:select id="oemId" name="protocalInfo.oemId" list="oemInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getTypeInfo()">
				</s:select>
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('protocal.device.own')" />：
              </td>
              <td align="left">
                <s:select id="typeId" name="protocalInfo.typeId" list="typeInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="onTypeChange()">
				</s:select>
              </td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('protocal.name')" />：
              </td>
              <td colspan="5" align="left">
                <s:textfield id="protocalName" name="protocalInfo.protocalName" maxlength="20"/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('protocalmanage.shtml')">
            <s:property value="getText('btn.back')" />
          </a>
          
          <s:if test="#session.perUrlList.contains('111_0_3_3_4')">
          <s:url id="delProtocal" action="deleteProtocal">
            <s:param name="protocalId">${protocalId}</s:param>
            <s:param name="page">${page}</s:param>
            <s:param name="pageSize">${pageSize}</s:param>
          </s:url>
          <a href="#" class="buttontwo" onclick="delProtocal('${delProtocal}');">
            <s:property value="getText('btn.delete')" />
          </a>
          </s:if>
          
          <s:if test="#session.perUrlList.contains('111_0_3_3_3')">
            <a href="#" class="buttontwo" onclick="submitForm()">
              <s:property value="getText('btn.update')" />
            </a>
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
