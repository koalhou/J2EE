<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/key2.jsp" %> 
<script type="text/javascript">

	/** 地图对象 **/
	var mapObj = null;
	var publicMapID="";
	
	/** 地图初始化 **/
	function mapInit() {
		var mapOptions = new MMapOptions();//构建地图辅助类   
		mapOptions.zoom=10;//要加载的地图的缩放级别   
		mapOptions.toolbar = MConstants.ROUND; //设置地图初始化工具条，ROUND:新版圆工具条   
		mapOptions.toolbarPos = new MPoint(20,20); //设置工具条在地图上的显示位置   
		mapOptions.overviewMap = MConstants.MINIMIZE; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）  MINIMIZE:最小化
		mapOptions.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。
		mapOptions.language = MConstants.MAP_CN;//设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图
		mapOptions.fullScreenButton = MConstants.HIDE;//设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏
		mapOptions.centerCross = MConstants.HIDE;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏
		mapOptions.requestNum=100;//设置地图切片请求并发数。默认100。
		mapOptions.isQuickInit=true;//设置是否快速显示地图，true显示，false不显示。	
		mapOptions.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标   		
		mapOptions.hasDefaultMenu=false;  //去掉鼠标右键	
		//地图右下角logo去掉
		mapOptions.logoUrl="../newimages/sidelayerimages/mask.png";
		//背景图片
		mapOptions.groundLogo="../newimages/sidelayerimages/mapbackgroud.jpg";
		
		var mlglat = new MLngLat("113.686","34.693",MConstants.COORD_TYPE_OFFSET);
		mapOptions.center = mlglat;
		mapObj=new MMap("iCenter",mapOptions); //地图初始化   
		mapObj.setKeyboardEnabled(false);
		//地图加载完成
		mapObj.addEventListener(mapObj,MConstants.MAP_READY,map_ready);
		mapObj.addEventListener(mapObj,MConstants.ZOOM_CHANGED,setStatus);
	}
	
	function map_ready(param){  
		//初始化加载点
		initMapList();
	}
	
	//设置聚合的回调方法--设置聚合
	function setStatus(){
		if(jQuery("#pointTogether").attr("checked")){
			normal();
		}else{
			cancelCluster();
		}
	}
	
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
	    labeloption.labelAlign=direction;
	    return  labeloption;
	}
	
	/* 选择CHECKBOX按钮，在地图上绘点 
	 * 如果站点是通过查询条件，已在地图上存在，那么修改该点的图片；
	 * 否则直接在地图上绘点。
	 */
	function addMarkerToMap(checkValueStr) {
		var checkValue= new Array();
		checkValue=checkValueStr.split("#!#");
		var stationID=checkValue[0];
		var stationName=checkValue[1];
		var lonValue =  checkValue[4];
		var latValue = checkValue[2];
		if(lonValue >=0 && lonValue <=180 && latValue>=0 && latValue <=90){//(if1-start)
			if(mapObj.getOverlayById(stationID)!=null){//地图已经存在该点,选择复选框
				var thisMarker = mapObj.getOverlayById(stationID);
				//thisMarker.option.showFlag -- getPointSAByObj(thisMarker,"showFlag")
				if(getPointSAByObj(thisMarker,"showFlag")!="listAdd"){
					thisMarker.option.imageUrl="../images/map/station2.png";
					//根据对象设置业务属性
					//thisMarker.option.showFlag="searchCheckedAdd";
					setPointSAByObj(thisMarker,"showFlag","searchCheckedAdd");
					//thisMarker.option.rotation=360;
					mapObj.updateOverlay(thisMarker);					
				}
			}else{//在地图上无该站点的标注，选中复选框的操作
				jQuery.ajax({
					type: 'POST', 
					url: '../station/getSingleLONLAT.shtml',	
					data: {
						longitude:lonValue,
						latitude:latValue
					},
					success: function(lonlatString) {
						if(lonlatString==null||lonlatString=="582.5422219509549,582.5422219509549"){
							return false;
						}
						var strs= new Array();
						strs=lonlatString.split(",");
						lonValue=strs[0];
						latValue=strs[1];
						var markerOption=new MMarkerOptions();  
						markerOption.imageUrl="../images/map/station2.png"; 
						//markerOption.imageSize=new MSize(30,30); 
						markerOption.picAgent=false;
						//设置点的标注参数选项  (自定义的属性) 
						markerOption.labelOption=addMarkerLabel(stationName,MConstants.BOTTOM_CENTER);
						//flash版本设置的属性
						markerOption.imageAlign=MConstants.BOTTOM_CENTER;
						markerOption.isBounce=false;
						markerOption.isEditable=false;
						markerOption.canShowTip= false;
						markerOption.rotation=0;
						markerOption.hasShadow=false;
						markerOption.tipOption=addDataToTipContent("","1","listAdd",checkValue[4],checkValue[2],checkValue[4],checkValue[2],0,checkValue[3],checkValue[5],checkValue[6]);					
						var Marker="";
						Marker = new MMarker(new MLngLat(lonValue,latValue),markerOption);  
						Marker.id=checkValue[0];
						mapObj.addOverlay(Marker,true);
						mapObj.addEventListener(Marker,MConstants.MOUSE_CLICK,clickMap);//update by fxy				
					}
				});
			}
		}
	}

	function selectAllStation(stationName,stationProperties){
		var arr = new Array();
		jQuery.ajax({
			type: 'POST', 
			dataType: 'json', 
			url: '../stationGrid/getAllStationInfo.shtml',	
			data: {
				site_name:stationName,
				//control_station:stationProperties,
				rp:jQuery('#gala').flex_rp(),
				page:jQuery('#gala').flex_current_page(),
				sortname:jQuery('#gala').flex_sortname(),
				sortorder:jQuery('#gala').flex_sortorder()
			},
			beforeSend:function(){
				jQuery.blockUI({ message: "<h1>站点绘制中,请稍等...</h1>" });
			},
			success: function(data) {
				for(var i=0;i<data.length;i++){
					if(data[i].LONGITUDE >=0 && data[i].LONGITUDE <=180 && data[i].LATITUDE>=0 && data[i].LATITUDE <=90){
						if(mapObj.getOverlayById(data[i].ID)!=null){
							var thisMarker = mapObj.getOverlayById(data[i].ID);
							//thisMarker.option.showFlag -- getPointSAByObj(thisMarker,"showFlag")
							if(getPointSAByObj(thisMarker,"showFlag")!="listAdd"){
								thisMarker.option.imageUrl="../images/map/station2.png";
								//thisMarker.option.showFlag="searchCheckedAdd";
								setPointSAByObj(thisMarker,"showFlag","searchCheckedAdd");
								thisMarker.option.rotation=360;
								mapObj.updateOverlay(thisMarker);
							}
						}else{
							var markerOption=new MMarkerOptions(); 
							//markerOption.realLng=data[i].ST_LONGITUDE;	//坐标真实经度
							//markerOption.realLat=data[i].ST_LATITUDE;	//坐标真实纬度
							//markerOption.initLng=data[i].ST_LONGITUDE;	//坐标初始经度
							//markerOption.initLat=data[i].ST_LATITUDE;	//坐标初始纬度
							//markerOption.control_station=data[i].CONTROL_STATION; 	//坐标上下行状态
							//markerOption.station_addr=data[i].STATION_ADDR;			//坐标详细地址
							//markerOption.organization_id=data[i].ORGANIZATION_ID;	//坐标组织机构ID
							//markerOption.organization_name=data[i].ENTER_SHORT_NAME;//坐标组织机构名称
							//markerOption.showFlag="listAdd";
							//markerOption.pointType="1";
							//update by fxy start(设置业务属性)
							//将业务属性缓存
							markerOption.tipOption = addDataToTipContent("","1","listAdd",data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].CONTROL_STATION,data[i].STATION_ADDR,data[i].ORGANIZATION_ID,data[i].ENTER_SHORT_NAME);				
							//update by fxy end						
							markerOption.imageUrl="../images/map/station2.png";
							markerOption.picAgent=false;
							//flash版本设置的属性
							markerOption.imageAlign=MConstants.BOTTOM_CENTER;
							markerOption.isBounce=false;
							markerOption.isEditable=false;
							markerOption.canShowTip= false;
							markerOption.rotation=0;
							markerOption.hasShadow=false;
							//markerOption.imageSize=new MSize(30,30);
							markerOption.labelOption=addMarkerLabel(data[i].DIRECTION,MConstants.BOTTOM_CENTER);
							var Marker = new MMarker(new MLngLat(data[i].LONGITUDE,data[i].LATITUDE),markerOption);
				      		Marker.id=data[i].ID; 
				   	  		arr.push(Marker);
						}
						
					}
				}
				if(arr.length!=0){
					// 批量添加标点
					mapObj.addOverlays(arr,true);  
					var mks = mapObj.getOverlaysByType(MOverlay.TYPE_MARKER);
					if(mks.length>0){
						for(var j = 0; j < mks.length; j++){
							mapObj.addEventListener(mks[j],MConstants.MOUSE_CLICK,clickMap);//update by fxy
						}
					}
				}
			},
			complete: function() { 
                jQuery.unblockUI(); 
            }
		});
		
	}
	/* 取消CHECKBOX按钮，在地图上删除点 ，
	 * 如果站点是通过查询条件，在地图上绘点，那么修改该点的图片；
	 * 如果站点是点击LIST列表，在地图上绘点，那么删除该点。
	 */
	function cancelMarkerFromMap(checkValueStr) {
		var checkValue= new Array();
		checkValue=checkValueStr.split("#!#");
		var stationID=checkValue[0];
		if(mapObj.getOverlayById(stationID)!=null){
			var thisMarker = mapObj.getOverlayById(stationID);
			//thisMarker.option.showFlag -- getPointSAByObj(thisMarker,"showFlag")
			if(getPointSAByObj(thisMarker,"showFlag")=="listAdd"||getPointSAByObj(thisMarker,"showFlag")=="searchListAdd"){
				mapObj.removeOverlayById(stationID);
			}else{
				thisMarker.option.imageUrl="../images/map/station1.png"; 
				//thisMarker.option.imageSize=new MSize(30,30);
				//thisMarker.option.showFlag="searchAdd";
				setPointSAByObj(thisMarker,"showFlag","searchAdd");
				thisMarker.option.rotation=360; 
				mapObj.updateOverlay(thisMarker);
			}
		}
	}
	
	/* 初始化地图数据
	 * 按CHECKBOX选择与时间段，在地图上进行绘点，
	 * 默认坐标点CHECKBOX选中，时间段当前一个月。
	 */
	function initMapList() {
		var startTime=document.getElementById("start_time").value;
		var endTime=document.getElementById("end_time").value;
		var inout_flag="";

		if(jQuery("#showStation").attr("checked")&&!jQuery("#showPoint").attr("checked")){
			inout_flag="1";
		}
		if(jQuery("#showPoint").attr("checked")&&!jQuery("#showStation").attr("checked")){
			inout_flag="0";
		}
		if(jQuery("#showPoint").attr("checked")&&jQuery("#showStation").attr("checked")){
			inout_flag="";
		}
		if(!jQuery("#showPoint").attr("checked")&&!jQuery("#showStation").attr("checked")){
			inout_flag="2";
		}
			
		mapObj.removeAllOverlays();	
  	 	var arr = new Array();
  	 	var checkValue="";
  	 	jQuery("input[name='carChoice']").each(function(){
			if(jQuery(this).attr("checked")){
				if(checkValue!=""){
					checkValue+=',';
				}
				checkValue+=jQuery(this).val().split("#!#")[0];
				return true;
			}
	 	});
  	 	jQuery.ajax({
			type: 'POST', 
			dataType: 'json', 
			async:false,
			url: '../stationGrid/getCheckedLeftList.shtml',	
			data: {
  	 			site_id_checked:checkValue,
				site_name:jQuery('#stationName').val(),
				//control_station:jQuery('#stationProperties').val(),
				rp:jQuery('#gala').flex_rp(),
				page:jQuery('#gala').flex_current_page(),
				sortname:jQuery('#gala').flex_sortname(),
				sortorder:jQuery('#gala').flex_sortorder()
			},
			beforeSend:function(){
				jQuery.blockUI({ message: "<h1>站点绘制中,请稍等...</h1>" });
			},
			success: function(data) {
				for(var i=0;i<data.length;i++){
					if(data[i].LONGITUDE >=0 && data[i].LONGITUDE <=180 && data[i].LATITUDE>=0 && data[i].LATITUDE <=90){
						var markerListOption=new MMarkerOptions();
						var MarkerList = new MMarker(new MLngLat(data[i].LONGITUDE,data[i].LATITUDE),markerListOption);
						//将业务属性缓存
						markerListOption.tipOption = addDataToTipContent("","1","searchListAdd",data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].CONTROL_STATION,data[i].STATION_ADDR,data[i].ORGANIZATION_ID,data[i].ENTER_SHORT_NAME);
						markerListOption.imageUrl="../images/map/station2.png";
						//flash版本设置的属性
						markerListOption.picAgent=false;
						markerListOption.imageAlign=MConstants.BOTTOM_CENTER;
						markerListOption.isBounce=false;
						markerListOption.isEditable=false;
						markerListOption.canShowTip= false;
						markerListOption.rotation=0;
						markerListOption.hasShadow=false;
						//markerListOption.imageSize=new MSize(30,30); update by fxy
						markerListOption.labelOption=addMarkerLabel(data[i].DIRECTION,MConstants.BOTTOM_CENTER);//update by fxy					
						MarkerList.id=data[i].ID; 
			   	  		arr.push(MarkerList);
					}
				}
			}
		});

  	 	jQuery.ajax({
				type: 'POST', 
				dataType: 'json', 
				url: '../stationGrid/getMapSearchList.shtml',	
				async:false,
				data: {
		  	 		startTime:startTime,
		  	 		endTime:endTime,
		  	 		inout_flag:inout_flag
				},
				success: function(data) {
				
					for(var i=0;i<data.length;i++){
						if(data[i].LONGITUDE >=0 && data[i].LONGITUDE <=180 && data[i].LATITUDE>=0 && data[i].LATITUDE <=90){
							var markerOption=new MMarkerOptions();
							var Marker = new MMarker(new MLngLat(data[i].LONGITUDE,data[i].LATITUDE),markerOption);
							//自定义属性
							//markerOption.realLng=data[i].ST_LONGITUDE;	//坐标真实经度
							//markerOption.realLat=data[i].ST_LATITUDE;	//坐标真实纬度
							//markerOption.initLng=data[i].ST_LONGITUDE;	//坐标初始经度
							//markerOption.initLat=data[i].ST_LATITUDE;	//坐标初始纬度
							//markerOption.pointType=data[i].INOUT_FLAG;	//坐标点类型（0：坐标点，1：站点）
							//markerOption.control_station=data[i].CONTROL_STATION; 	//坐标上下行状态
							//markerOption.station_addr=data[i].STATION_ADDR;			//坐标详细地址
							//markerOption.organization_id=data[i].ORGANIZATION_ID;	//坐标组织机构ID
							//markerOption.organization_name=data[i].ENTER_SHORT_NAME;//坐标组织机构名称
							markerOption.tipOption = addDataToTipContent("",data[i].INOUT_FLAG,"",data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].CONTROL_STATION,data[i].STATION_ADDR,data[i].ORGANIZATION_ID,data[i].ENTER_SHORT_NAME);
							//flash版本属性设置
							markerOption.picAgent=false;
							markerOption.imageAlign=MConstants.BOTTOM_CENTER;
							markerOption.isBounce=false;
							markerOption.isEditable=false;
							markerOption.canShowTip= false;
							markerOption.rotation=0;
							markerOption.hasShadow=false;
							
							//判断类型为坐标点还是站点，标注不同的图片（0：坐标点；1：站点）
							if(data[i].INOUT_FLAG == "0"){
								markerOption.imageUrl="../images/map/point1.png";
								//markerOption.imageSize=new MSize(30,30);
							}else{
								markerOption.imageUrl="../images/map/station1.png";  
								//markerOption.imageSize=new MSize(30,30);
								jQuery("input[name='carChoice']").each(function(){
						 			if(jQuery(this).attr("checked")){
						 				var checkValue= new Array();
						 				checkValue=jQuery(this).val().split("#!#");
							 				if(checkValue[0]==data[i].ID){
							 					markerOption.imageUrl="../images/map/station2.png";
							 					//markerOption.imageSize=new MSize(30,30);
							 					//markerOption.showFlag="searchCheckedAdd";
							 					markerOption.tipOption = addDataToTipContent("",data[i].INOUT_FLAG,"searchCheckedAdd",data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].CONTROL_STATION,data[i].STATION_ADDR,data[i].ORGANIZATION_ID,data[i].ENTER_SHORT_NAME);
							 					return false;}
							 		}else{
							 			markerOption.imageUrl="../images/map/station1.png";
							 			//markerOption.imageSize=new MSize(30,30);
							 			//markerOption.showFlag="searchAdd";
							 			markerOption.tipOption = addDataToTipContent("",data[i].INOUT_FLAG,"searchAdd",data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].CONTROL_STATION,data[i].STATION_ADDR,data[i].ORGANIZATION_ID,data[i].ENTER_SHORT_NAME);
							 			return true;
									}
							 	});
								markerOption.labelOption=addMarkerLabel(data[i].DIRECTION,MConstants.BOTTOM_CENTER);
							}
							
				      		Marker.id=data[i].ID;
				      		for(var j=0;j<arr.length;j++){
					      		if(arr[j].id==data[i].ID){
					      			arr.splice(i,1);
						      	}
					      	}
				   	  		arr.push(Marker);
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
							}
						}										
					}		
					
				},
				complete: function() { 
					setStatus();
	                jQuery.unblockUI(); 
	            }
			});
		  	  
	}

