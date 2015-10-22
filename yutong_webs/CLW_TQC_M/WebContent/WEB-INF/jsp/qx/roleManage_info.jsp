<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<title><s:property value="getText('qx.menu')"/>&nbsp;|&nbsp;<s:property value="getText('rolemanage.menu')"/></title>
</head>
<body>

<%@include file="../common/menu.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
function submitPostFrom(){
	var f1=checkROLE_NAME();
	
	var f2=checkROLES_STR();
	if(f1&&f2){
		
		if(confirm("<s:property value="getText('confirm.modify')" />")) {
	        Wit.commitAll($('clwForm'));
	    }
	}else  {
	  return false;
	}
}
function submitPostDelFrom(){
	  if(confirm("<s:property value="getText('confirm.delete')" />")) {
		  var fm=$('clwForm');
		  fm.action="<s:url value='/qx/rolemanage_do_del.shtml' />";
		  Wit.commitAll(fm);
	  }else{
		  return false;
	}
}
/** 初始化样式 **/
function setFormMessage() {
	<s:if test="roleDataInfo.apply_id==\"0\"">
	Mat.setDefault($('ROLE_NAME'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('REMARK'),'');
	</s:if>
}
/** 加载事件 **/
function setFormEvent() {
	<s:if test="roleDataInfo.apply_id==\"0\"">
	  $('ROLE_NAME').onkeyup = checkROLE_NAME;
	  $('ROLE_NAME').onblur = checkROLE_NAME;
	</s:if>
}
/** 角色名验证 **/
function checkROLE_NAME(){
  var elm = $('ROLE_NAME');
  if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	    return false;
	}
	if(Wit.checkErr(elm,/[%"'|\\\/<>,]/)){
		Wit.showErr(elm, "<s:property value="getText('rolemanage.js.info.notexchar')"/>");
	    return false;
	}
	if(!Mat.checkLength(elm,30)){
		Wit.showErr(elm, "<s:property value="getText('rolemanage.js.info.lenles30')"/>");
	    return false;
	}

  Mat.showSucc(elm);
  return true;
}
/**角色选择验证**/
function checkROLES_STR(){
	var elm = $('ROLES_STR');
	var ids = '';
	if (checkBoxs == null) {
		checkBoxs = document.getElementsByName("roleTreeCheckBox");
	}
	if (!checkBoxs || checkBoxs.length == 0) {
		Wit.showErr(elm, "<s:property value="getText('rolemanage.js.info.needRoleAtLeft')"/>");
		return false;
	}
	for ( var i = 0; i < checkBoxs.length; i++) {
		var temp = checkBoxs[i];
		if (temp.checked == true) {
			ids += jQuery(temp).attr("module_id")+",";//temp.module_id + ',';
		}
	}
	if(ids==''){
		Wit.showErr(elm, "<s:property value="getText('rolemanage.js.info.needRoleAtLeft')"/>");
		return false;
		
	}
	document.getElementById('ROLES_STR').value=ids.substring(0, ids.length - 1);
	Mat.showSucc(elm,"<s:property value="getText('rolemanage.js.info.roleSucc')"/>");
	return true;
}
window.addEvent('domready', setFormEvent);
window.addEvent('domready', setFormMessage);

</script>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" height="628">
	<tr>
		<td width="220" valign="top" class="treeline">

		<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="36" background="../images/tree_tabBg.gif"
					class="titleStyleZi"><s:property value="getText('rolemanage.info.roleDis')"/></td>
			</tr>
			<!--<tr id="treeTr1" height="592px">
				<td valign="top"><div style="width: 220px; overflow: auto; height:592px;"><%@include file="../treeSpan.jsp"%></div></td>
			</tr>-->
			<tr id="treeTr1" height="592px">
				<td valign="top" nowrap="nowrap"><div style="width: 220px; overflow: auto; height:592px;"><table border="0" cellpadding="0"	cellspacing="8" >
											<tr id="treeTr1">
												<td valign="top" nowrap="nowrap" style=" padding:0 10px 0 0;"><%@include file="../treeSpan.jsp"%></td></tr></table></div></td>
			</tr>
		</table>

		</td>
		<td valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="36" valign="top" background="../images/tree_tabBg.gif">
				<div class="contentTil"><s:property value="getText('rolemanage.info.roleInfo')"/></div>
				</td>
			</tr>
		</table>
		<s:form id="clwForm" name="clwForm"
			action="rolemanage_do_edit" method="post">
			<s:hidden id="page" name="page" />
			<s:hidden id="pageSize" name="pageSize" />
			<input type="hidden" id="ChooseModID_tree" name ="ChooseModID_tree" value="<s:property value="ChooseModID_tree" />">
			
			<input type="hidden" id="ROLE_ID" name ="ROLE_ID" value="<s:property value="role_id" />">
			<input type="hidden" id="role_id" name ="role_id" value="<s:property value="role_id" />">
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <td align="right" bgcolor="#F6F6F6">
                   <div id="message">
                     <s:actionerror theme="mat" />
                     <s:fielderror theme="mat"/>
                     <s:actionmessage theme="mat"/>
                   </div>
                 </td>
               </tr>
             </table>
			<table class="msgBox2" width="650" border="0" align="center"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="32" background="../images/msg_title_bg.gif"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('rolemanage.info.roleInfo')"/>(<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)</span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td  align="right"></td>
							<td colspan="4" align="left"><input type="hidden" id="ROLES_STR" name ="ROLES_STR" value=""></td>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('rolemanage.info.ROLE_NAME')"/>：</td>
							<td width="50%" class="fsBlack" align="left"> <label>
							<s:if test="roleDataInfo.apply_id==\"0\"">
							<input type="text" id="ROLE_NAME" name="ROLE_NAME" maxlength="30" value="<s:property value="roleDataInfo.role_name" />">
							</s:if>
							<s:else>
							<s:property value="roleDataInfo.role_name" />
							</s:else>
							
							</label> </td>
							<td>&nbsp;</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('rolemanage.info.REMARK')"/>：</td>
							<td colspan="4" align="left"><label> 
							
							<s:if test="roleDataInfo.apply_id==\"0\"">
							<input type="text" id="REMARK" name="REMARK" size="60"  maxlength="60"  value="<s:property value="roleDataInfo.remark" />"> 
							</s:if>
							<s:else>
							<s:property value="roleDataInfo.remark" />
							</s:else>
							</label></td>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td height="28" align="right"><s:property value="getText('illdrive.info.full_name')"/>：</td>
							<td colspan="4" align="left"><label> <s:property value="roleDataInfo.full_name" /> </label></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('rolemanage.apply_id')"/>：</td>
							<td colspan="4" align="left"><label>
							<s:if test="roleDataInfo.apply_id==0">
							<s:property value="getText('apply_id_0')"/>
							</s:if>
							<s:elseif test="roleDataInfo.apply_id==1">
							<s:property value="getText('apply_id_1')"/>
							</s:elseif>
							<s:elseif test="roleDataInfo.apply_id==2">
							<s:property value="getText('apply_id_2')"/>
							</s:elseif>
							
							</label></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('info.creater')"/>：</td>
							<td colspan="4" align="left"><label> <s:property value="roleDataInfo.creater" /> </label></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('info.create_time')"/>：</td>
							<td colspan="4" align="left"><label> <s:property value="roleDataInfo.create_time" /> </label></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('info.modifier')"/>：</td>
							<td colspan="4" align="left"><label> <s:property value="roleDataInfo.modifier" /> </label></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('info.modify_time')"/>：</td>
							<td colspan="4" align="left"><label> <s:property value="roleDataInfo.modify_time" /> </label></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td  align="right"></td>
							<td colspan="4" align="left"></td>
							<td>&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class="btnBar">
					<a href="<s:url value='/qx/rolemanage.shtml' />" class="buttontwo"><s:property value="getText('btn.back')"/></a>
					<s:if test="roleDataInfo.apply_id==\"0\"">
					<s:if test="#session.perUrlList.contains('111_0_1_4_4')">
					<a href="#" onclick="submitPostDelFrom();" class="buttontwo"><s:property value="getText('btn.delete')"/></a>
					</s:if>
					<s:if test="#session.perUrlList.contains('111_0_1_4_3')">
					<a href="#" class="buttontwo" onclick="submitPostFrom();"><s:property value="getText('btn.update')"/></a>
					</s:if>
					</s:if>
						
					<s:else></s:else>
					
					 
					</td>
				</tr>
			</table>
		</s:form></td>
	</tr>
</table>

<%@include file="../common/copyright.jsp"%>

</body>
</html>