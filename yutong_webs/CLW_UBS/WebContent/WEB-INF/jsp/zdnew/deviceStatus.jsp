<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title>终端管理&nbsp;|&nbsp;终端设备状态</title>
<style type="text/css">
.searchBox3{ background: #f5f5f5; border: 1px solid #c3c3c3; float: left; line-height: 32px; margin: 5px;  width:230px; height:35px;}
.searchPlan {
	float: left;
	width: 260px;
}
.mapsMask{ background: #fff; position: relative; top: 20px; display: block; z-index: 1000; height: 20px; width: 150px;}
a.btnbuleMap{ background: url(../images/btn_blue.gif) no-repeat left top; color: #fff; display: block; height: 28px; line-height: 28px; text-align: center; 
width: 76px; }
.searchPlanMap{ background: #eee url(../images/tree_tabBg.gif) repeat-x left top;float: left; height: 35px; padding-top: 3px; width: 100%;}
.fanwei{ width:260px; height:0px; overflow:auto;}
.contentParams{
	color: #121212;
	font-size: 14px;
	font-weight: bold;
	line-height: 20px;
	padding-left: 8px;
}

.hongd_dot{ color:#FF0000; font-size:26px;margin-top: -9px;}
.lvd_dot{ color:#0C0; font-size:26px;margin-top: -9px;}
.huid_dot{ color:#999; font-size:26px;margin-top:-9px;}
</style>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="deviceStatus_validate.jsp"%>
  <%@include file="zTree_common.jsp"%>
  <s:form id="terminalparams_form" action="" method="post" onsubmit="return false;">
    <s:hidden id="enterpriseId" name="enterpriseId"/>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
	    <td id='searchPlanId' width="260" valign="top" class="treeline">
	      <div id="leftInfoDiv"  class="searchPlan">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center" valign="top">
			      <table border="0" align="left" cellpadding="0"	cellspacing="0">
				    <tr>
				      <td>
						<div class="searchPlanMap">
				          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				            <tr>
				              <td width="130" align="right">
				                <input name="enterpriseName" id="enterpriseName" type="text" class="input120" />
				              </td>
				              <td align="center">
				                <a href="#" class="btnbuleMap" onclick="searchTree()">企业查询</a>
				              </td>
				              <td align="center">
				                <div class="searchHide">
				                  <a href="#" onClick="LeftScreen() ;return false;" class="btnHide"></a>
				                </div>
				              </td>
				            </tr>
				          </table>
				        </div>
					  </td>
				    </tr>
				    <tr>
				      <td align="center" valign="top">
				      	<div>
                          <div>
                            <ul id="treeDemo" class="ztree"></ul>
                          </div>
                        </div>
			            <div class="fanwei">
					    </div>
					  </td>
				    </tr>
			      </table>
                </td>
              </tr>
              <tr>
	            <td class="reportInline" align="left">
				  &nbsp;
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
      
        <td valign="top" >
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							
								<tr>
						           <td class="reportOnline2">
										  <table width="100%" border="0" cellspacing="4" cellpadding="0">
						                    <tr>
						                      <td><table border="0" cellspacing="4" cellpadding="0">
						                        <tr>
						                          <td id="vln">
						                          <table><tr>
						                          	<td>车牌号：<s:textfield id="vehicle_ln" name="vehicle_ln" cssClass="input120" maxlength="30" /></td>
						                          	<td><a href="#"><img src="../images/gif-0707.gif" border="0" onclick="choiceCar()" /></a></td>
						                          </tr></table>
						                          </td>
						                          <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						                          <td id="vln">车况：&nbsp;
						                              <s:select id="device_status" name="device_status" list="device_status_map" headerKey="" headerValue="%{getText('please.select')}">
													  </s:select> &nbsp;
						                          </td>						                          
						                          <td><a href="javascript:void(0);" onclick="javascript:searchVehicleList();" class="sbutton">查询</a></td>
						                        </tr>
						                      </table></td>
						                    </tr>
						                  </table>
						             </td>
								</tr>
							

							<tr>
								<td valign="top">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="reportOnline">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td class="titleStyle">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td width="30%" class="titleStyleZi"> 终端设备状态 </td>
																	<td align="right">
																		<table><tr>
																	    <td align="right"><div style="margin-top: -8px;_margin-top: -4px;"><font class="lvd_dot">●</font></div></td>
																	    <td align="left">：正常</td>
																	    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
																	    <td align="right"><div style="margin-top: -8px;_margin-top: -4px;"><font class="hongd_dot">●</font></div></td>
																	    <td align="left">：异常</td>
																	     <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
																	    <td align="right"><div style="margin-top: -8px;_margin-top: -4px;"><font class="huid_dot">●</font></div></td>
																	    <td align="left">：不在线</td>																			
																		</tr></table>
																	</td>
															        <td width="1%">&nbsp;</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td class="reportInline">
															<div id="message">
															  <div id="empDiv">
																<s:actionerror theme="mat" />
																<s:fielderror theme="mat" />
																<s:actionmessage theme="mat" />
														      </div>
															</div>
															<div>
																<table id="carList" width="100%"  cellspacing="0" style="Text-align:center"></table>
															</div>
														</td>
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
	    </td>
      </tr>
    </table>
  </s:form>
  <%@include file="../common/copyright.jsp"%>
</body>
</html>