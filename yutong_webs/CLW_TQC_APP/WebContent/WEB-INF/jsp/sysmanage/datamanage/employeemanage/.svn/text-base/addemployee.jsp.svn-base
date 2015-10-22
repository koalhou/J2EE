<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
	<style type="text/css">
		.lineStyle{ background: url(../images/wline.gif) repeat-x top left; float: left; height: 0px; padding-top: 10px; width: 100%;color: #2a2a2a;}
		.mypreview {
		 max-width:120px;
		 max-height:140px;
		 width:expression_r_r(document.body.clientWidth > 120? "120px": "auto" ); 
		 height:expression_r_r(document.body.scrollHeight > 400 ? "140px" : "auto" );
		}
	</style>
</head>
<body >
<object id="GenerateCardId" name="GenerateCardId" classid="clsid:7DE30258-36E3-4F87-B7FA-F093FF205D33" codebase="<s:url value='/codebase/ComReader.dll' />#version=1.0" style="display:none;">
</object>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
	<s:form id="add_studentform" action="addemployee_add" method="post" enctype="multipart/form-data">
		<s:hidden id="enid" name="studentInfo.enterprise_id"/>
		<div class="toolbar">
			<div class="contentTil">员工信息</div>
		</div>
		<div id="studentDiv" style="overflow:auto;clear:both;">
		<table id="studentTable" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center">
								<s:actionerror theme="mat" />
		      					<s:fielderror theme="mat"/>
		      					<s:actionmessage theme="mat"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<table class="msgBox2" width="720" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td height="32">
								<span class="msgTitle">新建员工信息</span>
							</td>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="100px" height="28" align="right">
										姓名：
									</td>
									<td width="160px" class="fsBlack">
										<s:textfield id="student_name" name="studentInfo.student_name" maxlength="16" />
									</td>
								</tr>
								<tr>
									<td width="100px" height="28" align="right">员工号：</td>
									<td align="left">
										<s:textfield id="student_code" name="studentInfo.student_code" maxlength="14">
										</s:textfield>
									</td>
								</tr>
								<tr>
									<td width="100px" height="28" align="right">
										卡号：
									</td>
									<td align="left">
										<s:textfield id="student_card" name="studentInfo.student_card" maxlength="14" />
										<a href="#" onclick="getCardId();">采集</a>
										
									</td>
								</tr>
								<tr>
									<td width="100px" height="28" align="right">所属组织：</td>
									<td align="left">
										<s:textfield id="organizationName" name="studentInfo.short_allname" readonly="true" onclick="openorganizidtree()"/>
		                                	<s:hidden id="organizationID" name="studentInfo.organization_id"></s:hidden>
									</td>
									
								</tr>
								<tr>
									<td align="right">
										备注：
									</td>
									<td align="left" colspan="2">
<!--										<s:textfield id="remarks" name="studentInfo.remarks" size="83" maxlength="40" />-->
										<textarea id="remarks" name="studentInfo.remarks" rows="3" cols="61" border="1px" onpropertychange="if(value.length>40) value=value.substr(0,40)"  ></textarea>
										
									</td>
								</tr>
								<tr height="30px">
									<td></td>
									<td colspan=2  align="left" style="color:green;margin-left:5px" >还可以输入<span id="textleft" >40</span>个字</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
						   <td height="5px" >
							</td>
						</tr>
						<tr>
						   <td height="5px" >
							</td>
							<td height="5px" >
							</td>
						</tr>
						<tr>
							<td class="btnBar">
								<a href="#" class="buttontwo" onclick="goBack('employeemanage.shtml');"><s:text name="button.cancle" /></a> 
								<a href="#" class="buttontwo" onclick="submitForm()"><s:text name="button.queding" /></a>
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
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="addemployee_validate.jsp"%>
</body>
</html>
