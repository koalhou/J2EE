<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/alarm.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/badDrive.css" />" />
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseonly_css.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style type="text/css">
	.car-status3 {
	 height:195px;
	 min-width:1070px;
	 margin:0 auto;
	}
	#leftpie {
	 float: left;
	 height: 195px;
	 width: 535px;
	}
	#rightpie {
	 float: right;
	 height: 195px;
	 width: 535px;
	}
	</style>
</head>
<body>
	<div id="wrapper">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">
			<s:hidden id="chooseorgid" name="chooseorgid"/>
			<s:hidden id="orgname" name="orgname"/>
			<s:hidden id="datestr" name="datestr"/>
			<s:hidden id="choiceVin" name="choiceVin"/>
			<s:hidden id="alarm_start_longitude" name="baddetail.alarm_start_longitude"/>
			<s:hidden id="alarm_start_latitude" name="baddetail.alarm_start_latitude"/>
			<input type="hidden" id="ChooseEnterID_tree" name="ChooseEnterID_tree" value="<%=session.getValue("ChooseEnterID_tree")%>"/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			   <tr>
			     <td id='leftdiv' valign="top" class="leftline">
					<div id="content_leftside">
						<div id="leftInfoDiv" class="treeTab">
							<a href="javascript:void(0);" class="tabfocus">组织</a>
							<a href="javascript:void(0);" onclick="HideandShowControl()" class="hideLeft"></a>
						</div>
						<div id="lefttree" class="treeBox">
					    	<ul id="treeDemo" class="ztree"></ul>
			    		</div>	
					</div>
				</td>
				<td  bgcolor="#E9E9E9" valign="top" class="leftline" id="middeldiv" style="display: none;">
			       <div id="content_middelside">
			       <div>
			  	     <a href="javascript:void(0);" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
				   </div>
			       </div>
			     </td>     
			     <td valign="top">
			     	<div id="content_rightside">
				        <div class="titleBar">
				         <div class="title"><s:text name="menu3.buliang" /></div>
				         <s:if test="#session.perUrlList.contains('111_3_2_4_2')">
				            <div class="workLink">
				              <div class="export">
				              <a href="javascript:void(0);" class="export_icon" onfocus="this.blur()" onclick="javascript:exportData();"><s:text name="button.daochu" /></a>
				              </div>
				            </div>   
				         </s:if>      
				        </div>
				        <div id="statusManger">
					        <div class="car-info">
					        	<div style=" float:left;">
								<h1 id="carln">未选车</h1>
								<span id="messagetime" class="times"><s:property value="oilused.check_time_code"/></span>
								</div>
								<div style=" float:left; margin:4px 0 0 20px;"><a href="javascript:void(0);" class="btn-blue" onclick= "showViloationDetail()">查看详情</a></div>  
							</div>
							
							<div class="car-status3">
								<div id="leftpie">
									<div id="badTimes" style="display: none;text-align:center;"></div>
									<div id="noBadTimesDiv" style="text-align:center;position: relative; top: 20px; ">
										<img src="<s:url value='/newimages/pic_nodata.png'/>"/>
									</div>
									<div id="zoomBadTimesPic" style="float:left; position: relative; top: -180px; left: 50px; z-index: 99;display: none;" class="magnifier_a" onclick="showBadTimesReport();" title="点击查看大图"> 						
									</div>
								</div>
								<div id="rightpie">
									<div id="badTime" style="display: none; text-align:center;"></div>
									<div id="noBadTimeDiv" style="text-align:center;position: relative; top: 20px;">
										<img src="<s:url value='/newimages/pic_nodata.png'/>"/>
									</div>
									<div id="zoomBadTimePic" style="float:left; position: relative; top: -180px; left: 0px; z-index: 99;display: none;" class="magnifier_a" onclick="showBadTimeReport();" title="点击查看大图">						
									</div>
								</div>
							</div>
						    <div class="list-area">
								<div class="list-sech">
									<div class="div_float">
										<table border="0" cellpadding="0" cellspacing="0" style="margin:0px 10px;">
											  <tr>
											     <th align="right"><s:text name="common.vehcileln" />：</th>
								                 <td align="left" style="padding: 0 5px;">
								                         <input type="text" id="vehicle_ln" name="baddrivday.vehicle_ln" maxlength="32" value="" class="input90" onkeypress="if(event.keyCode==13){searchRideAlarm();}"/>
								                 </td>
								                 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								                 <td align="right" align="center"><input id="dataFileter" name="fileterFlag" type="checkbox" value="1" onclick="dataFileterFlagValue();" checked /></td>
								                 <td>&nbsp;&nbsp;&nbsp;</td>
			                    				 <td align="left">全0数据过滤</td>
			                    				 
								                 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								                 <th align="right" style="padding: 0 5px;">时间：</th>
								                 <td align="left">
								                      <select name="time_option" style=" width:118px;" id="time_option" onchange="selectTime(this)">
								                      	<option value="define">自定义</option>
								                      	<option value="week" selected="selected"><s:text name="common.benweek" /></option>
								                        <option value="month"><s:text name="common.benmonth" /></option>
								                        <option value="quarter"><s:text name="common.benquarter" /></option>
								                        <option value="year"><s:text name="common.benyear" /></option>
								                      </select>
								                  </td>
								                  <td>&nbsp;&nbsp;&nbsp;</td>
								                  <td align="right"> 
								                      <input readonly="readonly" id="begTime" name="begTime" value="${start_time}"
																	class="Wdate" type="text" 
																	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																						autoPickDate:true,
																						maxDate:'#F{$dp.$D(\'endTime\')}',
																						isShowClear:false})"/>
												  </td><td style="padding: 5px 5px;"><s:text name="common.zhi" /></td><td align="left">
													  <input readonly="readonly" id="endTime" name="endTime" value="${end_time}"
																	class="Wdate" type="text" 
																	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																						autoPickDate:true,
																						minDate:'#F{$dp.$D(\'begTime\')}',
																						maxDate:'%y-%M-{%d-1}',
																						isShowClear:false})"/>
								                  </td>                 
								                  <td>&nbsp;&nbsp;</td>
								                  <td><a href="javascript:void(0);" onclick="searchRideAlarm()" class="btn-blue"><s:text name="common.chaxun" /></a></td>
								                  <td align="center" width="150" style="color:#797979;">( 不能查询当日数据 )</td>
											  </tr>
										</table>
									</div>
								</div>
							    <div id="tableparent" style="padding: 4px;">
									<table id="gala" ></table>		
							    </div>			
							</div>
						</div>
					</div>
			     </td>   
			   </tr>
			 </table>
			 <div id="badTimesReport" class="chartDetail_c" style="display:none; width: 628px;">
			    	<div class="chartDetail_title" id="badTimesTitle">
			        	<img src="<s:url value='/newimages/chartDetail_t_l.gif'/>" class="lt"/>
			            <img src="<s:url value='/newimages/chartDetail_t_r.gif'/>" class="rt"/> 不良驾驶记录-按次
			            <img src="<s:url value='/newimages/chartDetail_t_close.gif'/>" class="close" onclick="closeBadTimesReport();"/>
			        </div>
			        <div class="chartDetail_cent" style="height:385px;">
			        	<div style="height:30px; text-align:center;">
			        		<!-- style="margin:auto;" -->
				        	<table>
						        <tr>
						        	<td>&nbsp;&nbsp;</td>
									<td align="right"><strong>车牌号：</strong>
									</td>
									<td>
										<input id="vehicle_ln_v" type="text" style="background:url(../newimages/input_bg80.png) no-repeat left top; border:none; width:75px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
									</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td align="right"><strong>部门：</strong>
									</td>
									<td>
										<input id="org_name_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
									</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td align="right"><strong>时段：</strong>
									</td>
									<td>
										<input id="time_name_v" type="text" style="background:url(../newimages/input_bg160.png) no-repeat left top; border:none; width:160px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
									</td>
									<td align="right">&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
					        </table>
				        </div>
				        <div id="badtimescontent" class="chartDetail_c_c"></div>
			        </div>
			 </div>
			 <div id="badTimeReport" class="chartDetail_c" style="display:none; width: 628px;">
			    	<div class="chartDetail_title" id="badTimeTitle">
			        	<img src="<s:url value='/newimages/chartDetail_t_l.gif'/>" class="lt"/>
			            <img src="<s:url value='/newimages/chartDetail_t_r.gif'/>" class="rt"/> 不良驾驶记录-按时间
			            <img src="<s:url value='/newimages/chartDetail_t_close.gif'/>" class="close" onclick="closeBadTimeReport();"/>
			        </div>
			        <div class="chartDetail_cent"  style="height:385px;">
			        	<div style="height:30px; text-align:center;">
				        	<table>
						        <tr>
						        	<td>&nbsp;&nbsp;</td>
									<td align="right"><strong>车牌号：</strong>
									</td>
									<td>
										<input id="vehicle_ln_vs" type="text" style="background:url(../newimages/input_bg80.png) no-repeat left top; border:none; width:75px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
									</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td align="right"><strong>部门：</strong>
									</td>
									<td>
										<input id="org_name_vs" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
									</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td align="right"><strong>时段：</strong>
									</td>
									<td>
										<input id="time_name_vs" type="text" style="background:url(../newimages/input_bg160.png) no-repeat left top; border:none; width:160px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
									</td>
									<td align="right">&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
					        </table>
				        </div>
				        <div id="badtimecontent" class="chartDetail_c_c" ></div>
			        </div>
			 </div>
			 <!--图表详细 开始-->
			 <s:hidden id="vehicle_vin" name="baddetail.vehicle_vin"></s:hidden>
				<div id="viloationDetail" class="chartDetail_c" style="display:none;">
				 	<div class="chartDetail_title" id="viloationTitle">
				     	<img src="<s:url value='/newimages/chartDetail_t_l.gif'/>" class="lt"/>
				        <img src="<s:url value='/newimages/chartDetail_t_r.gif'/>" class="rt"/>不良驾驶详情
				        <img src="<s:url value='/newimages/chartDetail_t_close.gif'/>" class="close" onclick="closeViloationDetail();"/>
				    </div>
				    <div class="chartDetail_cent" style="height:450px;">
					    <div class="chartDetail_c_c">
					     	<div id="viloationMap" style="width: 700px; height: 200px;"></div>
				     	</div>
				         
				        <table width="99%" border="0" align="left" cellpadding="0" cellspacing="0">
				           <tr>
				             <td width="10" height="40">&nbsp;</td>
				             <td width="65"><strong>事件名称：</strong></td>
				             <td><input type="checkbox" name="alarm_type_id_name" value="32" checked="checked" />
				             <label for="checkbox2"></label></td>
				             <td>超速告警</td>
				             <td><input type="checkbox" name="alarm_type_id_name" value="46" checked="checked" /></td>
				             <td>空档滑行</td>
				             <td><input type="checkbox" name="alarm_type_id_name" value="49" checked="checked" /></td>
				             <td>超转告警</td>
				             <td><input type="checkbox" name="alarm_type_id_name" value="50" checked="checked" /></td>
				             <td>超长怠速</td>
				             <td><input type="checkbox" name="alarm_type_id_name" value="51" checked="checked" /></td>
				             <td>怠速空调</td>
				             <td><a href="javascript:void(0);"  class="btn-blue" onclick="searchList()">查询</a></td>
				           </tr>
				        </table>
				        <table id="forSumTable">
				        </table>
				        <table id="detailgrid"></table>
				    </div>
				</div>
			  <!--图表详细 结束-->  
			<s:form id="export_form" action="" method="post">
			<s:hidden id="baddrivday.vehicle_ln" name="baddrivday.vehicle_ln"/>
			<s:hidden id="baddrivday.organization_id" name="baddrivday.organization_id"/>
			<s:hidden id="baddrivday.time_list" name="baddrivday.time_list"/>
			<s:hidden id="baddrivday.start_time" name="baddrivday.start_time"/>
			<s:hidden id="baddrivday.end_time" name="baddrivday.end_time"/>
			<s:hidden id="baddrivday.fileterFlag" name="baddrivday.fileterFlag"/>
			</s:form>
		</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseonly_js.jsp"%>
	<script type='text/javascript' src='../scripts/fusioncharts/FusionCharts.js'></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/form.js' />"></script>
	<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
	<%@ include file="showposition_flash.jsp"%>
	<%@ include file="baddriving_validate.jsp"%>
</body>
</html>