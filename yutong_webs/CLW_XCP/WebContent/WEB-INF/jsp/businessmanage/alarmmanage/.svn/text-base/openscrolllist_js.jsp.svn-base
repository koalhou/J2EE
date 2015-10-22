<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>

<script type="text/javascript">
var timeInterval = null;
jQuery(function() {
	var gala = jQuery('#gala');
	gala.flexigrid({
		url: '<s:url value="/busalarmpkg/getRealTimeAlarm.shtml" />',
		dataType: 'json',
		height: '1420',
		width:'1360',
		colModel : [{display: '经度' ,name : 'longitude',width : 80,hide:true,toggle:false},
                    {display: '纬度' ,name : 'latitude',width : 80,hide:true,toggle:false},
                    {display: 'VIN' ,name : 'vehicle_vin',width : 80,hide:true,toggle:false},
					{display: '车牌号', name : 'vehicle_ln', width : 100, sortable : false, align: 'center',escape: true},
					{display: '线路', name : 'route_name', width : 160, sortable : false, align: 'center',escape: true},
					{display: '当前速度' , name : 'speeding', width : 70, sortable : false, align: 'center'},
					{display: '预警速度', name : 'top_speed', width : 70, sortable : false, align: 'center'},
					{display: 'GPS时间', name : 'terminal_time', width : 150, sortable : false, align: 'center'},
					{display: '当前位置' , name : 'alarm_address', width : 730, sortable :false, align: 'center'},
					{display: '' , name : 'isAlarm', width : 30, sortable :false, hide:true, align: 'center'},
					{display: '' , name : 'dvr_stat', width : 30, sortable :false, hide:true, align: 'center'}
					],	    		
		    	sortname: 'vehicle_vin',
		    	sortorder: 'desc',
		    	sortable: true,
				striped :true,
				usepager :false, 
				resizable: false,
		    	useRp: true,
		    	rp:61,
				rpOptions : [10,20,50,100 ],// 可选择设定的每页结果数
		    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
				newp: 1,
			    onSuccess:function(){
			    	jQuery("div[class=pDiv]").css("display","none");
			    	jQuery('#gala tr').each(function(i,n){
						   if(jQuery(n).text().indexOf('超速')>-1){jQuery(n).find('td').css({ background: "yellow" });}
						   if(jQuery(n).text().indexOf('不在线')>-1){jQuery(n).find('td').css({ background: "gray",color: "white"})};
						   //var t=jQuery(n).children('td').eq(8);t.attr('title',t.text());
					});
			    	if(timeInterval == null){
				    	timeInterval = setTimeout(function(){searchList();},10000);
			    	} else {
			    		clearTimeout(timeInterval);
			    		timeInterval = null;
			    		timeInterval = setTimeout(function(){searchList();},10000);
			    	}
		        }

	});
	
});


function searchList() {
	//alert(jQuery('#gala').flex_current_page());
    jQuery('#gala').flexOptions({
	newp: Number(jQuery('#gala').flex_current_page()) + Number(1)//,
	//params: [{name: 'page', value: jQuery('#gala').flex_current_page()+Number(1) }]
	});
	jQuery('#gala').flexReload();
    
}

function reWriteLink(tdDiv,pid,row){
	var tdV = "";
	var gpsTime = row.cell[7];
	if( tpsTime != '' || gpsTime != null || gpsTime.length != 0 || gpsTime != undefined){
		tdV = gpsTime.split(".")[0];
	}
	return tdV;	
}


</script>


