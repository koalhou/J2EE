var MapFactory = ["mapabc","baidu"];
/**
 * 地图静态变量集对象
 */
var MyStaticVal = {
		/*地图厂家类型*/
		"myMapType": MapFactory[0],
		
		/*对象类型*/
		"myMARKER" : "marker",
		"myLINE" : "line",
		
		/*工具显示类型*/
		"mySMALL": MConstants.SMALL, 
		"myROUND": MConstants.ROUND,
	    "myMINIMIZE": MConstants.MINIMIZE,
	    "mySHOW": MConstants.SHOW,
	    "myHIDE": MConstants.HIDE,
	    
	    /*数据类型*/
	    "myCOORD_TYPE_OFFSET": MConstants.COORD_TYPE_OFFSET,
	    
	    /*位置类变量*/
	    "myTOP_LEFT": MConstants.TOP_LEFT,
	    "myTOP_CENTER": MConstants.TOP_CENTER,
	    "myTOP_RIGHT": MConstants.TOP_RIGHT,
	    "myMIDDLE_LEFT": MConstants.MIDDLE_LEFT,
	    "myMIDDLE_CENTER": MConstants.MIDDLE_CENTER,
	    "myMIDDLE_RIGHT": MConstants.MIDDLE_RIGHT,
	    "myBOTTOM_LEFT": MConstants.BOTTOM_LEFT,
	    "myBOTTOM_CENTER": MConstants.BOTTOM_CENTER,
	    "myBOTTOM_RIGHT": MConstants.BOTTOM_RIGHT,
	    
	    /*tip窗体变量*/
	    "myHTML_CUSTOM_TIP": MConstants.HTML_CUSTOM_TIP,
	    "myFLASH_BUBBLE_TIP": MConstants.FLASH_BUBBLE_TIP,
	    "myHTML_BUBBLE_TIP": MConstants.HTML_BUBBLE_TIP,
	    "myIMG_BUBBLE_TIP": MConstants.IMG_BUBBLE_TIP,
	    
	    /*事件类变量*/
	    
	    "myMAP_READY": MConstants.MAP_READY, //2
	    "myTIP_OPEN" : MConstants.TIP_OPEN,  //3
	    "myTIP_CLOSE" : MConstants.TIP_CLOSE,//3
	    "myMOUSE_CLICK": MConstants.MOUSE_CLICK,//5
	    "myMOUSE_DOWN": MConstants.MOUSE_DOWN, //5
	    "myMOUSE_UP" : MConstants.MOUSE_UP,  // 5
	    "myMEASURE_END" : MConstants.MEASURE_END,  // 测距结束 6
	    "myZOOM_CHANGED" : MConstants.ZOOM_CHANGED, //6
	    
	    /*线样式*/
	     "myLINE_SOLID": MConstants.LINE_SOLID,
	     "myLINE_DASHED": MConstants.LINE_DASHED
		
		};
		
function MyPoint(lon,lat){
	var p = {
			"lon" : lon,
			"lat" : lat
	};
	return p;
}

/**
*地图初始化参数集对象
*/
function MyMapOptions(){
	
	var opt = { "MAPTYPE" : MyStaticVal.myMapType, //地图厂家类型
				"zoom" : 4, //地图初始缩放比例
				"center" : {"lon":"116.397428","lat":"39.90923"},//地图中心坐标
				"hasDefaultMenu" : false, //是否使用默认菜单
				"toolbar" :MyStaticVal.myROUND, //设置地图初始化工具条
				"toolbarPos" : {"X":15,"Y":15},//设置工具条在地图上的显示位置
				"overviewMap" : MyStaticVal.myMINIMIZE,//设置鹰眼地图的状态，SHOW:显示，:隐藏（默认）
				"scale" : "",      //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。
				"returnCoordType" : MyStaticVal.myCOORD_TYPE_OFFSET,//返回数字坐标  
				"maxZoomLevel" : "", 
				"logoUrl" : "../newimages/sidelayerimages/mask.png",
				"groundLogo" : "../newimages/sidelayerimages/mapbackgroud.jpg",
				"language" : "",
				"fullScreenButton" : MyStaticVal.mySHOW,
				"centerCross" : MyStaticVal.myHIDE,
				"requestNum" : 1000,
				"isQuickInit" : false,
				"setKeyboardEnabled" : false
		};
	
		return opt;

}

	
/**
 * 地图初始化对象
 * @param divid 
 * @param opt 参数集合
 * @return
 */		

