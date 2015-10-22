<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:property value="getText('common.title')" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	
	<div id="content">
		<s:form id="vehicle_form" action="vehiclemanage" method="post">
		<table  width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td  valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="36" valign="top" background="../images/tree_tabBg.gif">
						<div class="toolbar">
						<div class="contentTil"><s:text name="menu3.clgl" /></div>
						
						</div>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td class="reportOnline2">
						<table width="100%" border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td>
								<s:if test="#session.perUrlList.contains('111_3_3_4_1')">
								<table border="0" cellspacing="4" cellpadding="0">
									<tr>
										<td>
										<s:text name="common.vehcileln" />：
										<s:textfield id="vehcileLn" name="vehcileLn" cssClass="input150" maxlength="7" onkeypress="if(event.keyCode==13){searchList();}"/>
										</td>
										<td>
										<s:text name="common.vehvin" />：
										<s:textfield id="vehcileVin" name="vehcileVin" cssClass="input150" maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
										</td>
										
									<td>所属部门：</td>
		                              <td class="fsBlack">
		                              <s:textfield id="organizationName" name="optuserInfo.organizationName" readonly="true" onclick="openorganizidtree()"/>
		                              <s:hidden id="organizationID" name="optuserInfo.organizationID"></s:hidden>
		                              </td>
										<td><a href="javascript:void(0);" onClick="searchList()" class="sbutton"><s:text name="common.chaxun" /></a></td>
										<s:if test="#session.perUrlList.contains('111_3_3_4_5')">
		                                      <td><a href="#" onclick="save()" class="sbutton"><s:text name="vehcileinfo.cancle" /></a></td>
		                                </s:if>
									</tr>
								</table>
								</s:if>
								</td>
							</tr>
						</table>		
						</td>
					</tr>
					<tr>
					  <td valign="top">
					   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
					     <tr>
					        <td>
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          <tr>
		                         <td class="titleStyle">
		                         <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                                    <tr>
		                                      <td width="30%" class="titleStyleZi"><s:text name="vehcileinfo.info" /></td>
		                                      
		                                     <td  align="right">
		                                      <div  class="buhuanhangbut">
		                                      <s:if test="#session.perUrlList.contains('111_3_3_4_2') && 3 != #session.adminProfile.userType">
		                                      <a href="<s:url value='/vehicle/addvehicleb.shtml'/>" class="btnAllot" title="<s:text name="button.fenpei" />">
		                                      </a>
		                                      </s:if>
		                                      </div>
											  <s:if test="2==#session.adminProfile.en_mould">
	                                      		<div class="buhuanhangbut">
	                                      			<a href="#" 
	                                      			   class="btnIssue" 
	                                      			   onclick="sendRouteVin()"
	                                      			   title="<s:text name="button.xianluxiafa" />">
	                                      			</a>
	                                      		</div>	
	                                      	  </s:if>	
	                                      	  <s:if test="1==#session.adminProfile.en_mould">
													<div class="buhuanhangbut">
														<a href="#" 
														   class="btnDaochu"
														   onclick="exportIni()"
														   title="<s:property value="getText('msg.export')" />">
														</a>
													</div>
											  </s:if>
		                                      </td>
		                                      
		                                      <td width="1%">&nbsp;</td>
		                                    </tr>
		                          </table>
		                          </td>
		                      </tr>
					        </table>
					        </td>
					     </tr>
					     <tr>
					        <td class="reportInline" align="left">
					        <div>
					        <div id="Below_new" align="center">
					         <s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/>
					        </div>
		                       <table id="vehicletbl" width="100%"  cellspacing="0">
							   </table>
					        </div>
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
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">
function submit() {
	  var form=document.getElementById('vehicle_form');
	  form.submit();
}

function searchList() {
	 document.getElementById('Below_new').style.display='none';
	jQuery('#vehicletbl').flexOptions({
		newp: 1,
		params: [{name: 'vehcileVin', value: formatSpecialChar(jQuery('#vehcileVin').val()) },
		{name: 'organization_id', value: jQuery('#organizationID').val()},
		{name: 'vehcileLn', value: formatSpecialChar(jQuery('#vehcileLn').val())}
		]
	});
	jQuery('#vehicletbl').flexReload();
}


