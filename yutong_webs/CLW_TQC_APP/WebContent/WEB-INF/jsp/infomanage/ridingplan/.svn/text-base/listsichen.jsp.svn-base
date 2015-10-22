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
<body style="width:700px; min-width:700px;" onload="init();">
<s:form id="sichen_form" action="/ridingplanpkg/getSteward" method="post">
</s:form>
<s:hidden id ="steward_ids" name ="steward_ids" ></s:hidden>
<s:hidden id ="update" name ="update" ></s:hidden>
<s:hidden id ="vehicle_vin_old" name ="vehicle_vin_old" ></s:hidden>
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
								跟车老师姓名：
								<s:textfield id="sichen_name" name="sichenInfo.steward_name" cssClass="input120" onkeypress="if(event.keyCode==13){searchsichen();}"/>&nbsp;&nbsp;
								</td>
								<td><a href="#" onclick="searchsichen()" class="sbutton">查询</a></td>
								<td><a href="#" onclick="operate()" class="sbutton">选择</a></td>
								<td><a href="#" onclick="quxiao()" class="sbutton">取消选择</a></td>
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
                                <td width="30%" class="titleStyleZi">跟车老师信息</td>
                                 <td width="69%"align="right">                                     
                                 </td>
                                <td width="1%">&nbsp;</td>
                                
                              </tr>
                          </table>
                          </td>
                      </tr>
                      <tr>
				        <td class="reportInline">
					        <div>
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
		</table>
		</td>
	</tr>
</table>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<script type="text/javascript">

//查询
function searchsichen() {
	jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'sichenInfo.steward_name', value: jQuery('#sichen_name').val() },	
		         {name:'steward_ids',value: jQuery('#steward_ids').val()}		 		
			 	]
	});
	jQuery('#gala').flexReload();
}

//转换链接
function reWriteLink2(tdDiv,pid,row){
	if(row.cell[5]==1){
	  return '<input type="checkbox" style="_margin-top:-3px;" checked="checked" name="checksichen" value="'+row.cell[1]+'\,'+row.cell[2]+'" onclick="operate2()"/> ';                                                                                  
	  operate2();
	}else{
		return '<input type="checkbox" style="_margin-top:-3px;" name="checksichen" value="'+row.cell[1]+'\,'+row.cell[2]+'" onclick="operate2()"/> ';                                                                                  			
	}
}
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

jQuery(function() {
	 var gala = jQuery('#gala');
	 var url= "../ridingplanpkg/getSteward.shtml?steward_ids="+jQuery('#steward_ids').val();
	 if(jQuery('#update').val()=="update"){
		 url+="&sichenInfo.vehicle_vin="+jQuery('#vehicle_vin_old').val();
     }
	 gala.flexigrid({
	  url: '<s:url value="'+url+'" />', 
	  dataType: 'json',    //json格式
	  height: 271,
	  width:670,
	  colModel : 
		  [ 
            {display: '<input type="checkbox" id ="checkallst" style="_margin-top:-3px;" name="checkallst" onclick="operate3(this,this.checked)"/>',name : '', width : 20, sortable : false, align: 'center', process:reWriteLink2,toggle:false}, 
            {display: '司乘ID', name : 'SICHEN_ID', width : 150, sortable : false, align: 'center',toggle:false,hide:true},
            {display: '姓名', name : 'SICHEN_NAME', width : 80, sortable : false, align: 'center', escape:true},
		    {display: '卡号', name : 'SICHEN_CARD_ID', width : 100, sortable : false, align: 'center',toggle:false,hide:true},
		    {display: '身份证号',name : 'SICHEN_LICENSE', width : 150, sortable : false, align: 'center', escape:true},
		    {display: '是否选中', name : 'flag', width : 200, sortable : false, align: 'center',toggle:false,hide:true}	        
	        ],
	       sortname: 'sichen_id',
	       sortorder: 'asc',  //升序OR降序
	       sortable: true,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :false,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: false,    // 是否可以动态设置每页显示的结果数
	       rp: 10000,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showTableToggleBtn: true   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错             
	     });
	});
//<input type="checkbox" id ="checkallst" onclick="operate3(this,this.checked)"/>
  var checkedall =  document.getElementById('checkallst');
  function submit() {
	  var form=document.getElementById('sichen_form');
	  form.submit();
  }
var carvin="";
function operate(){
	count =0;
	var arrSon = document.getElementsByName("checksichen");
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
 		window.parent.document.getElementById("steward_ids").value=idlist;
 		window.parent.document.getElementById("steward_names").value=namelist; 
 		//window.parent.checkSteward_ids();			
 		art.dialog.close();
 	}
   function quxiao(){		
 		window.parent.document.getElementById("steward_ids").value="";
 		window.parent.document.getElementById("steward_names").value=""; 
 		//window.parent.checkSteward_ids();			
 		art.dialog.close();
 	}
   function operate2(){   
	var arrSon = document.getElementsByName("checksichen");
	for(var i=0;i<arrSon.length;i++) {
		if(!arrSon[i].checked){
			checkedall.checked = false;
			return;
	    }
	}
	checkedall.checked = true;	 
}
function operate3(str1,str2){
	checkedall = str1;
	var arrSon = document.getElementsByName("checksichen");				
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

