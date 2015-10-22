<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
var dealflag=0;
function qinli(){
	jQuery("#carln").html('未选车');
	jQuery("#carln").attr("title","");
	jQuery("#messagetime").css('display','none');
	jQuery("#messagetime").html(jQuery('#year').val()+"-00-00 00:00:00");
	jQuery("#alarmtypename1").val('');
	jQuery("#geo1").val('');
	jQuery("#drivername1").val('');
	jQuery("#tearcher1").val('');
	jQuery("#chengce1").val('');
	jQuery('#chuli').css('display','none');	
	jQuery('#yijianneirong').html('');
	jQuery('#chulirenname').html('无');
	if(dealflag==1){
		jQuery('#piliangchuli').css('display','none');
	}else{
		jQuery('#piliangchuli').css('display','');
	}
}
function chuli(){
    var alarmid=jQuery("#chulialarmid").val();
    var alarmtime=jQuery("#chulialarmtime").val();
    var alarmtypeid=jQuery("#chulialarmtypeid").val();
    var lon=jQuery("#chulilon").val();
    var lat=jQuery("#chulilat").val();
    var vin=jQuery("#chulivin").val();
    openPopUp(vin,alarmid,alarmtime,alarmtypeid,lon,lat);
}
function changedeal(){
	var flag=jQuery("#cdeal_flag").val();
	if(flag==0)
	{
		dealflag=flag;
		//jQuery('#deal0').addClass('alarm_tabs');
		//jQuery('#deal1').removeClass();
		//jQuery('#deal2').removeClass();
		jQuery('#deal_flag').val(0);
		//jQuery("#carln").html('未选车');
		//jQuery("#messagetime").html(jQuery('#year').val()+"-00-00 00:00:00");
		//jQuery("#alarmtypename1").html('');
		//jQuery("#geo1").html('');
		//jQuery("#drivername1").html('');
		//jQuery("#tearcher1").html('');
		//jQuery("#chengce1").html('');
		//jQuery('#piliangchuli').css('display','');
		//jQuery('#chuli').css('display','none');
		//jQuery('#yijian').css('display','none');
		//jQuery('#yijianneirong').css('display','none');
		//jQuery('#chuliren').css('display','none');
		
	}

	if(flag==1){
		dealflag=flag;
		jQuery('#deal_flag').val(1);
		//jQuery('#deal0').removeClass();
		//jQuery('#deal1').addClass('alarm_tabs');
		//jQuery('#deal2').removeClass();
		//jQuery('#deal_flag').val(1);
		//jQuery("#carln").html('未选车');
		//jQuery("#messagetime").html(jQuery('#year').val()+"-00-00 00:00:00");
		//jQuery("#alarmtypename1").val('');
		//jQuery("#geo1").val('');
		//jQuery("#drivername1").val('');
		//jQuery("#tearcher1").val('');
		//jQuery("#chengce1").val('');
		//jQuery('#chuli').css('display','none');
		//jQuery('#piliangchuli').css('display','none');
		//jQuery('#yijian').css('display','');
		//jQuery('#yijianneirong').css('display','');
		//jQuery('#chuliren').css('display','');
		//jQuery('#chulirenname').val('');
		
	}
	if(flag==2){
		dealflag=flag;
		//jQuery('#deal0').removeClass();
		//jQuery('#deal1').removeClass();
		//jQuery('#deal2').addClass('alarm_tabs');
		jQuery('#deal_flag').val(2);
		//jQuery("#carln").html('未选车');
		//jQuery("#messagetime").html(jQuery('#year').val()+"-00-00 00:00:00");
		//jQuery("#alarmtypename1").html('');
		//jQuery("#geo1").html('');
		//jQuery("#drivername1").html('');
		//jQuery("#tearcher1").html('');
		//jQuery("#chengce1").html('');
		//jQuery('#piliangchuli').css('display','');
		//jQuery('#chuli').css('display','none');
		//jQuery('#yijian').css('display','none');
		//jQuery('#yijianneirong').css('display','none');
		//jQuery('#chuliren').css('display','none');
		
	}
	searchList();
}

