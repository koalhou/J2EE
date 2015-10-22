	<%@page language="java" contentType="text/html;charset=utf-8"%>
	<script type='text/javascript' src='../dwr/engine.js'></script>
	<script type='text/javascript' src='../dwr/util.js'></script>
	<script type='text/javascript' src='../dwr/interface/StuRidingDWR.js'></script>
	<script type="text/javascript">	
	jQuery(function() {
		jQuery('#gala').flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width:1500,
		  onSuccess: function() {//获取数据结束时执行  
			  jQuery('#gala tr').each(function(i,n){
				   if(jQuery(n).text().indexOf('乘车异常')>-1){jQuery(n).find('td').css({ color: "red" });}
				   var t=jQuery(n).children('td').eq(8);t.attr('title',t.text());
				});
		    },
		  colModel : [
				{display: '姓名', name : "NLSSORT(STU_NAME,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.05,60,238), sortable : true, align: 'center',escape: true},
				//{display: '学校', name : 'STU_SCHOOL', width : calcColumn(0.1,60,238), sortable : true, align: 'center',escape: true},
				//{display: '班级', name : 'STU_CLASS', width : calcColumn(0.05,60,238), sortable : true, align: 'center',escape: true},
				{display: '员工号', name : "NLSSORT(STU_CODE,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.1,60,238), sortable : true, align: 'center',escape: true},
				{display: '卡号', name : 'STU_CARD_ID', width : calcColumn(0.1,60,238), sortable : true, align: 'center',escape: true},
				{display: '所属组织', name : "NLSSORT(SHORT_NAME,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.05,60,238), sortable : true, align: 'center',escape: true},
				{display: '班车号', name : 'VEHICLE_CODE', width : calcColumn(0.05,60,238), sortable : true, align: 'center',escape: true},	
				{display: '车牌号', name : "NLSSORT(VEHICLE_LN,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.05,60,238), sortable : true, align: 'center',escape: true},										   											   					   					   											   					   
				{display: '驾驶员', name : "NLSSORT(driver_name,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.05,50,238), sortable : true, align: 'center',escape: true},
				{display: '应乘车辆', name : "NLSSORT(VEHICLE_LN,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.07,50,238), sortable : false, align: 'center',escape: true},
				{display: '乘车状态', name : "NLSSORT(PLAN_STRIDE_FLAG,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.05,50,238), sortable : true, align: 'center',escape: true},
				{display: '刷卡时间', name : 'TERMINAL_TIME', width : calcColumn(0.1,90,238), sortable : true, align: 'center',escape: true},
				{display: '刷卡地点', name : "NLSSORT(ZONENAME,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.25,100,238), sortable : true, align: 'center',escape: true}
				//{display: '状态', name : 'ST_RIDE_FLAG', width : calcColumn(0.05,60,238), sortable : true,align: 'center',escape: true},	
				//{display: '时间', name : 'TERMINAL_TIME', width : calcColumn(0.1,130,238), sortable : true, align: 'center',escape: true},	
				//{display: '跟车教师', name : 'SICHEN_NAME', width : calcColumn(0.05,50,238), sortable : true, align: 'center',escape: true}
		        ],
		       sortname: 'TERMINAL_TIME',
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true  // 是否允许显示隐藏列
		       //onRowSelect:rowstandbyclick //获取单行数据的方法
		     });	
		//触发查询方法
		searchList();
	});		
	function mytreeonClick(event, treeId, treeNode){
		document.getElementById('chooseorgid').value=treeNode.id;
		selectName=treeNode.name;
		searchList();
	}
	//设置详细信息显示
	/*var rowstandbyclick =function(rowData) {
		 jQuery("#stu_name_v").val(jQuery(rowData).data("STU_NAME"));
		 jQuery("#stu_school_v").val(jQuery(rowData).data("STU_SCHOOL"));
		 //jQuery("#stu_class_v").val(jQuery(rowData).data("STU_CLASS"));
		 jQuery("#stu_code_v").val(jQuery(rowData).data("STU_CODE"));
		 jQuery("#site_name_v").val(jQuery(rowData).data("SITE_NAME"));
		 jQuery("#route_name_v").val(jQuery(rowData).data("ROUTE_NAME"));
		 jQuery("#st_ride_flag_v").val(jQuery(rowData).data("ST_RIDE_FLAG"));
		 jQuery("#terminal_time_v").val(jQuery(rowData).data("TERMINAL_TIME"));
		 jQuery("#vehicle_ln_v").val(jQuery(rowData).data("VEHICLE_LN"));
		 jQuery("#driver_name_v").val(jQuery(rowData).data("DRIVER_NAME"));
		 jQuery("#sichen_name_v").val(jQuery(rowData).data("SICHEN_NAME"));
	};*/
	
	/*//重置详情信息
	function reSetRowInfo(){
		 jQuery("#stu_name_v").val("");
		 jQuery("#stu_school_v").val("");
		 //jQuery("#stu_class_v").val("");
		 jQuery("#stu_code_v").val("");
		 jQuery("#site_name_v").val("");
		 jQuery("#route_name_v").val("");
		 jQuery("#st_ride_flag_v").val("");
		 jQuery("#terminal_time_v").val("");
		 jQuery("#vehicle_ln_v").val("");
		 jQuery("#driver_name_v").val("");
		 jQuery("#sichen_name_v").val("");
	}*/
	
	
	//查询条件
	var stu_name = "";
	//var VIN = "";
	var begTime = "";
	var endTime = "";
	//var site_id = "";
	//var stu_school = "";
	var stu_code = "";
	//var stu_class = "";
	//var st_ride_flag = "";
			
	function searchList() {
	//姓名
	stu_name = formatSpecialChar(jQuery('#stu_name').val());

	//结束时间
	endTime = jQuery('#endTime').val();
	//开始时间
	begTime = jQuery('#begTime').val();
	//站点名称
	//site_id = jQuery('#selectSite').val();
	//员工号
	stu_code = formatSpecialChar(jQuery('#stu_code').val());
	//班级
	//stu_class = formatSpecialChar(jQuery('#stu_class').val());
	//状态
	//根据单选框选择情况 设置状态标识
	//setStRideFlag();
	//st_ride_flag = jQuery('#st_ride_flag').val();
				jQuery('#gala').flexOptions({
					newp: 1,
					  url: '<s:url value="/st_ride/getRideInfo.shtml"/>',
					  params: [
					           //{name: 'queryObj.VIN', value: VIN},
					           {name: 'queryObj.organization_id', value: jQuery('#chooseorgid').val()},
					           {name: 'queryObj.vehicle_code', value: formatSpecialChar(jQuery('#vehicle_code').val())},
					           {name: 'queryObj.vehicle_ln', value: formatSpecialChar(jQuery('#vehicle_ln').val())},
					           //{name: 'queryObj.st_ride_flag', value: st_ride_flag},
					           {name: 'queryObj.stu_name', value: stu_name},
					           //{name: 'queryObj.stu_class', value: stu_class},
						       {name: 'queryObj.stu_code', value: stu_code},
						       //{name: 'queryObj.site_id', value: site_id},
						       {name: 'queryObj.begTime', value: begTime},
						       {name: 'queryObj.endTime', value: endTime},
						       {name: 'queryObj.strideState', value: jQuery('#strideState').val()}
						       ]					
				});
				jQuery('#gala').flexReload();
	}
				
	
	
	function isNull(data){
		if(data == null || data == ""){
			return true;
		}
		return false;		
	}
	function exportData(){	
		if(jQuery("#gala").find("td").length == 0){
			alert("没有员工刷卡记录!");
			return;	
		}
		if(jQuery("#gala").flex_totalc > 50000){
			alert("无法导出，系统最多可一次导出5W条记录!");
			return;	
		}
		if(confirm("确定要导出员工刷卡记录吗？")) {
			document.getElementById('queryObj.organization_id').value = jQuery('#chooseorgid').val();
			document.getElementById('queryObj.vehicle_code').value = formatSpecialChar(jQuery('#vehicle_code').val());
			document.getElementById('queryObj.vehicle_ln').value = formatSpecialChar(jQuery('#vehicle_ln').val());
			document.getElementById('queryObj.stu_name').value = formatSpecialChar(jQuery('#stu_name').val());	
			
			document.getElementById('queryObj.stu_code').value = formatSpecialChar(jQuery('#stu_code').val());
			document.getElementById('queryObj.begTime').value = jQuery('#begTime').val();	
			document.getElementById('queryObj.endTime').value = jQuery('#endTime').val();
			document.getElementById('queryObj.sortname').value = jQuery('#gala').flex_sortname();	
			document.getElementById('queryObj.sortorder').value = jQuery('#gala').flex_sortorder();	
			document.getElementById('queryObj.strideState').value =  jQuery('#strideState').val();
			
			document.getElementById('export_form').action="<s:url value='/stride/exportStRide.shtml' />";
			document.getElementById('export_form').submit();
		}
	}
	

	/*//更新站点列表
	function updateSiteSelect(){
		if(!isNull(chart_route_id)){
			StuRidingDWR.getSiteByRoute(chart_route_id,backFun_getSiteSelect);
		}else{
			//site_id = "";
			document.getElementById('selectSite').value="";
		}	
	}*/
	/*
	function backFun_getSiteSelect(siteList){
		DWRUtil.removeAllOptions("selectSite");
		var all = "请选择站点";
		DWRUtil.addOptions("selectSite",{"":all});		
		if(null!=siteList && siteList.length>0){
			DWRUtil.addOptions("selectSite",siteList,"site_id","site_name");
		}
		site_id = "";
		document.getElementById('selectSite').value="";
	}*/
	/*
	function setStRideFlag() {
	
		var st_ride_flag_1 = $('st_ride_flag_1');
		var st_ride_flag_2 = $('st_ride_flag_2');
		var st_ride_flag_3 = $('st_ride_flag_3');
		var st_ride_flag_4 = $('st_ride_flag_4');
		
		var srf_v_1 = "0";
		
		if(st_ride_flag_1.checked){
			srf_v_1 = "1";
		}
		
		var srf_v_2 = "0";
		if(st_ride_flag_2.checked){
			srf_v_2 = "1";
		}
		
		var srf_v_3 = "0";
		if(st_ride_flag_3.checked){
			srf_v_3 = "1";
		}
		
		var srf_v_4 = "0";
		if(st_ride_flag_4.checked){
			srf_v_4 = "1";
		}
		
		var srf_v = srf_v_1+srf_v_2+srf_v_3+srf_v_4;
		//设置给st_ride_flag
		document.getElementById('st_ride_flag').value=srf_v;
	}*/
	
	//左侧树，查询线路
	function searchRoute() {
		routename = jQuery("#search_route_name").val();
		searchTree();
	}
	
	jQuery(function() {
		auto_size();
		//重新刷新页面 适配IE9的高分辨率
		jQuery(window).mk_autoresize({
			height_include: '#content',
			height_exclude: ['#header', '#footer'],
			height_offset: 0,
			width_include: ['#header', '#content', '#footer'],
			width_offset: 0});
		auto_size();
	});
	function auto_size(){
		//jQuery(window).mk_autoresize({
		//	min_width: 1200
		//  });
		
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
			height_exclude : [ '.treeTab', '.newsearchPlan' ],
			height_include : '#treeDemoDiv',
			height_offset : 10
		});
		//计算树区域高度
		//jQuery('#treeDemoDiv').mk_autoresize( {
		//	height_include : [ '#treeDemo' ],
		//	height_offset : 20
		//});	
	
		//计算右测区域高度
		jQuery('#content_rightside').mk_autoresize( {
			height_exclude : [ '.titleBar' ,'#statusManger','#info_stu_div'],
			height_include : '.search_condition',
			height_offset : 0,
			width_include : [ '.titleBar','#statusManger','.search_condition','#info_stu_div'],
			width_offset : 0
		});
		jQuery('.search_condition').mk_autoresize( {
			height_exclude : [ '#search_stu_tab'],
			height_include : '.table_list',
			height_offset : 0,
			width_include : [ '.table_list','#search_stu_tab'],
			width_offset : 0
		});		
		jQuery('.table_list').mk_autoresize({
		    height_include: '.bDiv',
		    height_offset: 78, // 该值各页面根据自己的页面布局调整
		    width_include: '.flexigrid',
		    width_offset: 8// 该值各页面根据自己的页面布局调整
		  });
		jQuery(window).mk_autoresize({
			height_include: '#content',
			height_exclude: ['#header', '#footer'],
			height_offset: 0,
			width_include: ['#header', '#content', '#footer'],
			width_offset: 0});
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
				height_include : [ '#content_rightside', '#content_middelside' ],
				height_offset : 0,
				width_exclude: ['#content_middelside'],
				width_include : [ '#content_rightside'],
				width_offset : 1
			});	
	
			//计算右测区域高度
			jQuery('#content_rightside').mk_autoresize( {
				height_exclude : [ '.titleBar' ,'#statusManger','#info_stu_div'],
				height_include : '.search_condition',
				height_offset : 0,
				width_include : [ '.titleBar','#statusManger','.search_condition','#info_stu_div'],
				width_offset : 0
			});
			jQuery('.search_condition').mk_autoresize( {
				height_exclude : [ '#search_stu_tab'],
				height_include : '.table_list',
				height_offset : 0,
				width_include : [ '.table_list','#search_stu_tab'],
				width_offset : 0
			});		
			jQuery('.table_list').mk_autoresize({
			    height_include: '.bDiv',
			    height_offset: 78, // 该值各页面根据自己的页面布局调整
			    width_include: '.flexigrid',
			    width_offset: 8// 该值各页面根据自己的页面布局调整
			  });
		}else{//隐藏状态
			jQuery('#middeldiv').css("display","none");
			jQuery('#leftdiv').css("display","block");
			auto_size();
		}
	}
	</script>