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
  <%@include file="terminalMonitor_validate.jsp"%>
  <s:form id="terminalmonitor_form" action="queryTerminalMonitor" method="post">
  
  <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
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
                                <s:property value="getText('terminal.monitor.info')" />
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
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="10%" scope="col">
                                    <s:property value="getText('common.list.vehicleln')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="15%" scope="col">
                                    <s:property value="getText('common.list.vehiclevin')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="15%" scope="col">
                                    <s:property value="getText('common.list.terminal')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="15%" scope="col">
                                    <s:property value="getText('common.list.simcardnumber')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="25%" scope="col">
                                    <s:property value="getText('common.list.enterprise')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="15%" scope="col">
                                    <s:property value="getText('common.list.user')" />
                                  </th>
                                  <!--  
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="10%" scope="col">
                                    <s:property value="getText('terminal.monitor.status')" />
                                  </th>
                                  -->
                                </tr>
                              </thead>
                              <tbody>
                                <s:iterator value="terminalList" status="rowstatus">
                                  <tr>
                                    <td>
                                      <s:property value="#rowstatus.index+1" /> 
                                    </td>
                                    <td>
                                      <s:url id="showDetail" action="queryTerminalParamsById">
							            <s:param name="terminalCode">${terminalCode}</s:param>
							            <s:param name="vehicleVin">${vehicleVin}</s:param>
							            <s:param name="simCardNumber">${simCardNumber}</s:param>
							            <s:param name="page">${page}</s:param>
							            <s:param name="pageSize">${pageSize}</s:param>
						              </s:url>
						              <a href="#" onclick="Wit.goBack('${showDetail}');">
						                <s:property value="vehicleLn" />
						              </a>
                                      </td>
                                    <td>
                                      <s:property value="vehicleVin" />
                                    </td>
                                    <td>
                                      <s:property value="terminalCode" />
                                    </td>
                                    <td>
                                      <s:property value="simCardNumber" />
                                    </td>
                                    <td>
                                      <s:property value="enterpriseName" />
                                    </td>
                                    <td>
                                      <s:property value="userName" />
                                    </td>
                                    <!--  
                                    <s:if test="\"4\"==gpsState || null==gpsState">
                                    <td style="color:green">
                                      <s:property value="getText('terminal.normal')" />
                                    </td>
                                    </s:if>
                                    <s:else>
                                    <td style="color:red">
                                      <s:property value="getText('terminal.unnormal')" />
                                    </td>
                                    </s:else>
                                    -->
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
  </s:form>
  
  <%@include file="../common/copyright.jsp"%>
</body>
</html>