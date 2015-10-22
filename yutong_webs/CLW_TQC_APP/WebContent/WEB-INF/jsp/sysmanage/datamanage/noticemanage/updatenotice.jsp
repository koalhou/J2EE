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
		<s:form id="updatenoticeform" action="/notice/updateNotice" method="post" enctype="multipart/form-data">
			<s:hidden name="page" />
			<s:hidden name="pageSize" />
			<table id="noticeTable" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top">
						<div class="toolbar">
							<div class="contentTil">公告信息</div>
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
					<div id="divheightsize" style="width:100%;overflow-y:auto;">
						<table class="msgBox2" border="0" align="center" cellpadding="0" cellspacing="0" style="width: 760px;">
							<tr>
								<td height="32">
									<span class="msgTitle"><s:text	name="公告详情" /></span>
								</td>
							</tr>
							<tr>
								<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="100px" height="28" align="right">
											<s:text	name="公告时间" />：
										</td>
										<td width="130px" class="fsBlack">
										 <input id="gonggaoDate"
												name="gonggao.gonggaoDate"
												readonly="readonly" style="width:150px;"/> 
											<span class="noticeMsg">*</span>
											<span id="gonggaoDateDesc" class="noticeMsg"></span>
											<s:hidden id="sgonggaoDate" name="gonggao.sgonggaoDate"/>
										</td>
									</tr>
									<tr>
										<td align="right">
											<s:text	name="标题" />：
										</td>
										<td align="left" class="fsBlack">
											<s:textfield id="gonggaoTitle" name="gonggao.gonggaoTitle"  maxlength="40" size="70" readonly="true">
											</s:textfield>
											<span class="noticeMsg">(40字以内)*</span>
											<span id="gonggaoTitleDesc" class="noticeMsg"></span>
											<div>&nbsp;</div>
										</td>
										
									</tr>
									<tr>
										<td align="right">
											<s:text	name="消息概述" />：
										</td>
										<td align="left" class="fsBlack">
											<s:textarea id="gonggaoSummary" name="gonggao.gonggaoSummary" rows="5" cols="60" readonly="true">
											</s:textarea>
											200字以内
											<span id="gonggaoSummaryDesc" class="noticeMsg"></span>
											<div>&nbsp;</div>
										</td>
									</tr>
									<tr>
										<td align="right">
											<s:text	name="内容" />：
										</td>
										<td align="left" colspan="5" class="fsBlack">
										<!-- 
											<s:textarea id="gonggaoContent" name="gonggaoContent" rows="15" cols="80">
											</s:textarea>
									     -->
									     <div id="gonggaoContent" style="height: 300px;width: 500px;border: 1px;background: white;overflow:auto;">
									     </div>
											2500字以内
											<span id="gonggaoContentDesc" class="noticeMsg"></span>
											<div>&nbsp;</div>
											<s:hidden name="gonggao.gonggaoContent" id="gonggaoContentHide"/>
										</td>
									</tr>
									<tr style="display:none;">
										<td align="right">
											<s:text	name="图片" />：
										</td>
										<td align="left" colspan="3">
											<div id="noPhoto">
											    <input id="fileupload" 
												       name="fileupload" 
												       type="file" 
												       size="50"
												       disabled="disabled"
												       style="height: 20px;line-height: 20px;font-size: 12px;border: #E0E0E0 1px solid;padding-left: 2px;padding-top: 1px;"
												       onchange="uploadImage()"/>
												 (200k以内)
											</div>
											<s:hidden id="photo1" name="gonggao.photo1"/>
										</td>
										<td align="center" colspan="2">
											<s:label id="errorLbl" cssStyle="font-size:12px;color:#CC0000"/>
											<s:label id="successLbl" cssStyle="font-size:12px;color:#009900"/>
										</td>
									</tr>
									<tr>
										<td align="right">
											&nbsp;
										</td>
										<td align="left" colspan="5">
											<%-- <s:checkbox name="isCheck"/>
											置顶显示 --%>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td class="btnBar">
									<a href="#" class="buttontwo" onclick="goBack('noticeManage.shtml');"><s:text name="button.cancle" /></a>
									<!--<a href="#" class="buttontwo" onclick="submitForm()"><s:text name="button.queding" /></a>  --> 
									<s:hidden name="gonggao.gonggaoId"/>
								</td>
							</tr>
						</table>
						</div>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="updatenotice_js.jsp"%>
</body>
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
