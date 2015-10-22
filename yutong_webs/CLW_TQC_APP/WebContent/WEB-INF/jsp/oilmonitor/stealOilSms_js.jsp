<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
	(function($){
		$.dialog.defaults.skin = ['aero'];// 预缓存皮肤，数组第一个为默认皮肤
	})(art);
	//初始化列表
	jQuery(function() {
		var gala = jQuery('#gala');
		gala.flexigrid({
			url: '<s:url value="/ftlyInfoNew/stealOilSmsList.shtml" />',
			dataType: 'json',
			height: 150,
			width :550,
			colModel : [
						{display: '<input id="carChoiceAll" style="_margin-top:-3px;" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', name : '', width : 20, process:reWriteCheckBox,align: 'center'},
						{display: '序号', name : 'ROWNUMBER', width : 20, sortable : false, align: 'center'},
						{display: '姓名', name : "NLSSORT(STU_NAME,'NLS_SORT = SCHINESE_PINYIN_M')", width : 180, sortable : true, align: 'center', process: reWriteEditLink},
			    		{display: '手机号', name : 'TELEPHONE', width : 150, sortable : true, align: 'center', escape:true},
			    		{display: '所属组织', name : 'FULL_NAME', width : 520, sortable : false, align: 'center', escape:true},
		  				{display: '', name : 'STU_ID', width : 150, sortable : true, align: 'center',toggle:false, hide : true},
		  				{display: '', name : 'MODIFIE_TIME', width : 100, sortable : true, align: 'center',toggle:false, hide : true}
		  				<s:if test="#session.perUrlList.contains('111_3_2_3_3')">
		  				,{display: '', name : '', width : 150, sortable : false, align: 'center', process: reWriteDelLink}
		  				</s:if>
			    		],
			    	sortname: 'MODIFIE_TIME',
			    	sortorder: 'desc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
			    	useRp: true,
			    	rp: 20,
					rpOptions : [10,20,50,100 ],// 可选择设定的每页结果数
			    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		});
	});
	
	function openorganizidtree(){
		art.dialog.open("<s:url value='/usm/usermanagetreeAction.shtml' />?rad="+Math.random(),
				{
				width :260,
				height:300,
				id: 'treeOID',
				title: ' ',
				skin: 'aero',
				limit: true,
				lock: true,
				drag: true,
				fixed: false,
					follow: document.getElementById('organizationName'),
					yesFn: function(iframeWin, topWin){
			        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
			        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
			        	//当前页面中对象
			            var topOrgName =  window.document.getElementById('organization_name');
			            var topOrgID = window.document.getElementById('organization_id_s');
			            //赋值
			        	if (topOrgName) topOrgName.value = orgNameValue;
			        	if (topOrgID) topOrgID.value = orgIDValue;
			    	}
				});
	}
	
    //查询
	function searchList() {
		jQuery("input[name='carChoiceAll']").removeAttr("checked"); 
		jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'stealOilSmsInfo.stu_name', value: formatSpecialChar(jQuery('#stu_name').val())},
		         {name: 'stealOilSmsInfo.stu_id', value: jQuery('#stu_id').val()},
		 		 {name: 'stealOilSmsInfo.telephone', value: formatSpecialChar(jQuery('#telephone').val())},
		         {name: 'stealOilSmsInfo.organization_id_s', value: jQuery('#organization_id_s').val()}
		 		]
		});
		jQuery('#gala').flexReload();
	}

	//每行修改链接
	function reWriteEditLink(tdDiv,pid,row){
		return '<a href="../ftlyInfoNew/editStealOilSms.shtml?stealOilSmsInfo.stu_id='+row.cell[5]+'">'+tdDiv+'</a>';
	}
	//每行删除链接
	function reWriteDelLink(tdDiv,pid,row){
	  	return '<a href="#" onclick="deleteStealOilSms(\''+row.cell[5]+'\')">删除</a>';
	}
	//每行删除方法
	function deleteStealOilSms(stu_id){
		if (confirm("您确定要删除吗？")) {
			window.location="../ftlyInfoNew/deleteStealOilSms.shtml?stealOilSmsInfo.stu_id="+stu_id;				
		}			
	}
	//批量删除
	function allDelete(){
		var carsChoice = document.getElementsByName("carChoice");
	    var stuList="";
	   	for(var i=0; i<carsChoice.length; i++) {
	       if(carsChoice[i].checked==true) {
	           if(stuList=="") {
	        	   stuList = carsChoice[i].value.split("||")[0] ;
	           } else {
	        	   stuList=stuList + "," + carsChoice[i].value.split("||")[0] ;
	           }
	       }
	   	}
	   	var carArr=unique(stuList.split(","));
		var newstr="";
		if(typeof(carArr)!="undefined"){
			newstr=carArr.join(",");
		}
		
	   	if(newstr==""){
			alert("请选择记录进行操作！");
			return false;
		}
	  	if(confirm("确认删除所选记录？")) {
	  		window.location="../ftlyInfoNew/deleteStealOilSms.shtml?stealOilSmsInfo.stu_id="+newstr;	
	  	}
	}
	//全选checkbox
	function reWriteCheckBox(tdDiv,pid,row){
		return '<input name="carChoice" style="_margin-top:-3px;" value="' + row.cell[5]+'||'+ pid + '"  type="checkbox" />';
	}
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
	function setOrCancelSelect(obj) {
		 if(jQuery(obj).attr("checked")){
			jQuery("input[name='carChoice']").each(function(){
				jQuery("input[name='carChoice']").attr("checked","true");
			});
		}else{
			jQuery("input[name='carChoice']").each(function(){
				jQuery("input[name='carChoice']").removeAttr("checked"); 
			});
		}
	}
	
	//页面自适应
	$(function(){
		jQuery(window).resize(function(){
			testWidthHeight();
		});
		jQuery(window).load(function(){
			testWidthHeight();
		});
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
		 if(jQuery(window).width()>1310) {
			 jQuery("#tabAreaId").width(jQuery("#header").width()-30);
			jQuery(".flexigrid").width("100%");
		 }else {
			 jQuery("#tabAreaId").width(jQuery("#header").width()-30);
			jQuery(".flexigrid").width(jQuery("#tabAreaId").width()-10);
		} 
		
		 jQuery(".bDiv").height(h-320);
		
	}
	//计算控件宽高
	function testWidthHeight(){
		
		 autoWidthHeight();
		
		 /* jQuery(window).mk_autoresize({
				height_include: '#content',
				height_exclude: ['#header', '#footer'],
				height_offset: 0,
				width_include: ['#header', '#content', '#footer'],
				width_offset: 0}); */
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
	
	 /**
	  * Array unique function,同时将去掉null及undefined
	  * @param {Array} ary 需要进行unique的数组.
	  * @return {Array} 返回经过去重的新的数组，
	  * 不会修改原来的数组内容.
	  */
	 function unique(ary) {
	     var i = 0,
	         gid='_'+(+new Date)+Math.random(),
	         objs = [],
	         hash = {
	             'string': {},
	             'boolean': {},
	             'number': {}
	         }, p, l = ary.length,
	         ret = [];
	     for (; i < l; i++) {
	         p = ary[i];
	         if (p == null) continue;
	         tp = typeof p;
	         if (tp in hash) {
	             if (!(p in hash[tp])) {
	                 hash[tp][p] = 1;
	                 ret.push(p);
	             }
	         } else {
	             if (p[gid]) continue;
	             p[gid]=1;
	             objs.push(p);
	             ret.push(p);
	         }
	     }
	     for(i=0,l=objs.length;i<l;i++) {
	         p=objs[i];
	         p[gid]=undefined;
	         delete p[gid];
	     }
	     return ret;
	 }
	  
	  function formatSpecialChar(str) {
			return str.replace(/\\/g,"\\\\")
			          .replace(/%/g,"\\%")
			          .replace(/_/g,"\\_")
			          .replace(/％/g,"\\％")
			          .replace(/＿/g,"\\＿")
			          .replace(/^\s+|\s+$/g, '');
	}

</script>