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
<s:form id="handmobile_form" action="handmobilemanage" method="post">
<table  width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td  valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="36" valign="top" background="../images/tree_tabBg.gif">
				<div class="toolbar">
				<div class="contentTil"><s:text name="menu3.handmobile" /></div>
				
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
								<s:text name="handmobile.terminalid" />：
								<s:textfield id="terminal_id" name="terminal_id" cssClass="input150" maxlength="32" onkeypress="chaxun()"/>
								</td>
								
							<td>所属部门：</td>
                              <td class="fsBlack">
                              <s:textfield id="organizationName" name="optuserInfo.organizationName" readonly="true" onclick="openorganizidtree()"/>
                              <s:hidden id="organizationID" name="optuserInfo.organizationID"></s:hidden>
                              </td>
                                <s:if test="#session.perUrlList.contains('111_3_3_10_1')">
								<td>
								<a href="javascript:void(0);" onClick="searchList()" class="sbutton"><s:text name="common.chaxun" /></a>
								</td>
								</s:if>
								<s:if test="#session.perUrlList.contains('111_3_3_10_5')">
                                      <td><a href="#" onclick="save()" class="sbutton"><s:text name="vehcileinfo.cancle" /></a></td>
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
                                      <td width="30%" class="titleStyleZi"><s:text name="menu3.handmobile" /></td>
                                      
                                     <td  align="right">
                                      <div  class="buhuanhangbut">
                                      <s:if test="#session.perUrlList.contains('111_3_3_10_2') && 3 != #session.adminProfile.userType">
                                      <a href="<s:url value='/handmobile/addhandmobileb.shtml'/>" class="btnAllot" title="<s:text name="button.fenpei" />">
                                      </a>
                                      </s:if>
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
			        <div>
			        <div id="Below_new" align="center">
			         <s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/>
			        </div>
                       <table id="handtbl" width="100%"  cellspacing="0">
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
    <script type="text/javascript" language="JavaScript1.2"
			src="<s:url value='/scripts/flexigrid/flexigrid2.0.js' />"></script>
	<%@include file="/WEB-INF/jsp/infomanage/handmobilemanage/handmobilemanage_js.jsp"%>
</body>
</html>