function reWriteLink(tdDiv,pid){
	/*
	var longitude = get_cell_text(pid, 0);
	var latitude = get_cell_text(pid, 1);
	var vehicle_vin = get_cell_text(pid, 2);	
	var alarmtime=get_cell_text(pid, 9);	
	var alarmflag=get_cell_text(pid, 4);
	var alarmtypename=get_cell_text(pid, 8);
	if(alarmflag==1){
		tdDiv.innerHTML = '<a href="javascript:openAlarmPosition(\'' + longitude + '\',\''+latitude+ '\',\''+vehicle_vin + '\',\''+pid+ '\',\''+alarmtime+'\',\''+alarmtypename+'\')">' + '处理结果' +'</a>';
	}
	else{
		tdDiv.innerHTML = '<a href="javascript:openAlarmPosition(\'' + longitude + '\',\''+latitude+ '\',\''+vehicle_vin + '\',\''+pid+ '\',\''+alarmtime+'\',\''+alarmtypename+'\')">' + '点击处理' +'</a>';
	}*/	
	return pid;
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

function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}
function reWritedeal_flag(tdDiv,pid,row){

   var deal_flag = row.cell[4];
   if(deal_flag ==0 ){
      return '<s:text name="vehcileinfo.alarmno" />';
   }else if(deal_flag==2){
	  return '处理中';
   }
   else{
      return '<s:text name="vehcileinfo.alarmyes" />';
   }
}

function rewritecheckbox(tdDiv,pid,row){
	  return '<input type="checkbox"  name="checkalarm" value="'+pid+'"/> '; 
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
	//senddealcommand();		   	
}

//批量解除告警
function senddealcommand(){
	var alarmChoice = document.getElementsByName("checkalarm");
	var alarmIdList = "";
	var vinList="";
	var alarmtypeList="";
	var dealfalgList="";
	var alarmtimelist="";
	for(var i=0; i<alarmChoice.length; i++) {
	       if(alarmChoice[i].checked==true) {
	    	   var dealflag=get_cell_text(alarmChoice[i].value, 4);
	    	   var vin=get_cell_text(alarmChoice[i].value, 2);
	    	   var alarmtypeid=get_cell_text(alarmChoice[i].value, 3);
	    	   var alarmtime=get_cell_text2(alarmChoice[i].value, 10);
	    	   if(dealflag==1){
	    	   }else{
	    		   alarmIdList += alarmChoice[i].value+",";
	    		   vinList+=vin+",";
	    		   alarmtypeList+=alarmtypeid+",";
	    		   dealfalgList+=dealflag+",";
	    		   alarmtimelist+=alarmtime+",";
	    	   }
	       }
    }
 	if(alarmIdList==""){
		alert("请选择需要批量解除的告警!");
		return false;
	}
	var info="确认批量解除告警?";
  	if(confirm(info)) {
  		jQuery.blockUI({ message: '<h1 style="background:url(../newimages/loading.gif)  no-repeat 35%; height:32px; padding-top:5px;padding-left:45px;">正在处理...</h1>'});   
  		jQuery.ajax({
	   		  type: 'POST',  
	   		  url: 'dealmorealarm.shtml',	
	   		  data: {alarmids: alarmIdList,vins:vinList,alarmtypes:alarmtypeList,dealflags:dealfalgList,alramtimes:alarmtimelist,mouldid:"111_3_1_12_5"},	
	   		 success: function() { 
	   			searchList();
		      },
    		  error:function (){
		    	
		      }
	    });
  	}  	
	
}

jQuery(function() {
	var gala = jQuery('#gala');
	gala.flexigrid({
		url: '<s:url value="/busalarmpkg/alarmsosmanage.shtml" />',
		dataType: 'json',
		height: '2000',
		width:'600',
		colModel : [{display: '经度' ,name : 'longitude',width : 80,hide:true,toggle:false},
                    {display: '纬度' ,name : 'latitude',width : 80,hide:true,toggle:false},
                    {display: 'VIN' ,name : 'vehicle_vin',width : 80,hide:true,toggle:false},
                    {display: 'ALARM_TYPE_ID' ,name : 'ALARM_TYPE_ID',width :80,hide:true,toggle:false},
                    {display: 'deal_flag' ,name : 'deal_flag',width :80,hide:true,toggle:false},
                    {display: '方向', name : 'direction', width : 30,hide:true,toggle:false},	
		            {display: '<input type="checkbox" id="checkallalarm" name="checkallalarm"  value="123" onclick="allchooseFlag(this)"/>',name : '',width : 25,sortable : false,<s:if test="#session.perUrlList.contains('111_3_1_12_5')">hide:false,</s:if><s:else>hide:true,toggle:false,</s:else>align: 'center',process:rewritecheckbox},
					{display: '车牌号', name : 'vehicle_ln', width : calcColumn(0.1,90,240), sortable : true, align: 'center',escape: true},
					{display: '告警类型', name : 'alarm_type_name', width : calcColumn(0.1,100,240), sortable : true, align: 'center'},
					{display: '处理状态', name : 'deal_flag', width : calcColumn(0.05,75,240), sortable : true, align: 'center',process: reWritedeal_flag},
					{display: '告警时间', name : 'alarm_time', width : calcColumn(0.12,100,240), sortable : true, align: 'center'},
					{display: '驾驶员', name : 'driver_name', width : calcColumn(0.1,85,240), sortable : true, align: 'center',escape: true},
					{display: '跟车老师'    , name : 'sichen_name', width : calcColumn(0.1,85,240), sortable : true, align: 'center',escape: true},
					{display: '乘坐学生数', name : 'stu_num', width : calcColumn(0.1,85,240), sortable : false, align: 'center'},
					{display: '地理位置' , name : 'geo', width : calcColumn(0.18,150,240), sortable :false, align: 'center'},
					{display: '处理' , name : 'alarmid', width : 80, sortable :false, align: 'center',hide:true,toggle:false,process:reWriteLink},
					{display: '处理人' , name : 'user_name', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true},
					{display: '处理意见' , name : 'opeerate_desc', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true}
					],	    		
		    	sortname: 'alarm_time',
		    	sortorder: 'desc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 20,
				rpOptions : [10,20,50,100 ],// 可选择设定的每页结果数
		    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
                onRowSelect:rowstandbyclick1,
                params: [{name: 'vehicle_ln', value: jQuery('#vehicle_ln').val() }, 
 				 		{ name: 'alarm_type_id', value: jQuery('#alarm_type_id').val()},
 				 		{ name: 'deal_flag', value: jQuery('#deal_flag').val()},
 				 		{ name: 'chooseorgid', value: jQuery('#chooseorgid').val()},
 				 		{ name: 'start_time', value: jQuery('#start_time').val()},
 				 		{ name: 'end_time', value: jQuery('#end_time').val()}],
 			   onSuccess:function(){
				   jQuery("#gala").find('tr').eq(0).click();
 			       jQuery("input[name='checkallalarm']").removeAttr("checked");
 			       jQuery.unblockUI();  
 		        }
              
	});
});

