<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
	<head>
		<%@include file="../common/meta.jsp"%>
		<title>
          <s:property value="getText('menu.cl')" />&nbsp;|&nbsp;<s:property value="getText('vehicle.register.name')" />
        </title>
		<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
			type="text/css" media="all" />
	</head>
	<body>
		<%@include file="../common/menu.jsp"%>
		<%@include file="vehicleRegister_validate.jsp"%>
		<s:form id="vehicleregister_form" action="queryVehicleRegister"
			method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							<s:if test="#session.perUrlList.contains('111_0_2_1_1')">
								<tr>
									<td class="reportOnline2">
										<table width="100%" border="0" cellspacing="8" cellpadding="0">
											<tr>
												<td>
													<table border="0" cellspacing="4" cellpadding="0">
														<tr>
															<td>车牌号：</td>
															<td>
																<s:textfield id="vehicleLn" name="vehicleLn" cssClass="input120" />&nbsp;&nbsp;
															</td>
															<td>
																<s:property value="getText('vehicle.vin.number')" />：															
															</td>	
															<td align="left">
																<s:textfield id="vehicleVin" name="vehicleVin" cssClass="input120" />&nbsp;&nbsp;												
															</td>
															<td>
																<s:property value="getText('terminal.code')" />：															
															</td>
															<td>
																<s:textfield id="terminalCode" name="terminalCode" cssClass="input120" />&nbsp;&nbsp;												
															</td>
															<td>
																<s:property value="getText('sim.card.number')" />：															
															</td>
															<td colspan="2">
																<s:textfield id="simCardNumber" name="simCardNumber" cssClass="input120" />&nbsp;&nbsp;
																手机号：
																<s:textfield id="cellPhone" name="cellPhone" cssClass="input120" />&nbsp;&nbsp;
															</td>
														</tr>
														<tr>
															<td>
														    	<s:property value="getText('enterprise.info.ENTERPRISE_CODE')" />：
														    </td>
															<td>
																<s:textfield id="enterpriseCode" name="enterpriseCode" cssClass="input120" />&nbsp;&nbsp;
															</td>
															<td>
																<s:property value="getText('vehicleregister.list.delivery')" />：															
															</td>
															<td  align="left">
																<s:select id="deliveryFlag" 
																          name="deliveryFlag"
																	      list="deliveryFlagMap" 
																	      listKey="key" 
																	      cssStyle="width:120px"
																	      listValue="value" 
																	      headerKey="" 
																	      headerValue="全部" />&nbsp;&nbsp;												
															</td>
															<td >
																<s:property value="getText('vehicleregister.list.fixtype')" />：															
															</td>
															<td>
																<s:select id="fixType" 
																          name="fixType"
																	      list="fixTypeMap" 
																	      listKey="key"
																	      cssStyle="width:120px"
																	      listValue="value" 
																	      headerKey="" 
																	      headerValue="全部" />&nbsp;&nbsp;												
															</td>
															<td>
																注册时间：															
															</td>
															<td>
															 	<input readonly="readonly" id="start_time" name="start_time"  
																       class="Wdate" type="text" size="10" value="<s:property value='start_time' />"
																       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end_time\')}'})"/>
										                        <input type="hidden" id="nullEle"/>至
															    <input readonly="readonly" size="10" id="end_time" name="end_time"
																       class="Wdate" type="text"  value="<s:property value='end_time' />"
																       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'start_time\')}'})"/>
															</td>
															<td align="center">
																<a href="#" 
																   onclick="searchList()"
																   class="sbutton">
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
							</s:if>

							<tr>
								<td valign="top">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="reportOnline">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td class="titleStyle">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td width="30%" class="titleStyleZi">
																		<s:property value="getText('vehicle.register.list')" />
																	</td>
																	<td width="69%" align="right">
																		<s:if
																			test="#session.perUrlList.contains('111_0_2_1_2')">
																			<div class="buhuanhangbut">
																				<a
																					href="<s:url value='/cl/addVehicleRegisterBefore.shtml' />"
																					class="btnAdd"
																					title="<s:property value="getText('msg.add')" />">
																				</a>
																			</div>
																		</s:if>

																		<s:if
																			test="#session.perUrlList.contains('111_0_2_1_6')">
																			<div class="buhuanhangbut">
																				<a href="#" class="btnDaochu"
																					onclick="exportVehicleRegister();"
																					title="<s:property value="getText('msg.export')" />">
																				</a>
																			</div>
																		</s:if>

																		<s:if
																			test="#session.perUrlList.contains('111_0_2_1_5')">
																			<div class="buhuanhangbut">
																				<a
																					href="<s:url value='/cl/importVehicleRegisterBefore.shtml' />"
																					class="btnInput"
																					title="<s:property value="getText('msg.import')" />"></a>
																			</div>
																		</s:if>

																	</td>
																	<td width="1%">&nbsp;
																		
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td class="reportInline">
															<div id="message">
															  <div id="empDiv">
																<s:actionerror theme="mat" />
																<s:fielderror theme="mat" />
																<s:actionmessage theme="mat" />
														      </div>
															</div>
															<div>
															  <table id="vehicleRegisteList" width="100%"  cellspacing="0">
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