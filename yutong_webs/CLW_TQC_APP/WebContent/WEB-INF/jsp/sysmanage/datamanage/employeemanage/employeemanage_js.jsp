<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/PasswordConfirmDWR.js'></script>
<script type="text/javascript">
		jQuery(function() {
		 var gala = jQuery('#gala');
		 gala.flexigrid({
		  url: '<s:url value="/employeeGrid/employeeListta.shtml" />',
		  dataType: 'json',    //json格式
		  height: 375,
		  width:0,
		  colModel : [
				{display: '<input id="carChoiceAll" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', name : '', width : get_page_width()*0.02, process: reWriteCheckBox},
				//{display: '序号', name : 'rowNumber', width : 30, sortable : false, align: 'center'},
				{display: '姓名', name : "NLSSORT(STU_NAME,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.075,60,238), sortable : true, align: 'center', escape:true, process: reWriteLink},
		        {display: '员工号',name : "NLSSORT(STU_CODE,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.175,60,238), sortable : true, align: 'center', escape:true}, 
		        {display: '卡号', name : 'STU_CARD_ID', width : calcColumn(0.175,60,238), sortable : true, align: 'center'},
		        {display: '所属组织', name : "NLSSORT(STU_SCHOOL,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.1,60,238), sortable : true, escape:true, align: 'center'},
		        {display: '备注', name : "NLSSORT(STU_CLASS,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.35,60,238), sortable : true, escape:true, align: 'center'},
		        {display: '', name : '', width : calcColumn(0.05,60,238), sortable : false, align: 'center',process: reWriteDelLink}
		        //{display: '<s:text name="sex" />', name : 'STU_SEX', width : 30, sortable : true, align: 'center', process: reWriteSex},
		        //{display: '<s:text name="birthday" />', name : 'STU_BIRTH', width : 80, sortable : true, align: 'center'},
		        //{display: '地址', name : 'STU_ADDRESS', width : 150, sortable : true, align: 'center',escape:true},
		        //{display: '班主任', name : 'TEACHER_NAME', width : 50, sortable : true, escape:true,align: 'center'},
		        //{display: '班主任联系电话', name : 'TEACHER_TEL', width : 90, sortable : true, align: 'center'},
		        //{display: '家长', name : 'RELATIVE_NAME', width : 60, sortable : true, escape:true, align: 'center'},
		        //{display: '关系', name : 'RELATIVE_TYPE', width : 30, sortable : true, align: 'center'},
		        //{display: '家长电话', name : 'RELATIVE_TEL', width : 80, sortable : true, align: 'center'},
		        //{display: '', name : '', width : 80, sortable : false, align: 'center', process: reWriteGatherLink}
		        ],
		       sortname: "MODIFY_TIME desc,NLSSORT(STU_NAME,'NLS_SORT = SCHINESE_PINYIN_M')",
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true,   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		       onSuccess:function(){
	               jQuery("input[name='carChoiceAll']").removeAttr("checked");  
                  }
		     });

		 	jQuery(window).wresize(function(){
			  	testWidthHeight();
			});
			jQuery(window).load(function (){
				testWidthHeight();
 		    });
		});

	
		/** 删除员工信息 **/
		  function delEmployeeInfo(id) {
		     if(confirm("确定删除该员工信息？")) {
		    	document.getElementById("studentmanage_form").action="../employee/employee_delete.shtml?studentInfo.student_id="+id;
		    	document.getElementById("studentmanage_form").submit(); 
		    }else{
		        return false;
		    }
		  }
		
		function reWriteCheckBox(tdDiv,pid,row){
			if(tdDiv!=null&&tdDiv.length>0)
				return '<input name="carChoice" value="' + pid + '"  type="checkbox" />';
			else
				return '<input type="checkbox" disabled="disabled"/>';
		}

		// 采集链接
		function reWriteGatherLink(tdDiv, pid) {
			return '<a href="javascript:getCardId(' + pid + ');">'
					+ '采集' + '</a>';
		}
		
		//转换链接
		function reWriteLink(tdDiv,pid){
		     return '<a href="employee_edit_form.shtml?employeeInfo.employee_id=' + pid + '">' + tdDiv +'</a>';
		 }
		//删除转换链接
		function reWriteDelLink(tdDiv,pid){
			if(tdDiv!=null&&tdDiv.length>0)
			return '<a href="#" onclick="delEmployeeInfo(' + pid + ')">删除</a>';
			else
				return "删除";
		}

		//判断性别
		function reWriteSex(tdDiv){
			if(tdDiv == '0'){
				return '<s:text name="male" />';
			}else{
				return '<s:text name="female" />';
			}
		}
		
		//查询
		var flagDel=false;
		function searchStudent() {
			if(flagDel){
				document.getElementById('Below_new').style.display='';
			}
			else{
				document.getElementById('Below_new').style.display='none';
			}
			flagDel=false;
			jQuery('#gala').flexOptions({
				newp: 1,
				params: [{name: 'studentInfo.student_code', value: formatSpecialChar(jQuery('#student_code').val())},
				         {name: 'studentInfo.organization_id', value: formatSpecialChar(jQuery('#organizationID').val())},
						 {name: 'studentInfo.student_card', value: formatSpecialChar(jQuery('#student_card').val())},
				         {name: 'studentInfo.student_name', value: formatSpecialChar(jQuery('#student_name').val())}]
			});
			jQuery('#gala').flexReload();
		}
		
		function setOrCancelSelect(obj) {
			
			 if(jQuery(obj).attr("checked")){
				jQuery("input[name='carChoice']").each(function(){
				jQuery("input[name='carChoice']").attr("checked","true");
				});
			 }else{
				 jQuery("input[name='carChoice']").each(function(){
					 jQuery("input[name='carChoice']").removeAttr("checked"); 
					// f_str += ","+jQuery("input[name='carChoice']").attr("value");
				});
			}
		}
		
		/**
		 * 删除批量
		 */
		function save() {
			var carsChoice = document.getElementsByName("carChoice");
			//alert(carsChoice);

		    var carIdList = "";
		    
		    for(var i=0; i<carsChoice.length; i++) {
		        if(carsChoice[i].checked==true) {
		            if(carIdList=="") {
		            	carIdList =carsChoice[i].value;
		            } else {
		            	carIdList=carIdList + ","+ carsChoice[i].value;
		            }
		        }
		    }

		    if(carIdList==""){
				alert("请选择员工信息删除！");
				return false;
			}

		    var info="<s:property value="getText('确认删除所选员工信息？')"/>";
		   if(confirm(info)) {
			   /* var url="<s:url value='/employee/employee_delete.shtml' />?studentInfo.student_id=" + carIdList;
			   document.forms[0].action=url;
			   document.forms[0].submit(); */
			   var data = "studentInfo.student_id="+ carIdList;
			   jQuery.post("../employee/employee_delete2.shtml",data,function(ddd){
				   if(ddd=='success') {
					   flagDel=true;
					   jQuery("#Below_new .actionMessage").html("删除成功");
					   searchStudent();
				   }
			   });
		   }
		}
	

		function exportStudent(){
			if(jQuery("#gala").find("td").length == 0){
				alert("没有员工信息,无法导出!");
				return;	
			}
			if(confirm("确定要导出员工信息么？")) {
				document.getElementById('studentInfo.student_code').value = jQuery('#student_code').val();
				document.getElementById('studentInfo.organization_id').value = formatSpecialChar(jQuery('#organizationID').val());
				document.getElementById('studentInfo.student_card').value = formatSpecialChar(jQuery('#student_card').val());
				document.getElementById('studentInfo.student_name').value = formatSpecialChar(jQuery('#student_name').val());
				document.getElementById('studentInfo.sortname').value = jQuery('#gala').flex_sortname();	
				document.getElementById('studentInfo.sortorder').value = jQuery('#gala').flex_sortorder();	

				document.getElementById('studentmanage_form').action="<s:url value='/employee/exportEmployee.shtml' />";
				document.getElementById('studentmanage_form').submit();
			}
		}
		function openorganizidtree(){
			art.dialog.open("<s:url value='/usm/usermanagetreeAction.shtml' />?rad="+Math.random(),
					//userupdatetreeAction.shtml?userID=" + userid,
					{
					width :260,
					height:300,
					id: 'treeOID',
					title: '选择组织',
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
		//获取页面高度
		function get_page_height() {
		  var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
		  return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
		}
		//获取页面宽度
		function get_page_width() {
		  var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
		  return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
		}
		 
		//计算控件宽高
		function testWidthHeight(){
			var h = get_page_height();
			var w = get_page_width();
			jQuery(".bDiv").height(h-330);
			jQuery(".flexigrid").width(w-23);
			 
			jQuery(window).mk_autoresize({
			       height_include: '#content',
			       height_exclude: ['#header', '#footer'],
			       height_offset: 0,
			       width_include: ['#header', '#content', '#footer'],
			       width_offset: 0
			    });

			 h = get_page_height();
			 w = get_page_width();
			 jQuery(".bDiv").height(h-330);
			 jQuery(".flexigrid").width(w-23);
			 
		}
</script>