function MyMapInit(divid,opt) {  
	

	var opts = new MyMapOptions();
	opts = opts==opt ? opts : opt;
	//alert(opts.center.lon);
	var mapObj = 
	{"Options":opts, //地图初始化参数对象
	 "setCenter" : function(lon,lat){
		this.Map.setCenter(new MLngLat(lon,lat));
		},
	 "Map":"",
	 "initialize": function(){//地图初始化方法
			if(this.Options.MAPTYPE==MapFactory[0]){
				var mapOption = new MMapOptions();
				mapOption.zoom = this.Options.zoom;
				mapOption.center = new MLngLat(this.Options.center.lon, this.Options.center.lat);
				mapOption.hasDefaultMenu = this.Options.hasDefaultMenu;
				mapOption.toolbar = this.Options.toolbar;
				mapOption.toolbarPos = new MPoint(this.Options.toolbarPos.X,this.Options.toolbarPos.Y);
				mapOption.overviewMap = this.Options.overviewMap;
				
				if(this.Options.scale !=""){
					mapOption.scale = this.Options.scale;
				}
				mapOption.returnCoordType = this.Options.returnCoordType;
				if(this.Options.logoUrl !=""){
					mapOption.logoUrl = this.Options.logoUrl;
				}
				if(this.Options.groundLogo !=""){
					mapOption.groundLogo = this.Options.groundLogo;
				}
				
				mapOption.language = this.Options.language;
				mapOption.fullScreenButton = this.Options.fullScreenButton;
				mapOption.centerCross = this.Options.centerCross;
				mapOption.requestNum = this.Options.requestNum;
				mapOption.isQuickInit = this.Options.isQuickInit;
				

				this.Map = new MMap(divid, mapOption);
				this.Map.setKeyboardEnabled(this.Options.setKeyboardEnabled);
				
			}
		 },
	 "addEventListenerToMap":function(name,call_back){
			 
			 var event = {
					 "id" : "",
					 "type" : "",
					 "overlayid" : "",
					 "eventx" : "",
					 "eventy" : "",
					 "measureResult" : "",
					 "zoom" : ""
			 };
			 this.Map.addEventListener(this.Map,name,call_in_back);
			 
			 function call_in_back(param){
				 if(name == MyStaticVal.myMAP_READY){
					 event.id = param.mapId;
					 event.type = param.eventType;
					 call_back(event);
				 }
				 else if(name == MyStaticVal.myTIP_OPEN || name == MyStaticVal.myTIP_CLOSE){
					 event.id = param.mapId;
					 event.type = param.eventType;
					 event.overlayid = param.overlayId;
					 call_back(event);
				 }else if(name == MyStaticVal.myMOUSE_CLICK || name == MyStaticVal.myMOUSE_DOWN || name == MyStaticVal.myMOUSE_UP){
					 event.id = param.mapId;
					 event.type = param.eventType;
					 event.overlayid = param.overlayId;
					 event.eventx = param.eventX;
					 event.eventy = param.eventY;
					 call_back(event);
				 }else if(name == MyStaticVal.myMEASURE_END ){
					 event.id = param.mapId;
					 event.type = param.eventType;
					 event.overlayid = param.overlayId;
					 event.eventx = param.eventX;
					 event.eventy = param.eventY;
					 event.measureResult = param.measureResult;
					 call_back(event);
				 }else if(name == MyStaticVal.myZOOM_CHANGED){
					 event.id = param.mapId;
					 event.type = param.eventType;
					 event.overlayid = param.overlayId;
					 event.eventx = param.eventX;
					 event.eventy = param.eventY;
					 event.room = param.room;
					 call_back(event);
				 }
			 }	 
		 },
	 "addEventListenerToOverlay":function(obj,name,call_back){
			 var event = {
					 "id" : "",
					 "type" : "",
					 "overlayid" : "",
					 "eventx" : "",
					 "eventy" : "",
					 "measureResult" : "",
					 "zoom" : ""
			 };
			 this.Map.addEventListener(obj.Marker,name,call_in_back);
			 
			 function call_in_back(param){
				 if(name == MyStaticVal.myMAP_READY){
					 event.id = param.mapId;
					 event.type = param.eventType;
					 call_back(event);
				 }
				 else if(name == MyStaticVal.myTIP_OPEN || name == MyStaticVal.myTIP_CLOSE){
					 event.id = param.mapId;
					 event.type = param.eventType;
					 event.overlayid = param.overlayId;
					 call_back(event);
				 }else if(name == MyStaticVal.myMOUSE_CLICK || name == MyStaticVal.myMOUSE_DOWN || name == MyStaticVal.myMOUSE_UP){
					 event.id = param.mapId;
					 event.type = param.eventType;
					 event.overlayid = param.overlayId;
					 event.eventx = param.eventX;
					 event.eventy = param.eventY;
					 call_back(event);
				 }else if(name == MyStaticVal.myMEASURE_END ){
					 event.id = param.mapId;
					 event.type = param.eventType;
					 event.overlayid = param.overlayId;
					 event.eventx = param.eventX;
					 event.eventy = param.eventY;
					 event.measureResult = param.measureResult;
					 call_back(event);
				 }else if(name == MyStaticVal.myZOOM_CHANGED){
					 event.id = param.mapId;
					 event.type = param.eventType;
					 event.overlayid = param.overlayId;
					 event.eventx = param.eventX;
					 event.eventy = param.eventY;
					 event.room = param.room;
					 call_back(event);
				 }
			 }	
		 }, 
	 "addOverlays": function (Overlaylist, bool){
			 var arr = new Array();
			 for(var i=0; i < Overlaylist.length; i++){
				 if(MyStaticVal.myMARKER == Overlaylist[i].TYPE){
					 arr.push(Overlaylist[i].Marker);
				 }
				 else if(MyStaticVal.myLINE == Overlaylist[i].TYPE){ 
					 arr.push(Overlaylist[i].Line);
				 }
			 }
			 var i = this.Map.addOverlays(arr,bool);
			 return i;
		 },
	 "addOverlay": function (overlay, isToFitView){
			 var i = 0; 
			 if(MyStaticVal.myMARKER == overlay.TYPE){
				i = this.Map.addOverlay(overlay.Marker, isToFitView);
			 }
			 else if(MyStaticVal.myLINE == overlay.TYPE){ 
				i = this.Map.addOverlay(overlay.Line, isToFitView);
			 }
			 //alert(i);
			 return i;
		 },
	 
	 "getOverlayById": function (overlayid){
			var overlay = this.Map.getOverlayById(overlayid);
			if(overlay.TYPE == "Marker"){
				var mo = new MyMarkerOptions();
				mo.imageUrl = overlay.option.imageUrl;
				mo.picAgent = overlay.option.picAgent;
				mo.imageAlign = overlay.option.imageAlign;
				mo.rotation = overlay.option.rotation;
				mo.labelOption = overlay.option.labelOption;
				if(overlay.option.tipOption != null && overlay.option.tipOption != "" && overlay.option.tipOption !="undefined"){
					
					var tip = new MyTipOption(overlay.option.tipOption.content);
					mo.tipOption = tip;
				}
				mo.canShowTip = overlay.option.canShowTip;
				mo.isEditable = overlay.option.isEditable;
				mo.hasShadow = overlay.option.hasShadow;
				mo.zoomLevels = overlay.option.zoomLevels;
				mo.isDimorphic = overlay.option.isDimorphic;
				mo.dimorphicColor = overlay.option.dimorphicColor;
				
				var m = new MyMarkerObj(overlay.id,overlay.lnglat.lngX,overlay.lnglat.latY,mo);
				return m;
				
			}else if(overlay.TYPE == "Polyline"){
				var opt = new MyLineOptions();
				
				var st = new MyLineStyle();
				st.thickness = overlay.option.lineStyle.thickness;
				st.color = overlay.option.lineStyle.color;
				st.alpha = overlay.option.lineStyle.alpha;
				st.lineType = overlay.option.lineStyle.lineType;
				
				opt.lineStyle = st;
				if(overlay.option.tipOption != null && overlay.option.tipOption != "" && overlay.option.tipOption !="undefined"){
					var tip = new MyTipOption(overlay.option.tipOption.content);
					opt.tipOption = tip;
				}
				opt.canShowTip = overlay.option.canShowTip;
				opt.isEditable = overlay.option.isEditable;
				opt.zoomLevels = overlay.option.zoomLevels;
				opt.isDimorphic = overlay.option.isDimorphic;
				opt.dimorphicColor = overlay.option.dimorphicColor;
				
				var arr = new Array();
				var plist = overlay.lnglatArr;
				for(var i = 0; i < plist.length; i++){
					arr.push(new MyPoint(plist[i].lnglat.lngX,plist[i].lnglat.latY));
				}
				
				var l = MyMPolyline(overlay.id,arr,opt);
				return l;
			}
			
		 },
	 
	 "getOverlaysByType": function (overlaytype){
			 var overlays = this.Map.getOverlaysByType(overlaytype);
			 if(overlaytype == MyStaticVal.myMARKER){
				 var arrm = new Array();
				 for(var i = 0; i < overlays.lenght; i++){
					var mo = new MyMarkerOptions();
					mo.imageUrl = overlays[i].option.imageUrl;
					mo.picAgent = overlays[i].option.picAgent;
					mo.imageAlign = overlays[i].option.imageAlign;
					mo.rotation = overlays[i].option.rotation;
					mo.labelOption = overlays[i].option.labelOption;
					if(overlays[i].option.tipOption != null && overlays[i].option.tipOption != "" && overlays[i].option.tipOption !="undefined"){
						var tip = new MyTipOption(overlays[i].option.tipOption.content);
						mo.tipOption = tip;
					}
					mo.canShowTip = overlays[i].option.canShowTip;
					mo.isEditable = overlays[i].option.isEditable;
					mo.hasShadow = overlays[i].option.hasShadow;
					mo.zoomLevels = overlays[i].option.zoomLevels;
					mo.isDimorphic = overlays[i].option.isDimorphic;
					mo.dimorphicColor = overlays[i].option.dimorphicColor;
					
					var m = new MyMarkerObj(overlays[i].id,overlays[i].lnglat.lngX,overlays[i].lnglat.latY,mo);
					arrm.push(mo);
				 }
				 return arrm;
				 
			 }else if(overlaytype == MyStaticVal.myLINE){
				 var arrl = new Array();
				 for(var i = 0; i < overlays.lenght; i++){
					var opt = new MyLineOptions();
					var st = new MyLineStyle();
					st.thickness = overlays[i].option.lineStyle.thickness;
					st.color = overlays[i].option.lineStyle.color;
					st.alpha = overlays[i].option.lineStyle.alpha;
					st.lineType = overlays[i].option.lineStyle.lineType;
					
					opt.lineStyle = st;
					if(overlays[i].option.tipOption != null && overlays[i].option.tipOption != "" && overlays[i].option.tipOption !="undefined"){
						var tip = new MyTipOption(overlays[i].option.tipOption.content);
						opt.tipOption = tip;
					}
					opt.canShowTip = overlays[i].option.canShowTip;
					opt.isEditable = overlays[i].option.isEditable;
					opt.zoomLevels = overlays[i].option.zoomLevels;
					opt.isDimorphic = overlays[i].option.isDimorphic;
					opt.dimorphicColor = overlays[i].option.dimorphicColor;
					
					var arr = new Array();
					var plist = overlays[i].lnglatArr;
					for(var j = 0; j < plist.length; j++){
						arr.push(new MyPoint(plist[j].lnglat.lngX,plist[j].lnglat.latY));
					}
					
					var l = MyMPolyline(overlay.id,arr,opt);
					arrl.push(l);
				 }
				 return arrl;
			 } 
		 },
	 
	 "removeOverlaysByType": function (overlaytype){
			 var i = 0;
			if(overlaytype == MyStaticVal.myLINE){
				i = this.Map.removeOverlaysByType(MOverlay.TYPE_POLYLINE);
				
			}else if(overlaytype == MyStaticVal.myMARKER){
				i = this.Map.removeOverlaysByType(MOverlay.TYPE_MARKER);
			}
			return i;
		 },
	 
	 "removeOverlayById":function (overlayid){
			 var i = this.Map.removeOverlayById(overlayid);
			 return i;
		 },
	 
	 "removeOverlay": function (overlay){  // 暂时无用
			 var i = this.Map.removeOverlay(overlay);
			 return i;
		 },
	 
	 "removeAllOverlays": function (){
			 var i = this.Map.removeAllOverlays();
			 return i;
		 },
	 
	 "setClusterState": function (state, clusterOption){ // 点绽放
			 var s = state;
			 var opt = clusterOption;
	 		 var clusterOptions = new MClusterOptions();
	 		 clusterOptions.gridSize = opt.gridSize;
	 		 clusterOptions.maxZoom = opt.maxZoom;
	 		 if(opt.attributeValue == opt.TOTAL_NUMBER){
	 			clusterOptions.attributeValue = MClusterOptions.TOTAL_NUMBER;
	 		 }else{
	 			clusterOptions.attributeValue = MClusterOptions.VALUE_SUM;
	 		 }
	 		 clusterOptions.fontStyle = new MFontStyle();
	 		 clusterOptions.fontStyle.name = opt.style_name;
	 		 clusterOptions.fontStyle.size = opt.style_size;
	 		 clusterOptions.fontStyle.color = opt.style_color;
	 		 clusterOptions.fontStyle.bold = opt.style_bold;
	 			
	 		 if(opt.centerMarkerURL != ""){
	 			clusterOptions.centerMarkerURL = opt.centerMarkerURL;
	 		 }
	 		 if(opt.aroundMarkerURL != ""){
	 			clusterOptions.aroundMarkerURL = opt.aroundMarkerURL;
	 		 }
	 		 
	 		 clusterOptions.flareDistance = opt.flareDistance;
	 		 clusterOptions.isUseMarkerIcon = opt.isUseMarkerIcon;
	 		
			 if(opt.NORMAL_CLUSTER == state){
				 s = MClusterOptions.NORMAL_CLUSTER;
			 }else if(opt.FLARE_CLUSTER == state){
				 s = MClusterOptions.FLARE_CLUSTER;
			 }
			 else{
				 s = MClusterOptions.NO_CLUSTER;
			 }
	 
			 var i = this.Map.setClusterState(s, clusterOptions); 
	 		 return i;
	 	},  
	 "setZoomLevel": function(level){
	 		var i = this.Map.setZoomLevel(level);
	 		return i;
	 	},
	 
	 "setLngLatBounds" : function (minx,miny,maxx,maxy){ 
		    var i = this.Map.setLngLatBounds(new MLngLatBounds(new MLngLat(minx,miny),new MLngLat(maxx,maxy)));
		    return i;
	 	},
	 "getLngLatBounds" : function (){
	 		var bounds=this.Map.getLngLatBounds(); 
	 		var se = {
	 				"southWest" : new MyPoint(bounds.southWest.lngX,bounds.southWest.latY),
	 				"northEast" : new MyPoint(bounds.northEast.lngX,bounds.northEast.latY)
	 		};
	 		return se;
	 	}, 
	 "openTip" : function (lon,lat,tipobj){
	 		var i = this.Map.openTip(new MLngLat(lon ,lat),tipobj);
	 		return i;
	 	},
	 "closeTip": function (){
	 		this.Map.closeTip();
	    },
	 "updateOverlay": function (overlay){
	    	var i = 0;
	    	if(overlay.TYPE == MyStaticVal.myMARKER){
	    		i = this.Map.updateOverlay(overlay.Marker);
	    		
	    	}else if(overlay.TYPE == MyStaticVal.myMARKER){
	    		i = this.Map.updateOverlay(overlay.Line);
	    	}
	    	 
	    	return i;
	    },
	 "markerMoveTo": function (markerid,lon,lat,hudu,speed){
	    	var i = this.Map.markerMoveTo(markerid, new MLngLat(lon,lat), hudu, speed);
	    	return i;
	    },
	 "panTo": function (lon,lat){
	    	var i = this.Map.panTo(new MLngLat(lon,lat));
	    	return i;
	    },
	  "MReGeoCodeSearch": function (lon,lat,call_back){
	    	 
	    	 var lnglatXY= new MLngLat(lon,lat); 
			 var mls = new MReGeoCodeSearch();   
			 var opt =  new MReGeoCodeSearchOptions();   
			 opt.poiNumber=10;;//返回周边的POI数量,默认10   
			 opt.range=100;//限定周边热点POI和道路的距离范围   
			 opt.pattern=0;//返回数据的模式,0表示返回地标性POI,1表示返回全部POI，   
			 opt.exkey="";//排除的关键字   
			 mls.setCallbackFunction(pcall_back);   
			 mls.poiToAddress(lnglatXY,opt);  
			 
			 function pcall_back(data){
				if(data.error_message != null){   
					resultStr="查询异常！"+data.error_message;
					call_back(resultStr);   
				}else{
					switch(data.message){
					case 'ok':
						if(data.SpatialBean.roadList!=null && data.SpatialBean.roadList.length>0){
							addr = data.SpatialBean.roadList[0].name;
						}
						if(addr != null && addr != "" && addr.trim != "undefined"){
							//alert(addr);
							call_back(addr);
						}
						else{
							call_back("");
						}
						break; 
					case 'error':
						call_back("");
						break; 
					default: 
						call_back("");						
				   }
				}
			 }
	    }
	};
	
	mapObj.initialize();   
	return mapObj;
}

