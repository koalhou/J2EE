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
		<table  width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="36" valign="top" background="../images/tree_tabBg.gif">
							<div class="toolbar">
								<div class="contentTil">请销假信息</div>
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
								<s:if test="#session.perUrlList.contains('111_3_3_8_1')">
								<table border="0" cellspacing="4" cellpadding="0">
									<tr>
										<td>
										学号：
										</td>
										<td>
										<s:textfield id="student_code" 
										             name="holidayInfo.student_code" 
										             cssClass="input120" 
										             maxlength="32"
										             onkeypress="if(event.keyCode==13){searchHoliday();}"/>&nbsp;&nbsp;
										</td>
										<td>
										姓名：
										</td>
										<td>
										<s:textfield id="student_name" 
										             name="holidayInfo.student_name" 
										             cssClass="input120" 
										             maxlength="16"
										             onkeypress="if(event.keyCode==13){searchHoliday();}"/>&nbsp;&nbsp;
										</td>
										<td>
										状态：
		                                <s:select id="holiday_flag" 
		                                          name="holidayInfo.holiday_flag" 
		                                          list="#{0:'请假中',1:'已销假'}" 
		                                          headerKey="" 
		                                          headerValue="%{getText('please.select')}"
		                                          cssStyle="width:120px"
		                                          onchange="searchHoliday();"
		                                          >
						                </s:select>&nbsp;&nbsp;
		                                </td>
										<td><a href="#" onclick="searchHoliday()" class="sbutton"><s:text name="common.chaxun" /></a></td>
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
				                               <td width="30%" class="titleStyleZi">请销假信息</td>
				                               <td width="69%"align="right">
				                                <s:if test="#session.perUrlList.contains('111_3_3_8_2')">
				                                	<div class="buhuanhangbut"><a href="<s:url value='/holiday/addholidaybefore.shtml' />" class="btnAdd" title="<s:text name="button.create" />"></a></div>
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
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="holidaymanage_js.jsp"%>
</body>
</html>

