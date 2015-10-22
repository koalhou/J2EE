<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/DispatchrouteChartDWR.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>

<script type="text/javascript">
//定时器id
var timerID = "";
//车辆定时器id 暂时不用
var timerID_car = "";
//站点动态滚动条定位
var scrollLeft1;
var scrollLeft2;
var scrollLeft3;
//车辆动态滚动条定位
var scrollcarLeft1;
var scrollcarLeft2;
var scrollcarLeft3;
//自适应
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
		height_exclude : ['.rmTitle','.rmRoutePlan','.rmForm'],
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
		width_offset : 85
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
		width_include : ['#tonqincar','#tonqincar2','#tonqincar3','#mk_routemonitor_route_up','#mk_routemonitor_route_up2','#mk_routemonitor_route_up3'],
		width_offset : 120
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
//左侧树，线路点击事件
function onClickRoute(route_id){
	if(!closebackdatainfo())
		return false;
	
	showcarchangeinfo = '1';
	showcarchangeinfo2 = '1';
	showcarchangeinfo3 = '1';
	if(chart_route_id != route_id){
		oprationType = "SR";
		chart_route_id = route_id;
		selectVIN = "";
		select_site = "";
		
		pagenumlength = 1;
		jQuery("#routempageno").val("1");
		//填写线路名称和线路负责人
		//updateRouteTitle();
		makeChart();
		//updateVehSelect();
		if(typeof(document.getElementById("infobarDivNO")) != "undefined"){
			document.getElementById("infobarDivNO").style.display = "none";
		}
		closeCarPOP();
	}
}
//左侧树勾选线路调用
function onClickRoute_check(list, route_id){
	if(!closebackdatainfo())
		return false;
	if(list.length <3)
		jQuery("#routempageslipe").hide();
	else
		jQuery("#routempageslipe").show();
	for ( var i = 0; i < list.length; i++) {
		if(i == list.length-1){
			route_id += list[i];
        }
    	else{
    		route_id +=list[i]+",";
       	}	
	}
	
	showcarchangeinfo = '1';
	showcarchangeinfo2 = '1';
	showcarchangeinfo3 = '1';
	
	//站点车辆 过多时 滚动条置左
	scrollLeft1 = 0;
	scrollLeft2 = 0;
	scrollLeft3 = 0;
	scrollcarLeft1 = 0;
	scrollcarLeft2 = 0;
	scrollcarLeft3 = 0;

	if(chart_route_id != route_id){
		oprationType = "SR";
		chart_route_id = route_id;
		selectVIN = "";
		select_site = "";
		
		pagenumlength = 1;
		jQuery("#routempageno").val("1");
		//填写线路名称和线路负责人
		//updateRouteTitle();
		makeChart();
		//updateVehSelect();
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
//时间日期控件调用	开始
function previousWeek(){
	if(!closebackdatainfo())
		return false;
	upDateTab("0");
	searchCarRunList("0");
}

function nextWeek(){
	if(!closebackdatainfo())
		return false;
	upDateTab("7");
	searchCarRunList("7");
}
function changeChoose(id){
	if(!closebackdatainfo())
		return false;
	if(jQuery("#buttonchangecar").length>0) {
		showcarchangeinfo = '1';
		jQuery("#buttonchangecar").html("调&nbsp;整");
	}
	if(jQuery("#buttonchangecar2").length>0) {
		showcarchangeinfo2 = '1';
		jQuery("#buttonchangecar2").html("调&nbsp;整");
	}
	if(jQuery("#buttonchangecar3").length>0) {
		showcarchangeinfo3 = '1';
		jQuery("#buttonchangecar3").html("调&nbsp;整");
	}
	
	//var myDate = new Date();
	//var aa = jQuery("#li"+id).children("span");
	
	var selfday = id - getTabSatus();
	
	var queryDate = new Date(document.getElementById('queryTime').value.replace(/-/g,"/"));
	var tempDate = queryDate;
	tempDate.setTime(tempDate.getTime() + 1000*60*60*24*selfday);
	var str = tempDate.pattern("yyyy-MM-dd");
	jQuery("#queryTime").val(str);
	//jQuery("#queryTime").val(myDate.getFullYear()+"-"+jQuery(aa[1]).html());
	upTabSatus(id);
	searchCarRunList("1");
}
//选择日期后，点击查询按钮
function queryWeekList(){
	//查询按钮处默认选中最后一个页签
	upDateTab("6");
	searchCarRunList("1");
}
//月 日 单数补0
function add0todate(m,d) {
	var month = parseInt(m)<=9?"0"+(parseInt(m)):parseInt(m);
	var day = parseInt(d)<=9?"0"+parseInt(d):parseInt(d);
	return month+"-"+day;
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
		//upTabSatus("0");
	}else{
		upTabSatus(getTabSatus());
	}
}
var finalTime;
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
//时间日期控件调用	结束
//查询 线路下车辆线路信息
function searchCarRunList(go) {
	//新选时间与原时间天数差
	var selfday = "";
	
	var starttimes = null;
	//new Date时间创建错误	下边判断不影响	暂时不修改
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
    
    var now = new Date();
    var lktime = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    var lktimes = lktime.getTime();
	//判断不能大于30天
	var maxtime = new Date(now.getFullYear(), now.getMonth(), now.getDate()+30);
	/* if(starttimes>maxtime.getTime()) {
		alert("查询时间不能大于30天！");
		return false;
	} */
    if(starttimes==lktimes) {
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
    }
    
	for(var i=0;i<7;i++){
		if(document.getElementById('li'+i).className=="today"){
			jQuery("#queryTime").val(selfday);
			
			//var myDate = new Date();
			//var aa = jQuery("#li"+i).children("span");
			//jQuery("#queryTime").val(myDate.getFullYear()+"-"+jQuery(aa[1]).html());
		}
	}
	DispatchrouteChartDWR.getChart(chart_route_id,treeType, pagenumlength,selectVIN, jQuery('#user_org_id').val(), backFun_getChartInfo);
	//DispatchrouteChartDWR.getChartCarList(chart_route_id,treeType, pagenumlength,jQuery("#queryTime").val(), jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo);
}
var chang_list = null;
//下发 撤销删除 使用 type表示下发撤销状态
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
		if(len>9) {
			chang_list.width((parseInt(len)-2) * 105 + 120);//126/2
			
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
//线路栏	调整状态	1是调整关闭	2是调整打开
var showcarchangeinfo = '1';
var showcarchangeinfo2 = '1';
var showcarchangeinfo3 = '1';
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
			jQuery(obj).html("调&nbsp;整");
			jQuery.post("../dispatchroute_chart/route_exe_car_back.shtml?route_id="+jQuery("#routeid1").val()+"&exe_date="+jQuery("#queryTime").val(),null,function(d){
				 if(d=='success') {
					showcarchangeinfo = '1';
					changgecarlist_('1','1');
					jQuery(".changecarinfobutton").hide();
					jQuery("#showaddbutton_check_1").hide();
					
					DispatchrouteChartDWR.getoneChart(jQuery("#routeid1").val(),treeType,jQuery("#queryTime").val(), pagenumlength,jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo1);
					//searchCarRunList("1");
				 } else {
					alert("数据库连接错误!");
				} 
			},"text");
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
			jQuery(".changecarinfobutton2").show();
			jQuery("#showaddbutton_check_2").show();
			showcarchangeinfo2 = '2';
		} else if(showcarchangeinfo2 == '2') {
			jQuery(obj).html("调&nbsp;整");
			jQuery.post("../dispatchroute_chart/route_exe_car_back.shtml?route_id="+jQuery("#routeid2").val()+"&exe_date="+jQuery("#queryTime").val(),null,function(d){
				 if(d=='success') {
					showcarchangeinfo2 = '1';
					changgecarlist_('1','2');
					jQuery(".changecarinfobutton2").hide();
					jQuery("#showaddbutton_check_2").hide();
					
					DispatchrouteChartDWR.getoneChart(jQuery("#routeid2").val(),treeType,jQuery("#queryTime").val(), pagenumlength,jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo2);
					//searchCarRunList("1");
				 } else {
					alert("数据库连接错误!");
				} 
			},"text");
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
			jQuery(obj).html("调&nbsp;整");
			jQuery.post("../dispatchroute_chart/route_exe_car_back.shtml?route_id="+jQuery("#routeid3").val()+"&exe_date="+jQuery("#queryTime").val(),null,function(d){
				 if(d=='success') { 
					showcarchangeinfo3 = '1';
					changgecarlist_('1','3');
					jQuery(".changecarinfobutton3").hide();
					jQuery("#showaddbutton_check_3").hide();
					
					DispatchrouteChartDWR.getoneChart(jQuery("#routeid3").val(),treeType,jQuery("#queryTime").val(), pagenumlength,jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo3);
					//searchCarRunList("1");
				 } else {
					alert("数据库连接错误!");
				} 
			},"text");
		}
	}
	//searchCarRunList("1");
}
//当点击其它调整时 
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
	},"text");
}
/**
* 线路图控制
*/	
var chart_route_id = "";
var selectVIN = "";
var select_site = "";
var oprationType = "INIT";

