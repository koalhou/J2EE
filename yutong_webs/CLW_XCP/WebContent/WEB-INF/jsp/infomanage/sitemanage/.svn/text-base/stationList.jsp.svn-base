<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function reWriteCheckBox(tdDiv,pid){
	tdDiv.innerHTML = '<input name="carChoice" id="checkbox_'+pid+'" style="_margin-top:-3px;" value="'+pid+'#!#'+get_cell_text(pid, 1)+'#!#' + get_cell_text(pid, 3) + '#!#'+get_cell_text(pid, 4) + '#!#'+get_cell_text(pid, 2)+'#!#' +get_cell_text(pid, 5)+'#!#'+get_cell_text(pid, 6)+'#!#'+get_cell_text(pid, 7)+'"  type="checkbox" onclick="chooseToMap(this)"/>';
}

function setNameInnerHtml(tdDiv,pid){
	tdDiv.id='name_'+pid;
}
function setPropertyInnerHtml(tdDiv,pid){
	tdDiv.id='property_'+pid;
	tdDiv.innerHTML =get_cell_text(pid, 2)=='0'?'上学':'放学';
}

function setLonInnerHtml(tdDiv,pid){
	tdDiv.id='lon_'+pid;
}

function setLatInnerHtml(tdDiv,pid){
	tdDiv.id='lat_'+pid;
}
function setAddrInnerHtml(tdDiv,pid){
	tdDiv.id='addr_'+pid;
}
function setOrgIDInnerHtml(tdDiv,pid){
	tdDiv.id='orgid_'+pid;
}
function setOrgNameInnerHtml(tdDiv,pid){
	tdDiv.id='orgname_'+pid;
}

function chooseToMap(obj){
	if(jQuery(obj).attr("checked")){
		addMarkerToMap(jQuery(obj).val());
	}else{
		cancelMarkerFromMap(jQuery(obj).val());
	}
}
	
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

function setOrCancelSelect(obj) {
	if(jQuery(obj).attr("checked")){
		jQuery("input[name='carChoice']").each(function(){
			if(jQuery("input[name='carChoice']").attr("checked")){
				jQuery("input[name='carChoice']").removeAttr("checked"); 
				cancelMarkerFromMap(jQuery(this).val());
			}else{
				return true;
			}
		});
		
		jQuery("input[name='carChoice']").each(function(){
			jQuery("input[name='carChoice']").attr("checked","true");
			//addMarkerToMap(jQuery(this).val());
		});
		selectAllStation(jQuery('#stationName').val(),jQuery('#stationProperties').val());
	}else{
		jQuery("input[name='carChoice']").each(function(){
			jQuery("input[name='carChoice']").removeAttr("checked"); 
			cancelMarkerFromMap(jQuery(this).val());
		});
	}
}

function addNewStation(){
	setStationInfo('BE66855BD543127FE0440019BB600AC6',false);
}

/**
* 删除站点
*/
function listDeleteStation() {
	var carsChoice = document.getElementsByName("carChoice");
	var stationIdList = "";
	for(var i=0; i<carsChoice.length; i++) {
	    if(carsChoice[i].checked==true) {
	        if(stationIdList=="") {
	     	   stationIdList = "'" + carsChoice[i].id.substr(9) + "'";
	        } else {
	     	   stationIdList=stationIdList + "," + "'" + carsChoice[i].id.substr(9)  + "'";
	        }
	    }
	}
	if(stationIdList==""){
		alert("请选择站点进行删除！");
		return false;
	}
	var info="您确定要删除所选站点吗？";
  
	if(confirm(info)) {
		jQuery.ajax({
			type: 'POST',  
			url: '../stationGrid/deleteBatchStation.shtml',	
			data: {stationIdList: stationIdList},	
			dataType: 'json',
			success: function(data) {
				if(data.returns=="success"){
					jQuery('#gala').flexReload();
					alert("站点删除成功！");
				}
				if(data.returns=="forbid"){
					alert("所选站点中包括已被分配的站点，不能被删除！");
				}
				if(data.returns=="error"){
					alert("站点删除失败！");
				}
			}  
		});
	}  	
}

