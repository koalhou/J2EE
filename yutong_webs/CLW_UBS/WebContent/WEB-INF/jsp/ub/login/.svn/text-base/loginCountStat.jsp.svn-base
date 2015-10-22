<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
<title><s:property
	value="getText('menu.ub.logincnt')" /></title>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<style type="text/css">
.reportOnline5 {
	background: #fff;
	border-bottom: 1px solid #c3c3c3;
	border-left: 1px solid #c3c3c3;
	border-right: 1px solid #c3c3c3;
}
.hidelogin
{
	display:none;
}

</style>


</head>
<body>
<%@include file="../../common/menu.jsp"%>


<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />


<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<script type="text/javascript"
	src="<s:url value='/scripts/common/moment.min.js' />"></script>
<script type="text/javascript">

  
  function query() {

      //Wit.commitAll($('logincntstat_form'));
      $('logincntstat_form').action="<s:url value='/ub/login/querystat.shtml' />";
      $('logincntstat_form').submit();
    
  }   
  function exportstat()
  {
	  if(confirm("<s:property value="getText('confirm.export.file')" />")) {
	      $('logincntstat_form').action="<s:url value='/ub/login/exportStat.shtml' />";
	      $('logincntstat_form').submit();
	    }
	  
  }
  jQuery(function(){
	  var currentm=jQuery("#selMonth").val();
	  
	  var m=moment(currentm, "YYYY-MM").format("MM");
	jQuery(".v_month").html(m);  
	  
  });
