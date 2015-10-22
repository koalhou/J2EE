<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type="text/javascript">
  
  function submit() {
    Wit.commitAll($('vehbrowse_form'));
  }
  
  /** 取消用户选择 **/
  function cancelSelect(){
	  
    window.dialogArguments.document.getElementById("vinStr").value="";
    window.dialogArguments.document.getElementById("gonggao_veh").value="";
    window.close();
  }

  /**
	 * 用户全选控制
	 */
	function setOrCancelSelect(obj) {
		var carChoiceObjs = document.getElementsByName("carChoice");
		if(obj.checked) {
		    for(var i=0; i<carChoiceObjs.length; i++) {
		    	if(carChoiceObjs[i].type=="checkbox") {
		    		carChoiceObjs[i].checked=true; 
		    	} 
		    }
		} else {
			for(var i=0; i<carChoiceObjs.length; i++) {
				if(carChoiceObjs[i].type=="checkbox") {
					carChoiceObjs[i].checked=false; 
		    	} 
		    }
		}
	}

	function getCheckValue(vin,index){
		var carsChoice = document.getElementsByName("carChoice");
		var vinStr=document.getElementById("vinStr").value;
        if(carsChoice[index].checked==true) {
            if(vinStr=="") {
            	vinStr =   vin ;
            } else {
            	vinStr += "," + vin ;
            }
        }else{
			if(vinStr.indexOf(vin)==0) {
				vinStr=vinStr.substr(vin.length+1);
			}
        	if(vinStr.indexOf(vin)>0) {
        		vinStr=vinStr.replace(","+vin,"");
            }
		}
		document.getElementById("vinStr").value=vinStr;
	}

	/**
	 * 车辆关联保存
	 */
	function save() {
		var carsChoice = document.getElementsByName("carChoice");
	    var carIdList = "";
	    var vinList = "";
	    for(var i=0; i<carsChoice.length; i++) {
	        if(carsChoice[i].checked==true) {

	            if(carIdList=="") {
	            	carIdList =   carsChoice[i].value.split(',')[0] ;
	            } else {
	            	carIdList=carIdList + "," + carsChoice[i].value.split(',')[0] ;
	            }
	            if(vinList==""){
	            	vinList =   carsChoice[i].value.split(',')[1] ;
	            } else {
	            	vinList=vinList + "," + carsChoice[i].value.split(',')[1] ;
	            }
	        }
	    }
	   document.getElementById("vinStr").value = vinList;
	   window.dialogArguments.document.getElementById("vinStr").value=document.getElementById("vinStr").value;
	   window.dialogArguments.document.getElementById("gonggao_veh").value=carIdList;
	   window.close();
		 	
	}
</script>