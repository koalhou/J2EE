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
								<table class="msgBox2" width="720" height="400" border="0"
									align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td height="16"><span class="msgTitle">问题反馈详情</span>
										</td>
									</tr>
									<tr>
										<td>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="100px;" height="28" align="right">反馈人：</td>
													<td class="fsBlack"><s:textfield
															id="emp_name" name="feedBackMsg.owner"
															cssStyle="border: 0px; background: #EEEEEE;"
															maxlength="16" readonly="readonly" /></td>
													<td height="28" align="right">员工号：</td>
													<td align="left"><s:textfield id="emp_license"
															name="feedBackMsg.empCode" maxlength="16"
															readonly="readonly"
															cssStyle="border: 0px; background: #EEEEEE;" /></td>
												</tr>

												<tr>
													<td width="100px;" height="28" align="right">反馈时间：</td>
													<td align="left" colspan="3"><s:textfield
															id="feedBackDateP" name="feedBackMsg.suggestDate"
															maxlength="16" readonly="readonly"
															cssStyle="border: 0px; background: #EEEEEE;" /></td>
												</tr>
												<tr>
													<td width="100px;" height="28" align="right">反馈内容：</td>
													<td align="left" colspan="3">
													<div style="width: 460px;height: 150px;background-color: white;overflow: scroll;">
													<s:property value="feedBackMsg.content" />
													</div>		
												    </td>
												</tr>
												<tr>
													<td height="5px" class="lineStyle" colspan="4" style="margin-top: 20px;margin-bottom: 20px;"></td>
												</tr>

												<tr>
													<td  width="100px;" height="28" align="right">回复人：</td>
													<td class="fsBlack">
													<s:textfield name="feedBackMsg.operator"
															cssStyle="border: 0px; background: #EEEEEE;"
															maxlength="16" readonly="readonly" />
															</td>
													<td  width="100px;" height="28" align="right">回复时间：</td>
													<td>
													<s:textfield name="feedBackMsg.operate_time" maxlength="16"
															readonly="readonly"
															cssStyle="border: 0px; background: #EEEEEE;" />
												   </td>
												</tr>
												<tr>
													<td width="100px;" align="right"
														style="padding-top: 3px;">回复内容：</td>
													<td colspan="3">
													<div style="width: 460px;height: 150px;overflow: scroll;margin-top: 10px;background-color: white;">
													  <s:property value="feedBackMsg.operate_content" />
													</div>
													
													</td>
												</tr>
											</table></td>
									</tr>

									<tr>
										<td align="right" colspan="4">
										<a href="javascript:void(0)" style="margin-right: 50px;"
											onclick="goBack();" class="buttontwo"> 取消 </a></td>
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
		function goBack() {
			window.location.href='<s:url value="/feedback/readyPage.shtml" />';
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
