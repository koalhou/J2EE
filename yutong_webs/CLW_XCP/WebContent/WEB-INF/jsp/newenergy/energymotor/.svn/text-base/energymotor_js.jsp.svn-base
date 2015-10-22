<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
/**
 * 左侧树区域显示控制
 */
function HideandShowControl(){//leftdiv
	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
		jQuery('#middeldiv').css("display","block");
		jQuery('#leftdiv').css("display","none");
		//计算中区高度
		jQuery('#content').mk_autoresize( {
			height_include : [ '#content_rightside', '#content_middelside' ],
			height_offset : 0,
			width_exclude: ['#content_middelside'],
			width_include : [ '#content_rightside'],
			width_offset : 1
		});

		//计算右测区域高度
		jQuery('#content_rightside').mk_autoresize( {
			height_exclude : [ '.titleBar'],
			height_include : '#right_main',
			height_offset : 0,
			width_include : [ '.titleBar','#right_main'],
			width_offset : 0
		});
		jQuery('#right_main').mk_autoresize( {
			height_exclude : ['.car-info'],
			height_include : ['alarm_tab','.table_list'],
			height_offset : 30,
			width_include : ['.car-info','.table_list'],
			width_offset : 0
		});		
		jQuery('.table_list').mk_autoresize({
		    height_include: ['.bDiv'],
		    height_offset: 70, // 该值各页面根据自己的页面布局调整
		    width_include: '.flexigrid',
		    width_offset: 8// 该值各页面根据自己的页面布局调整
		  });			
	}else{//隐藏状态
		jQuery('#middeldiv').css("display","none");
		jQuery('#leftdiv').css("display","block");
		auto_size();
		jQuery(window).mk_autoresize({
			height_include: '#content',
			height_exclude: ['#header', '#footer'],
			height_offset: 0,
			width_include: ['#header', '#content', '#footer'],
			width_offset: 0});
		auto_size();
	}
}

function mytreeonClick(type, treeId){
	jQuery("#isSearch").val("");
	jQuery("#alarmKey").val("");
	if(type=='1'){
		selectCar(treeId);
	}else{
		selectEnt(treeId);	
	}
}


