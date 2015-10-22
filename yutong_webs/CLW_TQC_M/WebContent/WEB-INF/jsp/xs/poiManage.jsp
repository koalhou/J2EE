<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title>
  <s:property value="getText('system.set.menu')" />&nbsp;|&nbsp;<s:property value="getText('poi.manage')" />
</title>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="poiManage_validate.jsp"%>
  <s:form id="poiinfo_form" action="queryPoiInfo" method="post" onsubmit="return false;">
  <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellspacing="5" cellpadding="0">
          <s:if test="#session.perUrlList.contains('111_0_6_2_1')">
          <tr>
            <td class="reportOnline2">
              <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                  <td>
                    <table border="0" cellspacing="4" cellpadding="0" >
                      <tr>
                        <td>
                          <s:property value="getText('poi.name')" />：
                          <s:textfield id="poiName" name="poiName" cssClass="input120"/>
                        </td>
                        <td>
                          <a href="#" onclick="submitPoiInfo()" class="sbutton">
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
                                <s:property value="getText('poi.info')" />
                              </td>
                              <td width="69%"align="right">
                                <s:if test="#session.perUrlList.contains('111_0_6_2_2')">
                                <div class="buhuanhangbut">
                                  <a href="<s:url value='/xs/addPoiInfoBefore.shtml' />" class="btnAdd" title="<s:property value="getText('msg.add')" />">
                                  </a>
                                </div>
                                </s:if>
                                
                                <s:if test="#session.perUrlList.contains('111_0_6_2_7')">
                                <div class="buhuanhangbut">
                                  <a href="<s:url value='/xs/importPoiBefore.shtml' />" class="btnInput" title="<s:property value="getText('msg.import')" />"></a>
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
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="20%" scope="col">
                                    <s:property value="getText('poi.code')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="20%" scope="col">
                                    <s:property value="getText('poi.name')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="20%" scope="col">
                                    <s:property value="getText('poi.type')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="20%" scope="col">
                                    <s:property value="getText('poi.level')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="15%" scope="col">
                                    <s:property value="getText('poi.status')" />
                                  </th>
                                </tr>
                              </thead>
                              <tbody>
                                <s:iterator value="poiList" status="rowstatus">
                                <tr>
                                  <td>
                                    <s:property value="#rowstatus.index+1" /> 
                                  </td>
                                  <td>
                                    <s:url id="showDetail" action="queryPoiInfoById">
							          <s:param name="poiId" value="poiId" />
							          <s:param name="page">${page}</s:param>
							          <s:param name="pageSize">${pageSize}</s:param>
						            </s:url>
						            <a href="#" onclick="Wit.goBack('${showDetail}');">
						              <s:property value="poiCode" />
						            </a>
                                  </td>
                                  <td>
                                    <s:property value="poiName"/>
                                  </td>
                                  <td>
                                    <s:property value="poiType" />
                                  </td>
                                  <td>
                                    <s:property value="poiLevel" />
                                  </td>
                                  
                                  <s:if test="\"2\"==validFlag">
                                  <td style="color:red">
                                    <s:property value="getText('poi.invalid')" />
                                  </td>
                                  </s:if>
                                  <s:else>
                                  <td style="color:green">
                                    <s:property value="getText('poi.useful')" />
                                  </td>
                                  </s:else>
                                  
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