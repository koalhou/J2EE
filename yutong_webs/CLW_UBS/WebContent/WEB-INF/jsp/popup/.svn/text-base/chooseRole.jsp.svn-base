<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0"> 
<base target='_self'>
<title><s:property value="getText('role.popup.menu')" /></title>
</head>
<body>
  <%@include file="roleBrowse_validate.jsp"%>
  
  <s:form id="rolebrowse_form" action="queryChooseRole" method="post" onsubmit="return false;">
  <table height="668px" width="700px" border="0" cellspacing="0" cellpadding="0" align="center" />
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellspacing="5" cellpadding="0">
          <tr>
            <td class="reportOnline2">
              <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                  <td>
                    <table border="0" cellspacing="4" cellpadding="0">
                      <tr>
                        <td>
                          <s:property value="getText('rolemanage.role_name')" />：
                          <s:textfield id="roleName" name="roleName" cssClass="input120"/>&nbsp;&nbsp;
                       	  <s:property value="getText('rolemanage.apply_id')" />：
                           <select name="apply_id" id="apply_id">
                           	<option value="" <s:if test="apply_id==0">selected</s:if>><s:property value="getText('select.all')"/></option>
                           	<option value="0" <s:if test="apply_id==\"0\"">selected</s:if>><s:property value="getText('apply_id_0')"/></option>
                           	<option value="1" <s:if test="apply_id==\"1\"">selected</s:if>><s:property value="getText('apply_id_1')"/></option>
                           </select>
                        </td>
                        
                        <td>
                          <a href="#" onclick="submit()" class="sbutton">
                            <s:property value="getText('btn.query')" />
                          </a>
                        </td>
                        <td>
                          <a href="#" onclick="cancelSelect()" class="sbutton">
                            <s:property value="getText('btn.cancel.select')" /> 
                          </a>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          
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
                                <s:property value="getText('role.popup.info')" />
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
                          <div id="list">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td align="right" bgcolor="#F6F6F6">
                                  <div id="message">
                                    <s:actionerror theme="mat" />
                                    <s:fielderror theme="mat"/>
                                    <s:actionmessage theme="mat"/>
                                  </div>
                                </td>
                              </tr>
                            </table>
                            <table width="100%" cellspacing="0" class="reportInline2" >
                              <thead>
                                <tr>
                                  <th scope="col" width="10%">
                                    <s:property value="getText('list.number')" />
                                  </th>
							      <th scope="col" width="40%">
							        <s:property value="getText('rolemanage.role_name')" />
							      </th>
							      <th scope="col" width="30%">
							      	<s:property value="getText('rolemanage.apply_id')" />
							      </th>
                                </tr>
                              </thead>
                              <tbody style="overflow:auto;">
                                <s:iterator value="roleList" status="rowstatus">
                                  <tr>
                                    <td>
                                      <s:property value="#rowstatus.index+1" />
                                    </td>
                                    <td>
								      <a href="#" 
								         onclick="operate('<s:property value="roleId"/>','<s:property value="roleName" />');" 
								         title="<s:property value="getText('role.popup.tip')" />"> 
                                        <s:property value="roleName" />
                                      </a>
                                    </td>
                                    <td class="tdClass">
										<s:if test="apply_id==0">
										<s:property value="getText('apply_id_0')"/>
										</s:if>
										<s:elseif test="apply_id==1">
										<s:property value="getText('apply_id_1')"/>
										</s:elseif>
										<s:elseif test="apply_id==2">
										<s:property value="getText('apply_id_2')"/>
										</s:elseif>
									</td>
                                  </tr>
                                </s:iterator>
                              </tbody>
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
  
  <table width="700px" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="footer"><s:property value="getText('copyright.info')" /> &copy; <s:property value="getText('copyright.company')" /> 2011</td>
    </tr>
  </table>
  </s:form>
  
</body>
</html>

