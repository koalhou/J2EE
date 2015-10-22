<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
var dealflag=0;
jQuery(function() {
	var gala = jQuery('#gala2');
	gala.flexigrid({
		url: '<s:url value="/drivershuaka/getDriverRecordList.shtml" />',
		dataType: 'json',
		height: '2000',
		width:'1005',
		colModel : [
					{display: '姓名', name : "NLSSORT(DRIVER_NAME,'NLS_SORT = SCHINESE_PINYIN_M')", width : 150, sortable : true, align: 'center',escape: true},
					{display: '卡号', name : 'DRIVER_CARD_ID', width : 150, sortable : true, align: 'center'},
					{display: '状态', name : 'STATE', width : 150, sortable : false, align: 'center'},
					{display: '时间', name : 'SWIPETIME', width : 150, sortable : true, align: 'center'},
					{display: '驾驶车辆', name : 'VEHICLE_LN', width : 150, sortable : true, align: 'center'}
					],	    		
		    	sortname: 'SWIPETIME',
		    	sortorder: 'desc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 20,
				rpOptions : [10,20,50,100 ],// 可选择设定的每页结果数
		    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
				newp: 1,
				params: [{ name: 'driverIds', value: jQuery('#choosedriverid').val()},
				 		 { name: 'searchTimeType', value: jQuery('#searchTimeType').val()},
				 		 { name: 'month', value: jQuery('#month').val()},
				 		 { name: 'start_time', value: jQuery('#start_time').val()},
				 		 { name: 'end_time', value: jQuery('#end_time').val()},
				 		 { name: 'state', value: jQuery('#stateType').val()},
				 		 { name: 'ln', value: jQuery('#vehicle_ln').val()}]

	});
});

function searchRecordList() {
    jQuery('#gala2').flexOptions({
		params: [{ name: 'driverIds', value: jQuery('#choosedriverid').val()},
		 		 { name: 'searchTimeType', value: jQuery('#searchTimeType').val()},
		 		 { name: 'month', value: jQuery('#month').val()},
		 		 { name: 'start_time', value: jQuery('#start_time').val()},
		 		 { name: 'end_time', value: jQuery('#end_time').val()},
		 		 { name: 'state', value: jQuery('#stateType').val()},
		 		 { name: 'ln', value: jQuery('#vehicle_ln').val()}]
	});
	jQuery('#gala2').flexReload();
}

function exportRecord() {
	document.getElementById('exportObj.driverId').value = jQuery('#choosedriverid').val();
	document.getElementById('exportObj.searchTimeType').value = jQuery('#searchTimeType').val();
	document.getElementById('exportObj.month').value = jQuery('#month').val();
	document.getElementById('exportObj.begTime').value = jQuery('#start_time').val();
	document.getElementById('exportObj.endTime').value = jQuery('#end_time').val();
	document.getElementById('exportObj.state').value = jQuery('#stateType').val();
	document.getElementById('exportObj.vehicleLn').value = jQuery('#vehicle_ln').val();
	if(confirm("确定要导出刷卡记录么？")) {
		document.getElementById('export_record_form').action="<s:url value='/drivershuaka/exportRecord.shtml' />";
		document.getElementById('export_record_form').submit();
  	}
}

function changeTimeType(obj){
	if(obj.value == 1){
		$("#searchType1").css("display", "block");
		$("#searchType2").css("display", "none");
	}else{
		$("#searchType1").css("display", "none");
		$("#searchType2").css("display", "block");
	}
}

function changeState(obj){
	jQuery('#gala2').flexOptions({
		params: [{ name: 'driverIds', value: jQuery('#choosedriverid').val()},
		 		 { name: 'searchTimeType', value: jQuery('#searchTimeType').val()},
		 		 { name: 'month', value: jQuery('#month').val()},
		 		 { name: 'start_time', value: jQuery('#start_time').val()},
		 		 { name: 'end_time', value: jQuery('#end_time').val()},
		 		 { name: 'state', value: jQuery('#stateType').val()},
		 		 { name: 'ln', value: jQuery('#vehicle_ln').val()}]
	});
	jQuery('#gala2').flexReload();
}

//获取页面高度
function get_page_height() {
	var height = 0;
	if (jQuery.browser.msie) {
		document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
	} else {
		height = self.innerHeight;
	}
	return height;
}
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

//计算控件宽高
function testWidthHeight(){
	var h = get_page_height();
	var w = get_page_width();
	jQuery(".flexigrid").width(w-235);
	jQuery(".bDiv").height(h-384);
	var leftdiv=document.getElementById("leftInfoDiv");
	var treechang=document.getElementById("treechang");
	 if(h>335){
		 if(!leftdiv){
			 leftdiv.style.height = h-335;
		 }	
		 treechang.style.height=h-190;
	 }
}
</script>