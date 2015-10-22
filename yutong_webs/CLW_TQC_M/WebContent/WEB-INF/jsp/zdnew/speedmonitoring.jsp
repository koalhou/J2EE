<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title>终端管理&nbsp;|&nbsp;车速监控</title>
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
.buttonstyle{
	background: url(../images/button_bg.gif) repeat-x top left;
	color: #2a2a2a;
	display: block;
	line-height: 24px;
	height: 26px;
	padding-top: 2px;
	text-align: center;
	width: 74px;
}
</style>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="speedmonitoring_validate.jsp"%>
  <%@include file="zTree_common.jsp"%>
  <s:form id="speedmonitoring_form" action="" method="post" onsubmit="return false;">
    <s:hidden id="enterpriseId" name="enterpriseId"/>
    <s:hidden id="carIdList" name="carIdList"/>
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
          <%-- 
          <div class="searchPlanMap">
          	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="15%" class="contentParams">
		        	车速监控
		        </td>
		        <td width="65%">
		        	<div id="message">
		                <s:label id="errorLbl" cssStyle="font-size:12px;color:#CC0000"/>
						<s:label id="successLbl" cssStyle="font-size:12px;color:#009900"/>
              		</div>
		        </td>
		        <td>
		          <s:if test="#session.perUrlList.contains('111_0_5_7_2')">
		          <a href="#" onclick="startAdjust();" class="buttontwo">开启矫正</a>
		          </s:if>
		        </td>
		        <td>
		          <s:if test="#session.perUrlList.contains('111_0_5_7_3')">
		          <a href="#" onclick="closeAdjust();" class="buttontwo">关闭矫正</a>
		          </s:if>
		        </td>
		      </tr>
            </table>
		    </div>
		    --%>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							
								<tr>
									<td class="reportOnline2">
										<s:if test="#session.perUrlList.contains('111_0_5_7_1')">
										<table width="100%" border="0" cellspacing="8" cellpadding="0">
											<tr>
												<td>
													<table border="0" cellspacing="2" cellpadding="0">
														<tr>
															<td>车牌号：<s:textfield id="vehicle_ln" name="vehicle_ln" cssClass="input120" />
															</td>
															<td width="30px" align="left">
															  <a href="#"><img src="../images/gif-0707.gif" border="0" onclick="choiceCar()" /></a>
															</td>
															<td>
															  <a href="#" onclick="searchVehicleList();" class="sbutton"><s:property value="getText('btn.query')" /></a>
															</td>	
														</tr>
													</table>
												</td>	
											</tr>
										</table>
										</s:if>
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
																	<td width="15%" class="titleStyleZi" valign="middle">
																		车速监控信息
																	</td>
																	<td width="65%" valign="middle" align="center">
																		<div id="message">
															                <s:label id="errorLbl" cssStyle="font-size:12px;color:#CC0000"/>
																			<s:label id="successLbl" cssStyle="font-size:12px;color:#009900"/>
													              		</div>
																	</td>
																	<td valign="middle">
																		<s:if test="#session.perUrlList.contains('111_0_5_7_2')">
																          <a href="#" onclick="startAdjust();" class="buttonstyle">开启矫正</a>
																        </s:if>
																    </td>
																    <td valign="middle">
																        <s:if test="#session.perUrlList.contains('111_0_5_7_3')">
																          <a href="#" onclick="closeAdjust();" class="buttonstyle">关闭矫正</a>
																        </s:if>
																	</td>
																	<td width="1%">&nbsp;
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td class="reportInline">
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