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
	
a.buleLink {
	background-color: #5CB0EE;
	color: #FFF;
	display: block;
	height: 25px;
	line-height: 25px;
	text-align: center;
	width: 40px;
}

a.blackLink {
	color: black;
	display: block;
	height: 25px;
	line-height: 25px;
	text-align: center;
	width: 40px;
}
.th1{
	border:1px;
	background-image:url("../scripts/flexigrid/images/fhbg1.gif");
	border-left-color: #fff;
	border-left-style:solid;
	border-right-color: #ddd;
	border-right-style: solid;
}
.th2{
	border:1px;
	background-image:url("../scripts/flexigrid/images/fhbg.gif");
	border-left-color: #fff;
	border-left-style:solid;
	border-right-color: #ddd;
	border-right-style: solid;
	border-top-color: #fff;
	border-top-style: solid;
	border-bottom-color: #ddd;
	border-bottom-style: solid;
}
</style>
	
	
	
	
	
	
</head>

<body>
<input type="hidden" id="newDayer" value=""/>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<s:hidden id="vehicle_vins" name="vehicle_vins" />
	
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
	                <td width="130" align="right"><input id="vehicleLn" type="text" class="input120" maxlength="14" onkeypress="if(event.keyCode==13){searchTreeClick();}"/></td>
	                <td align="center"><a onclick="javascript:searchTreeClick()" class="btnbule">查询</a></td>
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
					<div class="title"><s:text name="维保管理" /></div>
		         	<s:if test="#session.perUrlList.contains('111_3_4_2_2')">
		         	<div class="workLink">
									<a href="javascript:void(0)" onclick='exportData()'
										class="squarLink">
										<img src="<s:url value='../images/btn_tool/btn_export.png'/>" align="bottom" />
										导出</a>
								</div>
		            	
					</s:if>
	            </div>
	            <div id="statusManger">
				<div class="list-area">
				<div class="list-sech3">
					<div>
						<table  border="0" cellpadding="0" cellspacing="0">
						<tr>
						<!-- 
						<td width="70" align="right"><strong>线路类型：</strong></td>
									<td align="left">
										<select id="route_class" name="route_class" onchange="searchList();">
											<option value="-1" selected="selected">全部</option>
											<option value="0">早班</option>
											<option value="1">晚班</option>
										</select>
									</td>
						<td width="70" align="right"><strong>行驶线路：</strong></td>
									<td align="left"><s:textfield id="choiceroutename" name="choiceroutename" onclick="choiceRoute();" readonly="true" />
									<s:hidden id="choicerouteid" name="choicerouteid"></s:hidden>
									</td>
									 -->
						<td width="70" align="right"><strong>维保日期：</strong></td>
							<td width="122">
							<input type="hidden" id="serverTime" value="${currentDay}"/>
							<input readonly="readonly" id="begintime" name="begintime" value="" class="Wdate" type="text"
	                     	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'endtime\')}',maxDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endtime\')}',isShowClear:false})" />
							</td>
							<td width="40" align="center"><strong>至&nbsp;</strong></td>
							<td width="125">
               					<input readonly="readonly" id="endtime" name="endtime" value="${currentDay}" class="Wdate"
	                    				type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begintime\')}',maxDate:'%y-%M-%d',isShowClear:false})" />
							</td>
						<td width="50" align="right"><strong>类别：</strong></td>
									<td align="left">
										<select id="fix_type" name="fix_type" onpropertychange=searchRepareCarList()>
											<option value="" selected="selected">全部</option>
											<option value="0">维修</option>
											<option value="1">保养</option>
										</select>
									</td>
               				<td align="left"><a href="javascript:void(0);" class="btn-blue"  onclick="searchRepareCarList()">查询</a></td>
						</tr>
						</table>
					</div>
				</div>
				
				<div id="tableparent" style="padding: 4px;">
				
				<div id="myHDivBox" style="border: 1px;border-color: #ccc;border-style: solid;">
				<div style="border-bottom:2px solid #0361A4;border-left:1px solid #ccc;border-top:1px solid #ccc;border-right:1px solid #ccc;">
						<div style="height:37px;line-height:37px;margin-left:10px;display:inline-block;font-size:15px;color:#0363A8;font-weight:bold;">维保管理信息</div>
						<div class="workLink">
									<a href="javascript:void(0)" onclick='addRepare()'
										class="squarLink">
										<img src="<s:url value='../images/btn_tool/btn_add.png'/>" align="bottom" />
										新增</a>
								</div>
					    <div align="center" style="width: 100%" id="tipsInfo">
				    <font color="green"><s:property value="message"/></font>
				    </div>
					</div> 
					
																<TABLE cellSpacing=0 cellPadding=0 class="myTitle">
																<THEAD>
																<TR>
																<TH align="center" rowspan="2" class="th1"><DIV style="TEXT-ALIGN: center; WIDTH: 80px">维保日期</DIV></TH>
																<TH  align=center rowspan="2" class="th1"><DIV style="TEXT-ALIGN: center; WIDTH: 80px">班车号</DIV></TH>
																<TH align=center rowspan="2" class="th1"><DIV style="TEXT-ALIGN: center; WIDTH: 80px">车牌号</DIV></TH>
																<TH align="center" rowspan="2" class="th1"><DIV style="TEXT-ALIGN: center; WIDTH: 150px">故障描述</DIV></TH>
																<TH align="center" rowspan="3" class="th1"><DIV style="TEXT-ALIGN: center; WIDTH: 150px">维保项目</DIV></TH>
																<TH align="center" rowspan="2" class="th1"><DIV style="TEXT-ALIGN: center; WIDTH: 80px">类别</DIV></TH>
																<TH align="center" rowspan="2" class="th1"><DIV style="TEXT-ALIGN: center; WIDTH: 90px">是否正常维修</DIV></TH>
																<TH align="center" rowspan="2" class="th1"><DIV style="TEXT-ALIGN: center; WIDTH: 80px">责任人</DIV></TH>
																<TH align="center" colspan="2" class="th2"><DIV style="TEXT-ALIGN: center; WIDTH: 120px">维保费用</DIV></TH>
																<TH align="center" rowspan="2" class="th1"><DIV style="TEXT-ALIGN: center; WIDTH: 60px">合计</DIV></TH>
																<TH align="center" rowspan="2" class="th1"><DIV style="TEXT-ALIGN: center; WIDTH: 60px">操作</DIV></TH>
																</TR>
																
																<TR>
																<TH align="center" class="th2"><DIV style="TEXT-ALIGN: center; WIDTH: 60px">工时</DIV></TH>
																<TH align="center" class="th2"><DIV style="TEXT-ALIGN: center; WIDTH: 60px">配件</DIV></TH>
																</TR>
																</THEAD>
																</TABLE>
															</div>				
				<table id="galaList" width="100%" cellspacing="0">
				</table>
				</div>
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
		</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>

<form style="display: none;" id="gotoAddRepareForm" action="gotoAddRepare.shtml" method="post">
<input type="hidden" id="orgIDgoto" name="orgID" value=""/>
<input type="hidden" id="yeargoto" name="year" value=""/>
<input type="hidden" id="monthgoto" name="month" value=""/>
<input type="hidden" id="collectiongoto" name="collection" value=""/>
<input type="hidden" id="action" name="action" value=""/>
</form>
<!--JS区域--> 

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="zTree_withoutonline.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
<%@include file="repare_validate.jsp"%>
</body>

</html>