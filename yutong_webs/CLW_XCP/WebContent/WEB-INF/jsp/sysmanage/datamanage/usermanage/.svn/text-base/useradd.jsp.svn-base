<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body onload="show_enterprise_country();init()">
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<div id="main" style="overflow: auto;">
			<input type="hidden"  id="entiID" name="entiID" value="<s:property value='#session.adminProfile.entiID'/>" />
   			<s:form id="adduserform" action="/usm/useraddAction" method="post"> 
    			<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top">
							<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
							<tr>
							<td height="38" background="../images/contentTil_bg.gif"><span class="contentTil"><s:text name="menu3.yhgl" /></span></td>
							</tr>
			  				</table>
        				</td>
      				</tr>
      				<tr>
					<td  valign="top">
                    	<table class="msgBox4" width="100%" border="0" cellspacing="0" cellpadding="0">
                      	<tr>
			            	<td height="32"><span class="msgTitle">&nbsp;&nbsp;<s:text name="userinfo.denglu" /></span></td>
			          	</tr>
                      	<tr>
                        	<td height="90">
                          		<table width="100%" border="0" cellspacing="5" cellpadding="0">
                            	<tr>
                              		<td width="14%" height="28" align="right">用户名：</td>
                              		<td width="36%" class="fsBlack">
                              		<s:textfield id="loginName" name="optuserInfo.loginName" maxlength="15" cssStyle="ime-mode:disabled"/><span class="noticeMsg">*用户名只能为数字、字母、下划线或其组合</span>                              </td>
                            	</tr>
                            	<tr>
                              		<td width="14%" align="right">用户密码：</td>
                              		<td width="36%" class="fsBlack"><s:password  id="loginPwd" name="optuserInfo.loginPwd"/><span class="noticeMsg">*</span></td>
                            	</tr>
                           		<tr>
                              		<td width="14%" align="right">确认密码：</td>
              				  		<td width="36%" class="fsBlack">
                					<s:password id="confirmPassword" /><span class="noticeMsg">*</span></td>
                            	</tr> 
                          		</table>
                        	</td>
                      	</tr>
                      	<tr>
			            	<td height="32" class="msgBox5"><span class="msgTitle">&nbsp;&nbsp;<s:text name="userinfo.information" /></span>&nbsp;&nbsp;</td>
			          	</tr>
                      	<tr>
                        	<td height="90">
                          		<table width="100%" border="0" cellspacing="5" cellpadding="0">
                            		<tr>
                            			<td  width="14%" height="28" align="right">用户类型：</td>
	                            		<td width="36%" class="fsBlack">
	                            		<select id="userType" name="optuserInfo.userType" onchange="chezhuonchange()" style="width:120px;">
	                            		<option value="1">系统用户</option>                         
	                            		<option value="5">家长用户</option>
	                            		</select>
	                            		</td>                                                  
                               			<td width="14%" height="28" align="right">用户角色：</td>
                              			<td width="36%" class="fsBlack">
                              			<s:textfield id="roleName" name="optuserInfo.roleName" onclick="openrolewindow()" readonly="true"/>
                              			<s:hidden id="roleId" name="optuserInfo.roleId"></s:hidden><span class="noticeMsg">*</span>                              	
                              			</td> 
                            		</tr> 
                            		<tr style="display:none" id ="student_choose">
                             			<td width="14%" height="28" align="right">关联学生：</td>
                             			<td class="fsBlack" colspan="2">
                              			<s:textarea id="stu_name" name="optuserInfo.stu_name" onclick="choiceStudent()" cols="30" rows="6" readonly="true" />
                              			<s:hidden id="stu_id" name="optuserInfo.stu_id"></s:hidden><span class="noticeMsg">*</span>                              	
                             			</td>                            
                            		</tr>                           
                            <tr>                          
                            	<td width="14%" height="28" align="right">姓名：</td>
                              	<td width="36%" class="fsBlack">
                              	<s:textfield id="userName" name="optuserInfo.userName" maxlength="16"/><span class="noticeMsg">*</span></td>
                               	<td width="14%" height="28" align="right">组织结构：</td>
                              	<td width="36%" class="fsBlack">
                              	<s:textfield id="organizationName" name="optuserInfo.organizationName" readonly="true" onclick="openorganizidtree()"/>
                              	<s:hidden id="organizationID" name="optuserInfo.organizationID"></s:hidden><span class="noticeMsg">*</span>
                              	</td>                          
                            </tr>                      
                            <tr>
                            	<td width="14%" height="28" align="right">性别：</td>
                              	<td width="36%" class="fsBlack"><s:select name="optuserInfo.sex" list="sexSysMap" cssStyle="width:120px;"> </s:select></td>     
                              	<td  width="14%" height="28" align="right">身份证：</td>
                              	<td width="36%" class="fsBlack" colspan="2">
                              	<s:textfield id="idCard" name="optuserInfo.idCard" maxlength="18"/><span class="noticeMsg"></span></td> 
                            </tr>
                            <tr>
                            	<td width="14%" height="28" align="right">出生日期：</td>
                              	<td width="36%" class="fsBlack">
								<s:textfield id="birth" name="optuserInfo.birthday" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate"></s:textfield>
                              	</td>
                              	<td  width="14%" height="28" align="right">国家：</td>
                              	<td width="36%" class="fsBlack">
                              	<s:select cssStyle="width:120px;" id="countyID" name="optuserInfo.countyID" list="countryInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_province()"></s:select><span class="noticeMsg">*</span>
                              	</td>
                            </tr>
                            <tr>
                            	<td width="14%" height="28" align="right">省：</td>
                              	<td width="36%" class="fsBlack">
                              	<s:select cssStyle="width:120px;" id="provinceID" name="optuserInfo.provinceID" list="provinceInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_city()"></s:select><span class="noticeMsg">*</span>
                              	</td >
                              	<td  width="14%" height="28" align="right">市：</td>
                              	<td width="36%" class="fsBlack">
                              	<s:select cssStyle="width:120px;" id="cityID" name="optuserInfo.cityID" list="cityInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_city();"></s:select><span class="noticeMsg">*</span></td>
                            </tr>
                            <tr>
                              <td width="14%" height="28" align="right">移动电话：</td>
                              <td width="36%" class="fsBlack">
                              <s:textfield id="moblie" name="optuserInfo.moblie" maxlength="32"/><span class="noticeMsg"></span></td>
                              <td  width="14%" height="28" align="right">固定电话：</td>
                              <td width="36%" class="fsBlack" colspan="2">
                              <s:textfield id="tel" name="optuserInfo.tel" maxlength="16"/><span class="noticeMsg">(区号-电话)</span></td>
                            </tr>
                            <tr>
                              <td width="14%" height="28" align="right">电子邮件：</td>
                              <td width="36%" class="fsBlack">
                              <s:textfield id="email" name="optuserInfo.email" maxlength="52"/><span class="noticeMsg"></span></td>
                              <td  width="14%" height="28" align="right">职务：</td>
                              <td width="36%" class="fsBlack">
                              <s:textfield id="duty" name="optuserInfo.duty" maxlength="16"/>
                              </td>
                            </tr>  
                            <tr height="25px" id="parent_user">
                                 <s:hidden id="student_flag" name="optuserInfo.student_flag"></s:hidden>    
                                 <td align="right" width="16%" style="display:none" id="student_pwd_title">管理学生密码：</td>
                                 <td class="fsBlack" width="36%" style="display:none" id="student_pwd_td" colspan="3"><s:password  id="student_pwd" name="optuserInfo.student_pwd"/>
                                 <span class="noticeMsg">*</span></td>                                                       
                            </tr>
                          </table>
                        </td>
                      </tr>
                       <tr>
           			 <td class="btnBar"><a href="#" onclick="goBack('usermanageAction.shtml')" class="button">取消</a> <a href="#" onclick="submitForm()" class="button">确定</a></td>
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
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/UserNameSameDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/EntiDwr.js'></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<%@include file="addUser_validate.jsp"%>
</body>
</html>