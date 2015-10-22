<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style type="text/css">
		a.btnLinkMap{ 
		  background: url(../images/qipaoimages/btn_c80.gif) no-repeat left top; 
		  color: #333; 
		  display: block; 
		  float: left; 
		  height: 26px;
		  _height: 20px; 
		  line-height: 26px; 
		  margin: 4px 5px; 
		  text-align: center; 
		  width: 80px; }
	</style>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<s:form id="smsmanage_form" action="" method="post">
		<table  width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="36" valign="top" background="../images/tree_tabBg.gif">
							<div class="toolbar">
								<div class="contentTil" style="width:90%">短信提醒</div>
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
								<s:if test="#session.perUrlList.contains('111_3_3_1_5_1')">
								<table border="0" cellspacing="4" cellpadding="0">
									<tr>
										<td>
										学号：
										<s:textfield id="student_code" name="studentInfo.student_code" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchSms();}"/>&nbsp;&nbsp;
										</td>
										<td>
										姓名：
										<s:textfield id="student_name" name="studentInfo.student_name" cssClass="input120" maxlength="16" onkeypress="if(event.keyCode==13){searchSms();}"/>&nbsp;&nbsp;
										</td>
										<td>
										学校：
										<s:textfield id="student_school" name="studentInfo.student_school" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchSms();}"/>&nbsp;&nbsp;
										</td>
										<td>
										班级：
										<s:textfield id="student_class" name="studentInfo.student_class" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchSms();}"/>&nbsp;&nbsp;
										</td>
										<td><a href="#" onclick="searchSms()" class="sbutton"><s:text name="common.chaxun" /></a></td>
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
				                               <td width="30%" class="titleStyleZi">短信提醒业务开通列表</td>
				                               <td width="69%" align="right">
				                                <s:if test="#session.perUrlList.contains('111_3_3_1_5_3')">
				                                	<div class="buhuanhangbut"><a href="<s:url value='/sms/addsmsbefore.shtml' />" class="btnAdd" title="<s:text name="button.create" />"></a></div>
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
<%@include file="smsmanage_js.jsp"%>
</body>
</html>