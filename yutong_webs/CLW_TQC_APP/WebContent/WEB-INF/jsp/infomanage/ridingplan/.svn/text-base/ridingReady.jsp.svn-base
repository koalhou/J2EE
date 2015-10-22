<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<link rel="stylesheet" href="<s:url value='/styles/nyroModal.css' />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
	<style type="text/css">
		.searchBox3{ background: #f5f5f5; border: 1px solid #c3c3c3; float: left; line-height: 32px; margin: 5px;  width:230px; height:35px;}
		.searchPlan {
			float: left;
			width: 250px;
		}
	</style>
</head>
<body>
	<div id="wrapper">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">
			<s:hidden id="user_org_id" name="user_org_id"/>
			  <table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
				  <td id='searchPlanId' width="250" valign="top" class="treeline">
				   <div id="leftInfoDiv"  class="searchPlan">
				  <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td>
					   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
			            <tr>
			              <td height="36" background="../images/tree_tabBg.gif" class="titleStyleZi">线路</td>
			              <td align="right" background="../images/tree_tabBg.gif" >
						   <div class="searchHide"><a href="#" onClick="LeftScreen() ;return false;" class="btnHide"></a></div>
					      </td>
			            </tr>
			          </table>
			           </td>
			        </tr>
			
			        <tr>
			          <td align="center" valign="top">
			          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="searchBox3">
			            <tr>
			              <td align="center"><input type="text" name="route_name" id="route_name" class="input120" onkeypress="if(event.keyCode==13){searchRoute();}"/></td>
			              <td align="center"><input style=" width:70px; height:26px;" type="image" name="imageField" id="imageField" src="../images/btn_searchroad.gif" onclick="searchRoute()"/></td>
			            </tr>
			          </table></td>
			        </tr>       
			        <tr>
				        <td class="reportInline">
				          	<div id="Below_new" style="text-align:center"><s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/></div>
							<table id="gala" width="100%"  cellspacing="0"></table>
				        </td>
			        </tr>
			      </table>
			      </div>
			      </td>
			      <td id="leftTabtd" valign="top" bgcolor="#E9E9E9" width="24" style="display:none">
				     <table id="leftTab" width="100%" height="30" border="0" cellpadding="0" cellspacing="0" style="display:none">
			           <tr>
			             <td>
					     <div class="searchHide2"><a href="#" class="btnTreeVisible" id="Image2" onClick="midScreen() ;return false;"></a></div>
					     </td>
			           </tr>
			        </table>
			      </td>      
			        <td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="36" valign="top" background="../images/tree_tabBg.gif">
							<div class="toolbar">
							<div class="contentTil">乘车规划</div>
							</div>
							</td>
						</tr>
					</table>
					<s:form id="addridingPlanForm" action="/infomanage/ridingadd" method="post"> 
					<table width="100%" border="0" cellspacing="5" cellpadding="0">	
					<tr>
			           <td class="reportOnline2" height="40">
							  <table width="100%" border="0" cellspacing="8" cellpadding="0">
			                    <tr>
			                      <td><table border="0" cellspacing="8" cellpadding="0">
			                        <tr>
			                          <s:hidden id="routeid" name="ridingReady.route_id"/>
			                          <td id="vln">车辆选择：
			                              <s:textfield id="vehicle_ln" name="ridingReady.vehicle_ln" cssClass="input120" maxlength="30" onclick="choiceCar()" readonly="true" /><span class="noticeMsg">*</span>   
			                              <s:hidden id="vehicle_vin" name="ridingReady.vehicle_vin" cssClass="input120"/>                          
			                          </td>
			                          <td id="vln">驾驶员选择：
			                              <s:textfield id="driver_names" name="ridingReady.driver_names" cssClass="input120" maxlength="30" onclick="choiceDriver()" readonly="true" />  
			                              <s:hidden id="driver_ids" name="ridingReady.driver_ids"/>
			                          </td>
			                          <td id="vln">跟车老师选择：
			                              <s:textfield id="steward_names" name="ridingReady.steward_names" cssClass="input120" maxlength="30" onclick="choiceSichen()" readonly="true" />
			                              <s:hidden id="steward_ids" name="ridingReady.steward_ids"/>
			                              <s:hidden id="site_students" name ="ridingReady.site_students"/>
			                              <s:hidden id="route_status" name ="ridingReady.route_status"/>
			                          </td>
			                        </tr>
			                      </table></td>
			                    </tr>
			                  </table>
			             </td>
			         </tr>	
			          <tr>
						<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
							<tr>
								<td class="titleStyle">		               
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
								   <td>
									<%-- 列表标题 --%>
									<table width="100%" border="0" cellspacing="0" cellpadding="0" class="titleStyle">
										<div  class="reportTab">
										   <ul>
										     <li><a>站点信息</a></li>
										   </ul>
										</div>
									</table>
								   </td>
							     </tr>
									<tr>
									  <td  class="reportInline" >
									    <span id="parentSpan1"><table id="gala2" ></table></span>
								      </td>
									</tr>
									<tr>
									<table width="100%" border="0" cellspacing="8" cellpadding="0">
			                                 	<tr>
			                  						 <td width="90%"></td>
			                  						 <s:if test="#session.perUrlList.contains('111_3_3_7_1')">
			                  						 <td><a href="#" onclick="submitForm()" class="sbutton">确认</a></td>
			                  						 </s:if>
					 						 <td><a href="../infomanage/ridingplan.shtml" class="sbutton">取消</a></td>
			                						 </tr>
			               					</table>
									</tr>
								</table>
								</td>
							</tr>							
						</table>
						</td>          
			          </tr>
			          <tr>
			         </tr>	
					</table>
					</s:form>  		        
					</td>
			      </tr>
			    </table>
			</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<%@include file="ridingReady_validate.jsp"%>
</body>
</html>