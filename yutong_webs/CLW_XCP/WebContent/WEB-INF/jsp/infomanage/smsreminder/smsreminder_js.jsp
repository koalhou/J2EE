<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript">
	jQuery(function() {
		 var gala = jQuery('#gala');
		 gala.flexigrid({
		  url: '<s:url value="/smsreminderGrid/messageBrowse.shtml" />', 
		  dataType: 'json',    //json格式
		  height: 375,
		  width:0,
		  colModel : [ 
				{display: '序号', name : 'rowNumber', width : calcColumn(0.02,40,0), sortable : false, align: 'center'},
				{display: '姓名', name : 'STU_NAME', width : calcColumn(0.05,80,0), sortable : true, align: 'center',escape:true},
		        {display: '学号',name : 'STU_CODE', width : calcColumn(0.08,100,0), sortable : true, align: 'center',escape:true}, 
		        {display: '学校', name : 'STU_SCHOOL', width : calcColumn(0.1,120,0), sortable : true, align: 'center',escape:true},
		        {display: '班级', name : 'STU_CLASS', width : calcColumn(0.1,120,0), sortable : true, align: 'center',escape:true},
		        {display: '开通时间', name : 'ORDER_TIME', width : calcColumn(0.1,120,0), sortable : true, align: 'center'},
		        {display: '手机号码1', name : 'PHONE1', width : calcColumn(0.1,120,0), sortable : true, align: 'center'},
		        {display: '手机号码2', name : 'PHONE2', width : calcColumn(0.1,120,0), sortable : true, align: 'center'},
		        {display: '手机号码3', name : 'PHONE3', width : calcColumn(0.1,120,0), sortable : true, align: 'center'},
		        {display: '', name : '', width : calcColumn(0.05,80,0), sortable : false, align: 'center', process: reWriteLink}
		        ],
		       sortname: 'ORDER_TIME',
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		     });
		 jQuery(window).resize(function(){
			  testWidthHeight();
		 });
		 jQuery(window).load(function (){
			  testWidthHeight();
		 });
	});
		//转换链接
		function reWriteLink(tdDiv,pid){
			<s:if test="#session.perUrlList.contains('111_3_3_1_5_3')">
		      return '<a href="configSmsByStuId.shtml?studentInfo.student_id=' + pid + '">' + '配置' +'</a>';
		    </s:if>
		    <s:else>
		      return "无配置权限"; 
		    </s:else>
		 }

		//查询
		function searchSms() {
			if((jQuery('#queryStartTime').val() != "" && jQuery('#queryEndTime').val() == "") ||
					(jQuery('#queryStartTime').val() == "" && jQuery('#queryEndTime').val() != "")	 ) {
				alert("请将查询时间段填写完整！");
				return;
			}
			document.getElementById('Below_new').style.display='none';
			jQuery('#gala').flexOptions({
				newp: 1,
				params: [{name: 'smsReminderInfo.studentCode', value: formatSpecialChar(jQuery('#studentCode').val())},
				         {name: 'smsReminderInfo.studentSchool', value: formatSpecialChar(jQuery('#studentSchool').val())},
						 {name: 'smsReminderInfo.studentClass', value: formatSpecialChar(jQuery('#studentClass').val())},
				         {name: 'smsReminderInfo.studentName', value: formatSpecialChar(jQuery('#studentName').val())},
						 {name: 'smsReminderInfo.queryStartTime', value: jQuery('#queryStartTime').val()},
				         {name: 'smsReminderInfo.queryEndTime', value: jQuery('#queryEndTime').val()}]
			});
			jQuery('#gala').flexReload();
		}

		//获取页面高度
		function get_page_height() {
			var min_height = 0;
			try {
				min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
			}catch(e) {
				min_height = 700;
			}
			  return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
		}
		//获取页面宽度
		function get_page_width() {
			var min_width = 0;
			try {
				min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
			} catch(e) {
			   min_width = 1256;
			}
			  return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
		}
		 
		//计算控件宽高
		function testWidthHeight(){
		 
			var h = get_page_height();
			 var w = get_page_width();
			 jQuery(".flexigrid").width(w-23);
			 jQuery(".bDiv").height(h-330);

			 jQuery(window).mk_autoresize({
			       height_include: '#content',
			       height_exclude: ['#header', '#footer'],
			       height_offset: 0,
			       width_include: ['#header', '#content', '#footer'],
			       width_offset: 0
			    });
			 h = get_page_height();
			 w = get_page_width();
			 jQuery(".flexigrid").width(w-23);
			 jQuery(".bDiv").height(h-330);
		}
</script>