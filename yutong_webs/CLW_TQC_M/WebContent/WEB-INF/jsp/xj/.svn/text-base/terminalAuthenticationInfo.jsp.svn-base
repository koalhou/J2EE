<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('system.monitor.menu')" />&nbsp;|&nbsp;<s:property value="getText('terminal.monitor')" />
</title>
<%@include file="../common/meta.jsp" %>
<style type="text/css">
  .reportOnline5{ 
    background: #fff; 
    border-bottom: 1px solid #c3c3c3; 
    border-left: 1px solid #c3c3c3; 
    border-right: 1px solid #c3c3c3;
  }
</style>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="terminalAuthenticationInfo_validate.jsp"%>
  <s:form id="terminalauthenticationinfo_form" action="deleteAuthentication" method="post">
    <s:hidden id="page" name="page" />
    <s:hidden id="pageSize" name="pageSize" />
    <s:hidden id="terminalAnthenticationId" name="terminalAnthenticationId"/>
    
    <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr valign="top">
        <td class="reportOnline5">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>
                <div class="reportTab">
                  <s:url id="terminalmonitor" action="terminalmonitor"/>
                  <ul>
                    <li>
                      <a href="${terminalmonitor}">
                        <s:property value="getText('terminal.monitor.realtime')" />
                      </a>
                    </li>
                    <li>
                      <a href="#" id="current">
                        <s:property value="getText('terminal.monitor.authentication')" />
                      </a>
                    </li>
                  </ul>
                </div>
              </td>
            </tr>
            <tr>
              <td align="center" valign="top" >
                <table class="msgBox2" width="650" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>" align="left">
                      <span class="msgTitle">&nbsp;&nbsp;
                        <s:property value="getText('terminal.authentication.detail')" />
                      </span>
                    </td>
                  </tr>
                  <tr>
                    <td align="center">
		              <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                <tr>
                          <td width="14%" height="28" align="right">
                            <s:property value="getText('common.list.vehiclevin')" />：
                          </td>
                          <td width="20%" class="fsBlack" align="left">
                            <s:property value="terminalAuthenticationInfo.vehicleVin" />
                          </td>
                        </tr>
                        <tr>
                          <td width="14%" height="28" align="right">
                            <s:property value="getText('common.list.terminal')" />：
                          </td>
                          <td width="20%" class="fsBlack" align="left">
                            <s:property value="terminalAuthenticationInfo.terminalCode" />
                          </td>
                        </tr>
                        <tr>
                          <td width="14%" height="28" align="right">
                            <s:property value="getText('common.list.simcardnumber')" />：
                          </td>
                          <td width="20%" class="fsBlack" align="left">
                            <s:property value="terminalAuthenticationInfo.simCardNumber" />
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                  <tr>
                    <td class="btnBar"> 
                      <a href="#" class="buttontwo" onclick="goBack('terminalauthentication.shtml')">
                        <s:property value="getText('btn.back')" />
                      </a>
                      
                      <s:if test="#session.perUrlList.contains('111_0_5_1_3')">
                        <a href="#" class="buttontwo" onclick="submitForm()">
                          <s:property value="getText('authentication.done')" />
                        </a>
                      </s:if>
                    </td>
                  </tr>
                </table>
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
