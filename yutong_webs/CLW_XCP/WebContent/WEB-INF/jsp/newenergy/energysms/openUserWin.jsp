<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style>
		.contentWin {
			width: 400px;
		}
	</style>
</head>
<body>
<div id="wrapper" style="width: 400px;">
	<div id="contentWin" style="width: 400px;">
	<s:hidden name="page" />
	<s:hidden name="pageSize" />
	<table id="midarerId" style="width: 400px;" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
				<input type="hidden"  id="UrlList" name="UrlList" value="<s:property value="#session.perUrlList.contains('111_3_5_2_7')"/>" />
	    		<input type="hidden"  id="UrlList2" name="UrlList2" value="<s:property value="#session.perUrlList.contains('111_3_5_2_8')"/>" />
				<table style="width:400px" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td class="reportOnline2">
						<table style="width: 400px;" id="selectAreaId" border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td>
							<table  border="0" cellspacing="4" cellpadding="0">
								<tr>
									<td><s:label>姓名</s:label></td>
									<td align="left">
										<s:textfield id="userName" name="userName" maxlength="15" />
									</td>
									<td><a href="#" class="sbutton" onclick="searchList();">查询</a></td>
								</tr>
							</table>
							</td>
						</tr>
						</table>
						</td>
					</tr>
					<tr>
					<td valign="top" >
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
						<tr>
							<td >
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="titleStyle">							
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td width="30%" class="titleStyleZi">用户信息</td>
                                      <td width="69%" align="right">
									  	<div class="buhuanhangbut">
												<a href="javascript:void(0);" id="gameover" class="sbutton" onclick="parnetWinClose();">完成</a>
									  	</div>
									  </td>
                                      <td width="1%">&nbsp;</td>
                                    </tr>
                                  </table>
									</td>
								</tr>
							</table>
							</td>
                                </tr>
                                <tr>
                                  <td class="reportInline" align="left">
						    
							<div id="tabAreaId">
							<s:form action="usermanageAction" id="userselect" method="post">
							<div align="center">
							<s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/>
							</div>
							</s:form>				
							<table id="usertbl" width="100%"  cellspacing="0">
					   		</table>						
							</div>
							</td>
						</tr>
					</table>
					</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</div>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="openUserWin_js.jsp"%>
</body>
</html>
<style>
	body {
		min-width: 400px;
	}
</style>