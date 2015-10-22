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
						<td valign="top">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="36" valign="top"
										background="../images/tree_tabBg.gif">
										<div class="toolbar">
											<div class="contentTil">
												防偷油短信提醒设置
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
															<td>姓名：</td>
															<td><s:textfield id="stu_name"  name="stealOilSmsInfo.stu_name" cssClass="input80" maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/></td>
															<td>&nbsp;&nbsp;&nbsp;</td>
															<td>&nbsp;&nbsp;&nbsp;</td>
															<td>手机号：</td>
															<td><s:textfield id="telephone" name="stealOilSmsInfo.telephone" cssClass="input80" maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/></td>
															<td>&nbsp;&nbsp;&nbsp;</td>
															<td>&nbsp;&nbsp;&nbsp;</td>
															<td>关联组织：</td>
															<td><s:textfield id="organization_name" cssClass="input80" maxlength="32" onclick="openorganizidtree()" onkeypress="if(event.keyCode==13){searchList();}" readonly="true"/>
															<s:hidden id ="organization_id_s"></s:hidden> </td>
															<td>&nbsp;&nbsp;&nbsp;</td>
															<td>
																<a href="#" onClick="searchList()" class="sbutton"><s:text name="common.chaxun" /></a> 
															</td>
															<s:if test="#session.perUrlList.contains('111_3_2_3_4')">
																<td>
																	<a href="#" onClick="allDelete()" class="sbutton"><s:text name="批量删除" /></a>
																</td>
															</s:if>
														</tr>
													</table>
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
																			接收短信提醒人列表
																		</td>
																		<td width="69%" align="right">
																			<s:if test="#session.perUrlList.contains('111_3_2_3_2')">
																				<div class="buhuanhangbut">
																					<a href="./addStealOilSms.shtml"
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
	<%@include file="stealOilSms_js.jsp"%>
</body>
</html>