/**
 * 点聚合属性对象
 * @return
 */
function MyMClusterOptions(){
	var mmo = 
	{		// 点聚合模式常量，普通效果的点聚合，中心点图标将随着聚合所包含的MMarker对象的数量级（个、十、百、千、万）自动变化
			"NORMAL_CLUSTER":"NORMAL_CLUSTER",
			// 点聚合模式常量，绽放效果的点聚合，支持MClusterOptions对象的所有属性控制
			"FLARE_CLUSTER":"FLARE_CLUSTER",
			// 点聚合模式常量，取消点聚合。
			"NO_CLUSTER":"NO_CLUSTER",
			// 聚合中心点显示属性类型常量，显示所有聚合点个数
			"TOTAL_NUMBER":"TOTAL_NUMBER",
			// 聚合中心点显示属性类型常量，显示所有聚合点属性值之和
			"TOTAL_SUM":"TOTAL_SUM",
			
			// 聚合的范围，单位为像素
			"gridSize": 27,
			// 聚合的最大级别。默认值为15
			"maxZoom": 15,
			// 聚合中心点显示属性类型
			"attributeValue": this.TOTAL_NUMBER,
			// 聚合点显示的文字样式
			//"fontStyle":"",
			// 设置绽放聚合中心点图标的URL
			"centerMarkerURL":"",
			// 设置绽放聚合绽放点图标的URL
			"aroundMarkerURL":"",
			// 绽放点到中心点的距离，单位为像素，取值为整数
			"flareDistance": 30,
			// 绽放点聚合中，绽放点是否使用原始图标
			"isUseMarkerIcon": false,
			
			// 聚合点的字体样式
			"style_name" : "宋体",
			"style_size" : "12",
			"style_color": 0x000d46,
			"style_bold" : false
					
	};
	return mmo;
}
 

