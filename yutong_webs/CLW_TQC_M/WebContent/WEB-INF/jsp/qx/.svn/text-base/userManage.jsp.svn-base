<%@include file="../common/taglibs.jsp" %>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title><s:property value="getText('usermanage.menu')" />&nbsp;|&nbsp;<s:property value="getText('usermanage.name')" /></title>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="userManage_validate.jsp"%>
  <s:form id="user_form" method="post">
  <input type="hidden"  id="UrlList" name="UrlList"
	value="<s:property value="#session.perUrlList.contains('111_0_1_3_5')"/>" />
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
                          <s:property value="getText('user.name')" />：
                          <s:textfield id="userName" name="userName" cssClass="input120"/>&nbsp;&nbsp;
                          
                          <s:property value="getText('common.list.enterprise')" />：
				          <s:hidden id="entipriseId" name="enterpriseId"/>
                          <s:textfield id="entipriseName" 
                                       name="entipriseName"
                                       onclick="openEnterpriseBrowse()" 
                                       readonly="true"
                                       cssClass="input120"/>&nbsp;&nbsp;
                          <s:property value="getText('common.list.role')" />：
                          <s:hidden id="roleId" name="roleId"/>
                          <s:textfield id="roleName" 
                                       name="roleName"
                                       onclick="openRoleBrowse()" 
                                       readonly="true"
                                       cssClass="input120"/>&nbsp;&nbsp;
                          <s:property value="getText('user.type')" />：
                          <s:select name="sysId" id="sysId" 
                                    list="subSysMap" 
                                    headerKey="" 
                                    headerValue="%{getText('select.all')}">
                          </s:select> &nbsp;&nbsp;
                          <s:property value="getText('common.status')" />：
                          <select name="validFlag" id="validFlag">
                           	<option value="" <s:if test="validFlag==0">selected</s:if>><s:property value="getText('select.all')"/></option>
                           	<option value="0" <s:if test="validFlag==\"0\"">selected</s:if>><s:property value="getText('user.normal')"/></option>
                           	<option value="2" <s:if test="validFlag==\"2\"">selected</s:if>><s:property value="getText('user.forbid')"/></option>
                          </select>&nbsp;&nbsp;
                          
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
                                <s:property value="getText('user.info')" />
                              </td>
                              <td width="69%"align="right">
                              <s:if test="#session.perUrlList.contains('111_0_1_3_2')">
                                <div class="buhuanhangbut">
                                  <a href="<s:url value='/qx/adduser.shtml' />" class="btnAdd" title="<s:property value="getText('msg.add')" />">
                                  </a>
                                </div>
                              </s:if>
                              <s:if test="#session.perUrlList.contains('111_0_1_3_8')">
                                <div class="buhuanhangbut">
                                  <a href="<s:url value='/qx/authorizationBefore.shtml' />" class="btnShouquan" title="<s:property value="getText('msg.authorization')" />">
                                  </a>
                                </div>
                              </s:if>
                             
							 <s:if test="#session.perUrlList.contains('111_0_1_3_9')">
                                <div class="buhuanhangbut">
                                  <a href="#" onclick="exportUserInfo()" class="btnDaochu" title="<s:property value="getText('msg.export')" />">
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
  <%@include file="../common/copyright.jsp"%>
</body>
</html>