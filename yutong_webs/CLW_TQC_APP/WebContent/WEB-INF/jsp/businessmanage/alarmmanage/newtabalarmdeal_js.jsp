<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
	
<script type="text/javascript">

function trim(v){
	return v.replace(/(^[\s　]*)|([\s　]*$)/g, ""); 
}

	var mapObj = null;
	var arrForFitView=new Array();
	var popWidth ="500px";
	var popMaxWidth ="655px";
	var popHeight = "484px";
	var popMaxHeiht = "660px";
	jQuery( function() {
		 jQuery(window.parent.iframeshowArea).resize(function(){
			 styleControl();
		});
		 styleControl();
	});
	function styleControl(){
		if(jQuery(window.parent.iframeshowArea).width() > 500){
			jQuery('#poparea').width(popMaxWidth);
			jQuery('#poparea').height(popMaxHeiht);
			jQuery('#map2').height("460px");
			jQuery('#opeerate_desc').width("625px");
		}
		else{
			jQuery('#poparea').width(popWidth);
			jQuery('#poparea').height(popHeight);
			jQuery('#map2').height("280px");
			jQuery('#opeerate_desc').width("470px");
		}
	}		
	function mapInit() {
		document.getElementById("focus_txt").focus();
		document.getElementById("focusDiv").style.display = "none";


		var mapoption = new MMapOptions();   
		mapoption.zoom = 4;//要加载的地图的缩放级别   
		//mapoption.center = new MLngLat(lon,lat);//要加载的地图的中心点经纬度坐标   
		mapoption.center = new MLngLat(113.686, 34.693);//要加载的地图的中心点经纬度坐标   
		mapoption.hasDefaultMenu = false;
		mapoption.toolbar = MConstants.SMALL; //设置地图初始化工具条，ROUND:新版圆工具条   *
		mapoption.toolbarPos=new MPoint(5,5); //设置工具条在地图上的显示位置   
		mapoption.overviewMap = MConstants.MINIMIZE; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）   *
		mapoption.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。   *
		mapoption.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标  *

		//mapoption.maxZoomLevel=17;
		mapoption.logoUrl = "../newimages/sidelayerimages/mask.png";
		mapoption.groundLogo = "../newimages/sidelayerimages/mapbackgroud.jpg";
		mapoption.language = MConstants.MAP_CN;//设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图   **
		mapoption.fullScreenButton = MConstants.HIDE;//设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏   **
		mapoption.centerCross = MConstants.HIDE;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏   **
		mapoption.requestNum=100;//设置地图切片请求并发数。默认100。   **
		mapoption.isQuickInit=true;//设置是否快速显示地图，true显示，false不显示。   **
		mapObj = new MMap("map2", mapoption); //地图初始化
	 
	   	mapObj.setKeyboardEnabled(false);

	   	
	   	mapObj.addEventListener(mapObj,MConstants.MAP_READY,map_ready);
		
		//var div2 = draw2();
		
		
		//mapObj.setZoomLevel(13);
	}
	function map_ready(param){ 
		if(jQuery('#alarm_id').val()==''){
		var vin=jQuery('#vehicle_vin').val(); 
			AlarmDwr.getRecentAlarmInfo(vin,"","99", firstnoidcall);
            function firstnoidcall(data)
            {
			  if(data.alarm_count>0){
				  jQuery('#alarmtypename').html(data.alarm_type_name);
	              jQuery('#alarmtime').html(data.alarm_time);
	              jQuery('#alarmcount').html(data.alarm_count);
	              jQuery('#alarmspeed').html(data.speeding);
	              jQuery('#alarmdriver').html(data.driver_name);
	              jQuery('#drivertel').html(data.driver_tel);
	              //jQuery('#opeerate_desc').css('color','#999999');
	              //jQuery('#opeerate_desc').html('处理意见');
	              if(data.zonename == "定位无效"){
	            	  jQuery('#alarm_start_longitude').val("FFFF");
		              jQuery('#alarm_start_latitude').val("FFFF");
	              } else {
		              jQuery('#alarm_start_longitude').val(data.longitude);
		              jQuery('#alarm_start_latitude').val(data.latitude);
	              }
	              jQuery('#alarm_id').val(data.alarm_id);
	              jQuery('#alarmtypeid').val(data.alarm_type_id);
	              jQuery('#direction').val(data.direction);
	              fillDetails(data,data.alarm_type_id);
	              showpoint();  
			  }else{
				  jQuery('#alarmcount').html('0');  
				  var vin = document.getElementById("vehicle_vin").value;
				  GPSDwr.getRealVehcileByVin(vin,get_realposition);
			  }
		   }   
		}else{
			var vin=jQuery('#vehicle_vin').val();
			var alarmid=jQuery('#alarm_id').val();
			var alarmtypeid=jQuery('#alarmtypeid').val();
			var alarmtime=jQuery('#alarm_time').val();
			var entId = jQuery("#chooseorgid").val();
			AlarmDwr.getFirstDetail(alarmid,vin,alarmtime,alarmtypeid,entId,firstiddcall);
			 function firstiddcall(data)
	            {
				  if(data.alarm_time!=null){
					  jQuery('#alarmtypename').html(data.alarm_type_name);
		              jQuery('#alarmtime').html(data.alarm_time);
		              jQuery('#alarmcount').html(data.alarm_count);
		              fillDetails(data,alarmtypeid);
		              //jQuery('#opeerate_desc').css('color','#999999');
		              //jQuery('#opeerate_desc').html('处理意见');
		              if(data.zonename == "定位无效"){
		            	  jQuery('#alarm_start_longitude').val("FFFF");
			              jQuery('#alarm_start_latitude').val("FFFF");
		              } else {
			              jQuery('#alarm_start_longitude').val(data.longitude);
			              jQuery('#alarm_start_latitude').val(data.latitude);
		              }
		              //jQuery('#alarm_start_longitude').val(data.longitude);
		              //jQuery('#alarm_start_latitude').val(data.latitude);
		              //jQuery('#alarm_id').val(data.alarm_id);
		              //jQuery('#alarmtypeid').val(data.alarm_type_id);
		              jQuery('#direction').val(data.direction);
		              showpoint();
				  }else{
					  jQuery('#alarmcount').html('0');  
					  var vin = document.getElementById("vehicle_vin").value;
					  GPSDwr.getRealVehcileByVin(vin,get_realposition);  
				  }
			   }
		}
// 		alert(jQuery("td:contains('定位无效')",jQuery("#ala_details")).length() > 0);
// 		if(jQuery("td:contains('定位无效')",jQuery("#ala_details")).length() > 0){
// 			mapObj.mapObj.removeAllOverlays();
// 		}
    }
	
	function fillDetails(data,alarmtypeid){
        if("1"==alarmtypeid){
      	  jQuery('#ala_details').html(
          		  '<th>核载人数:</th>' +
            		  '<td>'+data.standard_cnt+'</td>'+
              	  '<th>空座位数:</th>' +
                    '<td>'+data.remain_cnt+'</td>'+
              	  '<th>驾驶员:</th>' + 
                	  '<td>'+data.driver_name+'</td>');
        }else if("2"==alarmtypeid){
            jQuery('#ala_details').html(
          		  '<th>计划时间:</th>' +
            		  '<td>'+data.send_time+'</td>'+
              	  '<th>提前时长:</th>' +
                    '<td>'+data.early_time+"（分）"+'</td>'+
              	  '<th>驾驶员:</th>' + 
                	  '<td>'+data.driver_name+'</td>');
        }else if("3"==alarmtypeid){
        	var address = data.zonename==null?"":data.zonename.substring(0,8);
            jQuery('#ala_details').html(
          		  '<th align="left" width="15%">开门位置:</th>' +
            		  '<td  width="30%" title="'+data.zonename+'">'+address+'</td>'+
            	  '<th align="left" width="1%"></th>' +
            		  '<th width="1%" ></td>'+
              	  '<th align="left" width="12%">驾驶员:</th>' +
                    '<td  width="30%">'+data.driver_name+'</td>');
        }else if("4"==alarmtypeid){
            jQuery('#ala_details').html(
          		  //'<th>迟到时间:</th>' +
            		  //'<td>'+data.later_config+'</td>'+  data.alarm_time.substr(11,5) 
            	  '<th>到达时间:</th>' +
            	  	'<td>'+data.alarm_time.substr(11,5)+'</td>'+
              	  '<th>迟到时长:</th>' +
                    '<td>'+data.late_time+"（分）"+'</td>'+
              	  '<th>驾驶员:</th>' +
                	  '<td>'+data.driver_name+'</td>');
        }else if("5"==alarmtypeid){
            jQuery('#ala_details').html(
            		
          		  '<th>计划时间:</th>' +
            		  '<td>'+data.send_time+'</td>'+
              	  '<th>延迟时长:</th>' +
                    '<td>'+data.delay_time+"（分）"+'</td>'+
              	  '<th>驾驶员:</th>' + 
                	  '<td >'+data.driver_name+'</td>');
        }else if("010"==alarmtypeid){
            jQuery('#ala_details').html(
          		  '<th>加油地点:</th>' +
            		  '<td title="'+data.zonename+'">'+data.zonename.substring(0,8)+'</td>'+
              	  '<th>加油油量:</th>' +
                    '<td>'+data.add_oill+"（升）"+'</td>'+
              	  '<th style="width: 49px;">驾驶员:</th>' + 
                	  '<td style="visibility: hidden;width: 110px;">'+data.driver_name+'</td>');
        }else if("12"==alarmtypeid || "010,12"==alarmtypeid){
            jQuery('#ala_details').html(
              	  '<th>加油油量:</th>' +
                    '<td>'+data.add_oill+"（升）"+'</td>'+
                  '<th align="left" width="1%"></th>' +
          		  	'<th width="1%" ></td>'+
              	  '<th>驾驶员:</th>' + 
                	  '<td>'+data.driver_name+'</td>');//'+data.alarm_time+'
        }else if("001"==alarmtypeid){
            jQuery('#ala_details').html(
          		  '<th>骤减位置:</th>' +
          		  '<td title="'+data.zonename+'">'+data.zonename.substring(0,8)+'</td>'+
              	  '<th>骤减油量:</th>' +
                    '<td>'+data.add_oill+"（升）"+'</td>'+
              	  '<th>驾驶员:</th>' + 
                	  '<td>'+data.driver_name+'</td>');
        }else if("40"==alarmtypeid){
            jQuery('#ala_details').html(
          		  '<th align="left" width="15%">告警位置:</th>' +
          		  '<td width="35%" title="'+data.zonename+'">'+data.zonename.substring(0,8)+'</td>'+
          		  '<th align="left" width="1%"></th>' +
      		  		'<th width="1%" ></td>'+
              	  '<th align="left" width="12%">驾驶员:</th>' + 
               	  '<td width="38%">'+data.driver_name+'</td>');//'+data.alarm_time.substring(10)+'
        }else if("32"==alarmtypeid){
            jQuery('#ala_details').html(
              	  '<th align="left" width="8%">车速:</th>' +
                  	'<td width="42%">'+data.speeding+'</td>'+
                  '<th align="left" width="1%"></th>' +
        		  	'<th width="1%" ></td>'+
              	  '<th align="left" width="12%">驾驶员:</th>' + 
               	  	'<td width="38%">'+data.driver_name+'</td>');
            	  //'+data.zonename+'---'+data.zonename.substring(0,8)+'
        }
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
	function showpoint(){
		 var lon = document.getElementById("alarm_start_longitude").value;
		 var lat =document.getElementById("alarm_start_latitude").value;
		 var vehicle_ln=document.getElementById("vehicle_ln").value;
		 var direction=document.getElementById("direction").value;
		//点的属性设置
		 var markerOption = new MMarkerOptions();
		//标注图片或SWF的url，默认为蓝色气球图片   
		markerOption.imageUrl="../images/arrow_red.png";
		 markerOption.imageSize = new MSize(14,32);
		//图片锚点BOTTOM_CENTER相对于标注位置的位置  
		 markerOption.imageAlign=MConstants.BOTTOM_CENTER;
		//设置图标旋转的角度  
		if(""==direction||null==direction||"undefined"==direction)
		{
		   direction=0;
		}
		markerOption.rotation=direction;
		//是否在地图中显示信息窗口，true，可以显示（默认）；false，不显示    
	    markerOption.canShowTip= false;  
	  //设置点的标注参数选项   
		//markerOption.labelOption=addMarkerLabel(vehicle_ln,MConstants.TOP_CENTER);

	    markerOption.picAgent=false;
		markerOption.isEditable=false; //设置点是否可编辑。
		markerOption.hasShadow=false;  //是否显示阴影。	
		markerOption.zoomLevels=[]; //设置点的缩放级别范围。
		markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
		markerOption. dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
		//通过经纬度坐标及参数选项确定标注信息  
		if((lon.length == 0 || lon == "FFFF") || (lat.length == 0 || lat == "FFFF")){
			/* mapObj.setZoomAndCenter(10,new MLngLat(113.686, 34.693)); */
			
			var mapground = "<s:property value="#session.themeProfile.ismapgroundlogo" />";
			var lon = mapground.split(",")[0];
			var lat = mapground.split(",")[1];
			mapObj.setZoomAndCenter(10,new MLngLat(lon, lat));
			/* if(jQuery("#isMapGroundLogo").val() == "0"){
				mapObj.setZoomAndCenter(10,new MLngLat(113.686, 34.693));
	    	} else {
	    		mapObj.setZoomAndCenter(10,new MLngLat(117.08 , 36.11));
	    	} */
			return;
		}  else if((parseFloat(lon) < 73 || parseFloat(lon) > 137) || (parseFloat(lat) < 1 || parseFloat(lat) > 55)){
			/* if(jQuery("#isMapGroundLogo").val() == "0"){
				mapObj.setZoomAndCenter(10,new MLngLat(113.686, 34.693));
	    	} else {
	    		mapObj.setZoomAndCenter(10,new MLngLat(117.08 , 36.11));
	    	} */
			
			var mapground = "<s:property value="#session.themeProfile.ismapgroundlogo" />";
			var lon = mapground.split(",")[0];
			var lat = mapground.split(",")[1];
			mapObj.setZoomAndCenter(10,new MLngLat(lon, lat));
			return;
		} else {
			mapObj.setZoomAndCenter(18,new MLngLat(lon,lat));//同时设置地图的中心点及zoom级别 
		}
		Mmarker = new MMarker(new MLngLat(lon,lat),markerOption);   
		 
		//对象编号，也是对象的唯一标识   
		Mmarker.id="mark101";   
		//向地图添加覆盖物   
		mapObj.addOverlay(Mmarker,false) ; 
		//setFitV(Mmarker.id,arrForFitView);
		mapObj.setCenter(new MLngLat(lon,lat));
		mapObj.setZoomLevel(17);			 	 		 
	}

	// 自适应room值
	function setFitV(overlayid,arr){          
		    arr.push(overlayid);   
		    mapObj.setFitview(arr);   
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

		function posttabOffCommand(){
			var vin = document.getElementById("vehicle_vin").value;
			var color=jQuery('#opeerate_desc').css('color');
			var desc =trim(document.getElementById("opeerate_desc").value);
			if(color=="#999999"){
				desc="";
			}
			//var desc = document.getElementById("opeerate_desc").value.trim();
			var userid = document.getElementById('optionUserid').value;
			var alarmtypeid=document.getElementById('alarmtypeid').value;
			var alarmid=document.getElementById('alarm_id').value;
			var alarmtime=jQuery("#alarmtime").html();
			//var alarmcount= jQuery('#alarm_id').val();
			if(alarmid=='null'||alarmid.length<1){
				tishiShow("无告警需要处理！");
			    tishiHide();
			    return;
			}
			if(desc.length>100)
			{
				//alert("描述长度请小于32个字符!");
				tishiShow("输入长度请小于100个字！");
			    tishiHide();
			    return;
				//return;
			}
			var flag = "";
			DWREngine.setAsync(false);
			if(alarmtypeid==1||alarmtypeid==2||alarmtypeid==3||alarmtypeid==4||alarmtypeid==5){
				if(alarmtypeid=='001'&&desc.length==0){     //油量告警处理意见不能为空
					tishiShow("处理意见不能为空！");
				    tishiHide();
				    return;
				}
				AlarmDwr.postTqcCommand(userid,vin,desc,alarmtypeid,alarmid,alarmtime,function (obj){
					  if (obj != null) {  
						  flag=obj;
					  }
					  });
			} else if(alarmtypeid=='001' || alarmtypeid=='010' || alarmtypeid=='12' || alarmtypeid=='010,12'){
				AlarmDwr.postTqcCommand(userid,vin,desc,alarmtypeid,alarmid,alarmtime,function(obj){
					if(obj != null){
						flag = obj;
					}
				});
			} else {
				AlarmDwr.postOffCommand(userid,vin,desc,alarmtypeid,alarmid,alarmtime,function (obj){
					  if (obj != null) {
						  flag=obj;
					    }
					  });
			}
			DWREngine.setAsync(true); 
            if(flag=="0"||flag=="2"){
            	 if(flag=="0"){
                 	tishiShow("处理成功！");
                 	jQuery("#curLength").html("0");
                 	window.parent.chooseAlarmSearch();
                 	try{
	                 	top.searchList();
                 	}catch(e){
                 		
                 	}
                 	jQuery("#opeerate_desc").val("");
     			    tishiHide();
                 }else{
                 	tishiShow("处理中！");
                 	try{
	                 	top.searchList();
                 	}catch(e){
                 		
                 	}
                 	jQuery("#opeerate_desc").val("");
     			    tishiHide();   
                 }
            	var entId = jQuery("#chooseorgid").val();
            	AlarmDwr.getRecentAlarmInfo(vin,entId,alarmtypeid,callBackFun);
                function callBackFun(data)
                {
					  var alarmtypeid = data.alarm_type_id;
  				  if(data.alarm_time!=null){
  					  jQuery('#alarmtypename').html(data.alarm_type_name);
  		              jQuery('#alarmtime').html(data.alarm_time);
  		              jQuery('#alarmcount').html(data.alarm_count);
  		              jQuery('#alarmtypeid').val(data.alarm_type_id);
  		          	  jQuery('#alarm_id').val(data.alarm_id);
  		              fillDetails(data,alarmtypeid);
  		              //jQuery('#opeerate_desc').css('color','#999999');
  		              //jQuery('#opeerate_desc').html('处理意见');
  		              jQuery('#alarm_start_longitude').val(data.longitude);
  		              jQuery('#alarm_start_latitude').val(data.latitude);
  		              //jQuery('#alarm_id').val(data.alarm_id);
  		              //jQuery('#alarmtypeid').val(data.alarm_type_id);
  		              jQuery('#direction').val(data.direction);
  		              showpoint();  
  				  }else{
  					  var vin = document.getElementById("vehicle_vin").value;
  					  jQuery('#alarmtypename').html("");
  		              jQuery('#alarmtime').html("");
  		              jQuery('#alarmcount').html("0");
  		              jQuery('#alarmtypeid').val("");
  		          	  jQuery('#alarm_id').val("");
  		          	  jQuery('#ala_details').html("");
  		              //jQuery('#opeerate_desc').css('color','#999999');
  		              //jQuery('#opeerate_desc').html('处理意见');
  					  GPSDwr.getRealVehcileByVin(vin,get_realposition);  
  				  }
  			   }
                //{
                	
/*                     var alarmtypeid = data.alarm_type_id;
                    fillDetails(data,alarmtypeid);
                	var sourceid=jQuery('#sourceid').val();
                	if(sourceid==2){
                    	  window.parent.qinli();
                          window.parent.searchList();
                      }
                  jQuery('#alarmtypename').html(data.alarm_type_name);
                  jQuery('#alarmtime').html(data.alarm_time);
                  jQuery('#alarmcount').html(data.alarm_count);
                  jQuery('#alarmspeed').html(data.speeding);
                  jQuery('#alarmdriver').html(data.driver_name);
                  jQuery('#drivertel').html(data.driver_tel);
                  jQuery('#opeerate_desc').css('color','#999999');
                  jQuery('#opeerate_desc').html('处理意见');
                  jQuery('#alarm_start_longitude').val(data.longitude);
                  jQuery('#alarm_start_latitude').val(data.latitude);
                  jQuery('#alarm_id').val(data.alarm_id);
                  jQuery('#alarmtypeid').val(data.alarm_type_id);
                  jQuery('#direction').val(data.direction);
                  if(data.alarm_count!=0){
                	  showpoint(); 
                  }else{
                     var vin = document.getElementById("vehicle_vin").value;
          			GPSDwr.getRealVehcileByVin(vin,get_realposition);  
                  }
                   */
                  //alert(jQuery('#alarm_start_longitude').val());
                  //jQuery('#alarm_start_longitude').html(data.longitude);
                  //jQuery('#alarm_start_latitude').html(data.latitude);
                  //jQuery('#alarm_id').html(data.alarm_id);
                  //jQuery('#alarmtypeid').html(data.alarm_type_id);
                  //jQuery('#vehicle_vin').html(data.vehicle_vin);
                  //jQuery('#direction').html(data.direction);
                //}
                
            }else{
            	tishiShow("处理失败！");
			    tishiHide(); 
            }
			

			function postAlarm_CallBack(data){
				if(data=="0"){
					alert("处理成功");
					jQuery("#opeerate_desc").val();
					window.parent.test();
					//art.dialog.close();	
					
					
					}
				else if(data=="2"){
					alert("处理中");
					art.dialog.close();
				}
				else{
					alert("处理失败");
					art.dialog.close();	
					}
				}
			
			//window.parent.tabcount();
		}

		function posttabStuOffCommand(){
			var vin = document.getElementById("vehicle_vin").value;
			var desc = document.getElementById("opeerate_desc").value;
		
			var userid = document.getElementById('optionUserid').value;
			var alarmtypeid=document.getElementById('alarmtypeid').value;
			var alarmid=document.getElementById('alarm_id').value;
			if(desc.length>100)
			{
				alert("描述长度请小于100个字符!");
				return;
			}
			AlarmDwr.postStuCommand(userid,vin,desc,alarmtypeid,alarmid,poststuAlarm_CallBack);

			function poststuAlarm_CallBack(data){
				if(data=="0"){
					alert("处理成功");
					art.dialog.close();	
					}
				else{
					alert("处理失败");
					art.dialog.close();	
					}
				}
		}

		function postdealOffCommand(){
			var vin = document.getElementById("vehicle_vin").value;
			var desc = document.getElementById("opeerate_desc").value;
			var userid = document.getElementById('optionUserid').value;
			var alarmtypeid=document.getElementById('alarmtypeid').value;
			var alarmid=document.getElementById('alarm_id').value;
			if(desc.length>32)
			{
				alert("描述长度请小于32个字符!");
				return;
			}
			AlarmDwr.postOffCommand(userid,vin,desc,alarmtypeid,alarmid,postdealAlarm_CallBack);

			function postdealAlarm_CallBack(data){
				if(data=="0"){
					alert("处理成功");
					art.dialog.close();
					art.dialog.parent.searchList();
					}
				else if(data=="2"){
					alert("处理中");
					art.dialog.close();
					art.dialog.parent.searchList();
				}
				else{
					alert("处理失败");
					art.dialog.close();	
					}
				}
		}
		
		function postStudealOffCommand(){
			var vin = document.getElementById("vehicle_vin").value;
			var desc = document.getElementById("opeerate_desc").value;
			var userid = document.getElementById('optionUserid').value;
			var alarmtypeid=document.getElementById('alarmtypeid').value;
			var alarmid=document.getElementById('alarm_id').value;
			if(desc.length>32)
			{
				alert("描述长度请小于32个字符!");
				return;
			}
			AlarmDwr.postStuCommand(userid,vin,desc,alarmtypeid,alarmid,poststudealAlarm_CallBack);

			function poststudealAlarm_CallBack(data){
				if(data=="0"){
					alert("处理成功");
					art.dialog.close();	
					art.dialog.parent.searchList();
					}
				else{
					alert("处理失败");
					art.dialog.close();	
					}
				}
		}

		function cancleCommand(){
			art.dialog.close();	
		}


	//根据点的分布范围，显示对应的缩放比例
	/*
	function boundshowpoint(){
		 //setLngLatBounds(bounds)
		 var points = mapObj.getOverlaysByType(MOverlay.TYPE_MARKER);
		 if(points.length != 0){
		  var maxx = points[0].lnglat.lngX;
		  var maxy = points[0].lnglat.latY;
		  var minx = points[0].lnglat.lngX;
		  var miny = points[0].lnglat.latY;
		 
		  for(var i = 1; i < points.length; i++){
		   if(points[i].lnglat.lngX >= maxx){
		    maxx = points[i].lnglat.lngX;
		   }
		   else if(points[i].lnglat.lngX <= minx){
		    minx = points[i].lnglat.lngX;
		   }
		 
		   if(points[i].lnglat.latY >= maxy){
		    maxy = points[i].lnglat.latY;
		   }
		   else if(points[i].lnglat.latY <= miny){
		    miny = points[i].lnglat.latY;
		   }  
		  }
		  mapObj.setLngLatBounds(new MLngLatBounds(new MLngLat(minx,miny),new MLngLat(maxx,maxy))); 
		 }
	}*/
function get_realposition(data){
   if(data != null && data.length>0){
	   var lon = data[0].LONGITUDE;
	   var lat = data[0].LATITUDE;
	   var color=data[0].color;
	   var direction=data[0].DIRECTION;
	   var vehicle_ln=document.getElementById("vehicle_ln").value;
	   var markerOption = new MMarkerOptions();
		//标注图片或SWF的url，默认为蓝色气球图片   
	   if(color=="b"){
		 	markerOption.imageUrl="../newimages/arrow_blue.png";
		}
		else if(color=="r"){
		 	markerOption.imageUrl="../newimages/arrow_red.png";
		}
		else{
		 	markerOption.imageUrl="../newimages/arrow_gray.png";
		}
		 markerOption.imageSize = new MSize(14,32);
		//图片锚点BOTTOM_CENTER相对于标注位置的位置  
		 markerOption.imageAlign=MConstants.BOTTOM_CENTER;
		//设置图标旋转的角度  
		if(""==direction||null==direction||"undefined"==direction||"FFFF"==direction)
		{
		   direction=0;
		}
		markerOption.rotation=direction;
		//是否在地图中显示信息窗口，true，可以显示（默认）；false，不显示    
	    markerOption.canShowTip= false;  
	    //markerOption.labelOption=addMarkerLabel(vehicle_ln,MConstants.TOP_CENTER);

	    markerOption.picAgent=false;
		markerOption.isEditable=false; //设置点是否可编辑。
		markerOption.hasShadow=false;  //是否显示阴影。	
		markerOption.zoomLevels=[]; //设置点的缩放级别范围。
		markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
		markerOption. dimorphicColor='0x00A0FF'; //设置第二种状态的颜色

	    
		//通过经纬度坐标及参数选项确定标注信息  
		var Mmarker = new MMarker(new MLngLat(lon,lat),markerOption);   
		//设置点的标注参数选项   
		
		//对象编号，也是对象的唯一标识   
		Mmarker.id="mark101";   
		//向地图添加覆盖物   
		mapObj.addOverlay(Mmarker,false) ; 
		//setFitV(Mmarker.id,arrForFitView);	
		mapObj.setCenter(new MLngLat(lon,lat));
		mapObj.setZoomLevel(15);
   } else {
	   mapObj.removeAllOverlays();
	   mapObj.setZoomAndCenter(10,new MLngLat(113.686, 34.693));//同时设置地图的中心点及zoom级别   
   }
}

function cleartext(){
	var color=jQuery('#opeerate_desc').css('color');
	if(color=='#999999'){
	jQuery('#opeerate_desc').html('');
	jQuery('#opeerate_desc').css('color','');
	}
}

function morealarm(){
	var vin=jQuery('#vehicle_vin').val();
	var alarmTypeId = jQuery("#alarmtypeid").val();
	if(alarmTypeId == "40"){
		alarmTypeId = "'40'";
	} else if(alarmTypeId == "32"){
		alarmTypeId = "'32'";
	} else if(alarmTypeId == "2"){
		alarmTypeId = "'2'";
	} else if(alarmTypeId == "3"){
		alarmTypeId = "'3'";
	} else if(alarmTypeId == "4"){
		alarmTypeId = "'4'";
	} else if(alarmTypeId == "010"){
		alarmTypeId = "'010','001'";
	} else if(alarmTypeId == "001"){
		alarmTypeId = "'010','001'";
	} else if(alarmTypeId == "5"){
		alarmTypeId = "'5'";
	} else if(alarmTypeId == "1"){
		alarmTypeId = "'1'";
	}
	var morurl="<s:url value='/busalarm/newmorealarm.shtml'/>?newalarm_type_id="+alarmTypeId+"&vin="+vin;
	jQuery('#morealarm').attr('href',morurl);
}

function hideshowresultDiv(flag){
	if(flag==1){
		jQuery('#Layer1').css('display','none');
	}
	else if(flag==0){
		jQuery('#Layer1').css('display','block');
	}	
}

function tishiShow(info){
	
	hideshowresultDiv(0);
	document.getElementById("inforeault").innerHTML=info;
}
function tishiHide(){
	window.setTimeout("hideshowresultDiv(1)",1000);
}
function testspeed(){
	mapObj = null;
	document.getElementById("map2").innerHTML = "";

}
function getTxtLength(obj){
	var txt = jQuery(obj).val();
	jQuery("#curLength").html(txt.length);
}
</script>


