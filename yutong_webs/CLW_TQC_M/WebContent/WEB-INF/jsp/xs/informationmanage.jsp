<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<title>
	<s:property value="getText('system.set.menu')" />&nbsp;|&nbsp;<s:property value="getText('informationmanage.menu')" />
</title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
</head>
<body>
<%@include file="../common/menu.jsp"%>
<%@include file="informationmanage_validate.jsp"%>
<s:form id="informationmanage_form" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<s:if test="#session.perUrlList.contains('111_0_6_5_1')">
					<tr>
						<td class="reportOnline2">
						<table width="100%" border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td>
								<table border="0" cellspacing="4" cellpadding="0">
									<tr>
										<td>
											<s:property value="getText('informationmanage.theme')" />：
										</td>
										<td align="left">
											<s:textfield id="theme"
											             name="theme" 
											             cssClass="input120" />&nbsp;&nbsp;
										</td>
										<td>
											<s:property value="getText('informationmanage.createtime')" />：
										</td>
										<td>
											<s:textfield id="createTime" 
										                 name="createTime"
										                 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" 
										                 cssClass="Wdate"
										                 cssClass="input120" />&nbsp;&nbsp;
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
					</s:if>

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
												<s:property value="getText('informationmanage.infolist')" />
											</td>
											<td width="69%" align="right">
												<s:if test="#session.perUrlList.contains('111_0_6_5_2')">
												<div class="buhuanhangbut">
													<a href="<s:url value='/xs/addInfoBefore.shtml' />" 
													                class="btnAdd"
																	title="<s:property value="getText('msg.add')" />">
													</a>
												</div>
												</s:if>
												<s:if test="#session.perUrlList.contains('111_0_6_5_7')">
												<div class="buhuanhangbut">
													<a href="#" class="btnDaochu" onclick="exportInformations();"
													   title="<s:property value="getText('msg.export')" />"> 
													</a>
												</div>
												</s:if>
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
											<table id="informationList" width="100%" cellspacing="0">
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