/*
* 地图注册点击事件
*/
function clickMap(event){
	//获取点击的覆盖点
	var mk = mapObj.getOverlayById(event.overlayId);
	//将前次点击点的颜色回复
	if(publicMapID!=event.overlayId){//（if1-start）如果触发事件的ID不是前一次缓存的ID (start)
		if(publicMapID!="BE66855BD543127FE0440019BB600AC6"){//(if2-start)
			//前一次缓存的ID不是新建事件-start
			if(mapObj.getOverlayById(publicMapID)!=null){//(if3-start)
				if(getPointSAById(publicMapID,"pointType") == "0"){//坐标点业务处理
					//坐标点信息使用浅蓝色图标标注
					var markerBefore = mapObj.getOverlayById(publicMapID);
					markerBefore.option.imageUrl="../images/map/point1.png";
					mapObj.addOverlay(markerBefore);
				}else{//站点业务处理
					var markerBefore = mapObj.getOverlayById(publicMapID);
				    //如果showFlag属性是(listAdd\searchCheckedAdd\searchListAdd)使用深蓝色图标，否则使用浅蓝色图标
					if(getPointSAByObj(markerBefore,"showFlag")=="listAdd"||getPointSAByObj(markerBefore,"showFlag")=="searchCheckedAdd"||getPointSAByObj(markerBefore,"showFlag")=="searchListAdd"){
						markerBefore.option.imageUrl="../images/map/station2.png";}else{markerBefore.option.imageUrl="../images/map/station1.png";}
					mapObj.addOverlay(markerBefore);
				}
			}//(if3-end)
		}else{//(if2-else)
			var mkForNew = mapObj.getOverlayById(event.overlayId);
			jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val(getPointSAByObj(mk,"realLng"));
			jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val(getPointSAByObj(mk,"realLat"));
			//坐标点站点业务处理判断开始
			if(getPointSAByObj(mkForNew,"pointType")=="0"){//坐标点业务处理
				mkForNew.option.imageUrl="../images/map/point1.png";
			}else{//站点业务处理
				   //如果showFlag属性是(listAdd\searchCheckedAdd\searchListAdd)使用深蓝色图标，否则使用浅蓝色图标
					if(getPointSAByObj(mkForNew,"mkForNew")=="listAdd"||getPointSAByObj(mkForNew,"mkForNew")=="searchCheckedAdd"||getPointSAByObj(mkForNew,"mkForNew")=="searchListAdd"){
						mkForNew.option.imageUrl="../images/map/station2.png";
					}else{
						mkForNew.option.imageUrl="../images/map/station1.png";}
				
			}
			//坐标点站点业务处理判断结束
			mapObj.addOverlay(mkForNew);
			addPoiMarker();
			return true;
		}//(if2-end)
	}//（if1-end）如果触发事件的ID不是前一次缓存的ID (end)
	
	//根据id将点覆盖物置于所有点的最顶层
    mapObj.setOverlayToTopById(mk.id);
	//设置地图中心点坐标
	mapObj.setCenter(mk.lnglat);//设置地图中心点的经纬度坐标

	//(点击地图上的点--图标变为红色的业务处理)如果触发事件的ID是前一次缓存的ID (start)
	if(getPointSAByObj(mk,"pointType")=="0"){
		mk.option.imageUrl="../images/map/point3.png";
		$("#deleteButton").css("display","none"); 
		$("#updateButton").css("display","none");
		$("#addButton").css("display","block"); 
	}else{
		mk.option.imageUrl="../images/map/station3.png";
		$("#deleteButton").css("display","block"); 
		$("#updateButton").css("display","block");
		$("#addButton").css("display","none"); 
	}
	mk.option.rotation=360;
	mapObj.updateOverlay(mk);
	//记录第一次点击事件的id
	publicMapID=event.overlayId;
	var lng=mk.lnglat.lngX;
	var lat=mk.lnglat.latY;
	var pixel=mapObj.fromLngLatToContainerPixel(new MLngLat(lng,lat));
	//点击后弹出浮动框
	/*新增站点坐标隐藏修改删除坐标*/
	setStationInfo(event.overlayId,true,(pixel.x+150),(pixel.y-100));
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(2).children("#site_id").val(event.overlayId);
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val('');//此处先清空，如果是坐标点则不再赋值，如果是站点重新赋值
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val(getPointSAByObj(mk,"realLng"));
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val(getPointSAByObj(mk,"realLat"));
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(7).children("td").eq(1).children("#sichen_addr").val(getPointSAByObj(mk,"station_addr"));
	if(getPointSAByObj(mk,"pointType")!="0"){//站点业务处理-start
		var sflag="yes";
		if(mk.option.labelOption.content!=''){
			  jQuery.ajax({
					type: 'POST', 
					url: '../station/getStationFlag.shtml',
					async:false,
					data: {site_id:event.overlayId
					},
					success: function(stationflag) {
						sflag=stationflag;
					}
				});
			jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val(mk.option.labelOption.content);
			if(sflag=="yes"){
				//jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#control_station").attr('disabled',true);	
				//jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#organizationName").attr('disabled',true);
			}else{
				//jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#control_station").attr('disabled',false);	
				//jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#organizationName").attr('disabled',false);
			}
			//jQuery("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#control_station").val(0);
			//jQuery("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#organizationName").val(getPointSAByObj(mk,"organization_name"));
			jQuery("#dataTable").children("tbody").children("tr").eq(7).children("td").eq(1).children("#sichen_addr").val(getPointSAByObj(mk,"station_addr"));
			//jQuery("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#orgidtag").val(getPointSAByObj(mk,"organization_id"));
		}				
	}//站点业务处理-end
}//clickMap方法结束

	/*
	* 站点设置弹出层
	*/
	function setStationInfo(stationID,flag,centerPixX,centerPixY){
		if(flag==false){
			if(mapObj.getOverlayById(publicMapID)!=null){
				//mapObj.getOverlayById(publicMapID).option.pointType
				//getPointSAById(publicMapID,"pointType")
				if(getPointSAById(publicMapID,"pointType")=="0"){
					var markerBefore = mapObj.getOverlayById(publicMapID);
					markerBefore.option.imageUrl="../images/map/point1.png";
					//markerBefore.option.imageSize=new MSize(30,30);
					mapObj.addOverlay(markerBefore);
				}else{
					var markerBefore = mapObj.getOverlayById(publicMapID);
					//markerBefore.option.showFlag -- getPointSAByObj(markerBefore,"showFlag")
					if(getPointSAByObj(markerBefore,"showFlag")=="listAdd"||getPointSAByObj(markerBefore,"showFlag")=="searchCheckedAdd"||getPointSAByObj(markerBefore,"showFlag")=="searchListAdd"){
						markerBefore.option.imageUrl="../images/map/station3.png";
						//markerBefore.option.imageSize=new MSize(30,30);
					}else{
						markerBefore.option.imageUrl="../images/map/station1.png";
						//markerBefore.option.imageSize=new MSize(30,30);
					}
					mapObj.addOverlay(markerBefore);				
				}
			}
			publicMapID=stationID;
		}
		art.dialog({
			id:'stationID',
			title:flag==true?'站点设置':'新建站点',
			lock:flag,
			width:450,
			height:270,
			//left:145,
			//top:centerPixY,
			//left:centerPixX,
			//top:centerPixY,
			fixed:true,
		    content:jQuery("#stationInfoDiv").html(),
		    closeFn:function(){
				if(publicMapID=="BE66855BD543127FE0440019BB600AC6"){ 
					mapObj.removeOverlayById(publicMapID);
					publicMapID='';
				}
		    }
		});
		if(flag==false){
			//jQuery("#stationID #deleteButton").hide();
			//jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(0).children("#deleteButton").hide();
			//jQuery("#stationIDcontent").children("#lonlatTable").children("tbody").children("tr").eq(3).children("td").eq(2).children("#resetButton").hide();
		}
		jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(3).children("#up_img").bind('click', function(event){
			add_Latitude();
		});

		jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(2).children("#left_img").bind('click', function(event){
			decrease_Longitude();
		});

		jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(4).children("#right_img").bind('click', function(event){
			add_Longitude();
		});

		jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(3).children("#down_img").bind('click', function(event){
			decrease_Latitude();
		});
	}

	function checkForm() {
		var pass = true; 
		
		jQuery("#stationID").find("input").each(function(index){
			if(jQuery.trim(jQuery(this).parent().prev().text())=="站点名称：") {
				var ab=jQuery(this).val();
				var xy = ab.replace(/(^\s*)|(\s*$)/g, "");
				if(xy=="") {
					text = jQuery(this).parent().prev().text();
					jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children(".noticeMsg").html("*请输入！");
					pass = false;
				} else {
					jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children(".noticeMsg").html("*");
				};
			};
			
			// add by jinp begin 20120813
			if(jQuery.trim(jQuery(this).parent().prev().text())=="经度：") {
				if(jQuery(this).val()!=''&&jQuery(this).val().length>0) {
					if(!checkLongitude(jQuery(this).val())) {
						jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children(".noticeMsg").html("*经度格式不正确！");
						jQuery(this).focus(); 
						pass = false;
					} else{
						jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children(".noticeMsg").html("*");
					}
				} else {
					jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children(".noticeMsg").html("*请输入！");
					pass = false;
				}
			}
			if(jQuery.trim(jQuery(this).parent().prev().text())=="纬度：") {
				if(jQuery(this).val()!=''&&jQuery(this).val().length>0) {
					if(!checkLatitude(jQuery(this).val())) {
						//alert("纬度格式不正确！");
						jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children(".noticeMsg").html("*纬度格式不正确！");
						jQuery(this).focus(); 
						pass = false;
					} else{
						jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children(".noticeMsg").html("*");
					}
				} else {
					jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children(".noticeMsg").html("*请输入！");
					pass = false;
				}
			}
		})
		return pass;
	}

	function checkLonLat(str){
		var result=str.match(/^(((180)|(((1[0-7]\d)|(\d{1,2}))(\.\d+)?)))$/g);		
		if(result==null){
			return false;
		}else{
			return true;
		};
	}

	
	/** 经纬度必填项 **/
	function checkLon(obj) {
  	  if(checkLonLat(jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val())&&checkLonLat(jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val())){
      	try{
            var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();
            if(!checkLatitude(site_latitude)) {
        		return false;
        	}
        	var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val();
			if(!checkLongitude(site_longitude)) {
				return false;
			}
            addPoiMarker();
          } catch(e) {
             ;
          }
      }else{
      	return false;
      }
	}

	function saveMarker(thisMarker,id){
		if(id!=null){
			var lonValue=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val();
			var latValue=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();
			var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val();
			var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();
			var site_name=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val();
			site_name = site_name.replace(/(^\s*)|(\s*$)/g, "");
			var control_station="";//jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#control_station").val();
			var organizationName="";//jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#organizationName").val();
			var sichen_addr=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(7).children("td").eq(1).children("#sichen_addr").val();
			sichen_addr = sichen_addr.replace(/(^\s*)|(\s*$)/g, "");
			var orgidtag="";//jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#orgidtag").val();
			var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val();
			var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();
			  if(latValue != "" && lonValue != "") {
				  jQuery.ajax({
						type: 'POST', 
						url: '../station/getSingleLONLAT.shtml',
						async:false,
						data: {
							longitude:lonValue,
							latitude:latValue
						},
						success: function(lonlatString) {
							if(lonlatString==null||lonlatString=="582.5422219509549,582.5422219509549"){
								return false;
							}
					    	var strs= new Array();
					    	strs=lonlatString.split(",");
					    	lonValue=strs[0];
					    	latValue=strs[1];
					    	var markerNew=new MMarkerOptions();   
							markerNew.imageUrl="../images/map/station3.png";
							markerNew.imageAlign=MConstants.BOTTOM_CENTER;
							markerNew.isBounce=false;
							markerNew.isEditable=false;
							markerNew.canShowTip= false;
							markerNew.rotation=0;
							markerNew.hasShadow=false;
							markerNew.picAgent=false;
							//markerNew.imageSize=new MSize(30,30);
							//markerNew.picAgent=false;
							//markerNew.realLng=site_longitude;	//坐标真实经度
							//markerNew.realLat=site_latitude;	//坐标真实纬度
							//markerNew.initLng=site_longitude;	//坐标初始经度
							//markerNew.initLat=site_latitude;	//坐标初始纬度
							//markerNew.pointType='1';	//坐标点类型（0：坐标点，1：站点）
							//markerNew.showFlag="listAdd";
							//markerNew.control_station=control_station;
							//markerNew.station_addr=sichen_addr;
							//markerNew.organization_id=orgidtag;
							//markerNew.organization_name=organizationName;				
							//update by fxy start
							markerNew.tipOption = addDataToTipContent("","1","listAdd",site_longitude,site_latitude,site_longitude,site_latitude,control_station,sichen_addr,orgidtag,organizationName);
							//update by fxy end
							markerNew.labelOption=addMarkerLabel(site_name,MConstants.BOTTOM_CENTER);
							var Marker="";
							Marker = new MMarker(new MLngLat(lonValue,latValue),markerNew);  
							Marker.id=id;
							mapObj.addOverlay(Marker);
							var thisMarker = mapObj.getOverlayById(id);
							mapObj.addEventListener(thisMarker,MConstants.MOUSE_CLICK,clickMap);
						}
					});
			  }
		}else{
			var site_name=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val();
			site_name = site_name.replace(/(^\s*)|(\s*$)/g, "");
			thisMarker.option.labelOption=addMarkerLabel(site_name,MConstants.BOTTOM_CENTER);
			var realLng=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val();
			var realLat=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();
			var initLng=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val(); //坐标初始经度
			var initLat=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();	//坐标初始纬度
			var showFlag="listAdd";
			var pointType="1";
			//var control_station=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#control_station").val();
			//var station_addr=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_addr").val();
			//station_addr = station_addr.replace(/(^\s*)|(\s*$)/g, "");
			//var organization_id=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#orgidtag").val();
			//var organization_name=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#organizationName").val();
			//设置业务属性
			thisMarker.option.tipOption=addDataToTipContent("",pointType,showFlag,realLng,realLat,initLng,initLat,control_station,station_addr,organization_id,organization_name);		
			thisMarker.option.imageUrl="../images/map/station2.png";
			mapObj.addOverlay(thisMarker);
		}
		
	}

	
	function saveListData(mid){
		var site_name=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val();
		site_name = site_name.replace(/(^\s*)|(\s*$)/g, "");
	//	var control_station=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#control_station").val();
	//	var organizationName=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#organizationName").val();
		var sichen_addr=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(7).children("td").eq(1).children("#sichen_addr").val();
		sichen_addr = sichen_addr.replace(/(^\s*)|(\s*$)/g, "");
		//var orgidtag=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#orgidtag").val();
		var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val();
		var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();
		//site_name site_latitude sichen_addr site_longitude organization_id organizationName
		jQuery('#checkbox_'+mid).val(mid+'#!#'+site_name+'#!#'+site_latitude+'#!#'+sichen_addr+'#!#'+site_longitude+'#!##!##!#');
		
		jQuery('#show_'+mid).attr("href",'javascript:alertSiteDetail(\'' + site_name + '\',\''+site_longitude+ '\',\''+site_latitude+ '\',\''+sichen_addr + '\',\''+mid+'\')');
		jQuery('#name_'+mid).html(site_name);
		//jQuery('#property_'+mid).html(control_station=='0'?'上学':'放学');
		jQuery('#lon_'+mid).html(site_longitude);
		jQuery('#lat_'+mid).html(site_latitude);
		jQuery('#addr_'+mid).html(sichen_addr);
		//jQuery('#orgid_'+mid).html(orgidtag);
		//jQuery('#orgname_'+mid).html(organizationName);
	}

	// add by jinp begin 2012-08-13
	/** 经度必填项 **/
	function checkLongitude(value) {
		var regFloat = /^(?:[0-9]|[1-9][0-9]|1[0-7][0-9])\.(\d)+$/;
		var regInteger = /^(?:[0-9]|[1-9][0-9]|1[0-7][0-9]|180)$/;
		if(regFloat.test(value)&&parseFloat(value)>73&&parseFloat(value)<137) {
			return true;
		} else if(regInteger.test(value)&&parseInt(value)>73&&parseInt(value)<137){
			return true;
		} else {
			return false;
		}
	}
	
	function checkLatitude(value) {
		var regFloat = /^(?:[0-9]|[1-8][0-9])\.(\d)+$/;
		var regInteger = /^(?:[0-9]|[1-8][0-9]|90)$/;
		if(regFloat.test(value)&&parseFloat(value)>1&&parseFloat(value)<55) {
			return true;
		} else if(regInteger.test(value)&&parseInt(value)>1&&parseInt(value)<55){
			return true;
		} else {
			return false;
		}
	}
	// add by hand end 2013-06-05

