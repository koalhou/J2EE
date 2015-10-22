<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style type="text/css">
		*{
		margin: 0;
		padding: 0;
		font-family: Tahoma, Arial, Verdana,'Microsoft Yahei', 微软雅黑, 宋体;
		}
	</style>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<s:form id="editOilSetbefore_form" action="messagemanage" method="post">
		</s:form>
		   <s:hidden id="page" name="page"></s:hidden>
		<s:form id="message_form" action="messagemanage" method="post">
		<s:hidden id="oilset.organization_id" name="oilset.organization_id"></s:hidden>
		<s:hidden id="oilset.enterprise_id" name="oilset.enterprise_id"></s:hidden>
		<table  width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="36" valign="top" background="../images/tree_tabBg.gif">
						<div class="toolbar">
						<div class="contentTil"><s:text name="menu3.khyhsz" /></div>
						
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
								 <s:if test="#session.perUrlList.contains('111_3_5_7_4')">
								<table border="0" cellspacing="4" cellpadding="0">
									<tr>
										<td>
										查询日期：
										<input type="text" name="oilset.check_time_code" value="" id="check_time_code" class="Wdate" 
										       onfocus="WdatePicker({dateFmt:'yyyy-MM',autoPickDate:true,isShowToday:false,onpicked:function(){searchList();}})"/>
										&nbsp;
										</td>
										<td><a href="javascript:void(0);" onclick="searchList()" class="sbutton">查询</a></td>
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
		                                      <td width="30%" class="titleStyleZi"><s:text name="menu3.khyhsz" /></td>
		                                      <td width="69%"align="right">
		                                      <s:if test="#session.perUrlList.contains('111_3_5_7_1')">
		                                      <div class="buhuanhangbut"><a href="<s:url value='/oilset/addOilSetbefore.shtml' />" class="btnAdd" title="<s:text name="button.create" />"></a></div>
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
					        <td  class="reportInline" >
					         <div id="Below_new" align="center">
					          <s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/>
					          </div>
					            <table id="gala" width="100%"  cellspacing="0">
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
<%@include file="oilset_js.jsp"%>
</body>
</html>
