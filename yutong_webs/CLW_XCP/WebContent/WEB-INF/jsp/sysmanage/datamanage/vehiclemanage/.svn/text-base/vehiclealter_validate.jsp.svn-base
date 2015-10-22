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
		if(!Mat.checkRequired(map)) return false;
		return true;
	}
		
	/** 检查车牌号唯一性 **/
	  function checkVehiclelnUnique(){
	    var tmCodeObj = $('vehicle_ln');
		if(!Mat.checkRequired(tmCodeObj)) return false;
		 if(/(^[A-Za-z0-9\u4e00-\u9fa5]+$)/.test(tmCodeObj.value)){
		  }else{
			  Wit.showErr(tmCodeObj, "车牌只能是汉字字母数字或其组合");
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
	 	      Wit.showErr(tmCodeObj, "<s:property value="getText('vehcileinfo.code.exist')" />");
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

	

	function alterForm() {
		    trimAllObjs();
		    var f0=checkVehiclelnUnique();
		    var f1=checkOrganizationName();
		    var driver_license = $('driver_license');
		    var driver_tel = $('driver_tel');
		    var driver_name = $('driver_name');
		    var f2;
		    var f3;
		    var f4;
		    var f5;
		    
		    if(driver_name != null && driver_license != null && driver_tel != null){
		    	 if(driver_name.value != null && driver_name.value != ''){
				    	f5=checkDriverName();
				    	if(driver_license.value == null || driver_license.value == ''){
				    		Wit.showErr(driver_license, "请输入驾驶证号！");
				    		return false;
			    		}
				    }else{
				    	Mat.showSucc(driver_license);
				    	f5 = true;
				    }
				    if(driver_license.value != null && driver_license.value != ''){
				    	var oldDriverLicense = $('old_driver_license');
				    	if(oldDriverLicense.value != driver_license.value) {
					    	f2=checkDriverLicense();
					    	f4=checkDriverLicenseUnique();
				    	}else{
				    		f2=true;
					    	f4=true;
				    	}
				    	if(driver_name.value == null || driver_name.value == ''){
				    		Wit.showErr(driver_name, "请输入驾驶员姓名！");
				    		return false;
				    	}
				    }else{
				    	Mat.showSucc(driver_name);
				    	f2=true;
				    	f4=true;
				    }
				    if(driver_tel.value != null && driver_tel.value != ''){
				    	f3=checkDriverTel();
				    }else{
				    	f3=true;
					}
		    }else{
		    	f2=true;
		    	f3=true;
		    	f4=true;
		    	f5 = true;
		    }
		    if(f0&&f1&&f2&&f3&&f4&&f5){
		    	 //var form = document.getElementById('alter_form');
		    	 alterVehicleInfo();
		    	 Wit.commitAll($('alter_form'));
		    	 
				//form.submit();
			}else{
				return false;
			}		
	}

	
	
	function setFormEvent() {
		//$('hdmf').onkeyup = checkHdmf;
		//$('hdmf').onblur = checkHdmf;
		//$('hdnm').onkeyup = checkHdnm;
		//$('hdnm').onblur = checkHdnm;
		<s:if test="#session.perUrlList.contains('111_3_3_4_4') && 3!=#session.adminProfile.userType ">
		$('alterbutton').onclick = alterForm;	
		</s:if>
	}
	
	window.addEvent('domready', setFormMessage);
	window.addEvent('domready', setFormEvent);
		
//-->
</script>

