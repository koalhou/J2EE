<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title>学生信息</title>
<!-- 中文注释
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">  -->
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body style="width:700px; min-width:700px;">
<s:form id="student_form" action="/userpkg/lnDivList" method="post">
<s:hidden id ="stu_id" name ="stu_id" ></s:hidden>
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
								<s:textfield id="stu_name" name="info.stu_name" cssClass="input120" maxlength="16" onkeypress="if(event.keyCode==13){searchStudent();}"/>&nbsp;&nbsp;
								</td>					
							</tr>
							<tr><td>
								学校：
								<s:textfield id="stu_school" name="info.stu_school" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStudent();}"/>&nbsp;&nbsp;
								</td>
							    <td>
								班级：
								<s:textfield id="stu_class" name="info.stu_class" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStudent();}"/>
								</td>	
								<td><a href="#" onclick="searchStudent()" class="sbutton">查询</a></td>
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
                                      <td width="30%" class="titleStyleZi">学生信息</td>
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
</s:form>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">
//查询
function searchStudent() {

	jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'info.stu_name', value: formatSpecialChar(jQuery('#stu_name').val())},
		         {name: 'info.stu_code', value: formatSpecialChar(jQuery('#stu_code').val())},
		         {name: 'info.stu_school', value: formatSpecialChar(jQuery('#stu_school').val())},
		         {name: 'info.stu_class', value: formatSpecialChar(jQuery('#stu_class').val())},	
		         {name:'stu_id',value: formatSpecialChar(jQuery('#stu_id').val())}		 		
			 	]
	});
	jQuery('#gala').flexReload();
}

//转换链接
function reWriteLink2(tdDiv,pid,row){
	//if(get_cell_text(pid, 5)==1){
	//  tdDiv.innerHTML = '<input type="checkbox" checked="checked" name="checkstudent" value="'+get_cell_text(pid, 1)+'\,'+get_cell_text(pid, 4)+'" onclick="operate2()"/> ';                                                                                  
	//  operate2();
	//}else{
	//  tdDiv.innerHTML = '<input type="checkbox" name="checkstudent" value="'+get_cell_text(pid, 1)+'\,'+get_cell_text(pid, 4)+'" onclick="operate2()"/> ';                                                                                  			
	//}
	
	if(row.cell[5]==1){
		return '<input type="checkbox" checked="checked" name="checkstudent" value="'+pid+'\,'+row.cell[2]+'" onclick="operate2()"/> ';                                                                                  
	}else{
		return '<input type="checkbox" name="checkstudent" value="'+pid+'\,'+row.cell[2]+'" onclick="operate2()"/> ';                                                                                  			
	}
	//operate2();
}
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

jQuery(function() {
	 var gala = jQuery('#gala');
	 var checkBoxHtml = '<input type="checkbox" name="checkstudent"  onclick="operate2()" ';
	 var url= "../userpkg/lnDivList.shtml?checkBoxHtml="+checkBoxHtml+"&stu_id="+jQuery('#stu_id').val();
	 //alert(url);
	 gala.flexigrid({
	  url: '<s:url value="'+url+'" />', 
	  dataType: 'json',    //json格式
	  height: 271,
	  width:670,
	  colModel : [ 
            {display: '<input type="checkbox" name="checkallst" onclick="operate3(this,this.checked)"/>',name : '', width : 40, sortable : false, align: 'center', process:reWriteLink2,toggle:false}, 
	        {display: '学号',name : 'stu_code', width : 60, sortable : false, escape:true,align: 'center'}, 
	        {display: '姓名', name : 'stu_name', width : 128, sortable : false, escape:true,align: 'center'},	       
	        {display: '学校', name : 'stu_school', width : 100, sortable : false, escape:true,align: 'center'},
	        {display: '班级', name : 'stu_class', width : 200, sortable : false, escape:true,align: 'center'},	       
	        {display: '是否选中', name : 'flag', width : 200, sortable : false, align: 'center',toggle:false,hide:true}
	        ],
	       sortname: 'stu_id',
	       sortorder: 'asc',  //升序OR降序
	       sortable: true,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :false,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: false,    // 是否可以动态设置每页显示的结果数
	       rp: 10000,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showTableToggleBtn: true   // 是否允许显示隐藏列
	     });
	    var checkList = document.getElementsByName("checkallst");
	    for(var i=0;i<checkList.length;i++) {	
	    	checkedall = checkList[i];	
	    	break;	
	    }
	});
//<input type="checkbox" id ="checkallst" onclick="operate3(this,this.checked)"/>
  var checkedall;
 
  function submit() {
	  var form=document.getElementById('student_form');
	  form.submit();
  }
	var carvin="";
	function operate(){
		count =0;
		var arrSon = document.getElementsByName("checkstudent");
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
  		window.parent.document.getElementById("stu_id").value=idlist;
  		window.parent.document.getElementById("stu_name").value=namelist;
  		window.parent.checkstudentID(); 		
  		art.dialog.close();
  	}
    function quxiao(){		
  		window.parent.document.getElementById("stu_id").value="";
  		window.parent.document.getElementById("stu_name").value=""; 
  		window.parent.checkstudentID(); 		
  		art.dialog.close();
  	}
    function operate2(){
    	
		var arrSon = document.getElementsByName("checkstudent");
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
		var arrSon = document.getElementsByName("checkstudent");				
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
</script>
</body>
</html>