<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>						
<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterprise_css.jsp"%>
<style>
	.treeBox{height:430px; overflow:auto;width:460px; position:relative; }
</style>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<div id="main" style="overflow: auto;">
			<input type="hidden"  id="entiID" name="entiID" value="<s:property value='#session.adminProfile.entiID'/>" />
   			<s:form id="addStealOilSmsForm" action="/ftlyInfoNew/insertStealOilSms" method="post">
   			<s:hidden id ="update" name ="update" ></s:hidden>
   			<s:hidden id ="stu_id" name ="stealOilSmsInfo.stu_id" ></s:hidden> 
   			<s:hidden id ="stu_name_old" name ="stealOilSmsInfo.stu_name_old" ></s:hidden>
   			<s:hidden id ="organization_id" name ="stealOilSmsInfo.organization_id" ></s:hidden> 
    			<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top">
							<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
							<tr>
							<td height="38" background="../images/contentTil_bg.gif"><span class="contentTil"><s:text name="menu3.yhgl" /></span></td>
							</tr>
			  				</table>
        				</td>
      				</tr>
      				<tr>
					<td  valign="top">
                    	<table class="msgBox4" width="100%" border="0" cellspacing="0" cellpadding="0">
                      	<tr>
			            	<td height="32"><span class="msgTitle">&nbsp;&nbsp;短信设置</span></td>
			          	</tr>
                      	<tr>
                        	<td height="90">
                          		<table width="100%" border="0" cellspacing="5" cellpadding="0">
                            	<tr>
                              		<td width="14%" height="28" align="right">姓名：</td>
                              		<td width="36%" class="fsBlack">
                              		<s:textfield id="stu_name" name="stealOilSmsInfo.stu_name" maxlength="15" onclick="chooseStu()" readonly="true"/><span class="noticeMsg">*</span>
									</td>
                            	</tr>
                            	<tr>
                              		<td width="14%" height="28" align="right">手机号码：</td>
                              		<td width="36%" class="fsBlack">
                              		<s:textfield id="telephone" name="stealOilSmsInfo.telephone" maxlength="15" cssStyle="ime-mode:disabled"/><span class="noticeMsg">*</span>         
                              		</td>
                            	</tr>
                            	<tr>
                            		<td width="14%" align="right">关联组织：</td>
                            		<td width="36%" class="fsBlack">
	                            		<div id="treeDemoDiv" class="treeBox">
								      		<ul id="treeDemo" class="ztree"></ul>
								        </div>
                            		</td>
                            		<td><input type="hidden" id="ORG_STR" name ="ORG_STR" value=""/></td>
                            	</tr>
                          		</table>
                        	</td>
                      	</tr>
                       <tr>
           			 <td class="btnBar">
	           			 <a href="../ftlyInfoNew/stealOilSms.shtml?stealOilSmsInfo.stu_id=&stealOilSmsInfo.stu_name=&stealOilSmsInfo.telephone=&stealOilSmsInfo.organization_id=" class="button">取消</a> 
	           			 <s:if test="%{update=='update'}">
        					<a href="javascript:deleteStealOilSms('<s:property value="stealOilSmsInfo.stu_id"/>')" class="button">删除</a>
        				</s:if>
	           			 <a href="#" onclick="submitForm()" class="button">确定</a>
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
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<%@include file="/WEB-INF/jsp/treemanage/zTree_organization_withcheckbox.jsp"%>	
<%@include file="/WEB-INF/jsp/oilmonitor/stealOilSmsAdd_js.jsp"%>
</body>
</html>