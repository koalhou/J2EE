<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
//页面初始化调用
jQuery(document).ready( function() {
	if($('update').value == "update"){
		jQuery('#stu_name').attr('disabled',true);
	}
});

function chooseStu(){
	var url="../ftlyInfoNew/showStudent.shtml";
	art.dialog.open("<s:url value='"+url+"' />",{
		title:"人员信息",
		lock:true,
		width:625,
		height:415
	});
}

function submitForm() {
	trimAllObjs();// 过滤前后空格函数，该函数放在validation.js中
	var f0 = checkStuName();
	var f1 = checkTel();
	var f2 = checkOrg();
	var form=document.getElementById('addStealOilSmsForm');
	if($('update').value == "update")//如果是修改操作 则改变form提交路径
		form.action="updateStealOilSms.shtml";
	if(f0&f1&f2){
		var telephone = $('telephone');
		jQuery.ajax({
	 		  type: 'POST',  
	 		  url: '../ftlyInfoNew/checkTelOnly.shtml',	
	 		  data: {telephone: telephone.value,
	 			     stu_id : jQuery('#stu_id').val(),
	 			     type : $('update').value},	
		 	  success: function(data){
		 		if(data == 'error'){
		 			Wit.showErr(telephone,getErrMsg('所选人员或对应电话号码已经存在'),getErrStyle(telephone));
		 			return false;
		 		}else
		 			form.submit();
		 	  }
	    });
	}else
		return;
}

function checkStuName(){
	var stuName = $('stu_name');
	if (!Mat.checkRequired(stuName))
		return false;
	if (!Mat.checkLength(stuName,15,'姓名' + '的长度不能超过' + '15'))
		return false;
	return true;
}

function checkTel() {
	var telephone = $('telephone');
	if (!Mat.checkRequired(telephone))
		return false;
	if (!Mat.checkDigital(telephone,'电话号码必须是数字'))
		return false; 
	if(telephone.value.charAt(0) != '1'){
		Wit.showErr(telephone,getErrMsg('电话号码必须1开头'),getErrStyle(telephone));
		return false;
	}
	if (!Mat.checkEqualLength(telephone,11,'电话号码的长度必须是11'))
		return false;
	return true;
}

function checkOrg(){
	var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	var elm = $('ORG_STR');
	if(nodes.length == 0){
		Wit.showErr(elm,getErrMsg('请选择组织'),getErrStyle(elm));
		return false;
	}
	var selectIds = '';
	for(var i = 0, len = nodes.length; i < len; i++) {
        selectIds = selectIds+nodes[i].id+',';
    }
	$('organization_id').value = selectIds.substr(0,selectIds.length-1);
	return true;
}

function deleteStealOilSms(stu_id){
	if (confirm("您确定要删除吗？")) {
		window.location="../ftlyInfoNew/deleteStealOilSms.shtml?stealOilSmsInfo.stu_id="+stu_id;				
	}			
}

function firstrisize(){
	jQuery('#content').mk_autoresize({
        height_include: '#main',
        height_offset: 0 
      });
}
jQuery(function() {
	firstrisize();
	jQuery(window).mk_autoresize({
	     height_include: '#content',
	     height_exclude: ['#header', '#footer'],
	     height_offset: 0,
	     width_include: ['#header', '#content', '#footer'],
	     width_offset: 0
	  });
	 firstrisize();
});
</script>