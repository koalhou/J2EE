<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function conditionselectSubmit(){
	document.conditionselectform.submit();
}
// 查询考核月度
function searchList() {
	jQuery('#usertbl').flexOptions({
		newp: 1,
		params: [{name: 'organizidtreeid', value: jQuery('#organizidtreeid').val() },
		         {name: 'loginName', value: formatSpecialChar(jQuery('#loginName').val()) },
		         {name: 'validFlag', value: jQuery('#validFlag').val() }]
	});
	jQuery('#usertbl').flexReload();
}


function detialbrwose(userId){
	var form = document.getElementById('userselect');
	form.action = 'userupdateinitAction.shtml?userID=' + userId;
	form.submit();
}
function reWriteLink(tdDiv,pid){
	//tdDiv.innerHTML = '<a href="javascript:detialbrwose(\'' + pid + '\')">' + tdDiv.innerHTML +'</a>';
	return '<a href="javascript:detialbrwose(\'' + pid + '\')">' + tdDiv+'</a>';
	}

function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

function reOPTRight(tdDiv,pid,row){

	  var _UrlList = document.getElementById('UrlList'); 
	      //get_cell_text(pid,8)=='0' && get_cell_text(pid,9)!='5'
		  if(row.cell[8]=='0' && row.cell[9]!='5'){
			  if(_UrlList.value=='true'){
			    //tdDiv.innerHTML = '<a href="../userpkg/resetPwdBefore.shtml?userId='+ pid +'">重置模块密码</a>';
			    return '<a href="../userpkg/resetPwdBefore.shtml?userId='+ pid +'">重置模块密码</a>';
			  }else{
				  //tdDiv.innerHTML=" <span style= 'color:green'>无权限</span> ";
				 return " <span style= 'color:green'>无权限</span> ";
			  }	
		  }else{
			  //tdDiv.innerHTML = '无模块密码';
			  return '无模块密码';
		  }		  
} 

function reOPTRight2(tdDiv,pid){

	  var _UrlList2 = document.getElementById('UrlList2'); 
      if(_UrlList2.value=='true'){
		 //tdDiv.innerHTML = '<a href="../userpkg/resetPwdBeforeP.shtml?userId='+ pid +'">重置用户密码</a>';
		 return '<a href="../userpkg/resetPwdBeforeP.shtml?userId='+ pid +'">重置用户密码</a>';
	  }else{
		 //tdDiv.innerHTML=" <span style= 'color:green'>无权限</span> "; 
		 return " <span style= 'color:green'>无权限</span> ";
	  }	
} 

jQuery(function() {
	var gala = jQuery('#usertbl');
	var flag = jQuery('#validFlag').val();
	//alert(flag);
	var url = "../userpkg/userbrowse.shtml?flag="+flag;
	gala.flexigrid({
		url: url,
		dataType: 'json',
		height: 300,
		width:2000,
		colModel : [

		    		{display: '登录名', name : 'LOGIN_NAME', width : calcColumn(0.04,80), sortable : true, align: 'center', escape:true, process: reWriteLink},
		    		{display: '用户类型', name : 'USER_TYPE', width : calcColumn(0.04,80), sortable : true, align: 'center'},
		    		{display: '姓名', name : 'USER_NAME', width : calcColumn(0.05,80), sortable : true, escape:true, align: 'center'},
		    		{display: '性别', name : 'USER_SEX', width : calcColumn(0.02,40), sortable : true, align: 'center'},
		    		{display: '手机', name : 'USER_MOBILE', width : calcColumn(0.04,80), sortable : true, escape:true,align: 'center'},
		    		{display: '<s:text name="enterprise.info.EMAIL" />', name : 'USER_EMAIL', width : calcColumn(0.12,120), sortable : true, align: 'center'},
		    		{display: '组织', name : 'ORGANIZATIONNAME', width : calcColumn(0.22,200), sortable : true, escape:true,align: 'center'},
		    		{display: '状态', name : 'VALID_FLAG', width : calcColumn(0.08,80), sortable : true, align: 'center'},
		    		{display: '模块密码状态', name : 'STUDENT_FLAG', width : calcColumn(0.1,80), sortable : true, align: 'center',toggle:false,hide:true},
		    		{display: '用户类型', name : '', width : calcColumn(0.1,80), sortable : false, align: 'center',toggle:false,hide:true},	    	
		    		{display: '用户密码重置', name : '', width : calcColumn(0.1,80), sortable : false, align: 'center', process:reOPTRight2},
		    		{display: '模块密码重置', name : '', width : calcColumn(0.1,80), sortable : false, align: 'center', process:reOPTRight}		    		
		    	   ],
		    		    		
		    	sortname: 'LOGIN_NAME',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 10,
		    	
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		    	//getQuery :getQuery
	});
});


//页面自适应
$(function(){
	jQuery(window).resize(function(){
		testWidthHeight();
	});
	testWidthHeight();
});
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

function  autoWidthHeight(){
	 var h = get_page_height();
	 var w = get_page_width();
	 jQuery(".flexigrid").width(w-25);
	 //jQuery("#reportTab").height(h-100);
	 jQuery(".bDiv").height(h-320);
	
}
//计算控件宽高
function testWidthHeight(){
	
	 autoWidthHeight();
	
	 jQuery(window).mk_autoresize({
			height_include: '#content',
			height_exclude: ['#header', '#footer'],
			height_offset: 0,
			width_include: ['#header', '#content', '#footer'],
			width_offset: 0});
	 autoWidthHeight();
}

(function(){
    var d = art.dialog.defaults;
    
    // 按需加载要用到的皮肤，数组第一个为默认皮肤
    // 如果只使用默认皮肤，可以不填写skin
    d.skin = ['aero'];
    
    // 支持拖动
    d.drag = true;
    
    // 超过此面积大小的对话框使用替身拖动
    d.showTemp = 100000;
})();

function openorganizidtree(){
	
	//alert("vin:" +vin);
	art.dialog.open("<s:url value='/usm/usermanagetreeAction.shtml' />",
			//userupdatetreeAction.shtml?userID=" + userid,
			{
			width :260,
			height:300,
			    id: 'treeID',
				title: ' ',
				skin: 'aero',
				limit: true,
				lock: true,
				drag: true,
				fixed: false,
				
				follow: document.getElementById('organizname'),
				yesFn: function(iframeWin, topWin){
		        	//对话框iframeWin中对象
		        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
		        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
		        	//当前页面中对象
		            var topOrgName =  window.document.getElementById('organizname');
		            var topOrgID = window.document.getElementById('organizidtreeid');
		            //赋值
		        	if (topOrgName) topOrgName.value = orgNameValue;
		        	if (topOrgID) topOrgID.value = orgIDValue;
		    	}
		    /*noFn:function(iframeWin, topWin){

			  }*/
			});
	
}
</script>