//如果要分页这里定义页数属性
var pagenumlength = 1;
//画线路图、更新站点进出站信息
function makeChart(){
	DispatchrouteChartDWR.getChart(chart_route_id,treeType, pagenumlength,selectVIN, jQuery('#user_org_id').val(), backFun_getChartInfo);
	//DispatchrouteChartDWR.getChartCarList(chart_route_id,treeType, pagenumlength,jQuery("#queryTime").val(), jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo);
	//DispatchrouteChartDWR.getLineSize(chart_route_id,treeType, selectVIN, jQuery('#user_org_id').val(), writepageallsize);
	writepageallsize(chart_route_id.split(',').length);
}
function changetypen(v) {
	return v=='1'?"调&nbsp;整":v=='2'?"撤销调整":"";
}
//线路图回调函数
var divcharttitle = "";
var divcharttitle2 = "";
var divcharttitle3 = "";
var treeObj = null;
var treenodes = null;
//线路查询返回结果方法
function backFun_getChartInfo(resultList){
	jQuery("#rm_route").hide();
	jQuery("#rm_route2").hide();
	jQuery("#rm_route3").hide();
	select_site_id_list = "";
	
	if(resultList.length==1) {
		divcharttitle = '<div class="mk-rm-route-til-css">'+encodeHtml(resultList[0][0].route_name)+"</div>";
		jQuery("#mk_routemonitor_route_up .mk-rm-route-til").html("<B>"+divcharttitle+"</B>");
		jQuery("#mk_routemonitor_route_up2 .mk-rm-route-til").html("");
		jQuery("#mk_routemonitor_route_up3 .mk-rm-route-til").html("");
		jQuery("#rm_route").show();
		jQuery("#routeid1").val(resultList[0][0].route_id);//根据站点添加线路id
		jQuery("#rm_route").show();
		
		backFun_getChartInfo_info(resultList[0],0);
		clear_rm(2);
		clear_rm(3);
		
		//站点刷新的同时刷新车辆信息
		DispatchrouteChartDWR.getoneChart(resultList[0][0].route_id,treeType,jQuery("#queryTime").val(), pagenumlength,jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo1);
	} else if(resultList.length==2){
		divcharttitle = '<div class="mk-rm-route-til-css">'+encodeHtml(resultList[0][0].route_name)+"</div>";
		divcharttitle2 = '<div class="mk-rm-route-til-css">'+encodeHtml(resultList[1][0].route_name)+"</div>";
		jQuery("#mk_routemonitor_route_up .mk-rm-route-til").html("<B>"+divcharttitle+"</B>");
		jQuery("#mk_routemonitor_route_up2 .mk-rm-route-til").html("<B>"+divcharttitle2+"</B>");
		jQuery("#mk_routemonitor_route_up3 .mk-rm-route-til").html("");
		
		jQuery("#routeid1").val(resultList[0][0].route_id);
		jQuery("#routeid2").val(resultList[1][0].route_id);
		jQuery("#rm_route").show();
		jQuery("#rm_route2").show();
		
		backFun_getChartInfo_info(resultList[0],0);
		backFun_getChartInfo_info(resultList[1],1);
		clear_rm(3);
		
		//站点刷新的同时刷新车辆信息
		DispatchrouteChartDWR.getoneChart(resultList[0][0].route_id,treeType,jQuery("#queryTime").val(), pagenumlength,jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo1);
		DispatchrouteChartDWR.getoneChart(resultList[1][0].route_id,treeType,jQuery("#queryTime").val(), pagenumlength,jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo2);
	} else if(resultList.length==3){
		divcharttitle = '<div class="mk-rm-route-til-css">'+encodeHtml(resultList[0][0].route_name)+"</div>";
		divcharttitle2 = '<div class="mk-rm-route-til-css">'+encodeHtml(resultList[1][0].route_name)+"</div>";
		divcharttitle3 = '<div class="mk-rm-route-til-css">'+encodeHtml(resultList[2][0].route_name)+"</div>";
		
		jQuery("#mk_routemonitor_route_up .mk-rm-route-til").html("<B>"+divcharttitle+"</B>");
		jQuery("#mk_routemonitor_route_up2 .mk-rm-route-til").html("<B>"+divcharttitle2+"</B>");
		jQuery("#mk_routemonitor_route_up3 .mk-rm-route-til").html("<B>"+divcharttitle3+"</B>");
						
		jQuery("#routeid1").val(resultList[0][0].route_id);
		jQuery("#routeid2").val(resultList[1][0].route_id);
		jQuery("#routeid3").val(resultList[2][0].route_id);
		jQuery("#rm_route").show();
		jQuery("#rm_route2").show();
		jQuery("#rm_route3").show();
		
		backFun_getChartInfo_info(resultList[0],0);
		backFun_getChartInfo_info(resultList[1],1);
		backFun_getChartInfo_info(resultList[2],2);
		
		//站点刷新的同时刷新车辆信息
		DispatchrouteChartDWR.getoneChart(resultList[0][0].route_id,treeType,jQuery("#queryTime").val(), pagenumlength,jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo1);
		DispatchrouteChartDWR.getoneChart(resultList[1][0].route_id,treeType,jQuery("#queryTime").val(), pagenumlength,jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo2);
		DispatchrouteChartDWR.getoneChart(resultList[2][0].route_id,treeType,jQuery("#queryTime").val(), pagenumlength,jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo3);
	} else {
		jQuery("#mk_routemonitor_route_up .mk-rm-route-til").html("");
		jQuery("#mk_routemonitor_route_up2 .mk-rm-route-til").html("");
		jQuery("#mk_routemonitor_route_up3 .mk-rm-route-til").html("");
		clear_rm(1);
		clear_rm(2);
		clear_rm(3);
	}
}
//车辆信息调用返回方法
function backFun_getChartInfo_charinfo1(resultList) {
	//if(null!=resultList && resultList.length>0){
		data_list_car = resultList;
		reloadlistcar(data_list_car, 1);
		changgecarlist_(showcarchangeinfo,1);
	//}
}
function backFun_getChartInfo_charinfo2(resultList) {
	//if(null!=resultList && resultList.length>0){
		data_list_car2 = resultList;
		reloadlistcar(data_list_car2, 2);
		changgecarlist_(showcarchangeinfo2,2);
	//}
}
function backFun_getChartInfo_charinfo3(resultList) {
	//if(null!=resultList && resultList.length>0){
		data_list_car3 = resultList;
		reloadlistcar(data_list_car3, 3);
		changgecarlist_(showcarchangeinfo3,3);
	//}
}
var data_list_car = [];
var data_list_car2 = [];
var data_list_car3 = [];
//车辆全部刷新暂时不使用	新方法车辆单独刷新
function backFun_getChartInfo_charinfo(resultList) {
	data_list_car = [];
	data_list_car2 = [];
	data_list_car3 = [];
	//var v1,v2,v3;
	//先循环线路,线路循环完毕循环车辆	共3条线路	这里也是分页
	if(null!=resultList && resultList.length>0){
		if(resultList.length==1) {
			data_list_car = resultList[0];
			reloadlistcar(data_list_car, 1);

			changgecarlist_(showcarchangeinfo,1);
		} else if(resultList.length==2){
			data_list_car = resultList[0];
			reloadlistcar(data_list_car, 1);
			data_list_car2 = resultList[1];
			reloadlistcar(data_list_car2, 2);
			
			changgecarlist_(showcarchangeinfo,1);
			changgecarlist_(showcarchangeinfo2,2);
		} else if(resultList.length==3){
			data_list_car = resultList[0];
			reloadlistcar(data_list_car, 1);
			data_list_car2 = resultList[1];
			reloadlistcar(data_list_car2, 2);
			data_list_car3 = resultList[2];
			reloadlistcar(data_list_car3, 3);
			
			changgecarlist_(showcarchangeinfo,1);
			changgecarlist_(showcarchangeinfo2,2);
			changgecarlist_(showcarchangeinfo3,3);
		}
	}
	//refreshChart();
}
var setsitewidth = 0;
//线路下发出安排车辆查询返回结果方法
function backFun_getChartInfo_info(resultList,v){
	//resultList v=1   resultList	写入测试数据
	setsitewidth = 0;
	//把所有在运行车辆制灰
	$("div .tableroute_car_info_blue").each(function() {
		$(this).removeClass().addClass("tableroute_car_info_grey");
	});
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
						//sitebrwose(resultList[i].site_id,resultList[i].site_name,temp);
					}else{
						//refreshSite();
					}
				}
				
				setsitewidth +=1;
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
		show_rm(route_data,v);
		if(setsitewidth > 5) {
			var dd = v=='0'?'':parseInt(v)+1;
			jQuery("#rm_route"+dd+" .mk-rm-route .mk-rm-route-station ul").width(145*setsitewidth+50);
		}
		
		if(timerID == ""){
			openTime();
		}
	}else{
		cancelTime();
		select_site = "";
		clear_rm(v);
	}
	
	if(v==0) {
		if(scrollLeft1!=null)
			document.getElementById("mk_up_station").scrollLeft=scrollLeft1;
	} else if(v==1) {
		if(scrollLeft2!=null)
			document.getElementById("mk_up_station2").scrollLeft=scrollLeft2;
	} else if(v==2) {
		if(scrollLeft3!=null)
			document.getElementById("mk_up_station3").scrollLeft=scrollLeft3;
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
		DispatchrouteChartDWR.getIOinfosBySite(chart_route_id, select_site, selectVIN, jQuery('#user_org_id').val(), backFun_getIOinfosBySite);
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

/*
 * 页面定时刷新部分
 */
//取消定时器
function cancelTime(){
	window.clearTimeout(timerID);
	window.clearTimeout(timerID_car);
	timerID = "";
	timerID_car = "";
}
function openTime(){
	cancelTime();
	timerID = setInterval("refreshChart()",15000); //线程暂时关闭
	
	//timerID_car = setInterval("refreshChart_car()",15000);//车辆全部刷新暂时不使用
}
function refreshChart(){
	if(jQuery("#mk_up_station").length>0)
		scrollLeft1 = document.getElementById('mk_up_station').scrollLeft;
	if(jQuery("#mk_up_station2").length>0)
		scrollLeft2 = document.getElementById('mk_up_station2').scrollLeft;
	if(jQuery("#mk_up_station3").length>0)
		scrollLeft3 = document.getElementById('mk_up_station3').scrollLeft;
	
	if(jQuery("#tonqincar #tonqincar_list").length>0) 
		scrollcarLeft1 = jQuery("#tonqincar #tonqincar_list").attr("scrollLeft");
	if(jQuery("#tonqincar2 #tonqincar_list").length>0)
		scrollcarLeft2 = jQuery("#tonqincar2 #tonqincar_list").attr("scrollLeft");
	if(jQuery("#tonqincar3 #tonqincar_list").length>0)
		scrollcarLeft3 = jQuery("#tonqincar3 #tonqincar_list").attr("scrollLeft");
	
	if(!isNull(chart_route_id)){
		oprationType = "RE";
		DispatchrouteChartDWR.getChart(chart_route_id,treeType, pagenumlength,selectVIN, jQuery('#user_org_id').val(), backFun_getChartInfo);
	}
}
/*更改站点车辆刷新 本方法暂时不使用
function refreshChart_car(){
	if(jQuery("#tonqincar #tonqincar_list").length>0) {
		scrollcarLeft1 = jQuery("#tonqincar #tonqincar_list").attr("scrollLeft");
	}
	if(jQuery("#tonqincar2 #tonqincar_list").length>0)
		scrollcarLeft2 = jQuery("#tonqincar2 #tonqincar_list").attr("scrollLeft");
	if(jQuery("#tonqincar3 #tonqincar_list").length>0)
		scrollcarLeft3 = jQuery("#tonqincar3 #tonqincar_list").attr("scrollLeft");
	if(!isNull(chart_route_id)){
		oprationType = "RE";
		DispatchrouteChartDWR.getChartCarList(chart_route_id,treeType, pagenumlength,jQuery("#queryTime").val(), jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo);
	}
}
*/
function getnowtime(){
	/* var myDate = new Date();
	var month = parseInt(myDate.getMonth())<=8?"0"+(parseInt(myDate.getMonth())+1):parseInt(myDate.getMonth())+1;
	var day = parseInt(myDate.getDate())<=9?"0"+parseInt(myDate.getDate()):parseInt(myDate.getDate());
	return myDate.getFullYear()+"-"+month+"-"+day; */
	return "<s:property value='sysdate' />";
}
function getsettime(){
	var myDate = new Date();
	for(var i=0;i<7;i++){
		if(document.getElementById('li'+i).className=="today"){
			return myDate.getFullYear()+"-"+jQuery(jQuery("#li"+i).children("span")[1]).html();
		}
	}
	return "";
}
function writepageallsize(res) {
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
	}else {
		jQuery("#routempageno").val(pagenumlength);
	}
}
function routempagec(obj) {
	if(!closebackdatainfo())
		return false;
	showcarchangeinfo = '1';
	showcarchangeinfo2 = '1';
	showcarchangeinfo3 = '1';
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
		}
	}else if(jQuery(obj).attr("id") == 'routempagenext') {
		if(nowpageno!=nowpagelno) {
			pagenumlength++;
			makeChart();
			jQuery("#routempageno").val(pagenumlength);
		}else {
			jQuery("#routempageno").val(pagenumlength);
		}
	}else if(jQuery(obj).attr("id") == 'routempagelast') {
		if(nowpageno!=nowpagelno) {
			pagenumlength = nowpagelno;
			makeChart();
			jQuery("#routempageno").val(pagenumlength);
		};
	};
}
function makeWeekList(ii) {
	var route_id_ = null;
	var value = ii;//jQuery('input:radio[name="routecarcopy"]:checked').val();
	if(value == 1) {
		route_id_ = jQuery("#routeid1").val();
	}else if(value == 2) {
		route_id_ = jQuery("#routeid2").val();
	}else if(value == 3) {
		route_id_ = jQuery("#routeid3").val();
	}
	var data = {
		"routeInfo.route_id":route_id_,
		"routeInfo.exe_date":jQuery("#queryTime").val()
	};
	jQuery.post("../dispatchroute_chart/makedispatchdata.shtml",data,function(v){
		searchCarRunList("1");
	},"text");
}
function showcarchangeback() {
	if(showcarchangeinfo=='2') {
		showcarchangeinfo = '1';
		jQuery("#buttonchangecar").html("调&nbsp;整");
	}
	if(showcarchangeinfo2=='2') {
		showcarchangeinfo2 = '1';
		jQuery("#buttonchangecar2").html("调&nbsp;整");
	}
	if(showcarchangeinfo3=='2') {
		showcarchangeinfo3 = '1';
		jQuery("#buttonchangecar3").html("调&nbsp;整");
	}
}
function tabSwitch(id){
	if(!closebackdatainfo())
		return false;
	showcarchangeback();
	
	if(iftruereturn==0) {
		return false;
	}else{
		iftruereturn = 0;
	}
	
	if(id=="treeupid"){
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
		chart_route_id = "";
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}else if(id=="treedownid"){
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
		chart_route_id = "";
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}else if(id=="treetqcid"){
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
		chart_route_id = "";
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}
}
//下发行程方法
function sendcommondbt(ii) {
	var starttimes = getnowandsearcht("search");
	var lktimes = getnowandsearcht("now");
	
	//(data_list_car[v].crc==null||data_list_car[v].crc.length<1) 表示 要修改的行程
	//(data_list_car[v].valid_flag!=null&&data_list_car[v].valid_flag=='1') 表示 要删除的行程
	//(data_list_car[v].send_condition=='1') 表示 发车条件是循环发车的行程
	//(data_list_car[v].crc!=null&&(data_list_car[v].mesg_flag==null||data_list_car[v].mesg_flag=='0')) 表示下发失败重新下发mesg_flag是update_time的判断
	//满足其中任意一条就添加下发
	//如果是今天就下发到终端
	//如果不是只更新临时存储行程表中
	var newstr = "";
	var tripidList = "";
	var validfList = "";
	if(ii==null||ii=='') {
		alert("请选择下发线路");
		return false;
	}
	if(ii==1) {
		if(data_list_car==null||data_list_car.length<1) {
			backpagebutton(ii);
			return false;
		}
		for(var v=0;v<data_list_car.length;v++) {
			//if((data_list_car[v].crc==null||data_list_car[v].crc.length<1)||(data_list_car[v].valid_flag!=null&&data_list_car[v].valid_flag=='1')||(data_list_car[v].send_condition=='1')||
					//(data_list_car[v].crc!=null&&(data_list_car[v].mesg_flag==null||data_list_car[v].mesg_flag=='0'))) {
				newstr += data_list_car[v].VIN+",";
				tripidList += data_list_car[v].trip_id+",";
				validfList += data_list_car[v].valid_flag+",";
			//}
		};
	} else if(ii==2) {
		if(data_list_car2==null||data_list_car2.length<1) {
			backpagebutton(ii);
			return false;
		}
		for(var v=0;v<data_list_car2.length;v++) {
			//if((data_list_car2[v].crc==null||data_list_car2[v].crc.length<1)||(data_list_car2[v].valid_flag!=null&&data_list_car2[v].valid_flag=='1')||(data_list_car2[v].send_condition=='1')||
					//(data_list_car2[v].crc!=null&&(data_list_car2[v].mesg_flag==null||data_list_car2[v].mesg_flag=='0'))) {
				newstr += data_list_car2[v].VIN+",";
				tripidList += data_list_car2[v].trip_id+",";
				validfList += data_list_car2[v].valid_flag+",";
			//}
		};
	} else if(ii==3) {
		if(data_list_car3==null||data_list_car3.length<1) {
			backpagebutton(ii);
			return false;
		}
		for(var v=0;v<data_list_car3.length;v++) {
			//if((data_list_car3[v].crc==null||data_list_car3[v].crc.length<1)||(data_list_car3[v].valid_flag!=null&&data_list_car3[v].valid_flag=='1')||(data_list_car3[v].send_condition=='1')||
					//(data_list_car3[v].crc!=null&&(data_list_car3[v].mesg_flag==null||data_list_car3[v].mesg_flag=='0'))) {
				newstr += data_list_car3[v].VIN+",";
				tripidList += data_list_car3[v].trip_id+",";
				validfList += data_list_car3[v].valid_flag+",";
			//}
		};
	}
	jQuery.blockUI({ message: "<h1>线路下发,请稍等...</h1>"});
	var data = {exe_date:jQuery("#queryTime").val(),carsVinInfos: newstr,tripidInfos:tripidList,valid_flags:validfList};
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
	} else if(starttimes>lktimes) {
		jQuery.post("../dispatchroute_chart/route_exe_car_totemp.shtml?route_id="+jQuery("#routeid"+ii).val()+"&exe_date="+jQuery("#queryTime").val(),null,function(v){
			if(v=='success') {
				backpagebutton(ii);
				jQuery.unblockUI();
			}else {
				backpagebutton(ii);
				jQuery.unblockUI();
			}
		},"text");
	};
}
//多台车辆在线发出安排测试方法
function testaaa() {
	oprationType = "RE";
	/* this.id = site_id;
	this.name = site_name;
	this.status = site_status;
	this.up = up_num;
	this.down = down_num; */
	/* this.vin = vin;
	this.site_id = site_id;
	this.vehicle_ln = ln;
	this.inout_flag = inout_flag; */
	var dd = new Array();
	//function aa0(){
		//this.re_flag = "1";this.vehicle_ln ="测试000"; this.vin = "000000";
		//this.site_id="3489";this.inout_flag="0";this.site_updown = "0";};
		
	var aa_0 = {"re_flag":"0","site_name":"航海路朝凤路口","site_updown":"0","site_id":"3489","stu_error":"0","time_error":"0","downNum":"0","upNum":"0"};
	var aa_1 = {"re_flag":"0","site_name":"七里河","site_updown":"0","site_id":"3490","stu_error":"0","time_error":"0","downNum":"0","upNum":"0"};
	var aa_2 = {"re_flag":"0","site_name":"橡树玫瑰城","site_updown":"0","site_id":"3491","stu_error":"0","time_error":"0","downNum":"0","upNum":"0"};
	var aa_aaa = {"re_flag":"0","site_name":"aaaa","site_updown":"0","site_id":"3492","stu_error":"0","time_error":"0","downNum":"0","upNum":"0"};
	var aa_bbb = {"re_flag":"0","site_name":"cccc","site_updown":"0","site_id":"3493","stu_error":"0","time_error":"0","downNum":"0","upNum":"0"};
	var aa_ccc = {"re_flag":"0","site_name":"bbbb","site_updown":"0","site_id":"3494","stu_error":"0","time_error":"0","downNum":"0","upNum":"0"};
	var aa_ddd = {"re_flag":"0","site_name":"dddd","site_updown":"0","site_id":"3495","stu_error":"0","time_error":"0","downNum":"0","upNum":"0"};
		
	var aa0 = {"re_flag":"1","vehicle_ln":"测试000","vin":"000000","site_id":"3489","inout_flag":"0","site_updown":"0"};
	var aa1 = {"re_flag":"1","vehicle_ln":"测试111","vin":"111111","site_id":"3489","inout_flag":"0","site_updown":"0"};
	var aa2 = {"re_flag":"1","vehicle_ln":"测试222","vin":"222222","site_id":"3489","inout_flag":"0","site_updown":"0"};
	var aa3 = {"re_flag":"1","vehicle_ln":"测试333","vin":"333333","site_id":"3489","inout_flag":"0","site_updown":"0"};
	var aa4 = {"re_flag":"1","vehicle_ln":"测试444","vin":"444444","site_id":"3489","inout_flag":"0","site_updown":"0"};
	var aa5 = {"re_flag":"1","vehicle_ln":"测试555","vin":"555555","site_id":"3489","inout_flag":"0","site_updown":"0"};
	dd.push(aa_0);
	dd.push(aa_1);
	dd.push(aa_2);
	
	dd.push(aa_aaa);
	dd.push(aa_bbb);
	dd.push(aa_ccc);
	dd.push(aa_ddd);
	/*  dd.push(aa0);
	dd.push(aa1);
	dd.push(aa2);
	dd.push(aa3);
	dd.push(aa4);
	dd.push(aa5);*/
	
	var bb0000 = {"re_flag":"1","vehicle_ln":"0000000","vin":"发车0001","site_id":"3490","inout_flag":"0","site_updown":"0"};
	var bb1111 = {"re_flag":"1","vehicle_ln":"1111111","vin":"发车0002","site_id":"3490","inout_flag":"10","site_updown":"0"};
	var bb2222 = {"re_flag":"1","vehicle_ln":"22222222","vin":"发车0003","site_id":"3493","inout_flag":"10","site_updown":"0"};
	var bb3333 = {"re_flag":"1","vehicle_ln":"33333333","vin":"发车0004","site_id":"3493","inout_flag":"30","site_updown":"0"};
	//测试如果有车发车了
	var bb0 = {"re_flag":"1","vehicle_ln":"测试发车01","vin":"发车0001","site_id":"3492","inout_flag":"10","site_updown":"0"};
	var bb1 = {"re_flag":"1","vehicle_ln":"测试发车02","vin":"发车0001","site_id":"3492","inout_flag":"20","site_updown":"0"};
	var bb2 = {"re_flag":"1","vehicle_ln":"测试发车03","vin":"发车0001","site_id":"3492","inout_flag":"30","site_updown":"0"};
	dd.push(bb0);
	dd.push(bb1);
	dd.push(bb2);
	
	dd.push(bb0000);
	dd.push(bb1111);
	dd.push(bb2222);
	dd.push(bb3333);
	backFun_getChartInfo_info(dd,0);
}
function turntosetvehicleorder() {
	if(!closebackdatainfo())
		return false;
	window.location.href = "../routechart/readyrc.shtml";
}
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
	
	Math.max.apply(null, jQuery.map(jQuery('#siteStuDetail'), function (e, n) {
		if (jQuery(e).css('position') == 'absolute')
			jQuery(e).css("left",((jQuery(document).width())/2-(parseInt(jQuery(e).width())/2))+"px").css("top",((jQuery(document).height())/2-(parseInt(jQuery(e).height())/2))+"px");
	}));
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
//页面关闭或者更新都要将未下发的原数据返回
function reflashpage() {
	if(!closebackdatainfo2()) {
		if(closewindowroute.length>0) {
			jQuery.post("../dispatchroute_chart/route_exe_car_back.shtml?route_id="+jQuery("#routeid"+closewindowroute).val(),null,function(d){
			},"text");
		}
	}
}
var closewindowroute="";
//任何在调整切换线路或者离开当前操作线路之前 必须调用此方法
var closebackd_routen = "";
function closebackdatainfo() {
	if(showcarchangeinfo=='2') {
		closebackd_routen = jQuery("#rm_route #mk_routemonitor_route_up .mk-rm-route-til .mk-rm-route-til-css").html();
		var titlen = "您是否放弃对线路 "+closebackd_routen+" 中的操作！";
		closewindowroute = 1;
		if(confirm(titlen)){
			showcarchangeinfo = '1';
		} else 
			return false;
	}
	if(showcarchangeinfo2=='2') {
		var titlen = "您是否放弃对线路 "+jQuery("#rm_route2 #mk_routemonitor_route_up2 .mk-rm-route-til .mk-rm-route-til-css").html()+" 中的操作！";
		closewindowroute = 2;
		if(confirm(titlen)){
			showcarchangeinfo2 = '1';
		} else 
			return false;
	}
	if(showcarchangeinfo3=='2') {
		var titlen = "您是否放弃对线路 "+jQuery("#rm_route3 #mk_routemonitor_route_up3 .mk-rm-route-til .mk-rm-route-til-css").html()+" 中的操作！";
		closewindowroute = 3;
		if(confirm(titlen)){
			showcarchangeinfo3 = '1';
		} else 
			return false;
	}
	if(closewindowroute>0) {
		jQuery.post("../dispatchroute_chart/route_exe_car_back.shtml?route_id="+jQuery("#routeid"+closewindowroute).val()+"&exe_date="+jQuery("#queryTime").val(),null,function(d){
		},"text");
	};

	return true;
}
window.onbeforeunload = function() {
	var n = window.event.screenX - window.screenLeft;
	var b = n > document.documentElement.scrollWidth - 20;
	if ((b && window.event.clientY < 0) || window.event.altKey || (window.event.keyCode == 8)) {
		if(!closebackdatainfo2())
			return false;
	}
}
//页面刷新或关闭的时候	都要执行返回数据
function closebackdatainfo2() {
	if(showcarchangeinfo=='2') {
		closebackd_routen = jQuery("#rm_route #mk_routemonitor_route_up .mk-rm-route-til .mk-rm-route-til-css").html();
		var titlen = "您是否放弃对线路 "+closebackd_routen+" 中的操作！";
		closewindowroute = 1;
		if(confirm(titlen))
			showcarchangeinfo = '1';
	}
	if(showcarchangeinfo2=='2') {
		var titlen = "您是否放弃对线路 "+jQuery("#rm_route2 #mk_routemonitor_route_up2 .mk-rm-route-til .mk-rm-route-til-css").html()+" 中的操作！";
		closewindowroute = 2;
		if(confirm(titlen))
			showcarchangeinfo2 = '1';
	}
	if(showcarchangeinfo3=='2') {
		var titlen = "您是否放弃对线路 "+jQuery("#rm_route3 #mk_routemonitor_route_up3 .mk-rm-route-til .mk-rm-route-til-css").html()+" 中的操作！";
		closewindowroute = 3;
		if(confirm(titlen))
			showcarchangeinfo3 = '1';
	}
	if(jQuery("#routeid"+closewindowroute).val().length>0) {
		jQuery.post("../dispatchroute_chart/route_exe_car_back.shtml?route_id="+jQuery("#routeid"+closewindowroute).val()+"&exe_date="+jQuery("#queryTime").val(),null,function(d){
		},"text");
	}
	return true;
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
	}else if(v=='2') {
		getRoute_name_back_d = jQuery("#rm_route2 #mk_routemonitor_route_up2 .mk-rm-route-til .mk-rm-route-til-css").html();
	}else if(v=='3') {
		getRoute_name_back_d = jQuery("#rm_route3 #mk_routemonitor_route_up3 .mk-rm-route-til .mk-rm-route-til-css").html();
	}
	return "您是否放弃对线路 "+getRoute_name_back_d+" 中的操作！";
}
</script>