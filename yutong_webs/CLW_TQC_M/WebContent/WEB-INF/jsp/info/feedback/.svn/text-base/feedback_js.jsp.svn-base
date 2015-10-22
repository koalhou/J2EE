<%@page language="java" contentType="text/html;charset=utf-8"%>

<script type="text/javascript">
		jQuery(function() {
		 var gala = jQuery('#gala');
		 gala.flexigrid({
		  url: '<s:url value="/feedback/feedBackList.shtml" />',
		  dataType: 'json',    //json格式
		  params: [  {name: 'empName', value: jQuery('#empName').val()},
			         {name: 'beginDate', value: jQuery('#beginDate').val()},
			         {name: 'endDate', value: jQuery('#endDate').val()},
			         {name: 'feedBackStatus', value: jQuery('#statues').val()}
			      ],
		  height: 375,
		  width:1100,
		  colModel : [
				{display: '序号', name : 'num', width : 35, sortable : false, align: 'center'},
				{display: '内容', name : "content", width : 200, sortable : false, align: 'center',  process: reWriteLink},
		        {display: '员工号',name : "emp_code", width : 100, sortable : true, align: 'center'}, 
				{display: '反馈人', name : "emp_name", width : 130, sortable : true, align: 'center'},
				{display: '手机号', name : "ygb_tel", width : 130, sortable : true, align: 'center'},
		        {display: '反馈时间', name :'SUGGEST_DATE', width : 150, sortable : true, align: 'center'},
		        {display: '状态', name : "TYPE", width : 130, sortable : true, align: 'center',process:reWriteStatus},
		        {display: '回复人', name : "operator", width : 130, sortable : true, align: 'center'},
		        {display: '回复时间', name : "operate_time", width : 130, sortable : true, align: 'center'},
		        {display: '操作', name : "", width : 130, align: 'center',process:reWriteLink1}
		        ],
		       sortname: "",
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

			searchMsg();
		});
		function reWriteStatus(tdDiv,pid){
			if(tdDiv.innerHTML=='1'){
				tdDiv.innerHTML='已回复';
			}else{
				tdDiv.innerHTML='未回复';
			}
		}
		function reWriteLink1(tdDiv,pid){
			var showStr='<a href="feedbackOperate.shtml?feedBackId=' + pid +'">立即回复</a>';
			if(tdDiv.innerHTML=='1'){
				showStr='<a href="feedbackUpdateDetail.shtml?feedBackId=' + pid +'">查看详情</a>';
			}
			tdDiv.innerHTML =  showStr;
		}
		//转换链接
		function reWriteLink(tdDiv,pid){
			if(tdDiv.length>40) {
				tdDiv=tdDiv.substr(0,40);
			}
			tdDiv.innerHTML =  '<a href="feedbackDetail.shtml?feedBackMsg.suggestId=' + pid +'">' + tdDiv.innerHTML +'</a>';
		}

		//查询
		function searchMsg() {
			jQuery('#gala').flexOptions({
				newp: 1,
				params: [{name: 'empName', value: jQuery('#empName').val()},
				         {name: 'beginDate', value: jQuery('#beginDate').val()},
				         {name: 'endDate', value: jQuery('#endDate').val()},
				         {name: 'feedBackStatus', value: jQuery('#statues').val()}
				         //{name: 'feedBackDate', value: jQuery('#feedBackDate').val()}
				         ]
			});
			jQuery('#gala').flexReload();
		}
		//导出登陆信息
		function exportFeedBackList() {
			if(jQuery('#gala').find("td").length == 0){
				alert("没有要导出的信息!");
				return;	
			}
			if (confirm("确定要导出问题反馈信息么？")) {
				document.getElementById('form1').action = "<s:url value='/feedback/exportFeedList.shtml' />";
				document.getElementById('form1').submit();
			}
		}
		//获取页面高度
	    function get_page_height() {
			var height = 0;
			
			if (jQuery.browser.msie) {
			    height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
			} else {
			    height = self.innerHeight;
			}
			return height;
	    }
	    //获取页面宽度
	    function get_page_width() {
			var width = 0;
			if(jQuery.browser.msie){ 
				width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
			} else { 
				width = self.innerWidth; 
			} 
			return width;
	    }
	    //计算控件宽高
	    function changeWidthHeight(){
			var h = get_page_height();
			var w = get_page_width();
			jQuery(".flexigrid").width(w-20);
			jQuery(".bDiv").height(h-380); 
	    }
	    //页面自适应
	    (function (jQuery) {
		    jQuery(window).resize(function(){
		    changeWidthHeight();
		    jQuery('#gala').fixHeight();
		});
	    jQuery(window).load(function (){
	    	changeWidthHeight();
	    	jQuery('#gala').fixHeight();
	    });
	    })(jQuery);
		
</script>