<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
jQuery(function() {
	 jQuery('#popArea').mk_sidelayer({
	      // 'top': '130px',  //update by fanxy 保证top 38px;
	      'height': '540px',
	      'width': '500px',
	      //'url': '<s:url value="/carrun/ready.shtml" />',
	      'is_show': false,
	      'can_close': true,
	      close_callback: function() {
	          var iframeobj = document.getElementById("iframeshowArea");
	          if(iframeobj != null){
	           iframeobj.src ="";
	          }
	      },
	      hide_callback: function() {
		    	var iframeobj = document.getElementById("iframeshowArea");
		    	if(iframeobj != null){
		    		iframeobj.src ="";
		    	}
	      }      
	    });
});

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
			height_exclude : ['.car-info','.week_list'],
			height_include : '.table_list',
			height_offset : 70,
			width_include : ['.car-info','.week_list','.table_list'],
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
	}
}

jQuery(function() {
	upDateTab("6");
	var galaList = jQuery('#galaList');
	 galaList.flexigrid({
		  url: '<s:url value="/runoilJS/runOilRecords.shtml" />', 
		  params: [{name: 'queryObj.organization_id', value: jQuery('#user_org_id').val() },
				   {name: 'queryObj.queryTime', value: finalTime}],
		  dataType: 'json',    //json格式
		  height: 1500,
		  width: 1500,
		  colModel : [ 
				{display: '序号', name : 'ROWNUMBER', width : 30, sortable : false, align: 'center',escape: true},
				{display: 'vin', name : 'VEHICLE_VIN', width : 120, sortable : true, align: 'center',hide:true,toggle:false,escape: true},
				{display: '组织', name : 'SHORT_NAME', width : 120, sortable : true, align: 'center',hide:true,toggle:false,escape: true},
				{display: '车牌号', name : 'VEHICLE_LN', width : calcColumn(0.15,120,240), sortable : true, align: 'center',escape: true},
				{display: '点火时间', name : 'ON_DATE', width : calcColumn(0.15,150,240), sortable : true, align: 'center',escape: true},
				{display: '熄火时间', name : 'OFF_DATE', width : calcColumn(0.15,150,240), sortable : true, align: 'center',escape: true},
				{display: '怠速油耗(L)', name : 'SPD_OIL', width : calcColumn(0.15,100,240), sortable : true, align: 'center',escape: true},							  
		        {display: '油耗(L)', name : 'OIL', width : calcColumn(0.15,80,240), sortable : true, align: 'center',escape: true},
		        {display: '里程(Km)', name : 'MILEAGE', width : calcColumn(0.12,100,240), sortable : true, align: 'center',escape: true}
		        ],
		       sortname: "NLSSORT(VEHICLE_LN,'NLS_SORT=SCHINESE_PINYIN_M') ASC,ON_DATE",
		       sortorder: 'DESC',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true ,  // 是否允许显示隐藏列
		       onRowSelect:rowstandbyclick
	     });
});

function rowstandbyclick(rowData){
	var titleln=jQuery(rowData).data("VEHICLE_LN");
	if(titleln.length>7){
		titleln=titleln.substr(titleln.length-7);
		titleln="*"+titleln;
	}
	jQuery('#popArea').mk_sidelayer('set_title', encodeHtml(titleln));

	var sidebuttonHtml="";
	sidebuttonHtml+="<span><table width='370' border='0' cellspacing='0' cellpadding='0'>";
	sidebuttonHtml+="<tr><td colspan='2' height='8'></td></tr>";
	sidebuttonHtml+="<tr><td width='20'>&nbsp;</td>";
	sidebuttonHtml+="<td width='65' style='height: 22px;' valign='bottom'><strong>所属部门：</strong></td>";
	sidebuttonHtml+="<td style='height: 22px;' valign='bottom'><strong>"+encodeHtml(jQuery(rowData).data("SHORT_NAME"))+"</strong></td></tr>";
	sidebuttonHtml+="<tr><td>&nbsp;</td>";
	sidebuttonHtml+="<td style='height: 22px;' valign='top'><strong>回放时段：</strong></td>";
	sidebuttonHtml+="<td style='height: 22px;' valign='top'><strong>"+jQuery(rowData).data("ON_DATE");
	sidebuttonHtml+="--"+jQuery(rowData).data("OFF_DATE")+"</strong></td></tr></table></span>";
	jQuery('.mk-sidelayer-tools').html(sidebuttonHtml);

	var iframeobj = document.getElementById("iframeshowArea");
    if(iframeobj != null){
     iframeobj.src ="";
    }
	
    jQuery('#popArea').mk_sidelayer('reload', {
  	    'url': '<s:url value="/runoil/iframeChoice.shtml" />', 
  	     'query_param': {'vin': jQuery(rowData).data("VEHICLE_VIN"), 'begTime':jQuery(rowData).data("ON_DATE"),'endTime':jQuery(rowData).data("OFF_DATE")}
      });

}

jQuery(function() {
	auto_size();
	//重新刷新页面 适配IE9的高分辨率
	jQuery(window).mk_autoresize({
		height_include: '#content',
		height_exclude: ['#header', '#footer'],
		height_offset: 0,
		width_include: ['#header', '#content', '#footer'],
		width_offset: 0});
	auto_size();
});

function auto_size(){
	//jQuery(window).mk_autoresize({
	//	min_width: 1170
	//  });
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
		height_exclude : [ '.treeTab', '.newsearchPlan' ],
		height_include : '.treeBox',
		height_offset :10
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
		height_exclude : ['.car-info','.week_list'],
		height_include : '.table_list',
		height_offset : 70,
		width_include : ['.car-info','.week_list','.table_list'],
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
function searchCarRunList() {
	jQuery('#galaList').flexOptions({
		newp: 1,
		params: [{name: 'queryObj.organization_id', value: jQuery('#user_org_id').val() },
		         {name: 'queryObj.str_vins', value: finalVINs },
			     {name: 'queryObj.queryTime', value: finalTime}]
	});
	jQuery('#galaList').flexReload();
}

function exportData(){
	if(confirm("确定要导出日行车记录吗？")) {
		document.getElementById('exportObj.organization_id').value = jQuery('#user_org_id').val();
		document.getElementById('exportObj.queryTime').value = finalTime;
		document.getElementById('exportObj.str_vins').value = finalVINs;
		document.getElementById('export_form').action="<s:url value='/runoil/exportRunOilRecord.shtml' />";
		document.getElementById('export_form').submit();
	}
}

//选择车辆后，刷新日行车记录数据
var finalVINs = "";
function mytreeonClick(carList){		
	//保存vehicle_vin信息
	var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	if(nodes.length != 0 && carList==""){
		carList = "''";
	}
	finalVINs=carList;
	//查询照片列表信息
	searchCarRunList();
}

//选择日期后，点击查询按钮
function queryWeekList(){
	//查询按钮处默认选中最后一个页签
	upDateTab("6");
	searchCarRunList();
}
//点击页签
function changeChoose(id){
	upTabSatus(id);
	searchCarRunList();			
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
//7个TAB状态更新
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

function previousWeek(){
	upDateTab("0");
	searchCarRunList();
}

function nextWeek(){
	upDateTab("1");
	searchCarRunList();
}

Date.prototype.addDays = Date.prototype.addDays || function(days){
	this.setDate(this.getDate() + days);
	return this;
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
	if(thisday.getTime() > nowday.getTime()){
		thisday = new Date(nowday.getFullYear(), nowday.getMonth(), nowday.getDate());
	}
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