function updateStation(){
	if(!checkForm()) {
		return false;
	}
		
	var site_id=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(2).children("#site_id").val();
	
	var site_name=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val();
	
	site_name = site_name.replace(/(^\s*)|(\s*$)/g, "");
	
	var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val();
	var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();
	var sichen_addr=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(7).children("td").eq(1).children("#sichen_addr").val();
	sichen_addr = formatSpecialChar(sichen_addr.replace(/(^\s*)|(\s*$)/g, ""));
	
	jQuery.ajax({
		type: 'POST',  
		dataType: 'json',
		url: '../stationGrid/editStation_do.shtml',	
		async:false,
		data: {
			site_id: site_id,
			site_name:site_name,
			longitude:site_longitude,
			latitude:site_latitude,
			sichen_addr:sichen_addr
		},
		success: function(data) {
			if(data.returns=="success"){
				var thisMarker = mapObj.getOverlayById(site_id);//publicMapID
				saveMarker(thisMarker,site_id);//publicMapID
				saveListData(site_id);
				art.dialog.get('stationID').close();
				//新增
				if(jQuery('#checkbox_'+site_id).attr("checked")==false)
					mapObj.removeOverlayById(site_id);
				alert("站点修改成功！");
				//searchStation();
			}
			if(data.returns=='allready') {
				//alert("站点已添加,请勿修改！");
				var thisMarker = mapObj.getOverlayById(site_id);//publicMapID
				saveMarker(thisMarker,site_id);//publicMapID
				saveListData(site_id);
				art.dialog.get('stationID').close();
				//新增
				if(jQuery('#checkbox_'+site_id).attr("checked")==false)
					mapObj.removeOverlayById(site_id);
				alert("站点修改成功！");
			}
			if(data.returns=="error"){
				alert("站点修改失败！");
			}
		}  
	});
	}	
	
