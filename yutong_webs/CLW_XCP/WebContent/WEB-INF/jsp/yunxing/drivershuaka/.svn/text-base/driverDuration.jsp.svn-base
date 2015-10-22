<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	    <%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
		<title>
			<s:text name="common.title" />
		</title>
		<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
		<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
		<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseonly_css.jsp"%>
		<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/alarm.css" />" />
    </head>
	<body>
      <div class="titleBar">
	      <div class="title">
	                             驾驶员刷卡统计
	      </div>					     
      </div>
      <div id="statusManger">
      	<br/>
        <div class="alarm_tab">
           <a id="deal0" href="#" onclick="changetab(this,1)" class="alarm_tabs">驾驶时长</a>
           <a id="deal2" href="#" onclick="changetab(this,2)">刷卡记录</a>
         </div>
         <div class="alarm_select">
              <span class="alarm_sel_txt">方式：</span>
              <div class="alarm_sel_c">
					<select id="searchTimeType" name="searchTimeType" style=" width:100px;" id="time_option" onchange="changeTimeType(this)">
	                	<option value="1" selected="selected">按时间段</option>
	                	<option value="2">按月</option>
	               	</select>
		      </div>
			  <!-- 按时段查询 -->
			  <div id="searchType1">
                <span class="alarm_sel_txt">时&nbsp;&nbsp;&nbsp;&nbsp;段：</span>
                <div class="alarm_sel_c">
                 	<input readonly="readonly" id="start_time" name="start_time" value="${queryObj.begTime}"
																	class="Wdate" type="text" 
																	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																						autoPickDate:true,
																						maxDate:'#F{$dp.$D(\'end_time\')}',
																						isShowClear:false})"/>
                </div>
                <span class="alarm_sel_txt_2">至</span>
                <div class="alarm_sel_c">
                 	 <input readonly="readonly" id="end_time" name="end_time" value="${queryObj.endTime}"
																	class="Wdate" type="text" 
																	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
																						autoPickDate:true,
																						minDate:'#F{$dp.$D(\'start_time\')}',
																						maxDate:'%y-%M-{%d-1}',
																						isShowClear:false})"/>
                </div>
              </div>
              <!-- 按月份查询 -->
              <div id="searchType2" style="display:none">
                <span class="alarm_sel_txt">月&nbsp;&nbsp;&nbsp;&nbsp;份：</span>
                <div class="alarm_sel_c">
                 	<input readonly="readonly" id="month" name="month" value="${queryObj.month}" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM',autoPickDate:true,isShowClear:false,isShowClear:false,onpicked:pickedstarttime})" />
                 </div>
	          </div>
	          <div>
	          	<a href="#" onclick="searchDurationList()" class="alarm_btn" >查询</a>
	          </div>
			  <div class="export_alarm1">
                 <a href="#" class="export_alarm1_c2" onclick="exportDuration()">导出</a>
              </div>
              <div class="alarm_t_c">
              ( 不能查询当日数据 )
              </div>
              <div class="alarm_t_c">
                   <table id="gala1">
                   </table>
              </div>
         </div>
      </div>
      <s:form id="export_duration_form" action="" method="post">
			<s:hidden id="exportObj.driverId" name="exportObj.driverId"/>
			<s:hidden id="exportObj.searchTimeType" name="exportObj.searchTimeType"/>
			<s:hidden id="exportObj.month" name="exportObj.month"/>
			<s:hidden id="exportObj.begTime" name="exportObj.begTime"/>
			<s:hidden id="exportObj.endTime" name="exportObj.endTime"/>
		</s:form>
       <%@include file="driverDuration_js.jsp"%>
       </body>
</html>