<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<div id="main" style="overflow: auto;">
		  <s:form id="alter_oilsetform" action="oilSet_edit" method="post">
		  <s:hidden name="page" />
		  <s:hidden name="pageSize" />
		  <s:hidden id="oilset.check_id" name="oilset.check_id" />
		  <s:hidden id="vehicle_ln" name="oilset.vehicle_ln" />
		  <s:hidden id="check_time_code" name="oilset.check_time_code" />
		  <s:hidden id="oilset.vehicle_vin" name="oilset.vehicle_vin" />
		  <s:hidden id="oilset.organization_id" name="oilset.organization_id"></s:hidden>
		  <s:hidden id="oilset.velTypeId" name="oilset.velTypeId"></s:hidden>
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
		
		               <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                  <tr>
		                  <td align="center"><s:actionmessage cssStyle="font-size: 12px;color: #009900"
			              /><s:actionerror cssStyle="font-size: 12px;color: #CC0000"  />
			             </td>
		                 </tr>
		               </table>
		               
					    <table class="msgBox2" width="650" border="0" align="center" cellpadding="0" cellspacing="0">
					      <tr>
					        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>">
					          <span class="msgTitle">&nbsp;&nbsp;<s:text name="oil.update" /></span>
					        </td>
					      </tr>
					      <tr>
					        <td align="center">
							   <table width="100%" border="0" cellspacing="0" cellpadding="0">
							    <tr>
					              <td width="30%" height="28" align="right">车牌号：</td>
					              <td width="70%" align="left">
					               <s:property value="oilset.vehicle_ln" />
					              </td>
					              <td>&nbsp;</td>
					            </tr>
					            <tr>
					              <td width="30%" height="28" align="right">车辆VIN号：</td>
					              <td width="70%" align="left">
					               <s:property value="oilset.vehicle_vin" />
					              </td>
					              <td>&nbsp;</td>
					            </tr>
					            <tr>
					          
					              <td width="30%" align="right">考核月度：</td>
					              <td width="70%" align="left">
					              <s:property value="oilset.check_time_code" />
					              
					              </td>
					            </tr>
					           <tr>
					              <td width="30%" height="28" align="right">考核油耗：</td>
					              <td width="70%" align="left">
					                <s:textfield id="check_value" name="oilset.check_value"  maxLength="10" />&nbsp;(单位：升/L)
					              </td>
					              <td>&nbsp;</td>
					            </tr>
					          </table>
					        </td>
					      </tr>
					      <tr>
					        <td class="btnBar"> 
					            <a href="#" onclick="goBack('oilSet.shtml')" class="buttontwo">取消</a>
					            <s:if test="#session.perUrlList.contains('111_3_5_7_3')">
					            <s:url id="delete" action="oilSet_delete">
									<s:param name="oilset.check_id" value="oilset.check_id" />
									<s:param name="oilset.velTypeId" value="oilset.velTypeId" />
									<s:param name="page" value="page" />
									<s:param name="pageSize" value="pageSize" />		
								</s:url>
								<a href="#" class="buttontwo" onclick="Wit.delConfirm('${delete}', '<s:text name="common.delete.confirm" />')">
								<s:text name="button.delete" /></a>
								</s:if>
							    <s:if test="#session.perUrlList.contains('111_3_5_7_2')">
					           <a href="#" class="buttontwo" onclick="submitalterForm()"><s:text name="button.edit" /></a>
					           </s:if>
					        </td>
					      </tr>
					    </table>
				</td>
			</tr>
		</table>
		  </s:form>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="oilset_validate.jsp"%>
<%@include file="editoilset_js.jsp"%>
</body>
</html>