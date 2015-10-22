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
function searchStatisticInfo(){
	//document.getElementById('clwForm').submit();
	 $('clwForm').action="<s:url value='/xj/operationsReportSearch.shtml' />";
	Wit.commitAll($('clwForm'));
}

/** 导出运营报表分析 **/
function exportFirstReport(){
  if(confirm("<s:property value="getText('confirm.export.file')" />")) {
    $('clwForm').action="<s:url value='/xj/exportFirstReport.shtml' />";
    Wit.commitAll($('clwForm'));
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
				<s:form id="clwForm" action="operationsReportSearch" method="post">
				<s:if test="#session.perUrlList.contains('111_0_5_5_1')">
					<tr>
	                  	<td class="reportOnline2">
		                    <table width="100%" border="0" cellspacing="8" cellpadding="0">
		                      <tr>
		                        
								<td><table border="0" cellspacing="4" cellpadding="0" >
		                          <tr>
		                            <td><s:property value="getText('operationsInfo.info.enterprise_code')"/>：
		                            <s:textfield id="enterprise_code" name="enterprise_code" />
		                           		&nbsp;&nbsp;
		                           		<s:property value="getText('operationsInfo.info.full_name')"/>：
		                            <s:textfield id="full_name" name="full_name" />
		                            </td>
		                            <td><a href="#" onclick="searchStatisticInfo()" class="sbutton"><s:property value="getText('btn.query')" /></a></td>
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
												 <s:if test="#session.perUrlList.contains('111_0_5_5_2')">
					                                <div class="buhuanhangbut">
					                                  <a href="#" onclick="exportFirstReport()" class="btnDaochu" title="<s:property value="getText('msg.export')" />">
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
										<div id="message">
				                                    <s:actionerror theme="mat" />
				                                    <s:fielderror theme="mat"/>
				                                    <s:actionmessage theme="mat"/>
				                                  </div>
										<div id="list">
											<table cellspacing="0" width="100%" class="reportInline2">
												<thead>
													<tr>
														<th scope="col" width="5%"><s:property value="getText('list.number')"/></th>
														<th scope="col" width="15%"><s:property value="getText('operationsInfo.info.enterprise_code')"/></th>
														<th scope="col" width="25%"><s:property value="getText('operationsInfo.info.full_name')"/></th>
														<th scope="col" width="10%"><s:property value="getText('operationsInfo.info.account_num')"/></th>
														<th scope="col" width="10%"><s:property value="getText('operationsInfo.info.zhuce_num')"/></th>
														<th scope="col" width="10%"><s:property value="getText('operationsInfo.info.curr_active_num')"/></th>
														<th scope="col" width="10%"><s:property value="getText('operationsInfo.info.his_active_num')"/></th>
														<th scope="col" width="10%"><s:property value="getText('operationsInfo.info.out_factory_num')"/></th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="pageList" status="rowstatus">
														<tr>
															<td class="tdClass"><s:property value="#rowstatus.index+1" /></td>
															<td class="tdClass">
															<s:url id="showDetail" action="enterReportInfo">
													          <s:param name="enterprise_code" value="enterprise_code" />
													          <s:param name="page">${page}</s:param>
													          <s:param name="pageSize">${pageSize}</s:param>
												            </s:url>
												            <a href="#" onclick="Wit.goBack('${showDetail}');">
												              <s:property value="enterprise_code" />
												            </a>
															</td>
															<td class="tdClass"><s:property value="full_name" /></td>
															<td class="tdClass"><s:property value="account_num" /></td>
															<td class="tdClass"><s:property value="zhuce_num" /></td>
															<td class="tdClass"><s:property value="curr_active_num" /></td>
															<td class="tdClass"><s:property value="his_active_num" /></td>
															<td class="tdClass"><s:property value="delivery_flag" /></td>
														</tr>
													</s:iterator>
													<s:iterator value="pageListSum" status="rowstatus">
														<tr>
															<td class="tdClass" style="font-weight: bold ;font-size:14px;"><s:property value="getText('common.sum')"/>：</td>
															<td class="tdClass"></td>
															<td class="tdClass"></td>
															<td class="tdClass" style="font-weight: bold ;font-size:14px;"><s:property value="account_num" /></td>
															<td class="tdClass" style="font-weight: bold ;font-size:14px;"><s:property value="zhuce_num" /></td>
															<td class="tdClass" style="font-weight: bold ;font-size:14px;"><s:property value="curr_active_num" /></td>
															<td class="tdClass" style="font-weight: bold ;font-size:14px;"><s:property value="his_active_num" /></td>
															<td class="tdClass" style="font-weight: bold ;font-size:14px;"><s:property value="delivery_flag" /></td>
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