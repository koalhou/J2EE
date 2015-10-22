<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/DispatchrouteChartDWR.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
<script type="text/javascript">
//线路位置	站点栏滚动条动态位置记录
var scrollLeft1;
var scrollLeft2;
var scrollLeft3;
//线路位置	车辆栏滚动条动态位置记录
var scrollcarLeft1;
var scrollcarLeft2;
var scrollcarLeft3;

//页面自适应
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
	//计算线路图区域高度
	jQuery('.rmMain').mk_autoresize({
		height_exclude : ['.titleBar','.rmTitle','.rmRoutePlan','.rmForm'],
		height_include : '#rm_route_big',
		height_offset : 60
	});
	jQuery('#rm_route_big').mk_autoresize({
		width_include : ['#rm_route','#rm_route2','#rm_route3'],
		width_offset : 80
	});
	jQuery('.rmMain').mk_autoresize({
		width_include : ['#rm_route','#rm_route2','#rm_route3'],
		width_offset : 80
	});
	
	jQuery('.rmMain').mk_autoresize({
		width_include : ['#tonqincar','#tonqincar2','#tonqincar3','#mk_routemonitor_route_up','#mk_routemonitor_route_up2','#mk_routemonitor_route_up3'],
		width_offset : 80
	});
}
/**
 * 左侧树区域显示控制 暂不使用
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
		width_include : ['#rm_route','#rm_route2','#rm_route3'],
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
		height_offset : 60
	});

	jQuery('.rmMain').mk_autoresize({
		width_include : ['#rm_route','#rm_route2','#rm_route3'],
		width_offset : 80
	});
	jQuery('.rmMain').mk_autoresize({
		width_include : ['#tonqincar','#tonqincar2','#tonqincar3'],
		width_offset : 80
	});
	//计算线路图区域高度
	/* jQuery('.rmMain').mk_autoresize({
		height_exclude : [ '.rmTitle','.rmRoutePlan','.rmForm'],
		height_include : '#rm_route_big',
		height_offset : 0
	});
	jQuery('.rmMain').mk_autoresize( {
		width_include : ['#rm_route','#rm_route2','#rm_route3'],
		width_offset : 80
	}); */
}
//选择车辆
function choiceCarln() {
	art.dialog.open("<s:url value='/infomanage/getchooseCarList.shtml' />",{
		title:"车辆信息",
		lock:true,
		width:700,
		height:435
	});
}
//选择线路
function choiceRoute() {
	jQuery("#driver_id").val('');
	if(selectVIN!=null&&selectVIN.length>0) {
		art.dialog.open("<s:url value='/infomanage/getchooseRoute.shtml' />?vehicle_vin="+selectVIN+"&exe_date="+jQuery("#queryTime").val(),{
			title:"线路查询",
			lock:true,
			width:260,
			height:465
		});
	} else {
		alert("请选择车辆！");
	}
} 
//左侧树，车辆点击事件
function mytreeonClick(car_id,car_name,code,driname){
	if(!closebackdatainfo())
		return false;
	
	showcarchangeinfo = '1';
	showcarchangeinfo2 = '1';
	showcarchangeinfo3 = '1';
	pagenumlength = 1;
	//if(chart_route_id != route_id){
		jQuery("#vehicle_code_car_r").removeClass();
		jQuery("#vehicle_code_car_num").html('');
		reflashcarlisttype = 0;
		jQuery("#routempageno").val("1");
		oprationType = "SR";
		selectln = car_name;
		selectVIN = car_id;
		vehicle_code = code;
		driver_name = driname;
		select_site = "";
		if(code == '外租')
			jQuery("#checkedvehiclename").html("<font size='4'>"+code+"</font> <font size='3'>("+selectln+")</font>");
		else if(code==null)
			jQuery("#checkedvehiclename").html("<font size='4'>空号</font> <font size='3'>("+selectln+")</font>");
		else
			jQuery("#checkedvehiclename").html("<font size='4'>"+code+"号</font> <font size='3'>("+selectln+")</font>");
		makeChart();
		if(typeof(document.getElementById("infobarDivNO")) != "undefined"){
			document.getElementById("infobarDivNO").style.display = "none";
		}
	//}
}
///左侧树，查询车辆
function searchRoute() {
	if(!closebackdatainfo())
		return false;
	searchTree();
}
//日期控件	上一日
function previousWeek(){
	if(!closebackdatainfo())
		return false;
	upDateTab("0");
	//searchCarRunList("0");
	//这里只刷新树就可以了
	updatequerytime(-1);
	DispatchrouteChartDWR.getcarrouteChart(pagenumlength,selectVIN, jQuery('#queryTime').val(),jQuery('#user_org_id').val(), backFun_getChartInfo);//组织树不刷新
	//searchTree();
}
//日期控件	下一日
function nextWeek(){
	if(!closebackdatainfo())
		return false;
	upDateTab("7");
	//searchCarRunList("7");
	//这里只刷新树就可以了
	updatequerytime(1);
	DispatchrouteChartDWR.getcarrouteChart(pagenumlength,selectVIN, jQuery('#queryTime').val(),jQuery('#user_org_id').val(), backFun_getChartInfo);//组织树不刷新
	//searchTree();
}
//日期控件	选择周几
function changeChoose(id){
	pagenumlength = 1;
	jQuery("#routempageno").val(pagenumlength);
	if(!closebackdatainfo())
		return false;
	
	/**var myDate = new Date();
	var aa = jQuery("#li"+id).children("span");
	jQuery("#queryTime").val(myDate.getFullYear()+"-"+jQuery(aa[1]).html());**/
	var selfday = id - getTabSatus();
	var queryDate = new Date(document.getElementById('queryTime').value.replace(/-/g,"/"));
	var tempDate = queryDate;
	tempDate.setTime(tempDate.getTime() + 1000*60*60*24*selfday);
	var str = tempDate.pattern("yyyy-MM-dd");
	jQuery("#queryTime").val(str);
	
	upTabSatus(id);
	//searchCarRunList("1");
	//通勤车一期bug  选择时间组织树没有记录选择的车辆
	DispatchrouteChartDWR.getcarrouteChart(pagenumlength,selectVIN, jQuery('#queryTime').val(),jQuery('#user_org_id').val(), backFun_getChartInfo);
	
	//这里只刷新树就可以了
	//searchTree();
}
//月 日 单数补0
function add0todate(m,d) {
	var month = parseInt(m)<=9?"0"+(parseInt(m)):parseInt(m);
	var day = parseInt(d)<=9?"0"+parseInt(d):parseInt(d);
	return month+"-"+day;
}
//选择日期后，点击查询按钮
function queryWeekList(){
	//查询按钮处默认选中最后一个页签
	upDateTab("6");
	//searchCarRunList("1");
	//这里只刷新树就可以了
	updatequerytime();
	searchTree();
}
//查询车辆之前更新查询时间与tab一致
function updatequerytime(sa) {
	for(var i=0;i<7;i++){
		if(document.getElementById('li'+i).className=="today"){
			var selfday = i - getTabSatus();
			//sa上一天 下一天
			if(sa == 1)
				selfday = 1;
			if(sa == -1)
				selfday = -1;
			
			var queryDate = new Date(document.getElementById('queryTime').value.replace(/-/g,"/"));
			var tempDate = queryDate;
			tempDate.setTime(tempDate.getTime() + 1000*60*60*24*selfday);
			var str = tempDate.pattern("yyyy-MM-dd");
			jQuery("#queryTime").val(str);
			
			//var myDate = new Date();
			//var aa = jQuery("#li"+i).children("span");
			//jQuery("#queryTime").val(myDate.getFullYear()+"-"+jQuery(aa[1]).html());
		}
	}
}
//查询 线路下车辆线路信息
function searchCarRunList(go) {
	//新选时间与原时间天数差
	var selfday = "";
	
	reflashcarlisttype = 0;
	
	var starttimes = null;
	if(go=="7"){
		var time = new Date(document.getElementById('queryTime').value.replace(/-/g,"/"));
		var dd = time;
	    dd.setDate(dd.getDate()+1);//获取AddDayCount天后的日期
	    var y = dd.getFullYear();
	    var m = dd.getMonth()+1;//获取当前月份的日期
	    var d = dd.getDate();
	    var starttime = new Date(y, m-1, d);
	    starttimes = starttime.getTime();
	    
	    selfday = y+"-"+add0todate(m,d);
	}
	if(go=="0"){
		var time = new Date(document.getElementById('queryTime').value.replace(/-/g,"/"));
		var dd = time;
	    dd.setDate(dd.getDate()-1);//获取AddDayCount天后的日期
	    var y = dd.getFullYear();
	    var m = dd.getMonth()+1;//获取当前月份的日期
	    var d = dd.getDate();
	    var starttime = new Date(y, m-1, d);
	    starttimes = starttime.getTime();
	    
	    selfday = y+"-"+add0todate(m,d);
	}
	if(go=="1") {
		var time = jQuery("#queryTime").val();
		var arr = time.split("-");
	    var starttime = new Date(arr[0], arr[1]-1, arr[2]);
	    starttimes = starttime.getTime();
	    
	    selfday = arr[0]+"-"+arr[1]+"-"+arr[2];
	}
	//var now = new Date();
    //var lktime = new Date(now.getFullYear(), now.getMonth()+1, now.getDate());
    var nowtimev = getnowandsearcht('now');//lktime.getTime();
    if(starttimes>=nowtimev) {
    	if(jQuery("#mk_routemonitor_route_up .mk-rm-route-til").html().length>0)
    	jQuery('#rm_route').mk_routemonitor("change_cars",{
        	"add_cars":true
        });
    	if(jQuery("#mk_routemonitor_route_up2 .mk-rm-route-til").html().length>0)
    	jQuery('#rm_route2').mk_routemonitor("change_cars",{
        	"add_cars":true
        });
    	if(jQuery("#mk_routemonitor_route_up3 .mk-rm-route-til").html().length>0)
    	jQuery('#rm_route3').mk_routemonitor("change_cars",{
        	"add_cars":true
        });
    	jQuery('#showaddbutton_check').show();
    } else {
    	if(jQuery("#mk_routemonitor_route_up .mk-rm-route-til").html().length>0)
    	jQuery('#rm_route').mk_routemonitor("change_cars",{
        	"add_cars":false
        });
    	if(jQuery("#mk_routemonitor_route_up2 .mk-rm-route-til").html().length>0)
    	jQuery('#rm_route2').mk_routemonitor("change_cars",{
        	"add_cars":false
        });
    	if(jQuery("#mk_routemonitor_route_up3 .mk-rm-route-til").html().length>0)
    	jQuery('#rm_route3').mk_routemonitor("change_cars",{
        	"add_cars":false
        });
    	jQuery('#showaddbutton_check').hide();
    }
    
	for(var i=0;i<7;i++){
		if(document.getElementById('li'+i).className=="today"){
			jQuery("#queryTime").val(selfday);
			
			//var myDate = new Date();
			//var aa = jQuery("#li"+i).children("span");
			//jQuery("#queryTime").val(myDate.getFullYear()+"-"+jQuery(aa[1]).html());
		}
	}
	DispatchrouteChartDWR.getcarrouteChart(pagenumlength,selectVIN, jQuery('#queryTime').val(),jQuery('#user_org_id').val(), backFun_getChartInfo);
	DispatchrouteChartDWR.getcarcountrouteChart(1,selectVIN, jQuery('#queryTime').val(),jQuery('#user_org_id').val(), writepageallsize);
}
function getDay(myday,daynum){
	var dateArr = (myday).split("-");
	var thisday = new Date(dateArr[0], dateArr[1]-1, dateArr[2]);
	if(daynum=="0"){
		thisday=thisday.addDays(-1);
	}else{
		thisday=thisday.addDays(1);
	}
	thisday = new Date(thisday);
	var nowday = new Date();
	/* if(thisday.getTime() > nowday.getTime()){
		thisday = new Date(nowday.getFullYear(), nowday.getMonth(), nowday.getDate());
	}; */
	var thisstr=thisday.getFullYear()+"-" + (thisday.getMonth() + 1) + "-"+ thisday.getDate();
	return thisstr;
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
Date.prototype.addDays = Date.prototype.addDays || function(days){
	this.setDate(this.getDate() + days);
	return this;
};
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
var pageDate = [];
//更新日期控件上周几
function upDateTab(go){
	var queryTime="";
	if(go=="7"){
		var nowd = new Date();
		//var time = new Date((nowd.getFullYear()+"-"+document.getElementById('day6').innerHTML).replace(/-/g,"/"));
		var time = new Date((nowd.getFullYear()+"-"+document.getElementById('day0').innerHTML).replace(/-/g,"/"));
		var dd = time;
	    dd.setDate(dd.getDate()+1);//获取AddDayCount天后的日期
	    var y = dd.getFullYear();
	    var m = dd.getMonth()+1;//获取当前月份的日期
	    var d = dd.getDate();
		var ttime = y+"-"+m+"-"+d;
		document.getElementById('newDayer').value=ttime;
		queryTime=ttime.replace(/-/g,"/");
	}
	if(go=="6"){
		document.getElementById('newDayer').value=document.getElementById('queryTime').value;
		var time = new Date(document.getElementById('queryTime').value.replace(/-/g,"/"));
		var dd = time;
		var vlue = parseInt(getTabSatus());
		if(getTabSatus()!=6)
			dd.setDate(dd.getDate()-vlue);
		else
			dd.setDate(dd.getDate());
		
		var y = dd.getFullYear();
	    var m = dd.getMonth()+1;//获取当前月份的日期
	    var d = dd.getDate();
	    var ttime = y+"-"+m+"-"+d;
		document.getElementById('newDayer').value=ttime;
		queryTime=ttime.replace(/-/g,"/");
	}
	if(go=="0"){
		/* document.getElementById('newDayer').value=getDay(document.getElementById('queryTime').value,"0");//getDay(document.getElementById('newDayer').value,"0");
		queryTime=document.getElementById('newDayer').value.replace(/-/g,"/"); */
		var nowd = new Date();
		//var time = new Date((nowd.getFullYear()+"-"+document.getElementById('day6').innerHTML).replace(/-/g,"/"));
		var time = new Date((nowd.getFullYear()+"-"+document.getElementById('day0').innerHTML).replace(/-/g,"/"));
		var dd = time;
	    dd.setDate(dd.getDate()-1);//获取AddDayCount天后的日期
	    var y = dd.getFullYear();
	    var m = dd.getMonth()+1;//获取当前月份的日期
	    var d = dd.getDate();
		var ttime = y+"-"+m+"-"+d;
		document.getElementById('newDayer').value=ttime;
		queryTime=ttime.replace(/-/g,"/");
	}
	if(go=="1"){
		document.getElementById('newDayer').value=getDay(document.getElementById('newDayer').value,"1");
		queryTime=document.getElementById('newDayer').value.replace(/-/g,"/");
	};
	var queryDate = new Date(queryTime);
	var tempDate = queryDate;
	for(var i=0;i<=6;i++){
		if(i != 0){
			tempDate.setTime(tempDate.getTime() + 1000*60*60*24);
		}
		var str = tempDate.pattern("yyyy-MM-dd");
		pageDate[i] = str;
		var aObjDay = document.getElementById('day'+ i);
		
		aObjDay.innerText = tempDate.pattern("MM-dd");

		var aObjWeek = document.getElementById('week'+ i);
		aObjWeek.innerText = tempDate.pattern("EE");
	};
	if(go=="0"){
		//upTabSatus("6");
	}else{
		upTabSatus(getTabSatus());
	}
}
var finalTime;
//更新当前日期控件选择天
function upTabSatus(id){
	finalTime = pageDate[id];
	var temp = id;
	for(var i=0;i<7;i++){
		if(i != temp){
			document.getElementById('li'+ i).className="";
		}else{
			document.getElementById('li'+id).className="today";
		}
	}
}
function getTabSatus(){
	for(var i=0;i<7;i++){
		if(document.getElementById('li'+i).className=="today"){
			return i;
		}
	}
}
/**
* 线路图控制
*/
var timerID = "";
var chart_route_id = "";
var selectVIN = "";
var selectln = "";
var vehicle_code="";
var driver_name = "";
var select_site = "";
var oprationType = "INIT";
//如果要分页这里定义页数属性
var pagenumlength = 1;
//画线路图、更新站点进出站信息
function makeChart(){
	if(!isNull(selectVIN)){
		//查询车辆线路所属站点
		DispatchrouteChartDWR.getcarrouteChart(pagenumlength,selectVIN, jQuery('#queryTime').val(),jQuery('#user_org_id').val(), backFun_getChartInfo);
		//所属线路多了分页
		DispatchrouteChartDWR.getcarcountrouteChart(pagenumlength,selectVIN, jQuery('#queryTime').val(),jQuery('#user_org_id').val(), writepageallsize);
	}else{
		selectVIN = "";
		cancelTime();
		select_site = "";
		clear_rm();
	}
}
var chang_list = null;
//线路上车辆宽度、滚动条动态设置
function changgecarlist_(type,ii) {
	chang_list = jQuery("#tonqincar_list_cl"+ii);
	if(type == '1') {
		var len = chang_list.find("li").length;
		if(len>11) {
			chang_list.width((parseInt(len)-2) * 95 + 120);
			
			if(scrollcarLeft1!=null&&ii==1)
				jQuery("#tonqincar #tonqincar_list").attr("scrollLeft",scrollcarLeft1);
			else if(scrollcarLeft2!=null&&ii==2)
				jQuery("#tonqincar2 #tonqincar_list").attr("scrollLeft",scrollcarLeft2);
			else if(scrollcarLeft3!=null&&ii==3)
				jQuery("#tonqincar3 #tonqincar_list").attr("scrollLeft",scrollcarLeft3);
		}else {
			chang_list.width("auto");
		};
	} else if(type == '2') {
		var len = chang_list.find("li").length;
		if(len>7) {
			chang_list.width((parseInt(len)-1) * 105 + 120);//126/2
			
			if(scrollcarLeft1!=null&&ii==1)
				jQuery("#tonqincar #tonqincar_list").attr("scrollLeft",scrollcarLeft1);
			else if(scrollcarLeft2!=null&&ii==2)
				jQuery("#tonqincar2 #tonqincar_list").attr("scrollLeft",scrollcarLeft2);
			else if(scrollcarLeft3!=null&&ii==3)
				jQuery("#tonqincar3 #tonqincar_list").attr("scrollLeft",scrollcarLeft3);
		}else {
			chang_list.width("auto");
		}
	}
}
//调整 撤销状态位
var showcarchangeinfo = '1';
var showcarchangeinfo2 = '1';
var showcarchangeinfo3 = '1';
//调整撤销发车安排调用
//当前暂时测试使用，当撤销调整时只刷新当前撤销线路
function showchangebutton(obj,v) {
	if(v==1){
		if(showcarchangeinfo2=='2') {
			if(confirm(getbackd_route_n(2))){
				showcarchangeinfo2 = '1';
				backtripinfo(jQuery("#routeid2").val(),2);
			} else 
				return false;
		} else if(showcarchangeinfo3=='2') {
			if(confirm(getbackd_route_n(3))){
				showcarchangeinfo3 = '1';
				backtripinfo(jQuery("#routeid3").val(),3);
			} else 
				return false;
		}
		
		if(showcarchangeinfo == '1') {
			jQuery(obj).html("撤销调整");
			changgecarlist_('2','1');
			jQuery(".changecarinfobutton").show();
			jQuery("#showaddbutton_check_1").show();
			showcarchangeinfo = '2';
		} else if(showcarchangeinfo == '2') {
			jQuery.post("../dispatchroute_chart/route_exe_car_back.shtml?route_id="+jQuery("#routeid1").val()+"&exe_date="+jQuery("#queryTime").val(),null,function(d){
				if(d=='success') {
					jQuery(obj).html("调&nbsp;整");
					changgecarlist_('1','1');
					jQuery(".changecarinfobutton").hide();
					jQuery("#showaddbutton_check_1").hide();
					showcarchangeinfo = '1';
					//将调整的线路状态回滚	只更新当前线路，其它线路按照定时刷新执行
					DispatchrouteChartDWR.getChartoneCarList(selectVIN, jQuery("#queryTime").val(),jQuery("#routeid1").val(), jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo1);
					//searchCarRunList("1");
				} else {
					alert("数据库连接错误!");
				}
			});
		}
	} else  if(v==2) {
		if(showcarchangeinfo=='2') {
			if(confirm(getbackd_route_n(1))){
				showcarchangeinfo = '1';
				backtripinfo(jQuery("#routeid1").val(),1);
			} else 
				return false;
		} else if(showcarchangeinfo3=='2') {
			if(confirm(getbackd_route_n(3))){
				showcarchangeinfo3 = '1';
				backtripinfo(jQuery("#routeid3").val(),3);
			} else 
				return false;
		}
		if(showcarchangeinfo2 == '1') {
			jQuery(obj).html("撤销调整");
			changgecarlist_('2','2');
			jQuery(".changecarinfobutton2").css("display","block");
			jQuery("#showaddbutton_check_2").show();
			showcarchangeinfo2 = '2';
		} else if(showcarchangeinfo2 == '2') {
			jQuery.post("../dispatchroute_chart/route_exe_car_back.shtml?route_id="+jQuery("#routeid2").val()+"&exe_date="+jQuery("#queryTime").val(),null,function(d){
				if(d=='success') {
					jQuery(obj).html("调&nbsp;整");
					changgecarlist_('1','2');
					jQuery(".changecarinfobutton2").hide();
					jQuery("#showaddbutton_check_2").hide();
					showcarchangeinfo2 = '1';
					//将调整的线路状态回滚	只更新当前线路，其它线路按照定时刷新执行
					DispatchrouteChartDWR.getChartoneCarList(selectVIN, jQuery("#queryTime").val(),jQuery("#routeid2").val(), jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo2);
					//searchCarRunList("1");
				} else {
					alert("数据库连接错误!");
				}
			});
		}
	} else  if(v==3) {
		if(showcarchangeinfo=='2') {
			if(confirm(getbackd_route_n(1))){
				showcarchangeinfo = '1';
				backtripinfo(jQuery("#routeid1").val(),1);
			} else 
				return false;
		} else if(showcarchangeinfo2=='2') {
			if(confirm(getbackd_route_n(2))){
				showcarchangeinfo2 = '1';
				backtripinfo(jQuery("#routeid2").val(),2);
			} else 
				return false;
		}
		if(showcarchangeinfo3 == '1') {
			jQuery(obj).html("撤销调整");
			changgecarlist_('2','3');
			jQuery(".changecarinfobutton3").show();
			jQuery("#showaddbutton_check_3").show();
			showcarchangeinfo3 = '2';
		} else if(showcarchangeinfo3 == '2') {
			jQuery.post("../dispatchroute_chart/route_exe_car_back.shtml?route_id="+jQuery("#routeid3").val()+"&exe_date="+jQuery("#queryTime").val(),null,function(d){
				if(d=='success') {
					jQuery(obj).html("调&nbsp;整");
					changgecarlist_('1','3');
					jQuery(".changecarinfobutton3").hide();
					jQuery("#showaddbutton_check_3").hide();
					showcarchangeinfo3 = '1';
					//将调整的线路状态回滚	只更新当前线路，其它线路按照定时刷新执行
					DispatchrouteChartDWR.getChartoneCarList(selectVIN, jQuery("#queryTime").val(),jQuery("#routeid3").val(), jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo3);
					//searchCarRunList("1");
				} else {
					alert("数据库连接错误!");
				}
			});
		}
	}
	//searchCarRunList("1");
}
//其它未关闭发车安排调整	方法
function backtripinfo(routeid,i) {
	jQuery.post("../dispatchroute_chart/route_exe_car_back.shtml?route_id="+routeid+"&exe_date="+jQuery("#queryTime").val(),null,function(d){
		if(d=='success') {
			changgecarlist_('1',i);
			if(i==1) {
				showcarchangeinfo = '1';
				jQuery(".changecarinfobutton").hide();
				jQuery("#buttonchangecar1").html("调&nbsp;整");
			}else if(i==2) {
				showcarchangeinfo2 = '1';
				jQuery(".changecarinfobutton"+i).hide();
				jQuery("#buttonchangecar2").html("调&nbsp;整");
			}else if(i==3) {
				showcarchangeinfo3 = '1';
				jQuery(".changecarinfobutton"+i).hide();
				jQuery("#buttonchangecar3").html("调&nbsp;整");
			}
			jQuery("#showaddbutton_check_"+i).hide();
			
		} else {
			alert("数据库连接错误!");
		}
	});
}
//线路图回调函数
function getupdownsite(v) {
	return v==null?"":v==0?"早班":v==1?"晚班":"厂内通勤";
}

var car_info_rclass1 = null;
var car_info_divshow1 = "";
var car_info_rclass2 = null;
var car_info_divshow2 = "";

var reflashcarlisttype = 0;

var reflashcaristype1 = 0;
var reflashcaristype2 = 0;
var reflashcaristype3 = 0;


function zhuanyi(str){
	var str2="";
	for(var i=0;i<str.length;i++){ 
		if(str.charAt(i)==">"){
			str2=str2+"&gt;";
		}else if(str.charAt(i)=="<"){
			str2=str2+"&lt;";
		}else{
			str2=str2+str.charAt(i);
		}
	}
	return str2;
}

//查询车辆线路返回	方法
function backFun_getChartInfo(resultList){
	jQuery("#rm_route").hide();
	jQuery("#rm_route2").hide();
	jQuery("#rm_route3").hide();
	$("div .tableroute_car_info_blue").each(function() {
		$(this).removeClass().addClass("tableroute_car_info_grey");
	});
	
	if(resultList.length==1) {
		car_info_rclass = resultList[0][0].route_class;
		reflashcaristype1 = car_info_rclass;
		car_info_divshow = '<div class="mk-rm-route-til-css">'+zhuanyi(resultList[0][0].route_name) + "<br>("+getupdownsite(car_info_rclass)+")"+"</div>";
		jQuery("#mk_routemonitor_route_up .mk-rm-route-til").html("<B>"+car_info_divshow+"</B>");
		jQuery("#mk_routemonitor_route_up2 .mk-rm-route-til").html("");
		jQuery("#mk_routemonitor_route_up3 .mk-rm-route-til").html("");
		jQuery("#rm_route").show();
		jQuery("#routeid1").val(resultList[0][0].route_id);
		backFun_getChartInfo_info(resultList[0],0);
		//根据线路查询车辆
		//if(reflashcarlisttype==0)
		DispatchrouteChartDWR.getChartoneCarList(selectVIN, jQuery("#queryTime").val(),resultList[0][0].route_id, jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo1);
		
		clear_rm(2);
		clear_rm(3);
	} else if(resultList.length==2){
		car_info_rclass = resultList[0][0].route_class;
		car_info_rclass1 = resultList[1][0].route_class;
		reflashcaristype1 = car_info_rclass;
		reflashcaristype2 = car_info_rclass1;
		car_info_divshow = '<div class="mk-rm-route-til-css">'+zhuanyi(resultList[0][0].route_name) + "<br>("+getupdownsite(car_info_rclass)+")"+"</div>";
		car_info_divshow1 = '<div class="mk-rm-route-til-css">'+zhuanyi(resultList[1][0].route_name) + "<br>("+getupdownsite(car_info_rclass1)+")"+"</div>";
		jQuery("#mk_routemonitor_route_up .mk-rm-route-til").html("<B>"+car_info_divshow+"</B>");
		jQuery("#mk_routemonitor_route_up2 .mk-rm-route-til").html("<B>"+car_info_divshow1+"</B>");
		jQuery("#mk_routemonitor_route_up3 .mk-rm-route-til").html("");
		jQuery("#rm_route").show();
		jQuery("#rm_route2").show();
		jQuery("#routeid1").val(resultList[0][0].route_id);
		jQuery("#routeid2").val(resultList[1][0].route_id);
		backFun_getChartInfo_info(resultList[0],0);
		backFun_getChartInfo_info(resultList[1],1);
		//根据线路查询车辆
		//if(reflashcarlisttype==0) {
			DispatchrouteChartDWR.getChartoneCarList(selectVIN, jQuery("#queryTime").val(),resultList[0][0].route_id, jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo1);
			DispatchrouteChartDWR.getChartoneCarList(selectVIN, jQuery("#queryTime").val(),resultList[1][0].route_id, jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo2);
		//}
		clear_rm(3);
	} else if(resultList.length==3){
		car_info_rclass = resultList[0][0].route_class;
		car_info_rclass1 = resultList[1][0].route_class;
		car_info_rclass2 = resultList[2][0].route_class;
		reflashcaristype1 = car_info_rclass;
		reflashcaristype2 = car_info_rclass1;
		reflashcaristype3 = car_info_rclass2;
		car_info_divshow = '<div class="mk-rm-route-til-css">'+zhuanyi(resultList[0][0].route_name) + "<br>("+getupdownsite(car_info_rclass)+")"+"</div>";
		car_info_divshow1 = '<div class="mk-rm-route-til-css">'+zhuanyi(resultList[1][0].route_name) + "<br>("+getupdownsite(car_info_rclass1)+")"+"</div>";
		car_info_divshow2 = '<div class="mk-rm-route-til-css">'+zhuanyi(resultList[2][0].route_name) + "<br>("+getupdownsite(car_info_rclass2)+")"+"</div>";
		jQuery("#mk_routemonitor_route_up .mk-rm-route-til").html("<B>"+car_info_divshow+"</B>");
		jQuery("#mk_routemonitor_route_up2 .mk-rm-route-til").html("<B>"+car_info_divshow1+"</B>");
		jQuery("#mk_routemonitor_route_up3 .mk-rm-route-til").html("<B>"+car_info_divshow2+"</B>");
		
		jQuery("#rm_route").show();
		jQuery("#rm_route2").show();
		jQuery("#rm_route3").show();
		
		jQuery("#routeid1").val(resultList[0][0].route_id);
		jQuery("#routeid2").val(resultList[1][0].route_id);
		jQuery("#routeid3").val(resultList[2][0].route_id);
		backFun_getChartInfo_info(resultList[0],0);
		backFun_getChartInfo_info(resultList[1],1);
		backFun_getChartInfo_info(resultList[2],2);
		//if(reflashcarlisttype==0) {
			DispatchrouteChartDWR.getChartoneCarList(selectVIN, jQuery("#queryTime").val(),resultList[0][0].route_id, jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo1);
			DispatchrouteChartDWR.getChartoneCarList(selectVIN, jQuery("#queryTime").val(),resultList[1][0].route_id, jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo2);
			DispatchrouteChartDWR.getChartoneCarList(selectVIN, jQuery("#queryTime").val(),resultList[2][0].route_id, jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo3);
		//}
	} else {
		jQuery("#mk_routemonitor_route_up .mk-rm-route-til").html("");
		jQuery("#mk_routemonitor_route_up2 .mk-rm-route-til").html("");
		jQuery("#mk_routemonitor_route_up3 .mk-rm-route-til").html("");
		clear_rm(1);
		clear_rm(2);
		clear_rm(3);
	}
	//writepageallsize(resultList.length);//隐藏上下页的图标
	reflashcarlisttype = 1;
}
var data_list_car = [];
var data_list_car2 = [];
var data_list_car3 = [];
//线路下车辆查询返回方法
function backFun_getChartInfo_charinfo1(resultList) {
	if(null!=resultList && resultList.length>0){
		if(resultList.length==1) {
			data_list_car = resultList[0];
			reloadlistcar(data_list_car, 1);
			changgecarlist_(showcarchangeinfo,1);
		}
	}
}
function backFun_getChartInfo_charinfo2(resultList) {
	if(null!=resultList && resultList.length>0){
		if(resultList.length==1) {
			data_list_car2 = resultList[0];
			reloadlistcar(data_list_car2, 2);
			changgecarlist_(showcarchangeinfo2,2);
		}
	}
}
function backFun_getChartInfo_charinfo3(resultList) {
	if(null!=resultList && resultList.length>0){
		if(resultList.length==1) {
			data_list_car3 = resultList[0];
			reloadlistcar(data_list_car3, 3);
			changgecarlist_(showcarchangeinfo3,3);
		}
	}
}
//线路下 站点查询返回方法
function backFun_getChartInfo_info(resultList,v){
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
						sitebrwose(resultList[i].site_id,resultList[i].site_name,temp,v);
					}else{
						refreshSite();
					}
				}
				var stu_error = resultList[i].stu_error;
				var time_error = resultList[i].time_error;
				/* var site_status = -1;
				//site_status == 1 时是延迟发车  site_status==2 时是提前发车 如果都存在就是错误暂时显示正常
				if(stu_error == -1 && time_error == -1){
					site_status = -1;
				}else if(stu_error>0 && time_error>0){
					site_status = 0;
				}else if(time_error>0){
					site_status = 1;
				}else if(stu_error>0){
					site_status = 2;
				}else if(stu_error == 0 && time_error == 0){
					site_status = 0;
				}; */
				site_status = -1;
				
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
		show_rm(route_data,v);
		if(timerID == ""){
			openTime();
		}
		
		if(resultList.length > 5) {
			if(v==0) {
				jQuery("#rm_route .mk-rm-route .mk-rm-route-station ul").width(145*resultList.length+50);
				
				if(scrollLeft1!=null)
					document.getElementById("mk_up_station").scrollLeft=scrollLeft1;
			} else if(v==1) {
				jQuery("#rm_route2 .mk-rm-route .mk-rm-route-station ul").width(145*resultList.length+50);
				
				if(scrollLeft2!=null)
					document.getElementById("mk_up_station2").scrollLeft=scrollLeft2;
			} else if(v==2) {
				jQuery("#rm_route3 .mk-rm-route .mk-rm-route-station ul").width(145*resultList.length+50);
				
				if(scrollLeft3!=null)
					document.getElementById("mk_up_station3").scrollLeft=scrollLeft3;
			}
		}
	}else{
		cancelTime();
		select_site = "";
		clear_rm(v);
	}
	return true;
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
function sitebrwose(v_site_id,v_site_name,v_site_updown,v){
	var site_updown="";
	if(v_site_updown == "up"){
		site_updown="上学";
	}else {
		site_updown="放学";
	}
	jQuery("#tab_site_name").html(site_updown+"--"+encodeHtml(v_site_name)+"站");
	select_site = v_site_id;
	refreshSite(v);
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
function refreshSite(v){
	/* var chart_route_id=null;
	if(v==0)
		chart_route_id = jQuery("#routeid1").val();
	else if(v==1)
		chart_route_id = jQuery("#routeid2").val();
	else if(v==2)
		chart_route_id = jQuery("#routeid3").val();
	
	if(!isNull(chart_route_id) && !isNull(select_site)){
		DispatchrouteChartDWR.getIOinfosBySite(chart_route_id, select_site, selectVIN, jQuery('#user_org_id').val(), backFun_getIOinfosBySite);
	} */
}
function backFun_getIOinfosBySite(ioList){
	/* if(null!=ioList && ioList.length>0){
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
	} */
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
//放弃调整操作调用	暂不使用
function showcarchangeback() {
	if(showcarchangeinfo=='2') {
		showcarchangeinfo = '1';
		jQuery("#buttonchangecar").html("调整");
	}
	if(showcarchangeinfo2=='2') {
		showcarchangeinfo2 = '1';
		jQuery("#buttonchangecar2").html("调整");
	}
	if(showcarchangeinfo3=='2') {
		showcarchangeinfo3 = '1';
		jQuery("#buttonchangecar3").html("调整");
	}
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
	timerID = setInterval("refreshChart()",15000); //线程暂时关闭
}
//定时刷新当前车辆发车信息
function refreshChart(){
	if(jQuery("#mk_up_station").length>0)
		scrollLeft1 = document.getElementById('mk_up_station').scrollLeft;
	if(jQuery("#mk_up_station2").length>0)
		scrollLeft2 = document.getElementById('mk_up_station2').scrollLeft;
	if(jQuery("#mk_up_station3").length>0)
		scrollLeft3 = document.getElementById('mk_up_station3').scrollLeft;
	
	if(jQuery("#tonqincar #tonqincar_list").length>0) {
		scrollcarLeft1 = jQuery("#tonqincar #tonqincar_list").attr("scrollLeft");
	}if(jQuery("#tonqincar2 #tonqincar_list").length>0)
		scrollcarLeft2 = jQuery("#tonqincar2 #tonqincar_list").attr("scrollLeft");
	if(jQuery("#tonqincar3 #tonqincar_list").length>0)
		scrollcarLeft3 = jQuery("#tonqincar3 #tonqincar_list").attr("scrollLeft");
	if(!isNull(selectVIN)){
		oprationType = "RE";
		DispatchrouteChartDWR.getcarrouteChart(pagenumlength,selectVIN, jQuery('#queryTime').val(),jQuery('#user_org_id').val(), backFun_getChartInfo);
		//DispatchrouteChartDWR.getChart(chart_route_id, pagenumlength,selectVIN, jQuery('#user_org_id').val(), backFun_getChartInfo);
	}
}
function getnowtime(){
	/* var myDate = new Date();
	var month = parseInt(myDate.getMonth())<=8?"0"+(parseInt(myDate.getMonth())+1):parseInt(myDate.getMonth())+1;
	var day = parseInt(myDate.getDate())<=9?"0"+parseInt(myDate.getDate()):parseInt(myDate.getDate());
	return myDate.getFullYear()+"-"+month+"-"+day; */
	return "<s:property value='sysdate' />";
}
//分页
function writepageallsize(res) {
	if(res <3)
		jQuery("#routempageslipe").hide();
	else
		jQuery("#routempageslipe").show();
	
	var no = null;
	if(res%3==0)
		no = parseInt(res/3);
	else
		no = parseInt(res/3)+1;
	jQuery("#routempageall").html("共"+no+"页");
}
function routempagechange(obj) {
	if(!closebackdatainfo())
		return false;
	
	showcarchangeinfo = '1';
	showcarchangeinfo2 = '1';
	showcarchangeinfo3 = '1';
	
	var nowpagelno = parseInt(jQuery("#routempageall").html().substring(1,jQuery("#routempageall").html().length-1));
	var nowpageno = jQuery("#routempageno").val();
	if(nowpageno>0&&nowpageno<=nowpagelno) {
		//pagenumlength = nowpageno;
		makeChart();
	}else
		jQuery("#routempageno").val(pagenumlength);
}
//分页调用前后页	最后开始页
function routempagec(obj) {
	if(!closebackdatainfo())
		return false;
	var nowpagelno = parseInt(jQuery("#routempageall").html().substring(1,jQuery("#routempageall").html().length-1));
	//这里需要有一个最大页的值 用来显示后一页和最大页
	var nowpageno = pagenumlength;//jQuery("#routempageno").val();
	if(jQuery(obj).attr("id") == 'routempagefirst') {
		if(nowpageno!=1) {
			pagenumlength = 1;
			makeChart();
			jQuery("#routempageno").val(pagenumlength);
		};
	}else if(jQuery(obj).attr("id") == 'routempageprev') {
		if(nowpageno!=1) {
			pagenumlength--;
			makeChart();
			jQuery("#routempageno").val(pagenumlength);
		}else {
			jQuery("#routempageno").val(pagenumlength);
		};
	}else if(jQuery(obj).attr("id") == 'routempagenext') {
		if(nowpageno!=nowpagelno) {
			pagenumlength++;
			makeChart();
			jQuery("#routempageno").val(pagenumlength);
		}else {
			jQuery("#routempageno").val(pagenumlength);
		};
	}else if(jQuery(obj).attr("id") == 'routempagelast') {
		if(nowpageno!=nowpagelno) {
			pagenumlength = nowpagelno;
			makeChart();
			jQuery("#routempageno").val(pagenumlength);
		};
	};
}
//修改发车安排，线路下发
function sendcommondbt(ii) {
	var starttimes = getnowandsearcht("search");
	var lktimes = getnowandsearcht("now");
	
	var newstr = "";
	var tripidList = "";
	
	var route_ids = "";
	var validf = "";
	if(ii==null||ii=='') {
		alert("请选择下发线路");
		return false;
	}
	if(ii==1) {
		for(var v=0;v<data_list_car.length;v++) {
			//if((data_list_car[v].crc==null||data_list_car[v].crc.length<1)||(data_list_car[v].valid_flag!=null&&data_list_car[v].valid_flag=='1')||(data_list_car[v].send_condition=='1')||
					//(data_list_car[v].crc!=null&&(data_list_car[v].mesg_flag==null||data_list_car[v].mesg_flag=='0'))) {
				newstr += data_list_car[v].VIN+",";
				tripidList += data_list_car[v].trip_id+",";
				validf += data_list_car[v].valid_flag+",";
				route_ids = data_list_car[v].route_id;
			//}
		};
	} else if(ii==2) {
		for(var v=0;v<data_list_car2.length;v++) {
			//if((data_list_car2[v].crc==null||data_list_car2[v].crc.length<1)||(data_list_car2[v].valid_flag!=null&&data_list_car2[v].valid_flag=='1')||(data_list_car2[v].send_condition=='1')||
					//(data_list_car2[v].crc!=null&&(data_list_car2[v].mesg_flag==null||data_list_car2[v].mesg_flag=='0'))) {
				newstr += data_list_car2[v].VIN+",";
				tripidList += data_list_car2[v].trip_id+",";
				validf += data_list_car2[v].valid_flag+",";
				route_ids = data_list_car2[v].route_id;
			//}
		};
	} else if(ii==3) {
		for(var v=0;v<data_list_car3.length;v++) {
			//if((data_list_car3[v].crc==null||data_list_car3[v].crc.length<1)||(data_list_car3[v].valid_flag!=null&&data_list_car3[v].valid_flag=='1')||(data_list_car3[v].send_condition=='1')||
					//(data_list_car3[v].crc!=null&&(data_list_car3[v].mesg_flag==null||data_list_car3[v].mesg_flag=='0'))) {
				newstr += data_list_car3[v].VIN+",";
				tripidList += data_list_car3[v].trip_id+",";
				validf += data_list_car3[v].valid_flag+",";
				route_ids = data_list_car3[v].route_id;
			//}
		};
	}
	jQuery.blockUI({ message: "<h1>线路下发,请稍等...</h1>"});
	var data = {exe_date:jQuery("#queryTime").val(),carsVinInfos: newstr,tripidInfos:tripidList,route_id:route_ids,valid_flags:validf};
	if(starttimes==lktimes) {
		jQuery.post('../infomanage/sendRouteFileGo.shtml',data,	function(v) { 
	         jQuery.post("../dispatchroute_chart/route_exe_car_totemp.shtml?route_id="+jQuery("#routeid"+ii).val()+"&exe_date="+jQuery("#queryTime").val(),null,function(v){
	 			if(v=='success') {
	 				backpagebutton(ii);
	 				jQuery.unblockUI();
	 			}else {
	 				backpagebutton(ii);
	 				jQuery.unblockUI();
	 			}
	 		});
	    },"text");
	} else {
		//当日数据往后的写入存储表,做表数据
		jQuery.post("../dispatchroute_chart/route_exe_car_totemp.shtml?route_id="+jQuery("#routeid"+ii).val()+"&exe_date="+jQuery("#queryTime").val(),null,function(v){
			if(v=='success') {
				backpagebutton(ii);
				jQuery.unblockUI();
			}else {
				backpagebutton(ii);
				jQuery.unblockUI();
			}
		},"text");
	}
}
//新增线路
function addnewroute() {
	if(!closebackdatainfo())
		return false;
	choiceRoute();
}
function turntosetvehicleorder() {
	if(!closebackdatainfo())
		return false;
	window.location.href = "../routechart/readyrc.shtml";
}
//新增线路回调函数
function reloadaddafterr() {
	var send_condition_t = "";
	if(jQuery("#choicerouteclass").val().length<1) {
		send_condition_t = '0';
	} else if(jQuery("#choicerouteclass").val()=='2'){
		send_condition_t = '1';
	} else {
		send_condition_t = '0';
	};
	var data = {
		"routeChart.route_id":jQuery("#choicerouteid").val(),
		"routeChart.VIN":selectVIN,
		"routeChart.send_condition":send_condition_t,
		"routeChart.send_time":"07:00",
		"routeChart.send_order":"last",
		"routeChart.start_time":getnowtime(),
		"routeChart.end_time":getnowtime(),
		"routeChart.exe_date":jQuery("#queryTime").val(),
		"routeChart.add_flag":1
	};
	jQuery.post("../dispatchroute_chart/route_add_line.shtml",data,function(v){
		if(v.indexOf("su")>=0) {
			if(parseInt(v.substring(2,v.legnth))%3 == 0) {
				jQuery("#routempageno").val(Math.floor(parseInt(v.substring(2,v.legnth))/3));
			}else {
				jQuery("#routempageno").val(Math.floor(parseInt(v.substring(2,v.legnth))/3)+1);
			}
			makeChart();
			routempagechange(this);
        	jQuery('#routempageno').blur();
        	//searchCarRunList("1");
		} else {
			alert(v);
			searchCarRunList("1");
		}
	},"text");
}
//页面初始化调用
jQuery(function() {
	jQuery("#queryTime").val(getnowtime());
	upDateTab("6");
	jQuery("#siteStuDetail").easydrag();
	jQuery("#siteStuDetail").setHandler("DetialTitle");
	jQuery("#BgDiv").css("z-index","104");
	auto_size();
	jQuery(window).mk_autoresize({
	    height_include: '#content',
	    height_exclude: ['#header', '#footer'],
	    height_offset: 0,
	    width_include: ['#header', '#content', '#footer'],
	    width_offset: 0
	});
	//jQuery(window).resize(function(){
		Math.max.apply(null, jQuery.map(jQuery('#siteStuDetail'), function (e, n) {
			if (jQuery(e).css('position') == 'absolute')
				jQuery(e).css("left",((jQuery(document).width())/2-(parseInt(jQuery(e).width())/2))+"px").css("top",((jQuery(document).height())/2-(parseInt(jQuery(e).height())/2))+"px");
		}));
	//});
	auto_size();
	
	jQuery('#routempageno').bind('keypress',function(event){
        if(event.keyCode == "13") {
        	var nowpagelno = parseInt(jQuery("#routempageall").html().substring(1,jQuery("#routempageall").html().length-1));
        	var nowpageno = jQuery("#routempageno").val();
        	if(nowpageno>0&&nowpageno<=nowpagelno) {
        		pagenumlength = nowpageno;
        	} else {
        		jQuery("#routempageno").val(pagenumlength);
        	}
        	routempagechange(this);
        	jQuery('#routempageno').blur();
        }
    });
});
//页面刷新调用
function reflashpage() {
	if(!closebackdatainfo()) {
		if(closewindowroute>0) {
			jQuery.post("../dispatchroute_chart/route_exe_car_back.shtml?route_id="+jQuery("#routeid"+closewindowroute).val()+"&exe_date="+jQuery("#queryTime").val()+"&d="+Date.parse(new Date()),null,function(d){
			},"text");
		}
	}
}
//任何在调整切换线路或者离开当前操作线路之前 必须调用此方法
var closebackd_routen = "";
var closewindowroute="";
function closebackdatainfo() {
	if(showcarchangeinfo=='2') {
		closebackd_routen = jQuery("#rm_route #mk_routemonitor_route_up .mk-rm-route-til .mk-rm-route-til-css").html();
		var titlen = "您是否放弃对线路 "+closebackd_routen.substring(0,closebackd_routen.indexOf("<BR>"))+" 中的操作！";
		closewindowroute = 1;
		if(confirm(titlen)){
			showcarchangeinfo = '1';
		} else 
			return false;
	}
	if(showcarchangeinfo2=='2') {
		closebackd_routen = jQuery("#rm_route2 #mk_routemonitor_route_up2 .mk-rm-route-til .mk-rm-route-til-css").html();
		var titlen = "您是否放弃对线路 "+closebackd_routen.substring(0,closebackd_routen.indexOf("<BR>"))+" 中的操作！";
		closewindowroute = 2;
		if(confirm(titlen)){
			showcarchangeinfo2 = '1';
		} else 
			return false;
	}
	if(showcarchangeinfo3=='2') {
		closebackd_routen = jQuery("#rm_route2 #mk_routemonitor_route_up2 .mk-rm-route-til .mk-rm-route-til-css").html();
		var titlen = "您是否放弃对线路 "+closebackd_routen.substring(0,closebackd_routen.indexOf("<BR>"))+" 中的操作！";
		closewindowroute = 3;
		if(confirm(titlen)){
			showcarchangeinfo3 = '1';
		} else 
			return false;
	}
	if(closewindowroute>0) {
		if(jQuery("#routeid"+closewindowroute).val().length>0) {
		jQuery.post("../dispatchroute_chart/route_exe_car_back.shtml?route_id="+jQuery("#routeid"+closewindowroute).val()+"&exe_date="+jQuery("#queryTime").val()+"&d="+Date.parse(new Date()),null,function(d){
		},"text");
		}
	};
	return true;
}
window.onbeforeunload = function() {
	var n = window.event.screenX - window.screenLeft;
	var b = n > document.documentElement.scrollWidth - 20;
	if ((b && window.event.clientY < 0) || window.event.altKey || (window.event.keyCode == 8)) {
		closebackdatainfo();
	}
}
//下发完之后返回
function backpagebutton(v) {
	showcarchangeinfo = '1';
	showcarchangeinfo2 = '1';
	showcarchangeinfo3 = '1';
	if(v==1){
		jQuery("#buttonchangecar").html("调&nbsp;整");
		jQuery(".changecarinfobutton").hide();
		jQuery("#showaddbutton_check_1").hide();
	} else if(v==2) {
		jQuery("#buttonchangecar2").html("调&nbsp;整");
		jQuery(".changecarinfobutton2").hide();
		jQuery("#showaddbutton_check_2").hide();
	} else if(v==3) {
		jQuery("#buttonchangecar3").html("调&nbsp;整");
		jQuery(".changecarinfobutton3").hide();
		jQuery("#showaddbutton_check_3").hide();
	}
	searchCarRunList("1");
}
function searchDriver(){
	art.dialog.open("<s:url value='/infomanage/chooseDriver_car.shtml' />",{
		title:"驾驶员信息",
		lock:true,
		width:460,
		height:435
	});
}
var getRoute_name_back_d = "";
function getbackd_route_n(v) {
	if(v=='1') {
		getRoute_name_back_d = jQuery("#rm_route #mk_routemonitor_route_up .mk-rm-route-til .mk-rm-route-til-css").html();
		return "您是否放弃对线路 "+getRoute_name_back_d.substring(0,getRoute_name_back_d.indexOf("<BR>"))+" 中的操作！";
	}
	if(v=='2') {
		getRoute_name_back_d = jQuery("#rm_route2 #mk_routemonitor_route_up2 .mk-rm-route-til .mk-rm-route-til-css").html();
		return "您是否放弃对线路 "+getRoute_name_back_d.substring(0,getRoute_name_back_d.indexOf("<BR>"))+" 中的操作！";
	}
	if(v=='3') {
		getRoute_name_back_d = jQuery("#rm_route3 #mk_routemonitor_route_up3 .mk-rm-route-til .mk-rm-route-til-css").html();
		return "您是否放弃对线路 "+getRoute_name_back_d.substring(0,getRoute_name_back_d.indexOf("<BR>"))+" 中的操作！";
	}
}
</script>