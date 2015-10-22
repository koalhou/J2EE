<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title><s:property value="getText('menu.xs')"/>&nbsp;|&nbsp;<s:property value="getText('menu.xs.vehiclebaseinfo')"/>&nbsp;|&nbsp;<s:property value="getText('tmmanage.oem')" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="oemManage_validate.jsp"%>
  <s:form id="oem_form" action="queryOem" method="post" onsubmit="return false;">
  <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellspacing="5" cellpadding="0">
          <s:if test="#session.perUrlList.contains('111_0_3_1_1')">
          <tr>
            <td class="reportOnline2">
              <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                  <td>
                    <table border="0" cellspacing="4" cellpadding="0" >
                      <tr>
                        <td>
                          <s:property value="getText('oem.name')" />：
                          <s:textfield id="oemName" name="oemName" cssClass="input120"/>
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
                                <s:property value="getText('oem.info')" />
                              </td>
                              <td width="69%"align="right">
                                <s:if test="#session.perUrlList.contains('111_0_3_1_2')">
                                  <div class="buhuanhangbut">
                                    <a href="<s:url value='/zd/addOemBefore.shtml' />" class="btnAdd" title="<s:property value="getText('msg.add')" />">
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
                                    <s:property value="getText('oem.name')" />
                                  </th>
                                  <!--  
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="7%" scope="col">用户类型</th>
                                  -->
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="9%" scope="col">
                                    <s:property value="getText('oem.concate.person')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="23%" scope="col">
                                    <s:property value="getText('oem.concate.address')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="6%" scope="col">
                                    <s:property value="getText('oem.concate.cell')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="8%" scope="col">
                                    <s:property value="getText('oem.concate.tel')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="17%" scope="col">
                                    <s:property value="getText('oem.concate.email')" />
                                  </th>
                                </tr>
                              </thead>
                              <tbody>
                                <s:iterator value="oemList" status="rowstatus">
                                <tr>
                                  <td>
                                    <s:property value="rowNumber" /> 
                                  </td>
                                  <td>
                                    <s:url id="showDetail" action="queryOemInfo">
							          <s:param name="oemId" value="oemId" />
							          <s:param name="page">${page}</s:param>
							          <s:param name="pageSize">${pageSize}</s:param>
						            </s:url>
						            <a href="#" onclick="Wit.goBack('${showDetail}');">
						              <s:property value="fullName" />
						            </a>
                                  </td>
                                  <td>
                                    <s:property value="concatPerson" />
                                  </td>
                                  <td>
                                    <s:property value="concatAddress" />
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