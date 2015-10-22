<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style type="text/css">
		.searchPlan {
			float: left;
			width: 250px;
		}
	</style>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
	<s:form id="student_form" action="/infomanage/ridingadd" method="post">
<s:hidden id ="stu_id" name ="stu_id" ></s:hidden>
<s:hidden id ="update" name ="update" ></s:hidden>
<s:hidden id ="selectRow" name ="selectRow" ></s:hidden>
<s:hidden id ="vehicle_vin_old" name ="vehcileInfo.vehicle_vin_old" ></s:hidden>
<s:hidden id ="vehicle_vin" name ="vehcileInfo.vehicle_vin" ></s:hidden>
<s:hidden id ="selectIds" name ="selectIds" ></s:hidden>
<s:hidden id ="organization_id_s"></s:hidden>
<s:hidden id ="trip_id" name ="vehcileInfo.trip_id" ></s:hidden>
	<div id="main" style="height: 746px; overflow: auto;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
				<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="38" background="../images/contentTil_bg.gif"><span class="contentTil">乘车规划</span></td>
					</tr>
	 			</table>
	    	</td>
	  	</tr>
    	<tr>
			<td valign="top">
	            <table class="msgBox4" width="100%" border="0" cellspacing="0" cellpadding="0">
	               	<tr>
	               		<td height="32"><span class="msgTitle">&nbsp;&nbsp;
	               		<s:if test="%{update!='update'}">
	               			新建乘车规划
	               		</s:if>
	               		<s:if test="%{update=='update'}">
	               		 	修改乘车规划
	               		</s:if>
	               		</span></td>
	          	   	</tr>
	                <tr>
	                    <td height="90">
	                        <table width="100%" border="0" cellspacing="5" cellpadding="0">
	                           	<tr>						
								<td>
									班车号：&nbsp;&nbsp;&nbsp;
									<s:textfield id="vehicle_code" name="vehcileInfo.vehicle_code" onclick="choiceCar()" readonly="true"/>&nbsp;&nbsp;<font color='red'>*</font>
								</td>
								<td>
									车牌号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input id="vehicle_ln" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
									&nbsp;&nbsp;
								</td>
								<td>
									所属组织：
									<input id="org_name" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
								</td>
								</tr>
								<tr>
							   		<td height="5px" >
									</td>
								</tr>
								<tr>
									<td>
										核载人数：
										<input id="limite_number" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
									</td>
								    <td>
										规划乘车人数：
										<input id="plan_number" type="text" value="0" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>
									</td>
								</tr>
	                          		</table>
	                        	</td>
	                      	</tr>
	                      	<tr>
				            	<td height="32" class="msgBox5"><span class="msgTitle">&nbsp;&nbsp;人员明细&nbsp;&nbsp;&nbsp;<font color='red'>*</font></span>&nbsp;&nbsp;</td>
				          	</tr>
	                      	<tr>
	                        	<td height="10">
	                          		<table width="100%" border="0" cellspacing="5" cellpadding="0" >
	                            		<tr>						
											<td>姓名：<s:textfield id="stu_name"  cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStudent();}"/>&nbsp;&nbsp;
											</td>
											<td>员工号：<s:textfield id="stu_code"  cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStudent();}"/>&nbsp;&nbsp;
											</td>
											<td>组织：<s:textfield id="organization_name"  cssClass="input120" maxlength="32" onclick="openorganizidtree()" onkeypress="if(event.keyCode==13){searchStudent();}"/>
											</td>
											<td>备注：<s:textfield id="stu_remark"  cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStudent();}"/>
											</td>
											<td><a href="#" onclick="searchStudent()" class="sbutton">查询</a></td>	
										</tr>
									</table>
								</td>
							</tr>
							<tr>
							   	<td height="5px" ></td>
								</tr>
							<tr>
							   	<td height="5px" ></td>
							</tr>
							<tr>
								<td valign="top">
								   <table width="100%" border="0" cellspacing="0" cellpadding="0">
								     <tr>
								        <td class="reportOnline">
								        	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
												<tr>
		                        					<td class="titleStyle" align="center">
		                         						<table width="100%" border="0" cellspacing="0" cellpadding="0">
		                                					<tr>
		                                  			 			 <td class="titleStyleZi" width="10%">待选列表</td> 
		                                  			 			 <td class="titleStyleZi" width="51%"></td> 
		                                 				  		 <td class="titleStyleZi" width="49%">已选列表</td>
		                               		    			</tr>
		                                 			 		<tr>
		                      									<table width="100%" id="gala">
		                      										<tr>
					        											<td class="reportInline">
			                       											<select id="upLeftos" name="upLeftos" size="10" multiple="multiple" style="width:350px;height:300px; font-size: 14px; " 
			                       												                ondblclick="javascript:moveSelect(document.getElementById('upLeftos'), document.getElementById('upRightos'),'os');"></select>
					         											</td>
					         						 					<td class="reportInline" width="190px;" align= "center">			   						 
												       			 			 <a href="#" class="sbutton" onclick="javascript:moveSelect(document.getElementById('upLeftos'), document.getElementById('upRightos'),'os');">></a>
							                                                 <a href="#" class="sbutton" onclick="javascript:deleteSelect(document.getElementById('upLeftos'),document.getElementById('upRightos'),'os');"><</a>
				 				                                             <a href="#" class="sbutton" onclick="javascript:moveSelectAll(document.getElementById('upLeftos'), document.getElementById('upRightos'),'os');">>></a>
								                                             <a href="#" class="sbutton" onclick="javascript:deleteSelectAll(document.getElementById('upLeftos'), document.getElementById('upRightos'),'os');"><<</a>
					         											</td>
					         						       				<td class="reportInline">
			                       											<select id="upRightos" name="upRightos" size="10" multiple="multiple" style="width:350px;height:300px; font-size: 14px;" 
			                       												               ondblclick="javascript:deleteSelect(document.getElementById('upLeftos'),document.getElementById('upRightos'));"></select>
			                       											<select id="ostype" multiple="multiple"
																					size="10" style="display: none" class="s"></select>				 						 					   
					        						 					</td>
				        							           		</tr>
				         										</table>
				       										</tr>
		                           						</table>
		                        					</td>
		                     					</tr>                     
					                      </table>		
								      </td>
								     </tr>
								     <tr>
				           			 	<td class="btnBar">
		           			 				<a href="../infomanage/showRidingplan.shtml" class="button">取消</a>
		           			 				<s:if test="%{update=='update'}">
	                  							<a href="javascript:deleteRidingPlan('<s:property value="vehcileInfo.vehicle_vin"/>', '<s:property value="vehcileInfo.trip_id"/>')" class="button">删除</a>
	                  						</s:if>
	                  						<a href="#" onclick="submitForm()" class="button">确定</a> 
			                  			</td>
				           			 </tr>
								  </table>
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
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<%@include file="liststudent_validate.jsp"%>
<script type="text/javascript">
//页面初始化调用
jQuery(function() {	 
	var upRightos = document.getElementById("upRightos");
	if(jQuery('#update').val()=="update"){//如果是修改操作 则给车辆信息赋值  并且查询右侧列表  
		$('#vehicle_ln').val('<s:property value="vehcileInfo.vehicle_ln"/>');
		$('#org_name').val('<s:property value="vehcileInfo.short_allname"/>');
		$('#limite_number').val('<s:property value="vehcileInfo.limite_number"/>');
		$('#plan_number').val('<s:property value="vehcileInfo.plan_num"/>');
		$('#vehicle_code').attr('disabled',true);//如果是修改操作 则不能换车
		//右侧人员列表查询
		jQuery.ajax({
			  type: 'POST',  
			  url: "../ridingplanpkg/ridingSTShow2.shtml",	
			  data: {'info.vehicle_vin': jQuery('#vehicle_vin').val(),
				  	 'info.trip_id': jQuery('#trip_id').val()},
			  success: function(data) {
				  var lenObj = formatInfo(data);
		   		  for(var i=0;i<data.length;i++){
					var rightName = '';
					/*if(data[i].stu_name.length==2){
		   				rightName=data[i].stu_name.substring(0,1)+"&#12288;"+data[i].stu_name.substring(1,2);
			   		}else{
			   			rightName=data[i].stu_name;
			   		}*/
			   		rightName=data[i].stu_name;
			   		var rightCode=data[i].stu_code;
			   		if(rightName.length < lenObj.nameLen){
			   			var sub = lenObj.nameLen - rightName.length;
			   			for(var k =0 ; k< sub; k++){
			   				rightName += '&#12288;';
			   			}
		   		    }
	   				if(rightCode.length < lenObj.codeLen){
	   					var sub = lenObj.codeLen - rightCode.length;
			   			for(var j =0 ; j< sub; j++)
			   				rightCode += '&#12288;';
	   				}
		   			jQuery("#upRightos").append("<option value='"+data[i].stu_id+"'>"+
		   					rightName+'&#12288;&#12288;'+rightCode+"</option>");
				}
			}  
		});
	}
	var dellist1 ="";
	for(var j=0;j<upRightos.length;j++) {
		  if(dellist1=="")
			dellist1 = "'"+upRightos.options[j].value+"'";
		  else
			dellist1 = dellist1+",'"+upRightos.options[j].value+"'";
	}
	//左侧人员列表查询
	jQuery.ajax({
		  type: 'POST',  
		  url: "../ridingplanpkg/ridingSTList2.shtml?info.flag=0",	
		  data: {stu_name: jQuery('#stu_name').val(),
		         stu_code:jQuery('#stu_code').val(),
		         stu_org:jQuery('#stu_org').val(),
		         stu_remark:jQuery('#stu_remark').val(),
		         existdata : dellist1
			     },
		  success: function(data) {
			var lenObj = formatInfo(data);
			jQuery("#upLeftos").empty();	
	   		for(var i=0;i<data.length;i++){
	   			var leftName='';
	   			/*if(data[i].stu_name.length==2){
		   			leftName=data[i].stu_name.substring(0,1)+"&#12288;"+data[i].stu_name.substring(1,2);
		   		}else{
		   			leftName=data[i].stu_name;
		   		}*/
		   		leftName=data[i].stu_name;
		   		var leftCode=data[i].stu_code;
		   		if(leftName.length < lenObj.nameLen){
		   			var sub = lenObj.nameLen - leftName.length;
		   			for(var k =0 ; k< sub; k++){
		   				leftName += '&#12288;';
		   			}
	   		    }
   				if(leftCode.length < lenObj.codeLen){
   					var sub = lenObj.codeLen - leftCode.length;
		   			for(var j =0 ; j< sub; j++)
		   				leftCode += '&#12288;';
   				}
	   			jQuery("#upLeftos").append("<option value='"+data[i].stu_id+"'>"+
	   					leftName+'&#12288;&#12288;'+leftCode+"</option>");
			}
		  }  
	});
});