/**
 * 点对象属性对象
 * @return
 */
function MyMarkerOptions(){
	var markeropt = {
			//地图厂家类型
			"MAPTYPE" : MyStaticVal.myMapType, 
			/*自定义图片*/
			"imageUrl" : "../newimages/arrow_blue.png", 
			/*是否使用图片代理形式*/
			"picAgent":false, 
			/*自定义图片大小*/
			//"imageSize":{"w":14,"h":32}, 
			/*自定义图片位置*/
			"imageAlign":MyStaticVal.MIDDLE_CENTER, 
			/*自定义图片方向*/
			"rotation": 0, 
			/*标注对象*/
			"labelOption": null, 
			/*点的tip信息窗体*/
			"tipOption": null,
			/*信息窗体可显示默认true*/
			"canShowTip": true, 
			/*设置点是否可编辑，默认false*/
			"isEditable": false, 
			/*弹跳效果，依赖isEditable*/
			"isBounce": false,
			/*阴影效果，默认true*/
			"hasShadow": true, 
			/*点的显示级别*/
			"zoomLevels": [], 
			/*点是否高亮显示，与isEditable互刺*/
			"isDimorphic": true,
			/*设置对象高亮状态的颜色。取值为十六进制整数,默认0xFF0000 */
			"dimorphicColor":"0xFF0000"
			
	};
	
	return markeropt;
}

