var alarmControl = function(){
	this.mapFlag=0;
	this.ftleL;
	this.mapabc = new mapabcObjdiv();
	this.alarmType = new Array();
	this.alarmTime = new Array();
	this.alarmID = new Array();
};
var isDisplayLabel = true;
alarmControl.prototype = {
		loadMap: function(){
			var thisObj = this;
			thisObj.mapabc.maiInit("map2");
		},
	    init: function(){
	    	var thisObj = this;
	    	var vinCode = $("#vehicle_vin").val();
	    	var ftlyAlarmList = jQuery('#ftlyAlarmList');
	    	ftlyAlarmList.flexigrid({
				url: 'getStealAlarmDisposeList.shtml',
				//加参数用于整合页面查询
				params: [
							{name:"vehicle_vin",value:vinCode},
							{name: "gridFlag",value: "0"},
							{name: "deal_flag",value: "0"}
				 		],
				dataType: 'json',
				height: 100,
				width: 500,
				colModel : [
				    		{display: '序号', name : '',width : 30, sortable : false, align: 'center'},
				    		{display: '告警类型', name : '', width : 210, sortable : true, align: 'center',process: thisObj.getAlarmType},//
				    		{display: '告警时间', name : 'report_time', width : 180, sortable : true, align: 'center',process:thisObj.getAlarmTime},
				    		{display: '', name : '', width : 140, hide:true, sortable : true, align: 'center'},//AddOill		    				    		
				    		{display: '', name : '', width : 140, hide:true, sortable : true, align: 'center'},//DriverName
				    		{display: '', name : '', width : 70, hide:true, sortable : true, align: 'center'},//DriverTel
				    		{display: '', name : '', width : 70, hide:true, sortable : true, align: 'center'},
				    		{display: '', name : '', width : 70, hide:true, sortable : true, align: 'center'},//Longitude
				    		{display: '', name : '', width : 70, hide:true, sortable : true, align: 'center'},//Latitude
				    		{display: '', name : '', width : 70, hide:true, sortable : true, align: 'center'},//OilboxMass
				    		{display: '', name : '', width : 70, hide:true, sortable : true, align: 'center'},
				    		{display: '', name : '', width : 70, hide:true, sortable : true, align: 'center'},//Zonename
				    		{display: '', name : '', width : 70, hide:true, sortable : true, align: 'center',process: thisObj.getAlarmID},//FtylyIdNum,process: thisObj.getAlarmID
				    		{display: '', name : '', width : 70, hide:true, sortable : true, align: 'center'},//OilboxState
				    		{display: '', name : '', width : 70, hide:true, sortable : true, align: 'center'}//旋转方向
				    	],
				    	sortname:'report_time',
				    	sortorder: 'desc',
				    	sortable: true,
						striped :true,
						usepager :true, 
						resizable: false,
						autoload:true,
				    	useRp: false,
				    	rp: 10,
				    	pagetext: '',
				    	outof: '/',
				    	gridId:'onChangeftlyInfo',
						rpOptions : [10],// 可选择设定的每页结果数
				    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错
				    	gridClass: "bbit-grid",
				    	onSuccess: function(data){
				    		if($('#ftlyAlarmList tr').length == 0 && isDisplayLabel){
				    			$("#alarmParam").css("visibility","hidden");
				    			$("#chiliBtn").css("display","none");
				    			tishiShow("无未处理的油量告警!");
				    			//tishiHide();
				    			return false;
				    		} else if($('#ftlyAlarmList tr').length != 0){
				    			$("#alarmParam").css("visibility","visible");
				    		} else {
				    			$("#alarmParam").css("visibility","hidden");
				    			$("#chiliBtn").css("display","none");
				    			thisObj.clearData();
				    			thisObj.mapabc.mapObjDiv.setZoomAndCenter(11,new MLngLat(113.669379, 34.7522));
				    			thisObj.mapabc.mapObjDiv.removeAllOverlays();
				    			$("#alarmMsg").empty();
				    		}
				    		$('#ftlyAlarmList tr').bind('click',function(){
				    	    	var ftlyId = $(this).children('td').eq(12).eq(0).text();
				    	    	var addOill = $(this).children('td').eq(3).eq(0).text();
				    	    	var driverName = $(this).children('td').eq(4).eq(0).text();
				    	    	var driverTel = $(this).children('td').eq(5).eq(0).text();
				    			var lon = $(this).children('td').eq(7).eq(0).text();
				    			var lat = $(this).children('td').eq(8).eq(0).text();
				    			var direction = $(this).children('td').eq(14).eq(0).text();
				    			var state = $(this).children('td').eq(13).eq(0).text();
				    			$("#ftlyId").val(ftlyId);
				    			jQuery("#alarmMsg").val(ftlyId);
				    			
				    			$("#addOill").html("<b>"+addOill+"L</b>");
				    			$("#driverEmp").html("<b>"+driverName+"</b>");
				    			$("#drivertel").html("<b>"+driverTel+"</b>");
				    			
				    			if(state == "001"){
				    				$("#stateTxt").html("油量骤减：");
				    			} else {
				    				$("#stateTxt").html("加油油量：");
				    			}
				    			
				    			if(lon == "0.0" || lat == "0.0"){
				    				//return;
				    			} else {
				    				thisObj.mapabc.showpointToFtlyInfo3(lon,lat,vehicle_ln,direction,0,"map");
				    			}
				    			
				    		});
				    		setTimeout(function(){thisObj.setSelect();},800);
				    	},
				    	onSubmit: function(){
				    		thisObj.alarmType = [];
				    		thisObj.alarmTime = [];
				    		thisObj.alarmID = [];
				    		
				    		return true;
				    	}
	    	//,
				    	//onRowDblclick:thisObj.rowdbclick
			});
//	    	delete vinCode;
//	    	delete ftlyAlarmList;
	    	
	    },
		getAlarmID: function (tdDiv,pid){
			var tmp = tdDiv.innerHTML;
			var len = alarmControlObj.alarmID.length;
			alarmControlObj.alarmID[len] = tmp;
//			delete tmp;
//			delete len;
			
		},
		getAlarmType: function (tdDiv,pid){
			var tmp = tdDiv.innerHTML;
			var len = alarmControlObj.alarmType.length;
			alarmControlObj.alarmType[len] = tmp;
//			delete tmp;
//			delete len;
			
		},
		getAlarmTime: function(tdDiv,pid){
			var tmp = tdDiv.innerHTML;
			var len = alarmControlObj.alarmTime.length;
			alarmControlObj.alarmTime[len] = tmp;
//			delete tmp;
//			delete len;
		},
		setSelect: function(){
			$("#alarmMsg").empty();
			var thisObj = this;
			var len = thisObj.alarmType.length;
    		var alarmType = thisObj.alarmType;
    		var alarmTime = thisObj.alarmTime;
    		var alarmIds = thisObj.alarmID;
    		for(var i = 0; i < len; i++){
    			var option = $("<option>").text(alarmTime[i]+ "            "+alarmType[i]).val(alarmIds[i]);
    			$("#alarmMsg").append(option);
    			option = null;
    			
    		}
    		$("tr[id^=row]",$("#ftlyAlarmList")).eq(0).trigger("click");
//    		delete len;
//    		delete alarmType;
//    		delete alarmIds;
    		
		},
		addSpace: function(strTmp){
			var str = "";
			if(strTmp.indexOf("/") != -1){
				str = "";
			}
			var len = 8-strTmp.length;
			for(var v=0;v<len;v++){
				str += "  ";
			}
			return strTmp+str;
		},
		selectOnChange: function(){
			var index = $("#alarmMsg").get(0).selectedIndex;
			$("tr[id^=row]",$("#ftlyAlarmList")).eq(index).trigger("click");
			delete index;
			
		},
		selectUp: function(){
			var index = $("#alarmMsg").get(0).selectedIndex - 1;
			$("tr[id^=row]",$("#ftlyAlarmList")).eq(index).trigger("click");
			delete index;
			
		},
		selectDown: function(){
			var index = $("#alarmMsg").get(0).selectedIndex + 1;
			$("tr[id^=row]",$("#ftlyAlarmList")).eq(index).trigger("click");
			delete index;
			
		},
	    cleartext: function (){
	    	if('处理意见'==$.trim(jQuery('#opeerate_desc').val())){
	    		jQuery('#opeerate_desc').val('');
	    		jQuery('#opeerate_desc').css('color','black');
	    	}
	    },
	    clearData: function(){
	    	var thisObj = this;
	    	thisObj.alarmType.length = 0;
			thisObj.alarmTime.length = 0;
			thisObj.alarmID.length = 0;
			$("#addOill").val("");
			$("#driverEmp").val("");
			$("#drivertel").val("");
			$("#opeerate_desc").val("");
	    },
	    updateFtlyAlarm: function(){
	    	var thisObj = this;
	    	var ftlyId = $("#ftlyId").val();
	    	if(ftlyId.length == 0){
	    		tishiShow("没有要处理的油量告警信息!");
    			//tishiHide();
	    		return ;
	    	}
	    	var opeerateDesc = $("#opeerate_desc").val();
	    	if(opeerateDesc.length==0 || opeerateDesc == "处理意见"){
	    		tishiShow("处理意见不能为空!");
    			//tishiHide();
	    		return false;
	    	}
	    	if(opeerateDesc.length>100){
	    		tishiShow("处理意见太长，请控制在100字内!");
	    		//tishiHide();
	    		return false;
	    	}
	    	jQuery.ajax({
				type: 'POST',
				url: "updateAlarmMsg.shtml",
				dataType: "json",
				data:  {
					'operateDesc': opeerateDesc,
					'ftlyId': ftlyId
				},
				success: function(data){
					if(data.returns == "success"){
						thisObj.clearData();
						$("#ftlyId").val("");
		    			tishiShow("油量告警处理成功!");
		    			//tishiHide();
						isDisplayLabel = false;
						jQuery('#ftlyAlarmList').flexReload();
					}
				}
			});
//	    	delete ftlyId;
//	    	delete opeerateDesc;
	    	
	    },
	    bindCheckDesc:function(){
	    	$('#opeerate_desc').bind("blur", function(event){ 
	    		var val = $(this).val();
	    		var len = 0; 
	    		for (var i = 0; i < val.length; i++) { 
	    			if (val.chartAt(i).match(/[^\x00-\xff]/ig) != null) {//全角 
	    				len += 2; 
	    				//如果是全角，占用两个字节 
	    			}else{
	    				len += 1; 
	    			}
	    			//半角占用一个字节 
	    		}
	    		if(len > 100){
	    			alert("只能填写100个字!");
	    		}
	    	});
	    }
};

function tishiShow(info){
	jQuery("#inforeault").html(info);
	jQuery("#Layer1").show().fadeOut(2000);
}
function tishiHide(){
	window.setTimeout("hideshowresultDiv(1)",1000);
}

function hideshowresultDiv(flag){
	if(flag==1){
		jQuery('#Layer1').css('display','none');
	}
	else if(flag==0){
		jQuery('#Layer1').css('display','block');
	}
}

var alarmControlObj = null;
$(function(){
	alarmControlObj = new alarmControl();
	alarmControlObj.loadMap();
	alarmControlObj.init();
});