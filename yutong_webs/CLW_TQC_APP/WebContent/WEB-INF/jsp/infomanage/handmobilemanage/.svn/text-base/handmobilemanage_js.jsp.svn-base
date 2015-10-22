<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function submit() {
	  var form=document.getElementById('vehicle_form');
	  form.submit();
}

function chaxun(){
	if(event.keyCode==13){
		searchList();
	}
}

function searchList() {
	document.getElementById('Below_new').style.display='none';
	jQuery("input[name='carChoiceAll']").removeAttr("checked"); 
	jQuery('#handtbl').flexOptions({
		newp: 1,
		params: [{name: 'terminal_id', value: jQuery('#terminal_id').val() },
		{name: 'organization_id', value: jQuery('#organizationID').val() }
		]
	});
	jQuery('#handtbl').flexReload();
}

function detialbrwose(vehicleid){
	var form = document.getElementById('handmobile_form');
	form.action = 'handmobile_edit_form.shtml?handmobileinfo.vehicle_id=' + vehicleid;
	form.submit();
}
function reWriteLink(tdDiv,pid,row){
	return '<a href="javascript:detialbrwose(\'' + pid + '\')">' + tdDiv +'</a>';
	}

function reWriteCheckBox(tdDiv,pid,row){
	//if(get_cell_text(pid, 5).length<=1){
		return  '<input name="carChoice" value="' + row.cell[2] + '"  type="checkbox" />';
	//}
	//else{
		
	 	//tdDiv.innerHTML = '<input name="carChoice" value="' + get_cell_text(pid, 1) + '" checked type="checkbox" />';
	//}
}


	/**
	 * 分配关联保存
	 */
	function save() {
		var carsChoice = document.getElementsByName("carChoice");


	    var carIdList = "";
	    
	    for(var i=0; i<carsChoice.length; i++) {
	        if(carsChoice[i].checked==true) {
	        	carIdList += carsChoice[i].value+",";
	        }
	    }

	    if(carIdList==""){
			alert("请选择设备进行去除分配！");
			return false;
		}

	    var info="<s:property value="getText('batch.canle.handmobile')"/>";
	   
	   if(confirm(info)) {
		   var url="<s:url value='/handmobile/batchHandmobileCancle.shtml' />?carsVinInfos=" + carIdList;
		   document.forms[0].action=url;
		   document.forms[0].submit();
		   
	   }  	
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
	var gala = jQuery('#handtbl');
	gala.flexigrid({
		url: '<s:url value="/handmobilegrid/handmobilebrowse.shtml" />',
		dataType: 'json',
		height: 375,
		width:2000,
		colModel : [
                    {display: '<input id="carChoiceAll" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', name : '', width : 20, process:reWriteCheckBox},
		    		{display: '设备编号', name : 'terminal_id', width : calcColumn(0.1,65,0), sortable : true, align: 'center', process: reWriteLink},
                    {display: '手机ＩＭＥＩ号', name : 'vehicle_vin', width : calcColumn(0.1,65,0), sortable : true, align: 'center'},
		    		{display: '手机号', name : 'cellphone', width : calcColumn(0.1,65,0), sortable : true, align: 'center'},
		    		{display: '<s:text name="vehcileinfo.short_allname" />', name : 'SHORT_ALLNAME', width :calcColumn(0.45,65,0), sortable : true, align: 'center',escape: true},
		    		{display: '使用者姓名', name : 'user_name', width : calcColumn(0.05,65,0), sortable : true, align: 'center',escape: true},
		    		{display: '联系方式', name : 'user_contact', width :calcColumn(0.1,65,0), sortable : true, align: 'center'}
			    	   ],	    		
		    	sortname: 'terminal_id',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 20,
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
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
//return height;
 */
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
	 jQuery(".flexigrid").width(w-25);
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