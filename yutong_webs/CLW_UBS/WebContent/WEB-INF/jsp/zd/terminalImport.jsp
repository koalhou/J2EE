<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('menu.cl')" />&nbsp;|&nbsp;<s:property value="getText('tmmanage.terminal')" />
</title>
<%@include file="../common/meta.jsp" %>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="terminalImport_validate.jsp"%>
  <s:form id="terminalimport_form" action="importTerminal" method="post" enctype="multipart/form-data">
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
            <s:property value="getText('terminal.manage.import')" />
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('oem.name')" />：
              </td>
              <td class="fsBlack" align="left">
                <s:select id="oemId" name="terminalInfo.oemId" list="oemInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getTypeInfo()" cssStyle="width:131px;">
                </s:select>
              </td>
            </tr>
            <tr>
              <td width="14%" align="right">
                <s:property value="getText('device.name')" />：
              </td>
              <td class="fsBlack" align="left">
                <s:select id="typeId" name="terminalInfo.typeId" list="typeInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getProtocalInfo()" cssStyle="width:131px;">
                </s:select>
              </td>
            </tr>
		    <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('protocal.name')" />：
              </td>
              <td class="fsBlack" align="left">
                <s:select id="protocalId" name="terminalInfo.protocalId" list="protocalInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="onProtocalChange()" cssStyle="width:131px;">
				</s:select>
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right" valign="top">
                <s:property value="getText('file.choice.filepath')" />：
              </td>
              <td class="fsBlack" align="left" valign="top">
                <s:file label="choicefile" 
                        theme="simple" 
                        name="file" 
                        size="60"
                        onkeydown="return false;"
                        cssStyle="height: 17px;line-height: 17px;font-size: 12px;border: #006699 1px solid;padding-left: 2px;padding-top: 1px;"/>
                <a href="<s:url value='/template/TerminalInformation.xls' />"><s:property value="getText('file.template.download')" /></a>
              </td>
            </tr>
            <tr>
              <td height="40" align="right">
                <b>
                  <span class="noticeMsg"><s:property value="getText('import.notice')" />：</span>
                </b>
              </td>
              <td height="40" align="left">
                <b>
                  <span class="noticeMsg"><s:property value="getText('import.tip.info')" /></span>
                </b>
              </td>
            </tr>
          </table>
        </td>
      </tr>

      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('terminalmanage.shtml')">
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
