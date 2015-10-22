<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/PoiManageDwr.js'></script>
<%@include file="../common/mapAbcKey.jsp" %>
<script type="text/javascript">
  /** 地图对象 **/
  var mapObj = null;

  /** 地图初始化 **/
  function mapInit() {
    var mapOptions = new MMapOptions();//构建地图辅助类   
    mapOptions.zoom=13;//要加载的地图的缩放级别   
    //mapOptions.center=new MLngLat(116.397428,39.90923);//要加载的地图的中心点经纬度坐标
    mapOptions.toolbar = DEFAULT;//设置地图初始化工具条   
    mapOptions.toolbarPos = new MPoint(15,15); //设置工具条在地图上的显示位置   
    mapOptions.overviewMap = SHOW; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）   
    mapOptions.scale = SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。   
    mapOptions.returnCoordType = COORD_TYPE_OFFSET;//返回数字坐标   
    mapOptions.zoomBox = true;//鼠标滚轮缩放和双击放大时是否有红框动画效果。   
    mapOptions.hasDefaultMenu=false;  //去掉鼠标右键
    mapOptions.logoUrl = " ";   //地图右下角logo去掉
    mapObj=new MMap("map",mapOptions); //地图初始化   
    mapObj.addEventListener(mapObj,MOUSE_CLICK,MclickMouse);//鼠标点击事件
    var maptools=new MMapTools(mapObj);   
    maptools.setCenterByCity("北京");
  }

  /** 鼠标事件 **/
  function MclickMouse(param){   
    document.getElementById("poiLon").value=param.eventX;   
    document.getElementById("poiLat").value=param.eventY;  

    var latValue = document.getElementById("poiLat").value;
    var lonValue = document.getElementById("poiLon").value;
    
    var markerOption=new MMarkerOptions();   
    markerOption.imageUrl="http://code.mapabc.com/images/lan_1.png";   
    markerOption.picAgent=false;
    var Marker = new MMarker(new MLngLat(lonValue,latValue),markerOption);   
    Marker.id="mark";
    mapObj.removeAllOverlays();
    // 添加标点
    mapObj.addOverlay(Marker,true) ;  
  }

  /** 创建POI点 **/
  function addPoiMarker() {
    var latValue = document.getElementById("poiLat").value;
    var lonValue = document.getElementById("poiLon").value;
    
	if(latValue != "" && lonValue != "") {
	  var markerOption=new MMarkerOptions();   
      markerOption.imageUrl="http://code.mapabc.com/images/lan_1.png";   
      markerOption.picAgent=false;
      var Marker = new MMarker(new MLngLat(lonValue,latValue),markerOption);   
      Marker.id="mark";
      mapObj.removeAllOverlays();
      // 添加标点
      mapObj.addOverlay(Marker,true) ;  
	}    
    
  }
  
  /** 获取所属省信息 **/
  function getProvinceInfo() {
    var countryObj = $('countryId');
    
    ZoneDwr.showZoneXsInfo(countryObj.value, callBackFun);

    if(countryObj.value != "") {
      Mat.showSucc(countryObj);
    }
    
    function callBackFun(data) {
      var provinceObj = $('provinceId');  
      DWRUtil.removeAllOptions(provinceObj);  
      DWRUtil.addOptions(provinceObj,{'':'<s:property value="getText('please.select')" />'});  
      if(countryObj.value != "") {
    	  DWRUtil.addOptions(provinceObj,data);
      }
    
      var cityObj = $('cityId');
      DWRUtil.removeAllOptions(cityObj);  
      DWRUtil.addOptions(cityObj,{'':'<s:property value="getText('please.select')" />'});  
    }
  }

  /** 获取所属市信息 **/
  function getCityInfo() {
    var provinceObj = $('provinceId');
    ZoneDwr.showZoneXsInfo(provinceObj.value, callBackFun);
    
    if(provinceObj.value != "") {
      Mat.showSucc(provinceObj);
    }
    
    function callBackFun(data) {
      var cityObj = $('cityId');  
      DWRUtil.removeAllOptions(cityObj);  
      DWRUtil.addOptions(cityObj,{'':'<s:property value="getText('please.select')" />'});  
      if(provinceObj.value != "") {
    	  DWRUtil.addOptions(cityObj,data);
      }
    }
  }

  /** 所属市变化 **/
  function onCityChange() {
	var cityObj = $('cityId');
	if(cityObj.value != "") {
	  Mat.showSucc(cityObj);
	  var cityName = cityObj.options[cityObj.selectedIndex].text;
	  
	  var maptools=new MMapTools(mapObj); 
	  try{  
	    maptools.setCenterByCity(cityName);
	  } catch(e) {
		  ;
	  }
	}
  }

  /** 网点类型必选 **/
  function checkPoiType() {
    var elm = $('poiType');
    if(elm.value == "") {
      Wit.showErr(elm, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }  

  /** 网点级别必选 **/
  function checkPoiLevel() {
    var elm = $('poiLevel');
    if(elm.value == "") {
      Wit.showErr(elm, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }

  /** 服务类型必选 **/
  function checkPoiService() {
    var elm = $('poiService');
    if(elm.value == "") {
      Wit.showErr(elm, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }
    
  /** 所属国家必选 **/
  function checkCountry() {
    var countryObj = $('countryId');
    if(countryObj.value == "") {
      Wit.showErr(countryObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }
	  
  /** 所属省必选 **/
  function checkProvince() {
    var provinceObj = $('provinceId');
    if(provinceObj.value == "") {
      Wit.showErr(provinceObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }

  /** 所属市必选 **/
  function checkCity() {
    var cityObj = $('cityId');
    if(cityObj.value == "") {
      Wit.showErr(cityObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }

  /** 网点类型变化 **/
  function onPoiTypeChange() {
	var elm = $('poiType');
	if(elm.value != "") {
	  Mat.showSucc(elm);
	}
  }
  
  /** 网点级别变化 **/
  function onPoiLevelChange() {
	var elm = $('poiLevel');
	if(elm.value != "") {
	  Mat.showSucc(elm);
	}
  }

  /** 检查网点代码格式 **/
  function checkPoiCode() {
    var elm = $('poiCode');
    if(Mat.checkRequired(elm)){
   	  if(Wit.checkErr(elm,/^[0-9a-zA-Z]*$/)){
        Mat.showSucc(elm);
        return true;
      } else {
        Wit.showErr(elm, "<s:property value="getText('msg.empno')" />");
        return false;
      }
    } else {
   	  Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
    }
  }
  
  /** 经度必填项 **/
  function checkLon() {
    var elm = $('poiLon');
    if(Mat.checkRequired(elm)){
    	Mat.showSucc(elm);
        try {
          addPoiMarker();
        } catch(e) {
          ;
        }
        return true;
        /**
      if(Wit.checkErr(elm,/^-?((0|[1-9]\d?|1[1-7]\d)(\.\d{0,})?|180(\.0{0,})?)?$/)){
        Mat.showSucc(elm);
        try {
          addPoiMarker();
        } catch(e) {
          ;
        }
        return true;
      } else {
        Wit.showErr(elm, "<s:property value="getText('common.latlon.error')" />");
        return false;
      }  **/
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }

  /** 纬度必填项 **/
  function checkLat() {
    var elm = $('poiLat');
    if(Mat.checkRequired(elm)) {
    	Mat.showSucc(elm);
        try{
          addPoiMarker();
        } catch(e) {
          ;
        }
        return true;
        /**
   	  if(Wit.checkErr(elm,/^-?((0|[1-8]\d|)(\.\d{0,})?|90(\.0{0,})?)?$/)){
        Mat.showSucc(elm);
        try{
          addPoiMarker();
        } catch(e) {
          ;
        }
        return true;
      } else {
        Wit.showErr(elm, "<s:property value="getText('common.latlon.error')" />");
        return false;
      }**/
        
    } else {
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
    }
  }  

  /** 服务点名称必填 **/
  function checkPoiName() {
    var elm = $('poiName');
    if(Mat.checkRequired(elm)) {
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
    }
  }

  /** 联系人必填 **/
  function checkConcatePerson() {
    var elm = $('concatePerson');
    if(Mat.checkRequired(elm)) {
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
    }
  }

  /** 电话必填 **/
  function checkConcateTel() {
    var elm = $('concateTel');
    if(Mat.checkRequired(elm)) {
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
    }
  }
  
  /** 初始化样式 **/
  function setFormMessage() {
	Mat.setDefault($('poiCode'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('poiName'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('poiType'),'');  
	Mat.setDefault($('poiLevel'),'');
	Mat.setDefault($('poiService'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('countryId'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('provinceId'),'<span class="noticeMsg">*</span>');  
	Mat.setDefault($('cityId'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('poiLon'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('poiLat'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('concateTel'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('concateFax'),'');
	Mat.setDefault($('poiAddress'),'');
	Mat.setDefault($('concatePos'),'');
	Mat.setDefault($('email'),'');
	Mat.setDefault($('website'),'');
	Mat.setDefault($('concatePerson'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('remark'),'');
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('poiName').onkeyup = checkPoiName;
    $('poiName').onblur = checkPoiName;
    $('poiLon').onblur = checkLon;
    $('poiLat').onblur = checkLat;

    $('concateTel').onkeyup = checkConcateTel;
    $('concateTel').onblur = checkConcateTel;

    $('concatePerson').onkeyup = checkConcatePerson;
    $('concatePerson').onblur = checkConcatePerson;
  }

  /** 电子邮件格式 **/
  function checkEamil(){
    var elm=$('email');
    if(elm.value!=''){
      if(!Mat.checkEmail(elm)){
        Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.emailSucc')"/>");
        return false;
      }
    }
    Mat.showSucc(elm);
    return true;
  }

  /** 检查CodeId唯一性 **/
  function checkPoiCodeUnique(){
    var codeObj = $('poiCode');
        
	DWREngine.setAsync(false);
	var ret = false; 
	PoiManageDwr.checkPoiCodeUnique(codeObj.value, callBackFun);
	    
	function callBackFun(data)
	{
	  ret = data;
	}
	    
	DWREngine.setAsync(true);
	   
	if(ret) {
	  Wit.showErr(codeObj, "<s:property value="getText('poi.code.exist')" />");
	  return false;
	} else {
	  return true;
	}
  }

  /** check描述文字长度 **/
  function checkMessageInput() {
    var elm = $('remark');
    if(elm.value != "") {
      if(!Mat.checkLength(elm,200,'<s:text name="poi.remark.length" />')) {
        return false;
      } else {
        Mat.showSucc(elm);
        return true;
      }
	} else {
      return true;
	}
  }
  
  /** 提交form **/
  function submitForm() {
    var f1 = checkLon();
    var f2 = checkLat();
    var f3 = checkPoiName();
    var f4 = checkPoiCode();
    var f5 = checkCountry();
    var f6 = checkProvince();
    var f7 = checkCity();
    //var f8 = checkPoiType();
    //var f9 = checkPoiLevel();
    var f10 = checkEamil();
    var f11 = checkPoiCodeUnique();
    var f12 = checkPoiService();
    var f13 = checkMessageInput();
    var f14 = checkConcateTel();
    var f15 = checkConcatePerson();
	if (f1 && f2 && f3 && f4 && f5 && f6 && f7 && f10 && f11 && f12 && f13 && f14 && f15) {
	  Wit.commitAll($('poiinfo_form'));
	} else  {
	  return false;
	}
  }

  /** 取消填加操作 **/
  function goBack(url) {
    Wit.goBack(url);
  }

//页面自适应
(function (jQuery) {
	 jQuery(window).load(function (){
		 changeWidthHeight();
	});			
	 jQuery(window).resize(function(){
		 changeWidthHeight();
	});
})(jQuery);
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

//计算控件宽高
function changeWidthHeight(){
	 var h = get_page_height();
	 var w = get_page_width();
	 var leftInfoDiv = document.getElementById("leftInfoDiv");
	 if(h>200) {
		 jQuery("#leftInfoDiv").height(h-195);
		 jQuery("#poiTbl").height(h-195);
		 jQuery("#map").width(w-265);
		 jQuery("#map").height(h-165);
	 } else {
		 jQuery("#leftInfoDiv").height(h);
		 jQuery("#poiTbl").height(h);
		 jQuery("#map").width(w-265);
		 jQuery("#map").height(h);
	 }
}

  
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>