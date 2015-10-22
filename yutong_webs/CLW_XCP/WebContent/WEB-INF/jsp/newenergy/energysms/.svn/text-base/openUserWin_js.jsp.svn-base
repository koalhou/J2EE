<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
var usersMsg = new Array();
var usersTxtMsg = "";
// 查询考核月度
function searchList() {
	jQuery('#usertbl').flexOptions({
		newp: 1,
		params: [
// 		         {name: 'organizidtreeid', value: jQuery('#organizidtreeid').val() },
		         {name: 'userName', value: formatSpecialChar(jQuery('#userName').val()) }]
	});
	jQuery('#usertbl').flexReload();
}

function reWriteLink(tdDiv,pid,row){
		var userName = row.cell[0];
		var phone = row.cell[1];
		var dv = "";
		if(usersTxtMsg.indexOf(pid) > -1){
			dv = '<a id=\''+pid+'\' href="javascript:removeArray(\''+userName+'\',\'' + pid + '\',\''+phone+'\')">删除短信提醒</a>';
		} else {
			dv = '<a id=\''+pid+'\' href="javascript:addArray(\''+userName+'\',\'' + pid + '\',\''+phone+'\')">加入短信提醒</a>';			
		}
	return dv;
}
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}



jQuery(function() {
	var gala = jQuery('#usertbl');
	var url = "../energySMS/queryUser.shtml";
	gala.flexigrid({
		url: url,
		dataType: 'json',
		height: 300,
		width:400,
		colModel : [
		    		{display: '姓名', name : 'USERNAME', width : calcColumn(0.05,80), sortable : true, escape:true, align: 'center'},
		    		{display: '手机号码', name : 'MOBLIE', width : calcColumn(0.04,80), sortable : true, escape:true,align: 'center'},
		    		{display: '用户类型', name : 'USERTYPE', width : calcColumn(0.04,80), sortable : true, escape:true,align: 'center'},
		    		{display: '操作', name : '', width : calcColumn(0.1,80), sortable : false, align: 'center',toggle:false,hide:false,process: reWriteLink}
		    	   ],
		    	sortname: 'USERTYPE',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 20,
				rpOptions : [20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	});
});

function addArray(userName,ids,phone){
	var txtMsg = ids+"!"+userName+"!"+phone;
	if(usersTxtMsg.indexOf(txtMsg) > -1){
		
	} else {
		usersMsg.push(txtMsg);
		usersTxtMsg = usersMsg.join(",");
	}
	var a = jQuery("#"+ids).parent();
	jQuery(a).html("");
	jQuery(a).html('<a id='+ids+' href="javascript:removeArray(\''+userName+'\',\'' + ids + '\',\''+phone+'\')">删除短信提醒</a>');
}

function removeArray(userName,pid,phone){
	var len = usersMsg.length;
	for(var i=0;i<len;i++){
		if(null != usersMsg[i] && usersMsg[i].indexOf(pid) > -1){
			delete usersMsg[i];
		}
	}
	usersTxtMsg = usersMsg.join(",");
	var a = jQuery("#"+pid).parent();
	jQuery(a).html("");
	jQuery(a).html('<a id='+pid+' href="javascript:addArray(\''+userName+'\',\'' + pid + '\',\''+phone+'\')">加入短信提醒</a>');
}


function parnetWinClose(){
	//top.cancelArt('smsUser');
	try{
		top.document.getElementById("smsSetcontent").childNodes[1].contentWindow.createMsg(usersTxtMsg);
	} catch(e) {
		top.document.getElementById("smsSetcontent").childNodes[0].contentWindow.createMsg(usersTxtMsg);
	}
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

</script>