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
<body style="width:620px; min-width:490px;">
<s:hidden id ="stu_id" name ="stu_id" ></s:hidden>
<table  width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td  valign="top">
		<table width="100%" border="0" cellspacing="5" cellpadding="0">
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
		</td>
	</tr>
</table>
</body>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<script type="text/javascript">
	jQuery(function() {
	 var gala = jQuery('#gala');
	 gala.flexigrid({
	  url: '../ftlyInfoNew/showStudentList.shtml', 
	  dataType: 'json',    //json格式
	  height: 330,
	  width:600,
	  colModel : [ 
	        {display: '姓名', name : 'USER_NAME', width : 100,  align: 'center',process:reWriteLink, escape:true},
	        {display: '', name : 'USER_ID', width : 128,  align: 'center', escape:true, hide:true},
	        {display: '电话号码', name : 'MOBLIE', width : 128,  align: 'center', escape:true},
	        {display: '备注', name : 'REMARKS', width : 200, align: 'center', escape:true}
	        ],
	       sortname: 'USER_ID',
	       sortorder: 'asc',  //升序OR降序
	       sortable: true,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :true,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: true,    // 是否可以动态设置每页显示的结果数
	       rp: 20,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showTableToggleBtn: true   // 是否允许显示隐藏列
	     });
	});			
	
	//转换链接
	function reWriteLink(tdDiv,pid,row){
		 return '<a href="javascript:operate(\'' + row.cell[0] + '\',\'' + row.cell[1] +'\',\'' + row.cell[2] +'\')">' + tdDiv +'</a>';
	}
	
	function operate(str1,str2,str3){
		if(str1 == 'null')
			str1 = "";
		if(str2 == 'null')
			str2 = "";
		if(str3 == 'null')
			str3 = "";
  		window.parent.document.getElementById("stu_name").value=str1;
  		window.parent.document.getElementById("stu_id").value=str2;
  		window.parent.document.getElementById("telephone").value=str3;
  		art.dialog.close();
  	}
</script>
</html>

