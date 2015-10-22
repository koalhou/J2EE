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
a.searchMap img {
	width: 16px;
	height: 16px;
}

#set_item2,#set_item1 {
	border: 0 solid #EEEEEE;
	width: 350px;
	height: 35px;
	font-weight: bold;
	font-size: 12px;
	color:black;
	line-height:23px;
	cursor:pointer;
	background: url('../scripts/flexigrid/images/wbg.gif') repeat-x center top transparent
}
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
							<div id="leftInfoDiv" class="treeTab" style="float: none;"><a href="#" class="tabfocus">加油站信息</a>
							<!-- 
							<a href="javascript:void(0);" onclick="javascript:HideandShowControl()" class="hideLeft"></a>
							 -->
							
							</div>
	
							<div id="lefttree">
							    	<table id="gala" ></table>
					    		</div>
						        <div id="set_item1"  onclick="sitManagerObj.openAddOilTimer();" >
						        <div style="padding: 6px">
						        <span >设置加油时段</span>	        		
						        		<span style="padding-right: 15px;float: right;" id="amWeeks"></span>
						        </div></div>
						        <div id="set_item2"  onclick="sitManagerObj.openAddLowerVal();" >
						        <div style="padding: 6px">
						        <span >设置低油量阀值</span>	        		
						        		<span style="padding-right: 15px;float: right;" id="amLower"></span>
						        </div></div>
						</div>
					</td>
								<td class="sleftline" valign="top" id="middeldiv" style="display: none;">
		       <div id="content_middelside">
		       <div>
		  	     <a href="javascript:void(0);" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
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
										<td align="left"><a href="javascript:void(0)" title="隐藏" onclick="sitManagerObj.maptoolbarIsshow('up');"><img id="goup" src="../images/xiaocheImage/up_arrow.png"  border="0" /></a></td>
												<td>
												<input type="checkbox" id="showPoint" checked onclick="mapPointFlashObj.initMapList();"/>
												显示坐标点
												<input type="checkbox" id="showStation" onclick="mapPointFlashObj.initMapList();"/>
												显示站点
												<input type="checkbox" id="pointTogether" onclick="mapPointFlashObj.setStatus();"/>
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
													<a href="javascript:void(0)" class="searchMap" title="查询"><img src="../images/xiaocheImage/searchmap.gif" onclick="mapPointFlashObj.initMapList();"></img></a>
												</td>
									</tr>
								</table>
						         </td>
						       </tr>
						       <tr id="fudong_down" style="display:none" style="position:absolute;z-index:1;">
						         <td align="center" valign="top"><a href="javascript:void(0)" ><img src="../images/fudong_down.png" width="62" height="15" onclick="sitManagerObj.maptoolbarIsshow('down')" border="0" /></a></td>
						       </tr>
						  </table>
		            	</div>
		            	<div id="content_rightside">
							<div id="iCenter" style="width: 100px; height: 100px;"></div>
						</div>
					</td>
				</tr>
			</table>
		
	
		</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	
	<!-- 弹出框 -->
<div class="popBox">
			<div id="stationInfoDiv" style="display:none" style="width:449px; height:350px;">
					<table id="dataTable" width="449px" border="0" align="center" cellpadding="0" cellspacing="0">
				     	<tr valign="middle"><td valign="middle" colspan="2">&nbsp;</td>
				     	</tr>
				     	<tr>
				               <td align="right" style="width: 80px;">加油站名称： </td>
				                 <td align="left">
				                   <input id="site_name" type="text" maxlength="20" /><span class="noticeMsg">*</span>
				                 </td>
								<td colspan="2" rowspan="3">
									<table id="lonlatTable"  border="0" align="center" cellpadding="0" cellspacing="0" style="width: 100%;">
										<tr>坐标调整：</tr>
										<tr valign="middle"><td valign="middle" colspan="3">&nbsp;</td></tr>
										<tr align="center" >
								         	<td valign="middle" colspan="2">
								         		<img id="up_img"  onclick="mapPointFlashObj.changePoint('vertical',0.000001)" src="../images/xiaocheImage/up.png" border="0" title="向上" style="width:32px; height:32px;cursor:pointer;" />
								          	</td>
									    </tr>
										<tr align="center">
											<td width="170px">
												<img id="left_img" onclick="mapPointFlashObj.changePoint('level',-0.000001)" src="../images/xiaocheImage/left.png" border="0" title="向左" style="width:32px; height:32px;cursor:pointer;"/>
									        </td>
									        <td width="170px">
									            <img id="right_img" onclick="mapPointFlashObj.changePoint('level',0.000001)" src="../images/xiaocheImage/right.png" border="0" title="向右" style="width:32px; height:32px;cursor:pointer;"/>
									        </td>
									        <td>
									        <s:if test="#session.perUrlList.contains('111_3_3_5_9')">
								                    <a href="javascript:void(0)" id="resetButton" class="buttonMapR" onclick="mapPointFlashObj.resetStation()">复位</a>
								            </s:if>
									        </td>
									    </tr>
									    <tr align="center">
									        <td valign="middle" colspan="2">
									        	<img id="down_img" onclick="mapPointFlashObj.changePoint('vertical',-0.000001)" src="../images/xiaocheImage/down.png" border="0" title="向下" style="width:32px; height:32px;cursor:pointer;"/>
									        </td>
									    </tr>
									</table>
								</td>
				        </tr>
				        <tr>
			                <td align="right"> 经度： </td>
			                 <td align="left">
			                   <input id="site_longitude" type="text" onblur="javascript: mapPointFlashObj.checkLon(this);" style="ime-mode:disabled" maxlength="10"/><span class="noticeMsg">*</span>
			                 </td>		                 
				       </tr>
				       <tr>
				       		<td align="right">纬度：</td>
				            <td align="left">
				                    <input id="site_latitude"  type="text" onblur="javascript: mapPointFlashObj.checkLon2(this);" style="ime-mode:disabled" maxlength="10"/><span class="noticeMsg">*</span>
				            </td>
				       </tr>
				       <tr>
				                 <td align="right">服务车辆：</td>
				                 <td align="left" colspan="3">
				                 	<input id="sichen_vin" type="hidden" />
				                 	<textarea id="sichen_vehicle" name="sichen_vehicle"  rows="" cols="" size="100" style="height: 75px;width: 354px" onclick="sitManagerObj.openorganizidtree()"></textarea>
				                 </td>
				        </tr>
						<tr valign="middle"  height="35px">
							<td  colspan="4">
								<a href="javascript:void(0)" class="buttonMap" onclick="mapPointFlashObj.cancelStation()">取消</a>
				                     <a href="javascript:void(0)" id="deleteButton" class="buttonMap" onclick="mapPointFlashObj.deleteStation()">删除</a>
				                     <a href="javascript:void(0)" class="buttonMap" onclick="mapPointFlashObj.addStation()">确定</a>
							</td>
						</tr>
					</table>
			</div>	
	</div>

	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/BlockUI/jquery.blockUI.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>
	<script type="text/javascript" src="http://apis.mapabc.com/webapi/auth.json?t=flashmap&v=2.4.1&key=<s:text name='map.key' />"></script>
	<script type="text/javascript" src="../scripts/sitemanage/mapPointFlash.js?sdf1"></script>
	<script type="text/javascript" src="../scripts/sitemanage/siteManager.js?sd445sd"></script>
</body>
</html>
