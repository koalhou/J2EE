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
	<s:hidden id="phot_name" name="vehcileInfo.phot_name" />
	<s:hidden id="photoDelFlag" name="vehcileInfo.photoDelFlag" />
	<s:hidden id="old_driver_license" name="oldDriverLicense" />
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
							<s:file id="idFile" label="choicefile" theme="simple"
							name="file" size="20" onkeydown="return false;"
							/>
							<span id="tishi" class="noticeMsg">请选择jpg,jpeg,bmp,png,gif格式的图片</span>
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
	                <td width="14%" height="25" align="right"><s:text name="vehcileinfo.vehicle_vin" />：</td>
	                <td width="36%" align="left" class="fsBlack">
	                 <s:property value="vehcileInfo.vehicle_vin"/>                 
	                </td>
	                <td width="14%" height="25" align="right">
	                  <s:text name="vehcileinfo.vehicle_ln"/>：</td>
	                <td width="36%" height="25" align="left" class="fsBlack">                
	                  <s:textfield id="vehicle_ln" name="vehcileInfo.vehicle_ln" maxlength="7"/><span class="noticeMsg">*</span>
	                </td>
	                
	              </tr>
	              
	              <tr>
	                <td width="14%" height="25" align="right">
	                  <s:text name="vehcileinfo.vehicle_code"/>：</td>
	                <td width="36%" height="25" align="left" class="fsBlack">                
	                  <s:textfield id="vehicle_code" name="vehcileInfo.vehicle_code" maxlength="20"/>
	                </td>
	                 <td width="14%" align="right">驾驶员：</td>
	                <td width="36%" align="left">
	                <s:if test="1!=#session.adminProfile.en_mould">
	                	<s:property value="vehcileInfo.driver_name"/>
	                </s:if>
	                <s:if test="1==#session.adminProfile.en_mould">
	                <s:textfield id="driver_name" name="vehcileInfo.driver_name" maxlength="16"/>
	                </s:if>
	                </td>
	              </tr>
	              
	             <s:if test="1==#session.adminProfile.en_mould">
	              <tr>
	                <td width="14%" height="25" align="right">
	                  	驾驶员电话：</td>
	                <td width="36%" height="25" align="left" class="fsBlack">                
	                  <s:textfield id="driver_tel" name="vehcileInfo.driver_tel" maxlength="13"/>
	                </td>
	                <td width="14%" align="right">驾驶证号：</td>
	                <td width="36%" align="left">
	                <s:textfield id="driver_license" name="vehcileInfo.driver_license" maxlength="32"/>
	                </td>
	              </tr>
	              </s:if>
	              
	              <tr>
	                <td width="14%" height="25" align="right">品牌：</td>
	                <td width="36%" align="left" class="fsBlack">
	                 <s:property value="vehcileInfo.vehbrand"/>                 
	                </td>
	                
	                <td width="14%" align="right"><s:text name="vehcileinfo.route" />：</td>
	                <td width="36%" align="left"><s:property value="vehcileInfo.route_name"/></td>
					</tr>
	               <tr>
	               <td width="14%" align="right">座位数：</td>
	                <td width="36%" align="left"><s:property value="vehcileInfo.limite_number"/></td>
					
	                <td width="14%" align="right">车型：</td>
	                <td width="36%" align="left"><s:property value="vehcileInfo.vehtypename"/></td>
	              </tr>
	               
	              <tr>
					<td width="14%" align="right">道路运输证：</td>
	                <td width="36%" align="left">
	                	<s:textfield id="veh_yunshuid" name="vehcileInfo.veh_yunshuid" maxlength="7"/>
	                </td>
	                <td width="14%" align="right">车辆颜色：</td>
	                <td width="36%" align="left"><s:property value="vehcileInfo.vehicle_color"/></td>
	              </tr>
	              <tr>
					<td width="14%" align="right">附加费：</td>
	                <td width="36%" align="left">
	                <s:textfield id="veh_fei" name="vehcileInfo.veh_fei" maxlength="7"/>
	                </td>
	                <td width="14%" align="right">出厂日期：</td>
	                <td width="36%" align="left"><s:property value="vehcileInfo.delivery_time"/></td>
	              </tr>              
	              <tr>
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
	                <td width="14%" align="right"><s:text name="vehcileinfo.short_allname" />：</td>
	                <td width="36%" align="left" >
						<s:textfield id="organizationName" name="vehcileInfo.organizationName" readonly="true" onclick="openorganizidtree()"/>
	                	<span class="noticeMsg">*</span><s:hidden id="orgidtag" name="orgidtag"></s:hidden>
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
					<s:param name="vehcileInfo.vehicle_vin" value="vehcileInfo.vehicle_vin" />
					<s:param name="page" value="page" />
					<s:param name="pageSize" value="pageSize" />	
				</s:url>
				<s:if test="#session.perUrlList.contains('111_3_3_4_3') && 3!=#session.adminProfile.userType ">
				<a href="${cancle}" class="buttontwo">
				<s:text name="vehcileinfo.cancle" />
				</a>
				</s:if>
				</s:if>
				
				<s:if test="#session.perUrlList.contains('111_3_3_4_4') && 3!=#session.adminProfile.userType ">
	            <a href="#" class="buttontwo" id="alterbutton"><s:text name="button.edit" /></a>
	            </s:if>
	           
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
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/PasswordConfirmDWR.js'></script>
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
	art.dialog.open("<s:url value='/vehicle/vehicle_driver.shtml' />",{
		title:"驾驶员信息",
		lock:true,
		width:700,
		height:410				
	});
}

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
	    	}
		});
}


function checkImage(filePath){
	  filePath = filePath.toLowerCase();
	  if((filePath.indexOf(".gif") > 0) || 
		 (filePath.indexOf(".jpg") > 0) || 
		 (filePath.indexOf(".bmp") > 0) || 
		 (filePath.indexOf(".jpeg") > 0) || 
		 (filePath.indexOf(".png") > 0)) {
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
				alert("请选择图片文件!");
			}
		};
	}

	jQuery(function() {
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
	});
	
	
	/** 驾驶员姓名 **/
	function checkDriverName() {
		var driver_name = $('driver_name');
		if (!Mat.checkRequired(driver_name))
			return false;
		if (!Mat.checkLength(driver_name, 16, '<s:text name="common.js.info.lenles"/>' + '16'))
			return false;
		return true;
	}
	
	/** 驾驶证号 **/
	function checkDriverLicense() {
		var obj = $('driver_license');
		if(Wit.checkErr(obj, /^[0-9-a-z-A-Z]+$/)) {
			Mat.showSucc(obj);
			return true;
		} else {
			Wit.showErr(obj,"驾驶证号为数字与字母组合");
			return false;
		}
	}
	
	/** 验证驾驶证号唯一性 **/
	function checkDriverLicenseUnique(){
	    var obj = $('driver_license');
		DWREngine.setAsync(false);
		var ret = false; 
		PasswordConfirmDWR.checkDriverLicense(obj.value, callBackFun);
		function callBackFun(data)
		{
		  ret = data;
		}
		DWREngine.setAsync(true);
		if(ret) {
		  Wit.showErr(obj, "驾驶证号已存在！");
		  return false;
		} else {
		  return true;
		}
	}
	
	/** 驾驶员联系 **/
	function checkDriverTel() {
		var obj = $('driver_tel');
		if (!Mat.checkTELandCell(obj, '电话格式不正确！')) {
			return false;
		} else {
			Mat.showSucc(obj);
			return true;
		}
	}
</script>
</body>
</html>