</script>
<s:form id="logincntstat_form" action="querystat" method="post">

	<table height="628px" width="100%" border="0" cellspacing="0"
		cellpadding="0">
		<tr valign="top">
			<td class="reportInline">

			<table width="100%" border="0" cellspacing="5" cellpadding="0">
				
					<tr>
						<td class="reportOnline2">
						<table width="100%" border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td width="50%">
								
								<table border="0" cellspacing="4" cellpadding="0">
									<tr>


										<td><s:property value="getText('ub.selectmonth')" />： <s:textfield
											id="selMonth" name="searchparam.statis_month" 
											onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM',onpicked:query})"
											cssClass="Wdate"></s:textfield></td>
										
										<td>
										<div class="hidelogin">
									   	   <s:property value="getText('ub.login.platform')" />：
									   	   
			                               <s:select id="statics_platform" 
			                                          name="searchparam.statics_platform" 
												      list="#{'all':'所有','web':'WEB平台','app':'手机客户端'}"  > 
					                       </s:select> 
					                       </div>
					                    </td>
					                       
					                       
									</tr>
								</table>
								
								</td>
								<td width="50%" align="right""><s:if
									test="#session.perUrlList.contains('111_4_6_2')">
									<div class="buhuanhangbut"><a href="#"
										onclick="exportstat()" class="btnDaochu"
										title="<s:property value="getText('msg.export')" />"> </a></div>
								</s:if>
								
								
								</td>
							</tr>
						</table>
						</td>
					</tr>
				

				<tr>
					<td valign="top"><!-- body -->
					<table width="100%">
						<tr>
							<td>
							<table width="100%">
								<tr>
									<td style="height:30px;">
									<div style="float:left;line-height:30px;font-weight:bold;">安芯<span class="v_month">-</span>月份客户活跃度报表：</div>
									<div style="float:right;line-height:30px;">注：活跃度口径，月登录次数大于等于8次的为活跃客户。</div>
									</td>
								</tr>
								<tr>
									<td><!-- 报表 -->
									<table width="100%" cellspacing="0" class="reportInline2" style="border-collapse:collapse;">
										<thead class="zy_bg">
											<tr >
												<th class="zy_title" height="30" width="25%" scope="col">
												<s:property value="getText('ub.login.customtype')" /></th>
												<th class="zy_title" height="30" width="25%" scope="col">
												<s:property value="getText('ub.login.customcnt')" /></th>
												<th class="zy_title" height="30" width="25%" scope="col">
												<s:property value="getText('ub.login.huoyuecnt')" /></th>
												<th class="zy_title" height="30" width="25%" scope="col">
												<s:property value="getText('ub.login.houyuescale')" /></th>

											</tr>
										</thead>
										<tbody>
											<s:iterator value="staticsList" status="rowstatus">
												<tr>
													<td class="zy_content"><s:property value="systemname" />
													</td>
													<td class="zy_content"><s:property value="epCount" />

													</td>
													<td class="zy_content"><s:property
														value="activeCount" /></td>
													<td class="zy_content"><s:property
														value="activePercentStr"/> </td>

												</tr>
											</s:iterator>
										</tbody>
									</table>
									<!-- 报表 --></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td style="padding-top:50px;">
							<table width="100%">
								<tr>
									<td width="30%" style="padding:0 20px;">
									<table width="100%">
										<tr>
											<td>
											<div style="text-align:center;line-height:30px;font-weight:bold;">安芯A类客户<span class="v_month">-</span>月份客户活跃度报表</div>
											
											</td>
										</tr>
										<tr>
											<td><!-- 报表 -->
											<table width="100%" cellspacing="0" class="reportInline2" style="border-collapse:collapse;">
												<thead class="zy_bg">
													<tr>
														<th class="zy_title" height="30" width="25%" scope="col">
														<s:property value="getText('ub.login.zone')" /></th>
														<th class="zy_title" height="30" width="25%" scope="col">
														<s:property value="getText('ub.login.customtotal')" /></th>
														<th class="zy_title" height="30" width="25%" scope="col">
														<s:property value="getText('ub.login.huoyuecnt')" /></th>
														<th class="zy_title" height="30" width="25%" scope="col">
														<s:property value="getText('ub.login.houyuerate')" /></th>

													</tr>
												</thead>
												<tbody>
													<s:iterator value="staticsA_AreaList" status="rowstatus">
														<tr>
															<td class="zy_content"><s:property
																value="systemname" /></td>
															<td class="zy_content"><s:property
																value="epCount" /></td>
															<td class="zy_content"><s:property
																value="activeCount" /></td>
															<td class="zy_content"><s:property
																value="activePercentStr" /></td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
											<!-- 报表 --></td>
										</tr>
									</table>
									</td>
									<td width="30%" style="padding:0 20px;">
									<table width="100%">
										<tr>
											<td>
											<div style="text-align:center;line-height:30px;font-weight:bold;">安芯B类客户<span class="v_month">-</span>月份客户活跃度报表</div>
											
											</td>
										</tr>
										<tr>
											<td><!-- 报表 -->
											<table width="100%" cellspacing="0" class="reportInline2" style="border-collapse:collapse;">
												<thead class="zy_bg">
													<tr>
														<th class="zy_title" height="30" width="25%" scope="col">
														<s:property value="getText('ub.login.zone')" /></th>
														<th class="zy_title" height="30" width="25%" scope="col">
														<s:property value="getText('ub.login.customtotal')" /></th>
														<th class="zy_title" height="30" width="25%" scope="col">
														<s:property value="getText('ub.login.huoyuecnt')" /></th>
														<th class="zy_title" height="30" width="25%" scope="col">
														<s:property value="getText('ub.login.houyuerate')" /></th>

													</tr>
												</thead>
												<tbody>
													<s:iterator value="staticsB_AreaList" status="rowstatus">
														<tr>
															<td class="zy_content"><s:property
																value="systemname" /></td>
															<td class="zy_content"><s:property
																value="epCount" /></td>
															<td class="zy_content"><s:property
																value="activeCount" /></td>
															<td class="zy_content"><s:property
																value="activePercentStr" /></td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
											<!-- 报表 --></td>
										</tr>
									</table>
									</td>
									<td width="30%" style="padding:0 20px;">
									<table width="100%">
										<tr>
											<td>
											<div style="text-align:center;line-height:30px;font-weight:bold;">安芯C类客户<span class="v_month">-</span>月份客户活跃度报表</div>
											
											</td>
										</tr>
										<tr>
											<td><!-- 报表 -->
											<table width="100%" cellspacing="0" class="reportInline2" style="border-collapse:collapse;">
												<thead class="zy_bg">
													<tr>
														<th class="zy_title" height="30" width="25%" scope="col">
														<s:property value="getText('ub.login.zone')" /></th>
														<th class="zy_title" height="30" width="25%" scope="col">
														<s:property value="getText('ub.login.customtotal')" /></th>
														<th class="zy_title" height="30" width="25%" scope="col">
														<s:property value="getText('ub.login.huoyuecnt')" /></th>
														<th class="zy_title" height="30" width="25%" scope="col">
														<s:property value="getText('ub.login.houyuerate')" /></th>

													</tr>
												</thead>
												<tbody>
													<s:iterator value="staticsC_AreaList" status="rowstatus">
														<tr>
															<td class="zy_content"><s:property
																value="systemname" /></td>
															<td class="zy_content"><s:property
																value="epCount" /></td>
															<td class="zy_content"><s:property
																value="activeCount" /></td>
															<td class="zy_content"><s:property
																value="activePercentStr" /></td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
											<!-- 报表 --></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>

					<!-- body --></td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
</s:form>

<%@include file="../../common/copyright.jsp"%>
</body>
</html>