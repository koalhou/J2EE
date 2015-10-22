function get_page_width() {
	return jQuery(document).width();
}
function get_page_height() {
	return jQuery(document).height();
}
var vacation = function(o) {
	this.init();
};

vacation.prototype = {
	search:function () {
		jQuery('#zzlist').flexOptions({
			newp: 1,
			params: [{name:'orgID',value:$('#orgID').val()},
			         {name:'year',value:$("#year").find("option:selected").val()},
			         {name:'month',value:$("#month").find("option:selected").val()}]
		});
		jQuery('#zzlist').flexReload();
	},
	init:function() {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		 } catch (e) {
			try {
			    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e2) {
			    xmlHttp = false;
			}
		 }

		if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
			xmlHttp = new XMLHttpRequest();
		}
		xmlHttp.open("GET", "null.txt", false);
		xmlHttp.setRequestHeader("Range", "bytes=-1");
		xmlHttp.send(null);

		severtime=new Date(xmlHttp.getResponseHeader("Date"));

		
		$('#year').val(severtime.getFullYear());
		if(severtime.getMonth()+1<10){
			$('#month').val('0'+(severtime.getMonth()+1));
		}else{
			$('#month').val(severtime.getMonth()+1);
		}
		jQuery('#zzlist').flexigrid({
			url: 'list.shtml',
			params: [{name:'orgID',value:$('#orgID').val()},
			         {name:'year',value:$("#year").find("option:selected").val()},
			         {name:'month',value:$("#month").find("option:selected").val()}],
			dataType: 'json',
			height: 575-190,
			width: get_page_width()-80,
			colModel : [
			    		{display: '序号', height:15,name : '', width : 30, sortable : false, align: 'center'},
			    		{display: '组织', name : 'orgName',height:30, width : 120, sortable : true, align: 'center'},
			    		{display: '年份', name : 'year', width : 60, sortable : true, align: 'center'},
			    		{display: '月份', name : 'month', width : 60, sortable : true, align: 'center'},
			    		{display: '放假时间', name : '', width : 500, sortable : false, align: 'center',process: sortDays},
			    		{display: '', name : '', width : 60, sortable : false, align: 'center',hide:true},
			    		{display: '', name : '', width : 60, sortable : false, align: 'center',process: modifyHtml},
			    		{display: '', name : '', width : 60, sortable : false, align: 'center',process: delHtml}
			    	],
			    	sortname:'create_date',
			    	sortorder: 'desc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
					autoload:true,
			    	useRp: true,
			    	rp:10,
			    	gridId:'vacationFlexGrid',
					rpOptions : [10,20,50,100],
			    	showTableToggleBtn: true 
		});
	}
};

function addVacation(){
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
	 } catch (e) {
		try {
		    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e2) {
		    xmlHttp = false;
		}
	 }

	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest();
	}
	xmlHttp.open("GET", "null.txt", false);
	xmlHttp.setRequestHeader("Range", "bytes=-1");
	xmlHttp.send(null);

	severtime=new Date(xmlHttp.getResponseHeader("Date"));

	//获取服务器日期
	var year=severtime.getFullYear();
	var month=severtime.getMonth()+1;
	
	     
	//var year=new Date().getFullYear();
	//var month=new Date().getMonth()+1;
	if(month<10){
		month='0'+month;
	}
	$('#orgIDgoto').val('');
	$('#yeargoto').val(year);
	$('#monthgoto').val(month);
	$('#collectiongoto').val('');
	$('#action').val('add');
	$('#gotoForm').submit();
	
}
function sortDays (tdDiv,pid){
	var ss=tdDiv.innerHTML.split(',');
	ss.sort();
	for ( var s in ss) {
		ss[s]=parseInt(ss[s],10)+'号';
	}
	 tdDiv.innerHTML =ss.toString();
}
function modifyHtml (tdDiv,pid){
	tdDiv.innerHTML = "<a class=\"modifyLink\" href=\"javascript:void(0)\" onclick=\"modifyVacation(this)\">修改</a>";
}
function delHtml (tdDiv,pid){
	tdDiv.innerHTML = "<a class=\"delLink\" href=\"javascript:void(0)\" onclick=\"delVacation(this)\">删除</a>";
}
function  setOrg(id,name,children){
	$('#orgID').val(id);
	$('#orgName').val(name);
} 
function modifyVacation(obj){
	var line =$(obj).parent().parent().parent().children('td');
	var year=line.eq(2).find('div').text();
	var month=line.eq(3).find('div').text();
	var orgID=line.eq(5).find('div').text();
	$('#orgIDgoto').val(orgID);
	$('#yeargoto').val(year);
	$('#monthgoto').val(month);
	$('#action').val('modify');
	$('#gotoForm').submit();

}
function delVacation(obj){
	var line =$(obj).parent().parent().parent().children('td');
	var year=line.eq(2).find('div').text();
	var month=line.eq(3).find('div').text();
	var orgID=line.eq(5).find('div').text();
	if(confirm('你确定要删除该条记录吗？')){
		jQuery.ajax({
			type: 'POST',  
			url: 'del.shtml',	
			data: {orgID: orgID,year:year,month:month},	
			dataType: 'json',
			success: function(data) {
				if(data.returns=="success"){
					jQuery('#zzlist').flexReload();
					alert("记录删除成功！");
				}else{
					alert("记录删除失败！");
				}
			}  
		});
	}
}
function cancelWinClosed(winID){
	art.dialog.get(winID).close();
	jQuery('#zzlist').flexReload();
}


var vacationObj=null;
jQuery(document).ready(function(){
	vacationObj = new vacation();
	$('#orgName').click(function(){
		art.dialog.open("orgs.shtml",{
			title:"选择组织",
			width :260,
			height:300,
			id: 'treeOrgsID',
			skin: 'aero',
			limit: true,
			lock: true,
			drag: true,
			fixed: false,
			yesFn: function(iframeWin, topWin){}});
	});
	

	jQuery('#content').mk_autoresize({
	    width_include: '.flexigrid',
	    width_offset: 25
	  });
	jQuery('#content').mk_autoresize({  
	   height_include: '.bDiv',
	   height_offset: 195
	 });
});