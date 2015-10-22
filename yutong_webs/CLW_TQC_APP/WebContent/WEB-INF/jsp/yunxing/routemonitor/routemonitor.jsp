<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/gpspostion.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/badDrive.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value='/stylesheets/routemonitor.css' />"/>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/mooko-ui/themes/default/mk.routemonitor.css" />" />
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseroute_css.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body>
<input type="hidden" id="newDayer" value=""/>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content" class="routemonitorDivArea">
		<s:hidden id="user_org_id" name="user_org_id"/>
		<s:hidden id="chooseorgid" name="chooseorgid"/>
		<s:hidden id="routeid1" />
		<s:hidden id="hiddencarcopy" />
	    <table width="100%" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td id='leftdiv'  valign="top" class="leftline">
	        <div id="content_leftside">
	          <div class="treeTab">
	          	<s:if test="#session.perUrlList.contains('111_3_5_3_5')">
					<a href="javascript:void(0);" id="treeupid" onclick="tabSwitch('treeupid')" class="tabfocus">早班线路</a>
					<s:if test="#session.perUrlList.contains('111_3_5_3_6')">
						<a  class="tab" onfocus="this.blur()" id="treedownid" onclick="tabSwitch('treedownid')">晚班线路</a>
					</s:if>
					<s:if test="#session.perUrlList.contains('111_3_5_3_7')">
						<a  class="tab" onfocus="this.blur()" id="treetqcid" onclick="tabSwitch('treetqcid')">厂内通勤</a>
					</s:if>
				</s:if>
				<s:else>
					<s:if test="#session.perUrlList.contains('111_3_5_3_6')">
						<a  href="javascript:void(0);" id="treedownid" onclick="tabSwitch('treedownid')" class="tabfocus">晚班线路</a>
						<s:if test="#session.perUrlList.contains('111_3_5_3_7')">
							<a  class="tab" onfocus="this.blur()" id="treetqcid" onclick="tabSwitch('treetqcid')">厂内通勤</a>
						</s:if>
					</s:if>
					<s:else>
						<s:if test="#session.perUrlList.contains('111_3_5_3_7')">
							<a  href="javascript:void(0);" id="treetqcid" onclick="tabSwitch('treetqcid')" class="tabfocus">厂内通勤</a>
						</s:if>
					</s:else>
				</s:else>
	            <!--  <a href="javascript:void(0);" class="hideLeft" onfocus="this.blur()" onclick="HideandShowControl()"></a> -->
	          </div>
	          <div class="newsearchPlan">
	            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	              <tr>
	                <td width="130" align="right"><input id="search_route_name" type="text" class="input120" maxlength="14" onkeypress="if(event.keyCode==13){searchRoute();}"/></td>
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
	        <td valign="top">
	        <div id="content_rightside">
		      <div class="titleBar" id="titleBarMap">
	            <div class="title">发车安排</div>
	            <div class="workLink">
	            	<s:if test="#session.perUrlList.contains('111_3_5_4_2')">
	              <a href="javascript:void(0);" id="showaddbutton_check" class="btnLink" onclick="showaddbutton()">调&nbsp;整</a>
	              </s:if>
	              <s:if test="#session.perUrlList.contains('111_3_5_4_3')">
	              <a href="javascript:void(0);" id="patch_check_add" class="btnLink" onclick="patch_check_add()">批量添加</a>
	              </s:if>
	              <s:if test="#session.perUrlList.contains('111_3_5_4_4')">
	              <a href="javascript:void(0);" id="patch_check_add" class="btnLink" onclick="patch_check_del()">清空车辆</a>
	              </s:if>
	              <s:if test="#session.perUrlList.contains('111_3_5_4_6')">
	              <a href="javascript:void(0);" onclick="javascript:makeWeekList(1);" class="btnLink">生成发车安排</a>
	              </s:if>
	              <!-- <a href="javascript:void(0);" id="patch_check_add" class="btnLink" onclick="reflashchart()">刷新</a> -->
	            </div>
	          </div>
	          <div class="rmMain" style="height:100%;">
	            <div id="rm_route_big" style="width: 100%;height:100%;padding:0 auto;overflow-y: auto;">
	             <table border="0" cellpadding="0" cellspacing="0" style="height:100%; width:100%;"><tr><td>
	             	<div id="rm_route1" style="margin-top:5px;" class="showedrmroutediv"></div>
	             	<div id="rm_route2" class="showedrmroutediv"></div>
	             	<div id="rm_route3" class="showedrmroutediv"></div>
	             	<div id="rm_route4" class="showedrmroutediv"></div>
	             	<div id="rm_route5" class="showedrmroutediv"></div>
	             	<div id="rm_route6" class="showedrmroutediv"></div>
	             	<div id="rm_route7" class="showedrmroutediv"></div>
	             </td></tr>
	             </table>
	             <table style="width:180px;" border="0" cellpadding="0" cellspacing="0" align="center">
	             <tr>
		             <td>
		             	<a href="javascript:void(0);" class="btn-blue" onclick="settomodule()">设置模板</a>
		             </td>
		             <td>
		             	<a href="javascript:void(0);" class="btn-blue" onclick="searchtomodule()">提取模板</a>
		             </td>
	             </tr>
	             </table>
	            </div>
	          </div>
	          </div>
	        </td>
	      </tr>
	    </table>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<div id="siteStuDetail" class="chartDetail_c" style="width:465px;display:none;z-index: 105;">
 	<div class="chartDetail_title" id="DetialTitle">
     	<%-- <img src="<s:url value='/newimages/chartDetail_t_l.gif'/>" class="lt"/>
        <img src="<s:url value='/newimages/chartDetail_t_r.gif'/>" class="rt"/> --%>
        <span id="detailTile"></span>
        <img src="<s:url value='/newimages/chartDetail_t_close.png'/>" class="close" onclick="closeViloationDetail();"/>
    </div>
    <div class="chartDetail_cent001" style="height:213px;width:465px;">
    <div class="chartDetail_cent" style="height:213px;width:445px;">
    	<s:hidden id="vehicle_vin" />
    	<s:hidden id="route_list_i" />
    	<s:hidden id="route_list_ii" />
    	<s:hidden id="addupdatetype" />
    	<s:hidden id="driver_id" />
	    <table id="detailgrid" align="center" width="445" height="200">
	    	<tr></tr>
	    	<tr><td align="right">选择车辆：</td><td width="150"><input id="vehicle_ln" name="" readonly="readonly"/><font color="red">&nbsp;&nbsp;*</font></td>
	    	<td align="center"><a class="testnohand" href="javascript:void(0);" id="driver_name"></a>
	    	</td></tr>
	    	<tr></tr>
	    	<tr><td align="right">发车条件：</td><td><input type="radio" id="send_condition_1" value="0" name="turnonvehiclechico"/>&nbsp;坐满发车</td>
	    	<td><input type="radio" id="send_condition_2" value="1" name="turnonvehiclechico"/>&nbsp;循环发车</td></tr>
	    	<tr><td></td><td><input type="radio" id="send_condition_3" value="2" name="turnonvehiclechico"/>&nbsp;按时发车</td>
	    	<td><input id="send_time_s" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'HH:mm',autoPickDate:true,isShowClear:false,qsEnabled:true,quickSel:['07:00','18:00','18:30','19:00','19:30','20:00','20:30']})"/></td></tr>
	    	<tr><td align="right">发车顺序：</td><td>
	    	<select id="routecarseqordernum" style="width:50px;"></select></td>
	    	<td></td></tr>
	    	<tr><td colspan="3">
	    	<div style="position:relative;width:100%;text-align:center;">
				<input class="btnLink_sure" style="float: none;" type="button" id="popinfoaddupdatesure"  value="确&nbsp;定" onclick="popinfoaddupdate();"/>
				<input class="btnLink_sure" style="float: none;" type="button" id="popinfodelbut"  value="删&nbsp;除" onclick="popinfodel();"/>
				<input class="btnLink_sure" style="float: none;" type="button"  value="取&nbsp;消" onclick="closeViloationDetail();"/>
			</div>
	    	<!-- <a id="popinfoaddupdatesure" class='btnLink' href='javascript:void(0);' onclick='popinfoaddupdate()' >确定</a>
	    	<a id="popinfodelbut" class='btnLink' href='javascript:void(0);' onclick='popinfodel()' >删除</a>
	    	<a class='btnLink' href='javascript:void(0);' onclick='closeViloationDetail()' >取消</a> -->
	    	</td></tr>
	    </table>
    </div>
    </div>
    <div class="chartDetail_bottom">
	</div>
