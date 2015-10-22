<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<title><s:property value="getText('xj.menu')" />&nbsp;|&nbsp;<s:property
	value="getText('operationsInfo.info.list')" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
</head>
<body>
<%@include file="../common/menu.jsp"%>
<%@include file="operatingTotal_validate.jsp"%>
<s:form id="operatingReport_form" action="" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellspacing="0"
				cellpadding="0">
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
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="30%" class="titleStyleZi">
														<s:property	value="getText('operationsInfo.info.list')" />
													</td>
													<td width="69%" align="right">
														<s:if test="#session.perUrlList.contains('111_0_5_5_1')">
														
														</s:if>
														<div class="buhuanhangbut">
															<a href="<s:url value='/cl/importVehicleRegisterBefore.shtml' />"
															   class="btnQuery"
															   title="<s:property value="getText('operationsInfo.info.detail')" />"></a>
														</div>
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
														<s:fielderror theme="mat" /> 
														<s:actionmessage theme="mat" />
													</div>
												</div>
	
												<div>
													<table id="operatingTotalList" width="100%" cellspacing="0">
													<tr>
													<td>
													<img src="../images/loaddata.gif" border="0" />
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

			<%@include file="../common/copyright.jsp"%></td>
		</tr>
	</table>
</s:form>
</body>
</html>