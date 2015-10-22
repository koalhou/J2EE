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
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseroute_css.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<s:hidden id="user_org_id" name="user_org_id"/>
		<s:hidden id="chooseorgid" name="chooseorgid"/>
		<div class="popBox">
		 <div id="popArea" class="mk-sidelayer mk-sidelayer-small" style="z-index: 103;overflow:hidden;">
			  <div class="mk-sidelayer-toolbar">        
			        <span class="mk-sidelayer-bar-btn show"></span>
			        <h1 class="mk-sidelayer-bar-title"></h1>
			        <div class="mk-sidelayer-tools" style="overflow:hidden;">
			         <!--  <ul>
			              <li id="aa5"><a href="#" onclick="sidelayerButtonControl('aa5')"><img src="../newimages/qipaoimages/btn_tool1.png"/></a></li>
			              <li id="aa4"><a href="#" onclick="sidelayerButtonControl('aa4')"><img src="../newimages/qipaoimages/btn_tool2.png"/></a></li>
			              <li id="aa3"><a href="#" onclick="sidelayerButtonControl('aa3')"><img src="../newimages/qipaoimages/btn_tool3.png"/></a></li>
			              <li id="aa7"><a href="#" onclick="sidelayerButtonControl('aa7')"><img src="../newimages/qipaoimages/btn_tool7.png"/></a></li>
			              <li id="aa1"><a href="#" onclick="sidelayerButtonControl('aa1')"><img src="../newimages/qipaoimages/btn_tool5.png"/></a></li>
			              <li id="aa2"><a href="#" onclick="sidelayerButtonControl('aa25')"><img src="../newimages/qipaoimages/btn_tool6.png"/></a></li>
			              <li id="aa6"><a href="#" onclick="sidelayerButtonControl('aa6')"><img src="../newimages/qipaoimages/ico_warning.png"/></a></li>
			          </ul>-->
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
	            <a href="#" class="tabfocus">线路</a>
	             <a href="#" class="hideLeft" onfocus="this.blur()" onclick="HideandShowControl()"></a>
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
		  	     <a href="#" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
			   </div>
		       </div>
		     </td>         
	        <td valign="top" border="0">
	        <div id="content_rightside">
	          <div class="titleBar">
	            <div class="title">线路监控</div>
	            <div class="workLink">
	            </div>
	          </div>
	          <div class="rmMain">
	            <div class="rmTitle"><span id="route_name" class="bTitle">--------</span><br/><div id="route_person">负责人：--</div></div>
	            <div class="rmRoutePlan">
	              <div class="rmSelectBus">
	                <div class="rmSelLeft">
	                  车牌号：
	                  <select id="selectBus" name="selectBus" onchange="queryVeh(this.value);">
	                    <option value="">全部车辆</option>
	                  </select>
	                </div>
	                <div class="rmSelRight">
	                  <span class="rm_icoCBlue">未运行</span>
	                  <span class="rm_icoCGreen">运行正常</span>
	                  <span class="rm_icoCYellow">停靠时间异常</span>
	                  <span class="rm_icoCRed">乘车情况异常</span>
	                  <span class="rm_icoCPur">全部异常</span>
	                </div>
	
	              </div>
	            </div>
	            <div id="rm_route_big" style="width: 100%;padding:0 auto;">
	             <table border="0" cellpadding="0" cellspacing="0" style="height:100%; width:100%;"><tr><td>
	             	<div id="rm_route"></div>
	             </td></tr></table>            
	            </div>
	            <div class="rmForm" style="padding:4px;">
	            <div class="rmInfoPlan">
	              <div class="rmInfoTab">
	                <strong id="tab_site_name"></strong>
	                <ul id="siteTab" style="display:none">
	                  <li><a class="rmArrowLeft" href="#"></a></li>
	                  <li><a class="rmArrowRight" href="#"></a></li>                
	                </ul>
	              </div>
	              <div class="rmInfoContent">
	                <table border="0" cellspacing="4">
	                  <tr>
	                    <td width="80" height="22" align="right">到站时间：</td>
	                    <td width="120" class="rminfoBg112" id="site_in_time"></td>
	                    <td width="72" align="right">停留时间：</td>
	                    <td width="120" class="rminfoBg112" id="site_stop_time"></td>
	                    <td width="80" align="right" >应乘车：</td>
	                    <td width="80" class="rminfoBg72" id="site_plan_up_num"></td>
	                    <td width="80" align="right" >应下车：</td>
	                    <td width="80" class="rminfoBg72" id="site_plan_down_num"></td>
	                    <td width="80" align="right" >请&nbsp;&nbsp;假：</td>
	                    <td width="80" class="rminfoBg72" id="site_qj_num"></td>
	                  </tr>
	                  <tr>
	                    <td height="22" align="right">停靠情况：</td>
	                    <td height="52" colspan="3" rowspan="2" valign="top" class="rminfotextArea" id="site_stop_desc"></td>
	                    <td align="right">实乘车：</td>
	                    <td class="rminfoBg72" id="site_reality_up_num"></td>
	                    <td align="right">实下车：</td>
	                    <td class="rminfoBg72" id="site_reality_down_num"></td>
	                    <td>&nbsp;</td>
	                    <td>&nbsp;</td>
	                  </tr>
	                  <tr>
	                    <td height="22">&nbsp;</td>
	                    <td align="right">未乘车：</td>
	                    <td class="rminfoBg72" id="site_no_up_num"></td>
	                    <td align="right">未下车：</td>
	                    <td class="rminfoBg72" id="site_no_down_num"></td>
	                    <td>&nbsp;</td>
	                    <td>&nbsp;</td>
	                  </tr>
	                </table>
	              </div>
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
<!--站点学生情况详细 开始-->
<div id="siteStuDetail" class="chartDetail_c" style="width:725px;display:none;z-index: 105;">
 	<div class="chartDetail_title" id="DetialTitle">
     	<img src="<s:url value='/newimages/chartDetail_t_l.gif'/>" class="lt"/>
        <img src="<s:url value='/newimages/chartDetail_t_r.gif'/>" class="rt"/><span id="detailTile"></span>
        <img src="<s:url value='/newimages/chartDetail_t_close.gif'/>" class="close" onclick="closeViloationDetail();"/>
    </div>
    <div class="chartDetail_cent" style="height:353px;">
	    <table id="detailgrid"></table>
    </div>
</div>
<!--站点学生情况详细 结束-->
<!--车辆弹出POP开始-->
<div id ="infobarDivNO" style="position: absolute; left:555px; top: 220px; z-index: 102; display:none"></div>
<!--车辆弹出POP结束-->

<!--JS区域--> 
<script type="text/javascript" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseroute.jsp"%>
<%@include file="routemonitor_validate.jsp"%>
<%@include file="routemonitor_chart.jsp"%>
<%@include file="routemonitor_car.jsp"%>
</body>

</html>