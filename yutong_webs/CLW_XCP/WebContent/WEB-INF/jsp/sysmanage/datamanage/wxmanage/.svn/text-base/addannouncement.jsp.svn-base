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
<body>
<div id="wrapper">						
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>					
		<div id="content">					
			<s:form id="addAnnouncementform" action="addAnnouncement.shtml" method="post" onsubmit="return false;">
			<s:hidden id="vinStr" name="announcementInfo.gonggao_veh" value=""/>
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
											class="msgTitle">&nbsp;&nbsp;新建公告</span></td>
									</tr>
									<tr>
										<td align="center">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<table width="100%" border="0" cellspacing="2" cellpadding="0">
													<tr>
														<td width="14%" height="28" align="right">公告标题:</td>
														<td width="86%" class="fsBlack" align="left">
														<s:textfield id="gonggao_title" name="announcementInfo.gonggao_title" maxlength="20"/><span class="noticeMsg">(*20字以内)</span>
														</td>
													</tr>
													<tr>
														<td width="14%" height="28" align="right">选择车辆:</td>
														<td width="86%" class="fsBlack" align="left">
														<textarea id="gonggao_veh" 
								                             name="gonggao_veh" cols="40" rows="5"
								                             onclick="openVehicleBrowse()" 
								                             readonly="readonly"></textarea><span class="noticeMsg">*</span>
														</td>
													</tr>
													<tr>
														<td width="14%" height="28" align="right">公告概述:</td>
														<td width="86%" class="fsBlack"  align="left">
															<s:textarea id="gonggao_summary" 
																name="announcementInfo.gonggao_summary"  cols="40" rows="5"/><span class="noticeMsg">(*100字以内)</span>
														</td>
													</tr>
													<tr>
														<td width="14%" height="28"  align="right">公告内容:</td>
														<td width="86%" class="fsBlack"  align="left">
															<s:textarea id="gonggao_content" 
																name="announcementInfo.gonggao_content"  cols="40" rows="8"/><span class="noticeMsg">(*1300字以内)</span>
														</td>
													</tr>
												</table>
												<div class="btnBar">
													<a href="#" onclick="goBack('readyPage.shtml')" class="buttontwo">取消</a>
													<a href="#" class="buttontwo" onclick="submitPostFrom();">确定</a>
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
			</s:form>
	</div>					
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>					
</div>						
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type="text/javascript">
function goBack(url) {
	Wit.goBack(url);
}

function checkGonggaoTitle(){
  	var elm = $('gonggao_title');
  	if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	    return false;
	}
  	if (!Mat.checkLength(
			elm,
			20,
			'公告标题' + '<s:text name="val.feild.overlength"/>' + '20'))
	return false;
	if(Wit.checkErr(elm,/[%"'|\\\/<>,]/)){
		Wit.showErr(elm, "不许出现特殊符号");
	    return false;
	}

  Mat.showSucc(elm);
  return true;
}

function checkGonggaoVeh(){
  	var elm = $('gonggao_veh');
  	if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	    return false;
	}
  Mat.showSucc(elm);
  return true;
}

function checkGonggaoSummary(){
  	var elm = $('gonggao_summary');
  	if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	    return false;
	}
	if (!Mat.checkLength(
			elm,
			100,
			'公告概要' + '<s:text name="val.feild.overlength"/>' + '100'))
	return false;
  	if(Wit.checkErr(elm,/[%"'|\\\/<>,]/)){
		Wit.showErr(elm, "不许出现特殊符号");
	    return false;
	}
  Mat.showSucc(elm);
  return true;
}

function checkGonggaoContent(){
  	var elm = $('gonggao_content');
	if(!Mat.checkRequired(elm)){
	    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	    return false;
	}
	if(Wit.checkErr(elm,/[%"'|\\\/<>,]/)){
		Wit.showErr(elm, "不许出现特殊符号");
	    return false;
	}
	if (!Mat.checkLength(
			elm,
			1300,
			'公告内容' + '<s:text name="val.feild.overlength"/>' + '1300'))
	return false;
  Mat.showSucc(elm);
  return true;
}

function submitPostFrom(){
	trimAllObjs();
	var f1=checkGonggaoTitle();
	var f2=checkGonggaoVeh();
	var f3=checkGonggaoSummary();
	var f4=checkGonggaoContent();
	if(f1&&f2&&f3&&f4){
		Wit.commitAll($('addAnnouncementform'));
	}else  {
	  return false;
	}
}
jQuery(function() {
	jQuery(window).mk_autoresize({
	       height_include: '#content',
	       height_exclude: ['#header', '#footer'],
	       height_offset: 0,
	       width_include: ['#header', '#content', '#footer'],
	       width_offset: 0
	    });
	jQuery('#content').mk_autoresize({
        height_include: '#main',
        height_offset: 0 // 该值各页面根据自己的页面布局调整
      });
});

function openVehicleBrowse() {
  var obj = window.showModalDialog("<s:url value='/wxannouncement/queryVehicle.shtml'/>?vehicle_ln=&rad="+Math.random(),
                                   self,
                                   "dialogWidth=700px;dialogHeight=700px;scroll:yes");
}
</script>

</body>
</html>