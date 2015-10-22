<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<style type="text/css">
.biankuang {
	border: 1px solid #AAAAAA;
}
</style>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
</head>
<body>
<div id="wrapper">
	    <%@include file="/WEB-INF/jsp/common/header.jsp"%>
	    <div id="content">
<div id="main">
<s:form id='add_form' method="post" action="handmobile_add">
<s:hidden name="page" />
<s:hidden name="pageSize" />
<s:hidden id="organization_id" name="handmobileinfo.organization_id" />
    <table id="handmobiletable" width="100%" border="0" cellspacing="0" cellpadding="0">
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
 		 <td>
 		 <!-- message -->
               <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                  <td align="center"><s:actionmessage cssStyle="font-size: 12px;color: #009900"
	              /><s:actionerror cssStyle="font-size: 12px;color: #CC0000"  />
	             </td>
                 </tr>
               </table>
               </td>
               </tr>
	  <tr>
	  <td  align="center" valign="top">
	  <table  class="msgBox2" width="650" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="32" background="../images/msg_title_bg.gif"><span class="msgTitle">&nbsp;&nbsp;<s:text name="handmobile.add.info" /></span></td>
          </tr>
          <tr>
             <td align="center">
            <table width="100%" border="0" cellspacing="10" cellpadding="0">
              <tr>
                <td width="21%" align="right"><s:text name="vehcileinfo.org" />：</td>
                <td width="79%" align="left"  valign="top">
                	<s:textfield id="organizationName" name="handmobileinfo.organizationName" readonly="true" onclick="openorganizidtree()"/>
                	<span class="noticeMsg">*</span><s:hidden id="orgidtag" name="orgidtag"></s:hidden>
                </td>
			  </tr>
              
              <tr>
                <td width="21%" align="right"><s:text name="handmobile.add.info" />：</td>
                <td align="left">
				<table id="tableos" style="width: 100%;"
					align="left">
					<tr>
						<td>
						<s:select id="selectLeftos" name="selectLeftos" list="leftList" listKey="vehicle_id" listValue="vehicle_desc" size="10" multiple="true" cssStyle="width:210px;height:200px;" ondblclick="javascript:moveSelect(this, document.getElementById('selectRightos'),'os');"/>
						
						</td>
						<td class="tbQuerySel">
						<a href="#" class="sbutton" onClick="javascript:moveSelect(document.getElementById('selectLeftos'), document.getElementById('selectRightos'),'os');">></a>
						<a href="#" class="sbutton" onClick="javascript:deleteSelect(document.getElementById('selectLeftos'),document.getElementById('selectRightos'),'os');"><</a>
	     				<a href="#" class="sbutton" onclick="javascript:moveSelectAll(document.getElementById('selectLeftos'), document.getElementById('selectRightos'),'os');">>></a>
						<a href="#" class="sbutton" onclick="javascript:deleteSelectAll(document.getElementById('selectLeftos'), document.getElementById('selectRightos'),'os');"><<</a>
						
	     				</td>
						<td><select id="selectRightos" name="selectRightos"
							multiple="multiple" size="10" style="width: 210px;height:200px;" ondblclick="javascript:deleteSelect(document.getElementById('selectLeftos'),this,'os');"></select>
						<select id="ostype" name="handmobileinfo.selectveh" multiple="multiple"
							size="10" style="display: none" class="s"></select></td>
					</tr>
					<tr>
					  <td colspan="3" align="left"><table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><span class="noticeMsg">*</span><s:hidden id="ostag" name="ostag" /></td>
                  </tr>
                </table></td>
				  </tr>
				</table>				
				</td>
			  </tr>
            </table></td>
          </tr>
           <tr>
            <td class="btnBar">
             <s:url id="quxiao" action="vehiclemanage">
				<s:param name="page" value="page" />
				<s:param name="pageSize" value="pageSize" />	
			</s:url>
            <a href="#" onclick="goBack('handmobilemanage.shtml')" class="buttontwo"><s:text name="button.cancle" /></a> 
            <a href="#" class="buttontwo" id="addbutton" onclick="addForm()"><s:text name="button.queding" /></a></td>
           </tr>
        </table></td>
	  </tr>
    </table>
     </s:form>
</div>
</div>
    <%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
    <%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
    <%@include file="handmobileadd_js.jsp" %>
</body>
</html>
