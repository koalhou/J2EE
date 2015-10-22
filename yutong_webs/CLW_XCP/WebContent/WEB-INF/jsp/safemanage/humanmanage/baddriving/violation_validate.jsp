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
		 jQuery("#viloationDetail").easydrag();
		 jQuery("#viloationDetail").setHandler("viloationTitle"); 
		 jQuery("#viloationReport").easydrag();
		 jQuery("#viloationReport").setHandler("detailTitle"); 
		 //jQuery("#carln").attr("title","");
		 time_select = jQuery('#time_option').val();
		 if(time_select == "define"){
			setColor();
		 }else{
			setGray();
		 }
		 var gala = jQuery('#gala');
		 gala.flexigrid({
		  dataType: 'json',    //json格式
		  height: 200,
		  width:200,
		  colModel : [ 
						{display: '<s:text name="common.vehcileln" />', name : 'vehicle_ln', width : calcColumn(0.15,120,350), sortable : true, align: 'center', process: reWriteLink,escape: true},
						{display: '<s:text name="common.vehvin" />', name : 'vehicle_vin', width : 180, sortable : true, align: 'center',hide:true,escape: true},
						{display: '<s:text name="common.route" />', name : 'route_name', width : 43, sortable : true, align: 'center',hide:true,toggle:false},
						{display: '<s:text name="common.vehcode" />', name : 'vehicle_code', width : 50, sortable : true, align: 'center',hide:true,toggle:false},
						{display: '<s:text name="common.driver" />', name : 'driver_name', width : 43, sortable : true, align: 'center',hide:true,toggle:false},
						{display: '未鉴权驾驶（次）', name : 'illegal_on_fire', width : calcColumn(0.21,150,350), sortable : true, align: 'center', process:sumStyle},
						{display: '<s:text name="common.chaoshu" />', name : 'speeding_num', width : calcColumn(0.21,150,350), sortable : true, align: 'center', process:sumStyle},
						{display: '<s:text name="common.chaoshutime" />', name : 'speeding_time', width : 65, sortable : true, align: 'center', process:sumStyle,hide:true,toggle:false},
						{display: '<s:text name="common.kongdanghuaxing" />', name : 'gear_glide_num', width : 66, sortable : true, align: 'center', process:sumStyle,process:_Style,hide:true,toggle:false},
						{display: '<s:text name="common.kongdanghuaxingtime" />', name : 'gear_glide_time', width : 90, sortable : true, align: 'center', process:sumStyle,process:_kongDangTimeStyle,hide:true,toggle:false},
						{display: '超载（次）', name : 'over_loading_num', width : calcColumn(0.21,150,350), sortable : true, align: 'center', process:sumStyle},
						{display: '<s:text name="common.pilaojiashi" />', name : 'fatigue_driving_num', width : calcColumn(0.21,150,350), sortable : true, align: 'center', process:sumStyle},
						{display: '<s:text name="common.pilaojiashitime" />', name : 'fatigue_driving_time', width : 90, sortable : true, align: 'center', process:sumStyle,hide:true,toggle:false},
						{display: '<s:text name="common.jijiashu" />', name : 'urgent_speed_num', width : 85, sortable : true, align: 'center', process:sumStyle,hide:true,toggle:false},
						{display: '<s:text name="driverinfo.short_allname" />', name : 'short_allname', width : 90, sortable : true, align: 'center',hide:true,escape: true}
						],
						sortname: 'speeding_num desc,vehicle_ln',
		       sortorder: 'asc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true,   // 是否允许显示隐藏列
		       onRowSelect:rowclickFun,
		       onFirstRowSelected:rowclickFun,
		       onSuccess:function(){
				   if(this.total==0){
					   showNoDataCharts();
			    	   jQuery("#zoomPic").css("display", "none");
			    	   jQuery("#carln").html('未选车');
				   }
				   jQuery("#rowsumid").css('height','15px').css('line-height','15px');
			       return true;
		       }
		     });
		 searchRideAlarm();
		});

	var speeding_num="";
	var over_loading_num="";
	var illegal_on_fire="";
	var fatigue_driving_num="";
	var choiceVin = "";
	var rowclickFun =function(rowData) {
		 var ln=jQuery(rowData).data("vehicle_ln");		 
		 if(ln=="总计"){
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
		 over_loading_num= jQuery(rowData).data("over_loading_num");
		 illegal_on_fire= jQuery(rowData).data("illegal_on_fire");
		 fatigue_driving_num= jQuery(rowData).data("fatigue_driving_num");
		 jQuery("#orgname").val(jQuery(rowData).data("short_allname"));
		 jQuery("#choiceVin").val(vin);
		 //choiceVin = vin;
		 zoomPositionCalc();
		 if("" != vin) {
			 pieChartDraw(illegal_on_fire,speeding_num,over_loading_num,fatigue_driving_num);
		 }else{
			 showNoDataCharts();
		 }
		 
	};
	
	var oilShowWidth=850;
	function zoomPositionCalc() {
		var leftPx = (jQuery(".car-status3").width() - oilShowWidth)/2 + 220;
		jQuery("#zoomPic").css("left", leftPx + "px");
		jQuery("#zoomPic").css("display", "block");
	}

	function pieChartDraw(v1,v2,v3,v4){
		jQuery('#noDataDiv').hide();
		jQuery('#3dChart').show();
		var xmlStr = "<chart  rotateYAxisName='0' showValues='0' enableRotation='0' baseFontSize='12' outCnvBaseFontSize='14'  decimalPrecision='2' pieRadius='75' pieYScale='60' formatNumberScale='0' formatNumber='0' showLabels='1' showPercentInToolTip='0'>";
	    xmlStr = xmlStr + "<set label='超速"+v2+"次' value='"+v2+"' color='ACD6FF' isSliced='1' toolText='超速"+v2+"次'/>";
	    xmlStr = xmlStr + "<set label='超载"+v3+"次' value='"+v3+"' color='EAC100' isSliced='1' toolText='超载"+v3+"次'/>";
	    xmlStr = xmlStr + "<set label='未鉴权驾驶"+v1+"次' value='"+v1+"' color='5CADAD' isSliced='1' toolText='未鉴权驾驶"+v1+"次'/>";
	    xmlStr = xmlStr + "<set label='超时驾驶"+v4+"次' value='"+v4+"' color='BB3D00' isSliced='1' toolText='超时驾驶"+v4+"次'/>";
	    xmlStr = xmlStr + "</chart>";
		var chart1 = new FusionCharts("../scripts/fusioncharts/Pie3D.swf?ChartNoDataText=无查询结果", "ChartId", oilShowWidth, "195", "0", "0");
		chart1.setDataXML(xmlStr);
		chart1.render("3dChart"); 
		if(v1==0&&v2==0&&v3==0&&v4==0){
			showNoDataCharts();
			jQuery("#zoomPic").css("display", "none");
		}
	}

	function reWriteLink(tdDiv,pid){
		if(pid=="sumid"){
			return "<span style= 'font-weight: bold ;font-size:14px;'>总计</span> "; 
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
	
	function sumStyle(tdDiv,pid,row){
		if(pid=="sumid"){
			return '<span style= "font-weight: bold ;font-size:14px;">' + tdDiv +'</span>'; 
		}else{
			return tdDiv;
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
		var fileterBox = $('dataFileter');
		fileterFlag = "0";
		if(jQuery("#dataFileter").attr("checked")){
			fileterFlag = "1";
		}

		vehicle_ln = formatSpecialChar(jQuery('#vehicle_ln').val());
		jQuery('#gala').flexOptions({
			newp: 1,
			url: '<s:url value="/humanbaddrivgrid/baddrivgrid.shtml" />',
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

	function exportData(){
		if(confirm("确定要导出违规驾驶统计吗？")) {
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
			document.getElementById('export_form').action="<s:url value='/humanbaddrv/exportCar.shtml' />";
			document.getElementById('export_form').submit();
		}
	}	

	//关闭弹出层
	function closeViloationReport() {
		jQuery("#BgDiv").css("display","none");
		jQuery("#viloationReport").css("display","none");
	}
	
	//打开弹出层
	function showViloationReport() {
		jQuery("#viloationReport").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#viloationReport").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#viloationReport").height())/2))+"px");
		
		jQuery("#BgDiv").css("display","block");
		var orgname="";
		if(selectName==""){
			var zTree = jQuery.fn.zTree.getZTreeObj("treeDemo");
			var node = zTree.getNodeByParam("level", "0");
			orgname=node.name;
		}else{
			orgname=selectName;
		}
		jQuery("#viloationReport").css("display","block");
		//var chooseorgid = jQuery('#chooseorgid').val();
		if("" ==  jQuery("#choiceVin").val()) {
			showNoDataCharts();
		} else {
			var xmlStr = "<chart  rotateYAxisName='0' showValues='0'   baseFontSize='14' outCnvBaseFontSize='15'  decimalPrecision='2' pieRadius='120' pieYScale='60' formatNumberScale='0' formatNumber='0' showLabels='1' showPercentInToolTip='0'>";
		    xmlStr = xmlStr + "<set label='超速"+speeding_num+"次' value='"+speeding_num+"' color='ACD6FF' isSliced='1' toolText='超速"+speeding_num+"次'/>";
		    xmlStr = xmlStr + "<set label='超载"+over_loading_num+"次' value='"+over_loading_num+"' color='EAC100' isSliced='1' toolText='超载"+over_loading_num+"次'/>";
		    xmlStr = xmlStr + "<set label='未鉴权驾驶"+illegal_on_fire+"次' value='"+illegal_on_fire+"' color='5CADAD' isSliced='1' toolText='未鉴权驾驶"+illegal_on_fire+"次'/>";
		    xmlStr = xmlStr + "<set label='超时驾驶"+fatigue_driving_num+"次' value='"+fatigue_driving_num+"' color='BB3D00' isSliced='1' toolText='超时驾驶"+fatigue_driving_num+"次'/>";
		    xmlStr = xmlStr + "</chart>";
		    
			var chart1 = new FusionCharts("../scripts/fusioncharts/Pie3D.swf?ChartNoDataText=无查询结果", "ChartId", "625", "350", "0", "0");
			chart1.setDataXML(xmlStr);
			chart1.render("viloationcontent"); 
			if(speeding_num=='0'&&over_loading_num=='0'&&illegal_on_fire=='0'&&fatigue_driving_num=='0'){
				showNoDataCharts();
				jQuery("#zoomPic").css("display", "none");
			}
		}
		//jQuery("#carln[value*=总计]")
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

	function showNoDataCharts() {
		jQuery('#3dChart').hide();
		jQuery('#noDataDiv').show();
	}

	//关闭弹出层
	function closeViloationDetail() {
		jQuery("#BgDiv").css("display","none");
		jQuery("#viloationDetail").css("display","none");
	}
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
	  		url: '<s:url value="/humanbaddrivgrid/loadEditData.shtml" />?vehicle_vin='+ jQuery("#choiceVin").val()+'&baddetail.time_list='+plan_time+'&baddetail.alarm_start_time='+user_beg_time+'&baddetail.alarm_end_time='+user_end_time+'&organization_id='+jQuery('#chooseorgid').val(),
			newp: 1,
			params: [{name: 'alarm_type_id_eq',value: alarm_type_id_list},
					 {name:'vehicle_ln',value:formatSpecialChar(jQuery('#vehicle_ln').val())}]
		 });		
	  	 jQuery('#detailgrid').flexReload();
	  	 if(formatSpecialChar(jQuery("#carln").html())=="总计"){
			 jQuery("#choiceVin").val('总计');
		 }
	}

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
			width: '750',
			colModel : [
			    		{display: '<s:text name="common.vehcileln" />', name : 'vehicle_ln', width : 80, sortable : true, align: 'center',hide:shows,toggle:toggles,escape: true},
			    		{display: '<s:text name="common.vehcode" />', name : 'vehicle_vin', width : 60, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '线路负责人', name : 'route_name', width : 80, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '驾驶员', name : 'driver_name', width : 80, sortable : true, align: 'center',escape: true,hide:true},
			    		{display: '<s:text name="common.shijian" />', name : 'alarm_type_name', width : 90, sortable : true, align: 'center',escape: true},
			    		{display: '数值', name : 'eventValue', width : 60, sortable : true, align: 'center',escape: true},
			    		{display: '单位', name : 'eventUnit', width : 60, sortable : true, align: 'center',escape: true},
			    		{display: '<s:text name="common.starttime" />', name : 'alarm_start_time', width : 125, sortable : true, align: 'center',escape: true},
			    		{display: '<s:text name="common.endtime" />', name : 'alarm_end_time', width : 125, sortable : true, align: 'center',escape: true},
			    		{display: '<s:text name="common.computtime" />', name : 'alarm_time', width : 65, sortable : true, align: 'center',escape: true},
			    		{display: '位置', name : 'alarm_address', width : 120, sortable : true, align: 'center',sortable : false,escape: true},
			    		{display: '<s:text name="common.cheshu" />', name : 'alarm_start_speed', width : 35, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '<s:text name="common.zhuanshu" />', name : 'alarm_start_rpm', width : 35, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '<s:text name="common.latitude" />' ,name : 'alarm_start_latitude',width : 80, align: 'center',hide:true,toggle:false},
			    		{display: '<s:text name="common.longitude" />'  ,name :'alarm_start_longitude',width :80, align: 'center',hide: true,toggle:false},
			    		{display: '', name : 'alarm_type_id', width : 50, sortable : true, align: 'center',hide: true,toggle:false}
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

	var rowclickMap =function(rowData){
		 var alarm_type_id=jQuery(rowData).data("alarm_type_id");
		 var vehicle_ln = jQuery(rowData).data("vehicle_ln");
		 var alarm_start_time= jQuery(rowData).data("alarm_start_time");
		 var alarm_end_time= jQuery(rowData).data("alarm_end_time");
		 var alarm_type_name= jQuery(rowData).data("alarm_type_name");
		 var vehicle_vin=jQuery(rowData).data("vehicle_vin");
		 showpoint(vehicle_ln,alarm_start_time,alarm_end_time,vehicle_vin,alarm_type_id);
	};

	var firstRowSelected = function(firstRowData) {
		 var alarm_type_id = jQuery(firstRowData).data("alarm_type_id");
		 var vehicle_ln = jQuery(firstRowData).data("vehicle_ln");
		 var alarm_start_time= jQuery(firstRowData).data("alarm_start_time");
		 var alarm_end_time= jQuery(firstRowData).data("alarm_end_time");
		 var alarm_type_name= jQuery(firstRowData).data("alarm_type_name");
		 var vehicle_vin=jQuery(firstRowData).data("vehicle_vin");
		 showpoint(vehicle_ln,alarm_start_time,alarm_end_time,vehicle_vin,alarm_type_id);
	};

	//页面自适应
	jQuery(function() {
		jQuery(window).mk_autoresize({
			min_width: 1320
		});
		resizeBgDiv();
		auto_size();
	});

    jQuery(window).wresize(function(){
    	jQuery("#viloationDetail").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#viloationDetail").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#viloationDetail").height())/2))+"px");
    	jQuery("#viloationReport").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#viloationReport").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#viloationReport").height())/2))+"px");
	});

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
			height_exclude : [ '.treeTab', '.msg-snapshot' ],
			height_include : '.treeBox',
			height_offset : 5  //该值页面不同值不同
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
				height_include : '#lefttree',
				height_offset : 10
			});

			//计算树区域高度
			jQuery('#lefttree').mk_autoresize( {
				height_include : '#treeDemo',
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
		}else{//隐藏状态
			jQuery('#middeldiv').css("display","none");
			jQuery('#leftdiv').css("display","block");
			auto_size();
		}
	}
</script>