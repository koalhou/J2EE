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
			//height_exclude : ['.week_list'],
			height_include : '.table_list',
			height_offset : 0,
			width_include : ['.table_list'],
			width_offset : 0
		});		
		jQuery('.table_list').mk_autoresize({
		    height_include: '.bDiv',
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

function mytreeonClick(treeId){
	selectCar(treeId);
}

function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

jQuery(function() {
	//upDateTab("6");
	var galaList = jQuery('#galaList');
	 galaList.flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width: 1500,
		  colModel : [ 
// 				{display: '序号', name : 'ROWNUMBER', width : 25, sortable : false, align: 'center',escape: true},
				{display: '车辆VIN', name : 'vehicle_vin', width : 150, sortable : false, align: 'center', hide: true, escape: true},
				{display: '企业ID', name : 'enterprise_id', width : 60, sortable : false, align: 'center', hide: true,escape: true},
				{display: '机构ID', name : 'ORGANIZATION_ID', width : 120, sortable : false, align: 'center', hide: true,escape: true},
				{display: '是否设置', name : 'is_set', width : 120, sortable : false, align: 'center', hide: true,escape: true},
				{display: '车牌号', name : 'vehicle_ln', width : 100, sortable : true, align: 'center',escape: true},							  
		        {display: '企业', name : 'enterprise_id', width : 200, sortable : true, align: 'center',escape: true},
		        {display: '机构', name : 'ORGANIZATION_ID', width : 200, sortable : true, align: 'center',escape: true},
		        {display: '品牌', name : 'vehbrand', width : 60, sortable : true, align: 'center',escape: true},
		        {display: '载客人数', name : 'LIMITE_NUMBER', width : 60, sortable : true, align: 'center',escape: true},	        
				{display: '操作', name : '', width : 100, sortable : true, align: 'center',escape: true, process: reWriteLink}
		        ],
		       sortname: 'vehicle_ln',
		       sortorder: 'asc',  //升序OR降序
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

function reWriteLink(tdDiv,pid,row){
	
	var vehicleVin = row.cell[0];
	var vehicleLn = row.cell[4];
	var isSet = row.cell[3];
	var tdV = "";
	tdV = "<a href=\"javascript:openSet('"+vehicleVin+"','1','"+vehicleLn+"')\">设置</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if(isSet == "1"){
		tdV = tdV + "<a href=\"javascript:openSet('"+vehicleVin+"','2','"+vehicleLn+"')\">查看</a>";
	}
	return tdV;	
}
function openSetVins(flag){
	if(finalVIN == '-----' || finalVIN.length == 0){
		return;
	}
	winVehicle = "";
	//<s:url value='/photograph/initSet.shtml' />
	art.dialog.open("../photograph/initSet.shtml?vehicleVin="+finalVIN+"&isOperator=3",{
			id:'phoSetId',
			title:flag=="1"?'定时拍照设置':'定时拍照查看',
			lock:flag,
			width:560,
			height:490,
			fixed:true,
			//content:jQuery("#stationInfoDiv").html(),
			initFn:function(){
				
			},
			closeFn:function(){
				//cancelArt();
			}
	}); 
}
function openSet(vin,flag,vehicleln){
	if(vin.length == 0){
		return ;
	}
	if(finalVIN == '-----' && finalVIN.length == 0){
		return;
	}
	//<s:url value='/photograph/initSet.shtml' />
	art.dialog.open("../photograph/initSet.shtml?vehicleVin="+vin+"&isOperator="+flag,{
			id:'phoSetId',
			title:flag=="1"?"<span style='font-weight: bold;font-size: 16px;'>"+vehicleln+'</span>--定时拍照设置':"<span style='font-weight: bold;font-size: 16px;'>"+vehicleln+'</span>--定时拍照查看',
			lock:flag,
			width:560,
			height:490,
			fixed:true,
			//content:jQuery("#stationInfoDiv").html(),
			initFn:function(){
				
			},
			closeFn:function(){
				//cancelArt();
			}
	}); 
}

/* 关闭dialog 窗口 */
function cancelArt(){
	art.dialog.get('phoSetId').close();
}

function tishiWin(content){
	art.dialog.alert(content);
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
		//height_exclude : ['.week_list'],
		height_include : '.table_list',
		height_offset : 0,
		width_include : ['.table_list'],
		width_offset : 0
	});		
	jQuery('.table_list').mk_autoresize({
	    height_include: '.bDiv',
	    height_offset: 70, // 该值各页面根据自己的页面布局调整
	    width_include: '.flexigrid',
	    width_offset: 8// 该值各页面根据自己的页面布局调整
	  });
}


//查询
function searchPhotoVehicleSetList() {
	//getTabSatus();
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '<s:url value="/photograph/photoSetVehicleList.shtml" />',
		params: [{name: 'vehicleVin', value: finalVIN }]
// 			     {name: 'weeks', value: weeks}]
	});
	jQuery('#galaList').flexReload();
}

