<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>

<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<link rel="stylesheet" href="<s:url value='/scripts/mktree.css' />" type="text/css" media="all" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>

</head>

<body>
<div id="wrapper">						
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>					
	<div id="content">					


			<div id="main" style="overflow: auto;">
			<s:form id="updateRoleform" action="updateRoleAction.shtml"	method="post">
			<input type="hidden" id="ChooseModID_tree" name ="ChooseModID_tree" value="<s:property value="ChooseModID_tree" />">
			<input type="hidden" id="ROLE_ID" name ="ROLE_ID" value="<s:property value="role_id" />">
			<input type="hidden"  id="entiID" name="entiID" value="<s:property value='#session.adminProfile.entiID'/>" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td valign="top">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="36" valign="top" background="../images/tree_tabBg.gif">
									<div class="toolbar">
									<div class="contentTil"><s:text name="menu3.jsgl" /></div>
									
									</div>
									</td>
								</tr>
							</table>
						
			                                      
							<table class="msgBox2" width="650" border="0" align="center"
								cellpadding="0" cellspacing="0">
								<tr>
									<td height="32" background="../images/msg_title_bg.gif"><span
										class="msgTitle">&nbsp;&nbsp;<s:text name="roleinfo.update" /></span></td>
								</tr>
								<tr>
									<td align="center">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td  align="right"></td>
														<td><input type="hidden" id="ROLES_STR" name ="ROLES_STR" value=""/></td>
													</tr>
													<tr>
														<td width="14%" align="right" height="28" >角色名称:</td>
														<td width="86%" align="left"><input type="text" id="ROLE_NAME" name="ROLE_NAME" maxlength="32" value="<s:property value="role.role_name" />"/>
														<span class="noticeMsg">*</span>
														</td>
													</tr>
													<tr>
														<td align="right">备注:</td>
														<td align="left">
															<s:textarea id="REMARK" 
																name="role.remark"  cols="40" rows="5"/>										
														</td>
														
													</tr>
													<tr>
														<td align="right" valign="top">权限选择:</td>
										                <td align="left">
									<div style=" width:500px; height:240px; overflow:auto;">
									<table border="0" cellpadding="0" cellspacing="8" >
										<tr height="200px">
											<td valign="top" nowrap="nowrap"><%@include
												file="/WEB-INF/jsp/treeSpan.jsp"%></td>
												<td valign="top" width="12"><span class="noticeMsg">*</span></td>
										</tr>
									</table>
									</div>
										                </td>
													</tr>
										
									</table>
										<div class="btnBar">
										    <a href="#" onclick="goBack('roleAction.shtml')" class="buttontwo">取消</a>
						<s:url id="delete" action="deteleRoleAction">
							<s:param name="role_id" value="role_id" />
						</s:url>
						<a href="#" class="buttontwo" onclick="Wit.delConfirm('${delete}', '<s:text name="common.delete.confirm" />')">
						<s:text name="button.delete" /></a>
											<a href="#" class="buttontwo" onclick="submitPostFrom();">修改</a>
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
				
		</div>					
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>					
</div>						

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/roleDWR.js'></script>
<script type="text/javascript">

function goBack(url) {
	Wit.goBack(url);
}

/** 角色名验证 **/
function checkROLE_NAME(){
  var elm = $('ROLE_NAME');
  if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	    return false;
	}
  if (!Mat.checkLength(
			elm,
			32,
			'角色名称' + '<s:text name="val.feild.overlength"/>' + '32'))
	return false;
	if(Wit.checkErr(elm,/[%"'|\\\/<>,]/)){
		Wit.showErr(elm, "不许出现特殊符号");
	    return false;
	}

  Mat.showSucc(elm);
  return true;
}
/** 角色备注验证 **/
function checkRemark(){
  	var elm = $('REMARK');
  	if (!Mat.checkLength(
					elm,
					32,
					'备注' + '<s:text name="val.feild.overlength"/>' + '32'))
		return false;
	if(Wit.checkErr(elm,/[%"'|\\\/<>,]/)){
		Wit.showErr(elm, "不许出现特殊符号");
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
		Wit.showErr(elm, "请选择该角色的权限");
		return false;
	}
	for ( var i = 0; i < checkBoxs.length; i++) {
		var temp = checkBoxs[i];
		if (temp.checked == true) {
			ids += temp.module_id + ',';
		}
	}
	if(ids==''){
		Wit.showErr(elm, "请选择该角色的权限");
		//$('rolestr_msg').innerHTML="没有可供选择的权限";
		return false;
		
	}
	document.getElementById('ROLES_STR').value=ids.substring(0, ids.length - 1);
	Mat.showSucc(elm,"权限已选择");
	return true;
}
/**角色名称唯一验证**/
function checkRoleNameUnique(){
	if(checkROLE_NAME()){
		var ID = document.getElementById("ROLE_ID").value;
		var name = document.getElementById("ROLE_NAME").value;
		var entiID = document.getElementById("entiID").value;
		DWREngine.setAsync(false);
		var isSame = false;
		roleDWR.checkRoleNameUnique(ID,name,entiID,IsNamesame_callback);
		
		
		function IsNamesame_callback(date){
			if(!date){
				Wit.showErr($('ROLE_NAME'), "此角色名称已存在!");
			}else{
				Mat.showSucc($('ROLE_NAME'));
			}		
			isSame = date;
		}
		DWREngine.setAsync(true);
		return isSame;
	}else{
		return false;
	}	
}
function submitPostFrom(){
	trimAllObjs();
	//var f1=checkROLE_NAME();
	var f2=checkROLES_STR();
	var f3=checkRemark();
	var f4=checkRoleNameUnique();
	if(f2&&f3&&f4){
		Wit.commitAll($('updateRoleform'));
	}else  {
	  return false;
	}
}
/** 初始化样式 **/
function setFormMessage() {
	Mat.setDefault($('ROLE_NAME'),'');
	Mat.setDefault($('ROLES_STR'),'');
	Mat.setDefault($('REMARK'),'');

}
jQuery(function() {
	jQuery('#content').mk_autoresize({
        height_include: '#main',
        height_offset: 0 // 该值各页面根据自己的页面布局调整
      });
});
</script>
</body>
</html>




