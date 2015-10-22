<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body onload="mapInit();">
	<div id="wrapper">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">
			<div id="main">
			  <s:form id="add_routeform" action="route_add" method="post">
			  <s:hidden id="oldroutecode" name="oldrouteCode"/>
			  <s:hidden id="enid" name="routeInfo.route_enterprise_id"/>
			  <s:hidden id="site_up_string" name="routeInfo.site_up_string"/>
			  <s:hidden id="site_down_string" name="routeInfo.site_down_string"/>
			  <!-- 上行初始的站点数据 -->
			  <s:hidden id="upinitstation" name="upinitstation" value=""/>
			  <!-- 下行初始的站点数据 -->
			  <s:hidden id="downinitstation" name="downinitstation" value=""/>
			  <!-- 上行追加的站点数据 -->
			  <s:hidden id="upappendstation" name="upappendstation" value=""/>
			  <!-- 下行追加的站点数据 -->
			  <s:hidden id="downappendstation" name="downappendstation" value=""/>
			  <!-- 上行删除的站点数据 -->
			  <s:hidden id="updeletestation" name="updeletestation" value=""/>
			  <!-- 下行删除的站点数据 -->
			  <s:hidden id="downdeletestation" name="downdeletestation" value=""/>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  	<tr>
			  		<td width="300px">
			  			<div id="content_leftside">
					      <table border="0" width="99%" align="center" cellpadding="0" cellspacing="0">
				            <tr>
				              <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>">
				                <table width="100%" border="0" cellspacing="0" cellpadding="0">
				                  <tr>
				                    <td width="100%" class="titleStyleZi" align="left">
				                      	<s:text name="menu3.xlgl" />
				                      (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
				                    </td>
				                    <td width="1%">&nbsp;</td>
				                  </tr>
				                </table>
				              </td>
				            </tr>
				            <tr>
				              <td>
				              	<div id="listRoute" >
				                <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1">
									  	<tr>
							              <td align="right"><s:text name="routeinfo.route_organization_id" />：</td>
							              <td align="left">
							              	<s:textfield id="route_organization_name" name="route_organization_name" onclick="openorganizidtree();" readonly="true" /><span class="noticeMsg">*</span>
			                				<s:hidden id="route_organization_id" name="routeInfo.route_organization_id"></s:hidden>
							              </td>
							            </tr>
							            <tr>
							              <td align="right"><s:text name="routeinfo.route_name" />：</td>
							              <td align="left">
							                <s:textfield id="route_name" name="routeInfo.route_name" maxlength="14"/><span class="noticeMsg">*</span>
							              </td>
							            </tr>
							            <tr>
							              <td align="right"><s:text name="routeinfo.route_incharge_person" />：</td>
							              <td align="left">
							                  <s:textfield id="route_incharge_person" name="routeInfo.route_incharge_person" maxlength="12" /><span class="noticeMsg">*</span>
							              </td>
							            </tr>
							            <tr>
							              <td align="right"><s:text name="routeinfo.fuzherentel" />：</td>
							              <td align="left">
							                <s:textfield id="route_phone" name="routeInfo.route_phone" maxlength="11"/><span class="noticeMsg">*</span>
							              </td>
							            </tr>
							            <tr>
							              <td align="right"><s:text name="routeinfo.routeremark" />：</td>
							              <td align="left">
							              	<s:textarea id="route_remark" name="routeInfo.route_remark" cols="25" rows="3"/>
							              </td>
							            </tr>
									</table>
									<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1">
										<tr>
											<td align="left" width="30%"><input type="radio" name="routeCheck" checked value="0" onclick="initMapList();"/>上行线路：</td>
											<td width="15%">&nbsp;</td>
											<td align="left" width="30%"><input type="radio" name="routeCheck" value="1" onclick="initMapList();"/>下行线路：</td>
											<td width="15%">&nbsp;</td>
										</tr>
										<tr>
											<td>
											<select id="selectLeftos" name="selectLeftos" multiple="multiple" style="width: 100px;" ondblclick="javascript:moveUpSelect(this);"></select>
											</td>
											<td align="left" >
											<a href="javascript:void(0)" class="sbuttonMove" onclick="javascript:doUp();">↑</a>
											<a href="javascript:void(0)" class="sbuttonMove" onclick="javascript:doDown();">↓</a>								
											</td>
											<td><select id="selectRightos" name="selectRightos" multiple="multiple" style="width: 100px;" ondblclick="javascript:moveDownSelect(this);"></select>
											</td>
											<td align="left" >
											<a href="javascript:void(0)" class="sbuttonMove" onclick="javascript:doUpDs()">↑</a>
											<a href="javascript:void(0)" class="sbuttonMove" onclick="javascript:doDownDs() ">↓</a>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="left">
												<table border="0" cellspacing="0" cellpadding="0">
													<tr>
													  <td><span class="noticeMsg">* </span><s:hidden id="Lefttag" name="Lefttag" /></td>
													  <td><div style="font-size: 12px;color: #CC0000;">注：双击删除站点</div></td>
													</tr>
								                </table>
							                </td>
							                <td colspan="2" align="left">
												<table border="0" cellspacing="0" cellpadding="0">
													<tr>
													  <td><span class="noticeMsg">* </span><s:hidden id="Righttag" name="Righttag" /></td>
													  <td><div style="font-size: 12px;color: #CC0000;">注：双击删除站点</div></td>
													</tr>
								                </table>
							                </td>
									   </tr>
								   	   <tr>
									      <td colspan="3" align="right">
								             <a href="javascript:void(0)" class="buttontwo" onClick="submitForm()">保存</a>
								          </td>
							              <td align="left"> 
								            <a href="javascript:void(0)" onclick="goBack('routemanage.shtml')" class="buttontwo"><s:text name="button.cancle" /></a>
								          </td>
							           </tr>
				          		</table>
				          		</div>
				              </td>
				            </tr>
				          </table>
						</div>
			  		</td>
			  		<td>
			  			<div class="popBox">
						    	<table id="mapbartab"  border="0" cellspacing="0" cellpadding="0" style="position:absolute; z-index:1; right:0;">
							       <tr id="maptoolbar" >
							         <td height="30" align="center" background="../images/fudong_bg_station.png" style=" border-left:1px solid #4573BE;">
							          <table border="0" cellpadding="0" cellspacing="0">
										<tr>
										<td align="left"><a href="javascript:void(0)" title="隐藏" onClick="maptoolbarIsshow('up');"><img id="goup" src="../images/up_arrow.png"  border="0" /></a></td>
										<td>
										<input type="checkbox" id="showRouteStation"/>显示线路站点&nbsp;
										站点:
										<input type="text" id="showStation" size="10" onkeypress="if(event.keyCode==13){initMapList();}"/>
										&nbsp;时间段:
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
							         <td align="center" valign="top"><a href="javascript:void(0)" ><img src="../images/fudong_down.png" width="60" height="8" onclick="maptoolbarIsshow('down')" border="0" /></a></td>
							       </tr>
							  </table>
					    	</div>
					    	<div id="content_rightside">
							<div id="iCenter" style="width: 100px; height: 100px;"></div>
							</div>
			  			</td>
					</tr>
				</table>
			 </s:form>
			</div>
			</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<%@include file="route_info_flash.jsp"%>
	<script type="text/javascript">
		(function (jQuery) {
			if (jQuery.browser.msie && jQuery.browser.version < 7.0) {
				document.getElementById("mapbartab").style.width='610px';
			}else{
				document.getElementById("mapbartab").style.width='600px';
				jQuery("#start_time").attr("size",'24');
				jQuery("#end_time").attr("size",'24');
			}
		})(jQuery);
		
		function openorganizidtree(){
			art.dialog.open("<s:url value='/route/routemanagetree.shtml' />",{
				title:"选择部门",
				lock:true,
				width:250,
				height:300,
				yesFn: function(iframeWin){
			        	//对话框iframeWin中对象
			        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
			        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
			        	//当前页面中对象
			            var orgName =  window.document.getElementById('route_organization_name');
			            var orgID = window.document.getElementById('route_organization_id');
			            //赋值
				        if (orgName) orgName.value = orgNameValue;
				        if (orgID) orgID.value = orgIDValue;
			    }
			});
		}
		
		//地图上方工具条缩放
		function maptoolbarIsshow(option){
			var map = document.getElementById("maptoolbar");
			var down = document.getElementById("fudong_down");
			if(option=="up"){
				map.style.display = "none";
				down.style.display = "";
			}
			else if(option=="down"){
				map.style.display = "";
				down.style.display = "none";
			}
		}
	</script>
</body>
</html>
