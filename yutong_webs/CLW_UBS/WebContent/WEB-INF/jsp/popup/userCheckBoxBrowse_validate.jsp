<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type="text/javascript">
  /** 查询用户 **/
  function submit() {
    Wit.commitAll($('userbrowse_form'));
  }
  
  /** 取消用户选择 **/
  function cancelSelect(){
    window.dialogArguments.document.getElementById("userIdStr").value="";
    window.dialogArguments.document.getElementById("userName").value="";
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

	function getCheckValue(userid,index){
		var carsChoice = document.getElementsByName("carChoice");
		var userStr=document.getElementById("userIdStr").value;
        if(carsChoice[index].checked==true) {
            if(userStr=="") {
            	userStr =   userid ;
            } else {
            	userStr += "," + userid ;
            }
        }else{

			if(userStr.indexOf(userid)==0) {
				userStr=userStr.substr(userid.length+1);
			}
        	if(userStr.indexOf(userid)>0) {
        		userStr=userStr.replace(","+userid,"");
            }
		}
		document.getElementById("userIdStr").value=userStr;
	}

	/**
	 * 车辆关联保存
	 */
	function save() {
		
		var carsChoice = document.getElementsByName("carChoice");
	    var carIdList = "";
	    for(var i=0; i<carsChoice.length; i++) {
	        if(carsChoice[i].checked==true) {

	            if(carIdList=="") {
	            	carIdList =   carsChoice[i].value ;
	            } else {
	            	carIdList=carIdList + "," + carsChoice[i].value ;
	            }
	        }
	    }
	   window.dialogArguments.document.getElementById("userIdStr").value=document.getElementById("userIdStr").value;
	   window.dialogArguments.document.getElementById("userName").value=carIdList;
	   window.close();
		 	
	}
</script>