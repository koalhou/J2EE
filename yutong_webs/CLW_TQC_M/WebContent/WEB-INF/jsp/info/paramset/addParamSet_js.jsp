<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/ckeditor/ckeditor.js'/>"></script>

<script type="text/javascript">
   function submitForm(){
	 //去除空格
	 trimAllObjs();
	//参数名称不能为空
	var paramName=$('#paramName').val();
	 if (paramName.length<1){
		 $('#paramNameDesc').text("参数名称不能为空!");
		return false;
	 }
	 if(paramName!=""){
		 var str=paramName.match(/^YGB_/);
		 if(str==null){
			 $('#paramNameDesc').text("参数名称应以YGB_开始!");
			 return false;
		 }
	 }
	//参数值不能为空
	if ($('#paramValue').val().length<1){
		$('#paramValueDesc').text("参数值不能为空!");
		return false;
	}
	//提交
	if($('#oldParamName').val()!=""){
		var form=document.getElementById("addParamSetForm");
		form.action="updateParamSet.shtml";
		form.method="post";
		Wit.commitAll(form);
	}else{
		Wit.commitAll($('#addParamSetForm'));
	}
	
  }
    //回退
  function goBack(url) {
	  Wit.goBack(url);
  }
</script>
