<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html >
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body onload="bodyload()">
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<div id="main" style="overflow: auto;">
 <s:form id="updateuserform" action="/usm/userupdateAction" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    	<tr>
        <td valign="top">
			  <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
 				 <tr>
            		<td height="38" class="changtiao2"><span class="contentTil"><s:text name="menu3.yhgl" /></span></td>
         		 </tr>
			  </table>
        </td>
      </tr>
     <tr>
	  <td valign="top">
          
                 
                    <table class="msgBox2" width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
			            <td height="25"><span class="msgTitle">&nbsp;&nbsp;<s:text name="userinfo.update" /></span></td>
			          </tr>
                      <tr>
                        <td height="90">
                          <table width="100%" border="0" cellspacing="5" cellpadding="0">
                            <tr>
                            <td  width="14%" height="28" align="right">用户类型：</td>
                            <td width="36%" class="fsBlack">
	                            
	                            <s:select name="userinfo.userType" list="userTypeSysMap" id="userType" accesskey="userType" onchange="chezhuonchange()"> </s:select>
	                        </td>
	                        <!-- 管理密码 
	                        <s:hidden id="student_flag" name="userinfo.student_flag"/>
	                        <s:if test="userinfo.student_flag==0">
	           			       <td align="right" width="14%" id="student_pwd_title">学生管理密码：</td>
                               <td class="fsBlack" width="36%">                             
                                 <s:password id="student_pwd" name="userinfo.student_pwd" showPassword="true"/>
                                 <input type="password" id="student_pwd" name="userinfo.student_pwd"/>
                                 <span class="noticeMsg">*</span>
                               </td> 
	           			    </s:if> -->
                            </tr>
                            <tr style="display:none" id ="student_choose">
                             <td width="14%" height="28" align="right">关联学生：</td>
                             <td class="fsBlack" colspan="2">
                              <s:textarea id="stu_name" name="userinfo.stu_name" onclick="choiceStudent()" cols="30" rows="6" readonly="true" />
                              <s:hidden id="stu_id" name="userinfo.stu_id"></s:hidden><span class="noticeMsg">*</span>                              	
                             </td>                            
                            </tr>    
                            <tr>
                            
                              <td width="14%" height="28" align="right">用户名：</td>
                              <td width="36%" class="fsBlack">
                              <s:textfield id="loginName" name="userinfo.loginName" readonly="true"/>
                              <s:hidden id="userID" name="userinfo.userID"/>
                              </td>
                             
                              <td width="14%" align="right">姓名：</td>
                              <td width="36%" class="fsBlack">
                              
                              <s:textfield id="userName" name="userinfo.userName" maxlength="16"/> <span class="noticeMsg">*</span></td>
                              
                            </tr>
                            <tr>
                               <td width="14%" height="28" align="right">组织结构：</td>
                              <td width="36%" class="fsBlack">
                              <s:textfield id="organizationName" name="userinfo.organizationName"  readonly="true" onclick="openorganizidtree()"/>
                              <s:hidden id="organizationID" name="userinfo.organizationID"/><span class="noticeMsg">*</span>
                              </td>
                              
                              
                               <td width="14%" height="28" align="right">用户角色：</td>
                              <td width="36%" class="fsBlack">
                              <s:textfield id="roleName" name="userinfo.roleName" readonly="true" onclick="openrolewindow()"/>
                              <s:hidden name="userinfo.roleId" id="roleId"/><span class="noticeMsg">*</span>
                              	
                              </td>
                              

                            </tr>
                            <tr>
                              
                              
                               <td width="14%" height="28" align="right">职务：</td>
                              <td width="36%" class="fsBlack"><s:textfield id="duty" name="userinfo.duty" maxlength="16"/></td>
                              
                             
                              <td width="14%" height="28" align="right">出生日期：</td>
                              <td width="36%" class="fsBlack">
                              <s:textfield id="birthday" name="userinfo.birthday" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate"></s:textfield></td>
                              
                              
                            </tr>
                            <tr>
                            <td width="14%" height="28" align="right">性别：</td>
                              <td width="36%" class="fsBlack">
                              <s:select name="userinfo.sex" list="sexSysMap" > </s:select></td>
                              
                              <td width="14%" height="28" align="right">身份证：</td>
                              <td width="36%" class="fsBlack" colspan="2">
                              <s:textfield id="idCard" name="userinfo.idCard" maxlength="18"/><span class="noticeMsg"></span></td>
                              
                              
                            </tr>
                            <tr>
                              <td  width="14%" height="28" align="right">国家：</td>
                              <td width="36%" class="fsBlack">
                              <s:select cssStyle="width:120px;" id="countyID" name="userinfo.countyID" list="countryInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_province()"></s:select><span class="noticeMsg">*</span>
                              </td>
                              <td  width="14%" height="28" align="right">省：</td>
                              <td width="36%" class="fsBlack">
                              <s:select cssStyle="width:120px;" id="provinceID" name="userinfo.provinceID" list="provinceInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_city()"></s:select><span class="noticeMsg">*</span>
                              </td>
                              
                            </tr>
                            <tr>
                            <td  width="14%" height="28" align="right">市：</td>
                             <td width="36%" class="fsBlack">
                              <s:select cssStyle="width:120px;" id="cityID" name="userinfo.cityID" list="cityInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_city();"></s:select><span class="noticeMsg">*</span></td>
                            
                              <td width="14%" height="28" align="right">移动电话：</td>
                              <td width="36%" class="fsBlack">
                              <s:textfield id="moblie" name="userinfo.moblie" maxlength="32"/><span class="noticeMsg"></span></td>                        
                            </tr>
                            <tr>
                             <td width="14%" height="28" align="right">固定电话：</td>
                              <td width="36%" class="fsBlack"><s:textfield id="tel" name="userinfo.tel" maxlength="16"/><span class="noticeMsg">(区号-电话)</span></td>
                              <td width="14%" height="28" align="right">电子邮件：</td>
                              <td width="36%" class="fsBlack"><s:textfield id="email" name="userinfo.email" maxlength="52"/><span class="noticeMsg"></span></td>                  
                            </tr> 
                            
                            <tr>
                                <s:if test="userinfo.student_flag==0">
                                 <td width="14%" align="right" id="student_reset_title">重置管理密码：</td>
                                 <td width="36%" class="fsBlack" id="student_reset_td">                               
                                 <input type="checkbox" id="stmg" onclick="choosestmg()"/>
                                 <s:hidden id="student_flag" name="userinfo.student_flag"/>
                                 </td>
                             	</s:if>
                             	<s:if test="userinfo.student_flag==1">                       
                                   <input type="checkbox" id="stmg" style="display:none"/>
                                   <s:hidden id="student_flag" name="userinfo.student_flag"/>
                             	</s:if>
                             </tr>
                             <tr height="25px">        
                             	 <s:hidden id="pwd_update_flag" name="userinfo.pwd_update_flag"/>                 	
                                 <td align="right" width="16%" style="display:none" id="student_pwd_title">管理学生密码：</td>
                                 <td class="fsBlack" width="36%" style="display:none" id="student_pwd_td" colspan="3">
                                 <s:password  id="student_pwd" name="userinfo.student_pwd"/><span class="noticeMsg">*</span>
                                 </td>                                                       
                            </tr>    
                          </table>
                        </td>
                      </tr>
                       <tr>
           			 <td class="btnBar">
           			 <a href="#" onclick="goBack('usermanageAction.shtml')" class="button">取消</a> 
           			
           			 <s:if test="#session.perUrlList.contains('111_3_5_2_6')">
	           			 <s:if test="userinfo.valideFlg==0">
	           			 <a id="jinyong" href="#" onclick="postValidflag('2')" class="button">禁用</a>
	           			 <a id="qiyong" style="display:none" href="#" onclick="postValidflag('0')" class="button">启用</a>
	           			 </s:if>
           			 </s:if>
           			  <s:if test="#session.perUrlList.contains('111_3_5_2_5')">
	           			  <s:if test="userinfo.valideFlg==2">
	           			  <a id="jinyong" style="display:none" href="#" onclick="postValidflag('2')" class="button">禁用</a>
	           			 <a  id="qiyong" href="#" onclick="postValidflag('0')" class="button">启用</a>
	           			 </s:if>
           			 </s:if>
           			 <s:if test="#session.perUrlList.contains('111_3_5_2_2')">
           			 <a href="#" onclick="submitForm()" class="button">修改</a>
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
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/updateValidflagDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/UserNameSameDwr.js'></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<%@include file="updateUser_validate.jsp"%>
</body>
</html>