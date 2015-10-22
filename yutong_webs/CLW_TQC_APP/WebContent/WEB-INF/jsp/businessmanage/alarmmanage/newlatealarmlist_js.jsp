<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
var dealflag=0;
function chuli(){
    var alarmid=jQuery("#chulialarmid").val();
    var alarmtime=jQuery("#chulialarmtime").val();
    var alarmtypeid=jQuery("#chulialarmtypeid").val();
    var lon=jQuery("#chulilon").val();
    var lat=jQuery("#chulilat").val();
    var vin=jQuery("#chulivin").val();
    
    openPopUp(vin,alarmid,alarmtime,alarmtypeid,lon,lat);
}

function qinli(){
	jQuery("#carln").html('未选车');
	jQuery("#carln").attr("title","");
	jQuery("#messagetime").html(jQuery('#year').val()+"-00-00 00:00:00");
	//jQuery("#messagetime").css("dispaly","none");
	document.getElementById("messagetime").style.display="none";
	jQuery("#alarmtypename2").val('');
	jQuery("#xianlu").val('');
	jQuery("#zhandian").val('');
	jQuery("#xingming").val('');
	jQuery("#xuexiao").val('');
	jQuery("#banji").val('');
	jQuery('#yijianneirong').html('');
	jQuery('#routeName').val('');
	jQuery('#siteName').val('');
	jQuery('#siji').val('');
	jQuery('#shijidaodashijian').val('');
	jQuery('#chidaoshijian').val('');
	jQuery('#chulirenname').html('无');
	jQuery('#chuli').css('display','none');	
	if(dealflag==1){
// 		jQuery('#piliangchuli').css('display','none');
		//jQuery('#chuliyijian').css('display','');
		//jQuery('#chuliren').css('display','');
		//jQuery('#yijianneirong').html('');
		//jQuery('#chulirenname').val('');
	}else{
// 		jQuery('#piliangchuli').css('display','');
		//jQuery('#chuliyijian').css('display','none');
		//jQuery('#yijianneirong').css('display','none');
		//jQuery('#chuliren').css('display','none');
	}
}

function changedeal(){
	var flag=jQuery("#cdeal_flag").val();
	if(flag=='0')
	{
		dealflag=flag;
		jQuery("#piliangchuli").css("display","block");
// 		jQuery("#chuli").css("display","block");
// 		jQuery('#deal_flag').val(0);
		qinli();
	}

	if(flag=='1'){
		dealflag=flag;
		jQuery("#piliangchuli").css("display","none");
// 		jQuery("#chuli").css("display","none");
// 		jQuery('#deal_flag').val(1);
		qinli();
	}
	if(flag=='2'){
		dealflag=flag;
		jQuery("#piliangchuli").css("display","none");
// 		jQuery("#chuli").css("display","none");
// 		jQuery('#deal_flag').val(2);
		qinli();
	}
	if(flag.length == '0'){
		jQuery("#piliangchuli").css("display","none");
// 		jQuery("#chuli").css("display","none");
		qinli();
	}
	jQuery('#deal_flag').val(flag);
	searchList();
}


function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

function get_cell_text2(pid, cell_idx) {
	var cellName = jQuery('#gala').flex_colModel()[cell_idx].name;
	var name;
	jQuery.each(jQuery('#row' + pid).parents("div.flexigrid").find('th'),function(i){  
	if(jQuery(this).attr("colname") == cellName) {
	name = jQuery('#row' + pid).children('td').eq(i).eq(0).text();
	return false;
	}
	});
	return name;
}

function reWritedeal_flag(tdDiv,pid,row){

	var deal_flag = row.cell[4];
   if(deal_flag ==0 ){
      return '<s:text name="vehcileinfo.alarmno" />';
   }
   else{
      return '<s:text name="vehcileinfo.alarmyes" />';
   }
}

