<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<style type="text/css">
*{
font-family:'Microsoft Yahei', 微软雅黑, 宋体,Tahoma, Arial, Verdana;
}
</style>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body>
<div id="wrapper">
  <%@include file="/WEB-INF/jsp/common/header.jsp"%>
	    <div id="content">
<div style="overflow:auto;">
<table id="contenttable" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	<td id="showTree" width="250" valign="top" class="treeline">
	    
		
			<table  width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" background="../images/tree_tabBg.gif"
						class="titleStyleZi"><s:text name="common.zhuzhijg" />
					</td>
					<td background="../images/tree_tabBg.gif" align="right">
						<div class="searchHide"><a class="btnHide" onClick="treeView()" href="#"></a></div>
					</td>
				</tr>
				<tr id="treeTr1" >
					<td valign="top" colspan="2">
				      <div class="fanwei2" id="treezuo">
						<table border="0" cellpadding="0" cellspacing="8">
							<tr id="treeTr1">
								<td valign="top" nowrap="nowrap"><%@include
													file="/WEB-INF/jsp/treeSpan.jsp"%></td>
								</tr>
						</table>
					 </div>
					</td>
			  </tr>
			</table>	
	</td>
	<td width="24" valign="top" bgcolor="#E9E9E9" style="display:none;" id="showBar">
		<div id="treehide" class="searchHide2" height="30"><a onClick="treeView()" id="Image2" class="btnTreeVisible" href="#"></a></div>
	</td>
	<td valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="36" valign="top" background="../images/tree_tabBg.gif">
				<div class="toolbar">
				<div class="contentTil">组织结构设置</div>
				<div class="tool">
				<ul>
					<li><a href="#" onClick="do_del()" class="current" id="en_del"><s:text name="button.delete" /></a></li>
					<li><a href="#" onClick="goto_edit()" class="current" id="en_edit"><s:text name="button.edit" /></a></li>
					<li><a href="#" onClick="goto_add()" class="current"  id="en_creat"><s:text name="button.create" /></a></li>
				</ul>
				</div>
				</div>
				</td>
			</tr>
		</table>
		<s:form id="clwForm" name="clwForm" action="entimanage_goto_add.shtml" method="post">
			<input type="hidden" id="ChooseEnterID_tree" name="ChooseEnterID_tree" value="<%=session.getValue("ChooseEnterID_tree")%>">
			<input type="hidden" id="ChooseEnterID_edit" name="ChooseEnterID_edit">
			<s:hidden id="userenid" name="userenid"/>
			<input type="hidden" id="CARSNUMS">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right">
              <div id="message">
                <s:actionerror theme="mat" />
                <s:fielderror theme="mat"/>
                <s:actionmessage theme="mat"/>
              </div>
            </td>
          </tr>
          <tr>
            <td align="center">
				<span id="ChooseEnterID_edit_show" class="error"></span>
            </td>
          </tr>
        </table>
		</s:form>
		<table width="650" border="0" align="center"
			cellpadding="0" cellspacing="0" class="msgBox2">
			<tr>
				<td height="32"><span
					class="msgTitle">&nbsp;&nbsp;<s:text name="enterprise.info" /></span></td>
			</tr>
			<tr>
				<td align="center">
				<table width="600" border="0" cellpadding="0" cellspacing="0">
					<tr>
					  <td width="11" align="right" valign="top">&nbsp;</td>
					  <td width="100" height="28" align="right" valign="top" style="white-space:nowrap;"><s:text name="enterprise.info.FULL_NAME" />:</td>
					  <td width="165" align="left" valign="top" ><label id="FULL_NAME"> </label></td>
						<td width="12" valign="top">&nbsp;</td>
					  <td width="97" height="28" align="right" valign="top" style="white-space:nowrap;"><s:text name="enterprise.info.ADDRESS" />:</td>
					  <td width="246" align="left" valign="top"><label id="ADDRESS"> </label></td>
					</tr>
					<tr>
					  <td align="right" valign="top">&nbsp;</td>
					  <td height="28" align="right" valign="top"><s:text name="enterprise.info.SHORT_NAME" />:</td>
					  <td align="left" valign="top"><label id="SHORT_NAME"> </label></td>
						<td valign="top">&nbsp;</td>
						<td height="28" align="right" valign="top"><s:text name="enterprise.info.ENTERPRISE_COUNTRY" />:</td>
					  <td align="left" valign="top"><label id="ENTERPRISE_COUNTRY"> </label></td>
					</tr>
					<tr>
					  <td align="right" valign="top">&nbsp;</td>
					  <td align="right" valign="top"><s:text name="enterprise.info.ENTERPRISE_PROVINCE" />:</td>
					  <td align="left" valign="top"><label id="ENTERPRISE_PROVINCE"> </label></td>
						<td valign="top">&nbsp;</td>
						<td height="28" align="right" valign="top"><s:text name="enterprise.info.ENTERPRISE_CITY" />:</td>
					  <td align="left" valign="top"><label id="ENTERPRISE_CITY"> </label></td>
					</tr>
					<tr>
					  <td align="right" valign="top">&nbsp;</td>
					  <td height="28" align="right" valign="top"><s:text name="enterprise.info.EMAIL" />:</td>
					  <td align="left" valign="top"><label id="EMAIL"> </label></td>
						<td valign="top">&nbsp;</td>
						<td align="right" valign="top"><s:text name="enterprise.info.POSTCODE" />:</td>
					  <td align="left" valign="top"><label id="POSTCODE"> </label></td>
					</tr>
					<tr>
					  <td align="right" valign="top">&nbsp;</td>
					  <td height="28" align="right" valign="top"><s:text name="enterprise.info.CONTACT_P" />:</td>
					  <td align="left" valign="top"><label id="CONTACT_P"> </label></td>
						<td valign="top">&nbsp;</td>
						<td align="right" valign="top"><s:text name="enterprise.info.CONTACT_TEL" />:</td>
					  <td align="left" valign="top"><label id="CONTACT_TEL"> </label></td>
					</tr>
					<tr>
					  <td align="right" valign="top">&nbsp;</td>
					  <td height="28" align="right" valign="top"><s:text name="enterprise.info.ENTERPRISE_DESC" />:</td>
					  <td colspan="5" align="left" valign="top"><label> <textarea
							id="ENTERPRISE_DESC" name="ENTERPRISE_DESC" cols="67" rows="6"
							readonly="readonly"></textarea></label>
					  </td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
	  </table>
	</td>
	</tr>
</table>
</div>		
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
  <script type='text/javascript' src='../dwr/engine.js'></script>
  <script type='text/javascript' src='../dwr/util.js'></script>
  <script type='text/javascript' src='../dwr/interface/EntiDwr.js'></script>
  <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
  <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
  <%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
  <%@include file="entiManage_js.jsp"%>
</body>
</html>