function detialbrwose(vehicleid){
	var form = document.getElementById('vehicle_form');
	form.action = 'vehicle_edit_form.shtml?vehcileInfo.vehicle_id=' + vehicleid;
	form.submit();
}
function reWriteLink(tdDiv,pid,row){
	return  '<a href="javascript:detialbrwose(\'' + pid + '\')">' + tdDiv +'</a>';
	}

function reWriteCheckBox(tdDiv,pid,row){
	//if(get_cell_text(pid, 5).length<=1){
		if(row.cell[6]==undefined){
			return '<input name="carChoice" value="' + row.cell[2] + '"  type="checkbox" disabled="disabled"/>';
		}else{
			return '<input name="carChoice" value="' + row.cell[2] + '"  type="checkbox"/>';
		}
		
	//}
	//else{
		
	 	//tdDiv.innerHTML = '<input name="carChoice" value="' + get_cell_text(pid, 1) + '" checked type="checkbox" />';
	//}
}


	/**
	 * 分配关联保存
	 */
	function save() {
		var carsChoice = document.getElementsByName("carChoice");


	    var carIdList = "";
	    
	    for(var i=0; i<carsChoice.length; i++) {
	        if(carsChoice[i].checked==true) {
	            if(carIdList=="") {
	            	carIdList = "'" + carsChoice[i].value + "'";
	            } else {
	            	carIdList=carIdList + "," + "'" + carsChoice[i].value  + "'";
	            }
	        }
	    }

	    if(carIdList==""){
			alert("请选择车辆进行去除分配！");
			return false;
		}

	    var info="<s:property value="getText('batch.canle.cars')"/>";
	   
	   if(confirm(info)) {
		   var url="<s:url value='/vehicle/batchVehicleCancle.shtml' />?carsVinInfos=" + carIdList;
		   document.forms[0].action=url;
		   document.forms[0].submit();
		   
	   }  	
	}
	
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}


function setOrCancelSelect(obj) {
	
	 if(jQuery(obj).attr("checked")){
		jQuery("input[name='carChoice']").each(function(){
		if(jQuery(this).attr('disabled')){
			 jQuery(this).removeAttr("checked"); 
		}else{
			jQuery(this).attr("checked","true");
		}
		});
	 }else{
		 jQuery("input[name='carChoice']").each(function(){
			 jQuery("input[name='carChoice']").removeAttr("checked"); 
			// f_str += ","+jQuery("input[name='carChoice']").attr("value");
		});
	}
}




jQuery(function() {
	//var strshow=decodeURIComponent(jQuery('.actionMessage').text());
	//jQuery('.actionMessage').text(strshow);
	var gala = jQuery('#vehicletbl');
	gala.flexigrid({
		url: '<s:url value="/vehiclemanagepkg/vehiclebrowse.shtml" />',
		dataType: 'json',
		height: 375,
		width:2000,
		colModel : [
                    {display: '<input id="carChoiceAll" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', name : '', width : get_page_width()*0.05, process:reWriteCheckBox},
		    		{display: '<s:text name="common.vehcileln" />', name : 'vehicle_ln', width : get_page_width()*0.15, sortable : true, align: 'center', process: reWriteLink,escape: true},
                    {display: '车辆ＶＩＮ', name : 'vehicle_vin', width : get_page_width()*0.2, sortable : true, align: 'center',escape: true},
		    		{display: '座位数', name : 'LIMITE_NUMBER', width : get_page_width()*0.1, sortable : true, align: 'center',escape: true},
		    		{display: '<s:text name="vehcileinfo.short_allname" />', name : 'SHORT_ALLNAME', width : get_page_width()*0.4, sortable : true, align: 'center',escape: true},
		    		{display: '所属车主姓名', name : 'user_name', width : 200, sortable : true, align: 'center',hide:true,toggle:false,escape: true},
		    		{display: 'orgid', name : 'orgid', width : 20, sortable : true, align: 'center',hide:true,toggle:false,escape: true}
			    	   ],
		    		    		
		    	sortname: 'vehicle_vin',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 20,
		    	
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		    	onSuccess:function(){
	               jQuery("input[name='carChoiceAll']").removeAttr("checked");  
                  }
		    	//getQuery :getQuery
	});
});