//2013-10-16添加车辆查询
function choiceCar(){	
	art.dialog.open("<s:url value='../infomanage/chooseTCar.shtml' />",{
		title:"车辆信息",
		lock:true,
		width:700,
		height:500
	});
}

//2013-10-16添加组织树
function openorganizidtree(){
	art.dialog.open("<s:url value='/usm/usermanagetreeAction.shtml' />",{
			width :260,
			height:300,
			    id: 'treeID',
				title: ' ',
				follow: document.getElementById('organization_name'),
				yesFn: function(iframeWin, topWin){
		        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
		        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
		        	//当前页面中对象
		            var topOrgName =  window.document.getElementById('organization_name');
		            var topOrgID = window.document.getElementById('organization_id_s');
		            //赋值
		        	if (topOrgName) 
		        		topOrgName.value = orgNameValue;
		        	if (topOrgID) 
		        		topOrgID.value = orgIDValue;
		    	}
			});
}

//点击查询按钮员工信息查询
function searchStudent() {
	var upRightos = document.getElementById("upRightos");
	var dellist1 ="";
	for(var j=0;j<upRightos.length;j++) {
		  if(dellist1=="")
			dellist1 = "'"+upRightos.options[j].value+"'";
		  else
			dellist1 = dellist1+",'"+upRightos.options[j].value+"'";
	}
	jQuery.ajax({
			  type: 'POST',  
			  url: "../ridingplanpkg/ridingSTList2.shtml?info.flag=0",	
			  data: {stu_name: formatSpecialChar(jQuery('#stu_name').val()),
			         stu_code:formatSpecialChar(jQuery('#stu_code').val()),
			         stu_org:jQuery('#organization_id_s').val(),
			         stu_remark:formatSpecialChar(jQuery('#stu_remark').val()),
			         existdata : dellist1
				     },
			  success: function(data) {
				   var lenObj = formatInfo(data);
				   jQuery("#upLeftos").empty();	
		   		   for(var i=0;i<data.length;i++){
				    var leftName = '';		
				    if(data[i].stu_name.length==2){
			   			leftName=data[i].stu_name.substring(0,1)+"&nbsp;&nbsp;&nbsp;"+data[i].stu_name.substring(1,2);
			   		}else{
			   			leftName=data[i].stu_name;
			   		}
			   		var leftCode=data[i].stu_code;
			   		if(leftName.length < lenObj.nameLen){
			   			var sub = lenObj.nameLen - leftName.length;
			   			for(var k =0 ; k< sub; k++){
			   				leftName += '&#12288;';
			   			}
		   		    }
	   				if(leftCode.length < lenObj.codeLen){
	   					var sub = lenObj.codeLen - leftCode.length;
			   			for(var j =0 ; j< sub; j++)
			   				leftCode += '&#12288;';
	   				}
		   			jQuery("#upLeftos").append("<option value='"+data[i].stu_id+"'>"+
		   					leftName+'&#12288;&#12288;'+leftCode+"</option>");
				  }
			  }  
		});
}

