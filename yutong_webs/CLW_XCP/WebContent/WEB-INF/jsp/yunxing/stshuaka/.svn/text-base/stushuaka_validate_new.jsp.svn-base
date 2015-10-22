<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">	

//点击组织数复选框触发
function mytreeonClick(carList){
		
	//保存vehicle_vin信息
	document.getElementById('vehicle_vins').value=carList;
	//查询方法
	searchList();
	//详情信息重置
	reSetRowInfo();
}

jQuery(function() {	
			jQuery('#gala').flexigrid({
			  dataType: 'json',    //json格式
			  height: '1500',
			  width:'1500',
			  colModel : [
					{display: '姓名', name : 'STU_NAME', width : 100, sortable : true, align: 'center',escape: true},
					{display: '学号', name : 'STU_CODE', width : 100, sortable : true, align: 'center',escape: true},
					{display: '状态', name : 'ST_RIDE_FLAG', width : 150, sortable : true,align: 'center',escape: true},
					{display: '时间', name : 'TERMINAL_TIME', width : 150, sortable : true, align: 'center',escape: true},	
					{display: '学校', name : 'STU_SCHOOL', width : 150, sortable : true, align: 'center',escape: true},
					{display: '班级', name : 'STU_CLASS', width : 150, sortable : true, align: 'center',escape: true},
					{display: '车牌号', name : 'VEHICLE_LN', width : 100, sortable : true, align: 'center',escape: true}
					//{display: 'PID', name : 'PID', width : 110, sortable : true, align: 'center',hide:true,toggle:false,process:reWritePID}
			        ],
			       sortname: 'TERMINAL_TIME',
			       sortorder: 'DESC',  //升序OR降序
			       sortable: true,   //是否支持排序
			       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
			       usepager :true,  //是否分页
			       resizable: false,  //是否可以设置表格大小
			       useRp: true,    // 是否可以动态设置每页显示的结果数
			       rp: 20,  //每页显示记录数
			       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
			       showTableToggleBtn: true,   // 是否允许显示隐藏列
			       onRowSelect:rowstandbyclick //获取单行数据的方法
			     });
			//searchList();
			//添加拖动事件  浮动层div
			jQuery("#popArea3").easydrag();
		});	


//设置详细信息显示
var rowstandbyclick =function(rowData,pid) {
	 jQuery("#stu_name_v").val(jQuery(rowData).data("STU_NAME"));
	 jQuery("#stu_school_v").val(jQuery(rowData).data("STU_SCHOOL"));
	 jQuery("#stu_class_v").val(jQuery(rowData).data("STU_CLASS"));
	 jQuery("#stu_code_v").val(jQuery(rowData).data("STU_CODE"));
	 jQuery("#vehicle_ln_v").val(jQuery(rowData).data("VEHICLE_LN")); 
	 jQuery("#st_ride_flag_v").val(jQuery(rowData).data("ST_RIDE_FLAG"));
	 jQuery("#terminal_time_v").val(jQuery(rowData).data("TERMINAL_TIME"));
	 jQuery("#location_v").html('<a href="#" onclick="javascript:showStuLocation(\'' + pid +'\')">' + '查看' +'</a>');
};

//重置详情信息
function reSetRowInfo(){
	 jQuery("#stu_name_v").val("");
	 jQuery("#stu_school_v").val("");
	 jQuery("#stu_class_v").val("");
	 jQuery("#stu_code_v").val("");
	 jQuery("#vehicle_ln_v").val("");
	 jQuery("#st_ride_flag_v").val("");
	 jQuery("#terminal_time_v").val("");
	 jQuery("#location_v").html("");
}

//回写行数据ID值
function reWritePID(tdDiv,pid){
	//tdDiv.innerHTML = '<a href="#" onclick="javascript:showStuLocation(\'' + pid  +'\')">' + '查看' +'</a>';
	tdDiv.innerHTML = pid;
}


function showStuLocation(pid) {
	/*
	art.dialog.open("<s:url value='/stushuaka/showStuLocation.shtml'/>?queryObj.id="+pid + "&count="+Math.random(),
					{
						title: '地理位置',
						skin: 'aero',
						limit: true,
						lock: true,
						drag: true,
						fixed: false,
						width :848,
						height:495
					});
	*/
	//跳入到弹出框--地图
	var iframeobj = document.getElementById("iframeshowArea");
	//var poparea3obj = document.getElementById("");
	iframeobj.src = "<s:url value='/stushuaka/showStuLocation.shtml'/>?queryObj.id="+pid + "&count="+Math.random();
	//显示照片相册
	jQuery("#popArea3").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#popArea3").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#popArea3").height())/2))+"px");
	jQuery('#popArea3').css("display", "block");
	//显示拖拽层
	jQuery("#BgDiv").css("display","block");
}
		
