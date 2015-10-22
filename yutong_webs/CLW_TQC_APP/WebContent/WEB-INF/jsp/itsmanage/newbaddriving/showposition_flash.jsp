<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/key2.jsp"%>
<script type="text/javascript">
	var mapObj = null;
	var arrForFitView=new Array();
	function mapInit() {
		var mapOptions = new MMapOptions();//构建地图辅助类   
		mapOptions.zoom = 4;//要加载的地图的缩放级别   
		//mapOptions.center = new MLngLat(116.397428, 39.90923);//要加载的地图的中心点经纬度坐标   
		mapOptions.toolbar = MConstants.MINI;//设置地图初始化工具条   
		mapOptions.toolbarPos = new MPoint(15, 15); //设置工具条在地图上的显示位置   
		mapOptions.overviewMap = MConstants.HIDE; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）   
		mapOptions.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。   
		mapOptions.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标   
		//mapOptions.zoomBox = true;//鼠标滚轮缩放和双击放大时是否有红框动画效果。   
		mapOptions.hasDefaultMenu=false;  //去掉鼠标右键
		//地图右下角logo去掉
		mapOptions.logoUrl="../newimages/sidelayerimages/mask.png";
		//背景图片
		mapOptions.groundLogo="../newimages/sidelayerimages/mapbackgroud.jpg";
		//(flash版公共元素)设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图
		mapOptions.language = MConstants.MAP_CN;
		//(flash版公共元素)设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏
		mapOptions.fullScreenButton = MConstants.HIDE;
		//(flash版公共元素)设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏
		mapOptions.centerCross = MConstants.HIDE;
		mapOptions.requestNum=100;//(flash版公共元素)设置地图切片请求并发数。默认100。
		mapOptions.isQuickInit=true;//(flash版公共元素)设置是否快速显示地图，true显示，false不显示。
		mapObj = new MMap("viloationMap", mapOptions); //地图初始化   
		mapObj.setKeyboardEnabled(false);
		//var div2 = draw2();
		//mapObj.addControl(div2);
		mapObj.setZoomLevel(4);
		mapObj.addEventListener(mapObj,MConstants.MAP_READY,map_ready);
	}
	function map_ready(param){ 
		getDetailList();
	}

	function draw2() {
		 var mapdiv = document.getElementById("MapbarDivNO");
		 mapdiv.style.bottom = "0px";
		 mapdiv.style.left = "110px";
		 mapdiv.style.zIndex = "1005";
		 mapdiv.unselectable = "on";
		 mapdiv.style.position = "absolute";
		 return mapdiv;
	}

	/*向地图上画点*/
	function showpoint(param1,param2,param3,param4,param5){
		if(param2.length<5&&param3.length>5){
			param2=param3;
		}
		if(param2.length<5&&param3.length<5){
			var sdate = new Date();
			this.syear = sdate.getFullYear();
			this.smonth = sdate.getMonth() + 1;
			this.sdate = sdate.getDate();
			param2=this.syear+"-"+this.smonth + "-"+ this.sdate +" 00:00:00";
		}
		if(param3.length<5&&param2.length>5){
			param3=param2;
		}
		if(param3.length<5&&param2.length<5){
			var edate = new Date();
			this.eyear = edate.getFullYear();
			this.emonth = edate.getMonth() + 1;
			this.edate = edate.getDate();
			param3=this.eyear+"-"+this.emonth + "-"+ this.edate +" 23:59:59";
		}
		mapObj.removeAllOverlays();
		var vehicle_ln=param1;
		var counter=0;
		var arreditline = new Array();
		jQuery.ajax({
	   		  type: 'POST',  
	   		  url: '../humanbaddrivgrid/getAlarmGPSList.shtml',	
	   		  data: {vehicle_vin: param4,
					 start_time:param2,
					 end_time:param3,
					 alarm_type_id:param5},	
	   		  success: function(data) {
					if(data.length==0){
						mapObj.setCenter(new MLngLat("116.49746427536011","39.9086663756386"));//设置地图中心点的经纬度坐标。
						mapObj.setZoomLevel(4);
						return false;
					}
			   		for(var i=0;i<data.length;i++){
			   			if(data[i].LATITUDE >=0 && data[i].LONGITUDE>=0){
			   				arreditline.push(new MLngLat(data[i].LONGITUDE,data[i].LATITUDE));
			   				if(counter==0){
							    if(i==data.length-1){
									//点的属性设置
					   				//var markerOption = new MMarkerOptions();
					   				//标注图片或SWF的url，默认为蓝色气球图片   
					   				//markerOption.imageUrl="../images/arrow_red.gif";
					   				//图片锚点BOTTOM_CENTER相对于标注位置的位置  
					   				//markerOption.imageAlign=BOTTOM_CENTER;
					   				//设置图标旋转的角度   
					   				//markerOption.rotation=0;
					   				//是否在地图中显示信息窗口，true，可以显示（默认）；false，不显示    
					   			    //markerOption.canShowTip= false;  
					   				//通过经纬度坐标及参数选项确定标注信息   
					   				//Mmarker = new MMarker(new MLngLat(data[i].LONGITUDE,data[i].LATITUDE),markerOption);
					   				//设置点的标注参数选项   
					   				//markerOption.labelOption=addMarkerLabel(vehicle_ln,TOP_CENTER);; 
					   				//对象编号，也是对象的唯一标识   
					   				//Mmarker.id="mark101";   
					   				//向地图添加覆盖物   
					   				//mapObj.addOverlay(Mmarker,true) ;
					   				var labelOption = addMarkerLabel(vehicle_ln,MConstants.BOTTOM_CENTER);
									var location=new MLngLat(data[i].LONGITUDE,data[i].LATITUDE);
									addPointMarker(labelOption,location,data.length);								
					   				counter++;
								}
							}
				   		}
					}

			   		var linestyle=new MLineStyle();//创建线样式对象 
				    linestyle.thickness=3;//线的粗细度，默认为2 
				    linestyle.color=0xff0000;//线的颜色，16进制整数，默认为0x005890（蓝色） 
				    var lineopt=new MLineOptions();//构造一个名为lineopt的线选项对象 
				    lineopt.lineStyle=linestyle;//设置线的边缘风格 
				    
				    lineopt.tipOption = null;
				    lineopt.canShowTip = false;
				    
				    var polyline=new MPolyline(arreditline,lineopt);//通过经纬度坐标数组及参数选项构建多折线对象，arr是经纬度坐档数组 
				    polyline.id="upLine";//对象编号，也是对象的唯一标识 
				    mapObj.addOverlay(polyline,false);//向地图添加覆盖物 

			   		if(mapObj.getOverlayById("upLine")!=null){
			   			var arrForFitView=new Array(); 
				   	    arrForFitView.push("upLine"); 
				   	    mapObj.setFitview(arrForFitView);
	   				}
			  }
	   	});
		
	}

	// 自适应room值
	function setFitV(overlayid,arrForFitView){   
		    arrForFitView.push(overlayid);   
		    mapObj.setFitview(arrForFitView);   
	} 

	//添加标注
	function addMarkerLabel(pointname,direction){   
	    
	    var fontstyle=new MFontStyle();//创建字体风格对象   
	    //fontstyle.name="";//设置字体名称，默认为宋体   
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
	    labeloption.labelAlign=direction;
	    return  labeloption;
	}

