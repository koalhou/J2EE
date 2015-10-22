<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type="text/javascript">
	/**点击查询按钮**/
	function searchList() {
	    qinli();
	    jQuery("input[name='checkallalarm']").removeAttr("checked");
	    jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'vehicle_ln', value: jQuery('#vehicle_ln').val().trim() }, 
		 		{ name: 'alarm_type_id', value: jQuery('#alarm_type_id').val()},
		 		{ name: 'deal_flag', value: jQuery('#deal_flag').val()},
		 		{ name: 'start_time', value: jQuery('#start_time').val()},
		 		{ name: 'end_time', value: jQuery('#end_time').val()},
		 		{name: 'chooseorgid', value: jQuery('#chooseorgid').val()}]
		});
		jQuery('#gala').flexReload();
	    
	}

	function getAdressInfo(tdDiv,pid,row){
		tdDiv.innerHTML = "";
		DWREngine.setAsync(false);
		var lon = row.cell[0];
		var lat = row.cell[1];

		
	//alert("tet"+lon.trim() +","+lat.trim());
		if( lon!=null && lat!=null && lon !=""  && lat != "" && lon !=" "
			&& lat != " " && lon !="undefined" && lat != "undefined"&& lon !="&nbsp;" && lat != "&nbsp;"
			&&lat!="FFFF" && lon!="FFFF"){
		  if(lon.length >1 && lat.length >1){
			//alert(lon +","+lat);

			 var lnglatXY=new MLngLat(lon,lat); 
			 var mls =new MReGeoCodeSearch();   
			 var opt= new MReGeoCodeSearchOptions();   
			 opt.poiNumber=10;;//返回周边的POI数量,默认10   
			 opt.range=100;//限定周边热点POI和道路的距离范围   
			 opt.pattern=1;//返回数据的模式,0表示返回地标性POI,1表示返回全部POI，   
			 opt.exkey="";//排除的关键字   
			 mls.setCallbackFunction(poiToAddressSearch_CallBack);   
			 mls.poiToAddress(lnglatXY,opt);   
			 		

		
			 var addr ="";
			function poiToAddressSearch_CallBack(data){
				if(data.error_message != null){   
					resultStr="查询异常！"+data.error_message;
					return "";   
				}else{   
					switch(data.message){
					case 'ok':
						//addr =  data.SpatialBean.poiList[0].address;
						if(data.SpatialBean.roadList!=null && data.SpatialBean.roadList.length>0){
							addr = data.SpatialBean.roadList[0].name;
						}
						
						
						//alert(addr);
						if(addr != null && addr != "" && addr!= "undefined"){
							//alert(addr);
							return   addr+"附近";
						}
						else{
							return  "";
						}
						break; 
					case 'error':
						addr ="";
						return  "";
						break; 
					default: 
						addr ="";
						return "";
					}
				}
			}
		}}
		else{
			return "";
		}
		
		DWREngine.setAsync(true);
	}

   function mytreeonClick(event, treeId, treeNode){
		document.getElementById('chooseorgid').value=treeNode.id;
	    searchList();
	}

	function mychange(){
		 searchList();
	}

	function chaxun(){
		if(event.keyCode==13){
			searchList();
		}
	}
</script>