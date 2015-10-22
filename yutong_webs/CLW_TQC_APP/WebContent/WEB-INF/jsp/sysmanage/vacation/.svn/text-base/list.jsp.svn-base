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
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">

				<table id="yonghuguanliId" width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top" background="../images/tree_tabBg.gif">
					<div class="toolbar">
					<div class="contentTil">假期设置</div>
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
						
							<table  border="0" cellspacing="4" cellpadding="0">
								<tr>
									<td><s:label>组织：</s:label></td>
									<td align="left">
									<input id="orgName" name="orgName"  style="cursor: pointer" title="点击选择机构" readonly="readonly"   value="<s:property  value="#session.adminProfile.fullName"/>"/>
									<input type="hidden" id="orgs" name="orgs"/>
									<input type="hidden" id="orgID" name="orgID" value="<s:property  value="#session.adminProfile.organizationID"/>" />
									</td>
									<td><s:label>&nbsp;年份：</s:label></td>
									<td align="left">
										<select id="year" name="year" style=" width:118px">
												<option value="2013">2013</option>
											 	<option value="2014">2014</option>
											 	<option value="2015">2015</option>
											 	<option value="2016">2016</option>
											 	<option value="2017">2017</option>
											 	<option value="2018">2018</option>
											 	<option value="2019">2019</option>
											 	<option value="2020">2020</option>
					
										</select>
									</td>
									<td><s:label>&nbsp;月份：</s:label></td>
									<td align="left">
										<select id="month" name="month" style=" width:118px">
												<option value=''>请选择</option>
												<option value="01">1月</option>
												<option value="02">2月</option>
												<option value="03">3月</option>
												<option value="04">4月</option>
												<option value="05">5月</option>
												<option value="06">6月</option>
												<option value="07">7月</option>
												<option value="08">8月</option>
												<option value="09">9月</option>
												<option value="10">10月</option>
												<option value="11">11月</option>
												<option value="12">12月</option>
											
										</select>
									</td>
									<td>
									<s:if test="#session.perUrlList.contains('111_3_5_10_1')">  
									<a href="javascript:void(0)" class="sbutton" onclick="vacationObj.search();">查询</a>
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
					<td valign="top" >
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
						<tr>
							<td >
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="titleStyle">							
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td width="30%" class="titleStyleZi">假期信息</td>
                                      <td width="69%" align="right">
									  <s:if test="#session.perUrlList.contains('111_3_5_10_2')">
									  <div class="buhuanhangbut"><a href="javascript:void(0)"  class="btnAdd" onclick="addVacation()"></a></div>
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
                      <td class="reportInline" align="left">
						    
					  <div id="tabAreaId">
							<s:form action="usermanageAction" id="userselect" method="post">
								<div align="center">
									<s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/>
								</div>
								<table id="zzlist"   cellspacing="0">
					   			</table>	
							</s:form>				
												
					 </div>
							</td>
						</tr>
					</table>
					</td>
					</tr>
				</table>
			
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<form style="display: none;" id="gotoForm" action="one.shtml" method="post">
<input type="hidden" id="orgIDgoto" name="orgID" value=""/>
<input type="hidden" id="yeargoto" name="year" value=""/>
<input type="hidden" id="monthgoto" name="month" value=""/>
<input type="hidden" id="collectiongoto" name="collection" value=""/>
<input type="hidden" id="action" name="action" value=""/>
</form>

<!--JS区域--> 
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='../scripts/flexigrid/flexigrid.js' />"></script>
<script type="text/javascript" src="../scripts/vacation/vacation.js"></script>
</body>
</html>