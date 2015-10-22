<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<title><s:text name="menu.xj" />&nbsp;|&nbsp;<s:text
	name="drivingrecords.info" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />

<link rel="stylesheet" href="<s:url value='/styles/nyroModal.css' />"
	type="text/css" media="screen" />
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/md5/base64.js' />"></script>	
<style type="text/css">
.searchPlan {
	float: left;
	width: 250px;
}

#searchPlanId {
	display: none;
}
</style>

</head>
<body>
<%@include file="../common/menu.jsp"%>
<s:form id="drivingRecords_form" action="" method="post">
	<!-- 组织机构及数据区 -->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top"><!-- 标题:行车记录查询 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top" background="../images/tree_tabBg.gif">
					<div class="toolbar">
					<div class="contentTil"><s:text name="drivingrecords.info" /></div>
					</div>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td class="reportOnline2"><!-- 查询条件 -->
			         <s:if test="#session.perUrlList.contains('111_0_5_6_1')">			
					<table width="100%" border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td width="200" class="hline">
							<table width="100%" border="0" cellspacing="4" cellpadding="0">
								<tr>
									<td>车牌号:</td>
									<td><s:hidden id="vehicle_vin"
										name="drivingRecords.vehicle_vin"></s:hidden> <s:textfield
										id="vehicle_ln" name="drivingRecords.vehicle_ln"
										cssClass="input80" maxlength="30" title="请选择车牌号"
										cssStyle="background:#f2f2f2;" ondblclick="choiceCarln();"
										readonly="true" /> <a href="#"><img
										src="../images/gif-0707.gif" border="0"
										onClick="choiceCarln();" title="查询车牌号" /></a></td>
								</tr>
							</table>
							</td>
							<td>
							<table border="0" cellspacing="4" cellpadding="0">
								<tr>
									<td align="left"><s:text name="common.type" />: <s:select
										id="typeId" name="drivingRecords.typeId"
										list="#request.typeIdMap" listKey="key" listValue="value"
										theme="simple"></s:select> &nbsp;&nbsp; <s:text
										name="common.startendtime" />: <input readonly="readonly"
										size="24" id="start_time" name="drivingRecords.start_time"
										class="Wdate" type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,maxDate:'#F{$dp.$D(\'end_time\')}',onpicked:pickedstarttime})"/>
									<input type="hidden" id="nullEle" /> <s:text name="common.zhi" />
									<input readonly="readonly" size="24" id="end_time"
										name="drivingRecords.end_time" class="Wdate" type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,minDate:'#F{$dp.$D(\'start_time\')}',onpicked:pickedendtime})"/>
									</td>
									<td><a href="#" onClick="javascript:searchList();"
										class="sbutton"><s:text name="btn.query" /></a></td>
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
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="reportOnline">
						<tr>
							<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="titleStyle"><!-- 数据列表上方的标题行(栏) -->
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="30%" class="titleStyleZi">行车记录列表</td>
											<td width="70%">
											<s:if test="#session.perUrlList.contains('111_0_5_6_2')">
											<div class="buhuanhangbut"><a href="#"
												class="btnDaochu" onclick="exportRecords()" title="<s:text name='button.daochu' />"></a></div>
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
							<td class="reportInline">
							<table id="gala" width="100%" cellspacing="0">
								<tr id='noAnyData'>
									<td id="noAnyDataTd" height='246' valign="middle"
										align="center">请您选择查询条件!</td>
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
	<script type="text/javascript">
	( function($) {
		// 预缓存皮肤，数组第一个为默认皮肤
		$.dialog.defaults.skin = [ 'aero' ];
	})(art);

    /**      
			* 对Date的扩展，将 Date 转化为指定格式的String      
			* 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符      
			* 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)      
			* eg:      
			* (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423      
			* (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
			* (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
			* (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
			* (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
			*/        
			Date.prototype.pattern=function(fmt) {         
			    var o = {         
			    "M+" : this.getMonth()+1, //月份         
			    "d+" : this.getDate(), //日         
			    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
			    "H+" : this.getHours(), //小时         
			    "m+" : this.getMinutes(), //分         
			    "s+" : this.getSeconds(), //秒         
			    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
			    "S" : this.getMilliseconds() //毫秒         
			    };         
			    var week = {         
			    "0" : "\u65e5",         
			    "1" : "\u4e00",         
			    "2" : "\u4e8c",         
			    "3" : "\u4e09",         
			    "4" : "\u56db",         
			    "5" : "\u4e94",         
			    "6" : "\u516d"        
			    };         
			    if(/(y+)/.test(fmt)){         
			        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
			    }         
			    if(/(E+)/.test(fmt)){         
			        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);         
			    }         
			    for(var k in o){         
			        if(new RegExp("("+ k +")").test(fmt)){         
			            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
			        }         
			    }         
			    return fmt;         
			}
			function pickedstarttime(){
			    var start_time=document.getElementById('start_time').value.replace(/-/g,"/");
		        var end_time=document.getElementById('end_time').value.replace(/-/g,"/");
		        
				var startdate=new Date(start_time);
				var enddate=new Date(end_time);
				var iHours = parseInt((enddate - startdate) / 1000 / 60 / 60);
				
				if(iHours < 0 || iHours > 2){
					enddate.setTime(startdate.getTime() + 1000*60*60*2);
				}
				var str1 = startdate.pattern("yyyy/MM/dd");
				var str2 = enddate.pattern("yyyy/MM/dd");
				if(str1 != str2){
					enddate = new Date(str1+" 23:59:59");
				}
				$dp.$('end_time').value=enddate.pattern("yyyy-MM-dd HH:mm:ss") ;
			}
			function pickedendtime(){
			    var start_time=document.getElementById('start_time').value.replace(/-/g,"/");
		        var end_time=document.getElementById('end_time').value.replace(/-/g,"/");

				var startdate=new Date(start_time);
				var enddate=new Date(end_time);
				var iHours = parseInt((enddate - startdate) / 1000 / 60 / 60);

				if(iHours < 0 || iHours > 2){
					startdate.setTime(enddate.getTime() - 1000*60*60*2);
				}
				var str1 = startdate.pattern("yyyy/MM/dd");
				var str2 = enddate.pattern("yyyy/MM/dd");
				if(str1 != str2){
					startdate = new Date(str2+" 00:00:00");
				}
				$dp.$('start_time').value=startdate.pattern("yyyy-MM-dd HH:mm:ss") ;
			} 
	//查询条件:车牌号(查询车牌号)
	function choiceCarln() {
		art.dialog.open(
				"<s:url value='/xj/vehListSearch.shtml' />", {
					title : "车辆信息",
					lock : true,
					width : 700,
					height : 435
				});
	}

	function choiceCar(longitude, latitude, vehicle_ln) {
		art.dialog.open(
				"<s:url value='/cl/gpsbrowse.shtml' />?vehicleRegisterInfo.longitude="
						+ longitude + "&vehicleRegisterInfo.latitude=" + latitude
						+ "&vehicleRegisterInfo.vehicleLn=" + base64encode(utf16to8(jQuery('#vehicle_ln').val())), 
				{
					title : '位置显示',
					skin : 'aero',
					limit : true,
					lock : true,
					drag : true,
					fixed : false,
					width : 700,
					height : 370
				});
	}
	
	function choiceCar2(longitude, latitude, vehicle_vin, alarm_id,direction,color) {
	art.dialog.open("<s:url value='/xj/gpsbrowse.shtml' />?alarm.color="+ color + "&alarm.longitude="+ longitude + "&alarm.latitude=" + latitude + "&alarm.vehicle_vin=" + vehicle_vin + "&alarm.alarm_id="+ alarm_id + "&alarm.direction="+ direction +"&rad="+Math.random(),
			{
				title: '位置显示',
				skin: 'aero',
				limit: true,
				lock: true,
				drag: true,
				fixed: false,
				width :900,
				height:370
			});
}

	//function reWriteLink(tdDiv, pid) {
	//	var longitude = get_cell_text(pid, 2);
	//	var latitude = get_cell_text(pid, 3);
	//	var vehicle_vin = get_cell_text(pid, 7);
	//	var vehicle_ln = get_cell_text(pid, 1);

	//	if(get_cell_text(pid, 2).length > 1 && get_cell_text(pid, 3).length > 1){
	//		tdDiv.innerHTML = '<a href="javascript:choiceCar(\'' + longitude
	//		+ '\',\'' + latitude + '\')">' + '位置' + '</a>';
	//	} else {
	//		tdDiv.innerHTML = '--';
	//	}
	//}
	
	function reWriteLink(tdDiv,pid){
			var longitude = get_cell_text(pid, 2);
			var latitude = get_cell_text(pid, 3);
			var vehicle_vin = get_cell_text(pid, 7);
			var alarm_id = '';//get_cell_text(pid, 12);
			var direction = get_cell_text(pid, 8);
			var color = get_cell_text(pid, 9);			
			if(longitude==" "&&latitude==" "){
				tdDiv.innerHTML='--';
				}
			else{
			tdDiv.innerHTML = '<a href="javascript:choiceCar2(\'' + longitude + '\',\''+latitude+ '\',\''+vehicle_vin + '\',\''+alarm_id+ '\',\'' + direction + '\',\'' + color + '\')">' + '位置' +'</a>';
			}
		}

	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}

	//jquery flex那段代码
	jQuery( function() {
		var gala = jQuery('#gala'), urlPara = "";
		//加点其它业务,回填写时间
		jQuery('#start_time').attr("value", "${drivingRecords.start_time}");
		jQuery('#end_time').attr("value", "${drivingRecords.end_time}");
		//车牌号或者类型不为空时查询,否则不查询
		if ("${drivingRecords.typeId}" == ""
				|| "${drivingRecords.vehicle_ln}" == "") {

			//GPS信息
		} else {
			jQuery("#noAnyData").remove();
			urlPara = '?drivingRecords.vehicle_ln='
					+ encodeURI(encodeURI('${drivingRecords.vehicle_ln}'))
					+ '&drivingRecords.start_time=${drivingRecords.start_time}&drivingRecords.end_time=${drivingRecords.end_time}&drivingRecords.typeId=${drivingRecords.typeId}&drivingRecords.timeScale='
					+ encodeURI(encodeURI('${drivingRecords.timeScale}'))
					+ '&drivingRecords.vehicle_vin=${drivingRecords.vehicle_vin}'
					+ '&yourTimeScale=${yourTimeScale}&timestamp=' + new Date();
			//t01-t23参考Type.java
			if ("${drivingRecords.typeId}" == "t01") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ 
							{display : '时间',name : 'terminal_time',width : 128,sortable : true,align : 'left'}, 
							{display : '数据接收时间',name : 'create_time',width : 128,sortable : false,align : 'left'}, 
							{display : '经度',name : 'longitude',width : 120,sortable : false,align : 'left'}, 
						    {display : '纬度',name : 'latitude',width : 120,sortable : false,align : 'left'}, 
							{display : '方向',name : 'direction',width : 100,sortable : false,align : 'left'}, 
							{display : 'GPS速度(km/h)',name : 'gps_speeding',width : 100,sortable : false,align : 'left'}, 
							{display : '定位',name : '',width : 100,sortable : false,align : 'center',process : reWriteLink},
							{display : 'VIN',name : 'vehicle_vin',width : 130,hide : true},
							{display : '方向(原始值)',name : 'direction',width : 120,hide : true},
							{display: '颜色' ,name : 'color',width : 120,hide:true,sortable : false,toggle:false} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						// 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
						//getQuery :getQuery
						});
				//速度
			} else if ("${drivingRecords.typeId}" == "t02") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'create_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '车速(km/h)',
								name : 'speeding',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '转速(rpm)',
								name : 'engine_rotate_speed',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '里程(km)',
								name : 'mileage',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//转速
			} else if ("${drivingRecords.typeId}" == "t03") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'create_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '转速(rpm)',
								name : 'engine_rotate_speed',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '车速(km/h)',
								name : 'speeding',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '里程(km)',
								name : 'mileage',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//里程信息
			} else if ("${drivingRecords.typeId}" == "t04") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'create_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '里程(km)',
								name : 'mileage',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '转速(rpm)',
								name : 'engine_rotate_speed',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '车速(km/h)',
								name : 'speeding',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//开关量信息
			} else if ("${drivingRecords.typeId}" == "t05") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'create_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : 'ABS工作',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : 'SOS开关',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '点火',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '油压报警',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '制动气压报警',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '雾灯',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '远光灯信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '右转向灯信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '喇叭信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '左转向灯信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '倒档信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '近光灯信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '制动状态',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '严重故障',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '水位低报警',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '制动蹄片磨损报警',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '空滤堵塞报警',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '燃油警告',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '空调状态',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '空挡信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '前门信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '中门信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '缓速器工作',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '加热器工作',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '离合器状态',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '缓速器高温报警信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '仓温报警信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '机滤堵塞信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '燃油堵塞信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '机油温度报警信号',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '开关量31',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '开关量32',
								name : 'on_off',
								width : 40,
								sortable : false,
								align : 'left',
								hide : false
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-发动机转速
			} else if ("${drivingRecords.typeId}" == "t06") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '发动机转速(rpm)',
								name : 'engine_rotate_speed',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-扭矩
			} else if ("${drivingRecords.typeId}" == "t07") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '转矩-原始值',
								name : 'torque',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '转矩-转换值(%)',
								name : 'torque',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-锂电池电压
			} else if ("${drivingRecords.typeId}" == "t08") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'create_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '电池电压(v)',
								name : 'battery_voltage',
								width : 160,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-蓄电池电压
			} else if ("${drivingRecords.typeId}" == "t09") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'create_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '电池电压(v)',
								name : 'ext_voltage',
								width : 160,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-供电状态
			} else if ("${drivingRecords.typeId}" == "t10") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'create_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '供电状态',
								name : 'power_state',
								width : 120,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-发动机油温
			} else if ("${drivingRecords.typeId}" == "t11") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '机油温度(<sup>o</sup>C)',
								name : 'oil_temperature',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-发动机水温
			} else if ("${drivingRecords.typeId}" == "t12") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'create_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '发动机水温(<sup>o</sup>C)',
								name : 'e_water_temp',
								width : 160,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-机油压力
			} else if ("${drivingRecords.typeId}" == "t13") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'create_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '机油压力(kPa)',
								name : 'oil_pressure',
								width : 160,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-大气压力
			} else if ("${drivingRecords.typeId}" == "t14") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '大气压力(kPa)',
								name : 'air_pressure',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-进气温度
			} else if ("${drivingRecords.typeId}" == "t15") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '进气温度(<sup>o</sup>C)',
								name : 'air_inflow_tpr',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-车速
			} else if ("${drivingRecords.typeId}" == "t16") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '车速(km/h)',
								name : 'vehicle_speed',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-冷却剂温度
			} else if ("${drivingRecords.typeId}" == "t17") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '冷却剂温度(<sup>o</sup>C)',
								name : 'colder_temperature',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-累计油耗
			} else if ("${drivingRecords.typeId}" == "t18") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '累计油耗(L)',
								name : 'oil_total',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-发动机运行时间
			} else if ("${drivingRecords.typeId}" == "t19") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '发动机运行时间(小时)',
								name : 'enghrrev_t_e_h',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//CAN-瞬时油耗
			} else if ("${drivingRecords.typeId}" == "t20") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '瞬时油耗(L/h)',
								name : 'oil_instant',
								width : 128,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//Extend-急减速
			} else if ("${drivingRecords.typeId}" == "t21") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '持续时间(秒)',
								name : 'alarm_time',
								width : 240,
								sortable : false,
								align : 'left',
								hide : false
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//Extend-急加速
			} else if ("${drivingRecords.typeId}" == "t22") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '持续时间(秒)',
								name : 'alarm_time',
								width : 240,
								sortable : false,
								align : 'left',
								hide : false
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
				//Extend-超速
			} else if ("${drivingRecords.typeId}" == "t23") {
				gala
						.flexigrid( {
							url : '<s:url value="/xjf/gpsInfoList.shtml" />' + urlPara,
							dataType : 'json',
							height : '188',
							width : '980',
							colModel : [ {
								display : '时间',
								name : 'terminal_time',
								width : 128,
								sortable : true,
								align : 'left'
							}, {
								display : '数据接收时间',
								name : 'write_time',
								width : 128,
								sortable : false,
								align : 'left'
							}, {
								display : '持续时间(秒)',
								name : 'alarm_time',
								width : 240,
								sortable : false,
								align : 'left',
								hide : false
							}, {
								display : '最高车速(km/h)',
								name : 'maxspeed',
								width : 120,
								sortable : false,
								align : 'left'
							} ],
							sortname : 'terminal_time',
							sortorder : 'desc',
							sortable : true,
							striped : true,
							usepager : true,
							resizable : false,
							useRp : true,
							rp : 20,

							rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
							showTableToggleBtn : true
						});
			}
		}
	});
	//页面自适应
	( function(jQuery) {
		jQuery(window).resize( function() {
			testWidthHeight();
		});
		jQuery(window).load( function() {
			testWidthHeight();
			if (jQuery("#gala").text() != '') {
				jQuery("#noAnyDataTd").height(get_page_height() - 291);
			}
		});

	})(jQuery);
	//获取页面高度
	function get_page_height() {
		var height = 0;
		if (jQuery.browser.msie) {
			height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight
					: document.body.clientHeight;
		} else {
			height = self.innerHeight;
		}
		return height;
	}
	//获取页面宽度
	function get_page_width() {
		var width = 0;
		if (jQuery.browser.msie) {
			width = document.compatMode == "CSS1Compat" ? document.documentElement.clientWidth
					: document.body.clientWidth;
		} else {
			width = self.innerWidth;
		}
		return width;
	}
	//计算控件宽高
	function testWidthHeight() {
		var h = get_page_height();
		var w = get_page_width();
		jQuery(".flexigrid").width(w - 50);
		//jQuery("#reportTab").height(h-100);
		jQuery(".bDiv").height(h - 350);
	}
	//单击xxx时段的时候为
	function yourSelect(value) {
		jQuery("#yourTimeScale").attr("value", value);
	}
	function searchList() {
		if (jQuery("#yourTimeScale").val() == '1') {
			if (jQuery("#start_time").val() == ''
					|| jQuery("#end_time").val() == '') {
				alert('您选择的是[自定义时段],需要选择起止日期!');
				return;
			}
		}
		jQuery("#drivingRecords_form").attr("action", "drivingrecords.shtml");
		jQuery("#drivingRecords_form").submit();
	}
	//导出数据
	function exportRecords() {
		//首先查看车牌号和类型是否为空 ，如果为空，则不能让其导出
		if (jQuery("#vehicle_vin").val() == "") {
			alert("车牌号没有填写");
			return false;
		} else {
			var form = document.getElementById("drivingRecords_form");
			form.action = "<s:url value='/xj/drivingRecords_export.shtml' />";
			form.submit();
		}
	}
</script>
</s:form>
</body>
</html>

