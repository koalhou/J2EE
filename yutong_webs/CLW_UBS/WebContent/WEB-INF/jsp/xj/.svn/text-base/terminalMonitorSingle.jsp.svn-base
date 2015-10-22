<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title><s:property value="getText('system.monitor.menu')" />&nbsp;|&nbsp;<s:property value="getText('terminal.monitor')" /></title>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
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
  <%@include file="terminalMonitorSingle_validate.jsp"%>
  <s:form id="terminalmonitorsingle_form" action="queryterminalstatus" method="post">
  
  <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr valign="top">
      <td class="reportOnline5">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>
              <div class="reportTab">
                <s:url id="terminalauthentication" action="terminalauthentication"/>
                <ul>
                  <li>
                    <a href="#" id="current">
                      <s:property value="getText('terminal.monitor.status')" />
                    </a>
                  </li>
                  <li>
                    <a href="${terminalauthentication}">
                      <s:property value="getText('terminal.monitor.authentication')" />
                    </a>
                  </li>
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
                                <s:property value="getText('single.select.vehicle')" />：
                                <s:hidden id="vehicleVin" name="singleTerminalInfo.vehicleVin"/>
                                <s:textfield id="vehicleLn" 
                                             name="singleTerminalInfo.vehicleLn" 
                                             onclick="openVehicleBrowse()" 
                                             readonly="true"/>
                              </td>
                              <td>
                                <s:property value="getText('single.select.startdate')" />：
                                <s:textfield id="startDate" name="singleTerminalInfo.startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',autoPickDate:true})" cssClass="Wdate">
				                </s:textfield>
                              </td>
                              <td>
                                <s:property value="getText('single.select.minutevalue')" />：
                                <!--
                                <s:textfield id="minuteValue" name="singleTerminalInfo.minuteValue" cssClass="input120"/>
                                -->
                                <s:select id="minuteValue" 
                                          name="singleTerminalInfo.minuteValue" 
                                          list="#{5:'5分钟',10:'10分钟',30:'30分钟',60:'60分钟',120:'120分钟'}"
                                          headerKey="" 
                                          headerValue="%{getText('please.select')}"> 
		                        </s:select>
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
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="9%" scope="col">
                                          <s:property value="getText('single.time')" />
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
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="10%" scope="col">
                                          <s:property value="getText('single.videoid')" />
                                        </th>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="12%" scope="col">
                                          <s:property value="getText('common.list.enterprise')" />
                                        </th>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="8%" scope="col">
                                          <s:property value="getText('terminal.monitor.status')" />
                                        </th>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="8%" scope="col">
                                          <s:property value="getText('single.gps')" />
                                        </th>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="8%" scope="col">
                                          <s:property value="getText('single.power')" />
                                        </th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <s:iterator value="terminalList" status="rowstatus">
                                      <tr>
                                        <td>
                                          <s:property value="#rowstatus.index+1" /> 
                                        </td>
                                        <td>
						                  <s:property value="monitorTime" />
                                        </td>
                                        <td>
						                  <s:property value="vehicleLn" />
                                        </td>
                                        <td>
                                          <s:property value="vehicleVin" />
                                        </td>
                                        <td>
                                          <s:property value="terminalCode" />
                                        </td>
                                        <td>
                                          <s:property value="videoId" />
                                        </td>
                                        <td>
                                          <s:property value="enterpriseName" />
                                        </td>
                                        
                                        <s:if test="\"0\"==terminalStatus">
                                        <td>
                                          <img src="<s:url value='/images/hongdeng.gif'/>"/>
                                        </td>
                                        </s:if>
                                        <s:else>
                                        <td>
                                          <img src="<s:url value='/images/lvdeng.gif'/>"/>
                                        </td>
                                        </s:else>
                                        
                                        <s:if test="\"4\"==gpsStatus">
                                        <td style="color:green">
                                          <s:property value="gpsStatusName" />
                                        </td>
                                        </s:if>
                                        <s:else>
                                        <td style="color:red">
                                          <s:property value="gpsStatusName" />
                                        </td>
                                        </s:else>
                                        
                                        <td style="color:green">
                                          <s:property value="powerStatus" />
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
      </td>
    </tr>
  </table>
  </s:form>
  
  <%@include file="../common/copyright.jsp"%>
</body>
</html>