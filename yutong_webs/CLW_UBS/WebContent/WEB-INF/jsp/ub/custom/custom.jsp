<%@page language="java" contentType="text/html;charset=utf-8"%>

<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/poshytip/tip-yellow/tip-yellow.css'/>">
<title><s:property
	value="getText('menu.ub.customliveness')" /></title>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/common/function_common.js' />"></script>
	<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>

</head>
<body>
<%@include file="../../common/menu.jsp"%>
<%@include file="custom_js.jsp"%>

<script type="text/javascript"
	src="<s:url value='/scripts/poshytip/jquery.poshytip.js' />"></script>	
<script type="text/javascript"
	src="<s:url value='/scripts/common/HelpTip.js' />"></script>
	
<s:form id="logincntstat_form" action="querystat" method="post">

	<table height="628px" width="100%" border="0" cellspacing="0"
		cellpadding="0">
		<tr valign="top">
			<td class="reportInline">

			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
				
					<tr >
						<td class="reportOnline2" >
						
							<div style="float: left;  padding: 5px; line-height: 30px;width:100%;">
								
								<ul class="recent_ul">
								<li id="recent7"><a href="#" id="q_r7" onclick="q_switch(7)">
								 <s:property
								value="getText('ub.recent7day')" /></a></li>
	
								<a href="#" id="q_r30" onclick="q_switch(30)">
								<s:property
								value="getText('ub.recent30day')" /> </a>
								</li>
								</ul>
								
								<span style="float:left;padding:0px 10px;"> <s:property
								value="getText('ub.selectmonth')" />：</span>
								<div style="float:left;padding:0px 10px;">
	 &nbsp;
									 <s:textfield
												id="selMonth" name="selMonth" 
												onfocus="var date=getMaxDateMonth();WdatePicker({maxDate:date,skin:'whyGreen',dateFmt:'yyyy-MM',onpicked:selMonthClick})"
												cssClass="Wdate" ></s:textfield>
								</div>
								<span style="float:left;padding:0px 10px;"> <s:property
								value="getText('ub.dateduration')" />：</span>
								<div style="float:left;padding:0px 10px;">
	
									 <s:textfield
									id="startTime" name="searchparam.startDate"
									onfocus="WdatePicker({maxDate:'#F{$dp.$D(\\\'endTime\\\');}',dateFmt:'yyyy-MM-dd',autoPickDate:true,onpicked:function(){startTimeClick();endTime.focus();}})"
									cssClass="Wdate">
								</s:textfield>-<s:textfield id="endTime" name="searchparam.endDate"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\\\'startTime\\\');}',maxDate:'%y-%M-{%d-1}',dateFmt:'yyyy-MM-dd',autoPickDate:true,onpicked:endTimeClick})"
									cssClass="Wdate">
								</s:textfield>
								</div>
								<!-- timespan -->
								<div id="timespancon1" style="margin:0px 10px;float:left;"></div>						
								<!-- timespan -->
								<div style="float:left;padding:0px 10px;" >
									<s:property value="getText('ub.selectplat')" />：
	                              
	                              	 <s:select cssStyle="width:100px;" id="plat" list="#{'all':'所有','web':'WEB平台','app':'手机客户端'}"  listKey="key" listValue="value" onchange="platClick()">
			                        </s:select>
			                        </div>
	
							</div>
						
						</td>
					</tr>
					<tr>
						<td class="reportOnline2" style="background:rgb(246,246,246);" >
							<div style=" float:right;padding: 5px; margin:5px;line-height: 30px;">
		                              	 <input id="searchText" value="请输入企业客户名称或企业编码" class="ub_searchinput" 
		                              	 onfocus="this.select();" onblur="if(this.value=='')this.value='请输入企业客户名称或企业编码';"
		                              	 onkeyup="e = event ? event :(window.event ? window.event : null); if(e.keyCode==13)searchent();"/>
		                              	 <div style="float:left;height:25px;margin-top:3px;margin-left:3px;"><div class="ub_searchbtn"  onclick="searchent()"></div></div>
                            </div>
							<div style=" float:left;padding: 5px; margin:5px;line-height: 30px;">
									
							
									<div style="float:left;padding:0px 10px;" >
									大区：
									 <s:select cssStyle="width:100px;" id="zoneSelect" name="regions" list="regions" listKey="code" listValue="name" headerKey="" headerValue="全部" onchange="zoneSelClick()">
			                        </s:select>
									
	                              	 </div>
									<div style="float:left;padding:0px 10px;" >
									省份：<select style="width:100px;" id="provinceSelect" onchange="provinceSelClick()"><option value="">全部</option></select>
	                              	 </div>
	                              	 <div style="float:left;padding:0px 10px;" >
									地市：<select style="width:100px;" id="citySelect" onchange="citySelClick()"><option value="">全部</option></select>
	                              	 </div>
	                              	 
	                              	  <div style="float:left;padding:0px 10px;" >
									企业类别：<select style="width:100px;" id="entTypeSelect" onchange="entTypeSelClick()">
												<option value="">全部</option>
												<option value="1">A类</option>
												<option value="2">B类</option>
												<option value="3">C类</option>
											</select>
	                              	 </div>
	                              	  <div style="float:left;padding:0px 10px;" >
									是否活跃：<select  style="width:100px;" id="activitySelect" onchange="acSelClick()">
												<option value="">全部</option>
												<option value="1">活跃</option>
												<option value="0">不活跃</option>
											</select>
	                              	 </div>
	                              	 <div style="float:left;padding:0px 10px;margin-top:3px;" >
		                              	 <a href="#" onclick="query()" class="sbutton">
	                                  <s:property value="getText('btn.query')" />
	                               		 </a>
                               		 </div>
							</div>
						</td>
					</tr>
					<tr style="background:white;">
						<td  >
							<div style="margin:5px 10px 5px 10px;">
							<!-- 汇总摘要统计 -->
								<table id="summaryCon" class="summary" width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
									<span class="text" _ubtip="ent_vehiclecnt">车辆总数</span><br/>
									<span class="value" id="sum_viheclecnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="ent_openentcnt">开通企业客户数</span><br/>
									<span class="value" id="sum_openentcnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="ent_acentcnt">活跃企业客户数</span><br/>
									<span class="value" id="sum_liveentcnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="ent_entac">企业客户活跃度</span><br/>
									<span class="value" id="sum_activity">-</span>
									</td>
									<td>
									<span class="text" _ubtip="ent_toatl">总访问次数</span><br/>
									<span class="value" id="sum_totalcnt">-</span>
									</td>
									<td>
									<span class="text" _ubtip="ent_dayvis">日均访问次数</span><br/>
									<span class="value" id="sum_dayavgcnt">-</span>
									</td>
								</tr>
								</table> 						
							</div>
						</td>
					</tr>
				
				<tr style="background:white;">
					<td valign="top"><!-- body -->
					
					<div style="margin:10px;min-height:600px;background-color:white;">
						<div class="ubtab">
							<ul>
								<li class="ubtabselect"><a href="#" onclick="tabClick('overall')" _tabid="overall"> 总体访问情况</a></li>
								<li><a href="#" onclick="tabClick('ent')" _tabid="ent"> 各个企业访问情况 </a></li>
								<li><a href="#" onclick="tabClick('srv')" _tabid="srv"> 服务访问情况 </a></li>	
							</ul>
							<div class="ubtabcontent">
								<!-- tab内容 -->
								<!-- 总体访问情况 -->
								<div id="tab_content_overall" style="width:100%;height:100%;margin-top:5px;">
									<div id="n_indicator" style="height:30px;width:100%;margin:10px 10px 10px 10px;">
										<div class="chartlegent"><span>节假日</span></div>
										<div id="indicator_container"></div>
									</div>						
									<div style="text-align:center;width:100%;">
										<div id="n_chart_overall"></div>
									</div>
									<div style="margin:5px;border:0px solid #cccccc;">
										<div style="height:30px;background:#eeeeee;border-left:1px solid #cccccc;border-top:1px solid #cccccc;border-right:1px solid #cccccc;margin-bottom:0px;line-height:30px;font-weight:bold;padding-left:10px;">访问情况记录表</div>		
										<div style="margin:0px;">				
									  	<table id="vlist_overall" width="100%"  cellspacing="0">
			                          	</table>
			                          	</div>
									</div>					
								</div>
								<!-- 各个企业访问情况 -->					
								<div id="tab_content_ent" style="width:100%;height:100%;margin-top:5px;display:none;">							
									<div style="margin:5px;">				
									  	<table id="vlist_ent" width="100%"  cellspacing="0">
			                          	</table>
			                        </div>
								</div>
								<!-- 服务访问情况 -->
								<div id="tab_content_srv" style="width:100%;height:100%;margin-top:5px;display:none;">							
														
									<div style="text-align:center;width:100%;">
										<div id="n_chart_srv"></div>
									</div>
								<div style="margin:5px;border:0px solid #cccccc;">
									<div style="height:30px;background:#eeeeee;border-left:1px solid #cccccc;border-right:1px solid #cccccc;border-top:1px solid #cccccc;margin-bottom:0px;line-height:30px;font-weight:bold;padding-left:10px;">服务访问详情</div>		
									<div style="margin:0px;">				
								  	<table id="vlist_srv" width="100%"  cellspacing="0">
		                          	</table>
		                          	</div>
								</div>
						</div>
							
							</div>
						</div>
						
						
					
					</div>
					
					
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
</s:form>
<script type="text/html" id="tmp_chart_nodata">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:[[height]]px;width:[[width]]px;color:rgb(234,118,79);line-height:[[height]]px;border:1px solid #ccc;">图表无数据！</div>
</script>
<script type="text/html" id="tmp_chart_loading">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height[[height]]px;width:[[width]]px;color:rgb(72,190,244);line-height:[[height]]px;border:1px solid #ccc;">正在加载图表数据...</div>
</script>

<%@include file="../../common/copyright.jsp"%>
</body>
</html>