/**
  * marker属性对象
  * @param id  定义id
  * @param lon 精度
  * @param lat 纬度
  * @param opt 属性集合
  * @return
  */
function MyMarkerObj(id,lon,lat,opt){
	var opts = new MyMarkerOptions();
	opts = opts==opt?opts:opt;
	
	var markerobj = {
		"id": function(){
				return this.Marker.id;
			},
		"MKOptions" : opts,
		"Marker" : "",
		"Minitialize" : function(){
				if(this.MKOptions.MAPTYPE=="mapabc"){
					var markerOption = new MMarkerOptions();
					markerOption.imageUrl = this.MKOptions.imageUrl;
					markerOption.picAgent = this.MKOptions.picAgent;
					//markerOption.imageSize = new MSize(this.MKOptions.w, this.MKOptions.h);
					markerOption.imageAlign = MConstants.MIDDLE_CENTER;
					markerOption.rotation = this.MKOptions.rotation;	
					markerOption.labelOption = this.MKOptions.labelOption;	
					if(this.MKOptions.tipOption != null){
						markerOption.tipOption = this.MKOptions.tipOption;
					}
					markerOption.canShowTip = this.MKOptions.canShowTip;
					markerOption.isEditable = this.MKOptions.isEditable; //设置点是否可编辑。
					markerOption.hasShadow = this.MKOptions.hasShadow;  //是否显示阴影。
					markerOption.zoomLevels = this.MKOptions.zoomLevels; //设置点的缩放级别范围。
					markerOption.isDimorphic = this.MKOptions.isDimorphic;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
					markerOption. dimorphicColor = this.MKOptions.dimorphicColor; //设置第二种状态的颜色
					var marker = new MMarker(new MLngLat(lon, lat),markerOption); 
					marker.id = id;
					this.Marker = marker;
					this.id = id;
					this.lnglat = new MyPoint(lon,lat);
					this.size = this.Marker.size;
				}		
			},
		"TYPE": MyStaticVal.myMARKER,
		"lnglat": "",
		"size": ""
	};
	
	markerobj.Minitialize();
	return markerobj;
}

