var mapabcObjdiv = function(o){
	this.mapObjDiv = null;
	this.arrForFitView=new Array();
};

var jsGpsWhere = null;

mapabcObjdiv.prototype = {
	//初始化地图
	maiInit: function(mapId){
		var that = this;
		var mapOptions = new MMapOptions();//构建地图辅助类   
		mapOptions.zoom = 10;//要加载的地图的缩放级别   
		mapOptions.center = new MLngLat(113.686, 34.693);//要加载的地图的中心点经纬度坐标   
		//mapOptions.toolbar = MConstants.DEFAULT;//设置地图初始化工具条
		mapOptions.toolbar = MConstants.SMALL; //设置地图初始化工具条，ROUND:新版圆工具条   *
		mapOptions.isQuickInit=true;//设置是否快速显示地图，true显示，false不显示。
		mapOptions.toolbarPos = new MPoint(15, 15); //设置工具条在地图上的显示位置
		//mapOptions.overviewMap = SHOW; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认） 
		mapOptions.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏
		mapOptions.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标
		mapOptions.centerCross = MConstants.HIDE;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏
		//mapOptions.zoomBox = true;//鼠标滚轮缩放和双击放大时是否有红框动画效果。
		//地图右下角logo去掉
//		mapOptions.logoUrl = "../newimages/sidelayerimages/mask.png";
		//地图背景logo
//		mapOptions.groundLogo = "../newimages/sidelayerimages/mapbackgroud.jpg";
		if($("#isMapGroundLogo").val() == "0"){
			mapOptions.center = new MLngLat(113.686, 34.693);//要加载的地图的中心点经纬度坐标   
			mapOptions.groundLogo = "../newimages/sidelayerimages/mapbackgroud.jpg";
		} else {
			mapOptions.center = new MLngLat(117.08 , 36.11);//要加载的地图的中心点经纬度坐标   
			mapOptions.groundLogo = "../newimages/taian/mapbackgroud.jpg";
		}
		
		that.mapObjDiv = new MMap(mapId, mapOptions); //地图初始化
		that.mapObjDiv.setKeyboardEnabled(false);
	},		
	//lon,lat 均为数组  vin,mapId 唯一 orther为其它参数
	mapInitMorePoint2:function(lon,lat,vin,mapId,orther,mapArea) {
		var that = this;
		
		jsGpsWhere = true;
		
		var mapOptions = new MMapOptions();//构建地图辅助类   
		mapOptions.zoom = 10;//要加载的地图的缩放级别   
		mapOptions.center = new MLngLat(113.669379, 34.7522);//要加载的地图的中心点经纬度坐标   
		//mapOptions.toolbar = MConstants.DEFAULT;//设置地图初始化工具条   
		mapOptions.toolbar = MConstants.SMALL; //设置地图初始化工具条，ROUND:新版圆工具条   *
		mapOptions.toolbarPos = new MPoint(15, 15); //设置工具条在地图上的显示位置
		//mapOptions.overviewMap = SHOW; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认） 
		mapOptions.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏
		mapOptions.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标
		mapOptions.centerCross = MConstants.HIDE;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏
		//mapOptions.zoomBox = true;//鼠标滚轮缩放和双击放大时是否有红框动画效果。
		//mapOptions.logoUrl = "";
//		if(lon.length > 0){
//			mapOptions.zoom = 18;//要加载的地图的缩放级别   
//			mapOptions.center = new MLngLat(lon,lat);
//		}
//		if($("#isMapGroundLogo").val() == "0"){
//			mapOptions.center = new MLngLat(113.686, 34.693);//要加载的地图的中心点经纬度坐标   
//			mapOptions.groundLogo = "../newimages/sidelayerimages/mapbackgroud.jpg";
//		} else {
//			mapOptions.center = new MLngLat(117.08 , 36.11);//要加载的地图的中心点经纬度坐标   
//			mapOptions.groundLogo = "../newimages/taian/mapbackgroud.jpg";
//		}
		
		mapOptions.isQuickInit=true;//设置是否快速显示地图，true显示，false不显示。
		that.mapObjDiv = new MMap(mapId, mapOptions); //地图初始化
		
		that.mapObjDiv.setKeyboardEnabled(false);
		
		if($("#isMapGroundLogo").val() == "0"){
    		that.mapObjDiv.setZoomAndCenter(10,new MLngLat(113.686, 34.693));
    	} else if($("#isMapGroundLogo").val() == "1"){
    		that.mapObjDiv.setZoomAndCenter(10,new MLngLat(117.08 , 36.11));
    	}
		
		if(lon==null||lon.length!=lat.length||lat.length==0) {
			return ;
		} else {
		for(var i=0;i<lon.length;i++) {
			if(orther!=null&&orther!='') {
					that.showpointToFtlyInfo2(lon[i],lat[i],vin,orther[i],i,mapArea);
				} else {
					that.showpointToFtlyInfo2(lon[i],lat[i],vin,null,i,mapArea);
				};
			};
		};
	},
	//第二次添加 删除地图覆盖物  RemoveAllOverlays()
	showMapNoOverlays2:function (lon,lat,vehicle_ln,mapId,orther,mapArea) {
		var that = this;
		jsGpsWhere = true;
//		try{
			that.mapObjDiv.removeAllOverlays();
			if($("#isMapGroundLogo").val() == "0"){
	    		that.mapObjDiv.setZoomAndCenter(10,new MLngLat(113.686, 34.693));
	    	} else if($("#isMapGroundLogo").val() == "1"){
	    		that.mapObjDiv.setZoomAndCenter(10,new MLngLat(117.08 , 36.11));
	    	}
//		} catch(e){}
		if(lon==null||lon.length!=lat.length||lat.length==0){
			return;
		} else {
			for(var i=0;i<lon.length;i++) {
				if(orther!=null&&orther!='') {
					that.showpointToFtlyInfo2(lon[i],lat[i],vehicle_ln,orther[i],i,mapArea);
				} else {
					that.showpointToFtlyInfo2(lon[i],lat[i],vehicle_ln,null,i,mapArea);
				}
			}
			
		}
	},
	/*向地图上画点 加油为蓝色球  偷油为红色球*/
	showpointToFtlyInfo2:function (lon,lat,vehicle_ln,orther,i,mapArea){
		var that = this;
		//点的属性设置
		 var markerOption = new MMarkerOptions();
		//标注图片或SWF的url，默认为蓝色气球图片   
		 markerOption.picAgent=false;
		if(orther==null||orther=='')
			 markerOption.imageUrl="../images/map/alarm.png";
		else {
			if(orther=='010') {
			 markerOption.imageUrl="../images/map/addoil.png";
			}if(orther=='001')
			 markerOption.imageUrl="../newimages/arrow_red.png";
		}
		
		//图片锚点BOTTOM_CENTER相对于标注位置的位置
		 markerOption.imageAlign=MConstants.BOTTOM_CENTER;
		//设置图标旋转的角度
		markerOption.rotation=0;

		//是否在地图中显示信息窗口，true，可以显示（默认）；false，不显示
	    markerOption.canShowTip= true;
	    if((lon.length == 0 || lon == "FFFF") || (lat.length == 0 || lat == "FFFF")){
	    	return false;
		} else if((parseFloat(lon) < 73 || parseFloat(lon) > 137) || (parseFloat(lat) < 1 || parseFloat(lat) > 55)){
			return false;
		} else {
			that.mapObjDiv.setZoomAndCenter(17,new MLngLat(lon,lat));
		}
	    

	    
		//通过经纬度坐标及参数选项确定标注信息
		Mmarker = new MMarker(new MLngLat(lon,lat),markerOption);
		//设置点的标注参数选项
		//markerOption.labelOption=that.addMarkerLabel2('',MConstants.TOP_CENTER);; 
		//对象编号，也是对象的唯一标识   
		Mmarker.id=vehicle_ln+"-"+i+"-"+Math.round(Math.random() * 10000);
		//向地图添加覆盖物
		that.mapObjDiv.addOverlay(Mmarker,true);
		that.setFitVDiv2(Mmarker.id,that.arrForFitView);
		//onevmouse_click(Mmarker.id);
//		that.mapObjDiv.addEventListener(Mmarker,MConstants.MOUSE_CLICK,that.onevmouse_click2);
	},
	//地图标出车辆图片
	showpointToFtlyInfo3:function (lon,lat,vehicle_ln,orther,i,mapArea){
		var that = this;
		//删除所有点
		that.mapObjDiv.removeAllOverlays();
		
		jsGpsWhere = true;
		
		//点的属性设置
		 var markerOption = new MMarkerOptions();
		//标注图片或SWF的url，默认为汽车图片   
		 markerOption.picAgent=false;
//		if(orther==null||orther=='')
			 markerOption.imageUrl="../newimages/arrow_red.png";
//		else {
//			if(orther=='10') {
//			 markerOption.imageUrl="../images/map/addoil.png";
//			}if(orther=='01')
//			 markerOption.imageUrl="../images/map/reduceoil.png";
//		}
		
		//图片锚点BOTTOM_CENTER相对于标注位置的位置
		 markerOption.imageAlign=MConstants.BOTTOM_CENTER;
		//设置图标旋转的角度
		markerOption.rotation=orther;

		//是否在地图中显示信息窗口，true，可以显示（默认）；false，不显示
	    markerOption.canShowTip= true;
	    //地图放大级别
	    //markerOption.zoom = 18;
	    //地图坐标放大级别
	    if((lon.length == 0 || lon == "FFFF") || (lat.length == 0 || lat == "FFFF")){
	    	if(jsGpsWhere){
	    		jsGpsWhere = false;
	    	}
		} else if((parseFloat(lon) < 73 || parseFloat(lon) > 137) || (parseFloat(lat) < 1 || parseFloat(lat) > 55)){
			if(jsGpsWhere){
	    		jsGpsWhere = false;
	    	}
		} else {
			if(!jsGpsWhere){
	    		jsGpsWhere = true;
	    	}
			that.mapObjDiv.setZoomAndCenter(17,new MLngLat(lon,lat));
		}
	    
	    if($("#isMapGroundLogo").val() == "0" && !jsGpsWhere){
    		that.mapObjDiv.setZoomAndCenter(10,new MLngLat(113.686, 34.693));
    		return ;
    	} else if($("#isMapGroundLogo").val() == "1" && !jsGpsWhere){
    		that.mapObjDiv.setZoomAndCenter(10,new MLngLat(117.08 , 36.11));
    		return;
    	}
	    //markerOption.center = new MLngLat(lon,lat);
		//通过经纬度坐标及参数选项确定标注信息
		Mmarker = new MMarker(new MLngLat(lon,lat),markerOption);
		//设置点的标注参数选项
		//markerOption.labelOption=that.addMarkerLabel2('',MConstants.TOP_CENTER);
		//对象编号，也是对象的唯一标识   
		Mmarker.id=vehicle_ln+"-"+i+"-"+Math.round(Math.random() * 10000);
		//向地图添加覆盖物
		that.mapObjDiv.addOverlay(Mmarker,true);
		that.setFitVDiv2(Mmarker.id,that.arrForFitView);
		//onevmouse_click(Mmarker.id);
//		that.mapObjDiv.addEventListener(Mmarker,MConstants.MOUSE_CLICK,that.onevmouse_click2);
	},
	onevmouse_click2:function (event){
		var mkid = event.overlayId;
		var lngX = ftlyInfoObj.mapabc.mapObjDiv.getOverlayById(mkid).lnglat.lngX;
		var latY = ftlyInfoObj.mapabc.mapObjDiv.getOverlayById(mkid).lnglat.latY;
		
		var dateV = mkid.split("-");
		var value = "vin="+dateV[0]+"&lngX="+lngX+"&latY="+latY+"&start_time="+encodeURI(encodeURI(jQuery('#start_time').val()+" 00:00:00"))+"&end_time="+encodeURI(encodeURI(jQuery('#end_time').val()+" 23:59:59"));
		jQuery.post("zsptOrgTree/returnTipFtlyInfo.shtml",value,function(data){
			var dd = eval(data);
			//returnTipInfo_callback(mkid,dd[0]);
			var value = dd[0];
			//var vin = mkid;
			var mk = ftlyInfoObj.mapabc.mapObjDiv.getOverlayById(mkid);
			if(mk != null){
				mk.option.tipOption = ftlyInfoObj.mapabc.addFlashTip2(value);
				mk.option.canShowTip=true;
				
				ftlyInfoObj.mapabc.mapObjDiv.addOverlay(mk);
				ftlyInfoObj.mapabc.mapObjDiv.openOverlayTip(mkid);
			};
		},"text");
	},
	returnTipInfo_callback2:function (mkid,data){
		var that = this;
		var value = data;
		//var vin = mkid;
		var mk = that.mapObjDiv.getOverlayById(mkid);
		
		if(mk != null){
			mk.option.tipOption = that.addFlashTip2(value);
			mk.option.canShowTip=true;
			
			that.mapObjDiv.addOverlay(mk);
			//mapObjDiv.setOverlayToTopById(mk);
			//mapObjDiv.openOverlayTip(mkid);
		}
	},
	//var tipOption = null;
	addFlashTip2:function (reportTime){
		var that = this;
		var tipOption=new MTipOptions();
	    tipOption.tipType=MConstants.HTML_CUSTOM_TIP;
	    var dd=that.gethtml2(reportTime);
	    tipOption.content = dd;
	    
	    //jQuery("#openChart_Mapabc_Container").append("<div id='popup' style='z-index: 751; position: absolute; overflow: visible; top: 37px; left: 299px;'>"+dd+"</div>");
	    tipOption.tipAlign=MConstants.BOTTOM_CENTER;
        tipOption.tipPosition=new MPoint(0,-15);
	    return tipOption;
	},
	// 构建气球框内容
	gethtml2:function (value){
		var httpinfo = "",ydFlag="";		
		if(value.state=='000' || value.state=='010' || value.state=='011'){
			httpinfo +=	'<div class="vehicle_information_tip_add" style="position:absolute;">';	
			ydFlag="加&nbsp;油";
		}else if(value.state=='001'){
			httpinfo +=	'<div class="vehicle_information_tip_reduce" style="position:absolute;">';
			ydFlag="骤&nbsp;减";
		}				  
		httpinfo +=	'<div class="vehicle_information_tip_top"><div class="tip-close"><a onclick="ftlyInfoObj.mapabc.mapObjDiv.closeTip()" href="javascript:void(0);">&nbsp;</a></div>';
		httpinfo +=	'</div>';
		httpinfo +=	'<div class="vehicle_information_tip_box">';
		httpinfo +=	'<div class="">';
		httpinfo +=	'<table width="240" align="center" cellpadding="3" border="0">';	
		httpinfo +=	'<tr height="12" style="line-height: 11px;">';
		httpinfo +=	'<td align="right" width="65"><b>'+ydFlag+'&nbsp;前：</b></td>';
		if(value.state=='001'){
			httpinfo +=	'<td >'+(parseFloat(value.oilboxMass)+parseFloat(value.addOill)).toFixed(2)+'(L)</td>';
		}else if(value.state=='010'){
			if((parseFloat(value.oilboxMass)-parseFloat(value.addOill))<=0)
				httpinfo +=	'<td >0(L)</td>';
			else
				httpinfo +=	'<td >'+(parseFloat(value.oilboxMass)-parseFloat(value.addOill)).toFixed(2)+'(L)</td>';
		}else {
			httpinfo +=	'<td ></td>';
		}
		
		httpinfo +=	'</tr>';
		httpinfo +=	'<tr height="12" style="line-height: 11px;">';
		httpinfo +=	'<td align="right" width="65"><b>变&nbsp;化&nbsp;量：</b></td>';
		httpinfo +=	'<td>'+value.addOill+'(L)</td>';
		httpinfo +=	'</tr>';
		httpinfo +=	'<tr height="12" style="line-height: 11px;">';
		httpinfo +=	'<td align="right" width="65"><b>'+ydFlag+'&nbsp;后：</b></td>';
		httpinfo +=	'<td>'+value.oilboxMass+'(L)</td>';
		httpinfo +=	'</tr>';
		httpinfo +=	'<tr height="18">';
		httpinfo +=	'<td align="right" width="65"><b>上报时间：</b></td>';
		httpinfo +=	'<td>'+value.reportTime+'</td>';
		httpinfo +=	'</tr>';
		httpinfo +=	'<tr height="12" vAlign="top">';
		httpinfo +=	'<td align="right" width="65"><b>地理位置：</b></td>';
		if(value.zonename!=null&&value.zonename!='null')
			httpinfo +=	'<td>'+value.zonename+'</td>';
		else
			httpinfo +=	'<td></td>';
		httpinfo +=	'</tr>';
		httpinfo +=	'</table>';
		httpinfo +=	'</div>';
		httpinfo +=	'<div class="vehicle_information_tip_bottom">';
		httpinfo +=	'</div>';
		httpinfo +=	'</div>';
	    
		httpinfo +=	'</div>';
		/**
		httpinfo +=	'上报时间：'+value.reportTime+'<br/>';
		httpinfo +=	'故障详情：'+value.bugDesc;**/
		return httpinfo;
	},
	poiToAddressSearch_CallBack2:function (data){
		var resultStr="";
	    if(data.error_message != null){
	        resultStr="查询异常！"+data.error_message;
	    }else{
		    switch(data.message){
		    case 'ok':
		        resultStr+=data.SpatialBean.City.name+"市"+data.SpatialBean.District.name+"区";
		            //for(var i=0;i<data.SpatialBean.roadList.length;i++){
		                //resultStr+="<br />道路："+data.SpatialBean.roadList.name+"  <br />距离："+data.SpatialBean.roadList.distance+"<br />";
		            	resultStr += data.SpatialBean.roadList[0].name+"路";
		            //}
		            //for (var i = 0; i < data.SpatialBean.poiList.length; i++) {
		            	resultStr += data.SpatialBean.poiList[0].address;
		            	resultStr += "距离"+data.SpatialBean.poiList[0].distance+"米附近";
		                //resultStr += "<br />名称："+data.SpatialBean.poiList.name+"<br />地址："+data.SpatialBean.poiList.address+"<br />类型："+data.SpatialBean.poiList.type+"<br />距离："+data.SpatialBean.poiList.distance+"<br />经度："+data.SpatialBean.poiList.x+"<br />纬度："+data.SpatialBean.poiList.y+"<br />";
		            //}
		    break;
		    case 'error':
		        content='<div class=\"default\"><div class=\"default_title\">网络忙！请重新尝试！</div><div class=\"d_link\"><div class=\"d_right\"></div><div class=\"suggest\"><strong>建议：</strong><br />如果您刷新页后仍无法显示结果，请过几分钟后再次尝试或者与我们的服务人员联系。<br />Email：service@mapabc.com <br />电话：400 810 0080</div></div></span>错误信息："+data.message+"</div>';
		    break;
		    default:
		        content='<div class=\"default\"><div class=\"default_title\">对不起！网络繁忙！请稍后重新尝试！</div><div class=\"d_link\"><div class=\"d_right\"></div><div class=\"suggest\"><strong>建议：</strong><br />如果您刷新页后仍无法显示结果，请过几分钟后再次尝试或者与我们的服务人员联系。<br />Email：service@mapabc.com <br />电话：400 810 0080</div></div></span>错误信息："+data.message+"</div>';
		    }
		}
	    mapgeography = resultStr;
	},
	// 自适应room值
	setFitVDiv2:function (overlayid,arrForFitView){
		var that = this;
		that.arrForFitView.push(overlayid);
		that.mapObjDiv.setFitview(arrForFitView);
	},
	addMarkerLabel2:function (pointname,direction){   
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
	    labeloption.labelPosition=new MPoint(0,0);   
	    //设置对准点正下方的文字标签锚点   
	    labeloption.labelAlign=direction;//TOP_CENTER;   
	   return  labeloption;
	}
};