/*
* 确定操作
*/
function addStation(){
	var site_name=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val();
	site_name = site_name.replace(/(^\s*)|(\s*$)/g, "");
	//var control_station=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#control_station").val();
	//var organizationName=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#organizationName").val();
	var sichen_addr=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(7).children("td").eq(1).children("#sichen_addr").val();
	sichen_addr = formatSpecialChar(sichen_addr.replace(/(^\s*)|(\s*$)/g, ""));
	//var orgidtag=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#orgidtag").val();
	var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val();
	var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();
	if(publicMapID!="BE66855BD543127FE0440019BB600AC6"){
		var thisMarker = mapObj.getOverlayById(publicMapID);
		if(getPointSAByObj(thisMarker,"pointType")=="0"){//通过坐标点添加站点的操作
			if(checkForm()){
				jQuery.ajax({
					type: 'POST', 
					dataType: 'json', 
					url: '../stationGrid/addStation_do.shtml',	
					data: {
						longitude:site_longitude,
						latitude:site_latitude,
						site_name:site_name,
						//control_station:control_station,
						sichen_addr:sichen_addr,
						//organization_id:orgidtag,
						pointID:publicMapID,
						sortname:jQuery('#gala').flex_sortname(),
						sortorder:jQuery('#gala').flex_sortorder(),
						esite_name:jQuery('#stationName').val(),
						econtrol_station:jQuery('#stationProperties').val()
						},
					async:false,
					success: function(data) {
						if(data.returns=="success"){
							jQuery('#gala').flexOptions({
								newp: Math.ceil(data.returnOrder/jQuery('#gala').flex_rp())
							});
							//jQuery('#gala').flex_setParmChecked("checkbox_"+data.returnID);
							jQuery('#gala').flexReload();
							
							mapObj.removeOverlayById(publicMapID);
							saveMarker(thisMarker,data.returnID);
							publicMapID=data.returnID;
							art.dialog.get('stationID').close();
							alert("站点添加成功！");
						}
						if(data.returns=="error"){
							alert("站点添加失败！");
						}
					}});
				}
			    //else{
					/*jQuery.ajax({
						type: 'POST',  
						url: '../station/updateCollection_ID.shtml',	
						data: {
							site_id: publicMapID,
							longitude:jQuery("#stationID #site_longitude").val(),
							latitude:jQuery("#stationID #site_latitude").val()
						}
					});*/
				//}
			}else{
				if(checkForm()){
					jQuery.ajax({
						type: 'POST',  
						dataType: 'json',
						url: '../stationGrid/editStation_do.shtml',	
						async:false,
						data: {
							site_id: publicMapID,
							longitude:site_longitude,
							latitude:site_latitude,
							site_name:site_name,
							//control_station:control_station,
							sichen_addr:sichen_addr
							//organization_id:orgidtag
						},
						success: function(data) {
							if(data.returns=="success"){
								saveMarker(thisMarker);
								saveListData(publicMapID);
								art.dialog.get('stationID').close();
								alert("站点修改成功！");
							}
							if(data.returns=="error"){
								alert("站点修改失败！");
							}
						}  
					});
				}
			}
		}else{
			var thisMarker = mapObj.getOverlayById(publicMapID);
			if(checkForm()){
				jQuery.ajax({
					type: 'POST',  
					dataType: 'json',
					url: '../stationGrid/addStation_do.shtml',
					async:false,
					data: {
						longitude:site_longitude,
						latitude:site_latitude,
						site_name:site_name,
						//control_station:control_station,
						sichen_addr:sichen_addr,
						//organization_id:orgidtag,
						sortname:jQuery('#gala').flex_sortname(),
						sortorder:jQuery('#gala').flex_sortorder(),
						esite_name:jQuery('#stationName').val(),
						econtrol_station:jQuery('#stationProperties').val()
					},
					success: function(data) {
						if(data.returns=="success"){
							jQuery('#gala').flexOptions({
								newp: Math.ceil(data.returnOrder/jQuery('#gala').flex_rp())
							});
							jQuery('#gala').flex_setParmChecked("checkbox_"+data.returnID);
							jQuery('#gala').flexReload();
							thisMarker.id=data.returnID;
							//mapObj.removeOverlayById(publicMapID);
							saveMarker(thisMarker,data.returnID);
							//publicMapID=data.returnID;
							art.dialog.get('stationID').close();
							alert("站点添加成功！");
						}
						if(data.returns=="error"){
							alert("站点添加失败！");
						}
					}
				});
			}
		}
	}

	function deleteCell(mid){
		jQuery('#row' + mid).hide()
	}

	/*
	* 删除
	*/
	function deleteStation(){
var site_id=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(2).children("#site_id").val();

		
if(confirm("您确定要删除该站点吗？")){
	jQuery.ajax({
		type: 'POST',  
		url: '../stationGrid/deleteStation.shtml',	
		data: {site_id: site_id},	
		dataType: 'json',
		success: function(data) {
			if(data.returns=="success"){
				mapObj.removeAllOverlays();
				jQuery('#gala').flexReload();
				alert("站点删除成功！");
				art.dialog.get('stationID').close();
			}
			if(data.returns=="forbid"){
				alert("站点已被分配，不能被删除！");
			}
			if(data.returns=="error"){
				alert("站点删除失败！");
			}
			//publicMapID="";
		}  
	});
	
}
		
		
		
		if(publicMapID!="BE66855BD543127FE0440019BB600AC6"){
			var thisMarker = mapObj.getOverlayById(publicMapID);
			//getPointSAByObj(thisMarker,"pointType")
			if(getPointSAByObj(thisMarker,"pointType")=="0"){
				if(confirm("您确定要删除该坐标点吗？ 注：坐标点删除后，将无法恢复。")){
					jQuery.ajax({
						type: 'POST',  
						url: '../station/deleteCollection_ID.shtml',	
						data: {site_id: publicMapID},	
						success: function() {  
							mapObj.removeOverlayById(publicMapID);
							publicMapID="";
							alert("坐标点删除成功！");
						}  
					});
				}
			}else{
				/* if(confirm("您确定要删除该站点吗？")){
					jQuery.ajax({
						type: 'POST',  
						url: '../stationGrid/deleteStation.shtml',	
						data: {site_id: site_id},	
						dataType: 'json',
						success: function(data) {
							if(data.returns=="success"){
								mapObj.removeOverlayById(site_id);
								//deleteCell(publicMapID);
								jQuery('#gala').flexReload();
								alert("站点删除成功！");
								art.dialog.get('stationID').close();
							}
							if(data.returns=="forbid"){
								alert("站点已被分配，不能被删除！");
							}
							if(data.returns=="error"){
								alert("站点删除失败！");
							}
							//publicMapID="";
						}  
					});
					
				} */
			}
		}else{
			//publicMapID="";
		}
		//art.dialog.get('stationID').close();
	}
	
	/*
	 * 复位
	 */
	function resetStation(){
		var thisMarker = mapObj.getOverlayById(publicMapID);
		//getPointSAByObj(thisMarker,"initLng") thisMarker.option.initLng
		jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val(getPointSAByObj(thisMarker,"initLng"));
		//getPointSAByObj(thisMarker,"initLat") thisMarker.option.initLat
		jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val(getPointSAByObj(thisMarker,"initLat"));
		addPoiMarker();
	}
	
	/*
	* 取消
	*/
	function cancelStation(){
		if(publicMapID!="BE66855BD543127FE0440019BB600AC6"){
			resetStation();
		}else{
			mapObj.removeOverlayById(publicMapID);
			publicMapID='';
		}
		art.dialog.get('stationID').close();
	}
		
