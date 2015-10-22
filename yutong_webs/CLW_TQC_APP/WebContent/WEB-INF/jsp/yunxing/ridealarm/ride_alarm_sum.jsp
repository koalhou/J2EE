<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
<link rel="stylesheet" href="<s:url value='/styles/nyroModal.css' />" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/badDrive.css" />" />
<%@include file="/WEB-INF/jsp/treemanage/css/zTree_carrun_css.jsp"%>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body>
<div id="wrapper">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">
			<s:hidden id="chooseorgid" name="chooseorgid"/>
			<%-- add by jinp begin --%>
			<s:hidden id="choosevin" name="choosevin"/>
			<%-- add by jinp end --%>
			
			<input type="hidden" id="chart_desc" name="chart_desc" value=""/> 
			<input type="hidden" id="time_select" name="time_select" value="week"/>
			<input type="hidden" id="plan_time" name="plan_time" value="week"/> 
			<input type="hidden" id="user_beg_time" name="user_beg_time" value=""/> 
			<input type="hidden" id="user_end_time" name="user_end_time" value=""/>
			<input type="hidden" id="fileterFlag" name="fileterFlag" value="1"/>
			<input type="hidden" id="ChooseEnterID_tree" name="ChooseEnterID_tree" value="<%=session.getValue("ChooseEnterID_tree")%>"/> 
			
			<div class="popBox">
				 <div id="popArea" class="mk-sidelayer mk-sidelayer-small" style="z-index:901;">
					  <div class="mk-sidelayer-toolbar">        
					        <span class="mk-sidelayer-bar-btn show"></span>
					        <h1 class="mk-sidelayer-bar-title"></h1>
					        <div class="mk-sidelayer-tools">
					        </div>
					  </div>
					  <div class="mk-sidelayer-content">
					  </div>
				  </div>
			  </div>
			 <table width="100%" border="0" cellpadding="0" cellspacing="0">
			   <tr>
			     <td id='leftdiv' valign="top" class="leftline">
					<div id="content_leftside">
						<div id="leftInfoDiv" class="treeTab">
							<a href="javascript:void(0);" class="tabfocus">组织</a>
							<a href="javascript:void(0);" onclick="HideandShowControl()" class="hideLeft"></a>
						</div>
			            <div class="newsearchPlan" style="height: 60px;">
				            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				              <tr>
				              	<td width="10px"></td>
				                <td width="130px" align="left"><input id="vehicleLn" type="text" class="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchTree();}"/></td>
				                <td align="center"><a href="javascript:searchTree()" class="btnbule">查询</a></td>
				              </tr> 
				              <tr>
				              	<td width="10px"></td>
				              	<td width="130px" align="left">
				              		<table border="0" align="left" cellpadding="0" cellspacing="0">
				              			<tr>
				              			  <td>
				              			    <span>
				              			    	<s:checkbox id="filterFlag" name="filterFlag" onclick="fliterCars();" value="true"/>
				              			    </span>
				              			  </td>
				              			  <td valign="top">
				              			    <span>&nbsp;未配置线路车辆过滤</span>
				              			  </td>
				              			</tr>
				              		</table>
				              	</td>
				              </tr>           
				            </table>
				        </div>
						<div id="lefttree" class="treeBox">
					    	<ul id="treeDemo" class="ztree"></ul>
			    		</div>	
					</div>
				</td>
				<td bgcolor="#E9E9E9" valign="top" class="leftline" id="middeldiv" style="display: none;">
			       <div id="content_middelside">
			       <div>
			  	     <a href="javascript:void(0);" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
				   </div>
			       </div>
			     </td>     
			     <td valign="top">
			     <div id="content_rightside">
			       <div class="titleBar">
			         <div class="title"><s:text name="menu2.yccctj" /></div>
			         <s:if test="#session.perUrlList.contains('111_3_2_3_2')">
			            <div class="workLink">
			              <div class="export">
			              <a href="javascript:void(0);" class="export_icon" onfocus="this.blur()" onclick="javascript:exportData();"><s:text name="button.daochu" /></a>
			              </div>
			            </div>   
			         </s:if>      
			       </div>
			       <div id="statusManger">
						<div class="car-info">
						<table border="0" cellpadding="0" cellspacing="0" style="margin:0px 10px;">
						  <tr>
						  		<%-- 
						     <th align="right"><s:text name="common.vehcileln" />：</th>
						     --%>
			                 <td align="left" style="padding: 0 5px;">
			                 	<div style="display:none;">
			                         <input type="text" id="vehicle_ln" name="baddrivday.vehicle_ln" maxlength="32" value="" class="input90" onkeypress="if(event.keyCode==13){searchRideAlarm();}"/>
			                    </div>
			                 </td>
			                 <%-- 
			                 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			                 --%>
			                 <td align="right" align="center"><input id="dataFileter" name="fileterFlag" type="checkbox" onclick="searchRideAlarm()" value="1" checked /></td>
			                 <td>&nbsp;&nbsp;&nbsp;</td>
			               	 <td align="left"><s:text name="data.filter" /></td>  
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
			                      <input readonly="readonly" id="begTime" name="begTime" value="${queryObj.begTime}"
												class="Wdate" type="text" 
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																	autoPickDate:true,
																	maxDate:'#F{$dp.$D(\'endTime\')}',
																	isShowClear:false})"/>
							  </td><td style="padding: 0px 5px;"><s:text name="common.zhi" /></td><td align="left">
								  <input readonly="readonly" id="endTime" name="endTime" value="${queryObj.endTime}"
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
					   <div id="tableparent" style="padding: 4px;">
						<table id="gala" ></table>		
					   </div>			
					</div>
				</div>
			     </td>   
			   </tr>
			 </table>
			 
			<s:form id="export_form" action="" method="post">
			<s:hidden id="exportObj.vehicle_ln" name="exportObj.vehicle_ln"/>
			<s:hidden id="exportObj.user_organization_id" name="exportObj.user_organization_id"/>
			<s:hidden id="exportObj.time_list" name="exportObj.time_list"/>
			<s:hidden id="exportObj.begTime" name="exportObj.begTime"/>
			<s:hidden id="exportObj.endTime" name="exportObj.endTime"/>
			<s:hidden id="exportObj.fileterFlag" name="exportObj.fileterFlag"/>
			<%-- add by jinp begin --%>
			<s:hidden id="exportObj.car_state" name="exportObj.car_state"/>
			<s:hidden id="exportObj.re_flag" name="exportObj.re_flag"/>
			<s:hidden id="exportObj.VIN" name="exportObj.VIN"/>
			<%-- add by jinp end --%>
			</s:form>
			
			<div id="rideAlarmDetial" class="chartDetail_c" style="display:none; position: absolute; left:248px; top:128px;z-index: 903; width: 683px;">
			    	<div class="chartDetail_title" id="DetialTitle">
			        	<img src="<s:url value='/newimages/chartDetail_t_l.gif'/>" class="lt"/>
			            <img src="<s:url value='/newimages/chartDetail_t_r.gif'/>" class="rt"/> 异常乘车统计
			            <img src="<s:url value='/newimages/chartDetail_t_close.gif'/>" class="close" onclick="closeRideAlarm();"/>
			        </div>
			        <div class="chartDetail_cent" style="height:385px; background: #fff;">
			        	<div id="noRideAlarmDataDiv" style="text-align:center;position: relative; top: 0px; padding:50px 0;  background-color: #fff">
							<img src="<s:url value='/newimages/pic_nodata.png'/>"/>
						</div>
						<div style="height:30px; text-align:center; background: #EEEEEE;">
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
			        	<div id="rideAlarmcontent" class="chartDetail_c_c"></div>
			        </div>
			 </div>
			 <s:hidden id="vehicle_vin" ></s:hidden>
			<s:hidden id="time_list" ></s:hidden>
			<s:hidden id="begTime" ></s:hidden>
			<s:hidden id="endTime" ></s:hidden>
			<s:hidden id="orgname" name="orgname"/>
			<s:hidden id="car_ln" name="car_ln"/>
			<s:hidden id="no_up_num" ></s:hidden>
			<s:hidden id="no_down_num" ></s:hidden>
			<s:hidden id="nofix_up_num"></s:hidden>
			<s:hidden id="nofix_down_num"></s:hidden>
		</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<%@include file="/WEB-INF/jsp/treemanage/zTree_carrun_js.jsp"%>
	<script type='text/javascript' src='../scripts/fusioncharts/FusionCharts.js'></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/form.js' />"></script>
	<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
	<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
	<%@ include file="ride_alarm_validate.jsp"%>

	<script type="text/javascript">
		jQuery(function() {
			 jQuery('#popArea').mk_sidelayer({
			       'top': '36px',
			      'height': '540px',
			      'width': '500px',
			      //'url': '<s:url value="/carrun/ready.shtml" />',
			      'is_show': false,
			      'can_close': true,
			      close_callback: function() {
			          var iframeobj = document.getElementById("iframeshowArea");
			          if(iframeobj != null){
			           iframeobj.src ="";
			          }
			      }
			    		      
			    });
		});
	</script>
</body>
</html>
