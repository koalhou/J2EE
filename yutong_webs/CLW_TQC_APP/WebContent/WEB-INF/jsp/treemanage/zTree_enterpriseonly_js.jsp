<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script>
function getAsyncUrl() {
    return '<s:url value="/treemanage/getOrganizationTreeData.shtml" />';
};
	var setting = {
		treeId:'orgTree',
		async: {
			enable: true,
			url:getAsyncUrl
		},data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		},
		callback: {
			onClick: onClick,
			onAsyncSuccess:setDefaultLevel
		}
	};

	
	function onClick(event, treeId, treeNode) {
		mytreeonClick(event,treeId,treeNode);
	}

	function setDefaultLevel(){
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
	    // 获取树上所有节点
	    var nodes = treeObj.transformToArray(treeObj.getNodes());
	    for(var i = 0, len = nodes.length; i < len; i++) {
			if(nodes[i].level<3){
				treeObj.expandNode(nodes[i], true, false, false);
			}else{
				treeObj.expandNode(nodes[i], false, true, false);
			}
	    }
	}

	jQuery(document).ready(function(){
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});
</script>