//切换告警标题
function changetab(obj,flag){
	//var changename=jQuery(obj).html();
	//jQuery('#alarmname').html(changename);
	//jQuery('#tabname').css('display','none');
	//var ln=jQuery('#vehicle_ln').val();
	jQuery("a[id^=deal]").removeClass("alarm_tabs");
	jQuery(obj).addClass("alarm_tabs");

	jQuery("div[id^=grid").css("display","none");
	
	if(flag==1){
// 		var parm={'vehicleVin': finalVIN,
// 		 		         'vehicleln': jQuery("#vehicleln").val(),
// 					     'startTime': jQuery("#startTime").val(),
// 					     'endTime': jQuery("#endTime").val(),
// 					     'routeName': jQuery("#routeName").val()=="--请选择--"?"":jQuery("#routeName").val(),
// 					     'organization_id': finalENT,
// 					     'car_state': filterFlag};
		gala1();
		jQuery("#grid1").css("display","block");
		searchMotorList('1');
	}

	if(flag==2){
// 		var parm={'vehicleVin': finalVIN,
// 	         'vehicleln': jQuery("#vehicleln").val(),
// 		     'startTime': jQuery("#startTime").val(),
// 		     'endTime': jQuery("#endTime").val(),
// 		     'routeName': jQuery("#routeName").val()=="--请选择--"?"":jQuery("#routeName").val(),
// 		     'organization_id': finalENT,
// 		     'car_state': filterFlag};
// 		load_page("<s:url value='/energyMotor/energyMotorISGBrows.shtml' />",parm);
		gala2();
		jQuery("#grid2").css("display","block");
		searchMotorList('2');
	}

	 if(flag==3){
// 		 var parm={'vehicleVin': finalVIN,
// 		         'vehicleln': jQuery("#vehicleln").val(),
// 			     'startTime': jQuery("#startTime").val(),
// 			     'endTime': jQuery("#endTime").val(),
// 			     'routeName': jQuery("#routeName").val()=="--请选择--"?"":jQuery("#routeName").val(),
// 			     'organization_id': finalENT,
// 			     'car_state': filterFlag};
// 		load_page("<s:url value='/energyMotor/energyMotorBMSBrows.shtml'/>",parm);
		gala3();
		jQuery("#grid3").css("display","block");
		 searchMotorList('3');
	 }

	 if(flag==4){
// 		 var parm={'vehicleVin': finalVIN,
// 		         'vehicleln': jQuery("#vehicleln").val(),
// 			     'startTime': jQuery("#startTime").val(),
// 			     'endTime': jQuery("#endTime").val(),
// 			     'routeName': jQuery("#routeName").val()=="--请选择--"?"":jQuery("#routeName").val(),
// 			     'organization_id': finalENT,
// 			     'car_state': filterFlag};
// 	     load_page("<s:url value='/energyBattery/energyBatteryBrows.shtml'/>",parm); 
		gala4();
		jQuery("#grid4").css("display","block");
		 searchMotorList('4');
	 }
	 
	 if(flag==5){
// 		 var parm={'vehicleVin': finalVIN,
// 		         'vehicleln': jQuery("#vehicleln").val(),
// 			     'startTime': jQuery("#startTime").val(),
// 			     'endTime': jQuery("#endTime").val(),
// 			     'routeName': jQuery("#routeName").val()=="--请选择--"?"":jQuery("#routeName").val(),
// 			     'organization_id': finalENT,
// 			     'car_state': filterFlag};
// 	     load_page("<s:url value='/energyBattery/energyBatteryBMSBrows.shtml'/>",parm); 
		gala5();
		jQuery("#grid5").css("display","block");
		 searchMotorList('5');
	 }
	 
	 if(flag==6){
// 		 var parm={'vehicleVin': finalVIN,
// 		         'vehicleln': jQuery("#vehicleln").val(),
// 			     'startTime': jQuery("#startTime").val(),
// 			     'endTime': jQuery("#endTime").val(),
// 			     'routeName': jQuery("#routeName").val()=="--请选择--"?"":jQuery("#routeName").val(),
// 			     'organization_id': finalENT,
// 			     'car_state': filterFlag};
// 	     load_page("<s:url value='/energyBattery/energyBatteryOtherBrows.shtml' />",parm); 
		gala6();
		jQuery("#grid6").css("display","block");
		 searchMotorList('6');
	 }
}


function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

var pomSel4 = null;
jQuery(function() {
	pomSel4 = new newSelect(jQuery("#routeName"),120);
	pomSel4.addSelect();
	getRouteSel();
	gala1();	 
});