/** 地图上单点移动操作 **/
function addPoiMarker() {
  var lonValue=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val();
  var latValue=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();
  var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val();
  var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();
  var site_name=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val();
  if(typeof(lonValue)=="undefined"){
	  latValue="";
  }

  if(typeof(latValue)=="undefined"){
	  latValue="";
  }
  if(latValue != "" && lonValue != "") {
	  jQuery.ajax({
			type: 'POST', 
			url: '../station/getSingleLONLAT.shtml',	
			data: {
				longitude:lonValue,
				latitude:latValue
			},
			success: function(lonlatString) {
				if(lonlatString==null||lonlatString=="582.5422219509549,582.5422219509549"){
					return false;
				}
		    	var strs= new Array();
		    	strs=lonlatString.split(",");
		    	lonValue=strs[0];
		    	latValue=strs[1];
				if(publicMapID!="BE66855BD543127FE0440019BB600AC6"){//点击操作触发
					var thisMarker = mapObj.getOverlayById(publicMapID);
					//如果有勾选
					if(thisMarker) {
				    	thisMarker.lnglat.lngX=lonValue;
				    	thisMarker.lnglat.latY=latValue;
				    	//thisMarker.option.realLng=site_longitude;
				    	//thisMarker.option.realLat=site_latitude;
				    	setPointSAByObj(thisMarker,"realLng",site_longitude);
				    	setPointSAByObj(thisMarker,"realLat",site_latitude);
				    	mapObj.markerMoveTo(publicMapID, new MLngLat(lonValue,latValue));
				    	mapObj.addOverlay(thisMarker,true);
					}
				}else{//新建动作触发
					var markerNew=new MMarkerOptions();   
					markerNew.imageUrl="../images/map/station3.png"; 
					//markerNew.imageSize=new MSize(30,30);
					markerNew.picAgent=false;
					//markerNew.realLng=site_longitude;	//坐标真实经度
					//markerNew.realLat=site_latitude;	//坐标真实纬度
					//markerNew.initLng=site_longitude;	//坐标初始经度
					//markerNew.initLat=site_latitude;	//坐标初始纬度
					//markerNew.pointType='1';	//坐标点类型（0：坐标点，1：站点）
					//markerNew.showFlag="listAdd";
					//update by fxy
					//将业务属性缓存
					markerNew.tipOption = addDataToTipContent("","1","listAdd",site_longitude,site_latitude,site_longitude,site_latitude,"","","","");				
					//flash版本设置的属性
					markerNew.imageAlign=MConstants.BOTTOM_CENTER;
					markerNew.isBounce=false;
					markerNew.isEditable=false;
					markerNew.canShowTip= false;
					markerNew.rotation=0;
					markerNew.hasShadow=false;
					markerNew.labelOption=addMarkerLabel(site_name,MConstants.BOTTOM_CENTER);
					var Marker="";
					Marker = new MMarker(new MLngLat(lonValue,latValue),markerNew);  
					Marker.id=publicMapID;
					mapObj.addOverlay(Marker,true);
					var thisMarker = mapObj.getOverlayById(publicMapID);
					mapObj.addEventListener(thisMarker,MConstants.MOUSE_CLICK,clickMap);
			  	}
			}
		});
  }
}

