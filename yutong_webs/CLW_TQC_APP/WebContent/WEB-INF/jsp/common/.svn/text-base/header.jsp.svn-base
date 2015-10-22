<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div id="navphotos_value" style="display:none;">
<s:property value="#session.themeProfile.navphotos" />
</div>
<div id="header" style="background: url(showPhoto.shtml?id=<s:property value="#session.themeProfile.userId" />&type=headerclass) repeat-x left top; height: 80px;  min-width: 1256px; width:100%;">

<div style="background: url(showPhoto.shtml?id=<s:property value="#session.themeProfile.userId" />&type=headbgclass) no-repeat left top; float: left; height: 80px;width:100%;" style="position: relative;" >
	
	<div class="logo"><img style="margin: 2px 0px 0px 0px;" src="showPhoto.shtml?id=<s:property value="#session.themeProfile.userId" />&type=companylogo" /></div>
	<div class="userInfo">
	    <nobr>
		    <strong style="color:#000;"> 
			    <s:if test="#session.adminProfile.userName.length()>8">
				    <span id="username10" title='<s:property value="#session.adminProfile.userName" />'>
				        <s:property value="#session.adminProfile.userName.substring(0,8)" />...
				    </span>
			    </s:if>
			    <s:else>
				    <s:property value="#session.adminProfile.userName" />
			    </s:else> 
		    </strong>
	    </nobr>
	    <p class="userLink">
	    
	    <s:if test="#session.public_user == 'tqc'">
	    	<a href="<s:url value='/logoutBiaoZhunHua.shtml' />">注销</a>
	    </s:if><s:else>
	    <s:if test="#session.adminProfile.en_code==600010">
		<a href="<s:url value='/logoutJyd.shtml' />">注销</a>
		</s:if><s:elseif test="#session.adminProfile.en_code==850260">
		<a href="<s:url value='/logoutTa.shtml' />">注销</a>
		</s:elseif><s:elseif test="#session.adminProfile.en_code==600001">
		<a href="<s:url value='/logout.shtml' />">注销</a>
		</s:elseif>
		</s:else> | <a href="<s:url value='/usm/modifyPersonalinitAction.shtml' />">设置</a> | 
		<a onclick="javascript:aboutbrwose();">帮助 </a></p>
		
		<%-- <s:else>
		<a href="<s:url value='/logoutBiaoZhunHua.shtml' />">注销</a>
		</s:else> --%>
	</div>		
	<div class="navigation">
		<ul>
			<s:if test="#session.perUrlList.contains('111_3_5')">
			<li>
				<a id="mi5" href="javascript:void(0)" class="mk-menubar-item" data-options="menu: '#mm5'">设置中心</a>
			</li>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4')">
			<li>
				<a id="mi4" href="javascript:void(0)" class="mk-menubar-item" data-options="menu: '#mm4'">统计中心</a>
			</li>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3')">
			<li>
				<a id="mi3" href="javascript:void(0)" class="mk-menubar-item" data-options="menu: '#mm3'">调度中心</a>
			</li>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_2')">
			<li>
				<a id="mi2" href="javascript:void(0)" class="mk-menubar-item" data-options="menu: '#mm2'">油量监控</a>
			</li>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_1')">
			<li>
				<a id="mi1" href="javascript:void(0)" onclick="gpsmenu()" class="mk-menubar-item">车辆监控</a>
			</li>
			</s:if>						
		</ul>
	</div>
</div>

