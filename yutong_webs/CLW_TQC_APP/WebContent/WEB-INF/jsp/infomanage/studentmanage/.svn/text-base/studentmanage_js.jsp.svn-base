<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/PasswordConfirmDWR.js'></script>
<script type="text/javascript">
		jQuery(function() {
		 var gala = jQuery('#gala');
		 gala.flexigrid({
		  url: '<s:url value="/studentGrid/studentList.shtml" />', 
		  dataType: 'json',    //json格式
		  height: 375,
		  width:0,
		  colModel : [ 
				{display: '序号', name : 'rowNumber', width : 30, sortable : false, align: 'center'},
				{display: '姓名', name : 'STU_NAME', width : 50, sortable : true, align: 'center', escape:true, process: reWriteLink},
		        {display: '学号',name : 'STU_CODE', width : 100, sortable : true, align: 'center', escape:true}, 
		        {display: '学生卡号', name : 'STU_CARD_ID', width : 120, sortable : true, align: 'center'},
		        {display: '学校', name : 'STU_SCHOOL', width : 80, sortable : true, escape:true, align: 'center'},
		        {display: '班级', name : 'STU_CLASS', width : 80, sortable : true, escape:true, align: 'center'},
		        {display: '<s:text name="sex" />', name : 'STU_SEX', width : 30, sortable : true, align: 'center', process: reWriteSex},
		        {display: '<s:text name="birthday" />', name : 'STU_BIRTH', width : 80, sortable : true, align: 'center'},
		        {display: '地址', name : 'STU_ADDRESS', width : 150, sortable : true, align: 'center',escape:true},
		        {display: '班主任', name : 'TEACHER_NAME', width : 50, sortable : true, escape:true,align: 'center'},
		        {display: '班主任联系电话', name : 'TEACHER_TEL', width : 90, sortable : true, align: 'center'},
		        {display: '家长', name : 'RELATIVE_NAME', width : 60, sortable : true, escape:true, align: 'center'},
		        {display: '关系', name : 'RELATIVE_TYPE', width : 30, sortable : true, align: 'center'},
		        {display: '家长电话', name : 'RELATIVE_TEL', width : 80, sortable : true, align: 'center'},
		        {display: '照片', name : 'PHOTO_NAME', width : 30, sortable : false, align: 'center',process: rePhotoStatus},
		        {display: '', name : '', width : 80, sortable : false, align: 'center', process: reWriteGatherLink}
		        ],
		       sortname: 'STU_ID',
		       sortorder: 'asc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		     });

		 	jQuery(window).wresize(function(){
			  	testWidthHeight();
			});
			jQuery(window).load(function (){
				testWidthHeight();
 		    });
		});

		// 更新卡号	
		function updateCardNoById(dataId, carNo) {
			DWREngine.setAsync(false);
			var ret = false;
			PasswordConfirmDWR.updateCardNoByInputType(dataId, carNo, "student", callBackFun);

			function callBackFun(data) {
				ret = data;
			}

			DWREngine.setAsync(true);
			
			if ("exists" == ret) {
				alert("学生卡号已存在！");
			} else if ("success" == ret) {
				// 更新成功
				document.getElementById(dataId).innerHTML = carNo;
			} else if ("error" == ret) {
				alert("数据更新失败，请重新采集！");
			}
		}
		
		// 获取卡信息
		function getCardId(dataId) {
			try {
				var obj = document.getElementById('GenerateCardId');
				var connValue = obj.OpenComm();
				if (0 == connValue) {
					var cardId = obj.QueryCard();
					if (0 == cardId) {
						alert("未找到卡");
					} else {
						updateCardNoById(dataId, cardId);
					}
					obj.CloseComm();
				} else {
					alert("设备未连接上");
				}
			} catch (e) {
				alert("未知异常");
			}
		}

		// 采集链接
		function reWriteGatherLink(tdDiv, pid) {
			return '<a href="javascript:getCardId(' + pid + ');">'
					+ '采集' + '</a>';
		}
		
		//转换链接
		function reWriteLink(tdDiv,pid){
		     return '<a href="student_edit_form.shtml?studentInfo.student_id=' + pid + '">' + tdDiv +'</a>';
		 }

		//判断性别
		function reWriteSex(tdDiv){
			if(tdDiv == '0'){
				return '<s:text name="male" />';
			}else{
				return '<s:text name="female" />';
			}
		}

		//判断照片有无
		function rePhotoStatus(tdDiv){
			if(tdDiv == '1'){
				return '无';
			}else{
				return '有';
			}
		}
		
		//查询
		function searchStudent() {
			 document.getElementById('Below_new').style.display='none';
			jQuery('#gala').flexOptions({
				newp: 1,
				params: [{name: 'studentInfo.student_code', value: formatSpecialChar(jQuery('#student_code').val())},
				         {name: 'studentInfo.student_school', value: formatSpecialChar(jQuery('#student_school').val())},
						 {name: 'studentInfo.student_class', value: formatSpecialChar(jQuery('#student_class').val())},
				         {name: 'studentInfo.student_name', value: formatSpecialChar(jQuery('#student_name').val())}]
			});
			jQuery('#gala').flexReload();
		}

		function exportStudent(){
			if(confirm("确定要导出学生信息么？")) {
				document.getElementById('studentmanage_form').action="<s:url value='/student/exportStudent.shtml' />";
				document.getElementById('studentmanage_form').submit();
			}
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