//增加纬度
function add_Latitude(){	
	var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();
	
	if(site_latitude==null||site_latitude=="")
		return false;
	if(!checkLatitude(site_latitude)) {
		alert("纬度格式不正确！");
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").focus(); 
		return false;
	}
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val(accAdd(site_latitude,0.0001));
	addPoiMarker();
}

//减小纬度
function decrease_Latitude(){
	var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val();

	if(site_latitude==null||site_latitude=="")
		return false;
	if(!checkLatitude(site_latitude)) {
		alert("纬度格式不正确！");
		jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").focus(); 
		return false;
	}
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(1).children("#site_latitude").val(accSub(site_latitude,0.0001));
	addPoiMarker();
}

//增加经度
function add_Longitude(){
	var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val();
	if(site_longitude==null||site_longitude=="")
		return false;
	if(!checkLongitude(site_longitude)) {
		alert("经度格式不正确！");
		jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").focus();
		return false;
	}
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val(accAdd(site_longitude,0.0001));
	addPoiMarker();
}

//减小经度
function decrease_Longitude(){
	var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val();
	if(site_longitude==null||site_longitude=="")
		return false;
	if(!checkLongitude(site_longitude)) {
		alert("经度格式不正确！");
		jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").focus();
		return false;
	}
	jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_longitude").val(accSub(site_longitude,0.0001));
	addPoiMarker();
}

