<%@page language="java" contentType="text/html;charset=utf-8"%>

<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>

<script>
	var curAsyncCount = 0;
	//树查询状态，只有数据返回成功之后方可进行第二次查询
	var checkoneclick = 0;
	
	function getAsyncUrl() {
		var url = "";
		url = '<s:url value="/runtimeSet/searchTreeNodes.shtml" />?sname='+ encodeURIComponent(encodeURIComponent(jQuery("#vehicleLntext").val().replace(/(^\s*)|(\s*$)/g, "")));
	    return url;
	};
	
	var setting = {
		treeId:'treeDemo',
		async: {
			enable: true,
			url:getAsyncUrl
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
			onClick: onClickCheBox,
			//onCheck: onCheck,
			beforeAsync: beforeAsync,
			onAsyncSuccess:searchTree
		},view: {
			addDiyDom: addDiyDom
		}
	};
	function addDiyDom(treeId, treeNode) {
		
	}

	function beforeAsync() {
		curAsyncCount++;
	}
	
	function onClickCheBox(event, treeId, treeNode) {
		var carList1 = new Array();
		carList1.push(treeNode.id);
		// 车辆信息列表
		decodeSelectV(carList1,true);
	}
	
	function onClick(event, treeId, treeNode) {
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		if(treeNode.checked) {
			// 当前是复选选择状态
			treeObj.checkNode(treeNode,false,true,true);
			treeNode.checkedOld = false;
		} else {
			// 当前是非选择状态
			treeObj.checkNode(treeNode,true,true,true);
			treeNode.checkedOld = true;
		}
	}
	
	// 复选框选中触发事件
	function onCheck(event, treeId, treeNode) {
		var carList = new Array();
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getChangeCheckedNodes();
		if(treeNode.checked) {
			for(var i = 0, len = nodes.length; i < len; i++) {
				nodes[i].checkedOld = true;
				if("pIcon" == nodes[i].iconSkin) {
					carList.push(nodes[i].id);
				}
			}
			
		} else {
			for(var i = 0, len = nodes.length; i < len; i++) {
				nodes[i].checkedOld = false;
				if("pIcon" == nodes[i].iconSkin) {
					carList.push(nodes[i].id);
				}
			}
		}
		// 车辆信息列表
		decodeSelectV(carList,treeNode.checked);
	}

	function check() {
		if (curAsyncCount > 0) {
			return false;
		}
		return true;
	}
		
	// 查询树
	function searchTree() {
		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var treeNodes = newTreeObj.transformToArray(newTreeObj.getNodes());
		if(treeNodes.length == 0) {
			jQuery("#treeDemo").html("<div style='text-align:center'>查询不到内容</div>");
		}
		
		if (!check()) {
			curAsyncCount=0;
			return;
		}
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}
</script>

