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

			<s:form id="addRoleform" action="addRoleAction.shtml" method="post">
			<input type="hidden"  id="entiID" name="entiID" value="<s:property value='#session.adminProfile.entiID'/>" />
			<div id="main" style="overflow: auto;">
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
											class="msgTitle">&nbsp;&nbsp;<s:text name="roleinfo.create" /></span></td>
									</tr>
									<tr>
										<td align="center">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
												
												<table width="100%" border="0" cellspacing="2" cellpadding="0">
													<tr>
														<td  align="right"></td>
														<td><input type="hidden" id="ROLES_STR" name ="ROLES_STR" value=""/></td>
													</tr>
													<tr>
														<td width="14%" height="28" align="right">角色名称:</td>
														<td width="86%" class="fsBlack" align="left">
														<s:textfield id="ROLE_NAME" name="ROLE_NAME" maxlength="32"/><span class="noticeMsg">*</span>
														</td>
													</tr>
<!-- 													<tr> -->
<!-- 														<td width="14%" height="28" align="right">角色类型:</td> -->
<!-- 														<td width="86%" class="fsBlack" align="left"> -->
<!-- 														  <select id="roleType"  onchange="chezhuonchange()"> -->
<!-- 														   <option value="0">请选择</option>     -->
<!-- 				                                           <option value="1">学生信息管理</option>                          -->
<!-- 				                                           <option value="2">管理员</option> -->
<!-- 				                                           <option value="3">线路规划</option> -->
<!-- 				                                           <option value="4">普通用户</option> -->
<!-- 				                                         </select> -->
<!-- 														</td> -->
<!-- 													</tr> -->
													<tr>
														<td align="right">备注:</td>
														<td align="left">
															<s:textarea id="REMARK" 
																name="REMARK"  cols="40" rows="5"/>
														</td>
													</tr>
													
													<tr>
														<td align="right" valign="top">权限选择:</td>
										                <td align="left" valign="top">
									<div style=" width:500px; height:240px; overflow:auto;">
									<table border="0" cellpadding="0" cellspacing="8" >
										<tr  height="200px">
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
													<a href="#" class="buttontwo" onclick="submitPostFrom();">确定</a>
												</div>
						
										</table>
										</td>
									</tr>
				
								</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</div>
			</s:form>
	</div>					
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>					
</div>						
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
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
/** 默认选中设置 prefix='tree_box_id_' */
function selectNodes(prefix){
	var idArray = new Array(),i=0,prefix='tree_box_id_';
	idArray[0] = "111_1";//车联网子系统
	idArray[1] = "111_1_1";//系统管理
	idArray[2] = "111_1_1_1_2";//修改密码
	idArray[3] = "111_1_1_1_2_1";//修改
	idArray[4] = "111_1_3_1_1";//关于安节通
	for(i=0;i<idArray.length;i++){
		try{document.getElementById(prefix+idArray[i]).checked = true;}catch(e){}
	}
	//document.getElementById(prefix+idArray[3]).click(this);
	//document.getElementById(prefix+idArray[4]).click(this);
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
		var name = document.getElementById("ROLE_NAME").value;
		var entiID = document.getElementById("entiID").value;
		DWREngine.setAsync(false);
		var isSame = false;
		roleDWR.checkRoleNameUnique("",name,entiID,IsNamesame_callback);
		
		
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
		
		//document.getElementById('clwForm').submit();
		Wit.commitAll($('addRoleform'));
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

function chezhuonchange(){
	var roleType = document.getElementById("roleType");
	var prefix='tree_box_id_';
	 var obj=document.getElementById(prefix+"111_3");
	 obj.checked=false;
	 treeCheckBox(obj);	
	if(roleType.value == 0){
		 
	}else if(roleType.value==1){
		 var obj1=document.getElementById(prefix+"111_3_3_1");
		 obj1.checked=true;
		 treeCheckBox(obj1);
		 var obj2=document.getElementById(prefix+"111_3_3_8");
		 obj2.checked=true;
		 treeCheckBox(obj2);
	}else if(roleType.value==2){
	  var obj=document.getElementById(prefix+"111_3");
	  obj.checked=true;
	  treeCheckBox(obj);	
	}else if(roleType.value==3){
		  var obj1=document.getElementById(prefix+"111_3_3");
		  obj1.checked=true;
		  treeCheckBox(obj1);
		  var obj2=document.getElementById(prefix+"111_3_3_1");
		  obj2.checked=false;
		  treeCheckBox(obj2);
		  var obj3=document.getElementById(prefix+"111_3_3_8");
		  obj3.checked=false;
		  treeCheckBox(obj3);
	}else if(roleType.value==4){
			  var obj1=document.getElementById(prefix+"111_3_1");
			  obj1.checked=true;
			  treeCheckBox(obj1);
			  var obj2=document.getElementById(prefix+"111_3_2");
			  obj2.checked=true;
			  treeCheckBox(obj2);
			  var obj3=document.getElementById(prefix+"111_3_2_1");
			  obj3.checked=true;
			  treeCheckBox(obj3);
			  var obj4=document.getElementById(prefix+"111_3_4");
			  obj4.checked=true;
			  treeCheckBox(obj4);
			}
}

jQuery(function() {
	
	jQuery(window).mk_autoresize({
	       height_include: '#content',
	       height_exclude: ['#header', '#footer'],
	       height_offset: 0,
	       width_include: ['#header', '#content', '#footer'],
	       width_offset: 0
	    });
	jQuery('#content').mk_autoresize({
        height_include: '#main',
        height_offset: 0 // 该值各页面根据自己的页面布局调整
      });
});
</script>

</body>
</html>