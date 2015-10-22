<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<title>
  <s:property value="getText('system.monitor.menu')" />&nbsp;|&nbsp;<s:property value="getText('menu.xj.monitorupdate')" />
</title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
</head>
<body>
<%@include file="../common/menu.jsp"%>
<%@include file="terminalRomoteUpdate_validate.jsp"%>
<s:form id="terminalRomote_form" action="terminalRomoteInsert" method="post">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellspacing="5" cellpadding="0">                
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
                              <td width="30%" class="titleStyleZi">待升级车辆信息                              
                              </td>                                    
                               <td  align="right" bordercolor="none">                                                              
                                  <a href="#" onclick="choiseVehicle()" class="btnAdd" title="选择车辆">
                                  </a>
                               </td>
                              <td width="1%">&nbsp;</td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <tr>
                        <td class="reportInline">
                          <div id="message">
                            <div id="empDiv">
                              <s:actionerror theme="mat" />
                              <s:fielderror theme="mat"/>
                              <s:actionmessage theme="mat"/>
                            </div>
                          </div>
                          <div>
                            <table id="terminalList" width="100%" cellspacing="0">
							</table> 
							 <table width="100%" border="0" cellspacing="8" cellpadding="0">
                    <tr>
                      <td>
                      <table border="0" cellspacing="8" cellpadding="0">                       
                        <tr align="right">
                          <td  width="8%">硬件版本：
                          </td>
                          <td width="17%" align="left">
                              <s:textfield id="hardver" name="terminalParamsInfo.hardver" cssClass="input120" maxlength="30"   />            
                          </td>
                          <td  width="8%">固件版本：
                          </td>
                          <td width="17%" align="left">
                              <s:textfield id="firmver" name="terminalParamsInfo.firmver" cssClass="input120" maxlength="30"   />
                          </td>
                          <td  width="8%">连接时限：
                          </td>
                          <td width="17%" align="left">
                              <s:textfield id="timePer" name="terminalParamsInfo.timePer" cssClass="input120" maxlength="30"  />        
                          </td>
                          <td  width="8%">URL地址：
                          </td>
                          <td width="17%" align="left">
                              <s:textfield id="url" name="terminalParamsInfo.url" cssClass="input120" maxlength="200"  />
                          </td>                       
                        </tr>
                        <tr align="right" align="left">
                          <td>拨号点名称：
                          </td>
                          <td align="left">
                              <s:textfield id="mainapn" name="terminalParamsInfo.mainapn" cssClass="input120" maxlength="30"  />  
                          </td>
                          <td>拨号点用户名：
                          </td>
                          <td  align="left">
                              <s:textfield id="mainuser" name="terminalParamsInfo.mainuser" cssClass="input120" maxlength="30"  />                              
                          </td>
                          <td >拨号密码：
                          </td>
                          <td align="left">
                              <s:textfield id="mainpass" name="terminalParamsInfo.mainpass" cssClass="input120" maxlength="30"  />
                          </td> 
                          <td></td>                                            
                        </tr>
                        <tr align="right">
                          <td>IP地址：
                          </td>
                          <td align="left">
                              <s:textfield id="ip" name="terminalParamsInfo.ip" cssClass="input120" maxlength="30"  />                    
                          </td>
                          <td>TCP端口号：
                          </td>
                          <td align="left">
                              <s:textfield id="tcpport" name="terminalParamsInfo.tcpport" cssClass="input120" maxlength="30" /> 
                          </td>
                          <td >UDP端口号：
                          </td>
                          <td align="left">
                              <s:textfield id="udpport" name="terminalParamsInfo.udpport" cssClass="input120" maxlength="30"  />                            
                          </td> 
                          <td>
                            <s:if test="#session.perUrlList.contains('111_0_5_5')">
                             <a href="#" onclick="submitForm()" class="sbutton">升级</a>
                             </s:if>
                          </td>   
                          <s:hidden name = "vinList" id="vinList" ></s:hidden>
                          <s:hidden name = "teminalList" id="teminalList" ></s:hidden> 
                          <s:hidden name = "simList" id="simList" ></s:hidden>
                          <s:hidden name = "lnList" id="lnList" ></s:hidden>                                                                          
                        </tr>
                        
                      </table>
                      </td>
                    </tr>
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