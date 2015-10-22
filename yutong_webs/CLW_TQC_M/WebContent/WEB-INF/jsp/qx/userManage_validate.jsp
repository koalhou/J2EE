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
  /** 打开角色选择子页面 **/
  function openRoleBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/chooseRole.shtml' />" + "?count=" + (++count) + "&flag=3",
	                                 self,
	                                 "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }

  /** 导出用户信息 **/
  function exportUserInfo(){
    if(confirm("<s:property value="getText('confirm.export.file')" />")) {
    	$('user_form').action="<s:url value='/qx/exportUserInfo.shtml' />";
    	$('user_form').submit();
    }
  }

	//转换链接
  function reWriteLink(tdDiv,pid){
	if(get_cell_text(pid, 11)=="0"){
		tdDiv.innerHTML = '<a href="../qx/userDetailQuery.shtml?userId='+ pid +'&applyId='+ get_cell_text(pid, 11) +'">' + tdDiv.innerHTML +'</a>';
	}
  }

  function reStatus(tdDiv,pid){
	if(get_cell_text(pid, 9)=="2"){
		tdDiv.innerHTML=" <span style= 'color:red'><s:text name='user.forbid'/></span> "; 
	}else{
		tdDiv.innerHTML=" <span style= 'color:green'><s:text name='user.normal'/></span> "; 
	}
  }

  function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
  }

  function reOPTRight(tdDiv,pid){

	  var _UrlList = document.getElementById('UrlList'); 

	  if(_UrlList.value && get_cell_text(pid, 11)!=null){
		  tdDiv.innerHTML = '<a href="../qx/resetPwdBefore.shtml?userId='+ pid +'"><s:text name="user.reset.password"/> </a>';
	  }else{
		  tdDiv.innerHTML=" <span style= 'color:green'><s:text name='user.no.config'/></span> "; 
	  }	 
  }
  
  jQuery(function() {
		var userList = jQuery('#userMangeList');
		userList.flexigrid({
			url: '<s:url value="/qxf/userManageList.shtml" />',
			dataType: 'json',
			height: '375',
			width: '2000',
			colModel : [
			    		{display: '<s:text name="list.number" />', name : 'rowNumber', width : 30, sortable : false, align: 'left'},
			    		{display: '<s:text name="user.name" />', name : 'loginName', width : 60, sortable : true, align: 'left', process: reWriteLink},
			    		{display: '<s:text name="user.type" />', name : 'userType', width : 80, sortable : true, align: 'left'},
			    		{display: '<s:text name="operationsInfo.info.enterprise_code" />', name : 'entipriseCode', width : 80, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.enterprise" />', name : 'entipriseName', width : 150, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.role" />', name : 'roleName', width : 120, sortable : true, align: 'left'},
			    		{display: '<s:text name="user.real.name" />', name : 'userName', width : 80, sortable : true, align: 'left'},
			    		{display: '<s:text name="user.cell" />', name : 'userCell', width : 60, sortable : true, align: 'left'},
			    		{display: '<s:text name="user.email" />', name : 'userEmail', width : 64, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.status" />', name : 'validFlag', width : 30, sortable : true, align: 'left', process: reStatus},
			    		{display: '<s:text name="common.createtime" />', name : 'creatTime', width : 85, sortable : true, align: 'left',hide:true},
			    		{display: '<s:text name="common.config" />', name : '', width : 60, sortable : false, align: 'left', process:reOPTRight}
			    		],
			    	   		
			    	sortname: 'entipriseCode,roleName',
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
    	
    	jQuery('#empDiv').css("display","none");
		jQuery('#userMangeList').flexOptions({
			newp: 1,
			params: [{name: 'userName', value: jQuery('#userName').val() }, 
			         {name: 'enterpriseId', value: jQuery('#entipriseId').val()},
			 		 {name: 'sysId', value: jQuery('#sysId').val()},
			 		 {name: 'roleId', value: jQuery('#roleId').val()},
			 		 {name: 'validFlag', value: jQuery('#validFlag').val()}]
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