/**
 * label对象生成
 * @param content 内容
 * @param direction  位置
 * @return
 */
function MyMLabelOptions(content,direction){
	
	var labelopt = {
		"LabelOptions":"",
		"labelOptinitialize": function(content,direction){
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
		    labeloption.content=content;//标注的显示内容   
		    //设置标注左上角相对于面对象中心的锚点。标注左上角与面对象中心重合时，像素坐标原点(0,0)   
		    //labeloption.labelPosition=new MPoint(0,25);   
		    //设置对准点正下方的文字标签锚点   
		    labeloption.labelAlign=direction;//TOP_CENTER; 
		    
		    this.LabelOptions = labeloption;
		    
		}
	};
	
	labelopt.labelOptinitialize(content,direction);
	return labelopt.LabelOptions;
	
}

/**
 * 生成自定义tip窗体对象
 * @param content  内容
 * @return
 */
function MyTipOption(content){
	
	var tipobj = {
			"TIP":"",
			"initialize" : function(content){
				
				var tipOption=new MTipOptions();  
			    tipOption.tipType=MConstants.HTML_CUSTOM_TIP;  
			    tipOption.content= content ;        
			    tipOption.tipAlign=MConstants.BOTTOM_CENTER;   
		        tipOption.tipPosition= new MPoint(0,15);   
			    this.TIP =  tipOption;
			    this.content = content;
		
			},
			"content":""
	};
	tipobj.initialize(content);
	return tipobj.TIP;
}

