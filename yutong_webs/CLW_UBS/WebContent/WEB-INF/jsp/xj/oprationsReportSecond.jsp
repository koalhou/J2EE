<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<title><s:property value="getText('xj.menu')"/>&nbsp;|&nbsp;<s:property value="getText('operationsInfo.info.list')"/></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
</head>
<body>
<script type="text/javascript">
function searchReportInfo(){
	//document.getElementById('clwForm').submit();
	document.getElementById("enterprise_code").value="<s:property value='enterprise_code'/>";
	$('clwReportForm').action="<s:url value='/xj/enterReportInfo.shtml' />";
	Wit.commitAll($('clwReportForm'));
}
/** 打开图表**/
function openChartBrowse(vehicle_vin) {
  var obj = window.showModalDialog("<s:url value='/xj/openChartReport.shtml' />?vehicle_vin="+ vehicle_vin +"&enterprise_code=<s:property value='enterprise_code'/>&rad="+Math.random(),
  	                             self,
  	                             "dialogWidth=740px;dialogHeight=510px;dialogLeft:200px");
}

/** 导出运营报表分析 **/
function exportSecondReport(){
  if(confirm("<s:property value="getText('confirm.export.file')" />")) {
    $('clwReportForm').action="<s:url value='/xj/exportSecondReport.shtml' />?expenterprise_code=<s:property value='enterprise_code'/>";
    Wit.commitAll($('clwReportForm'));
  }
}
</script>
<%@include file="../common/menu.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
				<s:form id="clwReportForm" action="enterReportInfo" method="post">
				<s:if test="#session.perUrlList.contains('111_0_5_5_1')">
					<tr>
	                  	<td class="reportOnline2">
		                    <table width="100%" border="0" cellspacing="8" cellpadding="0">
		                      <tr>
		                        
								<td><table border="0" cellspacing="4" cellpadding="0" >
		                          <tr>
		                            <td><s:property value="getText('oprationsReportSecond.info.vehicle_vin')"/>：
		                            	<s:textfield id="vehicle_vin" name="vehicle_vin" />
		                           		&nbsp;&nbsp;
		                           		<s:property value="getText('oprationsReportSecond.info.terminal_id')"/>：
		                            	<s:textfield id="terminal_id" name="terminal_id" />
		                            	&nbsp;&nbsp;
		                           		<s:property value="getText('oprationsReportSecond.info.sim_card_number')"/>：
		                            	<s:textfield id="sim_card_number" name="sim_card_number" />
		                            	<input type="hidden" id="enterprise_code" name="enterprise_code" />
		                            </td>
		                            <td><a href="#" onclick="searchReportInfo()" class="sbutton"><s:property value="getText('btn.query')" /></a></td>
		                          </tr>
		                        </table></td>
		                       
		                      </tr>
		                    </table>                  
	                    </td>
                	</tr>
                	</s:if>
                	</s:form>
					<tr>
						<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="reportOnline">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class="titleStyle">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="30%" class="titleStyleZi"><s:property value="getText('operationsInfo.info.list')"/></td>
												<td width="69%" align="right">
													<div class="buhuanhangbut"><a href="<s:url value='/xj/operationsReport.shtml'/>?page=<s:property value='page'/>&pageSize=<s:property value='pageSize'/>" class="btnBack" title="<s:text name="btn.goBack" />"></a></div>
					                                <s:if test="#session.perUrlList.contains('111_0_5_5_3')">
					                                <div class="buhuanhangbut">
					                                  <a href="#" onclick="exportSecondReport()" class="btnDaochu" title="<s:property value="getText('msg.export')" />">
					                                  </a>
					                                </div>
                                				 </s:if>
												</td>
												<td width="1%">&nbsp;</td>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td class="reportInline">
										<div id="list">
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
				                              <tr>
				                                <td align="right" bgcolor="#F6F6F6">
				                                  <div id="message">
				                                    <s:actionerror theme="mat" />
				                                    <s:fielderror theme="mat"/>
				                                    <s:actionmessage theme="mat"/>
				                                  </div>
				                                </td>
				                              </tr>
				                            </table>
											<table cellspacing="0" width="100%" class="reportInline2">
												<thead>
													<tr>
														<th scope="col" width="5%"><s:property value="getText('list.number')"/></th>
														<th scope="col" width="10%"><s:property value="getText('oprationsReportSecond.info.vehicle_vin')"/></th>
														<th scope="col" width="10%"><s:property value="getText('oprationsReportSecond.info.terminal_id')"/></th>
														<th scope="col" width="10%"><s:property value="getText('oprationsReportSecond.info.sim_card_number')"/></th>
														<th scope="col" width="10%"><s:property value="getText('oprationsReportSecond.info.curr_datasum')"/></th>
														<th scope="col" width="10%"><s:property value="getText('oprationsReportSecond.info.curr_start')"/></th>
														<th scope="col" width="10%"><s:property value="getText('oprationsReportSecond.info.curr_end')"/></th>
														<th scope="col" width="10%"><s:property value="getText('oprationsReportSecond.info.his_datasum')"/></th>
														<th scope="col" width="12%"><s:property value="getText('oprationsReportSecond.info.his_start')"/></th>
														<th scope="col" width="10%"><s:property value="getText('oprationsReportSecond.info.delivery_flag')"/></th>
														<th scope="col" width="5%"></th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="operInfo" status="rowstatus">
														<tr>
															<td class="tdClass"><s:property value="#rowstatus.index+1" /></td>
															<td class="tdClass"><s:property value="vehicle_vin" /></td>
															<td class="tdClass"><s:property value="terminal_id" /></td>
															<td class="tdClass"><s:property value="sim_card_number" /></td>
															<td class="tdClass"><s:property value="curr_datasum" /></td>
															<td class="tdClass"><s:property value="curr_start" /></td>
															<td class="tdClass"><s:property value="curr_end" /></td>
															<td class="tdClass"><s:property value="his_datasum" /></td>
															<td class="tdClass"><s:property value="his_start" /></td>
															<td class="tdClass">
															<s:if test="delivery_flag==0">
															<s:property value="getText('delivery_flag_0')"/>
															</s:if>
															<s:elseif test="delivery_flag==1">
															<s:property value="getText('delivery_flag_1')"/>
															</s:elseif>
															</td>
															<td class="tdClass"><img src="<s:url value='/images/bingtu.gif'/>" border="0" style="width:16px; height:16px;cursor:pointer;" onclick="openChartBrowse('<s:property value="vehicle_vin" />')"/></td>
														</tr>
													</s:iterator>
													<s:iterator value="operSumInfo" status="rowstatus">
														<tr>
															<td class="tdClass" style="font-weight: bold ;font-size:14px;"><s:property value="getText('common.sum')"/>：</td>
															<td class="tdClass"></td>
															<td class="tdClass"></td>
															<td class="tdClass"></td>
															<td class="tdClass" style="font-weight: bold ;font-size:14px;"><s:property value="curr_datasum" /></td>
															<td class="tdClass"></td>
															<td class="tdClass"></td>
															<td class="tdClass" style="font-weight: bold ;font-size:14px;"><s:property value="his_datasum" /></td>
															<td class="tdClass"></td>
															<td class="tdClass"></td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
											<div id="pageCtrl" align="right">
				                              <s:property value="pageBar" escape="false" />
				                            </div>
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

		<%@include file="../common/copyright.jsp"%>
		</td>
	</tr>
</table>

</body>
</html>