function reWriteLink(tdDiv,pid,row){
	/*
	var longitude = get_cell_text(pid, 0);
	var latitude = get_cell_text(pid, 1);
	var vehicle_vin = get_cell_text(pid, 2);	
	var alarmtypeid=get_cell_text(pid, 3);	
	var alarmtypeflag=get_cell_text(pid, 4);
	var alarmtypename=get_cell_text(pid, 13);
	if(alarmtypeflag==1){
		tdDiv.innerHTML = '<a href="javascript:openStuAlarmPosition(\'' + longitude + '\',\''+latitude+ '\',\''+vehicle_vin + '\',\''+pid+ '\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + '处理结果' +'</a>';
	}else{
		tdDiv.innerHTML = '<a href="javascript:openStuAlarmPosition(\'' + longitude + '\',\''+latitude+ '\',\''+vehicle_vin + '\',\''+pid+ '\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + '点击处理' +'</a>';
	}*/
	return pid;
}

function allchooseFlag(allcheck){
	var arrallcheck = document.getElementsByName("checkalarm");	
	if(allcheck.checked){
		  for(var i=0;i<arrallcheck.length;i++) {
			  arrallcheck[i].checked = true;	
		  }	
		}else{
		   for(i=0;i<arrallcheck.length;i++) {
			   arrallcheck[i].checked = false;   		
		   }	
		}		   	
}

