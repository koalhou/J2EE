<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body>
<object id="GenerateCardId" name="GenerateCardId" classid="clsid:7DE30258-36E3-4F87-B7FA-F093FF205D33" codebase="<s:url value='/codebase/ComReader.dll' />#version=1.0" style="display:none;">
</object>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<s:form id="stewardmanage_form" action="" method="post">
		<table  width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="36" valign="top" background="../images/tree_tabBg.gif">
							<div class="toolbar">
								<div class="contentTil">跟车老师信息</div>
							</div>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td class="reportOnline2">
						<table width="100%" border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td>
								<s:if test="#session.perUrlList.contains('111_3_3_3_1')">
								<table border="0" cellspacing="4" cellpadding="0">
									<tr>
										<td>
										卡号：
										<s:textfield id="steward_card" name="stewardInfo.steward_card" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchSteward();}"/>&nbsp;&nbsp;
										</td>
										<td>
										姓名：
										<s:textfield id="steward_name" name="stewardInfo.steward_name" cssClass="input120" maxlength="16" onkeypress="if(event.keyCode==13){searchSteward();}"/>&nbsp;&nbsp;
										</td>
										<td>
										所属企业：
		                                <s:textfield id="organizationName" name="stewardInfo.short_allname" readonly="true" onclick="openorganizidtree()"/>
		                                <s:hidden id="organizationID" name="stewardInfo.organization_id"></s:hidden>
		                                </td>
										<td><a href="#" onclick="searchSteward()" class="sbutton"><s:text name="common.chaxun" /></a></td>
									</tr>
								</table>
								</s:if>
								
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
				                               <td width="30%" class="titleStyleZi"><s:text name="steward.info" /></td>
				                               <td width="69%"align="right">
				                                <s:if test="#session.perUrlList.contains('111_3_3_3_2')">
				                                	<div class="buhuanhangbut"><a href="<s:url value='/steward/addstewardbefore.shtml' />" class="btnAdd" title="<s:text name="button.create" />"></a></div>
				                                </s:if>
				                                <s:if test="#session.perUrlList.contains('111_3_3_3_6')">
				                                	<div class="buhuanhangbut">
					                                  <a href="#" onclick="exportSteward();" class="btnDaochu" title="<s:property value="getText('msg.export')" />">
					                                  </a>
					                                </div>
					                            </s:if>
					                            <s:if test="#session.perUrlList.contains('111_3_3_3_5')">
					                                <div class="buhuanhangbut">
					                                  <a href="<s:url value='/steward/importStewardBefore.shtml' />" class="btnInput" title="导入"></a>
					                                </div>
					                            </s:if>
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
					        <div id="Below_new" style="text-align:center"><s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/></div>
					        <%-- 列表内容 --%>
							<table id="gala" width="100%"  cellspacing="0"></table>
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
<%@include file="stewardmanage_js.jsp"%>
</body>
</html>