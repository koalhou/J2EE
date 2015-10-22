<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title><s:property value="getText('system.monitor.menu')" />&nbsp;|&nbsp;<s:property value="getText('terminal.monitor')" /></title>
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
  <%@include file="terminalAuthentication_validate.jsp"%>
  <s:form id="terminalauthentication_form" action="queryTerminalAuthentication" method="post">
  
  <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr valign="top">
      <td class="reportOnline5">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>
              <div class="reportTab">
                <s:url id="terminalmonitor" action="singleterminalmonitor"/>
                <ul>
                  <li><a href="${terminalmonitor}"><s:property value="getText('terminal.monitor.status')" /></a></li>
                  <li><a href="#" id="current"><s:property value="getText('terminal.monitor.authentication')" /></a></li>
                </ul>
              </div>
            </td>
          </tr>
          <tr>
            <td class="reportInline">
              <table width="100%" border="0" cellspacing="5" cellpadding="0">
                <s:if test="#session.perUrlList.contains('111_0_5_1_1')">
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
                                <a href="#" onclick="queryRealTerminalStatus()" class="sbutton">
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
                                    <td width="30%" class="titleStyleZi">
                                      <s:property value="getText('terminal.authentication.info')" />
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
                                          <s:actionerror theme="mat" />
                                          <s:fielderror theme="mat"/>
                                          <s:actionmessage theme="mat"/>
                                        </div>
                                <div id="list">
                                  <table width="100%" cellspacing="0" class="reportInline2" >
                                    <thead>
                                      <tr>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="5%" scope="col">
                                          <s:property value="getText('list.number')" />
                                        </th>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="25%" scope="col">
                                          <s:property value="getText('common.list.vehiclevin')" />
                                        </th>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="20%" scope="col">
                                          <s:property value="getText('common.list.terminal')" />
                                        </th>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="20%" scope="col">
                                          <s:property value="getText('common.list.simcardnumber')" />
                                        </th>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="30%" scope="col">终端上报时间
                                        </th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <s:iterator value="authenticationList" status="rowstatus">
                                      <tr>
                                        <td>
                                          <s:property value="#rowstatus.index+1" /> 
                                        </td>
                                        <td>
                                          <s:url id="showDetail" action="queryAuthenticationById">
							                <s:param name="terminalAnthenticationId" value="terminalAnthenticationId" />
							                <s:param name="page">${page}</s:param>
							                <s:param name="pageSize">${pageSize}</s:param>
						                  </s:url>
						                  <a href="#" onclick="Wit.goBack('${showDetail}');">
						                    <s:property value="vehicleVin" />
						                  </a>
                                        </td>
                                        <td>
                                          <s:property value="terminalCode" />
                                        </td>
                                        <td>
                                          <s:property value="simCardNumber" />
                                        </td>
                                        <td>
                                          <s:property value="createTime" />
                                        </td>
                                      </tr>
                                      </s:iterator>
                                    </tbody>
                                  </table>
                                  
                                  <div id="pageCtrl" align="right">
                                    <s:property value="pageBar" escape="false" />
                                  </div>
                                  
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
      </td>
    </tr>
  </table>
  </s:form>
  
  <%@include file="../common/copyright.jsp"%>
</body>
</html>