<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jQuery/jquery-1.4.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jQuery/jquery.form.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jQuery/jquery.Jcrop.js'/>"></script>
<script type="text/javascript">
	var imgHeight = 140;
	var imgWidth = 120;
	var jcrop_api, boundx, boundy;

	function getScale(onecount, twocount) {
		var ret;
		if (onecount > 0 && twocount > 0) {
			if (onecount % 2 == 0 && twocount % 2 == 0) {
				onecount = onecount / 2;
				twocount = twocount / 2;
				return getScale(onecount, twocount);
			} else if (onecount % 3 == 0 && twocount % 3 == 0) {
				onecount = onecount / 3;
				twocount = twocount / 3;
				return getScale(onecount, twocount);
			} else if (onecount % 5 == 0 && twocount % 5 == 0) {
				onecount = onecount / 5;
				twocount = twocount / 5;
				return getScale(onecount, twocount);
			} else {
				ret = "" + onecount + "：" + twocount;
			}
		} else if (onecount == twocount) {
			ret = "1：1";
		} else if (onecount == 0 || twocount == 0) {
			ret = "" + onecount + "：" + twocount;
		}
		return ret;
	}
	
	function showCoords(c) {
		document.getElementById("left_val").value = c.x;
		document.getElementById("top_val").value = c.y;
		document.getElementById("w_val").value = c.w;
		document.getElementById("h_val").value = c.h;

		if (document.getElementById('w_val').value != "0"
			&& document.getElementById('h_val').value != "0") {
			document.getElementById('photoScale').innerHTML = "长：" + document.getElementById("h_val").value +
			                                                  "&nbsp;&nbsp;&nbsp;&nbsp;宽：" + document.getElementById("w_val").value + 
			                                                  "<br/>" + 
			                                                  "长宽比：&nbsp;&nbsp;" + 
			                                                  getScale(parseInt(document.getElementById('h_val').value, 10),parseInt(document.getElementById('w_val').value, 10));
		}
		
		if (parseInt(c.w) > 0){
          var rx = imgWidth / c.w;
          var ry = imgHeight / c.h;
          if(Math.round(rx * boundx) > 0 && Math.round(ry * boundy) > 0 && Math.round(rx * c.x) > 0 && Math.round(ry * c.y) >0) {
              try {
        	    document.getElementById("preview").style.width = Math.round(rx * boundx) + 'px';
        	    document.getElementById("preview").style.height = Math.round(ry * boundy) + 'px';
                document.getElementById("preview").style.marginLeft = '-' + Math.round(rx * c.x) + 'px';
                document.getElementById("preview").style.marginTop = '-' + Math.round(ry * c.y) + 'px';
              }catch(e) {
              }
          }
        }
        
	};

	function jcropPicture() {
		$('#picShow').Jcrop( {
			onChange : showCoords,
			onSelect : showCoords,
			setSelect : [ 0, 0, imgWidth, imgHeight ],
			minSize : [ 0, 0 ],
			maxSize : [ imgWidth, imgHeight ]
		}, function() {
			var bounds = this.getBounds();
			boundx = bounds[0];
			boundy = bounds[1];
			jcrop_api = this;
		});
	}

	function uploadImage() {
		if(document.getElementById("fileupload").value == "") {
			alert("请选择图片");
			return false;
		}
		
		var obj = document.getElementById("dataId");
		$(document)
				.ready(
						function() {
							var options = {
								url : '<s:url value="/picturefileupload/uploadFile.shtml" />?dataId=' + obj.value,
								type : "POST",
								dataType : "script",
								success : function(msg) {
									if (msg.indexOf("#") > 0) {
										var data = msg.split("#");
										//$("#successLbl").html(data[0]);
										$("#showImage")
												.html("<img id='"
																+ "picShow'"
																+ " src='"
																+ '<s:url value="/picturefileupload/showImage.shtml" />'
																+ "?imageUrl="
																+ data[1]
															    + "&dataId="
															    + obj.value
																+ "&rand="
																+ Math.random()
																//+ "'"
																//+ "class='"
																//+"mycss"
																+ "'/>");
										//document.getElementById("noPhoto").style.display="none";
										document.getElementById("photoPreview").style.display="block";
										var imgPreview =  document.getElementById("preview");
										imgPreview.src="<s:url value='/picturefileupload/showImage.shtml'/>"+ "?dataId=" + obj.value+ "&rand=" + Math.random();
										try {
										  jcropPicture();
										}catch(e){
										}
										document.getElementById("fileupload").outerHTML = document.getElementById("fileupload").outerHTML;
										document.getElementById("fileupload").value = '';
										
										
									} else {
										$("#successLbl").html(msg);
									}
								}
							};
							$("#form2").ajaxSubmit(options);
							return false;
						});
	}

	function resize(str) {
		$("#showImage").load("<s:url value='/picturefileupload/resizeImage.shtml' />",
			  { dataId: document.getElementById("dataId").value, zoomType:str}, 
			  function(ret){
				  $("#showImage").html("<img id='"
										+ "picShow'"
										+ " src='"
										+ '<s:url value="/picturefileupload/showImage.shtml" />'
										+ "?dataId="
									    + document.getElementById("dataId").value
										+ "&rand="
										+ Math.random()
										+ "'/>");
				  document.getElementById("photoPreview").style.display="block";
				  var imgPreview =  document.getElementById("preview");
				  imgPreview.src="<s:url value='/picturefileupload/showImage.shtml'/>"+ "?dataId=" + document.getElementById("dataId").value+ "&rand=" + Math.random();
				  try {
				    jcropPicture();
				  }catch(e){
				  }
		  	  }
		);
	}
	
	function saveImg() {
		if(document.getElementById('top_val').value=="" ||
		   document.getElementById('left_val').value=="" ||
		   document.getElementById('w_val').value == "" ||
		   document.getElementById('h_val').value == "") {
			alert("未选择图片");
			return false;
		}
		if(document.getElementById('w_val').value == "0" ||
				document.getElementById('h_val').value == "0") {
			alert("请在图片中截取要显示的部分");
			return false;
		}
		jQuery.ajax({
			type: 'POST', 
			url: "<s:url value='/picturefileupload/saveImage.shtml' />",	
			data: {
				top:document.getElementById('top_val').value,
				left:document.getElementById('left_val').value,
				width:document.getElementById('w_val').value,
				height:document.getElementById('h_val').value,
				dataId:document.getElementById("dataId").value
			},
			success: function(data) {
				if(data=="success"){
					var obj = document.getElementById('successLbl');
					obj.innerHTML = "图片保存成功！";
					window.parent.document.getElementById("picId").value=document.getElementById("dataId").value;
					window.parent.photoReload();
					art.dialog.close();
				}
				if(data == "error") {
					var obj = document.getElementById('errorLbl');
					obj.innerHTML = "图片保存失败！";
					return false;
				}
			}  
		});
	}

	function init() {
		document.getElementById("photoPreview").style.display = "none";
	}
</script>