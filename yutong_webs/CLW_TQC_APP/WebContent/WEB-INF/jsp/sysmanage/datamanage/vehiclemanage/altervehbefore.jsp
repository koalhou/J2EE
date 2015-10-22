<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:property value="getText('common.title')" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
</head>

<body  onload="onloadFunc()">
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	
	<div id="content">
	<s:form id='alter_form' method="post" action="vehicle_edit"  enctype="multipart/form-data">
	<s:hidden name="page" />
	<s:hidden name="pageSize" />
	<s:hidden id="organization_id" name="vehcileInfo.organization_id" />
	<s:hidden id="orgidold" name="organization_idold"/>
	<s:hidden id="enid" name="vehcileInfo.enterprise_id"/>
	<s:hidden id="oldln" name="oldvehcileLn"/>
	<s:hidden id="oldColor" name="oldVehcileColor"/>
	<s:hidden id="oldcode" name="oldvehcileCode"/>
	<s:hidden id="vehicle_id" name="vehcileInfo.vehicle_id"/>
	<s:hidden id="vehicle_vin" name="vehcileInfo.vehicle_vin"/>
	<s:hidden id="phot_name" name="vehcileInfo.phot_name" />
	<s:hidden id="photoDelFlag" name="vehcileInfo.photoDelFlag" />
	<input type="hidden" id="ChooseEnterID_tree"
					name="ChooseEnterID_tree" value="<%=session.getValue("ChooseEnterID_tree") %>">
	<div id="main" style="overflow: auto;">
	   <table width="100%" border="0" cellspacing="0" cellpadding="0">
	 		  <tr>
	        <td  height="36"  valign="top">
				  <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
	 				 <tr>
	            		<td height="38" background="../images/contentTil_bg.gif"><span class="contentTil"><s:text name="menu3.clgl" /></span></td>
	         		 </tr>
				  </table>
	        </td>
	      </tr>
	 		  	   <tr>
	 		  	   <td align="center">
	 		 <!-- message -->
	               	<s:actionmessage cssStyle="font-size: 12px;color: #009900"
		              /><s:actionerror cssStyle="font-size: 12px;color: #CC0000"  />
	               </td>
	               </tr>
	         <tr>
	         <td valign="top">
	
	        <table class="msgBox2" width="650" border="0" align="center" cellpadding="0" cellspacing="0">
	          <tr>
	            <td height="32"><span class="msgTitle">&nbsp;&nbsp;<s:text name="vehcileinfo.editbefore.title" /></span></td>
	          </tr>
	          <tr>
	            <td align="center">
	            <table width="100%" border="0" cellspacing="6" cellpadding="5">
	            	<tr>
						<td width="14%" height="25" align="right" valign="top">选择相片：</td>
	
						<td width="36%" height="25" align="left" valign="top" class="fsBlack">
							<s:file id="idFile" label="choicefile" theme="simple" name="file" size="20" onkeydown="return false;"
							/>
						</td>
						<td width="14%" height="25" align="right"></td>
						<td width="36%" height="25" align="left" class="fsBlack">
							<div id="imageDiv" style="display: none;">
								<img id="img" />
								<img id="delbutton" src="../images/delAccessory.gif" style="cursor: pointer;" title="删除" onclick="clearImagePreview();"></img>
							</div>
							<div id="driverPhotoDiv" style="display: none;">
								<img id="driverPhoto" height="100px" width="160px"/>
								<img id="delbutton" src="../images/delAccessory.gif" style="cursor: pointer;" title="删除" onclick="clearPhoto();"></img>
							</div>
						</td>
					</tr>
	               <tr>

	                <td width="14%" align="right">车牌类型：</td>
	                <td width="36%" align="left">
	                 <!-- test="#session.adminProfile.en_code == '0000600001' or #session.adminProfile.en_code == '0000600002' or #session.adminProfile.en_code == '0000600003' or #session.adminProfile.en_code == '0000600004' or #session.adminProfile.en_code == '0000105556'" -->
	                	<s:if test="#session.adminProfile.en_code == '0000850260'">
							<select name="vehcileInfo.vehicle_type" id="vehicle_type" style="width:120px" onchange="linkCode()">
	                    		<option value="0" <s:if test="''==vehcileInfo.vehicle_type"> selected="selected" </s:if>>
	                    			自有
								</option>
							</select>
	    				</s:if>
	    				<s:else>
	    				
					    	<select name="vehcileInfo.vehicle_type" id="vehicle_type" style="width:120px" onchange="linkCode()">
	                    		<option value="0" 
	                    			selected="selected" >

	                    			自有
								</option>
	                		</select>
						</s:else>
	                </td>
	                <span id="bcNumber" style="display:none;">
	                <td width="14%" height="" align="right">班车号：</td>
	                <td width="36%" height="" align="left" class="fsBlack">                
	                  <s:textfield id="vehicle_code" name="vehcileInfo.vehicle_code" maxlength="3"/>
	                  <%--<s:property id="vehicle_code" value="vehcileInfo.vehicle_code" />--%>	  
	                </td>
	                </span>
	              </tr>
	              
	              <tr>
	              	<td width="14%" height="25" align="right">
	                  <s:text name="vehcileinfo.vehicle_ln"/>：</td>
	                <td width="36%" height="25" align="left" class="fsBlack">                
	                  <s:textfield id="vehicle_ln" name="vehcileInfo.vehicle_ln" maxlength="7"/>
	                </td>
		            <td width="14%" height="25" align="right"><s:text name="车辆VIN" />：</td>
		            <td width="36%" align="left" class="fsBlack">
		              <s:property value="vehcileInfo.vehicle_vin"/>                 
		            </td>
	                
	                 
	              </tr>
	              
	              <tr>
	                <td width="14%" height="25" align="right">品牌：</td>
	                <td width="36%" align="left" class="fsBlack">
	                 <s:property value="vehcileInfo.vehbrand"/>                 
	                </td>
	                <td width="14%" align="right">车型：</td>
	                <td width="36%" align="left"><s:property value="vehcileInfo.vehtypename"/></td>
	                
	                
	               
	                
				</tr>
	               <tr>
	               <td width="14%" align="right">座位数：</td>
	                <td width="36%" align="left">
	                 <s:property id="limite_number" value="vehcileInfo.limite_number" />	               
	                </td>
	                
	                <td width="14%" align="right">驾驶员：</td>
	                 
	                <td width="36%" align="left">	               
	                <s:textfield id="driver_name" name="vehcileInfo.driver_name" maxlength="16" onclick="choosedriver()" readonly="true"/>
	                <s:hidden id="driver_id" name="vehcileInfo.driver_id"></s:hidden>
	                </td>
					
	                
	              </tr>
	              <tr>
	              	
	                <td width="14%" align="right"><s:text name="vehcileinfo.short_allname" />：</td>
	                <td width="36%" align="left" >
						<s:textfield id="organizationName" name="vehcileInfo.organizationName" readonly="true" onclick="openorganizidtree()"/>
	                	<s:hidden id="orgidtag" name="orgidtag"></s:hidden>
					</td>
					<td width="14%" align="right">车牌颜色：</td>
	                <td width="36%" align="left">
	                <select name="vehcileInfo.veh_pai_color" id="veh_pai_color" Style="width:120px">
	                    <option value="0" <s:if test="0==vehcileInfo.veh_pai_color">
								selected="selected"
							</s:if>><s:property value="getText('carbase.info.noSet')"/>
						</option>
	                    <option value="1" <s:if test="1==vehcileInfo.veh_pai_color">
								selected="selected"
							</s:if>><s:property value="getText('carbase.info.blue')"/></option>
						<option value="2" <s:if test="2==vehcileInfo.veh_pai_color">
								selected="selected"
							</s:if>><s:property value="getText('carbase.info.yellow')"/></option>
						<option value="3" <s:if test="3==vehcileInfo.veh_pai_color">
								selected="selected"
							</s:if>><s:property value="getText('carbase.info.black')"/></option>                
						<option value="4" <s:if test="4==vehcileInfo.veh_pai_color">
								selected="selected"
							</s:if>><s:property value="getText('carbase.info.white')"/></option>   
						<option value="9" <s:if test="9==vehcileInfo.veh_pai_color">
								selected="selected"
							</s:if>><s:property value="getText('carbase.info.other')"/>
						</option> 
	                </select>
	                
	                </td>
	              </tr>
				<tr id="ftlyCapacity" style="display:none;">
					<td align="right">油箱最大容量:</td>
	                <td align="left">
	                <input type="hidden" id="ftlyFlag" name="vehcileInfo.ftly_flag" value="<s:property value='vehcileInfo.ftly_flag'/>"/>
	                <s:textfield id="oilbox_capacity" name="vehcileInfo.oilbox_capacity" maxlength="7"/><strong id="units">&nbsp;&nbsp;L</strong>                
	                </td>
					<td width="14%" align="right"></td>
					<td width="36%" align="left" >
					</td>
	             </tr>
	            </table></td>
	          </tr>
	          <tr>
	            <td class="btnBar">
	             <s:url id="quxiao" action="vehiclemanage">
					<s:param name="page" value="page" />
					<s:param name="pageSize" value="pageSize" />	
				</s:url>
	            <a href="#" onclick="goBack('vehiclemanage.shtml')" class="buttontwo"><s:text name="button.cancle" /></a>
	            
	            <s:if test="NULL!=vehcileInfo.organization_id ">
	            <s:url id="cancle" action="vehicle_cancle">
					<s:param name="vehcileInfo.vehicle_id" value="vehcileInfo.vehicle_id" />
					<s:param name="organization_idold" value="organization_idold" />
					<s:param name="vehcileInfo.driver_id" value="vehcileInfo.driver_id" />
					<s:param name="vehcileInfo.vehicle_vin" value="vehcileInfo.vehicle_vin" />
					<s:param name="page" value="page" />
					<s:param name="pageSize" value="pageSize" />	
				</s:url>
				<a href="${cancle}" class="buttontwo">
				<s:text name="vehcileinfo.cancle" />
				</a>
				</s:if>
				
			
	            <a href="#" class="buttontwo" id="alterbutton"><s:text name="button.edit" /></a>
	           
	           
	            </td>
	          </tr>
	        </table>
	        </td>
	        </tr>
	    </table>
	    </div>
	</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="vehiclealter_validate.jsp"%>
