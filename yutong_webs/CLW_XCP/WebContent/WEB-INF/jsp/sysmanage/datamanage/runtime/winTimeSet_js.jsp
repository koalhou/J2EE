<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">


function searchSignSet(){
	if(top.getIsBtn()=="look"){
		//jQuery("#cancelButton").css("display","none");
		jQuery("#ysButton").css("display","none");
		jQuery("#delButton").css("display","none");
		jQuery("input[type='text']").attr("disabled","disabled");
	} else if(top.getIsBtn()==""){
		jQuery("#delButton").css("display","none");
	}
	if(jQuery("#keyId").val().length == 0){
		return ;
	}
	jQuery.ajax({
 		  type: 'POST',  
 		  url: '<s:url value="/runtimeset/findSign.shtml" />',	
 		  data: {keyId : jQuery("#keyId").val()},	
 		  success: function(data) {
 			if(data.message == "success"){
 				var objs = data.timelist[0];
 				jQuery("#goStartTime").val(objs.goStartTime);
 				jQuery("#goEndTime").val(objs.goEndTime);
 				jQuery("#backStartTime").val(objs.backStartTime);
 				jQuery("#backEndTime").val(objs.backEndTime);
 			} else {
 				//alert("没有内容！");
 			}
 			
	      },
		  error:function (){
	    	jQuery.unblockUI();  
	      }
  });
}

/* 关闭窗口 */
function cancelArt(){
	top.cancelArt();
}

/* 执行确定操作 */
function addSet(){
	if(!checkTimes()){
		return ;
	}
	jQuery.blockUI();
		jQuery.ajax({
	 		  type: 'POST',  
	 		  url: '<s:url value="/runtimeset/addRunTimeSet.shtml" />',	
	 		  data: { goStartTime : jQuery("#goStartTime").val(),goEndTime : jQuery("#goEndTime").val(), 
	 			  	  backStartTime: jQuery("#backStartTime").val(),backEndTime: jQuery("#backEndTime").val(),
	 			  	  keyId : jQuery("#keyId").val()},	
	 		 success: function(data) {
	 			 if(data.message == "success"){
	 				top.searchrunTimeList();
		 			jQuery.unblockUI();
		 			//top.tishiWin("运行时段设置成功！");
		 			top.cancelArt();
	 			 } else if(data.message == "error"){
	 				jQuery.unblockUI();
	 				top.tishiWin("运行时段设置失败！");
	 			 } else {
	 				jQuery.unblockUI();
	 				tishiShow(data.message);
	 			 }
		      },
			  error:function (){
		    	jQuery.unblockUI();  
		      }
	  	});
	
}

function checkTimes(){
	var goSTime = jQuery("#goStartTime").val();
	var goETime = jQuery("#goEndTime").val();
	var backSTime = jQuery("#backStartTime").val();
	var backETime = jQuery("#backEndTime").val();
	if(goSTime.length==0||goETime.lenth==0||backSTime.length==0||backETime.length==0){
		tishiShow("您有未设置完的时间范围！");
		return false;
	} else if(goSTime>=goETime){
		tishiShow("上学运行时段范围设置有误！");
		return false;
	} else if(goSTime >= backSTime || goETime >= backETime){
		tishiShow("上学运行时段不能晚于放学！");
		return false;
	}else if(backSTime>=backETime){
		tishiShow("放学运行时段范围设置有误！");
		return false;
	} else if(goSTime>=backSTime && goSTime<=backETime){
		tishiShow("您设置的时间范围有重叠！");
		return false;
	} else if(goETime>=backSTime && goETime<=backETime){
		tishiShow("您设置的时间范围有重叠！");
		return false;
	} else if(backSTime>=goSTime && backSTime<=goETime){
		tishiShow("您设置的时间范围有重叠！");
		return false;
	} else if(backETime>=goSTime && backETime<=goETime){
		tishiShow("您设置的时间范围有重叠！");
		return false;
	}
	return true;
}

function deleteSet(){
	if(confirm("您确定要删除该运行时段吗？")) {
		jQuery.blockUI();
			jQuery.ajax({
		 		  type: 'POST',  
		 		  url: '<s:url value="/runtimeset/deleteRunTimeSet.shtml" />',	
		 		  data: { keyId: jQuery("#keyId").val()},	
		 		 success: function(data) {
		 			 if(data.message == "success"){
		 				top.searchrunTimeList();
			 			jQuery.unblockUI();
			 			//top.tishiWin("运行时段删除成功！");
			 			top.cancelArt();
		 			 } else {
		 				jQuery.unblockUI();
		 				top.tishiWin("运行时段删除失败！");
		 			 }
			      },
				  error:function (){
			    	jQuery.unblockUI();  
			      }
		  	});
	}
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
	tishiHide();
	hideshowresultDiv(0);
	document.getElementById("inforeault").innerHTML=info;
}
function tishiHide(){
	window.setTimeout("hideshowresultDiv(1)",1500);
}

function setTimeVal(){
	var str = "";
	for(var i = 0;i < 24;i++){
		var minis = "0";
		if(i != 0){
			minis = i%2==0?"0":"3";
		}
		if(i<10){
			str = "0"+i+":"+minis+"0"+":00";
		} else {
			str = i+":"+minis+"0"+":00";
		}
		var tmp = "<option value='"+str+"'>"+str+"</option>";
		jQuery("#goStartTime").append(tmp);
		jQuery("#goEndTime").append(tmp);
		jQuery("#backStartTime").append(tmp);
		jQuery("#backEndTime").append(tmp);
	}
}

function isEdit(){
	if(jQuery("#isOperator").val() == "2"){
		isEditSet = true;
		jQuery("#addPoint").css("display","none");
		jQuery("#addRange").css("display","none");
	} else {
		isEditSet = false;
		jQuery("#addPoint").css("display","");
		jQuery("#addRange").css("display","");
	}
}
var isEditSet = true;
jQuery(function() {
	//setTimeVal();
	searchSignSet();
});
</script>
