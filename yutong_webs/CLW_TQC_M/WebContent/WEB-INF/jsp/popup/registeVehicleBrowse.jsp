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
<title><s:property value="getText('vehicle.popup.menu')" /></title>
</head>
<body>
  <%@include file="registeVehicleBrowse_validate.jsp"%>
  
  <s:form id="registevehiclebrowse_form" action="queryRegisteVehicle" method="post">
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
                          <s:property value="getText('vehicle.vin')" />：
                          <s:textfield id="vehicleVin" name="vehicleVin" cssClass="input120"/>
                        </td>
                        <td>
                          <s:property value="getText('vehicle.ln')" />：
                          <s:textfield id="vehicleLn" name="vehicleLn" cssClass="input120"/>
                        </td>
                        <td>
                          <a href="#" onclick="submit()" class="sbutton">
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
                                <s:property value="getText('vehicle.popup.info')" />
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
							      <th scope="col" width="45%">
							        <s:property value="getText('common.list.vehiclevin')" />
							      </th>
							      <th scope="col" width="45%">
							        <s:property value="getText('common.list.vehicleln')" />
							      </th>
                                </tr>
                              </thead>
                              <tbody style="overflow:auto;">
                                <s:iterator value="vehicleList" status="rowstatus">
                                  <tr>
                                    <td>
                                      <s:property value="#rowstatus.index+1" />
                                    </td>
                                    <td>
								      <a href="#" 
								         onclick="operate('<s:property value="vehicleVin"/>','<s:property value="vehicleLn" />');" 
								         title="<s:property value="getText('vehicle.popup.tip')" />"> 
                                        <s:property value="vehicleVin" />
                                      </a>
                                    </td>
                                    <td>
                                      <s:property value="vehicleLn" />
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

