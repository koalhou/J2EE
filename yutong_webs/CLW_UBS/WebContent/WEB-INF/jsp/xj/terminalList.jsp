<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<title>
  <s:property value="getText('system.monitor.menu')" />&nbsp;|&nbsp;<s:property value="getText('menu.xj.param')" />
</title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
</head>
<body>
<%@include file="../common/menu.jsp"%>
<%@include file="terminalList_validate.jsp"%>
<s:form id="terminalmonitor_form" action="queryTerminalMonitor" method="post">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellspacing="5" cellpadding="0">
          <s:if test="#session.perUrlList.contains('111_0_5_4_1')">
          <tr>
            <td class="reportOnline2">
              <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                  <td>
                    <table border="0" cellspacing="4" cellpadding="0" >
                      <tr>
                        <td>
                          <s:property value="getText('vehicle.vin.number')" />：
                          <s:textfield id="vehicleVin" name="vehicleVin" cssClass="input120"/>&nbsp;&nbsp;
                        </td>
                        <td>
                          <s:property value="getText('terminal.code')" />：
                          <s:textfield id="terminalCode" name="terminalCode" cssClass="input120"/>&nbsp;&nbsp;
                        </td>
                        <td>
                          <s:property value="getText('sim.card.number')" />：
                          <s:textfield id="simCardNumber" name="simCardNumber" cssClass="input120"/>
                        </td>
                        <td>
                          <a href="#" onclick="searchList()" class="sbutton">
                            <s:property value="getText('btn.query')" />
                          </a>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          </s:if>
                
          <tr>
            <td valign="top">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td class="reportOnline">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="titleStyle">
                          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td width="30%" class="titleStyleZi">注册终端信息表                                
                              </td>
                              <td width="69%"align="right">
                              </td>
                              <td width="1%">&nbsp;</td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <tr>
                        <td class="reportInline">
                          <div id="message">
                            <div id="empDiv">
                              <s:actionerror theme="mat" />
                              <s:fielderror theme="mat"/>
                              <s:actionmessage theme="mat"/>
                            </div>
                          </div>
                          <div>
                            <table id="terminalList" width="100%" cellspacing="0">
							</table>                                  
                          </div>
                        </td>
                      </tr>
                    </table>
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