<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<div id="header">
	<div class="headBg" style="position: relative;">
		<div id="backusername10" class="username10">
		    <s:property value="#session.adminProfile.userName" />
		</div>
		<div class="logo">
		    <s:if test="''==#session.adminProfile.img_path || null==#session.adminProfile.img_path">
			    <img src="../images/logo.png" />
		    </s:if> 
		    <s:else>
			    <img src="..<s:property value="#session.adminProfile.img_path" />"/>
		    </s:else>
		</div>
		<div class="userInfo" style="width:350px;">
		    <nobr>
			    <strong> 
				    <s:if test="#session.adminProfile.userName.length()>10">
					    <span id="username10">
					        <s:property value="#session.adminProfile.userName.substring(0,10)" />
					    </span>
				    </s:if>
				    <s:else>
					    <s:property value="#session.adminProfile.userName" />
				    </s:else> 
			    </strong>
		    </nobr>
			<p class="userLink"><a href="<s:url value='/logout.shtml' />">注销</a> | <a href="<s:url value='/usm/modifyPersonalinitAction.shtml' />">设置</a> | 
			<a href="../about/androidDownload.shtml" target="_blank"><img src="../newimages/phone.png" style="vertical-align:text-bottom;"/>手机客户端V1.2<img id="newVersionIconMobile" src="../newimages/newVersionIcon.gif" style="margin-left:4px;vertical-align:text-bottom;display:none;"/></a> | 
			<a href="../ytstatic/newfeature/index.html" target="_blank">2.9功能介绍<img id="newVersionIconPc" src="../newimages/newVersionIcon.gif" style="margin-left:4px;vertical-align:text-bottom;display:none;"/></a> 
			<!-- | <a onclick="openxintexing()" style="color:#ffd200">畅行版新特性<img src="../yutongimage/new.gif"/ style="margin-left: 2px;"></a> --></p>
			
		</div>
		<div class="advertise"><a href="../ytstatic/xccome/index.html" target="_blank"></a></div>
		<!-- 
		<div class="downLink" style="display:none;"><a href="../about/androidDownload.shtml"></a></div>
		<div class="downLink1"><a href="#" onclick="window.open('../ytstatic/newfeature/index.html')"></a></div>
		 -->
		<div class="navigation">
			<ul>
				<s:if test="#session.perUrlList.contains('111_3_5')">
					<li>
						<a id="mi7" href="javascript:void(0)" class="mk-menubar-item nav7" data-options="menu: '#mm4'">系统管理</a>
					</li>
				</s:if>
				 <!-- <s:if test="#session.perUrlList.contains('111_3_8')">
					<li>
						<a id="mi8" href="javascript:void(0)" class="mk-menubar-item nav8" data-options="menu: '#mm6'">数据挖掘</a>
					</li>
				</s:if> -->
				<s:if test="#session.perUrlList.contains('111_3_4')">
					<li>
						<a id="mi6" href="javascript:void(0)" class="mk-menubar-item nav6" data-options="menu: '#mm3'">机务管理</a>
					</li>
				</s:if>
				<s:if test="#session.perUrlList.contains('111_3_3_11')">
					<s:if test="1!=#session.adminProfile.en_mould">
						<li>
							<a id="mi5" href="javascript:void(0)" class="mk-menubar-item nav5" data-options="menu: '#mm5'">基础数据</a>
						</li>
					</s:if>
				</s:if>
				<s:if test="#session.perUrlList.contains('111_3_3')">
					<s:if test="3==#session.adminProfile.en_mould">
						<li>
							<a id="mi4" href="javascript:void(0)" class="mk-menubar-item nav4" data-options="menu: '#mm2'">乘车管理</a>
						</li>
					</s:if>
				</s:if>
				<s:if test="#session.perUrlList.contains('111_3_2')">
					<s:if test="1!=#session.adminProfile.en_mould">
						<li>
							<a id="mi3" href="javascript:void(0)" class="mk-menubar-item nav3" data-options="menu: '#mm1'">运营统计</a>
						</li>
					</s:if>
				</s:if>
				<s:if test="#session.perUrlList.contains('111_3_11')">
					<s:if test="1==#session.adminProfile.en_mould">
						<li>
							<a id="mi9" href="#" class="mk-menubar-item nav9" onclick="alarmmenu()">告警管理</a>
						</li>
					</s:if>
				</s:if>
				<s:if test="#session.perUrlList.contains('111_3_2_1')">
					<s:if test="3==#session.adminProfile.en_mould">
						<li>
							<a id="mi2" onclick="routechartmenu()" href="#" class="mk-menubar-item nav2">线路监控</a>
						</li>
					</s:if>
				</s:if>
				<li>
					<a id="mi1" onclick="gpsmenu()" href="#" class="mk-menubar-item nav1">车辆监控</a>
				</li>
			</ul>
		</div>
	</div>

	<div style="display: none">
		<div id="mm1" style="width: 140px;">
			<iframe class="menu_iframe"></iframe>
			<s:if test="#session.perUrlList.contains('111_3_2_5')">
				<s:if test="3==#session.adminProfile.en_mould">
				<div style="background: url(../images/menu_ico/1-1.gif) no-repeat left 2px;">
					<a href="<s:url value='/carrun/ready.shtml'/>"><s:text name="menu2.clyxrz" /></a>
				</div>
				</s:if>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_2_3')">
				<s:if test="3==#session.adminProfile.en_mould">
				<div style="background: url(../images/menu_ico/1-3.gif) no-repeat left 2px;">
					<a href="<s:url value='/ridealarm/ready.shtml'/>"><s:text name="menu2.yccctj" /></a>
				</div>
				</s:if>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_2_4')">
				<div style="background: url(../images/menu_ico/1-4.gif) no-repeat left 2px;">
					<a href="<s:url value='/humanbaddrv/baddrive.shtml'/>"><s:text name="menu2.weigui" /></a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_2_6')">
				<s:if test="3==#session.adminProfile.en_mould">
					<div style="background: url(../images/menu_ico/1-2.gif) no-repeat left 2px;">
						<a href="<s:url value='/stride/ready.shtml'/>"><s:text name="menu2.xsccjl" /></a>
					</div>
				</s:if>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_2_7')">
				<s:if test="2==#session.adminProfile.en_mould">
					<div style="background: url(../images/menu_ico/4-7.gif) no-repeat left 2px;">
						<a href="<s:url value='/stushuaka/ready.shtml'/>">学生刷卡记录</a>
					</div>
				</s:if>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_2_21')">
				<s:if test="2==#session.adminProfile.en_mould">
				<div style="background: url(../images/menu_ico/driver_record.png) no-repeat left 2px;">
					<a href="<s:url value='/drivershuaka/ready.shtml'/>">驾驶员刷卡记录</a>
				</div>
				</s:if>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_1_3_1')">
				<div style="background: url(../images/menu_ico/message.png) no-repeat left 2px;">
					<a href="<s:url value='/message/initmessage.shtml' />">消息调度记录</a>
				</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_1_2_1')">
				<div style="background: url(../images/menu_ico/photo.png) no-repeat left 2px;">
					<a href="<s:url value='/photomonitor/initphoto.shtml' />">历史照片管理</a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_1_20')">
				<div style="background: url(../images/menu_ico/record.png) no-repeat left 2px;">
					<a href="<s:url value='/overload/newoverloadready.shtml'/>">超载巡检记录</a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_1_12_1')">
				<div style="background: url(../images/menu_ico/alarm.png) no-repeat left 2px;">
					<a href="<s:url value='/busalarm/newmorealarm.shtml'/>?alarm_type_id='40','72'">告警管理</a>
				</div>
			</s:if>
		</div>
		<div id="mm2" style="width: 120px;">
			<iframe class="menu_iframe"></iframe>
			<s:if test="#session.perUrlList.contains('111_3_3_5')">
				<div style="background: url(../images/menu_ico/2-5.gif) no-repeat left 2px;">
					<a href="<s:url value='/station/stationmanage.shtml'/>"><s:text name="menu2.zdpz" /></a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_3_6')">
				<div style="background: url(../images/menu_ico/2-6.gif) no-repeat left 2px;">
					<a href="<s:url value='/route/routemanage.shtml' />"><s:text name="menu2.xlsz" /></a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_3_7')">
				<div style="background: url(../images/menu_ico/2-7.gif) no-repeat left 2px;">
					<a href="<s:url value='/infomanage/ridingplan.shtml'/>"><s:text name="menu2.ccgh" /></a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_3_8')">
				<div style="background: url(../images/menu_ico/2-8.gif) no-repeat left 2px;">
					<a href="<s:url value='/holiday/holidaymanage.shtml'/>"><s:text name="menu2.qxjgl" /></a>
				</div>
			</s:if>
		</div>
		<div id="mm3" style="width: 120px;">
			<iframe class="menu_iframe"></iframe>
			<s:if test="#session.perUrlList.contains('111_3_4_1')">
				<div style="background: url(../images/menu_ico/3-2.gif) no-repeat left 2px;">
					<a href="<s:url value='/vehiclestatus/newvehiclestatus.shtml'/>"><s:text name="menu2.ckjk" /></a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_4_2')">
				<div style="background: url(../images/menu_ico/3-1.gif) no-repeat left 2px;">
					<a href="<s:url value='/oilused/oilused.shtml'/>"><s:text name="menu3.youhaobaogao" /></a>
				</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_3')">
				<div style="background: url(../images/menu_ico/3-3.gif) no-repeat left 2px;">
					<a href="<s:url value='/baddriv/baddriving.shtml'/>"><s:text name="menu2.bljsjl" /></a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_4_5')">
				<div style="background: url(../images/menu_ico/2-4.gif) no-repeat left 2px;">
					<a href="<s:url value='/runoil/ready.shtml'/>"><s:text name="menu2.rxcjl" /></a>
				</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_6')">
			<div style="background: url(../images/menu_ico/1-2.gif) no-repeat left 2px;">
				<a href="<s:url value='/zspt/zsptOil.shtml'/>">实时油量</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_7')">
			<div style="background: url(../images/menu_ico/2-4.gif) no-repeat left 2px;">
				<a href="<s:url value='/zspt/zsptRecord.shtml'/>">油量统计</a>
			</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_8')">
			<div style="background: url(../images/menu_ico/4-8.gif) no-repeat left 2px;">
				<a href="<s:url value='/zspt/zsptSetStation.shtml'/>">加油设置</a>
			</div>
			</s:if>
		</div>
		<div id="mm4" style="width: 140px;"><iframe class="menu_iframe"></iframe>
			<s:if test="#session.perUrlList.contains('111_3_3_4')">
				<div style="background: url(../images/menu_ico/2-4.gif) no-repeat left 2px;">
					<a href="<s:url value='/vehicle/vehiclemanage.shtml' />"><s:text name="menu2.clxx" /></a>
				</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_1')">
				<div style="background: url(../images/menu_ico/4-1.gif) no-repeat left 2px;">
					<a href="<s:url value='/rm/roleAction.shtml' />"><s:text name="menu3.jsgl" /></a>
				</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_2')">
				<div style="background: url(../images/menu_ico/4-2.gif) no-repeat left 2px;">
					<a href="<s:url value='/usm/usermanageAction.shtml' />"><s:text name="menu3.yhgl" /></a>
				</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_3')">
				<div style="background: url(../images/menu_ico/4-3.gif) no-repeat left 2px;">
					<a href="<s:url value='/enti/entimanage.shtml' />"><s:text name="menu2.zzjgsz" /></a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_5_4')">
				<div style="background: url(../images/menu_ico/4-4.gif) no-repeat left 2px;">
					<a href="<s:url value='/logoset/logoset.shtml' />"><s:text name="menu3.qylogo" /></a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_5_5')">
				<div style="background: url(../images/menu_ico/4-5.gif) no-repeat left 2px;">
					<a href="<s:url value='/zdnew/simpleInit.shtml' />"><s:text name="menu2.ywcssz" /></a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_5_6')">
				<div style="background: url(../images/menu_ico/4-6.gif) no-repeat left 2px;">
					<a href="<s:url value='/ock/ocktimeset.shtml' />"><s:text name="menu2.khyhydsz" /></a>
				</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_7')">
				<div style="background: url(../images/menu_ico/4-7.gif) no-repeat left 2px;">
					<a href="<s:url value='/oilset/oilSet.shtml' />"><s:text name="menu2.khyhsz" /></a>
				</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_8')">
			   <s:if test="1!=#session.adminProfile.en_mould">
				<div style="background: url(../images/menu_ico/4-9.png) no-repeat left 2px;">
					<a href="<s:url value='/smssignset/init.shtml' />">短信签名设置</a>
				</div>
			  </s:if>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_9')">
				<div style="background: url(../images/menu_ico/paizhao.png) no-repeat left 2px;">
					<a href="<s:url value='/photograph/init.shtml' />">定时拍照设置</a>
				</div>
			</s:if>			
				<s:if test="#session.perUrlList.contains('111_3_5_10')">
					<s:if test="2==#session.adminProfile.en_mould">
						<div style="background: url(../images/menu_ico/2-8.gif) no-repeat left 2px;">
							<a href="<s:url value='/runtimeset/initBrows.shtml'/>">运行时段设置</a>
						</div>
					</s:if>
				</s:if>
			<s:if test="#session.perUrlList.contains('111_3_5_11')">	
				<div style="background: url(../images/menu_ico/2-9.png) no-repeat left 2px;">
					<a href="<s:url value='/wxannouncement/readyPage.shtml'/>">微信公告管理</a>
				</div>
			</s:if>
		</div>
		<div id="mm5" style="width: 120px;"><iframe class="menu_iframe"></iframe>
			<s:if test="#session.perUrlList.contains('111_3_3_1')">
				<div style="background: url(../images/menu_ico/2-1.gif) no-repeat left 2px;">
					<a href="javascript:studentPasswordConfirm();"><s:text name="menu2.xcxx" /></a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_3_2')">
				<div style="background: url(../images/menu_ico/2-2.gif) no-repeat left 2px;">
					<a href="<s:url value='/driver/drivermanage.shtml' />"><s:text name="menu2.jsyxx" /></a>
				</div>
			</s:if> 
			<s:if test="#session.perUrlList.contains('111_3_3_3')">
				<div style="background: url(../images/menu_ico/2-3.gif) no-repeat left 2px;">
					<a href="<s:url value='/steward/stewardmanage.shtml'/>"><s:text name="menu2.scxx" /></a>
				</div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_10')">
				<s:if test="3==#session.adminProfile.en_mould">
					<div style="background: url(../images/menu_ico/4-8.gif) no-repeat left 2px;">
						<a href="<s:url value='/handmobile/handmobilemanage.shtml' />">手持设备信息</a>
					</div>
				</s:if>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_1_5')">
				<div style="background: url(../images/menu_ico/5-5.gif) no-repeat left 2px;">
					<a href="javascript:messagePasswordConfirm();">短信提醒</a>
				</div>
			</s:if>
		</div>
		<div id="mm6" style="width: 120px;"><iframe class="menu_iframe"></iframe>
				<div style="background: url(../images/menu_ico/8-9.png) no-repeat left 2px;">
					<a href="<s:url value='/energyMotor/toIframe.shtml' />">新能源信息管理</a>
				</div>
				<div style="background: url(../images/menu_ico/8-3.png) no-repeat left 2px;border-bottom:#999 1px solid;">
					<a href="<s:url value='/energyAlarm/energyAlarmBrows.shtml' />">新能源故障</a>
				</div>
				<div style="background: url(../images/menu_ico/8-2.png) no-repeat left 2px;border-bottom:#999 1px solid;">
					<a href="<s:url value='/energyVersion/energyVersionBrows.shtml' />">软硬件版本号</a>
				</div>
				<div style="background: url(../images/menu_ico/8-1.png) no-repeat left 2px;">
					<a href="<s:url value='/energySMS/energySMSBrows.shtml' />">短信提醒</a>
				</div>
		</div>
	</div>