function gala1(){
	var galaList = jQuery('#galaList1');
	 galaList.flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width: 1500,
		  colModel : [ 
				{display: 'id', name : 'id', width : 25, sortable : false, align: 'center', hide: true,escape: true},
				{display: 'id', name : 'vehicle_vin', width : 25, sortable : false, align: 'center', hide: true,escape: true},
		        {display: '', name : 'longitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'latitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'speed', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'route_name', width : 200, sortable : false, align: 'center', hide: true,escape: true},
				{display: '车牌号', name : 'vehicle_ln', width : 120, sortable : true, align: 'center',escape: true},
				{display: '主电机工作模式', name : 'main_mode', width : 110, sortable : true, align: 'center',escape: true,process:reWriteLinkControl},
				{display: '主电机当前输出转矩/Nm', name : 'MAIN_TORQUE_OUT', width : 140, sortable : true, align: 'center',escape: true},
				{display: '主电机当前转速/rpm', name : 'MAIN_RATOTE', width : 130, sortable : true, align: 'center',escape: true},							  
		        {display: '主电机温度/℃', name : 'MAIN_TEMP', width : 110, sortable : true, align: 'center',escape: true},							  
		        {display: '主电机控制器温度/℃', name : 'MAIN_CONTROLLER_TEMP', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: '主电机控制器母线电流/A', name : 'MAIN_CONTROLLER_C', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: '主电机控制器母线电压/V', name : 'MAIN_CONTROLLER_V', width : 150, sortable : true, align: 'center',escape: true},						  
		        {display: '主电机控制器实际接收扭矩', name : 'MAIN_CONTROLLER_TORQUE_IN', width : 160, sortable : true, align: 'center',escape: true},							  
		        {display: '时间', name : 'terminal_time', width : 120, sortable : true, align: 'center',escape: true}
		        ], 
		       sortname: 'terminal_time',
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true   // 是否允许显示隐藏列
	     }); 
	 searchMotorList('1');
}
function reWriteLinkControl(tdDiv,pid,row){
	var tdV = "";
	var record = row.cell[7];
	
	if( record == "100"){
		tdV = record+"(复位)";
	} else if(record == "001"){
		tdV = record+"(驱动)";
	} else if(record == "010"){
		tdV = record+"(发电)";
	} else if(record == "011"){
		tdV = record+"(待机)";
	} else if(record == "101"){
		tdV = record+"高压急断";
	} else if(record == "110"){
		tdV = record+"已停机";
	} else {
		tdV = record;
	}
	return tdV;	
}
function gala2(){
	var galaList = jQuery('#galaList2');
	 galaList.flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width: 1500,
		  colModel : [ 
				{display: 'id', name : 'id', width : 25, sortable : false, align: 'center', hide: true,escape: true},
				{display: 'id', name : 'vehicle_vin', width : 25, sortable : false, align: 'center', hide: true,escape: true},
		        {display: '', name : 'longitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'latitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'speed', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'route_name', width : 200, sortable : false, align: 'center', hide: true,escape: true},
				{display: '车牌号', name : 'vehicle_ln', width : 120, sortable : true, align: 'center',escape: true},
				{display: 'ISG工作模式', name : 'ISG_mode', width : 110, sortable : true, align: 'center',escape: true,process: reWriteLinkISG},
				{display: 'ISG电机当前输出转矩/Nm', name : 'ISG_TORQUE_OUT', width : 140, sortable : true, align: 'center',escape: true},
				{display: 'ISG电机当前转速/rpm', name : 'ISG_RATOTE', width : 130, sortable : true, align: 'center',escape: true},							  
		        {display: 'ISG电机温度/℃', name : 'ISG_TEMP', width : 110, sortable : true, align: 'center',escape: true},							  
		        {display: 'ISG电机控制器温度/℃', name : 'ISG_CONTROLLER_TEMP', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: 'ISG电机控制器母线电流/A', name : 'ISG_CONTROLLER_C', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: 'ISG电机控制器母线电压/V', name : 'ISG_CONTROLLER_V', width : 150, sortable : true, align: 'center',escape: true},						  
		        {display: 'ISG电机控制器实际接收扭矩', name : 'ISG_CONTROLLER_TORQUE_IN', width : 160, sortable : true, align: 'center',escape: true},							  
		        {display: '时间', name : 'terminal_time', width : 120, sortable : true, align: 'center',escape: true}
		        ], 
		       sortname: 'terminal_time',
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true   // 是否允许显示隐藏列
	     }); 
	 searchMotorList('2');
}
function reWriteLinkISG(tdDiv,pid,row){
	var tdV = "";
	var record = row.cell[7];
	
	if( record == "100"){
		tdV = record+"(复位)";
	} else if(record == "001"){
		tdV = record+"(驱动)";
	} else if(record == "010"){
		tdV = record+"(发电)";
	} else if(record == "011"){
		tdV = record+"(待机)";
	} else if(record == "110"){
		tdV = record+"已停机";
	} else {
		tdV = record;
	}
	return tdV;	
}
function gala3(){
	var galaList = jQuery('#galaList3');
	 galaList.flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width: 1500,
		  colModel : [ 
				{display: 'id', name : 'id', width : 25, sortable : false, align: 'center', hide: true,escape: true},
				{display: 'id', name : 'vehicle_vin', width : 25, sortable : false, align: 'center', hide: true,escape: true},
		        {display: '', name : 'longitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'latitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'speed', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'route_name', width : 200, sortable : false, align: 'center', hide: true,escape: true},
				{display: '车牌号', name : 'vehicle_ln', width : 120, sortable : true, align: 'center',escape: true},
				{display: '电池系统绝缘电阻/KΩ', name : 'BAT_RESISTANCE', width : 120, sortable : true, align: 'center',escape: true},
				{display: '电池高压正极绝缘电阻/KΩ', name : 'BAT_RESISTANCE_A', width : 150, sortable : true, align: 'center',escape: true},
				{display: '电池高压负极绝缘电阻/KΩ', name : 'BAT_RESISTANCE_N', width : 140, sortable : true, align: 'center',escape: true},							  
		        {display: '电容系统绝缘电阻/KΩ', name : 'CAP_RESISTANCE', width : 120, sortable : true, align: 'center',escape: true},							  
		        {display: '电容高压正极绝缘电阻/KΩ', name : 'CAP_RESISTANCE_A', width : 160, sortable : true, align: 'center',escape: true},							  
		        {display: '电容高压负极绝缘电阻/KΩ', name : 'CAP_RESISTANCE_N', width : 160, sortable : true, align: 'center',escape: true},							  
		        {display: '时间', name : 'terminal_time', width : 120, sortable : true, align: 'center',escape: true}
		        ], 
		       sortname: 'terminal_time',
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true   // 是否允许显示隐藏列
	     }); 
	 searchMotorList('3');
}
function gala4(){
	var galaList = jQuery('#galaList4');
	 galaList.flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width: 1500,
		  colModel : [ 
				{display: 'id', name : 'id', width : 25, sortable : false, align: 'center', hide: true,escape: true},
				{display: 'id', name : 'vehicle_vin', width : 25, sortable : false, align: 'center', hide: true,escape: true},
		        {display: '', name : 'longitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'latitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'speed', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'route_name', width : 200, sortable : false, align: 'center', hide: true,escape: true},
				{display: '车牌号', name : 'vehicle_ln', width : 120, sortable : true, align: 'center',escape: true},
				{display: '单体电容最高电压/V', name : 'scap_highv', width : 110, sortable : true, align: 'center',escape: true},
				{display: '单体电容最低电压/V', name : 'scap_lowv', width : 140, sortable : true, align: 'center',escape: true},
				{display: '电容系统单体电压差/V', name : 'scap_v_diff', width : 130, sortable : true, align: 'center',escape: true},							  
		        {display: '电容最高电压电芯号', name : 'CAP_HIGHV_NUM', width : 110, sortable : true, align: 'center',escape: true},							  
		        {display: '电容最低电压电芯号', name : 'CAP_LOWV_NUM', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: '单体电容最高温度/℃', name : 'scap_temp_high', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: '单体电容平均电压/Ｖ', name : 'SCAP_AVG_V', width : 150, sortable : true, align: 'center',escape: true},
		        {display: '单体过压报警模块号', name : 'soverv_alarm_num', width : 150, sortable : true, align: 'center',escape: true},						  
		        {display: '最高温度单体电容号', name : 'scap_temp_high_num', width : 160, sortable : true, align: 'center',escape: true},			  
		        {display: '单体电容最低温度/℃', name : 'scap_temp_low', width : 160, sortable : true, align: 'center',escape: true},			  
		        {display: '最低温度单体电容号', name : 'scap_temp_low_num', width : 160, sortable : true, align: 'center',escape: true},			  
		        {display: '时间', name : 'terminal_time', width : 120, sortable : true, align: 'center',escape: true}
		        ], 
		       sortname: 'terminal_time',
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true   // 是否允许显示隐藏列
	     }); 
	 searchMotorList('4');
}
function gala5(){
	var galaList = jQuery('#galaList5');
	 galaList.flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width: 1500,
		  colModel : [ 
				{display: 'id', name : 'id', width : 25, sortable : false, align: 'center', hide: true,escape: true},
				{display: 'id', name : 'vehicle_vin', width : 25, sortable : false, align: 'center', hide: true,escape: true},
		        {display: '', name : 'longitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'latitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'speed', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'route_name', width : 200, sortable : false, align: 'center', hide: true,escape: true},
				{display: '车牌号', name : 'vehicle_ln', width : 120, sortable : true, align: 'center',escape: true},
				{display: '电池容量SOC', name : 'BAT_SOC', width : 110, sortable : true, align: 'center',escape: true},
				{display: '电池总电流/A', name : 'BAT_CURRENT', width : 110, sortable : true, align: 'center',escape: true},
				{display: '电池总电压/V', name : 'BAT_VOLTAGE', width : 110, sortable : true, align: 'center',escape: true},
				{display: '放电电流限制', name : 'DISCHARGE_LIMIT', width : 110, sortable : true, align: 'center',escape: true},
				{display: '充电电流限制', name : 'CHARGE_LIMIT', width : 140, sortable : true, align: 'center',escape: true},
				{display: '单体电池最高电压/V', name : 'SBAT_V_HIGH', width : 140, sortable : true, align: 'center',escape: true},
				{display: '单体电池最低电压/V', name : 'SBAT_V_LOW', width : 140, sortable : true, align: 'center',escape: true},
				{display: '电池系统单体电压差/V', name : 'sbat_v_diff', width : 130, sortable : true, align: 'center',escape: true},							  
		        {display: '电池最高电压电芯号', name : 'bat_highv_num', width : 110, sortable : true, align: 'center',escape: true},							  
		        {display: '电池最低电压电芯号', name : 'bat_lowv_num', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: '单体电池最高温度/℃', name : 'sbat_temp_high', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: '单体过压报警模块号', name : 'soverv_alarm_num', width : 150, sortable : true, align: 'center',escape: true},						  
		        {display: '最高温度单体电池号', name : 'sbat_temp_high_num', width : 160, sortable : true, align: 'center',escape: true},			  
		        {display: '单体电池最低温度/℃', name : 'sbat_temp_low', width : 160, sortable : true, align: 'center',escape: true},			  
		        {display: '最低温度单体电池号', name : 'sbat_temp_low_num', width : 160, sortable : true, align: 'center',escape: true},		  
		        {display: '时间', name : 'terminal_time', width : 120, sortable : true, align: 'center',escape: true}
		        ], 
		       sortname: 'terminal_time',
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true   // 是否允许显示隐藏列
	     }); 
	 searchMotorList('5');
}
function gala6(){
	var galaList = jQuery('#galaList6');
	 galaList.flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width: 1500,
		  colModel : [ 
				{display: 'id', name : 'id', width : 25, sortable : false, align: 'center', hide: true,escape: true},
				{display: 'id', name : 'vehicle_vin', width : 25, sortable : false, align: 'center', hide: true,escape: true},
		        {display: '', name : 'longitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'latitude', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'speed', width : 200, sortable : false, align: 'center', hide: true,escape: true},							  
		        {display: '', name : 'route_name', width : 200, sortable : false, align: 'center', hide: true,escape: true},
				{display: '车牌号', name : 'vehicle_ln', width : 120, sortable : true, align: 'center',escape: true},
				{display: '档位', name : 'gears', width : 80, sortable : true, align: 'center',escape: true,process: reWriteLinkOther},
				{display: '离合器状态', name : 'clutch_State', width : 110, sortable : true, align: 'center',escape: true,process: reWriteLinkOther1},
				{display: '手刹信号', name : 'handbrake_Signal', width : 110, sortable : true, align: 'center',escape: true,process: reWriteLinkOther2},
				{display: '脚刹信号', name : 'footbrake_Signal', width : 110, sortable : true, align: 'center',escape: true,process: reWriteLinkOther3},
				{display: 'ON挡信号', name : 'on_state', width : 140, sortable : true, align: 'center',escape: true,process: reWriteLinkOther4},
				{display: '加速踏板电压值%', name : 'acceleratorpedal_Voltage', width : 130, sortable : true, align: 'center',escape: true,process: reWriteLinkOther5},							  
		        {display: '制动踏板电压值', name : 'brakepedal_voltage', width : 110, sortable : true, align: 'center',escape: true,process: reWriteLinkOther6},							  
		        {display: '发动机指令扭矩百分比', name : 'DRIVER_COMMAND_TORQUE', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: '发动机实际扭矩百分比', name : 'e_torque', width : 150, sortable : true, align: 'center',escape: true},			
		        
		        {display: '输出轴转速', name : 'DRIVER_COMMAND_TORQUE', width : 150, sortable : true, align: 'center',escape: true},							  
		        {display: '当前齿轮数', name : 'e_torque', width : 150, sortable : true, align: 'center',escape: true},			
		        
		        
//		        {display: '单体过压报警模块号', name : 'soverv_alarm_num', width : 150, sortable : true, align: 'center',escape: true},						  
//		        {display: '最高温度单体电池号', name : 'scap_temp_high_num', width : 160, sortable : true, align: 'center',escape: true},			  
//		        {display: '单体电池最低温度/℃', name : 'scap_temp_low', width : 160, sortable : true, align: 'center',escape: true},			  
//		        {display: '最低温度单体电池号', name : 'scap_temp_low_num', width : 160, sortable : true, align: 'center',escape: true},		  
		        {display: '时间', name : 'terminal_time', width : 120, sortable : true, align: 'center',escape: true}
		        ], 
		       sortname: 'terminal_time',
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true   // 是否允许显示隐藏列
	     }); 
	 searchMotorList('6');
}

