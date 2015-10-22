<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<link rel="stylesheet" href="<s:url value='/styles/global.css'/>" type="text/css"/>
	<link rel="stylesheet" href="<s:url value='/styles/list.css'/>" type="text/css"/>
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
	<style type="text/css">
	body {
		background: #f8f8f8;
		color: #555;
		font: 12px/ 180% Tahoma, Arial, "宋体", "微软雅黑";
		margin: 0;
		width: 350px;
		min-width: 350px;
	}
	
	.msgBoxPass {
		margin: 10px auto;
		background: #eee url(../images/msg_title_bg.gif) repeat-x top left;
		border: 1px solid #b8b8b8;
		padding: 0 8px;
		width: 350px;
	}
	
	.msgTitlePwd{
		color: #000;
		font-weight: bold;
		line-height: 30px;
		height: 40px;
	}
	</style>
</head>

<body>
<s:form id="pwdconfirm_form" action="confirmPassword" method="post" onsubmit="return false;">
	<s:hidden id="moduleName" name="moduleName"/>
	<table id="contenttable" width="400" border="0" cellspacing="0"	cellpadding="0" align="center">
		<tr>
			<td valign="top" align="center">
				<s:label id="errorLbl" cssStyle="font-size:12px;color:#CC0000"/>
				<s:label id="successLbl" cssStyle="font-size:12px;color:#009900"/>
			</td>
		</tr>
		<tr>
			<td align="center">
			<table class="msgBoxPass" border="0" align="center" cellpadding="0"	cellspacing="0">
				<tr>
					<td height="32" background="<s:url value='/images/msg_title_bg.gif'/>">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="32" align="left">
								<span class="msgTitlePwd">模块密码</span>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="30" align="right">输入密码：</td>
							<td width="60%" align="left">
								<label>
								<s:password	id="inputPassword" name="inputPassword" />
								</label>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class="btnBar">
						<a href="javascript:art.dialog.close();" class="buttontwo">取消</a>
						<a href="#" class="buttontwo"	onclick="checkModulePassword()">确定</a>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<%@include file="modulePasswordConfirm_validate.jsp"%>
</body>
</html>

