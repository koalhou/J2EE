<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
	//查询
	var time_select = "week";
	var chart_desc = "";
	var plan_time = "week";
	var user_beg_time = "";
	var user_end_time = "";
	var fileterFlag = "0";
	var vehicle_ln = "";

	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
	jQuery(function() {
		 jQuery("#badTimesReport").easydrag();
		 jQuery("#badTimesReport").setHandler("badTimesTitle"); 
		 jQuery("#badTimeReport").easydrag();
		 jQuery("#badTimeReport").setHandler("badTimeTitle"); 
		 jQuery("#viloationDetail").easydrag();
		 jQuery("#viloationDetail").setHandler("viloationTitle"); 
		 time_select = jQuery('#time_option').val();
		 if(time_select == "define"){
			setColor();
		 }else{
			setGray();
		 }
		 var gala = jQuery('#gala');
			gala.flexigrid({
				dataType: 'json',
				height: 200,
				width:200,
				colModel : [
				    		{display: '<s:text name="common.vehcileln" />', name : 'vehicle_ln', width : 80, sortable : true, align: 'center', process: reWriteLink,escape: true},
				    		{display: '<s:text name="common.vehvin" />', name : 'vehicle_vin', width : 120, sortable : true, align: 'center',hide:true,escape: true},
				    		{display: '<s:text name="common.vehcode" />', name : 'vehicle_code', width : 50, sortable : true, align: 'center',hide:true,toggle:false},
				    		{display: '<s:text name="common.route" />', name : 'route_name', width : 43, sortable : true, align: 'center',hide:true,toggle:false},
				    		{display: '<s:text name="common.driver" />', name : "NLSSORT(driver_name,'NLS_SORT = SCHINESE_PINYIN_M')", width : 43, sortable : true, align: 'center',hide:true,toggle:false},
				    		{display: '<s:text name="common.chaoshu" />', name : 'speeding_num', width : 100, sortable : true, align: 'center', process:sumStyle},
				    		{display: '<s:text name="common.chaoshutime" />', name : 'speeding_time', width : 100, sortable : true, align: 'center', process:sumStyle},
				    		{display: '<s:text name="common.chaozhuan" />', name : 'rpm_num', width : 100, sortable : true, align: 'center', process:sumStyle},
				    		{display: '<s:text name="common.chaozhuantime" />', name : 'rpm_time', width : 100, sortable : true, align: 'center', process:sumStyle},
				    		{display: '<s:text name="common.kongdanghuaxing" />', name : 'gear_glide_num', width : 100, sortable : true, align: 'center', process:sumStyle,hide:true,toggle:false},
				    		{display: '<s:text name="common.kongdanghuaxingtime" />', name : 'gear_glide_time', width : 100, sortable : true, align: 'center', process:sumStyle,hide:true,toggle:false},
				    		{display: '超长怠速（次）', name : 'long_idle_num', width : 100, sortable : true, align: 'center', process:sumStyle},
				    		{display: '超长怠速时间', name : 'long_idle_time', width : 100, sortable : true, align: 'center', process:sumStyle},
				    		{display: '<s:text name="common.jijiashu" />', name : 'urgent_speed_num', width : 100, sortable : true, align: 'center', process:sumStyle,hide:true,toggle:false},
				    		{display: '<s:text name="common.dskt" />', name : 'air_condition_time', width : 100, sortable : true, align: 'center', process:sumStyle},
				    		{display: '超经济区运行时长', name : 'ENGINE_ROTATE_TIME', width : 120, sortable : true, align: 'center', process:sumStyle},
				    		{display: '<s:text name="driverinfo.short_allname" />', name : 'short_allname', width : 90, sortable : true, align: 'center',hide:true,escape: true},
				    		{display: '超经济区运行比例（％）', name : 'economic_run_per', width : 140, sortable : true, align: 'center', process:sumStyle,hide:true,toggle:false}
				    		],
				    	sortname: 'speeding_num desc,vehicle_ln',
				    	sortorder: 'asc',
				    	sortable: true,
						striped :true,
						usepager :true, 
						resizable: false,
				    	useRp: true,
				    	rp:10,	
						rpOptions : [10,20,50,100],// 可选择设定的每页结果数
				    	showTableToggleBtn: true,
					    onRowSelect:rowclickFun,
					    onFirstRowSelected:rowclickFun,
					    onSuccess:function(){
					       if(this.total==0){
					    	   showNoBadTimesCharts();
					    	   showNoBadTimeCharts();
					    	   jQuery("#zoomBadTimesPic").css("display", "none");
					    	   jQuery("#zoomBadTimePic").css("display", "none");
					    	   jQuery("#carln").html('未选车');
						   }
					       jQuery("#rowsumid").css('height','15px').css('line-height','15px');
					       return true;
				        }
			});
		 searchRideAlarm();
		});

	var speeding_num="";
	var rpm_num="";
	var gear_glide_num="";
	var long_idle_num="";
	var choiceVin = "";

	var speeding_time="";
	var rpm_time="";
	var gear_glide_time="";
	var long_idle_time="";
	var air_condition_time="";
	var engineRotateTime="";
	
	var rowclickFun =function(rowData) {
		 var ln=jQuery(rowData).data("vehicle_ln");		 
		 if(ln=="总计 "){
			 jQuery("#carln").html(ln.substr(0,2));
		 }else{
			 jQuery("#carln").html(jQuery(rowData).data("vehicle_ln"));
		 }
		 if(ln.length>7){
			 jQuery("#carln").html("*"+ln.substr(ln.length-7));
			 jQuery("#carln").attr("title",ln);
		 }else{
			 jQuery("#carln").removeAttr("title");
		 }
		 jQuery("#messagetime").html(jQuery('#datestr').val());
		 chooseEnterpriseId = jQuery('#chooseorgid').val();
		 var vin = jQuery(rowData).data("vehicle_vin");
		 speeding_num= jQuery(rowData).data("speeding_num");
		 rpm_num= jQuery(rowData).data("rpm_num");
		 gear_glide_num= jQuery(rowData).data("gear_glide_num");
		 long_idle_num= jQuery(rowData).data("long_idle_num");

		 speeding_time= jQuery(rowData).data("speeding_time");
		 rpm_time= jQuery(rowData).data("rpm_time");
		 gear_glide_time= jQuery(rowData).data("gear_glide_time");
		 long_idle_time= jQuery(rowData).data("long_idle_time");
		 air_condition_time= jQuery(rowData).data("air_condition_time");
		 engineRotateTime= jQuery(rowData).data("ENGINE_ROTATE_TIME");
		 jQuery("#orgname").val(jQuery(rowData).data("short_allname"));
		 jQuery("#choiceVin").val(vin);
		 
		 zoomPositionCalc();

		 if(gear_glide_num=="--"){
			 gear_glide_num = '0';
		 }
		 
		 var s2 = speeding_time.split(":");
		 speeding_time=accAdd(parseInt(s2[0],10)*60+parseInt(s2[1],10),(parseInt(s2[2],10)/60).toFixed(2));
		 var s4 = rpm_time.split(":");
		 rpm_time=accAdd(parseInt(s4[0],10)*60+parseInt(s4[1],10),(parseInt(s4[2],10)/60).toFixed(2));
		 if(gear_glide_time=="--"){
			 gear_glide_time = '0';
		 }else{
			var s6 = gear_glide_time.split(":");
			gear_glide_time=accAdd(parseInt(s6[0],10)*60+parseInt(s6[1],10),(parseInt(s6[2],10)/60).toFixed(2));
		 }
		 var s8 = long_idle_time.split(":");
		 long_idle_time=accAdd(parseInt(s8[0],10)*60+parseInt(s8[1],10),(parseInt(s8[2],10)/60).toFixed(2));
		 var s10 = air_condition_time.split(":");
		 air_condition_time=accAdd(parseInt(s10[0],10)*60+parseInt(s10[1],10),(parseInt(s10[2],10)/60).toFixed(2));
		 var s11 = engineRotateTime.split(":");
		 engineRotateTime=accAdd(parseInt(s11[0],10)*60+parseInt(s11[1],10),(parseInt(s11[2],10)/60).toFixed(2));
		 if("" != vin) {
			 pieChartDraw(speeding_num,rpm_num,gear_glide_num,long_idle_num,speeding_time,rpm_time,gear_glide_time,long_idle_time,air_condition_time,engineRotateTime);
		 }
	};

	function accAdd(arg1, arg2) {
	    var r1, r2, m, c;
	    try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }
	    try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }
	    c = Math.abs(r1 - r2);
	    m = Math.pow(10, Math.max(r1, r2))
	    if (c > 0) {
	        var cm = Math.pow(10, c);
	        if (r1 > r2) {
	            arg1 = Number(arg1.toString().replace(".", ""));
	            arg2 = Number(arg2.toString().replace(".", "")) * cm;
	        }
	        else {
	            arg1 = Number(arg1.toString().replace(".", "")) * cm;
	            arg2 = Number(arg2.toString().replace(".", ""));
	        }
	    }else {
	        arg1 = Number(arg1.toString().replace(".", ""));
	        arg2 = Number(arg2.toString().replace(".", ""));
	    }
	    return (arg1 + arg2) / m
	}
	
	var oilShowWidth=850;
	
	function zoomPositionCalc() {
		var leftPx = (jQuery(".car-status3").width() - oilShowWidth -200)/2 ;
		jQuery("#zoomBadTimesPic").css("display", "block");
		jQuery("#zoomBadTimePic").css("display", "block");
	}

	/*
	 * 绘制按次数饼图
	 */
	function pieChartDraw(v1,v3,v5,v7,v2,v4,v6,v8,v10,v11){
		jQuery('#noBadTimesDiv').hide();
		jQuery('#badTimes').show();
		jQuery('#noBadTimeDiv').hide();
		jQuery('#badTime').show();
		
		var xmlStr = "<chart  rotateYAxisName='0' showValues='0' caption='按次数' baseFontSize='12' outCnvBaseFontSize='14'  decimalPrecision='2' pieRadius='75' pieYScale='60' formatNumberScale='0' formatNumber='0' showLabels='1' showPercentInToolTip='0'>";
	    xmlStr = xmlStr + "<set label='超速"+v1+"次' value='"+v1+"' color='ACD6FF' isSliced='1' toolText='超速"+v1+"次'/>";
	    xmlStr = xmlStr + "<set label='超转"+v3+"次' value='"+v3+"' color='EAC100' isSliced='1' toolText='超转"+v3+"次'/>";
	    if(v5!="0"){
	    	xmlStr = xmlStr + "<set label='空档滑行"+v5+"次' value='"+v5+"' color='5CADAD' isSliced='1' toolText='空档滑行"+v5+"次'/>";
	    }
	    xmlStr = xmlStr + "<set label='超长怠速"+v7+"次' value='"+v7+"' color='BB3D00' isSliced='1' toolText='超长怠速"+v7+"次'/>";	    
	    var style="<styles>"+
		"<definition>"+
			"<style name='MyCaptionFontStyle1' type='font' face='Verdana' size='16'  bold='1' />"+
			//"<style name='myValuesFont' type='font' font='Verdana' size='13' color='235F81' bold='1' isHTML='1' />"+
		"</definition>"+
		"<application>"+
			"<apply toObject='caption' styles='MyCaptionFontStyle1' />"+
			//"<apply toObject='DATALABELS' styles='myValuesFont' />"+
		"</application>"+
		"</styles>"
		xmlStr = xmlStr +style;
	    xmlStr = xmlStr + "</chart>";

	    var xmlStr2 = "<chart  rotateYAxisName='0' showValues='0' caption='按时间'  baseFontSize='12' outCnvBaseFontSize='14' decimalPrecision='2' pieRadius='75' pieYScale='60' formatNumberScale='0' formatNumber='0' showLabels='1' showPercentInToolTip='0'>";
	    xmlStr2 = xmlStr2 + "<set label='超速"+v2+"分' value='"+v2+"' color='ACD6FF' isSliced='1' toolText='超速"+v2+"分'/>";
	    xmlStr2 = xmlStr2 + "<set label='超转"+v4+"分' value='"+v4+"' color='EAC100' isSliced='1' toolText='超转"+v4+"分'/>";
	    if(v6!="0"){
	    	xmlStr2 = xmlStr2 + "<set label='空档滑行"+v6+"分' value='"+v6+"' color='5CADAD' isSliced='1' toolText='空档滑行"+v6+"分'/>";
	    }
	    xmlStr2 = xmlStr2 + "<set label='超长怠速"+v8+"分' value='"+v8+"' color='BB3D00' isSliced='1' toolText='超长怠速"+v8+"分'/>";
	    xmlStr2 = xmlStr2 + "<set label='怠速空调"+v10+"分' value='"+v10+"' color='7D7DFF' isSliced='1' toolText='怠速空调"+v10+"分'/>";
	    xmlStr2 = xmlStr2 + "<set label='超经济区"+v11+"分' value='"+v11+"' color='F75000' isSliced='1' toolText='超经济区"+v11+"分'/>";
	    var style2="<styles>"+
		"<definition>"+
			"<style name='MyCaptionFontStyle1' type='font' face='Verdana' size='16'  bold='1' />"+
			//"<style name='myValuesFont' type='font' font='Verdana' size='13' color='235F81' bold='1' isHTML='1' />"+
		"</definition>"+
		"<application>"+
			"<apply toObject='caption' styles='MyCaptionFontStyle1' />"+
			//"<apply toObject='DATALABELS' styles='myValuesFont' />"+
		"</application>"+
		"</styles>"
		xmlStr2 = xmlStr2 +style2;
	    xmlStr2 = xmlStr2 + "</chart>";
	    
	    if(v1==0&&v3==0&&v5==0&&v7==0){
			showNoBadTimesCharts();
			jQuery("#zoomBadTimesPic").css("display", "none");
		}

	    if(v2==0&&v4==0&&v6==0&&v8==0&&v10==0&&v11==0){
			showNoBadTimeCharts();
			jQuery("#zoomBadTimePic").css("display", "none");
		}
		
	    setPieWidth();
	    
		var chart1 = new FusionCharts("../scripts/fusioncharts/Pie3D.swf?ChartNoDataText=无查询结果", "ChartId", jQuery('#badTimes').width(), "195", "0", "0");
		chart1.setDataXML(xmlStr);
		chart1.render("badTimes");  
		

		var chart2 = new FusionCharts("../scripts/fusioncharts/Pie3D.swf?ChartNoDataText=无查询结果", "ChartId", jQuery('#badTime').width(), "195", "0", "0");
		chart2.setDataXML(xmlStr2);
		chart2.render("badTime");  
		
	}

	function reWriteLink(tdDiv,pid){
		if(pid=="sumid"){
			return " <span style= 'font-weight: bold ;font-size:14px;'>总计</span> "; 
		}else{
			return tdDiv;
		}
	}
	
	function _Style(tdDiv,pid,row){
		var str8=row.cell[7];
		if(str8=='0'){
			return '--';
		}else{
			return tdDiv;
		}
	}

	function _kongDangTimeStyle(tdDiv,pid,row){
		var str8=row.cell[8];
		if(str8=='00:00:00'){
			return '--';
		}else{
			return tdDiv;
		}
	}
	
	function sumStyle(tdDiv,pid){
		if(pid=="sumid"){
			return '<span style= "font-weight: bold ;font-size:14px;">' + tdDiv +'</span>'; 
		}else{
			return  tdDiv;
		}
	}
	var selectName="";
	function mytreeonClick(event, treeId, treeNode){
		//将组织ID赋值给需要使用的变量
		document.getElementById('chooseorgid').value=treeNode.id;
		selectName=treeNode.name;
		searchRideAlarm();
	}

	function selectTime(){
		time_select = jQuery('#time_option').val();
		if(time_select == "define"){
			setColor();
		}else{
			setGray();
		}
		searchRideAlarm();
	}
	
	function setGray(){
		jQuery("#begTime").removeClass();
		jQuery("#endTime").removeClass();
		jQuery("#begTime").attr("disabled","disabled");
		jQuery("#endTime").attr("disabled","disabled");
	}

	function setColor(){
		jQuery("#begTime").addClass('Wdate');
		jQuery("#endTime").addClass('Wdate');
		jQuery("#begTime").attr("disabled","");
		jQuery("#endTime").attr("disabled","");
	}	
	
	/*
		查询不良驾驶记录
	*/
	function searchRideAlarm() {
		if(time_select != "define"){
			plan_time = time_select;
			user_beg_time = "";
			user_end_time = "";			
			if(plan_time=="month"){
				chart_desc="本月";
			}else if(plan_time=="quarter"){
				chart_desc="本季度";
			}else if(plan_time=="year"){
				chart_desc="本年";
			}else if(plan_time=="week"){
				chart_desc="本周";
			}
			jQuery('#datestr').val(chart_desc);
		}else if(time_select == "define"){
			if(checkTime()){
				chart_desc=jQuery('#begTime').val()+" 至 "+jQuery('#endTime').val();	
				plan_time = "";
				user_beg_time = jQuery('#begTime').val();
				user_end_time = jQuery('#endTime').val();	
			}else{
				return;
			}
			jQuery('#datestr').val(chart_desc);
		}
		
		if(jQuery("#dataFileter").attr("checked")){
			fileterFlag = "1";
		}else{
			fileterFlag = "0";
		}
		vehicle_ln = formatSpecialChar(jQuery('#vehicle_ln').val());

		jQuery('#gala').flexOptions({
			newp: 1,
			url: '<s:url value="/baddrivgrid/baddrivgrid.shtml" />',
			  params: [{name: 'baddrivday.vehicle_ln', value: vehicle_ln},
				       {name: 'baddrivday.organization_id', value: jQuery('#chooseorgid').val()},
				       {name: 'baddrivday.fileterFlag', value: fileterFlag},
				       {name: 'baddrivday.time_list', value: plan_time},
				       {name: 'baddrivday.end_time', value: user_end_time},
				       {name: 'baddrivday.start_time', value: user_beg_time}]
		});		
		jQuery("#messagetime").html(jQuery('#datestr').val());
		jQuery('#gala').flexReload();
	}

	function checkTime() {
		var end_time = $('endTime');
		var start_time = $('begTime');
		if (time_select == 1) {
		    if (!Mat.checkRequired(start_time))
				return false;
			if (!Mat.checkRequired(end_time))
				return false;
		}
		return true;
	}

	//数据过滤CHECKBOX选中状态赋值状态操作
	function dataFileterFlagValue(){
		var _fileterFlag = $('dataFileter');
		if(_fileterFlag.checked){
			_fileterFlag.value=1;
		}else{
			_fileterFlag.value=0;
		}
		searchRideAlarm();
	}

	/*
		导出不良驾驶记录
	*/
	function exportData(){
		if(confirm("确定要导出不良驾驶记录吗？")) {
			if(time_select != "define"){
				document.getElementById('baddrivday.time_list').value = jQuery('#time_option').val();
				document.getElementById('baddrivday.start_time').value = "";
				document.getElementById('baddrivday.end_time').value = "";
			}else if(time_select == "define"){
				if(checkTime()){
					document.getElementById('baddrivday.start_time').value = jQuery('#begTime').val();
					document.getElementById('baddrivday.end_time').value = jQuery('#endTime').val();
					document.getElementById('baddrivday.time_list').value = "";
					plan_time = "";
				}else{
					return;
				}
			}
			
			document.getElementById('baddrivday.vehicle_ln').value = formatSpecialChar(jQuery('#vehicle_ln').val());
			document.getElementById('baddrivday.organization_id').value = jQuery('#chooseorgid').val();

			if(jQuery("#dataFileter").attr("checked")){
				fileterFlag = "1";
			}else{
				fileterFlag = "0";
			}
			
			document.getElementById('baddrivday.fileterFlag').value = fileterFlag;
			document.getElementById('export_form').action="<s:url value='/baddriv/exportCar.shtml' />";
			document.getElementById('export_form').submit();
		}
	}	

	//关闭弹出层
	function closeBadTimesReport() {
		jQuery("#BgDiv").css("display","none");
		jQuery("#badTimesReport").css("display","none");
	}

	//关闭弹出层
	function closeBadTimeReport() {
		jQuery("#BgDiv").css("display","none");
		jQuery("#badTimeReport").css("display","none");
	}

	//打开弹出层
	function showBadTimesReport() {
		jQuery("#badTimesReport").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#badTimesReport").width())/2))+"px")
         .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#badTimesReport").height())/2))+"px");

		jQuery("#BgDiv").css("display","block");
		var orgname="";
		if(selectName==""){
			var zTree = jQuery.fn.zTree.getZTreeObj("treeDemo");
			var node = zTree.getNodeByParam("level", "0");
			orgname=node.name;
		}else{
			orgname=selectName;
		}
		jQuery("#badTimesReport").css("display","block");
		if("" == jQuery("#choiceVin").val()) {
			showNoDataCharts();
		} else {
			var xmlStr = "<chart  rotateYAxisName='0' showValues='0' baseFontSize='15' outCnvBaseFontSize='16'  decimalPrecision='2' pieRadius='120' pieYScale='60' formatNumberScale='0' formatNumber='0' showLabels='1' showPercentInToolTip='0'>";
			xmlStr = xmlStr + "<set label='超速"+speeding_num+"次' value='"+speeding_num+"' color='ACD6FF' isSliced='1' toolText='超速"+speeding_num+"次'/>";
		    xmlStr = xmlStr + "<set label='超转"+rpm_num+"次' value='"+rpm_num+"' color='EAC100' isSliced='1' toolText='超转"+rpm_num+"次'/>";
		    if(gear_glide_num!="0"){
		    	xmlStr = xmlStr + "<set label='空档滑行"+gear_glide_num+"次' value='"+gear_glide_num+"' color='5CADAD' isSliced='1' toolText='空档滑行"+gear_glide_num+"次'/>";
		    }
		    xmlStr = xmlStr + "<set label='超长怠速"+long_idle_num+"次' value='"+long_idle_num+"' color='BB3D00' isSliced='1' toolText='超长怠速"+long_idle_num+"次'/>";
		    xmlStr = xmlStr + "</chart>";
		    
			var chart1 = new FusionCharts("../scripts/fusioncharts/Pie3D.swf?ChartNoDataText=无查询结果", "ChartId", "625", "350", "0", "0");
			chart1.setDataXML(xmlStr);
			chart1.render("badtimescontent"); 
		}
		if(formatSpecialChar(jQuery("#carln").html())=="总计"){
			jQuery("#vehicle_ln_v").val("总计");
			jQuery("#org_name_v").val(orgname);
			jQuery("#time_name_v").val(jQuery("#messagetime").html());
		}else{
			jQuery("#vehicle_ln_v").val(jQuery("#carln").html());
			jQuery("#org_name_v").val(jQuery("#orgname").val());
			jQuery("#time_name_v").val(jQuery("#messagetime").html());
		}
	}

	//打开弹出层
	function showBadTimeReport() {
		jQuery("#badTimeReport").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#badTimeReport").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#badTimeReport").height())/2))+"px");
        
		jQuery("#BgDiv").css("display","block");
		var orgname="";
		if(selectName==""){
			var zTree = jQuery.fn.zTree.getZTreeObj("treeDemo");
			var node = zTree.getNodeByParam("level", "0");
			orgname=node.name;
		}else{
			orgname=selectName;
		}
		jQuery("#badTimeReport").css("display","block");
		if("" == jQuery("#choiceVin").val()) {
			showNoDataCharts();
		} else {
			var xmlStr = "<chart  rotateYAxisName='0' showValues='0'   baseFontSize='15' outCnvBaseFontSize='16'  decimalPrecision='2' pieRadius='120' pieYScale='60' formatNumberScale='0' formatNumber='0' showLabels='1' showPercentInToolTip='0'>";
			xmlStr = xmlStr + "<set label='超速"+speeding_time+"分' value='"+speeding_time+"' color='ACD6FF' isSliced='1' toolText='超速"+speeding_time+"分'/>";
		    xmlStr = xmlStr + "<set label='超转"+rpm_time+"分' value='"+rpm_time+"' color='EAC100' isSliced='1' toolText='超转"+rpm_time+"分'/>";
		    if(gear_glide_time!="0"){
		    	xmlStr = xmlStr + "<set label='空档滑行"+gear_glide_time+"分' value='"+gear_glide_time+"' color='5CADAD' isSliced='1' toolText='空档滑行"+gear_glide_time+"分'/>";
		    }
		    xmlStr = xmlStr + "<set label='超长怠速"+long_idle_time+"分' value='"+long_idle_time+"' color='BB3D00' isSliced='1' toolText='超长怠速"+long_idle_time+"分'/>";
		    xmlStr = xmlStr + "<set label='怠速空调"+air_condition_time+"分' value='"+air_condition_time+"' color='7D7DFF' isSliced='1' toolText='怠速空调"+air_condition_time+"分'/>";
		    xmlStr = xmlStr + "<set label='超经济区"+engineRotateTime+"分' value='"+engineRotateTime+"' color='F75000' isSliced='1' toolText='超经济区"+engineRotateTime+"分'/>";
		    xmlStr = xmlStr + "</chart>";
		    
			var chart1 = new FusionCharts("../scripts/fusioncharts/Pie3D.swf?ChartNoDataText=无查询结果", "ChartId", "625", "350", "0", "0");
			chart1.setDataXML(xmlStr);
			chart1.render("badtimecontent"); 
		}

		if(formatSpecialChar(jQuery("#carln").html())=="总计"){
			jQuery("#vehicle_ln_vs").val("总计");
			jQuery("#org_name_vs").val(orgname);
			jQuery("#time_name_vs").val(jQuery("#messagetime").html());
		}else{
			jQuery("#vehicle_ln_vs").val(jQuery("#carln").html());
			jQuery("#org_name_vs").val(jQuery("#orgname").val());
			jQuery("#time_name_vs").val(jQuery("#messagetime").html());
		}
	}

	function showNoDataCharts() {
		jQuery('#3dChart').hide();
		jQuery('#noDataDiv').show();
	}

	function showNoBadTimesCharts() {
		jQuery('#badTimes').hide();
		jQuery('#noBadTimesDiv').show();
	}

	function showNoBadTimeCharts() {
		jQuery('#badTime').hide();
		jQuery('#noBadTimeDiv').show();
	}

	//关闭弹出层
	function closeViloationDetail() {
		jQuery("#BgDiv").css("display","none");
		jQuery("#viloationDetail").css("display","none");
	}
	
	//显示详细页面
	function showViloationDetail() {
		if(jQuery("#carln").html()=='未选车'){
			return false;
		}
		jQuery("#viloationDetail").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#viloationDetail").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#viloationDetail").height())/2))+"px");
        
		jQuery("#BgDiv").css("display","block");
		jQuery("#viloationDetail").css("display","block");

		var carsChoice = document.getElementsByName("alarm_type_id_name");
		for(var i=0; i<carsChoice.length; i++) {
		    if(carsChoice[i].checked!=true) {
		    	jQuery("input[name='alarm_type_id_name']").attr("checked","true"); 
		    }
		}

		if(mapObj==null){
			mapInit();
		}else{
			mapObj.setCenter(new MLngLat("116.49746427536011","39.9086663756386"));//设置地图中心点的经纬度坐标。
			mapObj.setZoomLevel(4);
			mapObj.removeAllOverlays();
			//第二次加载表格数据时
			getDetailList();
		}	
	}

	/*
		查询操作
	*/
	function searchList(){
	  	 var alarm_type_id_list = "";
	  	 var j = 0;
	  	 var ids = document.getElementsByName("alarm_type_id_name");
		 for (var i = 0; i < ids.length; i++){          
		      if(ids[i].checked == true){
			      if(j==0){
		    	      alarm_type_id_list = alarm_type_id_list+ids[i].value;
		    	      j++;
			      }else{
			    	  alarm_type_id_list = alarm_type_id_list+","+ids[i].value;   
			    	  j++;
				  }
		      }
		 } 
	     if(j==0){
	    	 alarm_type_id_list = "-1";
		 } 

		 if(formatSpecialChar(jQuery("#carln").html())=="总计"){
			 jQuery("#choiceVin").val('');
		 }
		 
	  	 jQuery('#detailgrid').flexOptions({
	  		url: '<s:url value="/baddrivgrid/loadEditData.shtml" />?vehicle_vin='+ jQuery("#choiceVin").val()+'&baddetail.time_list='+plan_time+'&baddetail.alarm_start_time='+user_beg_time+'&baddetail.alarm_end_time='+user_end_time+'&organization_id='+jQuery('#chooseorgid').val(),
			newp: 1,
			params: [{name: 'alarm_type_id_eq',value: alarm_type_id_list},
					 {name:'vehicle_ln',value:formatSpecialChar(jQuery('#vehicle_ln').val())}]
					//{name:'route_name',value:formatSpecialChar(document.getElementById("route_name").value)}]
		 });		
	  	 jQuery('#detailgrid').flexReload();
	  	 
	  	 if(formatSpecialChar(jQuery("#carln").html())=="总计"){
			 jQuery("#choiceVin").val('总计');
		 }
	}

	/*
		不良驾驶详细列表
	*/
	function getDetailList(){
		var shows=true;
		var toggles=false;
		if(formatSpecialChar(jQuery("#carln").html())=="总计"){
			shows=false;
			toggles=true;
		}
		jQuery('#forSumTable').next().remove();
		jQuery('#forSumTable').after('<table id="detailgrid"></table>');
		var detailgrid = jQuery('#detailgrid');
		detailgrid.flexigrid({
			dataType: 'json',
			height: '145',
			width: '700',
			colModel : [
						{display: '<s:text name="common.vehcileln" />', name : 'vehicle_ln', width : 80, sortable : true, align: 'center',hide:shows,toggle:toggles,escape: true},
			    		{display: '<s:text name="vehcileinfo.driver" />', name : 'vehicle_vin', width : 50, sortable : true, align: 'left',hide:true,toggle:false},
			    		{display: '<s:text name="common.route" />', name : 'route_name', width : 80, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '<s:text name="common.driver" />', name : "NLSSORT(driver_name,'NLS_SORT = SCHINESE_PINYIN_M')", width : 50, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '<s:text name="common.shijian" />', name : 'alarm_type_name', width : 150, sortable : true, align: 'center',escape: true},
			    		{display: '<s:text name="common.starttime" />', name : 'alarm_start_time', width : 150, sortable : true, align: 'center',escape: true},
			    		{display: '<s:text name="common.endtime" />', name : 'alarm_end_time', width : 150, sortable : true, align: 'center',escape: true},
			    		{display: '<s:text name="common.computtime" />', name : 'alarm_time', width : 150, sortable : true, align: 'center',escape: true},
			    		{display: '<s:text name="common.cheshu" />', name : 'alarm_start_speed', width : 40, sortable : true, align: 'left',hide:true,toggle:false},
			    		{display: '<s:text name="common.zhuanshu" />', name : 'alarm_start_rpm', width : 40, sortable : true, align: 'left',hide:true,toggle:false},
			    		{display: '<s:text name="common.latitude" />' ,name : 'alarm_start_latitude',width : 40,hide:true,toggle:false},
			    		{display: '<s:text name="common.longitude" />'  ,name :'alarm_start_longitude',width :40,hide:true,toggle:false}
			    		],
			    	sortname: 'alarm_start_time',
			    	sortorder: 'desc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
			    	useRp: true,
			    	rp: 10,	
					rpOptions : [10,20,50,100],// 可选择设定的每页结果数
			    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错
			    	onRowSelect:rowclickMap,
			    	onFirstRowSelected:firstRowSelected,
			    	ifBlock:true
		});
		searchList();
	}

	//行点击取值
	var rowclickMap =function(rowData) {
		 var alarm_type_id=jQuery(rowData).data("alarm_type_id");
		 var vehicle_ln = jQuery(rowData).data("vehicle_ln");
		 var alarm_start_time= jQuery(rowData).data("alarm_start_time");
		 var alarm_end_time= jQuery(rowData).data("alarm_end_time");
		 var alarm_type_name= jQuery(rowData).data("alarm_type_name");
		 var vehicle_vin=jQuery(rowData).data("vehicle_vin");
		 showpoint(vehicle_ln,alarm_start_time,alarm_end_time,vehicle_vin,alarm_type_id);
	};

	//默认第一行选中
	var firstRowSelected = function(firstRowData) {
		 var alarm_type_id = jQuery(firstRowData).data("alarm_type_id");
		 var vehicle_ln = jQuery(firstRowData).data("vehicle_ln");
		 var alarm_start_time= jQuery(firstRowData).data("alarm_start_time");
		 var alarm_end_time= jQuery(firstRowData).data("alarm_end_time");
		 var alarm_type_name= jQuery(firstRowData).data("alarm_type_name");
		 var vehicle_vin=jQuery(firstRowData).data("vehicle_vin");
		 showpoint(vehicle_ln,alarm_start_time,alarm_end_time,vehicle_vin,alarm_type_id);
	};


	//页面入口
	jQuery(function() {
		jQuery(window).mk_autoresize({
			min_width: 1320
		});
		resizeBgDiv();
		auto_size();
	});

	/*
		捕捉页面resize事件
	*/
    jQuery(window).wresize(function(){
    	jQuery("#viloationDetail").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#viloationDetail").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#viloationDetail").height())/2))+"px");

    	jQuery("#badTimeReport").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#badTimeReport").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#badTimeReport").height())/2))+"px");

    	jQuery("#badTimesReport").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#badTimesReport").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#badTimesReport").height())/2))+"px");
	});

    /*
    	页面自适应计算
    */
	function auto_size(){
		//计算中区高度
		jQuery('#content').mk_autoresize( {
			height_include : [ '#content_rightside', '#content_leftside' ],
			height_offset : 0,
			width_exclude: ['#content_leftside'],
			width_include : [ '#content_rightside'],
			width_offset : 1
		});
		//计算左测区域高度
		jQuery('#content_leftside').mk_autoresize( {
			height_exclude : '#leftInfoDiv',
			height_include : '.treeBox',
			height_offset : 5
		});

		//计算右测区域高度
		jQuery('#content_rightside').mk_autoresize( {
			height_exclude : '.titleBar',
			height_include : '#statusManger',
			height_offset : 0,
			width_include : [ '#statusManger','.titleBar'],
			width_offset : 0
		});	

		jQuery('#statusManger').mk_autoresize( {
			height_exclude : ['.car-info','car-status3'],
			height_include : '.list-area',
			height_offset : 195,
			width_include : [ '.list-area','.car-info','car-status3'],
			width_offset : 0
		});	
		jQuery('.list-area').mk_autoresize({
		    height_include: '#tableparent .bDiv',
		    height_offset: 115, // 该值各页面根据自己的页面布局调整
		    width_include: '#tableparent .flexigrid',
		    width_offset: 10// 该值各页面根据自己的页面布局调整
		});
		setPieWidth();
	}
	
	/**
	 * 左侧树区域显示控制
	 */
	function HideandShowControl(){//leftdiv
		if(jQuery('#middeldiv').css("display")=="none"){//展开状态
			jQuery('#middeldiv').css("display","block");
			jQuery('#leftdiv').css("display","none");
			//计算中区高度
			jQuery('#content').mk_autoresize( {
				height_include : [ '#content_rightside', '#content_leftside' ],
				height_offset : 0,
				width_exclude: ['#content_leftside'],
				width_include : [ '#content_rightside'],
				width_offset : 25
			});
			//计算左测区域高度
			jQuery('#content_leftside').mk_autoresize( {
				height_exclude : '#leftInfoDiv',
				height_include : '.treeBox',
				height_offset : 5
			});

			//计算右测区域高度
			jQuery('#content_rightside').mk_autoresize( {
				height_exclude : '.titleBar',
				height_include : '#statusManger',
				height_offset : 0,
				width_include : [ '#statusManger','.titleBar'],
				width_offset : 0
			});	

			jQuery('#statusManger').mk_autoresize( {
				height_exclude : ['.car-info','car-status3'],
				height_include : '.list-area',
				height_offset : 195,
				width_include : [ '.list-area','.car-info','car-status3'],
				width_offset : 0
			});	
			jQuery('.list-area').mk_autoresize({
			    height_include: '#tableparent .bDiv',
			    height_offset: 115, // 该值各页面根据自己的页面布局调整
			    width_include: '#tableparent .flexigrid',
			    width_offset: 10// 该值各页面根据自己的页面布局调整
			});
			setPieWidth();
		}else{//隐藏状态
			jQuery('#middeldiv').css("display","none");
			jQuery('#leftdiv').css("display","block");
			auto_size();
		}
	}
	
	/*
		设置饼图所在DIV宽度
	*/
	function setPieWidth(){
		if(jQuery("#noBadTimesDiv").is(":visible")&&jQuery("#noBadTimeDiv").is(":visible")){
			if(jQuery('.car-status3').width()>=jQuery('#noBadTimesDiv').width()+545){
				jQuery('#noBadTimesDiv').width(jQuery('.car-status3').width()-545);
			}else{
				jQuery('#noBadTimesDiv').width(545);
			}
		}

		if(jQuery("#badTimes").is(":visible")&&jQuery("#badTime").is(":visible")){
			if(jQuery('.car-status3').width()>=jQuery('#badTimes').width()+545){
				jQuery('#badTimes').width(jQuery('.car-status3').width()-545);
				if((jQuery('#badTimes').width()-545)/2>50){
					jQuery("#zoomBadTimesPic").css("left", (jQuery('#badTimes').width()-545)/2 + "px");
				}else{
					jQuery("#zoomBadTimesPic").css("left", "50px");
				}
			}else{
				jQuery('#badTimes').width(545);
				jQuery("#zoomBadTimesPic").css("left", "50px");
			}
		}

		if(jQuery("#noBadTimesDiv").is(":visible")&&jQuery("#badTime").is(":visible")){
			if(jQuery('.car-status3').width()>=jQuery('#noBadTimesDiv').width()+545){
				jQuery('#noBadTimesDiv').width(jQuery('.car-status3').width()-545);
			}else{
				jQuery('#noBadTimesDiv').width(545);
			}
		}

		if(jQuery("#badTimes").is(":visible")&&jQuery("#noBadTimeDiv").is(":visible")){
			if(jQuery('.car-status3').width()>=jQuery('#badTimes').width()+545){
				jQuery('#badTimes').width(jQuery('.car-status3').width()-545);
				if((jQuery('#badTimes').width()-545)/2>50){
					jQuery("#zoomBadTimesPic").css("left", (jQuery('#badTimes').width()-545)/2 + "px");
				}else{
					jQuery("#zoomBadTimesPic").css("left", "50px");
				}
			}else{
				jQuery('#badTimes').width(545);
				jQuery("#zoomBadTimesPic").css("left", "50px");
			}
		}
	}
</script>