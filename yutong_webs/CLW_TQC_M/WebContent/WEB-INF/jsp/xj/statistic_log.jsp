<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<title><s:property value="getText('xj.menu')"/>&nbsp;|&nbsp;<s:property value="getText('statistic.menu2')"/></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript">
	function searchStatisticInfo(){
		$('clwForm').action="<s:url value='/xj/statistic_log.shtml' />";
		$('clwForm').submit();
	}

	/** 导出日志信息 **/
	/**
	function exportLog(){
	    if(confirm("<s:property value="getText('confirm.export.file')" />")) {
	      $('clwForm').action="<s:url value='/xj/exportLog.shtml' />";
	      $('clwForm').submit();
	    }
	  }**/
</script>
</head>

<body>
<%@include file="../common/menu.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<s:form id="clwForm" action="statistic_log" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
				<s:if test="#session.perUrlList.contains('111_0_5_3_1')">
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
												<td width="30%" class="titleStyleZi"><s:property value="getText('statistic.info.list2')"/></td>
												<td width="69%" align="right">
												<%-- 
													<div class="buhuanhangbut">
					                                  <a href="javascript:void(0);" onclick="exportLog()" class="btnDaochu" title="<s:property value="getText('msg.export')" />">
					                                  </a>
					                                </div>
					                            --%>
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
														<th scope="col" width="15%"><s:property value="getText('statistic.info.user_name')"/></th>
														<th scope="col" width="15%"><s:property value="getText('statistic.info.operate_ip')"/></th>
														<th scope="col" width="15%"><s:property value="getText('statistic.info.enterprise_name')"/></th>
														<th scope="col" width="15%"><s:property value="getText('statistic.info.operate_time')"/></th>
														<th scope="col" width="15%"><s:property value="getText('statistic.info.operate_desc')"/></th>
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
															<td class="tdClass"><s:property value="user_name" /></td>
															<td class="tdClass"><s:property value="operate_ip" /></td>
															<td class="tdClass"><s:property value="enterprise_name" /></td>
															<td class="tdClass"><s:property value="operate_time" /></td>
															<td class="tdClass"><s:property value="operate_desc" /></td>
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

</s:form>
</body>
</html>