/*
var rowstandbyclick1 =function(rowData) {
	alert("1");
	jQuery("#carln").html(jQuery(rowData).data("vehicle_ln"));
	jQuery("#messagetime").html(jQuery(rowData).data("alarm_time"));
	jQuery("#alarmtypename1").html(jQuery(rowData).data("alarm_type_name"));
	jQuery("#geo1").html(jQuery(rowData).data("geo"));
	jQuery("#drivername1").html(jQuery(rowData).data("driver_name"));
	jQuery("#tearcher1").html(jQuery(rowData).data("sichen_name"));
	jQuery("#chengce1").html(jQuery(rowData).data("stu_num"));
};*/

function rowstandbyclick1(rowData){
	//显示字段赋值
	var dealflag=jQuery('#deal_flag').val();
	if(dealflag==0||dealflag==2){
		jQuery('#chuli').css('display','');
	}
	var vehln=jQuery(rowData).data("vehicle_ln");
	if(vehln.length>7){
		jQuery("#carln").html("*"+encodeHtml(vehln.substr(vehln.length-7)));
		jQuery("#carln").attr("title",vehln);
	}else{
		jQuery("#carln").html(encodeHtml(vehln));
		jQuery("#carln").attr("title","");
	}
	jQuery("#messagetime").html(jQuery(rowData).data("alarm_time"));
	jQuery("#messagetime").css('display','');
	jQuery("#alarmtypename1").val(jQuery(rowData).data("alarm_type_name"));
	jQuery("#geo1").val(jQuery(rowData).data("geo"));
	jQuery("#drivername1").val(encodeHtml(jQuery(rowData).data("driver_name")));
	jQuery("#tearcher1").val(encodeHtml(jQuery(rowData).data("sichen_name")));
	jQuery("#chengce1").val(jQuery(rowData).data("stu_num"));
	//隐藏字段赋值
	jQuery("#chulialarmid").val(jQuery(rowData).data("alarmid"));
	jQuery("#chulialarmtime").val(jQuery(rowData).data("alarm_time"));
	jQuery("#chulialarmtypeid").val(jQuery(rowData).data("ALARM_TYPE_ID"));
	jQuery("#chulilon").val(jQuery(rowData).data("longitude"));
	jQuery("#chulilat").val(jQuery(rowData).data("latitude"));
	jQuery("#chulivin").val(jQuery(rowData).data("vehicle_vin"));
	//新增已处理字段赋值
	if(dealflag==0){
		jQuery('#chulirenname').html('无');
		jQuery('#yijianneirong').html('无');
	}else{
		jQuery('#chulirenname').html(encodeHtml(jQuery(rowData).data("user_name")));
		jQuery('#yijianneirong').html(encodeHtml(jQuery(rowData).data("opeerate_desc")));
	}
	
}

function exportAlarmNotice() {
	var form = document.getElementById('alarmManage_form');
	form.submit();
}

function exportAlarm(){
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
		document.getElementById('alarmManage_form').action="<s:url value='/busalarm/exportsosalarm.shtml' />";
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