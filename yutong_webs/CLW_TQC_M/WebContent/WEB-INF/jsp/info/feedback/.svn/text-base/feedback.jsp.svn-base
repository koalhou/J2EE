<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
	<style>
		.cs{width:100%;color:#6699ff;border:1px #ff8000 dashed;overflow:hidden;text-overflow:ellipsis}
	</style>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/menu.jsp"%>
	<div id="content">
	<s:form id="form1" action="" method="post">
	<table  width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top" background="../images/tree_tabBg.gif">
						<div class="toolbar">
							<div class="contentTil">问题反馈</div>
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
							
							<table border="0" cellspacing="4" cellpadding="0">
								<tr>
									<td>
									反馈人：
									<s:textfield id="empName" name="empName" cssClass="input120" maxlength="16" onkeypress="if(event.keyCode==13){searchMsg();}"/>&nbsp;&nbsp;
									</td>
									<td>
									反馈时段：
									<input id="beginDate" 
									             name="beginDate"
									             value='${beginDate}'
									             type="text"
									             class="Wdate"
									             readonly="readonly"
									             onkeypress="if(event.keyCode==13){searchMsg();} else {return false;}"  
									             onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,isShowClear:false,maxDate:'#F{$dp.$D(\'endDate\')}'})"
									             />
		                           		&nbsp;&nbsp;至&nbsp;&nbsp;
		                            	<input id="endDate" 
									             name="endDate"
									             value='${endDate}'
									             type="text"
									             class="Wdate"
									             readonly="readonly"
									             onkeypress="if(event.keyCode==13){searchMsg();} else {return false;}"  
									             onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,isShowClear:false,minDate:'#F{$dp.$D(\'beginDate\')}',maxDate:'#F{$dp.$D(\'scurrdate\')}'})"
									             />
									   <s:hidden id="scurrdate" value="%{endDate}"/>
									</td>
									<td>
									状态:
									<s:select id="statues" list="#{0:'未回复',1:'已回复'}" headerKey="" headerValue="全部" onchange="searchMsg();">
										
									</s:select>
									</td>
									<td>&nbsp;&nbsp;</td>
																		
									<td><a href="javascript:void(0)" onclick="searchMsg();" class="sbutton">查询</a></td>
								</tr>
							</table>
					
							
							</td>
						</tr>
					</table>		
					</td>
				</tr>
				<tr>
                 <td class="titleStyle">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="30%" class="titleStyleZi">内容信息</td>
                        <td width="69%" align="right">
						<div class="buhuanhangbut">
                            <a href="#" onclick="exportFeedBackList();" class="btnDaochu" title="<s:property value="getText('msg.export')" />">
                            </a>
                          </div>
                        </td>
                      </tr>
                   </table>
                  </td>
				        
				     </tr>
				     <tr>
				        <td class="reportInline" style="vertical-align : top;">
				        <div id="tabAreaId">
				        <div id="Below_new" style="text-align:center"><s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/></div>
						<table id="gala" width="100%"  cellspacing="0"></table>
						</div>
				     </td>
				    
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/copyright.jsp"%>
</div>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="feedback_js.jsp"%>
</body>
</html>

