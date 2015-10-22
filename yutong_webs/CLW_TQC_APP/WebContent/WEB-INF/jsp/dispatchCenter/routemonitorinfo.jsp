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
	<input id="endTimehidden" value="${endTime}" type="hidden"/>
	<table id="midarerId" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
				<table id="yonghuguanliId" width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top" background="../images/tree_tabBg.gif">
					<div class="toolbar">
					<div class="contentTil">调度信息查询</div>
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
							<s:form id="conditionselectform" action="" method="post">
							<s:if test="#session.perUrlList.contains('111_3_5_2_4')">
							<table  border="0" cellspacing="4" cellpadding="0">
								<tr>
									<td><s:label>班车号：</s:label></td>
									<td align="left">
									<s:textfield id="vehicle_code" name="vehicle_code" onkeypress="if(event.keyCode==13){searchList();}"/>
									</td>
									<td><s:label>车牌号：</s:label></td>
									<td align="left">
									<s:textfield id="vehicle_ln" name="vehicle_ln" onclick="choiceCarln();" readonly="true" />
									<s:hidden id="vehicle_vin" name="vehicle_vin"></s:hidden>
									</td>
									<td><s:label>发车日期：</s:label></td>
									<td align="left">
										<input readonly="readonly" id="begTime" name="begTime" value="${begTime}"
												class="Wdate" type="text" 
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	isShowToday:false,
																	maxDate:'#F{$dp.$D(\'endTime\')}',
																	isShowClear:false})"/> 至 
										<input readonly="readonly" id="endTime" name="endTime" value="${endTime}"
												class="Wdate" type="text" 
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	isShowToday:false,
																	maxDate:'#F{$dp.$D(\'endTimehidden\')}',
																	minDate:'#F{$dp.$D(\'begTime\')}',
																	isShowClear:false})"/>
									
									</td>
									<td><s:label>线路类型：</s:label></td>
									<td align="left">
										<select id="route_class" name="route_class" onchange="searchList();">
											<option value="-1" selected="selected">全部</option>
											<s:if test="#session.perUrlList.contains('111_3_5_3_5')">
											<option value="0">早班线路</option>
											</s:if>
											<s:if test="#session.perUrlList.contains('111_3_5_3_6')">
											<option value="1">晚班线路</option>
											</s:if>
											<s:if test="#session.perUrlList.contains('111_3_5_3_7')">
											<option value="2">厂内通勤</option>
											</s:if>
											
										</select>
									</td>
									<td><s:label>行驶线路：</s:label></td>
									<td align="left"><s:textfield id="choiceroutename" name="choiceroutename" onclick="choiceRoute();" readonly="true" />
									<s:hidden id="choicerouteid" name="choicerouteid"></s:hidden>
									</td>
									<td><s:label>下发结果：</s:label></td>
									<td align="left">
										<select id="route_res" name="route_res" onchange="searchList();">
											<option value="0" selected="selected">全部</option>
											<option value="1">成功</option>
											<option value="-1">失败</option>
											<option value="2">正在下发</option>
										</select>
									</td>
									<s:if test="#session.perUrlList.contains('111_3_3_3_1')">
									<td><a href="javascript:void(0);" class="sbutton" onclick="searchList();">查询</a></td>
									</s:if>
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
							<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="titleStyle">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td width="30%" class="titleStyleZi">发车信息</td>
                                      <td width="69%" align="right">
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
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="routemonitorinfo_js.jsp"%>
</body>
</html>
