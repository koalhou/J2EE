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
	<s:form id="studentmanage_form" action="" method="post">
	<table  width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top" background="../images/tree_tabBg.gif">
						<div class="toolbar">
							<div class="contentTil">员工信息</div>
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
									姓名：
									<s:textfield id="student_name" name="employeeInfo.student_name" cssClass="input120" maxlength="16" onkeypress="if(event.keyCode==13){searchStudent();}"/>&nbsp;&nbsp;
									</td>
									<td>
									员工号：
									<s:textfield id="student_code" name="employeeInfo.student_code" cssClass="input120" maxlength="14" onkeypress="if(event.keyCode==13){searchStudent();}"/>&nbsp;&nbsp;
									</td>
									<td>
									卡号：
									<s:textfield id="student_card" name="employeeInfo.student_card" cssClass="input120" maxlength="14" onkeypress="if(event.keyCode==13){searchStudent();}"/>&nbsp;&nbsp;
									</td>
									<td>
									所属组织：</td>
									<td class="fsBlack">
		                              <s:textfield id="organizationName" name="short_name" readonly="true" onclick="openorganizidtree()"/>
		                                <s:hidden id="organizationID" name="organization_id"></s:hidden>
									</td>
									
									<td><a href="#" onclick="searchStudent()" class="sbutton"><s:text name="common.chaxun" /></a></td>
									<td><a href="#" onclick="save()" class="sbutton">批量删除</a></td>
								</tr>
							</table>
					
							
							</td>
						</tr>
					</table>		
					</td>
				</tr>
				<tr>
				  
					        
					         
		                         <td class="titleStyle">
			                         <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                             <tr>
			                               <td width="30%" class="titleStyleZi">员工信息</td>
			                               <td width="69%" align="right">
			                               
			                                	<div class="buhuanhangbut"><a href="<s:url value='/employee/addemployeebefore.shtml' />" class="btnAdd" title="<s:text name="button.create" />"></a></div>
			                               
			                               
				                                <div class="buhuanhangbut">
				                                  <a href="#" onclick="exportStudent();" class="btnDaochu" title="<s:property value="getText('msg.export')" />">
				                                  </a>
				                                </div>
			                               
			                             
				                                <div class="buhuanhangbut">
				                                  <a href="<s:url value='/employee/importEmployeeBefore.shtml' />" class="btnInput" title="导入"></a>
				                                </div>
										
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
			<s:hidden id="studentInfo.student_code" name="studentInfo.student_code"/>
			<s:hidden id="studentInfo.organization_id" name="studentInfo.organization_id"/>
			<s:hidden id="studentInfo.student_card" name="studentInfo.student_card"/>
			<s:hidden id="studentInfo.student_name" name="studentInfo.student_name"/>
			
			<s:hidden id="studentInfo.sortname" name="studentInfo.sortname"/>
			<s:hidden id="studentInfo.sortorder" name="studentInfo.sortorder"/>
	</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="employeemanage_js.jsp"%>
</body>
</html>

