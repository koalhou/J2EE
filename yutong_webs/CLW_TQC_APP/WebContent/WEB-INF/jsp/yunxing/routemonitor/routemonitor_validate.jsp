<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/RouteChartDWR.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>

<script type="text/javascript">
var timerID = "";
function auto_size(){
	//计算中区高度
	jQuery('#content').mk_autoresize({
		height_include : ['#content_rightside', '#content_leftside'],
		height_offset : 0,
		width_exclude: ['#content_leftside'],
		width_include : ['#content_rightside'],
		width_offset : 2
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize({
		height_exclude : ['.treeTab', '.newsearchPlan'],
		height_include : '#treeDemoDiv',
		height_offset : 10
	});
	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize({
		height_exclude : '.titleBar' ,
		height_include : '.rmMain',
		height_offset : 0
	});
	jQuery('#rm_route_big').mk_autoresize({
		width_include : ['#rm_route1','#rm_route2','#rm_route3','#rm_route4','#rm_route5','#rm_route6','#rm_route7'],
		width_offset : 80
	});
	//计算线路图区域高度
	jQuery('.rmMain').mk_autoresize({
		height_exclude : ['.rmTitle','.rmRoutePlan','.rmForm'],
		height_include : '#rm_route_big',
		height_offset : 0
	});
	jQuery('.rmMain').mk_autoresize({
		width_include : ['#rm_route1','#rm_route2','#rm_route3','#rm_route4','#rm_route5','#rm_route6','#rm_route7'],
		width_offset : 80
	});
	jQuery('.rmMain').mk_autoresize({
		width_include : ['#mk_routemonitor_route_up1','#mk_routemonitor_route_up2','#mk_routemonitor_route_up3','#mk_routemonitor_route_up4','#mk_routemonitor_route_up5','#mk_routemonitor_route_up6','#mk_routemonitor_route_up7','#tonqincar1','#tonqincar2','#tonqincar3','#tonqincar4','#tonqincar5','#tonqincar6','#tonqincar7'],
		width_offset : 80
	});
	/* jQuery('.rmMain').mk_autoresize({
		width_include : ['#mk_routemonitor_route_up1','#mk_routemonitor_route_up2','#mk_routemonitor_route_up3','#mk_routemonitor_route_up4','#mk_routemonitor_route_up5','#mk_routemonitor_route_up6','#mk_routemonitor_route_up7'],
		width_offset : 120
	}); */
}
/**
 * 左侧树区域显示控制
 */
function HideandShowControl(){
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
	jQuery('#content_rightside').mk_autoresize({
		height_exclude : '.titleBar' ,
		height_include : '.rmMain',
		height_offset : 0
	});
	//计算线路图区域高度
	jQuery('.rmMain').mk_autoresize({
		height_include : '#rm_route_big',
		width_offset : 80
	});

	jQuery('.rmMain').mk_autoresize({
		width_include : ['#rm_route1','#rm_route2','#rm_route3','#rm_route4','#rm_route5','#rm_route6','#rm_route7'],
		width_offset : 80
	});
}
function treeShow(){
	jQuery('#content').mk_autoresize({
        width_exclude: ['#content_leftside','#content_middelside'],
        width_include: '#content_rightside',
        width_offset: 2,// 该值各页面根据自己的页面布局调整
        is_handler: false
    });
	jQuery('#content_leftside').mk_autoresize({
		height_exclude : [ '.treeTab', '.newsearchPlan'],
		height_include : '#treeDemoDiv',
		height_offset : 10
	});
	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize({
		height_exclude : '.titleBar' ,
		height_include : '.rmMain',
		height_offset : 0
	});
	jQuery('.rmMain').mk_autoresize({
		height_include : '#rm_route_big',
		height_offset : 40
	});

	jQuery('.rmMain').mk_autoresize({
		width_include : ['#rm_route1','#rm_route2','#rm_route3','#rm_route4','#rm_route5','#rm_route6','#rm_route7'],
		width_offset : 80
	});
	jQuery('.rmMain').mk_autoresize({
		width_include : ['#tonqincar1','#tonqincar2','#tonqincar3','#tonqincar4','#tonqincar5','#tonqincar6','#tonqincar7'],
		width_offset : 80
	});
}
function choiceCarln() {
	art.dialog.open("<s:url value='/infomanage/chooseCar.shtml' />",{
		title:"车辆信息",
		lock:true,
		width:260,
		height:435
	});
} 
function patchchoiceCarln() {
	art.dialog.open("<s:url value='/infomanage/patchchooseCar.shtml' />",{
		title:"车辆信息",
		lock:true,
		width:260,
		height:435
	});
} 
//左侧树，线路点击事件
function onClickRoute(route_id,route_name){
	if(chart_route_id != route_id){
		oprationType = "SR";
		chart_route_id = route_id;
		selectLn = route_name;
		select_site = "";
		//填写线路名称和线路负责人
		//updateRouteTitle();
		data_list_car1 = new Array();
		data_list_car2 = new Array();
		data_list_car3 = new Array();
		data_list_car4 = new Array();
		data_list_car5 = new Array();
		data_list_car6 = new Array();
		data_list_car7 = new Array();
		makeChart();
		//updateVehSelect();
		if(typeof(document.getElementById("infobarDivNO")) != "undefined"){
			document.getElementById("infobarDivNO").style.display = "none";
		}
		//closeCarPOP();
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
/**
* 线路图控制
*/	
var chart_route_id = "";
var selectLn = "";//线路节点名称
var select_site = "";
var oprationType = "INIT";
//如果要分页这里定义页数属性
var pagenumlength = 1;
//画线路图、更新站点进出站信息
function makeChart(){
	if(!isNull(chart_route_id)){
		RouteChartDWR.getChart(chart_route_id, pagenumlength,"", jQuery('#user_org_id').val(), backFun_getChartInfo);
		RouteChartDWR.getChartCarList(chart_route_id, pagenumlength,"", jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo);
	}else{
		chart_route_id = "";
		cancelTime();
		select_site = "";
		clear_rm();
	}
}
//线路图回调函数
function backFun_getChartInfo(resultList){
	//resultList 做循环显示线路list 中的站点  最多3个  判断resultList
	if(resultList.length==1) {
		/* var divshow1 = "<div style='height: 30px; line-height: 30px;'>周一</div>";
		var divshow2 = "<div style='height: 30px; line-height: 30px;'>周二</div>";
		var divshow3 = "<div style='height: 30px; line-height: 30px;'>周三</div>";
		var divshow4 = "<div style='height: 30px; line-height: 30px;'>周四</div>";
		var divshow5 = "<div style='height: 30px; line-height: 30px;'>周五</div>";
		var divshow6 = "<div style='height: 30px; line-height: 30px;'>周六</div>";
		var divshow7 = "<div style='height: 30px; line-height: 30px;'>周日</div>"; */
		//divshowln = "<div class='mk-rm-route-til-css'>"+selectLn+"</div>";
		jQuery("#mk_routemonitor_route_up1 .mk-rm-route-til2").html("<div class='mk-rm-route-til-css'><b>周一</b></div>");
		jQuery("#mk_routemonitor_route_up2 .mk-rm-route-til2").html("<div class='mk-rm-route-til-css'><b>周二</b></div>");
		jQuery("#mk_routemonitor_route_up3 .mk-rm-route-til2").html("<div class='mk-rm-route-til-css'><b>周三</b></div>");
		jQuery("#mk_routemonitor_route_up4 .mk-rm-route-til2").html("<div class='mk-rm-route-til-css'><b>周四</b></div>");
		jQuery("#mk_routemonitor_route_up5 .mk-rm-route-til2").html("<div class='mk-rm-route-til-css'><b>周五</b></div>");
		jQuery("#mk_routemonitor_route_up6 .mk-rm-route-til2").html("<div class='mk-rm-route-til-css'><b>周六</b></div>");
		jQuery("#mk_routemonitor_route_up7 .mk-rm-route-til2").html("<div class='mk-rm-route-til-css'><b>周日</b></div>");
		jQuery("#routeid1").val(chart_route_id);
		backFun_getChartInfo_info(resultList[0]);
	} else {
		jQuery("#mk_routemonitor_route_up1 .mk-rm-route-til2").html("");
		jQuery("#mk_routemonitor_route_up2 .mk-rm-route-til2").html("");
		jQuery("#mk_routemonitor_route_up3 .mk-rm-route-til2").html("");
		jQuery("#mk_routemonitor_route_up4 .mk-rm-route-til2").html("");
		jQuery("#mk_routemonitor_route_up5 .mk-rm-route-til2").html("");
		jQuery("#mk_routemonitor_route_up6 .mk-rm-route-til2").html("");
		jQuery("#mk_routemonitor_route_up7 .mk-rm-route-til2").html("");
		clear_rm(1);
		clear_rm(2);
		clear_rm(3);
		clear_rm(4);
		clear_rm(5);
		clear_rm(6);
		clear_rm(7);
	}
}
var data_list_car1 = new Array();
var data_list_car2 = new Array();
var data_list_car3 = new Array();
var data_list_car4 = new Array();
var data_list_car5 = new Array();
var data_list_car6 = new Array();
var data_list_car7 = new Array();
function backFun_getChartInfo_charinfo(resultList) {
	data_list_car1 = new Array();
	data_list_car2 = new Array();
	data_list_car3 = new Array();
	data_list_car4 = new Array();
	data_list_car5 = new Array();
	data_list_car6 = new Array();
	data_list_car7 = new Array();
	//先循环线路,线路循环完毕循环车辆	共3条线路	这里也是分页
	if(null!=resultList && resultList.length>0){
		if(resultList.length==1) {
			var datav = resultList[0];
			for(var i=0;i<datav.length;i++) {
				if(datav[i].week_seq == '1') {
					data_list_car1.push(datav[i]);
				} else if(datav[i].week_seq == '2') {
					data_list_car2.push(datav[i]);
				} else if(datav[i].week_seq == '3') {
					data_list_car3.push(datav[i]);
				} else if(datav[i].week_seq == '4') {
					data_list_car4.push(datav[i]);
				} else if(datav[i].week_seq == '5') {
					data_list_car5.push(datav[i]);
				} else if(datav[i].week_seq == '6') {
					data_list_car6.push(datav[i]);
				} else if(datav[i].week_seq == '7') {
					data_list_car7.push(datav[i]);
				}
			}
			reloadlistcar(data_list_car1, 1);
			reloadlistcar(data_list_car2, 2);
			reloadlistcar(data_list_car3, 3);
			reloadlistcar(data_list_car4, 4);
			reloadlistcar(data_list_car5, 5);
			reloadlistcar(data_list_car6, 6);
			reloadlistcar(data_list_car7, 7);
			changgecarlist_(showcarchangeinfo,1);
			changgecarlist_(showcarchangeinfo,2);
			changgecarlist_(showcarchangeinfo,3);
			changgecarlist_(showcarchangeinfo,4);
			changgecarlist_(showcarchangeinfo,5);
			changgecarlist_(showcarchangeinfo,6);
			changgecarlist_(showcarchangeinfo,7);
		}
	}
}

function backFun_getChartInfo_charinforeflush(resultList) {
	if(jQuery("#route_list_ii").val()==1)
		data_list_car1 = new Array();
	else if(jQuery("#route_list_ii").val()==2)
		data_list_car2 = new Array();
	else if(jQuery("#route_list_ii").val()==3)
		data_list_car3 = new Array();
	else if(jQuery("#route_list_ii").val()==4)
		data_list_car4 = new Array();
	else if(jQuery("#route_list_ii").val()==5)
		data_list_car5 = new Array();
	else if(jQuery("#route_list_ii").val()==6)
		data_list_car6 = new Array();
	else if(jQuery("#route_list_ii").val()==7)
		data_list_car7 = new Array();
	
	if(null!=resultList && resultList.length>0){
		if(resultList.length==1) {
			for(var i=0;i<resultList[0].length;i++) {
				if(resultList[0][i].week_seq == '1') {
					data_list_car1.push(resultList[0][i]);
				} else if(resultList[0][i].week_seq == '2') {
					data_list_car2.push(resultList[0][i]);
				} else if(resultList[0][i].week_seq == '3') {
					data_list_car3.push(resultList[0][i]);
				} else if(resultList[0][i].week_seq == '4') {
					data_list_car4.push(resultList[0][i]);
				} else if(resultList[0][i].week_seq == '5') {
					data_list_car5.push(resultList[0][i]);
				} else if(resultList[0][i].week_seq == '6') {
					data_list_car6.push(resultList[0][i]);
				} else if(resultList[0][i].week_seq == '7') {
					data_list_car7.push(resultList[0][i]);
				}
			}
			if(jQuery("#route_list_ii").val()==1) {
				reloadlistcar(data_list_car1, 1);
				changgecarlist_(showcarchangeinfo,1);
			}else if(jQuery("#route_list_ii").val()==2) {
				reloadlistcar(data_list_car2, 2);
				changgecarlist_(showcarchangeinfo,2);
			}else if(jQuery("#route_list_ii").val()==3) {
				reloadlistcar(data_list_car3, 3);
				changgecarlist_(showcarchangeinfo,3);
			}else if(jQuery("#route_list_ii").val()==4) {
				reloadlistcar(data_list_car4, 4);
				changgecarlist_(showcarchangeinfo,4);
			}else if(jQuery("#route_list_ii").val()==5) {
				reloadlistcar(data_list_car5, 5);
				changgecarlist_(showcarchangeinfo,5);
			}else if(jQuery("#route_list_ii").val()==6) {
				reloadlistcar(data_list_car6, 6);
				changgecarlist_(showcarchangeinfo,6);
			}else if(jQuery("#route_list_ii").val()==7) {
				reloadlistcar(data_list_car7, 7);
				changgecarlist_(showcarchangeinfo,7);
			}
		}
	}
}
function backFun_getChartInfo_info(resultList){
	if(null!=resultList && resultList.length>0){
		var up_site_arr = new Array();
		var down_site_arr = new Array();
		var up_car_arr = new Array();
		var down_car_arr = new Array();
		for(var i = 0;i < resultList.length; i++){
			if(resultList[i].re_flag == '0'){//如果不到站
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
						//refreshSite();
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
			}else if(resultList[i].re_flag == '1'){//如果进站
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
		if(resultList.length > 6) {
			jQuery("#rm_route1 .mk-rm-route2 .mk-rm-route-station2 ul").width(145*resultList.length+50);
			jQuery("#rm_route2 .mk-rm-route2 .mk-rm-route-station2 ul").width(145*resultList.length+50);
			jQuery("#rm_route3 .mk-rm-route2 .mk-rm-route-station2 ul").width(145*resultList.length+50);
			jQuery("#rm_route4 .mk-rm-route2 .mk-rm-route-station2 ul").width(145*resultList.length+50);
			jQuery("#rm_route5 .mk-rm-route2 .mk-rm-route-station2 ul").width(145*resultList.length+50);
			jQuery("#rm_route6 .mk-rm-route2 .mk-rm-route-station2 ul").width(145*resultList.length+50);
			jQuery("#rm_route7 .mk-rm-route2 .mk-rm-route-station2 ul").width(145*resultList.length+50);
		}
		if(timerID=='')
			openTime();
	}else{
		cancelTime();
		select_site = "";
		clear_rm(v);
	}
	return true;
}
var chang_list = null;
function changgecarlist_(type,ii) {
	chang_list = jQuery("#tonqincar_list_cl"+ii);
	if(type == '1') {
		var len = chang_list.find("li").length;
		if(len>11) {
			chang_list.width((parseInt(len)-2) * 95 + 120);
		}else {
			chang_list.width("auto");
		};
	} else if(type == '2') {
		var len = chang_list.find("li").length;
		if(len>9) {
			chang_list.width((parseInt(len)-2) * 105 + 120);//126/2
		}else {
			chang_list.width("auto");
		}
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
var tabnum = 4;
var site_io_arr = null;
var frist_site = 0;
var last_site = 0;
var focus_site = 0;
//站点选项卡-onclick事件
function clickSiteTab(i_site){
}
//线路图操作-点击站点，1-更新站点TAB的上下车信息
function refreshSite(){
	if(!isNull(chart_route_id) && !isNull(select_site)){
		RouteChartDWR.getIOinfosBySite(chart_route_id, select_site, "", jQuery('#user_org_id').val(), backFun_getIOinfosBySite);
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
	}
}
var showcarchangeinfo = '1';
function showaddbutton() {
	if(showcarchangeinfo == '1') {
		jQuery("#showaddbutton_check").html("返&nbsp;回");
		jQuery(".changecarinfobutton").show();
		showcarchangeinfo = '2';
	} else if(showcarchangeinfo == '2') {
		jQuery("#showaddbutton_check").html("调&nbsp;整");
		jQuery(".changecarinfobutton").hide();
		showcarchangeinfo = '1';
	}
	for(var v=1;v<8;v++) {
		changgecarlist_(showcarchangeinfo,v);
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
//关闭弹出层
function closepatch_Detail() {
	jQuery("#BgDiv").css("display","none");
	jQuery("#patch_siteStuDetail").css("display","none");
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
		striped :true,     //是否显示斑纹效果，默认是奇偶交互的形式
		usepager :false,  //是否分页
		resizable: false,  //是否可以设置表格大小
		showTableToggleBtn: true// 是否允许显示隐藏列
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
//取消定时器
function cancelTime(){
	window.clearTimeout(timerID);
	timerID = "";	
}
function openTime(){
	cancelTime();
	timerID = setInterval("refreshChart()",45000); //线程暂时关闭
}
function refreshChart(){
	if(!isNull(chart_route_id)){
		oprationType = "RE";
		RouteChartDWR.getChartCarList(chart_route_id, pagenumlength,"", jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo);
	}
}
function tabSwitch(id){
	if(showcarchangeinfo=='2') {
		showcarchangeinfo = '1';
		jQuery("#showaddbutton_check").html("调&nbsp;整");
	}
	
	if(iftruereturn==0) {
		return false;
	}else{
		iftruereturn = 0;
	}
		
	if(id=="treeupid"&&!jQuery("#treeupid").hasClass('tabfocus')){
		backFun_getChartInfo("");
		jQuery("#treeupid").removeClass();
		jQuery("#treedownid").removeClass();
		jQuery("#treetqcid").removeClass();
		jQuery("#treeupid").addClass('tabfocus');
		jQuery("#treedownid").addClass('tab');
		jQuery("#treetqcid").addClass('tab');
		//下两行加载树
		treeType='routeup';
		clear_rm(1);
		clear_rm(2);
		clear_rm(3);
		clear_rm(4);
		clear_rm(5);
		clear_rm(6);
		clear_rm(7);
		chart_route_id = "";
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}else if(id=="treedownid"&&!jQuery("#treedownid").hasClass('tabfocus')){
		backFun_getChartInfo("");
		jQuery("#treeupid").removeClass();
		jQuery("#treedownid").removeClass();
		jQuery("#treetqcid").removeClass();
		jQuery("#treeupid").addClass('tab');
		jQuery("#treedownid").addClass('tabfocus');
		jQuery("#treetqcid").addClass('tab');
		//下两行加载树
		treeType='routedown';
		clear_rm(1);
		clear_rm(2);
		clear_rm(3);
		clear_rm(4);
		clear_rm(5);
		clear_rm(6);
		clear_rm(7);
		chart_route_id = "";
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}else if(id=="treetqcid"&&!jQuery("#treetqcid").hasClass('tabfocus')){
		backFun_getChartInfo("");
		jQuery("#treeupid").removeClass();
		jQuery("#treedownid").removeClass();
		jQuery("#treetqcid").removeClass();
		jQuery("#treeupid").addClass('tab');
		jQuery("#treedownid").addClass('tab');
		jQuery("#treetqcid").addClass('tabfocus');
		//下两行加载树
		treeType='routetqc';
		clear_rm(1);
		clear_rm(2);
		clear_rm(3);
		clear_rm(4);
		clear_rm(5);
		clear_rm(6);
		clear_rm(7);
		chart_route_id = "";
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}
}
var patch_check_html = null;
function patch_check_add() {
	/* jQuery("#vehicle_code").html(''); */
	jQuery("#patch_driver_name").html('');
	jQuery("#driver_id").val('');
	jQuery("#patch_vehicle_ln").val('');
	jQuery("#patch_send_time_s").val('07:00');
	
	if(jQuery("#treetqcid").hasClass('tabfocus')) {
		document.getElementById("patch_send_condition_1").disabled = true;
		jQuery("input[name=patch_turnonvehiclechico][value=1]").attr("checked",'checked');
	} else {
		document.getElementById("patch_send_condition_1").disabled = false;
	}
	if(jQuery("#treeupid").hasClass('tabfocus')||jQuery("#treedownid").hasClass('tabfocus')) {
		document.getElementById("patch_send_condition_2").disabled = true;
		jQuery("input[name=patch_turnonvehiclechico][value=0]").attr("checked",'checked');
	} else {
		document.getElementById("patch_send_condition_2").disabled = false;
	}
	
	jQuery("#BgDiv").css("display","block");
	jQuery("#BgDiv").width(jQuery(window).width());
	jQuery("#BgDiv").height(jQuery(window).height());
	jQuery("#patch_siteStuDetail").css("display","block");
	
	//addorderinfoto	将周下拉选项填充
	/* for(var v=1;v<8;v++) {
		patch_check_html = addorderinfoto(data_list_car1.length,v,1);
		jQuery("#patch_"+v+"_sel").html(patch_check_html);
	} */
	patch_check_html = addorderinfoto(data_list_car1.length,1,2);
	jQuery("#patch_1_sel").html(patch_check_html);
	patch_check_html = addorderinfoto(data_list_car2.length,2,2);
	jQuery("#patch_2_sel").html(patch_check_html);
	patch_check_html = addorderinfoto(data_list_car3.length,3,2);
	jQuery("#patch_3_sel").html(patch_check_html);
	patch_check_html = addorderinfoto(data_list_car4.length,4,2);
	jQuery("#patch_4_sel").html(patch_check_html);
	patch_check_html = addorderinfoto(data_list_car5.length,5,2);
	jQuery("#patch_5_sel").html(patch_check_html);
	patch_check_html = addorderinfoto(data_list_car6.length,6,2);
	jQuery("#patch_6_sel").html(patch_check_html);
	patch_check_html = addorderinfoto(data_list_car7.length,7,2);
	jQuery("#patch_7_sel").html(patch_check_html);
	jQuery("#patch_1_day").attr("checked",'checked');
	jQuery("#patch_2_day").attr("checked",'checked');
	jQuery("#patch_3_day").attr("checked",'checked');
	jQuery("#patch_4_day").attr("checked",'checked');
	jQuery("#patch_5_day").attr("checked",'checked');
	jQuery("#patch_6_day").attr("checked",'checked');
	jQuery("#patch_7_day").attr("checked",'checked');
}
function patch_check_del() {
	if(confirm("清空当前线路下所有发车安排！")) {
		data_list_car1 = new Array();
		data_list_car2 = new Array();
		data_list_car3 = new Array();
		data_list_car4 = new Array();
		data_list_car5 = new Array();
		data_list_car6 = new Array();
		data_list_car7 = new Array();
		reloadlistcar(data_list_car1,1);
		reloadlistcar(data_list_car2,2);
		reloadlistcar(data_list_car3,3);
		reloadlistcar(data_list_car4,4);
		reloadlistcar(data_list_car5,5);
		reloadlistcar(data_list_car6,6);
		reloadlistcar(data_list_car7,7);
		RouteChartDWR.getDelAllCarbyroute_id(chart_route_id,nullfun);
	}
}
function nullfun() {
	
}
function makeWeekList() {
	/* if(data_list_car1.length==0&&data_list_car2.length==0&&data_list_car3.length==0&&data_list_car4.length==0&&data_list_car5.length==0&&data_list_car6.length==0&&data_list_car7.length==0) {
		alert("请选择车辆！");
		return false;
	} */
	var data = {
		"routeInfo.route_id":jQuery("#routeid1").val(),
		"routeInfo.exe_date":jQuery("#queryTime").val()
	};
	jQuery.blockUI({ message: "<h1>正在生成,请稍等...</h1>"});
	jQuery.post("../dispatchroute_chart/makedispatchdata.shtml",data,function(v){
		if(v == 'success') {
			alert("发车安排已生成，次日生效，请到调度中心查看！");
			jQuery.unblockUI();
		}else {
			alert("发车安排已生成失败！");
			jQuery.unblockUI();
		}
	});
}
//查询 线路下车辆线路信息
function searchCarRunList() {
	//RouteChartDWR.getChart(chart_route_id, pagenumlength,"", jQuery('#user_org_id').val(), backFun_getChartInfo);
	RouteChartDWR.getChartCarList(chart_route_id, pagenumlength,"", jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo);
}
jQuery(function() {
	jQuery("#siteStuDetail").easydrag();
	jQuery("#siteStuDetail").setHandler("DetialTitle");
	jQuery("#patch_siteStuDetail").easydrag();
	jQuery("#patch_siteStuDetail").setHandler("patch_DetialTitle");
	jQuery("#BgDiv").css("z-index","104");
	auto_size();
	jQuery(window).mk_autoresize({
	    height_include: '#content',
	    height_exclude: ['#header', '#footer'],
	    height_offset: 0,
	    min_height: 600,
	    min_width: 1024,
	    width_include: ['#header', '#content', '#footer'],
	    width_offset: 0
	});
	//jQuery(window).resize(function(){
		Math.max.apply(null, jQuery.map(jQuery('#siteStuDetail'), function (e, n) {
			if (jQuery(e).css('position') == 'absolute')
				jQuery(e).css("left",((jQuery(document).width())/2-(parseInt(jQuery(e).width())/2))+"px").css("top",((jQuery(document).height())/2-(parseInt(jQuery(e).height())/2))+"px");
		}));
	//});
	//jQuery(window).resize(function(){
		Math.max.apply(null, jQuery.map(jQuery('#patch_siteStuDetail'), function (e, n) {
			if (jQuery(e).css('position') == 'absolute')
				jQuery(e).css("left",((jQuery(document).width())/2-(parseInt(jQuery(e).width())/2))+"px").css("top",((jQuery(document).height())/2-(parseInt(jQuery(e).height())/2))+"px");
		}));
	//});
	auto_size();
});

function searchDriver(v){
	if(v=='1') {
		art.dialog.open("<s:url value='/infomanage/chooseDriver_car.shtml' />",{
			title:"驾驶员信息",
			lock:true,
			width:460,
			height:435
		});
	}else if(v=='2') {
		art.dialog.open("<s:url value='/infomanage/chooseDriver_car_pat.shtml' />",{
			title:"驾驶员信息",
			lock:true,
			width:460,
			height:435
		});
	}
}
function settomodule() {
	art.dialog.open("<s:url value='/carrunmodule/add_xc_trip_module.shtml' />?route_id="+chart_route_id,{
		title:"添加模板",
		lock:true,
		width:460,
		height:235
	});
}
function searchtomodule() {
	art.dialog.open("<s:url value='/carrunmodule/search_xc_trip_module.shtml' />?route_id="+chart_route_id,{
		title:"选择模板",
		lock:true,
		width:460,
		height:435
	});
}
</script>