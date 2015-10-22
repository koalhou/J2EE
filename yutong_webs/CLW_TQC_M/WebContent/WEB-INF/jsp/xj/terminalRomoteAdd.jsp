<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<title>
  <s:property value="getText('system.monitor.menu')" />&nbsp;|&nbsp;<s:property value="getText('menu.xj.param')" />
</title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
</head>
<body>
<%@include file="terminalRomoteAdd_validate.jsp"%>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellspacing="5" cellpadding="0">
           <tr>
            <td class="reportOnline">
              <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                  <td>
                     <div style="height: 100px;width:100%;overflow: auto;" >
		                 <table border="0" cellspacing="4" cellpadding="0" id = "showvehicle">
                         </table>  
	                 </div> 
	                  	<table width="100%">
		                    <tr align="right">
		                       <td width="70%"></td>
		                       <td><a href="#" onclick="rechoise()" class="sbutton">重新选择</a></td>
		                       <td><a href="#" onclick="choise()" class="sbutton">确认</a></td>
		                       <td><a href="#" onclick="quxiao()" class="sbutton">取消</a></td>
		                    </tr>
		</table>                
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td class="reportOnline2">
              <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                  <td>
                    <table border="0" cellspacing="4" cellpadding="0" >
                      <tr>
                        <td>
                          <s:property value="getText('vehicle.vin.number')" />：
                          <s:textfield id="vehicleVin" name="vehicleVin" cssClass="input120"/>&nbsp;&nbsp;
                        </td>
                        <td>
                          <s:property value="getText('vehicle.ln')" />：
                          <s:textfield id="vehicleLN" name="vehicleLN" cssClass="input120"/>&nbsp;&nbsp;
                        </td>
                        <td>
                          <s:property value="getText('terminal.enterprise_name')" />：
                          <s:textfield id="enterprise_name" name="enterprise_name" cssClass="input120"/>&nbsp;&nbsp;
                        </td>
                        <td>
                          <s:property value="getText('terminal.enterprise_code')" />：
                          <s:textfield id="enterprise_code" name="enterprise_code" cssClass="input120"/>&nbsp;&nbsp;
                        </td>
                        <td>
                          <a href="#" onclick="searchList()" class="sbutton">
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
                              <td width="30%" class="titleStyleZi">注册终端信息表                                
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
						  <s:hidden name = "type" id="type" ></s:hidden>
						  <s:hidden name = "vinList" id="vinList" ></s:hidden>
                          <s:hidden name = "teminalList" id="teminalList" ></s:hidden> 
                          <s:hidden name = "simList" id="simList" ></s:hidden>
                          <s:hidden name = "lnList" id="lnList" ></s:hidden>                                    
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
</body>
</html>