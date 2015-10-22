<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/badDrive.css" />" />
	<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/flexigrid/flexigrid.css'/>" />
	<link rel="stylesheet" href="<s:url value='/styles/nyroModal.css' />" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
	<style type="text/css">
		.car-status3{
			height:195px;
			min-width:920px;
			margin:0 auto;
		}
	</style>
</head>
<body>
<div class="chartDetail_cent" style="height:483px;">
   	<div class="ridealarm_warn">
	<div id="noDataDiv" style="text-align:center;position: relative; top: 0px; padding:50px 0;  background-color: #fff">
		<img src="<s:url value='/newimages/pic_nodata.png'/>"/>
	</div>
    <div id="zoomPic" style="float:left; position: absolute; top: 20px; left: 20px; z-index: 100;display: none;" class="magnifier_a" onclick="showRideAlarm();" title="点击查看大图">
	</div>
	<div id="3dChart" style="display: none;">
	</div>
	</div>
	         
	<div id="records_bg" style="background:#eee;">
	  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
	  <tr>
	    <td width="10" height="30" align="left" valign="middle" >&nbsp;</td>
	    <td align="left" ><input type="checkbox" id="alarm_all"  checked="checked"  onclick="selectAll();searchList();" /></td>
	    <td align="left">&nbsp;全选&nbsp;&nbsp;&nbsp;</td>

	    <td align="left"><input type="checkbox" name="alarm_type_id_name" value="79" checked="checked" onclick="makeSelectAll();searchList();"/></td>
	    <td align="left">&nbsp;未刷卡上车&nbsp;</td>
	    
	    <td align="left"><input type="checkbox"  name="alarm_type_id_name" value="80" checked="checked" onclick="makeSelectAll();searchList();"/></td>
	    <td align="left">&nbsp;未刷卡下车&nbsp;</td>
	    
	    <td align="left"><input type="checkbox" name="alarm_type_id_name" value="73" checked="checked" onclick="makeSelectAll();searchList();"/></td>
	    <td align="left">&nbsp;未在规定站点上车</td>
	    
	    <td align="left"><input type="checkbox" name="alarm_type_id_name" value="74" checked="checked" onclick="makeSelectAll();searchList();"/></td>
	    <td align="left">&nbsp;未在规定站点下车</td>	    	    	    	    
	  </tr>
	  </table>
  </div>
  <div class="table_list"><table id="detailgrid"></table></div>
</div>

<s:hidden id="vehicle_vin" name="detailObj.VIN"></s:hidden>
<s:hidden id="user_organization_id" name="detailObj.user_organization_id"></s:hidden>
<s:hidden id="time_list" name="detailObj.time_list"></s:hidden>
<s:hidden id="begTime" name="detailObj.begTime"></s:hidden>
<s:hidden id="endTime" name="detailObj.endTime"></s:hidden>
<s:hidden id="vehicle_ln" name="detailObj.vehicle_ln"></s:hidden>
<s:hidden id="no_up_num" name="detailObj.no_up_num"></s:hidden>
<s:hidden id="no_down_num" name="detailObj.no_down_num"></s:hidden>
<s:hidden id="nofix_up_num" name="detailObj.nofix_up_num"></s:hidden>
<s:hidden id="nofix_down_num" name="detailObj.nofix_down_num"></s:hidden>
<s:hidden id="car_state" name="detailObj.car_state"></s:hidden>