/**
 * 【将业务属性添加到对应点覆盖物的tip_content中】
 */
function addDataToTipContent(content,pointType,showFlag,realLng,realLat,initLng,initLat,control_station,station_addr,organization_id,organization_name){
	var tipOption = new MTipOptions();
    //将业务数据保存在对应点的MTipOptions的content中
	//说明：各字段以"英文逗号"分隔,第一位预留
    tipOption.tipType = MConstants.HTML_CUSTOM_TIP;
    tipOption.content = content+","+pointType+","+showFlag+","+realLng+","+realLat+","+initLng+","+initLat+","+control_station+","+station_addr+","+organization_id+","+organization_name;
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
	case "pointType": return data_array[1];break;
	case "showFlag": return data_array[2];break;
	case "realLng": return data_array[3];break;
	case "realLat": return data_array[4];break;
	case "initLng": return data_array[5];break;
	case "initLat": return data_array[6];break;
	//case "control_station": return data_array[7];break;
	case "station_addr": return data_array[8]=="null"?"":data_array[8];break;
	//case "organization_id": return data_array[9];break;
	//case "organization_name": return data_array[10];break;
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
	case "pointType": return data_array[1];break;
	case "showFlag": return data_array[2];break;
	case "realLng": return data_array[3];break;
	case "realLat": return data_array[4];break;
	case "initLng": return data_array[5];break;
	case "initLat": return data_array[6];break;
	//case "control_station": return data_array[7];break;
	case "station_addr": return data_array[8];break;
	//case "organization_id": return data_array[9];break;
	//case "organization_name": return data_array[10];break;
	default: return "";
	}
}
 
