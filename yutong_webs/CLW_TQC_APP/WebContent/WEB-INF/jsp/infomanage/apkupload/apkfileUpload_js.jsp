<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jQuery/jquery.form.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jQuery/jquery.Jcrop.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
	var flag=0;
	function uploadImage() {
		if(document.getElementById("fileupload").value == "") {
			alert("请选择文件");
			return false;
		}
	};
	function checkapply_id(){
		var apply_id = $('apply_id');
		if(apply_id.value==""){
		    Wit.showErr(apply_id, "请输入！");
			return false;
		}
		Wit.showSucc(apply_id,Mat.succMsg);
		return true;
	}
	function checkversion_name(){
		var version_name = $('version_name');
		if(version_name.value==""){
		    Wit.showErr(version_name, "请输入！");
			return false;
		}
		Wit.showSucc(version_name,Mat.succMsg);
		return true;
	}
	function checkversion_desc(){
		var version_desc = $('version_desc');
		if(version_desc.value==""){
		    Wit.showErr(version_desc, "请输入！");
			return false;
		}
		Wit.showSucc(version_desc,Mat.succMsg);
		return true;
	}
	function checkcreater(){
		var creater = $('creater');
		if(creater.value==""){
		    Wit.showErr(creater, "请输入！");
			return false;
		}
		Wit.showSucc(creater,Mat.succMsg);
		return true;
	}
	function checkfileupload(){
		var fileupload = $('fileupload');
		if(fileupload.value==""){
		    Wit.showErr(fileupload, "请输入！");
			return false;
		}
		Wit.showSucc(fileupload,Mat.succMsg);
		return true;
	}
	function saveAPK() {
		var f0 = checkapply_id();
		var f1 = checkversion_name();
		var f2 = checkversion_desc();
		var f3 = checkcreater();
		var f4 = checkfileupload();
		if(f0&&f1&&f2&&f3&&f4) {
			jQuery("#form2").submit();
		}
	}
	function downApk() {
		document.getElementById('apkdownloadid').submit();
	}
	
	//页面自适应
	jQuery(function(){
		jQuery(window).resize(function(){
			jQuery(window).mk_autoresize({
				height_include: '#content',
				height_exclude: ['#header', '#footer'],
				height_offset: 10,
				width_include: ['#header', '#content', '#footer'],
				width_offset: 0});
		});
		jQuery(window).mk_autoresize({
			height_include: '#content',
			height_exclude: ['#header', '#footer'],
			height_offset: 10,
			width_include: ['#header', '#content', '#footer'],
			width_offset: 0});
	});
</script>