<script type="text/javascript"
	src="<s:url value='/scripts/imgPreview/CJL.0.1.min.js' />"></script> <script
	type="text/javascript"
	src="<s:url value='/scripts/imgPreview/QuickUpload.js' />"></script> <script
	type="text/javascript"
	src="<s:url value='/scripts/imgPreview/ImagePreviewd.js' />"></script>
<script type="text/javascript">
function clickEnterEvent(obj) {
	    var orgid=document.getElementById('organization_id');
	    orgid.value=obj.id;
	}
/**
function choosedriver(){
	var url="<s:url value='/vehicle/vehicle_driver.shtml' />"+"?rad="+Math.random();
	var obj = window.showModalDialog(url,self,"dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
	//var info = obj.split(",");
	//document.getElementById("driver_name").value = info[1];
	//document.getElementById("driver_id").value = info[0];
}
**/
/**
function chooseuser(){
	var url="<s:url value='/vehicle/vehicle_user.shtml' />"+"?rad="+Math.random();
	var obj = window.showModalDialog(url,self,"dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
	//var info = obj.split(",");
	//document.getElementById("driver_name").value = info[1];
	//document.getElementById("driver_id").value = info[0];
}**/
function choosedriver(){
	 //var orgid=document.getElementById('organization_id');
	//alert(orgid);
	var dong=jQuery("#organization_id").val();
	art.dialog.open("<s:url value='/vehicle/vehicle_driver.shtml'/>?dong="+dong,{
	//art.dialog.open("<s:url value='/vehicle/vehicle_driver.shtml?dong="+dong+"'/>",{	
		title:"驾驶员信息",
		lock:true,
		width:700,
		height:410				
	});
	checkDriver();
}

