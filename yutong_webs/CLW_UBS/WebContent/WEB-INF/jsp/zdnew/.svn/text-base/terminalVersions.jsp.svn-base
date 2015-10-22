<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title>终端管理&nbsp;|&nbsp;终端版本信息</title>
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
</style>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="terminalVersions_validate.jsp"%>
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
										<table width="100%" border="0" cellspacing="8" cellpadding="0">
											<tr>
												<td>
													<table width="100%" border="0" cellspacing="4" cellpadding="0">
														<tr>
															<td align="right">车牌号：</td>	
															<td align="left">
																<s:textfield id="vehicle_ln" name="queryObj.vehicle_ln" cssClass="input120" />&nbsp;&nbsp;
															</td>
															<td align="right">VIN号：</td>
															<td>
																<s:textfield id="vehicle_vin" name="queryObj.vehicle_vin" cssClass="input120" />&nbsp;&nbsp;
															</td>
															<td align="right">终端号：</td>
															<td>
																<s:textfield id="terminal_id" name="queryObj.terminal_id" cssClass="input120" />&nbsp;&nbsp;
                                                            </td>
															<td align="right">手机号：</td>	
															<td align="left">
																<s:textfield id="cellphone" name="queryObj.cellphone" cssClass="input120" />&nbsp;&nbsp;
															</td>
														    <td width="1%">&nbsp;</td>
														</tr>
														<tr>                                                           
															<td align="right">ICCID：</td>	
															<td align="left">
																<s:textfield id="sim_sccid" name="queryObj.sim_sccid" cssClass="input120" />&nbsp;&nbsp;
															</td>
															<td align="right">主机硬件版本：</td>	
															<td align="left">
																<s:textfield id="host_hard_ver" name="queryObj.host_hard_ver" cssClass="input120" />&nbsp;&nbsp;
															</td>
															<td align="right">主机固件版本：</td>
															<td>
																<s:textfield id="host_firm_ver" name="queryObj.host_firm_ver" cssClass="input120" />&nbsp;&nbsp;
															</td>
															<td align="right">显示屏硬件版本：</td>
															<td>
																<s:textfield id="xianshi_hard_ver" name="queryObj.xianshi_hard_ver" cssClass="input120" />&nbsp;&nbsp;
                                                            </td>															
														    <td width="1%">&nbsp;</td>
														</tr>
														<tr>
															<td align="right">显示屏固件版本：</td>	
															<td align="left">
																<s:textfield id="xianshi_firm_ver" name="queryObj.xianshi_firm_ver" cssClass="input120" />&nbsp;&nbsp;
															</td>
															<td align="right">DVR硬件版本：</td>	
															<td align="left">
																<s:textfield id="dvr_hard_ver" name="queryObj.dvr_hard_ver" cssClass="input120" />&nbsp;&nbsp;
															</td>														
															<td align="right">DVR固件版本：</td>	
															<td align="left">
																<s:textfield id="dvr_firm_ver" name="queryObj.dvr_firm_ver" cssClass="input120" />&nbsp;&nbsp;
															</td>
															
														    <td colspan="3">&nbsp;</td>
														</tr>
														<tr>
															<td align="right">射频读卡器硬件版本：</td>
															<td>
																<s:textfield id="shepin_hard_ver" name="queryObj.shepin_hard_ver" cssClass="input120" />&nbsp;&nbsp;
															</td>														
															<td align="right">射频读卡器固件版本：</td>
															<td>
																<s:textfield id="shepin_firm_ver" name="queryObj.shepin_firm_ver" cssClass="input120" />&nbsp;&nbsp;
                                                            </td>
															<td colspan="3" >&nbsp;</td>                                                           
														    <td colspan="2" align="center">
														    <s:if test="#session.perUrlList.contains('111_0_3_5_1')">
														    	<a href="#" onclick="searchVehicleList()" class="sbutton"><s:property value="getText('btn.query')" /></a>
															</s:if>
															</td>
														</tr>																																																																						
													</table>
												</td>																																				
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
																	<td width="30%" class="titleStyleZi">
																		终端版本信息
																	</td>
																	<td width="69%" align="right">
																		&nbsp;
																	</td>
																	<td width="1%">&nbsp;
																	</td>
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