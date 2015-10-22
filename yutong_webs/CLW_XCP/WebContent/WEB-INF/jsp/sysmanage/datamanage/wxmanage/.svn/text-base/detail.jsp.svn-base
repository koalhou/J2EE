<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<link rel="stylesheet" href="<s:url value='/scripts/mktree.css' />" type="text/css" media="all" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<style>
.msgOV{
	padding:10px; 
	border:1px #000 dashed; 
	background-color:#fff; 
	text-align:left; 
	width:600px; 
	margin:0 auto;
}

.msgText{
	padding:10px; 
	width:600px; 
	margin:10px auto; 
	text-align:left; 
	font-size:12px;
	font-family:"宋体"

}	
	
</style>
<body>
<div id="wrapper">						
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>					
		<div id="content">					
			<div id="main" style="overflow: auto;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td valign="top">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="36" valign="top" background="../images/tree_tabBg.gif">
										<div class="toolbar">
										<div class="contentTil">公告管理</div>
										
										</div>
										</td>
									</tr>
								</table>
								<table class="msgBox2" width="650" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td height="32" background="../images/msg_title_bg.gif"><span
											class="msgTitle">&nbsp;&nbsp;公告详情</span></td>
									</tr>
									<tr>
										<td align="center">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<table width="100%" border="0" cellspacing="2" cellpadding="0">
													<tr><!-- 消息标题 -->
												        <td height="32" width="100%" background="<s:url value='/images/msg_title_bg.gif'/>" align="center" colspan="5">
												          <span class="msgTitle">
												            <s:property value="announcementInfo.gonggao_title"/>
												          </span>
												        </td>
												      </tr>
												      <tr><!-- 消息概述 和 消息内容 -->
												        <td colspan="5">
												        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
												        	   <div class="msgOV" style="word-wrap:break-word"><s:property value="announcementInfo.gonggao_summary"/></div>
												        	   <div class="msgText" style="word-wrap:break-word"><pre><s:property value="announcementInfo.gonggao_content"/></pre></div>
												        </table>
												        </td>
												      </tr>
												</table>
												<div class="btnBar">
													<a href="#" onclick="goBack('readyPage.shtml')" class="buttontwo">取消</a>
												</div>
						
										</table>
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
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>					
</div>						
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type="text/javascript">
function goBack(url) {
	Wit.goBack(url);
}
</script>
</body>
</html>