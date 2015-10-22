//加载地图


function sidemapInit(divid) {  
	var map = null;

	var mapoption = new MMapOptions();   
	mapoption.zoom = 4;//要加载的地图的缩放级别   
	mapoption.center = new MLngLat(116.397428,39.90923);//要加载的地图的中心点经纬度坐标   
	mapoption.hasDefaultMenu = false;
	mapoption.toolbar = MConstants.SMALL; //设置地图初始化工具条，ROUND:新版圆工具条   *
	mapoption.toolbarPos=new MPoint(5,5); //设置工具条在地图上的显示位置   
	mapoption.overviewMap = MConstants.MINIMIZE; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）   *
	mapoption.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。   *
	mapoption.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标  *
	
	mapoption.maxZoomLevel=17;
	mapoption.logoUrl = "../newimages/sidelayerimages/mask.png";
	mapoption.groundLogo = "../newimages/sidelayerimages/mapbackgroud.jpg";
	mapoption.language = MConstants.MAP_CN;//设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图   **
	mapoption.fullScreenButton = MConstants.HIDE;//设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏   **
	mapoption.centerCross = MConstants.HIDE;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏   **
	mapoption.requestNum=100;//设置地图切片请求并发数。默认100。   **
	mapoption.isQuickInit=true;//设置是否快速显示地图，true显示，false不显示。   **
	map = new MMap(divid, mapoption); //地图初始化
 
	map.setKeyboardEnabled(false);
    
	return map;
}