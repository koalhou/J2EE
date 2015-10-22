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
  <%@include file="terminalMonitorInfo_validate.jsp"%>
  <s:form id="terminalmonitorinfo_form" method="post">
    <s:hidden id="page" name="page" />
    <s:hidden id="pageSize" name="pageSize" />
    <s:hidden id="terminalCode" name="terminalCode"/>
    <s:hidden id="vehicleVin" name="vehicleVin"/>
    <s:hidden id="simCardNumber" name="simCardNumber"/>
    
    <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="top">
              
          <div id="message">
            <s:actionerror theme="mat" />
            <s:fielderror theme="mat"/>
            <s:actionmessage theme="mat"/>
          </div>
    
          <table class="msgBox2" width="650" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>" align="left">
                <span class="msgTitle">&nbsp;&nbsp;
                  <s:property value="getText('terminal.params.detail')" />
                </span>
              </td>
            </tr>
            <tr>
              <td align="center">
		        <table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('common.list.terminal')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.terminalCode" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('terminal.params.updatetime')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.updateTime" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('terminal.params.messagecenter')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.messageCenter" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('terminal.params.apn')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.apn" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('terminal.params.serverip')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.serverIp" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('terminal.params.port')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.serverPort" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('terminal.params.receivertime')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.receiverTime" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('terminal.params.timeanswers')" />(s)：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.timeAnswers" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('terminal.params.spacinganswers')" />(m)：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.spacingAnswers" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('terminal.params.keepalivetime')" />(s)：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.keepAliveTime" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('terminal.params.keepaliveovertime')" />(s)：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.keepAliveOverTime" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('terminal.params.stalledtime')" />(s)：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.stalledTimeAnswers" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('terminal.params.overspeed')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.overSpeed" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('terminal.params.overspeeddiff')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.overSpeedDiff" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('terminal.params.overspeedkeep')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.overSpeedKeep" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('terminal.params.drivingfatigue')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.drivingFatigue" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('terminal.params.drivingfatiguediff')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.drivingFatigueDiff" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('terminal.params.drivingfatiguerest')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.drivingFatigueRest" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('terminal.params.indexproperty')" />(r/km)：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.indexProperty" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('terminal.params.pulsepersecond')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.pulsePerSecond" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('terminal.params.enginegear')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.engineGear" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('common.list.vehicleln')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.vehicleLn" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('terminal.params.vehicleno')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.vehicleNo" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('terminal.params.vehiclesort')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.vehicleSort" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('common.list.vehiclevin')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.vehicleVin" />
                    </td>
                    <td>&nbsp;</td>
                    <td width="25%" height="28" align="right">
                      <s:property value="getText('terminal.params.sleeptime')" />：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:property value="terminalParamsInfo.sleepTime" />
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                </table>
              </td>
            </tr>
            
            <tr>
              <td class="btnBar">
                <s:if test="#session.perUrlList.contains('111_0_5_4_2')">
                  <s:url id="getTerminalParamsByCode" action="getTerminalParamsByCode">
                    <s:param name="terminalCode">${terminalCode}</s:param>
                    <s:param name="vehicleVin">${vehicleVin}</s:param>
                    <s:param name="simCardNumber">${simCardNumber}</s:param>
                    <s:param name="page">${page}</s:param>
                    <s:param name="pageSize">${pageSize}</s:param>
                  </s:url>
                  <a href="#" class="buttontwo" onclick="queryTerminalParams('${getTerminalParamsByCode}')">
                    <s:property value="getText('params.get.recent')" />
                  </a>
                </s:if>
                
                <s:url id="showTerminalParamsByCode" action="showTerminalParamsByCode">
                  <s:param name="terminalCode">${terminalCode}</s:param>
                  <s:param name="vehicleVin">${vehicleVin}</s:param>
                  <s:param name="simCardNumber">${simCardNumber}</s:param>
                  <s:param name="page">${page}</s:param>
                  <s:param name="pageSize">${pageSize}</s:param>
                </s:url>
                <a href="#" class="buttontwo" onclick="showTerminalParams('${showTerminalParamsByCode}')">
                  <s:property value="getText('params.show')" />
                </a>
                      
              </td>
            </tr>
            
                  
            <s:if test="#session.perUrlList.contains('111_0_5_4_2')">
            <tr>
              <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>" align="left">
                <span class="msgTitle">&nbsp;&nbsp;
                  <s:property value="getText('terminal.params.set')" />
                </span>
              </td>
            </tr>
                  
            <tr>
              <td align="center">
		        <table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
                    <td width="20%" height="28" align="right">
                      <s:property value="getText('terminal.params.indexproperty')" />(r/km)：
                    </td>
                    <td width="20%" class="fsBlack" align="left">
                      <s:textfield id="indexProperty" name="terminalParamsInfo.indexProperty" maxLength="5"/>
                    </td>
                    <td>
                      <a href="#" class="sbutton" onclick="setIndexProperty()">
                        <s:property value="getText('params.set')" />
                      </a>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>  
            </s:if>
                    
            <tr>
              <td class="btnBar">
                <a href="#" class="buttontwo" onclick="goBack('terminalmonitor.shtml')">
                  <s:property value="getText('btn.back')" />
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
