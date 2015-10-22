<%@taglib uri="/struts-tags" prefix="s" %>
<link rel="shortcut icon" href="<s:url value="/images/favicon.ico" />" type="image/x-icon" />
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0"> 
<base target='_self'>
<title>车辆选择</title>
</head>
<body>
<%@include file="vehicleBrowse_validate.jsp"%>
  <s:form id="vehbrowse_form" action="queryVehicle" method="post" onsubmit="return false;">
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
                          	车牌号：
                          <s:textfield id="vehicle_ln" name="vehicle_ln"  maxlength="10" cssClass="input120"/>&nbsp;&nbsp;
                        </td>
                        <td>
                          <a href="#" onclick="submit()" class="sbutton">
                            	查询
                          </a>
                        </td>
                        <td>
                          <a href="#" onclick="cancelSelect()" class="sbutton">
                           	取消选择
                          </a>
                        </td>
                        <td><a href="#" onclick="save()" class="sbutton">选择</a></td>
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
                                	车辆信息
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
                                  	<s:checkbox name="carChoiceAll" value="false" onclick="setOrCancelSelect(this);"></s:checkbox>
                                  </th>
                                  <th scope="col" width="10%">
                                    	序号
                                  </th>
							      <th scope="col" width="20%">
							        	车牌号
							      </th>
							      <th scope="col" width="35%">
							        	所属企业
							      </th>
                                </tr>
                              </thead>
                              <tbody style="overflow:auto;">
                                <s:iterator value="vehicleList" status="rowstatus">
                                  <tr>
                                  	<td>
                                  		<s:hidden id="vinStr" value=""/>
			                       		<s:checkbox name="carChoice" onclick="getCheckValue('%{vehicle_vin}','%{#rowstatus.index}');" fieldValue="%{vehicle_ln},%{vehicle_vin}" ></s:checkbox>
		                        	</td>
                                    <td>
                                      <s:property value="#rowstatus.index+1" />
                                    </td>
                                    
                                    <td>
                                      <s:property value="vehicle_ln" />
                                    </td>
                                    <td>
                                      <s:property value="short_allname" />
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
  </s:form>
</body>
</html>