//隐藏相册
function HideStuLocation(){
	jQuery('#popArea3').css("display", "none");
	//隐藏拖拽层
	jQuery("#BgDiv").css("display","none");
}
		/*
		function get_cell_text(pid, cell_idx) {
			return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
		}
		*/
		
		//查询
		var stu_name = "";
		//var vehicle_ln = "";
		var vehicle_vins = "";
		var begTime = "";
		var endTime = "";
		var stu_school = "";
		var stu_class = "";
		var st_ride_flag = "";
		
		function searchList() {
			//学生姓名
			stu_name = formatSpecialChar(jQuery('#stu_name').val());
			//学校
			stu_school = formatSpecialChar(jQuery('#stu_school').val());
			//班级
			stu_class = formatSpecialChar(jQuery('#stu_class').val());
			//时间范围
			endTime = jQuery('#endTime').val();
			begTime = jQuery('#begTime').val();
			vehicle_vins = jQuery('#vehicle_vins').val();
			//空字符串转换为''
			if(vehicle_vins == ''){vehicle_vins = "''";}
			//根据单选框选择情况 设置状态标识
			setStRideFlag();
			st_ride_flag = jQuery('#st_ride_flag').val();
			jQuery('#gala').flexOptions({
				newp: 1,
				  url: '<s:url value="/stshuakagrid/getRideInfo.shtml"/>',
				  params: [{name: 'queryObj.stu_name', value: stu_name},
				           {name: 'queryObj.stu_class', value: stu_class},
					       {name: 'queryObj.stu_school', value: stu_school},
					       {name: 'queryObj.endTime', value: endTime},
					       {name: 'queryObj.begTime', value: begTime},
				           {name: 'queryObj.VIN', value: vehicle_vins},    
					       {name: 'queryObj.st_ride_flag', value: st_ride_flag}
					       ]					
			});
			jQuery('#gala').flexReload();
		}

		
	  function setStRideFlag() {
			var st_ride_flag_1 = jQuery('#st_ride_flag_1');
			var st_ride_flag_2 = jQuery('#st_ride_flag_2');
				
			var srf_v_1 = "0";	
			if(st_ride_flag_1.attr("checked")){srf_v_1 = "1";}
			var srf_v_2 = "0";
			if(st_ride_flag_2.attr("checked")){srf_v_2 = "1";}
							
			var srf_v = srf_v_1+srf_v_2;
			//设置给st_ride_flag
			document.getElementById('st_ride_flag').value=srf_v;
			}

/*
* resize时让DIV在文档居中
*/
jQuery(window).wresize(function(){
	//显示照片相册
	jQuery("#popArea3").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#popArea3").width())/2))+"px")
		        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#popArea3").height())/2))+"px");
	});
	
//导出乘车记录
function exportData(){
	if(confirm("确定要导出学生刷卡记录吗？")) {
		stu_school = formatSpecialChar(jQuery('#stu_school').val());
		stu_class = formatSpecialChar(jQuery('#stu_class').val());
		endTime = jQuery('#endTime').val();
		begTime = jQuery('#begTime').val();
		vehicle_vins = jQuery('#vehicle_vins').val();
		if(vehicle_vins == ''){vehicle_vins = "''";}
		setStRideFlag();
		st_ride_flag = jQuery('#st_ride_flag').val();
		document.getElementById('queryObj.stu_name').value = formatSpecialChar(jQuery('#stu_name').val());//姓名
		document.getElementById('queryObj.stu_class').value = formatSpecialChar(jQuery('#stu_class').val());
		document.getElementById('queryObj.stu_school').value = formatSpecialChar(jQuery('#stu_school').val());
		document.getElementById('queryObj.endTime').value = jQuery('#endTime').val();
		document.getElementById('queryObj.begTime').value = jQuery('#begTime').val();
		document.getElementById('queryObj.VIN').value = vehicle_vins;
		document.getElementById('queryObj.st_ride_flag').value = st_ride_flag;
		document.getElementById('export_form').action="<s:url value='/stushuaka/exportStShuaka.shtml' />";
		document.getElementById('export_form').submit();
	}
}
</script>