function reWriteLinkOther(tdDiv,pid,row){
	var tdV = "";
	var record = row.cell[7];
	
	if( record == "000"){
		tdV = record+"(空档)";
	} else if(record == "001"){
		tdV = record+"(前进)";
	} else if(record == "010"){
		tdV = record+"(倒档)";
	} else if(record == "011"){
		tdV = record+"(低速)";
	} else {
		tdV = record;
	}
	return tdV;	
}
function reWriteLinkOther1(tdDiv,pid,row){
	var tdV = "";
	var record = row.cell[8];
	
	if( record == "01"){
		tdV = record+"(分离)";
	} else if(record == "10"){
		tdV = record+"(结合)";
	} else {
		tdV = record;
	}
	return tdV;	
}
function reWriteLinkOther2(tdDiv,pid,row){
	var tdV = "";
	var record = row.cell[9];
	
	if( record == "00"){
		tdV = record+"(拉手刹)";
	} else if(record == "01"){
		tdV = record+"(松手刹)";
	} else {
		tdV = record;
	}
	return tdV;	
}
function reWriteLinkOther3(tdDiv,pid,row){
	var tdV = "";
	var record = row.cell[10];
	
	if( record == "00"){
		tdV = record+"(无信号)";
	} else if(record == "01"){
		tdV = record+"(制动灯信号)";
	} else {
		tdV = record;
	}
	return tdV;	
}
function reWriteLinkOther4(tdDiv,pid,row){
	var tdV = "";
	var record = row.cell[11];
	
	if( record == "00"){
		tdV = record+"(无信号)";
	} else if(record == "01"){
		tdV = record+"(处于on火)";
	} else {
		tdV = record;
	}
	return tdV;	
}
function reWriteLinkOther5(tdDiv,pid,row){
	var tdV = "";
	var record = row.cell[12];
	
	if( record == "FF"){
		tdV = record;
	} else{
		tdV = record;
	}
	return tdV;	
}
function reWriteLinkOther6(tdDiv,pid,row){
	var tdV = "";
	var record = row.cell[13];
	
	if( record == "FF"){
		tdV = record;
	} else {
		tdV = record;
	}
	return tdV;	
}

