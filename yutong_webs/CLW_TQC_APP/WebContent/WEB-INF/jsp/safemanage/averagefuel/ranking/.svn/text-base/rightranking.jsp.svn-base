<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
    <%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />	
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_carrun_css.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style type="text/css">
	.car-status3{
		height:195px;
		min-width:1070px;
		margin:0 auto;
	}
	</style>
</head>

<body>
<input type="hidden" id="newDayer" value=""/>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content" class="routemonitorDivArea">
	  <s:form id="export_form" action="exportRanking.shtml" method="post">
		<s:hidden id="user_org_id" name="user_org_id"/>
		<s:hidden id="re_flag" name="re_flag"/>
		<s:hidden id="chooseorgid" name="chooseorgid"/>
		<s:hidden id="vehicle_vin" name="vehicle_vin"/>
		
	    <table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
			<td id='leftdiv'  valign="top" class="leftline">
	        <div id="content_leftside">
			<div class="treeTab">
	            <a href="javascript:void(0);" id="treeupid" class="tabfocus">组织</a>
	            
	            <!--<a  class="tabfocus" onfocus="this.blur()" id="enttabid" onclick="tabSwitch('enttabid')">组织</a>
	            <a  class="tab" onfocus="this.blur()"  id="treetabid" onclick="tabSwitch('treetabid')">线路</a>
	            -->
	            
	            <a href="javascript:void(0);" class="hideLeft" onfocus="this.blur()" onclick="HideandShowControl()"></a>
			</div>
			<div class="newsearchPlan">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
	              	<td width="10px"></td>
	                <td width="130" align="right"><input id="vehicleLn" type="text" class="input120" maxlength="14" onkeypress="if(event.keyCode==13){searchRoute();}"/></td>
	                <td align="center"><a onclick="javascript:searchRoute()" class="btnbule">查询</a></td>
					</tr>
	            </table>
	          </div>
			<div id="treeDemoDiv" class="treeBox">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			</div>
	        </td>
			<td class="sleftline" valign="top" id="middeldiv" style="display: none;">
				<div id="content_middelside">
				<div>
					<a href="javascript:void(0);" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
				</div>
				</div>
		    </td>
	        <td valign="top" border="0">
				<div id="content_rightside">
				<div class="titleBar">
					<div class="title"><s:text name="发车统计" /></div>
		         	<s:if test="#session.perUrlList.contains('111_3_4_2_2')">
		            	<div class="workLink">
						<div>
						<a href="javascript:void(0);" class="squarLink" onfocus="this.blur()" onclick="javascript:exportData();">
						<img src="<s:url value='../images/btn_tool/btn_export.png'/>" align="bottom" />
						<s:text name="button.daochu" /></a>
						</div>
						</div>
					</s:if>
	            </div>
	            <div id="statusManger">
				<div class="list-area">
				<div class="list-sech3">
					<div>
						<table  border="0" cellpadding="0" cellspacing="0">
						<tr>
						<td width="70" align="right"><strong>线路类型：</strong></td>
									<td align="left">
										<select id="route_class" name="route_class" onchange="searchList()">
											<option value="-1" selected="selected">全部</option>
											<option value="0">早班</option>
											<option value="1">晚班</option>
										</select>
									</td>
						<td width="70" align="right"><strong>行驶线路：</strong></td>
									<td align="left"><s:textfield id="choiceroutename" name="choiceroutename" onclick="choiceRoute();" readonly="true" />
									<s:hidden id="choicerouteid" name="choicerouteid"></s:hidden>
									</td>
						<td width="70" align="right"><strong>时&nbsp;&nbsp;段：&nbsp;</strong></td>
							<td width="122">
							<input readonly="readonly" id="begintime" name="begintime" value="${begintime}" class="Wdate" type="text"
	                     	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'endtime\')}',maxDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endtime\')}',isShowClear:false})" />
							</td>
							<td width="40" align="center"><strong>至&nbsp;</strong></td>
							<td width="125">
               					<input readonly="readonly" id="endtime" name="endtime" value="${endtime}" class="Wdate"
	                    				type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begintime\')}',maxDate:'%y-%M-%d',isShowClear:false})" />
							</td>
               				<td align="left"><a href="javascript:void(0);" class="btn-blue"  onclick="searchList()">查询</a></td>
						</tr>
						</table>
					</div>
				</div>
				<div id="tableparent" style="padding: 4px;">
				<div id="Below_new" align="center" >
					         	<s:actionmessage id="messagesuc" cssStyle="color:green"/><s:actionerror id="messageerror" cssStyle="color:red"/>
				</div>
				<table id="galaList" ></table>
				</div>
				<table border="0" cellpadding="0" cellspacing="0">
				<tr>
				<td width="90" align="right"><b>合计里程：</b></td>
				<td align="left" style="padding: 0 5px; width: 110px;">
					<span id="totalmileage"/>
				</td>
				<td width="120" align="right"><b>合计空驶里程：</b></td>
				<td align="left" style="padding: 0 5px; width: 110px;">
					<span id="totalemptymileage"/>
				</td>	
							
				<td width="120" align="right"><b>合计载重里程：</b></td>
				<td align="left" style="padding: 0 5px; width: 110px;">
					<span id="totalloadmileage"/>
				</td>
				
				<td width="100" align="right"><b>空驶率：</b></td>
				<td align="left" style="padding: 0 5px; width: 110px;">
					<span id="emptyload"/>
				</td>
<!-- 				<td width="100" align="right"><b>合计油耗：</b></td> -->
<!-- 				<td align="left" style="padding: 0 5px; width: 110px;"> -->
<!-- 					<span id="totaloil"/> -->
<!-- 				</td> -->
<!-- 				<td width="110" align="right"><b>怠速总油耗：</b></td> -->
<!-- 				<td align="left" style="padding: 0 5px; width: 110px;"> -->
<!-- 					<span id="totalspnoil"/> -->
<!-- 				</td> -->
				
				</tr>
				</table>
			</div>
			</div>
			</div>
	        </td>
	      </tr>
	    </table>
		<s:hidden id="rank.vehicle_ln" name="rank.vehicle_ln"/>·
		<s:hidden id="rank.time_list" name="rank.time_list"/>
		<s:hidden id="rank.start_time" name="rank.start_time"/>
		<s:hidden id="rank.end_time" name="rank.end_time"/>
		<s:hidden id="rank.fileterFlag" name="rank.fileterFlag"/>
		
		<s:hidden id="queryObj.sortname" name="queryObj.sortname"/>
		<s:hidden id="queryObj.sortorder" name="queryObj.sortorder"/>
		<s:hidden id="queryObj.route_class" name="queryObj.route_class"/>
		</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<!--JS区域--> 

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_carrun_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
<%@include file="routemonitor_validate.jsp"%>
</body>

</html>