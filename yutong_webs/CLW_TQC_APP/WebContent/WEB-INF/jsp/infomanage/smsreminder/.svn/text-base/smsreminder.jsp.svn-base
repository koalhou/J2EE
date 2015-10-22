<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style type="text/css">
		a.btnLinkMap{ 
		  background: url(../images/qipaoimages/btn_c80.gif) no-repeat left top; 
		  color: #333; 
		  display: block; 
		  float: left; 
		  height: 26px;
		  _height: 20px; 
		  line-height: 26px; 
		  margin: 4px 5px; 
		  text-align: center; 
		  width: 80px; }
	</style>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<s:form id="smsreminder_form" action="" method="post">
		<table  width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="36" valign="top" background="../images/tree_tabBg.gif">
							<div class="toolbar">
								<div class="contentTil" style="width:90%">短信提醒</div>
							</div>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td class="reportOnline2">
						<table width="100%" border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td>
								<s:if test="#session.perUrlList.contains('111_3_3_1_5_1')">
								<table border="0" cellspacing="4" cellpadding="0">
									<tr>
										<td>
										学号：
										<s:textfield id="studentCode" name="smsReminderInfo.studentCode" cssClass="input120" onkeypress="if(event.keyCode==13){searchSms();}"/>&nbsp;&nbsp;
										</td>
										<td>
										姓名：
										<s:textfield id="studentName" name="smsReminderInfo.studentName" cssClass="input120" onkeypress="if(event.keyCode==13){searchSms();}"/>&nbsp;&nbsp;
										</td>
										<td>
										学校：
										<s:textfield id="studentSchool" name="smsReminderInfo.studentSchool" cssClass="input120" onkeypress="if(event.keyCode==13){searchSms();}"/>&nbsp;&nbsp;
										</td>
										<td>
										班级：
										<s:textfield id="studentClass" name="smsReminderInfo.studentClass" cssClass="input120" onkeypress="if(event.keyCode==13){searchSms();}"/>&nbsp;&nbsp;
										</td>
										<td>开始时间：
				                            	<input id="queryStartTime" 
											             name="queryStartTime"
											             value="${smsReminderInfo.queryStartTime}"
											             type="text"
											             class="Wdate"
											             readonly="readonly"
											             onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,maxDate:'#F{$dp.$D(\'queryEndTime\')}'})"
											             />
				                           		至
				                            	<input id="queryEndTime" 
											             name="queryEndTime"
											             value="${smsReminderInfo.queryEndTime}"
											             type="text"
											             class="Wdate"
											             readonly="readonly"
											             onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,minDate:'#F{$dp.$D(\'queryStartTime\')}'})"
											             />
										</td>
										<td><a href="#" onclick="searchSms()" class="sbutton"><s:text name="common.chaxun" /></a></td>
									</tr>
								</table>
								</s:if>
								</td>
							</tr>
						</table>		
						</td>
					</tr>
					<tr>
					  <td valign="top">
					   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
					     <tr>
					        <td>
						        <table width="100%" border="0" cellspacing="0" cellpadding="0">
						          <tr>
			                         <td class="titleStyle">
				                         <table width="100%" border="0" cellspacing="0" cellpadding="0">
				                             <tr>
				                               <td width="30%" class="titleStyleZi">短信提醒业务开通列表</td>
				                               <td width="69%" align="right">
				                               </td>
				                             </tr>
				                          </table>
			                          </td>
			                      </tr>
						        </table>
					        </td>
					     </tr>
					     <tr>
					        <td class="reportInline">
					        <div id="Below_new" style="text-align:center"><s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/></div>
							<table id="gala" width="100%"  cellspacing="0"></table>
					        </td>
					     </tr>
					   </table>
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
<%@include file="smsreminder_js.jsp"%>
</body>
</html>

