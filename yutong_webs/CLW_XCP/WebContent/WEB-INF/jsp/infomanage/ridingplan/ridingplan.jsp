<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style type="text/css">
		.searchPlan {
			float: left;
			width: 250px;
		}
	</style>
</head>
<body>
	<div id="wrapper">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">
			<s:form id="ridingplan_form" action="ridingplan" method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td id='searchPlanId' width="250" valign="top" class="treeline">
							<div id="leftInfoDiv" class="searchPlan">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="36" background="../images/tree_tabBg.gif" class="titleStyleZi">
											组织机构
										</td>
										<td align="right" background="../images/tree_tabBg.gif"	class="titleStyleZi">
											<div class="searchHide">
												<a href="#" onClick="LeftScreen()" class="btnHide"></a>
											</div>
										</td>
									</tr>									
									<tr id="treeTr1" >
										<td valign="top" colspan="2">
											<div class="fanwei2" id="treechang">
												<table border="0" cellpadding="0" cellspacing="8" >
													<tr id="treeTr1">
														<td valign="top" nowrap="nowrap">
															<%@include file="/WEB-INF/jsp/treeSpan.jsp"%>
														</td>
													</tr>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
	
						<td valign="top" bgcolor="#E9E9E9" width="0">
							<table id="leftTab" width="100%" height="30" border="0"	cellpadding="0" cellspacing="0" style="display: none">
								<tr>
									<td valign="top">
										<div class="searchHide2">
											<a href="#" class="btnTreeVisible" id="Image2" onClick="midScreen()"></a>
										</div>
									</td>
								</tr>
							</table>
						</td>
	
						<td valign="top">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="36" valign="top"
										background="../images/tree_tabBg.gif">
										<div class="toolbar">
											<div class="contentTil">
												乘车规划
											</div>
										</div>
									</td>
								</tr>
							</table>
							<table width="100%" border="0" cellspacing="5" cellpadding="0">
								<tr>
									<td class="reportOnline2">
										<table width="100%" border="0" cellspacing="8" cellpadding="0">
											<tr>
												<td width="450px">
													<table border="0" cellspacing="4" cellpadding="0">
														<tr>
															<td>车牌号：</td>
															<td><s:textfield id="vehicle_ln" name="alarmmanage.vehicle_ln" cssClass="input80" maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/></td>
															<td><a href="#"><img id="fordudu" src="../images/gif-0707.gif" border="0" onClick="choiceCarln();"></a></td>
															<!--														
															<td>
																车辆VIN:
																<s:textfield id="vehicle_vin" name="alarmmanage.vehicle_vin" cssClass="input80" maxlength="30" />								
															</td>						
															-->
															<td>&nbsp;&nbsp;&nbsp;</td>
															<td>行驶线路：</td>
															<td><s:textfield id="route_name" name="alarmmanage.route_name" cssClass="input80" maxlength="30" onkeypress="if(event.keyCode==13){searchList();}"/></td>
															<td><a href="#" onClick="searchList()" class="sbutton"><s:text name="common.chaxun" /></a></td>
															<!--																											
															<td>
																<a href="#" onclick="sendRouteVin()" class="sbutton">
																	线路下发
																</a>
															</td>
															-->
														</tr>
													</table>
											  	</td>
												<td>
													<s:hidden id="chooseorgid" name="chooseorgid" />
													<input type="hidden" id="ChooseEnterID_tree" name="ChooseEnterID_tree" value="<%=session.getValue("ChooseEnterID_tree")%>">
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td valign="top">
										<table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
											<tr>
												<td>
													<table width="100%" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td class="titleStyle">
																<table width="100%" border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td width="30%" class="titleStyleZi">
																			乘车规划信息
																		</td>
																		<td width="69%" align="right">
																			<s:if test="#session.perUrlList.contains('111_3_3_7_2')">
																				<div class="buhuanhangbut">
																					<a href="./ridingReady.shtml"
																					   class="btnAdd"
																					   title="<s:property value="getText('msg.add')" />">
																					</a>
																				</div>
																			</s:if>
																			<s:if test="#session.perUrlList.contains('111_3_3_7_5')">
																				<div class="buhuanhangbut">
																					<a href="#" 
																					   class="btnDaochu"
																					   onclick="exportIni()"
																					   title="<s:property value="getText('msg.export')" />">
																					</a>
																				</div>
																			</s:if>
																			<s:if test="#session.perUrlList.contains('111_3_3_7_6')">
									                                      		<div class="buhuanhangbut">
									                                      			<a href="#" 
									                                      			   class="btnAllot" 
									                                      			   onclick="sendRouteVin()"
									                                      			   title="<s:text name="button.xianluxiafa" />">
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
													</table>
												</td>
											</tr>
											<tr>
												<td class="reportInline">
												     <div id="Below_new" style="text-align:center">
													  <s:actionmessage cssStyle="color:green"/>
													  <s:actionerror cssStyle="color:red"/>
												    </div>	
													<table id="gala" width="100%" cellspacing="0">
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
		</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/md5/base64.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
	<%@include file="ridingplan_js.jsp"%>
	<form  action="../infomanage/exportIni.shtml" method="post" name="exportIniForm" id="exportIniForm" enctype="multipart/form-data">
		<input type="hidden" id="vehicle_vin_pop" name="vehicle_vin_pop"/>
	</form>
</body>
</html>