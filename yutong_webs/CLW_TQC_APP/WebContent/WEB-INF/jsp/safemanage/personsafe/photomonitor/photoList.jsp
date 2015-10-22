<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<!-- 引入照片列表页样式  -->
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/photo.css" />" />
<!-- 分页标签插件样式  -->
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/pagination.css'/>" />
<!-- 全局通用样式 -->
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterprise_css.jsp"%>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<s:form id="photo_form" action="photomanage" method="post">
		<s:hidden id="vehicle_vins" name="vehicle_vins" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td id='leftdiv' valign="top" class="leftline">
					<!-- 组织树 组织  -->
					<div id="content_leftside">
						<div id="leftInfoDiv" class="treeTab">
							<a href="#" class="tabfocus" onfocus="this.blur()">组织</a> <a
								href="#" class="hideLeft" onfocus="this.blur()"
								onclick="HideandShowControl()"></a>
						</div>
						<!-- 组织树 查询  -->
						<div id="searchPlanDiv" class="searchPlan">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="130" align="right"><input id="vehicleLn"
										type="text" class="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchTree();}"/></td>
									<td align="center"><a href="#" class="btnbule"
										onfocus="this.blur()" onclick="searchTree()">查询</a></td>
								</tr>
							</table>
						</div>
						<!-- 组织车辆树  -->
						<div id="lefttree" class="treeBox">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
					</div>
				</td>
				<td id="middeldiv" valign="top"
					class="sleftline" style="display: none;">
					<div id="content_middelside">
						<a href="#" id="showleftbutton" class="showLeft"
							onfocus="this.blur()" onclick="HideandShowControl()"></a>
					</div>
				</td>
				<td id="rightdiv" valign="top">
					<div id="content_rightside">
						<div class="titleBar">
							<div class="title">照片管理</div>
						</div>
						<div id="photoManger">
							<div class="photo-sech">
								<div class="div_float">
									<font>触发类型:</font> <select id="photo_event" name="photo_event" onchange="searchList();">
										<option value="">全部</option>
										<option value="0">手动拍照</option>
										<option value="1">定时拍照</option>
										<!--<option value="2">SOS拍照</option>
										<option value="3">碰撞侧翻拍照</option>
										<option value="4">开门拍照</option>
										<option value="5">关门拍照</option>
										<option value="6">行驶开关门拍照</option>-->
										<option value="7">定距拍照</option>
										<!--<option value="99">其他拍照</option>-->
									</select> <font>时间:</font> <input readonly="readonly" id="start_time"
										name="start_time" value="${start_time}" class="Wdate"
										type="text" style="width: 100px"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end_time\')}',isShowClear:false})"
										style="width: 90px" /> <font><s:text name="common.zhi" />
									</font> <input readonly="readonly" id="end_time" name="end_time"
										value="${end_time}" class="Wdate" type="text"
										style="width: 100px"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'start_time\')}',maxDate:'%y-%M-%d',isShowClear:false})"
										style="width: 90px" />
								</div>
								<div>
									<a href="#" class="btn-blue" onfocus="this.blur()"
										style="margin-top: 3px;" onclick="searchList();"><s:text
											name="common.chaxun" /></a>
								</div>
							</div>
							<div id="photo-list" class="photo-list">
								<ul id="gala" style="min-height: 150px;height: auto;min-width:150px;width:auto;overflow-x: no;overflow-y: auto;"></ul>
							
							<!-- 将上面列表样式结束 -->
							<div style="clear:both; height:20px;"></div>
							<table align="center" style="width: 100%;">
								<tr>
									<td style="width: 55%; text-align: right;"><div id="Pagination" class="pagination"></div></td>
									<td style="width: 45%; text-align: left;"><div id="ResultInfo" class="resultinfo"></div></td>
								</tr>
							</table>
							</div>
						</div>
						<!-- end photoManger -->
					</div> <!-- end content_rightside -->
				</td>
			</tr>
		</table>
	</s:form>
	<!-- 弹出层（相册）开始   -->
	<div id="popArea3" class="popArea3">
		<table id="divTitle" width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
    		<td width="3"><img src="../newimages/photo_del_title_bg1.gif" width="3" height="31" /></td>
    		<td class="popArea3_title_bg">
    		<h3>图片详情<a href="#" class="close" onfocus="this.blur()" onclick="HideGalleria()"></a></h3>
    		</td>
    		<td width="3"><img src="../newimages/photo_del_title_bg3.gif" width="3" height="31" /></td>
  		</tr>
  		</table> 
	<iframe id="iframeshowArea" name="iframeshowArea" src='' height="385px" width="100%" frameborder="0" scrolling="no"></iframe>
	</div>
	<!-- 弹出层（相册）结束 -->
	</div><!-- content结束 -->
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<!-- 引入组织树结构  (组织 车辆)  -->
	<%@include file="/WEB-INF/jsp/treemanage/zTree_withoutonline.jsp"%>
	<!-- 页面引用js 开始 -->
	<script type="text/javascript" language="JavaScript1.2"
		src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"
		src="<s:url value='/scripts/wit/tools.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"
		src="<s:url value='/scripts/validate/validation.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/pagination/jquery.page.js'/>"></script>
	<!-- 引入拖拽工具类  -->
    <script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
    <!-- 页面引用js 结束 -->
    <%@include file="photoList_js.jsp"%>
</body>
</html>