<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:property value="getText('common.title')" />
</title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
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
	max-width: 120px;
	max-height: 140px;
	width: expression_r_r(document.body.clientWidth >   120 ?   "120px" :   "auto");
	height: expression_r_r(document.body.scrollHeight >   400 ?   "140px" :   "auto"
		);
}
</style>
</head>
<body>
	<div id="wrapper">
		<%@include file="/WEB-INF/jsp/common/menu.jsp"%>
		<div id="content">
			<s:form id="feedbackUpdate" action="feedbackUpdate.shtml" method="post">
				<s:hidden name="feedBackMsg.suggestId"/>
				<s:hidden name="feedBackMsg.content"/>
				<div class="toolbar">
					<div class="contentTil">员工信息</div>
				</div>
				<div id="studentDiv" style="overflow: auto; clear: both;">
					<table id="studentTable" width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td align="center"><s:actionerror theme="mat" /> <s:fielderror
												theme="mat" /> <s:actionmessage theme="mat" />
										</td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<td valign="top">
								<table class="msgBox2" width="720" height="450" border="0"
									align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td height="16"><span class="msgTitle">问题回复</span>
										</td>
									</tr>
									<tr>
										<td>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="100px" height="28" align="right">反馈人：</td>
													<td class="fsBlack"><s:textfield
															id="emp_name" name="feedBackMsg.owner"
															cssStyle="border: 0px; background: #EEEEEE;"
															maxlength="16" readonly="readonly" /></td>
													<td  width="100px" height="28" align="right">员工号：</td>
													<td align="left"><s:textfield id="emp_license"
															name="feedBackMsg.empCode" maxlength="16"
															readonly="readonly"
															cssStyle="border: 0px; background: #EEEEEE;" /></td>
												</tr>

												<tr>
													<td width="100px" height="28" align="right">反馈时间：</td>
													<td align="left" colspan="3"><s:textfield
															id="feedBackDateP" name="feedBackMsg.suggestDate"
															maxlength="16" readonly="readonly"
															cssStyle="border: 0px; background: #EEEEEE;" /></td>
												</tr>
												<tr>
													<td width="100px" height="28" align="right">反馈内容：</td>
													<td align="left" colspan="3">
													<div style="width: 460px;height: 150px;overflow: scroll;margin-top: 10px;background-color: white;">
													<s:property value="feedBackMsg.content"/>
													</div>
													</td>
												</tr>
												<tr>
													<td height="10px" class="lineStyle" colspan="4" style="margin-top: 20px;margin-bottom: 20px;"></td>
												</tr>
											    
												<tr>
													<td width="100px" height="28" align="right"
														style="padding-top: 3px;">回复内容：</td>
													<td colspan="3" style="vertical-align: top">
													<s:textarea id="operate_content"
															name="feedBackMsg.operate_content" rows="10" cols="83" onfocus="clearMsg();" onkeyup="calWord();"></s:textarea>
													<span id="msg" style="color: red"></span>
													<div style="margin-top: 10px;">
													您还可以输入<span id="wordCount" style="font-weight: bold;">1000</span>个字
													</div>
													</td>
												</tr>

											</table></td>
									</tr>

									<tr>
										<td>
										<a href="javascript:void(0)"
											onclick="goBack();" class="buttontwo" style="float: right;margin-right: 20px;"> 取消 </a>
										
										<a href="javascript:void(0)"
										onclick="ok();" class="buttontwo" style="float: right;margin-right: 20px;"> 确定 </a>
										</td>
									</tr>
								</table></td>
						</tr>
					</table>
				</div>
			</s:form>
		</div>
		<%@include file="/WEB-INF/jsp/common/copyright.jsp"%>
	</div>
	<script>
	function formatSpecialChar(str) {
		return str.replace(/\\/g,"\\\\")
		          .replace(/%/g,"\\%")
		          .replace(/_/g,"\\_")
		          .replace(/％/g,"\\%")
		          .replace(/＿/g,"\\_")
		          .replace(/^\s+|\s+$/g, '');
}
		function goBack() {
			window.location.href='<s:url value="/feedback/readyPage.shtml" />';
		}
		function ok(){
			var operate_content=$("#operate_content").text();
			if(formatSpecialChar(operate_content).length<1){
				$("#msg").text("不能为空");
				return false;
			}else{
				$("#msg").text("");
			}
			if(operate_content.length>1000){
				$("#msg").text("不能超过1000字");
				return false;
			}else{
				$("#msg").text("");
			}
			$("#feedbackUpdate").submit();
		}
		function clearMsg(){
			$("#msg").text("");
		}
		function calWord(){
			var operate_content=$("#operate_content").text();
			var len=operate_content.length;
			if(len>1000){
				$("#wordCount").text(0);
				$("#operate_content").text(operate_content.substr(0,1000));
			}else{
				$("#wordCount").text(1000-len);
			}
			
		}
		jQuery(function() {
			//获取页面高度
			function get_page_height() {
				var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
				return (parseInt(jQuery(window).height()) <= min_height) ? min_height
						: jQuery(window).height();
			}
			//获取页面宽度
			function get_page_width() {
				var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
				return (parseInt(jQuery(window).width()) <= min_width) ? min_width
						: jQuery(window).width();
			}

			//计算控件宽高
			function testWidthHeight() {
				var h = get_page_height();
				var w = get_page_width();
				jQuery(".bDiv").height(h - 330);
				jQuery(".flexigrid").width(w - 23);

				jQuery(window).mk_autoresize({
					height_include : '#content',
					height_exclude : [ '#header', '#footer' ],
					height_offset : 0,
					width_include : [ '#header', '#content', '#footer' ],
					width_offset : 0
				});

				h = get_page_height();
				w = get_page_width();
				jQuery(".bDiv").height(h - 330);
				jQuery(".flexigrid").width(w - 23);

			}
		});
	</script>
</body>
</html>
