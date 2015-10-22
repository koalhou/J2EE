<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title><s:property value="getText('menu.xs')"/>&nbsp;|&nbsp;<s:property value="getText('menu.xs.vehiclebaseinfo')"/>&nbsp;|&nbsp;<s:property value="getText('business.manage')" /></title>
</head>

<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="businessManage_validate.jsp"%>
  <s:form id="business_form" action="queryBusiness" method="post" onsubmit="return false;">
  <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellspacing="5" cellpadding="0">
          <s:if test="#session.perUrlList.contains('111_0_4_1_1')">
          <tr>
            <td class="reportOnline2">
              <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                  <td>
                    <table border="0" cellspacing="4" cellpadding="0" >
                      <tr>
                        <td>
                          <s:property value="getText('business.name')" />：
                          <s:textfield id="businessName" name="businessName" cssClass="input120"/>
                        </td>
                        <td>
                          <a href="#" onclick="submit()" class="sbutton"><s:property value="getText('btn.query')" /></a>
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
                                <s:property value="getText('transfer.business.list')" />
                              </td>
                              <td width="69%"align="right">
                                <s:if test="#session.perUrlList.contains('111_0_4_1_2')">
                                <div class="buhuanhangbut">
                                  <a href="<s:url value='/cs/addBusinessBefore.shtml' />" class="btnAdd" title="<s:property value="getText('msg.add')" />">
                                  </a>
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
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="7%" scope="col">
                                    <s:property value="getText('business.name')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="9%" scope="col">
                                    <s:property value="getText('business.concate.person')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="23%" scope="col">
                                    <s:property value="getText('business.concate.address')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="6%" scope="col">
                                    <s:property value="getText('business.concate.cell')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="8%" scope="col">
                                    <s:property value="getText('business.concate.tel')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="17%" scope="col">
                                    <s:property value="getText('business.concate.email')" />
                                  </th>
                                </tr>
                              </thead>
                              <tbody>
                                <s:iterator value="businessList" status="rowstatus">
                                <tr>
                                  <td>
                                    <s:property value="rowNumber" /> 
                                  </td>
                                  <td>
                                    <s:url id="showDetail" action="queryBusinessInfo">
							          <s:param name="businessId" value="businessId" />
							          <s:param name="page">${page}</s:param>
							          <s:param name="pageSize">${pageSize}</s:param>
						            </s:url>
						            <a href="#" onclick="Wit.goBack('${showDetail}');">
						              <s:property value="fullName" />
						            </a>
                                  </td>
                                  <td>
                                    <s:property value="concatePerson" />
                                  </td>
                                  <td>
                                    <s:property value="address" />
                                  </td>
                                  <td>
                                    <s:property value="cellPhone" />
                                  </td>
                                  <td>
                                    <s:property value="tel" />
                                  </td>
                                  <td>
                                    <s:property value="email" />
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