/**
 * 线的样式属性对像
 * @return
 */
function MyLineStyle(){
	
	var styleopt = {
			"thickness": 2,
			"color": 0x005890 ,
			"alpha": 1,
			"lineType": MyStaticVal.myLINE_SOLID
	};
	return styleopt;
}
/**
 * 线属性对象
 * @return
 */
function MyLineOptions(){
	
	var opt = {
			"lineStyle" : null,
			"tipOption" : null,
			"canShowTip" : true,
			"isEditable" : false,
			"zoomLevels" : [],
			"isDimorphic" : false,
			"dimorphicColor" : 0xFF0000		
	};
	return opt;
}
 
/**
 * 实例化线对象
 * @param id  id
 * @param MyPointList  点集合
 * @param MyLineOptions  属性集合
 * @return
 */
function MyMPolyline(id,MyPointList,MyLineOptions){
	
	var LineObj = {
			"Line" : "",
			"initialize" : function (id,MyPointList,MyLineOptions){
			    var arr = new Array();
			    for(var i = 0; i < MyPointList.length; i++){
			    	var lonlat = new  MLngLat(MyPointList[i].lon,MyPointList[i].lat);
			    	arr.push(lonlat);
			    }
				
				if(MyLineOptions != null){
					
					var lineopt=new MLineOptions();
					lineopt.lineStyle = MyLineOptions.lineStyle;
					lineopt.tipOption = MyLineOptions.tipOption;
					lineopt.canShowTip = MyLineOptions.canShowTip;
					lineopt.isEditable = MyLineOptions.isEditable;
					lineopt.zoomLevels = MyLineOptions.zoomLevels;
					lineopt.isDimorphic = MyLineOptions.isDimorphic;
					lineopt.dimorphicColor =  MyLineOptions.dimorphicColor;
					
					this.Line = new MPolyline(arr,lineopt);
					this.Line.id = id;
					this.id = id;
				}
				else{
					this.Line = new MPolyline(arr);
					this.Line.id = id;
					this.id = id;
				}
			},
		    "id": "",
		    "TYPE" : MyStaticVal.myLINE,
		    "lnglatArr":"",
		    "option":""
	};
	
	LineObj.initialize(id,MyPointList,MyLineOptions);
	
	return LineObj;
}
