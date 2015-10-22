<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<title><s:property value="getText('xj.menu')"/>&nbsp;|&nbsp;<s:property value="getText('statistic.menu')"/></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
</head>
<body>
<script type="text/javascript">
function searchStatisticInfo(){
	//document.getElementById('clwForm').submit();
	Wit.commitAll($('clwForm'));
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
				<s:form id="clwForm" action="statistic" method="post">
				<s:if test="#session.perUrlList.contains('111_0_5_2_1')">
					<tr>
	                  	<td class="reportOnline2">
		                    <table width="100%" border="0" cellspacing="8" cellpadding="0">
		                      <tr>
		                        
								<td><table border="0" cellspacing="4" cellpadding="0" >
		                          <tr>
		                            <td><s:property value="getText('statistic.info.apply_id')"/>：
		                            	<select name="apply_id" id="apply_id">
		                            		<option value="" <s:if test="apply_id==0">selected</s:if>><s:property value="getText('select.all')"/></option>
			                              	<option value="0" <s:if test="apply_id==\"0\"">selected</s:if>><s:property value="getText('apply_id_0')"/></option>
			                              	<option value="1" <s:if test="apply_id==\"1\"">selected</s:if>><s:property value="getText('apply_id_1')"/></option>
		                              	</select>
		                              	&nbsp;&nbsp;
		                              	<s:property value="getText('statistic.info.time_begin')"/>：
		                              	<input id="time_begin" 
									             name="time_begin"
									             value="${time_begin}"
									             type="text"
									             class="Wdate"
									             readonly="readonly"
									             onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,maxDate:'#F{$dp.$D(\'time_end\')}'})"
									             />
		                           		&nbsp;&nbsp;
		                           		<s:property value="getText('statistic.info.time_end')"/>：
		                           		<input id="time_end" 
									             name="time_end"
									             value="${time_end}"
									             type="text"
									             class="Wdate"
									             readonly="readonly"
									             onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,minDate:'#F{$dp.$D(\'time_begin\')}'})"
									             />
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
												<td width="30%" class="titleStyleZi"><s:property value="getText('statistic.info.list1')"/></td>
												<td width="69%" align="right">
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
														<th scope="col" width="15%"><s:property value="getText('statistic.info.apply_id')"/></th>
														<th scope="col" width="25%"><s:property value="getText('statistic.info.module_name')"/></th>
														<th scope="col" width="20%"><s:property value="getText('statistic.info.operate_type')"/></th>
														<th scope="col" width="20%"><s:property value="getText('statistic.info.count_num')"/></th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="pageList" status="rowstatus">
														
														<tr>
															<td class="tdClass"><s:property value="#rowstatus.index+1" /></td>
															<td class="tdClass">
															<s:if test="apply_id==0">
															<s:property value="getText('apply_id_0')"/>
															</s:if>
															<s:elseif test="apply_id==1">
															<s:property value="getText('apply_id_1')"/>
															</s:elseif>
															<s:elseif test="apply_id==2">
															<s:property value="getText('apply_id_2')"/>
															</s:elseif>
															</td>
															<td class="tdClass"><s:property value="module_name" /></td>
															<td class="tdClass"><s:property value="operate_type" /></td>
															<td class="tdClass"><s:property value="count_num" /></td>
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