<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
	jQuery(function() {
		 setGray();
		 var gala = jQuery('#gala');
		 gala.flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width:1500,
		  colModel : [ 
		        {display: '车牌号',name : 'VEHICLE_LN', width : calcColumn(0.1,120,350), sortable : true, align: 'center',process:sumStyle,escape: true},		        		        
		        {display: '未刷卡上车（次）', name : 'NO_UP_NUM', width : calcColumn(0.224,150,350), sortable : true, align: 'center',process:sumStyle,escape: true},
		        {display: '未刷卡下车（次）', name : 'NO_DOWN_NUM', width : calcColumn(0.224,150,350), sortable : true, align: 'center',process:sumStyle,escape: true},
		        {display: '未在规定站点上车（次）', name : 'NOFIX_UP_NUM', width : calcColumn(0.224,150,350), sortable : true, align: 'center',process:sumStyle,escape: true},
		        {display: '未在规定站点下车（次）', name : 'NOFIX_DOWN_NUM', width : calcColumn(0.224,150,350), sortable : true, align: 'center',process:sumStyle,escape: true},
		        {display: '', name : 'organization_id', width : 150, sortable : true, align: 'center',hide:true,toggle:false,escape: true},
		        {display: '', name : 'organization_name', width : 150, sortable : true, align: 'center',hide:true,toggle:false,escape: true},
		        {display: '', name : 'VIN', width : 100, sortable : false, align: 'center',hide:true,toggle:false,escape: true}
		        ],
		       sortname: '',
		       sortorder: '',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true,   // 是否允许显示隐藏列
		       onRowSelect:rowclickFun,
		       onSuccess:function(){
			     //jQuery("tr[id^='row'] div").css('height','15px').css('line-height','15px');
			     jQuery("#rowsumid").css('height','15px').css('line-height','15px');
					     return true;
					}
				       
		     });
		 searchRideAlarm();
		});
	function sumStyle(tdDiv,pid,row){
		if(pid=="sumid"){
			//tdDiv.style.height="15px";
			return '<span style= "font-weight: bold ;font-size:14px;">' + tdDiv +'</span>'; 
		}else{
			return tdDiv;
		}
	}

	/*树点击事件*/
	var selectName="";// division name
	var selectType="0";// 企业/车辆选中类型 0：企业 1：车辆

	// add by jinp start
	function mytreeonClick(type, treeNode){
		if(type=='1'){
			// 车辆点击
			document.getElementById('choosevin').value=treeNode.id;
			var name = treeNode.getParentNode().name;
			if(name != null) {
				selectName = name.split("(")[0];
			}
			selectType = "1";
			searchRideAlarm();
		} else {
			// 组织点击
			selectType="0";
			if(jQuery("#filterFlag").attr("checked")==true) {
				// 过滤未规划车辆
				document.getElementById('chooseorgid').value=treeNode.id;
				selectName=treeNode.name.split("(")[0];
				searchRideAlarm();
			} else {
				document.getElementById('chooseorgid').value=treeNode.id;
				selectName=treeNode.name.split("(")[0];
				searchRideAlarm();
			}
		}
	}
	// add by jinp end

	function getSelectOrgName(){
		if(selectName==""){
			var zTree = jQuery.fn.zTree.getZTreeObj("treeDemo");
			var node = zTree.getNodeByParam("level", "0");
			selectName=node.name.split("(")[0];
		}
		return selectName;
	}		
	/*表格行点击事件-左侧浮动POP*/
	var rowclickFun =function(rowData) {
		var VEHICLE_LN = jQuery(rowData).data("VEHICLE_LN");		
		if(VEHICLE_LN.length > 7){
			VEHICLE_LN = "*" + VEHICLE_LN.substring(VEHICLE_LN.length-7,VEHICLE_LN.length);
		}
		var vin = jQuery(rowData).data("VIN");
		var organization_name = jQuery(rowData).data("organization_name");
		var NO_UP_NUM = jQuery(rowData).data("NO_UP_NUM");
		var NO_DOWN_NUM = jQuery(rowData).data("NO_DOWN_NUM");
		var NOFIX_UP_NUM = jQuery(rowData).data("NOFIX_UP_NUM");
		var NOFIX_DOWN_NUM = jQuery(rowData).data("NOFIX_DOWN_NUM");
		if(formatSpecialChar(vin)!=""&&vin.length!=1){
			openPopUp(vin,VEHICLE_LN,NO_UP_NUM,NO_DOWN_NUM,NOFIX_UP_NUM,NOFIX_DOWN_NUM,organization_name,"","carchoice");
			jQuery('#orgname').val(organization_name);
		}else{
			var organization_id = jQuery(rowData).data("organization_id");
			openPopUp("",VEHICLE_LN,NO_UP_NUM,NO_DOWN_NUM,NOFIX_UP_NUM,NOFIX_DOWN_NUM,getSelectOrgName(),organization_id,"divisionchoice");
			jQuery('#orgname').val(getSelectOrgName());
		}

		jQuery('#car_ln').val(jQuery(rowData).data("VEHICLE_LN"));
		
	};

	var query_ln="";
	function openPopUp(vin,VEHICLE_LN,NO_UP_NUM,NO_DOWN_NUM,NOFIX_UP_NUM,NOFIX_DOWN_NUM,organization_name,organization_id,type){
		/*if(!jQuery('#popArea').mk_sidelayer('is_close')){
		    if (!jQuery('#popArea').mk_sidelayer('is_show')) {
			    jQuery('#popArea').mk_sidelayer('show');	
		    }
		}else{
			jQuery('#popArea').mk_sidelayer('open');
		}*/

		sidelayerHead(organization_name);
		jQuery('#popArea').mk_sidelayer('set_title',encodeHtml(VEHICLE_LN));
		// commet by jinp begin
		/**
	    jQuery('#popArea').mk_sidelayer('reload', {
	  	    'url': '<s:url value="/ridealarm/iframeChoice.shtml"/>', 
	  	     'query_param': {'detailObj.user_organization_id': organization_id, 
		  	     			 'detailObj.VIN':vin,
		  	     			 'detailObj.time_list':jQuery('#plan_time').val(),
		  	     			 'detailObj.begTime': jQuery('#user_beg_time').val(), 
		  	     			 'detailObj.endTime':jQuery('#user_end_time').val(),
		  	     			 'detailObj.vehicle_ln':query_ln,
		  	     			 'detailObj.no_up_num': NO_UP_NUM, 
		  	     			 'detailObj.no_down_num':NO_DOWN_NUM,
		  	     			 'detailObj.nofix_up_num':NOFIX_UP_NUM,
		  	     			 'detailObj.nofix_down_num':NOFIX_DOWN_NUM
		  	     			 }
	      });	 
	      **/
	    // commet by jinp end
	    
	    // add by jinp begin
	    if("carchoice" == type || selectType=="1") {
			// 单车查询
			jQuery('#popArea').mk_sidelayer('reload', {
		  	    'url': '<s:url value="/ridealarm/iframeChoice.shtml"/>', 
		  	     'query_param': {'detailObj.user_organization_id': organization_id, 
			  	     			 'detailObj.VIN':vin==""?jQuery('#choosevin').val():vin,
			  	     			 'detailObj.time_list':jQuery('#plan_time').val(),
			  	     			 'detailObj.begTime': jQuery('#user_beg_time').val(), 
			  	     			 'detailObj.endTime':jQuery('#user_end_time').val(),
			  	     			 'detailObj.vehicle_ln':query_ln,
			  	     			 'detailObj.no_up_num': NO_UP_NUM, 
			  	     			 'detailObj.no_down_num':NO_DOWN_NUM,
			  	     			 'detailObj.nofix_up_num':NOFIX_UP_NUM,
			  	     			 'detailObj.nofix_down_num':NOFIX_DOWN_NUM,
			  	     			 'detailObj.valid_flag':type
			  	     			 }
		      });
	    } else {
			// 组织查询
			jQuery('#popArea').mk_sidelayer('reload', {
		  	    'url': '<s:url value="/ridealarm/iframeChoice.shtml"/>', 
		  	     'query_param': {'detailObj.user_organization_id': organization_id, 
			  	     			 'detailObj.VIN':"",
			  	     			 'detailObj.time_list':jQuery('#plan_time').val(),
			  	     			 'detailObj.begTime': jQuery('#user_beg_time').val(), 
			  	     			 'detailObj.endTime':jQuery('#user_end_time').val(),
			  	     			 'detailObj.vehicle_ln':query_ln,
			  	     			 'detailObj.no_up_num': NO_UP_NUM, 
			  	     			 'detailObj.no_down_num':NO_DOWN_NUM,
			  	     			 'detailObj.nofix_up_num':NOFIX_UP_NUM,
			  	     			 'detailObj.nofix_down_num':NOFIX_DOWN_NUM,
			  	     			 // filter flag[是否过滤无效车辆 1：过滤]
			  	     			 'detailObj.car_state':jQuery("#filterFlag").attr("checked")==true ? "1" : "0",
			  	     			 'detailObj.valid_flag':type
			  	     			 }
		      });
	    }
	    // add by jinp end
	}

	function sidelayerHead(organization_name){

		var sidebuttonHtml="";
		sidebuttonHtml+="<span><table width='300' border='0' cellspacing='0' cellpadding='0'>";
		sidebuttonHtml+="<tr><td colspan='2' height='8'></td></tr>";
		sidebuttonHtml+="<tr><td width='40'>&nbsp;</td>";
		sidebuttonHtml+="<td width='65' style='height: 22px;' valign='bottom'><strong>所属部门：</strong></td>";
		sidebuttonHtml+="<td style='height: 22px;' valign='bottom'><strong>"+encodeHtml(organization_name)+"</strong></td></tr>";
		sidebuttonHtml+="<tr><td>&nbsp;</td>";
		sidebuttonHtml+="<td style='height: 22px;' valign='top'><strong>统计时段：</strong></td>";
		sidebuttonHtml+="<td style='height: 22px;' valign='top'><strong>"+encodeHtml(jQuery('#chart_desc').val())+"</strong></td></tr></table></span>";
      
		jQuery('.mk-sidelayer-tools').html(sidebuttonHtml);
		
	}

	/*大图展示-左侧浮动POP-放大镜响应事件*/
	function showRideAlarm() {
		jQuery("#rideAlarmDetial").css("top","169px");
		jQuery("#rideAlarmDetial").css("left","248px");
		jQuery("#BgDiv").css("display","block");
		jQuery("#rideAlarmDetial").css("display","block");
		
		if(jQuery('#no_up_num').val()=="0"&&jQuery('#no_down_num').val()=="0"&&jQuery('#nofix_up_num').val()=="0"&&jQuery('#nofix_down_num').val()=="0") {
			jQuery('#rideAlarmcontent').hide();
			jQuery('#noRideAlarmDataDiv').show();
		}else{
			jQuery('#noRideAlarmDataDiv').hide();
			jQuery('#rideAlarmcontent').show();
			var xmlStr = "<chart  rotateYAxisName='0' showValues='0' baseFontSize='15' outCnvBaseFontSize='16'  decimalPrecision='2' pieRadius='120' pieYScale='60' formatNumberScale='0' formatNumber='0' showLabels='1' showPercentInToolTip='0'>";
		    xmlStr = xmlStr + "<set label='未刷卡上车"+jQuery('#no_up_num').val()+"次' value='"+jQuery('#no_up_num').val()+"' color='ACD6FF' isSliced='1' toolText='未刷卡上车"+jQuery('#no_up_num').val()+"次'/>";
		    xmlStr = xmlStr + "<set label='未刷卡下车"+jQuery('#no_down_num').val()+"次' value='"+jQuery('#no_down_num').val()+"' color='EAC100' isSliced='1' toolText='未刷卡下车"+jQuery('#no_down_num').val()+"次'/>";
		    xmlStr = xmlStr + "<set label='未在规定站点上车"+jQuery('#nofix_up_num').val()+"次' value='"+jQuery('#nofix_up_num').val()+"' color='5CADAD' isSliced='1' toolText='未在规定站点上车"+jQuery('#nofix_up_num').val()+"次'/>";
		    xmlStr = xmlStr + "<set label='未在规定站点下车"+jQuery('#nofix_down_num').val()+"次' value='"+jQuery('#nofix_down_num').val()+"' color='BB3D00' isSliced='1' toolText='未在规定站点下车"+jQuery('#nofix_down_num').val()+"次'/>";
		    xmlStr = xmlStr + "</chart>";
		    
			var chart1 = new FusionCharts("../scripts/fusioncharts/Pie3D.swf?ChartNoDataText=无查询结果", "ChartId", "680", "350", "0", "0");
			chart1.setDataXML(xmlStr);
					   
			chart1.render("rideAlarmcontent"); 
		}
		jQuery("#vehicle_ln_v").val(jQuery("#car_ln").val());
		jQuery("#org_name_v").val(jQuery("#orgname").val());
		jQuery("#time_name_v").val(jQuery('#chart_desc').val());
		
	}
	//关闭弹出层
	function closeRideAlarm() {
		jQuery("#BgDiv").css("display","none");
		jQuery("#rideAlarmDetial").css("display","none");
	}
		
	/*查询条*/
	function selectTime(){
		jQuery('#time_select').val(jQuery('#time_option').val());
		if(jQuery('#time_select').val() == "define"){
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
	function searchRideAlarm() {
		var chart_desc = "";
		if(jQuery('#time_select').val() != "define"){
			jQuery('#plan_time').val(jQuery('#time_select').val());
			jQuery('#user_beg_time').val("");
			jQuery('#user_end_time').val("");			
			if(jQuery('#plan_time').val()=="month"){
				chart_desc="本月";
			}else if(jQuery('#plan_time').val()=="quarter"){
				chart_desc="本季度";
			}else if(jQuery('#plan_time').val()=="year"){
				chart_desc="本年";
			}else if(jQuery('#plan_time').val()=="week"){
				chart_desc="本周";
			}
		}else if(jQuery('#time_select').val() == "define"){
			if(checkTime()){
				chart_desc=jQuery('#begTime').val()+" 至 "+jQuery('#endTime').val();	
				jQuery('#plan_time').val("");
				jQuery('#user_beg_time').val(jQuery('#begTime').val());
				jQuery('#user_end_time').val(jQuery('#endTime').val());	
			}else{
				return;
			}
		
		}
		jQuery('#chart_desc').val(chart_desc);
		
		jQuery('#fileterFlag').val("0");
		if(jQuery("#dataFileter").attr("checked")){
			jQuery('#fileterFlag').val("1");
		}
		query_ln = formatSpecialChar(jQuery('#vehicle_ln').val());

		jQuery('#gala').flexOptions({
			newp: 1,
			url: '<s:url value="/ride_alarm/getRideAlarmSum.shtml" />',
			  params: [// add by jinp begin
					   // filter flag[是否过滤无效车辆 1：过滤]
					   {name: 'queryObj.car_state', value: jQuery("#filterFlag").attr("checked")==true ? "1" : "0"},
					   // selectType [企业查询0 or 车辆查询1]
					   {name: 'queryObj.re_flag', value: selectType=="0" ? "1" : "0"},
					   {name: 'queryObj.VIN', value: selectType=="0" ? "" : jQuery('#choosevin').val()},
					   // add by jinp end
					   {name: 'queryObj.vehicle_ln', value: query_ln},
				       {name: 'queryObj.user_organization_id', value: jQuery('#chooseorgid').val()},
				       {name: 'queryObj.fileterFlag', value: jQuery('#fileterFlag').val()},
				       {name: 'queryObj.time_list', value: jQuery('#plan_time').val()},
				       {name: 'queryObj.endTime', value: jQuery('#user_end_time').val()},
				       {name: 'queryObj.begTime', value: jQuery('#user_beg_time').val()}]	
		});		
		jQuery('#gala').flexReload();
	}

	function checkTime() {
		var end_time = $('endTime');
		var start_time = $('begTime');
		if (jQuery('#time_select').val() == 1) {
		    if (!Mat.checkRequired(start_time))
				return false;
			if (!Mat.checkRequired(end_time))
				return false;
		}
		return true;
	}
	/*导出*/
	function exportData(){
		if(confirm("确定要导出异常乘车统计吗？")) {
			if(jQuery('#time_select').val() != "define"){
				document.getElementById('exportObj.time_list').value = jQuery('#time_option').val();
				document.getElementById('exportObj.begTime').value = "";
				document.getElementById('exportObj.endTime').value = "";
			}else if(jQuery('#time_select').val() == "define"){
				if(checkTime()){
					document.getElementById('exportObj.begTime').value = jQuery('#begTime').val();
					document.getElementById('exportObj.endTime').value = jQuery('#endTime').val();
					document.getElementById('exportObj.time_list').value = "";
					jQuery('#plan_time').val("");
				}else{
					return;
				}
			}
			
			// add by jinp begin
			document.getElementById('exportObj.car_state').value = jQuery("#filterFlag").attr("checked")==true ? "1" : "0";
			document.getElementById('exportObj.re_flag').value = selectType=="0" ? "1" : "0";
			document.getElementById('exportObj.VIN').value = selectType=="0" ? "" : jQuery('#choosevin').val();
			// add by jinp end
			
			document.getElementById('exportObj.vehicle_ln').value = formatSpecialChar(jQuery('#vehicle_ln').val());
			document.getElementById('exportObj.user_organization_id').value = jQuery('#chooseorgid').val();
			document.getElementById('exportObj.fileterFlag').value = jQuery('#fileterFlag').val();
			document.getElementById('export_form').action="<s:url value='/ridealarm/exportRideAlarmSum.shtml' />";
			document.getElementById('export_form').submit();
		}
	}

	//页面自适应
	jQuery(function() {	
		/* jQuery(window).mk_autoresize({
			min_width: 1280
		}); */
		resizeBgDiv();
		auto_size();
		jQuery("#rideAlarmDetial").easydrag();
		jQuery("#rideAlarmDetial").setHandler("DetialTitle");
		jQuery("#BgDiv").css("z-index","902"); 
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
			height_exclude : [ '#leftInfoDiv', '.newsearchPlan'],
			height_include : '#lefttree',
			height_offset : 10
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
			height_include: '#tableparent .bDiv',
		    height_offset: 107, // 该值各页面根据自己的页面布局调整
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
			jQuery('#content').mk_autoresize( {
				height_include : [ '#content_rightside', '#content_leftside' ],
				height_offset : 0,
				width_exclude: ['#content_leftside'],
				width_include : [ '#content_rightside'],
				width_offset : 25
			});
			
			//计算左测区域高度
			jQuery('#content_leftside').mk_autoresize( {
				height_exclude : [ '#leftInfoDiv', '.newsearchPlan'],
				height_include : '#lefttree',
				height_offset : 10
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
				height_include: '#tableparent .bDiv',
			    height_offset: 107, // 该值各页面根据自己的页面布局调整
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