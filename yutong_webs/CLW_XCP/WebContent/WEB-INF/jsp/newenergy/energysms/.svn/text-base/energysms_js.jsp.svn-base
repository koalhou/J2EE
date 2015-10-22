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
			height_include : '.table_list',
			height_offset : 70,
			width_include : ['.car-info','.table_list'],
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

function mytreeonClick(type, treeId){
	jQuery("#isSearch").val("");
	jQuery("#alarmKey").val("");
	if(type=='1'){
		selectCar(treeId);
	}else{
		selectEnt(treeId);	
	}
}

function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}
var pomSel7 = null;
jQuery(function() {
	pomSel7 = new newSelect(jQuery("#routeName"),120);
	pomSel7.addSelect();
	getRouteSel();
	var galaList = jQuery('#galaList');
	 galaList.flexigrid({
		  dataType: 'json',    //json格式
		  height: 1500,
		  width: 1500,
		  colModel : [ 
				{display: '<input id="carChoiceAll" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', name : 'id', width : 25, sortable : false, align: 'center', hide: false,escape: true,process:reWriteCheckBox},
				{display: 'id', name : 'id', width : 25, sortable : false, align: 'center', hide: true,escape: true},
				{display: 'id', name : 'vehicle_vin', width : 25, sortable : false, align: 'center', hide: true,escape: true},						  
				{display: '车牌号', name : 'vehicle_ln', width : 120, sortable : true, align: 'center',escape: true},
				{display: '所属组织', name : 'organization_id', width : 170, sortable : true, align: 'center',escape: true},
				{display: '所属线路', name : 'route_name', width : 170, sortable : true, align: 'center',escape: true},
				{display: '提醒类型', name : 'alarm_type', width : 300, sortable : false, align: 'center',escape: true},
				{display: '', name : 'smsCount', width : 300, sortable : true, align: 'center',escape: true,hide: true},
				{display: '操作', name : 'create_time', width : 120, sortable : false, align: 'center',escape: true,process: reWriteLink}
		        ], 
		       sortname: 'short_name',
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
	 searchsmsList();
});

function reWriteLink(tdDiv,pid,row){
	var tdV = "";
	var vehicleVin = row.cell[2];
	var count = row.cell[7];
	if(Number(count) > 0){
		tdV = "<a href=\"javascript:void(0);\" onclick=\"openWinSign('"+vehicleVin+"','edit')\">修改</a>&nbsp;&nbsp;"+
		"<a href=\"javascript:void(0);\" onclick=\"openWinSign('"+vehicleVin+"','look')\">查看</a>";
	} else {
		tdV = "<a href=\"javascript:void(0);\" onclick=\"openWinSign('"+vehicleVin+"','edit')\">设置</a>";
	}
	return tdV;	
}

function reWriteCheckBox(tdDiv,pid,row){
	return '<input name="carChoice" value="' + row.cell[2] + '" type="checkbox"/>';
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
	 				pomSel7.addOption(objs.route_id,objs.route_name);
	 			}
	 			pomSel7.seBindOption(function(){searchsmsList();});
			 } else {
				 
			 }
	      },
		  error:function (){
	      }
	});
}
//单辆车设置
var editOrLook = "";
function getEidtOrLook(){
	return editOrLook;
}
function openWinSign(vin,suc){
	editOrLook = suc;
	art.dialog.open("<s:url value='/energySMS/iframeSmsSet.shtml' />?flag=0&vins="+vin,{
		id:'smsSet',
		title:'车辆短信提醒设置',
		lock:true,
		width:590,
		height:340,
		fixed:true,
		initFn:function(){
			
		},
		closeFn:function(){
			//cancelArt();
		}
	}); 
}
//批量设置
function openWinBatch(){
	
	editOrLook = "edit";
	//var vin = jQuery
	getCheckBoxVal();
	if(''==finalVIN){
		alert('请选择要设置的车辆!');
		return;
	}else{
		art.dialog.open("<s:url value='/energySMS/iframeSmsSet.shtml' />?flag=1&vins="+finalVIN,{
			id:'smsSet',
			title:'车辆短信提醒批量设置',
			lock:true,
			width:590,
			height:340,
			fixed:true,
			initFn:function(){
				
			},
			closeFn:function(){
				//cancelArt();
			}
		}); 
	}
}

