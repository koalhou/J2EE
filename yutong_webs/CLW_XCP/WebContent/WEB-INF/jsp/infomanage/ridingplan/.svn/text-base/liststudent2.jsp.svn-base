<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body style="width:700px; min-width:700px;">
<s:form id="student_form" action="/userpkg/lnDivList" method="post">
<s:hidden id ="stu_id" name ="stu_id" ></s:hidden>
<s:hidden id ="update" name ="update" ></s:hidden>
<s:hidden id ="studentListFlag" name ="studentListFlag" ></s:hidden>
<s:hidden id ="selectRow" name ="selectRow" ></s:hidden>
<s:hidden id ="vehicle_vin_old" name ="vehicle_vin_old" ></s:hidden>
<s:hidden id ="siteid" name ="siteid" ></s:hidden>
<s:hidden id ="route_id" name ="route_id" ></s:hidden>
<s:hidden id ="trip_id" name ="trip_id" ></s:hidden>
<s:hidden id ="upstudentids" name ="upstudentids" ></s:hidden>
<s:hidden id ="downstudentids" name ="downstudentids" ></s:hidden>
<s:hidden id ="sitename" name ="sitename" ></s:hidden>
<s:hidden id ="upnotexist" name ="upnotexist" ></s:hidden>
<s:hidden id ="downnotexist" name ="downnotexist" ></s:hidden>
<s:hidden id ="delupexistdata" name ="delupexistdata" ></s:hidden>
<s:hidden id ="deldownexistdata" name ="deldownexistdata" ></s:hidden>
<table  width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td  valign="top">
		<table width="100%" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td class="reportOnline2">
				<table width="100%" border="0" cellspacing="8" cellpadding="0">
					<tr>
						<td>
						<table border="0" cellspacing="4" cellpadding="0">
							<tr>						
								<td>
								学号：
								<s:textfield id="stu_code" name="info.stu_code" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStudent();}"/>&nbsp;&nbsp;
								</td>
								<td>
								姓名：
								<s:textfield id="stu_name" name="info.stu_name" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStudent();}"/>&nbsp;&nbsp;
								</td>
								<td>
								站点描述：
								<s:textfield id="site_desc" name="info.site_desc" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStudent();}"/>
								</td>
												
							</tr>
							<tr>
								<td>
								学校：
								<s:textfield id="stu_school" name="info.stu_school" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStudent();}"/>&nbsp;&nbsp;
								</td>
							    <td>
								班级：
								<s:textfield id="stu_class" name="info.stu_class" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStudent();}"/>
								</td>
								<td>
									<table>
										<tr>
											<td><a href="#" onclick="searchStudent()" class="sbutton">查询</a></td>								
											<td><a href="#" onclick="operate()" class="sbutton">确认</a></td>
											<td><a href="#" onclick="quxiao()" class="sbutton">取消</a></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>		
				</td>
			</tr>
			<tr>
			  <td valign="top">
			   <table width="100%" border="0" cellspacing="0" cellpadding="0">
			     <tr>
			        <td class="reportOnline">
			        <table width="100%" border="0" cellspacing="0" cellpadding="0">		
                     <tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
							<tr>
								<td>
									<%-- 列表标题 --%>
									<table width="100%" border="0" cellspacing="0" cellpadding="0" class="titleStyle">
										<div  class="reportTab">
										   <ul>
										     <li><a id="tab1" class="current2" style="cursor:pointer"  onclick="changeChoose(this.id)">上车学生</a></li>
										     <li><a id="tab2" style="cursor:pointer" onclick="changeChoose(this.id)">下车学生</a></li>  
										   </ul>
										</div>
									</table>
								</td>
							</tr>
							<tr>
								<td  class="reportInline" >
								    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
										<tr>
                        					<td class="titleStyle" align="center">
                         						<table width="100%" border="0" cellspacing="0" cellpadding="0">
                                					<tr>
                                  			 			 <td  class="titleStyleZi" width="25%">待选列表</td> 
                                  			  			 <td class="tbQuerySel" width="30%">										
	 									 				 </td>
                                 				  		  <td  class="titleStyleZi" id="chooseList" align="center"></td>                                                                     
                               		    			</tr>
                                 			 			<tr>
                      									<table width="100%" id="gala">
                      										<tr>
		        											<td class="reportInline">
                       												<select id="upLeftos" name="upLeftos" size="10" multiple="multiple" style="width:280px;height:300px; font-size: 14px; " 
                       												                ondblclick="javascript:moveSelect(document.getElementById('upLeftos'), document.getElementById('upRightos'),'os');"></select>
		         											</td>
		         						 					<td class="reportInline" width="50px;">			   						 
									       			 			  <a href="#" class="sbutton" onclick="javascript:moveSelect(document.getElementById('upLeftos'), document.getElementById('upRightos'),'os');">></a>
				                                                  <a href="#" class="sbutton" onclick="javascript:deleteSelect(document.getElementById('upLeftos'),document.getElementById('upRightos'),'os');"><</a>
	 				                                              <a href="#" class="sbutton" onclick="javascript:moveSelectAll(document.getElementById('upLeftos'), document.getElementById('upRightos'),'os');">>></a>
					                                              <a href="#" class="sbutton" onclick="javascript:deleteSelectAll(document.getElementById('upLeftos'), document.getElementById('upRightos'),'os');"><<</a>
		         											</td>
		         						       				 <td class="reportInline">
                       												<select id="upRightos" name="upRightos" size="10" multiple="multiple" style="width:280px;height:300px; font-size: 14px;" 
                       												               ondblclick="javascript:deleteSelect(document.getElementById('upLeftos'),document.getElementById('upRightos'));"></select>
                       												<select id="ostype" multiple="multiple"
																		size="10" style="display: none" class="s"></select>				 						 					   
		        						 					</td>
		        							           </tr>
		         									</table>
		         									<table width="100%" id="gala2">
                      										<tr>
		        											<td class="reportInline">
                       												<select id="downLeftos" name="downLeftos" size="10" multiple="multiple" style="width:280px;height:300px; font-size: 14px;" 
                       												               ondblclick="javascript:moveSelect(document.getElementById('downLeftos'), document.getElementById('downRightos'));"></select>
		         											</td>
		         						 					<td class="reportInline" width="50px;">
		         						   						  <a href="#" class="sbutton" onclick="javascript:moveSelect(document.getElementById('downLeftos'), document.getElementById('downRightos'),'os');">></a>
				                                                  <a href="#" class="sbutton" onclick="javascript:deleteSelect(document.getElementById('downLeftos'),document.getElementById('downRightos'),'os');"><</a>
	 				                                              <a href="#" class="sbutton" onclick="javascript:moveSelectAll(document.getElementById('downLeftos'), document.getElementById('downRightos'),'os');">>></a>
					                                              <a href="#" class="sbutton" onclick="javascript:deleteSelectAll(document.getElementById('downLeftos'), document.getElementById('downRightos'),'os');"><<</a>
		         											</td>
		         						       				 <td class="reportInline">
                       												<select id="downRightos" name="downRightos" size="10" multiple="multiple" style="width:280px;height:300px; font-size: 14px;" 
                       												              ondblclick="javascript:deleteSelect(document.getElementById('downLeftos'),document.getElementById('downRightos'));"></select>				 						 					   
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
						</table>
					</td>
				  </tr>
			      </table>
			      </td>
			     </tr>
			   </table>
			  </td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</s:form>
