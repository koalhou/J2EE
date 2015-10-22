<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title><s:property value="getText('menu.cl')" />&nbsp;|&nbsp;<s:property value="getText('sim.manage')" /></title>
</head>

<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="simManage_validate.jsp"%>
  <s:form id="sim_form" action="querySim" method="post">
  <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellspacing="5" cellpadding="0">
          <s:if test="#session.perUrlList.contains('111_0_4_2_1')">
          <tr>
            <td class="reportOnline2">
              <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                  <td>
                    <table border="0" cellspacing="4" cellpadding="0" >
                      <tr>
                        <td>
                          <s:property value="getText('sim.card.number')" />：
                          <s:textfield id="simNumber" name="simNumber" cssClass="input120"/>&nbsp;&nbsp;
                        </td>
                        <td>
                          <s:property value="getText('cell.number')" />：
                          <s:textfield id="phoneNumber" name="phoneNumber" cssClass="input120"/>
                        </td>
                        <td>
                          <a href="#" onclick="submitQuerySim()" class="sbutton"><s:property value="getText('btn.query')" /></a>
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
                                <s:property value="getText('sim.manage.list')" />
                              </td>
                              <td width="69%"align="right">
                                <s:if test="#session.perUrlList.contains('111_0_4_2_2')">
                                <div class="buhuanhangbut">
                                  <a href="<s:url value='/cs/addSimBefore.shtml' />" class="btnAdd" title="<s:property value="getText('msg.add')" />">
                                  </a>
                                </div>
                                </s:if>
                                
                                <s:if test="#session.perUrlList.contains('111_0_4_2_6')">
                                <div class="buhuanhangbut">
                                  <a href="#" onclick="exportSim()" class="btnDaochu" title="<s:property value="getText('msg.export')" />">
                                  </a>
                                </div>
                                </s:if>
                                
                                <s:if test="#session.perUrlList.contains('111_0_4_2_5')">
                                <div class="buhuanhangbut">
                                  <a href="<s:url value='/cs/importSimBefore.shtml' />" class="btnInput" title="<s:property value="getText('msg.import')" />"></a>
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
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="3%" scope="col">
                                    <s:property value="getText('list.number')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="15%" scope="col">
                                    <s:property value="getText('common.list.simcardnumber')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="10%" scope="col">
                                    <s:property value="getText('common.list.cellnumber')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="8%" scope="col">
                                    <s:property value="getText('common.list.business')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="8%" scope="col">
                                    <s:property value="getText('sim.card.apntype')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="10%" scope="col">
                                    <s:property value="getText('sim.card.startuse')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="8%" scope="col">
                                    <s:property value="getText('common.creater')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="15%" scope="col">
                                    <s:property value="getText('common.createtime')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="8%" scope="col">
                                    <s:property value="getText('common.modifier')" />
                                  </th>
                                  <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="15%" scope="col">
                                    <s:property value="getText('common.modifytime')" />
                                  </th>
                                </tr>
                              </thead>
                              <tbody>
                                <s:iterator value="simList" status="rowstatus">
                                <tr>
                                  <td>
                                    <s:property value="rowNumber" /> 
                                  </td>
                                  <td>
                                    <s:url id="showDetail" action="querySimInfo">
							          <s:param name="simId" value="simId" />
							          <s:param name="page">${page}</s:param>
							          <s:param name="pageSize">${pageSize}</s:param>
						            </s:url>
						            <a href="#" onclick="Wit.goBack('${showDetail}');">
						              <s:property value="simCardNumber" />
						            </a>
                                  </td>
                                  <td>
                                    <s:property value="cellPhone" />
                                  </td>
                                  <td>
                                    <s:property value="businessName" />
                                  </td>
                                   <td>
                                    <s:property value="apnType" />
                                  </td>
                                  <td>
                                    <s:property value="startUseTime" />
                                  </td>
                                  <td>
                                    <s:property value="creater" />
                                  </td>
                                  <td>
                                    <s:property value="createTime" />
                                  </td>
                                  <td>
                                    <s:property value="modifier" />
                                  </td>
                                  <td>
                                    <s:property value="modifyTime" />
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