<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseroute_css.jsp"%>
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
									<a href="#" class="tabfocus" onfocus="this.blur()">线路</a>
									<a href="#" class="hideLeft" onfocus="this.blur()" onclick="HideandShowControl()"></a>
								</div>
								<div class="newsearchPlan">
						            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						              <tr>
						                <td width="130" align="right"><input id="search_route_name" type="text" class="input120" maxlength="14" onkeypress="if(event.keyCode==13){searchRoute();}"/></td>
						                <td align="center"><a href="javascript:searchRoute()" class="btnbule">查询</a></td>
						              </tr>            
						            </table>
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
							<div class="title">学生乘车记录</div>
							<div class="workLink">
							  <div class="export">
				              	<a href="javascript:void(0);" class="export_icon" onfocus="this.blur()" onclick="javascript:exportData();"><s:text name="button.daochu" /></a>
				              </div>
							</div>
						</div><!-- statusManger 开始 -->
						<div id="statusManger">	
							<div class="car-info">
							<!-- 车牌号下拉框 及 4个状态单选框 (开始) -->
								<table width="600" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="15">&nbsp;</td>
										<td width="56" align="right"><strong>车牌号：&nbsp;</strong>
										</td>
										<td width="100">
									    <select id="selectBus" name="selectBus" style="width:110px;" onchange="searchList();">
	                    					<option value="">请选择车牌</option>
	                  					</select>
										</td>
										<td width="15">&nbsp;</td>					
			  							<s:hidden id="st_ride_flag" name="st_ride_flag"/>
										<td><input type="checkbox" name="st_ride_flag_1" id="st_ride_flag_1" checked="true" onclick="searchList();"/>
										</td>
										<td>上学正常</td>
	
										<td><input type="checkbox" name="st_ride_flag_2" id="st_ride_flag_2" checked="true" onclick="searchList();"/>
										</td>
										<td>上学异常</td>
	
										<td><input type="checkbox" name="st_ride_flag_3" id="st_ride_flag_3" checked="true" onclick="searchList();"/>
										</td>
										<td>放学正常</td>
	
										<td><input type="checkbox" name="st_ride_flag_4" id="st_ride_flag_4" checked="true" onclick="searchList();"/>
										</td>
										<td>放学异常</td>
									</tr>
								</table>
								<!-- 车牌号下拉框 及 4个状态单选框 (结束) -->
							</div>
						</div>
						
						<div id="info_stu_div">
						<div style="height: 5px;"></div><!-- 详情显示区域（开始） -->
						<table width="848" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="30" align="right"><strong>姓&nbsp;&nbsp;&nbsp;名：</strong>
								</td>
								<td class="text-bg-132">
								<input id="stu_name_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
								</td>
								<td align="right"><strong>学&nbsp;&nbsp;&nbsp;校：</strong>
								</td>
								<td class="text-bg-132">
								<input id="stu_school_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
								</td>
								<td align="right"><strong>班&nbsp;&nbsp;&nbsp;级：</strong>
								</td>
								<td class="text-bg-132">
								<input id="stu_class_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
								</td>
								<td align="right"><strong>学&nbsp;&nbsp;&nbsp;号：</strong>
								</td>
								<td class="text-bg-132">
								<input id="stu_code_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
								</td>
							</tr>
							<tr>
								<td height="30" align="right"><strong>站&nbsp;&nbsp;&nbsp;点：</strong>
								</td>
								<td class="text-bg-132">
								<input id="site_name_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
								</td>
								<td align="right"><strong>线&nbsp;&nbsp;&nbsp;路：</strong>
								</td>
								<td class="text-bg-132">
								<input id="route_name_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
								</td>
								<td align="right"><strong>状&nbsp;&nbsp;&nbsp;态：</strong>
								</td>
								<td class="text-bg-132">
								<input id="st_ride_flag_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
								</td>
								<td align="right"><strong>时&nbsp;&nbsp;&nbsp;间：</strong>
								</td>
								<td class="text-bg-132">
								<input id="terminal_time_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
								</td>
							</tr>
							<tr>
								<td height="30" align="right"><strong>车牌号：</strong>
								</td>
								<td class="text-bg-132">
								<input id="vehicle_ln_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
								</td>
								<td align="right"><strong>驾驶员：</strong>
								</td>
								<td class="text-bg-132">
								<input id="driver_name_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
								</td>
								<td align="right"><strong>跟车教师：</strong>
								</td>
								<td class="text-bg-132">
								<input id="sichen_name_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
								</td>
								<td align="right">&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</table>
						<div style="height: 5px;"></div><!-- 详情显示区域（结束） -->
						</div>
	
						<div class="search_condition"><!-- 查询区域 开始 -->
							<table id="search_stu_tab" width="980" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="65" align="right"><strong>姓&nbsp;&nbsp;&nbsp;名：</strong></td>
									<td class="text100">
										<s:textfield id="stu_name" name="stu_name" cssClass="text100"  maxlength="16" onkeypress="if(event.keyCode==13){searchList();}"/>
									</td>
									<td width="65" align="right"><strong>学&nbsp;&nbsp;&nbsp;校：</strong></td>
									<td class="text100">
										<s:textfield id="stu_school" name="stu_school" cssClass="text100"  maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
									</td>
									<td width="65" align="right"><strong>班&nbsp;&nbsp;&nbsp;级：</strong></td>
									<td class="text100">
										<s:textfield id="stu_class" name="stu_class" cssClass="text100"  maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
									</td>
									<td width="65" align="right"><strong>站&nbsp;&nbsp;&nbsp;点：</strong></td>
										<td width="100">
										<select id="selectSite" name="selectSite" style="width:110px;" onchange="searchList();">
		                    					<option value="">请选择站点</option>
		                  				</select>
									</td>
									<td width="65" align="right"><strong>时&nbsp;&nbsp;&nbsp;段：</strong>
									</td>
									<td align="right" width="100">
										<input readonly="readonly" id="begTime" name="begTime" value="${queryObj.begTime}" 
										class="Wdate" type="text" style="width:100px;" 
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'endTime\')}',position:{top:'under'},isShowClear:false})"/>
									</td>
									<td width="20" align="center">至</td>
									<td align="left" width="100">
										<input id="endTime" name="endTime"  value="${queryObj.endTime}" 
										class="Wdate" type="text" readonly="readonly" style="width:100px;" 
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begTime\')}',maxDate:'%y-%M-%d',position:{top:'under'},isShowClear:false})"/>
									</td>
									<td width="90" align="left"><a href="javascript:void(0);" class="btn-blue"
										onfocus="this.blur()" onclick="javascript:searchList();">查询</a>
									</td>
									<td>&nbsp;</td>
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
			<s:hidden id="exportObj.VIN" name="exportObj.VIN"/>
			<s:hidden id="exportObj.st_ride_flag" name="exportObj.st_ride_flag"/>
			<s:hidden id="exportObj.stu_name" name="exportObj.stu_name"/>
			<s:hidden id="exportObj.stu_class" name="exportObj.stu_class"/>
			<s:hidden id="exportObj.stu_school" name="exportObj.stu_school"/>
			<s:hidden id="exportObj.route_id" name="exportObj.route_id"/>
			<s:hidden id="exportObj.site_id" name="exportObj.site_id"/>
			<s:hidden id="exportObj.begTime" name="exportObj.begTime"/>
			<s:hidden id="exportObj.endTime" name="exportObj.endTime"/>
		</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="stride_validate_new.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseroute.jsp"%>
</body>
</html>