function getRouteSel(){
	jQuery.ajax({
		  type: 'POST',  
		  url: '<s:url value="/routeGrid/getUserRouteFind.shtml" />',	
		  data: {},	
		 success: function(data) {
			 if(data.message == "success"){
	 			var routeList = data.list;
	 			for(var i = 0,len = routeList.length; i < len; i++){
	 				var objs = routeList[i];
	 				pomSel4.addOption(objs.route_id,objs.route_name);
	 			}
	 			pomSel4.seBindOption(function(){searchMotorList();});
			 } else {
				 
			 }
	      },
		  error:function (){
	      }
	});
}

function calcColumn(persent, minwidth, offset) {
    var pagewidth = 0;
    
    var width = 0;
    if(jQuery.browser.msie){ 
       pagewidth = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
    } else { 
       pagewidth = self.innerWidth; 
    } 
    if(offset > 0) {
       width = pagewidth - offset;
    } else {
       width = pagewidth;
    }
    return width*persent < minwidth ? minwidth : width*persent;
}



jQuery(function() {
	auto_size();	
	jQuery(window).mk_autoresize({
		height_include: '#content',
		height_exclude: ['#header', '#footer'],
		height_offset: 0,
		width_include: ['#header', '#content', '#footer'],
		width_offset: 0});
	auto_size();
});