/**
 * 根据点对象设置对应的业务属性
 * set point Service Attribute
 * mk-->点覆盖物的对象
 * attribute-->属性名
 * value--->新的属性值
 */
function setPointSAByObj(mk,attribute,value){
	var tip = mk.option.tipOption;
	var tip_content = mk.option.tipOption.content;
	//拆分tip_content
	var data_array= new Array();
	data_array=tip_content.split(",");
	
	//根据属性值 返回对应的属性值
	switch(attribute){
	case "content": data_array[0]=value;break;
	case "pointType": data_array[1]=value;break;
	case "showFlag": data_array[2]=value;break;
	case "realLng": data_array[3]=value;break;
	case "realLat": data_array[4]=value;break;
	case "initLng": data_array[5]=value;break;
	case "initLat": data_array[6]=value;break;
	//case "control_station": data_array[7]=value;break;
	case "station_addr": data_array[8]=value;break;
	//case "organization_id": data_array[9]=value;break;
	//case "organization_name": data_array[10]=value;break;
	default: alert("点对象的异常业务属性");
	}
	
	tip_content = data_array[0]+","+data_array[1]+","+data_array[2]+","+data_array[3]+","+data_array[4]+","+data_array[5]+","+data_array[6]+","+data_array[7]+","+data_array[8]+","+data_array[9]+","+data_array[10];
	//重新设置业务数据
	tip.content = tip_content;
	//重新复制给点对象
	mk.option.tipOption = tip;
}

//聚合点设置
var clusterOptions=new MClusterOptions();  
function setClusters(){  
	//聚合的范围，以像素为单位  
	clusterOptions.gridSize=27;  
	//设置聚合的最大级别  
	clusterOptions.maxZoom=17;  
	//聚合点显示文字的样式  
	clusterOptions.fontStyle=new MFontStyle();  
	clusterOptions.fontStyle.color=0xfff000;  
	clusterOptions.fontStyle.size=20;  
	//设置绽放聚合中心点图标的URL，用于自定义绽放聚合中心点图标  
	//clusterOptions.centerMarkerURL="http://api.mapabc.com/flashmap/2.3.3/images/m1.png";  
	//设置绽放聚合绽放点图标的URL，用于自定义绽放聚合绽放点的图标  
	//clusterOptions.aroundMarkerURL="http://www.mapabc.com/images/point.png";  
	//绽放点离中心点的距离，以像素为单位  
	clusterOptions.flareDistance=30;  
	clusterOptions.isUseMarkerIcon = false;  
}
//添加普通聚合方式
function normal(){  
	setClusters();  
	mapObj.setClusterState(MClusterOptions.NORMAL_CLUSTER, clusterOptions);
}
//取消聚合方式
function cancelCluster(){
	mapObj.setClusterState(MClusterOptions.NO_CLUSTER, clusterOptions);
}

</script>