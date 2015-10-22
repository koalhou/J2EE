<%@page language="java" contentType="text/html;charset=utf-8"%>

<script type="text/javascript">
		jQuery(function() {
		 var gala = jQuery('#gala');
		 gala.flexigrid({
		  url: '<s:url value="/feedback/feedBackList.shtml" />',
		  dataType: 'json',    //json格式
		  height: 375,
		  width:0,
		  colModel : [
				//{display: '<input id="carChoiceAll" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', name : '', width : get_page_width()*0.02, process:reWriteCheckBox},
				{display: '序号', name : '', width : 35, sortable : false, align: 'center'},
				{display: '内容', name : "", width : calcColumn(0.175,60,238), sortable : false, align: 'center', escape:true, process: reWriteLink},
		        {display: '员工号',name : "", width : calcColumn(0.075,60,238), sortable : true, align: 'center', escape:true}, 
				{display: '姓名', name : "", width : calcColumn(0.075,60,238), sortable : true, align: 'center', escape:true},
		        {display: '反馈时间', name : 'SUGGEST_DATE', width : calcColumn(0.175,60,238), sortable : true, align: 'center'},
		        {display: '状态', name : "type", width : calcColumn(0.1,60,238), sortable : true, escape:true, align: 'center',process: reWrite_status,hide: true}
		        //{display: '', name : "status", width : calcColumn(0.1,60,238), sortable : false, hide: true, align: 'center'}
		        //{display: '', name : '', width : calcColumn(0.05,60,238), sortable : false, align: 'center',process: reWriteDelLink}
		        ],
		       //sortname: "MODIFY_TIME desc,NLSSORT(STU_NAME,'NLS_SORT = SCHINESE_PINYIN_M')",
		       sortname: "SUGGEST_DATE",
		       sortorder: 'desc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true,   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		       autoload: false,
		       onSuccess:function(){
	               //jQuery("input[name='carChoiceAll']").removeAttr("checked");  
               }
		     });

		 	jQuery(window).wresize(function(){
			  	testWidthHeight();
			});
			jQuery(window).load(function (){
				testWidthHeight();
 		    });
			searchMsg();
		});

		
		//转换链接
		function reWriteLink(tdDiv,pid,row){
// 			style="border: 0px;background: #EEEEEE;"
			if(tdDiv.length>40) {
				tdDiv=tdDiv.substr(0,40);
			}
			var tmpVal = jQuery(row)[0].cell[4];
			return '<div class="cs"><a href="feedbackDetail.shtml?feedBackMsg.suggestId=' + pid +'">' 
					+ tdDiv +'</a></div>';
		}
		//
		function reWrite_status(tdDiv,pid){
			var tmpStr = "";
			if(tdDiv == "0"){
				tdDiv = "新建";
			} else if(tdDiv == "1"){
				tdDiv = "已回复";
			}
			return tdDiv;
		}

		//查询
		function searchMsg() {
			jQuery('#gala').flexOptions({
				newp: 1,
				params: [{name: 'empName', value: formatSpecialChar(jQuery('#empName').val())},
				         {name: 'feedBackDate', value: jQuery('#feedBackDate').val()}
// 						 {name: 'feedBackStatus', value: formatSpecialChar(jQuery('#feedBackStatus').val())}
				         ]
			});
			jQuery('#gala').flexReload();
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