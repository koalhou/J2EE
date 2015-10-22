<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterprise_css.jsp"%>
<title><s:text name="common.title" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>		
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/gpspostion.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />		
<!-- 中文注释 -->
</head>
<body>
	<div id="wrapper" style="position: absolute;">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">

			<input type="hidden" id="vehicleLn" name="vehicleLn" />
			
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top" class="leftline" id="leftdiv">
						<div id="content_leftside">
							<div class="treeTab">
								<a class="tabfocus" onfocus="this.blur()" id="enttabid"
									onclick="tabSwitch('enttabid')">组织</a> <a class="hideLeft"
									onfocus="this.blur()" id="hideleftbutton"
									onclick="HideandShowControl()"></a>
							</div>

							<div class="newsearchPlan">
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="130px" align="right"><input maxlength="60"
											id="vehicleLntext" name="vehicleLntext" type="text"
											class="input120"
											onkeypress="if(event.keyCode==13){searchTreeClick();}" />
										</td>
										<td align="center"><a class="btnbule"
											onfocus="this.blur()" onclick="searchTreeClick()">查询</a>
										</td>
									</tr>
								</table>
							</div>
							<div id="treeDemoDiv" class="treeBox">
								<ul id="treeDemo" class="ztree"></ul>
							</div>
						</div></td>
					<td valign="top" class="sleftline" id="middeldiv"
						style="display: none;">
						<div id="content_middelside">
							<div>
								<a class="showLeft" onfocus="this.blur()" id="showleftbutton"
									onclick="HideandShowControl()"></a>
							</div>
						</div></td>
					<td valign="top" id="rightdiv">
						<div id="content_rightside">
							<div class="titleBar" id="titleBarMap">
								<div class="title">车辆运行时间</div>
								<div class="workLink">
									<a href="javascript:void(0)" onclick='addpage();'
										class="squarLink">
										<img src="<s:url value='../newimages/btn_add.png'/>" align="bottom"/>
										新增</a>
										<!-- 
										<a href="javascript:void(0)" onclick='updatepage();'
										class="squarLink">
										<img src="<s:url value='../newimages/btn_update.png'/>" align="bottom"/>
										修改</a>
										<a href="javascript:void(0)" onclick='changeStatus("0");'
										class="squarLink">
										<img src="<s:url value='../newimages/btn_disable.png'/>" align="bottom"/>
										禁用</a>
										<a href="javascript:void(0)" onclick='changeStatus("1");'
										class="squarLink">
										<img src="<s:url value='../newimages/btn_able.png'/>" align="bottom"/>
										启用</a>
										 -->
								</div>
							</div>
							<div id="iCenter">
								<table width="100%" border="0" cellspacing="5" cellpadding="0">
									<tr>
										<td valign="top">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" class="reportOnline">
												<tr>
													<td class="reportInline" align="left">
														<div id="tabAreaId">
															<s:form action="runtimeList" id="userselect"
																method="post">
																<div id="Below_new" align="center">
																	<s:actionmessage cssStyle="color:green" />
																	<s:actionerror cssStyle="color:red" />
																</div>
															</s:form>
															<table id="usertbl" width="100%" cellspacing="0">
															</table>
														</div></td>
												</tr>
											</table></td>
									</tr>
								</table>
							</div>
						</div></td>
				</tr>
			</table>
		</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<%@include file="left_org_tree_js.jsp"%>
	<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
	<%@include file="runtimeList_js.jsp"%>
</body>
</html>