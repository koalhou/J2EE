<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
</head>
<body>
<div id="wrapper">
	    <%@include file="/WEB-INF/jsp/common/header.jsp"%>
	    <div id="content">
<div id="main">
<s:form id='alter_form' method="post" action="handmobile_edit" >
<s:hidden id="organization_id" name="handmobileinfo.organization_id" />
<s:hidden id="vehicle_id" name="handmobileinfo.vehicle_id"/>
   <table id="handmobiletable"  width="100%" border="0" cellspacing="0" cellpadding="0">
 		  <tr>
        <td  height="36"  valign="top">
			  <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
 				 <tr>
            		<td height="38" background="../images/contentTil_bg.gif"><span class="contentTil"><s:text name="menu3.handmobile" /></span></td>
         		 </tr>
			  </table>
        </td>
      </tr>
 		  	   <tr>
 		  	   <td align="center">
 		 <!-- message -->
               	<s:actionmessage cssStyle="font-size: 12px;color: #009900"
	              /><s:actionerror cssStyle="font-size: 12px;color: #CC0000"  />
               </td>
               </tr>
         <tr>
         <td valign="top">

        <table class="msgBox2" width="650" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="32"><span class="msgTitle">&nbsp;&nbsp;<s:text name="handmobile.editbefore.title" /></span></td>
          </tr>
          
          <tr>
            <td align="center">
            <table width="100%" border="0" cellspacing="6" cellpadding="5">
               <tr>
                <td  width="12%" height="25" align="right">设备编号:</td>
                <td  width="38%" height="25" align="left" class="fsBlack">
                 <s:property value="handmobileinfo.terminal_id"/>                 
                </td>
                <td  width="12%" height="25" align="right">手机IMEI号:</td>                                      
                <td  width="38%" height="25" align="left" class="fsBlack">                
                  <s:property value="handmobileinfo.vehicle_vin"/>  
                </td>
              </tr>
              
              <tr>
                <td height="25" align="right">手机号:</td>
                <td height="25" align="left" class="fsBlack">
                 <s:property value="handmobileinfo.cellphone"/>                 
                </td>
                <td  height="25" align="right"><s:text name="vehcileinfo.short_allname" />：</td>
                <td  height="25" align="left" >
					<s:textfield id="organizationName" name="handmobileinfo.organizationName" readonly="true" onclick="openorganizidtree()"/>
                	<span class="noticeMsg">*</span>
				</td>
			 </tr>
               <tr>
               <td height="25" align="right">使用者姓名:</td>
               <td height="25" align="left"><s:textfield id="user_name" name="handmobileinfo.user_name" maxlength="10"/></td>
				
                <td height="25" align="right">联系方式:</td>
                <td height="25" align="left"><s:textfield id="user_contact" name="handmobileinfo.user_contact" maxlength="11"/></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td class="btnBar">
             <s:url id="quxiao" action="handmobilemanage">
				<s:param name="page" value="page" />
				<s:param name="pageSize" value="pageSize" />	
			</s:url>
            <a href="#" onclick="goBack('handmobilemanage.shtml')" class="buttontwo"><s:text name="button.cancle" /></a>
            
            <s:if test="NULL!=handmobileinfo.organization_id ">
            <s:url id="cancle" action="handmobile_cancle">
				<s:param name="handmobileinfo.vehicle_id" value="handmobileinfo.vehicle_id" />
			</s:url>
			<s:if test="#session.perUrlList.contains('111_3_3_10_3') && 3!=#session.adminProfile.userType ">
			<a href="${cancle}" class="buttontwo">
			<s:text name="vehcileinfo.cancle" />
			</a>
			</s:if>
			</s:if>
			
			<s:if test="#session.perUrlList.contains('111_3_3_10_4') && 3!=#session.adminProfile.userType ">
            <a href="#" class="buttontwo" id="alterbutton" onClick="alterForm()"><s:text name="button.edit" /></a>
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
  <%@include file="handmobilealter_js.jsp"%>
</body>
</html>

