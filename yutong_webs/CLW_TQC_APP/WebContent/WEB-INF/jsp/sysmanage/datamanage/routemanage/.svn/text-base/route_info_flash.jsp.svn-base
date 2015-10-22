<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/key2.jsp" %>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/Route.js'></script>
<script type='text/javascript' src='../dwr/interface/RouteStationLONLAT.js'></script>

<style type="text/css">
a.searchMap img{ width: 16px; height: 16px;}
</style>
<script type="text/javascript">
function auto_size(){
	//计算中区高度
	jQuery('#content').mk_autoresize({
		height_include : [ '#content_rightside', '#content_leftside' ],
		height_offset : 0,
		width_exclude: ['#content_leftside'],
		width_include : ['#content_rightside'],
		width_offset : 1
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize({
		height_include : '#listRoute',
		height_offset : 160
	});

	jQuery('#listRoute').mk_autoresize({
		height_include : ['#selectLeftos','#selectRightos'],
		height_offset : 190
	});

	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize({
		height_include : '#iCenter',
		height_offset : 0,
		width_include : '#iCenter',
		width_offset : 0
	});	
}

//计算控件宽高
function testWidthHeight(){
	var Sys = {}; 
    var ua = navigator.userAgent.toLowerCase(); 
    
    var s;
    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 
    (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : 
    (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : 
    (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : 
    (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0; 
    
	var h = get_page_height();
	var w = get_page_width();

	//var leftInfoDivh = document.getElementById("leftInfoDiv").offsetHeight;;
	var iCenterh = document.getElementById("iCenter").offsetHeight;;
	var list=document.getElementById("listRoute");
	var listSearch=document.getElementById("listSearch");
	var tmp = 0;
	var infoH = 0;
	var listH = 0;
	
	if(Sys.ie) {
		if(Sys.ie.match(/[7-8]{1}(\.[\w]*)/)) {
			infoH = 40;
			tmp = 163;
			listH = 82;
		} else if(Sys.ie.match(/[6]{1}(\.[\w]*)/)) {
			infoH = 40;
			tmp = 158;
			listH = 82;
		}
	 } else if(Sys.firefox) {
		 infoH = 40;
		 tmp = 131;
		 listH = 82;
	 } 

	if(Sys.ie) {
		if(h>(tmp + listH)){
			//leftInfoDiv.style.height = h - tmp + infoH;
			iCenter.style.height=h - tmp+35;
			iCenter.style.width=w-306;
			list.style.height = h - tmp + infoH - listH;
		} else {
			if(h > infoH) {
				//leftInfoDiv.style.height = h - infoH;
			} else {
				//leftInfoDiv.style.height = h;
			}
			iCenter.style.width=w-306;
			iCenter.style.height=h;
		}
	} else if(Sys.firefox) {
		if(h>(tmp + listH)){
			//document.getElementById("leftInfoDiv").style.height = h - tmp + infoH + "px";
			document.getElementById("iCenter").style.height =h - tmp + "px";
			list.style.height = h - tmp + infoH - listH + "px";
			document.getElementById("iCenter").style.width=w-306;
		} else {
			if(h > infoH) {
				//document.getElementById("leftInfoDiv").style.height = h - infoH + "px";
			} else {
				//document.getElementById("leftInfoDiv").style.height = h + "px";
			}
			document.getElementById("iCenter").style.height = h + "px";
			document.getElementById("iCenter").style.width=w-306;
		}
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

  /** 地图对象 **/
  var mapObj = null;
  function addMarkerLabel(pointname,direction){   
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
	    labeloption.backgroundColor=0x145697;//设置标注的背景颜色   
	    labeloption.borderColor=0x000088;//设置标注的边框颜色   
	    labeloption.content=pointname;//标注的显示内容   
	    //设置标注左上角相对于面对象中心的锚点。标注左上角与面对象中心重合时，像素坐标原点(0,0)   
	    //labeloption.labelPosition=new MPoint(0,0);   
	    //设置对准点正下方的文字标签锚点 
	    //MConstants.TOP_CENTER--direction 
	    labeloption.labelAlign=direction;
	    return  labeloption;
  }

  function map_ready(){
	  initMapList();
  }
  function map_edit_ready(){
	  initMapEditList('1');
  }
  function initMapList() {
		var startTime=document.getElementById("start_time").value;
		var endTime=document.getElementById("end_time").value;
		var inout_flag=formatSpecialChar(document.getElementById("showStation").value);
		var control_station=0;//jQuery('input:radio[name="routeCheck"]:checked').val();
		var addstationids="";
		var delstationids="";
		
		if(control_station=='0'){
			addstationids=document.getElementById("upappendstation").value;
			delstationids=document.getElementById("updeletestation").value;
		}else{
			addstationids=document.getElementById("downappendstation").value;
			delstationids=document.getElementById("downdeletestation").value;
		}
		mapObj.removeAllOverlays();
		var arr = new Array();
		RouteStationLONLAT.getMapList(startTime,endTime,inout_flag,control_station,addstationids,delstationids,{
			callback : function(data) {
				for(var i=0;i<data.length;i++){
					if(data[i].LONGITUDE >=0 && data[i].LONGITUDE<=180 && data[i].LATITUDE>=0 && data[i].LATITUDE <=90){
						var markerOption=new MMarkerOptions();
						var sMarker = new MMarker(new MLngLat(data[i].LONGITUDE,data[i].LATITUDE),markerOption);
						if(data[i].SITE_FLAG=='0'){
						 markerOption.imageUrl="../images/map/station1.gif";
						 //markerOption.imageSize=new MSize(30,30);
						}else{
						 markerOption.imageUrl="../images/map/station2.gif"; 
						 //markerOption.imageSize=new MSize(30,30);
						}   
						markerOption.picAgent=false;
						if(data[i].CONTROL_STATION=='0'){
						    markerOption.tipOption = addDataToTipContent("",data[i].CONTROL_STATION,"",data[i].STATION_ADDR,data[i].sichen_addr); //yg 2012-11-29
						   	markerOption.labelOption=addMarkerLabel(data[i].STATION_ADDR,MConstants.BOTTOM_CENTER);
						}else{
						 	markerOption.tipOption = addDataToTipContent("",data[i].CONTROL_STATION,"",data[i].STATION_ADDR,data[i].sichen_addr); //yg 2012-11-29
						 	markerOption.labelOption=addMarkerLabel(data[i].STATION_ADDR,MConstants.BOTTOM_CENTER);
						}
						  
				   	 	//flash版本设置的属性
						markerOption.imageAlign=MConstants.BOTTOM_CENTER;
						markerOption.isBounce=false;
						markerOption.isEditable=false;
						markerOption.canShowTip= false;
						markerOption.rotation=0;
						sMarker.id=data[i].ID; 
				   	   	arr.push(sMarker);
					}
				}
	
		  	 	if(arr.length!=0){
					// 批量添加标点
					mapObj.addOverlays(arr,true); 
					var mks = mapObj.getOverlaysByType(MOverlay.TYPE_MARKER);
					//设置点击事件
					if(mks.length>0){
						for(var j = 0; j < mks.length; j++){
							mapObj.addEventListener(mks[j],MConstants.MOUSE_CLICK,clickMap);

							mapObj.addEventListener(mks[j], MConstants.MOUSE_OVER,addFlashTip);
						}
					}
				}
	
		  	 	var upcounter=0;
		  	 	var downcounter=0;
		  	 
				if(control_station=='0'){
					jQuery("#selectLeftos option").each(function(index){
						var mkedit = mapObj.getOverlayById(jQuery(this).val());
						if(mkedit!=null){
			   				upcounter++;
			   				if(upcounter==1){
								var arreditline = new Array();
								arreditline.push(new MLngLat(mkedit.lnglat.lngX,mkedit.lnglat.latY));
							    var linestyle=new MLineStyle();//创建线样式对象 
							    linestyle.thickness=3;//线的粗细度，默认为2 
							    linestyle.color=0xff0000;//线的颜色，16进制整数，默认为0x005890（蓝色） 
							    var lineopt=new MLineOptions();//构造一个名为lineopt的线选项对象 
							    lineopt.lineStyle=linestyle;//设置线的边缘风格 
							    
							    lineopt.tipOption = null;
							    lineopt.canShowTip = false;
							    
							    var polyline=new MPolyline(arreditline,lineopt);//通过经纬度坐标数组及参数选项构建多折线对象，arr是经纬度坐档数组 
							    polyline.id="upLine";//对象编号，也是对象的唯一标识 
							    mapObj.addOverlay(polyline,true);//向地图添加覆盖物 
							}else{
								var arrseditline = mapObj.getOverlayById("upLine");
								arrseditline.lnglatArr.push(new MLngLat(mkedit.lnglat.lngX,mkedit.lnglat.latY));
								mapObj.updateOverlay(arrseditline);
							}
					   	}
					}); 
			   	}else{
			   		/* jQuery("#selectRightos option").each(function(index){
						var mkrightedit = mapObj.getOverlayById(jQuery(this).val());
						if(mkrightedit!=null){
							downcounter++;
			   				if(downcounter==1){
								var arreditdownline = new Array();
								arreditdownline.push(new MLngLat(mkrightedit.lnglat.lngX,mkrightedit.lnglat.latY));
							    var linestyle=new MLineStyle();//创建线样式对象 
							    linestyle.thickness=3;//线的粗细度，默认为2 
							    linestyle.color=0xff0000;//线的颜色，16进制整数，默认为0x005890（蓝色） 
							    var lineopt=new MLineOptions();//构造一个名为lineopt的线选项对象 
lineopt.lineStyle=linestyle;//设置线的边缘风格 
							    
							    lineopt.tipOption = null;
							    lineopt.canShowTip = false;
							    
							    var polyline=new MPolyline(arreditdownline,lineopt);//通过经纬度坐标数组及参数选项构建多折线对象，arr是经纬度坐档数组 
							    polyline.id="downLine";//对象编号，也是对象的唯一标识 
							    mapObj.addOverlay(polyline,true);//向地图添加覆盖物 
							}else{
								var arrseditdownline = mapObj.getOverlayById("downLine");
								arrseditdownline.lnglatArr.push(new MLngLat(mkrightedit.lnglat.lngX,mkrightedit.lnglat.latY));
								mapObj.updateOverlay(arrseditdownline);
							}
					   	}
					});  */
		   		}
	
		  	 	if(control_station=='0'){
			   		jQuery("#selectLeftos option").each(function(index){
						var orderMarker = mapObj.getOverlayById(jQuery(this).val());
						if(orderMarker!=null){
							orderMarker.option.labelOption.content="【"+Number(index+1)+"】 "+getPointSAByObj(orderMarker,"initDirection")+getPointSAByObj(orderMarker,"initLaber");
							mapObj.updateOverlay(orderMarker);
						}
					}); 
			   		if(mapObj.getOverlayById("upLine")!=null){
			   			var arrForFitView=new Array(); 
				   	    arrForFitView.push("upLine"); 
				   	    mapObj.setFitview(arrForFitView);
	   				} 
		   		}
	
		  	 	if(control_station=='1'){
					jQuery("#selectRightos option").each(function(index){
						var orderDsMarker = mapObj.getOverlayById(jQuery(this).val());
						if(orderDsMarker!=null){
							orderDsMarker.option.labelOption.content="【"+Number(index+1)+"】 "+getPointSAByObj(orderDsMarker,"initDirection")+getPointSAByObj(orderDsMarker,"initLaber");
							mapObj.updateOverlay(orderDsMarker);
						}
					});
					if(mapObj.getOverlayById("downLine")!=null){
			   			var arrForFitView=new Array(); 
				   	    arrForFitView.push("downLine"); 
				   	    mapObj.setFitview(arrForFitView);
	   				} 
	   			}
			}
		});
	}

  /**
   * Array unique function,同时将去掉null及undefined
   * @param {Array} ary 需要进行unique的数组.
   * @return {Array} 返回经过去重的新的数组，
   * 不会修改原来的数组内容.
   */
  function unique(ary) {
      var i = 0,
          gid='_'+(+new Date)+Math.random(),
          objs = [],
          hash = {
              'string': {},
              'boolean': {},
              'number': {}
          }, p, l = ary.length,
          ret = [];
      for (; i < l; i++) {
          p = ary[i];
          if (p == null) continue;
          tp = typeof p;
          if (tp in hash) {
              if (!(p in hash[tp])) {
                  hash[tp][p] = 1;
                  ret.push(p);
              }
          } else {
              if (p[gid]) continue;
              p[gid]=1;
              objs.push(p);
              ret.push(p);
          }
      }
      for(i=0,l=objs.length;i<l;i++) {
          p=objs[i];
          p[gid]=undefined;
          delete p[gid];
      }
      return ret;
  }

  /*
  * SID为空时，是点击地图右上查询按钮。SID不为空时则是页面初始化或点击查询单选按钮进行查询
  */
  function initMapEditList(sid) {
		var startTime=document.getElementById("start_time").value;
		var endTime=document.getElementById("end_time").value;
		var inout_flag=formatSpecialChar(document.getElementById("showStation").value);
		var route_id=jQuery('#route_id').val();
		//取得单选按钮选择状态
		var control_station=0;//jQuery('input:radio[name="routeCheck"]:checked').val();

		//显示线路站点状态
		var site_flag='';
		if(jQuery("#showRouteStation").attr("checked")){
			site_flag='1';
		}

		var addstationids="";
		var delstationids="";
		
		if(control_station=='0'){
			addstationids=document.getElementById("upappendstation").value;
			delstationids=document.getElementById("updeletestation").value;
		}else{
			addstationids=document.getElementById("downappendstation").value;
			delstationids=document.getElementById("downdeletestation").value;
		}
		mapObj.removeAllOverlays();
		DWREngine.setAsync(false);
		//按条件查询站点
		RouteStationLONLAT.getMapEditList(startTime,endTime,inout_flag,route_id,control_station,site_flag,addstationids,delstationids,{
			callback : function(data) {
		  	 	var arr = new Array();
		  	 	for(var i=0;i<data.length;i++){
					if(data[i].LONGITUDE >=0 && data[i].LONGITUDE <=180 && data[i].LATITUDE>=0 && data[i].LATITUDE <=90){
						var markerOption=new MMarkerOptions();
						  if(data[i].SITE_FLAG=='0'){
							  markerOption.imageUrl="../images/map/station1.gif";
							  //markerOption.imageSize=new MSize(30,30);
						  }else{
							  markerOption.imageUrl="../images/map/station2.gif"; 
							  //markerOption.imageSize=new MSize(30,30);
						  }
						  markerOption.picAgent=false;
						  if(data[i].CONTROL_STATION=='0'){
				   	   		  markerOption.tipOption = addDataToTipContent("",data[i].CONTROL_STATION," ",data[i].STATION_ADDR,data[i].sichen_addr); //yg 2012-11-29
				   	   		  markerOption.labelOption=addMarkerLabel(data[i].STATION_ADDR,MConstants.BOTTOM_CENTER);
						  }else{
							  markerOption.tipOption = addDataToTipContent("",data[i].CONTROL_STATION," ",data[i].STATION_ADDR,data[i].sichen_addr); //yg 2012-11-29
							  markerOption.labelOption=addMarkerLabel(data[i].STATION_ADDR,MConstants.BOTTOM_CENTER);
						  }
				   	   	  var Marker = new MMarker(new MLngLat(data[i].LONGITUDE,data[i].LATITUDE),markerOption);   
					   	 	//flash版本设置的属性
							markerOption.imageAlign=MConstants.BOTTOM_CENTER;
							markerOption.isBounce=false;
							markerOption.isEditable=false;
							markerOption.canShowTip= false;
							markerOption.rotation=0;
							Marker.id=data[i].ID; 
					   	   	arr.push(Marker);
					}
				}
				if(arr.length!=0){
					// 批量添加标点
				   mapObj.addOverlays(arr,true);  
				   var mks = mapObj.getOverlaysByType(MOverlay.TYPE_MARKER);
				   if(mks.length>0){
						for(var j = 0; j < mks.length; j++){
							mapObj.addEventListener(mks[j],MConstants.MOUSE_CLICK,clickMap);
							
							mapObj.addEventListener(mks[j], MConstants.MOUSE_OVER,addFlashTip);
						}
				   }
				}
			}
		});

		var upString="",downString="";
		for (var i = 0; i < document.getElementById("selectLeftos").length; i++){
			if(i==document.getElementById("selectLeftos").length-1){
				upString += document.getElementById("selectLeftos").options[i].value;
			}else{
				upString += document.getElementById("selectLeftos").options[i].value+",";
			}
		}
		/* for (var i = 0; i < document.getElementById("selectRightos").length; i++){
			if(i==document.getElementById("selectRightos").length-1){
				downString += document.getElementById("selectRightos").options[i].value;
			}else{
				downString += document.getElementById("selectRightos").options[i].value+",";
			}
		} */
 		
		//上行线路站点数据
			jQuery.ajax({
		   		  type: 'POST',  
		   		  url: '../routeGrid/stationUpList.shtml?upSiteStrings=' + upString,	
		   		  async:false,
		   		  data: {route_id: jQuery('#route_id').val(),
						 addStations:document.getElementById("upappendstation").value,
						 deleteStations:document.getElementById("updeletestation").value},	
		   		  success: function(data) {
					   	//点击地图右上查询，当为下行线路时不进行如下操作
			   			if(sid=='1'||control_station=='0'){
				   			jQuery('#selectLeftos option').remove();
					   		for(var i=0;i<data.length;i++){
					   			if(data[i].deleteflag=="0"){
					   				jQuery("#selectLeftos").append("<option value='"+data[i].site_id+"'  title='双击删除'>"+data[i].site_name+"</option>");
								}else{
									jQuery("#selectLeftos").append("<option value='"+data[i].site_id+"'  title='双击删除' disabled>"+data[i].site_name+"</option>");
								}
								//标注上行的站点
					   			if(control_station=='0'){
						   			var mkedit = mapObj.getOverlayById(data[i].site_id);
							   		if(mkedit!=null){
							   			if(jQuery("#selectLeftos option").length<=1){
											var arreditline = new Array();
											arreditline.push(new MLngLat(mkedit.lnglat.lngX,mkedit.lnglat.latY));
										    var linestyle=new MLineStyle();//创建线样式对象 
										    linestyle.thickness=3;//线的粗细度，默认为2 
										    linestyle.color=0xff0000;//线的颜色，16进制整数，默认为0x005890（蓝色） 
										    var lineopt=new MLineOptions();//构造一个名为lineopt的线选项对象 
										    lineopt.lineStyle=linestyle;//设置线的边缘风格 
										    
										    lineopt.tipOption = null;
										    lineopt.canShowTip = false;
										    
										    var polyline=new MPolyline(arreditline,lineopt);//通过经纬度坐标数组及参数选项构建多折线对象，arr是经纬度坐档数组 
										    polyline.id="upLine";//对象编号，也是对象的唯一标识 
										    mapObj.addOverlay(polyline,true);//向地图添加覆盖物 
										}else{
											var arrseditline = mapObj.getOverlayById("upLine");
											arrseditline.lnglatArr.push(new MLngLat(mkedit.lnglat.lngX,mkedit.lnglat.latY));
											mapObj.updateOverlay(arrseditline);
										}
								   	}
							   	}
							}
				   			
					   		if(control_station=='0'){
						   		jQuery("#selectLeftos option").each(function(index){
									var orderMarker = mapObj.getOverlayById(jQuery(this).val());
									if(orderMarker!=null){
										orderMarker.option.labelOption.content="【"+Number(index+1)+"】 "+getPointSAByObj(orderMarker,"initDirection")+getPointSAByObj(orderMarker,"initLaber");
										mapObj.updateOverlay(orderMarker);
									}
								}); 
					   		}
			   			}
				   		if(mapObj.getOverlayById("upLine")!=null){
				   			var arrForFitView=new Array(); 
					   	    arrForFitView.push("upLine"); 
					   	    mapObj.setFitview(arrForFitView);
		   				} 
				  }  
		   	});
		

		//下行线路站点数据
		jQuery.ajax({
	   		  type: 'POST',  
	   		  url: '../routeGrid/stationDownList.shtml?downSiteStrings=' + downString,		
	   		  async:false,
	   		  data: {route_id: jQuery('#route_id').val(),
			 		 addStations:document.getElementById("downappendstation").value,
			 		 deleteStations:document.getElementById("downdeletestation").value},		
	   		  success: function(data) {
		   			if(sid=='1'||control_station=='1'){
		   				/* jQuery('#selectRightos option').remove();
			   			for(var i=0;i<data.length;i++){
			   				if(data[i].deleteflag=="0"){
				   				jQuery("#selectRightos").append("<option value='"+data[i].site_id+"' title='双击删除'>"+data[i].site_name+"</option>");
							}else{
								jQuery("#selectRightos").append("<option value='"+data[i].site_id+"' title='双击删除' disabled>"+data[i].site_name+"</option>");
							}
					   		if(control_station=='1'){
						   		var mkdedit = mapObj.getOverlayById(data[i].site_id);
							   	if(mkdedit!=null){
						   			if(jQuery("#selectRightos option").length<=1){
										var arreditdownline = new Array();
										arreditdownline.push(new MLngLat(mkdedit.lnglat.lngX,mkdedit.lnglat.latY));
									    var linestyle=new MLineStyle();//创建线样式对象 
									    linestyle.thickness=3;//线的粗细度，默认为2 
									    linestyle.color=0xff0000;//线的颜色，16进制整数，默认为0x005890（蓝色） 
									    var lineopt=new MLineOptions();//构造一个名为lineopt的线选项对象 
									    lineopt.lineStyle=linestyle;//设置线的边缘风格 
									    
									    lineopt.tipOption = null;
									    lineopt.canShowTip = false;
									    
									    var polyline=new MPolyline(arreditdownline,lineopt);//通过经纬度坐标数组及参数选项构建多折线对象，arr是经纬度坐档数组 
									    polyline.id="downLine";//对象编号，也是对象的唯一标识 
									    mapObj.addOverlay(polyline,true);//向地图添加覆盖物 
									}else{
										var arrseditdownline = mapObj.getOverlayById("downLine");
										arrseditdownline.lnglatArr.push(new MLngLat(mkdedit.lnglat.lngX,mkdedit.lnglat.latY));
										mapObj.updateOverlay(arrseditdownline);
									}
							   	}
					   		}
						}
			   			if(control_station=='1'){
							jQuery("#selectRightos option").each(function(index){
								var orderDsMarker = mapObj.getOverlayById(jQuery(this).val());
								if(orderDsMarker!=null){
									orderDsMarker.option.labelOption.content="【"+Number(index+1)+"】 "+getPointSAByObj(orderDsMarker,"initDirection")+getPointSAByObj(orderDsMarker,"initLaber");
									mapObj.updateOverlay(orderDsMarker);
								}
							});
			   			} */
		   			}
		   			if(mapObj.getOverlayById("downLine")!=null){
		   				var arrForFitView=new Array(); 
				   	    arrForFitView.push("downLine"); 
				   	    mapObj.setFitview(arrForFitView); 
		   			} 
	   			
			  }
	   	});

		upString="";
		downString = "";
	}
  
  var tipOption=new MTipOptions();
  function addFlashTip(point){
	  var overlay = mapObj.getOverlayById(point.overlayId);
	  
      var fontstyle=new MFontStyle();
      fontstyle.size=14;
      fontstyle.color=0xFF0066;
      fontstyle.bold=false;

      var fontstyle1=new MFontStyle();
      fontstyle1.size=20;
      fontstyle1.color=0xFF0066;//内容字体颜色无效
      fontstyle1.bold=true;

      tipOption.tipType=MConstants.FLASH_BUBBLE_TIP;
      tipOption.title="<br>站点描述<br>";
      tipOption.content="<div>"+overlay.option.tipOption.content.split(",")[4]+"</div>";
      tipOption.tipAlign=MConstants.TOP_CENTER;
      tipOption.tipPosition=new MPoint(0,15);
      
      tipOption.hasShadow=true;
      tipOption.canShowTip=true;
      
      mapObj.openTip(overlay.lnglat,tipOption);
  }
    /*
	 * 地图注册点击事件
	 */
	
	function clickMap(event){
		var mk = mapObj.getOverlayById(event.overlayId);
		var k=0;
		var val=0;//jQuery('input:radio[name="routeCheck"]:checked').val();
		//判断选择是上行线路还是下行线路
		if(val=='0'){
			if(getPointSAByObj(mk,"controlStation")=='1'){
				 alert("只能添加上学站点！");
				 return false;
			}
			//循环判断所点击的站点是否被添加
			jQuery("select[@name=selectLeftos] option").each(function(index){
			   if(jQuery(this).val() == event.overlayId){
				   k=1;
				   alert("该站点已添加！");
				   return false;
			   }		   
			}); 
			//站点被添加则不执行
			if(k==0){
				jQuery("#selectLeftos").append("<option value='"+event.overlayId+"' title='双击删除'>"+getPointSAByObj(mk,"initLaber")+"</option>");
				if(jQuery("#selectLeftos option").length<=1){
					var arrline = new Array();
					arrline.push(new MLngLat(mk.lnglat.lngX,mk.lnglat.latY));
				    var linestyle=new MLineStyle();//创建线样式对象 
				    linestyle.thickness=3;//线的粗细度，默认为2 
				    linestyle.color=0xff0000;//线的颜色，16进制整数，默认为0x005890（蓝色） 
				    var lineopt=new MLineOptions();//构造一个名为lineopt的线选项对象 
				    lineopt.lineStyle=linestyle;//设置线的边缘风格 
				    
				    lineopt.tipOption = null;
				    lineopt.canShowTip = false;
				    
				    var polyline=new MPolyline(arrline,lineopt);//通过经纬度坐标数组及参数选项构建多折线对象，arr是经纬度坐档数组 
				    polyline.id="upLine";//对象编号，也是对象的唯一标识 
				    mapObj.addOverlay(polyline,false);//向地图添加覆盖物 
				}else{
					var arrsline = mapObj.getOverlayById("upLine");
					arrsline.lnglatArr.push(new MLngLat(mk.lnglat.lngX,mk.lnglat.latY));
					mapObj.updateOverlay(arrsline);
				}
				//循环上行线路列表
				jQuery("#selectLeftos option").each(function(index){
					var orderMarker = mapObj.getOverlayById(jQuery(this).val());
					if(orderMarker!=null){
	      				orderMarker.option.labelOption.content="【"+Number(index+1)+"】 "+getPointSAByObj(orderMarker,"initDirection")+getPointSAByObj(orderMarker,"initLaber");
	      				orderMarker.option.imageUrl="../images/map/station2.gif";  
	      				//orderMarker.option.imageSize=new MSize(30,30);
	      				orderMarker.option.rotation=360;
	          			mapObj.updateOverlay(orderMarker);
	          		}
				}); 
				//上行添加站点后的数据
				if(document.getElementById("upappendstation").value==""){
					document.getElementById("upappendstation").value = event.overlayId;
				}else{
					document.getElementById("upappendstation").value = document.getElementById("upappendstation").value+","+event.overlayId;
				}
		 		
				var updeletestr="";
				var upappendstr=""
		 		updeletestr=unique(document.getElementById("updeletestation").value.split(","));
		 		upappendstr=unique(document.getElementById("upappendstation").value.split(","));

		 		//去掉删除数据中被添加的数据
		 		for(var j=0;j<upappendstr.length;j++){
					for(var q=0;q<updeletestr.length;q++){
			      		if(updeletestr[q]==upappendstr[j]){
			      			updeletestr.splice(q,1);
				      	}
			      	}
				}
				if(typeof(updeletestr)!="undefined"){
					newstr=updeletestr.join(",");
				}
				document.getElementById("updeletestation").value=newstr;
			}
		}else{
			if(getPointSAByObj(mk,"controlStation")=='0'){
				 alert("只能添加放学站点！");
				 return false;
			}
			jQuery("select[@name=selectRightos] option").each(function(index){
			   if(jQuery(this).val() == event.overlayId){
				   k=1;
				   alert("该站点已添加！");
				   return false;
			   }		   
			}); 
			if(k==0){
				jQuery("#selectRightos").append("<option value='"+event.overlayId+"' title='双击删除'>"+getPointSAByObj(mk,"initLaber")+"</option>");
				if(jQuery("#selectRightos option").length<=1){
					var arrdownline = new Array();
					arrdownline.push(new MLngLat(mk.lnglat.lngX,mk.lnglat.latY));
				    var linestyle=new MLineStyle();//创建线样式对象 
				    linestyle.thickness=3;//线的粗细度，默认为2 
				    linestyle.color=0xff0000;//线的颜色，16进制整数，默认为0x005890（蓝色） 
				    var lineopt=new MLineOptions();//构造一个名为lineopt的线选项对象 
				    lineopt.lineStyle=linestyle;//设置线的边缘风格 
				    
				    lineopt.tipOption = null;
				    lineopt.canShowTip = false;
				    
				    var polyline=new MPolyline(arrdownline,lineopt);//通过经纬度坐标数组及参数选项构建多折线对象，arr是经纬度坐档数组 
				    polyline.id="downLine";//对象编号，也是对象的唯一标识 
				    mapObj.addOverlay(polyline,false);//向地图添加覆盖物 
				}else{
					var arrsdownline = mapObj.getOverlayById("downLine");
					arrsdownline.lnglatArr.push(new MLngLat(mk.lnglat.lngX,mk.lnglat.latY));
					mapObj.updateOverlay(arrsdownline);
				}
				jQuery("#selectRightos option").each(function(index){
					var orderMarker = mapObj.getOverlayById(jQuery(this).val());
					if(orderMarker!=null){
	      				orderMarker.option.labelOption.content="【"+Number(index+1)+"】 "+getPointSAByObj(orderMarker,"initDirection")+getPointSAByObj(orderMarker,"initLaber");
	      				orderMarker.option.imageUrl="../images/map/station2.gif";  
	      				//orderMarker.option.imageSize=new MSize(30,30);
	      				orderMarker.option.rotation=360;
	          			mapObj.updateOverlay(orderMarker);
	          		}
				}); 

				//下行添加站点后的数据
				if(document.getElementById("downappendstation").value==""){
					document.getElementById("downappendstation").value = event.overlayId;
				}else{
					document.getElementById("downappendstation").value = document.getElementById("downappendstation").value+","+event.overlayId;
				}
		 		
				var downdeletestr="";
				var downappendstr=""
				downdeletestr=unique(document.getElementById("downdeletestation").value.split(","));
		 		downappendstr=unique(document.getElementById("downappendstation").value.split(","));

		 		//去掉删除数据中被添加的数据
		 		for(var j=0;j<downappendstr.length;j++){
					for(var q=0;q<downdeletestr.length;q++){
			      		if(downdeletestr[q]==downappendstr[j]){
			      			downdeletestr.splice(q,1);
				      	}
			      	}
				}
				if(typeof(downdeletestr)!="undefined"){
					newstr=downdeletestr.join(",");
				}
				document.getElementById("downdeletestation").value=newstr;
			}
		}
	}

	function moveUpSelect(sl1) {
         if( sl1 == undefined || sl1 == null) return;
         for (var i = 0; i < sl1.length; i++){
            if (sl1.options[i].selected) {
            	if (jQuery.browser.msie && jQuery.browser.version < 7.0) {
	   	       		 if(sl1.options[i].getAttribute("disabled")){
	   	                	alert("已设置该线路的乘车规划，不能删除站点操作！");
	   	                	return false;
	   	             }
	   			}
   				var orderMarker = mapObj.getOverlayById(jQuery('#selectLeftos option:selected').val());
            	if(orderMarker!=null){
            		if(confirm("您确定要删除该站点吗？")){
	            		orderMarker.option.labelOption.content=getPointSAByObj(orderMarker,"initDirection")+getPointSAByObj(orderMarker,"initLaber");
	            		orderMarker.option.imageUrl="../images/map/station1.gif"; 
	            		//orderMarker.option.imageSize=new MSize(30,30);
	            		orderMarker.option.rotation=360;
	         			mapObj.updateOverlay(orderMarker);

	         			//上行删除站点后的数据
	         	        if(document.getElementById("updeletestation").value==""){
	         				document.getElementById("updeletestation").value = jQuery('#selectLeftos option:selected').val();
	         			}else{
	         				document.getElementById("updeletestation").value = document.getElementById("updeletestation").value+","+jQuery('#selectLeftos option:selected').val();
	         			}
	         	        var updeletestr="";
		   				var upappendstr=""
		   		 		updeletestr=unique(document.getElementById("updeletestation").value.split(","));
		   		 		upappendstr=unique(document.getElementById("upappendstation").value.split(","));
	
		   		 		//去掉删除数据中被添加的数据
		   		 		for(var q=0;q<updeletestr.length;q++){
		   		 			for(var j=0;j<upappendstr.length;j++){
		   			      		if(upappendstr[j]==updeletestr[q]){
		   			      			upappendstr.splice(j,1);
		   				      	}
		   			      	}
		   				}
		   				if(typeof(upappendstr)!="undefined"){
		   					newstr=upappendstr.join(",");
		   				}
		   				document.getElementById("upappendstation").value=newstr;

	                	jQuery('#selectLeftos option:selected').remove();    
	                	var arrsline = mapObj.getOverlayById("upLine");
	    				arrsline.lnglatArr.splice(i,1);
	    				mapObj.updateOverlay(arrsline);
            		}else{
    		   			return false;
    			   	}
                }
		   		
            }               
         }

        jQuery("#selectLeftos option").each(function(index){
 			var orderMarker = mapObj.getOverlayById(jQuery(this).val());
 			if(orderMarker!=null){
  				orderMarker.option.labelOption.content="【"+Number(index+1)+"】 "+getPointSAByObj(orderMarker,"initDirection")+getPointSAByObj(orderMarker,"initLaber");
      			mapObj.updateOverlay(orderMarker);
      		}
 		}); 
  		
    }

	function moveDownSelect(sl1) {
        if( sl1 == undefined || sl1 == null) return;
        for (var i = 0; i < sl1.length; i++){
           if (sl1.options[i].selected) {
	       	if (jQuery.browser.msie && jQuery.browser.version < 7.0) {
	       		 if(sl1.options[i].getAttribute("disabled")){
	                	alert("已设置该线路的乘车规划，不能删除站点操作！");
	                	return false;
	             }
			}
           	var orderMarker = mapObj.getOverlayById(jQuery('#selectRightos option:selected').val());
           	if(orderMarker!=null){
           		if(confirm("您确定要删除该站点吗？")){
	        		orderMarker.option.labelOption.content=getPointSAByObj(orderMarker,"initDirection")+getPointSAByObj(orderMarker,"initLaber");
	        		orderMarker.option.imageUrl="../images/map/station1.gif"; 
	        		//orderMarker.option.imageSize=new MSize(30,30); 
	        		orderMarker.option.rotation=360;
	     			mapObj.updateOverlay(orderMarker);
	     			
	     			//下行删除站点后的数据
	     	        if(document.getElementById("downdeletestation").value==""){
	     				document.getElementById("downdeletestation").value = jQuery('#selectRightos option:selected').val();
	     			}else{
	     				document.getElementById("downdeletestation").value = document.getElementById("downdeletestation").value+","+jQuery('#selectRightos option:selected').val();
	     			}

	     	        var downdeletestr="";
					var downappendstr=""
					downdeletestr=unique(document.getElementById("downdeletestation").value.split(","));
			 		downappendstr=unique(document.getElementById("downappendstation").value.split(","));

			 		//去掉删除数据中被添加的数据
			 		for(var q=0;q<downdeletestr.length;q++){
			 			for(var j=0;j<downappendstr.length;j++){
				      		if(downappendstr[j]==downdeletestr[q]){
				      			downappendstr.splice(j,1);
					      	}
				      	}
					}
					if(typeof(downappendstr)!="undefined"){
						newstr=downappendstr.join(",");
					}
					document.getElementById("downappendstation").value=newstr;
	     			
	            	jQuery('#selectRightos option:selected').remove();    
	            	var arrsline = mapObj.getOverlayById("downLine");
					arrsline.lnglatArr.splice(i,1);
					mapObj.updateOverlay(arrsline);
           		}else{
    	   			return false;
    		   	}
            }
           }              
        }

 		/*var deletestation="";
 		var selectRightos=document.getElementById("selectRightos");
		for(var j=0;j<selectRightos.length;j++) {
			  if(deletestation==""){
				 deletestation = selectRightos.options[j].value;
			  }else{
				 deletestation = deletestation+","+selectRightos.options[j].value;
			  }
		}*/

		jQuery("#selectRightos option").each(function(index){
			var orderMarker = mapObj.getOverlayById(jQuery(this).val());
			if(orderMarker!=null){
  				orderMarker.option.labelOption.content="【"+Number(index+1)+"】 "+getPointSAByObj(orderMarker,"initDirection")+getPointSAByObj(orderMarker,"initLaber");
      			mapObj.updateOverlay(orderMarker);
      		}
		}); 
 		
   }

	function doUp(){
        var index = jQuery('#selectLeftos option').index(jQuery('#selectLeftos option:selected:first'));
        if (jQuery.browser.msie && jQuery.browser.version < 7.0) {
      		 if(document.getElementById('selectLeftos').options[index].getAttribute("disabled")){
               	alert("已设置该线路的乘车规划，不能改变站点顺序！");
               	return false;
            }
		}
        var $recent = jQuery('#selectLeftos option:eq('+(index-1)+')');
        var arrsline = mapObj.getOverlayById("upLine");
        var mk = mapObj.getOverlayById(jQuery('#selectLeftos option:selected').val());
        if(mk==null) return false;
        if(index>0){
           var $options = jQuery('#selectLeftos option:selected').remove();
           arrsline.lnglatArr.splice(index,1);
           arrsline.lnglatArr.splice(index-1,0,new MLngLat(mk.lnglat.lngX,mk.lnglat.latY));
           setTimeout(function(){
             $recent.before($options);
             mapObj.updateOverlay(arrsline);
             jQuery("#selectLeftos option").each(function(index){
      			var orderMarker = mapObj.getOverlayById(jQuery(this).val());
      			if(orderMarker!=null){
      				orderMarker.option.labelOption.content="【"+Number(index+1)+"】 "+getPointSAByObj(orderMarker,"initDirection")+getPointSAByObj(orderMarker,"initLaber");
          			mapObj.updateOverlay(orderMarker);
          		}
      		}); 
           },10);
        }
        
	}
	
	function doDown(){
	   var index = jQuery('#selectLeftos option').index(jQuery('#selectLeftos option:selected:last'));
	   if (jQuery.browser.msie && jQuery.browser.version < 7.0) {
    		 if(document.getElementById('selectLeftos').options[index].getAttribute("disabled")){
             	alert("已设置该线路的乘车规划，不能改变站点顺序！");
             	return false;
          }
		}
       var len = jQuery('#selectLeftos option').length-1;
       var $recent = jQuery('#selectLeftos option:eq('+(index+1)+')');
       var arrsline = mapObj.getOverlayById("upLine");
       var mk = mapObj.getOverlayById(jQuery('#selectLeftos option:selected').val());
       if(mk==null) return false;
       if(index<len ){
	        var $options = jQuery('#selectLeftos option:selected').remove();
	        arrsline.lnglatArr.splice(index,1);
	        arrsline.lnglatArr.splice(index+1,0,new MLngLat(mk.lnglat.lngX,mk.lnglat.latY));
			setTimeout(function(){
			 $recent.after($options);
             mapObj.updateOverlay(arrsline);
             jQuery("#selectLeftos option").each(function(index){
     			var orderMarker = mapObj.getOverlayById(jQuery(this).val());
     			if(orderMarker!=null){
      				orderMarker.option.labelOption.content="【"+Number(index+1)+"】 "+getPointSAByObj(orderMarker,"initDirection")+getPointSAByObj(orderMarker,"initLaber");
          			mapObj.updateOverlay(orderMarker);
          		}
     		});
			},10);
       }
	}

	function doUpDs(){
        var index = jQuery('#selectRightos option').index(jQuery('#selectRightos option:selected:first'));
        if (jQuery.browser.msie && jQuery.browser.version < 7.0) {
     		 if(document.getElementById('selectRightos').options[index].getAttribute("disabled")){
              	alert("已设置该线路的乘车规划，不能改变站点顺序！");
              	return false;
           }
		}
        var $recent = jQuery('#selectRightos option:eq('+(index-1)+')');
        var arrsline = mapObj.getOverlayById("downLine");
        var mk = mapObj.getOverlayById(jQuery('#selectRightos option:selected').val());
        if(mk==null) return false;
        if(index>0){
           var $options = jQuery('#selectRightos option:selected').remove();
           arrsline.lnglatArr.splice(index,1);
           arrsline.lnglatArr.splice(index-1,0,new MLngLat(mk.lnglat.lngX,mk.lnglat.latY));
           setTimeout(function(){
             $recent.before($options);
             mapObj.updateOverlay(arrsline);
             jQuery("#selectRightos option").each(function(index){
      			var orderMarker = mapObj.getOverlayById(jQuery(this).val());
      			if(orderMarker!=null){
      				orderMarker.option.labelOption.content="【"+Number(index+1)+"】 "+getPointSAByObj(orderMarker,"initDirection")+getPointSAByObj(orderMarker,"initLaber");
          			mapObj.updateOverlay(orderMarker);
          		}
      		}); 
           },10);
        }
	}
	
	function doDownDs(){
	   var index = jQuery('#selectRightos option').index(jQuery('#selectRightos option:selected:last'));
	   if (jQuery.browser.msie && jQuery.browser.version < 7.0) {
    		 if(document.getElementById('selectRightos').options[index].getAttribute("disabled")){
             	alert("已设置该线路的乘车规划，不能改变站点顺序！");
             	return false;
          }
		}
       var len = jQuery('#selectRightos option').length-1;
       var $recent = jQuery('#selectRightos option:eq('+(index+1)+')');
       var arrsline = mapObj.getOverlayById("downLine");
       var mk = mapObj.getOverlayById(jQuery('#selectRightos option:selected').val());
       if(mk==null) return false;
       if(index<len ){
	        var $options = jQuery('#selectRightos option:selected').remove();
	        arrsline.lnglatArr.splice(index,1);
	        arrsline.lnglatArr.splice(index+1,0,new MLngLat(mk.lnglat.lngX,mk.lnglat.latY));
			setTimeout(function(){
			 $recent.after($options);
             mapObj.updateOverlay(arrsline);
             jQuery("#selectRightos option").each(function(index){
     			var orderMarker = mapObj.getOverlayById(jQuery(this).val());
     			if(orderMarker!=null){
      				orderMarker.option.labelOption.content="【"+Number(index+1)+"】 "+getPointSAByObj(orderMarker,"initDirection")+getPointSAByObj(orderMarker,"initLaber");
          			mapObj.updateOverlay(orderMarker);
          		}
     		}); 
			},10);
       }
	}

	/**
	 * 【将业务属性添加到对应点覆盖物的tip_content中】
	 */
	function addDataToTipContent(content,controlStation,initDirection,initLaber,addr){
		var tipOption = new MTipOptions();
	    //将业务数据保存在对应点的MTipOptions的content中
		//说明：各字段以"英文逗号"分隔,第一位预留
	    tipOption.tipType = MConstants.HTML_CUSTOM_TIP;
	    tipOption.content = content+","+controlStation+","+initDirection+","+initLaber+","+(addr==null?"无":addr);
	    return tipOption;
	}

	/**
	 * 获取点的业务属性(根据点对象)
	 * get point Service Attribute
	 * mk-->点覆盖物的对象
	 * Attribute-->属性值
	 */
	function getPointSAByObj(mk,attribute){
		//var mk = mapObj.getOverlayById(overlayId);
		var tip_content = mk.option.tipOption.content;
		//拆分tip_content
		var data_array= new Array();
		data_array=tip_content.split(",");
		//根据属性值 返回对应的属性值
		switch(attribute){
			case "content": return data_array[0];break;
			case "controlStation": return data_array[1];break;
			case "initDirection": return data_array[2];break;
			case "initLaber": return data_array[3];break;
			default: return "";
		}
	}

	 /**
	 * 获取点的业务属性(根据点id)
	 * get point Service Attribute
	 * overlayId-->点覆盖物的ID
	 * Attribute-->属性值
	 */
	function getPointSAById(overlayId,attribute){
		var mk = mapObj.getOverlayById(overlayId);
		var tip_content = mk.option.tipOption.content;
		//拆分tip_content
		var data_array= new Array();
		data_array=tip_content.split(",");
		//根据属性值 返回对应的属性值
		switch(attribute){
			case "content": return data_array[0];break;
			case "controlStation": return data_array[1];break;
			case "initDirection": return data_array[2];break;
			case "initLaber": return data_array[3];break;
			default: return "";
		}
	}
	
function init(){
	if (jQuery.browser.msie && jQuery.browser.version < 7.0) {
		document.getElementById("mapbartab").style.width='610px';
	}else{
		document.getElementById("mapbartab").style.width='600px';
		jQuery("#start_time").attr("size",'24');
		jQuery("#end_time").attr("size",'24');
	}
	mapEditInit();	
}
	/** 线路编码必填项 **/
	function checkRouteCode() {
		var route_code = $('route_code');
		if (!Mat.checkRequired(route_code))
			return false;
		if (!Mat
				.checkLength(
						route_code,
						32,
						'<s:text name="routeinfo.route_code"/>' + '<s:text name="val.feild.overlength"/>' + '32'))
			return false;
		return true;
	}

	/** 线路编码必填项 **/
	function checkRouteName() {
		var route_name = $('route_name');
		if (!Mat.checkRequired(route_name))
			return false;
		if (!Mat.checkLength(route_name,32,'<s:text name="routeinfo.route_name"/>' + '<s:text name="val.feild.overlength"/>' + '32'))
			return false;
		var s=jQuery('#route_name').val();
		var ss = s.substr(s.length-1, s.length); // 获取子字符串。
		var patrn=/^[0-9]*$/;  
		if (patrn.exec(ss)){
			Wit.showErr(route_name, "不能数字结尾");
			return false;
		}
		//jQuery('#route_name').val(formatSpecialCharreplce(jQuery('#route_name').val()));
		return true;
	}

	/**所属部门必填**/
	function checkOrganizationName() {
		var organization_name = $('route_organization_name');
		if (!Mat.checkRequired(organization_name))
			return false;
		return true;
	}

	/**所属部门必填**/
	function checkshort_allname() {
		var organization_name = $('short_allname');
		if (!Mat.checkRequired(organization_name))
			return false;
		return true;
	}
	/**线路负责人必填**/
	function checkInchargePerson(){
		var incharge_person = $('route_incharge_person');
		//if (!Mat.checkRequired(incharge_person))
			//return false;
		if (!Mat.checkLength(
						incharge_person,
						32,
						'<s:text name="routeinfo.route_incharge_person"/>' + '<s:text name="val.feild.overlength"/>' + '32'))
			return false;
		return true;
	}

	/** 检查线路编号唯一性 **/
	 function checkRouteCodeUnique(){
		var oldcode=$('oldroutecode');
	    var tmCodeObj = $('route_code');
	    if(oldcode.value != tmCodeObj.value){
	    	var enid=$('enid');
	 	    DWREngine.setAsync(false);
	 	    var ret = false; 
	 	    Route.checkRouteCodeUnique(tmCodeObj.value,enid.value, callBackFun);
	 	    DWREngine.setAsync(true);
	 	    function callBackFun(data)
	 	    {
	 	      ret = data;
	 	    }

	 	    if(ret) {
	 	      Wit.showErr(tmCodeObj, "<s:property value="getText('routeinfo.code.exist')" />");
	 	      return false;
	 	    } else {
	 	      return true;
	 	    }  
	    }else{
	    	 return true;
	    }
	   
	  }
	  
	/**验证手机号码**/
	 function checkCONTACT_TEL(){
			var phone=$('route_phone');
	        //if (!Mat.checkRequired(phone))
				//return false;
	        if (''==phone.value){
	        	Wit.showErr(phone, "<s:property value="getText('')"/>");
	        	return true;//允许为空
	        }
			if(phone.value!=''){
				if(!Mat.checkTelePhone(phone)){
					Wit.showErr(phone, "<s:property value="getText('手机号不合法')"/>");
				    return false;
				}
			}
			return true;
		}

    /**验证EMAIL**/
    function checkEMAIL(){
	var email=$('route_email');
	if(email.value!=''){
		if(!Mat.checkEmail(email)){
			Wit.showErr(email, "<s:property value="getText('routeinfo.email.succ')"/>");
		    return false;
		}
	}
	return true;
  }

    function checkTelphone(){
        var telphone=$('route_telphone');
        var reg=/^\d{3,4}-?\d{7,8}|\d{7,8,11}$/;
        if (!Mat.checkRequired(telphone))
			return false;
        if(telphone.value!=''){
            if(!reg.test(telphone.value)){
            	Wit.showErr(telphone, "<s:property value="getText('routeinfo.telphone.err')"/>");
    		    return false; 
            }
        }
        return true;
    }
	
	/**上学站点必填**/
	function checkleftselect() {
		var selectLeftos = $('selectLeftos');
 		if(selectLeftos.length <= 0){
		    Wit.showErr($('Lefttag'), "<s:property value="getText('msg.check.required')" />");
			return false;
		}
 		Wit.showSucc($('Lefttag'),Mat.succMsg);
 		
		return true;
	}

	/**放学站点必填**/
	function checkrightselect() {
		var selectRightos = $('selectRightos');
		if(selectRightos.length <= 0){
		    Wit.showErr($('Righttag'), "<s:property value="getText('msg.check.required')" />");
			return false;
		}
 		Wit.showSucc($('Righttag'),Mat.succMsg);
		return true;
	}
    
	/** 加载事件 **/
	function setFormEvent() {
		$('route_name').onkeyup = checkRouteName;
		$('route_name').onblur=checkRouteName;
		$('route_phone').onkeyup = checkCONTACT_TEL;
		$('route_phone').onblur = checkCONTACT_TEL;
	}

	// add by jinp begin 20120815
	function checkMessageInput() {
	    var elm = $('route_remark');
	    if(elm.value != "") {
	      if(!Mat.checkLength(elm,40,'备注长度应小于40')) {
	        return false;
	      } else {
	        Mat.showSucc(elm);
	        return true;
	      }
		} else {
	      return true;
		}
  }
	
	
	
	//判断线路名称的唯一性
	 function checkRouteNameUnique(){
		var oldcode=$('oldroutename');
	    var tmCodeObj = $('route_name');
	    if(oldcode.value != tmCodeObj.value){
	    	var enid=$('enid');
	 	    DWREngine.setAsync(false);
	 	    var ret = false;
	 	   var entid_v = '<s:property value="#session.adminProfile.entiID" />';
	 	   //alert(entid_v);   
	 	   Route.checkRouteNameUnique(tmCodeObj.value,entid_v, callBackFun);
	 	    DWREngine.setAsync(true);
	 	    function callBackFun(data)
	 	    {
	 	      ret = data;
	 	    }
			
	 	    if(ret) {
	 	      Wit.showErr(tmCodeObj, "名称已存在！");
	 	      return false;
	 	    } else {
	 	      return true;
	 	    }  
	    }else{
	    	 return true;
	    }
	   
	  }
	
	// add by jinp begin 20120815
	function submitForm() {
		trimAllObjs();
		document.getElementById("site_up_string").value="";
		//document.getElementById("site_down_string").value="";
		var f1=  checkRouteName();
		var f2=  checkOrganizationName();
		var f3= checkInchargePerson();
		var f5= checkCONTACT_TEL();
		/*var f6=checkUpstartTime();
		var f7=checkUpendTime();
		var f8=checkDownStartTime();
		var f9=checkDownEndTime();*/
		var f10=checkleftselect();
		var f11=checkRouteNameUnique();
		//var f11=checkrightselect();
		/*$('upstarttime').value=$('upstarttimetext').value;
		$('upendtime').value=$('upendtimetext').value;
		$('downstarttime').value=$('downstarttimetext').value;
		$('downendtime').value=$('downendtimetext').value;*/
		var f12 = checkMessageInput();
		for (var i = 0; i < document.getElementById("selectLeftos").length; i++){
			if(i==document.getElementById("selectLeftos").length-1){
				document.getElementById("site_up_string").value += document.getElementById("selectLeftos").options[i].value;
			}else{
				document.getElementById("site_up_string").value += document.getElementById("selectLeftos").options[i].value+",";
			}
		}
		/* for (var i = 0; i < document.getElementById("selectRightos").length; i++){
			if(i==document.getElementById("selectRightos").length-1){
				document.getElementById("site_down_string").value += document.getElementById("selectRightos").options[i].value;
			}else{
				document.getElementById("site_down_string").value += document.getElementById("selectRightos").options[i].value+",";
			}
		} */
		if (f1&&f2&&f3&&f5&&f10&&f11&&f12) {
			var data = {
				"routeInfo.route_enterprise_id":jQuery("#enid").val(),
				"routeInfo.site_up_string":jQuery("#site_up_string").val(),
				"routeinfo.route_organization_id":jQuery("#route_organization_id").val(),
				"routeinfo.route_name":jQuery("#route_name").val(),
				"routeinfo.route_incharge_person":jQuery("#route_incharge_person").val(),
				"routeInfo.route_phone":jQuery("#route_phone").val(),
				"routeInfo.route_remark":jQuery("#route_remark").val()
			};
			ajaxSubmit("../route/route_add2.shtml");
			//Wit.commitAll($('add_routeform'));
		} else {
			return false;
		}
	}

	function submitalterForm(){
		trimAllObjs();
		// add by jinp begin 20120815
		document.getElementById("site_up_string").value = "";
		document.getElementById("site_down_string").value = "";
		// add by jinp begin 20120815
		
		// add by jinp begin 20120813
		var configFlag = document.getElementById("configFlag").value;
		/* if("1" == configFlag) {
			alert("该线路已关联乘车规划，无法修改!");
			return false;
		} */
		// add by jinp end 20120813
		
		var f1=  checkRouteName();
		var f2=  checkshort_allname();
		var f3= checkInchargePerson();
		var f5= checkCONTACT_TEL();

		/*var f6=checkUpstartTime();
		var f7=checkUpendTime();
		var f8=checkDownStartTime();
		var f9=checkDownEndTime();*/
		var f10=checkleftselect();
		var f11=checkRouteNameUnique();
		//var f11=checkrightselect();
		var f12 = checkMessageInput();
		/*$('upstarttime').value=$('upstarttimetext').value;
		$('upendtime').value=$('upendtimetext').value;
		$('downstarttime').value=$('downstarttimetext').value;
		$('downendtime').value=$('downendtimetext').value;*/
		for (var i = 0; i < document.getElementById("selectLeftos").length; i++){
			if(i==document.getElementById("selectLeftos").length-1){
				document.getElementById("site_up_string").value += document.getElementById("selectLeftos").options[i].value;
			}else{
				document.getElementById("site_up_string").value += document.getElementById("selectLeftos").options[i].value+",";
			}
		}
		/* for (var i = 0; i < document.getElementById("selectRightos").length; i++){
			if(i==document.getElementById("selectRightos").length-1){
				document.getElementById("site_down_string").value += document.getElementById("selectRightos").options[i].value;
			}else{
				document.getElementById("site_down_string").value += document.getElementById("selectRightos").options[i].value+",";
			}
		} */
		if (f1&&f2&&f3&&f5&&f10&&f11&&f12) {
			var data = {
				"routeInfo.route_enterprise_id":jQuery("#enid").val(),
				"routeInfo.site_up_string":jQuery("#site_up_string").val(),
				"routeinfo.route_organization_id":jQuery("#route_organization_id").val(),
				"routeinfo.route_name":jQuery("#route_name").val(),
				"routeinfo.route_incharge_person":jQuery("#route_incharge_person").val(),
				"routeInfo.route_phone":jQuery("#route_phone").val(),
				"routeInfo.route_remark":jQuery("#route_remark").val()
			};
			ajaxSubmit2("../route/route_edit2.shtml");
			//Wit.commitAll($('alter_routeform'));
		} else {
			return false;
		}
	}
	 
	function goBack(url) {
		Wit.goBack(url);
	}
	function golistpage() {
		window.location.href = "../route/routemanage.shtml";
	}
	function ajaxSubmit(url){
		//增加早班晚班状态
		var data = {
			"routeInfo.route_id":jQuery("#route_id").val(),
			"routeInfo.route_enterprise_id":jQuery("#enid").val(),
			"routeInfo.site_up_string":jQuery("#site_up_string").val(),
			"routeInfo.configFlag":jQuery("#configFlag").val(),
			"routeInfo.route_organization_id":jQuery("#route_organization_id").val(),
			"routeInfo.route_name":jQuery("#route_name").val(),
			"routeInfo.route_incharge_person":jQuery("#route_incharge_person").val(),
			"routeInfo.route_phone":jQuery("#route_phone").val(),
			"routeInfo.route_remark":jQuery("#route_remark").val(),
			"routeInfo.route_class":jQuery('#route_class').val()
		};
		
		jQuery.post(url,data,function(v) {
			if(v == 'success') {
				//alert("线路保存成功");
				//jQuery("#mainFrameAllPage").load("route/routemanage.shtml");
				window.location.href = "../route/routemanage.shtml?message=1";
			} else {
				alert("线路保存失败");
			}
			
		})
	}
	function ajaxSubmit2(url){
		//增加早班晚班状态
		var data = {
			"routeInfo.route_id":jQuery("#route_id").val(),
			"routeInfo.route_enterprise_id":jQuery("#enid").val(),
			"routeInfo.site_up_string":jQuery("#site_up_string").val(),
			"routeInfo.configFlag":jQuery("#configFlag").val(),
			"routeInfo.route_organization_id":jQuery("#route_organization_id").val(),
			"routeInfo.route_name":jQuery("#route_name").val(),
			"routeInfo.route_incharge_person":jQuery("#route_incharge_person").val(),
			"routeInfo.route_phone":jQuery("#route_phone").val(),
			"routeInfo.route_remark":jQuery("#route_remark").val(),
			"routeInfo.route_class":jQuery('#route_class').val()
		};
		
		jQuery.post(url,data,function(v) {
			if(v == 'success') {
				//alert("线路保存成功");
				//jQuery("#mainFrameAllPage").load("route/routemanage.shtml");
				window.location.href = "../route/routemanage.shtml?message=2";
			} else {
				alert("线路保存失败");
			}
			
		})
	}
	  /** 地图初始化 **/
	  function mapInit() {
	    var mapOptions = new MMapOptions();//构建地图辅助类   
	    mapOptions.zoom=10;//要加载的地图的缩放级别   
	    //mapOptions.center=new MLngLat(116.397428,39.90923);//要加载的地图的中心点经纬度坐标
	    mapOptions.toolbar = MConstants.ROUND; //设置地图初始化工具条，ROUND:新版圆工具条   
	    mapOptions.toolbarPos = new MPoint(20,20); //设置工具条在地图上的显示位置   
	    mapOptions.overviewMap = MConstants.MINIMIZE; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）   
	    mapOptions.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。   
	    mapOptions.language = MConstants.MAP_CN;//设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图
		mapOptions.fullScreenButton = MConstants.HIDE;//设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏
		mapOptions.centerCross = MConstants.HIDE;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏
		mapOptions.requestNum=100;//设置地图切片请求并发数。默认100。
		mapOptions.isQuickInit=true;//设置是否快速显示地图，true显示，false不显示。	
		mapOptions.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标   		
		mapOptions.hasDefaultMenu=false;  //去掉鼠标右键	

		//地图右下角logo去掉
		mapOptions.logoUrl = "../newimages/sidelayerimages/mask.png";
		//背景图片
		mapOptions.groundLogo = "../newimages/sidelayerimages/mapbackgroud.jpg";
		var mlglat = new MLngLat("113.686","34.693",MConstants.COORD_TYPE_OFFSET);
		mapOptions.center = mlglat;
		mapObj=new MMap("iCenter",mapOptions); //地图初始化   
		mapObj.setKeyboardEnabled(false);
		mapObj.addEventListener(mapObj,MConstants.MAP_READY,map_ready);
	  }
	  /** 修改页面地图初始化 **/
	  function mapEditInit() {
	    var mapOptions = new MMapOptions();//构建地图辅助类   
	    mapOptions.zoom=10;//要加载的地图的缩放级别   
	    //mapOptions.center=new MLngLat(116.397428,39.90923);//要加载的地图的中心点经纬度坐标
	    mapOptions.toolbar = MConstants.ROUND; //设置地图初始化工具条，ROUND:新版圆工具条   
	    mapOptions.toolbarPos = new MPoint(20,20); //设置工具条在地图上的显示位置   
	    mapOptions.overviewMap = MConstants.MINIMIZE; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）   
	    mapOptions.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。   
	    mapOptions.language = MConstants.MAP_CN;//设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图
	    mapOptions.fullScreenButton = MConstants.HIDE;//设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏
		mapOptions.centerCross = MConstants.HIDE;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏
		mapOptions.requestNum=100;//设置地图切片请求并发数。默认100。
		mapOptions.isQuickInit=true;//设置是否快速显示地图，true显示，false不显示。	
		mapOptions.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标   		
		mapOptions.hasDefaultMenu=false;  //去掉鼠标右键	
		//地图右下角logo去掉
		mapOptions.logoUrl = "../newimages/sidelayerimages/mask.png";
		//背景图片
		mapOptions.groundLogo = "../newimages/sidelayerimages/mapbackgroud.jpg";
		
		var mlglat = new MLngLat("113.686","34.693",MConstants.COORD_TYPE_OFFSET);
		mapOptions.center = mlglat;
		
		mapObj=new MMap("iCenter",mapOptions); //地图初始化   
		mapObj.setKeyboardEnabled(false);
		mapObj.addEventListener(mapObj,MConstants.MAP_READY,map_edit_ready);
		//地图加载完成   添加下面的会导致线路中的站点显示不出来（左侧框中的站点）
		//mapObj.addEventListener(mapObj,MConstants.MAP_READY,map_ready);
		//mapObj.addEventListener(mapObj,MConstants.ZOOM_CHANGED,setStatus);
	  }
	//页面自适应
	  jQuery(function() {
	  	/* jQuery(window).mk_autoresize({
	  		min_width: 1256
	  	}); */
	  	auto_size();
	  	jQuery(window).mk_autoresize({
	  		height_include: '#content',
	  		height_exclude: ['#header', '#footer'],
	  		height_offset: 0,
	  		width_include: ['#header', '#content', '#footer'],
	  		width_offset: 0});
	  	auto_size();
	  	/* jQuery(document).wresize(function() {
	  		resizeBgDiv(1256,750);
	  	}); */
	  	
	  });
	window.addEvent('domready', setFormEvent);
	
	function TextValidate() {
	    var code;
	    var character;
	    if (document.all){ //判断是否是IE浏览器
	        code = window.event.keyCode;
	    }else{
	        code = arguments.callee.caller.arguments[0].which;
	    }
	    var character = String.fromCharCode(code);
	    
	    var txt=new RegExp("[ ,\\`,\\~,\\!,\\@,\#,\\$,\\%,\\^,\\+,\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\.,\\<,\\>,\\{,\\},\\(,\\),\\',\\;,\\=,\"]");
	    //特殊字符正则表达式
	    if (txt.test(character)) {
	        if (document.all){
	            window.event.returnValue = false;
	        }else{
	            arguments.callee.caller.arguments[0].preventDefault();
	        }
	    }
	} 
	function TextValidatereplace(obj) {
	    jQuery(obj).val(formatSpecialCharreplce(jQuery(obj).val()));
	}
</script>