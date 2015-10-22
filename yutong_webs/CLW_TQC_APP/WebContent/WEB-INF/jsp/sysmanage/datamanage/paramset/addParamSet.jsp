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
		.mypreview {
		 max-width:120px;
		 max-height:140px;
		 width:expression_r_r(document.body.clientWidth > 120? "120px": "auto" ); 
		 height:expression_r_r(document.body.scrollHeight > 400 ? "140px" : "auto" );
		}
	</style>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<s:form id="addParamSetForm" action="addParamSet" method="post">
			<table id="noticeTable" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top">
						<div class="toolbar">
							<div class="contentTil">参数设置信息</div>
						</div>
					</td>
				</tr>
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
						<table class="msgBox2" border="0" align="center" cellpadding="0" cellspacing="0" style="width: 760px;">
							<tr>
								<td height="32">
									<span class="msgTitle"><s:text	name="新建参数设置" /></span>
								</td>
							</tr>
							<tr>
								<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td align="right">
											<s:text	name="参数名称" />：
										</td>
										<td align="left" class="fsBlack">
											<s:textfield id="paramName" name="paramSet.paramName"  maxlength="60" size="70">
											</s:textfield>
											<s:hidden id="oldParamName" name="paramSet.oldParamName"/>
											<span class="noticeMsg">(以YGB_开始)*</span>
											<span id="paramNameDesc" class="noticeMsg"></span>
											<div>&nbsp;</div>
										</td>
										
									</tr>
									<tr>
										<td align="right">
											<s:text	name="参数值" />：
										</td>
										<td align="left" class="fsBlack">
											<s:textfield id="paramValue" name="paramSet.paramValue" size="70">
											</s:textfield>
											<span class="noticeMsg">*</span>
											<span id="paramValueDesc" class="noticeMsg"></span>
											<div>&nbsp;</div>
										</td>
										
									</tr>
									<tr>
										<td align="right">
											<s:text	name="参数说明" />：
										</td>
										<td align="left" class="fsBlack">
											<s:textarea id="remark" name="paramSet.remark" rows="10" cols="60">
											</s:textarea>
											<span id="remarkDesc" class="noticeMsg"></span>
											<div>&nbsp;</div>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td class="btnBar">
									<a href="#" class="buttontwo" onclick="goBack('paramsetManage.shtml');"><s:text name="button.cancle" /></a> 
									<a href="#" class="buttontwo" onclick="submitForm()"><s:text name="button.queding" /></a>
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
</body>
<%@include file="addParamSet_js.jsp"%>
<script type="text/javascript">
	//页面自适应
	(function (jQuery) {
		jQuery(window).resize(function(){
			testWidthHeight();
		});
		jQuery(window).load(function (){
			testWidthHeight();
		});
	})(jQuery);
	
	//获取页面高度
	function get_page_height() {
		  var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
		  return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
	}
	//获取页面宽度
	function get_page_width() {
		var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
		  return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
	}
	 
	//计算控件宽高
	function testWidthHeight(){
		var h = get_page_height();
		var test=document.getElementById("noticeTable");
		if(h>165){
			test.style.height = h-165;
		}
		jQuery(window).mk_autoresize({
		       height_include: '#content',
		       height_exclude: ['#header', '#footer'],
		       height_offset: 0,
		       width_include: ['#header', '#content', '#footer'],
		       width_offset: 0
		    });
		h = get_page_height();
		if(h>165){
			test.style.height = h-165;
		}
	}
</script>
</html>
