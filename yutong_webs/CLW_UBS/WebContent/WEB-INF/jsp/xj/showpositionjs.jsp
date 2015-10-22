<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/key.jsp"%>
<script type="text/javascript">
	var mapObj = null;
	var arrForFitView=new Array();
	function mapInit() {
		var mapOptions = new MMapOptions();//构建地图辅助类   
		mapOptions.zoom = 4;//要加载的地图的缩放级别   
		//mapOptions.center = new MLngLat(116.397428, 39.90923);//要加载的地图的中心点经纬度坐标   
		mapOptions.toolbar = DEFAULT;//设置地图初始化工具条   
		mapOptions.toolbarPos = new MPoint(15, 15); //设置工具条在地图上的显示位置   
		mapOptions.overviewMap = SHOW; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）   
		mapOptions.scale = SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。   
		mapOptions.returnCoordType = COORD_TYPE_OFFSET;//返回数字坐标   
		mapOptions.zoomBox = true;//鼠标滚轮缩放和双击放大时是否有红框动画效果。
		mapOptions.logoUrl = " ";   
		mapObj = new MMap("map", mapOptions); //地图初始化   
		showpoint();

		var div2 = draw2();
	    mapObj.addControl(div2);
	    mapObj.setZoomLevel(13);
	}

	/*向地图上画点*/
	function showpoint(){
		mapObj.removeOverlaysByType(MOverlay.TYPE_MARKER);
		 var lon = document.getElementById("alarm_start_longitude").value;
		 var lat =document.getElementById("alarm_start_latitude").value;
		 var vehicle_ln=document.getElementById("vehicle_ln").value;
		 var color = document.getElementById("color").value;
		 var direction = document.getElementById("alarm_start_direction").value;
		// alert(lon+","+lat+","+vehicle_ln);
		//点的属性设置
		 var markerOption = new MMarkerOptions();
		//标注图片或SWF的url，默认为蓝色气球图片   
		 if(color=="b"){
			 markerOption.imageUrl="../images/arrow_blue.gif";
	     }
	     else if(color=="r"){
	    	 markerOption.imageUrl="../images/arrow_red.gif";
	     }
		 //markerOption.imageUrl="../images/arrow_red.gif";//lan_2.png";
		//图片锚点BOTTOM_CENTER相对于标注位置的位置  
		 markerOption.imageAlign=BOTTOM_CENTER;
		//设置图标旋转的角度   
		if(direction !=null && direction != "" && direction !="FFFF"){
			markerOption.rotation=direction;
			}
		else{
			markerOption.rotation=0;
			}
		
		//是否在地图中显示信息窗口，true，可以显示（默认）；false，不显示    
	    markerOption.canShowTip= false;  
		//通过经纬度坐标及参数选项确定标注信息   
		Mmarker = new MMarker(new MLngLat(lon,lat),markerOption);   
		//设置点的标注参数选项   
		markerOption.labelOption=addMarkerLabel(vehicle_ln,TOP_CENTER);; 
		//对象编号，也是对象的唯一标识   
		Mmarker.id="mark101";   
		//向地图添加覆盖物   
		mapObj.addOverlay(Mmarker,false) ; 
		setFitV(Mmarker.id,arrForFitView);			 	 		 
	}

	// 自适应room值
	function setFitV(overlayid,arrForFitView){   
		       
		    arrForFitView.push(overlayid);   
		    mapObj.setFitview(arrForFitView);   
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
	   //设置标注左上角相对于面对象中心的锚点。标注左上角与面对象中心重合时，像素坐标原点(0,0)   
	    labeloption.labelPosition=new MPoint(0,0);   
	    //设置对准点正下方的文字标签锚点   
	    labeloption.labelAlign=direction;//TOP_CENTER;   
	   return  labeloption;
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
</script>


