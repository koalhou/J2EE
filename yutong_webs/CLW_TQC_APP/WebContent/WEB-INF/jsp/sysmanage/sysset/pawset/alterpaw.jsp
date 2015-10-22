<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="menu3.xgmm" /></title>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<style type="text/css">

body { 
    background: #f8f8f8;
	color: #555; 
	font: 12px/180% Tahoma,Arial,"宋体","微软雅黑";
	margin: 0;
	width:350px;
	min-width:350px; 
}
.msgBoxPass{
    margin:10px auto;	
	background: #eee url(../images/msg_title_bg.gif) repeat-x top left;
	border: 1px solid #b8b8b8;
	padding: 0 8px;
	width: 350px;
}
 </style>


</head>

<body >
  <s:form id="alter_pawform" action="pawSet" method="post">
  <table id="contenttable" width="400" border="0" cellspacing="0" cellpadding="0" align="center">
 		 <tr>
  			<td valign="top">
              
        </td>       
       </tr>        
       <tr>
        <td align="center">      
    <table class="msgBoxPass"   border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>">
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
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td height="30" align="right"><s:text name="password.old_pass" />：</td>
                              <td width="60%" align="left"><label><input type="password" id="oldPass" name="oldPass" onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/></label></td>
                              <td>&nbsp;</td>
                            </tr>
                            <tr>
                              <td height="30" align="right"><s:text name="password.new_pass1" />：</td>
                              <td width="60%" align="left"><label><input type="password" id="newPass1" name="newPass1" onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/></label></td>
                              <td>&nbsp;</td>
                            </tr>
                            <tr>
                              <td height="30" align="right"><s:text name="password.new_pass2" />：</td>
                              <td width="60%" align="left"><label><input type="password" id="newPass2" name="newPass2" onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/></label></td>
                              <td>&nbsp;</td>
                            </tr>

          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
            <a href="<s:url value='/pawset/alterpaw.shtml' />" class="buttontwo"><s:text name="button.chongzhi" /></a>
           <a href="#" class="buttontwo" onClick="submitalterForm()"><s:text name="button.edit" /></a>
        </td>
      </tr>
    </table>
        </td>       
       </tr> 
 </table>
 </s:form>
 
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>
<%@include file="alterpaw_validate.jsp"%>
</body>
</html>

