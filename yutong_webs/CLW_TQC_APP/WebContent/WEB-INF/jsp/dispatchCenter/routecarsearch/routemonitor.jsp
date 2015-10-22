<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page language="java" contentType="text/html;charset=utf-8"  deferredSyntaxAllowedAsLiteral="true"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/gpspostion.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/badDrive.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value='/stylesheets/routemonitor.css' />"/>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/mooko-ui/themes/default/mk.routemonitor.css" />" />
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterprise_css.jsp"%>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
</head>

<body onunload="reflashpage()">
<input type="hidden" id="newDayer" value=""/>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content" class="routemonitorDivArea">
		<s:hidden id="user_org_id" name="user_org_id"/>
		<s:hidden id="chooseorgid" name="chooseorgid"/>
		<s:hidden id="routeid1" />
		<s:hidden id="routeid2" />
		<s:hidden id="routeid3" />
		<s:hidden id="choicerouteid" />
		<s:hidden id="choicerouteclass" />
		<div class="popBox">
		 <div id="popArea" class="mk-sidelayer mk-sidelayer-small" style="z-index: 103;overflow:hidden;">
			  <div class="mk-sidelayer-toolbar">
					<span class="mk-sidelayer-bar-btn show"></span>
					<h1 class="mk-sidelayer-bar-title"></h1>
					<div class="mk-sidelayer-tools" style="overflow:hidden;">
					</div>
			  </div>
			  <div class="mk-sidelayer-content">
		<!--	  <iframe id="iframeshowArea" src="" height="484px" width="100%"></iframe>-->
			  </div>
		  </div>
		</div>
	    <table width="100%" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td id='leftdiv'  valign="top" class="leftline">
	        <div id="content_leftside">
	          <div class="treeTab">
	            <a href="javascript:void(0);" id="treeupid" class="tabfocus">车辆</a>
	            <!-- <a href="javascript:void(0);" class="hideLeft" onfocus="this.blur()" onclick="HideandShowControl()"></a> -->
	          </div>
	          <div class="newsearchPlan" style="height: 60px;">
	            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	              <tr>
	              	<td width="10px"></td>
	                <td width="130" align="right"><input id="search_route_name" type="text" class="input120" maxlength="14" onkeypress="if(event.keyCode==13){searchRoute();}"/></td>
	                <td align="center"><a onclick="javascript:searchRoute()" class="btnbule">查询</a></td>
	              </tr>
	              <tr>
	              	<td width="10px"></td>
	              	<td width="130px" colspan="2" align="left">
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
	              	<td align="center">
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
	        	 <div class="titleBar" id="titleBarMap">
		            <div class="title">车辆调度</div>
		          
		          <div class="workLink">
					<a href="javascript:void(0);" class="btnLink_sendcarset" onclick="turntosetvehicleorder()"></a>
					</div>
				</div>
	          <div class="rmMain">
					<div class="week-list">
		           		<div style=" float:left; margin-right:5px;"><img src="../newimages/week_arrow_left.png" width="16" height="50" onclick="previousWeek()"/></div>
		           		<ul>
		                	<li id="li0" class="today" onclick="changeChoose('0')" style="cursor:hand;"><span id="week0" class="week_bold"></span><span id="day0"></span></li>
		                    <li id="li1" onclick="changeChoose('1')" style="cursor:hand;"><span id="week1" class="week_bold"></span><span id="day1"></span></li>
		                    <li id="li2" onclick="changeChoose('2')" style="cursor:hand;"><span id="week2" class="week_bold"></span><span id="day2"></span></li>
		                    <li id="li3" onclick="changeChoose('3')" style="cursor:hand;"><span id="week3" class="week_bold"></span><span id="day3"></span></li>
		                    <li id="li4" onclick="changeChoose('4')" style="cursor:hand;"><span id="week4" class="week_bold"></span><span id="day4"></span></li>
		                    <li id="li5" onclick="changeChoose('5')" style="cursor:hand;"><span id="week5" class="week_bold"></span><span id="day5"></span></li>
		                    <li id="li6" onclick="changeChoose('6')"><span id="week6" class="week_bold"></span><span id="day6"></span></li>
		                </ul>
		                <div style=" float:left;"><img src="../newimages/week_arrow_right.png" width="16" height="50" onclick="nextWeek()"/></div>
		                <div style=" float:left;width:150px;height: 30px; margin-top: 10px;margin-left: 10px;">
		                	<table><tr>
		                	<td align="left">
								<input readonly="readonly" maxlength="15" id="queryTime" name="queryTime" class="Wdate" type="text" onfocus="if(closebackdatainfo()){WdatePicker({dateFmt:'yyyy-MM-dd',isShowToday:false,autoPickDate:true,isShowClear:false,onpicked:function(){queryWeekList();}})}"/>                          
	                    	</td>
	                    	<!--maxDate:'%y-%M-#{%d+30}', <td><a href="javascript:void(0);" onclick="javascript:queryWeekList();" class="btn-blue">查询</a></td> -->
	                    	</tr></table>
		                </div>
		            </div>
		            <div class="titleBar1" id="titleBarMap">
		            	<div id="checkedvehiclename" class="title_routecar"></div>
			            <div class="workLinkleft2">
			            <!-- <a href="javascript:void(0);" id="vehicle_code_car_r"></a> -->
			            <div id="vehicle_code_car_num" style="color:black;margin-top:2px;"></div>
			            </div>
			            <div class="workLink_tonew">
			            <s:if test="#session.perUrlList.contains('111_3_3_2_1')">
			              <a href="javascript:void(0);" id="showaddbutton_check" class="add_newline_Link" onclick="addnewroute()">增加新线路</a>
			              </s:if>
			            </div>
		                
			        </div>
	            <div id="rm_route_big" style="width: 100%;padding:0 auto;overflow-y: auto;">
	             <table border="0" cellpadding="0" cellspacing="0" style="width:100%;"><tr><td>
	             	<div id="rm_route" style="margin-top:5px;"></div>
	             	<div><div id="rm_route2"></div></div>
	             	<div><div id="rm_route3"></div></div>
	             </td></tr></table>
	            <div id="routempageslipe" style="text-align:center;display:none;">
	            	<img id="routempagefirst" style="cursor:hand;" src="../images/pageslipe/first.gif" alt="" onclick="routempagec(this);"/>
	            	<img id="routempageprev" style="cursor:hand;" src="../images/pageslipe/prev.gif" alt="" onclick="routempagec(this);"/>
	            	<input id="routempageno" value="1" style="width: 20px;" onchange="routempagechange(this);"/>
	            	<span id="routempageall"></span>
	            	<img id="routempagenext" style="cursor:hand;" src="../images/pageslipe/next.gif" alt="" onclick="routempagec(this);"/>
	            	<img id="routempagelast" style="cursor:hand;" src="../images/pageslipe/last.gif" alt="" onclick="routempagec(this);"/>
	            </div>
	            </div>
	          </div>
	          </div>
	        </td>
	      </tr>
	    </table>
	</div>
	<%@include file="../../../jsp/common/footer.jsp"%>
