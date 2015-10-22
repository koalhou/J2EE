<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/RouteChartDWR.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
<script type="text/javascript">
$(function() {
	jQuery("#siteStuDetail").easydrag();
	jQuery("#siteStuDetail").setHandler("DetialTitle"); 
	jQuery("#BgDiv").css("z-index","104");
	
	auto_size();
	jQuery(window).mk_autoresize({
	    height_include: '#content',
	    height_exclude: ['#header', '#footer'],
	    height_offset: 0,
	    width_include: ['#header', '#content', '#footer'],
	    width_offset: 0,
	}); 
	jQuery(window).resize(function(){
		Math.max.apply(null, jQuery.map(jQuery('#siteStuDetail'), function (e, n) {
	        if (jQuery(e).css('position') == 'absolute')
	         jQuery(e).css("left",((jQuery(document).width())/2-(parseInt(jQuery(e).width())/2))+"px")
	        .css("top",((jQuery(document).height())/2-(parseInt(jQuery(e).height())/2))+"px");
	        })
	    );
	});

	auto_size();
});
function auto_size(){
	//计算中区高度
	jQuery('#content').mk_autoresize( {
		height_include : [ '#content_rightside', '#content_leftside' ],
		height_offset : 0,
		width_exclude: ['#content_leftside'],
		width_include : [ '#content_rightside'],
		width_offset : 2
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize( {
		height_exclude : [ '.treeTab', '.newsearchPlan'],
		height_include : '#treeDemoDiv',
		height_offset : 10
	});

	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize( {
		height_exclude : '.titleBar' ,
		height_include : '.rmMain',
		height_offset : 0
	});
	//计算线路图区域高度
	jQuery('.rmMain').mk_autoresize( {
		height_exclude : [ '.rmTitle','.rmRoutePlan','.rmForm'],
		height_include : '#rm_route_big',
		height_offset : 40
	});
	jQuery('.rmMain').mk_autoresize( {
		width_include : '#rm_route',
		width_offset : 60
	});
	
}
/**
 * 左侧树区域显示控制
 */
function HideandShowControl(){//leftdiv
	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
		jQuery('#middeldiv').css("display","block");
		jQuery('#leftdiv').css("display","none");
		treeHide();
		
	}else{//隐藏状态
		jQuery('#middeldiv').css("display","none");
		jQuery('#leftdiv').css("display","block");
		treeShow();
	}
}
function treeHide(){
	jQuery('#content').mk_autoresize({
        width_exclude: '#content_middelside',
        width_include: '#content_rightside',
        width_offset: 2,// 该值各页面根据自己的页面布局调整
        is_handler: false
    });
	
	
	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize( {
		height_exclude : '.titleBar' ,
		height_include : '.rmMain',
		height_offset : 0
	});
	//计算线路图区域高度
	jQuery('.rmMain').mk_autoresize( {
		height_exclude : [ '.rmTitle','.rmRoutePlan','.rmForm'],
		height_include : '#rm_route_big',
		width_offset : 40
	});
	

	jQuery('.rmMain').mk_autoresize( {
		width_include : '#rm_route',
		width_offset : 60
	});	
}
function treeShow(){
	jQuery('#content').mk_autoresize({
        width_exclude: ['#content_leftside','#content_middelside'],
        width_include: '#content_rightside',
        width_offset: 2,// 该值各页面根据自己的页面布局调整
        is_handler: false
    });
	jQuery('#content_leftside').mk_autoresize( {
		height_exclude : [ '.treeTab', '.newsearchPlan'],
		height_include : '#treeDemoDiv',
		height_offset : 10

	});

	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize( {
		height_exclude : '.titleBar' ,
		height_include : '.rmMain',
		height_offset : 0
	});
	//计算线路图区域高度
	jQuery('.rmMain').mk_autoresize( {
		height_exclude : [ '.rmTitle','.rmRoutePlan','.rmForm'],
		height_include : '#rm_route_big',
		height_offset : 40
	});
	jQuery('.rmMain').mk_autoresize( {
		width_include : '#rm_route',
		width_offset : 60
	});
}
//左侧树，线路点击事件
function onClickRoute(route_id){
	if(chart_route_id != route_id){		
		oprationType = "SR";
		chart_route_id = route_id;
		selectVIN = "";
		select_site = "";
		//填写线路名称和线路负责人
		updateRouteTitle();
		makeChart();
		updateVehSelect();
		if(typeof(document.getElementById("infobarDivNO")) != "undefined"){
			document.getElementById("infobarDivNO").style.display = "none";
		}
		closeCarPOP();		
	}
}
///左侧树，查询线路
function searchRoute() {
	routename = jQuery("#search_route_name").val();
	searchTree();
}
function updateRouteTitle(){
	if(!isNull(chart_route_id)){
		RouteChartDWR.getRouteInfosByRoute(chart_route_id, jQuery('#user_org_id').val(), backFun_updateRouteTitle);
	}else{
		jQuery("#route_name").html("--------");
		jQuery("#route_person").html("负责人：--");
	}
}
function backFun_updateRouteTitle(routeList){
	if(null!=routeList && routeList.length>0){
		jQuery("#route_name").html(encodeHtml(routeList[0].route_name));
		jQuery("#route_person").html("负责人："+encodeHtml(routeList[0].route_incharge_person));
	}else{
		jQuery("#route_name").html("--------");
		jQuery("#route_person").html("负责人：--");
	}
}
//根据线路变化车辆下拉框内容
function updateVehSelect(){
	if(!isNull(chart_route_id)){
		RouteChartDWR.getVehByRoute(chart_route_id, jQuery('#user_org_id').val(), backFun_getVehSelect);
		selectVIN = "";
	}	
}
function backFun_getVehSelect(vehList){
	DWRUtil.removeAllOptions("selectBus");
	var all = "全部车辆";
	DWRUtil.addOptions("selectBus",{"":all});		
	if(null!=vehList && vehList.length>0){
		DWRUtil.addOptions("selectBus",vehList,"VIN","vehicle_ln");
	}
}
//车牌号下拉框onchange事件
function queryVeh(v_vin){
	if(selectVIN != v_vin){
		selectVIN = v_vin;
		RouteChartDWR.getChart(chart_route_id, selectVIN, jQuery('#user_org_id').val(), backFun_getChartInfo);
		closeCarPOP();
		oprationType = "SC";
	}
}
/**
* 线路图控制
*/	
var chart_route_id = "";
var selectVIN = "";
var select_site = "";
var oprationType = "INIT";
//画线路图、更新站点进出站信息
function makeChart(){
	if(!isNull(chart_route_id)){
		RouteChartDWR.getChart(chart_route_id, selectVIN, jQuery('#user_org_id').val(), backFun_getChartInfo);
	}else{
		chart_route_id = "";
		cancelTime();
		select_site = "";
		clear_rm();
	}
}
//线路图回调函数
function backFun_getChartInfo(resultList){
	if(null!=resultList && resultList.length>0){
		var up_site_arr = new Array();
		var down_site_arr = new Array();
		var up_car_arr = new Array();
		var down_car_arr = new Array();
		for(var i = 0;i < resultList.length; i++){
			if(resultList[i].re_flag == '0'){
				if(i == 0 && (oprationType == 'SR' || oprationType == 'SC' || oprationType == 'INIT')){
					if(select_site==''){
						var temp = "0";
						if(resultList[i].site_updown == "0"){
							temp="up";
						}else {
							temp="down";
						}						
						sitebrwose(resultList[i].site_id,resultList[i].site_name,temp);
					}else{
						refreshSite();
					}					
				}
				
				var stu_error = resultList[i].stu_error;
				var time_error = resultList[i].time_error;
				var site_status = -1;
				if(stu_error == -1 && time_error == -1){
					site_status = -1;					
				}else if(stu_error>0 && time_error>0){
					site_status = 3;
				}else if(time_error>0){
					site_status = 1;
				}else if(stu_error>0){
					site_status = 2;
				}else if(stu_error == 0 && time_error == 0){
					site_status = 0;
				}
				var site = new mk_routemonitor_site(resultList[i].site_id,resultList[i].site_name,site_status,resultList[i].upNum,resultList[i].downNum);
				if(resultList[i].site_updown == '0'){
					up_site_arr.push(site);
				}else if(resultList[i].site_updown == '1'){
					down_site_arr.push(site);
				}
			}else if(resultList[i].re_flag == '1'){
				var v_ln = resultList[i].vehicle_ln;
				if(v_ln.length > 7){
					v_ln = "*" + v_ln.substring(v_ln.length-7,v_ln.length);
				}				
				var car = new mk_routemonitor_car(resultList[i].VIN,v_ln,resultList[i].site_id,resultList[i].inout_flag);
				if(resultList[i].site_updown == '0'){
					up_car_arr.push(car);
				}else if(resultList[i].site_updown == '1'){
					down_car_arr.push(car);
				}
			}
		}	
		var route_data = new mk_routemonitor(chart_route_id,up_site_arr,down_site_arr,up_car_arr,down_car_arr);
		show_rm(route_data);
		if(timerID == ""){
			openTime();
		}
	}else{
		cancelTime();
		select_site = "";
		clear_rm();
	}
}

function isNull(data){
	if(data == null || data == ""){
		return true;
	}
	return false;		
}
/**
* 线路图-站点选项卡
*/
//线路图操作-点击站点入口
function sitebrwose(v_site_id,v_site_name,v_site_updown){
	var site_updown="";
	if(v_site_updown == "up"){
		site_updown="上学";
	}else {
		site_updown="放学";
	}
	jQuery("#tab_site_name").html(site_updown+"--"+encodeHtml(v_site_name)+"站");
	select_site = v_site_id;
	refreshSite();
}
//线路图操作-点击站点，1-更新站点TAB的上下车信息
function refreshSite(){
	if(!isNull(chart_route_id) && !isNull(select_site)){
		RouteChartDWR.getIOinfosBySite(chart_route_id, select_site, selectVIN, jQuery('#user_org_id').val(), backFun_getIOinfosBySite);
	}		
}
function backFun_getIOinfosBySite(ioList){
	if(null!=ioList && ioList.length>0){
		jQuery('#siteTab').css("display","");		
		var tabHtmlStr = "";
		for(var i = 0;i < ioList.length; i++){
			var v_ln = ioList[i].vehicle_ln;
			if(v_ln.length > 7){
				v_ln = "*" + v_ln.substring(v_ln.length-7,v_ln.length);
			}
			if(i == 0){
				tabHtmlStr = tabHtmlStr + '<li><a id="tab'+ i +'" class="rmbusNumberFocus" onclick="clickSiteTab('+ i +')">'+ encodeHtml(v_ln) 
									+'<span class="rmTime">('+ioList[i].realityInTimeStr+')</span></a></li>';
				editSiteIOinfo(ioList[i]);
			}else if(i>0 && i<tabnum){
				tabHtmlStr = tabHtmlStr + '<li><a id="tab'+ i +'" class="rmbusNumber" onclick="clickSiteTab('+ i +')">'+ encodeHtml(v_ln) 
									+'<span class="rmTime">('+ioList[i].realityInTimeStr+')</span></a></li>';
			}
		}
		jQuery("#siteTab").html('<li><a class="rmArrowLeft" onclick="clickArrow(1)"></a></li><li><a class="rmArrowRight" onclick="clickArrow(-1)"></a>'+ tabHtmlStr);
		site_io_arr = ioList;
		focus_site = 0;
		frist_site = 0;
		last_site = i;
		if(last_site > tabnum-1){
			last_site = 3;
		}
	}else{
		jQuery('#siteTab').css("display","none");
		editSiteIOinfo(null);
	}
}

var tabnum = 4;
var site_io_arr = null;
var frist_site = 0;
var last_site = 0;
var focus_site = 0;
//站点选项卡-onclick事件
function clickSiteTab(i_site){
	if(focus_site != i_site){
		if(focus_site >=frist_site && focus_site <= last_site){
			document.getElementById('tab'+ focus_site).className = "rmbusNumber";
		}	
		document.getElementById('tab'+ i_site).className = "rmbusNumberFocus";
		editSiteIOinfo(site_io_arr[i_site]);
		focus_site = i_site;
	}
}
//站点选项卡-左(右)移动事件
function clickArrow(offset){
	if(site_io_arr.length > tabnum){
		var beg_i = frist_site + offset;
		var end_i = last_site + offset;		
		if(end_i < site_io_arr.length && beg_i >= 0){
			var tabHtmlStr = "";
			for(var i = beg_i;i <= end_i; i++){
				var v_ln = site_io_arr[i].vehicle_ln;
				if(v_ln.length > 7){
					v_ln = "*" + v_ln.substring(v_ln.length-7,v_ln.length);
				}
				if(i == focus_site){
					tabHtmlStr = tabHtmlStr + '<li><a id="tab'+ i +'" class="rmbusNumberFocus" onclick="clickSiteTab('+ i +')">'+ encodeHtml(v_ln) 
					+'<span class="rmTime">('+site_io_arr[i].realityInTimeStr+')</span></a></li>';						
				}else{
					tabHtmlStr = tabHtmlStr + '<li><a id="tab'+ i +'" class="rmbusNumber" onclick="clickSiteTab('+ i +')">'+ encodeHtml(v_ln) 
					+'<span class="rmTime">('+site_io_arr[i].realityInTimeStr+')</span></a></li>';
				}
			}
			jQuery("#siteTab").html('<li><a class="rmArrowLeft" onclick="clickArrow(1)"></a></li><li><a class="rmArrowRight" onclick="clickArrow(-1)"></a>'+ tabHtmlStr);
			frist_site = beg_i;
			last_site = end_i;
		}			
	}
}
//站点选项卡-更新详细信息事件
function editSiteIOinfo(obj){
	if(isNull(obj)){
		jQuery("#site_in_time").html("");
		jQuery("#site_stop_time").html("");
		jQuery("#site_plan_up_num").html("");
		jQuery("#site_plan_down_num").html("");
		jQuery("#site_qj_num").html("");
		jQuery("#site_stop_desc").html("");
		jQuery("#site_reality_up_num").html("");
		jQuery("#site_reality_down_num").html("");
		jQuery("#site_no_up_num").html("");
		jQuery("#site_no_down_num").html("");
	}else{
		jQuery("#site_in_time").html(obj.realityInTime);
		jQuery("#site_stop_time").html(obj.stopingtime);
		jQuery("#site_plan_up_num").html(isFFFF(obj.bindUpNum));
		jQuery("#site_plan_down_num").html(isFFFF(obj.bindDownNum));
		jQuery("#site_qj_num").html('<a href="javascript:showViloationDetail(\'请假学生记录\',\'\',\'\',\'\',\'\',\'\',\'2\',\'' 
				+ obj.id + '\',\'QJ\')">'+ isFFFF(obj.qjia_num) +'</a>');
		jQuery("#site_stop_desc").html(obj.inoutremark);
		jQuery("#site_reality_up_num").html('<a href="javascript:showViloationDetail(\'实乘车学生记录\',\''
							+ chart_route_id + '\',\''
							+ select_site + '\',\''
							+ obj.VIN + '\',\''
							+ obj.realityInTime + '\',\''
							+ obj.realityOutTime + '\',\'0\',\'\',\'\')">'+ isFFFF(obj.upNum) +'</a>');
		jQuery("#site_reality_down_num").html('<a href="javascript:showViloationDetail(\'实下车学生记录\',\''
							+ chart_route_id + '\',\''
							+ select_site + '\',\''
							+ obj.VIN + '\',\''
							+ obj.realityInTime + '\',\''
							+ obj.realityOutTime + '\',\'1\',\'\',\'\')">'+ isFFFF(obj.downNum) +'</a>');
		jQuery("#site_no_up_num").html('<a href="javascript:showViloationDetail(\'未乘车学生记录\',\'\',\'\',\'\',\'\',\'\',\'2\',\'' 
				+ obj.id + '\',\'79\')">'+ isFFFF(obj.no_up_num) +'</a>');
		jQuery("#site_no_down_num").html('<a href="javascript:showViloationDetail(\'未下车学生记录\',\'\',\'\',\'\',\'\',\'\',\'2\',\'' 
				+ obj.id + '\',\'80\')">'+ isFFFF(obj.no_down_num) +'</a>');
	}	
}
function isFFFF(data){
	if(data == null || data == "-1"){
		return "";
	}
	return data + "人";	
}
/**
* 线路图-学生乘车详细信息弹出窗口
*/
//关闭弹出层
function closeViloationDetail() {
	jQuery("#BgDiv").css("display","none");
	jQuery("#siteStuDetail").css("display","none");
}
function showViloationDetail(title,r_id,s_id,v_vin,beg_time,end_time,vss_flag,io_id,alarm_id) {
	jQuery("#siteStuDetail").css("top","169px");
	jQuery("#siteStuDetail").css("left","248px");
	jQuery("#BgDiv").css("display","block");
	jQuery("#siteStuDetail").css("display","block");
	jQuery("#detailTile").html(title);
	getDetailList(r_id,s_id,v_vin,beg_time,end_time,vss_flag,io_id,alarm_id);
}

function getDetailList(r_id,s_id,v_vin,beg_time,end_time,vss_flag,io_id,alarm_id){
	var detailgrid = jQuery('#detailgrid');
	detailgrid.flexigrid({
		  dataType: 'json',    //json格式
		  height: 322,
		  width: 725,
		  colModel : [ 
		        {display: '学生', name : 'STU_NAME', width : 75, sortable : true, align: 'center',escape: true},
		        {display: '状态', name : 'VSS_FLAG', width : 100, sortable : true, align: 'center',escape: true},
		        {display: '学号', name : 'STU_CODE', width : 80, sortable : true, align: 'center',escape: true},
		        {display: '学校', name : 'STU_SCHOOL', width : 100, sortable : true, align: 'center',escape: true},
		        {display: '班级', name : 'STU_CLASS', width : 100, sortable : true, align: 'center',escape: true},
		        {display: '班主任联系电话', name : 'TEACHER_TEL', width : 85, sortable : true, align: 'center',escape: true},
		        {display: '家长联系电话', name : 'RELATIVE_TEL', width : 80, sortable : true, align: 'center',escape: true}
		        ],
			       sortname: 'STU_NAME',
			       sortorder: 'desc',  //升序OR降序
			       sortable: true,   //是否支持排序
			       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
			       usepager :false,  //是否分页
			       resizable: false,  //是否可以设置表格大小
			       showTableToggleBtn: true   // 是否允许显示隐藏列
		     });
	searchDetailGrid(r_id,s_id,v_vin,beg_time,end_time,vss_flag,io_id,alarm_id);	
}
function searchDetailGrid(r_id,s_id,v_vin,beg_time,end_time,vss_flag,io_id,alarm_id){
	jQuery('#detailgrid').flexOptions({
		newp: 1,
		  url: '<s:url value="/route_chart/stuList.shtml" />', 
		  params: [{name: 'routeInfo.route_id', value: r_id }, 
		           {name: 'routeInfo.site_id', value: s_id },
		           {name: 'routeInfo.VIN', value: v_vin },
		           {name: 'routeInfo.begTime', value: beg_time }, 
		           {name: 'routeInfo.endTime', value: end_time },
		           {name: 'routeInfo.VSS_FLAG', value: vss_flag },
		           {name: 'routeInfo.id', value: io_id}, 
		           {name: 'routeInfo.alarm_type_id', value: alarm_id }]					
	});
	jQuery('#detailgrid').flexReload();	
}
/*
 * 页面定时刷新部分
 */
var refresh = 1000*10*3;	
var timerID = "";	
//取消定时器
function cancelTime(){
	window.clearTimeout(timerID);
	timerID = "";	
}
function openTime(){
	cancelTime();
	timerID = setInterval("refreshChart()",refresh);
}
function refreshChart(){
	if(!isNull(chart_route_id)){
		oprationType = "RE";
		RouteChartDWR.getChart(chart_route_id, selectVIN, jQuery('#user_org_id').val(), backFun_getChartInfo);
	}
}
</script>