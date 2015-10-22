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
<body onload="loadFileContent()">
<object id="GenerateCardId" name="GenerateCardId" classid="clsid:7DE30258-36E3-4F87-B7FA-F093FF205D33" codebase="<s:url value='/codebase/ComReader.dll' />#version=1.0" style="display:none;">
</object>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<s:form id="add_stewardform" action="steward_add" method="post" enctype="multipart/form-data">
			<s:hidden name="page" />
			<s:hidden name="pageSize" />
			<s:hidden id="picId" name="picId" />
			<table id="stewardTable" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top">
						<div class="toolbar">
							<div class="contentTil">跟车老师信息</div>
						</div>
					</td>
				</tr>
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
									<span class="msgTitle"><s:text name="steward.add.info" /></span>
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
											<s:textfield id="steward_name" name="stewardInfo.steward_name" maxlength="16" />
										</td>
										<td rowspan="5" width="200px" valign="top">
											<div id="photoPreview" style="display:none;">
									    		<img src="<s:url value='/images/noPhoto.jpg'/>" id="preview" alt="Preview" class="mypreview"/><br/>
									    		<img id="addbutton" src="<s:url value='/scripts/flexigrid/images/add.gif'/>" style="cursor: pointer;" title="填加" onclick="fileFormate();"></img>
									    		<img id="delbutton" src="../images/delAccessory.gif" style="cursor: pointer;" title="删除" onclick="clearPhoto()"></img>
											</div>
											<div id="noPhoto">
												<img src="<s:url value='/images/noPhoto.png'/>" class="mypreview"/><br/>
												<img id="addbutton" src="<s:url value='/scripts/flexigrid/images/add.gif'/>" style="cursor: pointer;" title="填加" onclick="fileFormate();"></img>
											</div>
										</td>
									</tr>
									<tr>
										<td align="right">
											卡号：
										</td>
										<td align="left">
											<s:textfield id="steward_card" name="stewardInfo.steward_card" maxlength="32"/>
											<a href="#" onclick="getCardId();">采集</a>
										</td>
									</tr>
									<tr>
										<td height="28" align="right"><s:text name="sex" />：</td>
										<td align="left">
											<s:select name="stewardInfo.steward_sex" list="sexSysMap" cssStyle="width:120px">
											</s:select>
										</td>
									</tr>
									<tr>
										<td align="right">身份证号：</td>
										<td align="left">
											<s:textfield id="steward_ID_Card" name="stewardInfo.steward_ID_Card" maxlength="32">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td height="28" align="right">
											<s:text	name="birthday" />：
										</td>
										<td align="left">
											<s:textfield id="steward_birth" name="stewardInfo.steward_birth" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'%y-%M-%d'})" cssClass="Wdate">
											</s:textfield>
										</td>
									</tr>
									<tr>
										<td align="right" height="28">联系电话：</td>
										<td align="left">
											<s:textfield id="cell_number" name="stewardInfo.cell_number" maxlength="13">
											</s:textfield>
										</td>
										<td align="left">
											所属企业：
											<s:textfield id="organizationName" name="stewardInfo.short_allname" readonly="true" onclick="openorganizidtree()"/>
		                                	<s:hidden id="organizationID" name="stewardInfo.organization_id"></s:hidden>
										</td>
									</tr>
									<tr>
										<td align="right">
											家庭住址：
										</td>
										<td colspan="3">
											<s:textfield id="steward_address" name="stewardInfo.steward_address" size="83" maxlength="40" />
										</td>
									</tr>
									<tr>
										<td height="28" align="right">
											备注：
										</td>
										<td colspan="3">
											<s:textfield id="remarks" name="stewardInfo.remarks" size="83" maxlength="40" />
										</td>
									</tr>
								</table>
								</td>
							</tr>
							
							<tr>
								<td class="btnBar">
									<a href="#" class="buttontwo" onclick="goBack('stewardmanage.shtml');"><s:text name="button.cancle" /></a> 
									<a href="#" class="buttontwo" onclick="submitForm()"><s:text name="button.queding" /></a>
								</td>
							</tr>
							
						</table>
					</td>
				</tr>
			</table>
		</s:form> 
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="steward_validate.jsp"%>
<%@include file="addsteward_js.jsp"%>
</body>
</html>
