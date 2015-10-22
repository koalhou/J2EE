<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.form-3.45.0.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/ckeditor/ckeditor.js'/>"></script>

<script type="text/javascript">
	//上传图片
  function uploadImage() {
	var random=Math.random();
     var options = {
		url : "<s:url value='/notice/addPic.shtml'/>",
		type : "POST",
		dataType : "script",
		success : function(msg) { 
			if (msg.indexOf("#") > 0) {
				var data = msg.split("#");
				//上传成功后插入到文本编辑器的当前光标处
				$("#photo1").val(data[1]);
				$("#successLbl").html(data[0]);
				$("#errorLbl").html("");
			}else{
				$("#successLbl").html("");
				$("#errorLbl").html(msg);
			}
		},
		error : function(msg){
			alert("上传失败!");
		}
	};
	$("#updatenoticeform").ajaxSubmit(options);
	return false;
  }
    //发布日期必须
   function submitForm(){
	 //去除空格
	 trimAllObjs();
	//发布日期必须
	 var gonggaoDate=$('#gonggaoDate').val();
	 if (gonggaoDate.length<1){
		 $('#gonggaoDateDesc').text("发布日期不能为空!");
		return false;
	 }
	//标题必须
	if ($('#gonggaoTitle').val().length<1){
		$('#gonggaoTitleDesc').text("标题不能为空!");
		return false;
 	}
	//概述不能大于200字
	if ($('#gonggaoSummary').val().length<1){
		$('#gonggaoSummaryDesc').text("消息概述不能为空!");
		return false;
 	}
	if ($('#gonggaoSummary').val().length>200){
		$('#gonggaoSummaryDesc').text("消息概述不能大于200字!");
		return false;
 	}
	//内容不能大于1000字
// 	if ($('#gonggaoContent').val().length>1300){
// 		$('#gonggaoContentDesc').text("内容不能大于1300字!");
// 		return false;
//  	}
	//内容不能大于2500字
	var editorObj = CKEDITOR.instances.gonggaoContent;//获得编辑器对象 
	var simpleData=editorObj.document.getBody().getText();
	var htmlData=editorObj.getData();
	if (simpleData.length<1){
		$('#gonggaoContentDesc').text("内容不能为空!");
		return false;
 	}
	if (simpleData.length>2500){
		$('#gonggaoContentDesc').text("内容不能大于2500字!");
		return false;
 	}
	jQuery('#gonggaoContentHide').val(htmlData);
	//提交
	Wit.commitAll($('#updatenoticeform'));
	
  }
    //回退
  function goBack(url) {
	  Wit.goBack(url);
  }
  $(function(){
	  //给公告日期赋值
	  var gonggaoDate=$("#sgonggaoDate").val();
	  $("#gonggaoDate").val(gonggaoDate);
  });
  (function(){
	  jQuery(window).resize(function(){
		  jQuery("#divheightsize").height(jQuery(window).height()-160);
		});
		jQuery(window).load(function(){
			jQuery("#divheightsize").height(jQuery(window).height()-160);
		});
		//初始化文本编辑框
		
		//CKEDITOR.replace( 'gonggaoContent',{customConfig : '<s:url value="/scripts/ckeditor/notice-config.js"/>'});
		//jQuery('#gonggaoContent').val(jQuery('#gonggaoContentHide').val());
		jQuery('#gonggaoContent').html(jQuery('#gonggaoContentHide').val());
	})();
</script>
