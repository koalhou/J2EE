<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/JQuery zTree v3.1/css/zTreeStyle/zTreeStyle.css'/>" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>
<style type="text/css">
ul.ztree {position:relative;margin-top: 0px; border: 0px solid #617775; float:left; display:block; width:100%; padding:0px; }
.ztree li button.pIcon_ico_close{margin-right:4px; background: url(../images/ico_folderClosed.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.pIcon_ico_open{margin-right:4px; background: url(../images/ico_folderOpened.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.pIcon_ico_docu{margin-right:4px; background: url(../images/ico_sRoute.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.online_ico_docu{margin-right:4px;width:31px;font-weight: bold;line-height: 16px; background: url(../images/online_ico_docu.png) no-repeat scroll 0 0 transparent; vertical-align:middle; *vertical-align:middle;color: #fff;}
.ztree li button.offline_ico_docu{margin-right:4px;width:31px;font-weight: bold;line-height: 16px; background: url(../images/ico_SB_offline.png) no-repeat scroll 0 0 transparent; vertical-align:middle; *vertical-align:middle;color: #fff;}

.ztree li span.button.icon01{
	margin:0; 
	background: url(../images/dvr2.png) no-repeat scroll 0 0 transparent; 
	vertical-align:middle; 
	*vertical-align:middle;
	display:inline-block;
	width:16px;
	height:16px;
	margin-left:-6px;
	cursor:pointer;
	}
.ztree li span.button.icon02{
	margin:0; 
	background: url(../images/dvr1.png) no-repeat scroll 0 0 transparent; 
	vertical-align:middle; 
	*vertical-align:middle;
	display:inline-block;
	width:16px;
	height:16px;
	margin-left:-6px;
	cursor:pointer;
	}

</style>
<script>
	var curAsyncCount = 0;

	function getAsyncUrl() {
	    return jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, "")!="" ? 
	    	    '<s:url value="/treemanage/searchRepareTreeNodesWithoutOnline.shtml" />?name='+encodeURIComponent(encodeURIComponent(jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, ""))) : 
			    	'<s:url value="/treemanage/getRepareTreeNodesWithoutOnline.shtml" />';;
	};
	
	var setting = {
		treeId:'orgTree',
		async: {
			enable: true,
			url:getAsyncUrl
		},
		check: {
			enable: true,
			nocheck :false,
			chkboxType : { "Y" : "ps", "N" : "ps" }
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		},
		callback: {
			onClick: onClick,
			onCheck: onCheck,
			beforeAsync: beforeAsync,
			onAsyncSuccess:onAsyncSuccess
		},
		view: {
			addDiyDom: addDiyDom
		}
	};
	
	
	/*
	function getAsyncUrlRepare() {
	    return jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, "")!="" ? 
	    	    '<s:url value="/treemanage/searchTreeNodesWithoutOnline.shtml" />?name='+encodeURIComponent(encodeURIComponent(jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, ""))) : 
			    	'<s:url value="/treemanage/getTreeNodesWithoutOnlineRepare.shtml" />';;
	};
	
	
	var settingRepare = {
			treeId:'orgTree',
			async: {
				enable: true,
				url:getAsyncUrlRepare
			},
			check: {
				enable: true,
				nocheck :false,
				chkboxType : { "Y" : "ps", "N" : "ps" }
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			},
			callback: {
				onClick: onClick,
				onCheck: onCheck,
				beforeAsync: beforeAsync,
				onAsyncSuccess:onAsyncSuccess
			},
			view: {
				addDiyDom: addDiyDom
			}
		};
	
	*/
	function addDiyDom(treeId, treeNode) {
		var aObj = jQuery("#" + treeNode.tId + "_ico");
		if("pIcon" != treeNode.iconSkin) {
			jQuery(aObj).html(treeNode.vehicleCode);
		}
	}
	function beforeAsync() {
		curAsyncCount++;
	}

	function onClick(event, treeId, treeNode) {
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		if(treeNode.checked) {
			// 当前是复选选择状态
			treeObj.checkNode(treeNode,false,true,true);
		} else {
			// 当前是非选择状态
			treeObj.checkNode(treeNode,true,true,true);
		}
	}
	
	// 复选框选中触发事件
	function onCheck(event, treeId, treeNode) {
		var carList = new Array();
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
// 		var nodes = treeObj.getCheckedNodes(true);
// 		var url = window.location.href;
		
// 		if(url.indexOf("photomonitor") > 0){//照片管理相关 update by fxy 2012.11.13
//			照片管理的列表查询方法
// 			searchList();	
// 		 }else{//2012.11.13之前执行的业务处理
// 			 for(var i = 0, len = nodes.length; i < len; i++) {
// 					if("pIcon" != nodes[i].iconSkin) {
//						update by fxy(转换为 'A','B','C' 形式的字符串  为后台拼装SQL准备)
//						carList.push(nodes[i].id);
// 						carList.push("'"+nodes[i].id+"'");} //end if
// 				}//end for
// 				mytreeonClick(carList);		 
// 		 }	 
		var node = treeObj.getCheckedNodes();
		for(var i=0;i<node.length;i++){
			if("pIcon" != node[i].iconSkin) {
				carList.push(node[i].id);
			}
		}
		//如果组织下面没有车辆，则默认一个值“x”，让右侧列表查询不到数据
		if(carList.length==0) {
			carList.push('x');
		}
		mytreeonClick(carList);
	}

	function check() {
		if (curAsyncCount > 0) {
			return false;
		}
		return true;
	}

	// 查询树
	function searchTree() {
		if (!check()) {
			return;
		}
		// 初始化状态
		/**
		openNodesId = new Array();
		checkNodesId = new Array();
		closeNodesId = new Array();
		**/
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}
	
	jQuery(document).ready( function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});

	function onAsyncSuccess() {
		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var treeNodes = newTreeObj.transformToArray(newTreeObj.getNodes());
		/* var content = window.location.href;
		if (content.indexOf("photomonitor") >= 0){
		    for(var i = 0, len = treeNodes.length; i < len; i++) {
		    	if("pIcon" != treeNodes[i].iconSkin) {
					jQuery("#" + treeNodes[i].tId + "_ico").html(treeNodes[i].vehicleCode);
		    	}
		    }
		} */
		
		if(treeNodes.length == 0) {
			jQuery("#treeDemo").html("<div style='text-align:center'>查询不到内容</div>");
		}
		curAsyncCount = 0;
	}
	
	/**
	var openNodesId;
	var closeNodesId;
	var checkNodesId;

	function refreshTree() {
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var localNodes = treeObj.transformToArray(treeObj.getNodes());
		openNodesId = new Array();
		checkNodesId = new Array();
		closeNodesId = new Array();
		
		for ( var i = 0, l = localNodes.length; i < l; i++) {
			if (localNodes[i].open) {
				openNodesId.push(localNodes[i].id);
			} else {
				if("pIcon" == localNodes[i].iconSkin) {
					closeNodesId.push(localNodes[i].id);
				}
			}
			
			if (localNodes[i].checked) {
				checkNodesId.push(localNodes[i].id);
			} 
		}

		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}
	**/
	
</script>

