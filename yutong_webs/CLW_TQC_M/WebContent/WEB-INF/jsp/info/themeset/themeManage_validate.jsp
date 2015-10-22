<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type="text/javascript">
  var count = 0;
  /** 打开企业选择子页面 **/
  function openEnterpriseBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/enterpriseinit.shtml' />" + "?count=" + (++count) + "&flag=2",
	                                 self,
	                                 "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }
	//转换链接
  function reWriteLink(tdDiv,pid){
	tdDiv.innerHTML = '<a href="../paramset/updatethemeset.shtml?id='+get_cell_text(pid, 5)+'">修改</a>';//
  }
	function delLink(tdDiv,pid){
		tdDiv.innerHTML = '<a href="../paramset/delthemeset.shtml?id='+get_cell_text(pid, 6)+'">删除</a>';//
	}

  function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
  }

  jQuery(function() {
		var userList = jQuery('#userMangeList');
		userList.flexigrid({
			url: '<s:url value="/paramset/themesetList.shtml" />',
			dataType: 'json',
			height: '375',
			width: '2000',
			colModel : [
			    		{display: '<s:text name="list.number" />', width : 30, sortable : false, align: 'left'},
			    		{display: '用户名', width : 150, sortable : true, align: 'left'},
			    		{display: '登陆名', width : 120, sortable : true, align: 'left'},
			    		{display: '企业', width : 150, sortable : true, align: 'left'},
			    		{display: '企业编码', width : 150, sortable : true, align: 'left'},
			    		{display: '', width : 80, sortable : true, align: 'left', process: reWriteLink},
			    		{display: '', width : 80, sortable : true, align: 'left', process: delLink}
			    	],
			    	   		
			    	sortname: 'user_id',
			    	sortorder: 'asc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
			    	useRp: true,
			    	rp:10,	
					rpOptions : [10,20,50,100],// 可选择设定的每页结果数
			    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		});
	});

    function searchList() {
    	alert(jQuery('#entipriseId').val());
    	jQuery('#empDiv').css("display","none");
		jQuery('#userMangeList').flexOptions({
			newp: 1,
			params: [{name: 'loginName', value: jQuery('#loginName').val() }, 
			         {name: 'enterpriseId', value: jQuery('#entipriseId').val()}]
		});
		jQuery('#userMangeList').flexReload();
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
		jQuery(".bDiv").height(h-340); 
    }
    //页面自适应
    (function (jQuery) {
	    jQuery(window).resize(function(){
	    changeWidthHeight();
	    jQuery('#userMangeList').fixHeight();
	});
    jQuery(window).load(function (){
    	changeWidthHeight();
    	jQuery('#userMangeList').fixHeight();
    });
    })(jQuery);
</script>