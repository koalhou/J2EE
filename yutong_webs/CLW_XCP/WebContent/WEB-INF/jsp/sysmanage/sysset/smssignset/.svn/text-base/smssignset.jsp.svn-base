<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body>
<div id="wrapper"><%@include file="/WEB-INF/jsp/common/header.jsp"%>
<div id="content">
<div id="main" style="overflow: auto;">
<s:form id="smssignset_form" action="signset" method="post" onsubmit="return false;">
	<table id="contenttable" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="36" valign="top" background="../images/tree_tabBg.gif">
			<div class="toolbar">
			<div class="contentTil">短信签名设置</div>
			</div>
			</td>
		</tr>
		
		<tr>
			<td valign="top">
			<table class="msgBox2" width="650" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="32">
						<div>
							<span class="msgTitle" style="width: 220px;">&nbsp;&nbsp;短信签名设置 </span>
							<s:actionmessage cssStyle="font-size: 12px;height:40px;line-height:30px;color: #009900" />
							<s:actionerror cssStyle="font-size: 12px;height:40px;line-height:30px;color: #CC0000" />
						</div>
					</td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="30" align="right" width="220px">选择签名：</td>
							<td align="left">
								<s:radio name="smsSignFlag" list="smsSignMap" value="%{smsSignFlag}" />
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td align="left" style="background: url(../images/wline.gif) repeat-x top left;">注：短信签名内容来自企业简称</td>
					<td class="btnBar"><a href="#" class="buttontwo" onclick="submitSignSet();"><s:text name="确定" /> </a></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table height="180" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<br />
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
</div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp"%></div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">
  function submitSignSet() {
	jQuery(".actionMessage").html("");
	jQuery(".actionerror").html("");
	if(window.confirm('确定要进行短信签名设置操作？')){
		Wit.commitAll($('smssignset_form'));
    }
  }

  jQuery(function() {
		jQuery('#content').mk_autoresize({
	        height_include: '#main',
	        height_offset: 0 // 该值各页面根据自己的页面布局调整
	      });
		jQuery(window).mk_autoresize({
		       height_include: '#content',
		       height_exclude: ['#header', '#footer'],
		       height_offset: 0,
		       min_height: 700,
		       min_width: 1256,
		       width_include: ['#header', '#content', '#footer'],
		       width_offset: 0
		    });
		
	}); 
</script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
</body>
</html>
