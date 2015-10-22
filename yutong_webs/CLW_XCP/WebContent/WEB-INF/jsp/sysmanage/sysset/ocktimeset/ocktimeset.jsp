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
		<s:form id="ocktimeset_form" action="ocktimeset" method="post">
<table  width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="36" valign="top" background="../images/tree_tabBg.gif">
				<div class="toolbar">
				<div class="contentTil"><s:text name="menu3.khydsz" /></div>
				<div class="tool">
				<ul>
					<li><a href="#"></a></li>
				</ul>
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
						<td>
						<table border="0" cellspacing="4" cellpadding="0">
							<tr>
								<td>
								  <s:if test="#session.perUrlList.contains('111_3_5_6_4')">
								     考核月度:
								 <s:textfield id="check_time_code" 
								              name="ocktimeset.check_time_code" 
								              onfocus="WdatePicker({dateFmt:'yyyy-MM',readOnly:true,autoPickDate:true,isShowToday:false,onpicked:function(){searchList();}})" 
								              readonly="true" 
								              cssClass="Wdate"
								              onkeypress="return false;">
								</s:textfield>
								</s:if>
								</td>
								<td><a href="#" onclick="searchList()" class="sbutton">查询</a></td>
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
                                      <td width="30%" class="titleStyleZi"><s:text name="ocktime.info" /></td>
                                      <td width="69%"align="right">
                                      <s:if test="#session.perUrlList.contains('111_3_5_6_1')">
                                      <div class="buhuanhangbut"><a href="<s:url value='/ock/addTimeSetbefore.shtml' />" class="btnAdd" title="<s:text name="button.create" />"></a></div>
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
			        <div>
			          <div id="Below_new" align="center">
			            <s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/>
			          </div>
			          <%-- 
                       <table width="100%" cellspacing="0" class="reportInline2" >
                         <thead>
                            <tr>
                             <th scope="col" width="15%">考核月度</th>
							<th scope="col" width="15%">开始时间</th>
							<th scope="col" width="15%">结束时间</th>
							<th scope="col" width="15%">月度描述</th>
							<th scope="col"></th>
                            </tr>
                         </thead>
                         <tbody>
                           <s:iterator value="ocktimesetList" status="rowstatus">
                             <tr>
								<td>
								   <s:url id="editUrl" action="editTimeSetbefore">
				                   <s:param name="ocktimeset.check_time_id" value="check_time_id" />
				                   <s:param name="page">${page}</s:param>
				                   <s:param name="pageSize">${pageSize}</s:param>
			                       </s:url>
								<a href="${editUrl}"><s:property value="check_time_code" /></a>
								</td>
								<td><s:property value="start_time" /></td>
								<td><s:property value="end_time" /></td>
								<td><s:property value="check_time_desc" /></td>
								<td>&nbsp;</td>
							</tr>
                           </s:iterator>
                         </tbody>
                       </table>
                       <s:property value="pageBar" escape="false" />
                       --%>
                       
                       <table id="ocktimetbl" width="100%"  cellspacing="0">
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
</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="ocktimeset_js.jsp"%>
</body>
</html>