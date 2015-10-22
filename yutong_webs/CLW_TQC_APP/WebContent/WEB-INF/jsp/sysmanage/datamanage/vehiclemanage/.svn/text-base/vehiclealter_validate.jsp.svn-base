<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript'
	src='../dwr/interface/VehicleManageDWR.js'></script>
<script>
	<!--


	function checkDesc() {
		var map = $('vehicle_code');
		if(!Mat.checkRequired(map)) return false;
		return true;
	}

	/** 取消填加操作 **/
	function goBack(url) {
	    Wit.goBack(url);
	}
	
	
	
	function setFormMessage() {
		
	}
	function checkOrganizationName() {
		var map = $('organizationName');
		if(!Mat.checkRequired(map)) {
			return false;
		}
		return true;
	}
		
	/** 检查车牌号唯一性 **/
	  function checkVehiclelnUnique(){
	    var tmCodeObj = $('vehicle_ln');
		if(!Mat.checkRequired(tmCodeObj)) return false;
		var parten = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
  	  	if(parten.exec(tmCodeObj.value)){
  		
  	  	} else {
  			Wit.showErr(tmCodeObj, "只能输入汉字、字母、数字或其组合！");
  			return false;
  	  	}
	    var oldln=$('oldln');
	    if(oldln.value != tmCodeObj.value){
	    	DWREngine.setAsync(false);
		    var ret = false; 
		    VehicleManageDWR.checkVehiclelnUnique(tmCodeObj.value, callBackFun);
		    DWREngine.setAsync(true);
		    function callBackFun(data)
		    {
		      ret = data;
		    }

		    if(ret) {
		      Wit.showErr(tmCodeObj, "<s:property value="getText('vehcileinfo.ln.exist')" />");
		      return false;
		    } else {
		      return true;
		    }
	    }else{
		    return true;
	    }  
	  }
	
	  /** 检查车辆编码唯一性 **/
	  function checkVehCodeUnique(){
		var oldcode=$('oldcode');
	    var tmCodeObj = $('vehicle_code');
	    if(!Mat.checkRequired(tmCodeObj)) return false;
 	    if(tmCodeObj.value=="外租"){
 	    	return true;	
 	    }
    	var parten = /^\d{1,3}$/;
    	  if(parten.exec(tmCodeObj.value)){
    		
    	  } else {
    		Wit.showErr(tmCodeObj, "请输入不超过三位的数字！");
    		return false;
    	  }
	    if(oldcode.value != tmCodeObj.value){
	    	var enid=$('enid');
	 	    DWREngine.setAsync(false);
	 	    var ret = false; 
	 	    VehicleManageDWR.checkVehileCodeUnique(tmCodeObj.value,enid.value, callBackFun);
	 	    DWREngine.setAsync(true);
	 	    function callBackFun(data)
	 	    {
	 	      ret = data;
	 	    }

	 	    if(ret) {
	 	      Wit.showErr(tmCodeObj, "<s:property value="getText('班车号已存在')" />");
	 	      return false;
	 	    } else {
	 	      return true;
	 	    }  
	    }else{
	    	 return true;
	    }
	   
	  }

	  /** 通知核心车牌号/车牌颜色改变 **/
	  function alterVehicleInfo(){
	    var tmCodeLn = $('vehicle_ln');
	    var oldln=$('oldln');
	    
	    var tmCodeColor = $('veh_pai_color');
	    var oldColor=$('oldColor');
	    
	    if(oldln.value != tmCodeLn.value||oldColor.value != tmCodeColor.value){
	    	//DWREngine.setAsync(false);
	    	var vehicle_vin='<s:property value="vehcileInfo.vehicle_vin"/>';
		    VehicleManageDWR.postVehiclelnadvice(vehicle_vin,tmCodeLn.value,tmCodeColor.value);
		    //DWREngine.setAsync(true); 
	    }
	  }
	  function alterCapacity(){	   
		var capacity = $('oilbox_capacity');
		
	  	if(isCapacity()){
	  		if(!Mat.checkRequired(capacity)) {
				return false;
			}
	    	var parten = /^\d{1,3}$/;
	    	  if(parten.exec(capacity.value)){
	    		  return true;
	    	  } else {
	    		Wit.showErr($('units'), "请输入不超过三位数的正整数！");
	    		return false;
	    	  }
	    }
	  	return true;
	  }
	  /** 组织必填项 **/
	  function checkDriver() {
	  	var obj = $('driver_name');
	  	if (!Mat.checkRequired(obj)){
	  		return false;
	  	}
	  	else {
	  		Mat.showSucc(obj);
	  		return true;
	  	}
	  }
	function alterForm() {
		    trimAllObjs();
		    var f0=checkVehiclelnUnique();
		    var f1=checkOrganizationName();
		    var f3=checkVehCodeUnique();
		    var f4=alterCapacity();
		    var f5=checkDriver();
		    if(f0&&f1&&f3&&f4&&f5)
			{
		    	 //var form = document.getElementById('alter_form');
		    	 alterVehicleInfo();
		    	 //Wit.commitAll($('alter_form'));
		    	 
		    	 var form = document.getElementById('alter_form');
		 		form.submit();
		    	 
				//form.submit();
			}else{
				return false;
			}
		    return;
	}

	
	
	function setFormEvent() {
		
		<s:if test="#session.perUrlList.contains('111_3_3_4_4') && 3!=#session.adminProfile.userType ">
		$('#alterbutton').onclick = alterForm;	
		</s:if>
	}
	
	function getForm(){
		$("alterbutton").onclick = alterForm;	
	}
	
	//window.addEvent('domready', setFormMessage);
	//window.addEvent('domready', setFormEvent);
		
	jQuery(function() {
		//setFormMessage();
		getForm();
	});
//-->
</script>