<%@include file="liststudent_validate.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">
var cid = "tab1";
//查询
function searchStudent() {
	var url= "../ridingplanpkg/ridingSTList2.shtml?1=1";
	if(jQuery('#update').val()=="update"){
		 url+="&info.site_id="+jQuery('#siteid').val()+"&info.vehicle_vin="+jQuery('#vehicle_vin_old').val()+"&info.trip_id="+jQuery('#trip_id').val()+"&info.route_id="+jQuery('#route_id').val();
    }
	if(cid =="tab1"){
		/*
		取得上行查询数据中可以被显示的删除学生 START
		*/
		var upRightos = document.getElementById("upRightos");
		//上行待删除列表
		var dellist1 ="";
		for(var j=0;j<upRightos.length;j++) {
			  if(dellist1==""){
				dellist1 = upRightos.options[j].value;
			  }else{
				dellist1 = dellist1+","+upRightos.options[j].value;
			  }
		}
		var arr1=document.getElementById("delupexistdata").value.split(",");
		var arr2=dellist1.split(",");
		var delsearchupexistdata = arr_dive(arr1,arr2).join(",");
		var arr3=jQuery('#upnotexist').val().split(",");
		var searchparamdata = arr_dive_none(arr2,arr3).join(",");
		/*
		取得上行查询数据中可以被显示的删除学生 END
		*/
		jQuery.ajax({
			  type: 'POST',  
			  url: url+"&info.flag=0",	
			  data: {stu_name: formatSpecialChar(jQuery('#stu_name').val()),
			         stu_code: formatSpecialChar(jQuery('#stu_code').val()),
			         stu_school: formatSpecialChar(jQuery('#stu_school').val()),
			         stu_class: formatSpecialChar(jQuery('#stu_class').val()),
			         site_desc: formatSpecialChar(jQuery('#site_desc').val()),
			         upnotexist:jQuery('#upnotexist').val(),
			         /*
			         *解决点击查询时，已选数据在待选列表没有被过滤的问题
			         *增加参数 searchparam
			         */
			         searchparam:searchparamdata ,
			         delupexistdata : delsearchupexistdata ,
			         route_id:jQuery('#route_id').val()
				     },
			  success: function(data) {
				jQuery("#upLeftos").empty();		
		   		   for(var i=0;i<data.length;i++){
		   			if(data[i].stu_name.length==2){
		   				var name=encodeHtml(data[i].stu_name.substring(0,1))+"&nbsp;&nbsp;&nbsp;"+encodeHtml(data[i].stu_name.substring(1,2));
		   				jQuery("#upLeftos").append("<option value='"+data[i].stu_id+"'>"+
		   						name+"&nbsp;&nbsp;&nbsp;&nbsp;"+
	                            data[i].stu_code+"&nbsp;&nbsp;&nbsp;&nbsp;"+
	                           (data[i].stu_class==null?"无":encodeHtml(data[i].stu_class))+"&nbsp;&nbsp;&nbsp;&nbsp;"+
	                           (data[i].stu_school==null?"无":encodeHtml(data[i].stu_school))+"</option>");
		   					
		   			}else{
		   				jQuery("#upLeftos").append("<option value='"+data[i].stu_id+"'>"+
		   						encodeHtml(data[i].stu_name)+"&nbsp;&nbsp;&nbsp;&nbsp;"+
	                            data[i].stu_code+"&nbsp;&nbsp;&nbsp;&nbsp;"+
	                           (data[i].stu_class==null?"无":encodeHtml(data[i].stu_class))+"&nbsp;&nbsp;&nbsp;&nbsp;"+
	                           (data[i].stu_school==null?"无":encodeHtml(data[i].stu_school))+"</option>");
		   			}  
				  }
			  }  
		});
	}

	if(cid =="tab2"){
		var downRightos = document.getElementById("downRightos");
   		//下行待删除列表
   		var dellist2 ="";
   		for(var j=0;j<downRightos.length;j++) {
   			  if(dellist2==""){
   				dellist2 = downRightos.options[j].value;
   			  }else{
   				dellist2 = dellist2+","+downRightos.options[j].value;
   			  }
   		}
   		var arr1=document.getElementById("deldownexistdata").value.split(",");
		var arr2=dellist2.split(",");
		var delsearchdownexistdata = arr_dive(arr1,arr2).join(",");
		var arr3=jQuery('#downnotexist').val().split(",");
		var searchparamdata = arr_dive_none(arr2,arr3).join(",");
		jQuery.ajax({
			  type: 'POST',  
			  url: url+"&info.flag=1",	
			  data: {stu_name: formatSpecialChar(jQuery('#stu_name').val()),
			         stu_code: formatSpecialChar(jQuery('#stu_code').val()),
			         stu_school: formatSpecialChar(jQuery('#stu_school').val()),
			         stu_class: formatSpecialChar(jQuery('#stu_class').val()),
			         site_desc: formatSpecialChar(jQuery('#site_desc').val()),
			         downnotexist:jQuery('#downnotexist').val(),
			         /*
				      *解决点击查询时，已选数据在待选列表没有被过滤的问题
				      *增加参数 searchparam
				     */
				     searchparam:searchparamdata ,
			         deldownexistdata:delsearchdownexistdata,
			         route_id:jQuery('#route_id').val()
				     },
			  success: function(data) {
		   		jQuery("#downLeftos").empty();		
		   			for(var i=0;i<data.length;i++){
			   			if(data[i].stu_name.length==2){
			   				var name=encodeHtml(data[i].stu_name.substring(0,1))+"&nbsp;&nbsp;&nbsp;"+encodeHtml(data[i].stu_name.substring(1,2));
			   				jQuery("#downLeftos").append("<option value='"+data[i].stu_id+"'>"+
		                            name+"&nbsp;&nbsp;&nbsp;&nbsp;"+
		                            data[i].stu_code+"&nbsp;&nbsp;&nbsp;&nbsp;"+
		                           (data[i].stu_class==null?"无":encodeHtml(data[i].stu_class))+"&nbsp;&nbsp;&nbsp;&nbsp;"+
		                           (data[i].stu_school==null?"无":encodeHtml(data[i].stu_school))+"</option>");
				   			}else{
			   				jQuery("#downLeftos").append("<option value='"+data[i].stu_id+"'>"+
			   						encodeHtml(data[i].stu_name)+"&nbsp;&nbsp;&nbsp;&nbsp;"+
		                            data[i].stu_code+"&nbsp;&nbsp;&nbsp;&nbsp;"+
		                           (data[i].stu_class==null?"无":encodeHtml(data[i].stu_class))+"&nbsp;&nbsp;&nbsp;&nbsp;"+
		                           (data[i].stu_school==null?"无":encodeHtml(data[i].stu_school))+"</option>");
			   			}
			   		
					}
			  }  
		});
	}
}
/*
* 点击页签显示不同列表
*/
function changeChoose(id){
	var sitename=document.getElementById("sitename").value;
	if(id=="tab1"){
		cid = "tab1";
		document.getElementById("tab1").className="current2";
		document.getElementById("tab2").className="";
		document.getElementById("chooseList").innerHTML=sitename+"上车学生已选列表";
		jQuery('#gala2').hide();
		jQuery('#gala').show();
	}
	if(id=="tab2"){	
		cid = "tab2";	 
		document.getElementById("tab2").className="current2";
		document.getElementById("tab1").className="";	
		document.getElementById("chooseList").innerHTML=sitename+"下车学生已选列表";
		jQuery('#gala').hide();
		jQuery('#gala2').show();	
	}	
}
jQuery(function() {	 
	if(jQuery('#studentListFlag').val()=="0"){
		if(jQuery('#selectRow').val()=="last"){
			changeChoose("tab2");
		}else{
			changeChoose("tab1");
		}
	}else{
		if(jQuery('#selectRow').val()=="first"){
			changeChoose("tab1");
		}else{
			changeChoose("tab2");
		}
		
	}

	var url= "../ridingplanpkg/ridingSTList2.shtml";
	var tab1url=url+"?info.flag=0&info.trip_id="+jQuery('#trip_id').val();
	jQuery.ajax({
		  type: 'POST',  
		  url: tab1url,	
		  data: {stu_name: formatSpecialChar(jQuery('#stu_name').val()),
			     stu_code: formatSpecialChar(jQuery('#stu_code').val()),
			     stu_school: formatSpecialChar(jQuery('#stu_school').val()),
			     stu_class: formatSpecialChar(jQuery('#stu_class').val()),
			     site_desc: formatSpecialChar(jQuery('#site_desc').val()),
			     upnotexist:jQuery('#upnotexist').val(),
			     delupexistdata:jQuery('#delupexistdata').val(),
			     route_id:jQuery('#route_id').val()
			     },
		  success: function(data) {
			jQuery("#upLeftos").empty();	
	   		for(var i=0;i<data.length;i++){
		   		if(data[i].stu_name.length==2){
			   		var name=encodeHtml(data[i].stu_name.substring(0,1))+"&nbsp;&nbsp;&nbsp;"+encodeHtml(data[i].stu_name.substring(1,2));
		   			jQuery("#upLeftos").append("<option value='"+data[i].stu_id+"'>"+
                             name+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                             data[i].stu_code+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                             (data[i].stu_class==null?"无":encodeHtml(data[i].stu_class))+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                             (data[i].stu_school==null?"无":encodeHtml(data[i].stu_school))+"</option>");
		   		}else{
		   			jQuery("#upLeftos").append("<option value='"+data[i].stu_id+"'>"+
		   					encodeHtml(data[i].stu_name)+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                          data[i].stu_code+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                          (data[i].stu_class==null?"无":encodeHtml(data[i].stu_class))+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                          (data[i].stu_school==null?"无":encodeHtml(data[i].stu_school))+"</option>");
		   		}
		   		
			}
		  }  
	});

	var tab2url=url+"?info.flag=1&info.trip_id="+jQuery('#trip_id').val();
	jQuery.ajax({
		  type: 'POST',  
		  url: tab2url,	
		  data: {stu_name: formatSpecialChar(jQuery('#stu_name').val()),
			     stu_code: formatSpecialChar(jQuery('#stu_code').val()),
			     stu_school: formatSpecialChar(jQuery('#stu_school').val()),
			     stu_class: formatSpecialChar(jQuery('#stu_class').val()),
			     site_desc: formatSpecialChar(jQuery('#site_desc').val()),
			     downnotexist:jQuery('#downnotexist').val(),
			     deldownexistdata:jQuery('#deldownexistdata').val(),
			     route_id:jQuery('#route_id').val()
			     },
		  success: function(data) {
			jQuery("#downLeftos").empty();	
	   		for(var i=0;i<data.length;i++){
		   		if(data[i].stu_name.length==2){
		   			var name=encodeHtml(data[i].stu_name.substring(0,1))+"&nbsp;&nbsp;&nbsp;"+encodeHtml(data[i].stu_name.substring(1,2));
		   			jQuery("#downLeftos").append("<option value='"+data[i].stu_id+"'>"+
                             name+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                             data[i].stu_code+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                             (data[i].stu_class==null?"无":encodeHtml(data[i].stu_class))+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                             (data[i].stu_school==null?"无":encodeHtml(data[i].stu_school))+"</option>");
		   		}else{
		   			jQuery("#downLeftos").append("<option value='"+data[i].stu_id+"'>"+
		   					encodeHtml(data[i].stu_name)+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                          data[i].stu_code+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                          (data[i].stu_class==null?"无":encodeHtml(data[i].stu_class))+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                          (data[i].stu_school==null?"无":encodeHtml(data[i].stu_school))+"</option>");
		   		}
		   		
			}
		  }  
	});
	
	jQuery.ajax({
		  type: 'POST',  
		  url: "../ridingplanpkg/ridingSTShow2.shtml",	
		  data: {stu_id: jQuery('#upstudentids').val()},
		  success: function(data) {
	   		for(var i=0;i<data.length;i++){
	   			if(data[i].stu_name.length==2){
	   				var name=encodeHtml(data[i].stu_name.substring(0,1))+"&nbsp;&nbsp;&nbsp;"+encodeHtml(data[i].stu_name.substring(1,2));
	   				jQuery("#upRightos").append("<option value='"+data[i].stu_id+"'>"+
                               name+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                               data[i].stu_code+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                               (data[i].stu_class==null?"无":encodeHtml(data[i].stu_class))+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                               (data[i].stu_school==null?"无":encodeHtml(data[i].stu_school))+"</option>");
	   			}else{
	   				jQuery("#upRightos").append("<option value='"+data[i].stu_id+"'>"+
	   						encodeHtml(data[i].stu_name)+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                               data[i].stu_code+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                               (data[i].stu_class==null?"无":encodeHtml(data[i].stu_class))+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                               (data[i].stu_school==null?"无":encodeHtml(data[i].stu_school))+"</option>");
	   			}	
			}

	   		var upRightos = document.getElementById("upRightos");
	   		//上行待删除列表
	   		var dellist1 ="";
	   		for(var j=0;j<upRightos.length;j++) {
	   			  if(dellist1==""){
	   				dellist1 = upRightos.options[j].value;
	   			  }else{
	   				dellist1 = dellist1+","+upRightos.options[j].value;
	   			  }
	   		}
			var delupexistdata="";
	   		//如果上行不存在删除的已选学生数据，直接把删除的已选学生设为已选列表中的数据
	   		if(document.getElementById("delupexistdata").value==""){
	   			delupexistdata = dellist1;
	   		}else{
	   			//如果上行存在删除的已选学生数据，且已选列表不为空
	   			if(dellist1!=""){
	   				var upstr=document.getElementById("delupexistdata").value;
	   				delupexistdata=upstr+","+dellist1;
	   				//总删除的已选学生数据去重后结果
	   				var undelupexistdata=unique(delupexistdata.split(","));
	   				var newstr="";

	   				if(typeof(undelupexistdata)!="undefined"){
	   					newstr=undelupexistdata.join(",");
	   				}
	   				//得出上行总删除的已选学生数据
	   				delupexistdata=newstr;
	   			}else{
	   				//如果上行存在删除的已选学生数据，且已选列表为空
	   				var undelupexistdata=unique(document.getElementById("delupexistdata").value.split(","));
	   				var newstr="";
	   				if(typeof(undelupexistdata)!="undefined"){
	   					newstr=undelupexistdata.join(",");
	   				}
	   				delupexistdata=newstr;
	   			}
	   		}

	   		document.getElementById("delupexistdata").value=delupexistdata;
		}  
	});
	jQuery.ajax({
		  type: 'POST',  
		  url: "../ridingplanpkg/ridingSTShow2.shtml",	
		  data: {stu_id: jQuery('#downstudentids').val()},
		  success: function(data) {
	   		for(var i=0;i<data.length;i++){
	   			if(data[i].stu_name.length==2){
	   				var name=encodeHtml(data[i].stu_name.substring(0,1))+"&nbsp;&nbsp;&nbsp;"+encodeHtml(data[i].stu_name.substring(1,2));
	   				jQuery("#downRightos").append("<option value='"+data[i].stu_id+"'>"+
                            name+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                            data[i].stu_code+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                            (data[i].stu_class==null?"无":encodeHtml(data[i].stu_class))+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                            (data[i].stu_school==null?"无":encodeHtml(data[i].stu_school))+"</option>");  	
			   	}else{
			   		jQuery("#downRightos").append("<option value='"+data[i].stu_id+"'>"+
			   				encodeHtml(data[i].stu_name)+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                               data[i].stu_code+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                               (data[i].stu_class==null?"无":encodeHtml(data[i].stu_class))+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                               (data[i].stu_school==null?"无":encodeHtml(data[i].stu_school))+"</option>");  	
			   	}	
			}

	   		var downRightos = document.getElementById("downRightos");

	   		//下行待删除列表
	   		var dellist2 ="";

	   		for(var j=0;j<downRightos.length;j++) {
	   			  if(dellist2==""){
	   				dellist2 = downRightos.options[j].value;
	   			  }else{
	   				dellist2 = dellist2+","+downRightos.options[j].value;
	   			  }
	   		}
	   		var deldownexistdata="";
	   		//如果下行不存在删除的已选学生数据，直接把删除的已选学生设为已选列表中的数据
	   		if(document.getElementById("deldownexistdata").value==""){
	   			deldownexistdata = dellist2;
	   		}else{
	   			//如果下行存在删除的已选学生数据，且已选列表不为空
	   			if(dellist2!=""){
	   				var downstr=document.getElementById("deldownexistdata").value;
	   				deldownexistdata=downstr+","+dellist2;
	   				//总删除的已选学生数据去重后结果
	   				var undeldownexistdata=unique(deldownexistdata.split(","));
	   				var newstr="";

	   				if(typeof(undeldownexistdata)!="undefined"){
	   					newstr=undeldownexistdata.join(",");
	   				}
	   				//得出下行总删除的已选学生数据
	   				deldownexistdata=newstr;
	   			}else{
	   				//如果下行存在删除的已选学生数据，且已选列表为空
	   				var undeldownexistdata=unique(document.getElementById("deldownexistdata").value.split(","));
	   				var newstr="";
	   				if(typeof(undeldownexistdata)!="undefined"){
	   					newstr=undeldownexistdata.join(",");
	   				}
	   				deldownexistdata=newstr;
	   			}
	   		}
	   		document.getElementById("deldownexistdata").value=deldownexistdata;
		}
	});
});

   /*
   * 第一个数组减去第二个数组
   * 
   */
   function arr_dive(aArr,bArr){   
      if(bArr.length==0){return aArr;}
	  for(var j=0;j<bArr.length;j++){
			for(var q=0;q<aArr.length;q++){
				if(aArr[q]==bArr[j]){
					aArr.splice(q,1);
		      	}
	      	}
	  }
	  return aArr;
  }
   
   /*
   * 第一个数组减去第二个数组
   * 
   */
   function arr_dive_none(aArr,bArr){   
      if(bArr==""){return aArr;}
      if(aArr==""){return bArr;}
	  for(var j=0;j<bArr.length;j++){
			for(var q=0;q<aArr.length;q++){
				if(aArr[q]==bArr[j]){
					aArr.splice(q,1);
		      	}
	      	}
	  }
	  return aArr;
  }
   
  function submit() {
	  var form=document.getElementById('student_form');
	  form.submit();
  }
  function operate(){
	//上行右侧列表
	var upRightos = document.getElementById("upRightos");
	//下行右侧列表
	var downRightos = document.getElementById("downRightos");
	//上行左侧列表
	var upLeftos = document.getElementById("upLeftos");
	//下行左侧列表
	var downLeftos = document.getElementById("downLeftos");
	//上行已选列表
	var idlist1 ="";
	//下行已选列表
	var idlist2 ="";
	for(var i=0;i<upRightos.length;i++) {
		  if(idlist1==""){
			  idlist1 = upRightos.options[i].value;
		  }else{
			  idlist1 = idlist1+","+upRightos.options[i].value;
		  }				 
	}
	for(var i=0;i<downRightos.length;i++) {
	     if(idlist2==""){
		     idlist2 = downRightos.options[i].value;
		 }else{
		     idlist2 = idlist2+","+downRightos.options[i].value;
		 }				 
	}
	var arr1=document.getElementById("delupexistdata").value.split(",");
	var arr2=idlist1.split(",");
	var delupexistdata = arr_dive(arr1,arr2);
	var arr3=document.getElementById("deldownexistdata").value.split(",");
	var arr4=idlist2.split(",");
	var deldownexistdata = arr_dive(arr3,arr4);
	//上行不选数据
	var upnotexist="";
	//如果上行不存在不选数据，直接把不选数据设为已选列表中的数据
	if(document.getElementById("upnotexist").value==""){
		upnotexist = idlist1;
	}else{
		//如果上行存在不选数据，且已选列表不为空
		if(idlist1!=""){
			var upstr=document.getElementById("upnotexist").value;
			upnotexist=upstr+","+idlist1;
			//总不选数据去重后结果
			var unupnotexist=unique(upnotexist.split(","));
			var newstr="";
			//总不选数据去除，从右侧选至左侧的数据
			for(var j=0;j<upLeftos.length;j++){
				for(var q=0;q<unupnotexist.length;q++){
		      		if(unupnotexist[q]==upLeftos.options[j].value){
		      			unupnotexist.splice(q,1);
			      	}
		      	}
			}
			if(typeof(unupnotexist)!="undefined"){
				newstr=unupnotexist.join(",");
			}
			//得出上行总不选数据
			upnotexist=newstr;
		}else{
			//如果上行存在不选数据，且已选列表为空
			var unupnotexist=unique(document.getElementById("upnotexist").value.split(","));
			var newstr="";
			
			for(var j=0;j<upLeftos.length;j++){
				for(var q=0;q<unupnotexist.length;q++){
		      		if(unupnotexist[q]==upLeftos.options[j].value){
		      			unupnotexist.splice(q,1);
			      	}
		      	}
			}
			if(typeof(unupnotexist)!="undefined"){
				newstr=unupnotexist.join(",");
			}
			upnotexist=newstr;
		}
	}
	//下行不选数据
	var downnotexist="";
	if(document.getElementById("downnotexist").value==""){
		downnotexist = idlist2;
	}else{
		if(idlist2!=""){
			var downstr=document.getElementById("downnotexist").value;
			downnotexist=downstr+","+idlist2;
			var undownnotexist=unique(downnotexist.split(","));
			var newstr="";
			
			for(var j=0;j<downLeftos.length;j++){
				for(var q=0;q<undownnotexist.length;q++){
		      		if(undownnotexist[q]==downLeftos.options[j].value){
		      			undownnotexist.splice(q,1);
			      	}
		      	}
			}
			if(typeof(undownnotexist)!="undefined"){
				newstr=undownnotexist.join(",");
			}
			downnotexist=newstr;
		}else{
			var undownnotexist=unique(document.getElementById("downnotexist").value.split(","));
			var newstr="";
			
			for(var j=0;j<downLeftos.length;j++){
				for(var q=0;q<undownnotexist.length;q++){
		      		if(undownnotexist[q]==downLeftos.options[j].value){
		      			undownnotexist.splice(q,1);
			      	}
		      	}
			}
			if(typeof(undownnotexist)!="undefined"){
				newstr=undownnotexist.join(",");
			}
			downnotexist=newstr;
		}
	}
	//window.parent.addUpStudentData(jQuery('#siteid').val(),idlist1,idlist1.split(",").length,upnotexist,delupexistdata);
	//window.parent.addDownStudentData(jQuery('#siteid').val(),idlist2,idlist2.split(",").length,downnotexist,deldownexistdata);	 	 		
	//fixed SCRIPT5011: Can't execute code from a freed script
	window.parent.eval("addUpStudentData('"+jQuery('#siteid').val()+"','"+idlist1+"','"+idlist1.split(',').length+"','"+upnotexist+"','"+delupexistdata+"')");
	window.parent.eval("addDownStudentData('"+jQuery('#siteid').val()+"','"+idlist2+"','"+idlist2.split(',').length+"','"+downnotexist+"','"+deldownexistdata+"')");
  	art.dialog.close();
  }
    function quxiao(){		
  		art.dialog.close();
  	}				 

    /**
     * Array unique function,同时将去掉null及undefined
     * @param {Array} ary 需要进行unique的数组.
     * @return {Array} 返回经过去重的新的数组，
     * 不会修改原来的数组内容.
     */
    function unique(ary) {
        var i = 0,
            gid='_'+(+new Date)+Math.random(),
            objs = [],
            hash = {
                'string': {},
                'boolean': {},
                'number': {}
            }, p, l = ary.length,
            ret = [];
        for (; i < l; i++) {
            p = ary[i];
            if (p == null) continue;
            tp = typeof p;
            if (tp in hash) {
                if (!(p in hash[tp])) {
                    hash[tp][p] = 1;
                    ret.push(p);
                }
            } else {
                if (p[gid]) continue;
                p[gid]=1;
                objs.push(p);
                ret.push(p);
            }
        }
        for(i=0,l=objs.length;i<l;i++) {
            p=objs[i];
            p[gid]=undefined;
            delete p[gid];
        }
        return ret;
    }
      	
</script>
</body>
</html>

