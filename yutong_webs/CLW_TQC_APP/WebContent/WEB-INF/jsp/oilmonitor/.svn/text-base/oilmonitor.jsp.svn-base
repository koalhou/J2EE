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
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/alarm.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/ftleNew.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/mooko-ui/themes/default/mk.routemonitor.css" />" />
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseroute_css.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<script type="text/javascript"	src="../scripts/oilmonitor/raphael.2.1.0.min.js"></script>
<script type="text/javascript"	src="../scripts/oilmonitor/justgage.1.0.1.min.js"></script>
<script type="text/javascript"	src="../scripts/lib/js_Map.js"></script>
<!-- <script type="text/javascript" src="../scripts/oilmonitor/oilmonitor.js?sd234f"></script> -->
</head>

<body>
<input type="hidden" id="newDayer" value=""/>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content" class="routemonitorDivArea">

			<!-- 油量记录slider 窗口 -->
			<div class="popBox">
				<div id="popArea" class="mk-sidelayer mk-sidelayer-small" 
					style="overflow: hidden; display: none; width: 500px; height: 540px;">
					<div class="mk-sidelayer-toolbar">
						<span class="mk-sidelayer-bar-btn show" style="cursor: pointer;" onclick="showpop()"></span>
						<h1 class="mk-sidelayer-bar-title" style="display: block;"></h1>
						<div class="mk-sidelayer-bar-close" style="display: none;" onclick="closepop()"></div>
						<div class="mk-sidelayer-tools" style="overflow: hidden;">
							<ul >
								<li id="aa2"  title="油量记录" ><a href="javascript:void(0);"
									onclick="sidelayerButtonControl(2)"><img
										src="../images/xiaocheImage/oil_h.png" /></a></li>
								<li id="aa6"  title="告警记录" ><a href="javascript:void(0);"
									onclick="sidelayerButtonControl(1)"><img
										src="../images/xiaocheImage/ico_warning.png" /></a></li>
							</ul>
						</div>
					</div>
 					<div id="mk-sidelayer-content-flag" class="mk-sidelayer-content"> 
						<iframe id="mk-iframe" style="width: 500px;height: 540px;" frameborder="0" ></iframe>
					</div> 
				 </div> 
			</div>
	    <table width="100%" border="0"  cellpadding="0" cellspacing="0">
	      <tr>
	        <td id='leftdiv'  valign="top" class="leftline">
	         <div id="content_leftside">
				<div id="leftInfoDiv" class="treeTab" style="height: 38px;">
					<a href="#" class="tabfocus">组织</a>
					<a href="javascript:void(0);" onclick="javascript:HideandShowControl()" class="hideLeft"></a>
				</div>
	          	<div class="newsearchPlan" style="height: 60px;margin-top: -5px;">
		            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		                <td width="130" align="right" ><input id="vehicleLn" type="text" class="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchTree('1');}"/></td>
		                <td align="center"><a id="searchTreeBtn" onclick="javascript:searchTree('1')" class="btnbule">查&nbsp;&nbsp;询</a></td>
		              </tr>
		              <tr align="left" >
		              	<td align="left" colspan="2">
			              	<table width="80%" border="0" align="left" cellpadding="0" cellspacing="0">
			              		<tr>
				              		<td width="10" align="right" colspan="2">
				              			<input type="checkbox" name="isTerminal" id="isTerminal" onclick="javascript:searchTree('1','1');"/>
				              		</td>
	 		            			<td align="left" >
	 		            				<span style="height: 25px;">&nbsp;仅显示有设备车辆</span>
	 		            			</td>
			              		</tr>
							</table>
		              	</td>
		              </tr>          
		            </table>
	          	</div>
				<div id="lefttree" class="treeBox" style="float: left;width: 260px;">
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
	            <div class="title">实时油量</div>
	          </div>

<div id="right_main" style="width: 100%;min-height: 459px;">
					<div id="alarm_tab" class="alarm_tab" style="margin-top: 5px;height: 28px;">
                                  <a  class="alarm_tabs"  href="javascript:void(0)" type='all' >全&nbsp;&nbsp;部</a>
                                  <a    href="javascript:void(0)"  type='low'>油量低</a>
                                  <a    href="javascript:void(0)"  type='alarm'>有告警</a>
                      </div>
									<div id="oil_pic_message" class="oil_pic_message" style="min-height: 422px;">
									<div id="dash"  style="overflow: auto;min-height: 382px;">
										<div id="emptyStr" align='center' style='margin-top: 15px;display:none;'></div>
									</div>
										<input id="monitorType" type="hidden" value="all"/>
<!-- 	<div id="pageLine"  style="text-align: center; clear: both;padding-top: 10px;padding-bottom: 10px"> -->
<!--  共<span id="curPage">1</span>页&nbsp;&nbsp;&nbsp;当前第<select id="pageSelect" name="pageSelect" > -->
<!-- <option value='1'>1</option> -->
<!-- </select>页  <input  id="fuck" type="hidden" onclick="javascript:oilMassMonitorObj.init()" value="&nbsp跳  转 &nbsp"/></div> -->
	<div id="pageLine"  style="text-align: center; clear: both;padding-top:5px;padding-bottom: 15px; height: 35px;">
		<img id="pagefirst" style="cursor: pointer;" onclick="pageFirst(this);" alt="" src="../images/pageslipe/first.gif" complete="complete"/>
		<img id="pageprev" style="cursor: pointer;" onclick="pagePrev(this);" alt="" src="../images/pageslipe/prev.gif" complete="complete"/>
		<input id="pageSelect" name="pageSelect" style="width: 20px;"  value="1"/>
		共
		<span id="curPage">
			1
		</span>
		页
		<img id="pagenext" style="cursor: pointer;" onclick="pageNext(this);" alt="" src="../images/pageslipe/next.gif" complete="complete"/>
		<img id="pagelast" style="cursor: pointer;" onclick="pageLast(this);" alt="" src="../images/pageslipe/last.gif" complete="complete"/>
	</div>
									</div>
								</div>
							</div>

		               
	        </td>
	      </tr>
	    </table>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>



<!--JS区域--> 
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="zTree_withoutonline.jsp"%>
<!-- <script type="text/javascript"	src="../scripts/oilmonitor/raphael.2.1.0.min.js"></script> -->
<!-- <script type="text/javascript"	src="../scripts/oilmonitor/justgage.1.0.1.min.js"></script> -->
<script type="text/javascript" src="../scripts/oilmonitor/oilmonitor.js?sd234f"></script>
</body>

</html>