function submitForm(){
	if($('#vehicle_code').val() == ''){
		alert("请选择班车！");
		return;
 	}
	var upRightos = document.getElementById("upRightos");//右侧列表
	var idlist="";//已选人员列表
	for(var i=0;i<upRightos.length;i++) {
		  if(idlist=="")
			  idlist = upRightos.options[i].value;
		  else
			  idlist = idlist+","+upRightos.options[i].value;
	}
	if(idlist == ''){
		alert("请选择人员！");
		return;
 	}
	if(upRightos.length > $('#limite_number').val()){
		alert("所选人员数超出核载人数！");
		return;
	}
	$('#selectIds').val(idlist);
	jQuery.ajax({
		  type: 'POST',  
		  url: '../infomanage/checkCarAndStu.shtml',	
		  data: {vin: $('#vehicle_vin').val(),
			     stuIds : idlist,
			     type : $('#update').val()},	
	 	  success: function(data){
	 		if(data == 'error'){
	 			alert("所选车辆或员工已经绑定！");
	 			return false;
	 		}else
	 			var form=document.getElementById('student_form');
		 		if($('#update').val()=="update")//如果是修改操作 则改变form提交路径
		 			form.action="ridingupdate.shtml";
		 		form.submit();
	 	  }
  });
 }
  
//每行删除方法
function deleteRidingPlan(vin,trip_id){
	if (confirm("您确定要删除吗？")) {
		window.location="../infomanage/deleteRidingPlan.shtml?ridingReady.vehicle_vin="+vin+"&ridingReady.trip_id="+trip_id;				
	}			
}

function formatInfo(data){
	var nameLen = 0;
	var codeLen = 0;
	for(var i=0;i<data.length;i++){
		if(data[i].stu_name.length > nameLen)
			nameLen = data[i].stu_name.length;
		if(data[i].stu_code.length > codeLen)
			codeLen = data[i].stu_code.length;
	}
	var ret ={};
	ret.nameLen =nameLen;
	ret.codeLen =codeLen;
	return ret;
}

function formatSpecialChar(str) {
	return str.replace(/\\/g,"\\\\")
	          .replace(/%/g,"\\%")
	          .replace(/_/g,"\\_")
	          .replace(/％/g,"\\％")
	          .replace(/＿/g,"\\＿")
	          .replace(/^\s+|\s+$/g, '');
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
</body>
</html>

