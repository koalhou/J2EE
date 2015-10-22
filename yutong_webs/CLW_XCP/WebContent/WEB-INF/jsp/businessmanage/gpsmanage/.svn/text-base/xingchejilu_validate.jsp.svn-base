<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">	
	(function($){
		// 预缓存皮肤，数组第一个为默认皮肤
		$.dialog.defaults.skin = ['aero'];
	})(art);

	function choiceStu() {	
		art.dialog.open("<s:url value='/stalarm/getStudentList.shtml' />",{
			title:"学生信息",
			lock:true,
			width:800,
			height:435
		});
	}

	function choiceCar() {	
		art.dialog.open("<s:url value='/routechart/getCarList.shtml' />",{
			title:"车辆信息",
			lock:true,
			width:700,
			height:435
		});
	}

	//页面自适应
	(function (jQuery) {
		 jQuery(window).resize(function(){
		 	testWidthHeight();
		 	jQuery('#gala').fixHeight();
		 });
		 jQuery(window).load(function (){
		  	testWidthHeight();
		  	jQuery('#gala').fixHeight();
		 });	 
	})(jQuery);	 
	//获取页面宽度
	function get_page_width() {
		 var width = 0;
		 if(jQuery.browser.msie){ 
		  width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
		 } else { 
		  width = self.innerWidth; 
		 } 
		 return width;
	}
		
	//获取页面高度
	function get_page_height() {
		 var height = 0;
		 if (jQuery.browser.msie) {
		     height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
		 } else {
		     height = self.innerHeight;
		 }
		 return height;
	}

	//计算控件宽高
	function testWidthHeight(){
		 var h = get_page_height();
		 var w = get_page_width();
		 jQuery(".flexigrid").width(w-30);
		 if(h>375){
			 jQuery(".bDiv").height(h-370);
		 }
	}

		jQuery(function() {	
			var st_ride_flag = document.getElementById("st_ride_flag").value;
			//alert("321"+jQuery('#st_ride_flag').val());
			jQuery('#gala').flexigrid({
			  url: '<s:url value="/SendInfo/getRideInfo.shtml"/>', 
			  dataType: 'json',    //json格式
			  params: [{name: 'queryObj.st_ride_flag', value: jQuery('#st_ride_flag').val()},
				       {name: 'queryObj.endTime', value: jQuery('#endTime').val()},
				       {name: 'queryObj.begTime', value: jQuery('#begTime').val()},
				       {name: 'queryObj.USERID', value: jQuery('#optionUserid').val()}],
			  height: 430,
			  width:2000,
			  colModel : [
					{display: '姓名', name : 'stu_name', width : 50, sortable : true, align: 'left'},
					{display: '学号', name : 'stu_code', width : 85, sortable : true, align: 'left'},	
					{display: '学校', name : 'stu_school', width : 80, sortable : true, align: 'left'},
					{display: '班级', name : 'stu_class', width : 80, sortable : true, align: 'left'},	
					{display: '线路名称', name : 'route_name', width : 100, sortable : true, align: 'left'},	
					{display: '车牌号', name : 'vehicle_ln', width : 100, sortable : true, align: 'left'},										   											   					   					   											   					   
					{display: '驾驶员', name : 'driver_name', width : 65, sortable : true, align: 'left'},
					{display: '跟车老师', name : 'sichen_name', width : 65, sortable : true, align: 'left'},
				    {display: '时间', name : 'terminal_time', width : 120, sortable : true, align: 'left'},
					{display: '乘车类型', name : '', width : 120, align: 'left',process:reWriteRideFlag},
				    {display: '站点', name : 'site_name', width : 120, sortable : true, align: 'left'}
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
		});		

		function reWriteRideFlag(tdDiv,pid){
			var st_ride_flag = get_cell_text(pid, 9);
			if(st_ride_flag == "00"){
				tdDiv.innerHTML = "上学上车";
			}else if(st_ride_flag == "01"){
				tdDiv.innerHTML = "上学下车";
			}else if(st_ride_flag == "10"){
				tdDiv.innerHTML = "放学上车";
			}else if(st_ride_flag == "11"){
				tdDiv.innerHTML = "放学下车";
			}else{
				tdDiv.innerHTML = "--";
			}
		}

		function reWriteMesgFlag(tdDiv,pid){
			var st_ride_flag = get_cell_text(pid, 10);
			if(st_ride_flag == "0"){
				tdDiv.innerHTML = "未下发";
			}else if(st_ride_flag == "1"){
				tdDiv.innerHTML = "下发成功";
			}else if(st_ride_flag == "2"){
				tdDiv.innerHTML = "下发失败";
			}else{
				tdDiv.innerHTML = "--";
			}
		}

		function get_cell_text(pid, cell_idx) {
			return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
		}
		
		//查询
		function searchAlarm() {
			var st_ride_flag = document.getElementById("st_ride_flag").value;
			jQuery('#gala').flexOptions({
				newp: 1,
				  params: [{name: 'queryObj.st_ride_flag', value: st_ride_flag },
				           {name: 'queryObj.USERID', value: jQuery('#optionUserid').val() },
					       {name: 'queryObj.endTime', value: jQuery('#endTime').val()},
					       {name: 'queryObj.begTime', value: jQuery('#begTime').val()}]					
			});
			jQuery('#gala').flexReload();
		}
		function exportData(){
			if(confirm("确定要导出学生乘车记录么？")) {
				document.getElementById('exportObj.stu_code').value = jQuery('#stu_code').val();
				document.getElementById('exportObj.vehicle_ln').value = jQuery('#vehicle_ln').val();
				document.getElementById('exportObj.begTime').value = jQuery('#begTime').val();
				document.getElementById('exportObj.endTime').value = jQuery('#endTime').val();				
				document.getElementById('export_form').action="<s:url value='/gps/exportStRide.shtml' />";
				document.getElementById('export_form').submit();
			}
		}						
</script>