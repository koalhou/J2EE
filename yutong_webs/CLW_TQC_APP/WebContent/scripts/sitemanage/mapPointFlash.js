/**
 * 说明：
 * point标识：
 * searchAdd  ---search
 * searchCheckedAdd
 * listAdd
 */
var mapPointFlash = function(){
	//this.mapInit();
	/** 地图对象 **/
	this.vehicleln = "";
	this.vehicleVin = "";
};

var mapObj = null;
var publicMapID="";
var getflag=0;
mapPointFlash.prototype = {
		mapInit: function () {
			var mapOptions = new MMapOptions();//构建地图辅助类
			mapOptions.zoom=13;//要加载的地图的缩放级别   
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
			mapOptions.logoUrl="../images/xiaocheImage/mask.png";
			//背景图片
			mapOptions.groundLogo="../images/xiaocheImage/mapbackgroud.jpg";
//			113.669379, 34.7522
			var mlglat = new MLngLat("113.686","34.693",MConstants.COORD_TYPE_OFFSET);
			mapOptions.center = mlglat;
//			mapOptions.viewBounds = new MLngLatBounds(new MLngLat('113.505592','34.660322'),new MLngLat('113.887367','34.846776'));//设置为郑州
			mapObj=new MMap("iCenter",mapOptions); //地图初始化   
			mapObj.setKeyboardEnabled(false);
			//地图加载完成
			mapObj.addEventListener(mapObj,MConstants.MAP_READY,mapPointFlashObj.map_ready);
			mapObj.addEventListener(mapObj,MConstants.ZOOM_CHANGED,mapPointFlashObj.setStatus);
		},
		map_ready: function (param){  
			//初始化加载点
			mapPointFlashObj.initMapList();
		},
		//设置聚合的回调方法--设置聚合
		setStatus: function (){
			if(jQuery("#pointTogether").attr("checked")){
				mapPointFlashObj.normal();
			}else{
				mapPointFlashObj.cancelCluster();
			}
		},
		addMarkerLabel: function (pointname,direction){   
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
		},
		/* 选择CHECKBOX按钮，在地图上绘点 
		 * 如果站点是通过查询条件，已在地图上存在，那么修改该点的图片；
		 * 否则直接在地图上绘点。
		 */
		addMarkerToMap: function (checkValueStr,flag) {
			var checkValue= new Array();
			checkValue=checkValueStr.split("#!#");
			var stationID=checkValue[0];
			var stationName=checkValue[1];
			var lonValue =  checkValue[2];
			var latValue = checkValue[3];
			if(lonValue >=0 && lonValue <=180 && latValue>=0 && latValue <=90){//(if1-start)
				if(mapObj.getOverlayById(stationID)!=null){//地图已经存在该点,选择复选框
					var thisMarker = mapObj.getOverlayById(stationID);
					if(mapPointFlashObj.getPointSAByObj(thisMarker,"showFlag")!="listAdd"){
						thisMarker.option.imageUrl="../images/xiaocheImage/addoil.png";
						//根据对象设置业务属性
						mapPointFlashObj.setPointSAByObj(thisMarker,"showFlag","searchCheckedAdd");
						mapObj.updateOverlay(thisMarker);
					}
					if(flag){
						var st=new Object();
						st.overlayId=checkValue[0];
						mapPointFlashObj.clickMap(st);
					}
				}else{//在地图上无该站点的标注，选中复选框的操作
					jQuery.ajax({
						type: 'POST', 
						url: 'getSingleLONLAT.shtml',	
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
							markerOption.imageUrl="../images/xiaocheImage/addoil.png"; 
							//markerOption.imageSize=new MSize(30,30); 
							markerOption.picAgent=false;
							//设置点的标注参数选项  (自定义的属性) 
							markerOption.labelOption = mapPointFlashObj.addMarkerLabel(stationName,MConstants.BOTTOM_CENTER);
							//flash版本设置的属性
							markerOption.imageAlign=MConstants.BOTTOM_CENTER;
							markerOption.isBounce=false;
							markerOption.isEditable=false;
							markerOption.canShowTip= false;
							markerOption.rotation=0;
							markerOption.hasShadow=false;
							markerOption.tipOption = mapPointFlashObj.addDataToTipContent("","1","listAdd",checkValue[2],checkValue[3],checkValue[2],checkValue[3],checkValue[4],checkValue[5],checkValue[6],checkValue[7]);					
							var Marker="";
							Marker = new MMarker(new MLngLat(lonValue,latValue),markerOption);  
							Marker.id=checkValue[0];
							mapObj.addOverlay(Marker,true);
							mapObj.addEventListener(Marker,MConstants.MOUSE_CLICK,mapPointFlashObj.clickMap);//update by fxy
							if(flag){
								var st=new Object();
								st.overlayId=checkValue[0];
								mapPointFlashObj.clickMap(st);
							}
						}
					});
				}
			}
			
		},
		/**
		 * 显示列表中所有的站点
		 * @param stationName
		 * @param stationProperties
		 */
		selectAllStation: function (stationName,stationProperties){
			var arr = new Array();
			jQuery.ajax({
				type: 'POST', 
				dataType: 'json', 
				url: '../sitGrid/getAllStationInfo.shtml',	
				data: {
					site_name:stationName,
					rp:jQuery('#gala').flex_rp(),
					page:jQuery('#gala').flex_current_page(),
					sortname: jQuery('#gala').flex_sortname(),
					sortorder: jQuery('#gala').flex_sortorder()
				},
				beforeSend:function(){
					//jQuery.blockUI({ message: "<h1>站点绘制中,请稍等...</h1>" });
				},
				success: function(data) {
					for(var i=0;i<data.length;i++){
						if(data[i].LONGITUDE >=0 && data[i].LONGITUDE <=180 && data[i].LATITUDE>=0 && data[i].LATITUDE <=90){
							if(mapObj.getOverlayById(data[i].ID)!=null){
								var thisMarker = mapObj.getOverlayById(data[i].ID);
								//thisMarker.option.showFlag -- getPointSAByObj(thisMarker,"showFlag")
								if(mapPointFlashObj.getPointSAByObj(thisMarker,"showFlag")!="listAdd"){
									thisMarker.option.imageUrl="../images/xiaocheImage/addoil.png";
									//thisMarker.option.showFlag="searchCheckedAdd";
									mapPointFlashObj.setPointSAByObj(thisMarker,"showFlag","searchCheckedAdd");
									thisMarker.option.rotation=360;
									mapObj.updateOverlay(thisMarker);
								}
							}else{
								var markerOption=new MMarkerOptions(); 
								//将业务属性缓存
								markerOption.tipOption = mapPointFlashObj.addDataToTipContent("","1","listAdd",data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].CONTROL_STATION,data[i].STATION_ADDR,data[i].ORGANIZATION_ID,data[i].ENTER_SHORT_NAME);				
								//update by fxy end						
								markerOption.imageUrl="../images/xiaocheImage/addoil.png";
								markerOption.picAgent=false;
								//flash版本设置的属性
								markerOption.imageAlign=MConstants.BOTTOM_CENTER;
								markerOption.isBounce=false;
								markerOption.isEditable=false;
								markerOption.canShowTip= false;
								markerOption.rotation=0;
								markerOption.hasShadow=false;
								//markerOption.imageSize=new MSize(30,30);
								markerOption.labelOption = mapPointFlashObj.addMarkerLabel(data[i].DIRECTION,MConstants.BOTTOM_CENTER);
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
								mapObj.addEventListener(mks[j],MConstants.MOUSE_CLICK,mapPointFlashObj.clickMap);//update by fxy
							}
						}
					}
				},
				complete: function() { 
	                jQuery.unblockUI(); 
	            }
			});
			
		},
		/* 取消CHECKBOX按钮，在地图上删除点 ，
		 * 如果站点是通过查询条件，在地图上绘点，那么修改该点的图片；
		 * 如果站点是点击LIST列表，在地图上绘点，那么删除该点。
		 */
		cancelMarkerFromMap: function (checkValueStr) {
			var checkValue= new Array();
			checkValue=checkValueStr.split("#!#");
			var stationID=checkValue[0];
			if(mapObj.getOverlayById(stationID)!=null){
				var thisMarker = mapObj.getOverlayById(stationID);
				//thisMarker.option.showFlag -- getPointSAByObj(thisMarker,"showFlag")
				if(mapPointFlashObj.getPointSAByObj(thisMarker,"showFlag")=="listAdd" || mapPointFlashObj.getPointSAByObj(thisMarker,"showFlag")=="searchListAdd"){
					mapObj.removeOverlayById(stationID);
				}else{
					thisMarker.option.imageUrl="../images/xiaocheImage/addoil.png"; 
					//thisMarker.option.imageSize=new MSize(30,30);
					//thisMarker.option.showFlag="searchAdd";
					mapPointFlashObj.setPointSAByObj(thisMarker,"showFlag","searchAdd");
					thisMarker.option.rotation=360; 
					mapObj.updateOverlay(thisMarker);
				}
			}
		},
		/* 根据条件查询站点和坐标点并制图
		 * point 类型为searchAdd
		 */
		initMapList: function () {
			var startTime=document.getElementById("start_time").value;
			var endTime=document.getElementById("end_time").value;
			//判断时间区间
			if($.trim(startTime).length==0||$.trim(endTime).length==0){
	    		alert("上报时间不能为空");
	    		return false;
	    	}
	    	if(string2Date(startTime)>string2Date(endTime)){
	    		alert("开始时间应早于结束时间！");
	    		return false;
	    	}
	    	if(GetDateDiff(startTime,endTime,'day')>31){
	    		alert("查询时间段应控制在一个月内！");
	    		return false;
	    	}
			
			jQuery("input[name='carChoice']").removeAttr('checked');
			var inout_flag="";

			if(!jQuery("#showPoint").attr("checked")&&jQuery("#showStation").attr("checked")){
				inout_flag="1";
			}
			if(jQuery("#showPoint").attr("checked")&&!jQuery("#showStation").attr("checked")){
				inout_flag="0";
			}
			if(jQuery("#showPoint").attr("checked")&&jQuery("#showStation").attr("checked")){
				inout_flag="2";
			}
			if(!jQuery("#showPoint").attr("checked")&&!jQuery("#showStation").attr("checked")){
				mapObj.removeAllOverlays();
				return;
			}
				
			mapObj.removeAllOverlays();	
	  	 	var arr = new Array();

	  	 	jQuery.ajax({
					type: 'POST', 
					dataType: 'json', 
					url: '../sitGrid/getMapSearchList.shtml',	
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
								markerOption.tipOption = mapPointFlashObj.addDataToTipContent("",data[i].INOUT_FLAG,"",data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].CONTROL_STATION,data[i].STATION_ADDR,data[i].ORGANIZATION_ID,data[i].ENTER_SHORT_NAME);
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
									markerOption.imageUrl="../images/xiaocheImage/point1.png";
								}else{
									markerOption.imageUrl="../images/xiaocheImage/addoil.png";  
									jQuery("input[name='carChoice']").each(function(){
								 			markerOption.imageUrl="../images/xiaocheImage/addoil.png";
								 			markerOption.tipOption = mapPointFlashObj.addDataToTipContent("",data[i].INOUT_FLAG,"searchAdd",data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].ST_LONGITUDE,data[i].ST_LATITUDE,data[i].CONTROL_STATION,data[i].STATION_ADDR,data[i].ORGANIZATION_ID,data[i].ENTER_SHORT_NAME);
								 			return true;
								 	});
									markerOption.labelOption = mapPointFlashObj.addMarkerLabel(data[i].DIRECTION,MConstants.BOTTOM_CENTER);
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
									mapObj.addEventListener(mks[j],MConstants.MOUSE_CLICK,mapPointFlashObj.clickMap);
								}
							}										
						}		
						
					},
					complete: function() { 
						mapPointFlashObj.setStatus();
		                jQuery.unblockUI(); 
		            }
				});
			  	  
		},
		/*
		* 地图注册点击事件
		*/
		
		clickMap: function (event){
			var mk = mapObj.getOverlayById(event.overlayId);
		    mapObj.setOverlayToTopById(mk.id);//根据id将点覆盖物置于所有点的最顶层
			mapObj.setCenter(mk.lnglat);//设置地图中心点的经纬度坐标
			var flag="";
			if(mapPointFlashObj.getPointSAByObj(mk,"pointType")=="0"){
				mk.option.imageUrl="../images/xiaocheImage/point3.png";
				$('#sichen_vehicle').val('');
				flag=false;
				getflag=1;  //地图上采集的坐标点，设置标示，1：是      0：否
			}else{
				mk.option.imageUrl="../images/xiaocheImage/addoil.png";
				flag=true;
			}
			mk.option.rotation=360;
			mapObj.addOverlay(mk);
			//点击后弹出浮动框
			mapPointFlashObj.setStationInfo(event.overlayId,flag);
		}
		,
		/*
		 * 站点编辑弹出框
		* 
		*/
		setStationInfo: function (stationID,flag){
			publicMapID=stationID;
			art.dialog({
				id:'stationID',
				title:flag==true?'加油站设置':'新建加油站',
				lock:true,
				width:450,
				height:270,
				fixed:true,
			    content:jQuery("#stationInfoDiv").html(),
			    closeFn:function(){
					if(publicMapID=="BE66855BD543127FE0440019BB600AC6"){ 
						mapObj.removeOverlayById(publicMapID);
						publicMapID='';
					}
			    }
			});
			
			jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(0).children("#deleteButton").hide();
			jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(2).children("#lonlatTable").children("tbody").children("tr").eq(3).children("td").eq(2).children("#resetButton").hide();
			if(getflag==1){
				flag=true;
			}
			getflag=0;
			if(flag==false){
				$("#sichen_vehicle").val('');
				$("#sichen_vin").val('');
				jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_vehicle").val('');
				jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_vin").val('');

			}else{
				var mk = mapObj.getOverlayById(stationID);
				var initLat=mapPointFlashObj.getPointSAById(stationID,"initLat");
				var initLng=mapPointFlashObj.getPointSAById(stationID,"initLng");				
				jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val(mk.option.labelOption.content);
				jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude").val(initLng);
				jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude").val(initLat);
				
				if(mapPointFlashObj.getPointSAById(stationID,"pointType")=="0"){

				}else{
					//获取车牌号
					jQuery.ajax({
						type: 'POST', 
						url: '../sitGrid/selectStationSet.shtml',
						async: true,
						data: {site_id:stationID
						},
						success: function(data) {
							if(data.returnStr == "success"){
								mapPointFlashObj.vehicleln = data.vehicle_ln;
								mapPointFlashObj.vehicleVin = data.vehicle_vin;
								$("#sichen_vehicle").val(mapPointFlashObj.vehicleln);
								$("#sichen_vin").val(mapPointFlashObj.vehicleVin);
								jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_vehicle").val(mapPointFlashObj.vehicleln);
								jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_vin").val(mapPointFlashObj.vehicleVin);
								jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(5).children("td").eq(0).children("#deleteButton").show();
								jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(2).children("#lonlatTable").children("tbody").children("tr").eq(3).children("td").eq(2).children("#resetButton").show();
							}
						}
					});
				}
			}

			
		},
		//站点编辑form检查
		checkForm: function () {
			var site_name=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val();
			site_name = site_name.replace(/(^\s*)|(\s*$)/g, "");
			var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude").val();
			var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude").val();
			
			var vehicleLnList=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_vehicle").val();
	    	var vehicleVinList = jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_vin").val();

	    	if($.trim(site_name).length==0){
	    		alert('加油站名称不能为空');
	    		return false;
	    	}
	    	if($.trim(site_name).length>20){
	    		alert('加油站名称不能超过20个字符');
	    		return false;
	    	}
	    	var hanzi = new RegExp(/^[\u4e00-\u9fa5]+$/i);
	    	if(!hanzi.test(site_name)){
	    		alert('加油站名称必须为汉字');
	    		return false;
	    	}
	    	//var num = new RegExp(/^\d{1,3}\.\d{6}$/);
	    	if($.trim(site_longitude).length==0){
	    		alert("请输入经度!");return false;
	    	}
//	    	if(Number(site_longitude)){
//	    		
//	    	} else {
//	    		alert("经度请输入数字!");
//	    		return false;
//	    	}	    	
	    	//this.checkLon(site_longitude) && 
	    	if(parseFloat($.trim(site_longitude)) < 73 || parseFloat($.trim(site_longitude)) > 137|| !Number(site_longitude)||$.trim(site_longitude)!=site_longitude){
	    		alert('请输入正确的经度!');return false;
	    	}
	    	if($.trim(site_latitude).length==0){
	    		alert("请输入纬度!");return false;
	    	}
//	    	if(Number(site_latitude)){
//	    		
//	    	} else {
//	    		alert("纬度请输入数字!");
//	    		return false;
//	    	}
	    	//this.checkLon2(site_latitude) && 
	    	if(parseFloat($.trim(site_latitude)) < 1 || parseFloat($.trim(site_latitude)) > 55|| !Number(site_latitude)||$.trim(site_latitude)!=site_latitude){
	    		alert('请输入正确的纬度!');return false;
	    	}
//	    	if(parseFloat(site_longitude)>180){
//	    		alert('请输入正确的经度!');return false;
//	    	}
//	    	if(parseFloat(site_latitude)>90){
//	    		alert('请输入正确的纬度!');return false;
//	    	}
			
			return true; 
		},
		checkLonLat: function (str){
			var result=str.match(/^(((180)|(((1[0-7]\d)|(\d{1,2}))(\.\d+)?)))$/g);
			if(result==null){
				return false;
			}else{
				return true;
			}
		},
		/** 经纬度必填项 **/
		checkLon: function (obj) {
			var that = this;
			var longs = jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude").val()
			if($.trim(longs).length == 0){
				//alert("请填写经度!");
				return false;
			}
	  	  if(that.checkLonLat(longs)){
	      	try{
	            that.moveMarker();
	          } catch(e) {
	             ;
	          }
	      }else{
	    	//alert("经度不合法!");
	      	return false;
	      }
	  	  return true;
		},
		checkLon2: function (obj) {
			var that = this;
			var latitu = jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude").val();
			if($.trim(latitu).length == 0){
				//alert("请输入纬度!");
				return false;
			}
	  	  if(that.checkLonLat(latitu)){
	      	try{
	            that.moveMarker();
	          } catch(e) {
	             ;
	          }
	      }else{
	    	  //alert("纬度不合法!");
	      	return false;
	      }
	  	  return true;
		},
		saveMarker: function (thisMarker,id){
			var that = this;
			if(id!=null){
				var lonValue=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude").val();
				var latValue=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude").val();
				var site_name=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val();
				site_name = site_name.replace(/(^\s*)|(\s*$)/g, "");
				
				var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude").val();
				var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude").val();
				  if(latValue != "" && lonValue != "") {
					  jQuery.ajax({
							type: 'POST', 
							url: 'getSingleLONLAT.shtml',
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
								markerNew.imageUrl="../images/xiaocheImage/addoil.png";
								markerNew.imageAlign=MConstants.BOTTOM_CENTER;
								markerNew.isBounce=false;
								markerNew.isEditable=false;
								markerNew.canShowTip= false;
								markerNew.rotation=0;
								markerNew.hasShadow=false;
								markerNew.picAgent=false;
								markerNew.tipOption = that.addDataToTipContent("","1","listAdd",site_longitude,site_latitude,site_longitude,site_latitude,"","","","");
								//update by fxy end							
								markerNew.labelOption = that.addMarkerLabel(site_name,MConstants.BOTTOM_CENTER);							
								var Marker="";
								Marker = new MMarker(new MLngLat(lonValue,latValue),markerNew);  
								Marker.id=id;
								mapObj.addOverlay(Marker);					
								var thisMarker = mapObj.getOverlayById(id);
								mapObj.addEventListener(thisMarker,MConstants.MOUSE_CLICK,mapPointFlashObj.clickMap);
								
							}
						});
				  }
			}else{
				var site_name=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val();
				site_name = site_name.replace(/(^\s*)|(\s*$)/g, "");
				thisMarker.option.labelOption = that.addMarkerLabel(site_name,MConstants.BOTTOM_CENTER);
				var realLng=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude").val();
				var realLat=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude").val();
				var initLng=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude").val(); //坐标初始经度
				var initLat=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude").val();	//坐标初始纬度
				var showFlag="listAdd";
				var pointType="1";
				var vehicleVin = jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(2).children("#sichen_vehicle").val();
				//设置业务属性
				thisMarker.option.tipOption = that.addDataToTipContent("",pointType,showFlag,realLng,realLat,initLng,initLat,"","","","");		
				thisMarker.option.imageUrl="../images/xiaocheImage/addoil.png";
				mapObj.addOverlay(thisMarker);
			}
			
		},
		saveListData: function (mid){
			var site_name=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val();
			site_name = site_name.replace(/(^\s*)|(\s*$)/g, "");
			var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude").val();
			var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude").val();
			
			jQuery('#checkbox_'+mid).val(mid+'#!#'+site_name+'#!#'+site_longitude+'#!#'+site_latitude+'#!#'+""+'#!#'+""+'#!#'+""+'#!#'+"");
			jQuery('#name_'+mid).html(site_name);
			jQuery('#lon_'+mid).html(site_longitude);
			jQuery('#lat_'+mid).html(site_latitude);
		},
		// add by jinp begin 2012-08-13
		/** 经度必填项 **/
		checkLongitude: function (value) {
			var regFloat = /^(?:[0-9]|[1-9][0-9]|1[0-7][0-9])\.(\d)+$/;
			var regInteger = /^(?:[0-9]|[1-9][0-9]|1[0-7][0-9]|180)$/;
			if(regFloat.test(value)) {
				return true;
			} else if(regInteger.test(value)){
				return true;
			} else {
				return false;
			}
		},
		checkLatitude: function (value) {
			var regFloat = /^(?:[0-9]|[1-8][0-9])\.(\d)+$/;
			var regInteger = /^(?:[0-9]|[1-8][0-9]|90)$/;
			if(regFloat.test(value)) {
				return true;
			} else if(regInteger.test(value)){
				return true;
			} else {
				return false;
			}
		},
		/*
		* 确定操作
		*/
		addStation: function (){
			var that = this;
			var site_name=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(1).children("#site_name").val();
			site_name = site_name.replace(/(^\s*)|(\s*$)/g, "");
			var sichen_vehicle=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_vehicle").val();
			var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude").val();
			var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude").val();
			var vehiclelnList = jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_vehicle").val();
	    	var vehicleVinList = jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_vin").val();
			if(publicMapID!="BE66855BD543127FE0440019BB600AC6"){
				var thisMarker = mapObj.getOverlayById(publicMapID);
				if(that.getPointSAByObj(thisMarker,"pointType")=="0"){//通过坐标点添加站点的操作
					if(that.checkForm()){
						jQuery.ajax({
							type: 'POST', 
							dataType: 'json', 
							url: '../sitGrid/addStation_do.shtml',	
							data: {
								site_name:site_name,
								longitude:site_longitude,
								latitude:site_latitude,
								pointID:publicMapID,
								sortname:jQuery('#gala').flex_sortname(),
								sortorder:jQuery('#gala').flex_sortorder(),
								vehicle_vin: vehicleVinList,
								vehicle_ln: vehiclelnList
								},
							async:false,
							success: function(data) {
								if(data.returns=="success"){
									jQuery('#gala').flexOptions({
										newp: Math.ceil(data.returnOrder/jQuery('#gala').flex_rp())
									});
									jQuery('#gala').flex_setParmChecked("checkbox_"+data.returnID);
									jQuery('#gala').flexReload();
									
									mapObj.removeOverlayById(publicMapID);
									that.saveMarker(thisMarker,data.returnID);
									publicMapID=data.returnID;
									mapPointFlashObj.vehicleln = vehiclelnList;
									mapPointFlashObj.vehicleVin = vehicleVinList;
									art.dialog.get('stationID').close();
									alert("站点添加成功！");
								}
								if(data.returns=="error"){
									alert("站点添加失败！");
								}
							}});
						}
					   
					}else{
						if(that.checkForm()){
							jQuery.ajax({
								type: 'POST',  
								dataType: 'json',
								url: '../sitGrid/editStation_do.shtml',	
								async:false,
								data: {
									site_id: publicMapID,
									longitude:site_longitude,
									latitude:site_latitude,
									site_name:site_name,
									sichen_vehicle:sichen_vehicle,
									vehicle_vin: vehicleVinList,
									vehicle_ln: vehiclelnList
								},
								success: function(data) {
									if(data.returns=="success"){
										jQuery('#gala').flexOptions({
											newp: Math.ceil(data.returnOrder/jQuery('#gala').flex_rp())
										});
										jQuery('#gala').flex_setParmChecked("checkbox_"+publicMapID);
										jQuery('#gala').flexReload();
//										mapPointFlashObj.addMarkerToMap($("#checkbox_"+publicMapID).val());
										mapPointFlashObj.addMarkerToMap4Update(publicMapID,site_name,site_longitude,site_latitude);
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
					if(that.checkForm()){
						jQuery.ajax({
							type: 'POST',  
							dataType: 'json',
							url: '../sitGrid/addStation_do.shtml',
							async:false,
							data: {
								longitude:site_longitude,
								latitude:site_latitude,
								site_name:site_name,
								sichen_vehicle:sichen_vehicle,
								esite_name:jQuery('#stationName').val(),
								vehicle_vin: vehicleVinList,
								vehicle_ln: vehiclelnList
							},
							success: function(data) {
								if(data.returns=="success"){
									jQuery('#gala').flexOptions({
										newp: Math.ceil(data.returnOrder/jQuery('#gala').flex_rp())
									});
									jQuery('#gala').flex_setParmChecked("checkbox_"+data.returnID);
									jQuery('#gala').flexReload();
									//thisMarker.id=data.returnID;
									mapObj.removeOverlayById(publicMapID);
									that.saveMarker(thisMarker,data.returnID);
									that.publicMapID=data.returnID;
									mapPointFlashObj.vehicleln = vehiclelnList;
									mapPointFlashObj.vehicleVin = vehicleVinList;
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
			},
			deleteCell: function (mid){
				jQuery('#row' + mid).hide();
			},
			addMarkerToMap4Update: function (stationID,stationName,longValue,latuValue) {
						jQuery.ajax({
							type: 'POST', 
							url: 'getSingleLONLAT.shtml',	
							data: {
								longitude:longValue,
								latitude:latuValue
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
								markerOption.imageUrl="../images/xiaocheImage/addoil.png"; 
								markerOption.picAgent=false;
								markerOption.labelOption = mapPointFlashObj.addMarkerLabel(stationName,MConstants.BOTTOM_CENTER);
								//flash版本设置的属性
								markerOption.imageAlign=MConstants.BOTTOM_CENTER;
								markerOption.isBounce=false;
								markerOption.isEditable=false;
								markerOption.canShowTip= false;
								markerOption.rotation=0;
								markerOption.hasShadow=false;
								markerOption.tipOption = mapPointFlashObj.addDataToTipContent("","1","listAdd",longValue,latuValue,longValue,latuValue,'','','','');					
								var Marker="";
								Marker = new MMarker(new MLngLat(lonValue,latValue),markerOption);  
								Marker.id=stationID;
								mapObj.addOverlay(Marker,true);
								mapObj.addEventListener(Marker,MConstants.MOUSE_CLICK,mapPointFlashObj.clickMap);//update by fxy				
							}
						});
				
			},
			/*
			* 删除站点
			*/
			deleteStation: function (){
				if(publicMapID!="BE66855BD543127FE0440019BB600AC6"){
					var vehicleLnList=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(4).children("td").eq(1).children("#sichen_vehicle").val();
					var ok=false;
					if($.trim(vehicleLnList).length>0){
						ok=confirm("该加油站服务于以下车辆，您确定要删除吗？\n车牌号为:"+vehicleLnList.replace(/\'/g,""));
					}else{
						ok=confirm("您确定要删除该加油站点吗？");
					}	
						if(ok){
							jQuery.ajax({
								type: 'POST',  
								url: '../sitGrid/deleteStation.shtml',	
								data: {site_id: publicMapID},	
								dataType: 'json',
								success: function(data) {
									if(data.returns=="success"){
										mapObj.removeOverlayById(publicMapID);
										jQuery('#gala').flexReload();
										alert("站点删除成功！");
									}
									if(data.returns=="error"){
										alert("站点删除失败！");
									}
									publicMapID="";
								}  
							});
							art.dialog.get('stationID').close();
						}
					}
				
				
			},
			/*
			 * 复位
			 */
			resetStation: function (){
				var that = this;
				var thisMarker = mapObj.getOverlayById(publicMapID);
				//getPointSAByObj(thisMarker,"initLng") thisMarker.option.initLng
				jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude").val(mapPointFlashObj.getPointSAByObj(thisMarker,"initLng"));
				//getPointSAByObj(thisMarker,"initLat") thisMarker.option.initLat
				jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude").val(mapPointFlashObj.getPointSAByObj(thisMarker,"initLat"));
				that.moveMarker();
			},
			/*
			* 取消
			*/
			cancelStation: function (){
//				if(publicMapID!="BE66855BD543127FE0440019BB600AC6"){
//					mapPointFlashObj.resetStation();
//				}else{
//					mapObj.removeOverlayById(publicMapID);
//					publicMapID='';
//				}
				art.dialog.get('stationID').close();
			},
			/** 地图上单点移动操作 **/
			moveMarker: function () {
			  var lonValue=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude").val();
			  var latValue=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude").val();
			  var site_longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude").val();
			  var site_latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude").val();

			  if(typeof(lonValue)=="undefined"){
				  latValue="";
			  }

			  if(typeof(latValue)=="undefined"){
				  latValue="";
			  }
			  if(latValue != "" && lonValue != "") {
				  jQuery.ajax({
						type: 'POST', 
						url: 'getSingleLONLAT.shtml',	
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
						    	thisMarker.lnglat.lngX=lonValue;
						    	thisMarker.lnglat.latY=latValue;
						    	//thisMarker.option.realLng=site_longitude;
						    	//thisMarker.option.realLat=site_latitude;
						    	mapPointFlashObj.setPointSAByObj(thisMarker,"realLng",site_longitude);
						    	mapPointFlashObj.setPointSAByObj(thisMarker,"realLat",site_latitude);
						    	mapObj.markerMoveTo(publicMapID, new MLngLat (lonValue,latValue));
						    	mapObj.addOverlay(thisMarker,true);
							}else{//新建动作触发
								var markerNew=new MMarkerOptions();   
								markerNew.imageUrl="../images/xiaocheImage/addoil.png"; 
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
								markerNew.tipOption = mapPointFlashObj.addDataToTipContent("","1","listAdd",site_longitude,site_latitude,site_longitude,site_latitude,"","","","");				
								//flash版本设置的属性
								markerNew.imageAlign=MConstants.BOTTOM_CENTER;
								markerNew.isBounce=false;
								markerNew.isEditable=false;
								markerNew.canShowTip= false;
								markerNew.rotation=0;
								markerNew.hasShadow=false;
								markerNew.labelOption = mapPointFlashObj.addMarkerLabel('',MConstants.BOTTOM_CENTER);
								var Marker="";
								Marker = new MMarker(new MLngLat(lonValue,latValue),markerNew);  
								Marker.id=publicMapID;
								mapObj.addOverlay(Marker,true);
								var thisMarker = mapObj.getOverlayById(publicMapID);
								mapObj.addEventListener(thisMarker,MConstants.MOUSE_CLICK,mapPointFlashObj.clickMap);
						  	}
						}
					});
			  }
			},
			/**
			 * 【将业务属性添加到对应点覆盖物的tip_content中】
			 */
			addDataToTipContent: function (content,pointType,showFlag,realLng,realLat,initLng,initLat,control_station,station_addr,organization_id,organization_name){
				var tipOption = new MTipOptions();
			    //将业务数据保存在对应点的MTipOptions的content中
				//说明：各字段以"英文逗号"分隔,第一位预留
			    tipOption.tipType = MConstants.HTML_CUSTOM_TIP;
			    tipOption.content = content+","+pointType+","+showFlag+","+realLng+","+realLat+","+initLng+","+initLat+","+control_station+","+station_addr+","+organization_id+","+organization_name;
			    return tipOption;
			},
			/**
			 * 获取点的业务属性(根据点对象)
			 * get point Service Attribute
			 * mk-->点覆盖物的对象
			 * Attribute-->属性值
			 */
			getPointSAByObj: function (mk,attribute){
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
				default: return "";
				}
			},
			/**
			 * 获取点的业务属性(根据点id)
			 * get point Service Attribute
			 * overlayId-->点覆盖物的ID
			 * Attribute-->属性值
			 */
			getPointSAById: function (overlayId,attribute){
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
				default: return "";
				}
			},
			/**
			 * 根据点对象设置对应的业务属性
			 * set point Service Attribute
			 * mk-->点覆盖物的对象
			 * attribute-->属性名
			 * value--->新的属性值
			 */
			setPointSAByObj: function (mk,attribute,value){
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
				case "control_station": data_array[7]=value;break;
				case "station_addr": data_array[8]=value;break;
				case "organization_id": data_array[9]=value;break;
				case "organization_name": data_array[10]=value;break;
				default: alert("点对象的异常业务属性");
				}
				
				tip_content = data_array[0]+","+data_array[1]+","+data_array[2]+","+data_array[3]+","+data_array[4]+","+data_array[5]+","+data_array[6]+","+data_array[7]+","+data_array[8]+","+data_array[9]+","+data_array[10];
				//重新设置业务数据
				tip.content = tip_content;
				//重新复制给点对象
				mk.option.tipOption = tip;
			},
			//聚合点设置  
			setClusters: function (){  
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
			},
			//添加普通聚合方式
			normal: function (){  
				mapPointFlashObj.setClusters();  
				mapObj.setClusterState(MClusterOptions.NORMAL_CLUSTER, clusterOptions);
			},
			//取消聚合方式
			cancelCluster: function (){
				mapObj.setClusterState(MClusterOptions.NO_CLUSTER, clusterOptions);
			},
			
			/**
			 * 微调经纬度
			 * @param direct
			 * @param value
			 */
			changePoint:function(direct,value){
				var longitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(1).children("#site_longitude");
				var latitude=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#site_latitude");
				if(direct=='level'){
					if($.trim(longitude.val()).length==0){
						alert('请先设置经度！');
						return ;
					}
				
					else if(parseFloat(longitude.val()) < 73 || parseFloat(longitude.val()) > 137 || !Number(longitude.val())||$.trim(longitude.val())!=longitude.val()){
						alert("请输入正确的经度!");
			    		return false;
			    	} else {
			    		longitude.val((parseFloat(longitude.val())+parseFloat(value)).toFixed(6));
			    	}	    	
					
				}else{
					if($.trim(latitude.val()).length==0){
						alert('请先设置纬度！');
						return ;
					}
					else if(parseFloat(latitude.val()) < 1 || parseFloat(latitude.val()) > 55|| !Number(latitude.val())||$.trim(latitude.val())!=latitude.val()){
						alert("请输入正确的纬度!");
			    		return false;
			    	} else {
			    		latitude.val((parseFloat(latitude.val())+parseFloat(value)).toFixed(6));
			    	}	   
					
				}
			}
		
};


