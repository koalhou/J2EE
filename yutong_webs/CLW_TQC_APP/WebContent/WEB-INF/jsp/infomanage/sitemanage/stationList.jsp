<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript"><!--
function reWriteCheckBox(tdDiv,pid){
	tdDiv.innerHTML = '<input name="carChoice" id="checkbox_'+pid+'" style="_margin-top:-3px;" value="'+encodeHtml(pid)+'#!#'+encodeHtml(get_cell_text(pid, 1))+'#!#' + encodeHtml(get_cell_text(pid, 3)) + '#!#'+encodeHtml(get_cell_text(pid, 4)) + '#!#'+encodeHtml(get_cell_text(pid, 2))+'#!#' +encodeHtml(get_cell_text(pid, 5))+'#!#'+encodeHtml(get_cell_text(pid, 6))+'#!#'+encodeHtml(get_cell_text(pid, 7))+'"  type="checkbox" onclick="chooseToMap(this)"/>';
}
function reWriteareawatch(tdDiv,pid){
	var areapoint = get_cell_text(pid, 2);
	tdDiv.innerHTML = '<a id="show_'+pid+'" href="javascript:showareasite(\''+tdDiv.innerHTML+ '\',\''+areapoint+ '\')">' + '查看' +'</a>';
}
function showareasite(id,point) {
	mapObj.removeAllOverlays();
	drawMapPic(point);
	//标记点
	drawPointinArea(id);
}
function drawMapPic(xyremark) {
	var areopt=new MAreaOptions();
	areopt.canShowTip = false;
	
	var xys = new Array();
	//将坐标推入数组
	var value = xyremark.split(",");
	xys.push(new MLngLat(value[0],value[1]));
	xys.push(new MLngLat(value[2],value[3]));

	//参数分别为 左上角坐标 和对应和对角线坐标 , 样式 , ID qqhrqjgojKHEJ oipgkglllgOLIJ qqhrqfhohODMF oipgkgmgljOLIJ
	var rect = new MRectangle(xys,areopt);
	mapObj.addOverlay(rect,true);
}
function drawPointinArea(id) {
	var param = {areaid:id};
	jQuery.post("../area/getsiteinarea.shtml",param,function(data){
		//标记相对应的 site 点
		var points = eval(data);
		/**
		for(var v=0;v<point.length;v++) {
			point[v].site_id;
			point[v].site_name;
			point[v].lon;
			point[v].lat;
		}**/
		addpointtomap(points[0].sites);
	},"text");
}
function addpointtomap(points) {
	var markerOption = new MMarkerOptions();
	markerOption.imageUrl = "../images/map/station2.png";
	markerOption.picAgent=false;
	markerOption.imageSize = new MSize(14, 32);
	markerOption.imageAlign = MConstants.MIDDLE_CENTER;
	markerOption.rotation = 0;
	markerOption.canShowTip = true;
	markerOption.isEditable=false; //设置点是否可编辑。
	markerOption.hasShadow=false;  //是否显示阴影。
	markerOption.zoomLevels=[]; //设置点的缩放级别范围。
	markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
	markerOption.dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
	for(var v=0;v<points.length;v++) {
		markerOption.labelOption = addMarkerLabel2(points[v].sitename,MConstants.BOTTOM_CENTER);
		var marker = new MMarker(new MLngLat(points[v].lon,points[v].lat),markerOption);
		marker.id=points[v].siteid;
		mapObj.addOverlay(marker,false);
		//mapObj.addEventListener(marker, MConstants.MOUSE_CLICK,onevmouse_click);
	}
}
//地图上点的鼠标点击事件
function onevmouse_click(event){
	var mkid = event.overlayId;
	var marker = mapObj.getOverlayById(mkid);
	//记录打开tip的点的vin
	tipOpenVin = marker.id;
	tipOpenLon = marker.lnglat.lngX;
	tipOpenLat = marker.lnglat.latY;
	//document.write("onevmouse_click::"+lon+";"+lat);
	add_map_tiptions();

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
/**
* 新建站点
*/
function addNewStation(){
	$("#deleteButton").css("display","none"); 
	$("#updateButton").css("display","none"); 
	$("#addButton").css("display","block"); 
	$("#sichen_addr").val("");
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


/**
* 查看站点详情
*/
function alertSiteDetail(siteName,longitude,latitude,sichen_addr,site_id){
	$("#deleteButton").css("display","block"); 
	$("#updateButton").css("display","block"); 
	$("#addButton").css("display","none");
	setStationInfo('BE66855BD543127FE0440019BB600AC6',true);
	siteName = siteName.replace("danyinh","\'");
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val(siteName);
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val(longitude);
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val(latitude);
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(7).children("td").eq(1).children("#sichen_addr").val(sichen_addr);
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(2).children("#site_id").val(site_id);
	
	var markerOption=new MMarkerOptions();
	markerOption.picAgent=false;
	markerOption.imageAlign=MConstants.BOTTOM_CENTER;
	markerOption.isBounce=false;
	markerOption.isEditable=false;
	markerOption.canShowTip= false;
	markerOption.rotation=0;
	markerOption.hasShadow=false;
	markerOption.imageUrl="../images/map/station2.png";
	markerOption.labelOption=addMarkerLabel(siteName,MConstants.BOTTOM_CENTER);
	var jwd = new MLngLat(longitude,latitude);
	checkLon(this);
	var marker = new MMarker(jwd,markerOption);
	
	marker.id = 'BE66855BD543127FE0440019BB600AC6';
	publicMapID = marker.id;
	mapObj.addOverlay(marker,false);
	//checkLon(this);
}


function showDetail(tdDiv,pid){
	
	var siteName = get_cell_text(pid, 1);
	var longitude = get_cell_text(pid, 2);
	var latitude = get_cell_text(pid, 3);	
	var sichen_addr=get_cell_text(pid, 4);	
	var organization_id=get_cell_text(pid,5);
	var site_id=get_cell_text(pid,8);	
	siteName = siteName.replace("\'","danyinh");
	tdDiv.innerHTML = '<a id="show_'+pid+'" href="javascript:alertSiteDetail(\'' + encodeHtml(siteName) + '\',\''+longitude+ '\',\''+latitude+ '\',\''+encodeHtml(sichen_addr) + '\',\''+site_id+'\')">' + '查看' +'</a>';
    
	return pid;
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
		      {display: '', name : 'site_longitude', width : 70, sortable : true, align: 'center',hide:true,toggle:false,process:setLonInnerHtml},
		      {display: '', name : 'site_latitude', width : 70, sortable : true, align: 'center',hide:true,toggle:false,process:setLatInnerHtml},
		      {display: '', name : 'sichen_addr', width : 70, sortable : true, align: 'center',hide:true,toggle:false,process:setAddrInnerHtml},
		      {display: '', name : 'organization_id', width : 70, sortable : true, align: 'center',hide:true,toggle:false,process:setOrgIDInnerHtml},
		      {display: '', name : 'organizationName', width : 70, sortable : true, align: 'center',hide:true,toggle:false,process:setOrgNameInnerHtml},
		      {display: '', name : 'show', width : 70, sortable : true, align: 'center',process:showDetail},
		      {display: '', name : 'site_id', width : 70, sortable : true, align: 'center',hide:true,toggle:false}
		      ],
	    buttons : [
			  {name: '站点名称：<input type="text" id="stationName" style="width:85px" onkeypress="if(event.keyCode==13){searchStation();}"/>'},
			  {separator: true},
			  {name: '查询', bclass: 'searchInfo', onpress : searchStation},
        	{separator: true},
        	  {name: '新建', bclass: 'add',onpress :addNewStation}
  	         
  	          ],
		     //title: '站点信息',
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
var iTimerID=null;
function loadUpTabDate2(){
	jQuery('#gala2').flexigrid({
		url: '<s:url value="/areaplanpkg/areaList.shtml" />', 
		dataType: 'json',    //json格式
		height: get_page_height()-266,
		width:310,
		colModel : [
		      {display: '序号', name : '', width : 70, sortable : true, align: 'center',escape:true},			     
		      {display: '区域名称', name : 'area_name', width : 90, sortable : true, align: 'center',toggle:false},
		      {display: '', name : '', width : 70, sortable : true, align: 'center',hide:true,toggle:false},
		      {display: '', name : '', width : 60, sortable : true, align: 'center',toggle:false,process:reWriteareawatch}
		      ],
      buttons : [
   			  {name: '区域名称：<input type="text" id="areaName" style="width:85px" onkeypress="if(event.keyCode==13){searcharea();}"/>'},
   			  {separator: true},
   			  {name: '查询', bclass: 'searchInfo', onpress : searcharea}
     	 ],
	     //title: '区域信息',
	     sortname: 'area_name',
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
	     onSuccess:function(){
		    //showareasite(jQuery("#areaid").val(),jQuery("#row"+jQuery("#areaid").val()).children("td").eq(2).children("div").html());
		    //alert(jQuery("#row"+jQuery("#areaid").val()).children("td").eq(3).children("div").children("a").length);
		    
		    if(jQuery("#treearea").hasClass("tabfocus")) {
		    	iTimerID = window.setTimeout(function(){
			    	showareasite(jQuery("#areaid").val(),jQuery("#row"+jQuery("#areaid").val()).children("td").eq(2).children("div").html());
			    	//jQuery("#row"+jQuery("#areaid").val()).children("td").eq(3).children("div").children("a").click();
			    },3000);
		    }
		 }
	});
}

//查询
function searchStation() {	
	jQuery("input[name='carChoiceAll']").removeAttr("checked");
	var stationName=formatSpecialChar(jQuery('#stationName').val());
	jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'site.site_name', value: stationName},
		         {name: 'site.control_station', value: '0'}]	
	});
	jQuery('#gala').flexReload();
}

