<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseonly_css.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
			<s:hidden id="user_org_id" name="user_org_id"/>
			<s:hidden id="chooseorgid" name="chooseorgid"/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>			
						<td id='leftdiv' valign="top" class="leftline">
							<!-- 组织树 组织  -->
							<div id="content_leftside">
								<div id="leftInfoDiv" class="treeTab">
									<a href="#" class="tabfocus" onfocus="this.blur()">组织</a>
									<a href="#" class="hideLeft" onfocus="this.blur()" onclick="HideandShowControl()"></a>
								</div>
	          					<div id="treeDemoDiv" class="treeBox"><ul id="treeDemo" class="ztree"></ul></div>
					  		</div>
						</td>
						<td id="middeldiv" valign="top" class="sleftline" style="display: none;">
			                   <div id="content_middelside">
				                <div>
			            	     <a href="#" id="showleftbutton" class="showLeft" onfocus="this.blur()" onclick="HideandShowControl()"></a>
			          	       </div>
			                  </div>
		            	</td>
					<td id="rightdiv" valign="top">
						<div id="content_rightside"><!-- content_rightside 开始 -->	 
						<div class="titleBar">
							<div class="title">员工刷卡记录</div>
							<div class="workLink">
								<div class="export">
									<a href="javascript:void(0);" class="export_icon" onfocus="this.blur()" onclick="javascript:exportData();"><s:text name="button.daochu" /></a>
								</div>
							</div>
						</div><!-- statusManger 开始 -->
						<div class="search_condition"><!-- 查询区域 开始 -->
							<table id="search_stu_tab" width="500" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="100" align="right"><strong>姓&nbsp;&nbsp;&nbsp;名：</strong></td>
									<td class="text100">
										<s:textfield id="stu_name" name="stu_name" cssClass="text100"  maxlength="16" onkeypress="if(event.keyCode==13){searchList();}"/>
									</td>
									<td width="100" align="right"><strong>员&nbsp;工&nbsp;号&nbsp;：</strong></td>
									<td class="text100">
										<s:textfield id="stu_code" name="stu_code" cssClass="text100"  maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
									</td>
									<td width="100" align="right"><strong>班车号：</strong></td>
									<td class="text100">
										<s:textfield id="vehicle_code" name="vechicle_code" cssClass="text100"  maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
									</td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td width="100" align="right"><strong>车牌号：</strong></td>
									<td class="text100">
										<s:textfield id="vehicle_ln" name="vehicle_ln" cssClass="text100"  maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
									</td>
									<td width="100" align="right"><strong>乘车状态：</strong>
									</td>
									<td align="left" width="100">
										<select id="strideState" name="strideState" style=" width:100px;" onchange="searchList()">
						                	<option value="" selected="selected">全部</option>
						                	<option value="1">乘车正常</option>
						                	<option value="0">乘车异常</option>
						               	</select>
									</td>
									<td width="100" align="right"><strong>时&nbsp;&nbsp;&nbsp;段：</strong>
									</td>
									<td align="left" width="130">
										<input readonly="readonly" id="begTime" name="begTime" value="${queryObj.begTime}" 
										class="Wdate" type="text" style="width:100px;" 
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'endTime\')}',position:{top:'under'},isShowClear:false})"/>
										
										至
									</td>
									<td width="20" align="center">
										<input id="endTime" name="endTime"  value="${queryObj.endTime}" 
										class="Wdate" type="text" readonly="readonly" style="width:100px;" 
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begTime\')}',maxDate:'%y-%M-%d',position:{top:'under'},isShowClear:false})"/>
									</td>
									<td align="left" width="100">
										<a href="javascript:void(0);" class="btn-blue"
										onfocus="this.blur()" onclick="javascript:searchList();">查询</a>
									</td>
									<td>&nbsp;</td>
									<td width="90" align="left">
									</td>
								</tr>
							</table><!-- 查询区域 开始 -->
							<!-- 下面是列表不细作,不使用 -->
							<div class="table_list">
								<table cellspacing="4" width="100%">
				   					<tr>
				    					<td><table id="gala" width="100%" cellspacing="0"></table></td>
	                				</tr>
	                			</table>
							</div>
						 </div><!-- 查询区域  结束-->
					  </div><!-- content_rightside 结束 -->
					</td>
				</tr>
		</table>
		<s:form id="export_form" action="" method="post">
			<s:hidden id="queryObj.organization_id" name="queryObj.organization_id"/>
			<s:hidden id="queryObj.vehicle_code" name="queryObj.vehicle_code"/>
			<s:hidden id="queryObj.vehicle_ln" name="queryObj.vehicle_ln"/>
			<s:hidden id="queryObj.stu_name" name="queryObj.stu_name"/>
			<s:hidden id="queryObj.stu_code" name="queryObj.stu_code"/>
			<s:hidden id="queryObj.begTime" name="queryObj.begTime"/>
			<s:hidden id="queryObj.endTime" name="queryObj.endTime"/>
			
			<s:hidden id="queryObj.sortname" name="queryObj.sortname"/>
			<s:hidden id="queryObj.sortorder" name="queryObj.sortorder"/>
			<s:hidden id="queryObj.strideState" name="queryObj.strideState"/>
		</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="stride_validate_new.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseonly_js.jsp"%>
</body>
</html>