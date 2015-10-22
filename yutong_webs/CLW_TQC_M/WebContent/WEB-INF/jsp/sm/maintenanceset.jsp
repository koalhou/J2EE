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
		<%@include file="maintenanceset_validate.jsp"%>
		<s:form id="maintenanceset_form" action="maintenance"
			method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							<s:if test="#session.perUrlList.contains('111_0_2')">
								<tr>
									<td class="reportOnline2">
										<table width="100%" border="0" cellspacing="8" cellpadding="0">
											<tr>
												<td>
													<table border="0" cellspacing="4" cellpadding="0">
														<tr>
															<td><s:property value="getText('service.management.maintenance.item')" />：</td>	
															<td align="left">
																<s:select id="typeId" name="maintenance.type_id" list="#request.maintItem" listKey="key" listValue="value" headerKey="0" headerValue="请选择"></s:select>
															</td>
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
																		<s:property value="getText('service.management.maintenance.set.list')" />
																	</td>
																	<td width="69%" align="right">
																		<s:if
																			test="#session.perUrlList.contains('111_0_2_1_2')">
																			<div class="buhuanhangbut">
																				<a
																					href="<s:url value='/sm/maintenanceToAdd.shtml' />"
																					class="btnAdd"
																					title="<s:property value="getText('msg.add')" />">
																				</a>
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