<script type='text/javascript' src='../scripts/fusioncharts/FusionCharts.js'></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/form.js' />"></script>
<script type="text/javascript">
		function selectAll(){
            var all = document.getElementById("alarm_all");
            if(all.checked == true){
            	selectAllCheckBox(true,"alarm_type_id_name"); 
            }else{
            	selectAllCheckBox(false,"alarm_type_id_name");  
            }

	    }
		function selectAllCheckBox(state,name) {   
		    var ids = document.getElementsByName(name);
		    for (var i = 0; i < ids.length; i++){          
		            ids[i].checked = state;   
		    }   
		}
	    function makeSelectAll(){
	    	 var ids = document.getElementsByName("alarm_type_id_name");
	    	 var j=0;
			    for (var i = 0; i < ids.length; i++){          
			      if(ids[i].checked == true){
			           j++;
			      }
			 } 
		     if(j==ids.length){
		    	 document.getElementById("alarm_all").checked = true;
		     }else{
		    	 document.getElementById("alarm_all").checked = false;
			 }
		}
  
	    function searchList(){
	    	 var alarm_type_id_list = "";
	    	 var j = 0;
	    	 var ids = document.getElementsByName("alarm_type_id_name");
			    for (var i = 0; i < ids.length; i++){          
			      if(ids[i].checked == true){

				      if(j==0){
			    	      alarm_type_id_list = "'" + alarm_type_id_list+ids[i].value + "'";
			    	      j++;
				      }else{
				    	  alarm_type_id_list = alarm_type_id_list+",'" + ids[i].value + "'";   
				    	  j++;
					  }
			      }
			 } 
		     if(j==0){
		    	 alarm_type_id_list = "'-1'";
			 } 
	    	 jQuery('#detailgrid').flexOptions({
				newp: 1,
				params: [{name: 'detailObj.VIN', value: jQuery('#vehicle_vin').val() },
				         {name: 'detailObj.user_organization_id', value: jQuery('#user_organization_id').val() },
					      {name: 'detailObj.time_list', value: jQuery('#time_list').val()},
					      {name: 'detailObj.begTime', value: jQuery('#begTime').val()},
					      {name: 'detailObj.endTime', value: jQuery('#endTime').val()},
					      {name: 'detailObj.vehicle_ln', value: jQuery('#vehicle_ln').val()},
					      {name: 'detailObj.alarm_type_id',value: alarm_type_id_list},
					      // add by jinp begin
					      {name: 'detailObj.car_state',value: jQuery('#car_state').val()}
					      // add by jinp end
					    ]
			});		
	    	jQuery('#detailgrid').flexReload();
		}

	    window.onload = function() {
			
			var detailgrid = jQuery('#detailgrid');
			detailgrid.flexigrid({
				url: '<s:url value="/ride_alarm/getRideAlarmDetail.shtml" />',
				params: [{name: 'detailObj.VIN', value: jQuery('#vehicle_vin').val() }, 
				         {name: 'detailObj.user_organization_id', value: jQuery('#user_organization_id').val() },
					      {name: 'detailObj.time_list', value: jQuery('#time_list').val()},
					      {name: 'detailObj.begTime', value: jQuery('#begTime').val()},
					      {name: 'detailObj.endTime', value: jQuery('#endTime').val()},
					      {name: 'detailObj.vehicle_ln', value: jQuery('#vehicle_ln').val()},
					      // add by jinp begin
					      {name: 'detailObj.car_state',value: jQuery('#car_state').val()}
					      // add by jinp end
				    ],				
				dataType: 'json',
				height: '145',
				width: '500',
				colModel : [
							{display: '车牌号', name : 'VEHICLE_LN', width : 60, sortable : true, align: 'center',escape: true
								/**
								<s:if test="detailObj.VIN!=null && detailObj.VIN!=''">
									,hide:true,toggle:false
								</s:if>
                                 **/
								<s:if test="detailObj.valid_flag=='carchoice'">
									,hide:true,toggle:false
								</s:if>
							},
						    {display: '站点名称', name : 'site_name', width : 90, sortable : true, align: 'center',escape: true},
						    {display: '线路', name : 'route_name', width :  90, sortable : true, align: 'center',escape: true},
						    {display: '异常事件', name : 'alarm_type_name', width :  70, sortable : true, align: 'center',escape: true},
						    {display: '时间', name : 'terminal_time', width :  120, sortable : true, align: 'center',escape: true},
						    {display: '驾驶员', name : 'DRIVER_NAME', width :  60, sortable : true, align: 'center',escape: true},
						    {display: '跟车老师', name : 'SICHEN_NAME', width : 130, sortable : true, align: 'center',hide:true,toggle:false,escape: true},
						    {display: '学生姓名', name : 'stu_name', width : 100, sortable : true, align: 'center',escape: true,hide:true,toggle:false},
							{display: '学校', name : 'stu_school', width : 120, sortable : true, align: 'center',escape: true,hide:true,toggle:false},
							{display: '班级', name : 'stu_class', width : 100, sortable : true, align: 'center',escape: true,hide:true,toggle:false}
				    		],

				    	sortname: 'terminal_time',
				    	sortorder: 'desc',
				    	sortable: true,
						striped :true,
						usepager :true, 
						resizable: false,
				    	useRp: true,
				    	rp: 10,	
				    	useRp: false,
						rpOptions : [10,20,50,100],// 可选择设定的每页结果数
				    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
			});
			pieChartDraw();
	};


    function pieChartDraw(){   	
		if(jQuery('#no_up_num').val()=="0"&&jQuery('#no_down_num').val()=="0"&&jQuery('#nofix_up_num').val()=="0"&&jQuery('#nofix_down_num').val()=="0") {
			showNoDataCharts();
		}else{
			zoomPositionCalc();
			jQuery('#noDataDiv').hide();
			jQuery('#3dChart').show();
			var xmlStr = "<chart  rotateYAxisName='0' showValues='0' pieRadius='85' baseFontSize='12' outCnvBaseFontSize='14'  decimalPrecision='2' pieYScale='60' formatNumberScale='0' formatNumber='0' showLabels='1' showPercentInToolTip='0'>";
		    xmlStr = xmlStr + "<set label='未刷卡上车"+jQuery('#no_up_num').val()+"次' value='"+jQuery('#no_up_num').val()+"' color='ACD6FF' isSliced='1' toolText='未刷卡上车"+jQuery('#no_up_num').val()+"次'/>";
		    xmlStr = xmlStr + "<set label='未刷卡下车"+jQuery('#no_down_num').val()+"次' value='"+jQuery('#no_down_num').val()+"' color='EAC100' isSliced='0' toolText='未刷卡下车"+jQuery('#no_down_num').val()+"次'/>";
		    xmlStr = xmlStr + "<set label='未在规定站点上车"+jQuery('#nofix_up_num').val()+"次' value='"+jQuery('#nofix_up_num').val()+"' color='5CADAD' isSliced='0' toolText='未在规定站点上车"+jQuery('#nofix_up_num').val()+"次'/>";
		    xmlStr = xmlStr + "<set label='未在规定站点下车"+jQuery('#nofix_down_num').val()+"次' value='"+jQuery('#nofix_down_num').val()+"' color='BB3D00' isSliced='0' toolText='未在规定站点下车"+jQuery('#nofix_down_num').val()+"次'/>";
		    xmlStr = xmlStr + "</chart>";
		    
			var chart1 = new FusionCharts("../scripts/fusioncharts/Pie3D.swf?ChartNoDataText=无查询结果", "ChartId", "500", "250", "0", "0");
			chart1.setDataXML(xmlStr);
					   
			chart1.render("3dChart"); 
		}
		
	}
    function zoomPositionCalc() {
		var leftPx = (jQuery(".car-status3").width() - 50)/2 + 40;
		jQuery("#zoomPic").css("left", leftPx + "px");
		jQuery("#zoomPic").css("display", "block");
	}

    function showNoDataCharts() {
		jQuery('#3dChart').hide();
		jQuery('#noRideAlarmDataDiv').show();
	}

	//打开弹出层
	function showRideAlarm() {
		window.parent.no_up_num.value = jQuery('#no_up_num').val();
		window.parent.no_down_num.value = jQuery('#no_down_num').val();
		window.parent.nofix_up_num.value = jQuery('#nofix_up_num').val();
		window.parent.nofix_down_num.value = jQuery('#nofix_down_num').val();
		
		jQuery("#rideAlarmDetial").css("display","block");
		
		if(jQuery('#no_up_num').val()=="0"&&jQuery('#no_down_num').val()=="0"&&jQuery('#nofix_up_num').val()=="0"&&jQuery('#nofix_down_num').val()=="0") {
			jQuery('#rideAlarmcontent').hide();
			jQuery('#noRideAlarmDataDiv').show();
		}else{
			jQuery('#noRideAlarmDataDiv').hide();
			jQuery('#rideAlarmcontent').show();
		}

		window.parent.showRideAlarm();
	}
	
</script>
</body>
</html>