</div>
<!-- 批量添加 -->
<div id="patch_siteStuDetail" class="chartDetail_c" style="width:465px;display:none;z-index: 105;">
 	<div class="chartDetail_title" id="patch_DetialTitle">
     	<%-- <img src="<s:url value='/newimages/chartDetail_t_l.gif'/>" class="lt"/>
        <img src="<s:url value='/newimages/chartDetail_t_r.gif'/>" class="rt"/> --%>
        <span>批量添加发车</span>
        <img src="<s:url value='/newimages/chartDetail_t_close.png'/>" class="close" onclick="closepatch_Detail();"/>
    </div>
    <div class="chartDetail_cent001" style="height:213px;width:465px;">
    <div class="chartDetail_cent" style="height:213px;width:445px;">
    	<s:hidden id="patch_vehicle_vin" />
    	<s:hidden id="patch_route_list_i" />
    	<s:hidden id="patch_route_list_ii" />
    	<s:hidden id="patch_addupdatetype" />
	    <table id="patch_detailgrid" align="center" width="445" height="200">
	    	<tr></tr>
	    	<tr><td align="right">选择车辆：</td><td width="150"><input id="patch_vehicle_ln" name="" onclick="patchchoiceCarln()"/><font color="red">&nbsp;&nbsp;*</font></td><td><a href="javascript:void(0);" id="patch_driver_name" onclick="searchDriver(2)"></a></td></tr>
	    	<tr></tr>
	    	<tr><td align="right">发车条件：</td><td><input type="radio" checked="checked" id="patch_send_condition_1" value="0" name="patch_turnonvehiclechico"/>坐满发车</td>
	    	<td><input type="radio" id="patch_send_condition_2" value="1" name="patch_turnonvehiclechico"/>循环发车</td></tr>
	    	<tr><td></td><td><input type="radio" id="patch_send_condition_3" value="2" name="patch_turnonvehiclechico"/>按时发车</td>
	    	<td><input id="patch_send_time_s" value="07:00" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'HH:mm',autoPickDate:true,isShowClear:false,qsEnabled:true,quickSel:['07:00','18:00','18:30','19:00','19:30','20:00','20:30']})"/></td></tr>
	    	<tr><td align="right">频次顺序：</td><td colspan="2">
	    	<input type="checkbox" id="patch_1_day" checked="checked"/>&nbsp;周一 &nbsp;<select id="patch_1_sel" style="width:35px;"></select>&nbsp;
	    	<input type="checkbox" id="patch_2_day" checked="checked"/>&nbsp;周二 &nbsp;<select id="patch_2_sel" style="width:35px;"></select>&nbsp;
	    	<input type="checkbox" id="patch_3_day" checked="checked"/>&nbsp;周三 &nbsp;<select id="patch_3_sel" style="width:35px;"></select>&nbsp;
	    	</td></tr>
	    	<tr><td></td><td colspan="2">
	    	<input type="checkbox" id="patch_4_day" checked="checked"/>&nbsp;周四 &nbsp;<select id="patch_4_sel" style="width:35px;"></select>&nbsp;
	    	<input type="checkbox" id="patch_5_day" checked="checked"/>&nbsp;周五 &nbsp;<select id="patch_5_sel" style="width:35px;"></select>&nbsp;
	    	<input type="checkbox" id="patch_6_day" checked="checked"/>&nbsp;周六 &nbsp;<select id="patch_6_sel" style="width:35px;"></select>&nbsp;
	    	<input type="checkbox" id="patch_7_day" checked="checked"/>&nbsp;周日 &nbsp;<select id="patch_7_sel" style="width:35px;"></select></td></tr>
	    	<tr><td colspan="3">
	    	<div style="position:relative;width:100%;text-align:center;">
				<input class="btnLink_sure" style="float: none;" type="button" id="popinfoaddupdatesure"  value="确&nbsp;定" onclick="popinfoadd_patch(0);"/>
				<input class="btnLink_sure" style="float: none;" type="button"  value="取&nbsp;消" onclick="closepatch_Detail();"/>
			</div>
			
	    	<!-- <a id="popinfoaddupdatesure" class='btnLink' href='javascript:void(0);' onclick='popinfoadd_patch(0)' >确定</a>
	    	<a class='btnLink' href='javascript:void(0);' onclick='closepatch_Detail()' >取消</a> -->
	    	</td></tr>
	    </table>
    </div>
    </div>
    <div class="chartDetail_bottom">
	</div>
</div>
<!--站点学生情况详细 结束-->
<!--车辆弹出POP开始-->
<div id ="infobarDivNO" style="position: absolute; left:555px; top: 220px; z-index: 102; display:none"></div>
<!--车辆弹出POP结束-->

<!--JS区域--> 
<script type="text/javascript" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseroute2.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="routemonitor_chart.jsp"%>
<%@include file="routemonitor_validate.jsp"%>
</body>

</html>