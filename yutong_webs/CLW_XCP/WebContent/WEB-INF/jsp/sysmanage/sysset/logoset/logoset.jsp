<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>	
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
		type="text/css" media="all" />		
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body>  	
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	
	<div id="content">		
	  <div id="main" style="overflow: auto;">
		<s:form id="logo_form" action="logosetAction" method="post" onsubmit="return false;"
			enctype="multipart/form-data">
			<table  id="contenttable" width="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td height="36" valign="top" background="../images/tree_tabBg.gif">
						<div class="toolbar">
							<div class="contentTil">
								<s:text name="menu3.qylogo" />
							</div>
							
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<!-- message -->
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="center">
									<s:actionmessage cssStyle="font-size: 12px;color: #009900" />
									<s:actionerror cssStyle="font-size: 12px;color: #CC0000" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<table class="msgBox2" width="650" border="0" align="center"
							cellpadding="0" cellspacing="0">
							<tr>
								<td height="32">
									<span class="msgTitle">&nbsp;&nbsp;<s:text
											name="menu3.qylogo" /> </span>
								</td>
							</tr>
							<tr>
								<td align="center">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">

										<tr>
											<td width="14%" height="30" align="right">
												LOGO图片：
											</td>
											<td align="left">
												显示在左上方，最大尺寸为335*98像素，格式为jpg。
											</td>
										</tr>
										<tr>
											<td height="30" align="right">
												LOGO路径：
											</td>
											<td align="left">
												<s:file name="file" id="file"></s:file>
												<input type="hidden" id="ROLES_STR" name ="ROLES_STR" value=""/><span class="noticeMsg">*</span>
											</td>
											<td>
												&nbsp;

											</td>
										</tr>

									</table>
								</td>
							</tr>
							<tr>
								<td class="btnBar">
									<s:if test="#session.perUrlList.contains('111_3_5_4_2')">
								    <a href="#" class="buttontwo" onClick="submilogoDefaultForm()"><s:text
												name="默认LOGO" /> </a>    
									</s:if>
									<a href="<s:url value='/logoset/logoset.shtml' />"
										class="buttontwo"><s:text name="button.chongzhi" /> </a>
									<s:if test="#session.perUrlList.contains('111_3_5_4_1')">
										<a href="#" class="buttontwo" onClick="submilogoForm()"><s:text
												name="设置" /> </a>
									</s:if>
								                                
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
				<td>
				<table height="180" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<br/>
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
<script type="text/javascript">
 function submilogoForm() {
       var file=document.getElementById('file');
       if(file.value.length<=0){
       	var elm = $('ROLES_STR');
       	Wit.showErr(elm, "请选择图片路径");
         return false;
       }
	if(window.confirm('<s:text name="logo.conf" />')){
	   //var form=document.getElementById('logo_form');
       //form.submit();
       var form = document.getElementById('logo_form');
	   Wit.commitAll(form);
    }
 }
 function submilogoDefaultForm() {
	if(window.confirm('<s:text name="logo.conf.default" />')){
	   //var form=document.getElementById('logo_form');
	   //form.action='setLogoDefaultAction.shtml';
       //form.submit();
       var form = document.getElementById('logo_form');
       form.action='setLogoDefaultAction.shtml';
	   Wit.commitAll(form);
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

