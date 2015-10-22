<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/jquery.Jcrop.css'/>">
<style type="text/css">
.mycss {
 max-width:650px;
 max-height:400px;
 width:expression_r_r(document.body.clientWidth > 650? "650px": "auto" ); 
 height:expression_r_r(document.body.scrollHeight > 400 ? "400px" : "auto" );
 }

.mypreview {
 max-width:120px;
 max-height:140px;
 width:expression_r_r(document.body.clientWidth > 120? "120px": "auto" ); 
 height:expression_r_r(document.body.scrollHeight > 400 ? "140px" : "auto" );
}
.lineStyle{ background: url(../images/wline.gif) repeat-x top left; float: left; height: 0px; padding-top: 10px; width: 100%;color: #2a2a2a;}
</style>
</head>

<body onload="init();">
<form id="form2" method="post" enctype="multipart/form-data">
<s:hidden id="left_val" name="left_val"/>
<s:hidden id="top_val" name="top_val"/>
<s:hidden id="w_val" name="w_val"/>
<s:hidden id="h_val" name="h_val"/>
<s:hidden id="dataId" name="dataId"/>
<table class="msgBox2" cellpadding="0" style="width:780px">
	<tr>
		<td height="32" colspan="4">
			<span class="msgTitle" style="width:auto;">照片信息</span>
		</td>
	</tr>
	<tr>
		<td colspan="4" valign="top" align="center">
			<s:label id="errorLbl" cssStyle="font-size:12px;color:#CC0000"/>
			<s:label id="successLbl" cssStyle="font-size:12px;color:#009900"/>
		</td>
	</tr>
	<tr>
		<td height="25" align="right">选择图片：</td>
		<td align="left">
			<input id="fileupload" 
			       name="fileupload" 
			       type="file" 
			       size="60"
			       style="height: 17px;line-height: 17px;font-size: 12px;border: #E0E0E0 1px solid;padding-left: 2px;padding-top: 1px;"
			       onchange="uploadImage()"/>
		</td>
		<td align="left" colspan="2">
			<a class="sbuttonziyong" href="#" onclick="saveImg()">保存</a>
			<%-- 
			<a class="sbuttonziyong" href="#" onclick="resize('bigger')">放大</a>
			<a class="sbuttonziyong" href="#" onclick="resize('smaller')">缩小</a>
			<a class="sbuttonziyong" href="#" onclick="uploadImage()">上传</a>
			--%>
		</td>
	</tr>
	<tr>
	    <td height="5px" class="lineStyle" colspan="4">
		</td>
	</tr>
	<tr>
		<td valign="top">
			<table cellspacing="0">
				<tr>
					<td>
						<div id="photoPreview" style="width:120px;height:140px;overflow:hidden;">
				    		<img id="preview" alt="Preview" class="jcrop-preview;"/><br/>
						</div>
						<%-- 
						<div id="noPhoto">
							<img src="<s:url value='/images/noPhoto.jpg'/>" class="mypreview"/>
						</div>
						--%>
					</td>
				</tr>
				<tr>
					<td>
						<s:label id="photoScale"/>
					</td>
				</tr>
			</table>
		</td>
		<td align="left" colspan="3" valign="top">
			<div id="showImage" style="width:650px;max-width:650px;max-height:400px;overflow:auto;white-space:nowrap;position:relative;">
			</div>
		</td>
	</tr>
</table>
</form>
<%@include file="fileUploadAndFormat_js.jsp"%>
</body>
</html>
