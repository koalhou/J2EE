<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/JQuery zTree v3.1/css/zTreeStyle/zTreeStyle.css'/>" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>
<style type="text/css">
ul.ztree {position:relative;margin-top: 0px;border: 0px solid #617775;float:left; display:block; width:100%; padding:0px;}
.ztree li button.pIcon_ico_close{margin-right:4px; background: url(../images/ico_folderClosed.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.pIcon_ico_open{margin-right:4px; background: url(../images/ico_folderOpened.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.pIcon_ico_docu{margin-right:4px; background: url(../images/ico_sRoute.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.online_ico_docu{margin-right:4px; background: url(../images/ico_SB_online.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.offline_ico_docu{margin-right:4px; background: url(../images/ico_SB_online.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
</style>
<script>
	var curAsyncCount = 0;

	function getAsyncUrl() {
	    return jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, "")!="" ? 
	    	    '<s:url value="/treemanage/searchTreeNodesWithoutOnline.shtml" />?name='+encodeURIComponent(encodeURIComponent(jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, ""))) : 
			    	'<s:url value="/treemanage/getTreeNodesWithoutOnline.shtml" />';;
	};
	
	var setting = {
		treeId:'orgTree',
		async: {
			enable: true,
			url:getAsyncUrl
		},
		/**
		check: {
			enable: true,
			nocheck :false,
			chkboxType : { "Y" : "ps", "N" : "ps" }
		},**/
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
			beforeAsync: beforeAsync,
			onAsyncSuccess:checkFirstCar
		}
	};

	function beforeAsync() {
		curAsyncCount++;
	}

	function onClick(event, treeId, treeNode) {
		if("pIcon" == treeNode.iconSkin) {
			mytreeonClick('0',treeNode.id);
			//alert("组织click，id=" + treeNode.id);
		} else {
			mytreeonClick('1',treeNode.id);
			//alert("车辆click，id=" + treeNode.id);
		}
	}

	//1、车辆运行日志-默认选中第一辆车
	function checkFirstCar() {
		var url = window.location.href;
		if(url.indexOf("carrun/ready") > 0){
		    var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		    // 获取树上所有节点
		    var nodes = treeObj.transformToArray(treeObj.getNodes());
		    for(var i = 0, len = nodes.length; i < len; i++) {
		         if("pIcon" != nodes[i].iconSkin) {
			         treeObj.selectNode(nodes[i],false);
			         mytreeonClick('1',nodes[i].id);
			         break;
			      }
		      }
		}

		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var treeNodes = newTreeObj.transformToArray(newTreeObj.getNodes());
		if(treeNodes.length == 0) {
			jQuery("#treeDemo").html("<div style='text-align:center'>查询不到内容</div>");
		}
		curAsyncCount = 0;
	}

	// 复选框选中触发事件
	/**
	function onCheck(event, treeId, treeNode) {
		var carList = new Array();
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);

		for(var i = 0, len = nodes.length; i < len; i++) {
			if("pIcon" != nodes[i].iconSkin) {
				carList.push(nodes[i].id);
			}
		}
		// 车辆信息列表
		alert(carList);
	}
	**/

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
	
	/**
	var openNodesId;
	var closeNodesId;
	var checkNodesId;

	function refreshTreeStatus() {
		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var refreshNodes = newTreeObj.transformToArray(newTreeObj.getNodes());
		if (null != openNodesId) {
			if(jQuery("#vehicleLn").val() == "") {
				for ( var j = 0, len = openNodesId.length; j < len; j++) {
					for ( var k = 0, lenNodes = refreshNodes.length; k < lenNodes; k++) {
						if (refreshNodes[k].id == openNodesId[j]) {
							newTreeObj.expandNode(refreshNodes[k], true, false, false,false);
						}
					}
				}
			} else {
				for ( var m = 0, closeLen = closeNodesId.length; m < closeLen; m++) {
					for ( var n = 0, lenNodes1 = refreshNodes.length; n < lenNodes1; n++) {
						if (refreshNodes[n].id == closeNodesId[m]) {
							newTreeObj.expandNode(refreshNodes[n], false, false, false,false);
						}
					}
				}
			}
			
		}

		if(null != checkNodesId) {
			for( var m =0, lenChecked = checkNodesId.length; m < lenChecked; m++) {
				for ( var n = 0, lenNodesNew = refreshNodes.length; n < lenNodesNew; n++) {
					if (refreshNodes[n].id == checkNodesId[m]) {
						newTreeObj.checkNode(refreshNodes[n],true,false,false);
						refreshNodes[n].checkedOld = true;
					}
				}
			}
		}
	}

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