function rewritecheckbox(tdDiv,pid,row){
	  return '<input type="checkbox"  name="checkalarm" value="'+pid+'"/> '; 
}
var gala='';
jQuery(function() {
	gala = jQuery('#gala');
	gala.flexigrid({
		url: '<s:url value="/busalarmpkg/newlatetalarmlist.shtml" />',
		dataType: 'json',
		height: '2000',
		width:'800',
		colModel : [
                    {display: '经度', name : 'longitude', width : 65,align: 'left',hide:true,toggle:false},
                    {display: '纬度', name : 'latitude', width : 65, align: 'left',hide:true,toggle:false},
                    {display: '告警类型', name : 'alarm_type_id', width : 65, align: 'left',hide:true,toggle:false},
                    {display: '车辆VIN', name : 'vehicle_vin', width : 80, sortable : true, align: 'center',hide:true,toggle:false},
                    {display: 'operateflag' ,name : 'operate_flagT',width :80,hide:true,toggle:false},
		            {display: '<input type="checkbox" id="checkallalarm" name="checkallalarm" onclick="allchooseFlag(this)"/>',name : '',width : 20,sortable : false,align: 'center',process:rewritecheckbox},
		            {display: '班车号', name : 'vehicle_code', width : calcColumn(0.05,83,240), sortable : true, align: 'center',escape: true},
					{display: '车牌号', name : 'vehicle_ln', width : calcColumn(0.08,83,240), sortable : true, align: 'center',escape: true},
					{display: '告警类型', name : 'alarm_type', width : calcColumn(0.06,83,240), sortable : true, align: 'center',escape: true},
					{display: '处理状态', name : 'operate_flag', width : calcColumn(0.05,75,240), sortable : true, align: 'center',process: reWritedeal_flag},
					{display: '告警时间', name : 'alarm_date', width : calcColumn(0.1,105,240), sortable : true, align: 'center'},
					{display: '行驶线路', name : 'route_name', width : calcColumn(0.1,105,240), sortable : true, align: 'center'},
					{display: '终点站', name : 'site_name', width : calcColumn(0.1,105,240), hide: false,sortable : true, align: 'center'},
					{display: '实际到达时间', name : 'terminal_time', width : calcColumn(0.1,105,240), sortable : true, align: 'center'},
					{display: '迟到时长（分）', name : 'time_dis', width : calcColumn(0.08,105,240), sortable : false, align: 'center'},
					{display: '驾驶员', name : "NLSSORT(driver_name,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.04,105,240), sortable : true, align: 'center'},
					{display: '联系方式', name : 'driver_tel', width : calcColumn(0.04,105,240), hide: true, sortable : true, align: 'center',escape: true,toggle: false},
					{display: '处理', name : 'alarmid', width : 55, sortable :false, align: 'center',hide:true,toggle:false,process: reWriteLink},
					{display: '处理人' , name : 'user_name', width : 80, sortable :false, align: 'center',hide:true,toggle:false},
					{display: '处理意见' , name : 'opeerate_desc', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true}
					],
		    		    		
		    	sortname: 'alarm_date',
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
				onRowSelect:rowstandbyclick3,
				params: [{name: 'vehicle_ln', value: jQuery('#vehicle_ln').val() }, 
				 		{ name: 'alarm_type_id', value: jQuery('#alarm_type_id').val()},
				 		{ name: 'deal_flag', value: jQuery('#deal_flag').val()},
				 		{ name: 'chooseorgid', value: jQuery('#chooseorgid').val()},
				 		{ name: 'start_time', value: jQuery('#start_time').val()},
				 		{ name: 'end_time', value: jQuery('#end_time').val()},
				 		{ name: 'vehicleCode', value: jQuery("#vehicleCode").val()}],
			   onSuccess:function(){
	             jQuery("input[name='checkallalarm']").removeAttr("checked");
	             jQuery.unblockUI();  
                }
                /*
			   onSubmit:function(){
                       // jQuery("input[name='checkalarm']").each(function(){
                       // jQuery("input[name='checkalarm']").removeAttr("checked"); 
                       // });
                        jQuery("input[name='checkallalarm']").removeAttr("checked");
                        return true;
                        }	*/ 	    	
		    	//getQuery :getQuery
	});
});
	
	function rowstandbyclick3(rowData){
		//显示字段赋值
		var dealflag=jQuery('#deal_flag').val();
		var flag = jQuery(rowData).data("operate_flagT");
		if(flag=='0'){
			jQuery('#chuli').css('display','block');
		} else {
			jQuery('#chuli').css('display','none');
		}
		//jQuery("#carln").html(encodeHtml(jQuery(rowData).data("vehicle_ln")));
		var vehln=jQuery(rowData).data("vehicle_ln");
		var vehCoce = jQuery(rowData).data("vehicle_code");
		if(Number(vehln.length)>7){
			if(vehCoce == '外租')
				jQuery("#carln").html(vehCoce+"<font style='font-size: 12px;'>("+encodeHtml(vehln.substr(vehln.length-7))+")</font>");
			else
				jQuery("#carln").html(vehCoce+"号<font style='font-size: 12px;'>("+encodeHtml(vehln.substr(vehln.length-7))+")</font>");
			jQuery("#carln").attr("title",vehln);
		}else{
			if(vehCoce == '外租')
				jQuery("#carln").html(vehCoce+"<font style='font-size: 12px;'>("+encodeHtml(vehln)+")</font>");
			else
				jQuery("#carln").html(vehCoce+"号<font style='font-size: 12px;'>("+encodeHtml(vehln)+")</font>");
			jQuery("#carln").attr("title","");
		}
		
		jQuery("#alarmVehicleLn").val(jQuery(rowData).data("vehicle_ln"));
		var processName = jQuery(rowData).data("user_name");
		if(processName.length <= 1){
			jQuery("#alarmProPerson").val("无");
		} else {
			jQuery("#alarmProPerson").val(processName);
		}
		jQuery("#siteName").val(jQuery(rowData).data("site_name"));
		jQuery("#shijidaodashijian").val(jQuery(rowData).data("terminal_time"));
		jQuery("#routeName").val(jQuery(rowData).data("route_name"));
		
				
		jQuery("#messagetime").html(jQuery(rowData).data("alarm_date"));
		document.getElementById("messagetime").style.display="";
		//jQuery("#messagetime").css("dispaly","block");
		jQuery("#alarmtypename2").val(jQuery(rowData).data("alarm_type"));
// 		jQuery("#alarm_date").val(encodeHtml(jQuery(rowData).data("terminal_time").substring(11)));
		jQuery("#alarm_date").val(jQuery(rowData).data("terminal_time"));
		jQuery("#zhuangtai").val(encodeHtml(jQuery(rowData).data("operate_flag")));
		jQuery("#chidaoshijian").val(encodeHtml(jQuery(rowData).data("time_dis")) + "（分）");
		jQuery("#siji").val(encodeHtml(jQuery(rowData).data("NLSSORT(driver_name,'NLS_SORT = SCHINESE_PINYIN_M')")));
		jQuery("#sijidianhua").val(jQuery(rowData).data("driver_tel"));
		//隐藏字段赋值
		jQuery("#chulialarmid").val(jQuery(rowData).data("alarmid"));
		jQuery("#chulialarmtime").val(jQuery(rowData).data("terminal_time"));
		jQuery("#chulialarmtypeid").val(jQuery(rowData).data("alarm_type_id"));
		jQuery("#chulilon").val(jQuery(rowData).data("longitude"));
		jQuery("#chulilat").val(jQuery(rowData).data("latitude"));
		jQuery("#chulivin").val(jQuery(rowData).data("vehicle_vin"));
		//新增已处理字段赋值
		if(flag==0){
			jQuery('#chulirenname').html('无');
			jQuery('#yijianneirong').html('无');
			jQuery('#alarmProPerson').html('无');
		}else{
			jQuery('#chulirenname').html(encodeHtml(jQuery(rowData).data("user_name")));
			jQuery('#yijianneirong').html(encodeHtml(jQuery(rowData).data("opeerate_desc")));
		}
		
	}
//批量解除告警
function senddealcommand(){
	var alarmChoice = document.getElementsByName("checkalarm");
	var alarmIdList = "";
	//var vinList="";
	//var alarmtypeList="";
	var dealfalgList="";
	var tertimeList="";
	for(var i=0; i<alarmChoice.length; i++) {
	       if(alarmChoice[i].checked==true) {
	    	   var dealflag=get_cell_text(alarmChoice[i].value, 4);
	    	   var tertime=get_cell_text2(alarmChoice[i].value, 15);
	    	   //var vin=get_cell_text(alarmChoice[i].value, 2);
	    	   //var alarmtypeid=get_cell_text(alarmChoice[i].value, 3);
	    	   if(dealflag==1){
	    	   }else{
	    		   alarmIdList += alarmChoice[i].value+",";
	    		   tertimeList +=tertime+",";
	    		   //vinList+=vin+",";
	    		   //alarmtypeList+=alarmtypeid+",";
	    		   dealfalgList+=dealflag+",";
	    	   }
	       }
    }
 	if(alarmIdList==""){
		alert("请选择需要批量解除的告警!");
		return false;
	}
	var info="确认批量解除告警?";
  	if(confirm(info)) {
  		jQuery.blockUI({ message: '<h1 style="background:url(../newimages/loading.gif)  no-repeat 35%; height:32px; padding-top:5px;padding-left:45px; ">正在处理...</h1>'});   
  		jQuery.ajax({
	   		  type: 'POST',  
	   		  url: 'dealtqcmorealarm.shtml',	
	   		  data: {alarmids: alarmIdList,dealflags:dealfalgList,tertimes:tertimeList},	
	   		 success: function() { 
	   			searchList();
		      },
    		  error:function (){
		    	
		      }
	    });
  	}  	
	
}


function exportAlarm() {
	if(jQuery(gala).find("td").length == 0){
		alert("没有迟到告警!");
		return;	
	}
	if(jQuery(gala).flex_totalc > 50000){
		alert("无法导出，系统最多可一次导出5W条记录!");
		return;	
	}
	 var start_time=document.getElementById('start_time').value.replace(/-/g,"/");
	 var end_time=document.getElementById('end_time').value.replace(/-/g,"/");
     var startdate=new Date(start_time);	
     var enddate=new Date(end_time);
	 var idays = parseInt((enddate - startdate)/24/ 1000 / 60 / 60);
		if(idays> 2){
			alert("时间跨度大于3天,不可导出");
			return;
		}
	if(confirm("确定要导出告警信息么？")) {
		
		document.getElementById('tqcalarm.sortname').value = jQuery(gala).flex_sortname();	
		document.getElementById('tqcalarm.sortorder').value = jQuery(gala).flex_sortorder();
		
		document.getElementById('alarmManage_form').action="<s:url value='/busalarm/exportlatealarm.shtml' />";
		document.getElementById('alarmManage_form').submit();
	}
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