</div>
<script type="text/javascript">
if('<s:property value="#session.isDisplayNewIcon"/>' == '') {
	 jQuery.ajax({
			type: 'POST', 
			//url: '/AlermGrid/getDisplayNewIcon.shtml',
			url: '<s:url value='/newIconDisplay/getDisplayNewIcon.shtml' />',
			async : false,
			data: {},
			success: function(data) {
				var isMobile = data[0].IS_DISPLAY_NEW_ICON;
				if(isMobile == '0') {
					document.getElementById('newVersionIconMobile').style.display = 'none';
				} else if(isMobile == '1') {
					document.getElementById('newVersionIconMobile').style.display = 'inline-block';
				}
				var isPc = data[1].IS_DISPLAY_NEW_ICON;
				if(isPc == '0') {
					document.getElementById('newVersionIconPc').style.display = 'none';
				} else if(isPc == '1') {
					document.getElementById('newVersionIconPc').style.display = 'inline-block';
				}
			}
		});
} else {
	var isMobile = '<s:property value="#session.isDisplayNewIcon[0].IS_DISPLAY_NEW_ICON"/>';
	if(isMobile == '0') {
		document.getElementById('newVersionIconMobile').style.display = 'none';
	} else if(isMobile == '1') {
		document.getElementById('newVersionIconMobile').style.display = 'inline-block';
	}
	var isPc = '<s:property value="#session.isDisplayNewIcon[1].IS_DISPLAY_NEW_ICON"/>';
	if(isPc == '0') {
		document.getElementById('newVersionIconPc').style.display = 'none';
	} else if(isPc == '1') {
		document.getElementById('newVersionIconPc').style.display = 'inline-block';
	}
}
</script>