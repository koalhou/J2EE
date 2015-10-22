<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>





<script type="text/javascript">
 
 

function submit() {
	  var form=document.getElementById('vehicle_form');
	  form.submit();
}

function searchList() {
	 document.getElementById('Below_new').style.display='none';
	jQuery('#vehicletbl').flexOptions({
		newp: 1,
		params: [{name: 'vehcileVin', value: formatSpecialChar(jQuery('#vehcileVin').val()) },
		{name: 'organization_id', value: jQuery('#organizationID').val()},
		{name: 'vehcileLn', value: formatSpecialChar(jQuery('#vehcileLn').val())}
		]
	});
	jQuery('#vehicletbl').flexReload();
}


function detialbrwose(vehicleid){
	var form = document.getElementById('vehicle_form');
	form.action = 'vehicle_edit_form.shtml?vehcileInfo.vehicle_id=' + vehicleid;
	form.submit();
}
function reWriteLink(tdDiv,pid,row){
	return  '<a href="javascript:detialbrwose(\'' + pid + '\')">' + tdDiv +'</a>';
	}

function reWriteCheckBox(tdDiv,pid,row){
	//if(get_cell_text(pid, 5).length<=1){
		
		return '<input name="carChoice" value="' + row.cell[2] + '"  type="checkbox" />';
	//}
	//else{
		
	 	//tdDiv.innerHTML = '<input name="carChoice" value="' + get_cell_text(pid, 1) + '" checked type="checkbox" />';
	//}
}


	
	
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}


function setOrCancelSelect(obj) {
	
	 if(jQuery(obj).attr("checked")){
		jQuery("input[name='carChoice']").each(function(){
		jQuery("input[name='carChoice']").attr("checked","true");
		});
	 }else{
		 jQuery("input[name='carChoice']").each(function(){
			 jQuery("input[name='carChoice']").removeAttr("checked"); 
			// f_str += ","+jQuery("input[name='carChoice']").attr("value");
		});
	}
}




jQuery(function() {
	var gala = jQuery('#vehicletbl');
	gala.flexigrid({
		url: '<s:url value="/vehiclemanagepkg/vehiclebrowse.shtml" />',
		dataType: 'json',
		height: 375,
		width:2000,
		colModel : [
                    {display: '<input id="carChoiceAll" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', name : '', width : get_page_width()*0.05, process:reWriteCheckBox},
		    		{display: '车牌号', name : 'vehicle_ln', width : get_page_width()*0.15, sortable : true, align: 'center', process: reWriteLink,escape: true},
                    {display: '车辆ＶＩＮ', name : 'VEHICLE_VIN', width : get_page_width()*0.2, sortable : true, align: 'center',escape: true},
		    		{display: '座位数', name : 'LIMITE_NUMBER', width : get_page_width()*0.1, sortable : true, align: 'center',escape: true},
		    		{display: '驾驶员', name : "NLSSORT(driver_name,'NLS_SORT = SCHINESE_PINYIN_M')", width : get_page_width()*0.1, sortable : true, align: 'center',escape: true},
		    		{display: '<s:text name="vehcileinfo.short_allname" />', name : 'SHORT_ALLNAME', width : get_page_width()*0.4, sortable : true, align: 'center',escape: true}
		    		],
		    		    		
		    	sortname: 'vehicle_vin',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 20,
		    	
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		    	onSuccess:function(){
	               jQuery("input[name='carChoiceAll']").removeAttr("checked");  
                  }
		    	//getQuery :getQuery
	});
});


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
		            var topOrgName =  window.document.getElementById('organizationName');
		            var topOrgID = window.document.getElementById('organizationID');
		            //赋值
		        	if (topOrgName) topOrgName.value = orgNameValue;
		        	if (topOrgID) topOrgID.value = orgIDValue;
		    	}
		  
			});

}


//页面自适应
(function (jQuery) {
 jQuery(window).resize(function(){
 
  testWidthHeight();
  
 });
 jQuery(window).load(function (){
  
  testWidthHeight();
  
 });
 
})(jQuery);
//获取页面高度
function get_page_height() {
 /*
 var height = 0;
 
 if (jQuery.browser.msie) {
     height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
 } else {
     height = self.innerHeight;
 }
 return height;*/
 var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
 return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
}
//获取页面宽度
function get_page_width() {
 /*
 var width = 0;
 if(jQuery.browser.msie){ 
  width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
 } else { 
  width = self.innerWidth; 
 } 
 return width;*/
 var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
 return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
}

function firstrisize(){
	var h = get_page_height();
	 var w = get_page_width();
	 jQuery(".flexigrid").width(w-23);
	 //jQuery("#reportTab").height(h-100);
	 jQuery(".bDiv").height(h-325);
}

//计算控件宽高
function testWidthHeight(){
	firstrisize();
	 jQuery(window).mk_autoresize({
        height_include: '#content',
        height_exclude: ['#header', '#footer'],
        height_offset: 0,
        width_include: ['#header', '#content', '#footer'],
        width_offset: 0
     });
	 firstrisize();
}
</script>
 
 