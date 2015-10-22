<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global1.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/jquery.Jcrop.css'/>">
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<style type="text/css">
.mycss {
 max-width:650px;
 max-height:400px;
 /* width:expression_r_r(document.body.clientWidth > 650? "650px": "auto" ); 
 height:expression_r_r(document.body.scrollHeight > 400 ? "400px" : "auto" ); */
 }

.mypreview {
 max-width:120px;
 /* max-height:140px; */
 /* width:expression_r_r(document.body.clientWidth > 120? "120px": "auto" ); 
 height:expression_r_r(document.body.scrollHeight > 400 ? "140px" : "auto" ); */
}
.lineStyle{ background: url(../images/wline.gif) repeat-x top left; float: left; height: 0px; padding-top: 10px; width: 100%;color: #2a2a2a;}
</style>
</head>

<body>
<div id="wrapper">
<%@include file="/WEB-INF/jsp/common/header.jsp"%>
<div id="content">
<s:form id="form2" action="apkuploadFile.shtml" method="post" enctype="multipart/form-data">
<div>
<table class="msgBox2" cellpadding="0" style="width:780px">
	<tr>
		<td height="32" colspan="4">
			<span class="msgTitle" style="width:auto;">信息</span>
		</td>
	</tr>
	<tr>
		<td colspan="5" valign="top" align="center">
			<s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/>
		</td>
	</tr>
	<tr>
		<td height="25" align="right">终端软件：</td>
		<td align="left">
			<input id="apply_id"  name="ufi.apply_id"/>
			<span class="noticeMsg">*</span>
		</td>
		<td height="25" align="right">最新版本号：</td>
		<td align="left">
			<input id="version_name"  name="ufi.version_name"/>
			<span class="noticeMsg">*</span>
		</td>
		<td></td>
	</tr>
	<tr>
		<td height="25" align="right">版本描述：</td>
		<td align="left">
			<input id="version_desc"  name="ufi.version_desc"/>
			<span class="noticeMsg">*</span>
		</td>
		<td height="25" align="right">创建人：</td>
		<td align="left">
			<input id="creater"  name="ufi.creater"/>
			<span class="noticeMsg">*</span>
		</td>
		<td></td>
	</tr>
	<tr>
		<td height="25" align="right">是否强制更新：</td>
		<td align="left">
			是<input type="radio" value="0" checked="checked"  name="ufi.force"/>
			否<input type="radio" value="1"  name="ufi.force"/>
			<span class="noticeMsg">*</span>
		</td>
		<td align="left">
		</td>
		<td height="25" align="right"></td>
		<td></td>
	</tr>
	<tr>
		<td height="25" align="right">选择：</td>
		<td align="left" colspan="3">
			<input id="fileupload" 
			       name="fileupload" 
			       type="file" 
			       size="60"
			       style="height: 17px;line-height: 17px;font-size: 12px;border: #E0E0E0 1px solid;padding-left: 2px;padding-top: 1px;"
			       onchange="uploadImage()"/>
			<span class="noticeMsg">*</span>
		</td>
		<td align="left">
			<a class="sbuttonziyong" href="#" onclick="saveAPK()">保存</a>
			<!-- <a class="sbuttonziyong" href="#" onclick="downApk()">下载</a> -->
		</td>
	</tr>
	<tr>
	    <td height="5px" class="lineStyle" colspan="6">
	    	<s:label id="apkScale"/>
		</td>
	</tr>
</table>
</div>
</s:form>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
<s:form id="apkdownloadid" action="apkdownloadFile" method="post"></s:form>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="apkfileUpload_js.jsp"%>
</body>
</html>
