<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
	<head>
		<%@include file="../common/meta.jsp"%>
		<title><s:property value="getText('service.management.title')" />&nbsp;|&nbsp;<s:property
				value="getText('service.management.maintenance.set')" />
		</title>
		<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
			type="text/css" media="all" />
	</head>
	<body>
		<%@include file="../common/menu.jsp"%>
		<%@include file="luxurycarset_validate.jsp"%>
		<s:form id="luxurycarset_form" action="luxuryCarSet"
			method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							<s:if test="#session.perUrlList.contains('111_0_2')"><!-- 查询:111_0_6_7_2_1 -->
								<tr>
									<td class="reportOnline2">
										<table width="100%" border="0" cellspacing="8" cellpadding="0">
											<tr>
												<td>
													<table border="0" cellspacing="4" cellpadding="0">
														<tr>
															<td>车工号：</td>	
															<td align="left"><s:textfield id="vehicle_number" name="luxuryCar.vehicle_number" cssClass="input120" maxlength="64"/></td>
															<s:hidden id="luxury_car_id" name="luxuryCar.luxury_car_id"/>
															<td><a href="#" 
																   onclick="searchList()"
																   class="sbutton">
																   <s:property value="getText('btn.query')" /> 
																</a></td>
														    <td>&nbsp;</td>
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
																		高档车设置信息表
																	</td>
																	<td width="69%" align="right">
																		<s:if
																			test="#session.perUrlList.contains('111_0_2_1_2')"><!-- 新建:111_0_6_7_2_2 -->
																			<div class="buhuanhangbut">
																				<a
																					href="<s:url value='/sm/luxuryCarSetToAdd.shtml' />"
																					class="btnAdd"
																					title="<s:property value="getText('msg.add')" />">
																				</a>
																			</div>
																		</s:if>
																		<s:if
																			test="#session.perUrlList.contains('111_0_2_1_6')"><!-- 导出:111_0_6_7_2_6 -->
																			<div class="buhuanhangbut">
																				<a href="#" class="btnDaochu"
																					onclick="exportVehicleRegister();"
																					title="<s:property value="getText('msg.export')" />">
																				</a>
																			</div>
																		</s:if>

																		<s:if
																			test="#session.perUrlList.contains('111_0_2_1_5')"><!-- 导入:111_0_6_7_2_5 -->
																			<div class="buhuanhangbut">
																				<a
																					href="<s:url value='/sm/luxurycarsetImport.shtml' />"
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
															  <table id="maintenanceList" width="100%"  cellspacing="0">
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