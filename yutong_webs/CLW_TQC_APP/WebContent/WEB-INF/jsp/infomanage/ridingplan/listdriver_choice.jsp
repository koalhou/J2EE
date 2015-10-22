<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body style="width:450px; min-width:450px;" onload="init();">
<s:form id="driver_form" action="/ridingplanpkg/getDriver" method="post">
</s:form>
<s:hidden id ="driver_ids" name ="driver_ids" ></s:hidden>
<s:hidden id ="vehicle_vin_old" name ="vehicle_vin_old" ></s:hidden>
<s:hidden id ="update" name ="update" ></s:hidden>
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
								驾驶员姓名：
								<s:textfield id="driver_name" maxlength="16" name="driverInfo.driver_name" cssClass="input120" onkeypress="if(event.keyCode==13){searchDriver();}"/>&nbsp;&nbsp;
								</td>
								<td><a href="#" onclick="searchDriver()" class="sbutton">查询</a></td>
								<td><!-- <a href="#" onclick="operate()" class="sbutton">选择</a> --></td>
								<td><!-- <a href="#" onclick="quxiao()" class="sbutton">取消选择</a> --></td>
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
                         <td class="titleStyle">
                         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td width="30%" class="titleStyleZi">驾驶员信息</td>
                                       <td width="69%" align="right">                                     
                                       </td>
                                      <td width="1%">&nbsp;</td>
                                      
                                    </tr>
                          </table>
                          </td>
                      </tr>
                      
                      <tr>
				        <td class="reportInline">
				        
					        <div id="haveDriverData">
								<table id="gala" width="100%" cellspacing="0"></table>
					        </div>
					        
				        </td>
				     </tr>
			        </table>
			        </td>
			     </tr>
			     
			   </table>
			  </td>
			</tr>
			<tr>
			  <td>
			  	<div id="noDriverData" style='text-align:center'>
									查询不到内容
						        </div>
			  </td>
			  </tr>
		</table>
		</td>
	</tr>
</table>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">

//查询
function searchDriver() {
	jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'driverInfo.driver_name', value: encodeHtml(formatSpecialChar(jQuery('#driver_name').val()))},	
		         {name:'driver_ids',value: jQuery('#driver_ids').val()}		 		
			 	]
	});
	jQuery('#gala').flexReload();
}

//转换链接
function reWriteLink2(tdDiv,pid,row){
	//alert(get_cell_text(pid, 0)+","+get_cell_text(pid, 1)+","+get_cell_text(pid, 2)+','+get_cell_text(pid, 3)+','+get_cell_text(pid, 4));
	if(row.cell[5]==1){
		return '<input type="checkbox" style="_margin-top:-3px;" checked="checked" name="checkdriver" value="'+row.cell[1]+'\,'+row.cell[2]+'" onclick="operate2()"/> ';                                                                                  
		operate2();
	}else{
		return '<input type="checkbox" style="_margin-top:-3px;" name="checkdriver" value="'+row.cell[1]+'\,'+row.cell[2]+'" onclick="operate2()"/> ';                                                                                  			
	}
}
//转换链接
function checknametrue(tdDiv,pid,row){
	return '<a href="javascript:void(0);" onclick="operate5(\''+row.cell[1]+'\',\''+row.cell[2]+'\')">'+tdDiv+'</a>';
}
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

