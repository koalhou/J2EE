<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style type="text/css">
		a.searchMap img{ width: 16px; height: 16px; }
	</style>
</head>
<body>
	<div id="wrapper">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">
		
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td id='leftdiv' width="310" valign="top" class="leftline">
						<div id="content_leftside">
							<div id="lefttree">
						    	<table id="gala" ></table>
				    		</div>	
						</div>
					</td>
					
		            <td valign="top" id="rightdiv">
		            	<div class="popBox">
			            	<table id="mapbartab"  border="0" cellspacing="0" cellpadding="0" style="position:absolute; z-index:1; right:0;">
						       <tr id="maptoolbar" >
						         <td height="30" align="center" background="../images/fudong_bg_station.png" style=" border-left:1px solid #4573BE;">
						          <table border="0" cellpadding="0" cellspacing="0">
									<tr>
									<td align="left"><a href="javascript:void(0)" title="隐藏" onClick="maptoolbarIsshow('up');"><img id="goup" src="../images/up_arrow.png"  border="0" /></a></td>
									<td>
									<input type="checkbox" id="showPoint" checked onclick="initMapList();"/>
									显示坐标点
									<input type="checkbox" id="showStation" onclick="initMapList();"/>
									显示站点
									<input type="checkbox" id="pointTogether" onclick="setStatus();"/>
									聚合
									&nbsp;
									时间段:
									<input readonly="readonly" id="start_time" name="start_time" value="${site.start_time}"
									class="Wdate" type="text" 
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,maxDate:'#F{$dp.$D(\'end_time\')}'})"/>&nbsp;<s:text name="common.zhi" />			
									<input readonly="readonly" id="end_time" name="end_time" value="${site.end_time}"
									class="Wdate" type="text" 
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,minDate:'#F{$dp.$D(\'start_time\')}'})"/>&nbsp;
									</td>
									<td>
										<a href="javascript:void(0)" class="searchMap" title="查询"><img src="../images/map/searchmap.gif" onclick="initMapList();"></img></a>
									</td>
									</tr>
								</table>
						         </td>
						       </tr>
						       <tr id="fudong_down" style="display:none" style="position:absolute;z-index:1;">
						         <td align="center" valign="top"><a href="javascript:void(0)" ><img src="../images/fudong_down.png" width="62" height="15" onclick="maptoolbarIsshow('down')" border="0" /></a></td>
						       </tr>
						  </table>
		            	</div>
		            	<div id="content_rightside">
							<div id="iCenter" style="width: 100px; height: 100px;"></div>
						</div>
					</td>
				</tr>
			</table>
		
		<div class="popBox">
			<div id="stationInfoDiv" style="display:none" style="width:449px; height:350px;">
				<table id="lonlatTable"  width="449px" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>坐标调整：</tr>
				<tr valign="middle"><td valign="middle" colspan="3">&nbsp;</td></tr>
				<tr align="center" >
		         	<td valign="middle" colspan="2">
		         		<img id="up_img" src="../images/map/up.png" border="0" title="向上" style="width:32px; height:32px;cursor:pointer;" />
		          	</td>
			    </tr>
				<tr align="center">
					<td width="170px">
						<img id="left_img" src="../images/map/left.png" border="0" title="向左" style="width:32px; height:32px;cursor:pointer;"/>
			        </td>
			        <td width="170px">
			            <img id="right_img" src="../images/map/right.png" border="0" title="向右" style="width:32px; height:32px;cursor:pointer;"/>
			        </td>
			        <td>
			        <s:if test="#session.perUrlList.contains('111_3_3_5_9')">
		                    <a href="javascript:void(0)" id="resetButton" class="buttonMapR" onclick="resetStation()">复位</a>
		            </s:if>
			        </td>
			    </tr>
			    <tr align="center">
			        <td valign="middle" colspan="2">
			        	<img id="down_img" src="../images/map/down.png" border="0" title="向下" style="width:32px; height:32px;cursor:pointer;"/>
			        </td>
			    </tr>
			</table>
			<table id="dataTable" width="449px" border="0" align="center" cellpadding="0" cellspacing="0">
		     	<tr valign="middle"><td valign="middle" colspan="2">&nbsp;</td>
		     	</tr>
		     	<tr>
		               <td align="right">站点名称： </td>
		                 <td align="left">
		                   <input id="site_name" type="text" maxlength="13"/><span class="noticeMsg">*</span>
		                 </td>
		                 <td align="right"> 经度： </td>
		                 <td align="left">
		                   <input id="site_longitude" type="text" onblur="checkLon(this)" style="ime-mode:disabled" maxlength="30"/><span class="noticeMsg">*</span>
		                 </td>
		        </tr>
		        <tr>
		                <td align="right">站点属性：</td>
		                <td align="left">
		                   <select id="control_station">
		                   <option value="0" >上学</option>
		                   <option value="1" >放学</option>
		               		</select><span class="noticeMsg">*</span>
		                </td>
		                 <td align="right">纬度：</td>
		                 <td align="left">
		                    <input id="site_latitude"  type="text" onblur="checkLon(this)" style="ime-mode:disabled" maxlength="30"/><span class="noticeMsg">*</span>
		                 </td>
		       </tr>
		       <tr>
		            <td align="right">所属部门：</td>
		            <td align="left" >
						<input id="organizationName" type="text" readonly="true" onclick="openorganizidtree()"/><span class="noticeMsg">*</span>
						<input id="orgidtag" type="hidden" name="orgidtag"/>
					</td>
		       </tr>
		       <tr>
		                 <td align="right">详细地址：</td>
		                 <td align="left" colspan="3">
		                   <input id="sichen_addr" type="text" size="50"/><span class="noticeMsg">*</span>
		                 </td>
		        </tr>
				<tr valign="middle"  height="35px">
					<td  colspan="4">
						<a href="javascript:void(0)" class="buttonMap" onclick="cancelStation()">取消</a>
						<s:if test="#session.perUrlList.contains('111_3_3_5_8')">
		                     <a href="javascript:void(0)" id="deleteButton" class="buttonMap" onclick="deleteStation()">删除</a>
		                </s:if>
						<s:if test="#session.perUrlList.contains('111_3_3_5_7')">
		                     <a href="javascript:void(0)" class="buttonMap" onclick="addStation()">确定</a>
		                </s:if>
					</td>
				</tr>
			</table>
			</div>
		</div>
		
		</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/BlockUI/jquery.blockUI.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>
	<%@include file="stationAllInOne_js.jsp"%>
	<%@include file="mapPointFlash.jsp"%>
	<%@include file="stationList.jsp"%>
</body>
</html>
