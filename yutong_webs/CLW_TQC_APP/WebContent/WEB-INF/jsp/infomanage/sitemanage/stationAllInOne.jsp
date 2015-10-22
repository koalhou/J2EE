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
		<input id="areaid" type="hidden" value="${areaid}"/>
		<input id="end_time_hidden" type="hidden" value="${site.end_time}"/>
		<div id="content">
			<div id="main">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td id='leftdiv' width="310" valign="top" class="leftline">
						<div id="content_leftside">
							<s:if test="%{switchsite == 0}">
								<div class="treeTab">
									<a href="javascript:void(0);" id="treesite" onclick="tabSwitch('site')" class="tabfocus">站点信息</a>
									<s:if test="#session.perUrlList.contains('111_3_5_16')">
									<a class="tab" onfocus="this.blur()" id="treearea" onclick="tabSwitch('area')">区域信息</a>
									</s:if>
							  	</div>
							  
								<div id="lefttree">
							    	<table id="gala" ></table>
					    		</div>
								<div id="leftarea" style="display:none;">
							    	<table id="gala2" ></table>
					    		</div>
				    		</s:if><s:else>
				    			<div class="treeTab">
									<a class="tab" href="javascript:void(0);" id="treesite" onclick="tabSwitch('site')">站点信息</a>
									<s:if test="#session.perUrlList.contains('111_3_5_16')">
									<a onfocus="this.blur()" id="treearea" onclick="tabSwitch('area')" class="tabfocus">区域信息</a>
									</s:if>
							  	</div>
							  
								<div id="lefttree" style="display:none;">
							    	<table id="gala" ></table>
					    		</div>
								<div id="leftarea">
							    	<table id="gala2" ></table>
					    		</div>
							</s:else>
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
									<input type="checkbox" id="showPoint" onclick="initMapList();"/>
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
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,maxDate:'#F{$dp.$D(\'end_time_hidden\')}',autoPickDate:true,minDate:'#F{$dp.$D(\'start_time\')}'})"/>&nbsp;
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
			</div>
		<div class="popBox" style="margin-left:20px;">
			<div id="stationInfoDiv" style="display:none" style="width:449px; height:350px;">
			<script type="text/javascript">
			function TextValidate() {
				    var code;
				    var character;
				    if (document.all){ //判断是否是IE浏览器
				        code = window.event.keyCode;
				    }else{
				        code = arguments.callee.caller.arguments[0].which;
				    }
				    var character = String.fromCharCode(code);
				    
				    var txt=new RegExp("[ ,\\`,\\~,\\!,\\@,\#,\\$,\\%,\\^,\\+,\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\.,\\<,\\>,\\{,\\},\\(,\\),\\',\\;,\\=,\"]");
				    //特殊字符正则表达式
				    if (txt.test(character)) {
				        if (document.all){
				            window.event.returnValue = false;
				        }else{
				            arguments.callee.caller.arguments[0].preventDefault();
				        }
				    }
			} 
			</script>
			<table id="dataTable" width="500px" border="0" align="center" cellpadding="0" cellspacing="0">
		     	<tr valign="middle"><td valign="middle" colspan="2">&nbsp;</td>
		     	</tr>
		     	<tr>
		               <td  width="300px">站点名称：</td>
		               
		                 <td  width="170px">
		                   <input id="site_name" type="text" maxlength="13"/><span class="noticeMsg">*</span>
		                 </td>
		                 
		                 <td  width="170px">
		                 <input id="site_id" type="text" maxlength="13" style="display:none"/>
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
		                   <input id="site_longitude" type="text" onblur="checkLon(this)" style="ime-mode:disabled" maxlength="30"/><span class="noticeMsg">*</span>
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
		                    <input id="site_latitude"  type="text" onblur="checkLon(this)" style="ime-mode:disabled" maxlength="30"/><span class="noticeMsg">*</span>
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
	                 <td  width="300px">详细地址：</td>
	                 <td width="370px">
	                   <input id="sichen_addr" maxlength="20" type="text" size="40"  onkeypress="TextValidate()"/>
	                  </td>
		               
		        </tr>
				<tr valign="bottom"  height="50px">
					<td  colspan="4">
						<a href="javascript:void(0)" class="buttonMap" onclick="cancelStation()">取消</a>
	                    <a id="deleteButton" href="javascript:void(0)" style="display:none" class="buttonMap" onclick="deleteStation()" display="none">删除</a>
	                    <a id="updateButton" style="display:none" href="javascript:void(0)" class="buttonMap" onclick="updateStation()">修改</a>
	                    <a id="addButton" style="display:block" href="javascript:void(0)" class="buttonMap" onclick="addStation()">确定</a>
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
	<%@include file="mapPointFlash.jsp"%>
	<%@include file="stationList.jsp"%>
	<%@include file="stationAllInOne_js.jsp"%>
</body>
</html>
