<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title><s:property value="getText('menu.cl')" />&nbsp;|&nbsp;<s:property value="getText('tmmanage.terminal')" /></title>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="terminalManage_validate.jsp"%>
  <s:form id="terminal_form" action="queryterminal" method="post" onsubmit="return false;">
  <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellspacing="5" cellpadding="0">
          <s:if test="#session.perUrlList.contains('111_0_3_4_1')">
          <tr>
            <td class="reportOnline2">
              <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                  <td>
                    <table border="0" cellspacing="4" cellpadding="0" >
                      <tr>
                        <td>
                          <s:property value="getText('oem.name')" />：
                        </td>
                        <td>
                          <s:select id="oemId" name="terminalInfo.oemId" list="oemInfosMap" headerKey="" headerValue="%{getText('please.select')}" cssStyle="width:120px;" onchange="getTypeInfo()" >
				          </s:select>&nbsp;&nbsp;
                        </td>
                        <td>
                          <s:property value="getText('device.name')" />：
                        </td>
                        <td>
                          <s:select id="typeId" name="terminalInfo.typeId" list="typeInfosMap" headerKey="" headerValue="%{getText('please.select')}" cssStyle="width:120px;" onchange="getProtocalInfo()">
				          </s:select>&nbsp;&nbsp;
                        </td>
                        <td>
                          <s:property value="getText('protocal.name')" />：
                        </td>
                        <td>
                          <s:select id="protocalId" name="terminalInfo.protocalId" list="protocalInfosMap" headerKey="" headerValue="%{getText('please.select')}" cssStyle="width:120px;">
				          </s:select>&nbsp;&nbsp;
                        </td>
                         <td>
                          <s:property value="getText('terminal.code')" />：
                        </td>
                        <td>
                          <s:textfield id="terminalCode" name="terminalInfo.terminalCode" cssClass="input120"/>
                        </td>
                      </tr>
                      <tr>
                        <td>视频厂家：
                        </td>
                        <td>
                        <s:select id="videoFactory" name="terminalInfo.videoFactory" list="#{'HI':'海康','DA':'大华'}" headerKey="" headerValue="%{getText('please.select')}" cssStyle="width:120px;">
				        </s:select>
                        </td>
                        <td>视频地址：
                        </td>
                        <td>
                          <s:textfield id="videoServerIp" name="terminalInfo.videoServerIp" cssClass="input120"/>
                        </td>
                        <td>视频ID：
                        </td>
                        <td>
                          <s:textfield id="videoId" name="terminalInfo.videoId" cssClass="input120"/>
                        </td>
                        <td colspan="2">
                          <a href="#" onclick="submitQueryTerminal()" class="sbutton"><s:property value="getText('btn.query')" /></a>
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
                                <s:property value="getText('terminal.info')" />
                              </td>
                              <td width="69%"align="right">
                                <s:if test="#session.perUrlList.contains('111_0_3_4_2')">
                                <div class="buhuanhangbut">
                                  <a href="<s:url value='/zd/addTerminalBefore.shtml' />" class="btnAdd" title="<s:property value="getText('msg.add')" />">
                                  </a>
                                </div>
                                </s:if>
                                
                                <s:if test="#session.perUrlList.contains('111_0_3_4_6')">
                                <div class="buhuanhangbut">
                                  <a href="#" onclick="exportTerminal()" class="btnDaochu" title="<s:property value="getText('msg.export')" />">
                                  </a>
                                </div>
                                </s:if>
                                
                                <s:if test="#session.perUrlList.contains('111_0_3_4_5')">
                                <div class="buhuanhangbut">
                                  <a href="<s:url value='/zd/importTerminalBefore.shtml' />" class="btnInput" title="<s:property value="getText('msg.import')" />"></a>
                                </div>
								</s:if>
                                
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
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="15%" scope="col">
                                    <s:property value="getText('terminal.code')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="16%" scope="col">
                                    <s:property value="getText('device.oem.own')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="15%" scope="col">
                                    <s:property value="getText('protocal.device.own')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="15%" scope="col">
                                    <s:property value="getText('protocal.name')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="10%" scope="col">视频厂家
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="10%" scope="col">视频地址
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="14%" scope="col">视频ID
                                  </th>
                                </tr>
                              </thead>
                              <tbody>
                                <s:iterator value="terminalList" status="rowstatus">
                                <tr>
                                  <td>
                                    <s:property value="rowNumber" /> 
                                  </td>
                                  <td>
                                    <s:url id="showDetail" action="queryTerminalInfo">
							          <s:param name="terminalId" value="terminalId" />
							          <s:param name="page">${page}</s:param>
							          <s:param name="pageSize">${pageSize}</s:param>
						            </s:url>
						            <a href="#" onclick="Wit.goBack('${showDetail}');">
						              <s:property value="terminalCode" />
						            </a>
                                  </td>
                                  <td>
                                    <s:property value="oemName" />
                                  </td>
                                  <td>
                                    <s:property value="typeName" />
                                  </td>
                                  <td>
                                    <s:property value="protocalName" />
                                  </td>
                                  <td>
                                    <s:property value="videoFactory" />
                                  </td>
                                  <td>
                                    <s:property value="videoServerIp" />
                                  </td>
                                  <td>
                                    <s:property value="videoId" />
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
  </s:form>
  <%@include file="../common/copyright.jsp"%>
</body>
</html>