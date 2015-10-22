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
	.lineStyle {
		background: url(../images/wline.gif) repeat-x top left;
		float: left;
		height: 0px;
		padding-top: 10px;
		width: 100%;
		color: #2a2a2a;
	}
	.mypreview {
	 max-width:120px;
	 max-height:140px;
	 width:expression_r_r(document.body.clientWidth > 120? "120px": "auto" ); 
	 height:expression_r_r(document.body.scrollHeight > 400 ? "140px" : "auto" );
	}
	</style>
</head>
<body onload="onloadFunc()">
<object id="GenerateCardId" name="GenerateCardId" classid="clsid:7DE30258-36E3-4F87-B7FA-F093FF205D33" codebase="<s:url value='/codebase/ComReader.dll' />#version=1.0" style="display:none;">
</object>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<s:form id="alter_driverform" action="driver_edit" method="post" enctype="multipart/form-data">
			<s:hidden name="page" />
			<s:hidden name="pageSize" />
			<s:hidden id="driver_id" name="driverInfo.driver_id" />
			<s:hidden id="photo_name" name="driverInfo.photo_name" />
			<s:hidden id="photoDelFlag" name="driverInfo.photoDelFlag" />
			<s:hidden id="old_driver_card" name="driverInfo.old_driver_card" />
			<s:hidden id="old_driver_license" name="driverInfo.old_driver_license" />
			<s:hidden id="picId" name="picId" />
			<table id="driverTable" width="100%" border="0" cellspacing="0"	cellpadding="0">
				<tr>
					<td height="36" valign="top">
					  <div class="toolbar">
					    <div class="contentTil">驾驶员信息</div>
					  </div>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center"><s:actionerror theme="mat" /> <s:fielderror
								theme="mat" /> <s:actionmessage theme="mat" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
					<table class="msgBox2" width="720" border="0" align="center"
						cellpadding="0" cellspacing="0">
						<tr>
							<td height="32"><span class="msgTitle"><s:text
								name="driver.editbefore.title" /></span></td>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<s:if test="driverInfo.vehicle_ln!=NULL">
								<tr>
									<td width="100px" height="28" align="right">
										行驶路线：
									</td>
									<td width="160px" class="fsBlack">
										<s:property value="driverInfo.route_name" />
									</td>
									<td width="200px" align="left" colspan="2">
										驾驶车辆：<s:property value="driverInfo.vehicle_ln" />
									</td>
								</tr>
								</s:if>
								
								<tr>
									<td width="100px" height="28" align="right">
										<s:text	name="driverinfo.driver_name" />：
									</td>
									<td width="160px" class="fsBlack">
										<s:textfield id="driver_name" name="driverInfo.driver_name" maxlength="16" />
									</td>
									<td rowspan="5" width="200px" valign="top">
										<div id="photoPreview" style="display:none;">
								    		<img src="<s:url value='/images/noPhoto.jpg'/>" id="preview" alt="Preview" class="mypreview"/><br/>
								    		<img id="addbutton" src="<s:url value='/scripts/flexigrid/images/add.gif'/>" style="cursor: pointer;" title="填加" onclick="fileFormate();"></img>
								    		<img id="delbutton" src="../images/delAccessory.gif" style="cursor: pointer;" title="删除" onclick="clearDriverPhoto()"></img>
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
										<s:textfield id="driver_card" name="driverInfo.driver_card" maxlength="32">
										</s:textfield>
										<a href="#" onclick="getCardId();">采集</a>
									</td>
								</tr>
								<tr>
									<td height="28" align="right"><s:text name="sex" />：</td>
									<td align="left">
										<s:select name="driverInfo.driver_sex" list="sexSysMap" cssStyle="width:120px">
										</s:select>
									</td>
								</tr>
								<tr>
									<td align="right"><s:text	name="driverinfo.driver_license" />：</td>
									<td align="left">
										<s:textfield id="driver_license" name="driverInfo.driver_license" maxlength="32" />
									</td>
								</tr>
								<tr>
									<td height="28" align="right">
										<s:text	name="birthday" />：
									</td>
									<td align="left">
										<s:textfield id="driver_birth" name="driverInfo.driver_birth" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'%y-%M-%d'})" cssClass="Wdate">
										</s:textfield>
									</td>
								</tr>
								<tr>
									<td align="right" height="28">联系电话：</td>
									<td align="left">
										<s:textfield id="cell_number" name="driverInfo.cell_number" maxlength="13">
										</s:textfield>
									</td>
									<td align="left">
										所属企业：
										<s:textfield id="organizationName" name="driverInfo.short_allname" readonly="true" onclick="openorganizidtree()"/>
		                               	<s:hidden id="organizationID" name="driverInfo.organization_id"></s:hidden>
									</td>
								</tr>
								<tr>
									<td align="right">家庭住址：</td>
									<td colspan="3">
										<s:textfield id="driver_address" name="driverInfo.driver_address" size="83" maxlength="40" />
									</td>
								</tr>
								<tr>
									<td height="28" align="right">备注：</td>
									<td colspan="3">
										<s:textfield id="remarks" name="driverInfo.remarks" size="83" maxlength="40" />
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="btnBar"><a href="#"
								onclick="goBack('drivermanage.shtml');" class="buttontwo"><s:text
								name="button.cancle" /></a> <s:if
								test="#session.perUrlList.contains('111_3_3_2_4')">
								<s:url id="delete" action="driver_delete">
									<s:param name="driverInfo.driver_id" value="driverInfo.driver_id" />
									<s:param name="page" value="page" />
									<s:param name="pageSize" value="pageSize" />
								</s:url>
								<a href="#"
									onClick="Wit.delConfirm('${delete}', '<s:text name="common.delete.confirm" />')"
									class="buttontwo"> <s:text name="button.delete" /></a>
							</s:if> <s:if test="#session.perUrlList.contains('111_3_3_2_3')">
								<a href="#" class="buttontwo" onClick="submitalterForm()"><s:text
									name="button.edit" /></a>
							</s:if></td>
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
<%@include file="driver_validate.jsp"%>
<%@include file="alterdriver_js.jsp"%>
</body>
</html>