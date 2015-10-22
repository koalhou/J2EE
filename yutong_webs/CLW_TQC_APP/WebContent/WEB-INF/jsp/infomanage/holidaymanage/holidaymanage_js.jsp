<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
	jQuery(function() {
		 var gala = jQuery('#gala');
		 gala.flexigrid({
		  url: '<s:url value="/holidayGrid/holidayList.shtml" />', 
		  dataType: 'json',    //json格式
		  height: 375,
		  width:0,
		  colModel : [ 
				{display: '序号', name : 'rowNumber', width : calcColumn(0.02,40,0), sortable : false, align: 'center'},
				{display: '姓名', name : 'STU_NAME', width : calcColumn(0.05,80,0), sortable : true, align: 'center', escape:true},
		        {display: '学号',name : 'STU_CODE', width : calcColumn(0.05,100,0), sortable : true, align: 'center', escape:true}, 
		        {display: '乘坐车辆', name : 'VEHICLE_LN', width : calcColumn(0.07,150,0), sortable : true, align: 'center', escape:true},
		        {display: '行驶线路', name : 'ROUTE_NAME', width : calcColumn(0.07,150,0), sortable : true, align: 'center', escape:true},
		        {display: '请假开始时间', name : 'START_TIME', width : calcColumn(0.1,120,0), sortable : true, align: 'center'},
		        {display: '请假结束时间', name : 'END_TIME', width : calcColumn(0.1,120,0), sortable : true, align: 'center'},
		        {display: '实际销假时间', name : 'MODIFY_TIME', width : calcColumn(0.1,120,0), sortable : true, align: 'center'},
		        {display: '请假原因', name : 'REASON', width : calcColumn(0.06,120,0), sortable : true, align: 'center',escape:true},
		        {display: '状态', name : 'FLAG', width : calcColumn(0.04,100,0), sortable : true, align: 'center'},
		        {display: '', name : '', width : calcColumn(0.03,80,0), sortable : false, align: 'center', process: reWriteLink}
		        ],
		       sortname: 'START_TIME',
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
		
		function reWriteLink(tdDiv,pid){
			<s:if test="#session.perUrlList.contains('111_3_3_8_3')">
		      return '<a href="holiday_edit_form.shtml?holidayInfo.holiday_id=' + pid + '">' + '修改' +'</a>';
		    </s:if>
			<s:else>
		    return "无修改权限"; 
			</s:else>
		 }
		//查询
		function searchHoliday() {
			 document.getElementById('Below_new').style.display='none';
			jQuery('#gala').flexOptions({
				newp: 1,
				params: [{name: 'holidayInfo.student_code', value: formatSpecialChar(jQuery('#student_code').val())},
				         {name: 'holidayInfo.student_name', value: formatSpecialChar(jQuery('#student_name').val())},
				         {name: 'holidayInfo.holiday_flag', value: formatSpecialChar(jQuery('#holiday_flag').val())}]
			});
			jQuery('#gala').flexReload();
		}

		//获取页面高度
		function get_page_width() {
		  var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
		  return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
		}
		
		//获取页面高度
		function get_page_height() {
		  var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
		  return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
		}
		 
		//计算控件宽高
		function testWidthHeight(){
			var h = get_page_height();
			var w = get_page_width();
			jQuery(".flexigrid").width(w-23);
			 //jQuery("#reportTab").height(h-100);
			jQuery(".bDiv").height(h-320);

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
			 //jQuery("#reportTab").height(h-100);
			jQuery(".bDiv").height(h-320);
		}
</script>