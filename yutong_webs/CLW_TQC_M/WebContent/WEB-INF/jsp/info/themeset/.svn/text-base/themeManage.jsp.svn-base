<%@include file="../../common/taglibs.jsp" %>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="../../common/meta.jsp" %>
<title>系统设置&nbsp;|&nbsp;用户界面设置</title>
</head>
<body>
  <%@include file="../../common/menu.jsp"%>
  <%@include file="themeManage_validate.jsp"%>
  <s:form id="user_form" method="post">
  <input type="hidden"  id="UrlList" name="UrlList" value="<s:property value="#session.perUrlList.contains('111_0_1_3_5')"/>" />
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <s:if test="#session.perUrlList.contains('111_0_1_3_1')">
          <tr>
            <td class="reportOnline2">
              <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                  <td>
                  
                    <table border="0" cellspacing="4" cellpadding="0" >
                      <tr>
                        <td>
                          登录名：
                          <s:textfield id="loginName" name="loginName" cssClass="input120"/>&nbsp;&nbsp;
                          
                          企业编码：
				          <s:hidden id="entipriseId" name="enterpriseId"/>
                          <s:textfield id="entipriseName" 
                                       name="entipriseName"
                                       onclick="openEnterpriseBrowse()" 
                                       readonly="true"
                                       cssClass="input120"/>&nbsp;&nbsp;
                        </td>
                        <td>
                          <a href="#" onclick="searchList()" class="sbutton"><s:property value="getText('btn.query')" /></a>
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
                               用户主题列表
                              </td>
                              <td width="69%" align="right">
                              <s:if test="#session.perUrlList.contains('111_0_1_3_2')">
                                <div class="buhuanhangbut">
                                  <a href="<s:url value='/paramset/addthemeset.shtml' />" class="btnAdd" title="<s:property value="getText('msg.add')" />">
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
                          <div>
                              <div id="message">
                                  <div id="empDiv">
                                    <s:actionerror theme="mat" /><s:fielderror theme="mat"/><s:actionmessage theme="mat"/>
                                  </div>
                              </div>
                              <table id="userMangeList" width="100%"  cellspacing="0">
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
  <%@include file="../../common/copyright.jsp"%>
</body>
</html>