function auto_size(){
	jQuery(window).mk_autoresize({
		min_width: 1170
	  });
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
		height_exclude : [ '#leftInfoDiv', '.newsearchPlan'],
		height_include : '#lefttree',
		height_offset : 10

	});

	//计算树区域高度
	//jQuery('#lefttree').mk_autoresize( {
	//	height_include : '#treeDemo',
	//	height_offset : 20
	//});


	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize( {
		height_exclude : [ '.titleBar'],
		height_include : '#right_main',
		height_offset : 0,
		width_include : [ '.titleBar','#right_main'],
		width_offset : 0
	});
	jQuery('#right_main').mk_autoresize( {
		height_exclude : ['.car-info'],
		height_include : ['alarm_tab','.table_list'],
		height_offset : 30,
		width_include : ['.car-info','.table_list'],
		width_offset : 0
	});		
	jQuery('.table_list').mk_autoresize({
	    height_include: ['.bDiv'],
	    height_offset: 70, // 该值各页面根据自己的页面布局调整
	    width_include: '.flexigrid',
	    width_offset: 8// 该值各页面根据自己的页面布局调整
	  });
}

function keyEnter(event){
	var e = event ? event : window.event;
	if(e.keyCode=='13'){
		searchMotorList();
	}
}

//查询
function searchMotorList(tabs) {
	var filterFlag = "";
	var url = "";
	if(jQuery("#filterFlag").attr("checked")){
		filterFlag = "1";
	}
	var ids = jQuery("a[class=alarm_tabs]").attr("id");
	if(tabs=='1' || ids=='deal1'){
		url = "<s:url value='/energyMotor/getMontorControlList.shtml' />";
		tabs = '1';
	} else if(tabs=='2' || ids=='deal2'){
		url = "<s:url value='/energyMotor/getMontorISGList.shtml' />";
		tabs = '2';
	} else if(tabs=='3' || ids=='deal3'){
		url = "<s:url value='/energyMotor/getMontorBMSList.shtml' />";
		tabs = '3';
	} else if(tabs=='4' || ids=='deal4'){
		url = "<s:url value='/energyBattery/getBatteryList.shtml' />";
		tabs = '4';
	} else if(tabs=='5' || ids=='deal5'){
		url = "<s:url value='/energyBattery/getBatteryBMSList.shtml' />";
		tabs = '5';
	} else if(tabs=='6' || ids=='deal6'){
		url = "<s:url value='/energyBattery/getBatteryOtherList.shtml' />";
		tabs = '6';
	}
	jQuery('#galaList'+tabs).flexOptions({
		newp: 1,
		url: url,
		params: [{name: 'vehicleVin', value: finalVIN },
		         {name: 'vehicleln',value: jQuery("#vehicleln").val()},
			     {name: 'startTime', value: jQuery("#startTime").val()},
			     {name: 'endTime', value: jQuery("#endTime").val()},
			     {name: 'routeName',value: jQuery("#routeName").val()=="--请选择--"?"":jQuery("#routeName").val()},
			     {name: 'organization_id',value: finalENT},
			     {name: 'car_state',value: filterFlag}]
	});
	jQuery('#galaList'+tabs).flexReload();
}

