<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title>
  <s:property value="getText('system.set.menu')" />&nbsp;|&nbsp;<s:property value="getText('xs.bm.menu')" />&nbsp;|&nbsp;<s:property value="getText('base.info.name')" />
</title>

</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="baseInfoManage_validate.jsp"%>
  <s:form id="baseinfo_form" action="queryBaseInfo" method="post">
  <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellspacing="5" cellpadding="0">
          <s:if test="#session.perUrlList.contains('111_0_6_1_2_1')">
          <tr>
            <td class="reportOnline2">
              <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                  <td>
                    <table border="0" cellspacing="4" cellpadding="0" >
                      <tr>
                        <td>
                          <s:property value="getText('base.info.type')" />：
                          <s:select id="codeType" 
                                    name="codeType"
                                    list="typeList" 
                                    listKey="codeId" 
                                    listValue="codeName" 
                                    headerKey="" 
                                    headerValue="%{getText('select.all')}" >
                          </s:select>&nbsp;&nbsp;
                        </td>
                        <td>
                          <s:property value="getText('base.info.codename')" />：
                          <s:textfield id="codeNameQuery" name="codeNameQuery" cssClass="input120"/>
                        </td>
                        <td>
                          <a href="#" onclick="submitBaseInfo()" class="sbutton"><s:property value="getText('btn.query')" /></a>
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
                                <s:property value="codeName" />
                              </td>
                              <td width="69%"align="right">
                                <s:if test="#session.perUrlList.contains('111_0_6_1_2_2') && codeType!=\"\"">
                                <div class="buhuanhangbut">
                                  <s:url id="addBaseInfoBefore" action="addBaseInfoBefore">
							        <s:param name="codeType"><s:property value="codeType"/></s:param>
						          </s:url>
                                  <a href="#" class="btnAdd" title="<s:property value="getText('msg.add')" />" onclick="Wit.goBack('${addBaseInfoBefore}');">
                                  </a>
                                </div>
                                
                                <div class="buhuanhangbut">
                                  <s:url id="importBaseInfoBefore" action="importBaseInfoBefore">
							        <s:param name="codeType"><s:property value="codeType"/></s:param>
						          </s:url>
                                  <a href="#" class="btnInput" title="<s:property value="getText('msg.import')" />" onclick="Wit.goBack('${importBaseInfoBefore}');"></a>
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
                                    <s:property value="getText('base.info.type')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="20%" scope="col">
                                    <s:property value="getText('base.info.code')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="20%" scope="col">
                                    <s:property value="getText('base.info.codename')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="10%" scope="col">
                                    <s:property value="getText('base.info.level')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="25%" scope="col">
                                    <s:property value="getText('base.info.parent')" />
                                  </th>
                                </tr>
                              </thead>
                              <tbody>
                                <s:iterator value="baseInfoList" status="rowstatus">
                                <tr>
                                  <td>
                                    <s:property value="#rowstatus.index+1" /> 
                                  </td>
                                  <td>
                                    <s:url id="showDetail" action="queryBaseInfoById">
							          <s:param name="defId" value="defId" />
							          <s:param name="page">${page}</s:param>
							          <s:param name="pageSize">${pageSize}</s:param>
						            </s:url>
						            <a href="#" onclick="Wit.goBack('${showDetail}');">
						              <s:property value="codeTypeName" />
						            </a>
                                  </td>
                                  <td>
                                    <s:property value="codeId"/>
                                  </td>
                                  <td>
                                    <s:property value="codeName" />
                                  </td>
                                  <td>
                                    <s:property value="codeLevel" />
                                  </td>
                                  <td>
                                    <s:property value="parentName" />
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