<div style="display: none">
	<div id="mm2" style="width: 140px;">
		<iframe class="menu_iframe"></iframe>
		<s:if test="#session.perUrlList.contains('111_3_2_1')">
		<div class="titleloadname" onclick="menuurlto('<s:url value='/ftlyInfoNew/FtlyInfoList.shtml' />');" style="background: url(../images/menu_ico/yljk-ssyl.png) no-repeat left 2px;">
			<a href="javascript:void(0);">实时油量</a>
		</div>
		</s:if>
		<s:if test="#session.perUrlList.contains('111_3_2_2')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/ftlyInfoNew/initOilWearList.shtml' />');" style="background: url(../images/menu_ico/yljk-yltj.png) no-repeat left 2px;">
				<a href="javascript:void(0);">油量统计</a>
			</div>
		</s:if>
		<s:if test="#session.perUrlList.contains('111_3_2_3')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/ftlyInfoNew/stealOilSms.shtml' />');" style="background: url(../images/menu_ico/yljk-tydx.png) no-repeat left 2px;">
				<a href="javascript:void(0);">油量短信设置</a>
			</div>
		</s:if>
	</div>
	<div id="mm3" style="width: 120px;">
		<iframe class="menu_iframe"></iframe>
			<s:if test="#session.perUrlList.contains('111_3_3_1')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/dispatchroutechart/ready.shtml' />');" style="background: url(../images/menu_ico/1-1.gif) no-repeat left 2px;">
				<a href="javascript:void(0);">线路调度</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_2')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/dispatchroutechart/routecarready.shtml' />');" style="background: url(../images/menu_ico/ddzx-cldd.png) no-repeat left 2px;">
				<a href="javascript:void(0);">车辆调度</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_3')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/dispatchroutechart/routecarinfoready.shtml' />');" style="background: url(../images/menu_ico/ddzx-ddxxcx.png) no-repeat left 2px;">
				<a href="javascript:void(0);">调度信息查询</a>
			</div>
			</s:if>
			
			<s:if test="#session.perUrlList.contains('111_3_3_4')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/tripPatch/tripPatchManage.shtml' />');" style="background: url(../images/menu_ico/vpatch.png) no-repeat left 2px;">
				<a href="javascript:void(0);">临时派车补录</a>
			</div>
			</s:if>
			
	</div>
	<div id="mm4" style="width: 120px;">
		<iframe class="menu_iframe"></iframe>
			<s:if test="#session.perUrlList.contains('111_3_4_1')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/oilused/oilused.shtml' />');" style="background: url(../images/menu_ico/tjzx-yhylctj.png) no-repeat left 2px;">
				<a href="javascript:void(0);">油耗与里程统计</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_6')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/oilusedNew/oilused.shtml' />');" style="background: url(../images/menu_ico/tjzx-yhylctj.png) no-repeat left 2px;">
				<a href="javascript:void(0);">油耗与里程统计</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_95')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/oilusedEcu/oilused.shtml' />');" style="background: url(../images/menu_ico/tjzx-yhylctj.png) no-repeat left 2px;">
				<a href="javascript:void(0);">油耗与里程统计</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_2')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/averagefuelranking/ranking.shtml' />');" style="background: url(../images/menu_ico/tjzx-fcykltj.png) no-repeat left 2px;">
				<a href="javascript:void(0);">发车统计</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_3')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/humanbaddrv/baddrive.shtml' />');" style="background: url(../images/menu_ico/tjzx-cstj.png) no-repeat left 2px;">
				<a href="javascript:void(0);">超速统计</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_4')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/busalarm/newmorealarm.shtml' />');" style="background: url(../images/menu_ico/tjzx-gjgl.png) no-repeat left 2px;">
				<a href="javascript:void(0);">告警管理</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_5')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/photomonitor/initphoto.shtml' />');" style="background: url(../images/menu_ico/photo.png) no-repeat left 2px;">
				<a href="javascript:void(0);">历史照片管理</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_8')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/driverecord/init.shtml' />');" style="background: url(../images/menu_ico/1-2.gif) no-repeat left 2px;">
				<a href="javascript:void(0);">驾驶员刷卡管理</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_7')">
				<div class="titleloadname" onclick="menuurlto('<s:url value='/stride/ready.shtml' />');" style="background: url(../images/menu_ico/5-2.png) no-repeat left 2px;">
					<a href="javascript:void(0);">员工刷卡记录</a>
				</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_9')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/runStatistic/stcheckPage.shtml' />');" style="background: url(../images/menu_ico/emp-readcard.png) no-repeat left 2px;">
				<a href="javascript:void(0);">员工刷卡记录</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_94')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/daydriverecord/daydriverecord.shtml' />');" style="background: url(../images/menu_ico/tjzx-xcjl.gif) no-repeat left 2px;">
				<a href="javascript:void(0);">行车记录</a>
			</div>
			</s:if>	
						
			<s:if test="#session.perUrlList.contains('111_3_4_90')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/checking/vehicleCheckManage.shtml' />');" style="background: url(../images/menu_ico/vcheck.png) no-repeat left 2px;">
				<a href="javascript:void(0);">公车私用巡检</a>
			</div>
			</s:if>
			
			<s:if test="#session.perUrlList.contains('111_3_4_91')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/averagefuelranking/passengerStat.shtml' />');" style="background: url(../images/menu_ico/tjzx-kltj.png) no-repeat left 2px;">
				<a href="javascript:void(0);">客流统计</a>
			</div>
			</s:if>
			
			<s:if test="#session.perUrlList.contains('111_3_4_92')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/runStatistic/runStatisticManage.shtml' />');" style="background: url(../images/menu_ico/runstatistic.png) no-repeat left 2px;">
				<a href="javascript:void(0);">运营统计</a>
			</div>
			</s:if>
			
			<s:if test="#session.perUrlList.contains('111_3_4_93')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/averagefuelranking/repare.shtml' />');" style="background: url(../images/menu_ico/tjzx-wbgl.png) no-repeat left 2px;">
				<a href="javascript:void(0);">维保管理</a>
			</div>
			</s:if>
	</div>
	<div id="mm5" style="width: 140px;"><iframe class="menu_iframe"></iframe>
			<s:if test="#session.perUrlList.contains('111_3_5_2')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/station/stationmanage.shtml' />');" style="background: url(../images/menu_ico/szzx-zdgl.png) no-repeat left 2px;">
				<a href="javascript:void(0);">站点管理</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_3')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/route/routemanage.shtml' />');" style="background: url(../images/menu_ico/szzx-xlgl.png) no-repeat left 2px;">
				<a href="javascript:void(0);">线路管理</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_15')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/route/drawroutem.shtml' />');" style="background: url(../images/menu_ico/2-4.gif) no-repeat left 2px;">
				<a href="javascript:void(0);">路线采集</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_4')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/routechart/readyrc.shtml' />');" style="background: url(../images/menu_ico/szzx-fcap.png) no-repeat left 2px;border-bottom:#999 1px solid;">
				<a href="javascript:void(0);">发车安排</a>
				<a href="javascript:void(0);" style="background: url(../images/wline.gif) no-repeat left 2px;height:3px;width:100%;">
				</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_1')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/vehicle/vehiclemanage.shtml' />');" style="background: url(../images/menu_ico/szzx-clgl.png) no-repeat left 2px;">
				<a href="javascript:void(0);">车辆管理</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_12')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/employee/employeemanage.shtml' />');" style="background: url(../images/menu_ico/5-1.png) no-repeat left 2px;">
				<a href="javascript:void(0);">员工信息</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_5')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/driver/drivermanage.shtml' />');" style="background: url(../images/menu_ico/szzx-sjgl.png) no-repeat left 2px;">
				<a href="javascript:void(0);">驾驶员管理</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_10')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/vacation/init.shtml' />');" style="background: url(../images/menu_ico/szzx-jqsz.png) no-repeat left 2px;border-bottom:#999 1px solid;">
				<a href="javascript:void(0);">假期设置</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_11')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/zdnew/init.shtml' />');" style="background: url(../images/menu_ico/4-5.gif) no-repeat left 2px;">
				<a href="javascript:void(0);">业务参数设置</a>
			</div>
			<s:if test="#session.perUrlList.contains('111_3_5_19')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/runtimeSet/runtimeManage.shtml' />');" style="background: url(../images/menu_ico/szzx-fcap.png) no-repeat left 2px;">
				<a href="javascript:void(0);">车辆运行时间</a>
			</div>
			</s:if>
			
			
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_13')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/sit/stationmanage.shtml' />');" style="background: url(../images/menu_ico/szzx-jysz.png) no-repeat left 2px;">
				<a href="javascript:void(0);">加油设置</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_6')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/checkOilSet/checkOilSetInit.shtml' />');" style="background: url(../images/menu_ico/szzx-khyhsz.png) no-repeat left 2px;border-bottom:#999 1px solid;">
			<a href="javascript:void(0);">考核油耗设置</a>					
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_7')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/enti/entimanage.shtml' />');" style="background: url(../images/menu_ico/szzx-zzjg.png) no-repeat left 2px;">
				<a href="javascript:void(0);">组织结构</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_8')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/usm/usermanageAction.shtml' />');" style="background: url(../images/menu_ico/szzx-yhgl.png) no-repeat left 2px;">
				<a href="javascript:void(0);">用户管理</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_9')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/rm/roleAction.shtml' />');" style="background: url(../images/menu_ico/szzx-jsgl.png) no-repeat left 2px;border-bottom:#999 1px solid;">
				<a href="javascript:void(0);">角色管理</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_14')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/infomanage/showRidingplan.shtml'/>');" style="background: url(../images/menu_ico/szzx-ccgh.gif) no-repeat left 2px;">
				<a href="javascript:void(0);">乘车规划</a>
			</div>		
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_16')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/area/areaReady.shtml' />');" style="background: url(../images/menu_ico/szzx-jqsz.png) no-repeat left 2px;">
				<a href="javascript:void(0);">区域设置</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_17')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/notice/noticeManage.shtml' />');" style="background: url(../images/menu_ico/gggl.png) no-repeat left 2px;">
				<a href="javascript:void(0);">公告管理</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_18')">
			<div class="titleloadname" onclick="menuurlto('<s:url value='/feedback/readyPage.shtml' />');" style="background: url(../images/menu_ico/wtfk.png) no-repeat left 2px;">
				<a href="javascript:void(0);">问题反馈</a>
			</div>
			</s:if>
			<%-- 在后台导航中出现
			<div class="titleloadname" onclick="menuurlto('<s:url value='/picturefileupload/apkfileUploadInit.shtml' />');" style="background: url(../images/menu_ico/wtfk.png) no-repeat left 2px;">
				<a href="javascript:void(0);">文件上传</a>
			</div>
			<div class="titleloadname" onclick="menuurlto('<s:url value='/paramset/paramsetManage.shtml' />');" style="background: url(../images/menu_ico/wtfk.png) no-repeat left 2px;">
				<a href="javascript:void(0);">参数设置</a>
			</div>
			 --%>
		</div>
	</div>
</div>
 