//mapABC-flash-工具方法
/**
 * 添加点工具类
 */
function addPointMarker(labelOption,location,count){
	
	//构建一个名为markerOption的点选项对象。
	var markerOption = new MMarkerOptions();
	//添加本地图片
	markerOption.imageUrl="../images/arrow_red.gif";
	//是否使用图片代理形式
	//如果imageUrl属性的图片资源所在域名下没有crossdomain.xml，则需要用代理形式添加该图片资源
	markerOption.picAgent=false;
	//设置图片相对于加点经纬度坐标的位置。九宫格位置。默认BOTTOM_CENTER代表正下方
	markerOption.imageAlign=MConstants.BOTTOM_CENTER;
	//设置点的标注参数选项
	markerOption.labelOption=labelOption;
	//拖动结束后是否有弹跳效果,ture，有弹跳效果；false，没有弹跳效果（默认）
	//当有弹跳效果的时候，marker的imageAlign属性必须为BOTTOM_CENTER，否则弹跳效果显示不正确
	markerOption.isBounce=false;
	//设置点是否为可编辑状态,rue，可以编辑；	false，不可编辑（默认）
	markerOption.isEditable=false;
	//是否在地图中显示信息窗口，true，可以显示（默认）；false，不显示
	markerOption.canShowTip= false;
	//设置图标旋转的角度
	markerOption.rotation=0;
	//设置点是否高亮显示
	//设置高亮显示与设置可编辑有冲突，只能设置一个，不能同时设置。
	markerOption.isDimorphic=true;
	//设置第二种状态的颜色，默认为0xFF0000，即红色
	markerOption.dimorphicColor="0x00A0FF";
	//通过经纬度坐标及参数选项确定标注信息
	Mmarker = new MMarker(location,markerOption);
	//对象编号，也是对象的唯一标识
	Mmarker.id="mark101";
	//向地图添加覆盖物

	if(count == 1){//仅有点的情况
		//向地图添加覆盖物
		mapObj.addOverlay(Mmarker,true) ;
		//设置地图的缩放级别
		mapObj.setZoomLevel(17);
	}else{
		mapObj.addOverlay(Mmarker,false);
	}
}

</script>