function exportData(){
		var url = "";
		var txt = "";
		var ids = jQuery("a[class=alarm_tabs]").attr("id");
		if(ids == "deal1"){
			url = "<s:url value='/energyMotor/exportMontorControl.shtml' />";
			txt = "确定要导出主电机控制器状态信息吗？";
		} else if(ids == "deal2"){
			url = "<s:url value='/energyMotor/exportMontorISG.shtml' />";
			txt = "确定要导出ISG电机状态信息吗？";
		} else if(ids == "deal3"){
			url = "<s:url value='/energyMotor/exportMontorBMS.shtml' />";
			txt = "确定要导出BMS和CMS绝缘电阻信息吗？";
		} else if(ids == "deal4"){
			url = "<s:url value='/energyBattery/exportBatteryList.shtml' />";
			txt = "确定要导出CMS系统管理信息吗？";
		} else if(ids == "deal5"){
			url = "<s:url value='/energyBattery/exportBatteryBMSList.shtml' />";
			txt = "确定要导出BMS系统管理信息吗？";
		} else if(ids == "deal6"){
			url = "<s:url value='/energyBattery/exportBatteryOtherList.shtml' />";
			txt = "确定要导出新能源其他信息吗？";
		}
		if(confirm(txt)) {
			jQuery("#organization_id").val(finalENT);
			jQuery("#vehicleVin").val(finalVIN);
			document.getElementById('vehiclestatus_form').action=url;
			document.getElementById('vehiclestatus_form').submit();
		}
}



