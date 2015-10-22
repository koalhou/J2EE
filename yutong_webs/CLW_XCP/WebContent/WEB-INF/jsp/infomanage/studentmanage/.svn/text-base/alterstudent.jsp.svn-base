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
	<s:form id="alter_studentform" action="student_edit" method="post" enctype="multipart/form-data">
		<s:hidden name="page" />
		<s:hidden name="pageSize" />
		<s:hidden id="student_id" name="studentInfo.student_id" />
		<s:hidden id="photo_name" name="studentInfo.photo_name" />
		<s:hidden id="photoDelFlag" name="studentInfo.photoDelFlag" />
		<s:hidden id="oldStudentCard" name="studentInfo.old_student_card" />
		<s:hidden id="picId" name="picId" />
		<div class="toolbar">
		    <div class="contentTil">学生信息</div>
		</div>
		<div id="studentDiv" style="overflow:auto;clear:both;">
		<table id="studentTable" width="100%" border="0" cellspacing="0" cellpadding="0">
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
							name="student.editbefore.title" /></span></td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<s:if test="studentInfo.vehicle_ln!=NULL">
								<tr>
									<td width="100px" height="28" align="right">
										企业名称：
									</td>
									<td width="160px" class="fsBlack">
										<s:property value="studentInfo.short_allname" />
									</td>
									<td width="200px" align="left" colspan="2">
										乘坐校车：<s:property value="studentInfo.vehicle_ln" />
									</td>
								</tr>
								</s:if>
								<tr>
									<td width="100px" height="28" align="right">
										姓名：
									</td>
									<td width="160px" class="fsBlack">
										<s:textfield id="student_name" name="studentInfo.student_name" maxlength="16" />
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
										学生卡号：
									</td>
									<td align="left">
										<s:textfield id="student_card" name="studentInfo.student_card" maxlength="32" />
										<a href="#" onclick="getCardId();">采集</a>
									</td>
								</tr>
								<tr>
									<td height="28" align="right"><s:text name="sex" />：</td>
									<td align="left">
										<s:select name="studentInfo.student_sex" list="sexSysMap" cssStyle="width:120px">
										</s:select>
									</td>
								</tr>
								<tr>
									<td align="right">学号：</td>
									<td align="left">
										<s:textfield id="student_code" name="studentInfo.student_code" maxlength="32">
										</s:textfield>
									</td>
								</tr>
								<tr>
									<td height="28" align="right">学校：</td>
									<td align="left">
										<s:textfield id="student_school" name="studentInfo.student_school" maxlength="32">
										</s:textfield>
									</td>
									
								</tr>
								<tr>
									<td align="right">班级：</td>
									<td align="left">
										<s:textfield id="student_class" name="studentInfo.student_class" maxlength="32">
										</s:textfield>
									</td>
									<td align="left">
										<s:text	name="birthday" />：
										<s:textfield id="student_birth" name="studentInfo.student_birth" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'%y-%M-%d'})" cssClass="Wdate">
										</s:textfield>
									</td>
								</tr>
								<tr>
									<td width="15%" height="28" align="right">所属省：</td>
									<td width="35%" align="left">
										<s:select id="studentProvince" 
										          name="studentInfo.studentProvince" 
										          list="provinceInfosList" 
										          listKey="zone_id" 
	                                              listValue="zone_name"
	                                              cssStyle="width:120px"
										          headerKey="" 
										          headerValue="%{getText('please.select')}" 
										          onchange="getCityInfo();">
	                                    </s:select>
									</td>
									<td align="left">
										&nbsp;&nbsp;&nbsp;所属市：
										<s:select id="studentCity" 
										          name="studentInfo.studentCity" 
										          list="cityInfosList" 
										          listKey="zone_id" 
	                                              listValue="zone_name"
	                                              cssStyle="width:120px"
										          headerKey="" 
										          headerValue="%{getText('please.select')}" 
										          onchange="getDistrictInfo();">
	                                    </s:select>
									</td>
								</tr>
								<tr>
									<td align="right">所属县区：</td>
									<td align="left">
										<s:select id="studentDistrict" 
										          name="studentInfo.studentDistrict" 
										          list="countryInfosList" 
										          listKey="zone_id" 
	                                              listValue="zone_name"
	                                              cssStyle="width:120px"
										          headerKey="" 
										          headerValue="%{getText('please.select')}" 
										          onchange="onDistrictChange()">
	                                    </s:select>
									</td>
									<td align="left">
									</td>
								</tr>
								<tr>
									<td align="right" height="32">
										家庭住址：
									</td>
									<td colspan="3">
										<s:textfield id="student_address" name="studentInfo.student_address" size="83" maxlength="40" />
									</td>
								</tr>
								<tr>
									<td align="right">
										备注：
									</td>
									<td colspan="3">
										<s:textfield id="remarks" name="studentInfo.remarks" size="83" maxlength="40" />
									</td>
								</tr>
								<tr>
									<td align="right" height="32">
										站点描述：
									</td>
									<td align="left" colspan="2">
										<s:textfield id="siteDesc" name="studentInfo.siteDesc" size="83" maxlength="20" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
					    <td height="5px" class="lineStyle">
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="15%" height="28" align="right">
										班主任姓名：
									</td>
									<td width="35%" >
										<s:textfield id="teacher_name" name="studentInfo.teacher_name" maxlength="20">
										</s:textfield>
									</td>
									<td align="right" width="15%">
										班主任电话：
									</td>
									<td>
										<s:textfield id="teacher_tel" name="studentInfo.teacher_tel" maxlength="20">
										</s:textfield>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
					    <td height="5px" class="lineStyle">
						</td>
					</tr>
					<tr>	
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="15%" height="28" align="right">
										家长姓名：
									</td>
									<td width="35%" align="left">
										<s:textfield id="relative_name" name="studentInfo.relative_name" maxlength="20">
										</s:textfield>
									</td>
									<td align="right" width="15%">
										家长电话：
									</td>
									<td align="left" width="35%">
										<s:textfield id="relative_tel" name="studentInfo.relative_tel" maxlength="20">
										</s:textfield>
									</td>
								</tr>
								<tr>
									<td width="15%" align="right">
										家长角色：
									</td>
									<td width="35%" align="left">
										<s:select id="relative_type" 
	                                                  name="studentInfo.relative_type"
	                                                  list="relativeTypeList" 
	                                                  listKey="codeId" 
	                                                  listValue="codeName"
	                                                  cssStyle="width:120px">
	                                    </s:select>
									</td>
									<td>
									</td>
									<td>
									</td>
								</tr>
							</table>
						</td>
					</tr>	
					<tr>
						<td class="btnBar">
							<a href="#" onclick="goBack('studentmanage.shtml');" class="buttontwo">
								<s:text name="button.cancle" />
							</a>
							<s:if test="#session.perUrlList.contains('111_3_3_1_4')">
								<s:url id="delete" action="student_delete">
									<s:param name="studentInfo.student_id" value="studentInfo.student_id" />
									<s:param name="page" value="page" />
									<s:param name="pageSize" value="pageSize" />
								</s:url>
								<a href="#"	onclick="Wit.delConfirm('${delete}', '<s:text name="common.delete.confirm" />')" class="buttontwo">
									<s:text name="button.delete" />
								</a>
						    </s:if> 
						    <s:if test="#session.perUrlList.contains('111_3_3_1_3')">
								<a href="#" class="buttontwo" onClick="submitAlterForm();">
									<s:text name="button.edit" />
								</a>
							</s:if>
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
<%@include file="alterstudent_validate.jsp"%>
</body>
</html>
