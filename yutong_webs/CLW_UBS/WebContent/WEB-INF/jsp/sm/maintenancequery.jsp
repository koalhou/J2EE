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
	<style type="text/css">
		.searchPlan {
			float: left;
			width: 250px;
		}
	</style>			
	</head>
	<body>
		<%@include file="../common/menu.jsp"%>
		<%@include file="maintenancequery_validate.jsp"%>
		<s:form id="maintenancesetquery_form" action="maintenanceQueryInit"
			method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="220" valign="top" class="treeline">
					<table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td height="36" background="../images/tree_tabBg.gif"
								class="titleStyleZi"><s:property
								value="getText('enterprise.info2')" /></td>
						</tr>
						<!--<tr id="treeTr1" height="592px">
							<td valign="top"><div style="width: 220px; overflow: auto; height:592px;"><%@include file="../treeSpan.jsp"%></div></td>
						</tr>-->
						<tr id="treeTr1">
							<td valign="top" nowrap="nowrap"><div id="treeDiv" style="width: 220px; overflow: auto; height:370px;">
							<table border="0" cellpadding="0"	cellspacing="8" >
								<tr id="treeTr1">
									<td valign="top" nowrap="nowrap"><%@include file="../treeSpan.jsp"%></td>
								</tr>
							</table></div>
							</td>
						</tr>
					</table>
					</td>
					<td valign="top">
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							<s:if test="#session.perUrlList.contains('111_0_2')"><!-- 维保查询:111_0_6_7_3_1 -->
								<tr>
									<td class="reportOnline2">
										<table width="100%" border="0" cellspacing="8" cellpadding="0">
											<tr>
												<td>
													<table border="0" cellspacing="4" cellpadding="0">
														<tr>
															<!-- 
															<td align="right">公司</td>	
															<td align="left"><s:textfield id="company" name="maintenance.company" cssClass="input120" /></td>
															<td align="right">分公司</td>	
															<td align="left"><s:textfield id="branch" name="maintenance.branch" cssClass="input120" /></td>
															<td align="right">车队</td>	
															<td align="left"><s:textfield id="vehicleFleet" name="maintenance.vehicle_fleet" cssClass="input120" /></td>
															 -->
															<td align="right">车型</td>	
															<td align="left"><s:textfield id="type_name" name="maintenance.type_name" cssClass="input120" /></td>
															<td align="right">车工号</td>	
															<td align="left"><s:textfield id="vehicle_number" name="maintenance.vehicle_number" cssClass="input120" /></td>
															<td >&nbsp;</td>
														</tr>
														<tr>
															<td align="right">VIN号</td>	
															<td align="left"><s:textfield id="vehicle_vin" name="maintenance.vehicle_vin" cssClass="input120" /></td>
															<td align="right">车牌号</td>
															<td align="left"><s:textfield id="vehicle_ln" name="maintenance.vehicle_ln" cssClass="input120" /></td>
															<td ><a href="#" 
																   onclick="searchList()"
																   class="sbutton">
																   <s:property value="getText('btn.query')" /> 
																</a>
																<input type="hidden" id="ChooseEnterID_tree" name="ChooseEnterID_tree" value="<s:property value='#session.ChooseEnterID_tree'/>"/>
																<input id="checkItemIds" type="hidden" name="checkItemIds">
																<input id="unCheckItemIds" type="hidden" name="unCheckItemIds">
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
																		维保信息表
																	</td>
																	<td width="69%" align="right">
																		<a href="#" onclick="doNotRemind()"
																   			class="sbutton" title="先选中记录">不再提醒</a>
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