<%@page language="java" contentType="text/html;charset=utf-8"%>

<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>

<script>
	var curAsyncCount = 0;
	//树查询状态，只有数据返回成功之后方可进行第二次查询
	var checkoneclick = 0;
	
	function getAsyncUrl() {
		var url = "";
		url = '<s:url value="/runtimeSet/searchTreeNodesNoSelectes.shtml" />?sname=';
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
			onClick: onClick,
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
	
	function onClick(event, treeId, treeNode) {
		
		//var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		//var nodes = treeObj.getChangeCheckedNodes();
		if("宇通通勤车"!=treeNode.name){
			onclicktree(treeNode.id,treeNode.name);	
		}else{
			return false ;
		}
		/*if(treeNode.checked) {
			// 当前是复选选择状态
			treeObj.checkNode(treeNode,false,true,true);
			treeNode.checkedOld = false;
		} else {
			// 当前是非选择状态
			treeObj.checkNode(treeNode,true,true,true);
			treeNode.checkedOld = true;
		}*/
	}
	
	// 复选框选中触发事件
	function onCheck(event, treeId, treeNode) {
		onclicktree(treeNode.id,treeNode.name);
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
			curAsyncCount=0;
			return;
		}
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}
</script>