jQuery(function() {
	 var gala = jQuery('#gala');
	 //alert(jQuery('#driver_ids').val());
	 // alert(jQuery('#update').val()+","+jQuery('#vehicle_vin_old').val());
	 var url= "../ridingplanpkg/getDriver.shtml?driver_ids="+jQuery('#driver_ids').val();
	 if(jQuery('#update').val()=="update"){
		 url+="&driverInfo.vehicle_vin="+jQuery('#vehicle_vin_old').val();
     }
	 //alert(url);
	 gala.flexigrid({
	  url: '<s:url value="'+url+'" />', 
	  dataType: 'json',    //json格式
	  height: 271,
	  width:420,
	  colModel : 
		  [ 
			{display: '<input type="checkbox" id ="checkallst" style="_margin-top:-3px;" name="checkallst" onclick="operate3(this,this.checked)"/>',name : '',hide:true ,width : 20, sortable : false, align: 'center', process:reWriteLink2,toggle:false}, 
			{display: '驾驶员ID', name : 'DRIVER_ID', width : 150, sortable : false, align: 'center',toggle:false,hide:true},
			{display: '驾驶员姓名', name : 'DRIVER_NAME', width : 150, sortable : false, align: 'center', process:checknametrue,escape:true},
	        {display: '卡号', name : 'DRIVER_CARD_ID', width : 150, sortable : false, align: 'center',toggle:false,hide:true},
	        {display: '<s:text name="driverinfo.driver_license" />',name : 'DRIVER_LICENSE', width : 150, sortable : false, align: 'center', escape:true},
	        {display: '是否选中', name : 'flag', width : 200, sortable : false, align: 'center',toggle:false,hide:true}	        
	        ],
	       sortname: 'driver_id',
	       sortorder: 'asc',  //升序OR降序
	       sortable: true,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :false,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: false,    // 是否可以动态设置每页显示的结果数
	       rp: 10000,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showTableToggleBtn: true,   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错             
			onSuccess:function(){
			if(this.total == 0) {
					jQuery("#noDriverData").show();
		  	   } else {
		  		jQuery("#noDriverData").hide();
		  	   }
		       return true;
	       }
	     });
	});
//<input type="checkbox" id ="checkallst" onclick="operate3(this,this.checked)"/>
  var checkedall =  document.getElementById('checkallst');
 
  function submit() {
	  var form=document.getElementById('driver_form');
	  form.submit();
  }
	var carvin="";
	function operate(){
		count =0;
		var arrSon = document.getElementsByName("checkdriver");
		var idlist ="";
		var namelist ="";
		for(var i=0;i<arrSon.length;i++) {
			if(arrSon[i].checked){
				 count++;
				 if(count==1){
					 idlist = arrSon[i].value.split(",")[0];
					 namelist =arrSon[i].value.split(",")[1];
			     }else{
			    	 idlist = idlist+","+arrSon[i].value.split(",")[0];
					 namelist =namelist+","+arrSon[i].value.split(",")[1];
				 }
				 
		   }
		}
  		window.parent.document.getElementById("driver_ids").value=idlist;
  		window.parent.document.getElementById("driver_names").value=namelist; 	
  		//window.parent.checkDriver_ids();	
  		art.dialog.close();
  	}
    function quxiao(){		
  		window.parent.document.getElementById("driver_ids").value="";
  		window.parent.document.getElementById("driver_names").value=""; 
  		//window.parent.checkDriver_ids();			
  		art.dialog.close();
  	}
    function operate2(){
		var arrSon = document.getElementsByName("checkdriver");
		for(var i=0;i<arrSon.length;i++) {
			if(!arrSon[i].checked){
				checkedall.checked = false;
				return;
		    }
		}
		checkedall.checked = true;	 
	}
    function operate5(id,name){
    	window.parent.document.getElementById("driver_id").value=id;
  		window.parent.document.getElementById("driver_name").innerText="驾驶员："+name;
  		art.dialog.close();
	}
	function operate3(str1,str2){
		checkedall = str1;
		var arrSon = document.getElementsByName("checkdriver");				
		if(str2){
		  for(var i=0;i<arrSon.length;i++) {
		    arrSon[i].checked = true;		
		  }	
		}else{
		   for(i=0;i<arrSon.length;i++) {
			    arrSon[i].checked = false;		
		   }	
		}		   
	}					 
  function closewindow(){
		window.returnValue=carvin;
		window.close();
  }
  function init(){
	 var checkList = document.getElementsByName("checkallst");
 	 for(var i=0;i<checkList.length;i++) {	
 		 checkedall = checkList[i];		
 	 }
  }

</script>
</body>
</html>

