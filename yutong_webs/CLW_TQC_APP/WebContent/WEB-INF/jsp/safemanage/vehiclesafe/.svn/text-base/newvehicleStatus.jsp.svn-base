<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseonly_css.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/stylesheets/status.css" />" />
</head>
<body>
<div id="wrapper">
	    <%@include file="/WEB-INF/jsp/common/header.jsp"%>
	    <div id="content">
<s:form id="vehiclestatus_form" action="vehiclestatus" method="post">
	<s:hidden id="chooseorgid" name="chooseorgid" />
	<s:hidden id="year" name="year"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td id='leftdiv' valign="top" class="leftline">
			<div id="content_leftside">
			<div id="leftInfoDiv" class="treeTab">
							<a href="#" class="tabfocus">组织</a>
							<a href="#" onclick="HideandShowControl()" class="hideLeft"></a>
			</div>
			<div id="lefttree" class="treeBox">
						     <ul id="treeDemo" class="ztree"></ul>
		    </div>	
			</div>	
			</td>
			<td valign="top" class="sleftline" id="middeldiv" style="display: none;">
	                   <div id="content_middelside">
		                <div>
	            	     <a href="#" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
	          	       </div>
	                  </div>
            </td>
			<td valign="top" id="rightdiv">
			<div id="content_rightside">
			<div class="titleBar">
			<div class="title"><s:text name="menu2.ckjk" /></div>
			</div>

			<div id="statusManger">
			<div class="car-info">
			<h1 id="carln">未选车</h1>
			<span id="messagetime" class="times"><s:property value="year"/>-00-00 00:00:00</span>
			  <div class="status_bar">
                	<table width="180" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="20" align="left" valign="middle"></td>
                        <td align="left" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td width="20" align="left" valign="middle"><img src="../newimages/line_status_g.png" width="16" height="15" /></td>
                        <td align="left" valign="middle">正常</td>
                        <td width="20" align="left" valign="middle"><img src="../newimages/line_status_r.png" width="16" height="15" /></td>
                        <td align="left" valign="middle">异常</td>
                      </tr>
                    </table>
                </div>
			</div>
			
			<div class="car-status">
			  <ul>
                <li class="ico1">
                    <strong>严重故障</strong>
                    <span id="yy">油压</span>
                    <span id="zdqqy">制动器气压</span>
                    <span id="zdtpms">制动蹄片磨损</span>
                    
                </li>
                <li class="ico2">
                    <strong>堵塞</strong>
                    <span id="klzs">空滤堵塞</span>
                    <span id="jlzs">机滤堵塞</span>
                    <span id="ryzs">燃油堵塞</span>
                </li>
                <li class="ico3">
                    <strong>高温</strong>
                    <span id="jywd">机油温度</span>
                    <span id="hsqgw">缓速器高温</span>
                    <span id="cwg">仓温高</span>
                   
                    
                </li>
                <li class="ico4">
                   <strong>常规故障</strong>
                   <span id="fdjwd">发动机温度</span>
                   <span id="wbdy">蓄电池电压</span>
                   <span id="shuiwei">水位</span>
                </li>
                <li class="ico5">
                   <strong>其他故障</strong>
                   <span id="abs">ABS故障</span>
                   <span id="rygj">燃油告警</span>
                </li>
              </ul>
			</div>
			<div class="list-area">
			<div class="list-sech">
			<div class="div_float">
			<table border="0" cellpadding="0" cellspacing="0">
			  <tr>
			     <th align="right">车牌号：</th>
                 <td align="left" style="padding: 0 5px;">
                         <s:textfield id="vehicle_ln" name="vehicle_ln"  maxlength="30" cssClass="input100" onkeypress="chaxun()"/>
                 </td>
                 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                 <th align="right">车况：</th>
                   <td align="left" style="padding: 0 5px;">
                     <select id="searchState" name="searchState" style="width:98px" onchange="mychange()">
				      <option value=""><s:text name="all.info" /></option>
				      <option value="0">正常</option>
				      <option value="1"><s:text name="vehstatus.yichang" /></option>
			         </select>
                   </td>
                  <td>&nbsp;&nbsp;</td>
                  <td><a href="#" class="btn-blue" onclick="searchList()">查询</a></td>
			  </tr>
			</table>
			</div>
			</div>
			
			<table cellspacing="4" width="100%">
			  <tr>
			    <td>
			<table id="vehicletbl" width="100%" cellspacing="0">
			</table>
			 </td>
			 </tr>
		   </table>
			
           </div>
			</div>
			</div>
			</td>
		</tr>
	  </table>
    </s:form>
   </div>
     <%@include file="/WEB-INF/jsp/common/footer.jsp"%>
  </div>
   <%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
   <%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseonly_js.jsp"%>
   <script type="text/javascript" language="JavaScript1.2"
			src="<s:url value='/scripts/flexigrid/flexigrid2.0.js' />"></script>
   <%@include file="/WEB-INF/jsp/safemanage/vehiclesafe/newvehicleStatus_js.jsp"%>
</body>
</html>