/** 初始化样式 **/
function setFormMessage() {
	Mat.setDefault($('vehicle_code'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('vehicle_ln'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('driver_name'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('organizationName'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('oilbox_capacity'),'<span class="noticeMsg">*</span>');
}
/** 加载事件 **/
function setFormEvent() {
	$('vehicle_code').onkeyup = checkVehCodeUnique;
	$('vehicle_code').onblur = checkVehCodeUnique;
	$('vehicle_ln').onkeyup = checkVehiclelnUnique;
	$('vehicle_ln').onblur = checkVehiclelnUnique;
	$('driver_name').onkeyup = checkDriver;
	$('driver_name').onblur = checkDriver;
	$('organizationName').onkeyup = checkOrganizationName;
	$('organizationName').onblur = checkOrganizationName;
	$('oilbox_capacity').onkeyup = alterCapacity;
	$('oilbox_capacity').onblur = alterCapacity;
	
	//$('student_school').onkeyup = checkSchool;
	//$('student_school').onblur=checkSchool;oilbox_capacity

}
window.addEvent('domready', setFormMessage);
window.addEvent('domready', setFormEvent);
//window.addEvent('domready', checkDriver);
function chooseuser() {
	art.dialog.open("<s:url value='/vehicle/vehicle_user.shtml' />",{
		title:"车主信息",
		lock:true,
		width:700,
		height:410		
	});
}
function openorganizidtree(){
	art.dialog.open("<s:url value='/vehicle/vehiclemanagetreeAction.shtml' />?rad="+Math.random(),
		{width :260,
		height:300,
		id: 'treeOID',
		title: ' ',
		skin: 'aero',
		limit: true,
		lock: true,
		drag: true,
		fixed: false,
			//follow: document.getElementById('organizationName'),
			yesFn: function(iframeWin, topWin){
	        	//对话框iframeWin中对象
	        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
	        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
	        	//当前页面中对象
	            var topOrgName =  window.document.getElementById('organizationName');
	            var topOrgID = window.document.getElementById('organization_id');
	            //赋值
	        	if (topOrgName) topOrgName.value = orgNameValue;
	        	if (topOrgID) topOrgID.value = orgIDValue;
	        	checkOrganizationName();
	    	}
		});
}

var abc=null;
function linkCode(){//将下拉框和班车号联系在一起，外租车就将班车号设置为外租，自有则将班车号设置为空
	if(document.getElementById('vehicle_type').value>0){
		abc=document.getElementById('vehicle_code').value;
		document.getElementById('vehicle_code').value="外租";	
		document.getElementById('bcNumber').style.display = "none";	
	}
	else{
		if(abc!=null){
			document.getElementById('vehicle_code').value=abc;
		}
		else{
			document.getElementById('vehicle_code').value='';
		}	
		document.getElementById('bcNumber').style.display = "";		
	}
	isCapacity();
}


	function checkImage(filePath){
	  filePath = filePath.toLowerCase();
	  if((filePath.indexOf(".gif") > 0) || 
		 (filePath.indexOf(".jpg") > 0) || 
		 (filePath.indexOf(".bmp") > 0) || 
		 (filePath.indexOf(".jpeg") > 0) || 
		 (filePath.indexOf(".png") > 0)
		 //||(filePath.indexOf(".tif") > 0)
		 ) {
	  	return true;
	  } else {
		return false;
	  }
	}

	function clearImagePreview() {
		document.getElementById("idFile").outerHTML = document.getElementById("idFile").outerHTML;
		document.getElementById("idFile").value = '';
		loadFileContent();
		document.getElementById("imageDiv").style.display="none";
	}

	function clearPhoto() {
		if(confirm("确定要删除当前设置的相片么？")) {
			document.getElementById("driverPhotoDiv").style.display="none";
			document.getElementById("photoDelFlag").value = "del";
		}
	}

	function onloadFunc() {
		loadFileContent();
		
		// 判断是否有相片
		var photoName = document.getElementById("phot_name").value;
		if(photoName != "") {
			var driverPhoto =  document.getElementById("driverPhoto");
			driverPhoto.src="<s:url value='show_vehicle_pic.shtml'/>" + "?vehicle_id=" + document.getElementById("vehicle_id").value;
			document.getElementById("driverPhotoDiv").style.display="block";
		}
	}
	
	function loadFileContent() {
		var ip = new ImagePreview($$("idFile"), $$("img"), {
			maxWidth : 160,
			maxHeight : 100
		});
		
		ip.img.src = ImagePreview.TRANSPARENT;
		
		ip.file.onchange = function() {
			if(checkImage(document.getElementById("idFile").value)) {
				ip.preview();
				document.getElementById("imageDiv").style.display="block";
				document.getElementById("driverPhotoDiv").style.display="none";
			} else {
				clearImagePreview();
				alert("请选择图片格式文件上传!");
			}
		};
	}

	function isCapacity(){
		var flag = jQuery("#ftlyFlag").val();
		var selfType = jQuery("#vehicle_type").val();
		if(flag == "0" && selfType == "0"){
			jQuery("#ftlyCapacity").show();
			return true;
		} else {
			jQuery("#ftlyCapacity").hide();
			return false;
		}
	}
	
	jQuery(function() {
		if(document.getElementById('vehicle_type').value>0){	
			document.getElementById('bcNumber').style.display = "none";	
		}
		else{
			document.getElementById('bcNumber').style.display = "";		
		}
		 jQuery(window).mk_autoresize({
	         height_include: '#content',
	         height_exclude: ['#header', '#footer'],
	         height_offset: 0,
	         width_include: ['#header', '#content', '#footer'],
	         width_offset: 0
	      });
		jQuery('#content').mk_autoresize({
	        height_include: '#main',
	        height_offset: 0 // 该值各页面根据自己的页面布局调整
	      });
		isCapacity();
	});
</script>
</body>
</html>