</div>
<!--站点学生情况详细 开始-->
<div id="siteStuDetail" class="chartDetail_c" style="width:465px;display:none;z-index: 105;">
 	<div class="chartDetail_title" id="DetialTitle">
     	<%-- <img src="<s:url value='/newimages/chartDetail_t_l.gif'/>" class="lt"/>
        <img src="<s:url value='/newimages/chartDetail_t_r.gif'/>" class="rt"/> --%>
        <span id="detailTile">发车安排</span>
        <img src="<s:url value='/newimages/chartDetail_t_close.png'/>" class="close" onclick="closeViloationDetail();"/>
    </div>
    <div class="chartDetail_cent001" style="height:213px;width:465px;">
    <div class="chartDetail_cent" style="height:213px;width:445px;">
    	<s:hidden id="vehicle_vin" />
    	<s:hidden id="route_list_i" />
    	<s:hidden id="route_list_ii" />
    	<s:hidden id="addupdatetype" />
    	<s:hidden id="driver_id" />
    	<s:hidden id="addupdatetimetype" /><!-- 0长期还是1临时 -->
	    <table id="detailgrid" align="center" width="445" height="200">
	    	<tr></tr>
	    	<tr><td align="right" width="60">选择车辆：</td><td width="100"><input id="vehicle_ln" name=""/><font color="red">&nbsp;&nbsp;*</font></td><td width="120"><a class="testnohand" href="javascript:void(0);" id="driver_name"></a></td></tr>
	    	<tr><td align="right">发车条件：</td><td><input type="radio" id="send_condition_1" value="0" name="turnonvehiclechico"/>&nbsp;<span id="send_condition_span">坐满发车</span></td>
	    	<td><input type="radio" id="send_condition_2" value="1" name="turnonvehiclechico"/>&nbsp;<span id="send_condition_span2">循环发车</span></td></tr>
	    	<tr><td></td><td><input type="radio" id="send_condition_3" value="2" name="turnonvehiclechico"/>&nbsp;按时发车</td>
	    	<td><input id="send_time_s" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'HH:mm',autoPickDate:true,isShowClear:false,qsEnabled:true,quickSel:['07:00','18:00','18:30','19:00','19:30','20:00','20:30']})"/></td></tr>
	    	<tr><td align="right">发车顺序：</td><td>
	    	<select id="routecarseqordernum" style="width:50px;"></select></td><td></td></tr>
	    	<tr><td align="right">有效期限：</td><td><input id="send_s_time_s" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'%y-%M-%d',isShowClear:false})"/>
	    	</td><td><input id="send_e_time_s" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'send_s_time_s\')}',maxDate:'#F{$dp.$D(\'send_s_time_s\',{d:+6});}',isShowClear:false})"/><font color="red">&nbsp;&nbsp;*</font></td></tr>
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
<!--站点学生情况详细 结束-->
<!--车辆弹出POP开始-->
<div id ="infobarDivNO" style="position: absolute; left:555px; top: 220px; z-index: 102; display:none;"></div>
<!--车辆弹出POP结束-->

<!--JS区域--> 
<script type="text/javascript" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="routemonitor_chart.jsp"%>
<%@include file="routemonitor_validate.jsp"%>
<%@include file="routemonitor_car.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_carrun_js2.jsp"%>
</body>

</html>