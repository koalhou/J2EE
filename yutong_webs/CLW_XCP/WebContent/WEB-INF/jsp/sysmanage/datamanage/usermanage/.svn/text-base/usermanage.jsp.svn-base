<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
	<s:hidden name="page" />
	<s:hidden name="pageSize" />
	<table id="midarerId" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
				<table id="yonghuguanliId" width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top" background="../images/tree_tabBg.gif">
					<div class="toolbar">
					<div class="contentTil"><s:text name="menu3.yhgl" /></div>
					<div class="tool"></div>
					</div>
					</td>
				</tr>
				</table>
				<input type="hidden"  id="UrlList" name="UrlList" value="<s:property value="#session.perUrlList.contains('111_3_5_2_7')"/>" />
	    		<input type="hidden"  id="UrlList2" name="UrlList2" value="<s:property value="#session.perUrlList.contains('111_3_5_2_8')"/>" />
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td class="reportOnline2">
						<table id="selectAreaId" width="100%" border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td>
							<s:form id="conditionselectform" action="/usm/usermanageconditionselectAction" method="post">
							<s:if test="#session.perUrlList.contains('111_3_5_2_4')">
							<table  border="0" cellspacing="4" cellpadding="0">
								<tr>
									<td><s:label>组织结构</s:label></td>
									<td align="left">
									<s:textfield id="organizname" name="organizname" onclick="openorganizidtree();" readonly="true" />
										<s:hidden id="organizidtreeid" name="organizidtreeid"></s:hidden>
									</td>
									<td><s:label>登录名</s:label></td>
									<td align="left"><s:textfield id="loginName" name="loginName" maxlength="15" onkeypress="if(event.keyCode==13){searchList();}"/></td>
									<td><s:label>状态</s:label></td>
									<td align="left">
										<select id="validFlag" name="validFlag" onchange="searchList();">
											<option value="3">全部</option>
											<option value="0" selected="selected">可用</option>
											<option value="2">禁用</option>
										</select>
									</td>									
									<td><a href="#" class="sbutton" onclick="searchList();">查询</a></td>
								</tr>
							</table>
							</s:if>
							</s:form>
							</td>
						</tr>
						</table>
						</td>
					</tr>
					<tr>
					<td valign="top" >
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
						<tr>
							<td >
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="titleStyle">							
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td width="30%" class="titleStyleZi">用户信息</td>
                                      <td width="69%" align="right">
									  <s:if test="#session.perUrlList.contains('111_3_5_2_1')">
									  <div class="buhuanhangbut"><a href="<s:url value='/usm/useraddinitAction.shtml' />"  class="btnAdd" title="<s:text name="button.create" />"></a></div>
								      </s:if>
									  </td>
                                      <td width="1%">&nbsp;</td>
                                    </tr>
                                  </table>
									</td>
								</tr>
							</table>
							</td>
                                </tr>
                                <tr>
                                  <td class="reportInline" align="left">
						    
							<div id="tabAreaId">
							<s:form action="usermanageAction" id="userselect" method="post">
							<div align="center">
							<s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/>
							</div>
							</s:form>				
							<table id="usertbl" width="100%"  cellspacing="0">
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
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="usermanage_js.jsp"%>
</body>
</html>