function searchFresh(){
	jQuery('#galaList').flexReload();
}

// function exportData(){
// 	if(finalVIN != ""){
// 		var vehicleln="";
// 		var node = jQuery.fn.zTree.getZTreeObj("treeDemo").getNodeByParam("id", finalVIN, null);
// 		if(node != null) {
// 			vehicleln = node.name;
// 		}
// 		if(confirm("确定要导出车辆运行日志吗？")) {
// 			document.getElementById('exportObj.vehicle_ln').value = vehicleln;
// 			document.getElementById('exportObj.queryTime').value = finalTime;
// 			document.getElementById('export_form').action="<s:url value='/carrun/exportRunHistory.shtml' />";
// 			document.getElementById('export_form').submit();
// 		}
// 	}else {
// 		alert("请选择车辆！");
// 	}
// }

//选择车辆后，刷新车辆运行日志数据
var finalVIN = "";
function selectCar(radioObj){
	if(radioObj.length != 0){
		finalVIN = radioObj;
	} else {
		//finalVIN = "";
		
		//return ;
		finalVIN = "-----";
	}
	searchPhotoVehicleSetList();
}

//选择日期后，点击查询按钮
function queryWeekList(){
	//查询按钮处默认选中最后一个页签
	upDateTab("6");
	//searchPhotoVehicleSetList();
}
//点击页签
function changeChoose(id){
	upTabSatus(id);
	searchPhotoVehicleSetList();			
}		
//根据日期重新更新页面7个TAB项，全局日期数组
var pageDate = [];
function upDateTab(go){
	var queryTime="";
	
	if(go=="6"){
		document.getElementById('newDayer').value=document.getElementById('queryTime').value;
		queryTime=document.getElementById('queryTime').value.replace(/-/g,"/");
	}
	if(go=="0"){
		document.getElementById('newDayer').value=getDay(document.getElementById('newDayer').value,"0");
		queryTime=document.getElementById('newDayer').value.replace(/-/g,"/");
	}
	if(go=="1"){
		document.getElementById('newDayer').value=getDay(document.getElementById('newDayer').value,"1");
		queryTime=document.getElementById('newDayer').value.replace(/-/g,"/");
	}
	
	var queryDate = new Date(queryTime);
	var tempDate = queryDate;
	for(var i=6;i>=0;i--){
		if(i != 6){
			tempDate.setTime(tempDate.getTime() - 1000*60*60*24);
		}				
		var str = tempDate.pattern("yyyy-MM-dd");
		pageDate[i] = str;

		var aObjDay = document.getElementById('day'+ i);
		aObjDay.innerText = tempDate.pattern("MM-dd");

		var aObjWeek = document.getElementById('week'+ i);
		aObjWeek.innerText = tempDate.pattern("EE");

	}
	if(go=="6"){
		upTabSatus("6");
	}else{
		upTabSatus(getTabSatus());
	}
}
//TAB状态更新
var finalTime;
function upTabSatus(id){
	//finalTime = pageDate[id];
	var temp = id;	
	var className = jQuery("#li"+id).attr("class");
	if(className == "today"){
		jQuery("#li"+id).removeClass("today");
	} else {
		jQuery("#li"+id).addClass("today");
	}			
}

var weeks = "";
function getTabSatus(){
	var week = new Array();
	for(var i=1;i<=7;i++){
		if(jQuery("#li"+i).attr("class")=="today"){
			week.push(i);
		}
	}
	if(week.length != 0){
		weeks = week.join(",");
	} else {
		weeks = "-----";
	}
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
