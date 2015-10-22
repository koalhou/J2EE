<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="common.title" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
<%@include file="/WEB-INF/jsp/common/key2.jsp"%>
</head>
<body onload="mapInit('bitiCenter')" onunload="setonunload()" onbeforeunload="testspeed()">
<div id="wrapper">
<%@include file="/WEB-INF/jsp/common/header.jsp"%>
<div id="content">
	<s:hidden id="organizationid" name="organization_id"></s:hidden>
	<div class="warn">
	<table id="yonghuguanliId" width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="36" valign="top" background="../images/tree_tabBg.gif">
		<div class="toolbar">
		<div class="contentTil">线路信息采集</div>
		<div class="tool"></div>
		</div>
		</td>
	</tr>
	</table>
	<table width="100%" border="0" cellpadding="2" cellspacing="1">
	<tr>
	<td class="reportOnline2">
	<table border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td><s:label>行驶线路：</s:label></td>
		<td align="left"><s:textfield id="choiceroutename" name="choiceroutename" onclick="choiceRoute();" readonly="true" />
		<s:hidden id="choicerouteid" name="choicerouteid"></s:hidden>
		</td>
		<s:if test="#session.perUrlList.contains('111_3_5_15_2')">
		<td><input type="button" value="查看" class="btn-blue" style="float:left;" onfocus="this.blur()" onclick="route_point_check()" />
		</td>
		</s:if>
	</tr>
	<tr>
	  	<td><s:label>车牌号：</s:label></td>
		<td align="left">
		<s:textfield id="vehicle_ln" name="vehicle_ln" onclick="choiceCarln();" readonly="true" />
		<font color="red">*</font>&nbsp;&nbsp;
		<s:hidden id="vehicle_vin" name="vehicle_vin"></s:hidden>
		</td>
	    <td align="left" ><label for="select"></label>
	      <select name="select" class="select84" id="selectModel" onchange="selectModel(this)">
	        <option value="1">行车记录</option>
	        <option value="2">任意时段</option>
	      </select>
	    </td>
	    <td>
	    	<div id="everyTime" style="display: none;">
	    		<table>
	    			<tr>
	    				<td align="center">
						      <label for="textfield">
						      <input class="Wdate" readonly="readonly" type="text"  size="24" id="line_start_time" name="line_start_time" 
	                         onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,maxDate:'%y-%M-%d %H:%m:%s',
															  isShowClear:false,onpicked:pickedstarttime})"
				  value="<s:property value='line_start_time'/>"/>
						      </label>
					    </td>
					    <td width="16" align="center">至</td>
					    <td align="center">
						    <label for="select2">
						      <input class="Wdate" readonly="readonly" type="text"  size="24" id="line_end_time" name="line_end_time" 
	                         onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,maxDate:'%y-%M-%d %H:%m:%s',
															  isShowClear:false,onpicked:pickedendtime})"
				 value="<s:property value='line_end_time'/>"/>
						    </label>
					    </td>
	    			</tr>
	    		</table>
	    	</div>
	    	<div id="diveringNode" style="display: block">
	    		<table>
	    			<tr>
	    				<td align="center">
						      <input class="Wdate" readonly="readonly" type="text"  size="13" id="timetab_time" name="timetab_time"  style="width: 90px;"
	                         onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
															  autoPickDate:true,
															  maxDate:'%y-%M-%d',
															  isShowClear:false})"  value="<s:property value='timetab_time'/>"/>
					    </td>
					    <td width="2px" align="center"></td>
					    <td align="center">
						    <select name="select" style="width: 200px;" id="selectDriveingNode"  onchange="selectbutton()">
						        <option value="0">请点击查询</option>
						    </select>
					    </td>
	    			</tr>
	    		</table>
	    	</div>
	    </td>
	    <s:if test="#session.perUrlList.contains('111_3_5_15_1')">
	    <td align="left" ><input type="button" value="查询" class="btn-blue" onfocus="this.blur()" onclick="selectControl()" /></td>
	    </s:if>
		<s:if test="#session.perUrlList.contains('111_3_5_15_3')">
		<td align="left" ><input type="button" value="导入" class="btn-blue" onfocus="this.blur()" onclick="route_point_in()" /></td>
		</s:if>
	  </tr>
	  </table>
	  </td>
	  </tr>
	  <tr>
	  <td>
	  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
			<tr>
				<td>
	  	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td class="titleStyle">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td width="30%" class="titleStyleZi">地图信息
				</td>
				<td width="69%" align="left">
					<div id="Layer1" class="Layerbitlook_map" style="display: none"><img align="absmiddle" src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
				    	<span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
				    </div>
				</td>
				<td width="1%">&nbsp;</td>
              </tr>
            </table>
			</td>
		</tr>
		</table>
		</td>
		</tr>
		</table>
	  </td>
	  </tr>
	</table>
	</div>
	<!--以下为预留位置-->  
	  <div id="bitmap" style="height:325px;">
		  <div id ="MapbarDiv" valign="right" style="width:110px; height:41px; position:absolute; z-index:1; right:0; top:50px;display:none">
			  <table  width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr id="maptoolbar" valign="right">
			       <td height="33" valign="right">
			       
			      <table width="110px" border="0" cellpadding="0" cellspacing="0" style="border:1px solid #186883;">
				      <tr id="tr01">
				         <td align="center" width="100px">
				   			<s:label id="errorinfo" name="errorinfo" ></s:label>
				         </td>
				       </tr>
			      
			       </table>
			       </td>
			      </tr>
			  </table>	    	
	   </div>
	   <div id="bitiCenter"></div>
	   </div>
	   <%-- <div>
		<div class="arr_bar">
		   <div class="arr_bar_l"></div>
		    <div class="arr_bar_c">
		     <table width="232px" cellpadding="0" cellspacing="0" >
		         <tr>
		             <td width="38px"><img style="cursor: pointer;" id="tongbu" onclick="tongbuOpt()" title="重新开始" src="../newimages/arr_first.gif" /></td>
		               <td width="38px"><img style="cursor: pointer;" id="kuaitui" onclick="kuaituiOpt()" title="慢速" src="../newimages/arr_pre.gif" /></td>
		               <td width="38px"><img style="cursor: pointer;" id="playimg" onclick="baofangOpt();return false;" title="播放" src="../newimages/arr_play.gif" /></td>
		               <td width="38px"><img style="cursor: pointer;" id="jieshu" onclick="jieshuOpt()" title="结束" src="../newimages/arr_stop2.gif" /></td>
		               <td width="38px"><img style="cursor: pointer;" id="kuaijin" onclick="kuaijinOpt()" title="快速" src="../newimages/arr_next.gif" /></td>
		               <td ><img style="cursor: pointer;" id="weibu" onclick="weibuOpt()" title="完成播放" src="../newimages/arr_last.gif" /></td>
		           </tr>
		       </table>
		   </div>
		   <div class="arr_bar_r"><strong><s:label id="bofangbeishu" name="bofangbeishu" ></s:label></strong></div>
		</div>
	</div> --%>
	<input type='hidden' id='noteId' name='noteId'/>
	<div class="popBox" style="margin-left:20px;">
			<div id="stationInfoDiv" style="width:449px; height:350px;display:none;">
			<table id="dataTable" width="500px" border="0" align="center" cellpadding="0" cellspacing="0">
		     	<tr valign="middle"><td valign="middle" colspan="2">&nbsp;</td>
		     	</tr>
		     	<tr>
		               <td  width="300px">&nbsp;</td>
		               
		                 <td  width="170px">
		                  <div id="msg"></div>
		                 </td>
		                 
		                 <td  width="170px">
		                 &nbsp;
		                 </td>
		                 
		                 <td>
		                 <img id="up_img" src="../images/map/up.png" border="0" title="向上" style="width:32px; height:32px;cursor:pointer;" />
		                 </td>
		                 
		                 <td  width="170px">
		                 </td>
		          </tr>
		          <tr valign="middle"><td valign="middle" colspan="2">&nbsp;</td>
		     	</tr>
		          <tr> 
		                 <td  width="300px">经度：</td>
		                 <td  width="170px">
		                   <input id="site_longitude" type="text" style="ime-mode:disabled" maxlength="30"/><span class="noticeMsg">*</span>
		                 </td>
		                 
		                 <td width="170px">
						<img id="left_img" src="../images/map/left.png" border="0" title="向左" style="width:32px; height:32px;cursor:pointer;"/>
			        </td>
			        
			          <td  width="170px">
		             </td>
			        
			        <td width="170px">
			            <img id="right_img" src="../images/map/right.png" border="0" title="向右" style="width:32px; height:32px;cursor:pointer;"/>
			        </td>
		        </tr>
		        <tr valign="middle"><td valign="middle" colspan="2">&nbsp;</td>
		     	</tr>
		        <tr>
		                 <td  width="300px">纬度：</td>
		                 <td  width="170px">
		                    <input id="site_latitude"  type="text" style="ime-mode:disabled" maxlength="30"/><span class="noticeMsg">*</span>
		                 </td>
		                 
		                 <td  width="170px">
		                 </td>
		                 
		                 <td  width="170px">
		                  <img id="down_img" src="../images/map/down.png" border="0" title="向下" style="width:32px; height:32px;cursor:pointer;"/>
		                 </td>
		                 
		                 <td  width="170px">
		                 </td>
		       </tr>
		       <tr valign="middle"><td valign="middle" colspan="2">&nbsp;</td>
		     	</tr>
		      
		       <tr>
	                 <td  width="300px">&nbsp;</td>
	                 <td width="370px">
	                   &nbsp;
	                  </td>
		               
		        </tr>
				<tr valign="bottom" height="50px">
					<td  colspan="4">
						<a href="#" class="buttonMap" onclick="cancelPoint()">取消</a>
	                    <a href="#" class="buttonMap" onclick="updatePoint()">确定</a>
					</td>
				</tr>
			</table>
			</div>
		</div>	
		
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<%@ include file="routebitlook_js.jsp"%>
</div>
</body>
</html>