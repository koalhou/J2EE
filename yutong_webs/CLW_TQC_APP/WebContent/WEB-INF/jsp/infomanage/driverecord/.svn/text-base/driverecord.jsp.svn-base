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
	            <a href="javascript:void(0);" class="hideLeft" onfocus="this.blur()" onclick="HideandShowControl()"></a>
			</div>
			<div class="newsearchPlan">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
	              	<td width="10px"></td>
	                <td width="130" align="right"><input id="vehicleLn" type="text" class="input120" maxlength="14" onkeypress="if(event.keyCode==13){searchRoute();}"/></td>
	                <td align="center">
	                <s:if test="#session.perUrlList.contains('111_3_4_8_1')">
	                <a onclick="javascript:searchRoute()" class="btnbule">查询</a>
	                </s:if>
	                </td>
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
					<div class="title"><s:text name="驾驶员刷卡记录" /></div>
		         	<s:if test="#session.perUrlList.contains('111_3_4_2_2')">
		            	<div class="workLink">
						<div >
						<s:if test="#session.perUrlList.contains('111_3_4_8_2')">
						
						<a href="javascript:void(0);" class="squarLink" onfocus="this.blur()" onclick="javascript:exportData();">
						<img src="<s:url value='../images/btn_tool/btn_export.png'/>" align="bottom" />
						<s:text name="button.daochu" /></a>
						
						
						</s:if>
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
						<td width="70" align="right"><strong>姓名：</strong></td>
						<td align="left"><s:textfield id="drivername" name="drivername" onkeypress="if(event.keyCode==13){searchList();}" />
						</td>
						<td width="70" align="right"><strong>班车号：</strong></td>
						<td align="left"><s:textfield id="choiceroutename" name="choiceroutename" onkeypress="if(event.keyCode==13){searchList();}" />
						</td>
						<td width="70" align="right"><strong>刷卡日期：</strong></td>
							<td width="122">
							<input readonly="readonly" id="begintime" name="begintime" value="${begintime}" class="Wdate" type="text"
	                     	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'endtime\')}',maxDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endtime\')}',isShowClear:false})" />
							</td>
							<td width="40" align="center"><strong>至</strong></td>
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
				<table id="galaList" ></table>
				</div>
			</div>
			</div>
			</div>
	        </td>
	      </tr>
	    </table>
	    <s:hidden id="sortname" name="sortname"/>
		<s:hidden id="sortorder" name="sortorder"/>
		</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<!--JS区域-->

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_carrun_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
<%@include file="driverecord_validate.jsp"%>
</body>

</html>