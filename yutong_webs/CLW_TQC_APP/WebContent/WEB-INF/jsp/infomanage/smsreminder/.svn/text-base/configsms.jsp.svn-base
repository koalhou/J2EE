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
	</style>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<s:form id="configsms_form" action="configSms" method="post">
			<s:hidden name="page" />
			<s:hidden name="pageSize" />
			<s:hidden id="delParentId" name="delParentId"/>
			<s:hidden id="otherPersonId" name="otherPersonId"/>
			<div class="toolbar">
				<div class="contentTil">短信提醒</div>
			</div>
			<div id="smsTable" style="overflow:auto;clear:both;">
			<table id="holidayTable" width="100%" border="0" cellspacing="0" cellpadding="0">
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
						<table class="msgBox2" border="0" align="center" cellpadding="0" cellspacing="0" style="padding-right:0px; padding-left:0px; width:800px;">
							<tr>
								<td height="32">
									<span class="msgTitle">&nbsp;&nbsp;配置短信提醒</span>
								</td>
							</tr>
							<tr>
								<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="28" align="left">
											&nbsp;&nbsp;姓名：
											<s:hidden id="student_id" name="studentInfo.student_id" />
											<s:hidden id="student_name" name="studentInfo.student_name" />
											<s:property value="studentInfo.student_name" />
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
						        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>" align="left">
						          <table width="100%" border="0" cellspacing="0" cellpadding="0">
						            <tr>
						              <td width="80%" class="titleStyleZi">联系人信息</td>
						              <td align="right">
						              </td>
						              <td width="1%">&nbsp;</td>
						            </tr>
						          </table>
						        </td>
						    </tr>
						    <tr>
						        <td align="center">
						          <s:label id="warnMessage" cssClass="error"/>
						        </td>
						    </tr>
						    <s:iterator value="parentsList" status="stat">
						    <tr>
						    	<td>
						    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
						    			<s:if test="#stat.index > 0">
						    		    <tr>
							            	<td class="lineStyle" colspan="4">
								            </td>
							            </tr>
							            </s:if>
						    			<tr>
						    				<td height="28">
						    				  &nbsp;&nbsp;称谓：
						    				  <s:property value="%{parentsList[#stat.index].relativeType}" />
						    				</td>
						    				<td>手机号码：
						    				  <s:hidden id="cellPhone" name="parentsList[%{#stat.index}].cellPhone" value="%{parentsList[#stat.index].cellPhone}" />
						    				  <s:hidden id="mainCellFlag" name="parentsList[%{#stat.index}].mainCellFlag" value="%{parentsList[#stat.index].mainCellFlag}" />
						    				  <s:hidden id="student_id" name="parentsList[%{#stat.index}].student_id" value="%{parentsList[#stat.index].student_id}" />
						    				  <s:property value="%{parentsList[#stat.index].cellPhone}" />
						    				</td>
						    			</tr>
						    			<tr>
						    				<td height="28" colspan="4" align="left" class="titleStyleZi">提醒类型</td>
						    			</tr>
						    			<tr>
						    				<td height="28">&nbsp;&nbsp;
						    				  <s:checkbox id="upNotChargeChoiceFlag" name="parentsList[%{#stat.index}].upNotChargeChoiceFlag" fieldValue="true"/>未刷卡上车
						    				</td>
						    				<td>
						    				  <s:checkbox id="downNotChargeChoiceFlag" name="parentsList[%{#stat.index}].downNotChargeChoiceFlag" fieldValue="true"/>未刷卡下车
						    				</td>
						    				<td>
						    				  <s:checkbox id="upNotSetSiteChoiceFlag" name="parentsList[%{#stat.index}].upNotSetSiteChoiceFlag" fieldValue="true"/>未在规定站点上车
						    				</td>
						    				<td>
						    				  <s:checkbox id="downNotSetSiteChoiceFlag" name="parentsList[%{#stat.index}].downNotSetSiteChoiceFlag" fieldValue="true"/>未在规定站点下车
						    				</td>
						    			</tr>
						    			<tr>
						    				<td height="28">&nbsp;&nbsp;
						    				  <s:checkbox id="upNormalChoiceFlag" name="parentsList[%{#stat.index}].upNormalChoiceFlag" fieldValue="true"/>正常上车
						    				</td>
						    				<td>
						    				  <s:checkbox id="downNormalChoiceFlag" name="parentsList[%{#stat.index}].downNormalChoiceFlag" fieldValue="true"/>正常下车
						    				</td>
						    				<td>
						    				  <s:checkbox id="anXinWarnChoiceFlag" name="parentsList[%{#stat.index}].anXinWarnChoiceFlag" fieldValue="true"/>安芯提醒
						    				</td>
						    			</tr>
						    		</table>
						    	</td>
						    </tr>
						    </s:iterator>
							
							<tr>
								<td class="btnBar" style="padding-top: -5px">
									<a href="#" class="buttontwo" onclick="goBack('init.shtml');"><s:text name="button.cancle" /></a> 
								    <s:if test="#session.perUrlList.contains('111_3_3_1_5_3')">
										<a href="#" class="buttontwo" onClick="submitAlterForm()">配置
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
<%@include file="configsms_validate.jsp"%>
</body>
</html>