function getCheckBoxVal(){
	finalVIN = "";
	var vins = new Array();
	jQuery("input[name=carChoice]").each(function(i){
		if(jQuery(this).attr("checked")==true){
			vins.push(jQuery(this).val());
		}
	});
	finalVIN = vins.join(",");
}

function openorganizidtree(){
	

	art.dialog.open("<s:url value='/usm/usermanagetreeAction.shtml' />?rad="+Math.random(),
			//userupdatetreeAction.shtml?userID=" + userid,
			{
			width :260,
			height:300,
			id: 'treeOID',
			title: ' ',
			skin: 'aero',
			limit: true,
			lock: true,
			drag: true,
			fixed: false,
				
				//follow: document.getElementById('organizationName'),
				yesFn: function(iframeWin, topWin){
		        	//对话框iframeWin中对象
		        	//alert(iframeWin.test);
		        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
		        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
		        	//当前页面中对象
		            var topOrgName =  window.document.getElementById('organizname');
		            var topOrgID = window.document.getElementById('organization_id');
		            //赋值
		        	if (topOrgName) topOrgName.value = orgNameValue;
		        	if (topOrgID) topOrgID.value = orgIDValue;
		    	}
		  
			});

}



/* 关闭dialog 窗口 */
function cancelArt(winName){
	art.dialog.get(winName).close();
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
		height_include : [ '#content_rightside'],
		height_offset : 0,
		//width_exclude: ['#content_leftside'],
		width_include : [ '#content_rightside'],
		width_offset : 1
	});
	//计算左测区域高度
// 	jQuery('#content_leftside').mk_autoresize( {
// 		height_exclude : [ '#leftInfoDiv', '.newsearchPlan'],
// 		height_include : '#lefttree',
// 		height_offset : 10

// 	});

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
		height_include : '.table_list',
		height_offset : 0,
		width_include : ['.car-info','.table_list'],
		width_offset : 0
	});		
	jQuery('.table_list').mk_autoresize({
	    height_include: '.bDiv',
	    height_offset: 70, // 该值各页面根据自己的页面布局调整
	    width_include: '.flexigrid',
	    width_offset: 8// 该值各页面根据自己的页面布局调整
	  });
}

function keyEnter(event){
	var e = event ? event : window.event;
	if(e.keyCode=='13'){
		searchsmsList();
	}
}

//查询
function searchsmsList() {
	jQuery('#galaList').flexOptions({
		newp: 1,
		url: '<s:url value="/energySMS/findSmsPageList.shtml" />',
		params: [{name: 'vehicleVin', value: finalVIN },
		         {name: 'vehicleln',value: jQuery("#vehicleln").val()},
			     {name: 'startTime', value: jQuery("#startTime").val()},
			     {name: 'endTime', value: jQuery("#endTime").val()},
			     {name: 'routeName',value: jQuery("#routeName").val()=="--请选择--"?"":jQuery("#routeName").val()},
			     {name: 'organization_id',value: jQuery("#organization_id").val()},
			     {name: 'organizname',value: jQuery("#organizname").val()}]
	});
	jQuery('#galaList').flexReload();
}

function smsSetSearch(){
	finalVIN="";
	searchsmsList();
}

//选择车辆后，刷新车辆运行日志数据
var finalVIN = "";
var finalENT = "";
function selectCar(radioObj){
	//finalVIN = radioObj;
	//finalENT = "";
	searchsmsList();
}
function selectEnt(radioObj){
	//finalVIN = "";
	//finalENT = radioObj;
	searchsmsList();
}	

</script>