function searcharea() {
	var areaName=formatSpecialChar(jQuery('#areaName').val());
	jQuery('#gala2').flexOptions({
		newp: 1,
		params: [{name: 'areaName', value: areaName}]	
	});
	jQuery('#gala2').flexReload();
}
function addMarkerLabel2(pointname,direction){   
    var fontstyle=new MFontStyle();//创建字体风格对象   
    fontstyle.name="";//设置字体名称，默认为宋体   
    fontstyle.size=14;//设置字体大小，默认为12   
    fontstyle.color=0xFFFFFF;//设置字体的颜色，默认为0x000d46(黑色)   
    fontstyle.bold=false;//设置字体是否为粗体，true，是，fasle，否（默认）   
    var labeloption=new MLabelOptions();//添加标注   
    labeloption.fontStyle=fontstyle;//设置标注的字体样式   
    labeloption.alpha=0.8;//设置标背景及边框的透明度，默认为1，及不透明   
    labeloption.hasBackground=true;//设置标注是否有背景，默认为false，即没有背景   
    labeloption.hasBorder=true;//设置标注背景是否有边框，默认为false，即没有边框   
    labeloption.backgroundColor=0x000000;//设置标注的背景颜色   
    labeloption.borderColor=0x145697;//设置标注的边框颜色   
    labeloption.content=pointname;//标注的显示内容   
   //设置标注左上角相对于面对象中心的锚点。标注左上角与面对象中心重合时，像素坐标原点(0,0)   
    //labeloption.labelPosition=new MPoint(0,8);   
    //设置对准点正下方的文字标签锚点   
    labeloption.labelAlign=direction;   
  
   return  labeloption;
}
</script>