function loadUpTabDate(){
	jQuery('#gala').flexigrid({
		url: '<s:url value="/stationGrid/stationList.shtml" />', 
		dataType: 'json',    //json格式
		height: get_page_height()-266,
		width:310,
		colModel : [ 
			  {display: '<input id="carChoiceAll" name="carChoiceAll" style="_margin-top:-3px;" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', name : '', width : 20, process:reWriteCheckBox,align: 'center'},
		      {display: '站点名称', name : 'site_name', width : 172, sortable : true, align: 'center',escape:true,process:setNameInnerHtml},
		      {display: '站点属性', name : 'control_station',width : 50, sortable : true, align: 'center',process:setPropertyInnerHtml},
		      {display: '', name : 'site_longitude', width : 70, sortable : true, align: 'center',hide:true,toggle:false,process:setLonInnerHtml},
		      {display: '', name : 'site_latitude', width : 70, sortable : true, align: 'center',hide:true,toggle:false,process:setLatInnerHtml},
		      {display: '', name : 'sichen_addr', width : 70, sortable : true, align: 'center',hide:true,toggle:false,process:setAddrInnerHtml},
		      {display: '', name : 'organization_id', width : 70, sortable : true, align: 'center',hide:true,toggle:false,process:setOrgIDInnerHtml},
		      {display: '', name : 'organizationName', width : 70, sortable : true, align: 'center',hide:true,toggle:false,process:setOrgNameInnerHtml}
		      ],
	    buttons : [
			  {name: '站点名称：<input type="text" id="stationName" maxlength="13" style="width:85px" onkeypress="if(event.keyCode==13){searchStation();}"/>'},
			  {separator: true},
			  {name: '查询', bclass: 'searchInfo', onpress : searchStation},
        	  {name: '站点属性：<select id="stationProperties" style="width:80px" onchange="searchStation()"><option value="">全部</option><option value="0">上学</option><option value="1">放学</option></select>&nbsp;&nbsp;'},
        	  {separator: true}   	     
        	  <s:if test="#session.perUrlList.contains('111_3_3_5_2')">
  				,{name: '新建', bclass: 'add',onpress :addNewStation}
  	          </s:if>
  			  <s:if test="#session.perUrlList.contains('111_3_3_5_4')">
  				,{name: '删除', bclass: 'del',onpress :listDeleteStation}
  	          </s:if>
  	          ],
		     title: '站点信息',
		     sortname: 'CXST.site_name',
		     sortorder: 'asc',  //升序OR降序
		     sortable: true,   //是否支持排序
		     striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		     usepager :true,  //是否分页
		     resizable: false,  //是否可以设置表格大小
		     useRp: false,    // 是否可以动态设置每页显示的结果数
		     pagestat: ' 共 {total} 条',
		     pagetext: '',
		  	 outof: '/',
		  	 procmsg: '加载中',
		     rp: 20,  //每页显示记录数
		     rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		     setListChecked:true,
		     showTableToggleBtn: false,
		     showToggleBtn:false,
		     onSubmit:function(){
		    	jQuery("input[name='carChoice']").each(function(){
		 			jQuery("input[name='carChoice']").removeAttr("checked"); 
		 			cancelMarkerFromMap(jQuery(this).val());
		 		});
		    	 jQuery("input[name='carChoiceAll']").removeAttr("checked"); 
		    	 return true;
		     }
	});
}

//查询
function searchStation() {
	jQuery("input[name='carChoiceAll']").removeAttr("checked");
	jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'site.site_name', value: formatSpecialChar(jQuery('#stationName').val())},
		         {name: 'site.control_station', value: jQuery('#stationProperties').val()}]	
	});
	jQuery('#gala').flexReload();
}
</script>