//选择车辆后，刷新车辆运行日志数据
var finalVIN = "";
var finalENT = "";
function selectCar(radioObj){
	finalVIN = radioObj;
	finalENT = "";
	searchMotorList();
}
function selectEnt(radioObj){
	finalVIN = "";
	finalENT = radioObj;
	searchMotorList();
}	

function pickedstarttime(){
	var date1 = document.getElementById('startTime').value + ":00";
	var date2 = document.getElementById('endTime').value + ":00";
    var line_start_time=date1.replace(/-/g,"/");
    var line_end_time=date2.replace(/-/g,"/");
    
	var startdate=new Date(line_start_time);
	var enddate=new Date(line_end_time);
	var iDays = parseInt((enddate - startdate) / 1000 / 60 / 60 / 24);
	
	if(iDays < 0 || iDays > 6){
		enddate.setTime(startdate.getTime() + 1000*60*60*24*7);
	}
	var str1 = startdate.pattern("yyyy/MM/dd");
	var str2 = enddate.pattern("yyyy/MM/dd");
	//if(str1 != str2){
		enddate = new Date(str1+" 23:59");
	//}
	$dp.$('endTime').value=enddate.pattern("yyyy-MM-dd HH:mm") ;
}
function pickedendtime(){
	var date1 = document.getElementById('startTime').value + ":00";
	var date2 = document.getElementById('endTime').value + ":00";
    var line_start_time=date1.replace(/-/g,"/");
    var line_end_time=date2.replace(/-/g,"/");

	var startdate=new Date(line_start_time);
	var enddate=new Date(line_end_time);
	var iDays = parseInt((enddate - startdate) / 1000 / 60 / 60 / 24);

	if(iDays < 0 || iDays > 6){
		startdate.setTime(enddate.getTime() - 1000*60*60*24*7);
	}
// 	var str1 = startdate.pattern("yyyy/MM/dd");
// 	var str2 = enddate.pattern("yyyy/MM/dd");
// 	if(str1 != str2){
// 		startdate = new Date(str2+" 00:00:00");
// 	}
	$dp.$('startTime').value=startdate.pattern("yyyy-MM-dd HH:mm") ;
}
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
</script>
