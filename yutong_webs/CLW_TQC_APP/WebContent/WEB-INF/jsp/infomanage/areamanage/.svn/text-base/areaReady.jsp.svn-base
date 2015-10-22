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
					<div class="contentTil">站点区域分配</div>
					<div class="tool"></div>
					</div>
					</td>
				</tr>
				</table>
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td class="reportOnline2">
						<table id="selectAreaId" width="100%" border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td>
							<s:form id="conditionselectform" action="" method="post">
							<s:if test="#session.perUrlList.contains('111_3_5_2_4')">
							<table  border="0" cellspacing="4" cellpadding="0">
								<tr>
									<td><s:label>站点名称：</s:label></td>
									<td align="left">
									<s:textfield id="vehicle_code" name="vehicle_code" onkeypress="if(event.keyCode==13){searchList();}"/>
									</td>
									<td><s:label>大区名称：</s:label></td>
									<td align="left">
									<s:textfield id="vehicle_ln" name="vehicle_ln" onclick="choiceCarln();" readonly="true" />
									<s:hidden id="vehicle_vin" name="vehicle_vin"></s:hidden>
									</td>
									<s:if test="#session.perUrlList.contains('111_3_3_3_1')">
									<td><a href="javascript:void(0);" class="sbutton" onclick="searchList();">查询</a></td>
									</s:if>
								</tr>
							</table>
							</s:if>
							</s:form>
							<s:form id="updateByAreaId" action="" method="post">
								<s:hidden id="areaId_hidden" name="areaInfo.areaId"></s:hidden>
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
							<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="titleStyle">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td width="30%" class="titleStyleZi">发车信息</td>
                                      <td align="right">
                                      <div class="buhuanhangbut">
										<a href="<s:url value='/area/addareaReady.shtml'/>" class="btnAdd" title="添加区域">
                                      </a>
									</div>
                                      <div class="buhuanhangbut">
                                      <a href="<s:url value='/area/addsiteareaReady.shtml'/>" class="btnAllot" title="<s:text name="button.fenpei" />">
                                      </a>
                                      </div></td>
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
							<div id="Below_new" style="text-align:center">
							  <s:actionmessage cssStyle="color:green"/>
							  <s:actionerror cssStyle="color:red"/>
						    </div>
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
	<s:form id="siteareacheck" action="../station/stationmanage.shtml" method="post">
		<s:hidden name="switchsite" value="1"></s:hidden>
		<s:hidden id="areaidto" name="areaid"></s:hidden>
	</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="areaReady_js.jsp"%>
</body>
</html>