function openorganizidtree(){
	

	art.dialog.open("<s:url value='/usm/usermanagetreeAction.shtml' />?rad="+Math.random(),
			//userupdatetreeAction.shtml?userID=" + userid,
			{
			width :260,
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
		        	//alert(iframeWin.test);
		        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
		        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
		        	//当前页面中对象
		            var topOrgName =  window.document.getElementById('organizationName');
		            var topOrgID = window.document.getElementById('organizationID');
		            //赋值
		        	if (topOrgName) topOrgName.value = orgNameValue;
		        	if (topOrgID) topOrgID.value = orgIDValue;
		    	}
		  
			});

}


//页面自适应
(function (jQuery) {
 jQuery(window).resize(function(){
 
  testWidthHeight();
  
 });
 jQuery(window).load(function (){
  
  testWidthHeight();
  
 });
 
})(jQuery);
//获取页面高度
function get_page_height() {
 /*
 var height = 0;
 
 if (jQuery.browser.msie) {
     height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
 } else {
     height = self.innerHeight;
 }
 return height;*/
 var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
 return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
}
//获取页面宽度
function get_page_width() {
 /*
 var width = 0;
 if(jQuery.browser.msie){ 
  width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
 } else { 
  width = self.innerWidth; 
 } 
 return width;*/
 var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
 return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
}

function firstrisize(){
	var h = get_page_height();
	 var w = get_page_width();
	 jQuery(".flexigrid").width(w-23);
	 //jQuery("#reportTab").height(h-100);
	 jQuery(".bDiv").height(h-325);
}

//计算控件宽高
function testWidthHeight(){
	firstrisize();
	 jQuery(window).mk_autoresize({
        height_include: '#content',
        height_exclude: ['#header', '#footer'],
        height_offset: 0,
        width_include: ['#header', '#content', '#footer'],
        width_offset: 0
     });
	 firstrisize();
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
/**
* 线路下发
*/
function sendRouteVin() {
	var carsChoice = document.getElementsByName("carChoice");
   	var carIdList = "";
    var tripidList="";
   	for(var i=0; i<carsChoice.length; i++) {
       if(carsChoice[i].checked==true) {
           if(carIdList=="") {
           	carIdList = carsChoice[i].value.split("||")[0] ;
           	//tripidList = carsChoice[i].value.split("||")[1] ;
           } else {
           	carIdList=carIdList + "," + carsChoice[i].value.split("||")[0] ;
           	//tripidList = tripidList+","+carsChoice[i].value.split("||")[1] ;
           }
       }
   	}
   	var carArr=unique(carIdList.split(","));
	var newstr="";
	if(typeof(carArr)!="undefined"){
		newstr=carArr.join(",");
	}
   	if(newstr==""){
		alert("请选择车辆进行线路下发！");
		return false;
	}
   	var info="确认下发所选车辆？";
  	if(confirm(info)) {
  		jQuery.blockUI({ message: "<h1>线路下发,请稍等...</h1>" }); 
  		jQuery.ajax({
	   		  type: 'POST',  
	   		  url: '../infomanage/sendRouteFileM2.shtml',	
	   		  data: {carsVinInfos: newstr,
  					 tripidInfos:tripidList
	   		  },	
	   		  complete: function() { 
                jQuery.unblockUI(); 
              }
	    });
  	}
}

function exportIni(){
	if(confirm("确定要导出么？")) {
		var organization_id= jQuery('#organizationID').val();
		document.getElementById('vehicle_form').action="<s:url value='/vehicle/exportVehicleInfo.shtml' />?organization_id="+organization_id;
		document.getElementById('vehicle_form').submit();
	 }
}

</script>
</body>
</html>


